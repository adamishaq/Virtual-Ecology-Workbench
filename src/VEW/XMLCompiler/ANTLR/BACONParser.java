// $ANTLR 3.4 C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2011-12-06 20:15:06

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:208:1: rule2 : ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET expr RBRACKET ( changeExpr )+ -> ^( CHANGE expr ( changeExpr )+ ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 );
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
        Token RBRACKET40=null;
        Token DIVIDE42=null;
        Token LBRACKET43=null;
        Token RBRACKET45=null;
        Token CREATE46=null;
        Token LBRACKET47=null;
        Token VAR48=null;
        Token COMMA49=null;
        Token RBRACKET51=null;
        Token WITH52=null;
        Token LSQUARE53=null;
        Token RSQUARE55=null;
        Token LBRACKET56=null;
        Token RBRACKET58=null;
        BACONParser.assign_return assign12 =null;

        BACONParser.bExpr_return bExpr14 =null;

        BACONParser.rule_return rule16 =null;

        BACONParser.expr_return expr21 =null;

        BACONParser.expr_return expr27 =null;

        BACONParser.expr_return expr33 =null;

        BACONParser.expr_return expr35 =null;

        BACONParser.expr_return expr39 =null;

        BACONParser.changeExpr_return changeExpr41 =null;

        BACONParser.expr_return expr44 =null;

        BACONParser.expr_return expr50 =null;

        BACONParser.assignList_return assignList54 =null;

        BACONParser.rule2_return rule257 =null;


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
        Object RBRACKET40_tree=null;
        Object DIVIDE42_tree=null;
        Object LBRACKET43_tree=null;
        Object RBRACKET45_tree=null;
        Object CREATE46_tree=null;
        Object LBRACKET47_tree=null;
        Object VAR48_tree=null;
        Object COMMA49_tree=null;
        Object RBRACKET51_tree=null;
        Object WITH52_tree=null;
        Object LSQUARE53_tree=null;
        Object RSQUARE55_tree=null;
        Object LBRACKET56_tree=null;
        Object RBRACKET58_tree=null;
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

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:2: ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET expr RBRACKET ( changeExpr )+ -> ^( CHANGE expr ( changeExpr )+ ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 )
            int alt6=9;
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
                alt6=6;
                }
                break;
            case DIVIDE:
                {
                alt6=7;
                }
                break;
            case CREATE:
                {
                alt6=8;
                }
                break;
            case LBRACKET:
                {
                alt6=9;
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
                    // elements: expr, INGEST, expr, VAR
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:216:4: CHANGE LBRACKET expr RBRACKET ( changeExpr )+
                    {
                    CHANGE37=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21169); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE37);


                    LBRACKET38=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21171); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET38);


                    pushFollow(FOLLOW_expr_in_rule21173);
                    expr39=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr39.getTree());

                    RBRACKET40=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21175); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET40);


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
                    	    pushFollow(FOLLOW_changeExpr_in_rule21177);
                    	    changeExpr41=changeExpr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_changeExpr.add(changeExpr41.getTree());

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
                    // elements: expr, CHANGE, changeExpr
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
                case 7 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:217:4: DIVIDE LBRACKET expr RBRACKET
                    {
                    DIVIDE42=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_rule21194); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIVIDE.add(DIVIDE42);


                    LBRACKET43=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21196); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET43);


                    pushFollow(FOLLOW_expr_in_rule21198);
                    expr44=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr44.getTree());

                    RBRACKET45=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21200); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET45);


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
                case 8 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:218:4: CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )?
                    {
                    CREATE46=(Token)match(input,CREATE,FOLLOW_CREATE_in_rule21213); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CREATE.add(CREATE46);


                    LBRACKET47=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21215); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET47);


                    VAR48=(Token)match(input,VAR,FOLLOW_VAR_in_rule21217); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR48);


                    COMMA49=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21219); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA49);


                    pushFollow(FOLLOW_expr_in_rule21221);
                    expr50=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr50.getTree());

                    RBRACKET51=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21223); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET51);


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
                            WITH52=(Token)match(input,WITH,FOLLOW_WITH_in_rule21228); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_WITH.add(WITH52);


                            LSQUARE53=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_rule21230); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE53);


                            pushFollow(FOLLOW_assignList_in_rule21232);
                            assignList54=assignList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignList.add(assignList54.getTree());

                            RSQUARE55=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_rule21234); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE55);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: assignList, VAR, expr, CREATE
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
                case 9 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:220:4: LBRACKET rule2 RBRACKET
                    {
                    LBRACKET56=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21256); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET56);


                    pushFollow(FOLLOW_rule2_in_rule21258);
                    rule257=rule2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule2.add(rule257.getTree());

                    RBRACKET58=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21260); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET58);


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

        Token BAR59=null;
        Token TO61=null;
        Token VAR62=null;
        BACONParser.changeCond_return changeCond60 =null;


        Object BAR59_tree=null;
        Object TO61_tree=null;
        Object VAR62_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_TO=new RewriteRuleTokenStream(adaptor,"token TO");
        RewriteRuleTokenStream stream_BAR=new RewriteRuleTokenStream(adaptor,"token BAR");
        RewriteRuleSubtreeStream stream_changeCond=new RewriteRuleSubtreeStream(adaptor,"rule changeCond");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:2: ( BAR changeCond TO VAR -> ^( CHANGEASSIGN changeCond VAR ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:4: BAR changeCond TO VAR
            {
            BAR59=(Token)match(input,BAR,FOLLOW_BAR_in_changeExpr1276); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_BAR.add(BAR59);


            pushFollow(FOLLOW_changeCond_in_changeExpr1278);
            changeCond60=changeCond();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_changeCond.add(changeCond60.getTree());

            TO61=(Token)match(input,TO,FOLLOW_TO_in_changeExpr1280); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TO.add(TO61);


            VAR62=(Token)match(input,VAR,FOLLOW_VAR_in_changeExpr1282); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR62);


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

        Token OTHERWISE63=null;
        BACONParser.bExpr_return bExpr64 =null;


        Object OTHERWISE63_tree=null;

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


                    OTHERWISE63=(Token)match(input,OTHERWISE,FOLLOW_OTHERWISE_in_changeCond1304); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OTHERWISE63_tree = 
                    (Object)adaptor.create(OTHERWISE63)
                    ;
                    adaptor.addChild(root_0, OTHERWISE63_tree);
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:229:4: bExpr
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_bExpr_in_changeCond1309);
                    bExpr64=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr64.getTree());

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

        Token VAR65=null;
        Token EQUALS66=null;
        BACONParser.expr_return expr67 =null;


        Object VAR65_tree=null;
        Object EQUALS66_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:233:2: ( VAR EQUALS expr -> ^( ASSIGN VAR expr ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:233:4: VAR EQUALS expr
            {
            VAR65=(Token)match(input,VAR,FOLLOW_VAR_in_assign1320); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR65);


            EQUALS66=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assign1322); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS66);


            pushFollow(FOLLOW_expr_in_assign1324);
            expr67=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr67.getTree());

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

        Token COMMA69=null;
        BACONParser.assign_return assign68 =null;

        BACONParser.assign_return assign70 =null;


        Object COMMA69_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_assign=new RewriteRuleSubtreeStream(adaptor,"rule assign");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:2: ( assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:4: assign ( COMMA assign )*
            {
            pushFollow(FOLLOW_assign_in_assignList1345);
            assign68=assign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assign.add(assign68.getTree());

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
            	    COMMA69=(Token)match(input,COMMA,FOLLOW_COMMA_in_assignList1348); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA69);


            	    pushFollow(FOLLOW_assign_in_assignList1350);
            	    assign70=assign();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_assign.add(assign70.getTree());

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

        BACONParser.expr2_return expr271 =null;

        BACONParser.lowPrecMathOp_return lowPrecMathOp72 =null;

        BACONParser.expr2_return expr273 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:2: ( expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:4: expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr2_in_expr1378);
            expr271=expr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr271.getTree());

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
            	    pushFollow(FOLLOW_lowPrecMathOp_in_expr1392);
            	    lowPrecMathOp72=lowPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecMathOp72.getTree(), root_0);

            	    pushFollow(FOLLOW_expr2_in_expr1395);
            	    expr273=expr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr273.getTree());

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

        BACONParser.expr3_return expr374 =null;

        BACONParser.medPrecMathOp_return medPrecMathOp75 =null;

        BACONParser.expr3_return expr376 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:2: ( expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:4: expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr3_in_expr21409);
            expr374=expr3();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr374.getTree());

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
            	    pushFollow(FOLLOW_medPrecMathOp_in_expr21423);
            	    medPrecMathOp75=medPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(medPrecMathOp75.getTree(), root_0);

            	    pushFollow(FOLLOW_expr3_in_expr21427);
            	    expr376=expr3();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr376.getTree());

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:248:1: expr3 : expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )* ;
    public final BACONParser.expr3_return expr3() throws RecognitionException {
        BACONParser.expr3_return retval = new BACONParser.expr3_return();
        retval.start = input.LT(1);

        int expr3_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr4_return expr477 =null;

        BACONParser.highPrecMathOp_return highPrecMathOp78 =null;

        BACONParser.expr4_return expr479 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:2: ( expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:4: expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr4_in_expr31441);
            expr477=expr4();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr477.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:10: ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==POW) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:38: highPrecMathOp ^ expr4
            	    {
            	    pushFollow(FOLLOW_highPrecMathOp_in_expr31455);
            	    highPrecMathOp78=highPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(highPrecMathOp78.getTree(), root_0);

            	    pushFollow(FOLLOW_expr4_in_expr31458);
            	    expr479=expr4();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr479.getTree());

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

        Token LBRACKET80=null;
        Token RBRACKET82=null;
        Token LBRACKET84=null;
        Token RBRACKET86=null;
        Token MINUS87=null;
        Token FLOAT89=null;
        Token VAR90=null;
        Token IF91=null;
        Token THEN93=null;
        Token ELSE95=null;
        Token LBRACKET98=null;
        Token COMMA100=null;
        Token RBRACKET102=null;
        Token VARHIST103=null;
        Token LBRACKET104=null;
        Token VAR105=null;
        Token COMMA106=null;
        Token RBRACKET108=null;
        Token LBRACKET110=null;
        Token RBRACKET112=null;
        BACONParser.expr_return expr81 =null;

        BACONParser.unaryPrimitives_return unaryPrimitives83 =null;

        BACONParser.expr_return expr85 =null;

        BACONParser.expr_return expr88 =null;

        BACONParser.bExpr_return bExpr92 =null;

        BACONParser.expr_return expr94 =null;

        BACONParser.expr_return expr96 =null;

        BACONParser.binPrim_return binPrim97 =null;

        BACONParser.expr_return expr99 =null;

        BACONParser.expr_return expr101 =null;

        BACONParser.expr_return expr107 =null;

        BACONParser.vOp_return vOp109 =null;

        BACONParser.expr_return expr111 =null;


        Object LBRACKET80_tree=null;
        Object RBRACKET82_tree=null;
        Object LBRACKET84_tree=null;
        Object RBRACKET86_tree=null;
        Object MINUS87_tree=null;
        Object FLOAT89_tree=null;
        Object VAR90_tree=null;
        Object IF91_tree=null;
        Object THEN93_tree=null;
        Object ELSE95_tree=null;
        Object LBRACKET98_tree=null;
        Object COMMA100_tree=null;
        Object RBRACKET102_tree=null;
        Object VARHIST103_tree=null;
        Object LBRACKET104_tree=null;
        Object VAR105_tree=null;
        Object COMMA106_tree=null;
        Object RBRACKET108_tree=null;
        Object LBRACKET110_tree=null;
        Object RBRACKET112_tree=null;
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
                    LBRACKET80=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41474); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET80);


                    pushFollow(FOLLOW_expr_in_expr41476);
                    expr81=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr81.getTree());

                    RBRACKET82=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41478); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET82);


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
                    pushFollow(FOLLOW_unaryPrimitives_in_expr41487);
                    unaryPrimitives83=unaryPrimitives();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryPrimitives.add(unaryPrimitives83.getTree());

                    LBRACKET84=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41489); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET84);


                    pushFollow(FOLLOW_expr_in_expr41491);
                    expr85=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr85.getTree());

                    RBRACKET86=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41493); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET86);


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
                    MINUS87=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr41506); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_MINUS.add(MINUS87);


                    pushFollow(FOLLOW_expr_in_expr41508);
                    expr88=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr88.getTree());

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


                    FLOAT89=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_expr41521); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT89_tree = 
                    (Object)adaptor.create(FLOAT89)
                    ;
                    adaptor.addChild(root_0, FLOAT89_tree);
                    }

                    }
                    break;
                case 5 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:258:4: VAR
                    {
                    root_0 = (Object)adaptor.nil();


                    VAR90=(Token)match(input,VAR,FOLLOW_VAR_in_expr41526); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR90_tree = 
                    (Object)adaptor.create(VAR90)
                    ;
                    adaptor.addChild(root_0, VAR90_tree);
                    }

                    }
                    break;
                case 6 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:259:4: IF bExpr THEN expr ELSE expr
                    {
                    IF91=(Token)match(input,IF,FOLLOW_IF_in_expr41531); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF91);


                    pushFollow(FOLLOW_bExpr_in_expr41533);
                    bExpr92=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr92.getTree());

                    THEN93=(Token)match(input,THEN,FOLLOW_THEN_in_expr41535); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN93);


                    pushFollow(FOLLOW_expr_in_expr41537);
                    expr94=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr94.getTree());

                    ELSE95=(Token)match(input,ELSE,FOLLOW_ELSE_in_expr41539); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELSE.add(ELSE95);


                    pushFollow(FOLLOW_expr_in_expr41541);
                    expr96=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr96.getTree());

                    // AST REWRITE
                    // elements: IF, expr, expr, bExpr
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
                    pushFollow(FOLLOW_binPrim_in_expr41558);
                    binPrim97=binPrim();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binPrim.add(binPrim97.getTree());

                    LBRACKET98=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41560); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET98);


                    pushFollow(FOLLOW_expr_in_expr41562);
                    expr99=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr99.getTree());

                    COMMA100=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41564); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA100);


                    pushFollow(FOLLOW_expr_in_expr41566);
                    expr101=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr101.getTree());

                    RBRACKET102=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41568); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET102);


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
                    VARHIST103=(Token)match(input,VARHIST,FOLLOW_VARHIST_in_expr41583); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VARHIST.add(VARHIST103);


                    LBRACKET104=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41585); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET104);


                    VAR105=(Token)match(input,VAR,FOLLOW_VAR_in_expr41587); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR105);


                    COMMA106=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41589); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA106);


                    pushFollow(FOLLOW_expr_in_expr41591);
                    expr107=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr107.getTree());

                    RBRACKET108=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41593); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET108);


                    // AST REWRITE
                    // elements: VARHIST, expr, VAR
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
                    pushFollow(FOLLOW_vOp_in_expr41608);
                    vOp109=vOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vOp.add(vOp109.getTree());

                    LBRACKET110=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41610); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET110);


                    pushFollow(FOLLOW_expr_in_expr41612);
                    expr111=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr111.getTree());

                    RBRACKET112=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41614); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET112);


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

        BACONParser.bExpr2_return bExpr2113 =null;

        BACONParser.lowPrecBoolOp_return lowPrecBoolOp114 =null;

        BACONParser.bExpr2_return bExpr2115 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:2: ( bExpr2 ( lowPrecBoolOp ^ bExpr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:4: bExpr2 ( lowPrecBoolOp ^ bExpr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_bExpr2_in_bExpr1634);
            bExpr2113=bExpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2113.getTree());

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
            	    pushFollow(FOLLOW_lowPrecBoolOp_in_bExpr1637);
            	    lowPrecBoolOp114=lowPrecBoolOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecBoolOp114.getTree(), root_0);

            	    pushFollow(FOLLOW_bExpr2_in_bExpr1640);
            	    bExpr2115=bExpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2115.getTree());

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

        Token NOT119=null;
        Token LBRACKET120=null;
        Token RBRACKET122=null;
        Token LBRACKET123=null;
        Token RBRACKET125=null;
        Token LBRACKET127=null;
        Token RBRACKET129=null;
        BACONParser.expr_return expr116 =null;

        BACONParser.comparators_return comparators117 =null;

        BACONParser.expr_return expr118 =null;

        BACONParser.bExpr_return bExpr121 =null;

        BACONParser.bExpr_return bExpr124 =null;

        BACONParser.vBOp_return vBOp126 =null;

        BACONParser.bExpr_return bExpr128 =null;


        Object NOT119_tree=null;
        Object LBRACKET120_tree=null;
        Object RBRACKET122_tree=null;
        Object LBRACKET123_tree=null;
        Object RBRACKET125_tree=null;
        Object LBRACKET127_tree=null;
        Object RBRACKET129_tree=null;
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


                    pushFollow(FOLLOW_expr_in_bExpr21664);
                    expr116=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr116.getTree());

                    pushFollow(FOLLOW_comparators_in_bExpr21666);
                    comparators117=comparators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(comparators117.getTree(), root_0);

                    pushFollow(FOLLOW_expr_in_bExpr21669);
                    expr118=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr118.getTree());

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:272:4: NOT LBRACKET bExpr RBRACKET
                    {
                    NOT119=(Token)match(input,NOT,FOLLOW_NOT_in_bExpr21674); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT119);


                    LBRACKET120=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21676); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET120);


                    pushFollow(FOLLOW_bExpr_in_bExpr21678);
                    bExpr121=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr121.getTree());

                    RBRACKET122=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21680); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET122);


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
                    LBRACKET123=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21693); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET123);


                    pushFollow(FOLLOW_bExpr_in_bExpr21695);
                    bExpr124=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr124.getTree());

                    RBRACKET125=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21697); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET125);


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
                    pushFollow(FOLLOW_vBOp_in_bExpr21706);
                    vBOp126=vBOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vBOp.add(vBOp126.getTree());

                    LBRACKET127=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21708); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET127);


                    pushFollow(FOLLOW_bExpr_in_bExpr21710);
                    bExpr128=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr128.getTree());

                    RBRACKET129=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21712); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET129);


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

        Token set130=null;

        Object set130_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:281:2: ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE | VISIRRADAT )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set130=(Token)input.LT(1);

            if ( (input.LA(1) >= ABS && input.LA(1) <= ACOS)||input.LA(1)==ASIN||input.LA(1)==ATAN||input.LA(1)==COS||(input.LA(1) >= DENSITYAT && input.LA(1) <= DEPTHFORVI)||input.LA(1)==EXP||input.LA(1)==FULLIRRADAT||input.LA(1)==INTEGRATE||(input.LA(1) >= LN && input.LA(1) <= LOGTEN)||input.LA(1)==RND||(input.LA(1) >= SALINITYAT && input.LA(1) <= SIN)||(input.LA(1) >= SQRT && input.LA(1) <= TEMPAT)||input.LA(1)==UVIRRADAT||input.LA(1)==VISIRRADAT ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set130)
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

        Token set131=null;

        Object set131_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:305:2: ( PLUS | MINUS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set131=(Token)input.LT(1);

            if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
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

        Token set132=null;

        Object set132_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:310:2: ( MUL | DIV )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set132=(Token)input.LT(1);

            if ( input.LA(1)==DIV||input.LA(1)==MUL ) {
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

        Token POW133=null;

        Object POW133_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:315:2: ( POW )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:315:4: POW
            {
            root_0 = (Object)adaptor.nil();


            POW133=(Token)match(input,POW,FOLLOW_POW_in_highPrecMathOp1882); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            POW133_tree = 
            (Object)adaptor.create(POW133)
            ;
            adaptor.addChild(root_0, POW133_tree);
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

        Token set134=null;

        Object set134_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:319:2: ( MIN | MAX )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set134=(Token)input.LT(1);

            if ( (input.LA(1) >= MAX && input.LA(1) <= MIN) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set134)
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

        Token set135=null;

        Object set135_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:324:2: ( AND | OR )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set135=(Token)input.LT(1);

            if ( input.LA(1)==AND||input.LA(1)==OR ) {
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

        Token set136=null;

        Object set136_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:329:2: ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set136=(Token)input.LT(1);

            if ( input.LA(1)==EQUALS||(input.LA(1) >= GREATEREQUALS && input.LA(1) <= GREATERTHAN)||(input.LA(1) >= LESSEQUALS && input.LA(1) <= LESSTHAN)||input.LA(1)==NEQUALS ) {
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

        Token set137=null;

        Object set137_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:338:2: ( VSUM | VPRODUCT | VAVERAGE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set137=(Token)input.LT(1);

            if ( input.LA(1)==VAVERAGE||(input.LA(1) >= VPRODUCT && input.LA(1) <= VSUM) ) {
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

        Token set138=null;

        Object set138_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:344:2: ( ALL | SOME | NONE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set138=(Token)input.LT(1);

            if ( input.LA(1)==ALL||input.LA(1)==NONE||input.LA(1)==SOME ) {
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
        pushFollow(FOLLOW_expr_in_synpred1_BACON1656);
        expr();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_comparators_in_synpred1_BACON1658);
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
    public static final BitSet FOLLOW_CHANGE_in_rule21169 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21171 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21173 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21175 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_changeExpr_in_rule21177 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_DIVIDE_in_rule21194 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21196 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21198 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_rule21213 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21215 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_rule21217 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_rule21219 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_rule21221 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21223 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_WITH_in_rule21228 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LSQUARE_in_rule21230 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_assignList_in_rule21232 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_RSQUARE_in_rule21234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21256 = new BitSet(new long[]{0x2000015004104000L,0x0000000000005000L});
    public static final BitSet FOLLOW_rule2_in_rule21258 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BAR_in_changeExpr1276 = new BitSet(new long[]{0x4163B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_changeCond_in_changeExpr1278 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_TO_in_changeExpr1280 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_changeExpr1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OTHERWISE_in_changeCond1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr_in_changeCond1309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_assign1320 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_EQUALS_in_assign1322 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_assign1324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_assignList1345 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_assignList1348 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_assign_in_assignList1350 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_expr2_in_expr1378 = new BitSet(new long[]{0x0402000000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_expr1392 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr2_in_expr1395 = new BitSet(new long[]{0x0402000000000002L});
    public static final BitSet FOLLOW_expr3_in_expr21409 = new BitSet(new long[]{0x0004000002000002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_expr21423 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr3_in_expr21427 = new BitSet(new long[]{0x0004000002000002L});
    public static final BitSet FOLLOW_expr4_in_expr31441 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_expr31455 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr4_in_expr31458 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41474 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41476 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryPrimitives_in_expr41487 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41489 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41491 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expr41506 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_expr41521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_expr41526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr41531 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr_in_expr41533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_THEN_in_expr41535 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41537 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ELSE_in_expr41539 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binPrim_in_expr41558 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41560 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41562 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_expr41564 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41566 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARHIST_in_expr41583 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41585 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_VAR_in_expr41587 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_COMMA_in_expr41589 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41591 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vOp_in_expr41608 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41610 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_expr41612 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1634 = new BitSet(new long[]{0x0080000000000082L});
    public static final BitSet FOLLOW_lowPrecBoolOp_in_bExpr1637 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1640 = new BitSet(new long[]{0x0080000000000082L});
    public static final BitSet FOLLOW_expr_in_bExpr21664 = new BitSet(new long[]{0x0010060C10000000L});
    public static final BitSet FOLLOW_comparators_in_bExpr21666 = new BitSet(new long[]{0x4003B19320E80930L,0x00000000000FE1D8L});
    public static final BitSet FOLLOW_expr_in_bExpr21669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bExpr21674 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21676 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21678 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21693 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21695 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vBOp_in_bExpr21706 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21708 = new BitSet(new long[]{0x4063B19320E80970L,0x00000000000FE1F8L});
    public static final BitSet FOLLOW_bExpr_in_bExpr21710 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POW_in_highPrecMathOp1882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred1_BACON1656 = new BitSet(new long[]{0x0010060C10000000L});
    public static final BitSet FOLLOW_comparators_in_synpred1_BACON1658 = new BitSet(new long[]{0x0000000000000002L});

}