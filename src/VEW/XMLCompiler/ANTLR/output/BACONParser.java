// $ANTLR 3.4 C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2011-10-26 20:08:50

package VEW.XMLCompiler.ANTLR.output;


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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACOS", "ALL", "AND", "ASIN", "ASSIGN", "ASSIGNLIST", "ATAN", "BEXPR", "CHANGE", "COLON", "COMMA", "COMMENT", "COS", "CREATE", "DENSITYAT", "DEPTHFORFI", "DEPTHFORVI", "DIGIT", "DIV", "DIVIDE", "ELSE", "EQUALS", "EXP", "EXPR", "FLOAT", "FULLIRRADAT", "GREATEREQUALS", "GREATERTHAN", "IF", "IGNORE", "INGEST", "INTEGRATE", "LBRACKET", "LESSEQUALS", "LESSTHAN", "LETTER", "LN", "LOGTEN", "LSQUARE", "MAX", "MIN", "MINUS", "MUL", "NEQUALS", "NEWLINE", "NONE", "NOT", "OR", "PCHANGE", "PLUS", "POW", "RBRACKET", "RELEASE", "RND", "RSQUARE", "RULE", "RULENAME", "SALINITYAT", "SIN", "SOME", "SQRT", "TAN", "TEMPAT", "THEN", "UNKNOWN", "UPTAKE", "UVIRRADAT", "VAR", "VARHIST", "VAVERAGE", "VPRODUCT", "VSUM", "WITH"
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
    public static final int SALINITYAT=62;
    public static final int SIN=63;
    public static final int SOME=64;
    public static final int SQRT=65;
    public static final int TAN=66;
    public static final int TEMPAT=67;
    public static final int THEN=68;
    public static final int UNKNOWN=69;
    public static final int UPTAKE=70;
    public static final int UVIRRADAT=71;
    public static final int VAR=72;
    public static final int VARHIST=73;
    public static final int VAVERAGE=74;
    public static final int VPRODUCT=75;
    public static final int VSUM=76;
    public static final int WITH=77;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


public static final String[] ruleNames = new String[] {
    "invalidRule", "synpred56_BACON", "binPrim", "synpred29_BACON", "synpred11_BACON", 
    "synpred34_BACON", "synpred32_BACON", "synpred6_BACON", "synpred47_BACON", 
    "synpred40_BACON", "synpred62_BACON", "synpred30_BACON", "synpred4_BACON", 
    "synpred24_BACON", "synpred23_BACON", "synpred52_BACON", "synpred38_BACON", 
    "synpred25_BACON", "synpred17_BACON", "synpred27_BACON", "synpred18_BACON", 
    "synpred43_BACON", "synpred59_BACON", "expr3", "rule2", "synpred64_BACON", 
    "synpred63_BACON", "bExpr", "synpred9_BACON", "synpred48_BACON", "synpred61_BACON", 
    "synpred57_BACON", "comparators", "assignList", "unaryPrimitives", "medPrecMathOp", 
    "bExpr2", "synpred15_BACON", "assign", "synpred10_BACON", "lowPrecMathOp", 
    "expr4", "synpred8_BACON", "synpred20_BACON", "synpred16_BACON", "synpred60_BACON", 
    "synpred14_BACON", "synpred26_BACON", "synpred50_BACON", "synpred35_BACON", 
    "synpred45_BACON", "synpred42_BACON", "synpred33_BACON", "synpred44_BACON", 
    "synpred39_BACON", "synpred1_BACON", "synpred53_BACON", "synpred46_BACON", 
    "synpred36_BACON", "synpred49_BACON", "expr", "synpred41_BACON", "synpred19_BACON", 
    "synpred51_BACON", "expr2", "synpred5_BACON", "synpred13_BACON", "rule", 
    "synpred55_BACON", "vOp", "synpred7_BACON", "synpred21_BACON", "synpred3_BACON", 
    "synpred28_BACON", "synpred2_BACON", "synpred37_BACON", "synpred12_BACON", 
    "vBOp", "lowPrecBoolOp", "highPrecMathOp", "synpred22_BACON", "rules", 
    "ruleName", "synpred54_BACON", "synpred31_BACON", "synpred58_BACON", 
    "pair"
};

