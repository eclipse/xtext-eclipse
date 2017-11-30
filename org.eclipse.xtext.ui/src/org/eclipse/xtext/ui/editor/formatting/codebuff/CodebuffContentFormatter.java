/*******************************************************************************
 * Copyright (c) 2017 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.formatting.codebuff;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.osgi.framework.Bundle;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author Holger Schill - Initial contribution and API
 */
public class CodebuffContentFormatter implements IContentFormatter {
	// Classes to use
	private static final String CODEBUFF_TRAINER = "org.antlr.codebuff.Trainer";
	private static final String INPUT_DOCUMENT = "org.antlr.codebuff.InputDocument";
	private static final String FEATURE_META_DATA = "org.antlr.codebuff.FeatureMetaData";
	private static final String LANG_DESCRIPTOR = "org.antlr.codebuff.misc.LangDescriptor";
	private static final String CODEBUFFPARSER = "org.antlr.codebuff.CodebuffParser";
	private static final String CODEBUFFLEXER = "org.antlr.codebuff.CodebuffLexer";
	private static final String FORMATTER = "org.antlr.codebuff.Formatter";
	private static final String CORPUS = "org.antlr.codebuff.Corpus";
	private static final String TOOLCLASS = "org.antlr.codebuff.Tool";
	
	// Folders and libs
	private static final String CORPUSDIR = "corpus";
	private static final String ANTLR4GEN = "antlr4gen";
	private static final String TARGETFOLDER = ANTLR4GEN + "/target/";
	private static final String CODEBUFF = "codebuff-1.5.1.jar";

	@Inject
	@Named(Constants.LANGUAGE_NAME)
	String languageName;

	@Inject
	@Named(Constants.FILE_EXTENSIONS)
	String fileExtension;

	@Inject
	@Named("COMMENTRULE")
	String commentRule;
	
	@Inject
	@Named("INDENT")
	int indent;

	@Override
	public void format(IDocument document, IRegion region) {
		ClassLoader loader = initializeLoader();
		XtextDocument xtextDoc = (XtextDocument) document;
		try {
			Class<?> lanDescClass = loader.loadClass(LANG_DESCRIPTOR);
			Constructor<?> langDescConstructor = lanDescClass.getConstructor(String.class, String.class, String.class, Class.class,
					Class.class, String.class, int.class, int.class);
			String fileRegex = ".*\\." + fileExtension;
			Class<?> tool = loader.loadClass(TOOLCLASS);
			Method getFileNames = tool.getDeclaredMethod("getFilenames", File.class, String.class);
			URI resourceURI = xtextDoc.getResourceURI();
			URI projectURI = resourceURI.trimSegments(resourceURI.segmentCount() - 2);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject project = root.getProject(projectURI.segment(1));
			String projectPath = project.getLocationURI().getPath();
			IPath file = root.getFile(new Path(resourceURI.toPlatformString(true))).getLocation();
			String corpusDirString = projectPath + "/" + CORPUSDIR;
			File corpusDirFile = new File(corpusDirString);
			Class<?> parser = loader.loadClass(CODEBUFFPARSER);
			Class<?> lexer = loader.loadClass(CODEBUFFLEXER);
			Field commentField = lexer.getDeclaredField(commentRule);
			int commentRuleValue = commentField.getInt(null);
			Field ruleNamesField = parser.getDeclaredField("ruleNames");
			String[] ruleNames = (String[]) ruleNamesField.get(null);
			String rootRule = ruleNames[0];
			Object lanDesc = langDescConstructor.newInstance("XtextGEN", corpusDirString, fileRegex, lexer, parser, rootRule, indent,
					commentRuleValue);
			if (corpusDirFile.exists()) {
				Object allFiles = getFileNames.invoke(null, corpusDirFile, fileRegex);
				Method load = tool.getDeclaredMethod("load", List.class, lanDescClass);
				Object documents = load.invoke(null, allFiles, lanDesc);
				Method parse = tool.getDeclaredMethod("parse", String.class, lanDescClass);
				Object testDoc = parse.invoke(null, file.toOSString(), lanDesc);
				Class<?> corpusClass = loader.loadClass(CORPUS);
				Constructor<?> corpusConstructor = corpusClass.getConstructor(List.class, lanDescClass);
				Object corpus = corpusConstructor.newInstance(documents, lanDesc);
				Method train = corpusClass.getDeclaredMethod("train");
				train.invoke(corpus);
				Class<?> formatterClass = loader.loadClass(FORMATTER);
				Class<?> featureMetaDataClass = loader.loadClass(FEATURE_META_DATA);
				Object array = java.lang.reflect.Array.newInstance(featureMetaDataClass, 0);
				Constructor<?> formatterConstructor = formatterClass.getConstructor(corpusClass, int.class, int.class, array.getClass(),
						array.getClass());
				Field default_k = formatterClass.getDeclaredField("DEFAULT_K");
				Class<?> trainer = loader.loadClass(CODEBUFF_TRAINER);
				Field features_inject_ws = trainer.getDeclaredField("FEATURES_INJECT_WS");
				Field features_hpos = trainer.getDeclaredField("FEATURES_HPOS");
				Object formatter = formatterConstructor.newInstance(corpus, indent, default_k.get(null), features_inject_ws.get(null),
						features_hpos.get(null));
				Class<?> inputDocClass = loader.loadClass(INPUT_DOCUMENT);
				Method format = formatterClass.getDeclaredMethod("format", inputDocClass, boolean.class);
				String result = (String) format.invoke(formatter, testDoc, false);
				document.set(result);
			}
		} catch (Exception e) {
			throw new WrappedException(e);
		}

	}

	protected ClassLoader initializeLoader() {
		try {
			Bundle bundle = Platform.getBundle(languageName.substring(0, languageName.lastIndexOf(".")));
			URL targetFolder = bundle.getEntry(TARGETFOLDER);
			URL codebuff = bundle.getEntry(CODEBUFF);

			return new URLClassLoader(new URL[] { codebuff, targetFolder }, null);
		} catch (Exception e) {
			throw new WrappedException(e);
		}
	}

	@Override
	public IFormattingStrategy getFormattingStrategy(String contentType) {
		return null;
	}

}
