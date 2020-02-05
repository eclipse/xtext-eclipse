/*******************************************************************************
 * Copyright (c) 2014, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xbase.compiler

import java.util.Set
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.generator.InMemoryFileSystemAccess

/**
 * @author Sven Efftinge - Initial contribution and API
 * @noextend This class is not intended to be subclassed by clients.
 * @noreference This class is not intended to be referenced by clients.
 * 
 * @since 2.7
 * @deprecated Use org.eclipse.xtext.xbase.testing.RegisteringFileSystemAccess instead
 */
@Deprecated
class RegisteringFileSystemAccess extends InMemoryFileSystemAccess { 

	/**
	 * @noreference This class is not intended to be referenced by clients.
	 */
	@Data static class GeneratedFile {
		String path
		String javaClassName
		CharSequence contents
	}
	@Accessors val Set<GeneratedFile> generatedFiles = newHashSet()
	@Accessors String projectName;
	
	override generateFile(String fileName, String outputConfigurationName, CharSequence contents) {
		super.generateFile(fileName, outputConfigurationName, contents)
		val path = getPath(fileName, outputConfigurationName)
		val javaName = if (fileName.endsWith(".java")) {
				fileName.substring(0, fileName.length - 5).replace('/', '.')
			}
		generatedFiles.add(new RegisteringFileSystemAccess.GeneratedFile(path, javaName, contents))
	}

	protected def getPath(String fileName, String outputConfigurationName) {
		val path = pathes.get(outputConfigurationName)
		return "/" + projectName + "/" + path + "/" + fileName
	}
	
}