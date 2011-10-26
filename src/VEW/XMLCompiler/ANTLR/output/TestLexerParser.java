// $ANTLR 3.4 C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g 2011-10-26 17:44:24

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
public class TestLexerParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACOS", "ALL", "AND", "ASIN", "ASSIGN", "ATAN", "CHANGE", "COLON", "COMMA", "COMMENT", "COS", "CREATE", "DENSITYAT", "DEPTHFORFI", "DEPTHFORVI", "DIGIT", "DIV", "DIVIDE", "ELSE", "EQUALS", "EXP", "FLOAT", "FULLIRRADAT", "GREATEREQUALS", "GREATERTHAN", "IF", "IGNORE", "INGEST", "INTEGRATE", "LBRACKET", "LESSEQUALS", "LESSTHAN", "LETTER", "LN", "LOGTEN", "LSQUARE", "MAX", "MIN", "MINUS", "MUL", "NEQUALS", "NEWLINE", "NONE", "NOT", "OR", "PCHANGE", "PLUS", "POW", "RBRACKET", "RELEASE", "RND", "RSQUARE", "RULENAME", "SALINITYAT", "SIN", "SOME", "SQRT", "TAN", "TEMPAT", "THEN", "UNKNOWN", "UPTAKE", "UVIRRADAT", "VAR", "VARHIST", "VAVERAGE", "VPRODUCT", "VSUM", "WITH"
    };

    public static final int EOF=-1;
    public static final int ABS=4;
    public static final int ACOS=5;
    public static final int ALL=6;
    public static final int AND=7;
    public static final int ASIN=8;
    public static final int ASSIGN=9;
    public static final int ATAN=10;
    public static final int CHANGE=11;
    public static final int COLON=12;
    public static final int COMMA=13;
    public static final int COMMENT=14;
    public static final int COS=15;
    public static final int CREATE=16;
    public static final int DENSITYAT=17;
    public static final int DEPTHFORFI=18;
    public static final int DEPTHFORVI=19;
    public static final int DIGIT=20;
    public static final int DIV=21;
    public static final int DIVIDE=22;
    public static final int ELSE=23;
    public static final int EQUALS=24;
    public static final int EXP=25;
    public static final int FLOAT=26;
    public static final int FULLIRRADAT=27;
    public static final int GREATEREQUALS=28;
    public static final int GREATERTHAN=29;
    public static final int IF=30;
    public static final int IGNORE=31;
    public static final int INGEST=32;
    public static final int INTEGRATE=33;
    public static final int LBRACKET=34;
    public static final int LESSEQUALS=35;
    public static final int LESSTHAN=36;
    public static final int LETTER=37;
    public static final int LN=38;
    public static final int LOGTEN=39;
    public static final int LSQUARE=40;
    public static final int MAX=41;
    public static final int MIN=42;
    public static final int MINUS=43;
    public static final int MUL=44;
    public static final int NEQUALS=45;
    public static final int NEWLINE=46;
    public static final int NONE=47;
    public static final int NOT=48;
    public static final int OR=49;
    public static final int PCHANGE=50;
    public static final int PLUS=51;
    public static final int POW=52;
    public static final int RBRACKET=53;
    public static final int RELEASE=54;
    public static final int RND=55;
    public static final int RSQUARE=56;
    public static final int RULENAME=57;
    public static final int SALINITYAT=58;
    public static final int SIN=59;
    public static final int SOME=60;
    public static final int SQRT=61;
    public static final int TAN=62;
    public static final int TEMPAT=63;
    public static final int THEN=64;
    public static final int UNKNOWN=65;
    public static final int UPTAKE=66;
    public static final int UVIRRADAT=67;
    public static final int VAR=68;
    public static final int VARHIST=69;
    public static final int VAVERAGE=70;
    public static final int VPRODUCT=71;
    public static final int VSUM=72;
    public static final int WITH=73;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


public static final String[] ruleNames = new String[] {
    "invalidRule", "synpred29_TestLexer", "synpred15_TestLexer", "synpred49_TestLexer", 
    "lowPrecMathOp", "medPrecMathOp", "synpred26_TestLexer", "synpred35_TestLexer", 
    "synpred30_TestLexer", "synpred33_TestLexer", "synpred18_TestLexer", 
    "synpred53_TestLexer", "synpred9_TestLexer", "synpred11_TestLexer", 
    "synpred17_TestLexer", "expr3", "synpred32_TestLexer", "synpred21_TestLexer", 
    "synpred16_TestLexer", "synpred28_TestLexer", "lowPrecBoolOp", "synpred27_TestLexer", 
    "comparators", "rule", "synpred20_TestLexer", "synpred50_TestLexer", 
    "synpred12_TestLexer", "synpred39_TestLexer", "bExpr", "synpred40_TestLexer", 
    "assign", "bExpr2", "synpred48_TestLexer", "synpred56_TestLexer", "synpred51_TestLexer", 
    "synpred54_TestLexer", "synpred52_TestLexer", "synpred3_TestLexer", 
    "synpred22_TestLexer", "expr", "assignList", "synpred43_TestLexer", 
    "synpred42_TestLexer", "synpred41_TestLexer", "synpred36_TestLexer", 
    "synpred1_TestLexer", "ruleName", "synpred38_TestLexer", "synpred8_TestLexer", 
    "synpred7_TestLexer", "synpred14_TestLexer", "synpred55_TestLexer", 
    "synpred2_TestLexer", "synpred47_TestLexer", "synpred31_TestLexer", 
    "highPrecMathOp", "synpred44_TestLexer", "synpred24_TestLexer", "synpred6_TestLexer", 
    "expr4", "unaryPrimitives", "expr2", "synpred23_TestLexer", "synpred19_TestLexer", 
    "synpred45_TestLexer", "synpred34_TestLexer", "synpred25_TestLexer", 
    "binPrim", "synpred4_TestLexer", "synpred13_TestLexer", "synpred37_TestLexer", 
    "synpred5_TestLexer", "synpred10_TestLexer", "synpred46_TestLexer", 
    "rules"
};

