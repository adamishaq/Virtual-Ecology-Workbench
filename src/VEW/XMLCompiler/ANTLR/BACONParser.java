// $ANTLR 3.4 C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2011-11-26 18:53:12

package VEW.XMLCompiler.ANTLR;
import java.util.Map;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.debug.*;
import java.io.IOException;
import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class BACONParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACOS", "ALL", "AND", "ASIN", "ASSIGN", "ASSIGNLIST", "ATAN", "BEXPR", "CHANGE", "COLON", "COMMA", "COMMENT", "COS", "CREATE", "DENSITYAT", "DEPTHFORFI", "DEPTHFORVI", "DIGIT", "DIV", "DIVIDE", "ELSE", "EQUALS", "EXP", "EXPR", "FLOAT", "FULLIRRADAT", "GREATEREQUALS", "GREATERTHAN", "IF", "IGNORE", "INGEST", "INTEGRATE", "LBRACKET", "LESSEQUALS", "LESSTHAN", "LETTER", "LN", "LOGTEN", "LSQUARE", "MAX", "MIN", "MINUS", "MUL", "NEQUALS", "NEWLINE", "NONE", "NOT", "OR", "PCHANGE", "PLUS", "POW", "RBRACKET", "RELEASE", "RND", "RSQUARE", "RULE", "RULENAME", "RULES", "SALINITYAT", "SIN", "SOME", "SQRT", "TAN", "TEMPAT", "THEN", "UNKNOWN", "UPTAKE", "UVIRRADAT", "VAR", "VARHIST", "VAVERAGE", "VPRODUCT", "VSUM", "WITH"
    };

    public static final int EOF=-1;
    public static final int ABS=4;
    public static final int ACOS=5;
    public static final int ALL=6;
    public static final int AND=7;
    public static final int ASIN=8;
    public static final int ASSIGN=9;
    public static final int ASSIGNLIST=10;
    public static final int ATAN=11;
    public static final int BEXPR=12;
    public static final int CHANGE=13;
    public static final int COLON=14;
    public static final int COMMA=15;
    public static final int COMMENT=16;
    public static final int COS=17;
    public static final int CREATE=18;
    public static final int DENSITYAT=19;
    public static final int DEPTHFORFI=20;
    public static final int DEPTHFORVI=21;
    public static final int DIGIT=22;
    public static final int DIV=23;
    public static final int DIVIDE=24;
    public static final int ELSE=25;
    public static final int EQUALS=26;
    public static final int EXP=27;
    public static final int EXPR=28;
    public static final int FLOAT=29;
    public static final int FULLIRRADAT=30;
    public static final int GREATEREQUALS=31;
    public static final int GREATERTHAN=32;
    public static final int IF=33;
    public static final int IGNORE=34;
    public static final int INGEST=35;
    public static final int INTEGRATE=36;
    public static final int LBRACKET=37;
    public static final int LESSEQUALS=38;
    public static final int LESSTHAN=39;
    public static final int LETTER=40;
    public static final int LN=41;
    public static final int LOGTEN=42;
    public static final int LSQUARE=43;
    public static final int MAX=44;
    public static final int MIN=45;
    public static final int MINUS=46;
    public static final int MUL=47;
    public static final int NEQUALS=48;
    public static final int NEWLINE=49;
    public static final int NONE=50;
    public static final int NOT=51;
    public static final int OR=52;
    public static final int PCHANGE=53;
    public static final int PLUS=54;
    public static final int POW=55;
    public static final int RBRACKET=56;
    public static final int RELEASE=57;
    public static final int RND=58;
    public static final int RSQUARE=59;
    public static final int RULE=60;
    public static final int RULENAME=61;
    public static final int RULES=62;
    public static final int SALINITYAT=63;
    public static final int SIN=64;
    public static final int SOME=65;
    public static final int SQRT=66;
    public static final int TAN=67;
    public static final int TEMPAT=68;
    public static final int THEN=69;
    public static final int UNKNOWN=70;
    public static final int UPTAKE=71;
    public static final int UVIRRADAT=72;
    public static final int VAR=73;
    public static final int VARHIST=74;
    public static final int VAVERAGE=75;
    public static final int VPRODUCT=76;
    public static final int VSUM=77;
    public static final int WITH=78;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


public static final String[] ruleNames = new String[] {
    "invalidRule", "binPrim", "bExpr", "highPrecMathOp", "ruleName", "expr2", 
    "medPrecMathOp", "expr4", "comparators", "rule2", "synpred1_BACON", 
    "vBOp", "vOp", "rule", "expr3", "bExpr2", "lowPrecMathOp", "assign", 
    "lowPrecBoolOp", "rules", "assignList", "expr", "unaryPrimitives", "pair"
};

public static final boolean[] decisionCanBacktrack = new boolean[] {
    false, // invalid decision
    false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, true
};

 
    public int ruleLevel = 0;
    public int getRuleLevel() { return ruleLevel; }
    public void incRuleLevel() { ruleLevel++; }
    public void decRuleLevel() { ruleLevel--; }
    public BACONParser(TokenStream input) {
        this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
    }
    public BACONParser(TokenStream input, int port, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[23+1];
         

        DebugEventSocketProxy proxy =
            new DebugEventSocketProxy(this,port,adaptor);
        setDebugListener(proxy);
        setTokenStream(new DebugTokenStream(input,proxy));
        try {
            proxy.handshake();
        }
        catch (IOException ioe) {
            reportError(ioe);
        }
        TreeAdaptor adap = new CommonTreeAdaptor();
        setTreeAdaptor(adap);
        proxy.setTreeAdaptor(adap);
    }

public BACONParser(TokenStream input, DebugEventListener dbg) {
    super(input, dbg);
    this.state.ruleMemo = new HashMap[23+1];
     

     
    TreeAdaptor adap = new CommonTreeAdaptor();
    setTreeAdaptor(adap);


}

protected boolean evalPredicate(boolean result, String predicate) {
    dbg.semanticPredicate(result, predicate);
    return result;
}

