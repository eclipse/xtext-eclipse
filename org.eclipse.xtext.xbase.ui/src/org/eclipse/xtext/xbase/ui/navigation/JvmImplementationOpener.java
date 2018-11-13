/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.navigation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NodeFinder;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.manipulation.SharedASTProviderCore;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jdt.internal.corext.dom.Bindings;
import org.eclipse.jdt.internal.corext.util.JdtFlags;
import org.eclipse.jdt.internal.corext.util.Messages;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.text.JavaElementProvider;
import org.eclipse.jdt.ui.JavaElementLabels;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jdt.ui.text.IJavaPartitions;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.AbstractInformationControlManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.xbase.ui.hierarchy.HierarchyInformationPresenter;

import com.google.common.collect.Lists;

/**
 * @author Holger Schill - Initial contribution and API
 */
@SuppressWarnings("restriction")
public class JvmImplementationOpener {

	private final static Logger log = Logger.getLogger(JvmImplementationOpener.class);

	/**
	 * Main parts of the logic is taken from {@link org.eclipse.jdt.internal.ui.javaeditor.JavaElementImplementationHyperlink}
	 *
	 * @param element - Element to show implementations for
	 * @param textviewer - Viewer to show hierarchy view on
	 * @param region - Region where to show hierarchy view
	 */
	public void openImplementations(final IJavaElement element, ITextViewer textviewer, IRegion region){
		if (element instanceof IMethod) {
			ITypeRoot typeRoot = ((IMethod) element).getTypeRoot();
			CompilationUnit ast = SharedASTProviderCore.getAST(typeRoot, SharedASTProviderCore.WAIT_YES, null);
			if (ast == null) {
				openQuickHierarchy(textviewer,element,region);
				return;
			}
			try {
				ISourceRange nameRange = ((IMethod) element).getNameRange();
				ASTNode node = NodeFinder.perform(ast, nameRange);
				ITypeBinding parentTypeBinding = null;
				if (node instanceof SimpleName) {
					ASTNode parent = node.getParent();
					if (parent instanceof MethodInvocation) {
						Expression expression = ((MethodInvocation) parent).getExpression();
						if (expression == null) {
							parentTypeBinding = Bindings.getBindingOfParentType(node);
						} else {
							parentTypeBinding = expression.resolveTypeBinding();
						}
					} else if (parent instanceof SuperMethodInvocation) {
						// Directly go to the super method definition
						openEditor(element);
						return;
					} else if (parent instanceof MethodDeclaration) {
						parentTypeBinding = Bindings.getBindingOfParentType(node);
					}
				}
				final IType type = parentTypeBinding != null ? (IType) parentTypeBinding.getJavaElement() : null;
				if (type == null) {
					openQuickHierarchy(textviewer,element,region);
					return;
				}

				final String earlyExitIndicator = "EarlyExitIndicator";
				final ArrayList<IJavaElement> links = Lists.newArrayList();
				IRunnableWithProgress runnable = new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						if (monitor == null) {
							monitor = new NullProgressMonitor();
						}
						try {
							String methodLabel = JavaElementLabels.getElementLabel(element,
									JavaElementLabels.DEFAULT_QUALIFIED);
							monitor.beginTask(Messages.format("Searching for implementors of  ''{0}''", methodLabel),
									100);
							SearchRequestor requestor = new SearchRequestor() {
								@Override
								public void acceptSearchMatch(SearchMatch match) throws CoreException {
									if (match.getAccuracy() == SearchMatch.A_ACCURATE) {
										IJavaElement element = (IJavaElement) match.getElement();
										if (element instanceof IMethod && !JdtFlags.isAbstract((IMethod) element)) {
											links.add(element);
											if (links.size() > 1) {
												throw new OperationCanceledException(earlyExitIndicator);
											}
										}
									}
								}
							};
							int limitTo = IJavaSearchConstants.DECLARATIONS
									| IJavaSearchConstants.IGNORE_DECLARING_TYPE
									| IJavaSearchConstants.IGNORE_RETURN_TYPE;
							SearchPattern pattern = SearchPattern.createPattern(element, limitTo);
							Assert.isNotNull(pattern);
							SearchParticipant[] participants = new SearchParticipant[] { SearchEngine
									.getDefaultSearchParticipant() };
							SearchEngine engine = new SearchEngine();
							engine.search(pattern, participants, SearchEngine.createHierarchyScope(type), requestor,
									SubMonitor.convert(monitor, 100));

							if (monitor.isCanceled()) {
								throw new InterruptedException();
							}
						} catch (OperationCanceledException e) {
							throw new InterruptedException(e.getMessage());
						} catch (CoreException e) {
							throw new InvocationTargetException(e);
						} finally {
							monitor.done();
						}
					}
				};

				try {
					PlatformUI.getWorkbench().getProgressService().busyCursorWhile(runnable);
				} catch (InvocationTargetException e) {
					IStatus status = new Status(
							IStatus.ERROR,
							JavaPlugin.getPluginId(),
							IStatus.OK,
							Messages.format(
									"An error occurred while searching for implementations of method ''{0}''. See error log for details.",
									element.getElementName()), e.getCause());
					JavaPlugin.log(status);
					ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
							"Open Implementation", "Problems finding implementations.", status);
				} catch (InterruptedException e) {
					if (e.getMessage() != earlyExitIndicator) {
						return;
					}
				}

				if (links.size() == 1) {
					openEditor(links.get(0));
				} else {
					openQuickHierarchy(textviewer,element,region);
				}

			} catch (JavaModelException e) {
				log.error("An error occurred while searching for implementations", e.getCause());
			} catch (PartInitException e) {
				log.error("An error occurred while searching for implementations", e.getCause());
			}
		}
	}

	protected IEditorPart openEditor(final IJavaElement element) throws JavaModelException, PartInitException {
		return JavaUI.openInEditor(element);
	}

	protected void openQuickHierarchy(ITextViewer textViewer, IJavaElement element, IRegion region) {
		HierarchyInformationPresenter presenter = new HierarchyInformationPresenter((ISourceViewer) textViewer,
				element, region);
		presenter.setDocumentPartitioning(IDocumentExtension3.DEFAULT_PARTITIONING);
		presenter.setAnchor(AbstractInformationControlManager.ANCHOR_GLOBAL);
		IInformationProvider provider = new JavaElementProvider(null, false);
		presenter.setInformationProvider(provider, IDocument.DEFAULT_CONTENT_TYPE);
		presenter.setInformationProvider(provider, IJavaPartitions.JAVA_DOC);
		presenter.setInformationProvider(provider, IJavaPartitions.JAVA_MULTI_LINE_COMMENT);
		presenter.setInformationProvider(provider, IJavaPartitions.JAVA_SINGLE_LINE_COMMENT);
		presenter.setInformationProvider(provider, IJavaPartitions.JAVA_STRING);
		presenter.setInformationProvider(provider, IJavaPartitions.JAVA_CHARACTER);
		presenter.setSizeConstraints(50, 20, true, false);
		presenter.install(textViewer);
		presenter.showInformation();
	}
}
