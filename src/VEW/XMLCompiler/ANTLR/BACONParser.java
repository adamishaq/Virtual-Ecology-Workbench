// $ANTLR 3.4 C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2011-12-02 21:20:54

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACOS", "ALL", "AND", "ASIN", "ASSIGN", "ASSIGNLIST", "ATAN", "BEXPR", "CHANGE", "COLON", "COMMA", "COMMENT", "COS", "CREATE", "DENSITYAT", "DEPTHFORFI", "DEPTHFORVI", "DIGIT", "DIV", "DIVIDE", "ELSE", "EQUALS", "EXP", "EXPONENT", "EXPR", "FLOAT", "FULLIRRADAT", "GREATEREQUALS", "GREATERTHAN", "IF", "IGNORE", "INGEST", "INTEGRATE", "LBRACKET", "LESSEQUALS", "LESSTHAN", "LETTER", "LN", "LOGTEN", "LSQUARE", "MAX", "MIN", "MINUS", "MUL", "NEG", "NEQUALS", "NONE", "NOT", "OR", "PCHANGE", "PLUS", "POW", "RBRACKET", "RELEASE", "RND", "RSQUARE", "RULE", "RULENAME", "RULES", "SALINITYAT", "SIN", "SOME", "SQRT", "TAN", "TEMPAT", "THEN", "UNKNOWN", "UPTAKE", "UVIRRADAT", "VAR", "VARHIST", "VAVERAGE", "VISIRRADAT", "VPRODUCT", "VSUM", "WITH"
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
    public static final int EXPONENT=28;
    public static final int EXPR=29;
    public static final int FLOAT=30;
    public static final int FULLIRRADAT=31;
    public static final int GREATEREQUALS=32;
    public static final int GREATERTHAN=33;
    public static final int IF=34;
    public static final int IGNORE=35;
    public static final int INGEST=36;
    public static final int INTEGRATE=37;
    public static final int LBRACKET=38;
    public static final int LESSEQUALS=39;
    public static final int LESSTHAN=40;
    public static final int LETTER=41;
    public static final int LN=42;
    public static final int LOGTEN=43;
    public static final int LSQUARE=44;
    public static final int MAX=45;
    public static final int MIN=46;
    public static final int MINUS=47;
    public static final int MUL=48;
    public static final int NEG=49;
    public static final int NEQUALS=50;
    public static final int NONE=51;
    public static final int NOT=52;
    public static final int OR=53;
    public static final int PCHANGE=54;
    public static final int PLUS=55;
    public static final int POW=56;
    public static final int RBRACKET=57;
    public static final int RELEASE=58;
    public static final int RND=59;
    public static final int RSQUARE=60;
    public static final int RULE=61;
    public static final int RULENAME=62;
    public static final int RULES=63;
    public static final int SALINITYAT=64;
    public static final int SIN=65;
    public static final int SOME=66;
    public static final int SQRT=67;
    public static final int TAN=68;
    public static final int TEMPAT=69;
    public static final int THEN=70;
    public static final int UNKNOWN=71;
    public static final int UPTAKE=72;
    public static final int UVIRRADAT=73;
    public static final int VAR=74;
    public static final int VARHIST=75;
    public static final int VAVERAGE=76;
    public static final int VISIRRADAT=77;
    public static final int VPRODUCT=78;
    public static final int VSUM=79;
    public static final int WITH=80;

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
        this.state.ruleMemo = new HashMap[23+1];
         

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

    @Override
    public void emitErrorMessage(String message) {
    	// Don't put error messages on the command line!
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:183:1: rules : pair ( pair )* EOF -> ^( RULES pair ( pair )* ) ;
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

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:2: ( pair ( pair )* EOF -> ^( RULES pair ( pair )* ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:4: pair ( pair )* EOF
            {
            pushFollow(FOLLOW_pair_in_rules921);
            pair1=pair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pair.add(pair1.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:9: ( pair )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==CHANGE||LA1_0==CREATE||LA1_0==DIVIDE||LA1_0==IF||LA1_0==INGEST||LA1_0==LBRACKET||LA1_0==PCHANGE||LA1_0==RELEASE||LA1_0==RULENAME||LA1_0==UPTAKE||LA1_0==VAR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:10: pair
            	    {
            	    pushFollow(FOLLOW_pair_in_rules924);
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


            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_rules928); if (state.failed) return retval; 
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
            // 184:21: -> ^( RULES pair ( pair )* )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:24: ^( RULES pair ( pair )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(RULES, "RULES")
                , root_1);

                adaptor.addChild(root_1, stream_pair.nextTree());

                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:184:37: ( pair )*
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:187:1: pair : ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule );
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

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:188:2: ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule )
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
            case PCHANGE:
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:188:4: ruleName rule2
                    {
                    pushFollow(FOLLOW_ruleName_in_pair952);
                    ruleName4=ruleName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleName.add(ruleName4.getTree());

                    pushFollow(FOLLOW_rule2_in_pair954);
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
                    // 188:19: -> ^( RULE ruleName rule2 )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:188:22: ^( RULE ruleName rule2 )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:189:4: rule
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_rule_in_pair969);
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:192:1: ruleName : ( RULENAME COLON -> RULENAME | VAR COLON -> VAR );
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

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:193:2: ( RULENAME COLON -> RULENAME | VAR COLON -> VAR )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:193:4: RULENAME COLON
                    {
                    RULENAME7=(Token)match(input,RULENAME,FOLLOW_RULENAME_in_ruleName981); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULENAME.add(RULENAME7);


                    COLON8=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName983); if (state.failed) return retval; 
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
                    // 193:19: -> RULENAME
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:194:4: VAR COLON
                    {
                    VAR9=(Token)match(input,VAR,FOLLOW_VAR_in_ruleName992); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR9);


                    COLON10=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName994); if (state.failed) return retval; 
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
                    // 194:14: -> VAR
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:197:1: rule : rule2 -> ^( RULE rule2 ) ;
    public final BACONParser.rule_return rule() throws RecognitionException {
        BACONParser.rule_return retval = new BACONParser.rule_return();
        retval.start = input.LT(1);

        int rule_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.rule2_return rule211 =null;


        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:198:2: ( rule2 -> ^( RULE rule2 ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:198:4: rule2
            {
            pushFollow(FOLLOW_rule2_in_rule1009);
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
            // 198:10: -> ^( RULE rule2 )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:198:13: ^( RULE rule2 )
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:201:1: rule2 : ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 );
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
        Token PCHANGE41=null;
        Token LBRACKET42=null;
        Token VAR43=null;
        Token COMMA44=null;
        Token RBRACKET46=null;
        Token DIVIDE47=null;
        Token LBRACKET48=null;
        Token RBRACKET50=null;
        Token CREATE51=null;
        Token LBRACKET52=null;
        Token VAR53=null;
        Token COMMA54=null;
        Token RBRACKET56=null;
        Token WITH57=null;
        Token LSQUARE58=null;
        Token RSQUARE60=null;
        Token LBRACKET61=null;
        Token RBRACKET63=null;
        BACONParser.assign_return assign12 =null;

        BACONParser.bExpr_return bExpr14 =null;

        BACONParser.rule_return rule16 =null;

        BACONParser.expr_return expr21 =null;

        BACONParser.expr_return expr27 =null;

        BACONParser.expr_return expr33 =null;

        BACONParser.expr_return expr35 =null;

        BACONParser.expr_return expr45 =null;

        BACONParser.expr_return expr49 =null;

        BACONParser.expr_return expr55 =null;

        BACONParser.assignList_return assignList59 =null;

        BACONParser.rule2_return rule262 =null;


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
        Object PCHANGE41_tree=null;
        Object LBRACKET42_tree=null;
        Object VAR43_tree=null;
        Object COMMA44_tree=null;
        Object RBRACKET46_tree=null;
        Object DIVIDE47_tree=null;
        Object LBRACKET48_tree=null;
        Object RBRACKET50_tree=null;
        Object CREATE51_tree=null;
        Object LBRACKET52_tree=null;
        Object VAR53_tree=null;
        Object COMMA54_tree=null;
        Object RBRACKET56_tree=null;
        Object WITH57_tree=null;
        Object LSQUARE58_tree=null;
        Object RSQUARE60_tree=null;
        Object LBRACKET61_tree=null;
        Object RBRACKET63_tree=null;
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
        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        RewriteRuleSubtreeStream stream_assignList=new RewriteRuleSubtreeStream(adaptor,"rule assignList");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:202:2: ( assign | IF bExpr THEN rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 )
            int alt5=10;
            switch ( input.LA(1) ) {
            case VAR:
                {
                alt5=1;
                }
                break;
            case IF:
                {
                alt5=2;
                }
                break;
            case UPTAKE:
                {
                alt5=3;
                }
                break;
            case RELEASE:
                {
                alt5=4;
                }
                break;
            case INGEST:
                {
                alt5=5;
                }
                break;
            case CHANGE:
                {
                alt5=6;
                }
                break;
            case PCHANGE:
                {
                alt5=7;
                }
                break;
            case DIVIDE:
                {
                alt5=8;
                }
                break;
            case CREATE:
                {
                alt5=9;
                }
                break;
            case LBRACKET:
                {
                alt5=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:202:4: assign
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assign_in_rule21028);
                    assign12=assign();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign12.getTree());

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:4: IF bExpr THEN rule
                    {
                    IF13=(Token)match(input,IF,FOLLOW_IF_in_rule21033); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF13);


                    pushFollow(FOLLOW_bExpr_in_rule21035);
                    bExpr14=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr14.getTree());

                    THEN15=(Token)match(input,THEN,FOLLOW_THEN_in_rule21037); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN15);


                    pushFollow(FOLLOW_rule_in_rule21039);
                    rule16=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule16.getTree());

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
                    // 203:23: -> ^( IF bExpr rule )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:203:26: ^( IF bExpr rule )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:204:4: UPTAKE LBRACKET VAR COMMA expr RBRACKET
                    {
                    UPTAKE17=(Token)match(input,UPTAKE,FOLLOW_UPTAKE_in_rule21054); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UPTAKE.add(UPTAKE17);


                    LBRACKET18=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21056); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET18);


                    VAR19=(Token)match(input,VAR,FOLLOW_VAR_in_rule21058); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR19);


                    COMMA20=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21060); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA20);


                    pushFollow(FOLLOW_expr_in_rule21062);
                    expr21=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr21.getTree());

                    RBRACKET22=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21064); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET22);


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
                    // 204:44: -> ^( UPTAKE VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:204:47: ^( UPTAKE VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:205:4: RELEASE LBRACKET VAR COMMA expr RBRACKET
                    {
                    RELEASE23=(Token)match(input,RELEASE,FOLLOW_RELEASE_in_rule21079); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RELEASE.add(RELEASE23);


                    LBRACKET24=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21081); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET24);


                    VAR25=(Token)match(input,VAR,FOLLOW_VAR_in_rule21083); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR25);


                    COMMA26=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21085); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA26);


                    pushFollow(FOLLOW_expr_in_rule21087);
                    expr27=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr27.getTree());

                    RBRACKET28=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21089); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET28);


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
                    // 205:45: -> ^( RELEASE VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:205:48: ^( RELEASE VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:206:4: INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET
                    {
                    INGEST29=(Token)match(input,INGEST,FOLLOW_INGEST_in_rule21104); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INGEST.add(INGEST29);


                    LBRACKET30=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21106); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET30);


                    VAR31=(Token)match(input,VAR,FOLLOW_VAR_in_rule21108); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR31);


                    COMMA32=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21110); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA32);


                    pushFollow(FOLLOW_expr_in_rule21112);
                    expr33=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr33.getTree());

                    COMMA34=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21114); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA34);


                    pushFollow(FOLLOW_expr_in_rule21116);
                    expr35=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr35.getTree());

                    RBRACKET36=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21118); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET36);


                    // AST REWRITE
                    // elements: INGEST, expr, expr, VAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 206:55: -> ^( INGEST VAR expr expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:206:58: ^( INGEST VAR expr expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:207:4: CHANGE LBRACKET VAR RBRACKET
                    {
                    CHANGE37=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21135); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE37);


                    LBRACKET38=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21137); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET38);


                    VAR39=(Token)match(input,VAR,FOLLOW_VAR_in_rule21139); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR39);


                    RBRACKET40=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21141); if (state.failed) return retval; 
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
                    // 207:33: -> ^( CHANGE VAR )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:207:36: ^( CHANGE VAR )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:208:4: PCHANGE LBRACKET VAR COMMA expr RBRACKET
                    {
                    PCHANGE41=(Token)match(input,PCHANGE,FOLLOW_PCHANGE_in_rule21154); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_PCHANGE.add(PCHANGE41);


                    LBRACKET42=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21156); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET42);


                    VAR43=(Token)match(input,VAR,FOLLOW_VAR_in_rule21158); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR43);


                    COMMA44=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21160); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA44);


                    pushFollow(FOLLOW_expr_in_rule21162);
                    expr45=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr45.getTree());

                    RBRACKET46=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21164); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET46);


                    // AST REWRITE
                    // elements: PCHANGE, expr, VAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 208:45: -> ^( PCHANGE VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:208:48: ^( PCHANGE VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:4: DIVIDE LBRACKET expr RBRACKET
                    {
                    DIVIDE47=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_rule21179); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIVIDE.add(DIVIDE47);


                    LBRACKET48=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21181); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET48);


                    pushFollow(FOLLOW_expr_in_rule21183);
                    expr49=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr49.getTree());

                    RBRACKET50=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21185); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET50);


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
                    // 209:34: -> ^( DIVIDE expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:37: ^( DIVIDE expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:210:4: CREATE LBRACKET VAR COMMA expr RBRACKET ( WITH LSQUARE assignList RSQUARE )?
                    {
                    CREATE51=(Token)match(input,CREATE,FOLLOW_CREATE_in_rule21198); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CREATE.add(CREATE51);


                    LBRACKET52=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21200); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET52);


                    VAR53=(Token)match(input,VAR,FOLLOW_VAR_in_rule21202); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR53);


                    COMMA54=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21204); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA54);


                    pushFollow(FOLLOW_expr_in_rule21206);
                    expr55=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr55.getTree());

                    RBRACKET56=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21208); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET56);


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:211:3: ( WITH LSQUARE assignList RSQUARE )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==WITH) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:211:4: WITH LSQUARE assignList RSQUARE
                            {
                            WITH57=(Token)match(input,WITH,FOLLOW_WITH_in_rule21213); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_WITH.add(WITH57);


                            LSQUARE58=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_rule21215); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE58);


                            pushFollow(FOLLOW_assignList_in_rule21217);
                            assignList59=assignList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignList.add(assignList59.getTree());

                            RSQUARE60=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_rule21219); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE60);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: expr, CREATE, VAR, assignList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 211:38: -> ^( CREATE VAR expr ( assignList )? )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:211:41: ^( CREATE VAR expr ( assignList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CREATE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:211:59: ( assignList )?
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:212:4: LBRACKET rule2 RBRACKET
                    {
                    LBRACKET61=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21241); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET61);


                    pushFollow(FOLLOW_rule2_in_rule21243);
                    rule262=rule2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule2.add(rule262.getTree());

                    RBRACKET63=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21245); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET63);


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
                    // 212:28: -> rule2
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


    public static class assign_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assign"
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:215:1: assign : VAR EQUALS expr -> ^( ASSIGN VAR expr ) ;
    public final BACONParser.assign_return assign() throws RecognitionException {
        BACONParser.assign_return retval = new BACONParser.assign_return();
        retval.start = input.LT(1);

        int assign_StartIndex = input.index();

        Object root_0 = null;

        Token VAR64=null;
        Token EQUALS65=null;
        BACONParser.expr_return expr66 =null;


        Object VAR64_tree=null;
        Object EQUALS65_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:216:2: ( VAR EQUALS expr -> ^( ASSIGN VAR expr ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:216:4: VAR EQUALS expr
            {
            VAR64=(Token)match(input,VAR,FOLLOW_VAR_in_assign1260); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR64);


            EQUALS65=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assign1262); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS65);


            pushFollow(FOLLOW_expr_in_assign1264);
            expr66=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr66.getTree());

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
            // 216:20: -> ^( ASSIGN VAR expr )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:216:23: ^( ASSIGN VAR expr )
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:219:1: assignList : assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) ;
    public final BACONParser.assignList_return assignList() throws RecognitionException {
        BACONParser.assignList_return retval = new BACONParser.assignList_return();
        retval.start = input.LT(1);

        int assignList_StartIndex = input.index();

        Object root_0 = null;

        Token COMMA68=null;
        BACONParser.assign_return assign67 =null;

        BACONParser.assign_return assign69 =null;


        Object COMMA68_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_assign=new RewriteRuleSubtreeStream(adaptor,"rule assign");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:220:2: ( assign ( COMMA assign )* -> ^( ASSIGNLIST assign ( assign )* ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:220:4: assign ( COMMA assign )*
            {
            pushFollow(FOLLOW_assign_in_assignList1285);
            assign67=assign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assign.add(assign67.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:220:11: ( COMMA assign )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COMMA) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:220:12: COMMA assign
            	    {
            	    COMMA68=(Token)match(input,COMMA,FOLLOW_COMMA_in_assignList1288); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA68);


            	    pushFollow(FOLLOW_assign_in_assignList1290);
            	    assign69=assign();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_assign.add(assign69.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
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
            // 220:27: -> ^( ASSIGNLIST assign ( assign )* )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:220:30: ^( ASSIGNLIST assign ( assign )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGNLIST, "ASSIGNLIST")
                , root_1);

                adaptor.addChild(root_1, stream_assign.nextTree());

                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:220:50: ( assign )*
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:223:1: expr : expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* ;
    public final BACONParser.expr_return expr() throws RecognitionException {
        BACONParser.expr_return retval = new BACONParser.expr_return();
        retval.start = input.LT(1);

        int expr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr2_return expr270 =null;

        BACONParser.lowPrecMathOp_return lowPrecMathOp71 =null;

        BACONParser.expr2_return expr272 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:2: ( expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:4: expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr2_in_expr1318);
            expr270=expr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr270.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:10: ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==MINUS||LA7_0==PLUS) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:224:38: lowPrecMathOp ^ expr2
            	    {
            	    pushFollow(FOLLOW_lowPrecMathOp_in_expr1332);
            	    lowPrecMathOp71=lowPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecMathOp71.getTree(), root_0);

            	    pushFollow(FOLLOW_expr2_in_expr1335);
            	    expr272=expr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr272.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:227:1: expr2 : expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* ;
    public final BACONParser.expr2_return expr2() throws RecognitionException {
        BACONParser.expr2_return retval = new BACONParser.expr2_return();
        retval.start = input.LT(1);

        int expr2_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr3_return expr373 =null;

        BACONParser.medPrecMathOp_return medPrecMathOp74 =null;

        BACONParser.expr3_return expr375 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:2: ( expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:4: expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr3_in_expr21349);
            expr373=expr3();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr373.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:10: ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==DIV||LA8_0==MUL) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:38: medPrecMathOp ^ expr3
            	    {
            	    pushFollow(FOLLOW_medPrecMathOp_in_expr21363);
            	    medPrecMathOp74=medPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(medPrecMathOp74.getTree(), root_0);

            	    pushFollow(FOLLOW_expr3_in_expr21367);
            	    expr375=expr3();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr375.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:231:1: expr3 : expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )* ;
    public final BACONParser.expr3_return expr3() throws RecognitionException {
        BACONParser.expr3_return retval = new BACONParser.expr3_return();
        retval.start = input.LT(1);

        int expr3_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr4_return expr476 =null;

        BACONParser.highPrecMathOp_return highPrecMathOp77 =null;

        BACONParser.expr4_return expr478 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:232:2: ( expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:232:4: expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr4_in_expr31381);
            expr476=expr4();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr476.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:232:10: ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==POW) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:232:38: highPrecMathOp ^ expr4
            	    {
            	    pushFollow(FOLLOW_highPrecMathOp_in_expr31395);
            	    highPrecMathOp77=highPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(highPrecMathOp77.getTree(), root_0);

            	    pushFollow(FOLLOW_expr4_in_expr31398);
            	    expr478=expr4();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr478.getTree());

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:1: expr4 : ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | MINUS expr -> ^( NEG expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) );
    public final BACONParser.expr4_return expr4() throws RecognitionException {
        BACONParser.expr4_return retval = new BACONParser.expr4_return();
        retval.start = input.LT(1);

        int expr4_StartIndex = input.index();

        Object root_0 = null;

        Token LBRACKET79=null;
        Token RBRACKET81=null;
        Token LBRACKET83=null;
        Token RBRACKET85=null;
        Token MINUS86=null;
        Token FLOAT88=null;
        Token VAR89=null;
        Token IF90=null;
        Token THEN92=null;
        Token ELSE94=null;
        Token LBRACKET97=null;
        Token COMMA99=null;
        Token RBRACKET101=null;
        Token VARHIST102=null;
        Token LBRACKET103=null;
        Token VAR104=null;
        Token COMMA105=null;
        Token RBRACKET107=null;
        Token LBRACKET109=null;
        Token RBRACKET111=null;
        BACONParser.expr_return expr80 =null;

        BACONParser.unaryPrimitives_return unaryPrimitives82 =null;

        BACONParser.expr_return expr84 =null;

        BACONParser.expr_return expr87 =null;

        BACONParser.bExpr_return bExpr91 =null;

        BACONParser.expr_return expr93 =null;

        BACONParser.expr_return expr95 =null;

        BACONParser.binPrim_return binPrim96 =null;

        BACONParser.expr_return expr98 =null;

        BACONParser.expr_return expr100 =null;

        BACONParser.expr_return expr106 =null;

        BACONParser.vOp_return vOp108 =null;

        BACONParser.expr_return expr110 =null;


        Object LBRACKET79_tree=null;
        Object RBRACKET81_tree=null;
        Object LBRACKET83_tree=null;
        Object RBRACKET85_tree=null;
        Object MINUS86_tree=null;
        Object FLOAT88_tree=null;
        Object VAR89_tree=null;
        Object IF90_tree=null;
        Object THEN92_tree=null;
        Object ELSE94_tree=null;
        Object LBRACKET97_tree=null;
        Object COMMA99_tree=null;
        Object RBRACKET101_tree=null;
        Object VARHIST102_tree=null;
        Object LBRACKET103_tree=null;
        Object VAR104_tree=null;
        Object COMMA105_tree=null;
        Object RBRACKET107_tree=null;
        Object LBRACKET109_tree=null;
        Object RBRACKET111_tree=null;
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
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:3: ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | MINUS expr -> ^( NEG expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) )
            int alt10=9;
            switch ( input.LA(1) ) {
            case LBRACKET:
                {
                alt10=1;
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
                alt10=2;
                }
                break;
            case MINUS:
                {
                alt10=3;
                }
                break;
            case FLOAT:
                {
                alt10=4;
                }
                break;
            case VAR:
                {
                alt10=5;
                }
                break;
            case IF:
                {
                alt10=6;
                }
                break;
            case MAX:
            case MIN:
                {
                alt10=7;
                }
                break;
            case VARHIST:
                {
                alt10=8;
                }
                break;
            case VAVERAGE:
            case VPRODUCT:
            case VSUM:
                {
                alt10=9;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:5: LBRACKET expr RBRACKET
                    {
                    LBRACKET79=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41414); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET79);


                    pushFollow(FOLLOW_expr_in_expr41416);
                    expr80=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr80.getTree());

                    RBRACKET81=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41418); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET81);


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
                    // 237:28: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:238:4: unaryPrimitives LBRACKET expr RBRACKET
                    {
                    pushFollow(FOLLOW_unaryPrimitives_in_expr41427);
                    unaryPrimitives82=unaryPrimitives();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryPrimitives.add(unaryPrimitives82.getTree());

                    LBRACKET83=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41429); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET83);


                    pushFollow(FOLLOW_expr_in_expr41431);
                    expr84=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr84.getTree());

                    RBRACKET85=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41433); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET85);


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
                    // 238:43: -> ^( unaryPrimitives expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:238:46: ^( unaryPrimitives expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:239:4: MINUS expr
                    {
                    MINUS86=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr41446); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_MINUS.add(MINUS86);


                    pushFollow(FOLLOW_expr_in_expr41448);
                    expr87=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr87.getTree());

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
                    // 239:15: -> ^( NEG expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:239:18: ^( NEG expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:240:4: FLOAT
                    {
                    root_0 = (Object)adaptor.nil();


                    FLOAT88=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_expr41461); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT88_tree = 
                    (Object)adaptor.create(FLOAT88)
                    ;
                    adaptor.addChild(root_0, FLOAT88_tree);
                    }

                    }
                    break;
                case 5 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:4: VAR
                    {
                    root_0 = (Object)adaptor.nil();


                    VAR89=(Token)match(input,VAR,FOLLOW_VAR_in_expr41466); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR89_tree = 
                    (Object)adaptor.create(VAR89)
                    ;
                    adaptor.addChild(root_0, VAR89_tree);
                    }

                    }
                    break;
                case 6 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:242:4: IF bExpr THEN expr ELSE expr
                    {
                    IF90=(Token)match(input,IF,FOLLOW_IF_in_expr41471); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF90);


                    pushFollow(FOLLOW_bExpr_in_expr41473);
                    bExpr91=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr91.getTree());

                    THEN92=(Token)match(input,THEN,FOLLOW_THEN_in_expr41475); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN92);


                    pushFollow(FOLLOW_expr_in_expr41477);
                    expr93=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr93.getTree());

                    ELSE94=(Token)match(input,ELSE,FOLLOW_ELSE_in_expr41479); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELSE.add(ELSE94);


                    pushFollow(FOLLOW_expr_in_expr41481);
                    expr95=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr95.getTree());

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
                    // 242:33: -> ^( IF bExpr expr expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:242:36: ^( IF bExpr expr expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:243:4: binPrim LBRACKET expr COMMA expr RBRACKET
                    {
                    pushFollow(FOLLOW_binPrim_in_expr41498);
                    binPrim96=binPrim();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binPrim.add(binPrim96.getTree());

                    LBRACKET97=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41500); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET97);


                    pushFollow(FOLLOW_expr_in_expr41502);
                    expr98=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr98.getTree());

                    COMMA99=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41504); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA99);


                    pushFollow(FOLLOW_expr_in_expr41506);
                    expr100=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr100.getTree());

                    RBRACKET101=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41508); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET101);


                    // AST REWRITE
                    // elements: binPrim, expr, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 243:46: -> ^( binPrim expr expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:243:49: ^( binPrim expr expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:244:4: VARHIST LBRACKET VAR COMMA expr RBRACKET
                    {
                    VARHIST102=(Token)match(input,VARHIST,FOLLOW_VARHIST_in_expr41523); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VARHIST.add(VARHIST102);


                    LBRACKET103=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41525); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET103);


                    VAR104=(Token)match(input,VAR,FOLLOW_VAR_in_expr41527); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR104);


                    COMMA105=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41529); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA105);


                    pushFollow(FOLLOW_expr_in_expr41531);
                    expr106=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr106.getTree());

                    RBRACKET107=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41533); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET107);


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
                    // 244:45: -> ^( VARHIST VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:244:48: ^( VARHIST VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:4: vOp LBRACKET expr RBRACKET
                    {
                    pushFollow(FOLLOW_vOp_in_expr41548);
                    vOp108=vOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vOp.add(vOp108.getTree());

                    LBRACKET109=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41550); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET109);


                    pushFollow(FOLLOW_expr_in_expr41552);
                    expr110=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr110.getTree());

                    RBRACKET111=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41554); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET111);


                    // AST REWRITE
                    // elements: vOp, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 245:31: -> ^( vOp expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:34: ^( vOp expr )
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:1: bExpr : bExpr2 ( lowPrecBoolOp ^ bExpr2 )* ;
    public final BACONParser.bExpr_return bExpr() throws RecognitionException {
        BACONParser.bExpr_return retval = new BACONParser.bExpr_return();
        retval.start = input.LT(1);

        int bExpr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.bExpr2_return bExpr2112 =null;

        BACONParser.lowPrecBoolOp_return lowPrecBoolOp113 =null;

        BACONParser.bExpr2_return bExpr2114 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:250:2: ( bExpr2 ( lowPrecBoolOp ^ bExpr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:250:4: bExpr2 ( lowPrecBoolOp ^ bExpr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_bExpr2_in_bExpr1574);
            bExpr2112=bExpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2112.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:250:11: ( lowPrecBoolOp ^ bExpr2 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==AND||LA11_0==OR) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:250:12: lowPrecBoolOp ^ bExpr2
            	    {
            	    pushFollow(FOLLOW_lowPrecBoolOp_in_bExpr1577);
            	    lowPrecBoolOp113=lowPrecBoolOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecBoolOp113.getTree(), root_0);

            	    pushFollow(FOLLOW_bExpr2_in_bExpr1580);
            	    bExpr2114=bExpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2114.getTree());

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:1: bExpr2 : ( ( expr comparators )=> expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) );
    public final BACONParser.bExpr2_return bExpr2() throws RecognitionException {
        BACONParser.bExpr2_return retval = new BACONParser.bExpr2_return();
        retval.start = input.LT(1);

        int bExpr2_StartIndex = input.index();

        Object root_0 = null;

        Token NOT118=null;
        Token LBRACKET119=null;
        Token RBRACKET121=null;
        Token LBRACKET122=null;
        Token RBRACKET124=null;
        Token LBRACKET126=null;
        Token RBRACKET128=null;
        BACONParser.expr_return expr115 =null;

        BACONParser.comparators_return comparators116 =null;

        BACONParser.expr_return expr117 =null;

        BACONParser.bExpr_return bExpr120 =null;

        BACONParser.bExpr_return bExpr123 =null;

        BACONParser.vBOp_return vBOp125 =null;

        BACONParser.bExpr_return bExpr127 =null;


        Object NOT118_tree=null;
        Object LBRACKET119_tree=null;
        Object RBRACKET121_tree=null;
        Object LBRACKET122_tree=null;
        Object RBRACKET124_tree=null;
        Object LBRACKET126_tree=null;
        Object RBRACKET128_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_vBOp=new RewriteRuleSubtreeStream(adaptor,"rule vBOp");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:254:2: ( ( expr comparators )=> expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) )
            int alt12=4;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==LBRACKET) ) {
                int LA12_1 = input.LA(2);

                if ( (synpred1_BACON()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;

                }
            }
            else if ( ((LA12_0 >= ABS && LA12_0 <= ACOS)||LA12_0==ASIN||LA12_0==ATAN||LA12_0==COS||(LA12_0 >= DENSITYAT && LA12_0 <= DEPTHFORVI)||LA12_0==EXP||LA12_0==FULLIRRADAT||LA12_0==INTEGRATE||(LA12_0 >= LN && LA12_0 <= LOGTEN)||LA12_0==RND||(LA12_0 >= SALINITYAT && LA12_0 <= SIN)||(LA12_0 >= SQRT && LA12_0 <= TEMPAT)||LA12_0==UVIRRADAT||LA12_0==VISIRRADAT) && (synpred1_BACON())) {
                alt12=1;
            }
            else if ( (LA12_0==MINUS) && (synpred1_BACON())) {
                alt12=1;
            }
            else if ( (LA12_0==FLOAT) && (synpred1_BACON())) {
                alt12=1;
            }
            else if ( (LA12_0==VAR) && (synpred1_BACON())) {
                alt12=1;
            }
            else if ( (LA12_0==IF) && (synpred1_BACON())) {
                alt12=1;
            }
            else if ( ((LA12_0 >= MAX && LA12_0 <= MIN)) && (synpred1_BACON())) {
                alt12=1;
            }
            else if ( (LA12_0==VARHIST) && (synpred1_BACON())) {
                alt12=1;
            }
            else if ( (LA12_0==VAVERAGE||(LA12_0 >= VPRODUCT && LA12_0 <= VSUM)) && (synpred1_BACON())) {
                alt12=1;
            }
            else if ( (LA12_0==NOT) ) {
                alt12=2;
            }
            else if ( (LA12_0==ALL||LA12_0==NONE||LA12_0==SOME) ) {
                alt12=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:254:4: ( expr comparators )=> expr comparators ^ expr
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_bExpr21604);
                    expr115=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr115.getTree());

                    pushFollow(FOLLOW_comparators_in_bExpr21606);
                    comparators116=comparators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(comparators116.getTree(), root_0);

                    pushFollow(FOLLOW_expr_in_bExpr21609);
                    expr117=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr117.getTree());

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:255:4: NOT LBRACKET bExpr RBRACKET
                    {
                    NOT118=(Token)match(input,NOT,FOLLOW_NOT_in_bExpr21614); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT118);


                    LBRACKET119=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21616); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET119);


                    pushFollow(FOLLOW_bExpr_in_bExpr21618);
                    bExpr120=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr120.getTree());

                    RBRACKET121=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21620); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET121);


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
                    // 255:32: -> ^( NOT bExpr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:255:35: ^( NOT bExpr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:256:4: LBRACKET bExpr RBRACKET
                    {
                    LBRACKET122=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21633); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET122);


                    pushFollow(FOLLOW_bExpr_in_bExpr21635);
                    bExpr123=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr123.getTree());

                    RBRACKET124=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21637); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET124);


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
                    // 256:28: -> bExpr
                    {
                        adaptor.addChild(root_0, stream_bExpr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:4: vBOp LBRACKET bExpr RBRACKET
                    {
                    pushFollow(FOLLOW_vBOp_in_bExpr21646);
                    vBOp125=vBOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vBOp.add(vBOp125.getTree());

                    LBRACKET126=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21648); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET126);


                    pushFollow(FOLLOW_bExpr_in_bExpr21650);
                    bExpr127=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr127.getTree());

                    RBRACKET128=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21652); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET128);


                    // AST REWRITE
                    // elements: vBOp, bExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 257:33: -> ^( vBOp bExpr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:36: ^( vBOp bExpr )
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:263:1: unaryPrimitives : ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE | VISIRRADAT );
    public final BACONParser.unaryPrimitives_return unaryPrimitives() throws RecognitionException {
        BACONParser.unaryPrimitives_return retval = new BACONParser.unaryPrimitives_return();
        retval.start = input.LT(1);

        int unaryPrimitives_StartIndex = input.index();

        Object root_0 = null;

        Token set129=null;

        Object set129_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:264:2: ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE | VISIRRADAT )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set129=(Token)input.LT(1);

            if ( (input.LA(1) >= ABS && input.LA(1) <= ACOS)||input.LA(1)==ASIN||input.LA(1)==ATAN||input.LA(1)==COS||(input.LA(1) >= DENSITYAT && input.LA(1) <= DEPTHFORVI)||input.LA(1)==EXP||input.LA(1)==FULLIRRADAT||input.LA(1)==INTEGRATE||(input.LA(1) >= LN && input.LA(1) <= LOGTEN)||input.LA(1)==RND||(input.LA(1) >= SALINITYAT && input.LA(1) <= SIN)||(input.LA(1) >= SQRT && input.LA(1) <= TEMPAT)||input.LA(1)==UVIRRADAT||input.LA(1)==VISIRRADAT ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (Object)adaptor.create(set129)
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:287:1: lowPrecMathOp : ( PLUS | MINUS );
    public final BACONParser.lowPrecMathOp_return lowPrecMathOp() throws RecognitionException {
        BACONParser.lowPrecMathOp_return retval = new BACONParser.lowPrecMathOp_return();
        retval.start = input.LT(1);

        int lowPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set130=null;

        Object set130_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:288:2: ( PLUS | MINUS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set130=(Token)input.LT(1);

            if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:292:1: medPrecMathOp : ( MUL | DIV );
    public final BACONParser.medPrecMathOp_return medPrecMathOp() throws RecognitionException {
        BACONParser.medPrecMathOp_return retval = new BACONParser.medPrecMathOp_return();
        retval.start = input.LT(1);

        int medPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set131=null;

        Object set131_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:293:2: ( MUL | DIV )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set131=(Token)input.LT(1);

            if ( input.LA(1)==DIV||input.LA(1)==MUL ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:297:1: highPrecMathOp : POW ;
    public final BACONParser.highPrecMathOp_return highPrecMathOp() throws RecognitionException {
        BACONParser.highPrecMathOp_return retval = new BACONParser.highPrecMathOp_return();
        retval.start = input.LT(1);

        int highPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token POW132=null;

        Object POW132_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:298:2: ( POW )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:298:4: POW
            {
            root_0 = (Object)adaptor.nil();


            POW132=(Token)match(input,POW,FOLLOW_POW_in_highPrecMathOp1822); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            POW132_tree = 
            (Object)adaptor.create(POW132)
            ;
            adaptor.addChild(root_0, POW132_tree);
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:301:1: binPrim : ( MIN | MAX );
    public final BACONParser.binPrim_return binPrim() throws RecognitionException {
        BACONParser.binPrim_return retval = new BACONParser.binPrim_return();
        retval.start = input.LT(1);

        int binPrim_StartIndex = input.index();

        Object root_0 = null;

        Token set133=null;

        Object set133_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:302:2: ( MIN | MAX )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set133=(Token)input.LT(1);

            if ( (input.LA(1) >= MAX && input.LA(1) <= MIN) ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:306:1: lowPrecBoolOp : ( AND | OR );
    public final BACONParser.lowPrecBoolOp_return lowPrecBoolOp() throws RecognitionException {
        BACONParser.lowPrecBoolOp_return retval = new BACONParser.lowPrecBoolOp_return();
        retval.start = input.LT(1);

        int lowPrecBoolOp_StartIndex = input.index();

        Object root_0 = null;

        Token set134=null;

        Object set134_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:307:2: ( AND | OR )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set134=(Token)input.LT(1);

            if ( input.LA(1)==AND||input.LA(1)==OR ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:311:1: comparators : ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS );
    public final BACONParser.comparators_return comparators() throws RecognitionException {
        BACONParser.comparators_return retval = new BACONParser.comparators_return();
        retval.start = input.LT(1);

        int comparators_StartIndex = input.index();

        Object root_0 = null;

        Token set135=null;

        Object set135_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:312:2: ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set135=(Token)input.LT(1);

            if ( input.LA(1)==EQUALS||(input.LA(1) >= GREATEREQUALS && input.LA(1) <= GREATERTHAN)||(input.LA(1) >= LESSEQUALS && input.LA(1) <= LESSTHAN)||input.LA(1)==NEQUALS ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:320:1: vOp : ( VSUM | VPRODUCT | VAVERAGE );
    public final BACONParser.vOp_return vOp() throws RecognitionException {
        BACONParser.vOp_return retval = new BACONParser.vOp_return();
        retval.start = input.LT(1);

        int vOp_StartIndex = input.index();

        Object root_0 = null;

        Token set136=null;

        Object set136_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:321:2: ( VSUM | VPRODUCT | VAVERAGE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set136=(Token)input.LT(1);

            if ( input.LA(1)==VAVERAGE||(input.LA(1) >= VPRODUCT && input.LA(1) <= VSUM) ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:326:1: vBOp : ( ALL | SOME | NONE );
    public final BACONParser.vBOp_return vBOp() throws RecognitionException {
        BACONParser.vBOp_return retval = new BACONParser.vBOp_return();
        retval.start = input.LT(1);

        int vBOp_StartIndex = input.index();

        Object root_0 = null;

        Token set137=null;

        Object set137_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:327:2: ( ALL | SOME | NONE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set137=(Token)input.LT(1);

            if ( input.LA(1)==ALL||input.LA(1)==NONE||input.LA(1)==SOME ) {
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
            if ( state.backtracking>0 ) { memoize(input, 22, vBOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "vBOp"

    // $ANTLR start synpred1_BACON
    public final void synpred1_BACON_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:254:4: ( expr comparators )
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:254:5: expr comparators
        {
        pushFollow(FOLLOW_expr_in_synpred1_BACON1596);
        expr();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_comparators_in_synpred1_BACON1598);
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


 

    public static final BitSet FOLLOW_pair_in_rules921 = new BitSet(new long[]{0x4440005401042000L,0x0000000000000500L});
    public static final BitSet FOLLOW_pair_in_rules924 = new BitSet(new long[]{0x4440005401042000L,0x0000000000000500L});
    public static final BitSet FOLLOW_EOF_in_rules928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_pair952 = new BitSet(new long[]{0x0440005401042000L,0x0000000000000500L});
    public static final BitSet FOLLOW_rule2_in_pair954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_pair969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULENAME_in_ruleName981 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_ruleName992 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule2_in_rule1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_rule21028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_rule21033 = new BitSet(new long[]{0x0818EC64C83A0970L,0x000000000000FE3FL});
    public static final BitSet FOLLOW_bExpr_in_rule21035 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_THEN_in_rule21037 = new BitSet(new long[]{0x0440005401042000L,0x0000000000000500L});
    public static final BitSet FOLLOW_rule_in_rule21039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPTAKE_in_rule21054 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21056 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_VAR_in_rule21058 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21060 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_rule21062 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELEASE_in_rule21079 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21081 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_VAR_in_rule21083 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21085 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_rule21087 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INGEST_in_rule21104 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_VAR_in_rule21108 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21110 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_rule21112 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21114 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_rule21116 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule21135 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_VAR_in_rule21139 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PCHANGE_in_rule21154 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21156 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_VAR_in_rule21158 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21160 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_rule21162 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDE_in_rule21179 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21181 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_rule21183 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_rule21198 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21200 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_VAR_in_rule21202 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21204 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_rule21206 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21208 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
    public static final BitSet FOLLOW_WITH_in_rule21213 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LSQUARE_in_rule21215 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_assignList_in_rule21217 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_RSQUARE_in_rule21219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21241 = new BitSet(new long[]{0x0440005401042000L,0x0000000000000500L});
    public static final BitSet FOLLOW_rule2_in_rule21243 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_assign1260 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUALS_in_assign1262 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_assign1264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_assignList1285 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_assignList1288 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_assign_in_assignList1290 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_expr2_in_expr1318 = new BitSet(new long[]{0x0080800000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_expr1332 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr2_in_expr1335 = new BitSet(new long[]{0x0080800000000002L});
    public static final BitSet FOLLOW_expr3_in_expr21349 = new BitSet(new long[]{0x0001000000800002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_expr21363 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr3_in_expr21367 = new BitSet(new long[]{0x0001000000800002L});
    public static final BitSet FOLLOW_expr4_in_expr31381 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_expr31395 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr4_in_expr31398 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41414 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_expr41416 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryPrimitives_in_expr41427 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41429 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_expr41431 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expr41446 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_expr41448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_expr41461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_expr41466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr41471 = new BitSet(new long[]{0x0818EC64C83A0970L,0x000000000000FE3FL});
    public static final BitSet FOLLOW_bExpr_in_expr41473 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_THEN_in_expr41475 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_expr41477 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ELSE_in_expr41479 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_expr41481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binPrim_in_expr41498 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41500 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_expr41502 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41504 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_expr41506 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARHIST_in_expr41523 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_VAR_in_expr41527 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41529 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_expr41531 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vOp_in_expr41548 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41550 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_expr41552 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1574 = new BitSet(new long[]{0x0020000000000082L});
    public static final BitSet FOLLOW_lowPrecBoolOp_in_bExpr1577 = new BitSet(new long[]{0x0818EC64C83A0970L,0x000000000000FE3FL});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1580 = new BitSet(new long[]{0x0020000000000082L});
    public static final BitSet FOLLOW_expr_in_bExpr21604 = new BitSet(new long[]{0x0004018304000000L});
    public static final BitSet FOLLOW_comparators_in_bExpr21606 = new BitSet(new long[]{0x0800EC64C83A0930L,0x000000000000FE3BL});
    public static final BitSet FOLLOW_expr_in_bExpr21609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bExpr21614 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21616 = new BitSet(new long[]{0x0818EC64C83A0970L,0x000000000000FE3FL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21618 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21633 = new BitSet(new long[]{0x0818EC64C83A0970L,0x000000000000FE3FL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21635 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vBOp_in_bExpr21646 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21648 = new BitSet(new long[]{0x0818EC64C83A0970L,0x000000000000FE3FL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21650 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POW_in_highPrecMathOp1822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred1_BACON1596 = new BitSet(new long[]{0x0004018304000000L});
    public static final BitSet FOLLOW_comparators_in_synpred1_BACON1598 = new BitSet(new long[]{0x0000000000000002L});

}