public static final boolean[] decisionCanBacktrack = new boolean[] {
    false, // invalid decision
    false, false, false, false, false, false, false, false, false, false, 
        true, true, true, false, false, true, false, false
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
        this.state.ruleMemo = new HashMap[86+1];
         

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
    this.state.ruleMemo = new HashMap[86+1];
     

     
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
    public String getGrammarFileName() { return "C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g"; }


    public static class rules_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rules"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:1: rules : ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )? -> pair ( pair )* ;
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
        dbg.location(130, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:131:2: ( ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )? -> pair ( pair )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:131:4: ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )?
            {
            dbg.location(131,4);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:131:4: ( NEWLINE )?
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:131:5: NEWLINE
                    {
                    dbg.location(131,5);
                    NEWLINE1=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules898); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE1);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(1);}

            dbg.location(131,15);
            pushFollow(FOLLOW_pair_in_rules902);
            pair2=pair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pair.add(pair2.getTree());
            dbg.location(131,20);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:131:20: ( NEWLINE pair )*
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

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:131:21: NEWLINE pair
            	    {
            	    dbg.location(131,21);
            	    NEWLINE3=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules905); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE3);

            	    dbg.location(131,29);
            	    pushFollow(FOLLOW_pair_in_rules907);
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

            dbg.location(131,36);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:131:36: ( NEWLINE )?
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:131:37: NEWLINE
                    {
                    dbg.location(131,37);
                    NEWLINE5=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules912); if (state.failed) return retval; 
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
            // 131:47: -> pair ( pair )*
            {
                dbg.location(131,50);
                adaptor.addChild(root_0, stream_pair.nextTree());
                dbg.location(131,55);
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:131:55: ( pair )*
                while ( stream_pair.hasNext() ) {
                    dbg.location(131,55);
                    adaptor.addChild(root_0, stream_pair.nextTree());

                }
                stream_pair.reset();

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
        dbg.location(132, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:134:1: pair : ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule );
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
        dbg.location(134, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:2: ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule )
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:4: ruleName rule2
                    {
                    dbg.location(135,4);
                    pushFollow(FOLLOW_ruleName_in_pair934);
                    ruleName6=ruleName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleName.add(ruleName6.getTree());
                    dbg.location(135,13);
                    pushFollow(FOLLOW_rule2_in_pair936);
                    rule27=rule2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule2.add(rule27.getTree());

                    // AST REWRITE
                    // elements: ruleName, rule2
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 135:19: -> ^( RULE ruleName rule2 )
                    {
                        dbg.location(135,22);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:22: ^( RULE ruleName rule2 )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(135,24);
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(RULE, "RULE")
                        , root_1);

                        dbg.location(135,29);
                        adaptor.addChild(root_1, stream_ruleName.nextTree());
                        dbg.location(135,38);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:136:4: rule
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(136,4);
                    pushFollow(FOLLOW_rule_in_pair951);
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
        dbg.location(137, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:139:1: ruleName : ( RULENAME COLON ( NEWLINE )? -> RULENAME | VAR COLON ( NEWLINE )? -> VAR );
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
        dbg.location(139, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:140:2: ( RULENAME COLON ( NEWLINE )? -> RULENAME | VAR COLON ( NEWLINE )? -> VAR )
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:140:4: RULENAME COLON ( NEWLINE )?
                    {
                    dbg.location(140,4);
                    RULENAME9=(Token)match(input,RULENAME,FOLLOW_RULENAME_in_ruleName963); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULENAME.add(RULENAME9);

                    dbg.location(140,13);
                    COLON10=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName965); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON10);

                    dbg.location(140,19);
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:140:19: ( NEWLINE )?
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

                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:140:20: NEWLINE
                            {
                            dbg.location(140,20);
                            NEWLINE11=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName968); if (state.failed) return retval; 
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
                    // 140:30: -> RULENAME
                    {
                        dbg.location(140,33);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:141:4: VAR COLON ( NEWLINE )?
                    {
                    dbg.location(141,4);
                    VAR12=(Token)match(input,VAR,FOLLOW_VAR_in_ruleName979); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR12);

                    dbg.location(141,8);
                    COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName981); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON13);

                    dbg.location(141,14);
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:141:14: ( NEWLINE )?
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

                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:141:15: NEWLINE
                            {
                            dbg.location(141,15);
                            NEWLINE14=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName984); if (state.failed) return retval; 
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
                    // 141:25: -> VAR
                    {
                        dbg.location(141,28);
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
        dbg.location(142, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:144:1: rule : rule2 -> ^( RULE rule2 ) ;
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
        dbg.location(144, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:145:2: ( rule2 -> ^( RULE rule2 ) )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:145:4: rule2
            {
            dbg.location(145,4);
            pushFollow(FOLLOW_rule2_in_rule1001);
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
            // 145:10: -> ^( RULE rule2 )
            {
                dbg.location(145,13);
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:145:13: ^( RULE rule2 )
                {
                Object root_1 = (Object)adaptor.nil();
                dbg.location(145,15);
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(RULE, "RULE")
                , root_1);

                dbg.location(145,20);
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
        dbg.location(146, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:148:1: rule2 : ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule RBRACKET -> rule );
    public final BACONParser.rule2_return rule2() throws RecognitionException {
        BACONParser.rule2_return retval = new BACONParser.rule2_return();
        retval.start = input.LT(1);

        int rule2_StartIndex = input.index();

        Object root_0 = null;

        Token IF17=null;
        Token THEN19=null;
        Token UPTAKE21=null;
        Token LBRACKET22=null;
        Token VAR23=null;
        Token COMMA24=null;
        Token RBRACKET26=null;
        Token RELEASE27=null;
        Token LBRACKET28=null;
        Token VAR29=null;
        Token COMMA30=null;
        Token RBRACKET32=null;
        Token INGEST33=null;
        Token LBRACKET34=null;
        Token VAR35=null;
        Token COMMA36=null;
        Token COMMA38=null;
        Token RBRACKET40=null;
        Token CHANGE41=null;
        Token LBRACKET42=null;
        Token VAR43=null;
        Token RBRACKET44=null;
        Token PCHANGE45=null;
        Token LBRACKET46=null;
        Token VAR47=null;
        Token COMMA48=null;
        Token RBRACKET50=null;
        Token DIVIDE51=null;
        Token LBRACKET52=null;
        Token RBRACKET54=null;
        Token CREATE55=null;
        Token LBRACKET56=null;
        Token VAR57=null;
        Token COMMA58=null;
        Token RBRACKET60=null;
        Token WITH61=null;
        Token LSQUARE62=null;
        Token RSQUARE64=null;
        Token LBRACKET65=null;
        Token RBRACKET67=null;
        BACONParser.assign_return assign16 =null;

        BACONParser.bExpr_return bExpr18 =null;

        BACONParser.rule_return rule20 =null;

        BACONParser.expr_return expr25 =null;

        BACONParser.expr_return expr31 =null;

        BACONParser.expr_return expr37 =null;

        BACONParser.expr_return expr39 =null;

        BACONParser.expr_return expr49 =null;

        BACONParser.expr_return expr53 =null;

        BACONParser.expr_return expr59 =null;

        BACONParser.assignList_return assignList63 =null;

        BACONParser.rule_return rule66 =null;


        Object IF17_tree=null;
        Object THEN19_tree=null;
        Object UPTAKE21_tree=null;
        Object LBRACKET22_tree=null;
        Object VAR23_tree=null;
        Object COMMA24_tree=null;
        Object RBRACKET26_tree=null;
        Object RELEASE27_tree=null;
        Object LBRACKET28_tree=null;
        Object VAR29_tree=null;
        Object COMMA30_tree=null;
        Object RBRACKET32_tree=null;
        Object INGEST33_tree=null;
        Object LBRACKET34_tree=null;
        Object VAR35_tree=null;
        Object COMMA36_tree=null;
        Object COMMA38_tree=null;
        Object RBRACKET40_tree=null;
        Object CHANGE41_tree=null;
        Object LBRACKET42_tree=null;
        Object VAR43_tree=null;
        Object RBRACKET44_tree=null;
        Object PCHANGE45_tree=null;
        Object LBRACKET46_tree=null;
        Object VAR47_tree=null;
        Object COMMA48_tree=null;
        Object RBRACKET50_tree=null;
        Object DIVIDE51_tree=null;
        Object LBRACKET52_tree=null;
        Object RBRACKET54_tree=null;
        Object CREATE55_tree=null;
        Object LBRACKET56_tree=null;
        Object VAR57_tree=null;
        Object COMMA58_tree=null;
        Object RBRACKET60_tree=null;
        Object WITH61_tree=null;
        Object LSQUARE62_tree=null;
        Object RSQUARE64_tree=null;
        Object LBRACKET65_tree=null;
        Object RBRACKET67_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_PCHANGE=new RewriteRuleTokenStream(adaptor,"token PCHANGE");
        RewriteRuleTokenStream stream_CREATE=new RewriteRuleTokenStream(adaptor,"token CREATE");
        RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
        RewriteRuleTokenStream stream_THEN=new RewriteRuleTokenStream(adaptor,"token THEN");
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
        RewriteRuleSubtreeStream stream_assignList=new RewriteRuleSubtreeStream(adaptor,"rule assignList");
        try { dbg.enterRule(getGrammarFileName(), "rule2");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(148, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:149:2: ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule RBRACKET -> rule )
            int alt9=10;
            try { dbg.enterDecision(9, decisionCanBacktrack[9]);

            switch ( input.LA(1) ) {
            case VAR:
                {
                alt9=1;
                }
                break;
            case IF:
                {
                alt9=2;
                }
                break;
            case UPTAKE:
                {
                alt9=3;
                }
                break;
            case RELEASE:
                {
                alt9=4;
                }
                break;
            case INGEST:
                {
                alt9=5;
                }
                break;
            case CHANGE:
                {
                alt9=6;
                }
                break;
            case PCHANGE:
                {
                alt9=7;
                }
                break;
            case DIVIDE:
                {
                alt9=8;
                }
                break;
            case CREATE:
                {
                alt9=9;
                }
                break;
            case LBRACKET:
                {
                alt9=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }

            } finally {dbg.exitDecision(9);}

            switch (alt9) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:149:4: assign
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(149,4);
                    pushFollow(FOLLOW_assign_in_rule21020);
                    assign16=assign();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign16.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:150:4: IF bExpr THEN rule
                    {
                    dbg.location(150,4);
                    IF17=(Token)match(input,IF,FOLLOW_IF_in_rule21025); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF17);

                    dbg.location(150,7);
                    pushFollow(FOLLOW_bExpr_in_rule21027);
                    bExpr18=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr18.getTree());
                    dbg.location(150,13);
                    THEN19=(Token)match(input,THEN,FOLLOW_THEN_in_rule21029); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN19);

                    dbg.location(150,18);
                    pushFollow(FOLLOW_rule_in_rule21031);
                    rule20=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule20.getTree());

                    // AST REWRITE
                    // elements: rule, bExpr, IF
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 150:23: -> ^( IF bExpr rule )
                    {
                        dbg.location(150,26);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:150:26: ^( IF bExpr rule )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(150,28);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_IF.nextNode()
                        , root_1);

                        dbg.location(150,31);
                        adaptor.addChild(root_1, stream_bExpr.nextTree());
                        dbg.location(150,37);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:151:4: UPTAKE LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(151,4);
                    UPTAKE21=(Token)match(input,UPTAKE,FOLLOW_UPTAKE_in_rule21046); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UPTAKE.add(UPTAKE21);

                    dbg.location(151,11);
                    LBRACKET22=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21048); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET22);

                    dbg.location(151,20);
                    VAR23=(Token)match(input,VAR,FOLLOW_VAR_in_rule21050); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR23);

                    dbg.location(151,24);
                    COMMA24=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21052); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA24);

                    dbg.location(151,30);
                    pushFollow(FOLLOW_expr_in_rule21054);
                    expr25=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr25.getTree());
                    dbg.location(151,35);
                    RBRACKET26=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21056); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET26);


                    // AST REWRITE
                    // elements: VAR, UPTAKE, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 151:44: -> ^( UPTAKE VAR expr )
                    {
                        dbg.location(151,47);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:151:47: ^( UPTAKE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(151,49);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_UPTAKE.nextNode()
                        , root_1);

                        dbg.location(151,56);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(151,60);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:152:4: RELEASE LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(152,4);
                    RELEASE27=(Token)match(input,RELEASE,FOLLOW_RELEASE_in_rule21071); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RELEASE.add(RELEASE27);

                    dbg.location(152,12);
                    LBRACKET28=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21073); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET28);

                    dbg.location(152,21);
                    VAR29=(Token)match(input,VAR,FOLLOW_VAR_in_rule21075); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR29);

                    dbg.location(152,25);
                    COMMA30=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21077); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA30);

                    dbg.location(152,31);
                    pushFollow(FOLLOW_expr_in_rule21079);
                    expr31=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr31.getTree());
                    dbg.location(152,36);
                    RBRACKET32=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21081); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET32);


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
                    // 152:45: -> ^( RELEASE VAR expr )
                    {
                        dbg.location(152,48);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:152:48: ^( RELEASE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(152,50);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_RELEASE.nextNode()
                        , root_1);

                        dbg.location(152,58);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(152,62);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:153:4: INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET
                    {
                    dbg.location(153,4);
                    INGEST33=(Token)match(input,INGEST,FOLLOW_INGEST_in_rule21096); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INGEST.add(INGEST33);

                    dbg.location(153,11);
                    LBRACKET34=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21098); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET34);

                    dbg.location(153,20);
                    VAR35=(Token)match(input,VAR,FOLLOW_VAR_in_rule21100); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR35);

                    dbg.location(153,24);
                    COMMA36=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21102); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA36);

                    dbg.location(153,30);
                    pushFollow(FOLLOW_expr_in_rule21104);
                    expr37=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr37.getTree());
                    dbg.location(153,35);
                    COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21106); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA38);

                    dbg.location(153,41);
                    pushFollow(FOLLOW_expr_in_rule21108);
                    expr39=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr39.getTree());
                    dbg.location(153,46);
                    RBRACKET40=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21110); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET40);


                    // AST REWRITE
                    // elements: INGEST, expr, VAR, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 153:55: -> ^( INGEST VAR expr expr )
                    {
                        dbg.location(153,58);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:153:58: ^( INGEST VAR expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(153,60);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_INGEST.nextNode()
                        , root_1);

                        dbg.location(153,67);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(153,71);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(153,76);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:154:4: CHANGE LBRACKET VAR RBRACKET
                    {
                    dbg.location(154,4);
                    CHANGE41=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21127); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE41);

                    dbg.location(154,11);
                    LBRACKET42=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21129); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET42);

                    dbg.location(154,20);
                    VAR43=(Token)match(input,VAR,FOLLOW_VAR_in_rule21131); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR43);

                    dbg.location(154,24);
                    RBRACKET44=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21133); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET44);


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
                    // 154:33: -> ^( CHANGE VAR )
                    {
                        dbg.location(154,36);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:154:36: ^( CHANGE VAR )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(154,38);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CHANGE.nextNode()
                        , root_1);

                        dbg.location(154,45);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:155:4: PCHANGE LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(155,4);
                    PCHANGE45=(Token)match(input,PCHANGE,FOLLOW_PCHANGE_in_rule21146); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_PCHANGE.add(PCHANGE45);

                    dbg.location(155,12);
                    LBRACKET46=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21148); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET46);

                    dbg.location(155,21);
                    VAR47=(Token)match(input,VAR,FOLLOW_VAR_in_rule21150); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR47);

                    dbg.location(155,25);
                    COMMA48=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21152); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA48);

                    dbg.location(155,31);
                    pushFollow(FOLLOW_expr_in_rule21154);
                    expr49=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr49.getTree());
                    dbg.location(155,36);
                    RBRACKET50=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21156); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET50);


                    // AST REWRITE
                    // elements: VAR, PCHANGE, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 155:45: -> ^( PCHANGE VAR expr )
                    {
                        dbg.location(155,48);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:155:48: ^( PCHANGE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(155,50);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_PCHANGE.nextNode()
                        , root_1);

                        dbg.location(155,58);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(155,62);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:156:4: DIVIDE LBRACKET expr RBRACKET
                    {
                    dbg.location(156,4);
                    DIVIDE51=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_rule21171); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIVIDE.add(DIVIDE51);

                    dbg.location(156,11);
                    LBRACKET52=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21173); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET52);

                    dbg.location(156,20);
                    pushFollow(FOLLOW_expr_in_rule21175);
                    expr53=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr53.getTree());
                    dbg.location(156,25);
                    RBRACKET54=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21177); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET54);


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
                    // 156:34: -> ^( DIVIDE expr )
                    {
                        dbg.location(156,37);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:156:37: ^( DIVIDE expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(156,39);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_DIVIDE.nextNode()
                        , root_1);

                        dbg.location(156,46);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:157:4: CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )?
                    {
                    dbg.location(157,4);
                    CREATE55=(Token)match(input,CREATE,FOLLOW_CREATE_in_rule21190); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CREATE.add(CREATE55);

                    dbg.location(157,11);
                    LBRACKET56=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21192); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET56);

                    dbg.location(157,20);
                    VAR57=(Token)match(input,VAR,FOLLOW_VAR_in_rule21194); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR57);

                    dbg.location(157,24);
                    COMMA58=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21196); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA58);

                    dbg.location(157,30);
                    pushFollow(FOLLOW_expr_in_rule21198);
                    expr59=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr59.getTree());
                    dbg.location(157,35);
                    RBRACKET60=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21200); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET60);

                    dbg.location(158,3);
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:158:3: ( WITH LSQUARE assignList RSQUARE )?
                    int alt8=2;
                    try { dbg.enterSubRule(8);
                    try { dbg.enterDecision(8, decisionCanBacktrack[8]);

                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==WITH) ) {
                        alt8=1;
                    }
                    } finally {dbg.exitDecision(8);}

                    switch (alt8) {
                        case 1 :
                            dbg.enterAlt(1);

                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:158:4: WITH LSQUARE assignList RSQUARE
                            {
                            dbg.location(158,4);
                            WITH61=(Token)match(input,WITH,FOLLOW_WITH_in_rule21206); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_WITH.add(WITH61);

                            dbg.location(158,9);
                            LSQUARE62=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_rule21208); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE62);

                            dbg.location(158,17);
                            pushFollow(FOLLOW_assignList_in_rule21210);
                            assignList63=assignList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignList.add(assignList63.getTree());
                            dbg.location(158,28);
                            RSQUARE64=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_rule21212); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE64);


                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(8);}


                    // AST REWRITE
                    // elements: expr, VAR, CREATE, assignList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 158:38: -> ^( CREATE VAR expr ( assignList )? )
                    {
                        dbg.location(158,41);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:158:41: ^( CREATE VAR expr ( assignList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(158,43);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CREATE.nextNode()
                        , root_1);

                        dbg.location(158,50);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(158,54);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(158,59);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:158:59: ( assignList )?
                        if ( stream_assignList.hasNext() ) {
                            dbg.location(158,60);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:159:4: LBRACKET rule RBRACKET
                    {
                    dbg.location(159,4);
                    LBRACKET65=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21234); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET65);

                    dbg.location(159,13);
                    pushFollow(FOLLOW_rule_in_rule21236);
                    rule66=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule66.getTree());
                    dbg.location(159,18);
                    RBRACKET67=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21238); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET67);


                    // AST REWRITE
                    // elements: rule
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 159:27: -> rule
                    {
                        dbg.location(159,30);
                        adaptor.addChild(root_0, stream_rule.nextTree());

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
        dbg.location(160, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:162:1: assign : VAR EQUALS expr -> ^( ASSIGN VAR expr ) ;
    public final BACONParser.assign_return assign() throws RecognitionException {
        BACONParser.assign_return retval = new BACONParser.assign_return();
        retval.start = input.LT(1);

        int assign_StartIndex = input.index();

        Object root_0 = null;

        Token VAR68=null;
        Token EQUALS69=null;
        BACONParser.expr_return expr70 =null;


        Object VAR68_tree=null;
        Object EQUALS69_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try { dbg.enterRule(getGrammarFileName(), "assign");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(162, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:163:2: ( VAR EQUALS expr -> ^( ASSIGN VAR expr ) )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:163:4: VAR EQUALS expr
            {
            dbg.location(163,4);
            VAR68=(Token)match(input,VAR,FOLLOW_VAR_in_assign1253); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR68);

            dbg.location(163,8);
            EQUALS69=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assign1255); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS69);

            dbg.location(163,15);
            pushFollow(FOLLOW_expr_in_assign1257);
            expr70=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr70.getTree());

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
            // 163:20: -> ^( ASSIGN VAR expr )
            {
                dbg.location(163,23);
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:163:23: ^( ASSIGN VAR expr )
                {
                Object root_1 = (Object)adaptor.nil();
                dbg.location(163,25);
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGN, "ASSIGN")
                , root_1);

                dbg.location(163,32);
                adaptor.addChild(root_1, 
                stream_VAR.nextNode()
                );
                dbg.location(163,36);
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
        dbg.location(164, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:166:1: assignList : assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) ;
    public final BACONParser.assignList_return assignList() throws RecognitionException {
        BACONParser.assignList_return retval = new BACONParser.assignList_return();
        retval.start = input.LT(1);

        int assignList_StartIndex = input.index();

        Object root_0 = null;

        Token COMMA72=null;
        BACONParser.assign_return assign71 =null;

        BACONParser.assign_return assign73 =null;


        Object COMMA72_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_assign=new RewriteRuleSubtreeStream(adaptor,"rule assign");
        try { dbg.enterRule(getGrammarFileName(), "assignList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(166, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:167:2: ( assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:167:4: assign ( COMMA assign )*
            {
            dbg.location(167,4);
            pushFollow(FOLLOW_assign_in_assignList1278);
            assign71=assign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assign.add(assign71.getTree());
            dbg.location(167,11);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:167:11: ( COMMA assign )*
            try { dbg.enterSubRule(10);

            loop10:
            do {
                int alt10=2;
                try { dbg.enterDecision(10, decisionCanBacktrack[10]);

                int LA10_0 = input.LA(1);

                if ( (LA10_0==COMMA) ) {
                    alt10=1;
                }


                } finally {dbg.exitDecision(10);}

                switch (alt10) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:167:12: COMMA assign
            	    {
            	    dbg.location(167,12);
            	    COMMA72=(Token)match(input,COMMA,FOLLOW_COMMA_in_assignList1281); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA72);

            	    dbg.location(167,18);
            	    pushFollow(FOLLOW_assign_in_assignList1283);
            	    assign73=assign();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_assign.add(assign73.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);
            } finally {dbg.exitSubRule(10);}


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
            // 167:27: -> ^( ASSIGNLIST assign ( assign )* )
            {
                dbg.location(167,30);
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:167:30: ^( ASSIGNLIST assign ( assign )* )
                {
                Object root_1 = (Object)adaptor.nil();
                dbg.location(167,32);
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGNLIST, "ASSIGNLIST")
                , root_1);

                dbg.location(167,43);
                adaptor.addChild(root_1, stream_assign.nextTree());
                dbg.location(167,50);
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:167:50: ( assign )*
                while ( stream_assign.hasNext() ) {
                    dbg.location(167,51);
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
        dbg.location(168, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:170:1: expr : expr2 ( lowPrecMathOp ^ expr2 )* ;
    public final BACONParser.expr_return expr() throws RecognitionException {
        BACONParser.expr_return retval = new BACONParser.expr_return();
        retval.start = input.LT(1);

        int expr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr2_return expr274 =null;

        BACONParser.lowPrecMathOp_return lowPrecMathOp75 =null;

        BACONParser.expr2_return expr276 =null;



        try { dbg.enterRule(getGrammarFileName(), "expr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(170, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:171:2: ( expr2 ( lowPrecMathOp ^ expr2 )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:171:4: expr2 ( lowPrecMathOp ^ expr2 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(171,4);
            pushFollow(FOLLOW_expr2_in_expr1311);
            expr274=expr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr274.getTree());
            dbg.location(171,10);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:171:10: ( lowPrecMathOp ^ expr2 )*
            try { dbg.enterSubRule(11);

            loop11:
            do {
                int alt11=2;
                try { dbg.enterDecision(11, decisionCanBacktrack[11]);

                int LA11_0 = input.LA(1);

                if ( (LA11_0==MINUS||LA11_0==PLUS) ) {
                    int LA11_2 = input.LA(2);

                    if ( (synpred19_BACON()) ) {
                        alt11=1;
                    }


                }


                } finally {dbg.exitDecision(11);}

                switch (alt11) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:171:11: lowPrecMathOp ^ expr2
            	    {
            	    dbg.location(171,24);
            	    pushFollow(FOLLOW_lowPrecMathOp_in_expr1314);
            	    lowPrecMathOp75=lowPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecMathOp75.getTree(), root_0);
            	    dbg.location(171,26);
            	    pushFollow(FOLLOW_expr2_in_expr1317);
            	    expr276=expr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr276.getTree());

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);
            } finally {dbg.exitSubRule(11);}


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
        dbg.location(172, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:174:1: expr2 : expr3 ( medPrecMathOp ^ expr3 )* ;
    public final BACONParser.expr2_return expr2() throws RecognitionException {
        BACONParser.expr2_return retval = new BACONParser.expr2_return();
        retval.start = input.LT(1);

        int expr2_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr3_return expr377 =null;

        BACONParser.medPrecMathOp_return medPrecMathOp78 =null;

        BACONParser.expr3_return expr379 =null;



        try { dbg.enterRule(getGrammarFileName(), "expr2");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(174, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:175:2: ( expr3 ( medPrecMathOp ^ expr3 )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:175:4: expr3 ( medPrecMathOp ^ expr3 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(175,4);
            pushFollow(FOLLOW_expr3_in_expr21331);
            expr377=expr3();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr377.getTree());
            dbg.location(175,10);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:175:10: ( medPrecMathOp ^ expr3 )*
            try { dbg.enterSubRule(12);

            loop12:
            do {
                int alt12=2;
                try { dbg.enterDecision(12, decisionCanBacktrack[12]);

                int LA12_0 = input.LA(1);

                if ( (LA12_0==DIV||LA12_0==MUL) ) {
                    int LA12_2 = input.LA(2);

                    if ( (synpred20_BACON()) ) {
                        alt12=1;
                    }


                }


                } finally {dbg.exitDecision(12);}

                switch (alt12) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:175:11: medPrecMathOp ^ expr3
            	    {
            	    dbg.location(175,24);
            	    pushFollow(FOLLOW_medPrecMathOp_in_expr21334);
            	    medPrecMathOp78=medPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(medPrecMathOp78.getTree(), root_0);
            	    dbg.location(175,27);
            	    pushFollow(FOLLOW_expr3_in_expr21338);
            	    expr379=expr3();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr379.getTree());

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);
            } finally {dbg.exitSubRule(12);}


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
        dbg.location(176, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:178:1: expr3 : expr4 ( highPrecMathOp ^ expr4 )* ;
    public final BACONParser.expr3_return expr3() throws RecognitionException {
        BACONParser.expr3_return retval = new BACONParser.expr3_return();
        retval.start = input.LT(1);

        int expr3_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr4_return expr480 =null;

        BACONParser.highPrecMathOp_return highPrecMathOp81 =null;

        BACONParser.expr4_return expr482 =null;



        try { dbg.enterRule(getGrammarFileName(), "expr3");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(178, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:179:2: ( expr4 ( highPrecMathOp ^ expr4 )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:179:4: expr4 ( highPrecMathOp ^ expr4 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(179,4);
            pushFollow(FOLLOW_expr4_in_expr31352);
            expr480=expr4();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr480.getTree());
            dbg.location(179,10);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:179:10: ( highPrecMathOp ^ expr4 )*
            try { dbg.enterSubRule(13);

            loop13:
            do {
                int alt13=2;
                try { dbg.enterDecision(13, decisionCanBacktrack[13]);

                int LA13_0 = input.LA(1);

                if ( (LA13_0==POW) ) {
                    int LA13_2 = input.LA(2);

                    if ( (synpred21_BACON()) ) {
                        alt13=1;
                    }


                }


                } finally {dbg.exitDecision(13);}

                switch (alt13) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:179:11: highPrecMathOp ^ expr4
            	    {
            	    dbg.location(179,25);
            	    pushFollow(FOLLOW_highPrecMathOp_in_expr31355);
            	    highPrecMathOp81=highPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(highPrecMathOp81.getTree(), root_0);
            	    dbg.location(179,27);
            	    pushFollow(FOLLOW_expr4_in_expr31358);
            	    expr482=expr4();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr482.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 10, expr3_StartIndex); }

        }
        dbg.location(180, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:183:1: expr4 : ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) );
    public final BACONParser.expr4_return expr4() throws RecognitionException {
        BACONParser.expr4_return retval = new BACONParser.expr4_return();
        retval.start = input.LT(1);

        int expr4_StartIndex = input.index();

        Object root_0 = null;

        Token LBRACKET83=null;
        Token RBRACKET85=null;
        Token LBRACKET87=null;
        Token RBRACKET89=null;
        Token FLOAT90=null;
        Token VAR91=null;
        Token IF92=null;
        Token THEN94=null;
        Token ELSE96=null;
        Token LBRACKET99=null;
        Token COMMA101=null;
        Token RBRACKET103=null;
        Token VARHIST104=null;
        Token LBRACKET105=null;
        Token VAR106=null;
        Token COMMA107=null;
        Token RBRACKET109=null;
        Token LBRACKET111=null;
        Token RBRACKET113=null;
        BACONParser.expr_return expr84 =null;

        BACONParser.unaryPrimitives_return unaryPrimitives86 =null;

        BACONParser.expr_return expr88 =null;

        BACONParser.bExpr_return bExpr93 =null;

        BACONParser.expr_return expr95 =null;

        BACONParser.expr_return expr97 =null;

        BACONParser.binPrim_return binPrim98 =null;

        BACONParser.expr_return expr100 =null;

        BACONParser.expr_return expr102 =null;

        BACONParser.expr_return expr108 =null;

        BACONParser.vOp_return vOp110 =null;

        BACONParser.expr_return expr112 =null;


        Object LBRACKET83_tree=null;
        Object RBRACKET85_tree=null;
        Object LBRACKET87_tree=null;
        Object RBRACKET89_tree=null;
        Object FLOAT90_tree=null;
        Object VAR91_tree=null;
        Object IF92_tree=null;
        Object THEN94_tree=null;
        Object ELSE96_tree=null;
        Object LBRACKET99_tree=null;
        Object COMMA101_tree=null;
        Object RBRACKET103_tree=null;
        Object VARHIST104_tree=null;
        Object LBRACKET105_tree=null;
        Object VAR106_tree=null;
        Object COMMA107_tree=null;
        Object RBRACKET109_tree=null;
        Object LBRACKET111_tree=null;
        Object RBRACKET113_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_THEN=new RewriteRuleTokenStream(adaptor,"token THEN");
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
        dbg.location(183, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:3: ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) )
            int alt14=8;
            try { dbg.enterDecision(14, decisionCanBacktrack[14]);

            switch ( input.LA(1) ) {
            case LBRACKET:
                {
                alt14=1;
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
                alt14=2;
                }
                break;
            case FLOAT:
                {
                alt14=3;
                }
                break;
            case VAR:
                {
                alt14=4;
                }
                break;
            case IF:
                {
                alt14=5;
                }
                break;
            case MAX:
            case MIN:
                {
                alt14=6;
                }
                break;
            case VARHIST:
                {
                alt14=7;
                }
                break;
            case VAVERAGE:
            case VPRODUCT:
            case VSUM:
                {
                alt14=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }

            } finally {dbg.exitDecision(14);}

            switch (alt14) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:5: LBRACKET expr RBRACKET
                    {
                    dbg.location(184,5);
                    LBRACKET83=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41374); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET83);

                    dbg.location(184,14);
                    pushFollow(FOLLOW_expr_in_expr41376);
                    expr84=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr84.getTree());
                    dbg.location(184,19);
                    RBRACKET85=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41378); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET85);


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
                    // 184:28: -> expr
                    {
                        dbg.location(184,31);
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:185:4: unaryPrimitives LBRACKET expr RBRACKET
                    {
                    dbg.location(185,4);
                    pushFollow(FOLLOW_unaryPrimitives_in_expr41387);
                    unaryPrimitives86=unaryPrimitives();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryPrimitives.add(unaryPrimitives86.getTree());
                    dbg.location(185,20);
                    LBRACKET87=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41389); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET87);

                    dbg.location(185,29);
                    pushFollow(FOLLOW_expr_in_expr41391);
                    expr88=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr88.getTree());
                    dbg.location(185,34);
                    RBRACKET89=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41393); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET89);


                    // AST REWRITE
                    // elements: unaryPrimitives, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 185:43: -> ^( unaryPrimitives expr )
                    {
                        dbg.location(185,46);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:185:46: ^( unaryPrimitives expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(185,48);
                        root_1 = (Object)adaptor.becomeRoot(stream_unaryPrimitives.nextNode(), root_1);

                        dbg.location(185,64);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:186:4: FLOAT
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(186,4);
                    FLOAT90=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_expr41406); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT90_tree = 
                    (Object)adaptor.create(FLOAT90)
                    ;
                    adaptor.addChild(root_0, FLOAT90_tree);
                    }

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:187:4: VAR
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(187,4);
                    VAR91=(Token)match(input,VAR,FOLLOW_VAR_in_expr41411); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR91_tree = 
                    (Object)adaptor.create(VAR91)
                    ;
                    adaptor.addChild(root_0, VAR91_tree);
                    }

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:188:4: IF bExpr THEN expr ELSE expr
                    {
                    dbg.location(188,4);
                    IF92=(Token)match(input,IF,FOLLOW_IF_in_expr41416); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF92);

                    dbg.location(188,7);
                    pushFollow(FOLLOW_bExpr_in_expr41418);
                    bExpr93=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr93.getTree());
                    dbg.location(188,13);
                    THEN94=(Token)match(input,THEN,FOLLOW_THEN_in_expr41420); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN94);

                    dbg.location(188,18);
                    pushFollow(FOLLOW_expr_in_expr41422);
                    expr95=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr95.getTree());
                    dbg.location(188,23);
                    ELSE96=(Token)match(input,ELSE,FOLLOW_ELSE_in_expr41424); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELSE.add(ELSE96);

                    dbg.location(188,28);
                    pushFollow(FOLLOW_expr_in_expr41426);
                    expr97=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr97.getTree());

                    // AST REWRITE
                    // elements: bExpr, expr, expr, IF
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 188:33: -> ^( IF bExpr expr expr )
                    {
                        dbg.location(188,36);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:188:36: ^( IF bExpr expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(188,38);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_IF.nextNode()
                        , root_1);

                        dbg.location(188,41);
                        adaptor.addChild(root_1, stream_bExpr.nextTree());
                        dbg.location(188,47);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(188,52);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:189:4: binPrim LBRACKET expr COMMA expr RBRACKET
                    {
                    dbg.location(189,4);
                    pushFollow(FOLLOW_binPrim_in_expr41443);
                    binPrim98=binPrim();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binPrim.add(binPrim98.getTree());
                    dbg.location(189,12);
                    LBRACKET99=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41445); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET99);

                    dbg.location(189,21);
                    pushFollow(FOLLOW_expr_in_expr41447);
                    expr100=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr100.getTree());
                    dbg.location(189,26);
                    COMMA101=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41449); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA101);

                    dbg.location(189,32);
                    pushFollow(FOLLOW_expr_in_expr41451);
                    expr102=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr102.getTree());
                    dbg.location(189,37);
                    RBRACKET103=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41453); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET103);


                    // AST REWRITE
                    // elements: expr, expr, binPrim
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 189:46: -> ^( binPrim expr expr )
                    {
                        dbg.location(189,49);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:189:49: ^( binPrim expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(189,51);
                        root_1 = (Object)adaptor.becomeRoot(stream_binPrim.nextNode(), root_1);

                        dbg.location(189,59);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(189,64);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:190:4: VARHIST LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(190,4);
                    VARHIST104=(Token)match(input,VARHIST,FOLLOW_VARHIST_in_expr41468); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VARHIST.add(VARHIST104);

                    dbg.location(190,12);
                    LBRACKET105=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41470); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET105);

                    dbg.location(190,21);
                    VAR106=(Token)match(input,VAR,FOLLOW_VAR_in_expr41472); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR106);

                    dbg.location(190,25);
                    COMMA107=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41474); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA107);

                    dbg.location(190,31);
                    pushFollow(FOLLOW_expr_in_expr41476);
                    expr108=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr108.getTree());
                    dbg.location(190,36);
                    RBRACKET109=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41478); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET109);


                    // AST REWRITE
                    // elements: VARHIST, VAR, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 190:45: -> ^( VARHIST VAR expr )
                    {
                        dbg.location(190,48);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:190:48: ^( VARHIST VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(190,50);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_VARHIST.nextNode()
                        , root_1);

                        dbg.location(190,58);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(190,62);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:191:4: vOp LBRACKET expr RBRACKET
                    {
                    dbg.location(191,4);
                    pushFollow(FOLLOW_vOp_in_expr41493);
                    vOp110=vOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vOp.add(vOp110.getTree());
                    dbg.location(191,8);
                    LBRACKET111=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41495); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET111);

                    dbg.location(191,17);
                    pushFollow(FOLLOW_expr_in_expr41497);
                    expr112=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr112.getTree());
                    dbg.location(191,22);
                    RBRACKET113=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41499); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET113);


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
                    // 191:31: -> ^( vOp expr )
                    {
                        dbg.location(191,34);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:191:34: ^( vOp expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(191,36);
                        root_1 = (Object)adaptor.becomeRoot(stream_vOp.nextNode(), root_1);

                        dbg.location(191,40);
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
        dbg.location(192, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:195:1: bExpr : bExpr2 ( lowPrecBoolOp ^ bExpr2 )* ;
    public final BACONParser.bExpr_return bExpr() throws RecognitionException {
        BACONParser.bExpr_return retval = new BACONParser.bExpr_return();
        retval.start = input.LT(1);

        int bExpr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.bExpr2_return bExpr2114 =null;

        BACONParser.lowPrecBoolOp_return lowPrecBoolOp115 =null;

        BACONParser.bExpr2_return bExpr2116 =null;



        try { dbg.enterRule(getGrammarFileName(), "bExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(195, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:196:2: ( bExpr2 ( lowPrecBoolOp ^ bExpr2 )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:196:4: bExpr2 ( lowPrecBoolOp ^ bExpr2 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(196,4);
            pushFollow(FOLLOW_bExpr2_in_bExpr1519);
            bExpr2114=bExpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2114.getTree());
            dbg.location(196,11);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:196:11: ( lowPrecBoolOp ^ bExpr2 )*
            try { dbg.enterSubRule(15);

            loop15:
            do {
                int alt15=2;
                try { dbg.enterDecision(15, decisionCanBacktrack[15]);

                int LA15_0 = input.LA(1);

                if ( (LA15_0==AND||LA15_0==OR) ) {
                    alt15=1;
                }


                } finally {dbg.exitDecision(15);}

                switch (alt15) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:196:12: lowPrecBoolOp ^ bExpr2
            	    {
            	    dbg.location(196,25);
            	    pushFollow(FOLLOW_lowPrecBoolOp_in_bExpr1522);
            	    lowPrecBoolOp115=lowPrecBoolOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecBoolOp115.getTree(), root_0);
            	    dbg.location(196,27);
            	    pushFollow(FOLLOW_bExpr2_in_bExpr1525);
            	    bExpr2116=bExpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2116.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 12, bExpr_StartIndex); }

        }
        dbg.location(197, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:199:1: bExpr2 : ( expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) );
    public final BACONParser.bExpr2_return bExpr2() throws RecognitionException {
        BACONParser.bExpr2_return retval = new BACONParser.bExpr2_return();
        retval.start = input.LT(1);

        int bExpr2_StartIndex = input.index();

        Object root_0 = null;

        Token NOT120=null;
        Token LBRACKET121=null;
        Token RBRACKET123=null;
        Token LBRACKET124=null;
        Token RBRACKET126=null;
        Token LBRACKET128=null;
        Token RBRACKET130=null;
        BACONParser.expr_return expr117 =null;

        BACONParser.comparators_return comparators118 =null;

        BACONParser.expr_return expr119 =null;

        BACONParser.bExpr_return bExpr122 =null;

        BACONParser.bExpr_return bExpr125 =null;

        BACONParser.vBOp_return vBOp127 =null;

        BACONParser.bExpr_return bExpr129 =null;


        Object NOT120_tree=null;
        Object LBRACKET121_tree=null;
        Object RBRACKET123_tree=null;
        Object LBRACKET124_tree=null;
        Object RBRACKET126_tree=null;
        Object LBRACKET128_tree=null;
        Object RBRACKET130_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_vBOp=new RewriteRuleSubtreeStream(adaptor,"rule vBOp");
        try { dbg.enterRule(getGrammarFileName(), "bExpr2");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(199, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:200:2: ( expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) )
            int alt16=4;
            try { dbg.enterDecision(16, decisionCanBacktrack[16]);

            switch ( input.LA(1) ) {
            case LBRACKET:
                {
                int LA16_1 = input.LA(2);

                if ( (synpred30_BACON()) ) {
                    alt16=1;
                }
                else if ( (synpred32_BACON()) ) {
                    alt16=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;

                }
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
            case FLOAT:
            case FULLIRRADAT:
            case IF:
            case INTEGRATE:
            case LN:
            case LOGTEN:
            case MAX:
            case MIN:
            case RND:
            case SALINITYAT:
            case SIN:
            case SQRT:
            case TAN:
            case TEMPAT:
            case UVIRRADAT:
            case VAR:
            case VARHIST:
            case VAVERAGE:
            case VPRODUCT:
            case VSUM:
                {
                alt16=1;
                }
                break;
            case NOT:
                {
                alt16=2;
                }
                break;
            case ALL:
            case NONE:
            case SOME:
                {
                alt16=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }

            } finally {dbg.exitDecision(16);}

            switch (alt16) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:200:4: expr comparators ^ expr
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(200,4);
                    pushFollow(FOLLOW_expr_in_bExpr21540);
                    expr117=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr117.getTree());
                    dbg.location(200,20);
                    pushFollow(FOLLOW_comparators_in_bExpr21542);
                    comparators118=comparators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(comparators118.getTree(), root_0);
                    dbg.location(200,22);
                    pushFollow(FOLLOW_expr_in_bExpr21545);
                    expr119=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr119.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:201:4: NOT LBRACKET bExpr RBRACKET
                    {
                    dbg.location(201,4);
                    NOT120=(Token)match(input,NOT,FOLLOW_NOT_in_bExpr21550); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT120);

                    dbg.location(201,8);
                    LBRACKET121=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21552); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET121);

                    dbg.location(201,17);
                    pushFollow(FOLLOW_bExpr_in_bExpr21554);
                    bExpr122=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr122.getTree());
                    dbg.location(201,23);
                    RBRACKET123=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21556); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET123);


                    // AST REWRITE
                    // elements: NOT, bExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 201:32: -> ^( NOT bExpr )
                    {
                        dbg.location(201,35);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:201:35: ^( NOT bExpr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(201,37);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_NOT.nextNode()
                        , root_1);

                        dbg.location(201,41);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:202:4: LBRACKET bExpr RBRACKET
                    {
                    dbg.location(202,4);
                    LBRACKET124=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21569); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET124);

                    dbg.location(202,13);
                    pushFollow(FOLLOW_bExpr_in_bExpr21571);
                    bExpr125=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr125.getTree());
                    dbg.location(202,19);
                    RBRACKET126=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21573); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET126);


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
                    // 202:28: -> bExpr
                    {
                        dbg.location(202,31);
                        adaptor.addChild(root_0, stream_bExpr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:4: vBOp LBRACKET bExpr RBRACKET
                    {
                    dbg.location(203,4);
                    pushFollow(FOLLOW_vBOp_in_bExpr21582);
                    vBOp127=vBOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vBOp.add(vBOp127.getTree());
                    dbg.location(203,9);
                    LBRACKET128=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21584); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET128);

                    dbg.location(203,18);
                    pushFollow(FOLLOW_bExpr_in_bExpr21586);
                    bExpr129=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr129.getTree());
                    dbg.location(203,24);
                    RBRACKET130=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21588); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET130);


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
                    // 203:33: -> ^( vBOp bExpr )
                    {
                        dbg.location(203,36);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:36: ^( vBOp bExpr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(203,38);
                        root_1 = (Object)adaptor.becomeRoot(stream_vBOp.nextNode(), root_1);

                        dbg.location(203,43);
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
        dbg.location(204, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:1: unaryPrimitives : ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE );
    public final BACONParser.unaryPrimitives_return unaryPrimitives() throws RecognitionException {
        BACONParser.unaryPrimitives_return retval = new BACONParser.unaryPrimitives_return();
        retval.start = input.LT(1);

        int unaryPrimitives_StartIndex = input.index();

        Object root_0 = null;

        Token set131=null;

        Object set131_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "unaryPrimitives");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(209, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:210:2: ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(210,2);
            set131=(Token)input.LT(1);

            if ( (input.LA(1) >= ABS && input.LA(1) <= ACOS)||input.LA(1)==ASIN||input.LA(1)==ATAN||input.LA(1)==COS||(input.LA(1) >= DENSITYAT && input.LA(1) <= DEPTHFORVI)||input.LA(1)==EXP||input.LA(1)==FULLIRRADAT||input.LA(1)==INTEGRATE||(input.LA(1) >= LN && input.LA(1) <= LOGTEN)||input.LA(1)==RND||(input.LA(1) >= SALINITYAT && input.LA(1) <= SIN)||(input.LA(1) >= SQRT && input.LA(1) <= TEMPAT)||input.LA(1)==UVIRRADAT ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set131)
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
        dbg.location(230, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:232:1: lowPrecMathOp : ( PLUS | MINUS );
    public final BACONParser.lowPrecMathOp_return lowPrecMathOp() throws RecognitionException {
        BACONParser.lowPrecMathOp_return retval = new BACONParser.lowPrecMathOp_return();
        retval.start = input.LT(1);

        int lowPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set132=null;

        Object set132_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "lowPrecMathOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(232, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:233:2: ( PLUS | MINUS )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(233,2);
            set132=(Token)input.LT(1);

            if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set132)
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
        dbg.location(235, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:1: medPrecMathOp : ( MUL | DIV );
    public final BACONParser.medPrecMathOp_return medPrecMathOp() throws RecognitionException {
        BACONParser.medPrecMathOp_return retval = new BACONParser.medPrecMathOp_return();
        retval.start = input.LT(1);

        int medPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set133=null;

        Object set133_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "medPrecMathOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(237, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:238:2: ( MUL | DIV )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(238,2);
            set133=(Token)input.LT(1);

            if ( input.LA(1)==DIV||input.LA(1)==MUL ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set133)
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
        dbg.location(240, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:242:1: highPrecMathOp : POW ;
    public final BACONParser.highPrecMathOp_return highPrecMathOp() throws RecognitionException {
        BACONParser.highPrecMathOp_return retval = new BACONParser.highPrecMathOp_return();
        retval.start = input.LT(1);

        int highPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token POW134=null;

        Object POW134_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "highPrecMathOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(242, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:243:2: ( POW )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:243:4: POW
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(243,4);
            POW134=(Token)match(input,POW,FOLLOW_POW_in_highPrecMathOp1753); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            POW134_tree = 
            (Object)adaptor.create(POW134)
            ;
            adaptor.addChild(root_0, POW134_tree);
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
        dbg.location(244, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:246:1: binPrim : ( MIN | MAX );
    public final BACONParser.binPrim_return binPrim() throws RecognitionException {
        BACONParser.binPrim_return retval = new BACONParser.binPrim_return();
        retval.start = input.LT(1);

        int binPrim_StartIndex = input.index();

        Object root_0 = null;

        Token set135=null;

        Object set135_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "binPrim");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(246, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:247:2: ( MIN | MAX )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(247,2);
            set135=(Token)input.LT(1);

            if ( (input.LA(1) >= MAX && input.LA(1) <= MIN) ) {
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
            if ( state.backtracking>0 ) { memoize(input, 18, binPrim_StartIndex); }

        }
        dbg.location(249, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:251:1: lowPrecBoolOp : ( AND | OR );
    public final BACONParser.lowPrecBoolOp_return lowPrecBoolOp() throws RecognitionException {
        BACONParser.lowPrecBoolOp_return retval = new BACONParser.lowPrecBoolOp_return();
        retval.start = input.LT(1);

        int lowPrecBoolOp_StartIndex = input.index();

        Object root_0 = null;

        Token set136=null;

        Object set136_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "lowPrecBoolOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(251, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:252:2: ( AND | OR )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(252,2);
            set136=(Token)input.LT(1);

            if ( input.LA(1)==AND||input.LA(1)==OR ) {
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
            if ( state.backtracking>0 ) { memoize(input, 19, lowPrecBoolOp_StartIndex); }

        }
        dbg.location(254, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:256:1: comparators : ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS );
    public final BACONParser.comparators_return comparators() throws RecognitionException {
        BACONParser.comparators_return retval = new BACONParser.comparators_return();
        retval.start = input.LT(1);

        int comparators_StartIndex = input.index();

        Object root_0 = null;

        Token set137=null;

        Object set137_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "comparators");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(256, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:2: ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(257,2);
            set137=(Token)input.LT(1);

            if ( input.LA(1)==EQUALS||(input.LA(1) >= GREATEREQUALS && input.LA(1) <= GREATERTHAN)||(input.LA(1) >= LESSEQUALS && input.LA(1) <= LESSTHAN)||input.LA(1)==NEQUALS ) {
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
            if ( state.backtracking>0 ) { memoize(input, 20, comparators_StartIndex); }

        }
        dbg.location(263, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:265:1: vOp : ( VSUM | VPRODUCT | VAVERAGE );
    public final BACONParser.vOp_return vOp() throws RecognitionException {
        BACONParser.vOp_return retval = new BACONParser.vOp_return();
        retval.start = input.LT(1);

        int vOp_StartIndex = input.index();

        Object root_0 = null;

        Token set138=null;

        Object set138_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "vOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(265, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:266:2: ( VSUM | VPRODUCT | VAVERAGE )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(266,2);
            set138=(Token)input.LT(1);

            if ( (input.LA(1) >= VAVERAGE && input.LA(1) <= VSUM) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set138)
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
        dbg.location(269, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:271:1: vBOp : ( ALL | SOME | NONE );
    public final BACONParser.vBOp_return vBOp() throws RecognitionException {
        BACONParser.vBOp_return retval = new BACONParser.vBOp_return();
        retval.start = input.LT(1);

        int vBOp_StartIndex = input.index();

        Object root_0 = null;

        Token set139=null;

        Object set139_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "vBOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(271, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:272:2: ( ALL | SOME | NONE )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(272,2);
            set139=(Token)input.LT(1);

            if ( input.LA(1)==ALL||input.LA(1)==NONE||input.LA(1)==SOME ) {
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
            if ( state.backtracking>0 ) { memoize(input, 22, vBOp_StartIndex); }

        }
        dbg.location(275, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "vBOp");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "vBOp"

    // $ANTLR start synpred19_BACON
    public final void synpred19_BACON_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:171:11: ( lowPrecMathOp expr2 )
        dbg.enterAlt(1);

        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:171:11: lowPrecMathOp expr2
        {
        dbg.location(171,11);
        pushFollow(FOLLOW_lowPrecMathOp_in_synpred19_BACON1314);
        lowPrecMathOp();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(171,26);
        pushFollow(FOLLOW_expr2_in_synpred19_BACON1317);
        expr2();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_BACON

    // $ANTLR start synpred20_BACON
    public final void synpred20_BACON_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:175:11: ( medPrecMathOp expr3 )
        dbg.enterAlt(1);

        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:175:11: medPrecMathOp expr3
        {
        dbg.location(175,11);
        pushFollow(FOLLOW_medPrecMathOp_in_synpred20_BACON1334);
        medPrecMathOp();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(175,27);
        pushFollow(FOLLOW_expr3_in_synpred20_BACON1338);
        expr3();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred20_BACON

    // $ANTLR start synpred21_BACON
    public final void synpred21_BACON_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:179:11: ( highPrecMathOp expr4 )
        dbg.enterAlt(1);

        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:179:11: highPrecMathOp expr4
        {
        dbg.location(179,11);
        pushFollow(FOLLOW_highPrecMathOp_in_synpred21_BACON1355);
        highPrecMathOp();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(179,27);
        pushFollow(FOLLOW_expr4_in_synpred21_BACON1358);
        expr4();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred21_BACON

    // $ANTLR start synpred30_BACON
    public final void synpred30_BACON_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:200:4: ( expr comparators expr )
        dbg.enterAlt(1);

        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:200:4: expr comparators expr
        {
        dbg.location(200,4);
        pushFollow(FOLLOW_expr_in_synpred30_BACON1540);
        expr();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(200,9);
        pushFollow(FOLLOW_comparators_in_synpred30_BACON1542);
        comparators();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(200,22);
        pushFollow(FOLLOW_expr_in_synpred30_BACON1545);
        expr();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred30_BACON

    // $ANTLR start synpred32_BACON
    public final void synpred32_BACON_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:202:4: ( LBRACKET bExpr RBRACKET )
        dbg.enterAlt(1);

        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:202:4: LBRACKET bExpr RBRACKET
        {
        dbg.location(202,4);
        match(input,LBRACKET,FOLLOW_LBRACKET_in_synpred32_BACON1569); if (state.failed) return ;
        dbg.location(202,13);
        pushFollow(FOLLOW_bExpr_in_synpred32_BACON1571);
        bExpr();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(202,19);
        match(input,RBRACKET,FOLLOW_RBRACKET_in_synpred32_BACON1573); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred32_BACON

    // Delegated rules

    public final boolean synpred32_BACON() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred32_BACON_fragment(); // can never throw exception
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
    public final boolean synpred20_BACON() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred20_BACON_fragment(); // can never throw exception
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
    public final boolean synpred21_BACON() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred21_BACON_fragment(); // can never throw exception
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
    public final boolean synpred19_BACON() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred19_BACON_fragment(); // can never throw exception
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
    public final boolean synpred30_BACON() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred30_BACON_fragment(); // can never throw exception
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


 

    public static final BitSet FOLLOW_NEWLINE_in_rules898 = new BitSet(new long[]{0x2220002A01042000L,0x0000000000000140L});
    public static final BitSet FOLLOW_pair_in_rules902 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules905 = new BitSet(new long[]{0x2220002A01042000L,0x0000000000000140L});
    public static final BitSet FOLLOW_pair_in_rules907 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_pair934 = new BitSet(new long[]{0x0220002A01042000L,0x0000000000000140L});
    public static final BitSet FOLLOW_rule2_in_pair936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_pair951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULENAME_in_ruleName963 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName965 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_ruleName979 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName981 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule2_in_rule1001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_rule21020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_rule21025 = new BitSet(new long[]{0xC40C3632683A0970L,0x0000000000001F8FL});
    public static final BitSet FOLLOW_bExpr_in_rule21027 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_THEN_in_rule21029 = new BitSet(new long[]{0x0220002A01042000L,0x0000000000000140L});
    public static final BitSet FOLLOW_rule_in_rule21031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPTAKE_in_rule21046 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21048 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_VAR_in_rule21050 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21052 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_rule21054 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELEASE_in_rule21071 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21073 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_VAR_in_rule21075 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21077 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_rule21079 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INGEST_in_rule21096 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21098 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_VAR_in_rule21100 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21102 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_rule21104 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21106 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_rule21108 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule21127 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21129 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_VAR_in_rule21131 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PCHANGE_in_rule21146 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21148 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_VAR_in_rule21150 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21152 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_rule21154 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDE_in_rule21171 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21173 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_rule21175 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_rule21190 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21192 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_VAR_in_rule21194 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21196 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_rule21198 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21200 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_WITH_in_rule21206 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_LSQUARE_in_rule21208 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_assignList_in_rule21210 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_RSQUARE_in_rule21212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21234 = new BitSet(new long[]{0x0220002A01042000L,0x0000000000000140L});
    public static final BitSet FOLLOW_rule_in_rule21236 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_assign1253 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUALS_in_assign1255 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_assign1257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_assignList1278 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_assignList1281 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_assign_in_assignList1283 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_expr2_in_expr1311 = new BitSet(new long[]{0x0040400000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_expr1314 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr2_in_expr1317 = new BitSet(new long[]{0x0040400000000002L});
    public static final BitSet FOLLOW_expr3_in_expr21331 = new BitSet(new long[]{0x0000800000800002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_expr21334 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr3_in_expr21338 = new BitSet(new long[]{0x0000800000800002L});
    public static final BitSet FOLLOW_expr4_in_expr31352 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_expr31355 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr4_in_expr31358 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41374 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_expr41376 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryPrimitives_in_expr41387 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41389 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_expr41391 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_expr41406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_expr41411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr41416 = new BitSet(new long[]{0xC40C3632683A0970L,0x0000000000001F8FL});
    public static final BitSet FOLLOW_bExpr_in_expr41418 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_THEN_in_expr41420 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_expr41422 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ELSE_in_expr41424 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_expr41426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binPrim_in_expr41443 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41445 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_expr41447 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41449 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_expr41451 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARHIST_in_expr41468 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41470 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_VAR_in_expr41472 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41474 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_expr41476 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vOp_in_expr41493 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41495 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_expr41497 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1519 = new BitSet(new long[]{0x0010000000000082L});
    public static final BitSet FOLLOW_lowPrecBoolOp_in_bExpr1522 = new BitSet(new long[]{0xC40C3632683A0970L,0x0000000000001F8FL});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1525 = new BitSet(new long[]{0x0010000000000082L});
    public static final BitSet FOLLOW_expr_in_bExpr21540 = new BitSet(new long[]{0x000100C184000000L});
    public static final BitSet FOLLOW_comparators_in_bExpr21542 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_bExpr21545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bExpr21550 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21552 = new BitSet(new long[]{0xC40C3632683A0970L,0x0000000000001F8FL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21554 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21569 = new BitSet(new long[]{0xC40C3632683A0970L,0x0000000000001F8FL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21571 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vBOp_in_bExpr21582 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21584 = new BitSet(new long[]{0xC40C3632683A0970L,0x0000000000001F8FL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21586 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POW_in_highPrecMathOp1753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_synpred19_BACON1314 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr2_in_synpred19_BACON1317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_synpred20_BACON1334 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr3_in_synpred20_BACON1338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_synpred21_BACON1355 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr4_in_synpred21_BACON1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred30_BACON1540 = new BitSet(new long[]{0x000100C184000000L});
    public static final BitSet FOLLOW_comparators_in_synpred30_BACON1542 = new BitSet(new long[]{0xC4003632683A0930L,0x0000000000001F8EL});
    public static final BitSet FOLLOW_expr_in_synpred30_BACON1545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_synpred32_BACON1569 = new BitSet(new long[]{0xC40C3632683A0970L,0x0000000000001F8FL});
    public static final BitSet FOLLOW_bExpr_in_synpred32_BACON1571 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_synpred32_BACON1573 = new BitSet(new long[]{0x0000000000000002L});

}