/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.ui.editor.bracketmatching;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Set;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.xtend.lib.annotations.Delegate;
import org.eclipse.xtext.ide.editor.bracketmatching.BracePair;
import org.eclipse.xtext.ide.editor.bracketmatching.IBracePairProvider;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;

/**
 * @author kosyakov - Initial contribution and API
 * 
 * @since 2.8
 */
@SuppressWarnings("all")
public class BracePairMatcher implements ICharacterPairMatcher {
  @Delegate
  private DefaultCharacterPairMatcher characterPairMatcher;
  
  @Inject
  public void setBracePairProvider(final IBracePairProvider bracePairProvider) {
    final ArrayList<Character> chars = this.getChars(bracePairProvider);
    DefaultCharacterPairMatcher _defaultCharacterPairMatcher = new DefaultCharacterPairMatcher(((char[])Conversions.unwrapArray(chars, char.class)));
    this.characterPairMatcher = _defaultCharacterPairMatcher;
  }
  
  protected ArrayList<Character> getChars(final IBracePairProvider bracePairProvider) {
    ArrayList<Character> _xblockexpression = null;
    {
      final ArrayList<Character> chars = CollectionLiterals.<Character>newArrayList();
      Set<BracePair> _pairs = bracePairProvider.getPairs();
      for (final BracePair pair : _pairs) {
        if (((pair.getLeftBrace().length() == 1) && (pair.getRightBrace().length() == 1))) {
          char _charAt = pair.getLeftBrace().charAt(0);
          chars.add(Character.valueOf(_charAt));
          char _charAt_1 = pair.getRightBrace().charAt(0);
          chars.add(Character.valueOf(_charAt_1));
        } else {
          throw new IllegalStateException((("Brace pair is invalid: " + pair) + "; left and right braces should have length of one character."));
        }
      }
      _xblockexpression = chars;
    }
    return _xblockexpression;
  }
  
  public void clear() {
    this.characterPairMatcher.clear();
  }
  
  public void dispose() {
    this.characterPairMatcher.dispose();
  }
  
  public int getAnchor() {
    return this.characterPairMatcher.getAnchor();
  }
  
  public IRegion match(final IDocument document, final int offset) {
    return this.characterPairMatcher.match(document, offset);
  }
}
