// Generated from /Users/schill/dev/xtext-master/git/xtext-eclipse/org.eclipse.xtext.xtext.ui.examples/projects/org.eclipse.xtext.example.domainmodel/../org.eclipse.xtext.example.domainmodel/src-gen/org/eclipse/xtext/example/domainmodel/parser/antlr/internal/Codebuff.g4 by ANTLR 4.5.3
package org.antlr.codebuff;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CodebuffParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, RULE_HEX=78, RULE_INT=79, RULE_DECIMAL=80, 
		RULE_ID=81, RULE_STRING=82, RULE_ML_COMMENT=83, RULE_SL_COMMENT=84, RULE_WS=85, 
		RULE_ANY_OTHER=86;
	public static final int
		RULE_ruleDomainModel = 0, RULE_ruleAbstractElement = 1, RULE_rulePackageDeclaration = 2, 
		RULE_ruleEntity = 3, RULE_ruleFeature = 4, RULE_ruleProperty = 5, RULE_ruleOperation = 6, 
		RULE_ruleXExpression = 7, RULE_ruleXAssignment = 8, RULE_ruleOpSingleAssign = 9, 
		RULE_ruleOpMultiAssign = 10, RULE_ruleXOrExpression = 11, RULE_ruleOpOr = 12, 
		RULE_ruleXAndExpression = 13, RULE_ruleOpAnd = 14, RULE_ruleXEqualityExpression = 15, 
		RULE_ruleOpEquality = 16, RULE_ruleXRelationalExpression = 17, RULE_ruleOpCompare = 18, 
		RULE_ruleXOtherOperatorExpression = 19, RULE_ruleOpOther = 20, RULE_ruleXAdditiveExpression = 21, 
		RULE_ruleOpAdd = 22, RULE_ruleXMultiplicativeExpression = 23, RULE_ruleOpMulti = 24, 
		RULE_ruleXUnaryOperation = 25, RULE_ruleOpUnary = 26, RULE_ruleXCastedExpression = 27, 
		RULE_ruleXPostfixOperation = 28, RULE_ruleOpPostfix = 29, RULE_ruleXMemberFeatureCall = 30, 
		RULE_ruleXPrimaryExpression = 31, RULE_ruleXLiteral = 32, RULE_ruleXCollectionLiteral = 33, 
		RULE_ruleXSetLiteral = 34, RULE_ruleXListLiteral = 35, RULE_ruleXClosure = 36, 
		RULE_ruleXExpressionInClosure = 37, RULE_ruleXShortClosure = 38, RULE_ruleXParenthesizedExpression = 39, 
		RULE_ruleXIfExpression = 40, RULE_ruleXSwitchExpression = 41, RULE_ruleXCasePart = 42, 
		RULE_ruleXForLoopExpression = 43, RULE_ruleXBasicForLoopExpression = 44, 
		RULE_ruleXWhileExpression = 45, RULE_ruleXDoWhileExpression = 46, RULE_ruleXBlockExpression = 47, 
		RULE_ruleXExpressionOrVarDeclaration = 48, RULE_ruleXVariableDeclaration = 49, 
		RULE_ruleJvmFormalParameter = 50, RULE_ruleFullJvmFormalParameter = 51, 
		RULE_ruleXFeatureCall = 52, RULE_ruleFeatureCallID = 53, RULE_ruleIdOrSuper = 54, 
		RULE_ruleXConstructorCall = 55, RULE_ruleXBooleanLiteral = 56, RULE_ruleXNullLiteral = 57, 
		RULE_ruleXNumberLiteral = 58, RULE_ruleXStringLiteral = 59, RULE_ruleXTypeLiteral = 60, 
		RULE_ruleXThrowExpression = 61, RULE_ruleXReturnExpression = 62, RULE_ruleXTryCatchFinallyExpression = 63, 
		RULE_ruleXSynchronizedExpression = 64, RULE_ruleXCatchClause = 65, RULE_ruleQualifiedName = 66, 
		RULE_ruleNumber = 67, RULE_ruleJvmTypeReference = 68, RULE_ruleArrayBrackets = 69, 
		RULE_ruleXFunctionTypeRef = 70, RULE_ruleJvmParameterizedTypeReference = 71, 
		RULE_ruleJvmArgumentTypeReference = 72, RULE_ruleJvmWildcardTypeReference = 73, 
		RULE_ruleJvmUpperBound = 74, RULE_ruleJvmUpperBoundAnded = 75, RULE_ruleJvmLowerBound = 76, 
		RULE_ruleJvmLowerBoundAnded = 77, RULE_ruleQualifiedNameWithWildcard = 78, 
		RULE_ruleValidID = 79, RULE_ruleXImportSection = 80, RULE_ruleXImportDeclaration = 81, 
		RULE_ruleQualifiedNameInStaticImport = 82;
	public static final String[] ruleNames = {
		"ruleDomainModel", "ruleAbstractElement", "rulePackageDeclaration", "ruleEntity", 
		"ruleFeature", "ruleProperty", "ruleOperation", "ruleXExpression", "ruleXAssignment", 
		"ruleOpSingleAssign", "ruleOpMultiAssign", "ruleXOrExpression", "ruleOpOr", 
		"ruleXAndExpression", "ruleOpAnd", "ruleXEqualityExpression", "ruleOpEquality", 
		"ruleXRelationalExpression", "ruleOpCompare", "ruleXOtherOperatorExpression", 
		"ruleOpOther", "ruleXAdditiveExpression", "ruleOpAdd", "ruleXMultiplicativeExpression", 
		"ruleOpMulti", "ruleXUnaryOperation", "ruleOpUnary", "ruleXCastedExpression", 
		"ruleXPostfixOperation", "ruleOpPostfix", "ruleXMemberFeatureCall", "ruleXPrimaryExpression", 
		"ruleXLiteral", "ruleXCollectionLiteral", "ruleXSetLiteral", "ruleXListLiteral", 
		"ruleXClosure", "ruleXExpressionInClosure", "ruleXShortClosure", "ruleXParenthesizedExpression", 
		"ruleXIfExpression", "ruleXSwitchExpression", "ruleXCasePart", "ruleXForLoopExpression", 
		"ruleXBasicForLoopExpression", "ruleXWhileExpression", "ruleXDoWhileExpression", 
		"ruleXBlockExpression", "ruleXExpressionOrVarDeclaration", "ruleXVariableDeclaration", 
		"ruleJvmFormalParameter", "ruleFullJvmFormalParameter", "ruleXFeatureCall", 
		"ruleFeatureCallID", "ruleIdOrSuper", "ruleXConstructorCall", "ruleXBooleanLiteral", 
		"ruleXNullLiteral", "ruleXNumberLiteral", "ruleXStringLiteral", "ruleXTypeLiteral", 
		"ruleXThrowExpression", "ruleXReturnExpression", "ruleXTryCatchFinallyExpression", 
		"ruleXSynchronizedExpression", "ruleXCatchClause", "ruleQualifiedName", 
		"ruleNumber", "ruleJvmTypeReference", "ruleArrayBrackets", "ruleXFunctionTypeRef", 
		"ruleJvmParameterizedTypeReference", "ruleJvmArgumentTypeReference", "ruleJvmWildcardTypeReference", 
		"ruleJvmUpperBound", "ruleJvmUpperBoundAnded", "ruleJvmLowerBound", "ruleJvmLowerBoundAnded", 
		"ruleQualifiedNameWithWildcard", "ruleValidID", "ruleXImportSection", 
		"ruleXImportDeclaration", "ruleQualifiedNameInStaticImport"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'package'", "'{'", "'}'", "'entity'", "'extends'", "':'", "'op'", 
		"'('", "','", "')'", "'='", "'+='", "'-='", "'*='", "'/='", "'%='", "'<'", 
		"'>'", "'>='", "'||'", "'&&'", "'=='", "'!='", "'==='", "'!=='", "'instanceof'", 
		"'->'", "'..<'", "'..'", "'=>'", "'<>'", "'?:'", "'+'", "'-'", "'*'", 
		"'**'", "'/'", "'%'", "'!'", "'as'", "'++'", "'--'", "'.'", "'::'", "'?.'", 
		"'#'", "'['", "']'", "'|'", "';'", "'if'", "'else'", "'switch'", "'default'", 
		"'case'", "'for'", "'while'", "'do'", "'var'", "'val'", "'static'", "'import'", 
		"'extension'", "'super'", "'new'", "'false'", "'true'", "'null'", "'typeof'", 
		"'throw'", "'return'", "'try'", "'finally'", "'synchronized'", "'catch'", 
		"'?'", "'&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "RULE_HEX", "RULE_INT", "RULE_DECIMAL", 
		"RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", 
		"RULE_ANY_OTHER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Codebuff.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CodebuffParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RuleDomainModelContext extends ParserRuleContext {
		public RuleXImportSectionContext ruleXImportSection() {
			return getRuleContext(RuleXImportSectionContext.class,0);
		}
		public List<RuleAbstractElementContext> ruleAbstractElement() {
			return getRuleContexts(RuleAbstractElementContext.class);
		}
		public RuleAbstractElementContext ruleAbstractElement(int i) {
			return getRuleContext(RuleAbstractElementContext.class,i);
		}
		public RuleDomainModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleDomainModel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleDomainModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleDomainModel(this);
		}
	}

	public final RuleDomainModelContext ruleDomainModel() throws RecognitionException {
		RuleDomainModelContext _localctx = new RuleDomainModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_ruleDomainModel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_la = _input.LA(1);
			if (_la==T__61) {
				{
				setState(166);
				ruleXImportSection();
				}
			}

			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__3) {
				{
				{
				setState(169);
				ruleAbstractElement();
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleAbstractElementContext extends ParserRuleContext {
		public RulePackageDeclarationContext rulePackageDeclaration() {
			return getRuleContext(RulePackageDeclarationContext.class,0);
		}
		public RuleEntityContext ruleEntity() {
			return getRuleContext(RuleEntityContext.class,0);
		}
		public RuleAbstractElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAbstractElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleAbstractElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleAbstractElement(this);
		}
	}

	public final RuleAbstractElementContext ruleAbstractElement() throws RecognitionException {
		RuleAbstractElementContext _localctx = new RuleAbstractElementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ruleAbstractElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(175);
				rulePackageDeclaration();
				}
				break;
			case T__3:
				{
				setState(176);
				ruleEntity();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulePackageDeclarationContext extends ParserRuleContext {
		public RuleQualifiedNameContext ruleQualifiedName() {
			return getRuleContext(RuleQualifiedNameContext.class,0);
		}
		public List<RuleAbstractElementContext> ruleAbstractElement() {
			return getRuleContexts(RuleAbstractElementContext.class);
		}
		public RuleAbstractElementContext ruleAbstractElement(int i) {
			return getRuleContext(RuleAbstractElementContext.class,i);
		}
		public RulePackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rulePackageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRulePackageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRulePackageDeclaration(this);
		}
	}

	public final RulePackageDeclarationContext rulePackageDeclaration() throws RecognitionException {
		RulePackageDeclarationContext _localctx = new RulePackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_rulePackageDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(T__0);
			setState(180);
			ruleQualifiedName();
			setState(181);
			match(T__1);
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__3) {
				{
				{
				setState(182);
				ruleAbstractElement();
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(188);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleEntityContext extends ParserRuleContext {
		public RuleValidIDContext ruleValidID() {
			return getRuleContext(RuleValidIDContext.class,0);
		}
		public RuleJvmParameterizedTypeReferenceContext ruleJvmParameterizedTypeReference() {
			return getRuleContext(RuleJvmParameterizedTypeReferenceContext.class,0);
		}
		public List<RuleFeatureContext> ruleFeature() {
			return getRuleContexts(RuleFeatureContext.class);
		}
		public RuleFeatureContext ruleFeature(int i) {
			return getRuleContext(RuleFeatureContext.class,i);
		}
		public RuleEntityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEntity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleEntity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleEntity(this);
		}
	}

	public final RuleEntityContext ruleEntity() throws RecognitionException {
		RuleEntityContext _localctx = new RuleEntityContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ruleEntity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(T__3);
			setState(191);
			ruleValidID();
			setState(194);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(192);
				match(T__4);
				setState(193);
				ruleJvmParameterizedTypeReference();
				}
			}

			setState(196);
			match(T__1);
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6 || _la==RULE_ID) {
				{
				{
				setState(197);
				ruleFeature();
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(203);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleFeatureContext extends ParserRuleContext {
		public RulePropertyContext ruleProperty() {
			return getRuleContext(RulePropertyContext.class,0);
		}
		public RuleOperationContext ruleOperation() {
			return getRuleContext(RuleOperationContext.class,0);
		}
		public RuleFeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleFeature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleFeature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleFeature(this);
		}
	}

	public final RuleFeatureContext ruleFeature() throws RecognitionException {
		RuleFeatureContext _localctx = new RuleFeatureContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ruleFeature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			switch (_input.LA(1)) {
			case RULE_ID:
				{
				setState(205);
				ruleProperty();
				}
				break;
			case T__6:
				{
				setState(206);
				ruleOperation();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulePropertyContext extends ParserRuleContext {
		public RuleValidIDContext ruleValidID() {
			return getRuleContext(RuleValidIDContext.class,0);
		}
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RulePropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleProperty(this);
		}
	}

	public final RulePropertyContext ruleProperty() throws RecognitionException {
		RulePropertyContext _localctx = new RulePropertyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ruleProperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			ruleValidID();
			setState(210);
			match(T__5);
			setState(211);
			ruleJvmTypeReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOperationContext extends ParserRuleContext {
		public RuleValidIDContext ruleValidID() {
			return getRuleContext(RuleValidIDContext.class,0);
		}
		public RuleXBlockExpressionContext ruleXBlockExpression() {
			return getRuleContext(RuleXBlockExpressionContext.class,0);
		}
		public List<RuleFullJvmFormalParameterContext> ruleFullJvmFormalParameter() {
			return getRuleContexts(RuleFullJvmFormalParameterContext.class);
		}
		public RuleFullJvmFormalParameterContext ruleFullJvmFormalParameter(int i) {
			return getRuleContext(RuleFullJvmFormalParameterContext.class,i);
		}
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOperation(this);
		}
	}

	public final RuleOperationContext ruleOperation() throws RecognitionException {
		RuleOperationContext _localctx = new RuleOperationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ruleOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(T__6);
			setState(214);
			ruleValidID();
			setState(215);
			match(T__7);
			setState(224);
			_la = _input.LA(1);
			if (_la==T__7 || _la==T__29 || _la==RULE_ID) {
				{
				setState(216);
				ruleFullJvmFormalParameter();
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(217);
					match(T__8);
					setState(218);
					ruleFullJvmFormalParameter();
					}
					}
					setState(223);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(226);
			match(T__9);
			setState(229);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(227);
				match(T__5);
				setState(228);
				ruleJvmTypeReference();
				}
			}

			setState(231);
			ruleXBlockExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXExpressionContext extends ParserRuleContext {
		public RuleXAssignmentContext ruleXAssignment() {
			return getRuleContext(RuleXAssignmentContext.class,0);
		}
		public RuleXExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXExpression(this);
		}
	}

	public final RuleXExpressionContext ruleXExpression() throws RecognitionException {
		RuleXExpressionContext _localctx = new RuleXExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ruleXExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			ruleXAssignment();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXAssignmentContext extends ParserRuleContext {
		public RuleFeatureCallIDContext ruleFeatureCallID() {
			return getRuleContext(RuleFeatureCallIDContext.class,0);
		}
		public RuleOpSingleAssignContext ruleOpSingleAssign() {
			return getRuleContext(RuleOpSingleAssignContext.class,0);
		}
		public RuleXAssignmentContext ruleXAssignment() {
			return getRuleContext(RuleXAssignmentContext.class,0);
		}
		public RuleXOrExpressionContext ruleXOrExpression() {
			return getRuleContext(RuleXOrExpressionContext.class,0);
		}
		public RuleOpMultiAssignContext ruleOpMultiAssign() {
			return getRuleContext(RuleOpMultiAssignContext.class,0);
		}
		public RuleXAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXAssignment(this);
		}
	}

	public final RuleXAssignmentContext ruleXAssignment() throws RecognitionException {
		RuleXAssignmentContext _localctx = new RuleXAssignmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ruleXAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(235);
				ruleFeatureCallID();
				setState(236);
				ruleOpSingleAssign();
				setState(237);
				ruleXAssignment();
				}
				break;
			case 2:
				{
				setState(239);
				ruleXOrExpression();
				setState(243);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					{
					setState(240);
					ruleOpMultiAssign();
					}
					setState(241);
					ruleXAssignment();
					}
					break;
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpSingleAssignContext extends ParserRuleContext {
		public RuleOpSingleAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpSingleAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpSingleAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpSingleAssign(this);
		}
	}

	public final RuleOpSingleAssignContext ruleOpSingleAssign() throws RecognitionException {
		RuleOpSingleAssignContext _localctx = new RuleOpSingleAssignContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ruleOpSingleAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpMultiAssignContext extends ParserRuleContext {
		public RuleOpMultiAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpMultiAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpMultiAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpMultiAssign(this);
		}
	}

	public final RuleOpMultiAssignContext ruleOpMultiAssign() throws RecognitionException {
		RuleOpMultiAssignContext _localctx = new RuleOpMultiAssignContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ruleOpMultiAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			switch (_input.LA(1)) {
			case T__11:
				{
				setState(249);
				match(T__11);
				}
				break;
			case T__12:
				{
				setState(250);
				match(T__12);
				}
				break;
			case T__13:
				{
				setState(251);
				match(T__13);
				}
				break;
			case T__14:
				{
				setState(252);
				match(T__14);
				}
				break;
			case T__15:
				{
				setState(253);
				match(T__15);
				}
				break;
			case T__16:
				{
				setState(254);
				match(T__16);
				setState(255);
				match(T__16);
				setState(256);
				match(T__10);
				}
				break;
			case T__17:
				{
				setState(257);
				match(T__17);
				setState(259);
				_la = _input.LA(1);
				if (_la==T__17) {
					{
					setState(258);
					match(T__17);
					}
				}

				setState(261);
				match(T__18);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXOrExpressionContext extends ParserRuleContext {
		public List<RuleXAndExpressionContext> ruleXAndExpression() {
			return getRuleContexts(RuleXAndExpressionContext.class);
		}
		public RuleXAndExpressionContext ruleXAndExpression(int i) {
			return getRuleContext(RuleXAndExpressionContext.class,i);
		}
		public List<RuleOpOrContext> ruleOpOr() {
			return getRuleContexts(RuleOpOrContext.class);
		}
		public RuleOpOrContext ruleOpOr(int i) {
			return getRuleContext(RuleOpOrContext.class,i);
		}
		public RuleXOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXOrExpression(this);
		}
	}

	public final RuleXOrExpressionContext ruleXOrExpression() throws RecognitionException {
		RuleXOrExpressionContext _localctx = new RuleXOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_ruleXOrExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			ruleXAndExpression();
			setState(270);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(265);
					ruleOpOr();
					}
					setState(266);
					ruleXAndExpression();
					}
					} 
				}
				setState(272);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpOrContext extends ParserRuleContext {
		public RuleOpOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpOr(this);
		}
	}

	public final RuleOpOrContext ruleOpOr() throws RecognitionException {
		RuleOpOrContext _localctx = new RuleOpOrContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ruleOpOr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXAndExpressionContext extends ParserRuleContext {
		public List<RuleXEqualityExpressionContext> ruleXEqualityExpression() {
			return getRuleContexts(RuleXEqualityExpressionContext.class);
		}
		public RuleXEqualityExpressionContext ruleXEqualityExpression(int i) {
			return getRuleContext(RuleXEqualityExpressionContext.class,i);
		}
		public List<RuleOpAndContext> ruleOpAnd() {
			return getRuleContexts(RuleOpAndContext.class);
		}
		public RuleOpAndContext ruleOpAnd(int i) {
			return getRuleContext(RuleOpAndContext.class,i);
		}
		public RuleXAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXAndExpression(this);
		}
	}

	public final RuleXAndExpressionContext ruleXAndExpression() throws RecognitionException {
		RuleXAndExpressionContext _localctx = new RuleXAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ruleXAndExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			ruleXEqualityExpression();
			setState(281);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(276);
					ruleOpAnd();
					}
					setState(277);
					ruleXEqualityExpression();
					}
					} 
				}
				setState(283);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpAndContext extends ParserRuleContext {
		public RuleOpAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpAnd(this);
		}
	}

	public final RuleOpAndContext ruleOpAnd() throws RecognitionException {
		RuleOpAndContext _localctx = new RuleOpAndContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ruleOpAnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(T__20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXEqualityExpressionContext extends ParserRuleContext {
		public List<RuleXRelationalExpressionContext> ruleXRelationalExpression() {
			return getRuleContexts(RuleXRelationalExpressionContext.class);
		}
		public RuleXRelationalExpressionContext ruleXRelationalExpression(int i) {
			return getRuleContext(RuleXRelationalExpressionContext.class,i);
		}
		public List<RuleOpEqualityContext> ruleOpEquality() {
			return getRuleContexts(RuleOpEqualityContext.class);
		}
		public RuleOpEqualityContext ruleOpEquality(int i) {
			return getRuleContext(RuleOpEqualityContext.class,i);
		}
		public RuleXEqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXEqualityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXEqualityExpression(this);
		}
	}

	public final RuleXEqualityExpressionContext ruleXEqualityExpression() throws RecognitionException {
		RuleXEqualityExpressionContext _localctx = new RuleXEqualityExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ruleXEqualityExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			ruleXRelationalExpression();
			setState(292);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(287);
					ruleOpEquality();
					}
					setState(288);
					ruleXRelationalExpression();
					}
					} 
				}
				setState(294);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpEqualityContext extends ParserRuleContext {
		public RuleOpEqualityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpEquality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpEquality(this);
		}
	}

	public final RuleOpEqualityContext ruleOpEquality() throws RecognitionException {
		RuleOpEqualityContext _localctx = new RuleOpEqualityContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ruleOpEquality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXRelationalExpressionContext extends ParserRuleContext {
		public List<RuleXOtherOperatorExpressionContext> ruleXOtherOperatorExpression() {
			return getRuleContexts(RuleXOtherOperatorExpressionContext.class);
		}
		public RuleXOtherOperatorExpressionContext ruleXOtherOperatorExpression(int i) {
			return getRuleContext(RuleXOtherOperatorExpressionContext.class,i);
		}
		public List<RuleJvmTypeReferenceContext> ruleJvmTypeReference() {
			return getRuleContexts(RuleJvmTypeReferenceContext.class);
		}
		public RuleJvmTypeReferenceContext ruleJvmTypeReference(int i) {
			return getRuleContext(RuleJvmTypeReferenceContext.class,i);
		}
		public List<RuleOpCompareContext> ruleOpCompare() {
			return getRuleContexts(RuleOpCompareContext.class);
		}
		public RuleOpCompareContext ruleOpCompare(int i) {
			return getRuleContext(RuleOpCompareContext.class,i);
		}
		public RuleXRelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXRelationalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXRelationalExpression(this);
		}
	}

	public final RuleXRelationalExpressionContext ruleXRelationalExpression() throws RecognitionException {
		RuleXRelationalExpressionContext _localctx = new RuleXRelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_ruleXRelationalExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			ruleXOtherOperatorExpression();
			setState(305);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(303);
					switch (_input.LA(1)) {
					case T__25:
						{
						{
						setState(298);
						match(T__25);
						}
						setState(299);
						ruleJvmTypeReference();
						}
						break;
					case T__16:
					case T__17:
					case T__18:
						{
						{
						setState(300);
						ruleOpCompare();
						}
						setState(301);
						ruleXOtherOperatorExpression();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(307);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpCompareContext extends ParserRuleContext {
		public RuleOpCompareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpCompare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpCompare(this);
		}
	}

	public final RuleOpCompareContext ruleOpCompare() throws RecognitionException {
		RuleOpCompareContext _localctx = new RuleOpCompareContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ruleOpCompare);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(308);
				match(T__18);
				}
				break;
			case 2:
				{
				setState(309);
				match(T__16);
				setState(310);
				match(T__10);
				}
				break;
			case 3:
				{
				setState(311);
				match(T__17);
				}
				break;
			case 4:
				{
				setState(312);
				match(T__16);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXOtherOperatorExpressionContext extends ParserRuleContext {
		public List<RuleXAdditiveExpressionContext> ruleXAdditiveExpression() {
			return getRuleContexts(RuleXAdditiveExpressionContext.class);
		}
		public RuleXAdditiveExpressionContext ruleXAdditiveExpression(int i) {
			return getRuleContext(RuleXAdditiveExpressionContext.class,i);
		}
		public List<RuleOpOtherContext> ruleOpOther() {
			return getRuleContexts(RuleOpOtherContext.class);
		}
		public RuleOpOtherContext ruleOpOther(int i) {
			return getRuleContext(RuleOpOtherContext.class,i);
		}
		public RuleXOtherOperatorExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXOtherOperatorExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXOtherOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXOtherOperatorExpression(this);
		}
	}

	public final RuleXOtherOperatorExpressionContext ruleXOtherOperatorExpression() throws RecognitionException {
		RuleXOtherOperatorExpressionContext _localctx = new RuleXOtherOperatorExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_ruleXOtherOperatorExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			ruleXAdditiveExpression();
			setState(321);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(316);
					ruleOpOther();
					}
					setState(317);
					ruleXAdditiveExpression();
					}
					} 
				}
				setState(323);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpOtherContext extends ParserRuleContext {
		public RuleOpOtherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpOther; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpOther(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpOther(this);
		}
	}

	public final RuleOpOtherContext ruleOpOther() throws RecognitionException {
		RuleOpOtherContext _localctx = new RuleOpOtherContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_ruleOpOther);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(324);
				match(T__26);
				}
				break;
			case 2:
				{
				setState(325);
				match(T__27);
				}
				break;
			case 3:
				{
				setState(326);
				match(T__17);
				setState(327);
				match(T__28);
				}
				break;
			case 4:
				{
				setState(328);
				match(T__28);
				}
				break;
			case 5:
				{
				setState(329);
				match(T__29);
				}
				break;
			case 6:
				{
				setState(330);
				match(T__17);
				setState(334);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					{
					setState(331);
					match(T__17);
					setState(332);
					match(T__17);
					}
					}
					break;
				case 2:
					{
					setState(333);
					match(T__17);
					}
					break;
				}
				}
				break;
			case 7:
				{
				setState(336);
				match(T__16);
				setState(341);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					{
					setState(337);
					match(T__16);
					setState(338);
					match(T__16);
					}
					}
					break;
				case 2:
					{
					setState(339);
					match(T__16);
					}
					break;
				case 3:
					{
					setState(340);
					match(T__29);
					}
					break;
				}
				}
				break;
			case 8:
				{
				setState(343);
				match(T__30);
				}
				break;
			case 9:
				{
				setState(344);
				match(T__31);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXAdditiveExpressionContext extends ParserRuleContext {
		public List<RuleXMultiplicativeExpressionContext> ruleXMultiplicativeExpression() {
			return getRuleContexts(RuleXMultiplicativeExpressionContext.class);
		}
		public RuleXMultiplicativeExpressionContext ruleXMultiplicativeExpression(int i) {
			return getRuleContext(RuleXMultiplicativeExpressionContext.class,i);
		}
		public List<RuleOpAddContext> ruleOpAdd() {
			return getRuleContexts(RuleOpAddContext.class);
		}
		public RuleOpAddContext ruleOpAdd(int i) {
			return getRuleContext(RuleOpAddContext.class,i);
		}
		public RuleXAdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXAdditiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXAdditiveExpression(this);
		}
	}

	public final RuleXAdditiveExpressionContext ruleXAdditiveExpression() throws RecognitionException {
		RuleXAdditiveExpressionContext _localctx = new RuleXAdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_ruleXAdditiveExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(347);
			ruleXMultiplicativeExpression();
			setState(353);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(348);
					ruleOpAdd();
					}
					setState(349);
					ruleXMultiplicativeExpression();
					}
					} 
				}
				setState(355);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpAddContext extends ParserRuleContext {
		public RuleOpAddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpAdd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpAdd(this);
		}
	}

	public final RuleOpAddContext ruleOpAdd() throws RecognitionException {
		RuleOpAddContext _localctx = new RuleOpAddContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_ruleOpAdd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			_la = _input.LA(1);
			if ( !(_la==T__32 || _la==T__33) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXMultiplicativeExpressionContext extends ParserRuleContext {
		public List<RuleXUnaryOperationContext> ruleXUnaryOperation() {
			return getRuleContexts(RuleXUnaryOperationContext.class);
		}
		public RuleXUnaryOperationContext ruleXUnaryOperation(int i) {
			return getRuleContext(RuleXUnaryOperationContext.class,i);
		}
		public List<RuleOpMultiContext> ruleOpMulti() {
			return getRuleContexts(RuleOpMultiContext.class);
		}
		public RuleOpMultiContext ruleOpMulti(int i) {
			return getRuleContext(RuleOpMultiContext.class,i);
		}
		public RuleXMultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXMultiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXMultiplicativeExpression(this);
		}
	}

	public final RuleXMultiplicativeExpressionContext ruleXMultiplicativeExpression() throws RecognitionException {
		RuleXMultiplicativeExpressionContext _localctx = new RuleXMultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ruleXMultiplicativeExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			ruleXUnaryOperation();
			setState(364);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(359);
					ruleOpMulti();
					}
					setState(360);
					ruleXUnaryOperation();
					}
					} 
				}
				setState(366);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpMultiContext extends ParserRuleContext {
		public RuleOpMultiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpMulti; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpMulti(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpMulti(this);
		}
	}

	public final RuleOpMultiContext ruleOpMulti() throws RecognitionException {
		RuleOpMultiContext _localctx = new RuleOpMultiContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_ruleOpMulti);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXUnaryOperationContext extends ParserRuleContext {
		public RuleOpUnaryContext ruleOpUnary() {
			return getRuleContext(RuleOpUnaryContext.class,0);
		}
		public RuleXUnaryOperationContext ruleXUnaryOperation() {
			return getRuleContext(RuleXUnaryOperationContext.class,0);
		}
		public RuleXCastedExpressionContext ruleXCastedExpression() {
			return getRuleContext(RuleXCastedExpressionContext.class,0);
		}
		public RuleXUnaryOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXUnaryOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXUnaryOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXUnaryOperation(this);
		}
	}

	public final RuleXUnaryOperationContext ruleXUnaryOperation() throws RecognitionException {
		RuleXUnaryOperationContext _localctx = new RuleXUnaryOperationContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_ruleXUnaryOperation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			switch (_input.LA(1)) {
			case T__32:
			case T__33:
			case T__38:
				{
				setState(369);
				ruleOpUnary();
				setState(370);
				ruleXUnaryOperation();
				}
				break;
			case T__1:
			case T__4:
			case T__7:
			case T__16:
			case T__45:
			case T__46:
			case T__50:
			case T__52:
			case T__55:
			case T__56:
			case T__57:
			case T__60:
			case T__61:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__73:
			case RULE_HEX:
			case RULE_INT:
			case RULE_DECIMAL:
			case RULE_ID:
			case RULE_STRING:
				{
				setState(372);
				ruleXCastedExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpUnaryContext extends ParserRuleContext {
		public RuleOpUnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpUnary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpUnary(this);
		}
	}

	public final RuleOpUnaryContext ruleOpUnary() throws RecognitionException {
		RuleOpUnaryContext _localctx = new RuleOpUnaryContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_ruleOpUnary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__32) | (1L << T__33) | (1L << T__38))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXCastedExpressionContext extends ParserRuleContext {
		public RuleXPostfixOperationContext ruleXPostfixOperation() {
			return getRuleContext(RuleXPostfixOperationContext.class,0);
		}
		public List<RuleJvmTypeReferenceContext> ruleJvmTypeReference() {
			return getRuleContexts(RuleJvmTypeReferenceContext.class);
		}
		public RuleJvmTypeReferenceContext ruleJvmTypeReference(int i) {
			return getRuleContext(RuleJvmTypeReferenceContext.class,i);
		}
		public RuleXCastedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXCastedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXCastedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXCastedExpression(this);
		}
	}

	public final RuleXCastedExpressionContext ruleXCastedExpression() throws RecognitionException {
		RuleXCastedExpressionContext _localctx = new RuleXCastedExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_ruleXCastedExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			ruleXPostfixOperation();
			setState(382);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(378);
					match(T__39);
					}
					setState(379);
					ruleJvmTypeReference();
					}
					} 
				}
				setState(384);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXPostfixOperationContext extends ParserRuleContext {
		public RuleXMemberFeatureCallContext ruleXMemberFeatureCall() {
			return getRuleContext(RuleXMemberFeatureCallContext.class,0);
		}
		public RuleOpPostfixContext ruleOpPostfix() {
			return getRuleContext(RuleOpPostfixContext.class,0);
		}
		public RuleXPostfixOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXPostfixOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXPostfixOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXPostfixOperation(this);
		}
	}

	public final RuleXPostfixOperationContext ruleXPostfixOperation() throws RecognitionException {
		RuleXPostfixOperationContext _localctx = new RuleXPostfixOperationContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_ruleXPostfixOperation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			ruleXMemberFeatureCall();
			setState(387);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(386);
				ruleOpPostfix();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleOpPostfixContext extends ParserRuleContext {
		public RuleOpPostfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOpPostfix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleOpPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleOpPostfix(this);
		}
	}

	public final RuleOpPostfixContext ruleOpPostfix() throws RecognitionException {
		RuleOpPostfixContext _localctx = new RuleOpPostfixContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_ruleOpPostfix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
			_la = _input.LA(1);
			if ( !(_la==T__40 || _la==T__41) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXMemberFeatureCallContext extends ParserRuleContext {
		public RuleXPrimaryExpressionContext ruleXPrimaryExpression() {
			return getRuleContext(RuleXPrimaryExpressionContext.class,0);
		}
		public List<RuleXAssignmentContext> ruleXAssignment() {
			return getRuleContexts(RuleXAssignmentContext.class);
		}
		public RuleXAssignmentContext ruleXAssignment(int i) {
			return getRuleContext(RuleXAssignmentContext.class,i);
		}
		public List<RuleIdOrSuperContext> ruleIdOrSuper() {
			return getRuleContexts(RuleIdOrSuperContext.class);
		}
		public RuleIdOrSuperContext ruleIdOrSuper(int i) {
			return getRuleContext(RuleIdOrSuperContext.class,i);
		}
		public List<RuleFeatureCallIDContext> ruleFeatureCallID() {
			return getRuleContexts(RuleFeatureCallIDContext.class);
		}
		public RuleFeatureCallIDContext ruleFeatureCallID(int i) {
			return getRuleContext(RuleFeatureCallIDContext.class,i);
		}
		public List<RuleOpSingleAssignContext> ruleOpSingleAssign() {
			return getRuleContexts(RuleOpSingleAssignContext.class);
		}
		public RuleOpSingleAssignContext ruleOpSingleAssign(int i) {
			return getRuleContext(RuleOpSingleAssignContext.class,i);
		}
		public List<RuleJvmArgumentTypeReferenceContext> ruleJvmArgumentTypeReference() {
			return getRuleContexts(RuleJvmArgumentTypeReferenceContext.class);
		}
		public RuleJvmArgumentTypeReferenceContext ruleJvmArgumentTypeReference(int i) {
			return getRuleContext(RuleJvmArgumentTypeReferenceContext.class,i);
		}
		public List<RuleXClosureContext> ruleXClosure() {
			return getRuleContexts(RuleXClosureContext.class);
		}
		public RuleXClosureContext ruleXClosure(int i) {
			return getRuleContext(RuleXClosureContext.class,i);
		}
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public List<RuleXShortClosureContext> ruleXShortClosure() {
			return getRuleContexts(RuleXShortClosureContext.class);
		}
		public RuleXShortClosureContext ruleXShortClosure(int i) {
			return getRuleContext(RuleXShortClosureContext.class,i);
		}
		public RuleXMemberFeatureCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXMemberFeatureCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXMemberFeatureCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXMemberFeatureCall(this);
		}
	}

	public final RuleXMemberFeatureCallContext ruleXMemberFeatureCall() throws RecognitionException {
		RuleXMemberFeatureCallContext _localctx = new RuleXMemberFeatureCallContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_ruleXMemberFeatureCall);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			ruleXPrimaryExpression();
			setState(433);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(431);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
					case 1:
						{
						{
						setState(392);
						_la = _input.LA(1);
						if ( !(_la==T__42 || _la==T__43) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(393);
						ruleFeatureCallID();
						setState(394);
						ruleOpSingleAssign();
						}
						setState(396);
						ruleXAssignment();
						}
						break;
					case 2:
						{
						{
						setState(398);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__42) | (1L << T__43) | (1L << T__44))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						setState(410);
						_la = _input.LA(1);
						if (_la==T__16) {
							{
							setState(399);
							match(T__16);
							setState(400);
							ruleJvmArgumentTypeReference();
							setState(405);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__8) {
								{
								{
								setState(401);
								match(T__8);
								setState(402);
								ruleJvmArgumentTypeReference();
								}
								}
								setState(407);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(408);
							match(T__17);
							}
						}

						setState(412);
						ruleIdOrSuper();
						setState(426);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
						case 1:
							{
							{
							setState(413);
							match(T__7);
							}
							setState(423);
							_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
							case 1:
								{
								{
								setState(414);
								ruleXShortClosure();
								}
								}
								break;
							case 2:
								{
								setState(415);
								ruleXExpression();
								setState(420);
								_errHandler.sync(this);
								_la = _input.LA(1);
								while (_la==T__8) {
									{
									{
									setState(416);
									match(T__8);
									setState(417);
									ruleXExpression();
									}
									}
									setState(422);
									_errHandler.sync(this);
									_la = _input.LA(1);
								}
								}
								break;
							}
							setState(425);
							match(T__9);
							}
							break;
						}
						setState(429);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
						case 1:
							{
							setState(428);
							ruleXClosure();
							}
							break;
						}
						}
						break;
					}
					} 
				}
				setState(435);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXPrimaryExpressionContext extends ParserRuleContext {
		public RuleXConstructorCallContext ruleXConstructorCall() {
			return getRuleContext(RuleXConstructorCallContext.class,0);
		}
		public RuleXBlockExpressionContext ruleXBlockExpression() {
			return getRuleContext(RuleXBlockExpressionContext.class,0);
		}
		public RuleXSwitchExpressionContext ruleXSwitchExpression() {
			return getRuleContext(RuleXSwitchExpressionContext.class,0);
		}
		public RuleXFeatureCallContext ruleXFeatureCall() {
			return getRuleContext(RuleXFeatureCallContext.class,0);
		}
		public RuleXLiteralContext ruleXLiteral() {
			return getRuleContext(RuleXLiteralContext.class,0);
		}
		public RuleXIfExpressionContext ruleXIfExpression() {
			return getRuleContext(RuleXIfExpressionContext.class,0);
		}
		public RuleXBasicForLoopExpressionContext ruleXBasicForLoopExpression() {
			return getRuleContext(RuleXBasicForLoopExpressionContext.class,0);
		}
		public RuleXWhileExpressionContext ruleXWhileExpression() {
			return getRuleContext(RuleXWhileExpressionContext.class,0);
		}
		public RuleXDoWhileExpressionContext ruleXDoWhileExpression() {
			return getRuleContext(RuleXDoWhileExpressionContext.class,0);
		}
		public RuleXThrowExpressionContext ruleXThrowExpression() {
			return getRuleContext(RuleXThrowExpressionContext.class,0);
		}
		public RuleXReturnExpressionContext ruleXReturnExpression() {
			return getRuleContext(RuleXReturnExpressionContext.class,0);
		}
		public RuleXTryCatchFinallyExpressionContext ruleXTryCatchFinallyExpression() {
			return getRuleContext(RuleXTryCatchFinallyExpressionContext.class,0);
		}
		public RuleXParenthesizedExpressionContext ruleXParenthesizedExpression() {
			return getRuleContext(RuleXParenthesizedExpressionContext.class,0);
		}
		public RuleXSynchronizedExpressionContext ruleXSynchronizedExpression() {
			return getRuleContext(RuleXSynchronizedExpressionContext.class,0);
		}
		public RuleXForLoopExpressionContext ruleXForLoopExpression() {
			return getRuleContext(RuleXForLoopExpressionContext.class,0);
		}
		public RuleXPrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXPrimaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXPrimaryExpression(this);
		}
	}

	public final RuleXPrimaryExpressionContext ruleXPrimaryExpression() throws RecognitionException {
		RuleXPrimaryExpressionContext _localctx = new RuleXPrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_ruleXPrimaryExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(436);
				ruleXConstructorCall();
				}
				break;
			case 2:
				{
				setState(437);
				ruleXBlockExpression();
				}
				break;
			case 3:
				{
				setState(438);
				ruleXSwitchExpression();
				}
				break;
			case 4:
				{
				{
				setState(439);
				ruleXSynchronizedExpression();
				}
				}
				break;
			case 5:
				{
				setState(440);
				ruleXFeatureCall();
				}
				break;
			case 6:
				{
				setState(441);
				ruleXLiteral();
				}
				break;
			case 7:
				{
				setState(442);
				ruleXIfExpression();
				}
				break;
			case 8:
				{
				{
				setState(443);
				ruleXForLoopExpression();
				}
				}
				break;
			case 9:
				{
				setState(444);
				ruleXBasicForLoopExpression();
				}
				break;
			case 10:
				{
				setState(445);
				ruleXWhileExpression();
				}
				break;
			case 11:
				{
				setState(446);
				ruleXDoWhileExpression();
				}
				break;
			case 12:
				{
				setState(447);
				ruleXThrowExpression();
				}
				break;
			case 13:
				{
				setState(448);
				ruleXReturnExpression();
				}
				break;
			case 14:
				{
				setState(449);
				ruleXTryCatchFinallyExpression();
				}
				break;
			case 15:
				{
				setState(450);
				ruleXParenthesizedExpression();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXLiteralContext extends ParserRuleContext {
		public RuleXCollectionLiteralContext ruleXCollectionLiteral() {
			return getRuleContext(RuleXCollectionLiteralContext.class,0);
		}
		public RuleXBooleanLiteralContext ruleXBooleanLiteral() {
			return getRuleContext(RuleXBooleanLiteralContext.class,0);
		}
		public RuleXNumberLiteralContext ruleXNumberLiteral() {
			return getRuleContext(RuleXNumberLiteralContext.class,0);
		}
		public RuleXNullLiteralContext ruleXNullLiteral() {
			return getRuleContext(RuleXNullLiteralContext.class,0);
		}
		public RuleXStringLiteralContext ruleXStringLiteral() {
			return getRuleContext(RuleXStringLiteralContext.class,0);
		}
		public RuleXTypeLiteralContext ruleXTypeLiteral() {
			return getRuleContext(RuleXTypeLiteralContext.class,0);
		}
		public RuleXClosureContext ruleXClosure() {
			return getRuleContext(RuleXClosureContext.class,0);
		}
		public RuleXLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXLiteral(this);
		}
	}

	public final RuleXLiteralContext ruleXLiteral() throws RecognitionException {
		RuleXLiteralContext _localctx = new RuleXLiteralContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_ruleXLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
			switch (_input.LA(1)) {
			case T__45:
				{
				setState(453);
				ruleXCollectionLiteral();
				}
				break;
			case T__46:
				{
				{
				setState(454);
				ruleXClosure();
				}
				}
				break;
			case T__65:
			case T__66:
				{
				setState(455);
				ruleXBooleanLiteral();
				}
				break;
			case RULE_HEX:
			case RULE_INT:
			case RULE_DECIMAL:
				{
				setState(456);
				ruleXNumberLiteral();
				}
				break;
			case T__67:
				{
				setState(457);
				ruleXNullLiteral();
				}
				break;
			case RULE_STRING:
				{
				setState(458);
				ruleXStringLiteral();
				}
				break;
			case T__68:
				{
				setState(459);
				ruleXTypeLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXCollectionLiteralContext extends ParserRuleContext {
		public RuleXSetLiteralContext ruleXSetLiteral() {
			return getRuleContext(RuleXSetLiteralContext.class,0);
		}
		public RuleXListLiteralContext ruleXListLiteral() {
			return getRuleContext(RuleXListLiteralContext.class,0);
		}
		public RuleXCollectionLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXCollectionLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXCollectionLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXCollectionLiteral(this);
		}
	}

	public final RuleXCollectionLiteralContext ruleXCollectionLiteral() throws RecognitionException {
		RuleXCollectionLiteralContext _localctx = new RuleXCollectionLiteralContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_ruleXCollectionLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(462);
				ruleXSetLiteral();
				}
				break;
			case 2:
				{
				setState(463);
				ruleXListLiteral();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXSetLiteralContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleXSetLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXSetLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXSetLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXSetLiteral(this);
		}
	}

	public final RuleXSetLiteralContext ruleXSetLiteral() throws RecognitionException {
		RuleXSetLiteralContext _localctx = new RuleXSetLiteralContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_ruleXSetLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			match(T__45);
			setState(467);
			match(T__1);
			setState(476);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__7) | (1L << T__16) | (1L << T__32) | (1L << T__33) | (1L << T__38) | (1L << T__45) | (1L << T__46) | (1L << T__50) | (1L << T__52) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (T__67 - 64)) | (1L << (T__68 - 64)) | (1L << (T__69 - 64)) | (1L << (T__70 - 64)) | (1L << (T__71 - 64)) | (1L << (T__73 - 64)) | (1L << (RULE_HEX - 64)) | (1L << (RULE_INT - 64)) | (1L << (RULE_DECIMAL - 64)) | (1L << (RULE_ID - 64)) | (1L << (RULE_STRING - 64)))) != 0)) {
				{
				setState(468);
				ruleXExpression();
				setState(473);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(469);
					match(T__8);
					setState(470);
					ruleXExpression();
					}
					}
					setState(475);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(478);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXListLiteralContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleXListLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXListLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXListLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXListLiteral(this);
		}
	}

	public final RuleXListLiteralContext ruleXListLiteral() throws RecognitionException {
		RuleXListLiteralContext _localctx = new RuleXListLiteralContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_ruleXListLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			match(T__45);
			setState(481);
			match(T__46);
			setState(490);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__7) | (1L << T__16) | (1L << T__32) | (1L << T__33) | (1L << T__38) | (1L << T__45) | (1L << T__46) | (1L << T__50) | (1L << T__52) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (T__67 - 64)) | (1L << (T__68 - 64)) | (1L << (T__69 - 64)) | (1L << (T__70 - 64)) | (1L << (T__71 - 64)) | (1L << (T__73 - 64)) | (1L << (RULE_HEX - 64)) | (1L << (RULE_INT - 64)) | (1L << (RULE_DECIMAL - 64)) | (1L << (RULE_ID - 64)) | (1L << (RULE_STRING - 64)))) != 0)) {
				{
				setState(482);
				ruleXExpression();
				setState(487);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(483);
					match(T__8);
					setState(484);
					ruleXExpression();
					}
					}
					setState(489);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(492);
			match(T__47);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXClosureContext extends ParserRuleContext {
		public RuleXExpressionInClosureContext ruleXExpressionInClosure() {
			return getRuleContext(RuleXExpressionInClosureContext.class,0);
		}
		public List<RuleJvmFormalParameterContext> ruleJvmFormalParameter() {
			return getRuleContexts(RuleJvmFormalParameterContext.class);
		}
		public RuleJvmFormalParameterContext ruleJvmFormalParameter(int i) {
			return getRuleContext(RuleJvmFormalParameterContext.class,i);
		}
		public RuleXClosureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXClosure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXClosure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXClosure(this);
		}
	}

	public final RuleXClosureContext ruleXClosure() throws RecognitionException {
		RuleXClosureContext _localctx = new RuleXClosureContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_ruleXClosure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(494);
			match(T__46);
			}
			setState(506);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(503);
				_la = _input.LA(1);
				if (_la==T__7 || _la==T__29 || _la==RULE_ID) {
					{
					setState(495);
					ruleJvmFormalParameter();
					setState(500);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__8) {
						{
						{
						setState(496);
						match(T__8);
						setState(497);
						ruleJvmFormalParameter();
						}
						}
						setState(502);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(505);
				match(T__48);
				}
				break;
			}
			setState(508);
			ruleXExpressionInClosure();
			setState(509);
			match(T__47);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXExpressionInClosureContext extends ParserRuleContext {
		public List<RuleXExpressionOrVarDeclarationContext> ruleXExpressionOrVarDeclaration() {
			return getRuleContexts(RuleXExpressionOrVarDeclarationContext.class);
		}
		public RuleXExpressionOrVarDeclarationContext ruleXExpressionOrVarDeclaration(int i) {
			return getRuleContext(RuleXExpressionOrVarDeclarationContext.class,i);
		}
		public RuleXExpressionInClosureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXExpressionInClosure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXExpressionInClosure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXExpressionInClosure(this);
		}
	}

	public final RuleXExpressionInClosureContext ruleXExpressionInClosure() throws RecognitionException {
		RuleXExpressionInClosureContext _localctx = new RuleXExpressionInClosureContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_ruleXExpressionInClosure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__7) | (1L << T__16) | (1L << T__32) | (1L << T__33) | (1L << T__38) | (1L << T__45) | (1L << T__46) | (1L << T__50) | (1L << T__52) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (T__67 - 64)) | (1L << (T__68 - 64)) | (1L << (T__69 - 64)) | (1L << (T__70 - 64)) | (1L << (T__71 - 64)) | (1L << (T__73 - 64)) | (1L << (RULE_HEX - 64)) | (1L << (RULE_INT - 64)) | (1L << (RULE_DECIMAL - 64)) | (1L << (RULE_ID - 64)) | (1L << (RULE_STRING - 64)))) != 0)) {
				{
				{
				setState(511);
				ruleXExpressionOrVarDeclaration();
				setState(513);
				_la = _input.LA(1);
				if (_la==T__49) {
					{
					setState(512);
					match(T__49);
					}
				}

				}
				}
				setState(519);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXShortClosureContext extends ParserRuleContext {
		public RuleXExpressionContext ruleXExpression() {
			return getRuleContext(RuleXExpressionContext.class,0);
		}
		public List<RuleJvmFormalParameterContext> ruleJvmFormalParameter() {
			return getRuleContexts(RuleJvmFormalParameterContext.class);
		}
		public RuleJvmFormalParameterContext ruleJvmFormalParameter(int i) {
			return getRuleContext(RuleJvmFormalParameterContext.class,i);
		}
		public RuleXShortClosureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXShortClosure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXShortClosure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXShortClosure(this);
		}
	}

	public final RuleXShortClosureContext ruleXShortClosure() throws RecognitionException {
		RuleXShortClosureContext _localctx = new RuleXShortClosureContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_ruleXShortClosure);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(528);
			_la = _input.LA(1);
			if (_la==T__7 || _la==T__29 || _la==RULE_ID) {
				{
				setState(520);
				ruleJvmFormalParameter();
				setState(525);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(521);
					match(T__8);
					setState(522);
					ruleJvmFormalParameter();
					}
					}
					setState(527);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(530);
			match(T__48);
			}
			setState(532);
			ruleXExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXParenthesizedExpressionContext extends ParserRuleContext {
		public RuleXExpressionContext ruleXExpression() {
			return getRuleContext(RuleXExpressionContext.class,0);
		}
		public RuleXParenthesizedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXParenthesizedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXParenthesizedExpression(this);
		}
	}

	public final RuleXParenthesizedExpressionContext ruleXParenthesizedExpression() throws RecognitionException {
		RuleXParenthesizedExpressionContext _localctx = new RuleXParenthesizedExpressionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_ruleXParenthesizedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
			match(T__7);
			setState(535);
			ruleXExpression();
			setState(536);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXIfExpressionContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleXIfExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXIfExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXIfExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXIfExpression(this);
		}
	}

	public final RuleXIfExpressionContext ruleXIfExpression() throws RecognitionException {
		RuleXIfExpressionContext _localctx = new RuleXIfExpressionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_ruleXIfExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538);
			match(T__50);
			setState(539);
			match(T__7);
			setState(540);
			ruleXExpression();
			setState(541);
			match(T__9);
			setState(542);
			ruleXExpression();
			setState(545);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				{
				setState(543);
				match(T__51);
				}
				setState(544);
				ruleXExpression();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXSwitchExpressionContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public List<RuleXCasePartContext> ruleXCasePart() {
			return getRuleContexts(RuleXCasePartContext.class);
		}
		public RuleXCasePartContext ruleXCasePart(int i) {
			return getRuleContext(RuleXCasePartContext.class,i);
		}
		public RuleJvmFormalParameterContext ruleJvmFormalParameter() {
			return getRuleContext(RuleJvmFormalParameterContext.class,0);
		}
		public RuleXSwitchExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXSwitchExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXSwitchExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXSwitchExpression(this);
		}
	}

	public final RuleXSwitchExpressionContext ruleXSwitchExpression() throws RecognitionException {
		RuleXSwitchExpressionContext _localctx = new RuleXSwitchExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_ruleXSwitchExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(547);
			match(T__52);
			setState(561);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				{
				setState(548);
				match(T__7);
				setState(549);
				ruleJvmFormalParameter();
				setState(550);
				match(T__5);
				}
				setState(552);
				ruleXExpression();
				setState(553);
				match(T__9);
				}
				break;
			case 2:
				{
				setState(558);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					{
					setState(555);
					ruleJvmFormalParameter();
					setState(556);
					match(T__5);
					}
					break;
				}
				setState(560);
				ruleXExpression();
				}
				break;
			}
			setState(563);
			match(T__1);
			setState(567);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__29) | (1L << T__54))) != 0) || _la==RULE_ID) {
				{
				{
				setState(564);
				ruleXCasePart();
				}
				}
				setState(569);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(573);
			_la = _input.LA(1);
			if (_la==T__53) {
				{
				setState(570);
				match(T__53);
				setState(571);
				match(T__5);
				setState(572);
				ruleXExpression();
				}
			}

			setState(575);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXCasePartContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleXCasePartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXCasePart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXCasePart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXCasePart(this);
		}
	}

	public final RuleXCasePartContext ruleXCasePart() throws RecognitionException {
		RuleXCasePartContext _localctx = new RuleXCasePartContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_ruleXCasePart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(578);
			_la = _input.LA(1);
			if (_la==T__7 || _la==T__29 || _la==RULE_ID) {
				{
				setState(577);
				ruleJvmTypeReference();
				}
			}

			setState(582);
			_la = _input.LA(1);
			if (_la==T__54) {
				{
				setState(580);
				match(T__54);
				setState(581);
				ruleXExpression();
				}
			}

			setState(587);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(584);
				match(T__5);
				setState(585);
				ruleXExpression();
				}
				break;
			case T__8:
				{
				setState(586);
				match(T__8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXForLoopExpressionContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleJvmFormalParameterContext ruleJvmFormalParameter() {
			return getRuleContext(RuleJvmFormalParameterContext.class,0);
		}
		public RuleXForLoopExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXForLoopExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXForLoopExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXForLoopExpression(this);
		}
	}

	public final RuleXForLoopExpressionContext ruleXForLoopExpression() throws RecognitionException {
		RuleXForLoopExpressionContext _localctx = new RuleXForLoopExpressionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_ruleXForLoopExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(589);
			match(T__55);
			setState(590);
			match(T__7);
			setState(591);
			ruleJvmFormalParameter();
			setState(592);
			match(T__5);
			}
			setState(594);
			ruleXExpression();
			setState(595);
			match(T__9);
			setState(596);
			ruleXExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXBasicForLoopExpressionContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public List<RuleXExpressionOrVarDeclarationContext> ruleXExpressionOrVarDeclaration() {
			return getRuleContexts(RuleXExpressionOrVarDeclarationContext.class);
		}
		public RuleXExpressionOrVarDeclarationContext ruleXExpressionOrVarDeclaration(int i) {
			return getRuleContext(RuleXExpressionOrVarDeclarationContext.class,i);
		}
		public RuleXBasicForLoopExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXBasicForLoopExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXBasicForLoopExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXBasicForLoopExpression(this);
		}
	}

	public final RuleXBasicForLoopExpressionContext ruleXBasicForLoopExpression() throws RecognitionException {
		RuleXBasicForLoopExpressionContext _localctx = new RuleXBasicForLoopExpressionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_ruleXBasicForLoopExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(598);
			match(T__55);
			setState(599);
			match(T__7);
			setState(608);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__7) | (1L << T__16) | (1L << T__32) | (1L << T__33) | (1L << T__38) | (1L << T__45) | (1L << T__46) | (1L << T__50) | (1L << T__52) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (T__67 - 64)) | (1L << (T__68 - 64)) | (1L << (T__69 - 64)) | (1L << (T__70 - 64)) | (1L << (T__71 - 64)) | (1L << (T__73 - 64)) | (1L << (RULE_HEX - 64)) | (1L << (RULE_INT - 64)) | (1L << (RULE_DECIMAL - 64)) | (1L << (RULE_ID - 64)) | (1L << (RULE_STRING - 64)))) != 0)) {
				{
				setState(600);
				ruleXExpressionOrVarDeclaration();
				setState(605);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(601);
					match(T__8);
					setState(602);
					ruleXExpressionOrVarDeclaration();
					}
					}
					setState(607);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(610);
			match(T__49);
			setState(612);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__7) | (1L << T__16) | (1L << T__32) | (1L << T__33) | (1L << T__38) | (1L << T__45) | (1L << T__46) | (1L << T__50) | (1L << T__52) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (T__67 - 64)) | (1L << (T__68 - 64)) | (1L << (T__69 - 64)) | (1L << (T__70 - 64)) | (1L << (T__71 - 64)) | (1L << (T__73 - 64)) | (1L << (RULE_HEX - 64)) | (1L << (RULE_INT - 64)) | (1L << (RULE_DECIMAL - 64)) | (1L << (RULE_ID - 64)) | (1L << (RULE_STRING - 64)))) != 0)) {
				{
				setState(611);
				ruleXExpression();
				}
			}

			setState(614);
			match(T__49);
			setState(623);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__7) | (1L << T__16) | (1L << T__32) | (1L << T__33) | (1L << T__38) | (1L << T__45) | (1L << T__46) | (1L << T__50) | (1L << T__52) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (T__67 - 64)) | (1L << (T__68 - 64)) | (1L << (T__69 - 64)) | (1L << (T__70 - 64)) | (1L << (T__71 - 64)) | (1L << (T__73 - 64)) | (1L << (RULE_HEX - 64)) | (1L << (RULE_INT - 64)) | (1L << (RULE_DECIMAL - 64)) | (1L << (RULE_ID - 64)) | (1L << (RULE_STRING - 64)))) != 0)) {
				{
				setState(615);
				ruleXExpression();
				setState(620);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(616);
					match(T__8);
					setState(617);
					ruleXExpression();
					}
					}
					setState(622);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(625);
			match(T__9);
			setState(626);
			ruleXExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXWhileExpressionContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleXWhileExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXWhileExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXWhileExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXWhileExpression(this);
		}
	}

	public final RuleXWhileExpressionContext ruleXWhileExpression() throws RecognitionException {
		RuleXWhileExpressionContext _localctx = new RuleXWhileExpressionContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_ruleXWhileExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(628);
			match(T__56);
			setState(629);
			match(T__7);
			setState(630);
			ruleXExpression();
			setState(631);
			match(T__9);
			setState(632);
			ruleXExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXDoWhileExpressionContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleXDoWhileExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXDoWhileExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXDoWhileExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXDoWhileExpression(this);
		}
	}

	public final RuleXDoWhileExpressionContext ruleXDoWhileExpression() throws RecognitionException {
		RuleXDoWhileExpressionContext _localctx = new RuleXDoWhileExpressionContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_ruleXDoWhileExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(634);
			match(T__57);
			setState(635);
			ruleXExpression();
			setState(636);
			match(T__56);
			setState(637);
			match(T__7);
			setState(638);
			ruleXExpression();
			setState(639);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXBlockExpressionContext extends ParserRuleContext {
		public List<RuleXExpressionOrVarDeclarationContext> ruleXExpressionOrVarDeclaration() {
			return getRuleContexts(RuleXExpressionOrVarDeclarationContext.class);
		}
		public RuleXExpressionOrVarDeclarationContext ruleXExpressionOrVarDeclaration(int i) {
			return getRuleContext(RuleXExpressionOrVarDeclarationContext.class,i);
		}
		public RuleXBlockExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXBlockExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXBlockExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXBlockExpression(this);
		}
	}

	public final RuleXBlockExpressionContext ruleXBlockExpression() throws RecognitionException {
		RuleXBlockExpressionContext _localctx = new RuleXBlockExpressionContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_ruleXBlockExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(641);
			match(T__1);
			setState(648);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__4) | (1L << T__7) | (1L << T__16) | (1L << T__32) | (1L << T__33) | (1L << T__38) | (1L << T__45) | (1L << T__46) | (1L << T__50) | (1L << T__52) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59) | (1L << T__60) | (1L << T__61) | (1L << T__62))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__63 - 64)) | (1L << (T__64 - 64)) | (1L << (T__65 - 64)) | (1L << (T__66 - 64)) | (1L << (T__67 - 64)) | (1L << (T__68 - 64)) | (1L << (T__69 - 64)) | (1L << (T__70 - 64)) | (1L << (T__71 - 64)) | (1L << (T__73 - 64)) | (1L << (RULE_HEX - 64)) | (1L << (RULE_INT - 64)) | (1L << (RULE_DECIMAL - 64)) | (1L << (RULE_ID - 64)) | (1L << (RULE_STRING - 64)))) != 0)) {
				{
				{
				setState(642);
				ruleXExpressionOrVarDeclaration();
				setState(644);
				_la = _input.LA(1);
				if (_la==T__49) {
					{
					setState(643);
					match(T__49);
					}
				}

				}
				}
				setState(650);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(651);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXExpressionOrVarDeclarationContext extends ParserRuleContext {
		public RuleXVariableDeclarationContext ruleXVariableDeclaration() {
			return getRuleContext(RuleXVariableDeclarationContext.class,0);
		}
		public RuleXExpressionContext ruleXExpression() {
			return getRuleContext(RuleXExpressionContext.class,0);
		}
		public RuleXExpressionOrVarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXExpressionOrVarDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXExpressionOrVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXExpressionOrVarDeclaration(this);
		}
	}

	public final RuleXExpressionOrVarDeclarationContext ruleXExpressionOrVarDeclaration() throws RecognitionException {
		RuleXExpressionOrVarDeclarationContext _localctx = new RuleXExpressionOrVarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_ruleXExpressionOrVarDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(655);
			switch (_input.LA(1)) {
			case T__58:
			case T__59:
				{
				setState(653);
				ruleXVariableDeclaration();
				}
				break;
			case T__1:
			case T__4:
			case T__7:
			case T__16:
			case T__32:
			case T__33:
			case T__38:
			case T__45:
			case T__46:
			case T__50:
			case T__52:
			case T__55:
			case T__56:
			case T__57:
			case T__60:
			case T__61:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__73:
			case RULE_HEX:
			case RULE_INT:
			case RULE_DECIMAL:
			case RULE_ID:
			case RULE_STRING:
				{
				setState(654);
				ruleXExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXVariableDeclarationContext extends ParserRuleContext {
		public RuleValidIDContext ruleValidID() {
			return getRuleContext(RuleValidIDContext.class,0);
		}
		public RuleXExpressionContext ruleXExpression() {
			return getRuleContext(RuleXExpressionContext.class,0);
		}
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleXVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXVariableDeclaration(this);
		}
	}

	public final RuleXVariableDeclarationContext ruleXVariableDeclaration() throws RecognitionException {
		RuleXVariableDeclarationContext _localctx = new RuleXVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_ruleXVariableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(657);
			_la = _input.LA(1);
			if ( !(_la==T__58 || _la==T__59) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(662);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				{
				{
				setState(658);
				ruleJvmTypeReference();
				setState(659);
				ruleValidID();
				}
				}
				break;
			case 2:
				{
				setState(661);
				ruleValidID();
				}
				break;
			}
			setState(666);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(664);
				match(T__10);
				setState(665);
				ruleXExpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleJvmFormalParameterContext extends ParserRuleContext {
		public RuleValidIDContext ruleValidID() {
			return getRuleContext(RuleValidIDContext.class,0);
		}
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleJvmFormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJvmFormalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleJvmFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleJvmFormalParameter(this);
		}
	}

	public final RuleJvmFormalParameterContext ruleJvmFormalParameter() throws RecognitionException {
		RuleJvmFormalParameterContext _localctx = new RuleJvmFormalParameterContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_ruleJvmFormalParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(669);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				{
				setState(668);
				ruleJvmTypeReference();
				}
				break;
			}
			setState(671);
			ruleValidID();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleFullJvmFormalParameterContext extends ParserRuleContext {
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleValidIDContext ruleValidID() {
			return getRuleContext(RuleValidIDContext.class,0);
		}
		public RuleFullJvmFormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleFullJvmFormalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleFullJvmFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleFullJvmFormalParameter(this);
		}
	}

	public final RuleFullJvmFormalParameterContext ruleFullJvmFormalParameter() throws RecognitionException {
		RuleFullJvmFormalParameterContext _localctx = new RuleFullJvmFormalParameterContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_ruleFullJvmFormalParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(673);
			ruleJvmTypeReference();
			setState(674);
			ruleValidID();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXFeatureCallContext extends ParserRuleContext {
		public RuleIdOrSuperContext ruleIdOrSuper() {
			return getRuleContext(RuleIdOrSuperContext.class,0);
		}
		public List<RuleJvmArgumentTypeReferenceContext> ruleJvmArgumentTypeReference() {
			return getRuleContexts(RuleJvmArgumentTypeReferenceContext.class);
		}
		public RuleJvmArgumentTypeReferenceContext ruleJvmArgumentTypeReference(int i) {
			return getRuleContext(RuleJvmArgumentTypeReferenceContext.class,i);
		}
		public RuleXClosureContext ruleXClosure() {
			return getRuleContext(RuleXClosureContext.class,0);
		}
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleXShortClosureContext ruleXShortClosure() {
			return getRuleContext(RuleXShortClosureContext.class,0);
		}
		public RuleXFeatureCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXFeatureCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXFeatureCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXFeatureCall(this);
		}
	}

	public final RuleXFeatureCallContext ruleXFeatureCall() throws RecognitionException {
		RuleXFeatureCallContext _localctx = new RuleXFeatureCallContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_ruleXFeatureCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(687);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(676);
				match(T__16);
				setState(677);
				ruleJvmArgumentTypeReference();
				setState(682);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(678);
					match(T__8);
					setState(679);
					ruleJvmArgumentTypeReference();
					}
					}
					setState(684);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(685);
				match(T__17);
				}
			}

			setState(689);
			ruleIdOrSuper();
			setState(703);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				{
				{
				setState(690);
				match(T__7);
				}
				setState(700);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					{
					{
					setState(691);
					ruleXShortClosure();
					}
					}
					break;
				case 2:
					{
					setState(692);
					ruleXExpression();
					setState(697);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__8) {
						{
						{
						setState(693);
						match(T__8);
						setState(694);
						ruleXExpression();
						}
						}
						setState(699);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				setState(702);
				match(T__9);
				}
				break;
			}
			setState(706);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				{
				setState(705);
				ruleXClosure();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleFeatureCallIDContext extends ParserRuleContext {
		public RuleValidIDContext ruleValidID() {
			return getRuleContext(RuleValidIDContext.class,0);
		}
		public RuleFeatureCallIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleFeatureCallID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleFeatureCallID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleFeatureCallID(this);
		}
	}

	public final RuleFeatureCallIDContext ruleFeatureCallID() throws RecognitionException {
		RuleFeatureCallIDContext _localctx = new RuleFeatureCallIDContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_ruleFeatureCallID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(713);
			switch (_input.LA(1)) {
			case RULE_ID:
				{
				setState(708);
				ruleValidID();
				}
				break;
			case T__4:
				{
				setState(709);
				match(T__4);
				}
				break;
			case T__60:
				{
				setState(710);
				match(T__60);
				}
				break;
			case T__61:
				{
				setState(711);
				match(T__61);
				}
				break;
			case T__62:
				{
				setState(712);
				match(T__62);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleIdOrSuperContext extends ParserRuleContext {
		public RuleFeatureCallIDContext ruleFeatureCallID() {
			return getRuleContext(RuleFeatureCallIDContext.class,0);
		}
		public RuleIdOrSuperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleIdOrSuper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleIdOrSuper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleIdOrSuper(this);
		}
	}

	public final RuleIdOrSuperContext ruleIdOrSuper() throws RecognitionException {
		RuleIdOrSuperContext _localctx = new RuleIdOrSuperContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_ruleIdOrSuper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(717);
			switch (_input.LA(1)) {
			case T__4:
			case T__60:
			case T__61:
			case T__62:
			case RULE_ID:
				{
				setState(715);
				ruleFeatureCallID();
				}
				break;
			case T__63:
				{
				setState(716);
				match(T__63);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXConstructorCallContext extends ParserRuleContext {
		public RuleQualifiedNameContext ruleQualifiedName() {
			return getRuleContext(RuleQualifiedNameContext.class,0);
		}
		public List<RuleJvmArgumentTypeReferenceContext> ruleJvmArgumentTypeReference() {
			return getRuleContexts(RuleJvmArgumentTypeReferenceContext.class);
		}
		public RuleJvmArgumentTypeReferenceContext ruleJvmArgumentTypeReference(int i) {
			return getRuleContext(RuleJvmArgumentTypeReferenceContext.class,i);
		}
		public RuleXClosureContext ruleXClosure() {
			return getRuleContext(RuleXClosureContext.class,0);
		}
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleXShortClosureContext ruleXShortClosure() {
			return getRuleContext(RuleXShortClosureContext.class,0);
		}
		public RuleXConstructorCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXConstructorCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXConstructorCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXConstructorCall(this);
		}
	}

	public final RuleXConstructorCallContext ruleXConstructorCall() throws RecognitionException {
		RuleXConstructorCallContext _localctx = new RuleXConstructorCallContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_ruleXConstructorCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(719);
			match(T__64);
			setState(720);
			ruleQualifiedName();
			setState(732);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				{
				{
				setState(721);
				match(T__16);
				}
				setState(722);
				ruleJvmArgumentTypeReference();
				setState(727);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(723);
					match(T__8);
					setState(724);
					ruleJvmArgumentTypeReference();
					}
					}
					setState(729);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(730);
				match(T__17);
				}
				break;
			}
			setState(747);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				{
				setState(734);
				match(T__7);
				}
				setState(744);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					{
					setState(735);
					ruleXShortClosure();
					}
					}
					break;
				case 2:
					{
					setState(736);
					ruleXExpression();
					setState(741);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__8) {
						{
						{
						setState(737);
						match(T__8);
						setState(738);
						ruleXExpression();
						}
						}
						setState(743);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				setState(746);
				match(T__9);
				}
				break;
			}
			setState(750);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				{
				setState(749);
				ruleXClosure();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXBooleanLiteralContext extends ParserRuleContext {
		public RuleXBooleanLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXBooleanLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXBooleanLiteral(this);
		}
	}

	public final RuleXBooleanLiteralContext ruleXBooleanLiteral() throws RecognitionException {
		RuleXBooleanLiteralContext _localctx = new RuleXBooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_ruleXBooleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(752);
			_la = _input.LA(1);
			if ( !(_la==T__65 || _la==T__66) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXNullLiteralContext extends ParserRuleContext {
		public RuleXNullLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXNullLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXNullLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXNullLiteral(this);
		}
	}

	public final RuleXNullLiteralContext ruleXNullLiteral() throws RecognitionException {
		RuleXNullLiteralContext _localctx = new RuleXNullLiteralContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_ruleXNullLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(754);
			match(T__67);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXNumberLiteralContext extends ParserRuleContext {
		public RuleNumberContext ruleNumber() {
			return getRuleContext(RuleNumberContext.class,0);
		}
		public RuleXNumberLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXNumberLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXNumberLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXNumberLiteral(this);
		}
	}

	public final RuleXNumberLiteralContext ruleXNumberLiteral() throws RecognitionException {
		RuleXNumberLiteralContext _localctx = new RuleXNumberLiteralContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_ruleXNumberLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(756);
			ruleNumber();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXStringLiteralContext extends ParserRuleContext {
		public TerminalNode RULE_STRING() { return getToken(CodebuffParser.RULE_STRING, 0); }
		public RuleXStringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXStringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXStringLiteral(this);
		}
	}

	public final RuleXStringLiteralContext ruleXStringLiteral() throws RecognitionException {
		RuleXStringLiteralContext _localctx = new RuleXStringLiteralContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_ruleXStringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(758);
			match(RULE_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXTypeLiteralContext extends ParserRuleContext {
		public RuleQualifiedNameContext ruleQualifiedName() {
			return getRuleContext(RuleQualifiedNameContext.class,0);
		}
		public List<RuleArrayBracketsContext> ruleArrayBrackets() {
			return getRuleContexts(RuleArrayBracketsContext.class);
		}
		public RuleArrayBracketsContext ruleArrayBrackets(int i) {
			return getRuleContext(RuleArrayBracketsContext.class,i);
		}
		public RuleXTypeLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXTypeLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXTypeLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXTypeLiteral(this);
		}
	}

	public final RuleXTypeLiteralContext ruleXTypeLiteral() throws RecognitionException {
		RuleXTypeLiteralContext _localctx = new RuleXTypeLiteralContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_ruleXTypeLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(760);
			match(T__68);
			setState(761);
			match(T__7);
			setState(762);
			ruleQualifiedName();
			setState(766);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__46) {
				{
				{
				setState(763);
				ruleArrayBrackets();
				}
				}
				setState(768);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(769);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXThrowExpressionContext extends ParserRuleContext {
		public RuleXExpressionContext ruleXExpression() {
			return getRuleContext(RuleXExpressionContext.class,0);
		}
		public RuleXThrowExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXThrowExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXThrowExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXThrowExpression(this);
		}
	}

	public final RuleXThrowExpressionContext ruleXThrowExpression() throws RecognitionException {
		RuleXThrowExpressionContext _localctx = new RuleXThrowExpressionContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_ruleXThrowExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(771);
			match(T__69);
			setState(772);
			ruleXExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXReturnExpressionContext extends ParserRuleContext {
		public RuleXExpressionContext ruleXExpression() {
			return getRuleContext(RuleXExpressionContext.class,0);
		}
		public RuleXReturnExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXReturnExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXReturnExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXReturnExpression(this);
		}
	}

	public final RuleXReturnExpressionContext ruleXReturnExpression() throws RecognitionException {
		RuleXReturnExpressionContext _localctx = new RuleXReturnExpressionContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_ruleXReturnExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(774);
			match(T__70);
			setState(776);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				{
				setState(775);
				ruleXExpression();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXTryCatchFinallyExpressionContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public List<RuleXCatchClauseContext> ruleXCatchClause() {
			return getRuleContexts(RuleXCatchClauseContext.class);
		}
		public RuleXCatchClauseContext ruleXCatchClause(int i) {
			return getRuleContext(RuleXCatchClauseContext.class,i);
		}
		public RuleXTryCatchFinallyExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXTryCatchFinallyExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXTryCatchFinallyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXTryCatchFinallyExpression(this);
		}
	}

	public final RuleXTryCatchFinallyExpressionContext ruleXTryCatchFinallyExpression() throws RecognitionException {
		RuleXTryCatchFinallyExpressionContext _localctx = new RuleXTryCatchFinallyExpressionContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_ruleXTryCatchFinallyExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(778);
			match(T__71);
			setState(779);
			ruleXExpression();
			setState(791);
			switch (_input.LA(1)) {
			case T__74:
				{
				setState(781); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(780);
						ruleXCatchClause();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(783); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(787);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					{
					{
					setState(785);
					match(T__72);
					}
					setState(786);
					ruleXExpression();
					}
					break;
				}
				}
				break;
			case T__72:
				{
				setState(789);
				match(T__72);
				setState(790);
				ruleXExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXSynchronizedExpressionContext extends ParserRuleContext {
		public List<RuleXExpressionContext> ruleXExpression() {
			return getRuleContexts(RuleXExpressionContext.class);
		}
		public RuleXExpressionContext ruleXExpression(int i) {
			return getRuleContext(RuleXExpressionContext.class,i);
		}
		public RuleXSynchronizedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXSynchronizedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXSynchronizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXSynchronizedExpression(this);
		}
	}

	public final RuleXSynchronizedExpressionContext ruleXSynchronizedExpression() throws RecognitionException {
		RuleXSynchronizedExpressionContext _localctx = new RuleXSynchronizedExpressionContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_ruleXSynchronizedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(793);
			match(T__73);
			setState(794);
			match(T__7);
			}
			setState(796);
			ruleXExpression();
			setState(797);
			match(T__9);
			setState(798);
			ruleXExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXCatchClauseContext extends ParserRuleContext {
		public RuleFullJvmFormalParameterContext ruleFullJvmFormalParameter() {
			return getRuleContext(RuleFullJvmFormalParameterContext.class,0);
		}
		public RuleXExpressionContext ruleXExpression() {
			return getRuleContext(RuleXExpressionContext.class,0);
		}
		public RuleXCatchClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXCatchClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXCatchClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXCatchClause(this);
		}
	}

	public final RuleXCatchClauseContext ruleXCatchClause() throws RecognitionException {
		RuleXCatchClauseContext _localctx = new RuleXCatchClauseContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_ruleXCatchClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(800);
			match(T__74);
			}
			setState(801);
			match(T__7);
			setState(802);
			ruleFullJvmFormalParameter();
			setState(803);
			match(T__9);
			setState(804);
			ruleXExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleQualifiedNameContext extends ParserRuleContext {
		public List<RuleValidIDContext> ruleValidID() {
			return getRuleContexts(RuleValidIDContext.class);
		}
		public RuleValidIDContext ruleValidID(int i) {
			return getRuleContext(RuleValidIDContext.class,i);
		}
		public RuleQualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleQualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleQualifiedName(this);
		}
	}

	public final RuleQualifiedNameContext ruleQualifiedName() throws RecognitionException {
		RuleQualifiedNameContext _localctx = new RuleQualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_ruleQualifiedName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(806);
			ruleValidID();
			setState(811);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(807);
					match(T__42);
					}
					setState(808);
					ruleValidID();
					}
					} 
				}
				setState(813);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleNumberContext extends ParserRuleContext {
		public TerminalNode RULE_HEX() { return getToken(CodebuffParser.RULE_HEX, 0); }
		public List<TerminalNode> RULE_INT() { return getTokens(CodebuffParser.RULE_INT); }
		public TerminalNode RULE_INT(int i) {
			return getToken(CodebuffParser.RULE_INT, i);
		}
		public List<TerminalNode> RULE_DECIMAL() { return getTokens(CodebuffParser.RULE_DECIMAL); }
		public TerminalNode RULE_DECIMAL(int i) {
			return getToken(CodebuffParser.RULE_DECIMAL, i);
		}
		public RuleNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleNumber(this);
		}
	}

	public final RuleNumberContext ruleNumber() throws RecognitionException {
		RuleNumberContext _localctx = new RuleNumberContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_ruleNumber);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(820);
			switch (_input.LA(1)) {
			case RULE_HEX:
				{
				setState(814);
				match(RULE_HEX);
				}
				break;
			case RULE_INT:
			case RULE_DECIMAL:
				{
				setState(815);
				_la = _input.LA(1);
				if ( !(_la==RULE_INT || _la==RULE_DECIMAL) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(818);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
				case 1:
					{
					setState(816);
					match(T__42);
					setState(817);
					_la = _input.LA(1);
					if ( !(_la==RULE_INT || _la==RULE_DECIMAL) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleJvmTypeReferenceContext extends ParserRuleContext {
		public RuleJvmParameterizedTypeReferenceContext ruleJvmParameterizedTypeReference() {
			return getRuleContext(RuleJvmParameterizedTypeReferenceContext.class,0);
		}
		public RuleXFunctionTypeRefContext ruleXFunctionTypeRef() {
			return getRuleContext(RuleXFunctionTypeRefContext.class,0);
		}
		public List<RuleArrayBracketsContext> ruleArrayBrackets() {
			return getRuleContexts(RuleArrayBracketsContext.class);
		}
		public RuleArrayBracketsContext ruleArrayBrackets(int i) {
			return getRuleContext(RuleArrayBracketsContext.class,i);
		}
		public RuleJvmTypeReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJvmTypeReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleJvmTypeReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleJvmTypeReference(this);
		}
	}

	public final RuleJvmTypeReferenceContext ruleJvmTypeReference() throws RecognitionException {
		RuleJvmTypeReferenceContext _localctx = new RuleJvmTypeReferenceContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_ruleJvmTypeReference);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(830);
			switch (_input.LA(1)) {
			case RULE_ID:
				{
				setState(822);
				ruleJvmParameterizedTypeReference();
				setState(826);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(823);
						ruleArrayBrackets();
						}
						} 
					}
					setState(828);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
				}
				}
				break;
			case T__7:
			case T__29:
				{
				setState(829);
				ruleXFunctionTypeRef();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleArrayBracketsContext extends ParserRuleContext {
		public RuleArrayBracketsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleArrayBrackets; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleArrayBrackets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleArrayBrackets(this);
		}
	}

	public final RuleArrayBracketsContext ruleArrayBrackets() throws RecognitionException {
		RuleArrayBracketsContext _localctx = new RuleArrayBracketsContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_ruleArrayBrackets);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(832);
			match(T__46);
			setState(833);
			match(T__47);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXFunctionTypeRefContext extends ParserRuleContext {
		public List<RuleJvmTypeReferenceContext> ruleJvmTypeReference() {
			return getRuleContexts(RuleJvmTypeReferenceContext.class);
		}
		public RuleJvmTypeReferenceContext ruleJvmTypeReference(int i) {
			return getRuleContext(RuleJvmTypeReferenceContext.class,i);
		}
		public RuleXFunctionTypeRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXFunctionTypeRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXFunctionTypeRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXFunctionTypeRef(this);
		}
	}

	public final RuleXFunctionTypeRefContext ruleXFunctionTypeRef() throws RecognitionException {
		RuleXFunctionTypeRefContext _localctx = new RuleXFunctionTypeRefContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_ruleXFunctionTypeRef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(847);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(835);
				match(T__7);
				setState(844);
				_la = _input.LA(1);
				if (_la==T__7 || _la==T__29 || _la==RULE_ID) {
					{
					setState(836);
					ruleJvmTypeReference();
					setState(841);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__8) {
						{
						{
						setState(837);
						match(T__8);
						setState(838);
						ruleJvmTypeReference();
						}
						}
						setState(843);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(846);
				match(T__9);
				}
			}

			setState(849);
			match(T__29);
			setState(850);
			ruleJvmTypeReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleJvmParameterizedTypeReferenceContext extends ParserRuleContext {
		public RuleQualifiedNameContext ruleQualifiedName() {
			return getRuleContext(RuleQualifiedNameContext.class,0);
		}
		public List<RuleJvmArgumentTypeReferenceContext> ruleJvmArgumentTypeReference() {
			return getRuleContexts(RuleJvmArgumentTypeReferenceContext.class);
		}
		public RuleJvmArgumentTypeReferenceContext ruleJvmArgumentTypeReference(int i) {
			return getRuleContext(RuleJvmArgumentTypeReferenceContext.class,i);
		}
		public List<RuleValidIDContext> ruleValidID() {
			return getRuleContexts(RuleValidIDContext.class);
		}
		public RuleValidIDContext ruleValidID(int i) {
			return getRuleContext(RuleValidIDContext.class,i);
		}
		public RuleJvmParameterizedTypeReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJvmParameterizedTypeReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleJvmParameterizedTypeReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleJvmParameterizedTypeReference(this);
		}
	}

	public final RuleJvmParameterizedTypeReferenceContext ruleJvmParameterizedTypeReference() throws RecognitionException {
		RuleJvmParameterizedTypeReferenceContext _localctx = new RuleJvmParameterizedTypeReferenceContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_ruleJvmParameterizedTypeReference);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(852);
			ruleQualifiedName();
			setState(883);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
			case 1:
				{
				{
				setState(853);
				match(T__16);
				}
				setState(854);
				ruleJvmArgumentTypeReference();
				setState(859);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(855);
					match(T__8);
					setState(856);
					ruleJvmArgumentTypeReference();
					}
					}
					setState(861);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(862);
				match(T__17);
				setState(880);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						{
						setState(863);
						match(T__42);
						}
						setState(864);
						ruleValidID();
						setState(876);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
						case 1:
							{
							{
							setState(865);
							match(T__16);
							}
							setState(866);
							ruleJvmArgumentTypeReference();
							setState(871);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__8) {
								{
								{
								setState(867);
								match(T__8);
								setState(868);
								ruleJvmArgumentTypeReference();
								}
								}
								setState(873);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(874);
							match(T__17);
							}
							break;
						}
						}
						} 
					}
					setState(882);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleJvmArgumentTypeReferenceContext extends ParserRuleContext {
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleJvmWildcardTypeReferenceContext ruleJvmWildcardTypeReference() {
			return getRuleContext(RuleJvmWildcardTypeReferenceContext.class,0);
		}
		public RuleJvmArgumentTypeReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJvmArgumentTypeReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleJvmArgumentTypeReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleJvmArgumentTypeReference(this);
		}
	}

	public final RuleJvmArgumentTypeReferenceContext ruleJvmArgumentTypeReference() throws RecognitionException {
		RuleJvmArgumentTypeReferenceContext _localctx = new RuleJvmArgumentTypeReferenceContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_ruleJvmArgumentTypeReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(887);
			switch (_input.LA(1)) {
			case T__7:
			case T__29:
			case RULE_ID:
				{
				setState(885);
				ruleJvmTypeReference();
				}
				break;
			case T__75:
				{
				setState(886);
				ruleJvmWildcardTypeReference();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleJvmWildcardTypeReferenceContext extends ParserRuleContext {
		public RuleJvmUpperBoundContext ruleJvmUpperBound() {
			return getRuleContext(RuleJvmUpperBoundContext.class,0);
		}
		public RuleJvmLowerBoundContext ruleJvmLowerBound() {
			return getRuleContext(RuleJvmLowerBoundContext.class,0);
		}
		public List<RuleJvmUpperBoundAndedContext> ruleJvmUpperBoundAnded() {
			return getRuleContexts(RuleJvmUpperBoundAndedContext.class);
		}
		public RuleJvmUpperBoundAndedContext ruleJvmUpperBoundAnded(int i) {
			return getRuleContext(RuleJvmUpperBoundAndedContext.class,i);
		}
		public List<RuleJvmLowerBoundAndedContext> ruleJvmLowerBoundAnded() {
			return getRuleContexts(RuleJvmLowerBoundAndedContext.class);
		}
		public RuleJvmLowerBoundAndedContext ruleJvmLowerBoundAnded(int i) {
			return getRuleContext(RuleJvmLowerBoundAndedContext.class,i);
		}
		public RuleJvmWildcardTypeReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJvmWildcardTypeReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleJvmWildcardTypeReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleJvmWildcardTypeReference(this);
		}
	}

	public final RuleJvmWildcardTypeReferenceContext ruleJvmWildcardTypeReference() throws RecognitionException {
		RuleJvmWildcardTypeReferenceContext _localctx = new RuleJvmWildcardTypeReferenceContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_ruleJvmWildcardTypeReference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(889);
			match(T__75);
			setState(904);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(890);
				ruleJvmUpperBound();
				setState(894);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__76) {
					{
					{
					setState(891);
					ruleJvmUpperBoundAnded();
					}
					}
					setState(896);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__63:
				{
				setState(897);
				ruleJvmLowerBound();
				setState(901);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__76) {
					{
					{
					setState(898);
					ruleJvmLowerBoundAnded();
					}
					}
					setState(903);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__8:
			case T__17:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleJvmUpperBoundContext extends ParserRuleContext {
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleJvmUpperBoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJvmUpperBound; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleJvmUpperBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleJvmUpperBound(this);
		}
	}

	public final RuleJvmUpperBoundContext ruleJvmUpperBound() throws RecognitionException {
		RuleJvmUpperBoundContext _localctx = new RuleJvmUpperBoundContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_ruleJvmUpperBound);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(906);
			match(T__4);
			setState(907);
			ruleJvmTypeReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleJvmUpperBoundAndedContext extends ParserRuleContext {
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleJvmUpperBoundAndedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJvmUpperBoundAnded; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleJvmUpperBoundAnded(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleJvmUpperBoundAnded(this);
		}
	}

	public final RuleJvmUpperBoundAndedContext ruleJvmUpperBoundAnded() throws RecognitionException {
		RuleJvmUpperBoundAndedContext _localctx = new RuleJvmUpperBoundAndedContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_ruleJvmUpperBoundAnded);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(909);
			match(T__76);
			setState(910);
			ruleJvmTypeReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleJvmLowerBoundContext extends ParserRuleContext {
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleJvmLowerBoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJvmLowerBound; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleJvmLowerBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleJvmLowerBound(this);
		}
	}

	public final RuleJvmLowerBoundContext ruleJvmLowerBound() throws RecognitionException {
		RuleJvmLowerBoundContext _localctx = new RuleJvmLowerBoundContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_ruleJvmLowerBound);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(912);
			match(T__63);
			setState(913);
			ruleJvmTypeReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleJvmLowerBoundAndedContext extends ParserRuleContext {
		public RuleJvmTypeReferenceContext ruleJvmTypeReference() {
			return getRuleContext(RuleJvmTypeReferenceContext.class,0);
		}
		public RuleJvmLowerBoundAndedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJvmLowerBoundAnded; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleJvmLowerBoundAnded(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleJvmLowerBoundAnded(this);
		}
	}

	public final RuleJvmLowerBoundAndedContext ruleJvmLowerBoundAnded() throws RecognitionException {
		RuleJvmLowerBoundAndedContext _localctx = new RuleJvmLowerBoundAndedContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_ruleJvmLowerBoundAnded);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(915);
			match(T__76);
			setState(916);
			ruleJvmTypeReference();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleQualifiedNameWithWildcardContext extends ParserRuleContext {
		public RuleQualifiedNameContext ruleQualifiedName() {
			return getRuleContext(RuleQualifiedNameContext.class,0);
		}
		public RuleQualifiedNameWithWildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleQualifiedNameWithWildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleQualifiedNameWithWildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleQualifiedNameWithWildcard(this);
		}
	}

	public final RuleQualifiedNameWithWildcardContext ruleQualifiedNameWithWildcard() throws RecognitionException {
		RuleQualifiedNameWithWildcardContext _localctx = new RuleQualifiedNameWithWildcardContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_ruleQualifiedNameWithWildcard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(918);
			ruleQualifiedName();
			setState(919);
			match(T__42);
			setState(920);
			match(T__34);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleValidIDContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(CodebuffParser.RULE_ID, 0); }
		public RuleValidIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleValidID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleValidID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleValidID(this);
		}
	}

	public final RuleValidIDContext ruleValidID() throws RecognitionException {
		RuleValidIDContext _localctx = new RuleValidIDContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_ruleValidID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(922);
			match(RULE_ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXImportSectionContext extends ParserRuleContext {
		public List<RuleXImportDeclarationContext> ruleXImportDeclaration() {
			return getRuleContexts(RuleXImportDeclarationContext.class);
		}
		public RuleXImportDeclarationContext ruleXImportDeclaration(int i) {
			return getRuleContext(RuleXImportDeclarationContext.class,i);
		}
		public RuleXImportSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXImportSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXImportSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXImportSection(this);
		}
	}

	public final RuleXImportSectionContext ruleXImportSection() throws RecognitionException {
		RuleXImportSectionContext _localctx = new RuleXImportSectionContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_ruleXImportSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(925); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(924);
				ruleXImportDeclaration();
				}
				}
				setState(927); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__61 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleXImportDeclarationContext extends ParserRuleContext {
		public RuleQualifiedNameInStaticImportContext ruleQualifiedNameInStaticImport() {
			return getRuleContext(RuleQualifiedNameInStaticImportContext.class,0);
		}
		public RuleQualifiedNameContext ruleQualifiedName() {
			return getRuleContext(RuleQualifiedNameContext.class,0);
		}
		public RuleQualifiedNameWithWildcardContext ruleQualifiedNameWithWildcard() {
			return getRuleContext(RuleQualifiedNameWithWildcardContext.class,0);
		}
		public RuleValidIDContext ruleValidID() {
			return getRuleContext(RuleValidIDContext.class,0);
		}
		public RuleXImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleXImportDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleXImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleXImportDeclaration(this);
		}
	}

	public final RuleXImportDeclarationContext ruleXImportDeclaration() throws RecognitionException {
		RuleXImportDeclarationContext _localctx = new RuleXImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_ruleXImportDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(929);
			match(T__61);
			setState(941);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				{
				setState(930);
				match(T__60);
				setState(932);
				_la = _input.LA(1);
				if (_la==T__62) {
					{
					setState(931);
					match(T__62);
					}
				}

				setState(934);
				ruleQualifiedNameInStaticImport();
				setState(937);
				switch (_input.LA(1)) {
				case T__34:
					{
					setState(935);
					match(T__34);
					}
					break;
				case RULE_ID:
					{
					setState(936);
					ruleValidID();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				{
				setState(939);
				ruleQualifiedName();
				}
				break;
			case 3:
				{
				setState(940);
				ruleQualifiedNameWithWildcard();
				}
				break;
			}
			setState(944);
			_la = _input.LA(1);
			if (_la==T__49) {
				{
				setState(943);
				match(T__49);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RuleQualifiedNameInStaticImportContext extends ParserRuleContext {
		public List<RuleValidIDContext> ruleValidID() {
			return getRuleContexts(RuleValidIDContext.class);
		}
		public RuleValidIDContext ruleValidID(int i) {
			return getRuleContext(RuleValidIDContext.class,i);
		}
		public RuleQualifiedNameInStaticImportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleQualifiedNameInStaticImport; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).enterRuleQualifiedNameInStaticImport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CodebuffListener ) ((CodebuffListener)listener).exitRuleQualifiedNameInStaticImport(this);
		}
	}

	public final RuleQualifiedNameInStaticImportContext ruleQualifiedNameInStaticImport() throws RecognitionException {
		RuleQualifiedNameInStaticImportContext _localctx = new RuleQualifiedNameInStaticImportContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_ruleQualifiedNameInStaticImport);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(949); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(946);
					ruleValidID();
					setState(947);
					match(T__42);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(951); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3X\u03bc\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\3\2\5\2\u00aa\n\2\3\2\7\2\u00ad\n\2\f\2\16\2\u00b0\13\2\3\3\3\3\5\3\u00b4"+
		"\n\3\3\4\3\4\3\4\3\4\7\4\u00ba\n\4\f\4\16\4\u00bd\13\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\5\5\u00c5\n\5\3\5\3\5\7\5\u00c9\n\5\f\5\16\5\u00cc\13\5\3\5\3"+
		"\5\3\6\3\6\5\6\u00d2\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00de"+
		"\n\b\f\b\16\b\u00e1\13\b\5\b\u00e3\n\b\3\b\3\b\3\b\5\b\u00e8\n\b\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00f6\n\n\5\n\u00f8\n\n"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0106\n\f\3\f\5"+
		"\f\u0109\n\f\3\r\3\r\3\r\3\r\7\r\u010f\n\r\f\r\16\r\u0112\13\r\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\7\17\u011a\n\17\f\17\16\17\u011d\13\17\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\7\21\u0125\n\21\f\21\16\21\u0128\13\21\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0132\n\23\f\23\16\23\u0135\13"+
		"\23\3\24\3\24\3\24\3\24\3\24\5\24\u013c\n\24\3\25\3\25\3\25\3\25\7\25"+
		"\u0142\n\25\f\25\16\25\u0145\13\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\5\26\u0151\n\26\3\26\3\26\3\26\3\26\3\26\5\26\u0158\n"+
		"\26\3\26\3\26\5\26\u015c\n\26\3\27\3\27\3\27\3\27\7\27\u0162\n\27\f\27"+
		"\16\27\u0165\13\27\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u016d\n\31\f\31"+
		"\16\31\u0170\13\31\3\32\3\32\3\33\3\33\3\33\3\33\5\33\u0178\n\33\3\34"+
		"\3\34\3\35\3\35\3\35\7\35\u017f\n\35\f\35\16\35\u0182\13\35\3\36\3\36"+
		"\5\36\u0186\n\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \7 \u0196"+
		"\n \f \16 \u0199\13 \3 \3 \5 \u019d\n \3 \3 \3 \3 \3 \3 \7 \u01a5\n \f"+
		" \16 \u01a8\13 \5 \u01aa\n \3 \5 \u01ad\n \3 \5 \u01b0\n \7 \u01b2\n "+
		"\f \16 \u01b5\13 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u01c6"+
		"\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u01cf\n\"\3#\3#\5#\u01d3\n#\3$\3$"+
		"\3$\3$\3$\7$\u01da\n$\f$\16$\u01dd\13$\5$\u01df\n$\3$\3$\3%\3%\3%\3%\3"+
		"%\7%\u01e8\n%\f%\16%\u01eb\13%\5%\u01ed\n%\3%\3%\3&\3&\3&\3&\7&\u01f5"+
		"\n&\f&\16&\u01f8\13&\5&\u01fa\n&\3&\5&\u01fd\n&\3&\3&\3&\3\'\3\'\5\'\u0204"+
		"\n\'\7\'\u0206\n\'\f\'\16\'\u0209\13\'\3(\3(\3(\7(\u020e\n(\f(\16(\u0211"+
		"\13(\5(\u0213\n(\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\5*\u0224"+
		"\n*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\5+\u0231\n+\3+\5+\u0234\n+\3+\3+"+
		"\7+\u0238\n+\f+\16+\u023b\13+\3+\3+\3+\5+\u0240\n+\3+\3+\3,\5,\u0245\n"+
		",\3,\3,\5,\u0249\n,\3,\3,\3,\5,\u024e\n,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3"+
		".\3.\3.\3.\3.\7.\u025e\n.\f.\16.\u0261\13.\5.\u0263\n.\3.\3.\5.\u0267"+
		"\n.\3.\3.\3.\3.\7.\u026d\n.\f.\16.\u0270\13.\5.\u0272\n.\3.\3.\3.\3/\3"+
		"/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\5\61\u0287"+
		"\n\61\7\61\u0289\n\61\f\61\16\61\u028c\13\61\3\61\3\61\3\62\3\62\5\62"+
		"\u0292\n\62\3\63\3\63\3\63\3\63\3\63\5\63\u0299\n\63\3\63\3\63\5\63\u029d"+
		"\n\63\3\64\5\64\u02a0\n\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66\3\66"+
		"\7\66\u02ab\n\66\f\66\16\66\u02ae\13\66\3\66\3\66\5\66\u02b2\n\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\7\66\u02ba\n\66\f\66\16\66\u02bd\13\66\5\66"+
		"\u02bf\n\66\3\66\5\66\u02c2\n\66\3\66\5\66\u02c5\n\66\3\67\3\67\3\67\3"+
		"\67\3\67\5\67\u02cc\n\67\38\38\58\u02d0\n8\39\39\39\39\39\39\79\u02d8"+
		"\n9\f9\169\u02db\139\39\39\59\u02df\n9\39\39\39\39\39\79\u02e6\n9\f9\16"+
		"9\u02e9\139\59\u02eb\n9\39\59\u02ee\n9\39\59\u02f1\n9\3:\3:\3;\3;\3<\3"+
		"<\3=\3=\3>\3>\3>\3>\7>\u02ff\n>\f>\16>\u0302\13>\3>\3>\3?\3?\3?\3@\3@"+
		"\5@\u030b\n@\3A\3A\3A\6A\u0310\nA\rA\16A\u0311\3A\3A\5A\u0316\nA\3A\3"+
		"A\5A\u031a\nA\3B\3B\3B\3B\3B\3B\3B\3C\3C\3C\3C\3C\3C\3D\3D\3D\7D\u032c"+
		"\nD\fD\16D\u032f\13D\3E\3E\3E\3E\5E\u0335\nE\5E\u0337\nE\3F\3F\7F\u033b"+
		"\nF\fF\16F\u033e\13F\3F\5F\u0341\nF\3G\3G\3G\3H\3H\3H\3H\7H\u034a\nH\f"+
		"H\16H\u034d\13H\5H\u034f\nH\3H\5H\u0352\nH\3H\3H\3H\3I\3I\3I\3I\3I\7I"+
		"\u035c\nI\fI\16I\u035f\13I\3I\3I\3I\3I\3I\3I\3I\7I\u0368\nI\fI\16I\u036b"+
		"\13I\3I\3I\5I\u036f\nI\7I\u0371\nI\fI\16I\u0374\13I\5I\u0376\nI\3J\3J"+
		"\5J\u037a\nJ\3K\3K\3K\7K\u037f\nK\fK\16K\u0382\13K\3K\3K\7K\u0386\nK\f"+
		"K\16K\u0389\13K\5K\u038b\nK\3L\3L\3L\3M\3M\3M\3N\3N\3N\3O\3O\3O\3P\3P"+
		"\3P\3P\3Q\3Q\3R\6R\u03a0\nR\rR\16R\u03a1\3S\3S\3S\5S\u03a7\nS\3S\3S\3"+
		"S\5S\u03ac\nS\3S\3S\5S\u03b0\nS\3S\5S\u03b3\nS\3T\3T\3T\6T\u03b8\nT\r"+
		"T\16T\u03b9\3T\2\2U\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086"+
		"\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e"+
		"\u00a0\u00a2\u00a4\u00a6\2\f\3\2\30\33\3\2#$\3\2%(\4\2#$))\3\2+,\3\2-"+
		".\3\2-/\3\2=>\3\2DE\3\2QR\u0401\2\u00a9\3\2\2\2\4\u00b3\3\2\2\2\6\u00b5"+
		"\3\2\2\2\b\u00c0\3\2\2\2\n\u00d1\3\2\2\2\f\u00d3\3\2\2\2\16\u00d7\3\2"+
		"\2\2\20\u00eb\3\2\2\2\22\u00f7\3\2\2\2\24\u00f9\3\2\2\2\26\u0108\3\2\2"+
		"\2\30\u010a\3\2\2\2\32\u0113\3\2\2\2\34\u0115\3\2\2\2\36\u011e\3\2\2\2"+
		" \u0120\3\2\2\2\"\u0129\3\2\2\2$\u012b\3\2\2\2&\u013b\3\2\2\2(\u013d\3"+
		"\2\2\2*\u015b\3\2\2\2,\u015d\3\2\2\2.\u0166\3\2\2\2\60\u0168\3\2\2\2\62"+
		"\u0171\3\2\2\2\64\u0177\3\2\2\2\66\u0179\3\2\2\28\u017b\3\2\2\2:\u0183"+
		"\3\2\2\2<\u0187\3\2\2\2>\u0189\3\2\2\2@\u01c5\3\2\2\2B\u01ce\3\2\2\2D"+
		"\u01d2\3\2\2\2F\u01d4\3\2\2\2H\u01e2\3\2\2\2J\u01f0\3\2\2\2L\u0207\3\2"+
		"\2\2N\u0212\3\2\2\2P\u0218\3\2\2\2R\u021c\3\2\2\2T\u0225\3\2\2\2V\u0244"+
		"\3\2\2\2X\u024f\3\2\2\2Z\u0258\3\2\2\2\\\u0276\3\2\2\2^\u027c\3\2\2\2"+
		"`\u0283\3\2\2\2b\u0291\3\2\2\2d\u0293\3\2\2\2f\u029f\3\2\2\2h\u02a3\3"+
		"\2\2\2j\u02b1\3\2\2\2l\u02cb\3\2\2\2n\u02cf\3\2\2\2p\u02d1\3\2\2\2r\u02f2"+
		"\3\2\2\2t\u02f4\3\2\2\2v\u02f6\3\2\2\2x\u02f8\3\2\2\2z\u02fa\3\2\2\2|"+
		"\u0305\3\2\2\2~\u0308\3\2\2\2\u0080\u030c\3\2\2\2\u0082\u031b\3\2\2\2"+
		"\u0084\u0322\3\2\2\2\u0086\u0328\3\2\2\2\u0088\u0336\3\2\2\2\u008a\u0340"+
		"\3\2\2\2\u008c\u0342\3\2\2\2\u008e\u0351\3\2\2\2\u0090\u0356\3\2\2\2\u0092"+
		"\u0379\3\2\2\2\u0094\u037b\3\2\2\2\u0096\u038c\3\2\2\2\u0098\u038f\3\2"+
		"\2\2\u009a\u0392\3\2\2\2\u009c\u0395\3\2\2\2\u009e\u0398\3\2\2\2\u00a0"+
		"\u039c\3\2\2\2\u00a2\u039f\3\2\2\2\u00a4\u03a3\3\2\2\2\u00a6\u03b7\3\2"+
		"\2\2\u00a8\u00aa\5\u00a2R\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ae\3\2\2\2\u00ab\u00ad\5\4\3\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2"+
		"\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\3\3\2\2\2\u00b0\u00ae"+
		"\3\2\2\2\u00b1\u00b4\5\6\4\2\u00b2\u00b4\5\b\5\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b2\3\2\2\2\u00b4\5\3\2\2\2\u00b5\u00b6\7\3\2\2\u00b6\u00b7\5\u0086"+
		"D\2\u00b7\u00bb\7\4\2\2\u00b8\u00ba\5\4\3\2\u00b9\u00b8\3\2\2\2\u00ba"+
		"\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2"+
		"\2\2\u00bd\u00bb\3\2\2\2\u00be\u00bf\7\5\2\2\u00bf\7\3\2\2\2\u00c0\u00c1"+
		"\7\6\2\2\u00c1\u00c4\5\u00a0Q\2\u00c2\u00c3\7\7\2\2\u00c3\u00c5\5\u0090"+
		"I\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00ca\7\4\2\2\u00c7\u00c9\5\n\6\2\u00c8\u00c7\3\2\2\2\u00c9\u00cc\3\2"+
		"\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cd\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cd\u00ce\7\5\2\2\u00ce\t\3\2\2\2\u00cf\u00d2\5\f\7\2"+
		"\u00d0\u00d2\5\16\b\2\u00d1\u00cf\3\2\2\2\u00d1\u00d0\3\2\2\2\u00d2\13"+
		"\3\2\2\2\u00d3\u00d4\5\u00a0Q\2\u00d4\u00d5\7\b\2\2\u00d5\u00d6\5\u008a"+
		"F\2\u00d6\r\3\2\2\2\u00d7\u00d8\7\t\2\2\u00d8\u00d9\5\u00a0Q\2\u00d9\u00e2"+
		"\7\n\2\2\u00da\u00df\5h\65\2\u00db\u00dc\7\13\2\2\u00dc\u00de\5h\65\2"+
		"\u00dd\u00db\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0"+
		"\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00da\3\2\2\2\u00e2"+
		"\u00e3\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e7\7\f\2\2\u00e5\u00e6\7\b"+
		"\2\2\u00e6\u00e8\5\u008aF\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9\u00ea\5`\61\2\u00ea\17\3\2\2\2\u00eb\u00ec\5\22\n"+
		"\2\u00ec\21\3\2\2\2\u00ed\u00ee\5l\67\2\u00ee\u00ef\5\24\13\2\u00ef\u00f0"+
		"\5\22\n\2\u00f0\u00f8\3\2\2\2\u00f1\u00f5\5\30\r\2\u00f2\u00f3\5\26\f"+
		"\2\u00f3\u00f4\5\22\n\2\u00f4\u00f6\3\2\2\2\u00f5\u00f2\3\2\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6\u00f8\3\2\2\2\u00f7\u00ed\3\2\2\2\u00f7\u00f1\3\2"+
		"\2\2\u00f8\23\3\2\2\2\u00f9\u00fa\7\r\2\2\u00fa\25\3\2\2\2\u00fb\u0109"+
		"\7\16\2\2\u00fc\u0109\7\17\2\2\u00fd\u0109\7\20\2\2\u00fe\u0109\7\21\2"+
		"\2\u00ff\u0109\7\22\2\2\u0100\u0101\7\23\2\2\u0101\u0102\7\23\2\2\u0102"+
		"\u0109\7\r\2\2\u0103\u0105\7\24\2\2\u0104\u0106\7\24\2\2\u0105\u0104\3"+
		"\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0109\7\25\2\2\u0108"+
		"\u00fb\3\2\2\2\u0108\u00fc\3\2\2\2\u0108\u00fd\3\2\2\2\u0108\u00fe\3\2"+
		"\2\2\u0108\u00ff\3\2\2\2\u0108\u0100\3\2\2\2\u0108\u0103\3\2\2\2\u0109"+
		"\27\3\2\2\2\u010a\u0110\5\34\17\2\u010b\u010c\5\32\16\2\u010c\u010d\5"+
		"\34\17\2\u010d\u010f\3\2\2\2\u010e\u010b\3\2\2\2\u010f\u0112\3\2\2\2\u0110"+
		"\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\31\3\2\2\2\u0112\u0110\3\2\2"+
		"\2\u0113\u0114\7\26\2\2\u0114\33\3\2\2\2\u0115\u011b\5 \21\2\u0116\u0117"+
		"\5\36\20\2\u0117\u0118\5 \21\2\u0118\u011a\3\2\2\2\u0119\u0116\3\2\2\2"+
		"\u011a\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\35"+
		"\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u011f\7\27\2\2\u011f\37\3\2\2\2\u0120"+
		"\u0126\5$\23\2\u0121\u0122\5\"\22\2\u0122\u0123\5$\23\2\u0123\u0125\3"+
		"\2\2\2\u0124\u0121\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124\3\2\2\2\u0126"+
		"\u0127\3\2\2\2\u0127!\3\2\2\2\u0128\u0126\3\2\2\2\u0129\u012a\t\2\2\2"+
		"\u012a#\3\2\2\2\u012b\u0133\5(\25\2\u012c\u012d\7\34\2\2\u012d\u0132\5"+
		"\u008aF\2\u012e\u012f\5&\24\2\u012f\u0130\5(\25\2\u0130\u0132\3\2\2\2"+
		"\u0131\u012c\3\2\2\2\u0131\u012e\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131"+
		"\3\2\2\2\u0133\u0134\3\2\2\2\u0134%\3\2\2\2\u0135\u0133\3\2\2\2\u0136"+
		"\u013c\7\25\2\2\u0137\u0138\7\23\2\2\u0138\u013c\7\r\2\2\u0139\u013c\7"+
		"\24\2\2\u013a\u013c\7\23\2\2\u013b\u0136\3\2\2\2\u013b\u0137\3\2\2\2\u013b"+
		"\u0139\3\2\2\2\u013b\u013a\3\2\2\2\u013c\'\3\2\2\2\u013d\u0143\5,\27\2"+
		"\u013e\u013f\5*\26\2\u013f\u0140\5,\27\2\u0140\u0142\3\2\2\2\u0141\u013e"+
		"\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144"+
		")\3\2\2\2\u0145\u0143\3\2\2\2\u0146\u015c\7\35\2\2\u0147\u015c\7\36\2"+
		"\2\u0148\u0149\7\24\2\2\u0149\u015c\7\37\2\2\u014a\u015c\7\37\2\2\u014b"+
		"\u015c\7 \2\2\u014c\u0150\7\24\2\2\u014d\u014e\7\24\2\2\u014e\u0151\7"+
		"\24\2\2\u014f\u0151\7\24\2\2\u0150\u014d\3\2\2\2\u0150\u014f\3\2\2\2\u0151"+
		"\u015c\3\2\2\2\u0152\u0157\7\23\2\2\u0153\u0154\7\23\2\2\u0154\u0158\7"+
		"\23\2\2\u0155\u0158\7\23\2\2\u0156\u0158\7 \2\2\u0157\u0153\3\2\2\2\u0157"+
		"\u0155\3\2\2\2\u0157\u0156\3\2\2\2\u0158\u015c\3\2\2\2\u0159\u015c\7!"+
		"\2\2\u015a\u015c\7\"\2\2\u015b\u0146\3\2\2\2\u015b\u0147\3\2\2\2\u015b"+
		"\u0148\3\2\2\2\u015b\u014a\3\2\2\2\u015b\u014b\3\2\2\2\u015b\u014c\3\2"+
		"\2\2\u015b\u0152\3\2\2\2\u015b\u0159\3\2\2\2\u015b\u015a\3\2\2\2\u015c"+
		"+\3\2\2\2\u015d\u0163\5\60\31\2\u015e\u015f\5.\30\2\u015f\u0160\5\60\31"+
		"\2\u0160\u0162\3\2\2\2\u0161\u015e\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161"+
		"\3\2\2\2\u0163\u0164\3\2\2\2\u0164-\3\2\2\2\u0165\u0163\3\2\2\2\u0166"+
		"\u0167\t\3\2\2\u0167/\3\2\2\2\u0168\u016e\5\64\33\2\u0169\u016a\5\62\32"+
		"\2\u016a\u016b\5\64\33\2\u016b\u016d\3\2\2\2\u016c\u0169\3\2\2\2\u016d"+
		"\u0170\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\61\3\2\2"+
		"\2\u0170\u016e\3\2\2\2\u0171\u0172\t\4\2\2\u0172\63\3\2\2\2\u0173\u0174"+
		"\5\66\34\2\u0174\u0175\5\64\33\2\u0175\u0178\3\2\2\2\u0176\u0178\58\35"+
		"\2\u0177\u0173\3\2\2\2\u0177\u0176\3\2\2\2\u0178\65\3\2\2\2\u0179\u017a"+
		"\t\5\2\2\u017a\67\3\2\2\2\u017b\u0180\5:\36\2\u017c\u017d\7*\2\2\u017d"+
		"\u017f\5\u008aF\2\u017e\u017c\3\2\2\2\u017f\u0182\3\2\2\2\u0180\u017e"+
		"\3\2\2\2\u0180\u0181\3\2\2\2\u01819\3\2\2\2\u0182\u0180\3\2\2\2\u0183"+
		"\u0185\5> \2\u0184\u0186\5<\37\2\u0185\u0184\3\2\2\2\u0185\u0186\3\2\2"+
		"\2\u0186;\3\2\2\2\u0187\u0188\t\6\2\2\u0188=\3\2\2\2\u0189\u01b3\5@!\2"+
		"\u018a\u018b\t\7\2\2\u018b\u018c\5l\67\2\u018c\u018d\5\24\13\2\u018d\u018e"+
		"\3\2\2\2\u018e\u018f\5\22\n\2\u018f\u01b2\3\2\2\2\u0190\u019c\t\b\2\2"+
		"\u0191\u0192\7\23\2\2\u0192\u0197\5\u0092J\2\u0193\u0194\7\13\2\2\u0194"+
		"\u0196\5\u0092J\2\u0195\u0193\3\2\2\2\u0196\u0199\3\2\2\2\u0197\u0195"+
		"\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u019a\3\2\2\2\u0199\u0197\3\2\2\2\u019a"+
		"\u019b\7\24\2\2\u019b\u019d\3\2\2\2\u019c\u0191\3\2\2\2\u019c\u019d\3"+
		"\2\2\2\u019d\u019e\3\2\2\2\u019e\u01ac\5n8\2\u019f\u01a9\7\n\2\2\u01a0"+
		"\u01aa\5N(\2\u01a1\u01a6\5\20\t\2\u01a2\u01a3\7\13\2\2\u01a3\u01a5\5\20"+
		"\t\2\u01a4\u01a2\3\2\2\2\u01a5\u01a8\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a6"+
		"\u01a7\3\2\2\2\u01a7\u01aa\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a9\u01a0\3\2"+
		"\2\2\u01a9\u01a1\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab"+
		"\u01ad\7\f\2\2\u01ac\u019f\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01af\3\2"+
		"\2\2\u01ae\u01b0\5J&\2\u01af\u01ae\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01b2"+
		"\3\2\2\2\u01b1\u018a\3\2\2\2\u01b1\u0190\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3"+
		"\u01b1\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4?\3\2\2\2\u01b5\u01b3\3\2\2\2"+
		"\u01b6\u01c6\5p9\2\u01b7\u01c6\5`\61\2\u01b8\u01c6\5T+\2\u01b9\u01c6\5"+
		"\u0082B\2\u01ba\u01c6\5j\66\2\u01bb\u01c6\5B\"\2\u01bc\u01c6\5R*\2\u01bd"+
		"\u01c6\5X-\2\u01be\u01c6\5Z.\2\u01bf\u01c6\5\\/\2\u01c0\u01c6\5^\60\2"+
		"\u01c1\u01c6\5|?\2\u01c2\u01c6\5~@\2\u01c3\u01c6\5\u0080A\2\u01c4\u01c6"+
		"\5P)\2\u01c5\u01b6\3\2\2\2\u01c5\u01b7\3\2\2\2\u01c5\u01b8\3\2\2\2\u01c5"+
		"\u01b9\3\2\2\2\u01c5\u01ba\3\2\2\2\u01c5\u01bb\3\2\2\2\u01c5\u01bc\3\2"+
		"\2\2\u01c5\u01bd\3\2\2\2\u01c5\u01be\3\2\2\2\u01c5\u01bf\3\2\2\2\u01c5"+
		"\u01c0\3\2\2\2\u01c5\u01c1\3\2\2\2\u01c5\u01c2\3\2\2\2\u01c5\u01c3\3\2"+
		"\2\2\u01c5\u01c4\3\2\2\2\u01c6A\3\2\2\2\u01c7\u01cf\5D#\2\u01c8\u01cf"+
		"\5J&\2\u01c9\u01cf\5r:\2\u01ca\u01cf\5v<\2\u01cb\u01cf\5t;\2\u01cc\u01cf"+
		"\5x=\2\u01cd\u01cf\5z>\2\u01ce\u01c7\3\2\2\2\u01ce\u01c8\3\2\2\2\u01ce"+
		"\u01c9\3\2\2\2\u01ce\u01ca\3\2\2\2\u01ce\u01cb\3\2\2\2\u01ce\u01cc\3\2"+
		"\2\2\u01ce\u01cd\3\2\2\2\u01cfC\3\2\2\2\u01d0\u01d3\5F$\2\u01d1\u01d3"+
		"\5H%\2\u01d2\u01d0\3\2\2\2\u01d2\u01d1\3\2\2\2\u01d3E\3\2\2\2\u01d4\u01d5"+
		"\7\60\2\2\u01d5\u01de\7\4\2\2\u01d6\u01db\5\20\t\2\u01d7\u01d8\7\13\2"+
		"\2\u01d8\u01da\5\20\t\2\u01d9\u01d7\3\2\2\2\u01da\u01dd\3\2\2\2\u01db"+
		"\u01d9\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01df\3\2\2\2\u01dd\u01db\3\2"+
		"\2\2\u01de\u01d6\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0"+
		"\u01e1\7\5\2\2\u01e1G\3\2\2\2\u01e2\u01e3\7\60\2\2\u01e3\u01ec\7\61\2"+
		"\2\u01e4\u01e9\5\20\t\2\u01e5\u01e6\7\13\2\2\u01e6\u01e8\5\20\t\2\u01e7"+
		"\u01e5\3\2\2\2\u01e8\u01eb\3\2\2\2\u01e9\u01e7\3\2\2\2\u01e9\u01ea\3\2"+
		"\2\2\u01ea\u01ed\3\2\2\2\u01eb\u01e9\3\2\2\2\u01ec\u01e4\3\2\2\2\u01ec"+
		"\u01ed\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01ef\7\62\2\2\u01efI\3\2\2\2"+
		"\u01f0\u01fc\7\61\2\2\u01f1\u01f6\5f\64\2\u01f2\u01f3\7\13\2\2\u01f3\u01f5"+
		"\5f\64\2\u01f4\u01f2\3\2\2\2\u01f5\u01f8\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f6"+
		"\u01f7\3\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f9\u01f1\3\2"+
		"\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01fd\7\63\2\2\u01fc"+
		"\u01f9\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01ff\5L"+
		"\'\2\u01ff\u0200\7\62\2\2\u0200K\3\2\2\2\u0201\u0203\5b\62\2\u0202\u0204"+
		"\7\64\2\2\u0203\u0202\3\2\2\2\u0203\u0204\3\2\2\2\u0204\u0206\3\2\2\2"+
		"\u0205\u0201\3\2\2\2\u0206\u0209\3\2\2\2\u0207\u0205\3\2\2\2\u0207\u0208"+
		"\3\2\2\2\u0208M\3\2\2\2\u0209\u0207\3\2\2\2\u020a\u020f\5f\64\2\u020b"+
		"\u020c\7\13\2\2\u020c\u020e\5f\64\2\u020d\u020b\3\2\2\2\u020e\u0211\3"+
		"\2\2\2\u020f\u020d\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u0213\3\2\2\2\u0211"+
		"\u020f\3\2\2\2\u0212\u020a\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0214\3\2"+
		"\2\2\u0214\u0215\7\63\2\2\u0215\u0216\3\2\2\2\u0216\u0217\5\20\t\2\u0217"+
		"O\3\2\2\2\u0218\u0219\7\n\2\2\u0219\u021a\5\20\t\2\u021a\u021b\7\f\2\2"+
		"\u021bQ\3\2\2\2\u021c\u021d\7\65\2\2\u021d\u021e\7\n\2\2\u021e\u021f\5"+
		"\20\t\2\u021f\u0220\7\f\2\2\u0220\u0223\5\20\t\2\u0221\u0222\7\66\2\2"+
		"\u0222\u0224\5\20\t\2\u0223\u0221\3\2\2\2\u0223\u0224\3\2\2\2\u0224S\3"+
		"\2\2\2\u0225\u0233\7\67\2\2\u0226\u0227\7\n\2\2\u0227\u0228\5f\64\2\u0228"+
		"\u0229\7\b\2\2\u0229\u022a\3\2\2\2\u022a\u022b\5\20\t\2\u022b\u022c\7"+
		"\f\2\2\u022c\u0234\3\2\2\2\u022d\u022e\5f\64\2\u022e\u022f\7\b\2\2\u022f"+
		"\u0231\3\2\2\2\u0230\u022d\3\2\2\2\u0230\u0231\3\2\2\2\u0231\u0232\3\2"+
		"\2\2\u0232\u0234\5\20\t\2\u0233\u0226\3\2\2\2\u0233\u0230\3\2\2\2\u0234"+
		"\u0235\3\2\2\2\u0235\u0239\7\4\2\2\u0236\u0238\5V,\2\u0237\u0236\3\2\2"+
		"\2\u0238\u023b\3\2\2\2\u0239\u0237\3\2\2\2\u0239\u023a\3\2\2\2\u023a\u023f"+
		"\3\2\2\2\u023b\u0239\3\2\2\2\u023c\u023d\78\2\2\u023d\u023e\7\b\2\2\u023e"+
		"\u0240\5\20\t\2\u023f\u023c\3\2\2\2\u023f\u0240\3\2\2\2\u0240\u0241\3"+
		"\2\2\2\u0241\u0242\7\5\2\2\u0242U\3\2\2\2\u0243\u0245\5\u008aF\2\u0244"+
		"\u0243\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0248\3\2\2\2\u0246\u0247\79"+
		"\2\2\u0247\u0249\5\20\t\2\u0248\u0246\3\2\2\2\u0248\u0249\3\2\2\2\u0249"+
		"\u024d\3\2\2\2\u024a\u024b\7\b\2\2\u024b\u024e\5\20\t\2\u024c\u024e\7"+
		"\13\2\2\u024d\u024a\3\2\2\2\u024d\u024c\3\2\2\2\u024eW\3\2\2\2\u024f\u0250"+
		"\7:\2\2\u0250\u0251\7\n\2\2\u0251\u0252\5f\64\2\u0252\u0253\7\b\2\2\u0253"+
		"\u0254\3\2\2\2\u0254\u0255\5\20\t\2\u0255\u0256\7\f\2\2\u0256\u0257\5"+
		"\20\t\2\u0257Y\3\2\2\2\u0258\u0259\7:\2\2\u0259\u0262\7\n\2\2\u025a\u025f"+
		"\5b\62\2\u025b\u025c\7\13\2\2\u025c\u025e\5b\62\2\u025d\u025b\3\2\2\2"+
		"\u025e\u0261\3\2\2\2\u025f\u025d\3\2\2\2\u025f\u0260\3\2\2\2\u0260\u0263"+
		"\3\2\2\2\u0261\u025f\3\2\2\2\u0262\u025a\3\2\2\2\u0262\u0263\3\2\2\2\u0263"+
		"\u0264\3\2\2\2\u0264\u0266\7\64\2\2\u0265\u0267\5\20\t\2\u0266\u0265\3"+
		"\2\2\2\u0266\u0267\3\2\2\2\u0267\u0268\3\2\2\2\u0268\u0271\7\64\2\2\u0269"+
		"\u026e\5\20\t\2\u026a\u026b\7\13\2\2\u026b\u026d\5\20\t\2\u026c\u026a"+
		"\3\2\2\2\u026d\u0270\3\2\2\2\u026e\u026c\3\2\2\2\u026e\u026f\3\2\2\2\u026f"+
		"\u0272\3\2\2\2\u0270\u026e\3\2\2\2\u0271\u0269\3\2\2\2\u0271\u0272\3\2"+
		"\2\2\u0272\u0273\3\2\2\2\u0273\u0274\7\f\2\2\u0274\u0275\5\20\t\2\u0275"+
		"[\3\2\2\2\u0276\u0277\7;\2\2\u0277\u0278\7\n\2\2\u0278\u0279\5\20\t\2"+
		"\u0279\u027a\7\f\2\2\u027a\u027b\5\20\t\2\u027b]\3\2\2\2\u027c\u027d\7"+
		"<\2\2\u027d\u027e\5\20\t\2\u027e\u027f\7;\2\2\u027f\u0280\7\n\2\2\u0280"+
		"\u0281\5\20\t\2\u0281\u0282\7\f\2\2\u0282_\3\2\2\2\u0283\u028a\7\4\2\2"+
		"\u0284\u0286\5b\62\2\u0285\u0287\7\64\2\2\u0286\u0285\3\2\2\2\u0286\u0287"+
		"\3\2\2\2\u0287\u0289\3\2\2\2\u0288\u0284\3\2\2\2\u0289\u028c\3\2\2\2\u028a"+
		"\u0288\3\2\2\2\u028a\u028b\3\2\2\2\u028b\u028d\3\2\2\2\u028c\u028a\3\2"+
		"\2\2\u028d\u028e\7\5\2\2\u028ea\3\2\2\2\u028f\u0292\5d\63\2\u0290\u0292"+
		"\5\20\t\2\u0291\u028f\3\2\2\2\u0291\u0290\3\2\2\2\u0292c\3\2\2\2\u0293"+
		"\u0298\t\t\2\2\u0294\u0295\5\u008aF\2\u0295\u0296\5\u00a0Q\2\u0296\u0299"+
		"\3\2\2\2\u0297\u0299\5\u00a0Q\2\u0298\u0294\3\2\2\2\u0298\u0297\3\2\2"+
		"\2\u0299\u029c\3\2\2\2\u029a\u029b\7\r\2\2\u029b\u029d\5\20\t\2\u029c"+
		"\u029a\3\2\2\2\u029c\u029d\3\2\2\2\u029de\3\2\2\2\u029e\u02a0\5\u008a"+
		"F\2\u029f\u029e\3\2\2\2\u029f\u02a0\3\2\2\2\u02a0\u02a1\3\2\2\2\u02a1"+
		"\u02a2\5\u00a0Q\2\u02a2g\3\2\2\2\u02a3\u02a4\5\u008aF\2\u02a4\u02a5\5"+
		"\u00a0Q\2\u02a5i\3\2\2\2\u02a6\u02a7\7\23\2\2\u02a7\u02ac\5\u0092J\2\u02a8"+
		"\u02a9\7\13\2\2\u02a9\u02ab\5\u0092J\2\u02aa\u02a8\3\2\2\2\u02ab\u02ae"+
		"\3\2\2\2\u02ac\u02aa\3\2\2\2\u02ac\u02ad\3\2\2\2\u02ad\u02af\3\2\2\2\u02ae"+
		"\u02ac\3\2\2\2\u02af\u02b0\7\24\2\2\u02b0\u02b2\3\2\2\2\u02b1\u02a6\3"+
		"\2\2\2\u02b1\u02b2\3\2\2\2\u02b2\u02b3\3\2\2\2\u02b3\u02c1\5n8\2\u02b4"+
		"\u02be\7\n\2\2\u02b5\u02bf\5N(\2\u02b6\u02bb\5\20\t\2\u02b7\u02b8\7\13"+
		"\2\2\u02b8\u02ba\5\20\t\2\u02b9\u02b7\3\2\2\2\u02ba\u02bd\3\2\2\2\u02bb"+
		"\u02b9\3\2\2\2\u02bb\u02bc\3\2\2\2\u02bc\u02bf\3\2\2\2\u02bd\u02bb\3\2"+
		"\2\2\u02be\u02b5\3\2\2\2\u02be\u02b6\3\2\2\2\u02be\u02bf\3\2\2\2\u02bf"+
		"\u02c0\3\2\2\2\u02c0\u02c2\7\f\2\2\u02c1\u02b4\3\2\2\2\u02c1\u02c2\3\2"+
		"\2\2\u02c2\u02c4\3\2\2\2\u02c3\u02c5\5J&\2\u02c4\u02c3\3\2\2\2\u02c4\u02c5"+
		"\3\2\2\2\u02c5k\3\2\2\2\u02c6\u02cc\5\u00a0Q\2\u02c7\u02cc\7\7\2\2\u02c8"+
		"\u02cc\7?\2\2\u02c9\u02cc\7@\2\2\u02ca\u02cc\7A\2\2\u02cb\u02c6\3\2\2"+
		"\2\u02cb\u02c7\3\2\2\2\u02cb\u02c8\3\2\2\2\u02cb\u02c9\3\2\2\2\u02cb\u02ca"+
		"\3\2\2\2\u02ccm\3\2\2\2\u02cd\u02d0\5l\67\2\u02ce\u02d0\7B\2\2\u02cf\u02cd"+
		"\3\2\2\2\u02cf\u02ce\3\2\2\2\u02d0o\3\2\2\2\u02d1\u02d2\7C\2\2\u02d2\u02de"+
		"\5\u0086D\2\u02d3\u02d4\7\23\2\2\u02d4\u02d9\5\u0092J\2\u02d5\u02d6\7"+
		"\13\2\2\u02d6\u02d8\5\u0092J\2\u02d7\u02d5\3\2\2\2\u02d8\u02db\3\2\2\2"+
		"\u02d9\u02d7\3\2\2\2\u02d9\u02da\3\2\2\2\u02da\u02dc\3\2\2\2\u02db\u02d9"+
		"\3\2\2\2\u02dc\u02dd\7\24\2\2\u02dd\u02df\3\2\2\2\u02de\u02d3\3\2\2\2"+
		"\u02de\u02df\3\2\2\2\u02df\u02ed\3\2\2\2\u02e0\u02ea\7\n\2\2\u02e1\u02eb"+
		"\5N(\2\u02e2\u02e7\5\20\t\2\u02e3\u02e4\7\13\2\2\u02e4\u02e6\5\20\t\2"+
		"\u02e5\u02e3\3\2\2\2\u02e6\u02e9\3\2\2\2\u02e7\u02e5\3\2\2\2\u02e7\u02e8"+
		"\3\2\2\2\u02e8\u02eb\3\2\2\2\u02e9\u02e7\3\2\2\2\u02ea\u02e1\3\2\2\2\u02ea"+
		"\u02e2\3\2\2\2\u02ea\u02eb\3\2\2\2\u02eb\u02ec\3\2\2\2\u02ec\u02ee\7\f"+
		"\2\2\u02ed\u02e0\3\2\2\2\u02ed\u02ee\3\2\2\2\u02ee\u02f0\3\2\2\2\u02ef"+
		"\u02f1\5J&\2\u02f0\u02ef\3\2\2\2\u02f0\u02f1\3\2\2\2\u02f1q\3\2\2\2\u02f2"+
		"\u02f3\t\n\2\2\u02f3s\3\2\2\2\u02f4\u02f5\7F\2\2\u02f5u\3\2\2\2\u02f6"+
		"\u02f7\5\u0088E\2\u02f7w\3\2\2\2\u02f8\u02f9\7T\2\2\u02f9y\3\2\2\2\u02fa"+
		"\u02fb\7G\2\2\u02fb\u02fc\7\n\2\2\u02fc\u0300\5\u0086D\2\u02fd\u02ff\5"+
		"\u008cG\2\u02fe\u02fd\3\2\2\2\u02ff\u0302\3\2\2\2\u0300\u02fe\3\2\2\2"+
		"\u0300\u0301\3\2\2\2\u0301\u0303\3\2\2\2\u0302\u0300\3\2\2\2\u0303\u0304"+
		"\7\f\2\2\u0304{\3\2\2\2\u0305\u0306\7H\2\2\u0306\u0307\5\20\t\2\u0307"+
		"}\3\2\2\2\u0308\u030a\7I\2\2\u0309\u030b\5\20\t\2\u030a\u0309\3\2\2\2"+
		"\u030a\u030b\3\2\2\2\u030b\177\3\2\2\2\u030c\u030d\7J\2\2\u030d\u0319"+
		"\5\20\t\2\u030e\u0310\5\u0084C\2\u030f\u030e\3\2\2\2\u0310\u0311\3\2\2"+
		"\2\u0311\u030f\3\2\2\2\u0311\u0312\3\2\2\2\u0312\u0315\3\2\2\2\u0313\u0314"+
		"\7K\2\2\u0314\u0316\5\20\t\2\u0315\u0313\3\2\2\2\u0315\u0316\3\2\2\2\u0316"+
		"\u031a\3\2\2\2\u0317\u0318\7K\2\2\u0318\u031a\5\20\t\2\u0319\u030f\3\2"+
		"\2\2\u0319\u0317\3\2\2\2\u031a\u0081\3\2\2\2\u031b\u031c\7L\2\2\u031c"+
		"\u031d\7\n\2\2\u031d\u031e\3\2\2\2\u031e\u031f\5\20\t\2\u031f\u0320\7"+
		"\f\2\2\u0320\u0321\5\20\t\2\u0321\u0083\3\2\2\2\u0322\u0323\7M\2\2\u0323"+
		"\u0324\7\n\2\2\u0324\u0325\5h\65\2\u0325\u0326\7\f\2\2\u0326\u0327\5\20"+
		"\t\2\u0327\u0085\3\2\2\2\u0328\u032d\5\u00a0Q\2\u0329\u032a\7-\2\2\u032a"+
		"\u032c\5\u00a0Q\2\u032b\u0329\3\2\2\2\u032c\u032f\3\2\2\2\u032d\u032b"+
		"\3\2\2\2\u032d\u032e\3\2\2\2\u032e\u0087\3\2\2\2\u032f\u032d\3\2\2\2\u0330"+
		"\u0337\7P\2\2\u0331\u0334\t\13\2\2\u0332\u0333\7-\2\2\u0333\u0335\t\13"+
		"\2\2\u0334\u0332\3\2\2\2\u0334\u0335\3\2\2\2\u0335\u0337\3\2\2\2\u0336"+
		"\u0330\3\2\2\2\u0336\u0331\3\2\2\2\u0337\u0089\3\2\2\2\u0338\u033c\5\u0090"+
		"I\2\u0339\u033b\5\u008cG\2\u033a\u0339\3\2\2\2\u033b\u033e\3\2\2\2\u033c"+
		"\u033a\3\2\2\2\u033c\u033d\3\2\2\2\u033d\u0341\3\2\2\2\u033e\u033c\3\2"+
		"\2\2\u033f\u0341\5\u008eH\2\u0340\u0338\3\2\2\2\u0340\u033f\3\2\2\2\u0341"+
		"\u008b\3\2\2\2\u0342\u0343\7\61\2\2\u0343\u0344\7\62\2\2\u0344\u008d\3"+
		"\2\2\2\u0345\u034e\7\n\2\2\u0346\u034b\5\u008aF\2\u0347\u0348\7\13\2\2"+
		"\u0348\u034a\5\u008aF\2\u0349\u0347\3\2\2\2\u034a\u034d\3\2\2\2\u034b"+
		"\u0349\3\2\2\2\u034b\u034c\3\2\2\2\u034c\u034f\3\2\2\2\u034d\u034b\3\2"+
		"\2\2\u034e\u0346\3\2\2\2\u034e\u034f\3\2\2\2\u034f\u0350\3\2\2\2\u0350"+
		"\u0352\7\f\2\2\u0351\u0345\3\2\2\2\u0351\u0352\3\2\2\2\u0352\u0353\3\2"+
		"\2\2\u0353\u0354\7 \2\2\u0354\u0355\5\u008aF\2\u0355\u008f\3\2\2\2\u0356"+
		"\u0375\5\u0086D\2\u0357\u0358\7\23\2\2\u0358\u035d\5\u0092J\2\u0359\u035a"+
		"\7\13\2\2\u035a\u035c\5\u0092J\2\u035b\u0359\3\2\2\2\u035c\u035f\3\2\2"+
		"\2\u035d\u035b\3\2\2\2\u035d\u035e\3\2\2\2\u035e\u0360\3\2\2\2\u035f\u035d"+
		"\3\2\2\2\u0360\u0372\7\24\2\2\u0361\u0362\7-\2\2\u0362\u036e\5\u00a0Q"+
		"\2\u0363\u0364\7\23\2\2\u0364\u0369\5\u0092J\2\u0365\u0366\7\13\2\2\u0366"+
		"\u0368\5\u0092J\2\u0367\u0365\3\2\2\2\u0368\u036b\3\2\2\2\u0369\u0367"+
		"\3\2\2\2\u0369\u036a\3\2\2\2\u036a\u036c\3\2\2\2\u036b\u0369\3\2\2\2\u036c"+
		"\u036d\7\24\2\2\u036d\u036f\3\2\2\2\u036e\u0363\3\2\2\2\u036e\u036f\3"+
		"\2\2\2\u036f\u0371\3\2\2\2\u0370\u0361\3\2\2\2\u0371\u0374\3\2\2\2\u0372"+
		"\u0370\3\2\2\2\u0372\u0373\3\2\2\2\u0373\u0376\3\2\2\2\u0374\u0372\3\2"+
		"\2\2\u0375\u0357\3\2\2\2\u0375\u0376\3\2\2\2\u0376\u0091\3\2\2\2\u0377"+
		"\u037a\5\u008aF\2\u0378\u037a\5\u0094K\2\u0379\u0377\3\2\2\2\u0379\u0378"+
		"\3\2\2\2\u037a\u0093\3\2\2\2\u037b\u038a\7N\2\2\u037c\u0380\5\u0096L\2"+
		"\u037d\u037f\5\u0098M\2\u037e\u037d\3\2\2\2\u037f\u0382\3\2\2\2\u0380"+
		"\u037e\3\2\2\2\u0380\u0381\3\2\2\2\u0381\u038b\3\2\2\2\u0382\u0380\3\2"+
		"\2\2\u0383\u0387\5\u009aN\2\u0384\u0386\5\u009cO\2\u0385\u0384\3\2\2\2"+
		"\u0386\u0389\3\2\2\2\u0387\u0385\3\2\2\2\u0387\u0388\3\2\2\2\u0388\u038b"+
		"\3\2\2\2\u0389\u0387\3\2\2\2\u038a\u037c\3\2\2\2\u038a\u0383\3\2\2\2\u038a"+
		"\u038b\3\2\2\2\u038b\u0095\3\2\2\2\u038c\u038d\7\7\2\2\u038d\u038e\5\u008a"+
		"F\2\u038e\u0097\3\2\2\2\u038f\u0390\7O\2\2\u0390\u0391\5\u008aF\2\u0391"+
		"\u0099\3\2\2\2\u0392\u0393\7B\2\2\u0393\u0394\5\u008aF\2\u0394\u009b\3"+
		"\2\2\2\u0395\u0396\7O\2\2\u0396\u0397\5\u008aF\2\u0397\u009d\3\2\2\2\u0398"+
		"\u0399\5\u0086D\2\u0399\u039a\7-\2\2\u039a\u039b\7%\2\2\u039b\u009f\3"+
		"\2\2\2\u039c\u039d\7S\2\2\u039d\u00a1\3\2\2\2\u039e\u03a0\5\u00a4S\2\u039f"+
		"\u039e\3\2\2\2\u03a0\u03a1\3\2\2\2\u03a1\u039f\3\2\2\2\u03a1\u03a2\3\2"+
		"\2\2\u03a2\u00a3\3\2\2\2\u03a3\u03af\7@\2\2\u03a4\u03a6\7?\2\2\u03a5\u03a7"+
		"\7A\2\2\u03a6\u03a5\3\2\2\2\u03a6\u03a7\3\2\2\2\u03a7\u03a8\3\2\2\2\u03a8"+
		"\u03ab\5\u00a6T\2\u03a9\u03ac\7%\2\2\u03aa\u03ac\5\u00a0Q\2\u03ab\u03a9"+
		"\3\2\2\2\u03ab\u03aa\3\2\2\2\u03ac\u03b0\3\2\2\2\u03ad\u03b0\5\u0086D"+
		"\2\u03ae\u03b0\5\u009eP\2\u03af\u03a4\3\2\2\2\u03af\u03ad\3\2\2\2\u03af"+
		"\u03ae\3\2\2\2\u03b0\u03b2\3\2\2\2\u03b1\u03b3\7\64\2\2\u03b2\u03b1\3"+
		"\2\2\2\u03b2\u03b3\3\2\2\2\u03b3\u00a5\3\2\2\2\u03b4\u03b5\5\u00a0Q\2"+
		"\u03b5\u03b6\7-\2\2\u03b6\u03b8\3\2\2\2\u03b7\u03b4\3\2\2\2\u03b8\u03b9"+
		"\3\2\2\2\u03b9\u03b7\3\2\2\2\u03b9\u03ba\3\2\2\2\u03ba\u00a7\3\2\2\2r"+
		"\u00a9\u00ae\u00b3\u00bb\u00c4\u00ca\u00d1\u00df\u00e2\u00e7\u00f5\u00f7"+
		"\u0105\u0108\u0110\u011b\u0126\u0131\u0133\u013b\u0143\u0150\u0157\u015b"+
		"\u0163\u016e\u0177\u0180\u0185\u0197\u019c\u01a6\u01a9\u01ac\u01af\u01b1"+
		"\u01b3\u01c5\u01ce\u01d2\u01db\u01de\u01e9\u01ec\u01f6\u01f9\u01fc\u0203"+
		"\u0207\u020f\u0212\u0223\u0230\u0233\u0239\u023f\u0244\u0248\u024d\u025f"+
		"\u0262\u0266\u026e\u0271\u0286\u028a\u0291\u0298\u029c\u029f\u02ac\u02b1"+
		"\u02bb\u02be\u02c1\u02c4\u02cb\u02cf\u02d9\u02de\u02e7\u02ea\u02ed\u02f0"+
		"\u0300\u030a\u0311\u0315\u0319\u032d\u0334\u0336\u033c\u0340\u034b\u034e"+
		"\u0351\u035d\u0369\u036e\u0372\u0375\u0379\u0380\u0387\u038a\u03a1\u03a6"+
		"\u03ab\u03af\u03b2\u03b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}