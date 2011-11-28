// $ANTLR 3.4 C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2011-11-27 20:27:47

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
                    NEWLINE1=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules940); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE1);


                    }
                    break;

            }


            pushFollow(FOLLOW_pair_in_rules944);
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
            	    NEWLINE3=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules947); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE3);


            	    pushFollow(FOLLOW_pair_in_rules949);
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
                    NEWLINE5=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rules954); if (state.failed) return retval; 
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
                    pushFollow(FOLLOW_ruleName_in_pair980);
                    ruleName6=ruleName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleName.add(ruleName6.getTree());

                    pushFollow(FOLLOW_rule2_in_pair982);
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


                    pushFollow(FOLLOW_rule_in_pair997);
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
                    RULENAME9=(Token)match(input,RULENAME,FOLLOW_RULENAME_in_ruleName1009); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULENAME.add(RULENAME9);


                    COLON10=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName1011); if (state.failed) return retval; 
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
                            NEWLINE11=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName1014); if (state.failed) return retval; 
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
                    VAR12=(Token)match(input,VAR,FOLLOW_VAR_in_ruleName1025); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR12);


                    COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_ruleName1027); if (state.failed) return retval; 
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
                            NEWLINE14=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ruleName1030); if (state.failed) return retval; 
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
            pushFollow(FOLLOW_rule2_in_rule1047);
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
    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:226:1: rule2 : ( assign | IF bExpr ( NEWLINE )? THEN ( NEWLINE )? rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( ( NEWLINE )? WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 );
    public final BACONParser.rule2_return rule2() throws RecognitionException {
        BACONParser.rule2_return retval = new BACONParser.rule2_return();
        retval.start = input.LT(1);

        int rule2_StartIndex = input.index();

        Object root_0 = null;

        Token IF17=null;
        Token NEWLINE19=null;
        Token THEN20=null;
        Token NEWLINE21=null;
        Token UPTAKE23=null;
        Token LBRACKET24=null;
        Token VAR25=null;
        Token COMMA26=null;
        Token RBRACKET28=null;
        Token RELEASE29=null;
        Token LBRACKET30=null;
        Token VAR31=null;
        Token COMMA32=null;
        Token RBRACKET34=null;
        Token INGEST35=null;
        Token LBRACKET36=null;
        Token VAR37=null;
        Token COMMA38=null;
        Token COMMA40=null;
        Token RBRACKET42=null;
        Token CHANGE43=null;
        Token LBRACKET44=null;
        Token VAR45=null;
        Token RBRACKET46=null;
        Token PCHANGE47=null;
        Token LBRACKET48=null;
        Token VAR49=null;
        Token COMMA50=null;
        Token RBRACKET52=null;
        Token DIVIDE53=null;
        Token LBRACKET54=null;
        Token RBRACKET56=null;
        Token CREATE57=null;
        Token LBRACKET58=null;
        Token VAR59=null;
        Token COMMA60=null;
        Token RBRACKET62=null;
        Token NEWLINE63=null;
        Token WITH64=null;
        Token LSQUARE65=null;
        Token RSQUARE67=null;
        Token LBRACKET68=null;
        Token RBRACKET70=null;
        BACONParser.assign_return assign16 =null;

        BACONParser.bExpr_return bExpr18 =null;

        BACONParser.rule_return rule22 =null;

        BACONParser.expr_return expr27 =null;

        BACONParser.expr_return expr33 =null;

        BACONParser.expr_return expr39 =null;

        BACONParser.expr_return expr41 =null;

        BACONParser.expr_return expr51 =null;

        BACONParser.expr_return expr55 =null;

        BACONParser.expr_return expr61 =null;

        BACONParser.assignList_return assignList66 =null;

        BACONParser.rule2_return rule269 =null;


        Object IF17_tree=null;
        Object NEWLINE19_tree=null;
        Object THEN20_tree=null;
        Object NEWLINE21_tree=null;
        Object UPTAKE23_tree=null;
        Object LBRACKET24_tree=null;
        Object VAR25_tree=null;
        Object COMMA26_tree=null;
        Object RBRACKET28_tree=null;
        Object RELEASE29_tree=null;
        Object LBRACKET30_tree=null;
        Object VAR31_tree=null;
        Object COMMA32_tree=null;
        Object RBRACKET34_tree=null;
        Object INGEST35_tree=null;
        Object LBRACKET36_tree=null;
        Object VAR37_tree=null;
        Object COMMA38_tree=null;
        Object COMMA40_tree=null;
        Object RBRACKET42_tree=null;
        Object CHANGE43_tree=null;
        Object LBRACKET44_tree=null;
        Object VAR45_tree=null;
        Object RBRACKET46_tree=null;
        Object PCHANGE47_tree=null;
        Object LBRACKET48_tree=null;
        Object VAR49_tree=null;
        Object COMMA50_tree=null;
        Object RBRACKET52_tree=null;
        Object DIVIDE53_tree=null;
        Object LBRACKET54_tree=null;
        Object RBRACKET56_tree=null;
        Object CREATE57_tree=null;
        Object LBRACKET58_tree=null;
        Object VAR59_tree=null;
        Object COMMA60_tree=null;
        Object RBRACKET62_tree=null;
        Object NEWLINE63_tree=null;
        Object WITH64_tree=null;
        Object LSQUARE65_tree=null;
        Object RSQUARE67_tree=null;
        Object LBRACKET68_tree=null;
        Object RBRACKET70_tree=null;
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

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:227:2: ( assign | IF bExpr ( NEWLINE )? THEN ( NEWLINE )? rule -> ^( IF bExpr rule ) | UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^( UPTAKE VAR expr ) | RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^( RELEASE VAR expr ) | INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^( INGEST VAR expr expr ) | CHANGE LBRACKET VAR RBRACKET -> ^( CHANGE VAR ) | PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^( PCHANGE VAR expr ) | DIVIDE LBRACKET expr RBRACKET -> ^( DIVIDE expr ) | CREATE LBRACKET VAR COMMA expr RBRACKET ( ( NEWLINE )? WITH LSQUARE assignList RSQUARE )? -> ^( CREATE VAR expr ( assignList )? ) | LBRACKET rule2 RBRACKET -> rule2 )
            int alt12=10;
            switch ( input.LA(1) ) {
            case VAR:
                {
                alt12=1;
                }
                break;
            case IF:
                {
                alt12=2;
                }
                break;
            case UPTAKE:
                {
                alt12=3;
                }
                break;
            case RELEASE:
                {
                alt12=4;
                }
                break;
            case INGEST:
                {
                alt12=5;
                }
                break;
            case CHANGE:
                {
                alt12=6;
                }
                break;
            case PCHANGE:
                {
                alt12=7;
                }
                break;
            case DIVIDE:
                {
                alt12=8;
                }
                break;
            case CREATE:
                {
                alt12=9;
                }
                break;
            case LBRACKET:
                {
                alt12=10;
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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:227:4: assign
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_assign_in_rule21066);
                    assign16=assign();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign16.getTree());

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:4: IF bExpr ( NEWLINE )? THEN ( NEWLINE )? rule
                    {
                    IF17=(Token)match(input,IF,FOLLOW_IF_in_rule21071); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF17);


                    pushFollow(FOLLOW_bExpr_in_rule21073);
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
                            NEWLINE19=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rule21076); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE19);


                            }
                            break;

                    }


                    THEN20=(Token)match(input,THEN,FOLLOW_THEN_in_rule21080); if (state.failed) return retval; 
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
                            NEWLINE21=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rule21083); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE21);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_rule_in_rule21087);
                    rule22=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule.add(rule22.getTree());

                    // AST REWRITE
                    // elements: rule, IF, bExpr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 228:45: -> ^( IF bExpr rule )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:228:48: ^( IF bExpr rule )
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
                    UPTAKE23=(Token)match(input,UPTAKE,FOLLOW_UPTAKE_in_rule21102); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_UPTAKE.add(UPTAKE23);


                    LBRACKET24=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21104); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET24);


                    VAR25=(Token)match(input,VAR,FOLLOW_VAR_in_rule21106); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR25);


                    COMMA26=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21108); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA26);


                    pushFollow(FOLLOW_expr_in_rule21110);
                    expr27=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr27.getTree());

                    RBRACKET28=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21112); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET28);


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
                    RELEASE29=(Token)match(input,RELEASE,FOLLOW_RELEASE_in_rule21127); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RELEASE.add(RELEASE29);


                    LBRACKET30=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21129); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET30);


                    VAR31=(Token)match(input,VAR,FOLLOW_VAR_in_rule21131); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR31);


                    COMMA32=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21133); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA32);


                    pushFollow(FOLLOW_expr_in_rule21135);
                    expr33=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr33.getTree());

                    RBRACKET34=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21137); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET34);


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
                    INGEST35=(Token)match(input,INGEST,FOLLOW_INGEST_in_rule21152); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_INGEST.add(INGEST35);


                    LBRACKET36=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21154); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET36);


                    VAR37=(Token)match(input,VAR,FOLLOW_VAR_in_rule21156); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR37);


                    COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21158); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA38);


                    pushFollow(FOLLOW_expr_in_rule21160);
                    expr39=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr39.getTree());

                    COMMA40=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21162); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA40);


                    pushFollow(FOLLOW_expr_in_rule21164);
                    expr41=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr41.getTree());

                    RBRACKET42=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21166); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET42);


                    // AST REWRITE
                    // elements: expr, VAR, INGEST, expr
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
                    CHANGE43=(Token)match(input,CHANGE,FOLLOW_CHANGE_in_rule21183); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHANGE.add(CHANGE43);


                    LBRACKET44=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21185); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET44);


                    VAR45=(Token)match(input,VAR,FOLLOW_VAR_in_rule21187); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR45);


                    RBRACKET46=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21189); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET46);


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
                    PCHANGE47=(Token)match(input,PCHANGE,FOLLOW_PCHANGE_in_rule21202); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_PCHANGE.add(PCHANGE47);


                    LBRACKET48=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21204); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET48);


                    VAR49=(Token)match(input,VAR,FOLLOW_VAR_in_rule21206); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR49);


                    COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21208); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA50);


                    pushFollow(FOLLOW_expr_in_rule21210);
                    expr51=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr51.getTree());

                    RBRACKET52=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21212); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET52);


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
                    DIVIDE53=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_rule21227); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIVIDE.add(DIVIDE53);


                    LBRACKET54=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21229); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET54);


                    pushFollow(FOLLOW_expr_in_rule21231);
                    expr55=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr55.getTree());

                    RBRACKET56=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21233); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET56);


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
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:235:4: CREATE LBRACKET VAR COMMA expr RBRACKET ( ( NEWLINE )? WITH LSQUARE assignList RSQUARE )?
                    {
                    CREATE57=(Token)match(input,CREATE,FOLLOW_CREATE_in_rule21246); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CREATE.add(CREATE57);


                    LBRACKET58=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21248); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET58);


                    VAR59=(Token)match(input,VAR,FOLLOW_VAR_in_rule21250); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR59);


                    COMMA60=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule21252); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA60);


                    pushFollow(FOLLOW_expr_in_rule21254);
                    expr61=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr61.getTree());

                    RBRACKET62=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21256); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET62);


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:3: ( ( NEWLINE )? WITH LSQUARE assignList RSQUARE )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==NEWLINE) ) {
                        int LA11_1 = input.LA(2);

                        if ( (LA11_1==WITH) ) {
                            alt11=1;
                        }
                    }
                    else if ( (LA11_0==WITH) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:4: ( NEWLINE )? WITH LSQUARE assignList RSQUARE
                            {
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:4: ( NEWLINE )?
                            int alt10=2;
                            int LA10_0 = input.LA(1);

                            if ( (LA10_0==NEWLINE) ) {
                                alt10=1;
                            }
                            switch (alt10) {
                                case 1 :
                                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:5: NEWLINE
                                    {
                                    NEWLINE63=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_rule21262); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE63);


                                    }
                                    break;

                            }


                            WITH64=(Token)match(input,WITH,FOLLOW_WITH_in_rule21266); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_WITH.add(WITH64);


                            LSQUARE65=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_rule21268); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LSQUARE.add(LSQUARE65);


                            pushFollow(FOLLOW_assignList_in_rule21270);
                            assignList66=assignList();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_assignList.add(assignList66.getTree());

                            RSQUARE67=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_rule21272); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE67);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: VAR, CREATE, expr, assignList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 236:49: -> ^( CREATE VAR expr ( assignList )? )
                    {
                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:52: ^( CREATE VAR expr ( assignList )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        stream_CREATE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VAR.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:236:70: ( assignList )?
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
                    LBRACKET68=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_rule21294); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET68);


                    pushFollow(FOLLOW_rule2_in_rule21296);
                    rule269=rule2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule2.add(rule269.getTree());

                    RBRACKET70=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_rule21298); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET70);


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

        Token VAR71=null;
        Token EQUALS72=null;
        BACONParser.expr_return expr73 =null;


        Object VAR71_tree=null;
        Object EQUALS72_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:2: ( VAR EQUALS expr -> ^( ASSIGN VAR expr ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:241:4: VAR EQUALS expr
            {
            VAR71=(Token)match(input,VAR,FOLLOW_VAR_in_assign1313); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(VAR71);


            EQUALS72=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_assign1315); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS72);


            pushFollow(FOLLOW_expr_in_assign1317);
            expr73=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr73.getTree());

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

        Token NEWLINE75=null;
        Token COMMA76=null;
        Token NEWLINE78=null;
        BACONParser.assign_return assign74 =null;

        BACONParser.assign_return assign77 =null;


        Object NEWLINE75_tree=null;
        Object COMMA76_tree=null;
        Object NEWLINE78_tree=null;
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_assign=new RewriteRuleSubtreeStream(adaptor,"rule assign");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:2: ( assign ( NEWLINE )? ( COMMA assign ( NEWLINE )? )* -> ^( ASSIGNLIST assign ( assign )* ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:4: assign ( NEWLINE )? ( COMMA assign ( NEWLINE )? )*
            {
            pushFollow(FOLLOW_assign_in_assignList1338);
            assign74=assign();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assign.add(assign74.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:11: ( NEWLINE )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==NEWLINE) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:12: NEWLINE
                    {
                    NEWLINE75=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_assignList1341); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE75);


                    }
                    break;

            }


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:22: ( COMMA assign ( NEWLINE )? )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==COMMA) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:23: COMMA assign ( NEWLINE )?
            	    {
            	    COMMA76=(Token)match(input,COMMA,FOLLOW_COMMA_in_assignList1346); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA76);


            	    pushFollow(FOLLOW_assign_in_assignList1348);
            	    assign77=assign();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_assign.add(assign77.getTree());

            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:36: ( NEWLINE )?
            	    int alt14=2;
            	    int LA14_0 = input.LA(1);

            	    if ( (LA14_0==NEWLINE) ) {
            	        alt14=1;
            	    }
            	    switch (alt14) {
            	        case 1 :
            	            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:245:37: NEWLINE
            	            {
            	            NEWLINE78=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_assignList1351); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE78);


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
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

        BACONParser.expr2_return expr279 =null;

        BACONParser.lowPrecMathOp_return lowPrecMathOp80 =null;

        BACONParser.expr2_return expr281 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:2: ( expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:4: expr2 ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr2_in_expr1381);
            expr279=expr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr279.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:10: ( options {greedy=true; } : lowPrecMathOp ^ expr2 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==MINUS||LA16_0==PLUS) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:249:38: lowPrecMathOp ^ expr2
            	    {
            	    pushFollow(FOLLOW_lowPrecMathOp_in_expr1395);
            	    lowPrecMathOp80=lowPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecMathOp80.getTree(), root_0);

            	    pushFollow(FOLLOW_expr2_in_expr1398);
            	    expr281=expr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr281.getTree());

            	    }
            	    break;

            	default :
            	    break loop16;
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

        BACONParser.expr3_return expr382 =null;

        BACONParser.medPrecMathOp_return medPrecMathOp83 =null;

        BACONParser.expr3_return expr384 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:2: ( expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:4: expr3 ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr3_in_expr21412);
            expr382=expr3();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr382.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:10: ( options {greedy=true; } : medPrecMathOp ^ expr3 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==DIV||LA17_0==MUL) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:253:38: medPrecMathOp ^ expr3
            	    {
            	    pushFollow(FOLLOW_medPrecMathOp_in_expr21426);
            	    medPrecMathOp83=medPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(medPrecMathOp83.getTree(), root_0);

            	    pushFollow(FOLLOW_expr3_in_expr21430);
            	    expr384=expr3();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr384.getTree());

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

        BACONParser.expr4_return expr485 =null;

        BACONParser.highPrecMathOp_return highPrecMathOp86 =null;

        BACONParser.expr4_return expr487 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:2: ( expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:4: expr4 ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_expr4_in_expr31444);
            expr485=expr4();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr485.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:10: ( options {greedy=true; } : highPrecMathOp ^ expr4 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==POW) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:257:38: highPrecMathOp ^ expr4
            	    {
            	    pushFollow(FOLLOW_highPrecMathOp_in_expr31458);
            	    highPrecMathOp86=highPrecMathOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(highPrecMathOp86.getTree(), root_0);

            	    pushFollow(FOLLOW_expr4_in_expr31461);
            	    expr487=expr4();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr487.getTree());

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

        Token LBRACKET88=null;
        Token RBRACKET90=null;
        Token LBRACKET92=null;
        Token RBRACKET94=null;
        Token MINUS95=null;
        Token FLOAT97=null;
        Token VAR98=null;
        Token IF99=null;
        Token THEN101=null;
        Token ELSE103=null;
        Token LBRACKET106=null;
        Token COMMA108=null;
        Token RBRACKET110=null;
        Token VARHIST111=null;
        Token LBRACKET112=null;
        Token VAR113=null;
        Token COMMA114=null;
        Token RBRACKET116=null;
        Token LBRACKET118=null;
        Token RBRACKET120=null;
        BACONParser.expr_return expr89 =null;

        BACONParser.unaryPrimitives_return unaryPrimitives91 =null;

        BACONParser.expr_return expr93 =null;

        BACONParser.expr_return expr96 =null;

        BACONParser.bExpr_return bExpr100 =null;

        BACONParser.expr_return expr102 =null;

        BACONParser.expr_return expr104 =null;

        BACONParser.binPrim_return binPrim105 =null;

        BACONParser.expr_return expr107 =null;

        BACONParser.expr_return expr109 =null;

        BACONParser.expr_return expr115 =null;

        BACONParser.vOp_return vOp117 =null;

        BACONParser.expr_return expr119 =null;


        Object LBRACKET88_tree=null;
        Object RBRACKET90_tree=null;
        Object LBRACKET92_tree=null;
        Object RBRACKET94_tree=null;
        Object MINUS95_tree=null;
        Object FLOAT97_tree=null;
        Object VAR98_tree=null;
        Object IF99_tree=null;
        Object THEN101_tree=null;
        Object ELSE103_tree=null;
        Object LBRACKET106_tree=null;
        Object COMMA108_tree=null;
        Object RBRACKET110_tree=null;
        Object VARHIST111_tree=null;
        Object LBRACKET112_tree=null;
        Object VAR113_tree=null;
        Object COMMA114_tree=null;
        Object RBRACKET116_tree=null;
        Object LBRACKET118_tree=null;
        Object RBRACKET120_tree=null;
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
            int alt19=9;
            switch ( input.LA(1) ) {
            case LBRACKET:
                {
                alt19=1;
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
                alt19=2;
                }
                break;
            case MINUS:
                {
                alt19=3;
                }
                break;
            case FLOAT:
                {
                alt19=4;
                }
                break;
            case VAR:
                {
                alt19=5;
                }
                break;
            case IF:
                {
                alt19=6;
                }
                break;
            case MAX:
            case MIN:
                {
                alt19=7;
                }
                break;
            case VARHIST:
                {
                alt19=8;
                }
                break;
            case VAVERAGE:
            case VPRODUCT:
            case VSUM:
                {
                alt19=9;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:262:5: LBRACKET expr RBRACKET
                    {
                    LBRACKET88=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41477); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET88);


                    pushFollow(FOLLOW_expr_in_expr41479);
                    expr89=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr89.getTree());

                    RBRACKET90=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41481); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET90);


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
                    pushFollow(FOLLOW_unaryPrimitives_in_expr41490);
                    unaryPrimitives91=unaryPrimitives();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryPrimitives.add(unaryPrimitives91.getTree());

                    LBRACKET92=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41492); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET92);


                    pushFollow(FOLLOW_expr_in_expr41494);
                    expr93=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr93.getTree());

                    RBRACKET94=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41496); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET94);


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
                    MINUS95=(Token)match(input,MINUS,FOLLOW_MINUS_in_expr41509); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_MINUS.add(MINUS95);


                    pushFollow(FOLLOW_expr_in_expr41511);
                    expr96=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr96.getTree());

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


                    FLOAT97=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_expr41524); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT97_tree = 
                    (Object)adaptor.create(FLOAT97)
                    ;
                    adaptor.addChild(root_0, FLOAT97_tree);
                    }

                    }
                    break;
                case 5 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:266:4: VAR
                    {
                    root_0 = (Object)adaptor.nil();


                    VAR98=(Token)match(input,VAR,FOLLOW_VAR_in_expr41529); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VAR98_tree = 
                    (Object)adaptor.create(VAR98)
                    ;
                    adaptor.addChild(root_0, VAR98_tree);
                    }

                    }
                    break;
                case 6 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:267:4: IF bExpr THEN expr ELSE expr
                    {
                    IF99=(Token)match(input,IF,FOLLOW_IF_in_expr41534); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF99);


                    pushFollow(FOLLOW_bExpr_in_expr41536);
                    bExpr100=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr100.getTree());

                    THEN101=(Token)match(input,THEN,FOLLOW_THEN_in_expr41538); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_THEN.add(THEN101);


                    pushFollow(FOLLOW_expr_in_expr41540);
                    expr102=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr102.getTree());

                    ELSE103=(Token)match(input,ELSE,FOLLOW_ELSE_in_expr41542); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELSE.add(ELSE103);


                    pushFollow(FOLLOW_expr_in_expr41544);
                    expr104=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr104.getTree());

                    // AST REWRITE
                    // elements: expr, bExpr, expr, IF
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
                    pushFollow(FOLLOW_binPrim_in_expr41561);
                    binPrim105=binPrim();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_binPrim.add(binPrim105.getTree());

                    LBRACKET106=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41563); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET106);


                    pushFollow(FOLLOW_expr_in_expr41565);
                    expr107=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr107.getTree());

                    COMMA108=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41567); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA108);


                    pushFollow(FOLLOW_expr_in_expr41569);
                    expr109=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr109.getTree());

                    RBRACKET110=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41571); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET110);


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
                    VARHIST111=(Token)match(input,VARHIST,FOLLOW_VARHIST_in_expr41586); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VARHIST.add(VARHIST111);


                    LBRACKET112=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41588); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET112);


                    VAR113=(Token)match(input,VAR,FOLLOW_VAR_in_expr41590); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR113);


                    COMMA114=(Token)match(input,COMMA,FOLLOW_COMMA_in_expr41592); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA114);


                    pushFollow(FOLLOW_expr_in_expr41594);
                    expr115=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr115.getTree());

                    RBRACKET116=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41596); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET116);


                    // AST REWRITE
                    // elements: expr, VARHIST, VAR
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
                    pushFollow(FOLLOW_vOp_in_expr41611);
                    vOp117=vOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vOp.add(vOp117.getTree());

                    LBRACKET118=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_expr41613); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET118);


                    pushFollow(FOLLOW_expr_in_expr41615);
                    expr119=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr119.getTree());

                    RBRACKET120=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_expr41617); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET120);


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

        BACONParser.bExpr2_return bExpr2121 =null;

        BACONParser.lowPrecBoolOp_return lowPrecBoolOp122 =null;

        BACONParser.bExpr2_return bExpr2123 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:2: ( bExpr2 ( lowPrecBoolOp ^ bExpr2 )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:4: bExpr2 ( lowPrecBoolOp ^ bExpr2 )*
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_bExpr2_in_bExpr1637);
            bExpr2121=bExpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2121.getTree());

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:11: ( lowPrecBoolOp ^ bExpr2 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==AND||LA20_0==OR) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:275:12: lowPrecBoolOp ^ bExpr2
            	    {
            	    pushFollow(FOLLOW_lowPrecBoolOp_in_bExpr1640);
            	    lowPrecBoolOp122=lowPrecBoolOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lowPrecBoolOp122.getTree(), root_0);

            	    pushFollow(FOLLOW_bExpr2_in_bExpr1643);
            	    bExpr2123=bExpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, bExpr2123.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
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

        Token NOT127=null;
        Token LBRACKET128=null;
        Token RBRACKET130=null;
        Token LBRACKET131=null;
        Token RBRACKET133=null;
        Token LBRACKET135=null;
        Token RBRACKET137=null;
        BACONParser.expr_return expr124 =null;

        BACONParser.comparators_return comparators125 =null;

        BACONParser.expr_return expr126 =null;

        BACONParser.bExpr_return bExpr129 =null;

        BACONParser.bExpr_return bExpr132 =null;

        BACONParser.vBOp_return vBOp134 =null;

        BACONParser.bExpr_return bExpr136 =null;


        Object NOT127_tree=null;
        Object LBRACKET128_tree=null;
        Object RBRACKET130_tree=null;
        Object LBRACKET131_tree=null;
        Object RBRACKET133_tree=null;
        Object LBRACKET135_tree=null;
        Object RBRACKET137_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleSubtreeStream stream_bExpr=new RewriteRuleSubtreeStream(adaptor,"rule bExpr");
        RewriteRuleSubtreeStream stream_vBOp=new RewriteRuleSubtreeStream(adaptor,"rule vBOp");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:279:2: ( ( expr comparators )=> expr comparators ^ expr | NOT LBRACKET bExpr RBRACKET -> ^( NOT bExpr ) | LBRACKET bExpr RBRACKET -> bExpr | vBOp LBRACKET bExpr RBRACKET -> ^( vBOp bExpr ) )
            int alt21=4;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==LBRACKET) ) {
                int LA21_1 = input.LA(2);

                if ( (synpred1_BACON()) ) {
                    alt21=1;
                }
                else if ( (true) ) {
                    alt21=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;

                }
            }
            else if ( ((LA21_0 >= ABS && LA21_0 <= ACOS)||LA21_0==ASIN||LA21_0==ATAN||LA21_0==COS||(LA21_0 >= DENSITYAT && LA21_0 <= DEPTHFORVI)||LA21_0==EXP||LA21_0==FULLIRRADAT||LA21_0==INTEGRATE||(LA21_0 >= LN && LA21_0 <= LOGTEN)||LA21_0==RND||(LA21_0 >= SALINITYAT && LA21_0 <= SIN)||(LA21_0 >= SQRT && LA21_0 <= TEMPAT)||LA21_0==UVIRRADAT||LA21_0==VISIRRADAT) && (synpred1_BACON())) {
                alt21=1;
            }
            else if ( (LA21_0==MINUS) && (synpred1_BACON())) {
                alt21=1;
            }
            else if ( (LA21_0==FLOAT) && (synpred1_BACON())) {
                alt21=1;
            }
            else if ( (LA21_0==VAR) && (synpred1_BACON())) {
                alt21=1;
            }
            else if ( (LA21_0==IF) && (synpred1_BACON())) {
                alt21=1;
            }
            else if ( ((LA21_0 >= MAX && LA21_0 <= MIN)) && (synpred1_BACON())) {
                alt21=1;
            }
            else if ( (LA21_0==VARHIST) && (synpred1_BACON())) {
                alt21=1;
            }
            else if ( (LA21_0==VAVERAGE||(LA21_0 >= VPRODUCT && LA21_0 <= VSUM)) && (synpred1_BACON())) {
                alt21=1;
            }
            else if ( (LA21_0==NOT) ) {
                alt21=2;
            }
            else if ( (LA21_0==ALL||LA21_0==NONE||LA21_0==SOME) ) {
                alt21=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:279:4: ( expr comparators )=> expr comparators ^ expr
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_bExpr21667);
                    expr124=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr124.getTree());

                    pushFollow(FOLLOW_comparators_in_bExpr21669);
                    comparators125=comparators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(comparators125.getTree(), root_0);

                    pushFollow(FOLLOW_expr_in_bExpr21672);
                    expr126=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr126.getTree());

                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:280:4: NOT LBRACKET bExpr RBRACKET
                    {
                    NOT127=(Token)match(input,NOT,FOLLOW_NOT_in_bExpr21677); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NOT.add(NOT127);


                    LBRACKET128=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21679); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET128);


                    pushFollow(FOLLOW_bExpr_in_bExpr21681);
                    bExpr129=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr129.getTree());

                    RBRACKET130=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21683); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET130);


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
                    LBRACKET131=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21696); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET131);


                    pushFollow(FOLLOW_bExpr_in_bExpr21698);
                    bExpr132=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr132.getTree());

                    RBRACKET133=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21700); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET133);


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
                    pushFollow(FOLLOW_vBOp_in_bExpr21709);
                    vBOp134=vBOp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_vBOp.add(vBOp134.getTree());

                    LBRACKET135=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_bExpr21711); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACKET.add(LBRACKET135);


                    pushFollow(FOLLOW_bExpr_in_bExpr21713);
                    bExpr136=bExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_bExpr.add(bExpr136.getTree());

                    RBRACKET137=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_bExpr21715); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACKET.add(RBRACKET137);


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

        Token set138=null;

        Object set138_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:289:2: ( ABS | ACOS | ASIN | ATAN | COS | EXP | LN | LOGTEN | RND | SIN | SQRT | TAN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | INTEGRATE | VISIRRADAT )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set138=(Token)input.LT(1);

            if ( (input.LA(1) >= ABS && input.LA(1) <= ACOS)||input.LA(1)==ASIN||input.LA(1)==ATAN||input.LA(1)==COS||(input.LA(1) >= DENSITYAT && input.LA(1) <= DEPTHFORVI)||input.LA(1)==EXP||input.LA(1)==FULLIRRADAT||input.LA(1)==INTEGRATE||(input.LA(1) >= LN && input.LA(1) <= LOGTEN)||input.LA(1)==RND||(input.LA(1) >= SALINITYAT && input.LA(1) <= SIN)||(input.LA(1) >= SQRT && input.LA(1) <= TEMPAT)||input.LA(1)==UVIRRADAT||input.LA(1)==VISIRRADAT ) {
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

        Token set139=null;

        Object set139_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:313:2: ( PLUS | MINUS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set139=(Token)input.LT(1);

            if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
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

        Token set140=null;

        Object set140_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:318:2: ( MUL | DIV )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set140=(Token)input.LT(1);

            if ( input.LA(1)==DIV||input.LA(1)==MUL ) {
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

        Token POW141=null;

        Object POW141_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:323:2: ( POW )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:323:4: POW
            {
            root_0 = (Object)adaptor.nil();


            POW141=(Token)match(input,POW,FOLLOW_POW_in_highPrecMathOp1885); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            POW141_tree = 
            (Object)adaptor.create(POW141)
            ;
            adaptor.addChild(root_0, POW141_tree);
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

        Token set142=null;

        Object set142_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:327:2: ( MIN | MAX )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set142=(Token)input.LT(1);

            if ( (input.LA(1) >= MAX && input.LA(1) <= MIN) ) {
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

        Token set143=null;

        Object set143_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:332:2: ( AND | OR )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set143=(Token)input.LT(1);

            if ( input.LA(1)==AND||input.LA(1)==OR ) {
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

        Token set144=null;

        Object set144_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:337:2: ( EQUALS | NEQUALS | GREATERTHAN | LESSTHAN | GREATEREQUALS | LESSEQUALS )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set144=(Token)input.LT(1);

            if ( input.LA(1)==EQUALS||(input.LA(1) >= GREATEREQUALS && input.LA(1) <= GREATERTHAN)||(input.LA(1) >= LESSEQUALS && input.LA(1) <= LESSTHAN)||input.LA(1)==NEQUALS ) {
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

        Token set145=null;

        Object set145_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:346:2: ( VSUM | VPRODUCT | VAVERAGE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set145=(Token)input.LT(1);

            if ( input.LA(1)==VAVERAGE||(input.LA(1) >= VPRODUCT && input.LA(1) <= VSUM) ) {
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

        Token set146=null;

        Object set146_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:352:2: ( ALL | SOME | NONE )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            root_0 = (Object)adaptor.nil();


            set146=(Token)input.LT(1);

            if ( input.LA(1)==ALL||input.LA(1)==NONE||input.LA(1)==SOME ) {
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
        pushFollow(FOLLOW_expr_in_synpred1_BACON1659);
        expr();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_comparators_in_synpred1_BACON1661);
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


 

    public static final BitSet FOLLOW_NEWLINE_in_rules940 = new BitSet(new long[]{0x8880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_pair_in_rules944 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules947 = new BitSet(new long[]{0x8880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_pair_in_rules949 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_rules954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleName_in_pair980 = new BitSet(new long[]{0x0880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_rule2_in_pair982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_pair997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULENAME_in_ruleName1009 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName1011 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName1014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_ruleName1025 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_COLON_in_ruleName1027 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_ruleName1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule2_in_rule1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_rule21066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_rule21071 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_rule21073 = new BitSet(new long[]{0x0008000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_rule21076 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_THEN_in_rule21080 = new BitSet(new long[]{0x0888005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_NEWLINE_in_rule21083 = new BitSet(new long[]{0x0880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_rule_in_rule21087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPTAKE_in_rule21102 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21104 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21106 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21108 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21110 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELEASE_in_rule21127 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21129 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21131 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21133 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21135 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INGEST_in_rule21152 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21154 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21156 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21158 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21160 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21162 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21164 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHANGE_in_rule21183 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21185 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21187 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PCHANGE_in_rule21202 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21204 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21206 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21208 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21210 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVIDE_in_rule21227 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21229 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21231 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CREATE_in_rule21246 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21248 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_rule21250 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_rule21252 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_rule21254 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21256 = new BitSet(new long[]{0x0008000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_NEWLINE_in_rule21262 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_WITH_in_rule21266 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LSQUARE_in_rule21268 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_assignList_in_rule21270 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_RSQUARE_in_rule21272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_rule21294 = new BitSet(new long[]{0x0880005401042000L,0x0000000000000A00L});
    public static final BitSet FOLLOW_rule2_in_rule21296 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_rule21298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_assign1313 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_EQUALS_in_assign1315 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_assign1317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_assignList1338 = new BitSet(new long[]{0x0008000000008002L});
    public static final BitSet FOLLOW_NEWLINE_in_assignList1341 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_assignList1346 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_assign_in_assignList1348 = new BitSet(new long[]{0x0008000000008002L});
    public static final BitSet FOLLOW_NEWLINE_in_assignList1351 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_expr2_in_expr1381 = new BitSet(new long[]{0x0100800000000002L});
    public static final BitSet FOLLOW_lowPrecMathOp_in_expr1395 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr2_in_expr1398 = new BitSet(new long[]{0x0100800000000002L});
    public static final BitSet FOLLOW_expr3_in_expr21412 = new BitSet(new long[]{0x0001000000800002L});
    public static final BitSet FOLLOW_medPrecMathOp_in_expr21426 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr3_in_expr21430 = new BitSet(new long[]{0x0001000000800002L});
    public static final BitSet FOLLOW_expr4_in_expr31444 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_highPrecMathOp_in_expr31458 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr4_in_expr31461 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41477 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41479 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryPrimitives_in_expr41490 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41492 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41494 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_expr41509 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_expr41524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_expr41529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr41534 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_expr41536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_THEN_in_expr41538 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41540 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ELSE_in_expr41542 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_binPrim_in_expr41561 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41563 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41565 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41567 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41569 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARHIST_in_expr41586 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41588 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_VAR_in_expr41590 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_COMMA_in_expr41592 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41594 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vOp_in_expr41611 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_expr41613 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_expr41615 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_expr41617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1637 = new BitSet(new long[]{0x0040000000000082L});
    public static final BitSet FOLLOW_lowPrecBoolOp_in_bExpr1640 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr2_in_bExpr1643 = new BitSet(new long[]{0x0040000000000082L});
    public static final BitSet FOLLOW_expr_in_bExpr21667 = new BitSet(new long[]{0x0004018304000000L});
    public static final BitSet FOLLOW_comparators_in_bExpr21669 = new BitSet(new long[]{0x1000EC64C83A0930L,0x000000000001FC76L});
    public static final BitSet FOLLOW_expr_in_bExpr21672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bExpr21677 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21679 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21681 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21696 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21698 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_vBOp_in_bExpr21709 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_bExpr21711 = new BitSet(new long[]{0x1030EC64C83A0970L,0x000000000001FC7EL});
    public static final BitSet FOLLOW_bExpr_in_bExpr21713 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_bExpr21715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POW_in_highPrecMathOp1885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred1_BACON1659 = new BitSet(new long[]{0x0004018304000000L});
    public static final BitSet FOLLOW_comparators_in_synpred1_BACON1661 = new BitSet(new long[]{0x0000000000000002L});

}