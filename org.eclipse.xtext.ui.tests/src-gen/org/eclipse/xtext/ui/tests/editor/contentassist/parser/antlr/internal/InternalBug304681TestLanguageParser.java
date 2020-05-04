package org.eclipse.xtext.ui.tests.editor.contentassist.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.IUnorderedGroupHelper.UnorderedGroupState;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.ui.tests.editor.contentassist.services.Bug304681TestLanguageGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalBug304681TestLanguageParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'1'", "'2'", "'package'", "';'", "'enabled'", "'abstract'", "'object'", "'extends'", "'{'", "'shortDescription'", "'longDescription'", "'serialUID'", "'cloneable'", "'before'", "'after'", "'optionalLoop'", "'mandatoryLoop'", "'}'", "'Attribute'", "'Reference'", "'*'", "'required'", "'technical'", "'constraint'", "'parameters'", "'message'"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalBug304681TestLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalBug304681TestLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalBug304681TestLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "InternalBug304681TestLanguage.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */

     	private Bug304681TestLanguageGrammarAccess grammarAccess;

        public InternalBug304681TestLanguageParser(TokenStream input, Bug304681TestLanguageGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected Bug304681TestLanguageGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalBug304681TestLanguage.g:71:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalBug304681TestLanguage.g:71:46: (iv_ruleModel= ruleModel EOF )
            // InternalBug304681TestLanguage.g:72:2: iv_ruleModel= ruleModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getModelRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleModel; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalBug304681TestLanguage.g:78:1: ruleModel returns [EObject current=null] : ( (otherlv_0= '1' ( (lv_definition_1_0= rulePackageDefinition ) ) ) | (otherlv_2= '2' ( (lv_definition_3_0= rulePackageDefinition2 ) ) ) ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_definition_1_0 = null;

        EObject lv_definition_3_0 = null;



        	enterRule();

        try {
            // InternalBug304681TestLanguage.g:84:2: ( ( (otherlv_0= '1' ( (lv_definition_1_0= rulePackageDefinition ) ) ) | (otherlv_2= '2' ( (lv_definition_3_0= rulePackageDefinition2 ) ) ) ) )
            // InternalBug304681TestLanguage.g:85:2: ( (otherlv_0= '1' ( (lv_definition_1_0= rulePackageDefinition ) ) ) | (otherlv_2= '2' ( (lv_definition_3_0= rulePackageDefinition2 ) ) ) )
            {
            // InternalBug304681TestLanguage.g:85:2: ( (otherlv_0= '1' ( (lv_definition_1_0= rulePackageDefinition ) ) ) | (otherlv_2= '2' ( (lv_definition_3_0= rulePackageDefinition2 ) ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==11) ) {
                alt1=1;
            }
            else if ( (LA1_0==12) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalBug304681TestLanguage.g:86:3: (otherlv_0= '1' ( (lv_definition_1_0= rulePackageDefinition ) ) )
                    {
                    // InternalBug304681TestLanguage.g:86:3: (otherlv_0= '1' ( (lv_definition_1_0= rulePackageDefinition ) ) )
                    // InternalBug304681TestLanguage.g:87:4: otherlv_0= '1' ( (lv_definition_1_0= rulePackageDefinition ) )
                    {
                    otherlv_0=(Token)match(input,11,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getModelAccess().getDigitOneKeyword_0_0());
                      			
                    }
                    // InternalBug304681TestLanguage.g:91:4: ( (lv_definition_1_0= rulePackageDefinition ) )
                    // InternalBug304681TestLanguage.g:92:5: (lv_definition_1_0= rulePackageDefinition )
                    {
                    // InternalBug304681TestLanguage.g:92:5: (lv_definition_1_0= rulePackageDefinition )
                    // InternalBug304681TestLanguage.g:93:6: lv_definition_1_0= rulePackageDefinition
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getModelAccess().getDefinitionPackageDefinitionParserRuleCall_0_1_0());
                      					
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_definition_1_0=rulePackageDefinition();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getModelRule());
                      						}
                      						set(
                      							current,
                      							"definition",
                      							lv_definition_1_0,
                      							"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.PackageDefinition");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalBug304681TestLanguage.g:112:3: (otherlv_2= '2' ( (lv_definition_3_0= rulePackageDefinition2 ) ) )
                    {
                    // InternalBug304681TestLanguage.g:112:3: (otherlv_2= '2' ( (lv_definition_3_0= rulePackageDefinition2 ) ) )
                    // InternalBug304681TestLanguage.g:113:4: otherlv_2= '2' ( (lv_definition_3_0= rulePackageDefinition2 ) )
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getModelAccess().getDigitTwoKeyword_1_0());
                      			
                    }
                    // InternalBug304681TestLanguage.g:117:4: ( (lv_definition_3_0= rulePackageDefinition2 ) )
                    // InternalBug304681TestLanguage.g:118:5: (lv_definition_3_0= rulePackageDefinition2 )
                    {
                    // InternalBug304681TestLanguage.g:118:5: (lv_definition_3_0= rulePackageDefinition2 )
                    // InternalBug304681TestLanguage.g:119:6: lv_definition_3_0= rulePackageDefinition2
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getModelAccess().getDefinitionPackageDefinition2ParserRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_definition_3_0=rulePackageDefinition2();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getModelRule());
                      						}
                      						set(
                      							current,
                      							"definition",
                      							lv_definition_3_0,
                      							"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.PackageDefinition2");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRulePackageDefinition"
    // InternalBug304681TestLanguage.g:141:1: entryRulePackageDefinition returns [EObject current=null] : iv_rulePackageDefinition= rulePackageDefinition EOF ;
    public final EObject entryRulePackageDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackageDefinition = null;


        try {
            // InternalBug304681TestLanguage.g:141:58: (iv_rulePackageDefinition= rulePackageDefinition EOF )
            // InternalBug304681TestLanguage.g:142:2: iv_rulePackageDefinition= rulePackageDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPackageDefinitionRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePackageDefinition=rulePackageDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePackageDefinition; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePackageDefinition"


    // $ANTLR start "rulePackageDefinition"
    // InternalBug304681TestLanguage.g:148:1: rulePackageDefinition returns [EObject current=null] : ( () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject ) )* ) ;
    public final EObject rulePackageDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_namespace_2_0=null;
        Token otherlv_3=null;
        EObject lv_contents_4_0 = null;



        	enterRule();

        try {
            // InternalBug304681TestLanguage.g:154:2: ( ( () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject ) )* ) )
            // InternalBug304681TestLanguage.g:155:2: ( () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject ) )* )
            {
            // InternalBug304681TestLanguage.g:155:2: ( () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject ) )* )
            // InternalBug304681TestLanguage.g:156:3: () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject ) )*
            {
            // InternalBug304681TestLanguage.g:156:3: ()
            // InternalBug304681TestLanguage.g:157:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getPackageDefinitionAccess().getPackageDefinitionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,13,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getPackageDefinitionAccess().getPackageKeyword_1());
              		
            }
            // InternalBug304681TestLanguage.g:170:3: ( (lv_namespace_2_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:171:4: (lv_namespace_2_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:171:4: (lv_namespace_2_0= RULE_ID )
            // InternalBug304681TestLanguage.g:172:5: lv_namespace_2_0= RULE_ID
            {
            lv_namespace_2_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_namespace_2_0, grammarAccess.getPackageDefinitionAccess().getNamespaceIDTerminalRuleCall_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getPackageDefinitionRule());
              					}
              					setWithLastConsumed(
              						current,
              						"namespace",
              						lv_namespace_2_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getPackageDefinitionAccess().getSemicolonKeyword_3());
              		
            }
            // InternalBug304681TestLanguage.g:192:3: ( (lv_contents_4_0= ruleObject ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=15 && LA2_0<=17)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalBug304681TestLanguage.g:193:4: (lv_contents_4_0= ruleObject )
            	    {
            	    // InternalBug304681TestLanguage.g:193:4: (lv_contents_4_0= ruleObject )
            	    // InternalBug304681TestLanguage.g:194:5: lv_contents_4_0= ruleObject
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getPackageDefinitionAccess().getContentsObjectParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    lv_contents_4_0=ruleObject();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getPackageDefinitionRule());
            	      					}
            	      					add(
            	      						current,
            	      						"contents",
            	      						lv_contents_4_0,
            	      						"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.Object");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePackageDefinition"


    // $ANTLR start "entryRuleObject"
    // InternalBug304681TestLanguage.g:215:1: entryRuleObject returns [EObject current=null] : iv_ruleObject= ruleObject EOF ;
    public final EObject entryRuleObject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObject = null;



        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getObjectAccess().getUnorderedGroup_5()
        	);

        try {
            // InternalBug304681TestLanguage.g:219:2: (iv_ruleObject= ruleObject EOF )
            // InternalBug304681TestLanguage.g:220:2: iv_ruleObject= ruleObject EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getObjectRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleObject=ruleObject();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleObject; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleObject"


    // $ANTLR start "ruleObject"
    // InternalBug304681TestLanguage.g:229:1: ruleObject returns [EObject current=null] : ( ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* ) ) ) otherlv_27= '}' ) ;
    public final EObject ruleObject() throws RecognitionException {
        EObject current = null;

        Token lv_enabled_0_0=null;
        Token lv_abstract_1_0=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token lv_shortDescription_9_0=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token lv_longDescription_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token lv_serialVersionUID_15_0=null;
        Token otherlv_16=null;
        Token lv_cloneable_17_0=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        EObject lv_features_19_0 = null;

        EObject lv_features_21_0 = null;

        EObject lv_features_24_0 = null;

        EObject lv_features_26_0 = null;



        	enterRule();
        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getObjectAccess().getUnorderedGroup_5()
        	);

        try {
            // InternalBug304681TestLanguage.g:238:2: ( ( ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* ) ) ) otherlv_27= '}' ) )
            // InternalBug304681TestLanguage.g:239:2: ( ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* ) ) ) otherlv_27= '}' )
            {
            // InternalBug304681TestLanguage.g:239:2: ( ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* ) ) ) otherlv_27= '}' )
            // InternalBug304681TestLanguage.g:240:3: ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* ) ) ) otherlv_27= '}'
            {
            // InternalBug304681TestLanguage.g:240:3: ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )?
            int alt3=3;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            else if ( (LA3_0==16) ) {
                alt3=2;
            }
            switch (alt3) {
                case 1 :
                    // InternalBug304681TestLanguage.g:241:4: ( (lv_enabled_0_0= 'enabled' ) )
                    {
                    // InternalBug304681TestLanguage.g:241:4: ( (lv_enabled_0_0= 'enabled' ) )
                    // InternalBug304681TestLanguage.g:242:5: (lv_enabled_0_0= 'enabled' )
                    {
                    // InternalBug304681TestLanguage.g:242:5: (lv_enabled_0_0= 'enabled' )
                    // InternalBug304681TestLanguage.g:243:6: lv_enabled_0_0= 'enabled'
                    {
                    lv_enabled_0_0=(Token)match(input,15,FollowSets000.FOLLOW_7); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_enabled_0_0, grammarAccess.getObjectAccess().getEnabledEnabledKeyword_0_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getObjectRule());
                      						}
                      						setWithLastConsumed(current, "enabled", lv_enabled_0_0 != null, "enabled");
                      					
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalBug304681TestLanguage.g:256:4: ( (lv_abstract_1_0= 'abstract' ) )
                    {
                    // InternalBug304681TestLanguage.g:256:4: ( (lv_abstract_1_0= 'abstract' ) )
                    // InternalBug304681TestLanguage.g:257:5: (lv_abstract_1_0= 'abstract' )
                    {
                    // InternalBug304681TestLanguage.g:257:5: (lv_abstract_1_0= 'abstract' )
                    // InternalBug304681TestLanguage.g:258:6: lv_abstract_1_0= 'abstract'
                    {
                    lv_abstract_1_0=(Token)match(input,16,FollowSets000.FOLLOW_7); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_abstract_1_0, grammarAccess.getObjectAccess().getAbstractAbstractKeyword_0_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getObjectRule());
                      						}
                      						setWithLastConsumed(current, "abstract", lv_abstract_1_0 != null, "abstract");
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,17,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getObjectAccess().getObjectKeyword_1());
              		
            }
            // InternalBug304681TestLanguage.g:275:3: ( (lv_name_3_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:276:4: (lv_name_3_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:276:4: (lv_name_3_0= RULE_ID )
            // InternalBug304681TestLanguage.g:277:5: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_3_0, grammarAccess.getObjectAccess().getNameIDTerminalRuleCall_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getObjectRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_3_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalBug304681TestLanguage.g:293:3: (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalBug304681TestLanguage.g:294:4: otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) )
                    {
                    otherlv_4=(Token)match(input,18,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getObjectAccess().getExtendsKeyword_3_0());
                      			
                    }
                    // InternalBug304681TestLanguage.g:298:4: ( (otherlv_5= RULE_ID ) )
                    // InternalBug304681TestLanguage.g:299:5: (otherlv_5= RULE_ID )
                    {
                    // InternalBug304681TestLanguage.g:299:5: (otherlv_5= RULE_ID )
                    // InternalBug304681TestLanguage.g:300:6: otherlv_5= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getObjectRule());
                      						}
                      					
                    }
                    otherlv_5=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_9); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_5, grammarAccess.getObjectAccess().getParentObjectCrossReference_3_1_0());
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,19,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getObjectAccess().getLeftCurlyBracketKeyword_4());
              		
            }
            // InternalBug304681TestLanguage.g:319:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* ) ) )
            // InternalBug304681TestLanguage.g:320:4: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* ) )
            {
            // InternalBug304681TestLanguage.g:320:4: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* ) )
            // InternalBug304681TestLanguage.g:321:5: ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* )
            {
            getUnorderedGroupHelper().enter(grammarAccess.getObjectAccess().getUnorderedGroup_5());
            // InternalBug304681TestLanguage.g:324:5: ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )* )
            // InternalBug304681TestLanguage.g:325:6: ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )*
            {
            // InternalBug304681TestLanguage.g:325:6: ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )*
            loop9:
            do {
                int alt9=9;
                alt9 = dfa9.predict(input);
                switch (alt9) {
            	case 1 :
            	    // InternalBug304681TestLanguage.g:326:4: ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:326:4: ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:327:5: {...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 0) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 0)");
            	    }
            	    // InternalBug304681TestLanguage.g:327:103: ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:328:6: ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 0);
            	    // InternalBug304681TestLanguage.g:331:9: ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
            	    // InternalBug304681TestLanguage.g:331:10: {...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:331:19: (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
            	    // InternalBug304681TestLanguage.g:331:20: otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';'
            	    {
            	    otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_8, grammarAccess.getObjectAccess().getShortDescriptionKeyword_5_0_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:335:9: ( (lv_shortDescription_9_0= RULE_STRING ) )
            	    // InternalBug304681TestLanguage.g:336:10: (lv_shortDescription_9_0= RULE_STRING )
            	    {
            	    // InternalBug304681TestLanguage.g:336:10: (lv_shortDescription_9_0= RULE_STRING )
            	    // InternalBug304681TestLanguage.g:337:11: lv_shortDescription_9_0= RULE_STRING
            	    {
            	    lv_shortDescription_9_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      											newLeafNode(lv_shortDescription_9_0, grammarAccess.getObjectAccess().getShortDescriptionSTRINGTerminalRuleCall_5_0_1_0());
            	      										
            	    }
            	    if ( state.backtracking==0 ) {

            	      											if (current==null) {
            	      												current = createModelElement(grammarAccess.getObjectRule());
            	      											}
            	      											setWithLastConsumed(
            	      												current,
            	      												"shortDescription",
            	      												lv_shortDescription_9_0,
            	      												"org.eclipse.xtext.common.Terminals.STRING");
            	      										
            	    }

            	    }


            	    }

            	    otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_10, grammarAccess.getObjectAccess().getSemicolonKeyword_5_0_2());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObjectAccess().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalBug304681TestLanguage.g:363:4: ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:363:4: ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:364:5: {...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 1) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 1)");
            	    }
            	    // InternalBug304681TestLanguage.g:364:103: ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:365:6: ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 1);
            	    // InternalBug304681TestLanguage.g:368:9: ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
            	    // InternalBug304681TestLanguage.g:368:10: {...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:368:19: (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' )
            	    // InternalBug304681TestLanguage.g:368:20: otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';'
            	    {
            	    otherlv_11=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_11, grammarAccess.getObjectAccess().getLongDescriptionKeyword_5_1_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:372:9: ( (lv_longDescription_12_0= RULE_STRING ) )
            	    // InternalBug304681TestLanguage.g:373:10: (lv_longDescription_12_0= RULE_STRING )
            	    {
            	    // InternalBug304681TestLanguage.g:373:10: (lv_longDescription_12_0= RULE_STRING )
            	    // InternalBug304681TestLanguage.g:374:11: lv_longDescription_12_0= RULE_STRING
            	    {
            	    lv_longDescription_12_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      											newLeafNode(lv_longDescription_12_0, grammarAccess.getObjectAccess().getLongDescriptionSTRINGTerminalRuleCall_5_1_1_0());
            	      										
            	    }
            	    if ( state.backtracking==0 ) {

            	      											if (current==null) {
            	      												current = createModelElement(grammarAccess.getObjectRule());
            	      											}
            	      											setWithLastConsumed(
            	      												current,
            	      												"longDescription",
            	      												lv_longDescription_12_0,
            	      												"org.eclipse.xtext.common.Terminals.STRING");
            	      										
            	    }

            	    }


            	    }

            	    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_13, grammarAccess.getObjectAccess().getSemicolonKeyword_5_1_2());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObjectAccess().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalBug304681TestLanguage.g:400:4: ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:400:4: ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:401:5: {...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 2) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 2)");
            	    }
            	    // InternalBug304681TestLanguage.g:401:103: ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:402:6: ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 2);
            	    // InternalBug304681TestLanguage.g:405:9: ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) )
            	    // InternalBug304681TestLanguage.g:405:10: {...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:405:19: (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' )
            	    // InternalBug304681TestLanguage.g:405:20: otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';'
            	    {
            	    otherlv_14=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_14, grammarAccess.getObjectAccess().getSerialUIDKeyword_5_2_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:409:9: ( (lv_serialVersionUID_15_0= RULE_INT ) )
            	    // InternalBug304681TestLanguage.g:410:10: (lv_serialVersionUID_15_0= RULE_INT )
            	    {
            	    // InternalBug304681TestLanguage.g:410:10: (lv_serialVersionUID_15_0= RULE_INT )
            	    // InternalBug304681TestLanguage.g:411:11: lv_serialVersionUID_15_0= RULE_INT
            	    {
            	    lv_serialVersionUID_15_0=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      											newLeafNode(lv_serialVersionUID_15_0, grammarAccess.getObjectAccess().getSerialVersionUIDINTTerminalRuleCall_5_2_1_0());
            	      										
            	    }
            	    if ( state.backtracking==0 ) {

            	      											if (current==null) {
            	      												current = createModelElement(grammarAccess.getObjectRule());
            	      											}
            	      											setWithLastConsumed(
            	      												current,
            	      												"serialVersionUID",
            	      												lv_serialVersionUID_15_0,
            	      												"org.eclipse.xtext.common.Terminals.INT");
            	      										
            	    }

            	    }


            	    }

            	    otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_16, grammarAccess.getObjectAccess().getSemicolonKeyword_5_2_2());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObjectAccess().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalBug304681TestLanguage.g:437:4: ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:437:4: ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:438:5: {...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 3) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 3)");
            	    }
            	    // InternalBug304681TestLanguage.g:438:103: ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:439:6: ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 3);
            	    // InternalBug304681TestLanguage.g:442:9: ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) )
            	    // InternalBug304681TestLanguage.g:442:10: {...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:442:19: ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' )
            	    // InternalBug304681TestLanguage.g:442:20: ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';'
            	    {
            	    // InternalBug304681TestLanguage.g:442:20: ( (lv_cloneable_17_0= 'cloneable' ) )
            	    // InternalBug304681TestLanguage.g:443:10: (lv_cloneable_17_0= 'cloneable' )
            	    {
            	    // InternalBug304681TestLanguage.g:443:10: (lv_cloneable_17_0= 'cloneable' )
            	    // InternalBug304681TestLanguage.g:444:11: lv_cloneable_17_0= 'cloneable'
            	    {
            	    lv_cloneable_17_0=(Token)match(input,23,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      											newLeafNode(lv_cloneable_17_0, grammarAccess.getObjectAccess().getCloneableCloneableKeyword_5_3_0_0());
            	      										
            	    }
            	    if ( state.backtracking==0 ) {

            	      											if (current==null) {
            	      												current = createModelElement(grammarAccess.getObjectRule());
            	      											}
            	      											setWithLastConsumed(current, "cloneable", lv_cloneable_17_0 != null, "cloneable");
            	      										
            	    }

            	    }


            	    }

            	    otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_18, grammarAccess.getObjectAccess().getSemicolonKeyword_5_3_1());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObjectAccess().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalBug304681TestLanguage.g:466:4: ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) )
            	    {
            	    // InternalBug304681TestLanguage.g:466:4: ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) )
            	    // InternalBug304681TestLanguage.g:467:5: {...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 4) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 4)");
            	    }
            	    // InternalBug304681TestLanguage.g:467:103: ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ )
            	    // InternalBug304681TestLanguage.g:468:6: ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 4);
            	    // InternalBug304681TestLanguage.g:471:9: ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+
            	    int cnt5=0;
            	    loop5:
            	    do {
            	        int alt5=2;
            	        int LA5_0 = input.LA(1);

            	        if ( (LA5_0==29) ) {
            	            int LA5_2 = input.LA(2);

            	            if ( ((synpred10_InternalBug304681TestLanguage()&&(true))) ) {
            	                alt5=1;
            	            }


            	        }
            	        else if ( (LA5_0==30) ) {
            	            int LA5_3 = input.LA(2);

            	            if ( ((synpred10_InternalBug304681TestLanguage()&&(true))) ) {
            	                alt5=1;
            	            }


            	        }


            	        switch (alt5) {
            	    	case 1 :
            	    	    // InternalBug304681TestLanguage.g:471:10: {...}? => ( (lv_features_19_0= ruleFeature ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        if (state.backtracking>0) {state.failed=true; return current;}
            	    	        throw new FailedPredicateException(input, "ruleObject", "true");
            	    	    }
            	    	    // InternalBug304681TestLanguage.g:471:19: ( (lv_features_19_0= ruleFeature ) )
            	    	    // InternalBug304681TestLanguage.g:471:20: (lv_features_19_0= ruleFeature )
            	    	    {
            	    	    // InternalBug304681TestLanguage.g:471:20: (lv_features_19_0= ruleFeature )
            	    	    // InternalBug304681TestLanguage.g:472:10: lv_features_19_0= ruleFeature
            	    	    {
            	    	    if ( state.backtracking==0 ) {

            	    	      										newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_4_0());
            	    	      									
            	    	    }
            	    	    pushFollow(FollowSets000.FOLLOW_10);
            	    	    lv_features_19_0=ruleFeature();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      										if (current==null) {
            	    	      											current = createModelElementForParent(grammarAccess.getObjectRule());
            	    	      										}
            	    	      										add(
            	    	      											current,
            	    	      											"features",
            	    	      											lv_features_19_0,
            	    	      											"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.Feature");
            	    	      										afterParserOrEnumRuleCall();
            	    	      									
            	    	    }

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt5 >= 1 ) break loop5;
            	    	    if (state.backtracking>0) {state.failed=true; return current;}
            	                EarlyExitException eee =
            	                    new EarlyExitException(5, input);
            	                throw eee;
            	        }
            	        cnt5++;
            	    } while (true);

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObjectAccess().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 6 :
            	    // InternalBug304681TestLanguage.g:494:4: ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:494:4: ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) )
            	    // InternalBug304681TestLanguage.g:495:5: {...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 5) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 5)");
            	    }
            	    // InternalBug304681TestLanguage.g:495:103: ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) )
            	    // InternalBug304681TestLanguage.g:496:6: ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 5);
            	    // InternalBug304681TestLanguage.g:499:9: ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) )
            	    // InternalBug304681TestLanguage.g:499:10: {...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:499:19: (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' )
            	    // InternalBug304681TestLanguage.g:499:20: otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after'
            	    {
            	    otherlv_20=(Token)match(input,24,FollowSets000.FOLLOW_13); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_20, grammarAccess.getObjectAccess().getBeforeKeyword_5_5_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:503:9: ( (lv_features_21_0= ruleFeature ) )*
            	    loop6:
            	    do {
            	        int alt6=2;
            	        int LA6_0 = input.LA(1);

            	        if ( ((LA6_0>=29 && LA6_0<=30)) ) {
            	            alt6=1;
            	        }


            	        switch (alt6) {
            	    	case 1 :
            	    	    // InternalBug304681TestLanguage.g:504:10: (lv_features_21_0= ruleFeature )
            	    	    {
            	    	    // InternalBug304681TestLanguage.g:504:10: (lv_features_21_0= ruleFeature )
            	    	    // InternalBug304681TestLanguage.g:505:11: lv_features_21_0= ruleFeature
            	    	    {
            	    	    if ( state.backtracking==0 ) {

            	    	      											newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_5_1_0());
            	    	      										
            	    	    }
            	    	    pushFollow(FollowSets000.FOLLOW_13);
            	    	    lv_features_21_0=ruleFeature();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      											if (current==null) {
            	    	      												current = createModelElementForParent(grammarAccess.getObjectRule());
            	    	      											}
            	    	      											add(
            	    	      												current,
            	    	      												"features",
            	    	      												lv_features_21_0,
            	    	      												"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.Feature");
            	    	      											afterParserOrEnumRuleCall();
            	    	      										
            	    	    }

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop6;
            	        }
            	    } while (true);

            	    otherlv_22=(Token)match(input,25,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_22, grammarAccess.getObjectAccess().getAfterKeyword_5_5_2());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObjectAccess().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 7 :
            	    // InternalBug304681TestLanguage.g:532:4: ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:532:4: ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) )
            	    // InternalBug304681TestLanguage.g:533:5: {...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 6) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 6)");
            	    }
            	    // InternalBug304681TestLanguage.g:533:103: ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) )
            	    // InternalBug304681TestLanguage.g:534:6: ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 6);
            	    // InternalBug304681TestLanguage.g:537:9: ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) )
            	    // InternalBug304681TestLanguage.g:537:10: {...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:537:19: (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* )
            	    // InternalBug304681TestLanguage.g:537:20: otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )*
            	    {
            	    otherlv_23=(Token)match(input,26,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_23, grammarAccess.getObjectAccess().getOptionalLoopKeyword_5_6_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:541:9: ( (lv_features_24_0= ruleFeature ) )*
            	    loop7:
            	    do {
            	        int alt7=2;
            	        alt7 = dfa7.predict(input);
            	        switch (alt7) {
            	    	case 1 :
            	    	    // InternalBug304681TestLanguage.g:542:10: (lv_features_24_0= ruleFeature )
            	    	    {
            	    	    // InternalBug304681TestLanguage.g:542:10: (lv_features_24_0= ruleFeature )
            	    	    // InternalBug304681TestLanguage.g:543:11: lv_features_24_0= ruleFeature
            	    	    {
            	    	    if ( state.backtracking==0 ) {

            	    	      											newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_6_1_0());
            	    	      										
            	    	    }
            	    	    pushFollow(FollowSets000.FOLLOW_10);
            	    	    lv_features_24_0=ruleFeature();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      											if (current==null) {
            	    	      												current = createModelElementForParent(grammarAccess.getObjectRule());
            	    	      											}
            	    	      											add(
            	    	      												current,
            	    	      												"features",
            	    	      												lv_features_24_0,
            	    	      												"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.Feature");
            	    	      											afterParserOrEnumRuleCall();
            	    	      										
            	    	    }

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop7;
            	        }
            	    } while (true);


            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObjectAccess().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 8 :
            	    // InternalBug304681TestLanguage.g:566:4: ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:566:4: ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) )
            	    // InternalBug304681TestLanguage.g:567:5: {...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 7) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 7)");
            	    }
            	    // InternalBug304681TestLanguage.g:567:103: ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) )
            	    // InternalBug304681TestLanguage.g:568:6: ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 7);
            	    // InternalBug304681TestLanguage.g:571:9: ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) )
            	    // InternalBug304681TestLanguage.g:571:10: {...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:571:19: (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ )
            	    // InternalBug304681TestLanguage.g:571:20: otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+
            	    {
            	    otherlv_25=(Token)match(input,27,FollowSets000.FOLLOW_14); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_25, grammarAccess.getObjectAccess().getMandatoryLoopKeyword_5_7_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:575:9: ( (lv_features_26_0= ruleFeature ) )+
            	    int cnt8=0;
            	    loop8:
            	    do {
            	        int alt8=2;
            	        alt8 = dfa8.predict(input);
            	        switch (alt8) {
            	    	case 1 :
            	    	    // InternalBug304681TestLanguage.g:576:10: (lv_features_26_0= ruleFeature )
            	    	    {
            	    	    // InternalBug304681TestLanguage.g:576:10: (lv_features_26_0= ruleFeature )
            	    	    // InternalBug304681TestLanguage.g:577:11: lv_features_26_0= ruleFeature
            	    	    {
            	    	    if ( state.backtracking==0 ) {

            	    	      											newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_7_1_0());
            	    	      										
            	    	    }
            	    	    pushFollow(FollowSets000.FOLLOW_10);
            	    	    lv_features_26_0=ruleFeature();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      											if (current==null) {
            	    	      												current = createModelElementForParent(grammarAccess.getObjectRule());
            	    	      											}
            	    	      											add(
            	    	      												current,
            	    	      												"features",
            	    	      												lv_features_26_0,
            	    	      												"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.Feature");
            	    	      											afterParserOrEnumRuleCall();
            	    	      										
            	    	    }

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt8 >= 1 ) break loop8;
            	    	    if (state.backtracking>0) {state.failed=true; return current;}
            	                EarlyExitException eee =
            	                    new EarlyExitException(8, input);
            	                throw eee;
            	        }
            	        cnt8++;
            	    } while (true);


            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObjectAccess().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }


            }

            getUnorderedGroupHelper().leave(grammarAccess.getObjectAccess().getUnorderedGroup_5());

            }

            otherlv_27=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_27, grammarAccess.getObjectAccess().getRightCurlyBracketKeyword_6());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleObject"


    // $ANTLR start "entryRuleFeature"
    // InternalBug304681TestLanguage.g:618:1: entryRuleFeature returns [EObject current=null] : iv_ruleFeature= ruleFeature EOF ;
    public final EObject entryRuleFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeature = null;


        try {
            // InternalBug304681TestLanguage.g:618:48: (iv_ruleFeature= ruleFeature EOF )
            // InternalBug304681TestLanguage.g:619:2: iv_ruleFeature= ruleFeature EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeatureRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleFeature=ruleFeature();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeature; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // InternalBug304681TestLanguage.g:625:1: ruleFeature returns [EObject current=null] : (this_Attribute_0= ruleAttribute | this_Reference_1= ruleReference ) ;
    public final EObject ruleFeature() throws RecognitionException {
        EObject current = null;

        EObject this_Attribute_0 = null;

        EObject this_Reference_1 = null;



        	enterRule();

        try {
            // InternalBug304681TestLanguage.g:631:2: ( (this_Attribute_0= ruleAttribute | this_Reference_1= ruleReference ) )
            // InternalBug304681TestLanguage.g:632:2: (this_Attribute_0= ruleAttribute | this_Reference_1= ruleReference )
            {
            // InternalBug304681TestLanguage.g:632:2: (this_Attribute_0= ruleAttribute | this_Reference_1= ruleReference )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==29) ) {
                alt10=1;
            }
            else if ( (LA10_0==30) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalBug304681TestLanguage.g:633:3: this_Attribute_0= ruleAttribute
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFeatureAccess().getAttributeParserRuleCall_0());
                      		
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Attribute_0=ruleAttribute();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Attribute_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalBug304681TestLanguage.g:645:3: this_Reference_1= ruleReference
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFeatureAccess().getReferenceParserRuleCall_1());
                      		
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Reference_1=ruleReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Reference_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleAttribute"
    // InternalBug304681TestLanguage.g:660:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // InternalBug304681TestLanguage.g:660:50: (iv_ruleAttribute= ruleAttribute EOF )
            // InternalBug304681TestLanguage.g:661:2: iv_ruleAttribute= ruleAttribute EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAttributeRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAttribute; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // InternalBug304681TestLanguage.g:667:1: ruleAttribute returns [EObject current=null] : (otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ';' ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_type_1_0=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalBug304681TestLanguage.g:673:2: ( (otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ';' ) )
            // InternalBug304681TestLanguage.g:674:2: (otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ';' )
            {
            // InternalBug304681TestLanguage.g:674:2: (otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ';' )
            // InternalBug304681TestLanguage.g:675:3: otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ';'
            {
            otherlv_0=(Token)match(input,29,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getAttributeAccess().getAttributeKeyword_0());
              		
            }
            // InternalBug304681TestLanguage.g:679:3: ( (lv_type_1_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:680:4: (lv_type_1_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:680:4: (lv_type_1_0= RULE_ID )
            // InternalBug304681TestLanguage.g:681:5: lv_type_1_0= RULE_ID
            {
            lv_type_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_type_1_0, grammarAccess.getAttributeAccess().getTypeIDTerminalRuleCall_1_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getAttributeRule());
              					}
              					setWithLastConsumed(
              						current,
              						"type",
              						lv_type_1_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalBug304681TestLanguage.g:697:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:698:4: (lv_name_2_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:698:4: (lv_name_2_0= RULE_ID )
            // InternalBug304681TestLanguage.g:699:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_2_0, grammarAccess.getAttributeAccess().getNameIDTerminalRuleCall_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getAttributeRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_2_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getAttributeAccess().getSemicolonKeyword_3());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleReference"
    // InternalBug304681TestLanguage.g:723:1: entryRuleReference returns [EObject current=null] : iv_ruleReference= ruleReference EOF ;
    public final EObject entryRuleReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReference = null;



        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1()
        	);

        try {
            // InternalBug304681TestLanguage.g:727:2: (iv_ruleReference= ruleReference EOF )
            // InternalBug304681TestLanguage.g:728:2: iv_ruleReference= ruleReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReferenceRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleReference=ruleReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReference; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleReference"


    // $ANTLR start "ruleReference"
    // InternalBug304681TestLanguage.g:737:1: ruleReference returns [EObject current=null] : (otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' ) ) ;
    public final EObject ruleReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_many_2_0=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token lv_shortDescription_7_0=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token lv_longDescription_10_0=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;


        	enterRule();
        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1()
        	);

        try {
            // InternalBug304681TestLanguage.g:746:2: ( (otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' ) ) )
            // InternalBug304681TestLanguage.g:747:2: (otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' ) )
            {
            // InternalBug304681TestLanguage.g:747:2: (otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' ) )
            // InternalBug304681TestLanguage.g:748:3: otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' )
            {
            otherlv_0=(Token)match(input,30,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getReferenceAccess().getReferenceKeyword_0());
              		
            }
            // InternalBug304681TestLanguage.g:752:3: ( (otherlv_1= RULE_ID ) )
            // InternalBug304681TestLanguage.g:753:4: (otherlv_1= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:753:4: (otherlv_1= RULE_ID )
            // InternalBug304681TestLanguage.g:754:5: otherlv_1= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					/* */
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getReferenceRule());
              					}
              				
            }
            otherlv_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getReferenceAccess().getTypeObjectCrossReference_1_0());
              				
            }

            }


            }

            // InternalBug304681TestLanguage.g:768:3: ( (lv_many_2_0= '*' ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==31) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalBug304681TestLanguage.g:769:4: (lv_many_2_0= '*' )
                    {
                    // InternalBug304681TestLanguage.g:769:4: (lv_many_2_0= '*' )
                    // InternalBug304681TestLanguage.g:770:5: lv_many_2_0= '*'
                    {
                    lv_many_2_0=(Token)match(input,31,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_many_2_0, grammarAccess.getReferenceAccess().getManyAsteriskKeyword_2_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getReferenceRule());
                      					}
                      					setWithLastConsumed(current, "many", lv_many_2_0 != null, "*");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalBug304681TestLanguage.g:782:3: ( (lv_name_3_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:783:4: (lv_name_3_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:783:4: (lv_name_3_0= RULE_ID )
            // InternalBug304681TestLanguage.g:784:5: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_3_0, grammarAccess.getReferenceAccess().getNameIDTerminalRuleCall_3_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getReferenceRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_3_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalBug304681TestLanguage.g:800:3: ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==19) ) {
                alt13=1;
            }
            else if ( (LA13_0==14) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalBug304681TestLanguage.g:801:4: (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' )
                    {
                    // InternalBug304681TestLanguage.g:801:4: (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' )
                    // InternalBug304681TestLanguage.g:802:5: otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}'
                    {
                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_4, grammarAccess.getReferenceAccess().getLeftCurlyBracketKeyword_4_0_0());
                      				
                    }
                    // InternalBug304681TestLanguage.g:806:5: ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) )
                    // InternalBug304681TestLanguage.g:807:6: ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) )
                    {
                    // InternalBug304681TestLanguage.g:807:6: ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) )
                    // InternalBug304681TestLanguage.g:808:7: ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* )
                    {
                    getUnorderedGroupHelper().enter(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1());
                    // InternalBug304681TestLanguage.g:811:7: ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* )
                    // InternalBug304681TestLanguage.g:812:8: ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )*
                    {
                    // InternalBug304681TestLanguage.g:812:8: ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )*
                    loop12:
                    do {
                        int alt12=3;
                        int LA12_0 = input.LA(1);

                        if ( LA12_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 0) ) {
                            alt12=1;
                        }
                        else if ( LA12_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 1) ) {
                            alt12=2;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // InternalBug304681TestLanguage.g:813:6: ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
                    	    {
                    	    // InternalBug304681TestLanguage.g:813:6: ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
                    	    // InternalBug304681TestLanguage.g:814:7: {...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 0) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleReference", "getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 0)");
                    	    }
                    	    // InternalBug304681TestLanguage.g:814:112: ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
                    	    // InternalBug304681TestLanguage.g:815:8: ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
                    	    {
                    	    getUnorderedGroupHelper().select(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 0);
                    	    // InternalBug304681TestLanguage.g:818:11: ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
                    	    // InternalBug304681TestLanguage.g:818:12: {...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' )
                    	    {
                    	    if ( !((true)) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleReference", "true");
                    	    }
                    	    // InternalBug304681TestLanguage.g:818:21: (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' )
                    	    // InternalBug304681TestLanguage.g:818:22: otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';'
                    	    {
                    	    otherlv_6=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_6, grammarAccess.getReferenceAccess().getShortDescriptionKeyword_4_0_1_0_0());
                    	      										
                    	    }
                    	    // InternalBug304681TestLanguage.g:822:11: ( (lv_shortDescription_7_0= RULE_STRING ) )
                    	    // InternalBug304681TestLanguage.g:823:12: (lv_shortDescription_7_0= RULE_STRING )
                    	    {
                    	    // InternalBug304681TestLanguage.g:823:12: (lv_shortDescription_7_0= RULE_STRING )
                    	    // InternalBug304681TestLanguage.g:824:13: lv_shortDescription_7_0= RULE_STRING
                    	    {
                    	    lv_shortDescription_7_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      													newLeafNode(lv_shortDescription_7_0, grammarAccess.getReferenceAccess().getShortDescriptionSTRINGTerminalRuleCall_4_0_1_0_1_0());
                    	      												
                    	    }
                    	    if ( state.backtracking==0 ) {

                    	      													if (current==null) {
                    	      														current = createModelElement(grammarAccess.getReferenceRule());
                    	      													}
                    	      													setWithLastConsumed(
                    	      														current,
                    	      														"shortDescription",
                    	      														lv_shortDescription_7_0,
                    	      														"org.eclipse.xtext.common.Terminals.STRING");
                    	      												
                    	    }

                    	    }


                    	    }

                    	    otherlv_8=(Token)match(input,14,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_8, grammarAccess.getReferenceAccess().getSemicolonKeyword_4_0_1_0_2());
                    	      										
                    	    }

                    	    }


                    	    }

                    	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1());

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalBug304681TestLanguage.g:850:6: ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) )
                    	    {
                    	    // InternalBug304681TestLanguage.g:850:6: ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) )
                    	    // InternalBug304681TestLanguage.g:851:7: {...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 1) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleReference", "getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 1)");
                    	    }
                    	    // InternalBug304681TestLanguage.g:851:112: ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) )
                    	    // InternalBug304681TestLanguage.g:852:8: ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) )
                    	    {
                    	    getUnorderedGroupHelper().select(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 1);
                    	    // InternalBug304681TestLanguage.g:855:11: ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) )
                    	    // InternalBug304681TestLanguage.g:855:12: {...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' )
                    	    {
                    	    if ( !((true)) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleReference", "true");
                    	    }
                    	    // InternalBug304681TestLanguage.g:855:21: (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' )
                    	    // InternalBug304681TestLanguage.g:855:22: otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';'
                    	    {
                    	    otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_9, grammarAccess.getReferenceAccess().getLongDescriptionKeyword_4_0_1_1_0());
                    	      										
                    	    }
                    	    // InternalBug304681TestLanguage.g:859:11: ( (lv_longDescription_10_0= RULE_STRING ) )
                    	    // InternalBug304681TestLanguage.g:860:12: (lv_longDescription_10_0= RULE_STRING )
                    	    {
                    	    // InternalBug304681TestLanguage.g:860:12: (lv_longDescription_10_0= RULE_STRING )
                    	    // InternalBug304681TestLanguage.g:861:13: lv_longDescription_10_0= RULE_STRING
                    	    {
                    	    lv_longDescription_10_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      													newLeafNode(lv_longDescription_10_0, grammarAccess.getReferenceAccess().getLongDescriptionSTRINGTerminalRuleCall_4_0_1_1_1_0());
                    	      												
                    	    }
                    	    if ( state.backtracking==0 ) {

                    	      													if (current==null) {
                    	      														current = createModelElement(grammarAccess.getReferenceRule());
                    	      													}
                    	      													setWithLastConsumed(
                    	      														current,
                    	      														"longDescription",
                    	      														lv_longDescription_10_0,
                    	      														"org.eclipse.xtext.common.Terminals.STRING");
                    	      												
                    	    }

                    	    }


                    	    }

                    	    otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_11, grammarAccess.getReferenceAccess().getSemicolonKeyword_4_0_1_1_2());
                    	      										
                    	    }

                    	    }


                    	    }

                    	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1());

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    }


                    }

                    getUnorderedGroupHelper().leave(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1());

                    }

                    otherlv_12=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_12, grammarAccess.getReferenceAccess().getRightCurlyBracketKeyword_4_0_2());
                      				
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBug304681TestLanguage.g:900:4: otherlv_13= ';'
                    {
                    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_13, grammarAccess.getReferenceAccess().getSemicolonKeyword_4_1());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleReference"


    // $ANTLR start "entryRulePackageDefinition2"
    // InternalBug304681TestLanguage.g:912:1: entryRulePackageDefinition2 returns [EObject current=null] : iv_rulePackageDefinition2= rulePackageDefinition2 EOF ;
    public final EObject entryRulePackageDefinition2() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackageDefinition2 = null;


        try {
            // InternalBug304681TestLanguage.g:912:59: (iv_rulePackageDefinition2= rulePackageDefinition2 EOF )
            // InternalBug304681TestLanguage.g:913:2: iv_rulePackageDefinition2= rulePackageDefinition2 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPackageDefinition2Rule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePackageDefinition2=rulePackageDefinition2();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePackageDefinition2; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePackageDefinition2"


    // $ANTLR start "rulePackageDefinition2"
    // InternalBug304681TestLanguage.g:919:1: rulePackageDefinition2 returns [EObject current=null] : ( () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject2 ) )* ) ;
    public final EObject rulePackageDefinition2() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_namespace_2_0=null;
        Token otherlv_3=null;
        EObject lv_contents_4_0 = null;



        	enterRule();

        try {
            // InternalBug304681TestLanguage.g:925:2: ( ( () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject2 ) )* ) )
            // InternalBug304681TestLanguage.g:926:2: ( () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject2 ) )* )
            {
            // InternalBug304681TestLanguage.g:926:2: ( () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject2 ) )* )
            // InternalBug304681TestLanguage.g:927:3: () otherlv_1= 'package' ( (lv_namespace_2_0= RULE_ID ) ) otherlv_3= ';' ( (lv_contents_4_0= ruleObject2 ) )*
            {
            // InternalBug304681TestLanguage.g:927:3: ()
            // InternalBug304681TestLanguage.g:928:4: 
            {
            if ( state.backtracking==0 ) {

              				/* */
              			
            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getPackageDefinition2Access().getPackageDefinitionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,13,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getPackageDefinition2Access().getPackageKeyword_1());
              		
            }
            // InternalBug304681TestLanguage.g:941:3: ( (lv_namespace_2_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:942:4: (lv_namespace_2_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:942:4: (lv_namespace_2_0= RULE_ID )
            // InternalBug304681TestLanguage.g:943:5: lv_namespace_2_0= RULE_ID
            {
            lv_namespace_2_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_namespace_2_0, grammarAccess.getPackageDefinition2Access().getNamespaceIDTerminalRuleCall_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getPackageDefinition2Rule());
              					}
              					setWithLastConsumed(
              						current,
              						"namespace",
              						lv_namespace_2_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getPackageDefinition2Access().getSemicolonKeyword_3());
              		
            }
            // InternalBug304681TestLanguage.g:963:3: ( (lv_contents_4_0= ruleObject2 ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=15 && LA14_0<=17)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalBug304681TestLanguage.g:964:4: (lv_contents_4_0= ruleObject2 )
            	    {
            	    // InternalBug304681TestLanguage.g:964:4: (lv_contents_4_0= ruleObject2 )
            	    // InternalBug304681TestLanguage.g:965:5: lv_contents_4_0= ruleObject2
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getPackageDefinition2Access().getContentsObject2ParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    lv_contents_4_0=ruleObject2();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getPackageDefinition2Rule());
            	      					}
            	      					add(
            	      						current,
            	      						"contents",
            	      						lv_contents_4_0,
            	      						"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.Object2");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePackageDefinition2"


    // $ANTLR start "entryRuleObject2"
    // InternalBug304681TestLanguage.g:986:1: entryRuleObject2 returns [EObject current=null] : iv_ruleObject2= ruleObject2 EOF ;
    public final EObject entryRuleObject2() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObject2 = null;



        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getObject2Access().getUnorderedGroup_5()
        	);

        try {
            // InternalBug304681TestLanguage.g:990:2: (iv_ruleObject2= ruleObject2 EOF )
            // InternalBug304681TestLanguage.g:991:2: iv_ruleObject2= ruleObject2 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getObject2Rule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleObject2=ruleObject2();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleObject2; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleObject2"


    // $ANTLR start "ruleObject2"
    // InternalBug304681TestLanguage.g:1000:1: ruleObject2 returns [EObject current=null] : ( ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* ) ) ) otherlv_20= '}' ) ;
    public final EObject ruleObject2() throws RecognitionException {
        EObject current = null;

        Token lv_enabled_0_0=null;
        Token lv_abstract_1_0=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token lv_shortDescription_9_0=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token lv_longDescription_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token lv_serialVersionUID_15_0=null;
        Token otherlv_16=null;
        Token lv_cloneable_17_0=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        EObject lv_features_19_0 = null;



        	enterRule();
        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getObject2Access().getUnorderedGroup_5()
        	);

        try {
            // InternalBug304681TestLanguage.g:1009:2: ( ( ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* ) ) ) otherlv_20= '}' ) )
            // InternalBug304681TestLanguage.g:1010:2: ( ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* ) ) ) otherlv_20= '}' )
            {
            // InternalBug304681TestLanguage.g:1010:2: ( ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* ) ) ) otherlv_20= '}' )
            // InternalBug304681TestLanguage.g:1011:3: ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )? otherlv_2= 'object' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )? otherlv_6= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* ) ) ) otherlv_20= '}'
            {
            // InternalBug304681TestLanguage.g:1011:3: ( ( (lv_enabled_0_0= 'enabled' ) ) | ( (lv_abstract_1_0= 'abstract' ) ) )?
            int alt15=3;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==15) ) {
                alt15=1;
            }
            else if ( (LA15_0==16) ) {
                alt15=2;
            }
            switch (alt15) {
                case 1 :
                    // InternalBug304681TestLanguage.g:1012:4: ( (lv_enabled_0_0= 'enabled' ) )
                    {
                    // InternalBug304681TestLanguage.g:1012:4: ( (lv_enabled_0_0= 'enabled' ) )
                    // InternalBug304681TestLanguage.g:1013:5: (lv_enabled_0_0= 'enabled' )
                    {
                    // InternalBug304681TestLanguage.g:1013:5: (lv_enabled_0_0= 'enabled' )
                    // InternalBug304681TestLanguage.g:1014:6: lv_enabled_0_0= 'enabled'
                    {
                    lv_enabled_0_0=(Token)match(input,15,FollowSets000.FOLLOW_7); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_enabled_0_0, grammarAccess.getObject2Access().getEnabledEnabledKeyword_0_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getObject2Rule());
                      						}
                      						setWithLastConsumed(current, "enabled", lv_enabled_0_0 != null, "enabled");
                      					
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalBug304681TestLanguage.g:1027:4: ( (lv_abstract_1_0= 'abstract' ) )
                    {
                    // InternalBug304681TestLanguage.g:1027:4: ( (lv_abstract_1_0= 'abstract' ) )
                    // InternalBug304681TestLanguage.g:1028:5: (lv_abstract_1_0= 'abstract' )
                    {
                    // InternalBug304681TestLanguage.g:1028:5: (lv_abstract_1_0= 'abstract' )
                    // InternalBug304681TestLanguage.g:1029:6: lv_abstract_1_0= 'abstract'
                    {
                    lv_abstract_1_0=(Token)match(input,16,FollowSets000.FOLLOW_7); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_abstract_1_0, grammarAccess.getObject2Access().getAbstractAbstractKeyword_0_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getObject2Rule());
                      						}
                      						setWithLastConsumed(current, "abstract", lv_abstract_1_0 != null, "abstract");
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,17,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getObject2Access().getObjectKeyword_1());
              		
            }
            // InternalBug304681TestLanguage.g:1046:3: ( (lv_name_3_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:1047:4: (lv_name_3_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:1047:4: (lv_name_3_0= RULE_ID )
            // InternalBug304681TestLanguage.g:1048:5: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_3_0, grammarAccess.getObject2Access().getNameIDTerminalRuleCall_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getObject2Rule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_3_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalBug304681TestLanguage.g:1064:3: (otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==18) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalBug304681TestLanguage.g:1065:4: otherlv_4= 'extends' ( (otherlv_5= RULE_ID ) )
                    {
                    otherlv_4=(Token)match(input,18,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getObject2Access().getExtendsKeyword_3_0());
                      			
                    }
                    // InternalBug304681TestLanguage.g:1069:4: ( (otherlv_5= RULE_ID ) )
                    // InternalBug304681TestLanguage.g:1070:5: (otherlv_5= RULE_ID )
                    {
                    // InternalBug304681TestLanguage.g:1070:5: (otherlv_5= RULE_ID )
                    // InternalBug304681TestLanguage.g:1071:6: otherlv_5= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getObject2Rule());
                      						}
                      					
                    }
                    otherlv_5=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_9); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_5, grammarAccess.getObject2Access().getParentObjectCrossReference_3_1_0());
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,19,FollowSets000.FOLLOW_18); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getObject2Access().getLeftCurlyBracketKeyword_4());
              		
            }
            // InternalBug304681TestLanguage.g:1090:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* ) ) )
            // InternalBug304681TestLanguage.g:1091:4: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* ) )
            {
            // InternalBug304681TestLanguage.g:1091:4: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* ) )
            // InternalBug304681TestLanguage.g:1092:5: ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* )
            {
            getUnorderedGroupHelper().enter(grammarAccess.getObject2Access().getUnorderedGroup_5());
            // InternalBug304681TestLanguage.g:1095:5: ( ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )* )
            // InternalBug304681TestLanguage.g:1096:6: ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )*
            {
            // InternalBug304681TestLanguage.g:1096:6: ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )*
            loop18:
            do {
                int alt18=6;
                int LA18_0 = input.LA(1);

                if ( LA18_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 0) ) {
                    alt18=1;
                }
                else if ( LA18_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 1) ) {
                    alt18=2;
                }
                else if ( LA18_0 == 22 && getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 2) ) {
                    alt18=3;
                }
                else if ( LA18_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 3) ) {
                    alt18=4;
                }
                else if ( LA18_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 4) ) {
                    alt18=5;
                }
                else if ( LA18_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 4) ) {
                    alt18=5;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalBug304681TestLanguage.g:1097:4: ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:1097:4: ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:1098:5: {...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 0) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject2", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 0)");
            	    }
            	    // InternalBug304681TestLanguage.g:1098:104: ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:1099:6: ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 0);
            	    // InternalBug304681TestLanguage.g:1102:9: ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
            	    // InternalBug304681TestLanguage.g:1102:10: {...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject2", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:1102:19: (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
            	    // InternalBug304681TestLanguage.g:1102:20: otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';'
            	    {
            	    otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_8, grammarAccess.getObject2Access().getShortDescriptionKeyword_5_0_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:1106:9: ( (lv_shortDescription_9_0= RULE_STRING ) )
            	    // InternalBug304681TestLanguage.g:1107:10: (lv_shortDescription_9_0= RULE_STRING )
            	    {
            	    // InternalBug304681TestLanguage.g:1107:10: (lv_shortDescription_9_0= RULE_STRING )
            	    // InternalBug304681TestLanguage.g:1108:11: lv_shortDescription_9_0= RULE_STRING
            	    {
            	    lv_shortDescription_9_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      											newLeafNode(lv_shortDescription_9_0, grammarAccess.getObject2Access().getShortDescriptionSTRINGTerminalRuleCall_5_0_1_0());
            	      										
            	    }
            	    if ( state.backtracking==0 ) {

            	      											if (current==null) {
            	      												current = createModelElement(grammarAccess.getObject2Rule());
            	      											}
            	      											setWithLastConsumed(
            	      												current,
            	      												"shortDescription",
            	      												lv_shortDescription_9_0,
            	      												"org.eclipse.xtext.common.Terminals.STRING");
            	      										
            	    }

            	    }


            	    }

            	    otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_18); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_10, grammarAccess.getObject2Access().getSemicolonKeyword_5_0_2());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObject2Access().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalBug304681TestLanguage.g:1134:4: ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:1134:4: ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:1135:5: {...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 1) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject2", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 1)");
            	    }
            	    // InternalBug304681TestLanguage.g:1135:104: ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:1136:6: ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 1);
            	    // InternalBug304681TestLanguage.g:1139:9: ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
            	    // InternalBug304681TestLanguage.g:1139:10: {...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject2", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:1139:19: (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' )
            	    // InternalBug304681TestLanguage.g:1139:20: otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';'
            	    {
            	    otherlv_11=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_11, grammarAccess.getObject2Access().getLongDescriptionKeyword_5_1_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:1143:9: ( (lv_longDescription_12_0= RULE_STRING ) )
            	    // InternalBug304681TestLanguage.g:1144:10: (lv_longDescription_12_0= RULE_STRING )
            	    {
            	    // InternalBug304681TestLanguage.g:1144:10: (lv_longDescription_12_0= RULE_STRING )
            	    // InternalBug304681TestLanguage.g:1145:11: lv_longDescription_12_0= RULE_STRING
            	    {
            	    lv_longDescription_12_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      											newLeafNode(lv_longDescription_12_0, grammarAccess.getObject2Access().getLongDescriptionSTRINGTerminalRuleCall_5_1_1_0());
            	      										
            	    }
            	    if ( state.backtracking==0 ) {

            	      											if (current==null) {
            	      												current = createModelElement(grammarAccess.getObject2Rule());
            	      											}
            	      											setWithLastConsumed(
            	      												current,
            	      												"longDescription",
            	      												lv_longDescription_12_0,
            	      												"org.eclipse.xtext.common.Terminals.STRING");
            	      										
            	    }

            	    }


            	    }

            	    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_18); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_13, grammarAccess.getObject2Access().getSemicolonKeyword_5_1_2());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObject2Access().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalBug304681TestLanguage.g:1171:4: ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:1171:4: ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:1172:5: {...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 2) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject2", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 2)");
            	    }
            	    // InternalBug304681TestLanguage.g:1172:104: ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:1173:6: ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 2);
            	    // InternalBug304681TestLanguage.g:1176:9: ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) )
            	    // InternalBug304681TestLanguage.g:1176:10: {...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject2", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:1176:19: (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' )
            	    // InternalBug304681TestLanguage.g:1176:20: otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';'
            	    {
            	    otherlv_14=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_14, grammarAccess.getObject2Access().getSerialUIDKeyword_5_2_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:1180:9: ( (lv_serialVersionUID_15_0= RULE_INT ) )
            	    // InternalBug304681TestLanguage.g:1181:10: (lv_serialVersionUID_15_0= RULE_INT )
            	    {
            	    // InternalBug304681TestLanguage.g:1181:10: (lv_serialVersionUID_15_0= RULE_INT )
            	    // InternalBug304681TestLanguage.g:1182:11: lv_serialVersionUID_15_0= RULE_INT
            	    {
            	    lv_serialVersionUID_15_0=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      											newLeafNode(lv_serialVersionUID_15_0, grammarAccess.getObject2Access().getSerialVersionUIDINTTerminalRuleCall_5_2_1_0());
            	      										
            	    }
            	    if ( state.backtracking==0 ) {

            	      											if (current==null) {
            	      												current = createModelElement(grammarAccess.getObject2Rule());
            	      											}
            	      											setWithLastConsumed(
            	      												current,
            	      												"serialVersionUID",
            	      												lv_serialVersionUID_15_0,
            	      												"org.eclipse.xtext.common.Terminals.INT");
            	      										
            	    }

            	    }


            	    }

            	    otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_18); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_16, grammarAccess.getObject2Access().getSemicolonKeyword_5_2_2());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObject2Access().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalBug304681TestLanguage.g:1208:4: ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:1208:4: ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:1209:5: {...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 3) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject2", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 3)");
            	    }
            	    // InternalBug304681TestLanguage.g:1209:104: ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:1210:6: ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 3);
            	    // InternalBug304681TestLanguage.g:1213:9: ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) )
            	    // InternalBug304681TestLanguage.g:1213:10: {...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject2", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:1213:19: ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' )
            	    // InternalBug304681TestLanguage.g:1213:20: ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';'
            	    {
            	    // InternalBug304681TestLanguage.g:1213:20: ( (lv_cloneable_17_0= 'cloneable' ) )
            	    // InternalBug304681TestLanguage.g:1214:10: (lv_cloneable_17_0= 'cloneable' )
            	    {
            	    // InternalBug304681TestLanguage.g:1214:10: (lv_cloneable_17_0= 'cloneable' )
            	    // InternalBug304681TestLanguage.g:1215:11: lv_cloneable_17_0= 'cloneable'
            	    {
            	    lv_cloneable_17_0=(Token)match(input,23,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      											newLeafNode(lv_cloneable_17_0, grammarAccess.getObject2Access().getCloneableCloneableKeyword_5_3_0_0());
            	      										
            	    }
            	    if ( state.backtracking==0 ) {

            	      											if (current==null) {
            	      												current = createModelElement(grammarAccess.getObject2Rule());
            	      											}
            	      											setWithLastConsumed(current, "cloneable", lv_cloneable_17_0 != null, "cloneable");
            	      										
            	    }

            	    }


            	    }

            	    otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_18); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_18, grammarAccess.getObject2Access().getSemicolonKeyword_5_3_1());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObject2Access().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;
            	case 5 :
            	    // InternalBug304681TestLanguage.g:1237:4: ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) )
            	    {
            	    // InternalBug304681TestLanguage.g:1237:4: ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) )
            	    // InternalBug304681TestLanguage.g:1238:5: {...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 4) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleObject2", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 4)");
            	    }
            	    // InternalBug304681TestLanguage.g:1238:104: ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ )
            	    // InternalBug304681TestLanguage.g:1239:6: ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 4);
            	    // InternalBug304681TestLanguage.g:1242:9: ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+
            	    int cnt17=0;
            	    loop17:
            	    do {
            	        int alt17=2;
            	        int LA17_0 = input.LA(1);

            	        if ( (LA17_0==29) ) {
            	            int LA17_2 = input.LA(2);

            	            if ( ((synpred31_InternalBug304681TestLanguage()&&(true))) ) {
            	                alt17=1;
            	            }


            	        }
            	        else if ( (LA17_0==30) ) {
            	            int LA17_3 = input.LA(2);

            	            if ( ((synpred31_InternalBug304681TestLanguage()&&(true))) ) {
            	                alt17=1;
            	            }


            	        }


            	        switch (alt17) {
            	    	case 1 :
            	    	    // InternalBug304681TestLanguage.g:1242:10: {...}? => ( (lv_features_19_0= ruleFeature2 ) )
            	    	    {
            	    	    if ( !((true)) ) {
            	    	        if (state.backtracking>0) {state.failed=true; return current;}
            	    	        throw new FailedPredicateException(input, "ruleObject2", "true");
            	    	    }
            	    	    // InternalBug304681TestLanguage.g:1242:19: ( (lv_features_19_0= ruleFeature2 ) )
            	    	    // InternalBug304681TestLanguage.g:1242:20: (lv_features_19_0= ruleFeature2 )
            	    	    {
            	    	    // InternalBug304681TestLanguage.g:1242:20: (lv_features_19_0= ruleFeature2 )
            	    	    // InternalBug304681TestLanguage.g:1243:10: lv_features_19_0= ruleFeature2
            	    	    {
            	    	    if ( state.backtracking==0 ) {

            	    	      										newCompositeNode(grammarAccess.getObject2Access().getFeaturesFeature2ParserRuleCall_5_4_0());
            	    	      									
            	    	    }
            	    	    pushFollow(FollowSets000.FOLLOW_18);
            	    	    lv_features_19_0=ruleFeature2();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      										if (current==null) {
            	    	      											current = createModelElementForParent(grammarAccess.getObject2Rule());
            	    	      										}
            	    	      										add(
            	    	      											current,
            	    	      											"features",
            	    	      											lv_features_19_0,
            	    	      											"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.Feature2");
            	    	      										afterParserOrEnumRuleCall();
            	    	      									
            	    	    }

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt17 >= 1 ) break loop17;
            	    	    if (state.backtracking>0) {state.failed=true; return current;}
            	                EarlyExitException eee =
            	                    new EarlyExitException(17, input);
            	                throw eee;
            	        }
            	        cnt17++;
            	    } while (true);

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getObject2Access().getUnorderedGroup_5());

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }


            }

            getUnorderedGroupHelper().leave(grammarAccess.getObject2Access().getUnorderedGroup_5());

            }

            otherlv_20=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_20, grammarAccess.getObject2Access().getRightCurlyBracketKeyword_6());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleObject2"


    // $ANTLR start "entryRuleFeature2"
    // InternalBug304681TestLanguage.g:1283:1: entryRuleFeature2 returns [EObject current=null] : iv_ruleFeature2= ruleFeature2 EOF ;
    public final EObject entryRuleFeature2() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeature2 = null;


        try {
            // InternalBug304681TestLanguage.g:1283:49: (iv_ruleFeature2= ruleFeature2 EOF )
            // InternalBug304681TestLanguage.g:1284:2: iv_ruleFeature2= ruleFeature2 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeature2Rule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleFeature2=ruleFeature2();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeature2; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeature2"


    // $ANTLR start "ruleFeature2"
    // InternalBug304681TestLanguage.g:1290:1: ruleFeature2 returns [EObject current=null] : (this_Attribute2_0= ruleAttribute2 | this_Reference2_1= ruleReference2 ) ;
    public final EObject ruleFeature2() throws RecognitionException {
        EObject current = null;

        EObject this_Attribute2_0 = null;

        EObject this_Reference2_1 = null;



        	enterRule();

        try {
            // InternalBug304681TestLanguage.g:1296:2: ( (this_Attribute2_0= ruleAttribute2 | this_Reference2_1= ruleReference2 ) )
            // InternalBug304681TestLanguage.g:1297:2: (this_Attribute2_0= ruleAttribute2 | this_Reference2_1= ruleReference2 )
            {
            // InternalBug304681TestLanguage.g:1297:2: (this_Attribute2_0= ruleAttribute2 | this_Reference2_1= ruleReference2 )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==29) ) {
                alt19=1;
            }
            else if ( (LA19_0==30) ) {
                alt19=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalBug304681TestLanguage.g:1298:3: this_Attribute2_0= ruleAttribute2
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFeature2Access().getAttribute2ParserRuleCall_0());
                      		
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Attribute2_0=ruleAttribute2();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Attribute2_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalBug304681TestLanguage.g:1310:3: this_Reference2_1= ruleReference2
                    {
                    if ( state.backtracking==0 ) {

                      			/* */
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFeature2Access().getReference2ParserRuleCall_1());
                      		
                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_Reference2_1=ruleReference2();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_Reference2_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeature2"


    // $ANTLR start "entryRuleAttribute2"
    // InternalBug304681TestLanguage.g:1325:1: entryRuleAttribute2 returns [EObject current=null] : iv_ruleAttribute2= ruleAttribute2 EOF ;
    public final EObject entryRuleAttribute2() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute2 = null;



        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1()
        	);

        try {
            // InternalBug304681TestLanguage.g:1329:2: (iv_ruleAttribute2= ruleAttribute2 EOF )
            // InternalBug304681TestLanguage.g:1330:2: iv_ruleAttribute2= ruleAttribute2 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAttribute2Rule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAttribute2=ruleAttribute2();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAttribute2; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleAttribute2"


    // $ANTLR start "ruleAttribute2"
    // InternalBug304681TestLanguage.g:1339:1: ruleAttribute2 returns [EObject current=null] : (otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) ) otherlv_16= '}' ) | otherlv_17= ';' ) ) ;
    public final EObject ruleAttribute2() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_type_1_0=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token lv_shortDescription_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token lv_longDescription_9_0=null;
        Token otherlv_10=null;
        Token lv_required_12_0=null;
        Token otherlv_13=null;
        Token lv_technical_14_0=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        EObject lv_constraintDefinitions_11_0 = null;



        	enterRule();
        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1()
        	);

        try {
            // InternalBug304681TestLanguage.g:1348:2: ( (otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) ) otherlv_16= '}' ) | otherlv_17= ';' ) ) )
            // InternalBug304681TestLanguage.g:1349:2: (otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) ) otherlv_16= '}' ) | otherlv_17= ';' ) )
            {
            // InternalBug304681TestLanguage.g:1349:2: (otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) ) otherlv_16= '}' ) | otherlv_17= ';' ) )
            // InternalBug304681TestLanguage.g:1350:3: otherlv_0= 'Attribute' ( (lv_type_1_0= RULE_ID ) ) ( (lv_name_2_0= RULE_ID ) ) ( (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) ) otherlv_16= '}' ) | otherlv_17= ';' )
            {
            otherlv_0=(Token)match(input,29,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getAttribute2Access().getAttributeKeyword_0());
              		
            }
            // InternalBug304681TestLanguage.g:1354:3: ( (lv_type_1_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:1355:4: (lv_type_1_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:1355:4: (lv_type_1_0= RULE_ID )
            // InternalBug304681TestLanguage.g:1356:5: lv_type_1_0= RULE_ID
            {
            lv_type_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_type_1_0, grammarAccess.getAttribute2Access().getTypeIDTerminalRuleCall_1_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getAttribute2Rule());
              					}
              					setWithLastConsumed(
              						current,
              						"type",
              						lv_type_1_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalBug304681TestLanguage.g:1372:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:1373:4: (lv_name_2_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:1373:4: (lv_name_2_0= RULE_ID )
            // InternalBug304681TestLanguage.g:1374:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_2_0, grammarAccess.getAttribute2Access().getNameIDTerminalRuleCall_2_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getAttribute2Rule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_2_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalBug304681TestLanguage.g:1390:3: ( (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) ) otherlv_16= '}' ) | otherlv_17= ';' )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==19) ) {
                alt22=1;
            }
            else if ( (LA22_0==14) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // InternalBug304681TestLanguage.g:1391:4: (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) ) otherlv_16= '}' )
                    {
                    // InternalBug304681TestLanguage.g:1391:4: (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) ) otherlv_16= '}' )
                    // InternalBug304681TestLanguage.g:1392:5: otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) ) otherlv_16= '}'
                    {
                    otherlv_3=(Token)match(input,19,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_3, grammarAccess.getAttribute2Access().getLeftCurlyBracketKeyword_3_0_0());
                      				
                    }
                    // InternalBug304681TestLanguage.g:1396:5: ( ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) ) )
                    // InternalBug304681TestLanguage.g:1397:6: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) )
                    {
                    // InternalBug304681TestLanguage.g:1397:6: ( ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* ) )
                    // InternalBug304681TestLanguage.g:1398:7: ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* )
                    {
                    getUnorderedGroupHelper().enter(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1());
                    // InternalBug304681TestLanguage.g:1401:7: ( ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )* )
                    // InternalBug304681TestLanguage.g:1402:8: ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )*
                    {
                    // InternalBug304681TestLanguage.g:1402:8: ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) | ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )*
                    loop21:
                    do {
                        int alt21=6;
                        int LA21_0 = input.LA(1);

                        if ( LA21_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 0) ) {
                            alt21=1;
                        }
                        else if ( LA21_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 1) ) {
                            alt21=2;
                        }
                        else if ( LA21_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 2) ) {
                            alt21=3;
                        }
                        else if ( LA21_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 3) ) {
                            alt21=4;
                        }
                        else if ( LA21_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 4) ) {
                            alt21=5;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // InternalBug304681TestLanguage.g:1403:6: ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1403:6: ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) )
                    	    // InternalBug304681TestLanguage.g:1404:7: {...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 0) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleAttribute2", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 0)");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1404:113: ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) )
                    	    // InternalBug304681TestLanguage.g:1405:8: ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) )
                    	    {
                    	    getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 0);
                    	    // InternalBug304681TestLanguage.g:1408:11: ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) )
                    	    // InternalBug304681TestLanguage.g:1408:12: {...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' )
                    	    {
                    	    if ( !((true)) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleAttribute2", "true");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1408:21: (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' )
                    	    // InternalBug304681TestLanguage.g:1408:22: otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';'
                    	    {
                    	    otherlv_5=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_5, grammarAccess.getAttribute2Access().getShortDescriptionKeyword_3_0_1_0_0());
                    	      										
                    	    }
                    	    // InternalBug304681TestLanguage.g:1412:11: ( (lv_shortDescription_6_0= RULE_STRING ) )
                    	    // InternalBug304681TestLanguage.g:1413:12: (lv_shortDescription_6_0= RULE_STRING )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1413:12: (lv_shortDescription_6_0= RULE_STRING )
                    	    // InternalBug304681TestLanguage.g:1414:13: lv_shortDescription_6_0= RULE_STRING
                    	    {
                    	    lv_shortDescription_6_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      													newLeafNode(lv_shortDescription_6_0, grammarAccess.getAttribute2Access().getShortDescriptionSTRINGTerminalRuleCall_3_0_1_0_1_0());
                    	      												
                    	    }
                    	    if ( state.backtracking==0 ) {

                    	      													if (current==null) {
                    	      														current = createModelElement(grammarAccess.getAttribute2Rule());
                    	      													}
                    	      													setWithLastConsumed(
                    	      														current,
                    	      														"shortDescription",
                    	      														lv_shortDescription_6_0,
                    	      														"org.eclipse.xtext.common.Terminals.STRING");
                    	      												
                    	    }

                    	    }


                    	    }

                    	    otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_7, grammarAccess.getAttribute2Access().getSemicolonKeyword_3_0_1_0_2());
                    	      										
                    	    }

                    	    }


                    	    }

                    	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1());

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalBug304681TestLanguage.g:1440:6: ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1440:6: ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
                    	    // InternalBug304681TestLanguage.g:1441:7: {...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 1) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleAttribute2", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 1)");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1441:113: ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
                    	    // InternalBug304681TestLanguage.g:1442:8: ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
                    	    {
                    	    getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 1);
                    	    // InternalBug304681TestLanguage.g:1445:11: ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
                    	    // InternalBug304681TestLanguage.g:1445:12: {...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
                    	    {
                    	    if ( !((true)) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleAttribute2", "true");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1445:21: (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
                    	    // InternalBug304681TestLanguage.g:1445:22: otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';'
                    	    {
                    	    otherlv_8=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_8, grammarAccess.getAttribute2Access().getLongDescriptionKeyword_3_0_1_1_0());
                    	      										
                    	    }
                    	    // InternalBug304681TestLanguage.g:1449:11: ( (lv_longDescription_9_0= RULE_STRING ) )
                    	    // InternalBug304681TestLanguage.g:1450:12: (lv_longDescription_9_0= RULE_STRING )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1450:12: (lv_longDescription_9_0= RULE_STRING )
                    	    // InternalBug304681TestLanguage.g:1451:13: lv_longDescription_9_0= RULE_STRING
                    	    {
                    	    lv_longDescription_9_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      													newLeafNode(lv_longDescription_9_0, grammarAccess.getAttribute2Access().getLongDescriptionSTRINGTerminalRuleCall_3_0_1_1_1_0());
                    	      												
                    	    }
                    	    if ( state.backtracking==0 ) {

                    	      													if (current==null) {
                    	      														current = createModelElement(grammarAccess.getAttribute2Rule());
                    	      													}
                    	      													setWithLastConsumed(
                    	      														current,
                    	      														"longDescription",
                    	      														lv_longDescription_9_0,
                    	      														"org.eclipse.xtext.common.Terminals.STRING");
                    	      												
                    	    }

                    	    }


                    	    }

                    	    otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_10, grammarAccess.getAttribute2Access().getSemicolonKeyword_3_0_1_1_2());
                    	      										
                    	    }

                    	    }


                    	    }

                    	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1());

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalBug304681TestLanguage.g:1477:6: ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1477:6: ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) )
                    	    // InternalBug304681TestLanguage.g:1478:7: {...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 2) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleAttribute2", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 2)");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1478:113: ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ )
                    	    // InternalBug304681TestLanguage.g:1479:8: ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+
                    	    {
                    	    getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 2);
                    	    // InternalBug304681TestLanguage.g:1482:11: ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+
                    	    int cnt20=0;
                    	    loop20:
                    	    do {
                    	        int alt20=2;
                    	        int LA20_0 = input.LA(1);

                    	        if ( (LA20_0==34) ) {
                    	            int LA20_2 = input.LA(2);

                    	            if ( ((synpred36_InternalBug304681TestLanguage()&&(true))) ) {
                    	                alt20=1;
                    	            }


                    	        }


                    	        switch (alt20) {
                    	    	case 1 :
                    	    	    // InternalBug304681TestLanguage.g:1482:12: {...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) )
                    	    	    {
                    	    	    if ( !((true)) ) {
                    	    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	    	        throw new FailedPredicateException(input, "ruleAttribute2", "true");
                    	    	    }
                    	    	    // InternalBug304681TestLanguage.g:1482:21: ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) )
                    	    	    // InternalBug304681TestLanguage.g:1482:22: (lv_constraintDefinitions_11_0= ruleConstraintDefinition )
                    	    	    {
                    	    	    // InternalBug304681TestLanguage.g:1482:22: (lv_constraintDefinitions_11_0= ruleConstraintDefinition )
                    	    	    // InternalBug304681TestLanguage.g:1483:12: lv_constraintDefinitions_11_0= ruleConstraintDefinition
                    	    	    {
                    	    	    if ( state.backtracking==0 ) {

                    	    	      												newCompositeNode(grammarAccess.getAttribute2Access().getConstraintDefinitionsConstraintDefinitionParserRuleCall_3_0_1_2_0());
                    	    	      											
                    	    	    }
                    	    	    pushFollow(FollowSets000.FOLLOW_19);
                    	    	    lv_constraintDefinitions_11_0=ruleConstraintDefinition();

                    	    	    state._fsp--;
                    	    	    if (state.failed) return current;
                    	    	    if ( state.backtracking==0 ) {

                    	    	      												if (current==null) {
                    	    	      													current = createModelElementForParent(grammarAccess.getAttribute2Rule());
                    	    	      												}
                    	    	      												add(
                    	    	      													current,
                    	    	      													"constraintDefinitions",
                    	    	      													lv_constraintDefinitions_11_0,
                    	    	      													"org.eclipse.xtext.ui.tests.editor.contentassist.Bug304681TestLanguage.ConstraintDefinition");
                    	    	      												afterParserOrEnumRuleCall();
                    	    	      											
                    	    	    }

                    	    	    }


                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    if ( cnt20 >= 1 ) break loop20;
                    	    	    if (state.backtracking>0) {state.failed=true; return current;}
                    	                EarlyExitException eee =
                    	                    new EarlyExitException(20, input);
                    	                throw eee;
                    	        }
                    	        cnt20++;
                    	    } while (true);

                    	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1());

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // InternalBug304681TestLanguage.g:1505:6: ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1505:6: ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) )
                    	    // InternalBug304681TestLanguage.g:1506:7: {...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 3) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleAttribute2", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 3)");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1506:113: ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) )
                    	    // InternalBug304681TestLanguage.g:1507:8: ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) )
                    	    {
                    	    getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 3);
                    	    // InternalBug304681TestLanguage.g:1510:11: ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) )
                    	    // InternalBug304681TestLanguage.g:1510:12: {...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' )
                    	    {
                    	    if ( !((true)) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleAttribute2", "true");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1510:21: ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' )
                    	    // InternalBug304681TestLanguage.g:1510:22: ( (lv_required_12_0= 'required' ) ) otherlv_13= ';'
                    	    {
                    	    // InternalBug304681TestLanguage.g:1510:22: ( (lv_required_12_0= 'required' ) )
                    	    // InternalBug304681TestLanguage.g:1511:12: (lv_required_12_0= 'required' )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1511:12: (lv_required_12_0= 'required' )
                    	    // InternalBug304681TestLanguage.g:1512:13: lv_required_12_0= 'required'
                    	    {
                    	    lv_required_12_0=(Token)match(input,32,FollowSets000.FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      													newLeafNode(lv_required_12_0, grammarAccess.getAttribute2Access().getRequiredRequiredKeyword_3_0_1_3_0_0());
                    	      												
                    	    }
                    	    if ( state.backtracking==0 ) {

                    	      													if (current==null) {
                    	      														current = createModelElement(grammarAccess.getAttribute2Rule());
                    	      													}
                    	      													setWithLastConsumed(current, "required", lv_required_12_0 != null, "required");
                    	      												
                    	    }

                    	    }


                    	    }

                    	    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_13, grammarAccess.getAttribute2Access().getSemicolonKeyword_3_0_1_3_1());
                    	      										
                    	    }

                    	    }


                    	    }

                    	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1());

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 5 :
                    	    // InternalBug304681TestLanguage.g:1534:6: ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1534:6: ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) )
                    	    // InternalBug304681TestLanguage.g:1535:7: {...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 4) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleAttribute2", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 4)");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1535:113: ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) )
                    	    // InternalBug304681TestLanguage.g:1536:8: ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) )
                    	    {
                    	    getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 4);
                    	    // InternalBug304681TestLanguage.g:1539:11: ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) )
                    	    // InternalBug304681TestLanguage.g:1539:12: {...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' )
                    	    {
                    	    if ( !((true)) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleAttribute2", "true");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1539:21: ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' )
                    	    // InternalBug304681TestLanguage.g:1539:22: ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';'
                    	    {
                    	    // InternalBug304681TestLanguage.g:1539:22: ( (lv_technical_14_0= 'technical' ) )
                    	    // InternalBug304681TestLanguage.g:1540:12: (lv_technical_14_0= 'technical' )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1540:12: (lv_technical_14_0= 'technical' )
                    	    // InternalBug304681TestLanguage.g:1541:13: lv_technical_14_0= 'technical'
                    	    {
                    	    lv_technical_14_0=(Token)match(input,33,FollowSets000.FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      													newLeafNode(lv_technical_14_0, grammarAccess.getAttribute2Access().getTechnicalTechnicalKeyword_3_0_1_4_0_0());
                    	      												
                    	    }
                    	    if ( state.backtracking==0 ) {

                    	      													if (current==null) {
                    	      														current = createModelElement(grammarAccess.getAttribute2Rule());
                    	      													}
                    	      													setWithLastConsumed(current, "technical", lv_technical_14_0 != null, "technical");
                    	      												
                    	    }

                    	    }


                    	    }

                    	    otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_15, grammarAccess.getAttribute2Access().getSemicolonKeyword_3_0_1_4_1());
                    	      										
                    	    }

                    	    }


                    	    }

                    	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1());

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    }


                    }

                    getUnorderedGroupHelper().leave(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1());

                    }

                    otherlv_16=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_16, grammarAccess.getAttribute2Access().getRightCurlyBracketKeyword_3_0_2());
                      				
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBug304681TestLanguage.g:1576:4: otherlv_17= ';'
                    {
                    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_17, grammarAccess.getAttribute2Access().getSemicolonKeyword_3_1());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleAttribute2"


    // $ANTLR start "entryRuleReference2"
    // InternalBug304681TestLanguage.g:1588:1: entryRuleReference2 returns [EObject current=null] : iv_ruleReference2= ruleReference2 EOF ;
    public final EObject entryRuleReference2() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReference2 = null;



        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getReference2Access().getUnorderedGroup_4_0_1()
        	);

        try {
            // InternalBug304681TestLanguage.g:1592:2: (iv_ruleReference2= ruleReference2 EOF )
            // InternalBug304681TestLanguage.g:1593:2: iv_ruleReference2= ruleReference2 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReference2Rule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleReference2=ruleReference2();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReference2; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleReference2"


    // $ANTLR start "ruleReference2"
    // InternalBug304681TestLanguage.g:1602:1: ruleReference2 returns [EObject current=null] : (otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' ) ) ;
    public final EObject ruleReference2() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_many_2_0=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token lv_shortDescription_7_0=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token lv_longDescription_10_0=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;


        	enterRule();
        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getReference2Access().getUnorderedGroup_4_0_1()
        	);

        try {
            // InternalBug304681TestLanguage.g:1611:2: ( (otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' ) ) )
            // InternalBug304681TestLanguage.g:1612:2: (otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' ) )
            {
            // InternalBug304681TestLanguage.g:1612:2: (otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' ) )
            // InternalBug304681TestLanguage.g:1613:3: otherlv_0= 'Reference' ( (otherlv_1= RULE_ID ) ) ( (lv_many_2_0= '*' ) )? ( (lv_name_3_0= RULE_ID ) ) ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' )
            {
            otherlv_0=(Token)match(input,30,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getReference2Access().getReferenceKeyword_0());
              		
            }
            // InternalBug304681TestLanguage.g:1617:3: ( (otherlv_1= RULE_ID ) )
            // InternalBug304681TestLanguage.g:1618:4: (otherlv_1= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:1618:4: (otherlv_1= RULE_ID )
            // InternalBug304681TestLanguage.g:1619:5: otherlv_1= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					/* */
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getReference2Rule());
              					}
              				
            }
            otherlv_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getReference2Access().getTypeObjectCrossReference_1_0());
              				
            }

            }


            }

            // InternalBug304681TestLanguage.g:1633:3: ( (lv_many_2_0= '*' ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==31) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalBug304681TestLanguage.g:1634:4: (lv_many_2_0= '*' )
                    {
                    // InternalBug304681TestLanguage.g:1634:4: (lv_many_2_0= '*' )
                    // InternalBug304681TestLanguage.g:1635:5: lv_many_2_0= '*'
                    {
                    lv_many_2_0=(Token)match(input,31,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_many_2_0, grammarAccess.getReference2Access().getManyAsteriskKeyword_2_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getReference2Rule());
                      					}
                      					setWithLastConsumed(current, "many", lv_many_2_0 != null, "*");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalBug304681TestLanguage.g:1647:3: ( (lv_name_3_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:1648:4: (lv_name_3_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:1648:4: (lv_name_3_0= RULE_ID )
            // InternalBug304681TestLanguage.g:1649:5: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_3_0, grammarAccess.getReference2Access().getNameIDTerminalRuleCall_3_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getReference2Rule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_3_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            // InternalBug304681TestLanguage.g:1665:3: ( (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' ) | otherlv_13= ';' )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==19) ) {
                alt25=1;
            }
            else if ( (LA25_0==14) ) {
                alt25=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // InternalBug304681TestLanguage.g:1666:4: (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' )
                    {
                    // InternalBug304681TestLanguage.g:1666:4: (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}' )
                    // InternalBug304681TestLanguage.g:1667:5: otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) ) otherlv_12= '}'
                    {
                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_4, grammarAccess.getReference2Access().getLeftCurlyBracketKeyword_4_0_0());
                      				
                    }
                    // InternalBug304681TestLanguage.g:1671:5: ( ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) ) )
                    // InternalBug304681TestLanguage.g:1672:6: ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) )
                    {
                    // InternalBug304681TestLanguage.g:1672:6: ( ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* ) )
                    // InternalBug304681TestLanguage.g:1673:7: ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* )
                    {
                    getUnorderedGroupHelper().enter(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1());
                    // InternalBug304681TestLanguage.g:1676:7: ( ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )* )
                    // InternalBug304681TestLanguage.g:1677:8: ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )*
                    {
                    // InternalBug304681TestLanguage.g:1677:8: ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )*
                    loop24:
                    do {
                        int alt24=3;
                        int LA24_0 = input.LA(1);

                        if ( LA24_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 0) ) {
                            alt24=1;
                        }
                        else if ( LA24_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 1) ) {
                            alt24=2;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // InternalBug304681TestLanguage.g:1678:6: ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1678:6: ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
                    	    // InternalBug304681TestLanguage.g:1679:7: {...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 0) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleReference2", "getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 0)");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1679:113: ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
                    	    // InternalBug304681TestLanguage.g:1680:8: ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
                    	    {
                    	    getUnorderedGroupHelper().select(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 0);
                    	    // InternalBug304681TestLanguage.g:1683:11: ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
                    	    // InternalBug304681TestLanguage.g:1683:12: {...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' )
                    	    {
                    	    if ( !((true)) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleReference2", "true");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1683:21: (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' )
                    	    // InternalBug304681TestLanguage.g:1683:22: otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';'
                    	    {
                    	    otherlv_6=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_6, grammarAccess.getReference2Access().getShortDescriptionKeyword_4_0_1_0_0());
                    	      										
                    	    }
                    	    // InternalBug304681TestLanguage.g:1687:11: ( (lv_shortDescription_7_0= RULE_STRING ) )
                    	    // InternalBug304681TestLanguage.g:1688:12: (lv_shortDescription_7_0= RULE_STRING )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1688:12: (lv_shortDescription_7_0= RULE_STRING )
                    	    // InternalBug304681TestLanguage.g:1689:13: lv_shortDescription_7_0= RULE_STRING
                    	    {
                    	    lv_shortDescription_7_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      													newLeafNode(lv_shortDescription_7_0, grammarAccess.getReference2Access().getShortDescriptionSTRINGTerminalRuleCall_4_0_1_0_1_0());
                    	      												
                    	    }
                    	    if ( state.backtracking==0 ) {

                    	      													if (current==null) {
                    	      														current = createModelElement(grammarAccess.getReference2Rule());
                    	      													}
                    	      													setWithLastConsumed(
                    	      														current,
                    	      														"shortDescription",
                    	      														lv_shortDescription_7_0,
                    	      														"org.eclipse.xtext.common.Terminals.STRING");
                    	      												
                    	    }

                    	    }


                    	    }

                    	    otherlv_8=(Token)match(input,14,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_8, grammarAccess.getReference2Access().getSemicolonKeyword_4_0_1_0_2());
                    	      										
                    	    }

                    	    }


                    	    }

                    	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1());

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalBug304681TestLanguage.g:1715:6: ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1715:6: ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) )
                    	    // InternalBug304681TestLanguage.g:1716:7: {...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 1) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleReference2", "getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 1)");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1716:113: ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) )
                    	    // InternalBug304681TestLanguage.g:1717:8: ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) )
                    	    {
                    	    getUnorderedGroupHelper().select(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 1);
                    	    // InternalBug304681TestLanguage.g:1720:11: ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) )
                    	    // InternalBug304681TestLanguage.g:1720:12: {...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' )
                    	    {
                    	    if ( !((true)) ) {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        throw new FailedPredicateException(input, "ruleReference2", "true");
                    	    }
                    	    // InternalBug304681TestLanguage.g:1720:21: (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' )
                    	    // InternalBug304681TestLanguage.g:1720:22: otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';'
                    	    {
                    	    otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_9, grammarAccess.getReference2Access().getLongDescriptionKeyword_4_0_1_1_0());
                    	      										
                    	    }
                    	    // InternalBug304681TestLanguage.g:1724:11: ( (lv_longDescription_10_0= RULE_STRING ) )
                    	    // InternalBug304681TestLanguage.g:1725:12: (lv_longDescription_10_0= RULE_STRING )
                    	    {
                    	    // InternalBug304681TestLanguage.g:1725:12: (lv_longDescription_10_0= RULE_STRING )
                    	    // InternalBug304681TestLanguage.g:1726:13: lv_longDescription_10_0= RULE_STRING
                    	    {
                    	    lv_longDescription_10_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      													newLeafNode(lv_longDescription_10_0, grammarAccess.getReference2Access().getLongDescriptionSTRINGTerminalRuleCall_4_0_1_1_1_0());
                    	      												
                    	    }
                    	    if ( state.backtracking==0 ) {

                    	      													if (current==null) {
                    	      														current = createModelElement(grammarAccess.getReference2Rule());
                    	      													}
                    	      													setWithLastConsumed(
                    	      														current,
                    	      														"longDescription",
                    	      														lv_longDescription_10_0,
                    	      														"org.eclipse.xtext.common.Terminals.STRING");
                    	      												
                    	    }

                    	    }


                    	    }

                    	    otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      											newLeafNode(otherlv_11, grammarAccess.getReference2Access().getSemicolonKeyword_4_0_1_1_2());
                    	      										
                    	    }

                    	    }


                    	    }

                    	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1());

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);


                    }


                    }

                    getUnorderedGroupHelper().leave(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1());

                    }

                    otherlv_12=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_12, grammarAccess.getReference2Access().getRightCurlyBracketKeyword_4_0_2());
                      				
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalBug304681TestLanguage.g:1765:4: otherlv_13= ';'
                    {
                    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_13, grammarAccess.getReference2Access().getSemicolonKeyword_4_1());
                      			
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleReference2"


    // $ANTLR start "entryRuleConstraintDefinition"
    // InternalBug304681TestLanguage.g:1777:1: entryRuleConstraintDefinition returns [EObject current=null] : iv_ruleConstraintDefinition= ruleConstraintDefinition EOF ;
    public final EObject entryRuleConstraintDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraintDefinition = null;



        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3()
        	);

        try {
            // InternalBug304681TestLanguage.g:1781:2: (iv_ruleConstraintDefinition= ruleConstraintDefinition EOF )
            // InternalBug304681TestLanguage.g:1782:2: iv_ruleConstraintDefinition= ruleConstraintDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstraintDefinitionRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleConstraintDefinition=ruleConstraintDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstraintDefinition; 
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleConstraintDefinition"


    // $ANTLR start "ruleConstraintDefinition"
    // InternalBug304681TestLanguage.g:1791:1: ruleConstraintDefinition returns [EObject current=null] : (otherlv_0= 'constraint' ( (lv_type_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?) ) ) otherlv_10= '}' ) ;
    public final EObject ruleConstraintDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_type_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_parameters_5_0=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token lv_message_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;


        	enterRule();
        	UnorderedGroupState myUnorderedGroupState = getUnorderedGroupHelper().snapShot(
        	grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3()
        	);

        try {
            // InternalBug304681TestLanguage.g:1800:2: ( (otherlv_0= 'constraint' ( (lv_type_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?) ) ) otherlv_10= '}' ) )
            // InternalBug304681TestLanguage.g:1801:2: (otherlv_0= 'constraint' ( (lv_type_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?) ) ) otherlv_10= '}' )
            {
            // InternalBug304681TestLanguage.g:1801:2: (otherlv_0= 'constraint' ( (lv_type_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?) ) ) otherlv_10= '}' )
            // InternalBug304681TestLanguage.g:1802:3: otherlv_0= 'constraint' ( (lv_type_1_0= RULE_ID ) ) otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?) ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,34,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getConstraintDefinitionAccess().getConstraintKeyword_0());
              		
            }
            // InternalBug304681TestLanguage.g:1806:3: ( (lv_type_1_0= RULE_ID ) )
            // InternalBug304681TestLanguage.g:1807:4: (lv_type_1_0= RULE_ID )
            {
            // InternalBug304681TestLanguage.g:1807:4: (lv_type_1_0= RULE_ID )
            // InternalBug304681TestLanguage.g:1808:5: lv_type_1_0= RULE_ID
            {
            lv_type_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_9); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_type_1_0, grammarAccess.getConstraintDefinitionAccess().getTypeIDTerminalRuleCall_1_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getConstraintDefinitionRule());
              					}
              					setWithLastConsumed(
              						current,
              						"type",
              						lv_type_1_0,
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            otherlv_2=(Token)match(input,19,FollowSets000.FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getConstraintDefinitionAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalBug304681TestLanguage.g:1828:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?) ) )
            // InternalBug304681TestLanguage.g:1829:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?) )
            {
            // InternalBug304681TestLanguage.g:1829:4: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?) )
            // InternalBug304681TestLanguage.g:1830:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?)
            {
            getUnorderedGroupHelper().enter(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3());
            // InternalBug304681TestLanguage.g:1833:5: ( ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?)
            // InternalBug304681TestLanguage.g:1834:6: ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+ {...}?
            {
            // InternalBug304681TestLanguage.g:1834:6: ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )+
            int cnt27=0;
            loop27:
            do {
                int alt27=3;
                int LA27_0 = input.LA(1);

                if ( LA27_0 == 35 && getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 0) ) {
                    alt27=1;
                }
                else if ( LA27_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 1) ) {
                    alt27=2;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalBug304681TestLanguage.g:1835:4: ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:1835:4: ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:1836:5: {...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 0) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleConstraintDefinition", "getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 0)");
            	    }
            	    // InternalBug304681TestLanguage.g:1836:117: ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:1837:6: ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 0);
            	    // InternalBug304681TestLanguage.g:1840:9: ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) )
            	    // InternalBug304681TestLanguage.g:1840:10: {...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleConstraintDefinition", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:1840:19: (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' )
            	    // InternalBug304681TestLanguage.g:1840:20: otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';'
            	    {
            	    otherlv_4=(Token)match(input,35,FollowSets000.FOLLOW_21); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_4, grammarAccess.getConstraintDefinitionAccess().getParametersKeyword_3_0_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:1844:9: ( (lv_parameters_5_0= RULE_STRING ) )*
            	    loop26:
            	    do {
            	        int alt26=2;
            	        int LA26_0 = input.LA(1);

            	        if ( (LA26_0==RULE_STRING) ) {
            	            alt26=1;
            	        }


            	        switch (alt26) {
            	    	case 1 :
            	    	    // InternalBug304681TestLanguage.g:1845:10: (lv_parameters_5_0= RULE_STRING )
            	    	    {
            	    	    // InternalBug304681TestLanguage.g:1845:10: (lv_parameters_5_0= RULE_STRING )
            	    	    // InternalBug304681TestLanguage.g:1846:11: lv_parameters_5_0= RULE_STRING
            	    	    {
            	    	    lv_parameters_5_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_21); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      											newLeafNode(lv_parameters_5_0, grammarAccess.getConstraintDefinitionAccess().getParametersSTRINGTerminalRuleCall_3_0_1_0());
            	    	      										
            	    	    }
            	    	    if ( state.backtracking==0 ) {

            	    	      											if (current==null) {
            	    	      												current = createModelElement(grammarAccess.getConstraintDefinitionRule());
            	    	      											}
            	    	      											addWithLastConsumed(
            	    	      												current,
            	    	      												"parameters",
            	    	      												lv_parameters_5_0,
            	    	      												"org.eclipse.xtext.common.Terminals.STRING");
            	    	      										
            	    	    }

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop26;
            	        }
            	    } while (true);

            	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_22); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_6, grammarAccess.getConstraintDefinitionAccess().getSemicolonKeyword_3_0_2());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3());

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalBug304681TestLanguage.g:1872:4: ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) )
            	    {
            	    // InternalBug304681TestLanguage.g:1872:4: ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) )
            	    // InternalBug304681TestLanguage.g:1873:5: {...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 1) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleConstraintDefinition", "getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 1)");
            	    }
            	    // InternalBug304681TestLanguage.g:1873:117: ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) )
            	    // InternalBug304681TestLanguage.g:1874:6: ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) )
            	    {
            	    getUnorderedGroupHelper().select(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 1);
            	    // InternalBug304681TestLanguage.g:1877:9: ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) )
            	    // InternalBug304681TestLanguage.g:1877:10: {...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' )
            	    {
            	    if ( !((true)) ) {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        throw new FailedPredicateException(input, "ruleConstraintDefinition", "true");
            	    }
            	    // InternalBug304681TestLanguage.g:1877:19: (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' )
            	    // InternalBug304681TestLanguage.g:1877:20: otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';'
            	    {
            	    otherlv_7=(Token)match(input,36,FollowSets000.FOLLOW_11); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_7, grammarAccess.getConstraintDefinitionAccess().getMessageKeyword_3_1_0());
            	      								
            	    }
            	    // InternalBug304681TestLanguage.g:1881:9: ( (lv_message_8_0= RULE_STRING ) )
            	    // InternalBug304681TestLanguage.g:1882:10: (lv_message_8_0= RULE_STRING )
            	    {
            	    // InternalBug304681TestLanguage.g:1882:10: (lv_message_8_0= RULE_STRING )
            	    // InternalBug304681TestLanguage.g:1883:11: lv_message_8_0= RULE_STRING
            	    {
            	    lv_message_8_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      											newLeafNode(lv_message_8_0, grammarAccess.getConstraintDefinitionAccess().getMessageSTRINGTerminalRuleCall_3_1_1_0());
            	      										
            	    }
            	    if ( state.backtracking==0 ) {

            	      											if (current==null) {
            	      												current = createModelElement(grammarAccess.getConstraintDefinitionRule());
            	      											}
            	      											setWithLastConsumed(
            	      												current,
            	      												"message",
            	      												lv_message_8_0,
            	      												"org.eclipse.xtext.common.Terminals.STRING");
            	      										
            	    }

            	    }


            	    }

            	    otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_22); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									newLeafNode(otherlv_9, grammarAccess.getConstraintDefinitionAccess().getSemicolonKeyword_3_1_2());
            	      								
            	    }

            	    }


            	    }

            	    getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3());

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt27 >= 1 ) break loop27;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(27, input);
                        throw eee;
                }
                cnt27++;
            } while (true);

            if ( ! getUnorderedGroupHelper().canLeave(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3()) ) {
                if (state.backtracking>0) {state.failed=true; return current;}
                throw new FailedPredicateException(input, "ruleConstraintDefinition", "getUnorderedGroupHelper().canLeave(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3())");
            }

            }


            }

            getUnorderedGroupHelper().leave(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3());

            }

            otherlv_10=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_10, grammarAccess.getConstraintDefinitionAccess().getRightCurlyBracketKeyword_4());
              		
            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myUnorderedGroupState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleConstraintDefinition"

    // $ANTLR start synpred6_InternalBug304681TestLanguage
    public final void synpred6_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_8=null;
        Token lv_shortDescription_9_0=null;
        Token otherlv_10=null;

        // InternalBug304681TestLanguage.g:326:4: ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:326:4: ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:326:4: ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:327:5: {...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 0) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred6_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 0)");
        }
        // InternalBug304681TestLanguage.g:327:103: ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
        // InternalBug304681TestLanguage.g:328:6: ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 0);
        // InternalBug304681TestLanguage.g:331:9: ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
        // InternalBug304681TestLanguage.g:331:10: {...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred6_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:331:19: (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
        // InternalBug304681TestLanguage.g:331:20: otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';'
        {
        otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:335:9: ( (lv_shortDescription_9_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:336:10: (lv_shortDescription_9_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:336:10: (lv_shortDescription_9_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:337:11: lv_shortDescription_9_0= RULE_STRING
        {
        lv_shortDescription_9_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred6_InternalBug304681TestLanguage

    // $ANTLR start synpred7_InternalBug304681TestLanguage
    public final void synpred7_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_11=null;
        Token lv_longDescription_12_0=null;
        Token otherlv_13=null;

        // InternalBug304681TestLanguage.g:363:4: ( ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:363:4: ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:363:4: ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:364:5: {...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 1) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred7_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 1)");
        }
        // InternalBug304681TestLanguage.g:364:103: ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
        // InternalBug304681TestLanguage.g:365:6: ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 1);
        // InternalBug304681TestLanguage.g:368:9: ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
        // InternalBug304681TestLanguage.g:368:10: {...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred7_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:368:19: (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' )
        // InternalBug304681TestLanguage.g:368:20: otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';'
        {
        otherlv_11=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:372:9: ( (lv_longDescription_12_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:373:10: (lv_longDescription_12_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:373:10: (lv_longDescription_12_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:374:11: lv_longDescription_12_0= RULE_STRING
        {
        lv_longDescription_12_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred7_InternalBug304681TestLanguage

    // $ANTLR start synpred8_InternalBug304681TestLanguage
    public final void synpred8_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_14=null;
        Token lv_serialVersionUID_15_0=null;
        Token otherlv_16=null;

        // InternalBug304681TestLanguage.g:400:4: ( ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:400:4: ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:400:4: ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:401:5: {...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 2) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred8_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 2)");
        }
        // InternalBug304681TestLanguage.g:401:103: ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
        // InternalBug304681TestLanguage.g:402:6: ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 2);
        // InternalBug304681TestLanguage.g:405:9: ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) )
        // InternalBug304681TestLanguage.g:405:10: {...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred8_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:405:19: (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' )
        // InternalBug304681TestLanguage.g:405:20: otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';'
        {
        otherlv_14=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:409:9: ( (lv_serialVersionUID_15_0= RULE_INT ) )
        // InternalBug304681TestLanguage.g:410:10: (lv_serialVersionUID_15_0= RULE_INT )
        {
        // InternalBug304681TestLanguage.g:410:10: (lv_serialVersionUID_15_0= RULE_INT )
        // InternalBug304681TestLanguage.g:411:11: lv_serialVersionUID_15_0= RULE_INT
        {
        lv_serialVersionUID_15_0=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred8_InternalBug304681TestLanguage

    // $ANTLR start synpred9_InternalBug304681TestLanguage
    public final void synpred9_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token lv_cloneable_17_0=null;
        Token otherlv_18=null;

        // InternalBug304681TestLanguage.g:437:4: ( ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:437:4: ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:437:4: ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:438:5: {...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 3) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred9_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 3)");
        }
        // InternalBug304681TestLanguage.g:438:103: ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) )
        // InternalBug304681TestLanguage.g:439:6: ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 3);
        // InternalBug304681TestLanguage.g:442:9: ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) )
        // InternalBug304681TestLanguage.g:442:10: {...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred9_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:442:19: ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' )
        // InternalBug304681TestLanguage.g:442:20: ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';'
        {
        // InternalBug304681TestLanguage.g:442:20: ( (lv_cloneable_17_0= 'cloneable' ) )
        // InternalBug304681TestLanguage.g:443:10: (lv_cloneable_17_0= 'cloneable' )
        {
        // InternalBug304681TestLanguage.g:443:10: (lv_cloneable_17_0= 'cloneable' )
        // InternalBug304681TestLanguage.g:444:11: lv_cloneable_17_0= 'cloneable'
        {
        lv_cloneable_17_0=(Token)match(input,23,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred9_InternalBug304681TestLanguage

    // $ANTLR start synpred10_InternalBug304681TestLanguage
    public final void synpred10_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        EObject lv_features_19_0 = null;


        // InternalBug304681TestLanguage.g:471:10: ({...}? => ( (lv_features_19_0= ruleFeature ) ) )
        // InternalBug304681TestLanguage.g:471:10: {...}? => ( (lv_features_19_0= ruleFeature ) )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred10_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:471:19: ( (lv_features_19_0= ruleFeature ) )
        // InternalBug304681TestLanguage.g:471:20: (lv_features_19_0= ruleFeature )
        {
        // InternalBug304681TestLanguage.g:471:20: (lv_features_19_0= ruleFeature )
        // InternalBug304681TestLanguage.g:472:10: lv_features_19_0= ruleFeature
        {
        if ( state.backtracking==0 ) {

          										newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_4_0());
          									
        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_features_19_0=ruleFeature();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred10_InternalBug304681TestLanguage

    // $ANTLR start synpred11_InternalBug304681TestLanguage
    public final void synpred11_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        EObject lv_features_19_0 = null;


        // InternalBug304681TestLanguage.g:466:4: ( ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) )
        // InternalBug304681TestLanguage.g:466:4: ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) )
        {
        // InternalBug304681TestLanguage.g:466:4: ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) )
        // InternalBug304681TestLanguage.g:467:5: {...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 4) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred11_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 4)");
        }
        // InternalBug304681TestLanguage.g:467:103: ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ )
        // InternalBug304681TestLanguage.g:468:6: ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+
        {
        getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 4);
        // InternalBug304681TestLanguage.g:471:9: ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+
        int cnt28=0;
        loop28:
        do {
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==29) && ((true))) {
                alt28=1;
            }
            else if ( (LA28_0==30) && ((true))) {
                alt28=1;
            }


            switch (alt28) {
        	case 1 :
        	    // InternalBug304681TestLanguage.g:471:10: {...}? => ( (lv_features_19_0= ruleFeature ) )
        	    {
        	    if ( !((true)) ) {
        	        if (state.backtracking>0) {state.failed=true; return ;}
        	        throw new FailedPredicateException(input, "synpred11_InternalBug304681TestLanguage", "true");
        	    }
        	    // InternalBug304681TestLanguage.g:471:19: ( (lv_features_19_0= ruleFeature ) )
        	    // InternalBug304681TestLanguage.g:471:20: (lv_features_19_0= ruleFeature )
        	    {
        	    // InternalBug304681TestLanguage.g:471:20: (lv_features_19_0= ruleFeature )
        	    // InternalBug304681TestLanguage.g:472:10: lv_features_19_0= ruleFeature
        	    {
        	    if ( state.backtracking==0 ) {

        	      										newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_4_0());
        	      									
        	    }
        	    pushFollow(FollowSets000.FOLLOW_23);
        	    lv_features_19_0=ruleFeature();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }


        	    }


        	    }
        	    break;

        	default :
        	    if ( cnt28 >= 1 ) break loop28;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(28, input);
                    throw eee;
            }
            cnt28++;
        } while (true);


        }


        }


        }
    }
    // $ANTLR end synpred11_InternalBug304681TestLanguage

    // $ANTLR start synpred13_InternalBug304681TestLanguage
    public final void synpred13_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_20=null;
        Token otherlv_22=null;
        EObject lv_features_21_0 = null;


        // InternalBug304681TestLanguage.g:494:4: ( ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) )
        // InternalBug304681TestLanguage.g:494:4: ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:494:4: ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) )
        // InternalBug304681TestLanguage.g:495:5: {...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 5) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred13_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 5)");
        }
        // InternalBug304681TestLanguage.g:495:103: ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) )
        // InternalBug304681TestLanguage.g:496:6: ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 5);
        // InternalBug304681TestLanguage.g:499:9: ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) )
        // InternalBug304681TestLanguage.g:499:10: {...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred13_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:499:19: (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' )
        // InternalBug304681TestLanguage.g:499:20: otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after'
        {
        otherlv_20=(Token)match(input,24,FollowSets000.FOLLOW_13); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:503:9: ( (lv_features_21_0= ruleFeature ) )*
        loop29:
        do {
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=29 && LA29_0<=30)) ) {
                alt29=1;
            }


            switch (alt29) {
        	case 1 :
        	    // InternalBug304681TestLanguage.g:504:10: (lv_features_21_0= ruleFeature )
        	    {
        	    // InternalBug304681TestLanguage.g:504:10: (lv_features_21_0= ruleFeature )
        	    // InternalBug304681TestLanguage.g:505:11: lv_features_21_0= ruleFeature
        	    {
        	    if ( state.backtracking==0 ) {

        	      											newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_5_1_0());
        	      										
        	    }
        	    pushFollow(FollowSets000.FOLLOW_13);
        	    lv_features_21_0=ruleFeature();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }


        	    }
        	    break;

        	default :
        	    break loop29;
            }
        } while (true);

        otherlv_22=(Token)match(input,25,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred13_InternalBug304681TestLanguage

    // $ANTLR start synpred14_InternalBug304681TestLanguage
    public final void synpred14_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        EObject lv_features_24_0 = null;


        // InternalBug304681TestLanguage.g:542:10: ( (lv_features_24_0= ruleFeature ) )
        // InternalBug304681TestLanguage.g:542:10: (lv_features_24_0= ruleFeature )
        {
        // InternalBug304681TestLanguage.g:542:10: (lv_features_24_0= ruleFeature )
        // InternalBug304681TestLanguage.g:543:11: lv_features_24_0= ruleFeature
        {
        if ( state.backtracking==0 ) {

          											newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_6_1_0());
          										
        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_features_24_0=ruleFeature();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred14_InternalBug304681TestLanguage

    // $ANTLR start synpred15_InternalBug304681TestLanguage
    public final void synpred15_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_23=null;
        EObject lv_features_24_0 = null;


        // InternalBug304681TestLanguage.g:532:4: ( ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) )
        // InternalBug304681TestLanguage.g:532:4: ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) )
        {
        // InternalBug304681TestLanguage.g:532:4: ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) )
        // InternalBug304681TestLanguage.g:533:5: {...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 6) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred15_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 6)");
        }
        // InternalBug304681TestLanguage.g:533:103: ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) )
        // InternalBug304681TestLanguage.g:534:6: ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 6);
        // InternalBug304681TestLanguage.g:537:9: ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) )
        // InternalBug304681TestLanguage.g:537:10: {...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred15_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:537:19: (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* )
        // InternalBug304681TestLanguage.g:537:20: otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )*
        {
        otherlv_23=(Token)match(input,26,FollowSets000.FOLLOW_23); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:541:9: ( (lv_features_24_0= ruleFeature ) )*
        loop30:
        do {
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>=29 && LA30_0<=30)) ) {
                alt30=1;
            }


            switch (alt30) {
        	case 1 :
        	    // InternalBug304681TestLanguage.g:542:10: (lv_features_24_0= ruleFeature )
        	    {
        	    // InternalBug304681TestLanguage.g:542:10: (lv_features_24_0= ruleFeature )
        	    // InternalBug304681TestLanguage.g:543:11: lv_features_24_0= ruleFeature
        	    {
        	    if ( state.backtracking==0 ) {

        	      											newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_6_1_0());
        	      										
        	    }
        	    pushFollow(FollowSets000.FOLLOW_23);
        	    lv_features_24_0=ruleFeature();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }


        	    }
        	    break;

        	default :
        	    break loop30;
            }
        } while (true);


        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred15_InternalBug304681TestLanguage

    // $ANTLR start synpred16_InternalBug304681TestLanguage
    public final void synpred16_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        EObject lv_features_26_0 = null;


        // InternalBug304681TestLanguage.g:576:10: ( (lv_features_26_0= ruleFeature ) )
        // InternalBug304681TestLanguage.g:576:10: (lv_features_26_0= ruleFeature )
        {
        // InternalBug304681TestLanguage.g:576:10: (lv_features_26_0= ruleFeature )
        // InternalBug304681TestLanguage.g:577:11: lv_features_26_0= ruleFeature
        {
        if ( state.backtracking==0 ) {

          											newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_7_1_0());
          										
        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_features_26_0=ruleFeature();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred16_InternalBug304681TestLanguage

    // $ANTLR start synpred17_InternalBug304681TestLanguage
    public final void synpred17_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_25=null;
        EObject lv_features_26_0 = null;


        // InternalBug304681TestLanguage.g:566:4: ( ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )
        // InternalBug304681TestLanguage.g:566:4: ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) )
        {
        // InternalBug304681TestLanguage.g:566:4: ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) )
        // InternalBug304681TestLanguage.g:567:5: {...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 7) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred17_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 7)");
        }
        // InternalBug304681TestLanguage.g:567:103: ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) )
        // InternalBug304681TestLanguage.g:568:6: ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 7);
        // InternalBug304681TestLanguage.g:571:9: ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) )
        // InternalBug304681TestLanguage.g:571:10: {...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred17_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:571:19: (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ )
        // InternalBug304681TestLanguage.g:571:20: otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+
        {
        otherlv_25=(Token)match(input,27,FollowSets000.FOLLOW_14); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:575:9: ( (lv_features_26_0= ruleFeature ) )+
        int cnt31=0;
        loop31:
        do {
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=29 && LA31_0<=30)) ) {
                alt31=1;
            }


            switch (alt31) {
        	case 1 :
        	    // InternalBug304681TestLanguage.g:576:10: (lv_features_26_0= ruleFeature )
        	    {
        	    // InternalBug304681TestLanguage.g:576:10: (lv_features_26_0= ruleFeature )
        	    // InternalBug304681TestLanguage.g:577:11: lv_features_26_0= ruleFeature
        	    {
        	    if ( state.backtracking==0 ) {

        	      											newCompositeNode(grammarAccess.getObjectAccess().getFeaturesFeatureParserRuleCall_5_7_1_0());
        	      										
        	    }
        	    pushFollow(FollowSets000.FOLLOW_23);
        	    lv_features_26_0=ruleFeature();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }


        	    }
        	    break;

        	default :
        	    if ( cnt31 >= 1 ) break loop31;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(31, input);
                    throw eee;
            }
            cnt31++;
        } while (true);


        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred17_InternalBug304681TestLanguage

    // $ANTLR start synpred20_InternalBug304681TestLanguage
    public final void synpred20_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_6=null;
        Token lv_shortDescription_7_0=null;
        Token otherlv_8=null;

        // InternalBug304681TestLanguage.g:813:6: ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:813:6: ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:813:6: ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:814:7: {...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 0) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred20_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 0)");
        }
        // InternalBug304681TestLanguage.g:814:112: ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
        // InternalBug304681TestLanguage.g:815:8: ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 0);
        // InternalBug304681TestLanguage.g:818:11: ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
        // InternalBug304681TestLanguage.g:818:12: {...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred20_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:818:21: (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' )
        // InternalBug304681TestLanguage.g:818:22: otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';'
        {
        otherlv_6=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:822:11: ( (lv_shortDescription_7_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:823:12: (lv_shortDescription_7_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:823:12: (lv_shortDescription_7_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:824:13: lv_shortDescription_7_0= RULE_STRING
        {
        lv_shortDescription_7_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_8=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred20_InternalBug304681TestLanguage

    // $ANTLR start synpred21_InternalBug304681TestLanguage
    public final void synpred21_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_9=null;
        Token lv_longDescription_10_0=null;
        Token otherlv_11=null;

        // InternalBug304681TestLanguage.g:850:6: ( ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:850:6: ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:850:6: ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:851:7: {...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 1) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred21_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 1)");
        }
        // InternalBug304681TestLanguage.g:851:112: ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) )
        // InternalBug304681TestLanguage.g:852:8: ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getReferenceAccess().getUnorderedGroup_4_0_1(), 1);
        // InternalBug304681TestLanguage.g:855:11: ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) )
        // InternalBug304681TestLanguage.g:855:12: {...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred21_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:855:21: (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' )
        // InternalBug304681TestLanguage.g:855:22: otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';'
        {
        otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:859:11: ( (lv_longDescription_10_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:860:12: (lv_longDescription_10_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:860:12: (lv_longDescription_10_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:861:13: lv_longDescription_10_0= RULE_STRING
        {
        lv_longDescription_10_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred21_InternalBug304681TestLanguage

    // $ANTLR start synpred27_InternalBug304681TestLanguage
    public final void synpred27_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_8=null;
        Token lv_shortDescription_9_0=null;
        Token otherlv_10=null;

        // InternalBug304681TestLanguage.g:1097:4: ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1097:4: ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1097:4: ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1098:5: {...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 0) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred27_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 0)");
        }
        // InternalBug304681TestLanguage.g:1098:104: ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
        // InternalBug304681TestLanguage.g:1099:6: ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 0);
        // InternalBug304681TestLanguage.g:1102:9: ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
        // InternalBug304681TestLanguage.g:1102:10: {...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred27_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1102:19: (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
        // InternalBug304681TestLanguage.g:1102:20: otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';'
        {
        otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:1106:9: ( (lv_shortDescription_9_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:1107:10: (lv_shortDescription_9_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:1107:10: (lv_shortDescription_9_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:1108:11: lv_shortDescription_9_0= RULE_STRING
        {
        lv_shortDescription_9_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred27_InternalBug304681TestLanguage

    // $ANTLR start synpred28_InternalBug304681TestLanguage
    public final void synpred28_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_11=null;
        Token lv_longDescription_12_0=null;
        Token otherlv_13=null;

        // InternalBug304681TestLanguage.g:1134:4: ( ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1134:4: ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1134:4: ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1135:5: {...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 1) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred28_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 1)");
        }
        // InternalBug304681TestLanguage.g:1135:104: ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) )
        // InternalBug304681TestLanguage.g:1136:6: ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 1);
        // InternalBug304681TestLanguage.g:1139:9: ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) )
        // InternalBug304681TestLanguage.g:1139:10: {...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred28_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1139:19: (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' )
        // InternalBug304681TestLanguage.g:1139:20: otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';'
        {
        otherlv_11=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:1143:9: ( (lv_longDescription_12_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:1144:10: (lv_longDescription_12_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:1144:10: (lv_longDescription_12_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:1145:11: lv_longDescription_12_0= RULE_STRING
        {
        lv_longDescription_12_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred28_InternalBug304681TestLanguage

    // $ANTLR start synpred29_InternalBug304681TestLanguage
    public final void synpred29_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_14=null;
        Token lv_serialVersionUID_15_0=null;
        Token otherlv_16=null;

        // InternalBug304681TestLanguage.g:1171:4: ( ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1171:4: ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1171:4: ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1172:5: {...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 2) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred29_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 2)");
        }
        // InternalBug304681TestLanguage.g:1172:104: ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) )
        // InternalBug304681TestLanguage.g:1173:6: ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 2);
        // InternalBug304681TestLanguage.g:1176:9: ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) )
        // InternalBug304681TestLanguage.g:1176:10: {...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred29_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1176:19: (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' )
        // InternalBug304681TestLanguage.g:1176:20: otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';'
        {
        otherlv_14=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:1180:9: ( (lv_serialVersionUID_15_0= RULE_INT ) )
        // InternalBug304681TestLanguage.g:1181:10: (lv_serialVersionUID_15_0= RULE_INT )
        {
        // InternalBug304681TestLanguage.g:1181:10: (lv_serialVersionUID_15_0= RULE_INT )
        // InternalBug304681TestLanguage.g:1182:11: lv_serialVersionUID_15_0= RULE_INT
        {
        lv_serialVersionUID_15_0=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred29_InternalBug304681TestLanguage

    // $ANTLR start synpred30_InternalBug304681TestLanguage
    public final void synpred30_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token lv_cloneable_17_0=null;
        Token otherlv_18=null;

        // InternalBug304681TestLanguage.g:1208:4: ( ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1208:4: ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1208:4: ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1209:5: {...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 3) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred30_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 3)");
        }
        // InternalBug304681TestLanguage.g:1209:104: ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) )
        // InternalBug304681TestLanguage.g:1210:6: ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 3);
        // InternalBug304681TestLanguage.g:1213:9: ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) )
        // InternalBug304681TestLanguage.g:1213:10: {...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred30_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1213:19: ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' )
        // InternalBug304681TestLanguage.g:1213:20: ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';'
        {
        // InternalBug304681TestLanguage.g:1213:20: ( (lv_cloneable_17_0= 'cloneable' ) )
        // InternalBug304681TestLanguage.g:1214:10: (lv_cloneable_17_0= 'cloneable' )
        {
        // InternalBug304681TestLanguage.g:1214:10: (lv_cloneable_17_0= 'cloneable' )
        // InternalBug304681TestLanguage.g:1215:11: lv_cloneable_17_0= 'cloneable'
        {
        lv_cloneable_17_0=(Token)match(input,23,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred30_InternalBug304681TestLanguage

    // $ANTLR start synpred31_InternalBug304681TestLanguage
    public final void synpred31_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        EObject lv_features_19_0 = null;


        // InternalBug304681TestLanguage.g:1242:10: ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )
        // InternalBug304681TestLanguage.g:1242:10: {...}? => ( (lv_features_19_0= ruleFeature2 ) )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred31_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1242:19: ( (lv_features_19_0= ruleFeature2 ) )
        // InternalBug304681TestLanguage.g:1242:20: (lv_features_19_0= ruleFeature2 )
        {
        // InternalBug304681TestLanguage.g:1242:20: (lv_features_19_0= ruleFeature2 )
        // InternalBug304681TestLanguage.g:1243:10: lv_features_19_0= ruleFeature2
        {
        if ( state.backtracking==0 ) {

          										newCompositeNode(grammarAccess.getObject2Access().getFeaturesFeature2ParserRuleCall_5_4_0());
          									
        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_features_19_0=ruleFeature2();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred31_InternalBug304681TestLanguage

    // $ANTLR start synpred32_InternalBug304681TestLanguage
    public final void synpred32_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        EObject lv_features_19_0 = null;


        // InternalBug304681TestLanguage.g:1237:4: ( ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) ) )
        // InternalBug304681TestLanguage.g:1237:4: ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) )
        {
        // InternalBug304681TestLanguage.g:1237:4: ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ ) )
        // InternalBug304681TestLanguage.g:1238:5: {...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 4) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred32_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getObject2Access().getUnorderedGroup_5(), 4)");
        }
        // InternalBug304681TestLanguage.g:1238:104: ( ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+ )
        // InternalBug304681TestLanguage.g:1239:6: ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+
        {
        getUnorderedGroupHelper().select(grammarAccess.getObject2Access().getUnorderedGroup_5(), 4);
        // InternalBug304681TestLanguage.g:1242:9: ({...}? => ( (lv_features_19_0= ruleFeature2 ) ) )+
        int cnt33=0;
        loop33:
        do {
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==29) && ((true))) {
                alt33=1;
            }
            else if ( (LA33_0==30) && ((true))) {
                alt33=1;
            }


            switch (alt33) {
        	case 1 :
        	    // InternalBug304681TestLanguage.g:1242:10: {...}? => ( (lv_features_19_0= ruleFeature2 ) )
        	    {
        	    if ( !((true)) ) {
        	        if (state.backtracking>0) {state.failed=true; return ;}
        	        throw new FailedPredicateException(input, "synpred32_InternalBug304681TestLanguage", "true");
        	    }
        	    // InternalBug304681TestLanguage.g:1242:19: ( (lv_features_19_0= ruleFeature2 ) )
        	    // InternalBug304681TestLanguage.g:1242:20: (lv_features_19_0= ruleFeature2 )
        	    {
        	    // InternalBug304681TestLanguage.g:1242:20: (lv_features_19_0= ruleFeature2 )
        	    // InternalBug304681TestLanguage.g:1243:10: lv_features_19_0= ruleFeature2
        	    {
        	    if ( state.backtracking==0 ) {

        	      										newCompositeNode(grammarAccess.getObject2Access().getFeaturesFeature2ParserRuleCall_5_4_0());
        	      									
        	    }
        	    pushFollow(FollowSets000.FOLLOW_24);
        	    lv_features_19_0=ruleFeature2();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }


        	    }


        	    }
        	    break;

        	default :
        	    if ( cnt33 >= 1 ) break loop33;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(33, input);
                    throw eee;
            }
            cnt33++;
        } while (true);


        }


        }


        }
    }
    // $ANTLR end synpred32_InternalBug304681TestLanguage

    // $ANTLR start synpred34_InternalBug304681TestLanguage
    public final void synpred34_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_5=null;
        Token lv_shortDescription_6_0=null;
        Token otherlv_7=null;

        // InternalBug304681TestLanguage.g:1403:6: ( ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1403:6: ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1403:6: ({...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1404:7: {...}? => ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 0) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred34_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 0)");
        }
        // InternalBug304681TestLanguage.g:1404:113: ( ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) ) )
        // InternalBug304681TestLanguage.g:1405:8: ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 0);
        // InternalBug304681TestLanguage.g:1408:11: ({...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' ) )
        // InternalBug304681TestLanguage.g:1408:12: {...}? => (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred34_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1408:21: (otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';' )
        // InternalBug304681TestLanguage.g:1408:22: otherlv_5= 'shortDescription' ( (lv_shortDescription_6_0= RULE_STRING ) ) otherlv_7= ';'
        {
        otherlv_5=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:1412:11: ( (lv_shortDescription_6_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:1413:12: (lv_shortDescription_6_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:1413:12: (lv_shortDescription_6_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:1414:13: lv_shortDescription_6_0= RULE_STRING
        {
        lv_shortDescription_6_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred34_InternalBug304681TestLanguage

    // $ANTLR start synpred35_InternalBug304681TestLanguage
    public final void synpred35_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_8=null;
        Token lv_longDescription_9_0=null;
        Token otherlv_10=null;

        // InternalBug304681TestLanguage.g:1440:6: ( ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1440:6: ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1440:6: ({...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1441:7: {...}? => ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 1) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred35_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 1)");
        }
        // InternalBug304681TestLanguage.g:1441:113: ( ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) )
        // InternalBug304681TestLanguage.g:1442:8: ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 1);
        // InternalBug304681TestLanguage.g:1445:11: ({...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) )
        // InternalBug304681TestLanguage.g:1445:12: {...}? => (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred35_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1445:21: (otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';' )
        // InternalBug304681TestLanguage.g:1445:22: otherlv_8= 'longDescription' ( (lv_longDescription_9_0= RULE_STRING ) ) otherlv_10= ';'
        {
        otherlv_8=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:1449:11: ( (lv_longDescription_9_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:1450:12: (lv_longDescription_9_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:1450:12: (lv_longDescription_9_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:1451:13: lv_longDescription_9_0= RULE_STRING
        {
        lv_longDescription_9_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred35_InternalBug304681TestLanguage

    // $ANTLR start synpred36_InternalBug304681TestLanguage
    public final void synpred36_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        EObject lv_constraintDefinitions_11_0 = null;


        // InternalBug304681TestLanguage.g:1482:12: ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )
        // InternalBug304681TestLanguage.g:1482:12: {...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred36_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1482:21: ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) )
        // InternalBug304681TestLanguage.g:1482:22: (lv_constraintDefinitions_11_0= ruleConstraintDefinition )
        {
        // InternalBug304681TestLanguage.g:1482:22: (lv_constraintDefinitions_11_0= ruleConstraintDefinition )
        // InternalBug304681TestLanguage.g:1483:12: lv_constraintDefinitions_11_0= ruleConstraintDefinition
        {
        if ( state.backtracking==0 ) {

          												newCompositeNode(grammarAccess.getAttribute2Access().getConstraintDefinitionsConstraintDefinitionParserRuleCall_3_0_1_2_0());
          											
        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_constraintDefinitions_11_0=ruleConstraintDefinition();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred36_InternalBug304681TestLanguage

    // $ANTLR start synpred37_InternalBug304681TestLanguage
    public final void synpred37_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        EObject lv_constraintDefinitions_11_0 = null;


        // InternalBug304681TestLanguage.g:1477:6: ( ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) ) )
        // InternalBug304681TestLanguage.g:1477:6: ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) )
        {
        // InternalBug304681TestLanguage.g:1477:6: ({...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ ) )
        // InternalBug304681TestLanguage.g:1478:7: {...}? => ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 2) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred37_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 2)");
        }
        // InternalBug304681TestLanguage.g:1478:113: ( ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+ )
        // InternalBug304681TestLanguage.g:1479:8: ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+
        {
        getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 2);
        // InternalBug304681TestLanguage.g:1482:11: ({...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) ) )+
        int cnt34=0;
        loop34:
        do {
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==34) && ((true))) {
                alt34=1;
            }


            switch (alt34) {
        	case 1 :
        	    // InternalBug304681TestLanguage.g:1482:12: {...}? => ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) )
        	    {
        	    if ( !((true)) ) {
        	        if (state.backtracking>0) {state.failed=true; return ;}
        	        throw new FailedPredicateException(input, "synpred37_InternalBug304681TestLanguage", "true");
        	    }
        	    // InternalBug304681TestLanguage.g:1482:21: ( (lv_constraintDefinitions_11_0= ruleConstraintDefinition ) )
        	    // InternalBug304681TestLanguage.g:1482:22: (lv_constraintDefinitions_11_0= ruleConstraintDefinition )
        	    {
        	    // InternalBug304681TestLanguage.g:1482:22: (lv_constraintDefinitions_11_0= ruleConstraintDefinition )
        	    // InternalBug304681TestLanguage.g:1483:12: lv_constraintDefinitions_11_0= ruleConstraintDefinition
        	    {
        	    if ( state.backtracking==0 ) {

        	      												newCompositeNode(grammarAccess.getAttribute2Access().getConstraintDefinitionsConstraintDefinitionParserRuleCall_3_0_1_2_0());
        	      											
        	    }
        	    pushFollow(FollowSets000.FOLLOW_25);
        	    lv_constraintDefinitions_11_0=ruleConstraintDefinition();

        	    state._fsp--;
        	    if (state.failed) return ;

        	    }


        	    }


        	    }
        	    break;

        	default :
        	    if ( cnt34 >= 1 ) break loop34;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(34, input);
                    throw eee;
            }
            cnt34++;
        } while (true);


        }


        }


        }
    }
    // $ANTLR end synpred37_InternalBug304681TestLanguage

    // $ANTLR start synpred38_InternalBug304681TestLanguage
    public final void synpred38_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token lv_required_12_0=null;
        Token otherlv_13=null;

        // InternalBug304681TestLanguage.g:1505:6: ( ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1505:6: ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1505:6: ({...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1506:7: {...}? => ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 3) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred38_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 3)");
        }
        // InternalBug304681TestLanguage.g:1506:113: ( ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) ) )
        // InternalBug304681TestLanguage.g:1507:8: ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 3);
        // InternalBug304681TestLanguage.g:1510:11: ({...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' ) )
        // InternalBug304681TestLanguage.g:1510:12: {...}? => ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred38_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1510:21: ( ( (lv_required_12_0= 'required' ) ) otherlv_13= ';' )
        // InternalBug304681TestLanguage.g:1510:22: ( (lv_required_12_0= 'required' ) ) otherlv_13= ';'
        {
        // InternalBug304681TestLanguage.g:1510:22: ( (lv_required_12_0= 'required' ) )
        // InternalBug304681TestLanguage.g:1511:12: (lv_required_12_0= 'required' )
        {
        // InternalBug304681TestLanguage.g:1511:12: (lv_required_12_0= 'required' )
        // InternalBug304681TestLanguage.g:1512:13: lv_required_12_0= 'required'
        {
        lv_required_12_0=(Token)match(input,32,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred38_InternalBug304681TestLanguage

    // $ANTLR start synpred39_InternalBug304681TestLanguage
    public final void synpred39_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token lv_technical_14_0=null;
        Token otherlv_15=null;

        // InternalBug304681TestLanguage.g:1534:6: ( ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1534:6: ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1534:6: ({...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1535:7: {...}? => ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 4) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred39_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 4)");
        }
        // InternalBug304681TestLanguage.g:1535:113: ( ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) ) )
        // InternalBug304681TestLanguage.g:1536:8: ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getAttribute2Access().getUnorderedGroup_3_0_1(), 4);
        // InternalBug304681TestLanguage.g:1539:11: ({...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' ) )
        // InternalBug304681TestLanguage.g:1539:12: {...}? => ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred39_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1539:21: ( ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';' )
        // InternalBug304681TestLanguage.g:1539:22: ( (lv_technical_14_0= 'technical' ) ) otherlv_15= ';'
        {
        // InternalBug304681TestLanguage.g:1539:22: ( (lv_technical_14_0= 'technical' ) )
        // InternalBug304681TestLanguage.g:1540:12: (lv_technical_14_0= 'technical' )
        {
        // InternalBug304681TestLanguage.g:1540:12: (lv_technical_14_0= 'technical' )
        // InternalBug304681TestLanguage.g:1541:13: lv_technical_14_0= 'technical'
        {
        lv_technical_14_0=(Token)match(input,33,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred39_InternalBug304681TestLanguage

    // $ANTLR start synpred42_InternalBug304681TestLanguage
    public final void synpred42_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_6=null;
        Token lv_shortDescription_7_0=null;
        Token otherlv_8=null;

        // InternalBug304681TestLanguage.g:1678:6: ( ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1678:6: ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1678:6: ({...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1679:7: {...}? => ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 0) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred42_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 0)");
        }
        // InternalBug304681TestLanguage.g:1679:113: ( ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) ) )
        // InternalBug304681TestLanguage.g:1680:8: ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 0);
        // InternalBug304681TestLanguage.g:1683:11: ({...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' ) )
        // InternalBug304681TestLanguage.g:1683:12: {...}? => (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred42_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1683:21: (otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';' )
        // InternalBug304681TestLanguage.g:1683:22: otherlv_6= 'shortDescription' ( (lv_shortDescription_7_0= RULE_STRING ) ) otherlv_8= ';'
        {
        otherlv_6=(Token)match(input,20,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:1687:11: ( (lv_shortDescription_7_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:1688:12: (lv_shortDescription_7_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:1688:12: (lv_shortDescription_7_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:1689:13: lv_shortDescription_7_0= RULE_STRING
        {
        lv_shortDescription_7_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_8=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred42_InternalBug304681TestLanguage

    // $ANTLR start synpred43_InternalBug304681TestLanguage
    public final void synpred43_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_9=null;
        Token lv_longDescription_10_0=null;
        Token otherlv_11=null;

        // InternalBug304681TestLanguage.g:1715:6: ( ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1715:6: ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1715:6: ({...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1716:7: {...}? => ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 1) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred43_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 1)");
        }
        // InternalBug304681TestLanguage.g:1716:113: ( ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) ) )
        // InternalBug304681TestLanguage.g:1717:8: ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getReference2Access().getUnorderedGroup_4_0_1(), 1);
        // InternalBug304681TestLanguage.g:1720:11: ({...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' ) )
        // InternalBug304681TestLanguage.g:1720:12: {...}? => (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred43_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1720:21: (otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';' )
        // InternalBug304681TestLanguage.g:1720:22: otherlv_9= 'longDescription' ( (lv_longDescription_10_0= RULE_STRING ) ) otherlv_11= ';'
        {
        otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:1724:11: ( (lv_longDescription_10_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:1725:12: (lv_longDescription_10_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:1725:12: (lv_longDescription_10_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:1726:13: lv_longDescription_10_0= RULE_STRING
        {
        lv_longDescription_10_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred43_InternalBug304681TestLanguage

    // $ANTLR start synpred46_InternalBug304681TestLanguage
    public final void synpred46_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_4=null;
        Token lv_parameters_5_0=null;
        Token otherlv_6=null;

        // InternalBug304681TestLanguage.g:1835:4: ( ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1835:4: ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1835:4: ({...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1836:5: {...}? => ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 0) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred46_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 0)");
        }
        // InternalBug304681TestLanguage.g:1836:117: ( ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) ) )
        // InternalBug304681TestLanguage.g:1837:6: ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 0);
        // InternalBug304681TestLanguage.g:1840:9: ({...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' ) )
        // InternalBug304681TestLanguage.g:1840:10: {...}? => (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred46_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1840:19: (otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';' )
        // InternalBug304681TestLanguage.g:1840:20: otherlv_4= 'parameters' ( (lv_parameters_5_0= RULE_STRING ) )* otherlv_6= ';'
        {
        otherlv_4=(Token)match(input,35,FollowSets000.FOLLOW_21); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:1844:9: ( (lv_parameters_5_0= RULE_STRING ) )*
        loop38:
        do {
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==RULE_STRING) ) {
                alt38=1;
            }


            switch (alt38) {
        	case 1 :
        	    // InternalBug304681TestLanguage.g:1845:10: (lv_parameters_5_0= RULE_STRING )
        	    {
        	    // InternalBug304681TestLanguage.g:1845:10: (lv_parameters_5_0= RULE_STRING )
        	    // InternalBug304681TestLanguage.g:1846:11: lv_parameters_5_0= RULE_STRING
        	    {
        	    lv_parameters_5_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_21); if (state.failed) return ;

        	    }


        	    }
        	    break;

        	default :
        	    break loop38;
            }
        } while (true);

        otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred46_InternalBug304681TestLanguage

    // $ANTLR start synpred47_InternalBug304681TestLanguage
    public final void synpred47_InternalBug304681TestLanguage_fragment() throws RecognitionException {   
        Token otherlv_7=null;
        Token lv_message_8_0=null;
        Token otherlv_9=null;

        // InternalBug304681TestLanguage.g:1872:4: ( ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) ) )
        // InternalBug304681TestLanguage.g:1872:4: ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) )
        {
        // InternalBug304681TestLanguage.g:1872:4: ({...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) ) )
        // InternalBug304681TestLanguage.g:1873:5: {...}? => ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) )
        {
        if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 1) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred47_InternalBug304681TestLanguage", "getUnorderedGroupHelper().canSelect(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 1)");
        }
        // InternalBug304681TestLanguage.g:1873:117: ( ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) ) )
        // InternalBug304681TestLanguage.g:1874:6: ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) )
        {
        getUnorderedGroupHelper().select(grammarAccess.getConstraintDefinitionAccess().getUnorderedGroup_3(), 1);
        // InternalBug304681TestLanguage.g:1877:9: ({...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' ) )
        // InternalBug304681TestLanguage.g:1877:10: {...}? => (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' )
        {
        if ( !((true)) ) {
            if (state.backtracking>0) {state.failed=true; return ;}
            throw new FailedPredicateException(input, "synpred47_InternalBug304681TestLanguage", "true");
        }
        // InternalBug304681TestLanguage.g:1877:19: (otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';' )
        // InternalBug304681TestLanguage.g:1877:20: otherlv_7= 'message' ( (lv_message_8_0= RULE_STRING ) ) otherlv_9= ';'
        {
        otherlv_7=(Token)match(input,36,FollowSets000.FOLLOW_11); if (state.failed) return ;
        // InternalBug304681TestLanguage.g:1881:9: ( (lv_message_8_0= RULE_STRING ) )
        // InternalBug304681TestLanguage.g:1882:10: (lv_message_8_0= RULE_STRING )
        {
        // InternalBug304681TestLanguage.g:1882:10: (lv_message_8_0= RULE_STRING )
        // InternalBug304681TestLanguage.g:1883:11: lv_message_8_0= RULE_STRING
        {
        lv_message_8_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_5); if (state.failed) return ;

        }


        }

        otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }


        }
    }
    // $ANTLR end synpred47_InternalBug304681TestLanguage

    // Delegated rules

    public final boolean synpred20_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred36_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred36_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred39_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred39_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred28_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred28_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred30_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred46_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred46_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred43_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred43_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred38_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred38_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred32_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred32_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred29_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred29_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred37_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred37_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred42_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred42_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred31_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred31_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred47_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred47_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_InternalBug304681TestLanguage() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_InternalBug304681TestLanguage_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA9 dfa9 = new DFA9(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA8 dfa8 = new DFA8(this);
    static final String dfa_1s = "\13\uffff";
    static final String dfa_2s = "\1\24\12\uffff";
    static final String dfa_3s = "\1\36\12\uffff";
    static final String dfa_4s = "\1\uffff\1\11\1\1\1\2\1\3\1\4\2\5\1\6\1\7\1\10";
    static final String dfa_5s = "\1\0\12\uffff}>";
    static final String[] dfa_6s = {
            "\1\2\1\3\1\4\1\5\1\10\1\uffff\1\11\1\12\1\1\1\6\1\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "()* loopback of 325:6: ( ({...}? => ( ({...}? => (otherlv_8= 'shortDescription' ( (lv_shortDescription_9_0= RULE_STRING ) ) otherlv_10= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'longDescription' ( (lv_longDescription_12_0= RULE_STRING ) ) otherlv_13= ';' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'serialUID' ( (lv_serialVersionUID_15_0= RULE_INT ) ) otherlv_16= ';' ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_cloneable_17_0= 'cloneable' ) ) otherlv_18= ';' ) ) ) ) | ({...}? => ( ({...}? => ( (lv_features_19_0= ruleFeature ) ) )+ ) ) | ({...}? => ( ({...}? => (otherlv_20= 'before' ( (lv_features_21_0= ruleFeature ) )* otherlv_22= 'after' ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'optionalLoop' ( (lv_features_24_0= ruleFeature ) )* ) ) ) ) | ({...}? => ( ({...}? => (otherlv_25= 'mandatoryLoop' ( (lv_features_26_0= ruleFeature ) )+ ) ) ) ) )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA9_0 = input.LA(1);

                         
                        int index9_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA9_0==28) ) {s = 1;}

                        else if ( LA9_0 == 20 && getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 0) ) {s = 2;}

                        else if ( LA9_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 1) ) {s = 3;}

                        else if ( LA9_0 == 22 && getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 2) ) {s = 4;}

                        else if ( LA9_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 3) ) {s = 5;}

                        else if ( LA9_0 == 29 && getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 4) ) {s = 6;}

                        else if ( LA9_0 == 30 && getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 4) ) {s = 7;}

                        else if ( LA9_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 5) ) {s = 8;}

                        else if ( LA9_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 6) ) {s = 9;}

                        else if ( LA9_0 == 27 && getUnorderedGroupHelper().canSelect(grammarAccess.getObjectAccess().getUnorderedGroup_5(), 7) ) {s = 10;}

                         
                        input.seek(index9_0);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 9, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_7s = "\24\uffff";
    static final String dfa_8s = "\1\24\1\uffff\4\4\1\16\1\4\1\16\1\0\1\24\1\0\1\uffff\2\5\1\0\2\16\2\24";
    static final String dfa_9s = "\1\36\1\uffff\3\4\1\37\1\16\1\4\1\23\1\0\1\34\1\0\1\uffff\2\5\1\0\2\16\2\34";
    static final String dfa_10s = "\1\uffff\1\2\12\uffff\1\1\7\uffff";
    static final String dfa_11s = "\11\uffff\1\0\1\uffff\1\2\3\uffff\1\1\4\uffff}>";
    static final String[] dfa_12s = {
            "\5\1\1\uffff\3\1\1\2\1\3",
            "",
            "\1\4",
            "\1\5",
            "\1\6",
            "\1\10\32\uffff\1\7",
            "\1\11",
            "\1\10",
            "\1\13\4\uffff\1\12",
            "\1\uffff",
            "\1\15\1\16\6\uffff\1\17",
            "\1\uffff",
            "",
            "\1\20",
            "\1\21",
            "\1\uffff",
            "\1\22",
            "\1\23",
            "\1\15\1\16\6\uffff\1\17",
            "\1\15\1\16\6\uffff\1\17"
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "()* loopback of 541:9: ( (lv_features_24_0= ruleFeature ) )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_9 = input.LA(1);

                         
                        int index7_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_InternalBug304681TestLanguage()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index7_9);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA7_15 = input.LA(1);

                         
                        int index7_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_InternalBug304681TestLanguage()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index7_15);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA7_11 = input.LA(1);

                         
                        int index7_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_InternalBug304681TestLanguage()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index7_11);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "()+ loopback of 575:9: ( (lv_features_26_0= ruleFeature ) )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_9 = input.LA(1);

                         
                        int index8_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_InternalBug304681TestLanguage()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index8_9);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA8_15 = input.LA(1);

                         
                        int index8_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_InternalBug304681TestLanguage()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index8_15);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA8_11 = input.LA(1);

                         
                        int index8_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_InternalBug304681TestLanguage()) ) {s = 12;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index8_11);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000038002L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000000C0000L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x000000007DF00000L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000062000000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000060000000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000080000010L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000010300000L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000070F00000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000710300000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000001800000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000004020L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000001810000000L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000060000002L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000060F00002L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000400000002L});
    }


}