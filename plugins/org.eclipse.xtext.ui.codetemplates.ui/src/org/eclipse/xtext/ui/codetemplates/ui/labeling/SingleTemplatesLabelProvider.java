/*
* generated by Xtext
*/
package org.eclipse.xtext.ui.codetemplates.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.google.inject.Inject;

/**
 * Provides labels for a EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
public class SingleTemplatesLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	public SingleTemplatesLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

/*
	//Labels and icons can be computed like this:
	
	String text(MyModel ele) {
	  return "my "+ele.getName();
	}
	 
    String image(MyModel ele) {
      return "MyModel.gif";
    }
*/
}
