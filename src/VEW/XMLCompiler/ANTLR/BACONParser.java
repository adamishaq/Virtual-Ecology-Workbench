// $ANTLR 3.4 C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2011-11-25 17:51:50

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ACOS", "ALL", "AND", "ASIN", "ASSIGN", "ASSIGNLIST", "ATAN", "BEXPR", "CHANGE", "COLON", "COMMA", "COMMENT", "COS", "CREATE", "DENSITYAT", "DEPTHFORFI", "DEPTHFORVI", "DIGIT", "DIV", "DIVIDE", "ELSE", "EQUALS", "EXP", "EXPONENT", "EXPR", "FLOAT", "FULLIRRADAT", "GREATEREQUALS", "GREATERTHAN", "IF", "IGNORE", "INGEST", "INTEGRATE", "LBRACKET", "LESSEQUALS", "LESSTHAN", "LETTER", "LN", "LOGTEN", "LSQUARE", "MAX", "MIN", "MINUS", "MUL", "NEG", "NEQUALS", "NEWLINE", "NONE", "NOT", "OR", "PCHANGE", "PLUS", "POW", "RBRACKET", "RELEASE", "RND", "RSQUARE", "RULE", "RULENAME", "RULES", "SALINITYAT", "SIN", "SOME", "SQRT", "TAN", "TEMPAT", "THEN", "UNKNOWN", "UPTAKE", "UVIRRADAT", "VAR", "VARHIST", "VAVERAGE", "VISIRRADAT", "VPRODUCT", "VSUM", "WITH"
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
    public static final int NEWLINE=51;
    public static final int NONE=52;
    public static final int NOT=53;
    public static final int OR=54;
    public static final int PCHANGE=55;
    public static final int PLUS=56;
    public static final int POW=57;
    public static final int RBRACKET=58;
    public static final int RELEASE=59;
    public static final int RND=60;
    public static final int RSQUARE=61;
    public static final int RULE=62;
    public static final int RULENAME=63;
    public static final int RULES=64;
    public static final int SALINITYAT=65;
    public static final int SIN=66;
    public static final int SOME=67;
    public static final int SQRT=68;
    public static final int TAN=69;
    public static final int TEMPAT=70;
    public static final int THEN=71;
    public static final int UNKNOWN=72;
    public static final int UPTAKE=73;
    public static final int UVIRRADAT=74;
    public static final int VAR=75;
    public static final int VARHIST=76;
    public static final int VAVERAGE=77;
    public static final int VISIRRADAT=78;
    public static final int VPRODUCT=79;
    public static final int VSUM=80;
    public static final int WITH=81;

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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:208:1: rules : ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )? -> ^( RULES pair ( pair )* ) ;
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

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:2: ( ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )? -> ^( RULES pair ( pair )* ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:4: ( NEWLINE )? pair ( NEWLINE pair )* ( NEWLINE )?
            {
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:4: ( NEWLINE )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==NEWLINE) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:5: NEWLINE
                    {
                    NEWLINE1=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules951); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE1);


                    }
                    break;

            }


            pushFollow(FOLLOW_pair_in_rules955);
            pair2=pair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pair.add(pair2.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:20: ( NEWLINE pair )*
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
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:21: NEWLINE pair
            	    {
            	    NEWLINE3=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules958); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE3);


            	    pushFollow(FOLLOW_pair_in_rules960);
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


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:36: ( NEWLINE )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==NEWLINE) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:37: NEWLINE
                    {
                    NEWLINE5=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules965); if (state.failed) return retval; 
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
            // 209:47: -> ^( RULES pair ( pair )* )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:50: ^( RULES pair ( pair )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(RULES, "RULES")
                , root_1);

                adaptor.addChild(root_1, stream_pair.nextTree());

                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:209:63: ( pair )*
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:212:1: pair : ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule );
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

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:213:2: ( ruleName rule2 -> ^( RULE ruleName rule2 ) | rule )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:213:4: ruleName rule2
                    {
                    pushFollow(FOLLOW_ruleName_in_pair991);
                    ruleName6=ruleName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleName.add(ruleName6.getTree());

                    pushFollow(FOLLOW_rule2_in_pair993);
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
                    // 213:19: -> ^( RULE ruleName rule2 )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:213:22: ^( RULE ruleName rule2 )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:214:4: rule
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_rule_in_pair1008);
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:217:1: ruleName : ( RULENAME COLON ( NEWLINE )? -> RULENAME | VAR COLON ( NEWLINE )? -> VAR );
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

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:218:2: ( RULENAME COLON ( NEWLINE )? -> RULENAME | VAR COLON ( NEWLINE )? -> VAR )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:218:4: RULENAME COLON ( NEWLINE )?
                    {
                    RULENAME9=(Token)match(input,RULENAME,FOLLOW_RULENAME_in_ruleName1020); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULENAME.add(RULENAME9);


                    COLON10=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName1022); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON10);


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:218:19: ( NEWLINE )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==NEWLINE) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:218:20: NEWLINE
                            {
                            NEWLINE11=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName1025); if (state.failed) return retval; 
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
                    // 218:30: -> RULENAME
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:219:4: VAR COLON ( NEWLINE )?
                    {
                    VAR12=(Token)match(input,VAR,FOLLOW_VAR_in_ruleName1036); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR12);


                    COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName1038); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON13);


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:219:14: ( NEWLINE )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==NEWLINE) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:219:15: NEWLINE
                            {
                            NEWLINE14=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName1041); if (state.failed) return retval; 
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
                    // 219:25: -> VAR
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:222:1: rule : rule2 -> ^( RULE rule2 ) ;
    public final BACONParser.rule_return rule() throws RecognitionException {
        BACONParser.rule_return retval = new BACONParser.rule_return();
        retval.start = input.LT(1);

        int rule_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.rule2_return rule215 =null;


        RewriteRuleSubtreeStream stream_rule2=new RewriteRuleSubtreeStream(adaptor,"rule rule2");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:223:2: ( rule2 -> ^( RULE rule2 ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:223:4: rule2
            {
            pushFollow(FOLLOW_rule2_in_rule1058);
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
            // 223:10: -> ^( RULE rule2 )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:223:13: ^( RULE rule2 )
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:226:1: rule2 : ( assign | IF bExpr ( NEWLINE )? THEN ( NEWLINE )? rule ( ( NEWLINE )? ) -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( NEWLINE )? ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 );
    public final BACONParser.rule2_return rule2() throws RecognitionException {
        BACONParser.rule2_return retval = new BACONParser.rule2_return();
        retval.start = input.LT(1);

        int rule2_StartIndex = input.index();

        Object root_0 = null;

        Token IF17=null;
        Token NEWLINE19=null;
        Token THEN20=null;
        Token NEWLINE21=null;
        Token NEWLINE23=null;
        Token UPTAKE24=null;
        Token LBRACKET25=null;
        Token VAR26=null;
        Token COMMA27=null;
        Token RBRACKET29=null;
        Token RELEASE30=null;
        Token LBRACKET31=null;
        Token VAR32=null;
        Token COMMA33=null;
        Token RBRACKET35=null;
        Token INGEST36=null;
        Token LBRACKET37=null;
        Token VAR38=null;
        Token COMMA39=null;
        Token COMMA41=null;
        Token RBRACKET43=null;
        Token CHANGE44=null;
        Token LBRACKET45=null;
        Token VAR46=null;
        Token RBRACKET47=null;
        Token PCHANGE48=null;
        Token LBRACKET49=null;
        Token VAR50=null;
        Token COMMA51=null;
        Token RBRACKET53=null;
        Token DIVIDE54=null;
        Token LBRACKET55=null;
        Token RBRACKET57=null;
        Token CREATE58=null;
        Token LBRACKET59=null;
        Token VAR60=null;
        Token COMMA61=null;
        Token RBRACKET63=null;
        Token NEWLINE64=null;
        Token WITH65=null;
        Token LSQUARE66=null;
        Token RSQUARE68=null;
        Token LBRACKET69=null;
        Token RBRACKET71=null;
        BACONParser.assign_return assign16 =null;

        BACONParser.bExpr_return bExpr18 =null;

        BACONParser.rule_return rule22 =null;

        BACONParser.expr_return expr28 =null;

        BACONParser.expr_return expr34 =null;

        BACONParser.expr_return expr40 =null;

        BACONParser.expr_return expr42 =null;

        BACONParser.expr_return expr52 =null;

        BACONParser.expr_return expr56 =null;

        BACONParser.expr_return expr62 =null;

        BACONParser.assignList_return assignList67 =null;

        BACONParser.rule2_return rule270 =null;


        Object IF17_tree=null;
        Object NEWLINE19_tree=null;
        Object THEN20_tree=null;
        Object NEWLINE21_tree=null;
        Object NEWLINE23_tree=null;
        Object UPTAKE24_tree=null;
        Object LBRACKET25_tree=null;
        Object VAR26_tree=null;
        Object COMMA27_tree=null;
        Object RBRACKET29_tree=null;
        Object RELEASE30_tree=null;
        Object LBRACKET31_tree=null;
        Object VAR32_tree=null;
        Object COMMA33_tree=null;
        Object RBRACKET35_tree=null;
        Object INGEST36_tree=null;
        Object LBRACKET37_tree=null;
        Object VAR38_tree=null;
        Object COMMA39_tree=null;
        Object COMMA41_tree=null;
        Object RBRACKET43_tree=null;
        Object CHANGE44_tree=null;
        Object LBRACKET45_tree=null;
        Object VAR46_tree=null;
        Object RBRACKET47_tree=null;
        Object PCHANGE48_tree=null;
        Object LBRACKET49_tree=null;
        Object VAR50_tree=null;
        Object COMMA51_tree=null;
        Object RBRACKET53_tree=null;
        Object DIVIDE54_tree=null;
        Object LBRACKET55_tree=null;
        Object RBRACKET57_tree=null;
        Object CREATE58_tree=null;
        Object LBRACKET59_tree=null;
        Object VAR60_tree=null;
        Object COMMA61_tree=null;
        Object RBRACKET63_tree=null;
        Object NEWLINE64_tree=null;
        Object WITH65_tree=null;
        Object LSQUARE66_tree=null;
        Object RSQUARE68_tree=null;
        Object LBRACKET69_tree=null;
        Object RBRACKET71_tree=null;
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
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:227:2: ( assign | IF bExpr ( NEWLINE )? THEN ( NEWLINE )? rule ( ( NEWLINE )? ) -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( NEWLINE )? ( WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 )
            int alt13=10;
            switch ( input.LA(1) ) {
            case VAR:
                {
                alt13=1;
                }
                break;
            case IF:
                {
                alt13=2;
                }
                break;
            case UPTAKE:
                {
                alt13=3;
                }
                break;
            case RELEASE:
                {
                alt13=4;
                }
                break;
            case INGEST:
                {
                alt13=5;
                }
                break;
            case CHANGE:
                {
                alt13=6;
                }
                break;
            case PCHANGE:
                {
                alt13=7;
                }
                break;
            case DIVIDE:
                {
                alt13=8;
                }
                break;
            case CREATE:
                {
                alt13=9;
                }
                break;
            case LBRACKET:
                {
                alt13=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:227:4: assign
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assign_in_rule21077);
                    assign16=assign();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign16.getTree());

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:4: IF bExpr ( NEWLINE )? THEN ( NEWLINE )? rule ( ( NEWLINE )? )
                    {
                    IF17=(Token)match(input,IF,FOLLOW_IF_in_rule21082); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF17);


                    pushFollow(FOLLOW_bExpr_in_rule21084);
                    bExpr18=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr18.getTree());

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:13: ( NEWLINE )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==NEWLINE) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:14: NEWLINE
                            {
                            NEWLINE19=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rule21087); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE19);


                            }
                            break;

                    }


                    THEN20=(Token)match(input,THEN,FOLLOW_THEN_in_rule21091); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN20);


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:29: ( NEWLINE )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==NEWLINE) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:30: NEWLINE
                            {
                            NEWLINE21=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rule21094); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE21);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_rule_in_rule21098);
                    rule22=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule22.getTree());

                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:45: ( ( NEWLINE )? )
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:46: ( NEWLINE )?
                    {
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:46: ( NEWLINE )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==NEWLINE) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:46: NEWLINE
                            {
                            NEWLINE23=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rule21101); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE23);


                            }
                            break;

                    }


                    }


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
                    // 228:56: -> ^( IF bExpr rule )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:59: ^( IF bExpr rule )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:229:4: UPTAKE LBRACKET VAR COMMA expr RBRACKET
                    {
                    UPTAKE24=(Token)match(input,UPTAKE,FOLLOW_UPTAKE_in_rule21118); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UPTAKE.add(UPTAKE24);


                    LBRACKET25=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21120); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET25);


                    VAR26=(Token)match(input,VAR,FOLLOW_VAR_in_rule21122); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR26);


                    COMMA27=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21124); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA27);


                    pushFollow(FOLLOW_expr_in_rule21126);
                    expr28=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr28.getTree());

                    RBRACKET29=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21128); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET29);


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
                    // 229:44: -> ^( UPTAKE VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:229:47: ^( UPTAKE VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:230:4: RELEASE LBRACKET VAR COMMA expr RBRACKET
                    {
                    RELEASE30=(Token)match(input,RELEASE,FOLLOW_RELEASE_in_rule21143); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RELEASE.add(RELEASE30);


                    LBRACKET31=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21145); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET31);


                    VAR32=(Token)match(input,VAR,FOLLOW_VAR_in_rule21147); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR32);


                    COMMA33=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21149); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA33);


                    pushFollow(FOLLOW_expr_in_rule21151);
                    expr34=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr34.getTree());

                    RBRACKET35=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21153); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET35);


                    // AST REWRITE
                    // elements: RELEASE, expr, VAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 230:45: -> ^( RELEASE VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:230:48: ^( RELEASE VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:231:4: INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET
                    {
                    INGEST36=(Token)match(input,INGEST,FOLLOW_INGEST_in_rule21168); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INGEST.add(INGEST36);


                    LBRACKET37=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21170); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET37);


                    VAR38=(Token)match(input,VAR,FOLLOW_VAR_in_rule21172); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR38);


                    COMMA39=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21174); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA39);


                    pushFollow(FOLLOW_expr_in_rule21176);
                    expr40=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr40.getTree());

                    COMMA41=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21178); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA41);


                    pushFollow(FOLLOW_expr_in_rule21180);
                    expr42=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr42.getTree());

                    RBRACKET43=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21182); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET43);


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
                    // 231:55: -> ^( INGEST VAR expr expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:231:58: ^( INGEST VAR expr expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:232:4: CHANGE LBRACKET VAR RBRACKET
                    {
                    CHANGE44=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21199); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE44);


                    LBRACKET45=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21201); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET45);


                    VAR46=(Token)match(input,VAR,FOLLOW_VAR_in_rule21203); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR46);


                    RBRACKET47=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21205); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET47);


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
                    // 232:33: -> ^( CHANGE VAR )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:232:36: ^( CHANGE VAR )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:233:4: PCHANGE LBRACKET VAR COMMA expr RBRACKET
                    {
                    PCHANGE48=(Token)match(input,PCHANGE,FOLLOW_PCHANGE_in_rule21218); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_PCHANGE.add(PCHANGE48);


                    LBRACKET49=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21220); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET49);


                    VAR50=(Token)match(input,VAR,FOLLOW_VAR_in_rule21222); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR50);


                    COMMA51=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21224); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA51);


                    pushFollow(FOLLOW_expr_in_rule21226);
                    expr52=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr52.getTree());

                    RBRACKET53=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21228); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET53);


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
                    // 233:45: -> ^( PCHANGE VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:233:48: ^( PCHANGE VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:234:4: DIVIDE LBRACKET expr RBRACKET
                    {
                    DIVIDE54=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_rule21243); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIVIDE.add(DIVIDE54);


                    LBRACKET55=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21245); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET55);


                    pushFollow(FOLLOW_expr_in_rule21247);
                    expr56=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr56.getTree());

                    RBRACKET57=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21249); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET57);


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
                    // 234:34: -> ^( DIVIDE expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:234:37: ^( DIVIDE expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:235:4: CREATE LBRACKET VAR COMMA expr RBRACKET ( NEWLINE )? ( WITH LSQUARE assignList RSQUARE )?
                    {
                    CREATE58=(Token)match(input,CREATE,FOLLOW_CREATE_in_rule21262); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CREATE.add(CREATE58);


                    LBRACKET59=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21264); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET59);


                    VAR60=(Token)match(input,VAR,FOLLOW_VAR_in_rule21266); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR60);


                    COMMA61=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21268); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA61);


                    pushFollow(FOLLOW_expr_in_rule21270);
                    expr62=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr62.getTree());

                    RBRACKET63=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21272); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET63);


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:235:44: ( NEWLINE )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==NEWLINE) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:235:45: NEWLINE
                            {
                            NEWLINE64=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rule21275); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE64);


                            }
                            break;

                    }


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:3: ( WITH LSQUARE assignList RSQUARE )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==WITH) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:4: WITH LSQUARE assignList RSQUARE
                            {
                            WITH65=(Token)match(input,WITH,FOLLOW_WITH_in_rule21282); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_WITH.add(WITH65);


                            LSQUARE66=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_rule21284); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE66);


                            pushFollow(FOLLOW_assignList_in_rule21286);
                            assignList67=assignList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignList.add(assignList67.getTree());

                            RSQUARE68=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_rule21288); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE68);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: CREATE, assignList, expr, VAR
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 236:38: -> ^( CREATE VAR expr ( assignList )? )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:41: ^( CREATE VAR expr ( assignList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CREATE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:59: ( assignList )?
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:237:4: LBRACKET rule2 RBRACKET
                    {
                    LBRACKET69=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21310); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET69);


                    pushFollow(FOLLOW_rule2_in_rule21312);
                    rule270=rule2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule2.add(rule270.getTree());

                    RBRACKET71=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21314); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET71);


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
                    // 237:28: -> rule2
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:240:1: assign : VAR EQUALS expr -> ^( ASSIGN VAR expr ) ;
    public final BACONParser.assign_return assign() throws RecognitionException {
        BACONParser.assign_return retval = new BACONParser.assign_return();
        retval.start = input.LT(1);

        int assign_StartIndex = input.index();

        Object root_0 = null;

        Token VAR72=null;
        Token EQUALS73=null;
        BACONParser.expr_return expr74 =null;


        Object VAR72_tree=null;
        Object EQUALS73_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:2: ( VAR EQUALS expr -> ^( ASSIGN VAR expr ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:4: VAR EQUALS expr
            {
            VAR72=(Token)match(input,VAR,FOLLOW_VAR_in_assign1329); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR72);


            EQUALS73=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assign1331); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS73);


            pushFollow(FOLLOW_expr_in_assign1333);
            expr74=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr74.getTree());

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
            // 241:20: -> ^( ASSIGN VAR expr )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:23: ^( ASSIGN VAR expr )
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:244:1: assignList : assign ( NEWLINE )? ( COMMA assign ( NEWLINE )? )* -> ^( ASSIGNLIST assign ( assign )* ) ;
    public final BACONParser.assignList_return assignList() throws RecognitionException {
        BACONParser.assignList_return retval = new BACONParser.assignList_return();
        retval.start = input.LT(1);

        int assignList_StartIndex = input.index();

        Object root_0 = null;

        Token NEWLINE76=null;
        Token COMMA77=null;
        Token NEWLINE79=null;
        BACONParser.assign_return assign75 =null;

        BACONParser.assign_return assign78 =null;


        Object NEWLINE76_tree=null;
        Object COMMA77_tree=null;
        Object NEWLINE79_tree=null;
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_assign=new RewriteRuleSubtreeStream(adaptor,"rule assign");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:2: ( assign ( NEWLINE )? ( COMMA assign ( NEWLINE )? )* -> ^( ASSIGNLIST assign ( assign )* ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:4: assign ( NEWLINE )? ( COMMA assign ( NEWLINE )? )*
            {
            pushFollow(FOLLOW_assign_in_assignList1354);
            assign75=assign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assign.add(assign75.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:11: ( NEWLINE )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==NEWLINE) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:12: NEWLINE
                    {
                    NEWLINE76=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_assignList1357); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE76);


                    }
                    break;

            }


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:22: ( COMMA assign ( NEWLINE )? )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:23: COMMA assign ( NEWLINE )?
            	    {
            	    COMMA77=(Token)match(input,COMMA,FOLLOW_COMMA_in_assignList1362); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA77);


            	    pushFollow(FOLLOW_assign_in_assignList1364);
            	    assign78=assign();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_assign.add(assign78.getTree());

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:36: ( NEWLINE )?
            	    int alt15=2;
            	    int LA15_0 = input.LA(1);

            	    if ( (LA15_0==NEWLINE) ) {
            	        alt15=1;
            	    }
            	    switch (alt15) {
            	        case 1 :
            	            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:37: NEWLINE
            	            {
            	            NEWLINE79=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_assignList1367); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE79);


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
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
            // 245:49: -> ^( ASSIGNLIST assign ( assign )* )
            {
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:52: ^( ASSIGNLIST assign ( assign )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(ASSIGNLIST, "ASSIGNLIST")
                , root_1);

                adaptor.addChild(root_1, stream_assign.nextTree());

                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:72: ( assign )*
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:248:1: expr : expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* ;
    public final BACONParser.expr_return expr() throws RecognitionException {
        BACONParser.expr_return retval = new BACONParser.expr_return();
        retval.start = input.LT(1);

        int expr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr2_return expr280 =null;

        BACONParser.lowPrecMathOp_return lowPrecMathOp81 =null;

        BACONParser.expr2_return expr282 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:2: ( expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:4: expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr2_in_expr1397);
            expr280=expr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr280.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:10: ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==MINUS||LA17_0==PLUS) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:38: lowPrecMathOp ^ expr2
            	    {
            	    pushFollow(FOLLOW_lowPrecMathOp_in_expr1411);
            	    lowPrecMathOp81=lowPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecMathOp81.getTree(), root_0);

            	    pushFollow(FOLLOW_expr2_in_expr1414);
            	    expr282=expr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr282.getTree());

            	    }
            	    break;

            	default :
            	    break loop17;
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:252:1: expr2 : expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* ;
    public final BACONParser.expr2_return expr2() throws RecognitionException {
        BACONParser.expr2_return retval = new BACONParser.expr2_return();
        retval.start = input.LT(1);

        int expr2_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr3_return expr383 =null;

        BACONParser.medPrecMathOp_return medPrecMathOp84 =null;

        BACONParser.expr3_return expr385 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:2: ( expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:4: expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr3_in_expr21428);
            expr383=expr3();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr383.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:10: ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==DIV||LA18_0==MUL) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:38: medPrecMathOp ^ expr3
            	    {
            	    pushFollow(FOLLOW_medPrecMathOp_in_expr21442);
            	    medPrecMathOp84=medPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(medPrecMathOp84.getTree(), root_0);

            	    pushFollow(FOLLOW_expr3_in_expr21446);
            	    expr385=expr3();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr385.getTree());

            	    }
            	    break;

            	default :
            	    break loop18;
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:256:1: expr3 : expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )* ;
    public final BACONParser.expr3_return expr3() throws RecognitionException {
        BACONParser.expr3_return retval = new BACONParser.expr3_return();
        retval.start = input.LT(1);

        int expr3_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.expr4_return expr486 =null;

        BACONParser.highPrecMathOp_return highPrecMathOp87 =null;

        BACONParser.expr4_return expr488 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:2: ( expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:4: expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr4_in_expr31460);
            expr486=expr4();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr486.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:10: ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==POW) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:38: highPrecMathOp ^ expr4
            	    {
            	    pushFollow(FOLLOW_highPrecMathOp_in_expr31474);
            	    highPrecMathOp87=highPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(highPrecMathOp87.getTree(), root_0);

            	    pushFollow(FOLLOW_expr4_in_expr31477);
            	    expr488=expr4();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr488.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:261:1: expr4 : ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | MINUS expr -> ^( NEG expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) );
    public final BACONParser.expr4_return expr4() throws RecognitionException {
        BACONParser.expr4_return retval = new BACONParser.expr4_return();
        retval.start = input.LT(1);

        int expr4_StartIndex = input.index();

        Object root_0 = null;

        Token LBRACKET89=null;
        Token RBRACKET91=null;
        Token LBRACKET93=null;
        Token RBRACKET95=null;
        Token MINUS96=null;
        Token FLOAT98=null;
        Token VAR99=null;
        Token IF100=null;
        Token THEN102=null;
        Token ELSE104=null;
        Token LBRACKET107=null;
        Token COMMA109=null;
        Token RBRACKET111=null;
        Token VARHIST112=null;
        Token LBRACKET113=null;
        Token VAR114=null;
        Token COMMA115=null;
        Token RBRACKET117=null;
        Token LBRACKET119=null;
        Token RBRACKET121=null;
        BACONParser.expr_return expr90 =null;

        BACONParser.unaryPrimitives_return unaryPrimitives92 =null;

        BACONParser.expr_return expr94 =null;

        BACONParser.expr_return expr97 =null;

        BACONParser.bExpr_return bExpr101 =null;

        BACONParser.expr_return expr103 =null;

        BACONParser.expr_return expr105 =null;

        BACONParser.binPrim_return binPrim106 =null;

        BACONParser.expr_return expr108 =null;

        BACONParser.expr_return expr110 =null;

        BACONParser.expr_return expr116 =null;

        BACONParser.vOp_return vOp118 =null;

        BACONParser.expr_return expr120 =null;


        Object LBRACKET89_tree=null;
        Object RBRACKET91_tree=null;
        Object LBRACKET93_tree=null;
        Object RBRACKET95_tree=null;
        Object MINUS96_tree=null;
        Object FLOAT98_tree=null;
        Object VAR99_tree=null;
        Object IF100_tree=null;
        Object THEN102_tree=null;
        Object ELSE104_tree=null;
        Object LBRACKET107_tree=null;
        Object COMMA109_tree=null;
        Object RBRACKET111_tree=null;
        Object VARHIST112_tree=null;
        Object LBRACKET113_tree=null;
        Object VAR114_tree=null;
        Object COMMA115_tree=null;
        Object RBRACKET117_tree=null;
        Object LBRACKET119_tree=null;
        Object RBRACKET121_tree=null;
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

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:262:3: ( LBRACKET expr RBRACKET -> expr | unaryPrimitives LBRACKET expr RBRACKET -> ^( unaryPrimitives expr ) | MINUS expr -> ^( NEG expr ) | FLOAT | VAR | IF bExpr THEN expr ELSE expr -> ^( IF bExpr expr expr ) | binPrim LBRACKET expr COMMA expr RBRACKET -> ^( binPrim expr expr ) | VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^( VARHIST VAR expr ) | vOp LBRACKET expr RBRACKET -> ^( vOp expr ) )
            int alt20=9;
            switch ( input.LA(1) ) {
            case LBRACKET:
                {
                alt20=1;
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
                alt20=2;
                }
                break;
            case MINUS:
                {
                alt20=3;
                }
                break;
            case FLOAT:
                {
                alt20=4;
                }
                break;
            case VAR:
                {
                alt20=5;
                }
                break;
            case IF:
                {
                alt20=6;
                }
                break;
            case MAX:
            case MIN:
                {
                alt20=7;
                }
                break;
            case VARHIST:
                {
                alt20=8;
                }
                break;
            case VAVERAGE:
            case VPRODUCT:
            case VSUM:
                {
                alt20=9;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }

            switch (alt20) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:262:5: LBRACKET expr RBRACKET
                    {
                    LBRACKET89=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41493); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET89);


                    pushFollow(FOLLOW_expr_in_expr41495);
                    expr90=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr90.getTree());

                    RBRACKET91=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41497); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET91);


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
                    // 262:28: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:263:4: unaryPrimitives LBRACKET expr RBRACKET
                    {
                    pushFollow(FOLLOW_unaryPrimitives_in_expr41506);
                    unaryPrimitives92=unaryPrimitives();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryPrimitives.add(unaryPrimitives92.getTree());

                    LBRACKET93=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41508); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET93);


                    pushFollow(FOLLOW_expr_in_expr41510);
                    expr94=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr94.getTree());

                    RBRACKET95=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41512); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET95);


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
                    // 263:43: -> ^( unaryPrimitives expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:263:46: ^( unaryPrimitives expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:264:4: MINUS expr
                    {
                    MINUS96=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr41525); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_MINUS.add(MINUS96);


                    pushFollow(FOLLOW_expr_in_expr41527);
                    expr97=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr97.getTree());

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
                    // 264:15: -> ^( NEG expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:264:18: ^( NEG expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:265:4: FLOAT
                    {
                    root_0 = (Object)adaptor.nil();


                    FLOAT98=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_expr41540); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT98_tree = 
                    (Object)adaptor.create(FLOAT98)
                    ;
                    adaptor.addChild(root_0, FLOAT98_tree);
                    }

                    }
                    break;
                case 5 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:266:4: VAR
                    {
                    root_0 = (Object)adaptor.nil();


                    VAR99=(Token)match(input,VAR,FOLLOW_VAR_in_expr41545); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR99_tree = 
                    (Object)adaptor.create(VAR99)
                    ;
                    adaptor.addChild(root_0, VAR99_tree);
                    }

                    }
                    break;
                case 6 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:4: IF bExpr THEN expr ELSE expr
                    {
                    IF100=(Token)match(input,IF,FOLLOW_IF_in_expr41550); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF100);


                    pushFollow(FOLLOW_bExpr_in_expr41552);
                    bExpr101=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr101.getTree());

                    THEN102=(Token)match(input,THEN,FOLLOW_THEN_in_expr41554); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN102);


                    pushFollow(FOLLOW_expr_in_expr41556);
                    expr103=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr103.getTree());

                    ELSE104=(Token)match(input,ELSE,FOLLOW_ELSE_in_expr41558); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELSE.add(ELSE104);


                    pushFollow(FOLLOW_expr_in_expr41560);
                    expr105=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr105.getTree());

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
                    // 267:33: -> ^( IF bExpr expr expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:36: ^( IF bExpr expr expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:268:4: binPrim LBRACKET expr COMMA expr RBRACKET
                    {
                    pushFollow(FOLLOW_binPrim_in_expr41577);
                    binPrim106=binPrim();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binPrim.add(binPrim106.getTree());

                    LBRACKET107=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41579); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET107);


                    pushFollow(FOLLOW_expr_in_expr41581);
                    expr108=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr108.getTree());

                    COMMA109=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41583); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA109);


                    pushFollow(FOLLOW_expr_in_expr41585);
                    expr110=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr110.getTree());

                    RBRACKET111=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41587); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET111);


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
                    // 268:46: -> ^( binPrim expr expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:268:49: ^( binPrim expr expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:269:4: VARHIST LBRACKET VAR COMMA expr RBRACKET
                    {
                    VARHIST112=(Token)match(input,VARHIST,FOLLOW_VARHIST_in_expr41602); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VARHIST.add(VARHIST112);


                    LBRACKET113=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41604); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET113);


                    VAR114=(Token)match(input,VAR,FOLLOW_VAR_in_expr41606); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR114);


                    COMMA115=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41608); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA115);


                    pushFollow(FOLLOW_expr_in_expr41610);
                    expr116=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr116.getTree());

                    RBRACKET117=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41612); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET117);


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
                    // 269:45: -> ^( VARHIST VAR expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:269:48: ^( VARHIST VAR expr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:270:4: vOp LBRACKET expr RBRACKET
                    {
                    pushFollow(FOLLOW_vOp_in_expr41627);
                    vOp118=vOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vOp.add(vOp118.getTree());

                    LBRACKET119=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41629); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET119);


                    pushFollow(FOLLOW_expr_in_expr41631);
                    expr120=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr120.getTree());

                    RBRACKET121=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41633); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET121);


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
                    // 270:31: -> ^( vOp expr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:270:34: ^( vOp expr )
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:274:1: bExpr : bExpr2 ( lowPrecBoolOp ^ bExpr2 )* ;
    public final BACONParser.bExpr_return bExpr() throws RecognitionException {
        BACONParser.bExpr_return retval = new BACONParser.bExpr_return();
        retval.start = input.LT(1);

        int bExpr_StartIndex = input.index();

        Object root_0 = null;

        BACONParser.bExpr2_return bExpr2122 =null;

        BACONParser.lowPrecBoolOp_return lowPrecBoolOp123 =null;

        BACONParser.bExpr2_return bExpr2124 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:2: ( bExpr2 ( lowPrecBoolOp ^ bExpr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:4: bExpr2 ( lowPrecBoolOp ^ bExpr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_bExpr2_in_bExpr1653);
            bExpr2122=bExpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2122.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:11: ( lowPrecBoolOp ^ bExpr2 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==AND||LA21_0==OR) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:12: lowPrecBoolOp ^ bExpr2
            	    {
            	    pushFollow(FOLLOW_lowPrecBoolOp_in_bExpr1656);
            	    lowPrecBoolOp123=lowPrecBoolOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecBoolOp123.getTree(), root_0);

            	    pushFollow(FOLLOW_bExpr2_in_bExpr1659);
            	    bExpr2124=bExpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2124.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:278:1: bExpr2 : ( ( expr comparators )=> expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) );
    public final BACONParser.bExpr2_return bExpr2() throws RecognitionException {
        BACONParser.bExpr2_return retval = new BACONParser.bExpr2_return();
        retval.start = input.LT(1);

        int bExpr2_StartIndex = input.index();

        Object root_0 = null;

        Token NOT128=null;
        Token LBRACKET129=null;
        Token RBRACKET131=null;
        Token LBRACKET132=null;
        Token RBRACKET134=null;
        Token LBRACKET136=null;
        Token RBRACKET138=null;
        BACONParser.expr_return expr125 =null;

        BACONParser.comparators_return comparators126 =null;

        BACONParser.expr_return expr127 =null;

        BACONParser.bExpr_return bExpr130 =null;

        BACONParser.bExpr_return bExpr133 =null;

        BACONParser.vBOp_return vBOp135 =null;

        BACONParser.bExpr_return bExpr137 =null;


        Object NOT128_tree=null;
        Object LBRACKET129_tree=null;
        Object RBRACKET131_tree=null;
        Object LBRACKET132_tree=null;
        Object RBRACKET134_tree=null;
        Object LBRACKET136_tree=null;
        Object RBRACKET138_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_vBOp=new RewriteRuleSubtreeStream(adaptor,"rule vBOp");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:279:2: ( ( expr comparators )=> expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) )
            int alt22=4;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==LBRACKET) ) {
                int LA22_1 = input.LA(2);

                if ( (synpred1_BACON()) ) {
                    alt22=1;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;

                }
            }
            else if ( ((LA22_0 >= ABS && LA22_0 <= ACOS)||LA22_0==ASIN||LA22_0==ATAN||LA22_0==COS||(LA22_0 >= DENSITYAT && LA22_0 <= DEPTHFORVI)||LA22_0==EXP||LA22_0==FULLIRRADAT||LA22_0==INTEGRATE||(LA22_0 >= LN && LA22_0 <= LOGTEN)||LA22_0==RND||(LA22_0 >= SALINITYAT && LA22_0 <= SIN)||(LA22_0 >= SQRT && LA22_0 <= TEMPAT)||LA22_0==UVIRRADAT||LA22_0==VISIRRADAT) && (synpred1_BACON())) {
                alt22=1;
            }
            else if ( (LA22_0==MINUS) && (synpred1_BACON())) {
                alt22=1;
            }
            else if ( (LA22_0==FLOAT) && (synpred1_BACON())) {
                alt22=1;
            }
            else if ( (LA22_0==VAR) && (synpred1_BACON())) {
                alt22=1;
            }
            else if ( (LA22_0==IF) && (synpred1_BACON())) {
                alt22=1;
            }
            else if ( ((LA22_0 >= MAX && LA22_0 <= MIN)) && (synpred1_BACON())) {
                alt22=1;
            }
            else if ( (LA22_0==VARHIST) && (synpred1_BACON())) {
                alt22=1;
            }
            else if ( (LA22_0==VAVERAGE||(LA22_0 >= VPRODUCT && LA22_0 <= VSUM)) && (synpred1_BACON())) {
                alt22=1;
            }
            else if ( (LA22_0==NOT) ) {
                alt22=2;
            }
            else if ( (LA22_0==ALL||LA22_0==NONE||LA22_0==SOME) ) {
                alt22=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:279:4: ( expr comparators )=> expr comparators ^ expr
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_bExpr21683);
                    expr125=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr125.getTree());

                    pushFollow(FOLLOW_comparators_in_bExpr21685);
                    comparators126=comparators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(comparators126.getTree(), root_0);

                    pushFollow(FOLLOW_expr_in_bExpr21688);
                    expr127=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr127.getTree());

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:280:4: NOT LBRACKET bExpr RBRACKET
                    {
                    NOT128=(Token)match(input,NOT,FOLLOW_NOT_in_bExpr21693); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT128);


                    LBRACKET129=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21695); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET129);


                    pushFollow(FOLLOW_bExpr_in_bExpr21697);
                    bExpr130=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr130.getTree());

                    RBRACKET131=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21699); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET131);


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
                    // 280:32: -> ^( NOT bExpr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:280:35: ^( NOT bExpr )
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:281:4: LBRACKET bExpr RBRACKET
                    {
                    LBRACKET132=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21712); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET132);


                    pushFollow(FOLLOW_bExpr_in_bExpr21714);
                    bExpr133=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr133.getTree());

                    RBRACKET134=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21716); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET134);


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
                    // 281:28: -> bExpr
                    {
                        adaptor.addChild(root_0, stream_bExpr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:282:4: vBOp LBRACKET bExpr RBRACKET
                    {
                    pushFollow(FOLLOW_vBOp_in_bExpr21725);
                    vBOp135=vBOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vBOp.add(vBOp135.getTree());

                    LBRACKET136=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21727); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET136);


                    pushFollow(FOLLOW_bExpr_in_bExpr21729);
                    bExpr137=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr137.getTree());

                    RBRACKET138=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21731); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET138);


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
                    // 282:33: -> ^( vBOp bExpr )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:282:36: ^( vBOp bExpr )
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:288:1: unaryPrimitives : ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE | VISIRRADAT );
    public final BACONParser.unaryPrimitives_return unaryPrimitives() throws RecognitionException {
        BACONParser.unaryPrimitives_return retval = new BACONParser.unaryPrimitives_return();
        retval.start = input.LT(1);

        int unaryPrimitives_StartIndex = input.index();

        Object root_0 = null;

        Token set139=null;

        Object set139_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:289:2: ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE | VISIRRADAT )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set139=(Token)input.LT(1);

            if ( (input.LA(1) >= ABS && input.LA(1) <= ACOS)||input.LA(1)==ASIN||input.LA(1)==ATAN||input.LA(1)==COS||(input.LA(1) >= DENSITYAT && input.LA(1) <= DEPTHFORVI)||input.LA(1)==EXP||input.LA(1)==FULLIRRADAT||input.LA(1)==INTEGRATE||(input.LA(1) >= LN && input.LA(1) <= LOGTEN)||input.LA(1)==RND||(input.LA(1) >= SALINITYAT && input.LA(1) <= SIN)||(input.LA(1) >= SQRT && input.LA(1) <= TEMPAT)||input.LA(1)==UVIRRADAT||input.LA(1)==VISIRRADAT ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:312:1: lowPrecMathOp : ( PLUS | MINUS );
    public final BACONParser.lowPrecMathOp_return lowPrecMathOp() throws RecognitionException {
        BACONParser.lowPrecMathOp_return retval = new BACONParser.lowPrecMathOp_return();
        retval.start = input.LT(1);

        int lowPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set140=null;

        Object set140_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:313:2: ( PLUS | MINUS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set140=(Token)input.LT(1);

            if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:317:1: medPrecMathOp : ( MUL | DIV );
    public final BACONParser.medPrecMathOp_return medPrecMathOp() throws RecognitionException {
        BACONParser.medPrecMathOp_return retval = new BACONParser.medPrecMathOp_return();
        retval.start = input.LT(1);

        int medPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token set141=null;

        Object set141_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:318:2: ( MUL | DIV )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set141=(Token)input.LT(1);

            if ( input.LA(1)==DIV||input.LA(1)==MUL ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:322:1: highPrecMathOp : POW ;
    public final BACONParser.highPrecMathOp_return highPrecMathOp() throws RecognitionException {
        BACONParser.highPrecMathOp_return retval = new BACONParser.highPrecMathOp_return();
        retval.start = input.LT(1);

        int highPrecMathOp_StartIndex = input.index();

        Object root_0 = null;

        Token POW142=null;

        Object POW142_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:323:2: ( POW )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:323:4: POW
            {
            root_0 = (Object)adaptor.nil();


            POW142=(Token)match(input,POW,FOLLOW_POW_in_highPrecMathOp1901); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            POW142_tree = 
            (Object)adaptor.create(POW142)
            ;
            adaptor.addChild(root_0, POW142_tree);
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:326:1: binPrim : ( MIN | MAX );
    public final BACONParser.binPrim_return binPrim() throws RecognitionException {
        BACONParser.binPrim_return retval = new BACONParser.binPrim_return();
        retval.start = input.LT(1);

        int binPrim_StartIndex = input.index();

        Object root_0 = null;

        Token set143=null;

        Object set143_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:327:2: ( MIN | MAX )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set143=(Token)input.LT(1);

            if ( (input.LA(1) >= MAX && input.LA(1) <= MIN) ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:331:1: lowPrecBoolOp : ( AND | OR );
    public final BACONParser.lowPrecBoolOp_return lowPrecBoolOp() throws RecognitionException {
        BACONParser.lowPrecBoolOp_return retval = new BACONParser.lowPrecBoolOp_return();
        retval.start = input.LT(1);

        int lowPrecBoolOp_StartIndex = input.index();

        Object root_0 = null;

        Token set144=null;

        Object set144_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:332:2: ( AND | OR )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set144=(Token)input.LT(1);

            if ( input.LA(1)==AND||input.LA(1)==OR ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:336:1: comparators : ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS );
    public final BACONParser.comparators_return comparators() throws RecognitionException {
        BACONParser.comparators_return retval = new BACONParser.comparators_return();
        retval.start = input.LT(1);

        int comparators_StartIndex = input.index();

        Object root_0 = null;

        Token set145=null;

        Object set145_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:337:2: ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set145=(Token)input.LT(1);

            if ( input.LA(1)==EQUALS||(input.LA(1) >= GREATEREQUALS && input.LA(1) <= GREATERTHAN)||(input.LA(1) >= LESSEQUALS && input.LA(1) <= LESSTHAN)||input.LA(1)==NEQUALS ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:345:1: vOp : ( VSUM | VPRODUCT | VAVERAGE );
    public final BACONParser.vOp_return vOp() throws RecognitionException {
        BACONParser.vOp_return retval = new BACONParser.vOp_return();
        retval.start = input.LT(1);

        int vOp_StartIndex = input.index();

        Object root_0 = null;

        Token set146=null;

        Object set146_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:346:2: ( VSUM | VPRODUCT | VAVERAGE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set146=(Token)input.LT(1);

            if ( input.LA(1)==VAVERAGE||(input.LA(1) >= VPRODUCT && input.LA(1) <= VSUM) ) {
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:351:1: vBOp : ( ALL | SOME | NONE );
    public final BACONParser.vBOp_return vBOp() throws RecognitionException {
        BACONParser.vBOp_return retval = new BACONParser.vBOp_return();
        retval.start = input.LT(1);

        int vBOp_StartIndex = input.index();

        Object root_0 = null;

        Token set147=null;

        Object set147_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:352:2: ( ALL | SOME | NONE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set147=(Token)input.LT(1);

            if ( input.LA(1)==ALL||input.LA(1)==NONE||input.LA(1)==SOME ) {
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
            if ( state.backtracking>0 ) { memoize(input, 22, vBOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "vBOp"

    // $ANTLR start synpred1_BACON
    public final void synpred1_BACON_fragment() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:279:4: ( expr comparators )
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:279:5: expr comparators
        {
        pushFollow(FOLLOW_expr_in_synpred1_BACON1675);
        expr();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_comparators_in_synpred1_BACON1677);
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


 

    public static final BitSet FOLLOW_NEWLINE_in_rules951 = new BitSet(new long[]{0x8880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_pair_in_rules955 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules958 = new BitSet(new long[]{0x8880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_pair_in_rules960 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_pair991 = new BitSet(new long[]{0x0880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_rule2_in_pair993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_pair1008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULENAME_in_ruleName1020 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName1022 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_ruleName1036 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName1038 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName1041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule2_in_rule1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_rule21077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_rule21082 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_rule21084 = new BitSet(new long[]{0x0008000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_rule21087 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_THEN_in_rule21091 = new BitSet(new long[]{0x0888005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_NEWLINE_in_rule21094 = new BitSet(new long[]{0x0880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_rule_in_rule21098 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rule21101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPTAKE_in_rule21118 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21120 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21122 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21124 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21126 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELEASE_in_rule21143 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21145 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21147 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21149 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21151 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INGEST_in_rule21168 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21170 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21172 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21174 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21176 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21178 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21180 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule21199 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21201 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21203 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PCHANGE_in_rule21218 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21220 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21222 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21224 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21226 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDE_in_rule21243 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21245 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21247 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_rule21262 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21264 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21266 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21268 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21270 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21272 = new BitSet(new long[]{0x0008000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_NEWLINE_in_rule21275 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_WITH_in_rule21282 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LSQUARE_in_rule21284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_assignList_in_rule21286 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_RSQUARE_in_rule21288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21310 = new BitSet(new long[]{0x0880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_rule2_in_rule21312 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_assign1329 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUALS_in_assign1331 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_assign1333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_assignList1354 = new BitSet(new long[]{0x0008000000008002L});
    public static final BitSet FOLLOW_NEWLINE_in_assignList1357 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_assignList1362 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_assign_in_assignList1364 = new BitSet(new long[]{0x0008000000008002L});
    public static final BitSet FOLLOW_NEWLINE_in_assignList1367 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_expr2_in_expr1397 = new BitSet(new long[]{0x0100800000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_expr1411 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr2_in_expr1414 = new BitSet(new long[]{0x0100800000000002L});
    public static final BitSet FOLLOW_expr3_in_expr21428 = new BitSet(new long[]{0x0001000000800002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_expr21442 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr3_in_expr21446 = new BitSet(new long[]{0x0001000000800002L});
    public static final BitSet FOLLOW_expr4_in_expr31460 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_expr31474 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr4_in_expr31477 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41493 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41495 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryPrimitives_in_expr41506 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41508 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41510 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expr41525 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_expr41540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_expr41545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr41550 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_expr41552 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_THEN_in_expr41554 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41556 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ELSE_in_expr41558 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binPrim_in_expr41577 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41579 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41581 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41583 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41585 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARHIST_in_expr41602 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_expr41606 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41608 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41610 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vOp_in_expr41627 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41629 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41631 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1653 = new BitSet(new long[]{0x0040000000000082L});
    public static final BitSet FOLLOW_lowPrecBoolOp_in_bExpr1656 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1659 = new BitSet(new long[]{0x0040000000000082L});
    public static final BitSet FOLLOW_expr_in_bExpr21683 = new BitSet(new long[]{0x0004018304000000L});
    public static final BitSet FOLLOW_comparators_in_bExpr21685 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_bExpr21688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bExpr21693 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21695 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21697 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21712 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21714 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vBOp_in_bExpr21725 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21727 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21729 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POW_in_highPrecMathOp1901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred1_BACON1675 = new BitSet(new long[]{0x0004018304000000L});
    public static final BitSet FOLLOW_comparators_in_synpred1_BACON1677 = new BitSet(new long[]{0x0000000000000002L});

}