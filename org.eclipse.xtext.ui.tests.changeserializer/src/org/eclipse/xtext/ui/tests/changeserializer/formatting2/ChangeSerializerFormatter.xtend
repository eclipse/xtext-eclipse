/*
 * generated by Xtext unspecified
 */
package org.eclipse.xtext.ui.tests.changeserializer.formatting2

import com.google.inject.Inject
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.Element
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.Import
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration
import org.eclipse.xtext.ui.tests.changeserializer.services.ChangeSerializerGrammarAccess

class ChangeSerializerFormatter extends AbstractFormatter2 {
	
	@Inject extension ChangeSerializerGrammarAccess

	def dispatch void format(PackageDeclaration packageDeclaration, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (Import _import : packageDeclaration.getImports()) {
			_import.format;
		}
		for (Element element : packageDeclaration.getContents()) {
			element.format;
		}
	}

	def dispatch void format(Element element, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (Element _element : element.getContents()) {
			_element.format;
		}
	}
	
	// TODO: implement for 
}