public static final boolean[] decisionCanBacktrack = new boolean[] {
    false, // invalid decision
    false, false, false, false, false, false, false, false, true, true, 
        true, false, false, true, false, false, false
};

 
    public int ruleLevel = 0;
    public int getRuleLevel() { return ruleLevel; }
    public void incRuleLevel() { ruleLevel++; }
    public void decRuleLevel() { ruleLevel--; }
    public TestLexerParser(TokenStream input) {
        this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
    }
    public TestLexerParser(TokenStream input, int port, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[74+1];
         

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

public TestLexerParser(TokenStream input, DebugEventListener dbg) {
    super(input, dbg);
    this.state.ruleMemo = new HashMap[74+1];
     

     
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

    public String[] getTokenNames() { return TestLexerParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g"; }


    public static class rules_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rules"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:128:1: rules : ( ( ruleName )? rule NEWLINE )+ -> ( ( ruleName )? rule )+ ;
    public final TestLexerParser.rules_return rules() throws RecognitionException {
        TestLexerParser.rules_return retval = new TestLexerParser.rules_return();
        retval.start = input.LT(1);

        int rules_StartIndex = input.index();

        Object root_0 = null;

        Token NEWLINE3=null;
        TestLexerParser.ruleName_return ruleName1 =null;

        TestLexerParser.rule_return rule2 =null;


        Object NEWLINE3_tree=null;
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleSubtreeStream stream_ruleName=new RewriteRuleSubtreeStream(adaptor,"rule ruleName");
        RewriteRuleSubtreeStream stream_rule=new RewriteRuleSubtreeStream(adaptor,"rule rule");
        try { dbg.enterRule(getGrammarFileName(), "rules");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(128, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:129:2: ( ( ( ruleName )? rule NEWLINE )+ -> ( ( ruleName )? rule )+ )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:129:4: ( ( ruleName )? rule NEWLINE )+
            {
            dbg.location(129,4);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:129:4: ( ( ruleName )? rule NEWLINE )+
            int cnt2=0;
            try { dbg.enterSubRule(2);

            loop2:
            do {
                int alt2=2;
                try { dbg.enterDecision(2, decisionCanBacktrack[2]);

                int LA2_0 = input.LA(1);

                if ( (LA2_0==CHANGE||LA2_0==CREATE||LA2_0==DIVIDE||LA2_0==IF||LA2_0==INGEST||LA2_0==LBRACKET||LA2_0==PCHANGE||LA2_0==RELEASE||LA2_0==RULENAME||LA2_0==UPTAKE||LA2_0==VAR) ) {
                    alt2=1;
                }


                } finally {dbg.exitDecision(2);}

                switch (alt2) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:129:5: ( ruleName )? rule NEWLINE
            	    {
            	    dbg.location(129,5);
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:129:5: ( ruleName )?
            	    int alt1=2;
            	    try { dbg.enterSubRule(1);
            	    try { dbg.enterDecision(1, decisionCanBacktrack[1]);

            	    int LA1_0 = input.LA(1);

            	    if ( (LA1_0==RULENAME) ) {
            	        alt1=1;
            	    }
            	    else if ( (LA1_0==VAR) ) {
            	        int LA1_2 = input.LA(2);

            	        if ( (LA1_2==COLON) ) {
            	            alt1=1;
            	        }
            	    }
            	    } finally {dbg.exitDecision(1);}

            	    switch (alt1) {
            	        case 1 :
            	            dbg.enterAlt(1);

            	            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:129:6: ruleName
            	            {
            	            dbg.location(129,6);
            	            pushFollow(FOLLOW_ruleName_in_rules885);
            	            ruleName1=ruleName();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) stream_ruleName.add(ruleName1.getTree());

            	            }
            	            break;

            	    }
            	    } finally {dbg.exitSubRule(1);}

            	    dbg.location(129,17);
            	    pushFollow(FOLLOW_rule_in_rules889);
            	    rule2=rule();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rule.add(rule2.getTree());
            	    dbg.location(129,22);
            	    NEWLINE3=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules891); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE3);


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt2++;
            } while (true);
            } finally {dbg.exitSubRule(2);}


            // AST REWRITE
            // elements: rule, ruleName
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 129:32: -> ( ( ruleName )? rule )+
            {
                dbg.location(129,35);
                if ( !(stream_rule.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_rule.hasNext() ) {
                    dbg.location(129,36);
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:129:36: ( ruleName )?
                    if ( stream_ruleName.hasNext() ) {
                        dbg.location(129,37);
                        adaptor.addChild(root_0, stream_ruleName.nextTree());

                    }
                    stream_ruleName.reset();
                    dbg.location(129,48);
                    adaptor.addChild(root_0, stream_rule.nextTree());

                }
                stream_rule.reset();

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
        dbg.location(130, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "rules");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "rules"


    public static class ruleName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ruleName"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:132:1: ruleName : ( RULENAME COLON ( NEWLINE )? | VAR COLON ( NEWLINE )? );
    public final TestLexerParser.ruleName_return ruleName() throws RecognitionException {
        TestLexerParser.ruleName_return retval = new TestLexerParser.ruleName_return();
        retval.start = input.LT(1);

        int ruleName_StartIndex = input.index();

        Object root_0 = null;

        Token RULENAME4=null;
        Token COLON5=null;
        Token NEWLINE6=null;
        Token VAR7=null;
        Token COLON8=null;
        Token NEWLINE9=null;

        Object RULENAME4_tree=null;
        Object COLON5_tree=null;
        Object NEWLINE6_tree=null;
        Object VAR7_tree=null;
        Object COLON8_tree=null;
        Object NEWLINE9_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "ruleName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(132, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:133:2: ( RULENAME COLON ( NEWLINE )? | VAR COLON ( NEWLINE )? )
            int alt5=2;
            try { dbg.enterDecision(5, decisionCanBacktrack[5]);

            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULENAME) ) {
                alt5=1;
            }
            else if ( (LA5_0==VAR) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }
            } finally {dbg.exitDecision(5);}

            switch (alt5) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:133:4: RULENAME COLON ( NEWLINE )?
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(133,4);
                    RULENAME4=(Token)match(input,RULENAME,FOLLOW_RULENAME_in_ruleName918); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RULENAME4_tree = 
                    (Object)adaptor.create(RULENAME4)
                    ;
                    adaptor.addChild(root_0, RULENAME4_tree);
                    }
                    dbg.location(133,13);
                    COLON5=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName920); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON5_tree = 
                    (Object)adaptor.create(COLON5)
                    ;
                    adaptor.addChild(root_0, COLON5_tree);
                    }
                    dbg.location(133,19);
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:133:19: ( NEWLINE )?
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

                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:133:20: NEWLINE
                            {
                            dbg.location(133,20);
                            NEWLINE6=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName923); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            NEWLINE6_tree = 
                            (Object)adaptor.create(NEWLINE6)
                            ;
                            adaptor.addChild(root_0, NEWLINE6_tree);
                            }

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(3);}


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:134:4: VAR COLON ( NEWLINE )?
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(134,4);
                    VAR7=(Token)match(input,VAR,FOLLOW_VAR_in_ruleName930); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR7_tree = 
                    (Object)adaptor.create(VAR7)
                    ;
                    adaptor.addChild(root_0, VAR7_tree);
                    }
                    dbg.location(134,8);
                    COLON8=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName932); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON8_tree = 
                    (Object)adaptor.create(COLON8)
                    ;
                    adaptor.addChild(root_0, COLON8_tree);
                    }
                    dbg.location(134,14);
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:134:14: ( NEWLINE )?
                    int alt4=2;
                    try { dbg.enterSubRule(4);
                    try { dbg.enterDecision(4, decisionCanBacktrack[4]);

                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==NEWLINE) ) {
                        alt4=1;
                    }
                    } finally {dbg.exitDecision(4);}

                    switch (alt4) {
                        case 1 :
                            dbg.enterAlt(1);

                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:134:15: NEWLINE
                            {
                            dbg.location(134,15);
                            NEWLINE9=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName935); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            NEWLINE9_tree = 
                            (Object)adaptor.create(NEWLINE9)
                            ;
                            adaptor.addChild(root_0, NEWLINE9_tree);
                            }

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(4);}


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
            if ( state.backtracking>0 ) { memoize(input, 2, ruleName_StartIndex); }

        }
        dbg.location(135, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:137:1: rule : ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? | LBRACKET rule RBRACKET -> rule );
    public final TestLexerParser.rule_return rule() throws RecognitionException {
        TestLexerParser.rule_return retval = new TestLexerParser.rule_return();
        retval.start = input.LT(1);

        int rule_StartIndex = input.index();

        Object root_0 = null;

        Token IF11=null;
        Token THEN13=null;
        Token UPTAKE15=null;
        Token LBRACKET16=null;
        Token VAR17=null;
        Token COMMA18=null;
        Token RBRACKET20=null;
        Token RELEASE21=null;
        Token LBRACKET22=null;
        Token VAR23=null;
        Token COMMA24=null;
        Token RBRACKET26=null;
        Token INGEST27=null;
        Token LBRACKET28=null;
        Token VAR29=null;
        Token COMMA30=null;
        Token COMMA32=null;
        Token RBRACKET34=null;
        Token CHANGE35=null;
        Token LBRACKET36=null;
        Token VAR37=null;
        Token RBRACKET38=null;
        Token PCHANGE39=null;
        Token LBRACKET40=null;
        Token VAR41=null;
        Token COMMA42=null;
        Token RBRACKET44=null;
        Token DIVIDE45=null;
        Token LBRACKET46=null;
        Token RBRACKET48=null;
        Token CREATE49=null;
        Token LBRACKET50=null;
        Token VAR51=null;
        Token COMMA52=null;
        Token RBRACKET54=null;
        Token WITH55=null;
        Token LSQUARE56=null;
        Token RSQUARE58=null;
        Token LBRACKET59=null;
        Token RBRACKET61=null;
        TestLexerParser.assign_return assign10 =null;

        TestLexerParser.bExpr_return bExpr12 =null;

        TestLexerParser.rule_return rule14 =null;

        TestLexerParser.expr_return expr19 =null;

        TestLexerParser.expr_return expr25 =null;

        TestLexerParser.expr_return expr31 =null;

        TestLexerParser.expr_return expr33 =null;

        TestLexerParser.expr_return expr43 =null;

        TestLexerParser.expr_return expr47 =null;

        TestLexerParser.expr_return expr53 =null;

        TestLexerParser.assignList_return assignList57 =null;

        TestLexerParser.rule_return rule60 =null;


        Object IF11_tree=null;
        Object THEN13_tree=null;
        Object UPTAKE15_tree=null;
        Object LBRACKET16_tree=null;
        Object VAR17_tree=null;
        Object COMMA18_tree=null;
        Object RBRACKET20_tree=null;
        Object RELEASE21_tree=null;
        Object LBRACKET22_tree=null;
        Object VAR23_tree=null;
        Object COMMA24_tree=null;
        Object RBRACKET26_tree=null;
        Object INGEST27_tree=null;
        Object LBRACKET28_tree=null;
        Object VAR29_tree=null;
        Object COMMA30_tree=null;
        Object COMMA32_tree=null;
        Object RBRACKET34_tree=null;
        Object CHANGE35_tree=null;
        Object LBRACKET36_tree=null;
        Object VAR37_tree=null;
        Object RBRACKET38_tree=null;
        Object PCHANGE39_tree=null;
        Object LBRACKET40_tree=null;
        Object VAR41_tree=null;
        Object COMMA42_tree=null;
        Object RBRACKET44_tree=null;
        Object DIVIDE45_tree=null;
        Object LBRACKET46_tree=null;
        Object RBRACKET48_tree=null;
        Object CREATE49_tree=null;
        Object LBRACKET50_tree=null;
        Object VAR51_tree=null;
        Object COMMA52_tree=null;
        Object RBRACKET54_tree=null;
        Object WITH55_tree=null;
        Object LSQUARE56_tree=null;
        Object RSQUARE58_tree=null;
        Object LBRACKET59_tree=null;
        Object RBRACKET61_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_PCHANGE=new RewriteRuleTokenStream(adaptor,"token PCHANGE");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_THEN=new RewriteRuleTokenStream(adaptor,"token THEN");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RELEASE=new RewriteRuleTokenStream(adaptor,"token RELEASE");
        RewriteRuleTokenStream stream_UPTAKE=new RewriteRuleTokenStream(adaptor,"token UPTAKE");
        RewriteRuleTokenStream stream_INGEST=new RewriteRuleTokenStream(adaptor,"token INGEST");
        RewriteRuleTokenStream stream_DIVIDE=new RewriteRuleTokenStream(adaptor,"token DIVIDE");
        RewriteRuleTokenStream stream_CHANGE=new RewriteRuleTokenStream(adaptor,"token CHANGE");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_rule=new RewriteRuleSubtreeStream(adaptor,"rule rule");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try { dbg.enterRule(getGrammarFileName(), "rule");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(137, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:138:2: ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? | LBRACKET rule RBRACKET -> rule )
            int alt7=10;
            try { dbg.enterDecision(7, decisionCanBacktrack[7]);

            switch ( input.LA(1) ) {
            case VAR:
                {
                alt7=1;
                }
                break;
            case IF:
                {
                alt7=2;
                }
                break;
            case UPTAKE:
                {
                alt7=3;
                }
                break;
            case RELEASE:
                {
                alt7=4;
                }
                break;
            case INGEST:
                {
                alt7=5;
                }
                break;
            case CHANGE:
                {
                alt7=6;
                }
                break;
            case PCHANGE:
                {
                alt7=7;
                }
                break;
            case DIVIDE:
                {
                alt7=8;
                }
                break;
            case CREATE:
                {
                alt7=9;
                }
                break;
            case LBRACKET:
                {
                alt7=10;
                }
                break;
            default:
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:138:4: assign
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(138,4);
                    pushFollow(FOLLOW_assign_in_rule949);
                    assign10=assign();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign10.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:139:4: IF bExpr THEN rule
                    {
                    dbg.location(139,4);
                    IF11=(Token)match(input,IF,FOLLOW_IF_in_rule954); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF11);

                    dbg.location(139,7);
                    pushFollow(FOLLOW_bExpr_in_rule956);
                    bExpr12=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr12.getTree());
                    dbg.location(139,13);
                    THEN13=(Token)match(input,THEN,FOLLOW_THEN_in_rule958); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN13);

                    dbg.location(139,18);
                    pushFollow(FOLLOW_rule_in_rule960);
                    rule14=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule14.getTree());

                    // AST REWRITE
                    // elements: bExpr, rule, IF
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 139:23: -> ^( IF bExpr rule )
                    {
                        dbg.location(139,26);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:139:26: ^( IF bExpr rule )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(139,28);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_IF.nextNode()
                        , root_1);

                        dbg.location(139,31);
                        adaptor.addChild(root_1, stream_bExpr.nextTree());
                        dbg.location(139,37);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:140:4: UPTAKE LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(140,4);
                    UPTAKE15=(Token)match(input,UPTAKE,FOLLOW_UPTAKE_in_rule975); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UPTAKE.add(UPTAKE15);

                    dbg.location(140,11);
                    LBRACKET16=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule977); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET16);

                    dbg.location(140,20);
                    VAR17=(Token)match(input,VAR,FOLLOW_VAR_in_rule979); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR17);

                    dbg.location(140,24);
                    COMMA18=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule981); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA18);

                    dbg.location(140,30);
                    pushFollow(FOLLOW_expr_in_rule983);
                    expr19=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr19.getTree());
                    dbg.location(140,35);
                    RBRACKET20=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule985); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET20);


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
                    // 140:44: -> ^( UPTAKE VAR expr )
                    {
                        dbg.location(140,47);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:140:47: ^( UPTAKE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(140,49);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_UPTAKE.nextNode()
                        , root_1);

                        dbg.location(140,56);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(140,60);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:141:4: RELEASE LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(141,4);
                    RELEASE21=(Token)match(input,RELEASE,FOLLOW_RELEASE_in_rule1000); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RELEASE.add(RELEASE21);

                    dbg.location(141,12);
                    LBRACKET22=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule1002); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET22);

                    dbg.location(141,21);
                    VAR23=(Token)match(input,VAR,FOLLOW_VAR_in_rule1004); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR23);

                    dbg.location(141,25);
                    COMMA24=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule1006); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA24);

                    dbg.location(141,31);
                    pushFollow(FOLLOW_expr_in_rule1008);
                    expr25=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr25.getTree());
                    dbg.location(141,36);
                    RBRACKET26=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule1010); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET26);


                    // AST REWRITE
                    // elements: VAR, RELEASE, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 141:45: -> ^( RELEASE VAR expr )
                    {
                        dbg.location(141,48);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:141:48: ^( RELEASE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(141,50);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_RELEASE.nextNode()
                        , root_1);

                        dbg.location(141,58);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(141,62);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:142:4: INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET
                    {
                    dbg.location(142,4);
                    INGEST27=(Token)match(input,INGEST,FOLLOW_INGEST_in_rule1025); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INGEST.add(INGEST27);

                    dbg.location(142,11);
                    LBRACKET28=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule1027); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET28);

                    dbg.location(142,20);
                    VAR29=(Token)match(input,VAR,FOLLOW_VAR_in_rule1029); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR29);

                    dbg.location(142,24);
                    COMMA30=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule1031); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA30);

                    dbg.location(142,30);
                    pushFollow(FOLLOW_expr_in_rule1033);
                    expr31=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr31.getTree());
                    dbg.location(142,35);
                    COMMA32=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule1035); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA32);

                    dbg.location(142,41);
                    pushFollow(FOLLOW_expr_in_rule1037);
                    expr33=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr33.getTree());
                    dbg.location(142,46);
                    RBRACKET34=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule1039); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET34);


                    // AST REWRITE
                    // elements: expr, VAR, expr, INGEST
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 142:55: -> ^( INGEST VAR expr expr )
                    {
                        dbg.location(142,58);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:142:58: ^( INGEST VAR expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(142,60);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_INGEST.nextNode()
                        , root_1);

                        dbg.location(142,67);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(142,71);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(142,76);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:143:4: CHANGE LBRACKET VAR RBRACKET
                    {
                    dbg.location(143,4);
                    CHANGE35=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule1056); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE35);

                    dbg.location(143,11);
                    LBRACKET36=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule1058); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET36);

                    dbg.location(143,20);
                    VAR37=(Token)match(input,VAR,FOLLOW_VAR_in_rule1060); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR37);

                    dbg.location(143,24);
                    RBRACKET38=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule1062); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET38);


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
                    // 143:33: -> ^( CHANGE VAR )
                    {
                        dbg.location(143,36);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:143:36: ^( CHANGE VAR )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(143,38);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CHANGE.nextNode()
                        , root_1);

                        dbg.location(143,45);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:144:4: PCHANGE LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(144,4);
                    PCHANGE39=(Token)match(input,PCHANGE,FOLLOW_PCHANGE_in_rule1075); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_PCHANGE.add(PCHANGE39);

                    dbg.location(144,12);
                    LBRACKET40=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule1077); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET40);

                    dbg.location(144,21);
                    VAR41=(Token)match(input,VAR,FOLLOW_VAR_in_rule1079); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR41);

                    dbg.location(144,25);
                    COMMA42=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule1081); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA42);

                    dbg.location(144,31);
                    pushFollow(FOLLOW_expr_in_rule1083);
                    expr43=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr43.getTree());
                    dbg.location(144,36);
                    RBRACKET44=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule1085); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET44);


                    // AST REWRITE
                    // elements: expr, VAR, PCHANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 144:45: -> ^( PCHANGE VAR expr )
                    {
                        dbg.location(144,48);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:144:48: ^( PCHANGE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(144,50);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_PCHANGE.nextNode()
                        , root_1);

                        dbg.location(144,58);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(144,62);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:145:4: DIVIDE LBRACKET expr RBRACKET
                    {
                    dbg.location(145,4);
                    DIVIDE45=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_rule1100); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIVIDE.add(DIVIDE45);

                    dbg.location(145,11);
                    LBRACKET46=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule1102); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET46);

                    dbg.location(145,20);
                    pushFollow(FOLLOW_expr_in_rule1104);
                    expr47=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr47.getTree());
                    dbg.location(145,25);
                    RBRACKET48=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule1106); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET48);


                    // AST REWRITE
                    // elements: DIVIDE, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 145:34: -> ^( DIVIDE expr )
                    {
                        dbg.location(145,37);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:145:37: ^( DIVIDE expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(145,39);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_DIVIDE.nextNode()
                        , root_1);

                        dbg.location(145,46);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:146:4: CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )?
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(146,4);
                    CREATE49=(Token)match(input,CREATE,FOLLOW_CREATE_in_rule1119); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CREATE49_tree = 
                    (Object)adaptor.create(CREATE49)
                    ;
                    adaptor.addChild(root_0, CREATE49_tree);
                    }
                    dbg.location(146,11);
                    LBRACKET50=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule1121); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LBRACKET50_tree = 
                    (Object)adaptor.create(LBRACKET50)
                    ;
                    adaptor.addChild(root_0, LBRACKET50_tree);
                    }
                    dbg.location(146,20);
                    VAR51=(Token)match(input,VAR,FOLLOW_VAR_in_rule1123); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR51_tree = 
                    (Object)adaptor.create(VAR51)
                    ;
                    adaptor.addChild(root_0, VAR51_tree);
                    }
                    dbg.location(146,24);
                    COMMA52=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule1125); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA52_tree = 
                    (Object)adaptor.create(COMMA52)
                    ;
                    adaptor.addChild(root_0, COMMA52_tree);
                    }
                    dbg.location(146,30);
                    pushFollow(FOLLOW_expr_in_rule1127);
                    expr53=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr53.getTree());
                    dbg.location(146,35);
                    RBRACKET54=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule1129); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACKET54_tree = 
                    (Object)adaptor.create(RBRACKET54)
                    ;
                    adaptor.addChild(root_0, RBRACKET54_tree);
                    }
                    dbg.location(147,3);
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:147:3: ( WITH LSQUARE assignList RSQUARE )?
                    int alt6=2;
                    try { dbg.enterSubRule(6);
                    try { dbg.enterDecision(6, decisionCanBacktrack[6]);

                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==WITH) ) {
                        alt6=1;
                    }
                    } finally {dbg.exitDecision(6);}

                    switch (alt6) {
                        case 1 :
                            dbg.enterAlt(1);

                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:147:4: WITH LSQUARE assignList RSQUARE
                            {
                            dbg.location(147,4);
                            WITH55=(Token)match(input,WITH,FOLLOW_WITH_in_rule1135); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            WITH55_tree = 
                            (Object)adaptor.create(WITH55)
                            ;
                            adaptor.addChild(root_0, WITH55_tree);
                            }
                            dbg.location(147,9);
                            LSQUARE56=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_rule1137); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            LSQUARE56_tree = 
                            (Object)adaptor.create(LSQUARE56)
                            ;
                            adaptor.addChild(root_0, LSQUARE56_tree);
                            }
                            dbg.location(147,17);
                            pushFollow(FOLLOW_assignList_in_rule1139);
                            assignList57=assignList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, assignList57.getTree());
                            dbg.location(147,28);
                            RSQUARE58=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_rule1141); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            RSQUARE58_tree = 
                            (Object)adaptor.create(RSQUARE58)
                            ;
                            adaptor.addChild(root_0, RSQUARE58_tree);
                            }

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(6);}


                    }
                    break;
                case 10 :
                    dbg.enterAlt(10);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:148:4: LBRACKET rule RBRACKET
                    {
                    dbg.location(148,4);
                    LBRACKET59=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule1148); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET59);

                    dbg.location(148,13);
                    pushFollow(FOLLOW_rule_in_rule1150);
                    rule60=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule60.getTree());
                    dbg.location(148,18);
                    RBRACKET61=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule1152); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET61);


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
                    // 148:27: -> rule
                    {
                        dbg.location(148,30);
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
            if ( state.backtracking>0 ) { memoize(input, 3, rule_StartIndex); }

        }
        dbg.location(149, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "rule");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "rule"


    public static class assign_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assign"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:151:1: assign : VAR EQUALS expr -> ^( ASSIGN VAR expr ) ;
    public final TestLexerParser.assign_return assign() throws RecognitionException {
        TestLexerParser.assign_return retval = new TestLexerParser.assign_return();
        retval.start = input.LT(1);

        int assign_StartIndex = input.index();

        Object root_0 = null;

        Token VAR62=null;
        Token EQUALS63=null;
        TestLexerParser.expr_return expr64 =null;


        Object VAR62_tree=null;
        Object EQUALS63_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try { dbg.enterRule(getGrammarFileName(), "assign");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(151, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:152:2: ( VAR EQUALS expr -> ^( ASSIGN VAR expr ) )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:152:4: VAR EQUALS expr
            {
            dbg.location(152,4);
            VAR62=(Token)match(input,VAR,FOLLOW_VAR_in_assign1168); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR62);

            dbg.location(152,8);
            EQUALS63=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assign1170); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS63);

            dbg.location(152,15);
            pushFollow(FOLLOW_expr_in_assign1172);
            expr64=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr64.getTree());

            // AST REWRITE
            // elements: VAR, expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 152:20: -> ^( ASSIGN VAR expr )
            {
                dbg.location(152,23);
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:152:23: ^( ASSIGN VAR expr )
                {
                Object root_1 = (Object)adaptor.nil();
                dbg.location(152,25);
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGN, "ASSIGN")
                , root_1);

                dbg.location(152,32);
                adaptor.addChild(root_1, 
                stream_VAR.nextNode()
                );
                dbg.location(152,36);
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
            if ( state.backtracking>0 ) { memoize(input, 4, assign_StartIndex); }

        }
        dbg.location(153, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:156:1: assignList : assign ( COMMA assign )* ;
    public final TestLexerParser.assignList_return assignList() throws RecognitionException {
        TestLexerParser.assignList_return retval = new TestLexerParser.assignList_return();
        retval.start = input.LT(1);

        int assignList_StartIndex = input.index();

        Object root_0 = null;

        Token COMMA66=null;
        TestLexerParser.assign_return assign65 =null;

        TestLexerParser.assign_return assign67 =null;


        Object COMMA66_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "assignList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(156, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:157:2: ( assign ( COMMA assign )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:157:4: assign ( COMMA assign )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(157,4);
            pushFollow(FOLLOW_assign_in_assignList1195);
            assign65=assign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, assign65.getTree());
            dbg.location(157,11);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:157:11: ( COMMA assign )*
            try { dbg.enterSubRule(8);

            loop8:
            do {
                int alt8=2;
                try { dbg.enterDecision(8, decisionCanBacktrack[8]);

                int LA8_0 = input.LA(1);

                if ( (LA8_0==COMMA) ) {
                    alt8=1;
                }


                } finally {dbg.exitDecision(8);}

                switch (alt8) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:157:12: COMMA assign
            	    {
            	    dbg.location(157,12);
            	    COMMA66=(Token)match(input,COMMA,FOLLOW_COMMA_in_assignList1198); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA66_tree = 
            	    (Object)adaptor.create(COMMA66)
            	    ;
            	    adaptor.addChild(root_0, COMMA66_tree);
            	    }
            	    dbg.location(157,18);
            	    pushFollow(FOLLOW_assign_in_assignList1200);
            	    assign67=assign();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign67.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);
            } finally {dbg.exitSubRule(8);}


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
            if ( state.backtracking>0 ) { memoize(input, 5, assignList_StartIndex); }

        }
        dbg.location(158, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:160:1: expr : expr2 ( lowPrecMathOp ^ expr2 )* ;
    public final TestLexerParser.expr_return expr() throws RecognitionException {
        TestLexerParser.expr_return retval = new TestLexerParser.expr_return();
        retval.start = input.LT(1);

        int expr_StartIndex = input.index();

        Object root_0 = null;

        TestLexerParser.expr2_return expr268 =null;

        TestLexerParser.lowPrecMathOp_return lowPrecMathOp69 =null;

        TestLexerParser.expr2_return expr270 =null;



        try { dbg.enterRule(getGrammarFileName(), "expr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(160, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:161:2: ( expr2 ( lowPrecMathOp ^ expr2 )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:161:4: expr2 ( lowPrecMathOp ^ expr2 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(161,4);
            pushFollow(FOLLOW_expr2_in_expr1215);
            expr268=expr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr268.getTree());
            dbg.location(161,10);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:161:10: ( lowPrecMathOp ^ expr2 )*
            try { dbg.enterSubRule(9);

            loop9:
            do {
                int alt9=2;
                try { dbg.enterDecision(9, decisionCanBacktrack[9]);

                int LA9_0 = input.LA(1);

                if ( (LA9_0==MINUS||LA9_0==PLUS) ) {
                    int LA9_2 = input.LA(2);

                    if ( (synpred17_TestLexer()) ) {
                        alt9=1;
                    }


                }


                } finally {dbg.exitDecision(9);}

                switch (alt9) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:161:11: lowPrecMathOp ^ expr2
            	    {
            	    dbg.location(161,24);
            	    pushFollow(FOLLOW_lowPrecMathOp_in_expr1218);
            	    lowPrecMathOp69=lowPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecMathOp69.getTree(), root_0);
            	    dbg.location(161,26);
            	    pushFollow(FOLLOW_expr2_in_expr1221);
            	    expr270=expr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr270.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);
            } finally {dbg.exitSubRule(9);}


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
            if ( state.backtracking>0 ) { memoize(input, 6, expr_StartIndex); }

        }
        dbg.location(162, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:164:1: expr2 : expr3 ( medPrecMathOp ^ expr3 )* ;
    public final TestLexerParser.expr2_return expr2() throws RecognitionException {
        TestLexerParser.expr2_return retval = new TestLexerParser.expr2_return();
        retval.start = input.LT(1);

        int expr2_StartIndex = input.index();

        Object root_0 = null;

        TestLexerParser.expr3_return expr371 =null;

        TestLexerParser.medPrecMathOp_return medPrecMathOp72 =null;

        TestLexerParser.expr3_return expr373 =null;



        try { dbg.enterRule(getGrammarFileName(), "expr2");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(164, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:165:2: ( expr3 ( medPrecMathOp ^ expr3 )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:165:4: expr3 ( medPrecMathOp ^ expr3 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(165,4);
            pushFollow(FOLLOW_expr3_in_expr21235);
            expr371=expr3();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr371.getTree());
            dbg.location(165,10);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:165:10: ( medPrecMathOp ^ expr3 )*
            try { dbg.enterSubRule(10);

            loop10:
            do {
                int alt10=2;
                try { dbg.enterDecision(10, decisionCanBacktrack[10]);

                int LA10_0 = input.LA(1);

                if ( (LA10_0==DIV||LA10_0==MUL) ) {
                    int LA10_2 = input.LA(2);

                    if ( (synpred18_TestLexer()) ) {
                        alt10=1;
                    }


                }


                } finally {dbg.exitDecision(10);}

                switch (alt10) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:165:11: medPrecMathOp ^ expr3
            	    {
            	    dbg.location(165,24);
            	    pushFollow(FOLLOW_medPrecMathOp_in_expr21238);
            	    medPrecMathOp72=medPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(medPrecMathOp72.getTree(), root_0);
            	    dbg.location(165,27);
            	    pushFollow(FOLLOW_expr3_in_expr21242);
            	    expr373=expr3();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr373.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);
            } finally {dbg.exitSubRule(10);}


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
            if ( state.backtracking>0 ) { memoize(input, 7, expr2_StartIndex); }

        }
        dbg.location(166, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:168:1: expr3 : expr4 ( highPrecMathOp ^ expr4 )* ;
    public final TestLexerParser.expr3_return expr3() throws RecognitionException {
        TestLexerParser.expr3_return retval = new TestLexerParser.expr3_return();
        retval.start = input.LT(1);

        int expr3_StartIndex = input.index();

        Object root_0 = null;

        TestLexerParser.expr4_return expr474 =null;

        TestLexerParser.highPrecMathOp_return highPrecMathOp75 =null;

        TestLexerParser.expr4_return expr476 =null;



        try { dbg.enterRule(getGrammarFileName(), "expr3");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(168, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:169:2: ( expr4 ( highPrecMathOp ^ expr4 )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:169:4: expr4 ( highPrecMathOp ^ expr4 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(169,4);
            pushFollow(FOLLOW_expr4_in_expr31256);
            expr474=expr4();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr474.getTree());
            dbg.location(169,10);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:169:10: ( highPrecMathOp ^ expr4 )*
            try { dbg.enterSubRule(11);

            loop11:
            do {
                int alt11=2;
                try { dbg.enterDecision(11, decisionCanBacktrack[11]);

                int LA11_0 = input.LA(1);

                if ( (LA11_0==POW) ) {
                    int LA11_2 = input.LA(2);

                    if ( (synpred19_TestLexer()) ) {
                        alt11=1;
                    }


                }


                } finally {dbg.exitDecision(11);}

                switch (alt11) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:169:11: highPrecMathOp ^ expr4
            	    {
            	    dbg.location(169,25);
            	    pushFollow(FOLLOW_highPrecMathOp_in_expr31259);
            	    highPrecMathOp75=highPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(highPrecMathOp75.getTree(), root_0);
            	    dbg.location(169,27);
            	    pushFollow(FOLLOW_expr4_in_expr31262);
            	    expr476=expr4();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr476.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 8, expr3_StartIndex); }

        }
        dbg.location(170, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:173:1: expr4 : ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) );
    public final TestLexerParser.expr4_return expr4() throws RecognitionException {
        TestLexerParser.expr4_return retval = new TestLexerParser.expr4_return();
        retval.start = input.LT(1);

        int expr4_StartIndex = input.index();

        Object root_0 = null;

        Token LBRACKET77=null;
        Token RBRACKET79=null;
        Token LBRACKET81=null;
        Token RBRACKET83=null;
        Token FLOAT84=null;
        Token VAR85=null;
        Token IF86=null;
        Token THEN88=null;
        Token ELSE90=null;
        Token LBRACKET93=null;
        Token COMMA95=null;
        Token RBRACKET97=null;
        Token VARHIST98=null;
        Token LBRACKET99=null;
        Token VAR100=null;
        Token COMMA101=null;
        Token RBRACKET103=null;
        TestLexerParser.expr_return expr78 =null;

        TestLexerParser.unaryPrimitives_return unaryPrimitives80 =null;

        TestLexerParser.expr_return expr82 =null;

        TestLexerParser.bExpr_return bExpr87 =null;

        TestLexerParser.expr_return expr89 =null;

        TestLexerParser.expr_return expr91 =null;

        TestLexerParser.binPrim_return binPrim92 =null;

        TestLexerParser.expr_return expr94 =null;

        TestLexerParser.expr_return expr96 =null;

        TestLexerParser.expr_return expr102 =null;


        Object LBRACKET77_tree=null;
        Object RBRACKET79_tree=null;
        Object LBRACKET81_tree=null;
        Object RBRACKET83_tree=null;
        Object FLOAT84_tree=null;
        Object VAR85_tree=null;
        Object IF86_tree=null;
        Object THEN88_tree=null;
        Object ELSE90_tree=null;
        Object LBRACKET93_tree=null;
        Object COMMA95_tree=null;
        Object RBRACKET97_tree=null;
        Object VARHIST98_tree=null;
        Object LBRACKET99_tree=null;
        Object VAR100_tree=null;
        Object COMMA101_tree=null;
        Object RBRACKET103_tree=null;
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
        RewriteRuleSubtreeStream stream_binPrim=new RewriteRuleSubtreeStream(adaptor,"rule binPrim");
        try { dbg.enterRule(getGrammarFileName(), "expr4");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(173, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:174:3: ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) )
            int alt12=7;
            try { dbg.enterDecision(12, decisionCanBacktrack[12]);

            switch ( input.LA(1) ) {
            case LBRACKET:
                {
                alt12=1;
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
                alt12=2;
                }
                break;
            case FLOAT:
                {
                alt12=3;
                }
                break;
            case VAR:
                {
                alt12=4;
                }
                break;
            case IF:
                {
                alt12=5;
                }
                break;
            case MAX:
            case MIN:
                {
                alt12=6;
                }
                break;
            case VARHIST:
                {
                alt12=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;

            }

            } finally {dbg.exitDecision(12);}

            switch (alt12) {
                case 1 :
                    dbg.enterAlt(1);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:174:5: LBRACKET expr RBRACKET
                    {
                    dbg.location(174,5);
                    LBRACKET77=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41278); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET77);

                    dbg.location(174,14);
                    pushFollow(FOLLOW_expr_in_expr41280);
                    expr78=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr78.getTree());
                    dbg.location(174,19);
                    RBRACKET79=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41282); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET79);


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
                    // 174:28: -> expr
                    {
                        dbg.location(174,31);
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:175:4: unaryPrimitives LBRACKET expr RBRACKET
                    {
                    dbg.location(175,4);
                    pushFollow(FOLLOW_unaryPrimitives_in_expr41291);
                    unaryPrimitives80=unaryPrimitives();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryPrimitives.add(unaryPrimitives80.getTree());
                    dbg.location(175,20);
                    LBRACKET81=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41293); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET81);

                    dbg.location(175,29);
                    pushFollow(FOLLOW_expr_in_expr41295);
                    expr82=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr82.getTree());
                    dbg.location(175,34);
                    RBRACKET83=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41297); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET83);


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
                    // 175:43: -> ^( unaryPrimitives expr )
                    {
                        dbg.location(175,46);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:175:46: ^( unaryPrimitives expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(175,48);
                        root_1 = (Object)adaptor.becomeRoot(stream_unaryPrimitives.nextNode(), root_1);

                        dbg.location(175,64);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:176:4: FLOAT
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(176,4);
                    FLOAT84=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_expr41310); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT84_tree = 
                    (Object)adaptor.create(FLOAT84)
                    ;
                    adaptor.addChild(root_0, FLOAT84_tree);
                    }

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:177:4: VAR
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(177,4);
                    VAR85=(Token)match(input,VAR,FOLLOW_VAR_in_expr41315); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR85_tree = 
                    (Object)adaptor.create(VAR85)
                    ;
                    adaptor.addChild(root_0, VAR85_tree);
                    }

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:178:4: IF bExpr THEN expr ELSE expr
                    {
                    dbg.location(178,4);
                    IF86=(Token)match(input,IF,FOLLOW_IF_in_expr41320); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF86);

                    dbg.location(178,7);
                    pushFollow(FOLLOW_bExpr_in_expr41322);
                    bExpr87=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr87.getTree());
                    dbg.location(178,13);
                    THEN88=(Token)match(input,THEN,FOLLOW_THEN_in_expr41324); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN88);

                    dbg.location(178,18);
                    pushFollow(FOLLOW_expr_in_expr41326);
                    expr89=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr89.getTree());
                    dbg.location(178,23);
                    ELSE90=(Token)match(input,ELSE,FOLLOW_ELSE_in_expr41328); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELSE.add(ELSE90);

                    dbg.location(178,28);
                    pushFollow(FOLLOW_expr_in_expr41330);
                    expr91=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr91.getTree());

                    // AST REWRITE
                    // elements: bExpr, expr, IF, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 178:33: -> ^( IF bExpr expr expr )
                    {
                        dbg.location(178,36);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:178:36: ^( IF bExpr expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(178,38);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_IF.nextNode()
                        , root_1);

                        dbg.location(178,41);
                        adaptor.addChild(root_1, stream_bExpr.nextTree());
                        dbg.location(178,47);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(178,52);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:179:4: binPrim LBRACKET expr COMMA expr RBRACKET
                    {
                    dbg.location(179,4);
                    pushFollow(FOLLOW_binPrim_in_expr41347);
                    binPrim92=binPrim();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binPrim.add(binPrim92.getTree());
                    dbg.location(179,12);
                    LBRACKET93=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41349); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET93);

                    dbg.location(179,21);
                    pushFollow(FOLLOW_expr_in_expr41351);
                    expr94=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr94.getTree());
                    dbg.location(179,26);
                    COMMA95=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41353); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA95);

                    dbg.location(179,32);
                    pushFollow(FOLLOW_expr_in_expr41355);
                    expr96=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr96.getTree());
                    dbg.location(179,37);
                    RBRACKET97=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41357); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET97);


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
                    // 179:46: -> ^( binPrim expr expr )
                    {
                        dbg.location(179,49);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:179:49: ^( binPrim expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(179,51);
                        root_1 = (Object)adaptor.becomeRoot(stream_binPrim.nextNode(), root_1);

                        dbg.location(179,59);
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        dbg.location(179,64);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:180:4: VARHIST LBRACKET VAR COMMA expr RBRACKET
                    {
                    dbg.location(180,4);
                    VARHIST98=(Token)match(input,VARHIST,FOLLOW_VARHIST_in_expr41372); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VARHIST.add(VARHIST98);

                    dbg.location(180,12);
                    LBRACKET99=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41374); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET99);

                    dbg.location(180,21);
                    VAR100=(Token)match(input,VAR,FOLLOW_VAR_in_expr41376); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR100);

                    dbg.location(180,25);
                    COMMA101=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41378); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA101);

                    dbg.location(180,31);
                    pushFollow(FOLLOW_expr_in_expr41380);
                    expr102=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr102.getTree());
                    dbg.location(180,36);
                    RBRACKET103=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41382); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET103);


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
                    // 180:45: -> ^( VARHIST VAR expr )
                    {
                        dbg.location(180,48);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:180:48: ^( VARHIST VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(180,50);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_VARHIST.nextNode()
                        , root_1);

                        dbg.location(180,58);
                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );
                        dbg.location(180,62);
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
            if ( state.backtracking>0 ) { memoize(input, 9, expr4_StartIndex); }

        }
        dbg.location(181, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:184:1: bExpr : bExpr2 ( lowPrecBoolOp ^ bExpr2 )* ;
    public final TestLexerParser.bExpr_return bExpr() throws RecognitionException {
        TestLexerParser.bExpr_return retval = new TestLexerParser.bExpr_return();
        retval.start = input.LT(1);

        int bExpr_StartIndex = input.index();

        Object root_0 = null;

        TestLexerParser.bExpr2_return bExpr2104 =null;

        TestLexerParser.lowPrecBoolOp_return lowPrecBoolOp105 =null;

        TestLexerParser.bExpr2_return bExpr2106 =null;



        try { dbg.enterRule(getGrammarFileName(), "bExpr");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(184, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:185:2: ( bExpr2 ( lowPrecBoolOp ^ bExpr2 )* )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:185:4: bExpr2 ( lowPrecBoolOp ^ bExpr2 )*
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(185,4);
            pushFollow(FOLLOW_bExpr2_in_bExpr1404);
            bExpr2104=bExpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2104.getTree());
            dbg.location(185,11);
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:185:11: ( lowPrecBoolOp ^ bExpr2 )*
            try { dbg.enterSubRule(13);

            loop13:
            do {
                int alt13=2;
                try { dbg.enterDecision(13, decisionCanBacktrack[13]);

                int LA13_0 = input.LA(1);

                if ( (LA13_0==AND||LA13_0==OR) ) {
                    alt13=1;
                }


                } finally {dbg.exitDecision(13);}

                switch (alt13) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:185:12: lowPrecBoolOp ^ bExpr2
            	    {
            	    dbg.location(185,25);
            	    pushFollow(FOLLOW_lowPrecBoolOp_in_bExpr1407);
            	    lowPrecBoolOp105=lowPrecBoolOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecBoolOp105.getTree(), root_0);
            	    dbg.location(185,27);
            	    pushFollow(FOLLOW_bExpr2_in_bExpr1410);
            	    bExpr2106=bExpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2106.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 10, bExpr_StartIndex); }

        }
        dbg.location(186, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:188:1: bExpr2 : ( expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr );
    public final TestLexerParser.bExpr2_return bExpr2() throws RecognitionException {
        TestLexerParser.bExpr2_return retval = new TestLexerParser.bExpr2_return();
        retval.start = input.LT(1);

        int bExpr2_StartIndex = input.index();

        Object root_0 = null;

        Token NOT110=null;
        Token LBRACKET111=null;
        Token RBRACKET113=null;
        Token LBRACKET114=null;
        Token RBRACKET116=null;
        TestLexerParser.expr_return expr107 =null;

        TestLexerParser.comparators_return comparators108 =null;

        TestLexerParser.expr_return expr109 =null;

        TestLexerParser.bExpr_return bExpr112 =null;

        TestLexerParser.bExpr_return bExpr115 =null;


        Object NOT110_tree=null;
        Object LBRACKET111_tree=null;
        Object RBRACKET113_tree=null;
        Object LBRACKET114_tree=null;
        Object RBRACKET116_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        try { dbg.enterRule(getGrammarFileName(), "bExpr2");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(188, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:189:2: ( expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr )
            int alt14=3;
            try { dbg.enterDecision(14, decisionCanBacktrack[14]);

            switch ( input.LA(1) ) {
            case LBRACKET:
                {
                int LA14_1 = input.LA(2);

                if ( (synpred27_TestLexer()) ) {
                    alt14=1;
                }
                else if ( (true) ) {
                    alt14=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

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
                {
                alt14=1;
                }
                break;
            case NOT:
                {
                alt14=2;
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:189:4: expr comparators ^ expr
                    {
                    root_0 = (Object)adaptor.nil();


                    dbg.location(189,4);
                    pushFollow(FOLLOW_expr_in_bExpr21425);
                    expr107=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr107.getTree());
                    dbg.location(189,20);
                    pushFollow(FOLLOW_comparators_in_bExpr21427);
                    comparators108=comparators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(comparators108.getTree(), root_0);
                    dbg.location(189,22);
                    pushFollow(FOLLOW_expr_in_bExpr21430);
                    expr109=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr109.getTree());

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:190:4: NOT LBRACKET bExpr RBRACKET
                    {
                    dbg.location(190,4);
                    NOT110=(Token)match(input,NOT,FOLLOW_NOT_in_bExpr21435); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT110);

                    dbg.location(190,8);
                    LBRACKET111=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21437); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET111);

                    dbg.location(190,17);
                    pushFollow(FOLLOW_bExpr_in_bExpr21439);
                    bExpr112=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr112.getTree());
                    dbg.location(190,23);
                    RBRACKET113=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21441); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET113);


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
                    // 190:32: -> ^( NOT bExpr )
                    {
                        dbg.location(190,35);
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:190:35: ^( NOT bExpr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        dbg.location(190,37);
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_NOT.nextNode()
                        , root_1);

                        dbg.location(190,41);
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

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:191:4: LBRACKET bExpr RBRACKET
                    {
                    dbg.location(191,4);
                    LBRACKET114=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21454); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET114);

                    dbg.location(191,13);
                    pushFollow(FOLLOW_bExpr_in_bExpr21456);
                    bExpr115=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr115.getTree());
                    dbg.location(191,19);
                    RBRACKET116=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21458); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET116);


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
                    // 191:28: -> bExpr
                    {
                        dbg.location(191,31);
                        adaptor.addChild(root_0, stream_bExpr.nextTree());

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
            if ( state.backtracking>0 ) { memoize(input, 11, bExpr2_StartIndex); }

        }
        dbg.location(192, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:197:1: unaryPrimitives : ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE );
    public final TestLexerParser.unaryPrimitives_return unaryPrimitives() throws RecognitionException {
        TestLexerParser.unaryPrimitives_return retval = new TestLexerParser.unaryPrimitives_return();
        retval.start = input.LT(1);

        int unaryPrimitives_StartIndex = input.index();

        Object root_0 = null;

        Token set117=null;

        Object set117_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "unaryPrimitives");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(197, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:198:2: ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(198,2);
            set117=(Token)input.LT(1);

            if ( (input.LA(1) >= ABS && input.LA(1) <= ACOS)||input.LA(1)==ASIN||input.LA(1)==ATAN||input.LA(1)==COS||(input.LA(1) >= DENSITYAT && input.LA(1) <= DEPTHFORVI)||input.LA(1)==EXP||input.LA(1)==FULLIRRADAT||input.LA(1)==INTEGRATE||(input.LA(1) >= LN && input.LA(1) <= LOGTEN)||input.LA(1)==RND||(input.LA(1) >= SALINITYAT && input.LA(1) <= SIN)||(input.LA(1) >= SQRT && input.LA(1) <= TEMPAT)||input.LA(1)==UVIRRADAT ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set117)
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
            if ( state.backtracking>0 ) { memoize(input, 12, unaryPrimitives_StartIndex); }

        }
        dbg.location(218, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:220:1: lowPrecMathOp : ( PLUS | MINUS );
    public final TestLexerParser.lowPrecMathOp_return lowPrecMathOp() throws RecognitionException {
        TestLexerParser.lowPrecMathOp_return retval = new TestLexerParser.lowPrecMathOp_return();
        retval.start = input.LT(1);

        int lowPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set118=null;

        Object set118_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "lowPrecMathOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(220, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:221:2: ( PLUS | MINUS )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(221,2);
            set118=(Token)input.LT(1);

            if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set118)
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
            if ( state.backtracking>0 ) { memoize(input, 13, lowPrecMathOp_StartIndex); }

        }
        dbg.location(223, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:225:1: medPrecMathOp : ( MUL | DIV );
    public final TestLexerParser.medPrecMathOp_return medPrecMathOp() throws RecognitionException {
        TestLexerParser.medPrecMathOp_return retval = new TestLexerParser.medPrecMathOp_return();
        retval.start = input.LT(1);

        int medPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set119=null;

        Object set119_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "medPrecMathOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(225, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:226:2: ( MUL | DIV )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(226,2);
            set119=(Token)input.LT(1);

            if ( input.LA(1)==DIV||input.LA(1)==MUL ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set119)
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
            if ( state.backtracking>0 ) { memoize(input, 14, medPrecMathOp_StartIndex); }

        }
        dbg.location(228, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:230:1: highPrecMathOp : POW ;
    public final TestLexerParser.highPrecMathOp_return highPrecMathOp() throws RecognitionException {
        TestLexerParser.highPrecMathOp_return retval = new TestLexerParser.highPrecMathOp_return();
        retval.start = input.LT(1);

        int highPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token POW120=null;

        Object POW120_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "highPrecMathOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(230, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:231:2: ( POW )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:231:4: POW
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(231,4);
            POW120=(Token)match(input,POW,FOLLOW_POW_in_highPrecMathOp1619); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            POW120_tree = 
            (Object)adaptor.create(POW120)
            ;
            adaptor.addChild(root_0, POW120_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 15, highPrecMathOp_StartIndex); }

        }
        dbg.location(232, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:234:1: binPrim : ( MIN | MAX );
    public final TestLexerParser.binPrim_return binPrim() throws RecognitionException {
        TestLexerParser.binPrim_return retval = new TestLexerParser.binPrim_return();
        retval.start = input.LT(1);

        int binPrim_StartIndex = input.index();

        Object root_0 = null;

        Token set121=null;

        Object set121_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "binPrim");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(234, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:235:2: ( MIN | MAX )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(235,2);
            set121=(Token)input.LT(1);

            if ( (input.LA(1) >= MAX && input.LA(1) <= MIN) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set121)
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
            if ( state.backtracking>0 ) { memoize(input, 16, binPrim_StartIndex); }

        }
        dbg.location(237, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:239:1: lowPrecBoolOp : ( AND | OR );
    public final TestLexerParser.lowPrecBoolOp_return lowPrecBoolOp() throws RecognitionException {
        TestLexerParser.lowPrecBoolOp_return retval = new TestLexerParser.lowPrecBoolOp_return();
        retval.start = input.LT(1);

        int lowPrecBoolOp_StartIndex = input.index();

        Object root_0 = null;

        Token set122=null;

        Object set122_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "lowPrecBoolOp");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(239, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:240:2: ( AND | OR )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(240,2);
            set122=(Token)input.LT(1);

            if ( input.LA(1)==AND||input.LA(1)==OR ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set122)
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
            if ( state.backtracking>0 ) { memoize(input, 17, lowPrecBoolOp_StartIndex); }

        }
        dbg.location(242, 1);

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:244:1: comparators : ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS );
    public final TestLexerParser.comparators_return comparators() throws RecognitionException {
        TestLexerParser.comparators_return retval = new TestLexerParser.comparators_return();
        retval.start = input.LT(1);

        int comparators_StartIndex = input.index();

        Object root_0 = null;

        Token set123=null;

        Object set123_tree=null;

        try { dbg.enterRule(getGrammarFileName(), "comparators");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(244, 0);

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:245:2: ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS )
            dbg.enterAlt(1);

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:
            {
            root_0 = (Object)adaptor.nil();


            dbg.location(245,2);
            set123=(Token)input.LT(1);

            if ( input.LA(1)==EQUALS||(input.LA(1) >= GREATEREQUALS && input.LA(1) <= GREATERTHAN)||(input.LA(1) >= LESSEQUALS && input.LA(1) <= LESSTHAN)||input.LA(1)==NEQUALS ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set123)
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
            if ( state.backtracking>0 ) { memoize(input, 18, comparators_StartIndex); }

        }
        dbg.location(251, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "comparators");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return retval;
    }
    // $ANTLR end "comparators"

    // $ANTLR start synpred17_TestLexer
    public final void synpred17_TestLexer_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:161:11: ( lowPrecMathOp expr2 )
        dbg.enterAlt(1);

        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:161:11: lowPrecMathOp expr2
        {
        dbg.location(161,11);
        pushFollow(FOLLOW_lowPrecMathOp_in_synpred17_TestLexer1218);
        lowPrecMathOp();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(161,26);
        pushFollow(FOLLOW_expr2_in_synpred17_TestLexer1221);
        expr2();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred17_TestLexer

    // $ANTLR start synpred18_TestLexer
    public final void synpred18_TestLexer_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:165:11: ( medPrecMathOp expr3 )
        dbg.enterAlt(1);

        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:165:11: medPrecMathOp expr3
        {
        dbg.location(165,11);
        pushFollow(FOLLOW_medPrecMathOp_in_synpred18_TestLexer1238);
        medPrecMathOp();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(165,27);
        pushFollow(FOLLOW_expr3_in_synpred18_TestLexer1242);
        expr3();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred18_TestLexer

    // $ANTLR start synpred19_TestLexer
    public final void synpred19_TestLexer_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:169:11: ( highPrecMathOp expr4 )
        dbg.enterAlt(1);

        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:169:11: highPrecMathOp expr4
        {
        dbg.location(169,11);
        pushFollow(FOLLOW_highPrecMathOp_in_synpred19_TestLexer1259);
        highPrecMathOp();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(169,27);
        pushFollow(FOLLOW_expr4_in_synpred19_TestLexer1262);
        expr4();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_TestLexer

    // $ANTLR start synpred27_TestLexer
    public final void synpred27_TestLexer_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:189:4: ( expr comparators expr )
        dbg.enterAlt(1);

        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\TestLexer.g:189:4: expr comparators expr
        {
        dbg.location(189,4);
        pushFollow(FOLLOW_expr_in_synpred27_TestLexer1425);
        expr();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(189,9);
        pushFollow(FOLLOW_comparators_in_synpred27_TestLexer1427);
        comparators();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(189,22);
        pushFollow(FOLLOW_expr_in_synpred27_TestLexer1430);
        expr();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred27_TestLexer

    // Delegated rules

    public final boolean synpred27_TestLexer() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred27_TestLexer_fragment(); // can never throw exception
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
    public final boolean synpred18_TestLexer() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred18_TestLexer_fragment(); // can never throw exception
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
    public final boolean synpred17_TestLexer() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred17_TestLexer_fragment(); // can never throw exception
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
    public final boolean synpred19_TestLexer() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred19_TestLexer_fragment(); // can never throw exception
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


 

    public static final BitSet FOLLOW_ruleName_in_rules885 = new BitSet(new long[]{0x0044000540410800L,0x0000000000000014L});
    public static final BitSet FOLLOW_rule_in_rules889 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_NEWLINE_in_rules891 = new BitSet(new long[]{0x0244000540410802L,0x0000000000000014L});
    public static final BitSet FOLLOW_RULENAME_in_ruleName918 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_ruleName920 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_ruleName930 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_ruleName932 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_rule949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_rule954 = new BitSet(new long[]{0xEC8106C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_bExpr_in_rule956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_rule958 = new BitSet(new long[]{0x0044000540410800L,0x0000000000000014L});
    public static final BitSet FOLLOW_rule_in_rule960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPTAKE_in_rule975 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule977 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_VAR_in_rule979 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_rule981 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_rule983 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELEASE_in_rule1000 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule1002 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_VAR_in_rule1004 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_rule1006 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_rule1008 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule1010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INGEST_in_rule1025 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule1027 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_VAR_in_rule1029 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_rule1031 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_rule1033 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_rule1035 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_rule1037 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule1056 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule1058 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_VAR_in_rule1060 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule1062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PCHANGE_in_rule1075 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule1077 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_VAR_in_rule1079 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_rule1081 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_rule1083 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDE_in_rule1100 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule1102 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_rule1104 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule1106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_rule1119 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule1121 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_VAR_in_rule1123 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_rule1125 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_rule1127 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule1129 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_WITH_in_rule1135 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LSQUARE_in_rule1137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_assignList_in_rule1139 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_RSQUARE_in_rule1141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_rule1148 = new BitSet(new long[]{0x0044000540410800L,0x0000000000000014L});
    public static final BitSet FOLLOW_rule_in_rule1150 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_assign1168 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_EQUALS_in_assign1170 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_assign1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_assignList1195 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_assignList1198 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_assign_in_assignList1200 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_expr2_in_expr1215 = new BitSet(new long[]{0x0008080000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_expr1218 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr2_in_expr1221 = new BitSet(new long[]{0x0008080000000002L});
    public static final BitSet FOLLOW_expr3_in_expr21235 = new BitSet(new long[]{0x0000100000200002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_expr21238 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr3_in_expr21242 = new BitSet(new long[]{0x0000100000200002L});
    public static final BitSet FOLLOW_expr4_in_expr31256 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_expr31259 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr4_in_expr31262 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41278 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_expr41280 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryPrimitives_in_expr41291 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41293 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_expr41295 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_expr41310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_expr41315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr41320 = new BitSet(new long[]{0xEC8106C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_bExpr_in_expr41322 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_THEN_in_expr41324 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_expr41326 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_ELSE_in_expr41328 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_expr41330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binPrim_in_expr41347 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41349 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_expr41351 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_expr41353 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_expr41355 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARHIST_in_expr41372 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_VAR_in_expr41376 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_expr41378 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_expr41380 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1404 = new BitSet(new long[]{0x0002000000000082L});
    public static final BitSet FOLLOW_lowPrecBoolOp_in_bExpr1407 = new BitSet(new long[]{0xEC8106C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1410 = new BitSet(new long[]{0x0002000000000082L});
    public static final BitSet FOLLOW_expr_in_bExpr21425 = new BitSet(new long[]{0x0000201831000000L});
    public static final BitSet FOLLOW_comparators_in_bExpr21427 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_bExpr21430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bExpr21435 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21437 = new BitSet(new long[]{0xEC8106C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21439 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21454 = new BitSet(new long[]{0xEC8106C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21456 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POW_in_highPrecMathOp1619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_synpred17_TestLexer1218 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr2_in_synpred17_TestLexer1221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_synpred18_TestLexer1238 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr3_in_synpred18_TestLexer1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_synpred19_TestLexer1259 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr4_in_synpred19_TestLexer1262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred27_TestLexer1425 = new BitSet(new long[]{0x0000201831000000L});
    public static final BitSet FOLLOW_comparators_in_synpred27_TestLexer1427 = new BitSet(new long[]{0xEC8006C64E0E8530L,0x0000000000000038L});
    public static final BitSet FOLLOW_expr_in_synpred27_TestLexer1430 = new BitSet(new long[]{0x0000000000000002L});

}