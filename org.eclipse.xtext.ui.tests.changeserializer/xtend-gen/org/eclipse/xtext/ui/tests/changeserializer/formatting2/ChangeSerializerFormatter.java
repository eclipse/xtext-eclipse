/**
 * generated by Xtext unspecified
 */
package org.eclipse.xtext.ui.tests.changeserializer.formatting2;

import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.formatting2.AbstractFormatter2;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.Element;
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.Import;
import org.eclipse.xtext.ui.tests.changeserializer.changeSerializer.PackageDeclaration;
import org.eclipse.xtext.ui.tests.changeserializer.services.ChangeSerializerGrammarAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class ChangeSerializerFormatter extends AbstractFormatter2 {
  @Inject
  @Extension
  private ChangeSerializerGrammarAccess _changeSerializerGrammarAccess;
  
  protected void _format(final PackageDeclaration packageDeclaration, @Extension final IFormattableDocument document) {
    EList<Import> _imports = packageDeclaration.getImports();
    for (final Import _import : _imports) {
      document.<Import>format(_import);
    }
    EList<Element> _contents = packageDeclaration.getContents();
    for (final Element element : _contents) {
      document.<Element>format(element);
    }
  }
  
  protected void _format(final Element element, @Extension final IFormattableDocument document) {
    EList<Element> _contents = element.getContents();
    for (final Element _element : _contents) {
      document.<Element>format(_element);
    }
  }
  
  public void format(final Object element, final IFormattableDocument document) {
    if (element instanceof XtextResource) {
      _format((XtextResource)element, document);
      return;
    } else if (element instanceof Element) {
      _format((Element)element, document);
      return;
    } else if (element instanceof PackageDeclaration) {
      _format((PackageDeclaration)element, document);
      return;
    } else if (element instanceof EObject) {
      _format((EObject)element, document);
      return;
    } else if (element == null) {
      _format((Void)null, document);
      return;
    } else if (element != null) {
      _format(element, document);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(element, document).toString());
    }
  }
}
