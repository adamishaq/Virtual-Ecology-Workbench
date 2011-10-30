// $ANTLR 3.4 E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2011-10-30 19:10:35

package VEW.XMLCompiler.ANTLR.output;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class BACONParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACOS", "ALL", "AND", "ASIN", "ASSIGN", "ASSIGNLIST", "ATAN", "CHANGE", "COLON", "COMMA", "COMMENT", "COS", "CREATE", "DENSITYAT", "DEPTHFORFI", "DEPTHFORVI", "DIGIT", "DIV", "DIVIDE", "ELSE", "EQUALS", "EXP", "FLOAT", "FULLIRRADAT", "GREATEREQUALS", "GREATERTHAN", "IF", "IGNORE", "INGEST", "INTEGRATE", "LBRACKET", "LESSEQUALS", "LESSTHAN", "LETTER", "LN", "LOGTEN", "LSQUARE", "MAX", "MIN", "MINUS", "MUL", "NEQUALS", "NEWLINE", "NONE", "NOT", "OR", "PCHANGE", "PLUS", "POW", "RBRACKET", "RELEASE", "RND", "RSQUARE", "RULE", "RULENAME", "RULES", "SALINITYAT", "SIN", "SOME", "SQRT", "TAN", "TEMPAT", "THEN", "UNKNOWN", "UPTAKE", "UVIRRADAT", "VAR", "VARHIST", "VAVERAGE", "VPRODUCT", "VSUM", "WITH"
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
    public static final int CHANGE=12;
    public static final int COLON=13;
    public static final int COMMA=14;
    public static final int COMMENT=15;
    public static final int COS=16;
    public static final int CREATE=17;
    public static final int DENSITYAT=18;
    public static final int DEPTHFORFI=19;
    public static final int DEPTHFORVI=20;
    public static final int DIGIT=21;
    public static final int DIV=22;
    public static final int DIVIDE=23;
    public static final int ELSE=24;
    public static final int EQUALS=25;
    public static final int EXP=26;
    public static final int FLOAT=27;
    public static final int FULLIRRADAT=28;
    public static final int GREATEREQUALS=29;
    public static final int GREATERTHAN=30;
    public static final int IF=31;
    public static final int IGNORE=32;
    public static final int INGEST=33;
    public static final int INTEGRATE=34;
    public static final int LBRACKET=35;
    public static final int LESSEQUALS=36;
    public static final int LESSTHAN=37;
    public static final int LETTER=38;
    public static final int LN=39;
    public static final int LOGTEN=40;
    public static final int LSQUARE=41;
    public static final int MAX=42;
    public static final int MIN=43;
    public static final int MINUS=44;
    public static final int MUL=45;
    public static final int NEQUALS=46;
    public static final int NEWLINE=47;
    public static final int NONE=48;
    public static final int NOT=49;
    public static final int OR=50;
    public static final int PCHANGE=51;
    public static final int PLUS=52;
    public static final int POW=53;
    public static final int RBRACKET=54;
    public static final int RELEASE=55;
    public static final int RND=56;
    public static final int RSQUARE=57;
    public static final int RULE=58;
    public static final int RULENAME=59;
    public static final int RULES=60;
    public static final int SALINITYAT=61;
    public static final int SIN=62;
    public static final int SOME=63;
    public static final int SQRT=64;
    public static final int TAN=65;
    public static final int TEMPAT=66;
    public static final int THEN=67;
    public static final int UNKNOWN=68;
    public static final int UPTAKE=69;
    public static final int UVIRRADAT=70;
    public static final int VAR=71;
    public static final int VARHIST=72;
    public static final int VAVERAGE=73;
    public static final int VPRODUCT=74;
    public static final int VSUM=75;
    public static final int WITH=76;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public BACONParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public BACONParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[86+1];
         

    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return BACONParser.tokenNames; }
    public String getGrammarFileName() { return "E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g"; }


    public static class rules_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rules"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:129:1: rules : ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )? -> ^( RULES pair ( pair )* ) ;
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
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:2: ( ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )? -> ^( RULES pair ( pair )* ) )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:4: ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )?
            {
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:4: ( NEWLINE )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==NEWLINE) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:5: NEWLINE
                    {
                    NEWLINE1=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules897); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE1);


                    }
                    break;

            }


            pushFollow(FOLLOW_pair_in_rules901);
            pair2=pair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pair.add(pair2.getTree());

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:20: ( NEWLINE pair )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==NEWLINE) ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1==CHANGE||LA2_1==CREATE||LA2_1==DIVIDE||LA2_1==IF||LA2_1==INGEST||LA2_1==LBRACKET||LA2_1==PCHANGE||LA2_1==RELEASE||LA2_1==RULENAME||LA2_1==UPTAKE||LA2_1==VAR) ) {
                        alt2=1;
                    }


                }


                switch (alt2) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:21: NEWLINE pair
            	    {
            	    NEWLINE3=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules904); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE3);


            	    pushFollow(FOLLOW_pair_in_rules906);
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


            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:36: ( NEWLINE )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==NEWLINE) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:37: NEWLINE
                    {
                    NEWLINE5=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules911); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE5);


                    }
                    break;

            }


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
            // 130:47: -> ^( RULES pair ( pair )* )
            {
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:50: ^( RULES pair ( pair )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(RULES, "RULES")
                , root_1);

                adaptor.addChild(root_1, stream_pair.nextTree());

                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:130:63: ( pair )*
                while ( stream_pair.hasNext() ) {
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
        return retval;
    }
    // $ANTLR end "rules"


    public static class pair_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pair"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:133:1: pair : ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule );
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
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:134:2: ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule )
            int alt4=2;
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

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:134:4: ruleName rule2
                    {
                    pushFollow(FOLLOW_ruleName_in_pair937);
                    ruleName6=ruleName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleName.add(ruleName6.getTree());

                    pushFollow(FOLLOW_rule2_in_pair939);
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
                    // 134:19: -> ^( RULE ruleName rule2 )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:134:22: ^( RULE ruleName rule2 )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(RULE, "RULE")
                        , root_1);

                        adaptor.addChild(root_1, stream_ruleName.nextTree());

                        adaptor.addChild(root_1, stream_rule2.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:4: rule
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_rule_in_pair954);
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
        return retval;
    }
    // $ANTLR end "pair"


    public static class ruleName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ruleName"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:138:1: ruleName : ( RULENAME COLON ( NEWLINE )? -> RULENAME | VAR COLON ( NEWLINE )? -> VAR );
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

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:139:2: ( RULENAME COLON ( NEWLINE )? -> RULENAME | VAR COLON ( NEWLINE )? -> VAR )
            int alt7=2;
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

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:139:4: RULENAME COLON ( NEWLINE )?
                    {
                    RULENAME9=(Token)match(input,RULENAME,FOLLOW_RULENAME_in_ruleName966); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULENAME.add(RULENAME9);


                    COLON10=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName968); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON10);


                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:139:19: ( NEWLINE )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==NEWLINE) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:139:20: NEWLINE
                            {
                            NEWLINE11=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName971); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE11);


                            }
                            break;

                    }


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
                    // 139:30: -> RULENAME
                    {
                        adaptor.addChild(root_0, 
                        stream_RULENAME.nextNode()
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:140:4: VAR COLON ( NEWLINE )?
                    {
                    VAR12=(Token)match(input,VAR,FOLLOW_VAR_in_ruleName982); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR12);


                    COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName984); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON13);


                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:140:14: ( NEWLINE )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==NEWLINE) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:140:15: NEWLINE
                            {
                            NEWLINE14=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName987); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE14);


                            }
                            break;

                    }


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
                    // 140:25: -> VAR
                    {
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
        return retval;
    }
    // $ANTLR end "ruleName"


    public static class rule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rule"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:143:1: rule : rule2 -> ^( RULE rule2 ) ;
    public final BACONParser.rule_return rule() throws RecognitionException {
        BACONParser.rule_return retval = new BACONParser.rule_return();
        retval.start = input.LT(1);

        int rule_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.rule2_return rule215 =null;


        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:144:2: ( rule2 -> ^( RULE rule2 ) )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:144:4: rule2
            {
            pushFollow(FOLLOW_rule2_in_rule1004);
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
            // 144:10: -> ^( RULE rule2 )
            {
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:144:13: ^( RULE rule2 )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(RULE, "RULE")
                , root_1);

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
        return retval;
    }
    // $ANTLR end "rule"


    public static class rule2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "rule2"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:147:1: rule2 : ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule RBRACKET -> rule );
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
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:148:2: ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule RBRACKET -> rule )
            int alt9=10;
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

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:148:4: assign
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assign_in_rule21023);
                    assign16=assign();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign16.getTree());

                    }
                    break;
                case 2 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:149:4: IF bExpr THEN rule
                    {
                    IF17=(Token)match(input,IF,FOLLOW_IF_in_rule21028); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF17);


                    pushFollow(FOLLOW_bExpr_in_rule21030);
                    bExpr18=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr18.getTree());

                    THEN19=(Token)match(input,THEN,FOLLOW_THEN_in_rule21032); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN19);


                    pushFollow(FOLLOW_rule_in_rule21034);
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
                    // 149:23: -> ^( IF bExpr rule )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:149:26: ^( IF bExpr rule )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_IF.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_bExpr.nextTree());

                        adaptor.addChild(root_1, stream_rule.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:150:4: UPTAKE LBRACKET VAR COMMA expr RBRACKET
                    {
                    UPTAKE21=(Token)match(input,UPTAKE,FOLLOW_UPTAKE_in_rule21049); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UPTAKE.add(UPTAKE21);


                    LBRACKET22=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21051); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET22);


                    VAR23=(Token)match(input,VAR,FOLLOW_VAR_in_rule21053); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR23);


                    COMMA24=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21055); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA24);


                    pushFollow(FOLLOW_expr_in_rule21057);
                    expr25=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr25.getTree());

                    RBRACKET26=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21059); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET26);


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
                    // 150:44: -> ^( UPTAKE VAR expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:150:47: ^( UPTAKE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_UPTAKE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:151:4: RELEASE LBRACKET VAR COMMA expr RBRACKET
                    {
                    RELEASE27=(Token)match(input,RELEASE,FOLLOW_RELEASE_in_rule21074); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RELEASE.add(RELEASE27);


                    LBRACKET28=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21076); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET28);


                    VAR29=(Token)match(input,VAR,FOLLOW_VAR_in_rule21078); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR29);


                    COMMA30=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21080); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA30);


                    pushFollow(FOLLOW_expr_in_rule21082);
                    expr31=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr31.getTree());

                    RBRACKET32=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21084); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET32);


                    // AST REWRITE
                    // elements: RELEASE, VAR, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 151:45: -> ^( RELEASE VAR expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:151:48: ^( RELEASE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_RELEASE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:152:4: INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET
                    {
                    INGEST33=(Token)match(input,INGEST,FOLLOW_INGEST_in_rule21099); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INGEST.add(INGEST33);


                    LBRACKET34=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21101); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET34);


                    VAR35=(Token)match(input,VAR,FOLLOW_VAR_in_rule21103); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR35);


                    COMMA36=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21105); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA36);


                    pushFollow(FOLLOW_expr_in_rule21107);
                    expr37=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr37.getTree());

                    COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21109); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA38);


                    pushFollow(FOLLOW_expr_in_rule21111);
                    expr39=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr39.getTree());

                    RBRACKET40=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21113); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET40);


                    // AST REWRITE
                    // elements: expr, expr, VAR, INGEST
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 152:55: -> ^( INGEST VAR expr expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:152:58: ^( INGEST VAR expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_INGEST.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:153:4: CHANGE LBRACKET VAR RBRACKET
                    {
                    CHANGE41=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21130); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE41);


                    LBRACKET42=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21132); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET42);


                    VAR43=(Token)match(input,VAR,FOLLOW_VAR_in_rule21134); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR43);


                    RBRACKET44=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21136); if (state.failed) return retval; 
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
                    // 153:33: -> ^( CHANGE VAR )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:153:36: ^( CHANGE VAR )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CHANGE.nextNode()
                        , root_1);

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
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:154:4: PCHANGE LBRACKET VAR COMMA expr RBRACKET
                    {
                    PCHANGE45=(Token)match(input,PCHANGE,FOLLOW_PCHANGE_in_rule21149); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_PCHANGE.add(PCHANGE45);


                    LBRACKET46=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21151); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET46);


                    VAR47=(Token)match(input,VAR,FOLLOW_VAR_in_rule21153); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR47);


                    COMMA48=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21155); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA48);


                    pushFollow(FOLLOW_expr_in_rule21157);
                    expr49=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr49.getTree());

                    RBRACKET50=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21159); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET50);


                    // AST REWRITE
                    // elements: PCHANGE, VAR, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 154:45: -> ^( PCHANGE VAR expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:154:48: ^( PCHANGE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_PCHANGE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 8 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:155:4: DIVIDE LBRACKET expr RBRACKET
                    {
                    DIVIDE51=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_rule21174); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIVIDE.add(DIVIDE51);


                    LBRACKET52=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21176); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET52);


                    pushFollow(FOLLOW_expr_in_rule21178);
                    expr53=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr53.getTree());

                    RBRACKET54=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21180); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET54);


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
                    // 155:34: -> ^( DIVIDE expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:155:37: ^( DIVIDE expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_DIVIDE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 9 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:156:4: CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )?
                    {
                    CREATE55=(Token)match(input,CREATE,FOLLOW_CREATE_in_rule21193); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CREATE.add(CREATE55);


                    LBRACKET56=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21195); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET56);


                    VAR57=(Token)match(input,VAR,FOLLOW_VAR_in_rule21197); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR57);


                    COMMA58=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21199); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA58);


                    pushFollow(FOLLOW_expr_in_rule21201);
                    expr59=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr59.getTree());

                    RBRACKET60=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21203); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET60);


                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:157:3: ( WITH LSQUARE assignList RSQUARE )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==WITH) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:157:4: WITH LSQUARE assignList RSQUARE
                            {
                            WITH61=(Token)match(input,WITH,FOLLOW_WITH_in_rule21209); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_WITH.add(WITH61);


                            LSQUARE62=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_rule21211); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE62);


                            pushFollow(FOLLOW_assignList_in_rule21213);
                            assignList63=assignList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignList.add(assignList63.getTree());

                            RSQUARE64=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_rule21215); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE64);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: expr, VAR, assignList, CREATE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 157:38: -> ^( CREATE VAR expr ( assignList )? )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:157:41: ^( CREATE VAR expr ( assignList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CREATE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:157:59: ( assignList )?
                        if ( stream_assignList.hasNext() ) {
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
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:158:4: LBRACKET rule RBRACKET
                    {
                    LBRACKET65=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21237); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET65);


                    pushFollow(FOLLOW_rule_in_rule21239);
                    rule66=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule66.getTree());

                    RBRACKET67=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21241); if (state.failed) return retval; 
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
                    // 158:27: -> rule
                    {
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
        return retval;
    }
    // $ANTLR end "rule2"


    public static class assign_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assign"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:161:1: assign : VAR EQUALS expr -> ^( ASSIGN VAR expr ) ;
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
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:162:2: ( VAR EQUALS expr -> ^( ASSIGN VAR expr ) )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:162:4: VAR EQUALS expr
            {
            VAR68=(Token)match(input,VAR,FOLLOW_VAR_in_assign1256); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR68);


            EQUALS69=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assign1258); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS69);


            pushFollow(FOLLOW_expr_in_assign1260);
            expr70=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr70.getTree());

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
            // 162:20: -> ^( ASSIGN VAR expr )
            {
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:162:23: ^( ASSIGN VAR expr )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGN, "ASSIGN")
                , root_1);

                adaptor.addChild(root_1, 
                stream_VAR.nextNode()
                );

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
        return retval;
    }
    // $ANTLR end "assign"


    public static class assignList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignList"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:165:1: assignList : assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) ;
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
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:166:2: ( assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:166:4: assign ( COMMA assign )*
            {
            pushFollow(FOLLOW_assign_in_assignList1281);
            assign71=assign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assign.add(assign71.getTree());

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:166:11: ( COMMA assign )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==COMMA) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:166:12: COMMA assign
            	    {
            	    COMMA72=(Token)match(input,COMMA,FOLLOW_COMMA_in_assignList1284); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA72);


            	    pushFollow(FOLLOW_assign_in_assignList1286);
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
            // 166:27: -> ^( ASSIGNLIST assign ( assign )* )
            {
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:166:30: ^( ASSIGNLIST assign ( assign )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGNLIST, "ASSIGNLIST")
                , root_1);

                adaptor.addChild(root_1, stream_assign.nextTree());

                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:166:50: ( assign )*
                while ( stream_assign.hasNext() ) {
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
        return retval;
    }
    // $ANTLR end "assignList"


    public static class expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:169:1: expr : expr2 ( lowPrecMathOp ^ expr2 )* ;
    public final BACONParser.expr_return expr() throws RecognitionException {
        BACONParser.expr_return retval = new BACONParser.expr_return();
        retval.start = input.LT(1);

        int expr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr2_return expr274 =null;

        BACONParser.lowPrecMathOp_return lowPrecMathOp75 =null;

        BACONParser.expr2_return expr276 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:170:2: ( expr2 ( lowPrecMathOp ^ expr2 )* )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:170:4: expr2 ( lowPrecMathOp ^ expr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr2_in_expr1312);
            expr274=expr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr274.getTree());

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:170:10: ( lowPrecMathOp ^ expr2 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==MINUS||LA11_0==PLUS) ) {
                    int LA11_2 = input.LA(2);

                    if ( (synpred19_BACON()) ) {
                        alt11=1;
                    }


                }


                switch (alt11) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:170:11: lowPrecMathOp ^ expr2
            	    {
            	    pushFollow(FOLLOW_lowPrecMathOp_in_expr1315);
            	    lowPrecMathOp75=lowPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecMathOp75.getTree(), root_0);

            	    pushFollow(FOLLOW_expr2_in_expr1318);
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
        return retval;
    }
    // $ANTLR end "expr"


    public static class expr2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr2"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:173:1: expr2 : expr3 ( medPrecMathOp ^ expr3 )* ;
    public final BACONParser.expr2_return expr2() throws RecognitionException {
        BACONParser.expr2_return retval = new BACONParser.expr2_return();
        retval.start = input.LT(1);

        int expr2_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr3_return expr377 =null;

        BACONParser.medPrecMathOp_return medPrecMathOp78 =null;

        BACONParser.expr3_return expr379 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:174:2: ( expr3 ( medPrecMathOp ^ expr3 )* )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:174:4: expr3 ( medPrecMathOp ^ expr3 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr3_in_expr21332);
            expr377=expr3();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr377.getTree());

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:174:10: ( medPrecMathOp ^ expr3 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==DIV||LA12_0==MUL) ) {
                    int LA12_2 = input.LA(2);

                    if ( (synpred20_BACON()) ) {
                        alt12=1;
                    }


                }


                switch (alt12) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:174:11: medPrecMathOp ^ expr3
            	    {
            	    pushFollow(FOLLOW_medPrecMathOp_in_expr21335);
            	    medPrecMathOp78=medPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(medPrecMathOp78.getTree(), root_0);

            	    pushFollow(FOLLOW_expr3_in_expr21339);
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
        return retval;
    }
    // $ANTLR end "expr2"


    public static class expr3_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr3"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:177:1: expr3 : expr4 ( highPrecMathOp ^ expr4 )* ;
    public final BACONParser.expr3_return expr3() throws RecognitionException {
        BACONParser.expr3_return retval = new BACONParser.expr3_return();
        retval.start = input.LT(1);

        int expr3_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr4_return expr480 =null;

        BACONParser.highPrecMathOp_return highPrecMathOp81 =null;

        BACONParser.expr4_return expr482 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:178:2: ( expr4 ( highPrecMathOp ^ expr4 )* )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:178:4: expr4 ( highPrecMathOp ^ expr4 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr4_in_expr31353);
            expr480=expr4();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr480.getTree());

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:178:10: ( highPrecMathOp ^ expr4 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==POW) ) {
                    int LA13_2 = input.LA(2);

                    if ( (synpred21_BACON()) ) {
                        alt13=1;
                    }


                }


                switch (alt13) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:178:11: highPrecMathOp ^ expr4
            	    {
            	    pushFollow(FOLLOW_highPrecMathOp_in_expr31356);
            	    highPrecMathOp81=highPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(highPrecMathOp81.getTree(), root_0);

            	    pushFollow(FOLLOW_expr4_in_expr31359);
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
        return retval;
    }
    // $ANTLR end "expr3"


    public static class expr4_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr4"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:182:1: expr4 : ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) );
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
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:183:3: ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) )
            int alt14=8;
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

                throw nvae;

            }

            switch (alt14) {
                case 1 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:183:5: LBRACKET expr RBRACKET
                    {
                    LBRACKET83=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41375); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET83);


                    pushFollow(FOLLOW_expr_in_expr41377);
                    expr84=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr84.getTree());

                    RBRACKET85=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41379); if (state.failed) return retval; 
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
                    // 183:28: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:4: unaryPrimitives LBRACKET expr RBRACKET
                    {
                    pushFollow(FOLLOW_unaryPrimitives_in_expr41388);
                    unaryPrimitives86=unaryPrimitives();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryPrimitives.add(unaryPrimitives86.getTree());

                    LBRACKET87=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41390); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET87);


                    pushFollow(FOLLOW_expr_in_expr41392);
                    expr88=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr88.getTree());

                    RBRACKET89=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41394); if (state.failed) return retval; 
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
                    // 184:43: -> ^( unaryPrimitives expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:46: ^( unaryPrimitives expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_unaryPrimitives.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:185:4: FLOAT
                    {
                    root_0 = (Object)adaptor.nil();


                    FLOAT90=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_expr41407); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT90_tree = 
                    (Object)adaptor.create(FLOAT90)
                    ;
                    adaptor.addChild(root_0, FLOAT90_tree);
                    }

                    }
                    break;
                case 4 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:186:4: VAR
                    {
                    root_0 = (Object)adaptor.nil();


                    VAR91=(Token)match(input,VAR,FOLLOW_VAR_in_expr41412); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR91_tree = 
                    (Object)adaptor.create(VAR91)
                    ;
                    adaptor.addChild(root_0, VAR91_tree);
                    }

                    }
                    break;
                case 5 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:187:4: IF bExpr THEN expr ELSE expr
                    {
                    IF92=(Token)match(input,IF,FOLLOW_IF_in_expr41417); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF92);


                    pushFollow(FOLLOW_bExpr_in_expr41419);
                    bExpr93=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr93.getTree());

                    THEN94=(Token)match(input,THEN,FOLLOW_THEN_in_expr41421); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN94);


                    pushFollow(FOLLOW_expr_in_expr41423);
                    expr95=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr95.getTree());

                    ELSE96=(Token)match(input,ELSE,FOLLOW_ELSE_in_expr41425); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELSE.add(ELSE96);


                    pushFollow(FOLLOW_expr_in_expr41427);
                    expr97=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr97.getTree());

                    // AST REWRITE
                    // elements: expr, IF, expr, bExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 187:33: -> ^( IF bExpr expr expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:187:36: ^( IF bExpr expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_IF.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_bExpr.nextTree());

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:188:4: binPrim LBRACKET expr COMMA expr RBRACKET
                    {
                    pushFollow(FOLLOW_binPrim_in_expr41444);
                    binPrim98=binPrim();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binPrim.add(binPrim98.getTree());

                    LBRACKET99=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41446); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET99);


                    pushFollow(FOLLOW_expr_in_expr41448);
                    expr100=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr100.getTree());

                    COMMA101=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41450); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA101);


                    pushFollow(FOLLOW_expr_in_expr41452);
                    expr102=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr102.getTree());

                    RBRACKET103=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41454); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET103);


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
                    // 188:46: -> ^( binPrim expr expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:188:49: ^( binPrim expr expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_binPrim.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 7 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:189:4: VARHIST LBRACKET VAR COMMA expr RBRACKET
                    {
                    VARHIST104=(Token)match(input,VARHIST,FOLLOW_VARHIST_in_expr41469); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VARHIST.add(VARHIST104);


                    LBRACKET105=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41471); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET105);


                    VAR106=(Token)match(input,VAR,FOLLOW_VAR_in_expr41473); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR106);


                    COMMA107=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41475); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA107);


                    pushFollow(FOLLOW_expr_in_expr41477);
                    expr108=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr108.getTree());

                    RBRACKET109=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41479); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET109);


                    // AST REWRITE
                    // elements: expr, VAR, VARHIST
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 189:45: -> ^( VARHIST VAR expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:189:48: ^( VARHIST VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_VARHIST.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 8 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:190:4: vOp LBRACKET expr RBRACKET
                    {
                    pushFollow(FOLLOW_vOp_in_expr41494);
                    vOp110=vOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vOp.add(vOp110.getTree());

                    LBRACKET111=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41496); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET111);


                    pushFollow(FOLLOW_expr_in_expr41498);
                    expr112=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr112.getTree());

                    RBRACKET113=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41500); if (state.failed) return retval; 
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
                    // 190:31: -> ^( vOp expr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:190:34: ^( vOp expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_vOp.nextNode(), root_1);

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
        return retval;
    }
    // $ANTLR end "expr4"


    public static class bExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bExpr"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:194:1: bExpr : bExpr2 ( lowPrecBoolOp ^ bExpr2 )* ;
    public final BACONParser.bExpr_return bExpr() throws RecognitionException {
        BACONParser.bExpr_return retval = new BACONParser.bExpr_return();
        retval.start = input.LT(1);

        int bExpr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.bExpr2_return bExpr2114 =null;

        BACONParser.lowPrecBoolOp_return lowPrecBoolOp115 =null;

        BACONParser.bExpr2_return bExpr2116 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:195:2: ( bExpr2 ( lowPrecBoolOp ^ bExpr2 )* )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:195:4: bExpr2 ( lowPrecBoolOp ^ bExpr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_bExpr2_in_bExpr1520);
            bExpr2114=bExpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2114.getTree());

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:195:11: ( lowPrecBoolOp ^ bExpr2 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==AND||LA15_0==OR) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:195:12: lowPrecBoolOp ^ bExpr2
            	    {
            	    pushFollow(FOLLOW_lowPrecBoolOp_in_bExpr1523);
            	    lowPrecBoolOp115=lowPrecBoolOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecBoolOp115.getTree(), root_0);

            	    pushFollow(FOLLOW_bExpr2_in_bExpr1526);
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
        return retval;
    }
    // $ANTLR end "bExpr"


    public static class bExpr2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bExpr2"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:198:1: bExpr2 : ( expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) );
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
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:199:2: ( expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) )
            int alt16=4;
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

                throw nvae;

            }

            switch (alt16) {
                case 1 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:199:4: expr comparators ^ expr
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_bExpr21541);
                    expr117=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr117.getTree());

                    pushFollow(FOLLOW_comparators_in_bExpr21543);
                    comparators118=comparators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(comparators118.getTree(), root_0);

                    pushFollow(FOLLOW_expr_in_bExpr21546);
                    expr119=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr119.getTree());

                    }
                    break;
                case 2 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:200:4: NOT LBRACKET bExpr RBRACKET
                    {
                    NOT120=(Token)match(input,NOT,FOLLOW_NOT_in_bExpr21551); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT120);


                    LBRACKET121=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21553); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET121);


                    pushFollow(FOLLOW_bExpr_in_bExpr21555);
                    bExpr122=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr122.getTree());

                    RBRACKET123=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21557); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET123);


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
                    // 200:32: -> ^( NOT bExpr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:200:35: ^( NOT bExpr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_NOT.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_bExpr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:201:4: LBRACKET bExpr RBRACKET
                    {
                    LBRACKET124=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21570); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET124);


                    pushFollow(FOLLOW_bExpr_in_bExpr21572);
                    bExpr125=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr125.getTree());

                    RBRACKET126=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21574); if (state.failed) return retval; 
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
                    // 201:28: -> bExpr
                    {
                        adaptor.addChild(root_0, stream_bExpr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:202:4: vBOp LBRACKET bExpr RBRACKET
                    {
                    pushFollow(FOLLOW_vBOp_in_bExpr21583);
                    vBOp127=vBOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vBOp.add(vBOp127.getTree());

                    LBRACKET128=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21585); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET128);


                    pushFollow(FOLLOW_bExpr_in_bExpr21587);
                    bExpr129=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr129.getTree());

                    RBRACKET130=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21589); if (state.failed) return retval; 
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
                    // 202:33: -> ^( vBOp bExpr )
                    {
                        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:202:36: ^( vBOp bExpr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_vBOp.nextNode(), root_1);

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
        return retval;
    }
    // $ANTLR end "bExpr2"


    public static class unaryPrimitives_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryPrimitives"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:208:1: unaryPrimitives : ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE );
    public final BACONParser.unaryPrimitives_return unaryPrimitives() throws RecognitionException {
        BACONParser.unaryPrimitives_return retval = new BACONParser.unaryPrimitives_return();
        retval.start = input.LT(1);

        int unaryPrimitives_StartIndex = input.index();

        Object root_0 = null;

        Token set131=null;

        Object set131_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:2: ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


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
        return retval;
    }
    // $ANTLR end "unaryPrimitives"


    public static class lowPrecMathOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lowPrecMathOp"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:231:1: lowPrecMathOp : ( PLUS | MINUS );
    public final BACONParser.lowPrecMathOp_return lowPrecMathOp() throws RecognitionException {
        BACONParser.lowPrecMathOp_return retval = new BACONParser.lowPrecMathOp_return();
        retval.start = input.LT(1);

        int lowPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set132=null;

        Object set132_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:232:2: ( PLUS | MINUS )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


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
        return retval;
    }
    // $ANTLR end "lowPrecMathOp"


    public static class medPrecMathOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "medPrecMathOp"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:1: medPrecMathOp : ( MUL | DIV );
    public final BACONParser.medPrecMathOp_return medPrecMathOp() throws RecognitionException {
        BACONParser.medPrecMathOp_return retval = new BACONParser.medPrecMathOp_return();
        retval.start = input.LT(1);

        int medPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set133=null;

        Object set133_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:2: ( MUL | DIV )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


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
        return retval;
    }
    // $ANTLR end "medPrecMathOp"


    public static class highPrecMathOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "highPrecMathOp"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:1: highPrecMathOp : POW ;
    public final BACONParser.highPrecMathOp_return highPrecMathOp() throws RecognitionException {
        BACONParser.highPrecMathOp_return retval = new BACONParser.highPrecMathOp_return();
        retval.start = input.LT(1);

        int highPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token POW134=null;

        Object POW134_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:242:2: ( POW )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:242:4: POW
            {
            root_0 = (Object)adaptor.nil();


            POW134=(Token)match(input,POW,FOLLOW_POW_in_highPrecMathOp1754); if (state.failed) return retval;
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
        return retval;
    }
    // $ANTLR end "highPrecMathOp"


    public static class binPrim_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "binPrim"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:1: binPrim : ( MIN | MAX );
    public final BACONParser.binPrim_return binPrim() throws RecognitionException {
        BACONParser.binPrim_return retval = new BACONParser.binPrim_return();
        retval.start = input.LT(1);

        int binPrim_StartIndex = input.index();

        Object root_0 = null;

        Token set135=null;

        Object set135_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:246:2: ( MIN | MAX )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


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
        return retval;
    }
    // $ANTLR end "binPrim"


    public static class lowPrecBoolOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lowPrecBoolOp"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:250:1: lowPrecBoolOp : ( AND | OR );
    public final BACONParser.lowPrecBoolOp_return lowPrecBoolOp() throws RecognitionException {
        BACONParser.lowPrecBoolOp_return retval = new BACONParser.lowPrecBoolOp_return();
        retval.start = input.LT(1);

        int lowPrecBoolOp_StartIndex = input.index();

        Object root_0 = null;

        Token set136=null;

        Object set136_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:251:2: ( AND | OR )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


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
        return retval;
    }
    // $ANTLR end "lowPrecBoolOp"


    public static class comparators_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comparators"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:255:1: comparators : ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS );
    public final BACONParser.comparators_return comparators() throws RecognitionException {
        BACONParser.comparators_return retval = new BACONParser.comparators_return();
        retval.start = input.LT(1);

        int comparators_StartIndex = input.index();

        Object root_0 = null;

        Token set137=null;

        Object set137_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:256:2: ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


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
        return retval;
    }
    // $ANTLR end "comparators"


    public static class vOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "vOp"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:264:1: vOp : ( VSUM | VPRODUCT | VAVERAGE );
    public final BACONParser.vOp_return vOp() throws RecognitionException {
        BACONParser.vOp_return retval = new BACONParser.vOp_return();
        retval.start = input.LT(1);

        int vOp_StartIndex = input.index();

        Object root_0 = null;

        Token set138=null;

        Object set138_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:265:2: ( VSUM | VPRODUCT | VAVERAGE )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


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
        return retval;
    }
    // $ANTLR end "vOp"


    public static class vBOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "vBOp"
    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:270:1: vBOp : ( ALL | SOME | NONE );
    public final BACONParser.vBOp_return vBOp() throws RecognitionException {
        BACONParser.vBOp_return retval = new BACONParser.vBOp_return();
        retval.start = input.LT(1);

        int vBOp_StartIndex = input.index();

        Object root_0 = null;

        Token set139=null;

        Object set139_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:271:2: ( ALL | SOME | NONE )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


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
        return retval;
    }
    // $ANTLR end "vBOp"

    // $ANTLR start synpred19_BACON
    public final void synpred19_BACON_fragment() throws RecognitionException {
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:170:11: ( lowPrecMathOp expr2 )
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:170:11: lowPrecMathOp expr2
        {
        pushFollow(FOLLOW_lowPrecMathOp_in_synpred19_BACON1315);
        lowPrecMathOp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_expr2_in_synpred19_BACON1318);
        expr2();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_BACON

    // $ANTLR start synpred20_BACON
    public final void synpred20_BACON_fragment() throws RecognitionException {
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:174:11: ( medPrecMathOp expr3 )
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:174:11: medPrecMathOp expr3
        {
        pushFollow(FOLLOW_medPrecMathOp_in_synpred20_BACON1335);
        medPrecMathOp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_expr3_in_synpred20_BACON1339);
        expr3();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred20_BACON

    // $ANTLR start synpred21_BACON
    public final void synpred21_BACON_fragment() throws RecognitionException {
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:178:11: ( highPrecMathOp expr4 )
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:178:11: highPrecMathOp expr4
        {
        pushFollow(FOLLOW_highPrecMathOp_in_synpred21_BACON1356);
        highPrecMathOp();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_expr4_in_synpred21_BACON1359);
        expr4();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred21_BACON

    // $ANTLR start synpred30_BACON
    public final void synpred30_BACON_fragment() throws RecognitionException {
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:199:4: ( expr comparators expr )
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:199:4: expr comparators expr
        {
        pushFollow(FOLLOW_expr_in_synpred30_BACON1541);
        expr();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_comparators_in_synpred30_BACON1543);
        comparators();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_expr_in_synpred30_BACON1546);
        expr();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred30_BACON

    // $ANTLR start synpred32_BACON
    public final void synpred32_BACON_fragment() throws RecognitionException {
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:201:4: ( LBRACKET bExpr RBRACKET )
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:201:4: LBRACKET bExpr RBRACKET
        {
        match(input,LBRACKET,FOLLOW_LBRACKET_in_synpred32_BACON1570); if (state.failed) return ;

        pushFollow(FOLLOW_bExpr_in_synpred32_BACON1572);
        bExpr();

        state._fsp--;
        if (state.failed) return ;

        match(input,RBRACKET,FOLLOW_RBRACKET_in_synpred32_BACON1574); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred32_BACON

    // Delegated rules

    public final boolean synpred32_BACON() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred32_BACON_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_BACON() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_BACON_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_BACON() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_BACON_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred19_BACON() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_BACON_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred30_BACON() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_BACON_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_NEWLINE_in_rules897 = new BitSet(new long[]{0x0888000A80821000L,0x00000000000000A0L});
    public static final BitSet FOLLOW_pair_in_rules901 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules904 = new BitSet(new long[]{0x0888000A80821000L,0x00000000000000A0L});
    public static final BitSet FOLLOW_pair_in_rules906 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_pair937 = new BitSet(new long[]{0x0088000A80821000L,0x00000000000000A0L});
    public static final BitSet FOLLOW_rule2_in_pair939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_pair954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULENAME_in_ruleName966 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COLON_in_ruleName968 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_ruleName982 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COLON_in_ruleName984 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule2_in_rule1004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_rule21023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_rule21028 = new BitSet(new long[]{0xE1030D8C9C1D0970L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_bExpr_in_rule21030 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_THEN_in_rule21032 = new BitSet(new long[]{0x0088000A80821000L,0x00000000000000A0L});
    public static final BitSet FOLLOW_rule_in_rule21034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPTAKE_in_rule21049 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_VAR_in_rule21053 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_rule21055 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_rule21057 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELEASE_in_rule21074 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21076 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_VAR_in_rule21078 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_rule21080 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_rule21082 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INGEST_in_rule21099 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_VAR_in_rule21103 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_rule21105 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_rule21107 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_rule21109 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_rule21111 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule21130 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21132 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_VAR_in_rule21134 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PCHANGE_in_rule21149 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21151 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_VAR_in_rule21153 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_rule21155 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_rule21157 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDE_in_rule21174 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21176 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_rule21178 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_rule21193 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21195 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_VAR_in_rule21197 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_rule21199 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_rule21201 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21203 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_WITH_in_rule21209 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_LSQUARE_in_rule21211 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_assignList_in_rule21213 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RSQUARE_in_rule21215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21237 = new BitSet(new long[]{0x0088000A80821000L,0x00000000000000A0L});
    public static final BitSet FOLLOW_rule_in_rule21239 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_assign1256 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_EQUALS_in_assign1258 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_assign1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_assignList1281 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_COMMA_in_assignList1284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_assign_in_assignList1286 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_expr2_in_expr1312 = new BitSet(new long[]{0x0010100000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_expr1315 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr2_in_expr1318 = new BitSet(new long[]{0x0010100000000002L});
    public static final BitSet FOLLOW_expr3_in_expr21332 = new BitSet(new long[]{0x0000200000400002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_expr21335 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr3_in_expr21339 = new BitSet(new long[]{0x0000200000400002L});
    public static final BitSet FOLLOW_expr4_in_expr31353 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_expr31356 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr4_in_expr31359 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41375 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_expr41377 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryPrimitives_in_expr41388 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41390 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_expr41392 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_expr41407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_expr41412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr41417 = new BitSet(new long[]{0xE1030D8C9C1D0970L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_bExpr_in_expr41419 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_THEN_in_expr41421 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_expr41423 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ELSE_in_expr41425 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_expr41427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binPrim_in_expr41444 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41446 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_expr41448 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_expr41450 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_expr41452 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARHIST_in_expr41469 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41471 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_VAR_in_expr41473 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COMMA_in_expr41475 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_expr41477 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vOp_in_expr41494 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41496 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_expr41498 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1520 = new BitSet(new long[]{0x0004000000000082L});
    public static final BitSet FOLLOW_lowPrecBoolOp_in_bExpr1523 = new BitSet(new long[]{0xE1030D8C9C1D0970L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1526 = new BitSet(new long[]{0x0004000000000082L});
    public static final BitSet FOLLOW_expr_in_bExpr21541 = new BitSet(new long[]{0x0000403062000000L});
    public static final BitSet FOLLOW_comparators_in_bExpr21543 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_bExpr21546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bExpr21551 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21553 = new BitSet(new long[]{0xE1030D8C9C1D0970L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21555 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21570 = new BitSet(new long[]{0xE1030D8C9C1D0970L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21572 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vBOp_in_bExpr21583 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21585 = new BitSet(new long[]{0xE1030D8C9C1D0970L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21587 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POW_in_highPrecMathOp1754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_synpred19_BACON1315 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr2_in_synpred19_BACON1318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_synpred20_BACON1335 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr3_in_synpred20_BACON1339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_synpred21_BACON1356 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr4_in_synpred21_BACON1359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred30_BACON1541 = new BitSet(new long[]{0x0000403062000000L});
    public static final BitSet FOLLOW_comparators_in_synpred30_BACON1543 = new BitSet(new long[]{0x61000D8C9C1D0930L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_expr_in_synpred30_BACON1546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_synpred32_BACON1570 = new BitSet(new long[]{0xE1030D8C9C1D0970L,0x0000000000000FC7L});
    public static final BitSet FOLLOW_bExpr_in_synpred32_BACON1572 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_synpred32_BACON1574 = new BitSet(new long[]{0x0000000000000002L});

}