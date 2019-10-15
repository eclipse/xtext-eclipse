/**
 * generated by Xtext
 */
package org.eclipse.xtext.xbase.annotations.ui.labeling;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#label-provider
 */
@SuppressWarnings("all")
public class XbaseWithAnnotationsLabelProvider extends XbaseLabelProvider {
  @Inject
  public XbaseWithAnnotationsLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
}