protected DebugTreeAdaptor adaptor;
public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = new DebugTreeAdaptor(dbg,adaptor);


}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}

    public String[] getTokenNames() { return BACONParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g"; }


    private static Map<Integer, String> naturalNames = null; 
    //Parser stuff here
    protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
                  throws RecognitionException
    {
    	RecognitionException e = null;
    	/*
    	// if next token is what we are looking for then "delete" this token
    	if ( mismatchIsUnwantedToken(input, ttype) ) {
    		e = new UnwantedTokenException(ttype, input);
    		
    		System.err.println("recoverFromMismatchedToken deleting "+
    		((TokenStream)input).LT(1)+
    		" since "+((TokenStream)input).LT(2)+" is what we want");
    		
    		beginResync();
    		input.consume(); // simply delete extra token
    		endResync();
    		reportError(e);  // report after consuming so AW sees the token in the exception
    		// we want to return the token we're actually matching
    		Object matchedSymbol = getCurrentInputSymbol(input);
    		input.consume(); // move past ttype token as if all were ok
    		return matchedSymbol;
    	}
    	// can't recover with single token deletion, try insertion
    	if ( mismatchIsMissingToken(input, follow) ) {
    		Object inserted = getMissingSymbol(input, e, ttype, follow);
    		e = new MissingTokenException(ttype, input, inserted);
    		reportError(e);  // report after inserting so AW sees the token in the exception
    		return inserted;
    	}
    	 // even that didn't work; must throw the exception
    	 */
    	e = new MismatchedTokenException(ttype, input);
    	throw e;
    }

    public static Map<Integer, String> getNaturalNames() {
    	if (naturalNames == null) {
    		naturalNames = new HashMap<Integer, String>();
    		populateNaturalNames();
    	}
    	return naturalNames;
    }

    private static void populateNaturalNames() {
    	naturalNames.put(COLON, ":");
    	naturalNames.put(COMMA, ",");
    	naturalNames.put(RBRACKET, "(");
    	naturalNames.put(LBRACKET, ")");
    	naturalNames.put(EOF, "eof");
    	naturalNames.put(EQUALS, "'='");
    	naturalNames.put(FLOAT, "number");
    	naturalNames.put(ELSE, "else");
    	naturalNames.put(THEN, "then");
    	naturalNames.put(IF, "if");
    	naturalNames.put(WITH, "with");
    	naturalNames.put(LSQUARE, "[");
    	naturalNames.put(RSQUARE, "]");
    	naturalNames.put(VAR, "variable");	
    }

    public static String getTokenFromType(int ttype) {
    	Map<Integer, String> names = getNaturalNames();
    	return names.get(ttype);
    }



    public static class rules_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rules"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:202:1: rules : ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )? -> ^( RULES pair ( pair )* ) ;
    public final BACONParser.rules_return rules() throws RecognitionException {
        BACONParser.rules_return retval = new BACONParser.rules_return();
        retval.start = input.LT(1);

        int rules_StartIndex = input.index();

        Object root_0 = null;

        Token NEWLINE1=null;
        Token NEWLINE3=null;
        Token NEWLINE5=null;
        BACONParser.pair_return pair2 =null;

        BACONParser.pair_return pair4 =null;


        Object NEWLINE1_tree=null;
        Object NEWLINE3_tree=null;
        Object NEWLINE5_tree=null;
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleSubtreeStream stream_pair=new RewriteRuleSubtreeStream(adaptor,"rule pair");
        try { dbg.enterRule(getGrammarFileName(), "rules");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(202, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:2: ( ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )? -> ^( RULES pair ( pair )* ) )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:4: ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )?
            {
            dbg.location(203,4);
            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:4: ( NEWLINE )?
            int alt1=2;
            try { dbg.enterSubRule(1);
            try { dbg.enterDecision(1, decisionCanBacktrack[1]);

            int LA1_0 = input.LA(1);

            if ( (LA1_0==NEWLINE) ) {
                alt1=1;
            }
            } finally {dbg.exitDecision(1);}

            switch (alt1) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:5: NEWLINE
                    {
                    dbg.location(203,5);
                    NEWLINE1=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules905); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE1);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(1);}

            dbg.location(203,15);
            pushFollow(FOLLOW_pair_in_rules909);
            pair2=pair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pair.add(pair2.getTree());
            dbg.location(203,20);
            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:20: ( NEWLINE pair )*
            try { dbg.enterSubRule(2);

            loop2:
            do {
                int alt2=2;
                try { dbg.enterDecision(2, decisionCanBacktrack[2]);

                int LA2_0 = input.LA(1);

                if ( (LA2_0==NEWLINE) ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1==CHANGE||LA2_1==CREATE||LA2_1==DIVIDE||LA2_1==IF||LA2_1==INGEST||LA2_1==LBRACKET||LA2_1==PCHANGE||LA2_1==RELEASE||LA2_1==RULENAME||LA2_1==UPTAKE||LA2_1==VAR) ) {
                        alt2=1;
                    }


                }


                } finally {dbg.exitDecision(2);}

                switch (alt2) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:21: NEWLINE pair
            	    {
            	    dbg.location(203,21);
            	    NEWLINE3=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules912); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE3);

            	    dbg.location(203,29);
            	    pushFollow(FOLLOW_pair_in_rules914);
            	    pair4=pair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_pair.add(pair4.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);
            } finally {dbg.exitSubRule(2);}

            dbg.location(203,36);
            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:36: ( NEWLINE )?
            int alt3=2;
            try { dbg.enterSubRule(3);
            try { dbg.enterDecision(3, decisionCanBacktrack[3]);

            int LA3_0 = input.LA(1);

            if ( (LA3_0==NEWLINE) ) {
                alt3=1;
            }
            } finally {dbg.exitDecision(3);}

            switch (alt3) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:37: NEWLINE
                    {
                    dbg.location(203,37);
                    NEWLINE5=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules919); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE5);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(3);}


            // AST REWRITE
            // elements: pair, pair
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 203:47: -> ^( RULES pair ( pair )* )
            {
                dbg.location(203,50);
                // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:50: ^( RULES pair ( pair )* )
                {
                Object root_1 = (Object)adaptor.nil();
                dbg.location(203,52);
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(RULES, "RULES")
                , root_1);

                dbg.location(203,58);
                adaptor.addChild(root_1, stream_pair.nextTree());
                dbg.location(203,63);
                // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:63: ( pair )*
                while ( stream_pair.hasNext() ) {
                    dbg.location(203,63);
                    adaptor.addChild(root_1, stream_pair.nextTree());

                }
                stream_pair.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, rules_StartIndex); }

        }
        dbg.location(204, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "rules");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "rules"


    public static class pair_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pair"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:206:1: pair : ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule );
    public final BACONParser.pair_return pair() throws RecognitionException {
        BACONParser.pair_return retval = new BACONParser.pair_return();
        retval.start = input.LT(1);

        int pair_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.ruleName_return ruleName6 =null;

        BACONParser.rule2_return rule27 =null;

        BACONParser.rule_return rule8 =null;


        RewriteRuleSubtreeStream stream_ruleName=new RewriteRuleSubtreeStream(adaptor,"rule ruleName");
        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        try { dbg.enterRule(getGrammarFileName(), "pair");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(206, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:207:2: ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule )
            int alt4=2;
            try { dbg.enterDecision(4, decisionCanBacktrack[4]);

            switch ( input.LA(1) ) {
            case RULENAME:
                {
                alt4=1;
                }
                break;
            case VAR:
                {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==COLON) ) {
                    alt4=1;
                }
                else if ( (LA4_2==EQUALS) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    dbg.recognitionException(nvae);
                    throw nvae;

                }
                }
                break;
            case CHANGE:
            case CREATE:
            case DIVIDE:
            case IF:
            case INGEST:
            case LBRACKET:
            case PCHANGE:
            case RELEASE:
            case UPTAKE:
                {
                alt4=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }

            } finally {dbg.exitDecision(4);}

            switch (alt4) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:207:4: ruleName rule2
                    {
                    dbg.location(207,4);
                    pushFollow(FOLLOW_ruleName_in_pair945);
                    ruleName6=ruleName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleName.add(ruleName6.getTree());
                    dbg.location(207,13);
                    pushFollow(FOLLOW_rule2_in_pair947);
                    rule27=rule2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule2.add(rule27.getTree());

                    // AST REWRITE
                    // elements: rule2, ruleName
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 207:19: -> ^( RULE ruleName rule2 )
                    {
                        dbg.location(207,22);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:207:22: ^( RULE ruleName rule2 )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(207,24);
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(RULE, "RULE")
                        , root_1);

                        dbg.location(207,29);
                        adaptor.addChild(root_1, stream_ruleName.nextTree());
                        dbg.location(207,38);
                        adaptor.addChild(root_1, stream_rule2.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:208:4: rule
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(208,4);
                    pushFollow(FOLLOW_rule_in_pair962);
                    rule8=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule8.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, pair_StartIndex); }

        }
        dbg.location(209, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "pair");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "pair"


    public static class ruleName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ruleName"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:211:1: ruleName : ( RULENAME COLON ( NEWLINE )? -> RULENAME | VAR COLON ( NEWLINE )? -> VAR );
    public final BACONParser.ruleName_return ruleName() throws RecognitionException {
        BACONParser.ruleName_return retval = new BACONParser.ruleName_return();
        retval.start = input.LT(1);

        int ruleName_StartIndex = input.index();

        Object root_0 = null;

        Token RULENAME9=null;
        Token COLON10=null;
        Token NEWLINE11=null;
        Token VAR12=null;
        Token COLON13=null;
        Token NEWLINE14=null;

        Object RULENAME9_tree=null;
        Object COLON10_tree=null;
        Object NEWLINE11_tree=null;
        Object VAR12_tree=null;
        Object COLON13_tree=null;
        Object NEWLINE14_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_RULENAME=new RewriteRuleTokenStream(adaptor,"token RULENAME");

        try { dbg.enterRule(getGrammarFileName(), "ruleName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(211, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:212:2: ( RULENAME COLON ( NEWLINE )? -> RULENAME | VAR COLON ( NEWLINE )? -> VAR )
            int alt7=2;
            try { dbg.enterDecision(7, decisionCanBacktrack[7]);

            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULENAME) ) {
                alt7=1;
            }
            else if ( (LA7_0==VAR) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }
            } finally {dbg.exitDecision(7);}

            switch (alt7) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:212:4: RULENAME COLON ( NEWLINE )?
                    {
                    dbg.location(212,4);
                    RULENAME9=(Token)match(input,RULENAME,FOLLOW_RULENAME_in_ruleName974); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULENAME.add(RULENAME9);

                    dbg.location(212,13);
                    COLON10=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName976); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON10);

                    dbg.location(212,19);
                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:212:19: ( NEWLINE )?
                    int alt5=2;
                    try { dbg.enterSubRule(5);
                    try { dbg.enterDecision(5, decisionCanBacktrack[5]);

                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==NEWLINE) ) {
                        alt5=1;
                    }
                    } finally {dbg.exitDecision(5);}

                    switch (alt5) {
                        case 1 :
                            dbg.enterAlt(1);

                            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:212:20: NEWLINE
                            {
                            dbg.location(212,20);
                            NEWLINE11=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName979); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE11);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(5);}


                    // AST REWRITE
                    // elements: RULENAME
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 212:30: -> RULENAME
                    {
                        dbg.location(212,33);
                        adaptor.addChild(root_0, 
                        stream_RULENAME.nextNode()
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:213:4: VAR COLON ( NEWLINE )?
                    {
                    dbg.location(213,4);
                    VAR12=(Token)match(input,VAR,FOLLOW_VAR_in_ruleName990); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR12);

                    dbg.location(213,8);
                    COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName992); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON13);

                    dbg.location(213,14);
                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:213:14: ( NEWLINE )?
                    int alt6=2;
                    try { dbg.enterSubRule(6);
                    try { dbg.enterDecision(6, decisionCanBacktrack[6]);

                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==NEWLINE) ) {
                        alt6=1;
                    }
                    } finally {dbg.exitDecision(6);}

                    switch (alt6) {
                        case 1 :
                            dbg.enterAlt(1);

                            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:213:15: NEWLINE
                            {
                            dbg.location(213,15);
                            NEWLINE14=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName995); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE14);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(6);}


                    // AST REWRITE
                    // elements: VAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 213:25: -> VAR
                    {
                        dbg.location(213,28);
                        adaptor.addChild(root_0, 
                        stream_VAR.nextNode()
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, ruleName_StartIndex); }

        }
        dbg.location(214, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "ruleName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "ruleName"


    public static class rule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rule"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:216:1: rule : rule2 -> ^( RULE rule2 ) ;
    public final BACONParser.rule_return rule() throws RecognitionException {
        BACONParser.rule_return retval = new BACONParser.rule_return();
        retval.start = input.LT(1);

        int rule_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.rule2_return rule215 =null;


        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        try { dbg.enterRule(getGrammarFileName(), "rule");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(216, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:217:2: ( rule2 -> ^( RULE rule2 ) )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:217:4: rule2
            {
            dbg.location(217,4);
            pushFollow(FOLLOW_rule2_in_rule1012);
            rule215=rule2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rule2.add(rule215.getTree());

            // AST REWRITE
            // elements: rule2
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 217:10: -> ^( RULE rule2 )
            {
                dbg.location(217,13);
                // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:217:13: ^( RULE rule2 )
                {
                Object root_1 = (Object)adaptor.nil();
                dbg.location(217,15);
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(RULE, "RULE")
                , root_1);

                dbg.location(217,20);
                adaptor.addChild(root_1, stream_rule2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, rule_StartIndex); }

        }
        dbg.location(218, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "rule");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "rule"


    public static class rule2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rule2"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:220:1: rule2 : ( assign | IF bExpr ( NEWLINE )? THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( ( NEWLINE )? WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 );
    public final BACONParser.rule2_return rule2() throws RecognitionException {
        BACONParser.rule2_return retval = new BACONParser.rule2_return();
        retval.start = input.LT(1);

        int rule2_StartIndex = input.index();

        Object root_0 = null;

        Token IF17=null;
        Token NEWLINE19=null;
        Token THEN20=null;
        Token UPTAKE22=null;
        Token LBRACKET23=null;
        Token VAR24=null;
        Token COMMA25=null;
        Token RBRACKET27=null;
        Token RELEASE28=null;
        Token LBRACKET29=null;
        Token VAR30=null;
        Token COMMA31=null;
        Token RBRACKET33=null;
        Token INGEST34=null;
        Token LBRACKET35=null;
        Token VAR36=null;
        Token COMMA37=null;
        Token COMMA39=null;
        Token RBRACKET41=null;
        Token CHANGE42=null;
        Token LBRACKET43=null;
        Token VAR44=null;
        Token RBRACKET45=null;
        Token PCHANGE46=null;
        Token LBRACKET47=null;
        Token VAR48=null;
        Token COMMA49=null;
        Token RBRACKET51=null;
        Token DIVIDE52=null;
        Token LBRACKET53=null;
        Token RBRACKET55=null;
        Token CREATE56=null;
        Token LBRACKET57=null;
        Token VAR58=null;
        Token COMMA59=null;
        Token RBRACKET61=null;
        Token NEWLINE62=null;
        Token WITH63=null;
        Token LSQUARE64=null;
        Token RSQUARE66=null;
        Token LBRACKET67=null;
        Token RBRACKET69=null;
        BACONParser.assign_return assign16 =null;

        BACONParser.bExpr_return bExpr18 =null;

        BACONParser.rule_return rule21 =null;

        BACONParser.expr_return expr26 =null;

        BACONParser.expr_return expr32 =null;

        BACONParser.expr_return expr38 =null;

        BACONParser.expr_return expr40 =null;

        BACONParser.expr_return expr50 =null;

        BACONParser.expr_return expr54 =null;

        BACONParser.expr_return expr60 =null;

        BACONParser.assignList_return assignList65 =null;

        BACONParser.rule2_return rule268 =null;


        Object IF17_tree=null;
        Object NEWLINE19_tree=null;
        Object THEN20_tree=null;
        Object UPTAKE22_tree=null;
        Object LBRACKET23_tree=null;
        Object VAR24_tree=null;
        Object COMMA25_tree=null;
        Object RBRACKET27_tree=null;
        Object RELEASE28_tree=null;
        Object LBRACKET29_tree=null;
        Object VAR30_tree=null;
        Object COMMA31_tree=null;
        Object RBRACKET33_tree=null;
        Object INGEST34_tree=null;
        Object LBRACKET35_tree=null;
        Object VAR36_tree=null;
        Object COMMA37_tree=null;
        Object COMMA39_tree=null;
        Object RBRACKET41_tree=null;
        Object CHANGE42_tree=null;
        Object LBRACKET43_tree=null;
        Object VAR44_tree=null;
        Object RBRACKET45_tree=null;
        Object PCHANGE46_tree=null;
        Object LBRACKET47_tree=null;
        Object VAR48_tree=null;
        Object COMMA49_tree=null;
        Object RBRACKET51_tree=null;
        Object DIVIDE52_tree=null;
        Object LBRACKET53_tree=null;
        Object RBRACKET55_tree=null;
        Object CREATE56_tree=null;
        Object LBRACKET57_tree=null;
        Object VAR58_tree=null;
        Object COMMA59_tree=null;
        Object RBRACKET61_tree=null;
        Object NEWLINE62_tree=null;
        Object WITH63_tree=null;
        Object LSQUARE64_tree=null;
        Object RSQUARE66_tree=null;
        Object LBRACKET67_tree=null;
        Object RBRACKET69_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_CREATE=new RewriteRuleTokenStream(adaptor,"token CREATE");
        RewriteRuleTokenStream stream_PCHANGE=new RewriteRuleTokenStream(adaptor,"token PCHANGE");
        RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
        RewriteRuleTokenStream stream_THEN=new RewriteRuleTokenStream(adaptor,"token THEN");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_UPTAKE=new RewriteRuleTokenStream(adaptor,"token UPTAKE");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_RSQUARE=new RewriteRuleTokenStream(adaptor,"token RSQUARE");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_RELEASE=new RewriteRuleTokenStream(adaptor,"token RELEASE");
        RewriteRuleTokenStream stream_INGEST=new RewriteRuleTokenStream(adaptor,"token INGEST");
        RewriteRuleTokenStream stream_CHANGE=new RewriteRuleTokenStream(adaptor,"token CHANGE");
        RewriteRuleTokenStream stream_DIVIDE=new RewriteRuleTokenStream(adaptor,"token DIVIDE");
        RewriteRuleTokenStream stream_WITH=new RewriteRuleTokenStream(adaptor,"token WITH");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_rule=new RewriteRuleSubtreeStream(adaptor,"rule rule");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        RewriteRuleSubtreeStream stream_assignList=new RewriteRuleSubtreeStream(adaptor,"rule assignList");
        try { dbg.enterRule(getGrammarFileName(), "rule2");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(220, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:221:2: ( assign | IF bExpr ( NEWLINE )? THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( ( NEWLINE )? WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 )
            int alt11=10;
            try { dbg.enterDecision(11, decisionCanBacktrack[11]);

            switch ( input.LA(1) ) {
            case VAR:
                {
                alt11=1;
                }
                break;
            case IF:
                {
                alt11=2;
                }
                break;
            case UPTAKE:
                {
                alt11=3;
                }
                break;
            case RELEASE:
                {
                alt11=4;
                }
                break;
            case INGEST:
                {
                alt11=5;
                }
                break;
            case CHANGE:
                {
                alt11=6;
                }
                break;
            case PCHANGE:
                {
                alt11=7;
                }
                break;
            case DIVIDE:
                {
                alt11=8;
                }
                break;
            case CREATE:
                {
                alt11=9;
                }
                break;
            case LBRACKET:
                {
                alt11=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }

            } finally {dbg.exitDecision(11);}

            switch (alt11) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:221:4: assign
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(221,4);
                    pushFollow(FOLLOW_assign_in_rule21031);
                    assign16=assign();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign16.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:222:4: IF bExpr ( NEWLINE )? THEN rule
                    {
                    dbg.location(222,4);
                    IF17=(Token)match(input,IF,FOLLOW_IF_in_rule21036); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF17);

                    dbg.location(222,7);
                    pushFollow(FOLLOW_bExpr_in_rule21038);
                    bExpr18=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr18.getTree());
                    dbg.location(222,13);
                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:222:13: ( NEWLINE )?
                    int alt8=2;
                    try { dbg.enterSubRule(8);
                    try { dbg.enterDecision(8, decisionCanBacktrack[8]);

                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==NEWLINE) ) {
                        alt8=1;
                    }
                    } finally {dbg.exitDecision(8);}

                    switch (alt8) {
                        case 1 :
                            dbg.enterAlt(1);

                            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:222:14: NEWLINE
                            {
                            dbg.location(222,14);
                            NEWLINE19=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rule21041); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE19);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(8);}

                    dbg.location(222,24);
                    THEN20=(Token)match(input,THEN,FOLLOW_THEN_in_rule21045); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN20);

                    dbg.location(222,29);
                    pushFollow(FOLLOW_rule_in_rule21047);
                    rule21=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule21.getTree());

                    // AST REWRITE
                    // elements: IF, bExpr, rule
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 222:34: -> ^( IF bExpr rule )
                    {
                        dbg.location(222,37);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:222:37: ^( IF bExpr rule )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(222,39);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_IF.nextNode()
                        , root_1);

                        dbg.location(222,42);
                        adaptor.addChild(root_1, stream_bExpr.nextTree());
                        dbg.location(222,48);
                        adaptor.addChild(root_1, stream_rule.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:223:4: UPTAKE LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(223,4);
                    UPTAKE22=(Token)match(input,UPTAKE,FOLLOW_UPTAKE_in_rule21062); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UPTAKE.add(UPTAKE22);

                    dbg.location(223,11);
                    LBRACKET23=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21064); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET23);

                    dbg.location(223,20);
                    VAR24=(Token)match(input,VAR,FOLLOW_VAR_in_rule21066); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR24);

                    dbg.location(223,24);
                    COMMA25=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21068); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA25);

                    dbg.location(223,30);
                    pushFollow(FOLLOW_expr_in_rule21070);
                    expr26=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr26.getTree());
                    dbg.location(223,35);
                    RBRACKET27=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21072); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET27);


                    // AST REWRITE
                    // elements: UPTAKE, expr, VAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 223:44: -> ^( UPTAKE VAR expr )
                    {
                        dbg.location(223,47);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:223:47: ^( UPTAKE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(223,49);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_UPTAKE.nextNode()
                        , root_1);

                        dbg.location(223,56);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(223,60);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:4: RELEASE LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(224,4);
                    RELEASE28=(Token)match(input,RELEASE,FOLLOW_RELEASE_in_rule21087); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RELEASE.add(RELEASE28);

                    dbg.location(224,12);
                    LBRACKET29=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21089); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET29);

                    dbg.location(224,21);
                    VAR30=(Token)match(input,VAR,FOLLOW_VAR_in_rule21091); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR30);

                    dbg.location(224,25);
                    COMMA31=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21093); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA31);

                    dbg.location(224,31);
                    pushFollow(FOLLOW_expr_in_rule21095);
                    expr32=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr32.getTree());
                    dbg.location(224,36);
                    RBRACKET33=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21097); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET33);


                    // AST REWRITE
                    // elements: expr, RELEASE, VAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 224:45: -> ^( RELEASE VAR expr )
                    {
                        dbg.location(224,48);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:48: ^( RELEASE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(224,50);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_RELEASE.nextNode()
                        , root_1);

                        dbg.location(224,58);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(224,62);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:225:4: INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET
                    {
                    dbg.location(225,4);
                    INGEST34=(Token)match(input,INGEST,FOLLOW_INGEST_in_rule21112); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INGEST.add(INGEST34);

                    dbg.location(225,11);
                    LBRACKET35=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21114); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET35);

                    dbg.location(225,20);
                    VAR36=(Token)match(input,VAR,FOLLOW_VAR_in_rule21116); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR36);

                    dbg.location(225,24);
                    COMMA37=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21118); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA37);

                    dbg.location(225,30);
                    pushFollow(FOLLOW_expr_in_rule21120);
                    expr38=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr38.getTree());
                    dbg.location(225,35);
                    COMMA39=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21122); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA39);

                    dbg.location(225,41);
                    pushFollow(FOLLOW_expr_in_rule21124);
                    expr40=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr40.getTree());
                    dbg.location(225,46);
                    RBRACKET41=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21126); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET41);


                    // AST REWRITE
                    // elements: expr, INGEST, VAR, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 225:55: -> ^( INGEST VAR expr expr )
                    {
                        dbg.location(225,58);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:225:58: ^( INGEST VAR expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(225,60);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_INGEST.nextNode()
                        , root_1);

                        dbg.location(225,67);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(225,71);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(225,76);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:226:4: CHANGE LBRACKET VAR RBRACKET
                    {
                    dbg.location(226,4);
                    CHANGE42=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21143); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE42);

                    dbg.location(226,11);
                    LBRACKET43=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21145); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET43);

                    dbg.location(226,20);
                    VAR44=(Token)match(input,VAR,FOLLOW_VAR_in_rule21147); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR44);

                    dbg.location(226,24);
                    RBRACKET45=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21149); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET45);


                    // AST REWRITE
                    // elements: CHANGE, VAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 226:33: -> ^( CHANGE VAR )
                    {
                        dbg.location(226,36);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:226:36: ^( CHANGE VAR )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(226,38);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CHANGE.nextNode()
                        , root_1);

                        dbg.location(226,45);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:227:4: PCHANGE LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(227,4);
                    PCHANGE46=(Token)match(input,PCHANGE,FOLLOW_PCHANGE_in_rule21162); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_PCHANGE.add(PCHANGE46);

                    dbg.location(227,12);
                    LBRACKET47=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21164); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET47);

                    dbg.location(227,21);
                    VAR48=(Token)match(input,VAR,FOLLOW_VAR_in_rule21166); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR48);

                    dbg.location(227,25);
                    COMMA49=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21168); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA49);

                    dbg.location(227,31);
                    pushFollow(FOLLOW_expr_in_rule21170);
                    expr50=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr50.getTree());
                    dbg.location(227,36);
                    RBRACKET51=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21172); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET51);


                    // AST REWRITE
                    // elements: VAR, expr, PCHANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 227:45: -> ^( PCHANGE VAR expr )
                    {
                        dbg.location(227,48);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:227:48: ^( PCHANGE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(227,50);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_PCHANGE.nextNode()
                        , root_1);

                        dbg.location(227,58);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(227,62);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:4: DIVIDE LBRACKET expr RBRACKET
                    {
                    dbg.location(228,4);
                    DIVIDE52=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_rule21187); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIVIDE.add(DIVIDE52);

                    dbg.location(228,11);
                    LBRACKET53=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21189); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET53);

                    dbg.location(228,20);
                    pushFollow(FOLLOW_expr_in_rule21191);
                    expr54=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr54.getTree());
                    dbg.location(228,25);
                    RBRACKET55=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21193); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET55);


                    // AST REWRITE
                    // elements: expr, DIVIDE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 228:34: -> ^( DIVIDE expr )
                    {
                        dbg.location(228,37);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:37: ^( DIVIDE expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(228,39);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_DIVIDE.nextNode()
                        , root_1);

                        dbg.location(228,46);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 9 :
                    dbg.enterAlt(9);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:229:4: CREATE LBRACKET VAR COMMA expr RBRACKET ( ( NEWLINE )? WITH LSQUARE assignList RSQUARE )?
                    {
                    dbg.location(229,4);
                    CREATE56=(Token)match(input,CREATE,FOLLOW_CREATE_in_rule21206); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CREATE.add(CREATE56);

                    dbg.location(229,11);
                    LBRACKET57=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21208); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET57);

                    dbg.location(229,20);
                    VAR58=(Token)match(input,VAR,FOLLOW_VAR_in_rule21210); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR58);

                    dbg.location(229,24);
                    COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21212); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA59);

                    dbg.location(229,30);
                    pushFollow(FOLLOW_expr_in_rule21214);
                    expr60=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr60.getTree());
                    dbg.location(229,35);
                    RBRACKET61=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21216); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET61);

                    dbg.location(230,3);
                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:230:3: ( ( NEWLINE )? WITH LSQUARE assignList RSQUARE )?
                    int alt10=2;
                    try { dbg.enterSubRule(10);
                    try { dbg.enterDecision(10, decisionCanBacktrack[10]);

                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==NEWLINE) ) {
                        int LA10_1 = input.LA(2);

                        if ( (LA10_1==WITH) ) {
                            alt10=1;
                        }
                    }
                    else if ( (LA10_0==WITH) ) {
                        alt10=1;
                    }
                    } finally {dbg.exitDecision(10);}

                    switch (alt10) {
                        case 1 :
                            dbg.enterAlt(1);

                            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:230:4: ( NEWLINE )? WITH LSQUARE assignList RSQUARE
                            {
                            dbg.location(230,4);
                            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:230:4: ( NEWLINE )?
                            int alt9=2;
                            try { dbg.enterSubRule(9);
                            try { dbg.enterDecision(9, decisionCanBacktrack[9]);

                            int LA9_0 = input.LA(1);

                            if ( (LA9_0==NEWLINE) ) {
                                alt9=1;
                            }
                            } finally {dbg.exitDecision(9);}

                            switch (alt9) {
                                case 1 :
                                    dbg.enterAlt(1);

                                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:230:5: NEWLINE
                                    {
                                    dbg.location(230,5);
                                    NEWLINE62=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rule21222); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE62);


                                    }
                                    break;

                            }
                            } finally {dbg.exitSubRule(9);}

                            dbg.location(230,15);
                            WITH63=(Token)match(input,WITH,FOLLOW_WITH_in_rule21226); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_WITH.add(WITH63);

                            dbg.location(230,20);
                            LSQUARE64=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_rule21228); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE64);

                            dbg.location(230,28);
                            pushFollow(FOLLOW_assignList_in_rule21230);
                            assignList65=assignList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignList.add(assignList65.getTree());
                            dbg.location(230,39);
                            RSQUARE66=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_rule21232); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE66);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(10);}


                    // AST REWRITE
                    // elements: VAR, CREATE, assignList, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 230:49: -> ^( CREATE VAR expr ( assignList )? )
                    {
                        dbg.location(230,52);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:230:52: ^( CREATE VAR expr ( assignList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(230,54);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CREATE.nextNode()
                        , root_1);

                        dbg.location(230,61);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(230,65);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(230,70);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:230:70: ( assignList )?
                        if ( stream_assignList.hasNext() ) {
                            dbg.location(230,71);
                            adaptor.addChild(root_1, stream_assignList.nextTree());

                        }
                        stream_assignList.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 10 :
                    dbg.enterAlt(10);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:231:4: LBRACKET rule2 RBRACKET
                    {
                    dbg.location(231,4);
                    LBRACKET67=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21254); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET67);

                    dbg.location(231,13);
                    pushFollow(FOLLOW_rule2_in_rule21256);
                    rule268=rule2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule2.add(rule268.getTree());
                    dbg.location(231,19);
                    RBRACKET69=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21258); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET69);


                    // AST REWRITE
                    // elements: rule2
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 231:28: -> rule2
                    {
                        dbg.location(231,31);
                        adaptor.addChild(root_0, stream_rule2.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, rule2_StartIndex); }

        }
        dbg.location(232, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "rule2");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "rule2"


    public static class assign_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assign"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:234:1: assign : VAR EQUALS expr -> ^( ASSIGN VAR expr ) ;
    public final BACONParser.assign_return assign() throws RecognitionException {
        BACONParser.assign_return retval = new BACONParser.assign_return();
        retval.start = input.LT(1);

        int assign_StartIndex = input.index();

        Object root_0 = null;

        Token VAR70=null;
        Token EQUALS71=null;
        BACONParser.expr_return expr72 =null;


        Object VAR70_tree=null;
        Object EQUALS71_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try { dbg.enterRule(getGrammarFileName(), "assign");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(234, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:235:2: ( VAR EQUALS expr -> ^( ASSIGN VAR expr ) )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:235:4: VAR EQUALS expr
            {
            dbg.location(235,4);
            VAR70=(Token)match(input,VAR,FOLLOW_VAR_in_assign1273); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR70);

            dbg.location(235,8);
            EQUALS71=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assign1275); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS71);

            dbg.location(235,15);
            pushFollow(FOLLOW_expr_in_assign1277);
            expr72=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr72.getTree());

            // AST REWRITE
            // elements: expr, VAR
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 235:20: -> ^( ASSIGN VAR expr )
            {
                dbg.location(235,23);
                // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:235:23: ^( ASSIGN VAR expr )
                {
                Object root_1 = (Object)adaptor.nil();
                dbg.location(235,25);
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGN, "ASSIGN")
                , root_1);

                dbg.location(235,32);
                adaptor.addChild(root_1, 
                stream_VAR.nextNode()
                );
                dbg.location(235,36);
                adaptor.addChild(root_1, stream_expr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, assign_StartIndex); }

        }
        dbg.location(236, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "assign");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "assign"


    public static class assignList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignList"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:238:1: assignList : assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) ;
    public final BACONParser.assignList_return assignList() throws RecognitionException {
        BACONParser.assignList_return retval = new BACONParser.assignList_return();
        retval.start = input.LT(1);

        int assignList_StartIndex = input.index();

        Object root_0 = null;

        Token COMMA74=null;
        BACONParser.assign_return assign73 =null;

        BACONParser.assign_return assign75 =null;


        Object COMMA74_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_assign=new RewriteRuleSubtreeStream(adaptor,"rule assign");
        try { dbg.enterRule(getGrammarFileName(), "assignList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(238, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:239:2: ( assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:239:4: assign ( COMMA assign )*
            {
            dbg.location(239,4);
            pushFollow(FOLLOW_assign_in_assignList1298);
            assign73=assign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assign.add(assign73.getTree());
            dbg.location(239,11);
            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:239:11: ( COMMA assign )*
            try { dbg.enterSubRule(12);

            loop12:
            do {
                int alt12=2;
                try { dbg.enterDecision(12, decisionCanBacktrack[12]);

                int LA12_0 = input.LA(1);

                if ( (LA12_0==COMMA) ) {
                    alt12=1;
                }


                } finally {dbg.exitDecision(12);}

                switch (alt12) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:239:12: COMMA assign
            	    {
            	    dbg.location(239,12);
            	    COMMA74=(Token)match(input,COMMA,FOLLOW_COMMA_in_assignList1301); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA74);

            	    dbg.location(239,18);
            	    pushFollow(FOLLOW_assign_in_assignList1303);
            	    assign75=assign();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_assign.add(assign75.getTree());

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);
            } finally {dbg.exitSubRule(12);}


            // AST REWRITE
            // elements: assign, assign
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 239:27: -> ^( ASSIGNLIST assign ( assign )* )
            {
                dbg.location(239,30);
                // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:239:30: ^( ASSIGNLIST assign ( assign )* )
                {
                Object root_1 = (Object)adaptor.nil();
                dbg.location(239,32);
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGNLIST, "ASSIGNLIST")
                , root_1);

                dbg.location(239,43);
                adaptor.addChild(root_1, stream_assign.nextTree());
                dbg.location(239,50);
                // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:239:50: ( assign )*
                while ( stream_assign.hasNext() ) {
                    dbg.location(239,51);
                    adaptor.addChild(root_1, stream_assign.nextTree());

                }
                stream_assign.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, assignList_StartIndex); }

        }
        dbg.location(240, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "assignList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "assignList"


    public static class expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:242:1: expr : expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* ;
    public final BACONParser.expr_return expr() throws RecognitionException {
        BACONParser.expr_return retval = new BACONParser.expr_return();
        retval.start = input.LT(1);

        int expr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr2_return expr276 =null;

        BACONParser.lowPrecMathOp_return lowPrecMathOp77 =null;

        BACONParser.expr2_return expr278 =null;



        try { dbg.enterRule(getGrammarFileName(), "expr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(242, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:243:2: ( expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:243:4: expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(243,4);
            pushFollow(FOLLOW_expr2_in_expr1331);
            expr276=expr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr276.getTree());
            dbg.location(243,10);
            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:243:10: ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            try { dbg.enterSubRule(13);

            loop13:
            do {
                int alt13=2;
                try { dbg.enterDecision(13, decisionCanBacktrack[13]);

                int LA13_0 = input.LA(1);

                if ( (LA13_0==MINUS||LA13_0==PLUS) ) {
                    alt13=1;
                }


                } finally {dbg.exitDecision(13);}

                switch (alt13) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:243:38: lowPrecMathOp ^ expr2
            	    {
            	    dbg.location(243,51);
            	    pushFollow(FOLLOW_lowPrecMathOp_in_expr1345);
            	    lowPrecMathOp77=lowPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecMathOp77.getTree(), root_0);
            	    dbg.location(243,53);
            	    pushFollow(FOLLOW_expr2_in_expr1348);
            	    expr278=expr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr278.getTree());

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);
            } finally {dbg.exitSubRule(13);}


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, expr_StartIndex); }

        }
        dbg.location(244, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "expr"


    public static class expr2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr2"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:246:1: expr2 : expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* ;
    public final BACONParser.expr2_return expr2() throws RecognitionException {
        BACONParser.expr2_return retval = new BACONParser.expr2_return();
        retval.start = input.LT(1);

        int expr2_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr3_return expr379 =null;

        BACONParser.medPrecMathOp_return medPrecMathOp80 =null;

        BACONParser.expr3_return expr381 =null;



        try { dbg.enterRule(getGrammarFileName(), "expr2");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(246, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:247:2: ( expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:247:4: expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(247,4);
            pushFollow(FOLLOW_expr3_in_expr21362);
            expr379=expr3();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr379.getTree());
            dbg.location(247,10);
            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:247:10: ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            try { dbg.enterSubRule(14);

            loop14:
            do {
                int alt14=2;
                try { dbg.enterDecision(14, decisionCanBacktrack[14]);

                int LA14_0 = input.LA(1);

                if ( (LA14_0==DIV||LA14_0==MUL) ) {
                    alt14=1;
                }


                } finally {dbg.exitDecision(14);}

                switch (alt14) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:247:38: medPrecMathOp ^ expr3
            	    {
            	    dbg.location(247,51);
            	    pushFollow(FOLLOW_medPrecMathOp_in_expr21376);
            	    medPrecMathOp80=medPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(medPrecMathOp80.getTree(), root_0);
            	    dbg.location(247,54);
            	    pushFollow(FOLLOW_expr3_in_expr21380);
            	    expr381=expr3();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr381.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);
            } finally {dbg.exitSubRule(14);}


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, expr2_StartIndex); }

        }
        dbg.location(248, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expr2");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "expr2"


    public static class expr3_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr3"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:250:1: expr3 : expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )* ;
    public final BACONParser.expr3_return expr3() throws RecognitionException {
        BACONParser.expr3_return retval = new BACONParser.expr3_return();
        retval.start = input.LT(1);

        int expr3_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr4_return expr482 =null;

        BACONParser.highPrecMathOp_return highPrecMathOp83 =null;

        BACONParser.expr4_return expr484 =null;



        try { dbg.enterRule(getGrammarFileName(), "expr3");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(250, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:251:2: ( expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )* )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:251:4: expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(251,4);
            pushFollow(FOLLOW_expr4_in_expr31394);
            expr482=expr4();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr482.getTree());
            dbg.location(251,10);
            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:251:10: ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            try { dbg.enterSubRule(15);

            loop15:
            do {
                int alt15=2;
                try { dbg.enterDecision(15, decisionCanBacktrack[15]);

                int LA15_0 = input.LA(1);

                if ( (LA15_0==POW) ) {
                    alt15=1;
                }


                } finally {dbg.exitDecision(15);}

                switch (alt15) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:251:38: highPrecMathOp ^ expr4
            	    {
            	    dbg.location(251,52);
            	    pushFollow(FOLLOW_highPrecMathOp_in_expr31408);
            	    highPrecMathOp83=highPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(highPrecMathOp83.getTree(), root_0);
            	    dbg.location(251,54);
            	    pushFollow(FOLLOW_expr4_in_expr31411);
            	    expr484=expr4();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr484.getTree());

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);
            } finally {dbg.exitSubRule(15);}


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, expr3_StartIndex); }

        }
        dbg.location(252, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expr3");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "expr3"


    public static class expr4_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr4"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:255:1: expr4 : ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | FLOAT | VAR | IF bExpr ( NEWLINE )? THEN expr ( NEWLINE )? ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) );
    public final BACONParser.expr4_return expr4() throws RecognitionException {
        BACONParser.expr4_return retval = new BACONParser.expr4_return();
        retval.start = input.LT(1);

        int expr4_StartIndex = input.index();

        Object root_0 = null;

        Token LBRACKET85=null;
        Token RBRACKET87=null;
        Token LBRACKET89=null;
        Token RBRACKET91=null;
        Token FLOAT92=null;
        Token VAR93=null;
        Token IF94=null;
        Token NEWLINE96=null;
        Token THEN97=null;
        Token NEWLINE99=null;
        Token ELSE100=null;
        Token LBRACKET103=null;
        Token COMMA105=null;
        Token RBRACKET107=null;
        Token VARHIST108=null;
        Token LBRACKET109=null;
        Token VAR110=null;
        Token COMMA111=null;
        Token RBRACKET113=null;
        Token LBRACKET115=null;
        Token RBRACKET117=null;
        BACONParser.expr_return expr86 =null;

        BACONParser.unaryPrimitives_return unaryPrimitives88 =null;

        BACONParser.expr_return expr90 =null;

        BACONParser.bExpr_return bExpr95 =null;

        BACONParser.expr_return expr98 =null;

        BACONParser.expr_return expr101 =null;

        BACONParser.binPrim_return binPrim102 =null;

        BACONParser.expr_return expr104 =null;

        BACONParser.expr_return expr106 =null;

        BACONParser.expr_return expr112 =null;

        BACONParser.vOp_return vOp114 =null;

        BACONParser.expr_return expr116 =null;


        Object LBRACKET85_tree=null;
        Object RBRACKET87_tree=null;
        Object LBRACKET89_tree=null;
        Object RBRACKET91_tree=null;
        Object FLOAT92_tree=null;
        Object VAR93_tree=null;
        Object IF94_tree=null;
        Object NEWLINE96_tree=null;
        Object THEN97_tree=null;
        Object NEWLINE99_tree=null;
        Object ELSE100_tree=null;
        Object LBRACKET103_tree=null;
        Object COMMA105_tree=null;
        Object RBRACKET107_tree=null;
        Object VARHIST108_tree=null;
        Object LBRACKET109_tree=null;
        Object VAR110_tree=null;
        Object COMMA111_tree=null;
        Object RBRACKET113_tree=null;
        Object LBRACKET115_tree=null;
        Object RBRACKET117_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_THEN=new RewriteRuleTokenStream(adaptor,"token THEN");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_VARHIST=new RewriteRuleTokenStream(adaptor,"token VARHIST");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleTokenStream stream_ELSE=new RewriteRuleTokenStream(adaptor,"token ELSE");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_unaryPrimitives=new RewriteRuleSubtreeStream(adaptor,"rule unaryPrimitives");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_vOp=new RewriteRuleSubtreeStream(adaptor,"rule vOp");
        RewriteRuleSubtreeStream stream_binPrim=new RewriteRuleSubtreeStream(adaptor,"rule binPrim");
        try { dbg.enterRule(getGrammarFileName(), "expr4");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(255, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:256:3: ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | FLOAT | VAR | IF bExpr ( NEWLINE )? THEN expr ( NEWLINE )? ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) )
            int alt18=8;
            try { dbg.enterDecision(18, decisionCanBacktrack[18]);

            switch ( input.LA(1) ) {
            case LBRACKET:
                {
                alt18=1;
                }
                break;
            case ABS:
            case ACOS:
            case ASIN:
            case ATAN:
            case COS:
            case DENSITYAT:
            case DEPTHFORFI:
            case DEPTHFORVI:
            case EXP:
            case FULLIRRADAT:
            case INTEGRATE:
            case LN:
            case LOGTEN:
            case RND:
            case SALINITYAT:
            case SIN:
            case SQRT:
            case TAN:
            case TEMPAT:
            case UVIRRADAT:
                {
                alt18=2;
                }
                break;
            case FLOAT:
                {
                alt18=3;
                }
                break;
            case VAR:
                {
                alt18=4;
                }
                break;
            case IF:
                {
                alt18=5;
                }
                break;
            case MAX:
            case MIN:
                {
                alt18=6;
                }
                break;
            case VARHIST:
                {
                alt18=7;
                }
                break;
            case VAVERAGE:
            case VPRODUCT:
            case VSUM:
                {
                alt18=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }

            } finally {dbg.exitDecision(18);}

            switch (alt18) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:256:5: LBRACKET expr RBRACKET
                    {
                    dbg.location(256,5);
                    LBRACKET85=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41427); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET85);

                    dbg.location(256,14);
                    pushFollow(FOLLOW_expr_in_expr41429);
                    expr86=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr86.getTree());
                    dbg.location(256,19);
                    RBRACKET87=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41431); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET87);


                    // AST REWRITE
                    // elements: expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 256:28: -> expr
                    {
                        dbg.location(256,31);
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:4: unaryPrimitives LBRACKET expr RBRACKET
                    {
                    dbg.location(257,4);
                    pushFollow(FOLLOW_unaryPrimitives_in_expr41440);
                    unaryPrimitives88=unaryPrimitives();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryPrimitives.add(unaryPrimitives88.getTree());
                    dbg.location(257,20);
                    LBRACKET89=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41442); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET89);

                    dbg.location(257,29);
                    pushFollow(FOLLOW_expr_in_expr41444);
                    expr90=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr90.getTree());
                    dbg.location(257,34);
                    RBRACKET91=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41446); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET91);


                    // AST REWRITE
                    // elements: expr, unaryPrimitives
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 257:43: -> ^( unaryPrimitives expr )
                    {
                        dbg.location(257,46);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:46: ^( unaryPrimitives expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(257,48);
                        root_1 = (Object)adaptor.becomeRoot(stream_unaryPrimitives.nextNode(), root_1);

                        dbg.location(257,64);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:258:4: FLOAT
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(258,4);
                    FLOAT92=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_expr41459); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT92_tree = 
                    (Object)adaptor.create(FLOAT92)
                    ;
                    adaptor.addChild(root_0, FLOAT92_tree);
                    }

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:259:4: VAR
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(259,4);
                    VAR93=(Token)match(input,VAR,FOLLOW_VAR_in_expr41464); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR93_tree = 
                    (Object)adaptor.create(VAR93)
                    ;
                    adaptor.addChild(root_0, VAR93_tree);
                    }

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:260:4: IF bExpr ( NEWLINE )? THEN expr ( NEWLINE )? ELSE expr
                    {
                    dbg.location(260,4);
                    IF94=(Token)match(input,IF,FOLLOW_IF_in_expr41469); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF94);

                    dbg.location(260,7);
                    pushFollow(FOLLOW_bExpr_in_expr41471);
                    bExpr95=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr95.getTree());
                    dbg.location(260,13);
                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:260:13: ( NEWLINE )?
                    int alt16=2;
                    try { dbg.enterSubRule(16);
                    try { dbg.enterDecision(16, decisionCanBacktrack[16]);

                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==NEWLINE) ) {
                        alt16=1;
                    }
                    } finally {dbg.exitDecision(16);}

                    switch (alt16) {
                        case 1 :
                            dbg.enterAlt(1);

                            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:260:14: NEWLINE
                            {
                            dbg.location(260,14);
                            NEWLINE96=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_expr41474); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE96);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(16);}

                    dbg.location(260,24);
                    THEN97=(Token)match(input,THEN,FOLLOW_THEN_in_expr41478); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN97);

                    dbg.location(260,29);
                    pushFollow(FOLLOW_expr_in_expr41480);
                    expr98=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr98.getTree());
                    dbg.location(260,34);
                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:260:34: ( NEWLINE )?
                    int alt17=2;
                    try { dbg.enterSubRule(17);
                    try { dbg.enterDecision(17, decisionCanBacktrack[17]);

                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==NEWLINE) ) {
                        alt17=1;
                    }
                    } finally {dbg.exitDecision(17);}

                    switch (alt17) {
                        case 1 :
                            dbg.enterAlt(1);

                            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:260:35: NEWLINE
                            {
                            dbg.location(260,35);
                            NEWLINE99=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_expr41483); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE99);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(17);}

                    dbg.location(260,45);
                    ELSE100=(Token)match(input,ELSE,FOLLOW_ELSE_in_expr41487); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELSE.add(ELSE100);

                    dbg.location(260,50);
                    pushFollow(FOLLOW_expr_in_expr41489);
                    expr101=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr101.getTree());

                    // AST REWRITE
                    // elements: expr, expr, IF, bExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 260:55: -> ^( IF bExpr expr expr )
                    {
                        dbg.location(260,58);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:260:58: ^( IF bExpr expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(260,60);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_IF.nextNode()
                        , root_1);

                        dbg.location(260,63);
                        adaptor.addChild(root_1, stream_bExpr.nextTree());
                        dbg.location(260,69);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(260,74);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:261:4: binPrim LBRACKET expr COMMA expr RBRACKET
                    {
                    dbg.location(261,4);
                    pushFollow(FOLLOW_binPrim_in_expr41506);
                    binPrim102=binPrim();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binPrim.add(binPrim102.getTree());
                    dbg.location(261,12);
                    LBRACKET103=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41508); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET103);

                    dbg.location(261,21);
                    pushFollow(FOLLOW_expr_in_expr41510);
                    expr104=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr104.getTree());
                    dbg.location(261,26);
                    COMMA105=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41512); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA105);

                    dbg.location(261,32);
                    pushFollow(FOLLOW_expr_in_expr41514);
                    expr106=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr106.getTree());
                    dbg.location(261,37);
                    RBRACKET107=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41516); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET107);


                    // AST REWRITE
                    // elements: expr, binPrim, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 261:46: -> ^( binPrim expr expr )
                    {
                        dbg.location(261,49);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:261:49: ^( binPrim expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(261,51);
                        root_1 = (Object)adaptor.becomeRoot(stream_binPrim.nextNode(), root_1);

                        dbg.location(261,59);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(261,64);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:262:4: VARHIST LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(262,4);
                    VARHIST108=(Token)match(input,VARHIST,FOLLOW_VARHIST_in_expr41531); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VARHIST.add(VARHIST108);

                    dbg.location(262,12);
                    LBRACKET109=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41533); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET109);

                    dbg.location(262,21);
                    VAR110=(Token)match(input,VAR,FOLLOW_VAR_in_expr41535); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR110);

                    dbg.location(262,25);
                    COMMA111=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41537); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA111);

                    dbg.location(262,31);
                    pushFollow(FOLLOW_expr_in_expr41539);
                    expr112=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr112.getTree());
                    dbg.location(262,36);
                    RBRACKET113=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41541); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET113);


                    // AST REWRITE
                    // elements: VAR, expr, VARHIST
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 262:45: -> ^( VARHIST VAR expr )
                    {
                        dbg.location(262,48);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:262:48: ^( VARHIST VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(262,50);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_VARHIST.nextNode()
                        , root_1);

                        dbg.location(262,58);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(262,62);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:263:4: vOp LBRACKET expr RBRACKET
                    {
                    dbg.location(263,4);
                    pushFollow(FOLLOW_vOp_in_expr41556);
                    vOp114=vOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vOp.add(vOp114.getTree());
                    dbg.location(263,8);
                    LBRACKET115=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41558); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET115);

                    dbg.location(263,17);
                    pushFollow(FOLLOW_expr_in_expr41560);
                    expr116=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr116.getTree());
                    dbg.location(263,22);
                    RBRACKET117=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41562); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET117);


                    // AST REWRITE
                    // elements: expr, vOp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 263:31: -> ^( vOp expr )
                    {
                        dbg.location(263,34);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:263:34: ^( vOp expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(263,36);
                        root_1 = (Object)adaptor.becomeRoot(stream_vOp.nextNode(), root_1);

                        dbg.location(263,40);
                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, expr4_StartIndex); }

        }
        dbg.location(264, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expr4");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "expr4"


    public static class bExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bExpr"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:1: bExpr : bExpr2 ( lowPrecBoolOp ^ bExpr2 )* ;
    public final BACONParser.bExpr_return bExpr() throws RecognitionException {
        BACONParser.bExpr_return retval = new BACONParser.bExpr_return();
        retval.start = input.LT(1);

        int bExpr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.bExpr2_return bExpr2118 =null;

        BACONParser.lowPrecBoolOp_return lowPrecBoolOp119 =null;

        BACONParser.bExpr2_return bExpr2120 =null;



        try { dbg.enterRule(getGrammarFileName(), "bExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(267, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:268:2: ( bExpr2 ( lowPrecBoolOp ^ bExpr2 )* )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:268:4: bExpr2 ( lowPrecBoolOp ^ bExpr2 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(268,4);
            pushFollow(FOLLOW_bExpr2_in_bExpr1582);
            bExpr2118=bExpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2118.getTree());
            dbg.location(268,11);
            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:268:11: ( lowPrecBoolOp ^ bExpr2 )*
            try { dbg.enterSubRule(19);

            loop19:
            do {
                int alt19=2;
                try { dbg.enterDecision(19, decisionCanBacktrack[19]);

                int LA19_0 = input.LA(1);

                if ( (LA19_0==AND||LA19_0==OR) ) {
                    alt19=1;
                }


                } finally {dbg.exitDecision(19);}

                switch (alt19) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:268:12: lowPrecBoolOp ^ bExpr2
            	    {
            	    dbg.location(268,25);
            	    pushFollow(FOLLOW_lowPrecBoolOp_in_bExpr1585);
            	    lowPrecBoolOp119=lowPrecBoolOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecBoolOp119.getTree(), root_0);
            	    dbg.location(268,27);
            	    pushFollow(FOLLOW_bExpr2_in_bExpr1588);
            	    bExpr2120=bExpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2120.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);
            } finally {dbg.exitSubRule(19);}


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, bExpr_StartIndex); }

        }
        dbg.location(269, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "bExpr");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "bExpr"


    public static class bExpr2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bExpr2"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:271:1: bExpr2 : ( ( expr comparators )=> expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) );
    public final BACONParser.bExpr2_return bExpr2() throws RecognitionException {
        BACONParser.bExpr2_return retval = new BACONParser.bExpr2_return();
        retval.start = input.LT(1);

        int bExpr2_StartIndex = input.index();

        Object root_0 = null;

        Token NOT124=null;
        Token LBRACKET125=null;
        Token RBRACKET127=null;
        Token LBRACKET128=null;
        Token RBRACKET130=null;
        Token LBRACKET132=null;
        Token RBRACKET134=null;
        BACONParser.expr_return expr121 =null;

        BACONParser.comparators_return comparators122 =null;

        BACONParser.expr_return expr123 =null;

        BACONParser.bExpr_return bExpr126 =null;

        BACONParser.bExpr_return bExpr129 =null;

        BACONParser.vBOp_return vBOp131 =null;

        BACONParser.bExpr_return bExpr133 =null;


        Object NOT124_tree=null;
        Object LBRACKET125_tree=null;
        Object RBRACKET127_tree=null;
        Object LBRACKET128_tree=null;
        Object RBRACKET130_tree=null;
        Object LBRACKET132_tree=null;
        Object RBRACKET134_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_vBOp=new RewriteRuleSubtreeStream(adaptor,"rule vBOp");
        try { dbg.enterRule(getGrammarFileName(), "bExpr2");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(271, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:272:2: ( ( expr comparators )=> expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) )
            int alt20=4;
            try { dbg.enterDecision(20, decisionCanBacktrack[20]);

            int LA20_0 = input.LA(1);

            if ( (LA20_0==LBRACKET) ) {
                int LA20_1 = input.LA(2);

                if ( (synpred1_BACON()) ) {
                    alt20=1;
                }
                else if ( (true) ) {
                    alt20=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;

                }
            }
            else if ( ((LA20_0 >= ABS && LA20_0 <= ACOS)||LA20_0==ASIN||LA20_0==ATAN||LA20_0==COS||(LA20_0 >= DENSITYAT && LA20_0 <= DEPTHFORVI)||LA20_0==EXP||LA20_0==FULLIRRADAT||LA20_0==INTEGRATE||(LA20_0 >= LN && LA20_0 <= LOGTEN)||LA20_0==RND||(LA20_0 >= SALINITYAT && LA20_0 <= SIN)||(LA20_0 >= SQRT && LA20_0 <= TEMPAT)||LA20_0==UVIRRADAT) && (synpred1_BACON())) {
                alt20=1;
            }
            else if ( (LA20_0==FLOAT) && (synpred1_BACON())) {
                alt20=1;
            }
            else if ( (LA20_0==VAR) && (synpred1_BACON())) {
                alt20=1;
            }
            else if ( (LA20_0==IF) && (synpred1_BACON())) {
                alt20=1;
            }
            else if ( ((LA20_0 >= MAX && LA20_0 <= MIN)) && (synpred1_BACON())) {
                alt20=1;
            }
            else if ( (LA20_0==VARHIST) && (synpred1_BACON())) {
                alt20=1;
            }
            else if ( ((LA20_0 >= VAVERAGE && LA20_0 <= VSUM)) && (synpred1_BACON())) {
                alt20=1;
            }
            else if ( (LA20_0==NOT) ) {
                alt20=2;
            }
            else if ( (LA20_0==ALL||LA20_0==NONE||LA20_0==SOME) ) {
                alt20=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }
            } finally {dbg.exitDecision(20);}

            switch (alt20) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:272:4: ( expr comparators )=> expr comparators ^ expr
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(272,27);
                    pushFollow(FOLLOW_expr_in_bExpr21612);
                    expr121=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr121.getTree());
                    dbg.location(272,43);
                    pushFollow(FOLLOW_comparators_in_bExpr21614);
                    comparators122=comparators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(comparators122.getTree(), root_0);
                    dbg.location(272,45);
                    pushFollow(FOLLOW_expr_in_bExpr21617);
                    expr123=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr123.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:273:4: NOT LBRACKET bExpr RBRACKET
                    {
                    dbg.location(273,4);
                    NOT124=(Token)match(input,NOT,FOLLOW_NOT_in_bExpr21622); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT124);

                    dbg.location(273,8);
                    LBRACKET125=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21624); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET125);

                    dbg.location(273,17);
                    pushFollow(FOLLOW_bExpr_in_bExpr21626);
                    bExpr126=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr126.getTree());
                    dbg.location(273,23);
                    RBRACKET127=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21628); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET127);


                    // AST REWRITE
                    // elements: bExpr, NOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 273:32: -> ^( NOT bExpr )
                    {
                        dbg.location(273,35);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:273:35: ^( NOT bExpr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(273,37);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_NOT.nextNode()
                        , root_1);

                        dbg.location(273,41);
                        adaptor.addChild(root_1, stream_bExpr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:274:4: LBRACKET bExpr RBRACKET
                    {
                    dbg.location(274,4);
                    LBRACKET128=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21641); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET128);

                    dbg.location(274,13);
                    pushFollow(FOLLOW_bExpr_in_bExpr21643);
                    bExpr129=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr129.getTree());
                    dbg.location(274,19);
                    RBRACKET130=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21645); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET130);


                    // AST REWRITE
                    // elements: bExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 274:28: -> bExpr
                    {
                        dbg.location(274,31);
                        adaptor.addChild(root_0, stream_bExpr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:4: vBOp LBRACKET bExpr RBRACKET
                    {
                    dbg.location(275,4);
                    pushFollow(FOLLOW_vBOp_in_bExpr21654);
                    vBOp131=vBOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vBOp.add(vBOp131.getTree());
                    dbg.location(275,9);
                    LBRACKET132=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21656); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET132);

                    dbg.location(275,18);
                    pushFollow(FOLLOW_bExpr_in_bExpr21658);
                    bExpr133=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr133.getTree());
                    dbg.location(275,24);
                    RBRACKET134=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21660); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET134);


                    // AST REWRITE
                    // elements: bExpr, vBOp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 275:33: -> ^( vBOp bExpr )
                    {
                        dbg.location(275,36);
                        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:36: ^( vBOp bExpr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(275,38);
                        root_1 = (Object)adaptor.becomeRoot(stream_vBOp.nextNode(), root_1);

                        dbg.location(275,43);
                        adaptor.addChild(root_1, stream_bExpr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, bExpr2_StartIndex); }

        }
        dbg.location(276, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "bExpr2");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "bExpr2"


    public static class unaryPrimitives_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryPrimitives"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:281:1: unaryPrimitives : ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE );
    public final BACONParser.unaryPrimitives_return unaryPrimitives() throws RecognitionException {
        BACONParser.unaryPrimitives_return retval = new BACONParser.unaryPrimitives_return();
        retval.start = input.LT(1);

        int unaryPrimitives_StartIndex = input.index();

        Object root_0 = null;

        Token set135=null;

        Object set135_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "unaryPrimitives");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(281, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:282:2: ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(282,2);
            set135=(Token)input.LT(1);

            if ( (input.LA(1) >= ABS && input.LA(1) <= ACOS)||input.LA(1)==ASIN||input.LA(1)==ATAN||input.LA(1)==COS||(input.LA(1) >= DENSITYAT && input.LA(1) <= DEPTHFORVI)||input.LA(1)==EXP||input.LA(1)==FULLIRRADAT||input.LA(1)==INTEGRATE||(input.LA(1) >= LN && input.LA(1) <= LOGTEN)||input.LA(1)==RND||(input.LA(1) >= SALINITYAT && input.LA(1) <= SIN)||(input.LA(1) >= SQRT && input.LA(1) <= TEMPAT)||input.LA(1)==UVIRRADAT ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set135)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, unaryPrimitives_StartIndex); }

        }
        dbg.location(302, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "unaryPrimitives");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "unaryPrimitives"


    public static class lowPrecMathOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lowPrecMathOp"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:304:1: lowPrecMathOp : ( PLUS | MINUS );
    public final BACONParser.lowPrecMathOp_return lowPrecMathOp() throws RecognitionException {
        BACONParser.lowPrecMathOp_return retval = new BACONParser.lowPrecMathOp_return();
        retval.start = input.LT(1);

        int lowPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set136=null;

        Object set136_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "lowPrecMathOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(304, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:305:2: ( PLUS | MINUS )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(305,2);
            set136=(Token)input.LT(1);

            if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set136)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, lowPrecMathOp_StartIndex); }

        }
        dbg.location(307, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "lowPrecMathOp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "lowPrecMathOp"


    public static class medPrecMathOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "medPrecMathOp"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:309:1: medPrecMathOp : ( MUL | DIV );
    public final BACONParser.medPrecMathOp_return medPrecMathOp() throws RecognitionException {
        BACONParser.medPrecMathOp_return retval = new BACONParser.medPrecMathOp_return();
        retval.start = input.LT(1);

        int medPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set137=null;

        Object set137_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "medPrecMathOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(309, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:310:2: ( MUL | DIV )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(310,2);
            set137=(Token)input.LT(1);

            if ( input.LA(1)==DIV||input.LA(1)==MUL ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set137)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, medPrecMathOp_StartIndex); }

        }
        dbg.location(312, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "medPrecMathOp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "medPrecMathOp"


    public static class highPrecMathOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "highPrecMathOp"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:314:1: highPrecMathOp : POW ;
    public final BACONParser.highPrecMathOp_return highPrecMathOp() throws RecognitionException {
        BACONParser.highPrecMathOp_return retval = new BACONParser.highPrecMathOp_return();
        retval.start = input.LT(1);

        int highPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token POW138=null;

        Object POW138_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "highPrecMathOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(314, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:315:2: ( POW )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:315:4: POW
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(315,4);
            POW138=(Token)match(input,POW,FOLLOW_POW_in_highPrecMathOp1825); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            POW138_tree = 
            (Object)adaptor.create(POW138)
            ;
            adaptor.addChild(root_0, POW138_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 17, highPrecMathOp_StartIndex); }

        }
        dbg.location(316, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "highPrecMathOp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "highPrecMathOp"


    public static class binPrim_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "binPrim"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:318:1: binPrim : ( MIN | MAX );
    public final BACONParser.binPrim_return binPrim() throws RecognitionException {
        BACONParser.binPrim_return retval = new BACONParser.binPrim_return();
        retval.start = input.LT(1);

        int binPrim_StartIndex = input.index();

        Object root_0 = null;

        Token set139=null;

        Object set139_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "binPrim");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(318, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:319:2: ( MIN | MAX )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(319,2);
            set139=(Token)input.LT(1);

            if ( (input.LA(1) >= MAX && input.LA(1) <= MIN) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set139)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 18, binPrim_StartIndex); }

        }
        dbg.location(321, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "binPrim");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "binPrim"


    public static class lowPrecBoolOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lowPrecBoolOp"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:323:1: lowPrecBoolOp : ( AND | OR );
    public final BACONParser.lowPrecBoolOp_return lowPrecBoolOp() throws RecognitionException {
        BACONParser.lowPrecBoolOp_return retval = new BACONParser.lowPrecBoolOp_return();
        retval.start = input.LT(1);

        int lowPrecBoolOp_StartIndex = input.index();

        Object root_0 = null;

        Token set140=null;

        Object set140_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "lowPrecBoolOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(323, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:324:2: ( AND | OR )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(324,2);
            set140=(Token)input.LT(1);

            if ( input.LA(1)==AND||input.LA(1)==OR ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set140)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 19, lowPrecBoolOp_StartIndex); }

        }
        dbg.location(326, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "lowPrecBoolOp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "lowPrecBoolOp"


    public static class comparators_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comparators"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:328:1: comparators : ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS );
    public final BACONParser.comparators_return comparators() throws RecognitionException {
        BACONParser.comparators_return retval = new BACONParser.comparators_return();
        retval.start = input.LT(1);

        int comparators_StartIndex = input.index();

        Object root_0 = null;

        Token set141=null;

        Object set141_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "comparators");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(328, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:329:2: ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(329,2);
            set141=(Token)input.LT(1);

            if ( input.LA(1)==EQUALS||(input.LA(1) >= GREATEREQUALS && input.LA(1) <= GREATERTHAN)||(input.LA(1) >= LESSEQUALS && input.LA(1) <= LESSTHAN)||input.LA(1)==NEQUALS ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set141)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 20, comparators_StartIndex); }

        }
        dbg.location(335, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "comparators");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "comparators"


    public static class vOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "vOp"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:337:1: vOp : ( VSUM | VPRODUCT | VAVERAGE );
    public final BACONParser.vOp_return vOp() throws RecognitionException {
        BACONParser.vOp_return retval = new BACONParser.vOp_return();
        retval.start = input.LT(1);

        int vOp_StartIndex = input.index();

        Object root_0 = null;

        Token set142=null;

        Object set142_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "vOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(337, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:338:2: ( VSUM | VPRODUCT | VAVERAGE )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(338,2);
            set142=(Token)input.LT(1);

            if ( (input.LA(1) >= VAVERAGE && input.LA(1) <= VSUM) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set142)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 21, vOp_StartIndex); }

        }
        dbg.location(341, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "vOp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "vOp"


    public static class vBOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "vBOp"
    // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:343:1: vBOp : ( ALL | SOME | NONE );
    public final BACONParser.vBOp_return vBOp() throws RecognitionException {
        BACONParser.vBOp_return retval = new BACONParser.vBOp_return();
        retval.start = input.LT(1);

        int vBOp_StartIndex = input.index();

        Object root_0 = null;

        Token set143=null;

        Object set143_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "vBOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(343, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:344:2: ( ALL | SOME | NONE )
            dbg.enterAlt(1);

            // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(344,2);
            set143=(Token)input.LT(1);

            if ( input.LA(1)==ALL||input.LA(1)==NONE||input.LA(1)==SOME ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set143)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 22, vBOp_StartIndex); }

        }
        dbg.location(347, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "vBOp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "vBOp"

    // $ANTLR start synpred1_BACON
    public final void synpred1_BACON_fragment() throws RecognitionException {
        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:272:4: ( expr comparators )
        dbg.enterAlt(1);

        // C:\\Users\\Adam\\Documents\\Imperial\\First_Term\\Workspace\\VirtualEcology\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:272:5: expr comparators
        {
        dbg.location(272,5);
        pushFollow(FOLLOW_expr_in_synpred1_BACON1604);
        expr();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(272,10);
        pushFollow(FOLLOW_comparators_in_synpred1_BACON1606);
        comparators();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_BACON

    // Delegated rules

    public final boolean synpred1_BACON() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred1_BACON_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        dbg.endBacktrack(state.backtracking, success);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_NEWLINE_in_rules905 = new BitSet(new long[]{0x2220002A01042000L,0x0000000000000280L});
    public static final BitSet FOLLOW_pair_in_rules909 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules912 = new BitSet(new long[]{0x2220002A01042000L,0x0000000000000280L});
    public static final BitSet FOLLOW_pair_in_rules914 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_pair945 = new BitSet(new long[]{0x0220002A01042000L,0x0000000000000280L});
    public static final BitSet FOLLOW_rule2_in_pair947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_pair962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULENAME_in_ruleName974 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName976 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_ruleName990 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName992 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule2_in_rule1012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_rule21031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_rule21036 = new BitSet(new long[]{0x840C3632683A0970L,0x0000000000003F1FL});
    public static final BitSet FOLLOW_bExpr_in_rule21038 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_rule21041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_THEN_in_rule21045 = new BitSet(new long[]{0x0220002A01042000L,0x0000000000000280L});
    public static final BitSet FOLLOW_rule_in_rule21047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPTAKE_in_rule21062 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21064 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_VAR_in_rule21066 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21068 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_rule21070 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELEASE_in_rule21087 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21089 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_VAR_in_rule21091 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21093 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_rule21095 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INGEST_in_rule21112 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21114 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_VAR_in_rule21116 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21118 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_rule21120 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21122 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_rule21124 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule21143 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21145 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_VAR_in_rule21147 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PCHANGE_in_rule21162 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21164 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_VAR_in_rule21166 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21168 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_rule21170 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDE_in_rule21187 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21189 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_rule21191 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_rule21206 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21208 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_VAR_in_rule21210 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21212 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_rule21214 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21216 = new BitSet(new long[]{0x0002000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_NEWLINE_in_rule21222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_WITH_in_rule21226 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LSQUARE_in_rule21228 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_assignList_in_rule21230 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_RSQUARE_in_rule21232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21254 = new BitSet(new long[]{0x0220002A01042000L,0x0000000000000280L});
    public static final BitSet FOLLOW_rule2_in_rule21256 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_assign1273 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUALS_in_assign1275 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_assign1277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_assignList1298 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_assignList1301 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_assign_in_assignList1303 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_expr2_in_expr1331 = new BitSet(new long[]{0x0040400000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_expr1345 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr2_in_expr1348 = new BitSet(new long[]{0x0040400000000002L});
    public static final BitSet FOLLOW_expr3_in_expr21362 = new BitSet(new long[]{0x0000800000800002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_expr21376 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr3_in_expr21380 = new BitSet(new long[]{0x0000800000800002L});
    public static final BitSet FOLLOW_expr4_in_expr31394 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_expr31408 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr4_in_expr31411 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41427 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_expr41429 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryPrimitives_in_expr41440 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41442 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_expr41444 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_expr41459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_expr41464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr41469 = new BitSet(new long[]{0x840C3632683A0970L,0x0000000000003F1FL});
    public static final BitSet FOLLOW_bExpr_in_expr41471 = new BitSet(new long[]{0x0002000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_expr41474 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_THEN_in_expr41478 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_expr41480 = new BitSet(new long[]{0x0002000002000000L});
    public static final BitSet FOLLOW_NEWLINE_in_expr41483 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ELSE_in_expr41487 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_expr41489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binPrim_in_expr41506 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41508 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_expr41510 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41512 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_expr41514 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARHIST_in_expr41531 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_VAR_in_expr41535 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41537 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_expr41539 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vOp_in_expr41556 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41558 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_expr41560 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1582 = new BitSet(new long[]{0x0010000000000082L});
    public static final BitSet FOLLOW_lowPrecBoolOp_in_bExpr1585 = new BitSet(new long[]{0x840C3632683A0970L,0x0000000000003F1FL});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1588 = new BitSet(new long[]{0x0010000000000082L});
    public static final BitSet FOLLOW_expr_in_bExpr21612 = new BitSet(new long[]{0x000100C184000000L});
    public static final BitSet FOLLOW_comparators_in_bExpr21614 = new BitSet(new long[]{0x84003632683A0930L,0x0000000000003F1DL});
    public static final BitSet FOLLOW_expr_in_bExpr21617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bExpr21622 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21624 = new BitSet(new long[]{0x840C3632683A0970L,0x0000000000003F1FL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21626 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21641 = new BitSet(new long[]{0x840C3632683A0970L,0x0000000000003F1FL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21643 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vBOp_in_bExpr21654 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21656 = new BitSet(new long[]{0x840C3632683A0970L,0x0000000000003F1FL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21658 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POW_in_highPrecMathOp1825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred1_BACON1604 = new BitSet(new long[]{0x000100C184000000L});
    public static final BitSet FOLLOW_comparators_in_synpred1_BACON1606 = new BitSet(new long[]{0x0000000000000002L});

}