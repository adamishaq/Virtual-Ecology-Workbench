// $ANTLR 3.4 C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2012-01-02 16:42:24

package VEW.XMLCompiler.ANTLR;
import java.util.Map;
import java.util.HashMap;


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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACOS", "ALL", "AND", "ASIN", "ASSIGN", "ASSIGNLIST", "ATAN", "BAR", "BEXPR", "CHANGE", "CHANGEASSIGN", "COLON", "COMMA", "COMMENT", "COS", "CREATE", "DENSITYAT", "DEPTHFORFI", "DEPTHFORVI", "DIGIT", "DIV", "DIVIDE", "ELSE", "EQUALS", "EXP", "EXPONENT", "EXPR", "FLOAT", "FULLIRRADAT", "GREATEREQUALS", "GREATERTHAN", "IF", "IGNORE", "INGEST", "INTEGRATE", "LBRACKET", "LESSEQUALS", "LESSTHAN", "LETTER", "LN", "LOGTEN", "LSQUARE", "MAX", "MIN", "MINUS", "MUL", "NEG", "NEQUALS", "NONE", "NOT", "OR", "OTHERWISE", "PCHANGE", "PLUS", "POW", "RBRACKET", "RELEASE", "RND", "RSQUARE", "RULE", "RULENAME", "RULES", "SALINITYAT", "SIN", "SOME", "SQRT", "TAN", "TEMPAT", "THEN", "TO", "UNKNOWN", "UPTAKE", "UVIRRADAT", "VAR", "VARHIST", "VAVERAGE", "VISIRRADAT", "VPRODUCT", "VSUM", "WITH"
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
    public static final int BAR=12;
    public static final int BEXPR=13;
    public static final int CHANGE=14;
    public static final int CHANGEASSIGN=15;
    public static final int COLON=16;
    public static final int COMMA=17;
    public static final int COMMENT=18;
    public static final int COS=19;
    public static final int CREATE=20;
    public static final int DENSITYAT=21;
    public static final int DEPTHFORFI=22;
    public static final int DEPTHFORVI=23;
    public static final int DIGIT=24;
    public static final int DIV=25;
    public static final int DIVIDE=26;
    public static final int ELSE=27;
    public static final int EQUALS=28;
    public static final int EXP=29;
    public static final int EXPONENT=30;
    public static final int EXPR=31;
    public static final int FLOAT=32;
    public static final int FULLIRRADAT=33;
    public static final int GREATEREQUALS=34;
    public static final int GREATERTHAN=35;
    public static final int IF=36;
    public static final int IGNORE=37;
    public static final int INGEST=38;
    public static final int INTEGRATE=39;
    public static final int LBRACKET=40;
    public static final int LESSEQUALS=41;
    public static final int LESSTHAN=42;
    public static final int LETTER=43;
    public static final int LN=44;
    public static final int LOGTEN=45;
    public static final int LSQUARE=46;
    public static final int MAX=47;
    public static final int MIN=48;
    public static final int MINUS=49;
    public static final int MUL=50;
    public static final int NEG=51;
    public static final int NEQUALS=52;
    public static final int NONE=53;
    public static final int NOT=54;
    public static final int OR=55;
    public static final int OTHERWISE=56;
    public static final int PCHANGE=57;
    public static final int PLUS=58;
    public static final int POW=59;
    public static final int RBRACKET=60;
    public static final int RELEASE=61;
    public static final int RND=62;
    public static final int RSQUARE=63;
    public static final int RULE=64;
    public static final int RULENAME=65;
    public static final int RULES=66;
    public static final int SALINITYAT=67;
    public static final int SIN=68;
    public static final int SOME=69;
    public static final int SQRT=70;
    public static final int TAN=71;
    public static final int TEMPAT=72;
    public static final int THEN=73;
    public static final int TO=74;
    public static final int UNKNOWN=75;
    public static final int UPTAKE=76;
    public static final int UVIRRADAT=77;
    public static final int VAR=78;
    public static final int VARHIST=79;
    public static final int VAVERAGE=80;
    public static final int VISIRRADAT=81;
    public static final int VPRODUCT=82;
    public static final int VSUM=83;
    public static final int WITH=84;

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
        this.state.ruleMemo = new HashMap[25+1];
         

    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return BACONParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g"; }


    private static Map<Integer, String> naturalNames = null; 
    //Parser stuff here
    protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
                  throws RecognitionException
    {
    	RecognitionException e = null;
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:190:1: rules : pair ( pair )* EOF -> ^( RULES pair ( pair )* ) ;
    public final BACONParser.rules_return rules() throws RecognitionException {
        BACONParser.rules_return retval = new BACONParser.rules_return();
        retval.start = input.LT(1);

        int rules_StartIndex = input.index();

        Object root_0 = null;

        Token EOF3=null;
        BACONParser.pair_return pair1 =null;

        BACONParser.pair_return pair2 =null;


        Object EOF3_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_pair=new RewriteRuleSubtreeStream(adaptor,"rule pair");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:191:2: ( pair ( pair )* EOF -> ^( RULES pair ( pair )* ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:191:4: pair ( pair )* EOF
            {
            pushFollow(FOLLOW_pair_in_rules951);
            pair1=pair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pair.add(pair1.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:191:9: ( pair )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==CHANGE||LA1_0==CREATE||LA1_0==DIVIDE||LA1_0==IF||LA1_0==INGEST||LA1_0==LBRACKET||LA1_0==RELEASE||LA1_0==RULENAME||LA1_0==UPTAKE||LA1_0==VAR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:191:10: pair
            	    {
            	    pushFollow(FOLLOW_pair_in_rules954);
            	    pair2=pair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_pair.add(pair2.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_rules958); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF3);


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
            // 191:21: -> ^( RULES pair ( pair )* )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:191:24: ^( RULES pair ( pair )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(RULES, "RULES")
                , root_1);

                adaptor.addChild(root_1, stream_pair.nextTree());

                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:191:37: ( pair )*
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:194:1: pair : ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule );
    public final BACONParser.pair_return pair() throws RecognitionException {
        BACONParser.pair_return retval = new BACONParser.pair_return();
        retval.start = input.LT(1);

        int pair_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.ruleName_return ruleName4 =null;

        BACONParser.rule2_return rule25 =null;

        BACONParser.rule_return rule6 =null;


        RewriteRuleSubtreeStream stream_ruleName=new RewriteRuleSubtreeStream(adaptor,"rule ruleName");
        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:195:2: ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule )
            int alt2=2;
            switch ( input.LA(1) ) {
            case RULENAME:
                {
                alt2=1;
                }
                break;
            case VAR:
                {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==COLON) ) {
                    alt2=1;
                }
                else if ( (LA2_2==EQUALS) ) {
                    alt2=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 2, input);

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
            case RELEASE:
            case UPTAKE:
                {
                alt2=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:195:4: ruleName rule2
                    {
                    pushFollow(FOLLOW_ruleName_in_pair982);
                    ruleName4=ruleName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleName.add(ruleName4.getTree());

                    pushFollow(FOLLOW_rule2_in_pair984);
                    rule25=rule2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule2.add(rule25.getTree());

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
                    // 195:19: -> ^( RULE ruleName rule2 )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:195:22: ^( RULE ruleName rule2 )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:196:4: rule
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_rule_in_pair999);
                    rule6=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule6.getTree());

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:199:1: ruleName : ( RULENAME COLON -> RULENAME | VAR COLON -> VAR );
    public final BACONParser.ruleName_return ruleName() throws RecognitionException {
        BACONParser.ruleName_return retval = new BACONParser.ruleName_return();
        retval.start = input.LT(1);

        int ruleName_StartIndex = input.index();

        Object root_0 = null;

        Token RULENAME7=null;
        Token COLON8=null;
        Token VAR9=null;
        Token COLON10=null;

        Object RULENAME7_tree=null;
        Object COLON8_tree=null;
        Object VAR9_tree=null;
        Object COLON10_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_RULENAME=new RewriteRuleTokenStream(adaptor,"token RULENAME");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:200:2: ( RULENAME COLON -> RULENAME | VAR COLON -> VAR )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULENAME) ) {
                alt3=1;
            }
            else if ( (LA3_0==VAR) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:200:4: RULENAME COLON
                    {
                    RULENAME7=(Token)match(input,RULENAME,FOLLOW_RULENAME_in_ruleName1011); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULENAME.add(RULENAME7);


                    COLON8=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName1013); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON8);


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
                    // 200:19: -> RULENAME
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:201:4: VAR COLON
                    {
                    VAR9=(Token)match(input,VAR,FOLLOW_VAR_in_ruleName1022); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR9);


                    COLON10=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName1024); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON10);


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
                    // 201:14: -> VAR
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:204:1: rule : rule2 -> ^( RULE rule2 ) ;
    public final BACONParser.rule_return rule() throws RecognitionException {
        BACONParser.rule_return retval = new BACONParser.rule_return();
        retval.start = input.LT(1);

        int rule_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.rule2_return rule211 =null;


        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:205:2: ( rule2 -> ^( RULE rule2 ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:205:4: rule2
            {
            pushFollow(FOLLOW_rule2_in_rule1039);
            rule211=rule2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rule2.add(rule211.getTree());

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
            // 205:10: -> ^( RULE rule2 )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:205:13: ^( RULE rule2 )
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:208:1: rule2 : ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | CHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( CHANGE VAR expr ) | CHANGE LBRACKET expr RBRACKET ( changeExpr )+ -> ^( CHANGE expr ( changeExpr )+ ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 );
    public final BACONParser.rule2_return rule2() throws RecognitionException {
        BACONParser.rule2_return retval = new BACONParser.rule2_return();
        retval.start = input.LT(1);

        int rule2_StartIndex = input.index();

        Object root_0 = null;

        Token IF13=null;
        Token THEN15=null;
        Token UPTAKE17=null;
        Token LBRACKET18=null;
        Token VAR19=null;
        Token COMMA20=null;
        Token RBRACKET22=null;
        Token RELEASE23=null;
        Token LBRACKET24=null;
        Token VAR25=null;
        Token COMMA26=null;
        Token RBRACKET28=null;
        Token INGEST29=null;
        Token LBRACKET30=null;
        Token VAR31=null;
        Token COMMA32=null;
        Token COMMA34=null;
        Token RBRACKET36=null;
        Token CHANGE37=null;
        Token LBRACKET38=null;
        Token VAR39=null;
        Token RBRACKET40=null;
        Token CHANGE41=null;
        Token LBRACKET42=null;
        Token VAR43=null;
        Token COMMA44=null;
        Token RBRACKET46=null;
        Token CHANGE47=null;
        Token LBRACKET48=null;
        Token RBRACKET50=null;
        Token DIVIDE52=null;
        Token LBRACKET53=null;
        Token RBRACKET55=null;
        Token CREATE56=null;
        Token LBRACKET57=null;
        Token VAR58=null;
        Token COMMA59=null;
        Token RBRACKET61=null;
        Token WITH62=null;
        Token LSQUARE63=null;
        Token RSQUARE65=null;
        Token LBRACKET66=null;
        Token RBRACKET68=null;
        BACONParser.assign_return assign12 =null;

        BACONParser.bExpr_return bExpr14 =null;

        BACONParser.rule_return rule16 =null;

        BACONParser.expr_return expr21 =null;

        BACONParser.expr_return expr27 =null;

        BACONParser.expr_return expr33 =null;

        BACONParser.expr_return expr35 =null;

        BACONParser.expr_return expr45 =null;

        BACONParser.expr_return expr49 =null;

        BACONParser.changeExpr_return changeExpr51 =null;

        BACONParser.expr_return expr54 =null;

        BACONParser.expr_return expr60 =null;

        BACONParser.assignList_return assignList64 =null;

        BACONParser.rule2_return rule267 =null;


        Object IF13_tree=null;
        Object THEN15_tree=null;
        Object UPTAKE17_tree=null;
        Object LBRACKET18_tree=null;
        Object VAR19_tree=null;
        Object COMMA20_tree=null;
        Object RBRACKET22_tree=null;
        Object RELEASE23_tree=null;
        Object LBRACKET24_tree=null;
        Object VAR25_tree=null;
        Object COMMA26_tree=null;
        Object RBRACKET28_tree=null;
        Object INGEST29_tree=null;
        Object LBRACKET30_tree=null;
        Object VAR31_tree=null;
        Object COMMA32_tree=null;
        Object COMMA34_tree=null;
        Object RBRACKET36_tree=null;
        Object CHANGE37_tree=null;
        Object LBRACKET38_tree=null;
        Object VAR39_tree=null;
        Object RBRACKET40_tree=null;
        Object CHANGE41_tree=null;
        Object LBRACKET42_tree=null;
        Object VAR43_tree=null;
        Object COMMA44_tree=null;
        Object RBRACKET46_tree=null;
        Object CHANGE47_tree=null;
        Object LBRACKET48_tree=null;
        Object RBRACKET50_tree=null;
        Object DIVIDE52_tree=null;
        Object LBRACKET53_tree=null;
        Object RBRACKET55_tree=null;
        Object CREATE56_tree=null;
        Object LBRACKET57_tree=null;
        Object VAR58_tree=null;
        Object COMMA59_tree=null;
        Object RBRACKET61_tree=null;
        Object WITH62_tree=null;
        Object LSQUARE63_tree=null;
        Object RSQUARE65_tree=null;
        Object LBRACKET66_tree=null;
        Object RBRACKET68_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_CREATE=new RewriteRuleTokenStream(adaptor,"token CREATE");
        RewriteRuleTokenStream stream_THEN=new RewriteRuleTokenStream(adaptor,"token THEN");
        RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
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
        RewriteRuleSubtreeStream stream_changeExpr=new RewriteRuleSubtreeStream(adaptor,"rule changeExpr");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_rule=new RewriteRuleSubtreeStream(adaptor,"rule rule");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        RewriteRuleSubtreeStream stream_assignList=new RewriteRuleSubtreeStream(adaptor,"rule assignList");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:2: ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | CHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( CHANGE VAR expr ) | CHANGE LBRACKET expr RBRACKET ( changeExpr )+ -> ^( CHANGE expr ( changeExpr )+ ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 )
            int alt6=11;
            switch ( input.LA(1) ) {
            case VAR:
                {
                alt6=1;
                }
                break;
            case IF:
                {
                alt6=2;
                }
                break;
            case UPTAKE:
                {
                alt6=3;
                }
                break;
            case RELEASE:
                {
                alt6=4;
                }
                break;
            case INGEST:
                {
                alt6=5;
                }
                break;
            case CHANGE:
                {
                int LA6_6 = input.LA(2);

                if ( (LA6_6==LBRACKET) ) {
                    int LA6_10 = input.LA(3);

                    if ( (LA6_10==VAR) ) {
                        switch ( input.LA(4) ) {
                        case RBRACKET:
                            {
                            int LA6_13 = input.LA(5);

                            if ( (LA6_13==EOF||LA6_13==CHANGE||LA6_13==CREATE||LA6_13==DIVIDE||LA6_13==IF||LA6_13==INGEST||LA6_13==LBRACKET||(LA6_13 >= RBRACKET && LA6_13 <= RELEASE)||LA6_13==RULENAME||LA6_13==UPTAKE||LA6_13==VAR) ) {
                                alt6=6;
                            }
                            else if ( (LA6_13==BAR) ) {
                                alt6=8;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 6, 13, input);

                                throw nvae;

                            }
                            }
                            break;
                        case COMMA:
                            {
                            alt6=7;
                            }
                            break;
                        case DIV:
                        case MINUS:
                        case MUL:
                        case PLUS:
                        case POW:
                            {
                            alt6=8;
                            }
                            break;
                        default:
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 6, 11, input);

                            throw nvae;

                        }

                    }
                    else if ( ((LA6_10 >= ABS && LA6_10 <= ACOS)||LA6_10==ASIN||LA6_10==ATAN||LA6_10==COS||(LA6_10 >= DENSITYAT && LA6_10 <= DEPTHFORVI)||LA6_10==EXP||(LA6_10 >= FLOAT && LA6_10 <= FULLIRRADAT)||LA6_10==IF||(LA6_10 >= INTEGRATE && LA6_10 <= LBRACKET)||(LA6_10 >= LN && LA6_10 <= LOGTEN)||(LA6_10 >= MAX && LA6_10 <= MINUS)||LA6_10==RND||(LA6_10 >= SALINITYAT && LA6_10 <= SIN)||(LA6_10 >= SQRT && LA6_10 <= TEMPAT)||LA6_10==UVIRRADAT||(LA6_10 >= VARHIST && LA6_10 <= VSUM)) ) {
                        alt6=8;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 10, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 6, input);

                    throw nvae;

                }
                }
                break;
            case DIVIDE:
                {
                alt6=9;
                }
                break;
            case CREATE:
                {
                alt6=10;
                }
                break;
            case LBRACKET:
                {
                alt6=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:4: assign
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assign_in_rule21058);
                    assign12=assign();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign12.getTree());

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:210:4: IF bExpr THEN rule
                    {
                    IF13=(Token)match(input,IF,FOLLOW_IF_in_rule21063); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF13);


                    pushFollow(FOLLOW_bExpr_in_rule21065);
                    bExpr14=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr14.getTree());

                    THEN15=(Token)match(input,THEN,FOLLOW_THEN_in_rule21067); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN15);


                    pushFollow(FOLLOW_rule_in_rule21069);
                    rule16=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule16.getTree());

                    // AST REWRITE
                    // elements: bExpr, IF, rule
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 210:23: -> ^( IF bExpr rule )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:210:26: ^( IF bExpr rule )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:211:4: UPTAKE LBRACKET VAR COMMA expr RBRACKET
                    {
                    UPTAKE17=(Token)match(input,UPTAKE,FOLLOW_UPTAKE_in_rule21084); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UPTAKE.add(UPTAKE17);


                    LBRACKET18=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21086); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET18);


                    VAR19=(Token)match(input,VAR,FOLLOW_VAR_in_rule21088); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR19);


                    COMMA20=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21090); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA20);


                    pushFollow(FOLLOW_expr_in_rule21092);
                    expr21=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr21.getTree());

                    RBRACKET22=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21094); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET22);


                    // AST REWRITE
                    // elements: UPTAKE, VAR, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 211:44: -> ^( UPTAKE VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:211:47: ^( UPTAKE VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:212:4: RELEASE LBRACKET VAR COMMA expr RBRACKET
                    {
                    RELEASE23=(Token)match(input,RELEASE,FOLLOW_RELEASE_in_rule21109); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RELEASE.add(RELEASE23);


                    LBRACKET24=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21111); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET24);


                    VAR25=(Token)match(input,VAR,FOLLOW_VAR_in_rule21113); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR25);


                    COMMA26=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21115); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA26);


                    pushFollow(FOLLOW_expr_in_rule21117);
                    expr27=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr27.getTree());

                    RBRACKET28=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21119); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET28);


                    // AST REWRITE
                    // elements: expr, VAR, RELEASE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 212:45: -> ^( RELEASE VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:212:48: ^( RELEASE VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:213:4: INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET
                    {
                    INGEST29=(Token)match(input,INGEST,FOLLOW_INGEST_in_rule21134); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INGEST.add(INGEST29);


                    LBRACKET30=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21136); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET30);


                    VAR31=(Token)match(input,VAR,FOLLOW_VAR_in_rule21138); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR31);


                    COMMA32=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21140); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA32);


                    pushFollow(FOLLOW_expr_in_rule21142);
                    expr33=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr33.getTree());

                    COMMA34=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21144); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA34);


                    pushFollow(FOLLOW_expr_in_rule21146);
                    expr35=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr35.getTree());

                    RBRACKET36=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21148); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET36);


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
                    // 213:55: -> ^( INGEST VAR expr expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:213:58: ^( INGEST VAR expr expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:214:4: CHANGE LBRACKET VAR RBRACKET
                    {
                    CHANGE37=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21165); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE37);


                    LBRACKET38=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21167); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET38);


                    VAR39=(Token)match(input,VAR,FOLLOW_VAR_in_rule21169); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR39);


                    RBRACKET40=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21171); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET40);


                    // AST REWRITE
                    // elements: VAR, CHANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 214:33: -> ^( CHANGE VAR )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:214:36: ^( CHANGE VAR )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:215:4: CHANGE LBRACKET VAR COMMA expr RBRACKET
                    {
                    CHANGE41=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21184); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE41);


                    LBRACKET42=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21186); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET42);


                    VAR43=(Token)match(input,VAR,FOLLOW_VAR_in_rule21188); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR43);


                    COMMA44=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21190); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA44);


                    pushFollow(FOLLOW_expr_in_rule21192);
                    expr45=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr45.getTree());

                    RBRACKET46=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21194); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET46);


                    // AST REWRITE
                    // elements: CHANGE, VAR, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 215:44: -> ^( CHANGE VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:215:47: ^( CHANGE VAR expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CHANGE.nextNode()
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:216:4: CHANGE LBRACKET expr RBRACKET ( changeExpr )+
                    {
                    CHANGE47=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21209); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE47);


                    LBRACKET48=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21211); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET48);


                    pushFollow(FOLLOW_expr_in_rule21213);
                    expr49=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr49.getTree());

                    RBRACKET50=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21215); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET50);


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:216:34: ( changeExpr )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==BAR) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:216:34: changeExpr
                    	    {
                    	    pushFollow(FOLLOW_changeExpr_in_rule21217);
                    	    changeExpr51=changeExpr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_changeExpr.add(changeExpr51.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt4 >= 1 ) break loop4;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(4, input);
                                throw eee;
                        }
                        cnt4++;
                    } while (true);


                    // AST REWRITE
                    // elements: expr, changeExpr, CHANGE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 216:46: -> ^( CHANGE expr ( changeExpr )+ )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:216:49: ^( CHANGE expr ( changeExpr )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CHANGE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        if ( !(stream_changeExpr.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_changeExpr.hasNext() ) {
                            adaptor.addChild(root_1, stream_changeExpr.nextTree());

                        }
                        stream_changeExpr.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 9 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:217:4: DIVIDE LBRACKET expr RBRACKET
                    {
                    DIVIDE52=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_rule21234); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIVIDE.add(DIVIDE52);


                    LBRACKET53=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21236); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET53);


                    pushFollow(FOLLOW_expr_in_rule21238);
                    expr54=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr54.getTree());

                    RBRACKET55=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21240); if (state.failed) return retval; 
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
                    // 217:34: -> ^( DIVIDE expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:217:37: ^( DIVIDE expr )
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
                case 10 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:218:4: CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )?
                    {
                    CREATE56=(Token)match(input,CREATE,FOLLOW_CREATE_in_rule21253); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CREATE.add(CREATE56);


                    LBRACKET57=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21255); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET57);


                    VAR58=(Token)match(input,VAR,FOLLOW_VAR_in_rule21257); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR58);


                    COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21259); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA59);


                    pushFollow(FOLLOW_expr_in_rule21261);
                    expr60=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr60.getTree());

                    RBRACKET61=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21263); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET61);


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:219:3: ( WITH LSQUARE assignList RSQUARE )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==WITH) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:219:4: WITH LSQUARE assignList RSQUARE
                            {
                            WITH62=(Token)match(input,WITH,FOLLOW_WITH_in_rule21268); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_WITH.add(WITH62);


                            LSQUARE63=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_rule21270); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE63);


                            pushFollow(FOLLOW_assignList_in_rule21272);
                            assignList64=assignList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignList.add(assignList64.getTree());

                            RSQUARE65=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_rule21274); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE65);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: expr, assignList, VAR, CREATE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 219:38: -> ^( CREATE VAR expr ( assignList )? )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:219:41: ^( CREATE VAR expr ( assignList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CREATE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:219:59: ( assignList )?
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
                case 11 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:220:4: LBRACKET rule2 RBRACKET
                    {
                    LBRACKET66=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21296); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET66);


                    pushFollow(FOLLOW_rule2_in_rule21298);
                    rule267=rule2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule2.add(rule267.getTree());

                    RBRACKET68=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21300); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET68);


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
                    // 220:28: -> rule2
                    {
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
        return retval;
    }
    // $ANTLR end "rule2"


    public static class changeExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "changeExpr"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:223:1: changeExpr : BAR changeCond TO VAR -> ^( CHANGEASSIGN changeCond VAR ) ;
    public final BACONParser.changeExpr_return changeExpr() throws RecognitionException {
        BACONParser.changeExpr_return retval = new BACONParser.changeExpr_return();
        retval.start = input.LT(1);

        int changeExpr_StartIndex = input.index();

        Object root_0 = null;

        Token BAR69=null;
        Token TO71=null;
        Token VAR72=null;
        BACONParser.changeCond_return changeCond70 =null;


        Object BAR69_tree=null;
        Object TO71_tree=null;
        Object VAR72_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_TO=new RewriteRuleTokenStream(adaptor,"token TO");
        RewriteRuleTokenStream stream_BAR=new RewriteRuleTokenStream(adaptor,"token BAR");
        RewriteRuleSubtreeStream stream_changeCond=new RewriteRuleSubtreeStream(adaptor,"rule changeCond");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:2: ( BAR changeCond TO VAR -> ^( CHANGEASSIGN changeCond VAR ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:4: BAR changeCond TO VAR
            {
            BAR69=(Token)match(input,BAR,FOLLOW_BAR_in_changeExpr1316); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_BAR.add(BAR69);


            pushFollow(FOLLOW_changeCond_in_changeExpr1318);
            changeCond70=changeCond();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_changeCond.add(changeCond70.getTree());

            TO71=(Token)match(input,TO,FOLLOW_TO_in_changeExpr1320); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TO.add(TO71);


            VAR72=(Token)match(input,VAR,FOLLOW_VAR_in_changeExpr1322); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR72);


            // AST REWRITE
            // elements: changeCond, VAR
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 224:26: -> ^( CHANGEASSIGN changeCond VAR )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:29: ^( CHANGEASSIGN changeCond VAR )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CHANGEASSIGN, "CHANGEASSIGN")
                , root_1);

                adaptor.addChild(root_1, stream_changeCond.nextTree());

                adaptor.addChild(root_1, 
                stream_VAR.nextNode()
                );

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
            if ( state.backtracking>0 ) { memoize(input, 6, changeExpr_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "changeExpr"


    public static class changeCond_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "changeCond"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:227:1: changeCond : ( OTHERWISE | bExpr );
    public final BACONParser.changeCond_return changeCond() throws RecognitionException {
        BACONParser.changeCond_return retval = new BACONParser.changeCond_return();
        retval.start = input.LT(1);

        int changeCond_StartIndex = input.index();

        Object root_0 = null;

        Token OTHERWISE73=null;
        BACONParser.bExpr_return bExpr74 =null;


        Object OTHERWISE73_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:2: ( OTHERWISE | bExpr )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==OTHERWISE) ) {
                alt7=1;
            }
            else if ( ((LA7_0 >= ABS && LA7_0 <= ALL)||LA7_0==ASIN||LA7_0==ATAN||LA7_0==COS||(LA7_0 >= DENSITYAT && LA7_0 <= DEPTHFORVI)||LA7_0==EXP||(LA7_0 >= FLOAT && LA7_0 <= FULLIRRADAT)||LA7_0==IF||(LA7_0 >= INTEGRATE && LA7_0 <= LBRACKET)||(LA7_0 >= LN && LA7_0 <= LOGTEN)||(LA7_0 >= MAX && LA7_0 <= MINUS)||(LA7_0 >= NONE && LA7_0 <= NOT)||LA7_0==RND||(LA7_0 >= SALINITYAT && LA7_0 <= TEMPAT)||(LA7_0 >= UVIRRADAT && LA7_0 <= VSUM)) ) {
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:4: OTHERWISE
                    {
                    root_0 = (Object)adaptor.nil();


                    OTHERWISE73=(Token)match(input,OTHERWISE,FOLLOW_OTHERWISE_in_changeCond1344); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OTHERWISE73_tree = 
                    (Object)adaptor.create(OTHERWISE73)
                    ;
                    adaptor.addChild(root_0, OTHERWISE73_tree);
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:229:4: bExpr
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_bExpr_in_changeCond1349);
                    bExpr74=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr74.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 7, changeCond_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "changeCond"


    public static class assign_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assign"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:232:1: assign : VAR EQUALS expr -> ^( ASSIGN VAR expr ) ;
    public final BACONParser.assign_return assign() throws RecognitionException {
        BACONParser.assign_return retval = new BACONParser.assign_return();
        retval.start = input.LT(1);

        int assign_StartIndex = input.index();

        Object root_0 = null;

        Token VAR75=null;
        Token EQUALS76=null;
        BACONParser.expr_return expr77 =null;


        Object VAR75_tree=null;
        Object EQUALS76_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:233:2: ( VAR EQUALS expr -> ^( ASSIGN VAR expr ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:233:4: VAR EQUALS expr
            {
            VAR75=(Token)match(input,VAR,FOLLOW_VAR_in_assign1360); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR75);


            EQUALS76=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assign1362); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS76);


            pushFollow(FOLLOW_expr_in_assign1364);
            expr77=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr77.getTree());

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
            // 233:20: -> ^( ASSIGN VAR expr )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:233:23: ^( ASSIGN VAR expr )
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
            if ( state.backtracking>0 ) { memoize(input, 8, assign_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "assign"


    public static class assignList_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignList"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:1: assignList : assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) ;
    public final BACONParser.assignList_return assignList() throws RecognitionException {
        BACONParser.assignList_return retval = new BACONParser.assignList_return();
        retval.start = input.LT(1);

        int assignList_StartIndex = input.index();

        Object root_0 = null;

        Token COMMA79=null;
        BACONParser.assign_return assign78 =null;

        BACONParser.assign_return assign80 =null;


        Object COMMA79_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_assign=new RewriteRuleSubtreeStream(adaptor,"rule assign");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:2: ( assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:4: assign ( COMMA assign )*
            {
            pushFollow(FOLLOW_assign_in_assignList1385);
            assign78=assign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assign.add(assign78.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:11: ( COMMA assign )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==COMMA) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:12: COMMA assign
            	    {
            	    COMMA79=(Token)match(input,COMMA,FOLLOW_COMMA_in_assignList1388); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA79);


            	    pushFollow(FOLLOW_assign_in_assignList1390);
            	    assign80=assign();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_assign.add(assign80.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
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
            // 237:27: -> ^( ASSIGNLIST assign ( assign )* )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:30: ^( ASSIGNLIST assign ( assign )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGNLIST, "ASSIGNLIST")
                , root_1);

                adaptor.addChild(root_1, stream_assign.nextTree());

                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:50: ( assign )*
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
            if ( state.backtracking>0 ) { memoize(input, 9, assignList_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "assignList"


    public static class expr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:240:1: expr : expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* ;
    public final BACONParser.expr_return expr() throws RecognitionException {
        BACONParser.expr_return retval = new BACONParser.expr_return();
        retval.start = input.LT(1);

        int expr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr2_return expr281 =null;

        BACONParser.lowPrecMathOp_return lowPrecMathOp82 =null;

        BACONParser.expr2_return expr283 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:2: ( expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:4: expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr2_in_expr1418);
            expr281=expr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr281.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:10: ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==MINUS||LA9_0==PLUS) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:38: lowPrecMathOp ^ expr2
            	    {
            	    pushFollow(FOLLOW_lowPrecMathOp_in_expr1432);
            	    lowPrecMathOp82=lowPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecMathOp82.getTree(), root_0);

            	    pushFollow(FOLLOW_expr2_in_expr1435);
            	    expr283=expr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr283.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
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
            if ( state.backtracking>0 ) { memoize(input, 10, expr_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "expr"


    public static class expr2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr2"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:244:1: expr2 : expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* ;
    public final BACONParser.expr2_return expr2() throws RecognitionException {
        BACONParser.expr2_return retval = new BACONParser.expr2_return();
        retval.start = input.LT(1);

        int expr2_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr3_return expr384 =null;

        BACONParser.medPrecMathOp_return medPrecMathOp85 =null;

        BACONParser.expr3_return expr386 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:2: ( expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:4: expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr3_in_expr21449);
            expr384=expr3();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr384.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:10: ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==DIV||LA10_0==MUL) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:38: medPrecMathOp ^ expr3
            	    {
            	    pushFollow(FOLLOW_medPrecMathOp_in_expr21463);
            	    medPrecMathOp85=medPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(medPrecMathOp85.getTree(), root_0);

            	    pushFollow(FOLLOW_expr3_in_expr21467);
            	    expr386=expr3();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr386.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
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
            if ( state.backtracking>0 ) { memoize(input, 11, expr2_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "expr2"


    public static class expr3_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr3"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:248:1: expr3 : expr4 ( options {greedy=true; } : highPrecMathOp ^ expr3 )? ;
    public final BACONParser.expr3_return expr3() throws RecognitionException {
        BACONParser.expr3_return retval = new BACONParser.expr3_return();
        retval.start = input.LT(1);

        int expr3_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr4_return expr487 =null;

        BACONParser.highPrecMathOp_return highPrecMathOp88 =null;

        BACONParser.expr3_return expr389 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:2: ( expr4 ( options {greedy=true; } : highPrecMathOp ^ expr3 )? )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:4: expr4 ( options {greedy=true; } : highPrecMathOp ^ expr3 )?
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr4_in_expr31481);
            expr487=expr4();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr487.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:10: ( options {greedy=true; } : highPrecMathOp ^ expr3 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==POW) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:38: highPrecMathOp ^ expr3
                    {
                    pushFollow(FOLLOW_highPrecMathOp_in_expr31495);
                    highPrecMathOp88=highPrecMathOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(highPrecMathOp88.getTree(), root_0);

                    pushFollow(FOLLOW_expr3_in_expr31498);
                    expr389=expr3();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr389.getTree());

                    }
                    break;

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
            if ( state.backtracking>0 ) { memoize(input, 12, expr3_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "expr3"


    public static class expr4_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr4"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:1: expr4 : ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | MINUS expr -> ^( NEG expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) );
    public final BACONParser.expr4_return expr4() throws RecognitionException {
        BACONParser.expr4_return retval = new BACONParser.expr4_return();
        retval.start = input.LT(1);

        int expr4_StartIndex = input.index();

        Object root_0 = null;

        Token LBRACKET90=null;
        Token RBRACKET92=null;
        Token LBRACKET94=null;
        Token RBRACKET96=null;
        Token MINUS97=null;
        Token FLOAT99=null;
        Token VAR100=null;
        Token IF101=null;
        Token THEN103=null;
        Token ELSE105=null;
        Token LBRACKET108=null;
        Token COMMA110=null;
        Token RBRACKET112=null;
        Token VARHIST113=null;
        Token LBRACKET114=null;
        Token VAR115=null;
        Token COMMA116=null;
        Token RBRACKET118=null;
        Token LBRACKET120=null;
        Token RBRACKET122=null;
        BACONParser.expr_return expr91 =null;

        BACONParser.unaryPrimitives_return unaryPrimitives93 =null;

        BACONParser.expr_return expr95 =null;

        BACONParser.expr_return expr98 =null;

        BACONParser.bExpr_return bExpr102 =null;

        BACONParser.expr_return expr104 =null;

        BACONParser.expr_return expr106 =null;

        BACONParser.binPrim_return binPrim107 =null;

        BACONParser.expr_return expr109 =null;

        BACONParser.expr_return expr111 =null;

        BACONParser.expr_return expr117 =null;

        BACONParser.vOp_return vOp119 =null;

        BACONParser.expr_return expr121 =null;


        Object LBRACKET90_tree=null;
        Object RBRACKET92_tree=null;
        Object LBRACKET94_tree=null;
        Object RBRACKET96_tree=null;
        Object MINUS97_tree=null;
        Object FLOAT99_tree=null;
        Object VAR100_tree=null;
        Object IF101_tree=null;
        Object THEN103_tree=null;
        Object ELSE105_tree=null;
        Object LBRACKET108_tree=null;
        Object COMMA110_tree=null;
        Object RBRACKET112_tree=null;
        Object VARHIST113_tree=null;
        Object LBRACKET114_tree=null;
        Object VAR115_tree=null;
        Object COMMA116_tree=null;
        Object RBRACKET118_tree=null;
        Object LBRACKET120_tree=null;
        Object RBRACKET122_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_THEN=new RewriteRuleTokenStream(adaptor,"token THEN");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
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
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:254:3: ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | MINUS expr -> ^( NEG expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) )
            int alt12=9;
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
            case VISIRRADAT:
                {
                alt12=2;
                }
                break;
            case MINUS:
                {
                alt12=3;
                }
                break;
            case FLOAT:
                {
                alt12=4;
                }
                break;
            case VAR:
                {
                alt12=5;
                }
                break;
            case IF:
                {
                alt12=6;
                }
                break;
            case MAX:
            case MIN:
                {
                alt12=7;
                }
                break;
            case VARHIST:
                {
                alt12=8;
                }
                break;
            case VAVERAGE:
            case VPRODUCT:
            case VSUM:
                {
                alt12=9;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }

            switch (alt12) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:254:5: LBRACKET expr RBRACKET
                    {
                    LBRACKET90=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41514); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET90);


                    pushFollow(FOLLOW_expr_in_expr41516);
                    expr91=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr91.getTree());

                    RBRACKET92=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41518); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET92);


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
                    // 254:28: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:255:4: unaryPrimitives LBRACKET expr RBRACKET
                    {
                    pushFollow(FOLLOW_unaryPrimitives_in_expr41527);
                    unaryPrimitives93=unaryPrimitives();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryPrimitives.add(unaryPrimitives93.getTree());

                    LBRACKET94=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41529); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET94);


                    pushFollow(FOLLOW_expr_in_expr41531);
                    expr95=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr95.getTree());

                    RBRACKET96=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41533); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET96);


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
                    // 255:43: -> ^( unaryPrimitives expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:255:46: ^( unaryPrimitives expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:256:4: MINUS expr
                    {
                    MINUS97=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr41546); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_MINUS.add(MINUS97);


                    pushFollow(FOLLOW_expr_in_expr41548);
                    expr98=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr98.getTree());

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
                    // 256:15: -> ^( NEG expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:256:18: ^( NEG expr )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(NEG, "NEG")
                        , root_1);

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:4: FLOAT
                    {
                    root_0 = (Object)adaptor.nil();


                    FLOAT99=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_expr41561); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT99_tree = 
                    (Object)adaptor.create(FLOAT99)
                    ;
                    adaptor.addChild(root_0, FLOAT99_tree);
                    }

                    }
                    break;
                case 5 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:258:4: VAR
                    {
                    root_0 = (Object)adaptor.nil();


                    VAR100=(Token)match(input,VAR,FOLLOW_VAR_in_expr41566); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR100_tree = 
                    (Object)adaptor.create(VAR100)
                    ;
                    adaptor.addChild(root_0, VAR100_tree);
                    }

                    }
                    break;
                case 6 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:259:4: IF bExpr THEN expr ELSE expr
                    {
                    IF101=(Token)match(input,IF,FOLLOW_IF_in_expr41571); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF101);


                    pushFollow(FOLLOW_bExpr_in_expr41573);
                    bExpr102=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr102.getTree());

                    THEN103=(Token)match(input,THEN,FOLLOW_THEN_in_expr41575); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN103);


                    pushFollow(FOLLOW_expr_in_expr41577);
                    expr104=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr104.getTree());

                    ELSE105=(Token)match(input,ELSE,FOLLOW_ELSE_in_expr41579); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELSE.add(ELSE105);


                    pushFollow(FOLLOW_expr_in_expr41581);
                    expr106=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr106.getTree());

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
                    // 259:33: -> ^( IF bExpr expr expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:259:36: ^( IF bExpr expr expr )
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
                case 7 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:260:4: binPrim LBRACKET expr COMMA expr RBRACKET
                    {
                    pushFollow(FOLLOW_binPrim_in_expr41598);
                    binPrim107=binPrim();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binPrim.add(binPrim107.getTree());

                    LBRACKET108=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41600); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET108);


                    pushFollow(FOLLOW_expr_in_expr41602);
                    expr109=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr109.getTree());

                    COMMA110=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41604); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA110);


                    pushFollow(FOLLOW_expr_in_expr41606);
                    expr111=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr111.getTree());

                    RBRACKET112=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41608); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET112);


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
                    // 260:46: -> ^( binPrim expr expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:260:49: ^( binPrim expr expr )
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
                case 8 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:261:4: VARHIST LBRACKET VAR COMMA expr RBRACKET
                    {
                    VARHIST113=(Token)match(input,VARHIST,FOLLOW_VARHIST_in_expr41623); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VARHIST.add(VARHIST113);


                    LBRACKET114=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41625); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET114);


                    VAR115=(Token)match(input,VAR,FOLLOW_VAR_in_expr41627); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR115);


                    COMMA116=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41629); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA116);


                    pushFollow(FOLLOW_expr_in_expr41631);
                    expr117=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr117.getTree());

                    RBRACKET118=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41633); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET118);


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
                    // 261:45: -> ^( VARHIST VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:261:48: ^( VARHIST VAR expr )
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
                case 9 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:262:4: vOp LBRACKET expr RBRACKET
                    {
                    pushFollow(FOLLOW_vOp_in_expr41648);
                    vOp119=vOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vOp.add(vOp119.getTree());

                    LBRACKET120=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41650); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET120);


                    pushFollow(FOLLOW_expr_in_expr41652);
                    expr121=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr121.getTree());

                    RBRACKET122=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41654); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET122);


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
                    // 262:31: -> ^( vOp expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:262:34: ^( vOp expr )
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
            if ( state.backtracking>0 ) { memoize(input, 13, expr4_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "expr4"


    public static class bExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bExpr"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:266:1: bExpr : bExpr2 ( lowPrecBoolOp ^ bExpr2 )* ;
    public final BACONParser.bExpr_return bExpr() throws RecognitionException {
        BACONParser.bExpr_return retval = new BACONParser.bExpr_return();
        retval.start = input.LT(1);

        int bExpr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.bExpr2_return bExpr2123 =null;

        BACONParser.lowPrecBoolOp_return lowPrecBoolOp124 =null;

        BACONParser.bExpr2_return bExpr2125 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:2: ( bExpr2 ( lowPrecBoolOp ^ bExpr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:4: bExpr2 ( lowPrecBoolOp ^ bExpr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_bExpr2_in_bExpr1674);
            bExpr2123=bExpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2123.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:11: ( lowPrecBoolOp ^ bExpr2 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==AND||LA13_0==OR) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:12: lowPrecBoolOp ^ bExpr2
            	    {
            	    pushFollow(FOLLOW_lowPrecBoolOp_in_bExpr1677);
            	    lowPrecBoolOp124=lowPrecBoolOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecBoolOp124.getTree(), root_0);

            	    pushFollow(FOLLOW_bExpr2_in_bExpr1680);
            	    bExpr2125=bExpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2125.getTree());

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
            if ( state.backtracking>0 ) { memoize(input, 14, bExpr_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "bExpr"


    public static class bExpr2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bExpr2"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:270:1: bExpr2 : ( ( expr comparators )=> expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) );
    public final BACONParser.bExpr2_return bExpr2() throws RecognitionException {
        BACONParser.bExpr2_return retval = new BACONParser.bExpr2_return();
        retval.start = input.LT(1);

        int bExpr2_StartIndex = input.index();

        Object root_0 = null;

        Token NOT129=null;
        Token LBRACKET130=null;
        Token RBRACKET132=null;
        Token LBRACKET133=null;
        Token RBRACKET135=null;
        Token LBRACKET137=null;
        Token RBRACKET139=null;
        BACONParser.expr_return expr126 =null;

        BACONParser.comparators_return comparators127 =null;

        BACONParser.expr_return expr128 =null;

        BACONParser.bExpr_return bExpr131 =null;

        BACONParser.bExpr_return bExpr134 =null;

        BACONParser.vBOp_return vBOp136 =null;

        BACONParser.bExpr_return bExpr138 =null;


        Object NOT129_tree=null;
        Object LBRACKET130_tree=null;
        Object RBRACKET132_tree=null;
        Object LBRACKET133_tree=null;
        Object RBRACKET135_tree=null;
        Object LBRACKET137_tree=null;
        Object RBRACKET139_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_vBOp=new RewriteRuleSubtreeStream(adaptor,"rule vBOp");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:271:2: ( ( expr comparators )=> expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) )
            int alt14=4;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==LBRACKET) ) {
                int LA14_1 = input.LA(2);

                if ( (synpred1_BACON()) ) {
                    alt14=1;
                }
                else if ( (true) ) {
                    alt14=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;

                }
            }
            else if ( ((LA14_0 >= ABS && LA14_0 <= ACOS)||LA14_0==ASIN||LA14_0==ATAN||LA14_0==COS||(LA14_0 >= DENSITYAT && LA14_0 <= DEPTHFORVI)||LA14_0==EXP||LA14_0==FULLIRRADAT||LA14_0==INTEGRATE||(LA14_0 >= LN && LA14_0 <= LOGTEN)||LA14_0==RND||(LA14_0 >= SALINITYAT && LA14_0 <= SIN)||(LA14_0 >= SQRT && LA14_0 <= TEMPAT)||LA14_0==UVIRRADAT||LA14_0==VISIRRADAT) && (synpred1_BACON())) {
                alt14=1;
            }
            else if ( (LA14_0==MINUS) && (synpred1_BACON())) {
                alt14=1;
            }
            else if ( (LA14_0==FLOAT) && (synpred1_BACON())) {
                alt14=1;
            }
            else if ( (LA14_0==VAR) && (synpred1_BACON())) {
                alt14=1;
            }
            else if ( (LA14_0==IF) && (synpred1_BACON())) {
                alt14=1;
            }
            else if ( ((LA14_0 >= MAX && LA14_0 <= MIN)) && (synpred1_BACON())) {
                alt14=1;
            }
            else if ( (LA14_0==VARHIST) && (synpred1_BACON())) {
                alt14=1;
            }
            else if ( (LA14_0==VAVERAGE||(LA14_0 >= VPRODUCT && LA14_0 <= VSUM)) && (synpred1_BACON())) {
                alt14=1;
            }
            else if ( (LA14_0==NOT) ) {
                alt14=2;
            }
            else if ( (LA14_0==ALL||LA14_0==NONE||LA14_0==SOME) ) {
                alt14=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:271:4: ( expr comparators )=> expr comparators ^ expr
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_bExpr21704);
                    expr126=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr126.getTree());

                    pushFollow(FOLLOW_comparators_in_bExpr21706);
                    comparators127=comparators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(comparators127.getTree(), root_0);

                    pushFollow(FOLLOW_expr_in_bExpr21709);
                    expr128=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr128.getTree());

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:272:4: NOT LBRACKET bExpr RBRACKET
                    {
                    NOT129=(Token)match(input,NOT,FOLLOW_NOT_in_bExpr21714); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT129);


                    LBRACKET130=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21716); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET130);


                    pushFollow(FOLLOW_bExpr_in_bExpr21718);
                    bExpr131=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr131.getTree());

                    RBRACKET132=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21720); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET132);


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
                    // 272:32: -> ^( NOT bExpr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:272:35: ^( NOT bExpr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:273:4: LBRACKET bExpr RBRACKET
                    {
                    LBRACKET133=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21733); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET133);


                    pushFollow(FOLLOW_bExpr_in_bExpr21735);
                    bExpr134=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr134.getTree());

                    RBRACKET135=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21737); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET135);


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
                    // 273:28: -> bExpr
                    {
                        adaptor.addChild(root_0, stream_bExpr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:274:4: vBOp LBRACKET bExpr RBRACKET
                    {
                    pushFollow(FOLLOW_vBOp_in_bExpr21746);
                    vBOp136=vBOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vBOp.add(vBOp136.getTree());

                    LBRACKET137=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21748); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET137);


                    pushFollow(FOLLOW_bExpr_in_bExpr21750);
                    bExpr138=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr138.getTree());

                    RBRACKET139=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21752); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET139);


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
                    // 274:33: -> ^( vBOp bExpr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:274:36: ^( vBOp bExpr )
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
            if ( state.backtracking>0 ) { memoize(input, 15, bExpr2_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "bExpr2"


    public static class unaryPrimitives_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryPrimitives"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:280:1: unaryPrimitives : ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE | VISIRRADAT );
    public final BACONParser.unaryPrimitives_return unaryPrimitives() throws RecognitionException {
        BACONParser.unaryPrimitives_return retval = new BACONParser.unaryPrimitives_return();
        retval.start = input.LT(1);

        int unaryPrimitives_StartIndex = input.index();

        Object root_0 = null;

        Token set140=null;

        Object set140_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:281:2: ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE | VISIRRADAT )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set140=(Token)input.LT(1);

            if ( (input.LA(1) >= ABS && input.LA(1) <= ACOS)||input.LA(1)==ASIN||input.LA(1)==ATAN||input.LA(1)==COS||(input.LA(1) >= DENSITYAT && input.LA(1) <= DEPTHFORVI)||input.LA(1)==EXP||input.LA(1)==FULLIRRADAT||input.LA(1)==INTEGRATE||(input.LA(1) >= LN && input.LA(1) <= LOGTEN)||input.LA(1)==RND||(input.LA(1) >= SALINITYAT && input.LA(1) <= SIN)||(input.LA(1) >= SQRT && input.LA(1) <= TEMPAT)||input.LA(1)==UVIRRADAT||input.LA(1)==VISIRRADAT ) {
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
            if ( state.backtracking>0 ) { memoize(input, 16, unaryPrimitives_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "unaryPrimitives"


    public static class lowPrecMathOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lowPrecMathOp"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:304:1: lowPrecMathOp : ( PLUS | MINUS );
    public final BACONParser.lowPrecMathOp_return lowPrecMathOp() throws RecognitionException {
        BACONParser.lowPrecMathOp_return retval = new BACONParser.lowPrecMathOp_return();
        retval.start = input.LT(1);

        int lowPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set141=null;

        Object set141_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:305:2: ( PLUS | MINUS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set141=(Token)input.LT(1);

            if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
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
            if ( state.backtracking>0 ) { memoize(input, 17, lowPrecMathOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "lowPrecMathOp"


    public static class medPrecMathOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "medPrecMathOp"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:309:1: medPrecMathOp : ( MUL | DIV );
    public final BACONParser.medPrecMathOp_return medPrecMathOp() throws RecognitionException {
        BACONParser.medPrecMathOp_return retval = new BACONParser.medPrecMathOp_return();
        retval.start = input.LT(1);

        int medPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set142=null;

        Object set142_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:310:2: ( MUL | DIV )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set142=(Token)input.LT(1);

            if ( input.LA(1)==DIV||input.LA(1)==MUL ) {
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
            if ( state.backtracking>0 ) { memoize(input, 18, medPrecMathOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "medPrecMathOp"


    public static class highPrecMathOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "highPrecMathOp"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:314:1: highPrecMathOp : POW ;
    public final BACONParser.highPrecMathOp_return highPrecMathOp() throws RecognitionException {
        BACONParser.highPrecMathOp_return retval = new BACONParser.highPrecMathOp_return();
        retval.start = input.LT(1);

        int highPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token POW143=null;

        Object POW143_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:315:2: ( POW )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:315:4: POW
            {
            root_0 = (Object)adaptor.nil();


            POW143=(Token)match(input,POW,FOLLOW_POW_in_highPrecMathOp1922); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            POW143_tree = 
            (Object)adaptor.create(POW143)
            ;
            adaptor.addChild(root_0, POW143_tree);
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
            if ( state.backtracking>0 ) { memoize(input, 19, highPrecMathOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "highPrecMathOp"


    public static class binPrim_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "binPrim"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:318:1: binPrim : ( MIN | MAX );
    public final BACONParser.binPrim_return binPrim() throws RecognitionException {
        BACONParser.binPrim_return retval = new BACONParser.binPrim_return();
        retval.start = input.LT(1);

        int binPrim_StartIndex = input.index();

        Object root_0 = null;

        Token set144=null;

        Object set144_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:319:2: ( MIN | MAX )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set144=(Token)input.LT(1);

            if ( (input.LA(1) >= MAX && input.LA(1) <= MIN) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set144)
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
            if ( state.backtracking>0 ) { memoize(input, 20, binPrim_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "binPrim"


    public static class lowPrecBoolOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "lowPrecBoolOp"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:323:1: lowPrecBoolOp : ( AND | OR );
    public final BACONParser.lowPrecBoolOp_return lowPrecBoolOp() throws RecognitionException {
        BACONParser.lowPrecBoolOp_return retval = new BACONParser.lowPrecBoolOp_return();
        retval.start = input.LT(1);

        int lowPrecBoolOp_StartIndex = input.index();

        Object root_0 = null;

        Token set145=null;

        Object set145_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:324:2: ( AND | OR )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set145=(Token)input.LT(1);

            if ( input.LA(1)==AND||input.LA(1)==OR ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set145)
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
            if ( state.backtracking>0 ) { memoize(input, 21, lowPrecBoolOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "lowPrecBoolOp"


    public static class comparators_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comparators"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:328:1: comparators : ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS );
    public final BACONParser.comparators_return comparators() throws RecognitionException {
        BACONParser.comparators_return retval = new BACONParser.comparators_return();
        retval.start = input.LT(1);

        int comparators_StartIndex = input.index();

        Object root_0 = null;

        Token set146=null;

        Object set146_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:329:2: ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set146=(Token)input.LT(1);

            if ( input.LA(1)==EQUALS||(input.LA(1) >= GREATEREQUALS && input.LA(1) <= GREATERTHAN)||(input.LA(1) >= LESSEQUALS && input.LA(1) <= LESSTHAN)||input.LA(1)==NEQUALS ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set146)
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
            if ( state.backtracking>0 ) { memoize(input, 22, comparators_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "comparators"


    public static class vOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "vOp"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:337:1: vOp : ( VSUM | VPRODUCT | VAVERAGE );
    public final BACONParser.vOp_return vOp() throws RecognitionException {
        BACONParser.vOp_return retval = new BACONParser.vOp_return();
        retval.start = input.LT(1);

        int vOp_StartIndex = input.index();

        Object root_0 = null;

        Token set147=null;

        Object set147_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:338:2: ( VSUM | VPRODUCT | VAVERAGE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set147=(Token)input.LT(1);

            if ( input.LA(1)==VAVERAGE||(input.LA(1) >= VPRODUCT && input.LA(1) <= VSUM) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set147)
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
            if ( state.backtracking>0 ) { memoize(input, 23, vOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "vOp"


    public static class vBOp_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "vBOp"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:343:1: vBOp : ( ALL | SOME | NONE );
    public final BACONParser.vBOp_return vBOp() throws RecognitionException {
        BACONParser.vBOp_return retval = new BACONParser.vBOp_return();
        retval.start = input.LT(1);

        int vBOp_StartIndex = input.index();

        Object root_0 = null;

        Token set148=null;

        Object set148_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:344:2: ( ALL | SOME | NONE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set148=(Token)input.LT(1);

            if ( input.LA(1)==ALL||input.LA(1)==NONE||input.LA(1)==SOME ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set148)
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
            if ( state.backtracking>0 ) { memoize(input, 24, vBOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "vBOp"

    // $ANTLR start synpred1_BACON
    public final void synpred1_BACON_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:271:4: ( expr comparators )
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:271:5: expr comparators
        {
        pushFollow(FOLLOW_expr_in_synpred1_BACON1696);
        expr();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_comparators_in_synpred1_BACON1698);
        comparators();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_BACON

    // Delegated rules

    public final boolean synpred1_BACON() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_BACON_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_pair_in_rules951 = new BitSet(new long[]{0x2000015004104000L,0x0000000000005002L});
    public static final BitSet FOLLOW_pair_in_rules954 = new BitSet(new long[]{0x2000015004104000L,0x0000000000005002L});
    public static final BitSet FOLLOW_EOF_in_rules958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_pair982 = new BitSet(new long[]{0x2000015004104000L,0x0000000000005000L});
    public static final BitSet FOLLOW_rule2_in_pair984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_pair999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULENAME_in_ruleName1011 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_COLON_in_ruleName1013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_ruleName1022 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_COLON_in_ruleName1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule2_in_rule1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_rule21058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_rule21063 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr_in_rule21065 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_THEN_in_rule21067 = new BitSet(new long[]{0x2000015004104000L,0x0000000000005000L});
    public static final BitSet FOLLOW_rule_in_rule21069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPTAKE_in_rule21084 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21086 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_rule21088 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_rule21090 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21092 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELEASE_in_rule21109 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21111 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_rule21113 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_rule21115 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21117 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INGEST_in_rule21134 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21136 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_rule21138 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_rule21140 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21142 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_rule21144 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21146 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule21165 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21167 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_rule21169 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule21184 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21186 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_rule21188 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_rule21190 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21192 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule21209 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21211 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21213 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21215 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_changeExpr_in_rule21217 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_DIVIDE_in_rule21234 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21236 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21238 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_rule21253 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21255 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_rule21257 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_rule21259 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21261 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21263 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_WITH_in_rule21268 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LSQUARE_in_rule21270 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_assignList_in_rule21272 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_RSQUARE_in_rule21274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21296 = new BitSet(new long[]{0x2000015004104000L,0x0000000000005000L});
    public static final BitSet FOLLOW_rule2_in_rule21298 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BAR_in_changeExpr1316 = new BitSet(new long[]{0x4163B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_changeCond_in_changeExpr1318 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_TO_in_changeExpr1320 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_changeExpr1322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OTHERWISE_in_changeCond1344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr_in_changeCond1349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_assign1360 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_EQUALS_in_assign1362 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_assign1364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_assignList1385 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_assignList1388 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_assign_in_assignList1390 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_expr2_in_expr1418 = new BitSet(new long[]{0x0402000000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_expr1432 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr2_in_expr1435 = new BitSet(new long[]{0x0402000000000002L});
    public static final BitSet FOLLOW_expr3_in_expr21449 = new BitSet(new long[]{0x0004000002000002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_expr21463 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr3_in_expr21467 = new BitSet(new long[]{0x0004000002000002L});
    public static final BitSet FOLLOW_expr4_in_expr31481 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_expr31495 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr3_in_expr31498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41514 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41516 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryPrimitives_in_expr41527 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41529 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41531 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expr41546 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_expr41561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_expr41566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr41571 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr_in_expr41573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_THEN_in_expr41575 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41577 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ELSE_in_expr41579 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binPrim_in_expr41598 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41600 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41602 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_expr41604 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41606 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARHIST_in_expr41623 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_expr41627 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_expr41629 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41631 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vOp_in_expr41648 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41650 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41652 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1674 = new BitSet(new long[]{0x0080000000000082L});
    public static final BitSet FOLLOW_lowPrecBoolOp_in_bExpr1677 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1680 = new BitSet(new long[]{0x0080000000000082L});
    public static final BitSet FOLLOW_expr_in_bExpr21704 = new BitSet(new long[]{0x0010060C10000000L});
    public static final BitSet FOLLOW_comparators_in_bExpr21706 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_bExpr21709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bExpr21714 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21716 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21718 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21733 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21735 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vBOp_in_bExpr21746 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21748 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21750 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POW_in_highPrecMathOp1922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred1_BACON1696 = new BitSet(new long[]{0x0010060C10000000L});
    public static final BitSet FOLLOW_comparators_in_synpred1_BACON1698 = new BitSet(new long[]{0x0000000000000002L});

}