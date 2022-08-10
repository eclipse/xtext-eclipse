package org.eclipse.xtext.ui.tests.editor.model.edit;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.parser.antlr.Lexer;
import org.eclipse.xtext.parser.antlr.internal.InternalXtextLexer;
import org.eclipse.xtext.resource.OutdatedStateManager;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.service.OperationCanceledManager;
import org.eclipse.xtext.ui.editor.model.DocumentTokenSource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.ITextEditComposer;
import org.eclipse.xtext.ui.tests.FoldingTestLanguageStandaloneSetup;
import org.eclipse.xtext.ui.tests.folding.Element;
import org.eclipse.xtext.ui.tests.folding.FoldingFactory;
import org.eclipse.xtext.ui.tests.folding.FoldingModel;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.inject.Provider;

public class FoldingTestLanguageDocumentModifyTest extends AbstractXtextTests {

	private Resource resource;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		with(FoldingTestLanguageStandaloneSetup.class);
	}

	@Test
	public void testAddElement() throws Exception {
		final String model = text( //
				"element Root", //
				" element child1 end // C1", //
				" element child2 end // C2", //
				" // C3", //
				"end" //
		);
		final IXtextDocument document = createDocument(model);
		document.modify(new IUnitOfWork.Void<XtextResource>() {
			@Override
			public void process(XtextResource state) throws Exception {
				EList<EObject> contents = state.getContents();
				FoldingModel foldingModel = (FoldingModel) contents.get(0);
				EList<Element> elements = foldingModel.getElements();
				Element rootElement = elements.get(0);
				EList<Element> subelements = rootElement.getSubelements();
				Element newElement = FoldingFactory.eINSTANCE.createElement();
				newElement.setName("newElement");
				subelements.add(1, newElement);
			}
		});
		final String expected = text( //
				"element Root", //
				" element child1 end // C1", //
				" element newElement end", //
				" element child2 end // C2", //
				" // C3", //
				"end" //
		);
		String result = document.get();
		assertEquals(expected, result);
	}

	@Test
	public void testMoveElement() throws Exception {
		final String model = text( //
				"element Root", //
				" element child1 end // C1", //
				" element child2 end // C2", //
				" // C3", //
				"end" //
		);
		final IXtextDocument document = createDocument(model);
		document.modify(new IUnitOfWork.Void<XtextResource>() {
			@Override
			public void process(XtextResource state) throws Exception {
				EList<EObject> contents = state.getContents();
				FoldingModel foldingModel = (FoldingModel) contents.get(0);
				EList<Element> elements = foldingModel.getElements();
				Element rootElement = elements.get(0);
				EList<Element> subelements = rootElement.getSubelements();
				subelements.move(1, 0);
			}
		});
		final String expected = text( //
				"element Root", //
				" element child2 end // C2", //
				" element child1 end // C1", //
				" // C3", //
				"end" //
		);
		String result = document.get();
		assertEquals(expected, result);
	}

	@Test
	public void testMoveElement2() throws Exception {
		final String model = text( //
				"element Root", //
				" element child1", //
				"   element child11 end // C1", //
				"   element child12 end // C2", //
				" end", //
				"end" //
		);
		final IXtextDocument document = createDocument(model);
		document.modify(new IUnitOfWork.Void<XtextResource>() {
			@Override
			public void process(XtextResource state) throws Exception {
				EList<EObject> contents = state.getContents();
				FoldingModel foldingModel = (FoldingModel) contents.get(0);
				EList<Element> elements = foldingModel.getElements();
				Element rootElement = elements.get(0);
				EList<Element> subelements = rootElement.getSubelements();
				Element element = subelements.get(0);
				EList<Element> subelements2 = element.getSubelements();
				subelements2.move(0, 1);
			}
		});
		final String expected = text( //
				"element Root", //
				" element child1", //
				"   element child12 end // C2", //
				"   element child11 end // C1", //
				" end", //
				"end" //
		);
		String result = document.get();
		assertEquals(expected, result);
	}

	private IXtextDocument createDocument(String model) throws Exception {
		resource = getResource(new StringInputStream(model));
		DocumentTokenSource tokenSource = new DocumentTokenSource();
		tokenSource.setLexer(new Provider<Lexer>() {
			@Override
			public Lexer get() {
				return new InternalXtextLexer();
			}
		});

		final XtextDocument document = new XtextDocument(tokenSource, get(ITextEditComposer.class), new OutdatedStateManager(),
				new OperationCanceledManager()) {
			@Override
			public <T> T internalModify(IUnitOfWork<T, XtextResource> work) {
				try {
					return work.exec((XtextResource) resource);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
		document.set(model);
		return document;
	}

	private String text(String... lines) {
		return Joiner.on(System.lineSeparator()).join(lines);
	}
}
