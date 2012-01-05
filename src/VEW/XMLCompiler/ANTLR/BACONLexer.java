// $ANTLR 3.4 C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2011-12-06 20:15:07

package VEW.XMLCompiler.ANTLR;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class BACONLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public BACONLexer() {} 
    public BACONLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public BACONLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g"; }

    // $ANTLR start "RULENAME"
    public final void mRULENAME() throws RecognitionException {
        try {
            int _type = RULENAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:73:10: ( ( '\"' ) (~ '\"' )* ( '\"' ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:73:12: ( '\"' ) (~ '\"' )* ( '\"' )
            {
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:73:12: ( '\"' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:73:13: '\"'
            {
            match('\"'); 

            }


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:73:17: (~ '\"' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '\u0000' && LA1_0 <= '!')||(LA1_0 >= '#' && LA1_0 <= '\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:73:24: ( '\"' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:73:25: '\"'
            {
            match('\"'); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RULENAME"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:74:10: ( ( ':' ) ( IGNORE )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:74:12: ( ':' ) ( IGNORE )*
            {
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:74:12: ( ':' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:74:13: ':'
            {
            match(':'); 

            }


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:74:17: ( IGNORE )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '\t' && LA2_0 <= '\n')||LA2_0=='\r'||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:74:18: IGNORE
            	    {
            	    mIGNORE(); 


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:78:6: ( 'if' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:78:8: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:79:6: ( 'then' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:79:8: 'then'
            {
            match("then"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "THEN"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:80:6: ( 'else' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:80:8: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "UPTAKE"
    public final void mUPTAKE() throws RecognitionException {
        try {
            int _type = UPTAKE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:83:12: ( 'uptake' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:83:14: 'uptake'
            {
            match("uptake"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UPTAKE"

    // $ANTLR start "RELEASE"
    public final void mRELEASE() throws RecognitionException {
        try {
            int _type = RELEASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:84:12: ( 'release' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:84:14: 'release'
            {
            match("release"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RELEASE"

    // $ANTLR start "INGEST"
    public final void mINGEST() throws RecognitionException {
        try {
            int _type = INGEST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:85:12: ( 'ingest' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:85:14: 'ingest'
            {
            match("ingest"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INGEST"

    // $ANTLR start "CHANGE"
    public final void mCHANGE() throws RecognitionException {
        try {
            int _type = CHANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:86:12: ( 'change' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:86:14: 'change'
            {
            match("change"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHANGE"

    // $ANTLR start "PCHANGE"
    public final void mPCHANGE() throws RecognitionException {
        try {
            int _type = PCHANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:87:12: ( 'pchange' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:87:14: 'pchange'
            {
            match("pchange"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PCHANGE"

    // $ANTLR start "DIVIDE"
    public final void mDIVIDE() throws RecognitionException {
        try {
            int _type = DIVIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:88:11: ( 'divide' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:88:13: 'divide'
            {
            match("divide"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIVIDE"

    // $ANTLR start "INTEGRATE"
    public final void mINTEGRATE() throws RecognitionException {
        try {
            int _type = INTEGRATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:89:12: ( 'integrate' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:89:14: 'integrate'
            {
            match("integrate"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTEGRATE"

    // $ANTLR start "BAR"
    public final void mBAR() throws RecognitionException {
        try {
            int _type = BAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:92:6: ( '|' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:92:8: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BAR"

    // $ANTLR start "OTHERWISE"
    public final void mOTHERWISE() throws RecognitionException {
        try {
            int _type = OTHERWISE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:94:2: ( 'otherwise' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:94:4: 'otherwise'
            {
            match("otherwise"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OTHERWISE"

    // $ANTLR start "TO"
    public final void mTO() throws RecognitionException {
        try {
            int _type = TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:95:4: ( 'to' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:95:6: 'to'
            {
            match("to"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TO"

    // $ANTLR start "CREATE"
    public final void mCREATE() throws RecognitionException {
        try {
            int _type = CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:98:8: ( 'create' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:98:10: 'create'
            {
            match("create"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CREATE"

    // $ANTLR start "WITH"
    public final void mWITH() throws RecognitionException {
        try {
            int _type = WITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:99:6: ( 'with' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:99:8: 'with'
            {
            match("with"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WITH"

    // $ANTLR start "LSQUARE"
    public final void mLSQUARE() throws RecognitionException {
        try {
            int _type = LSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:100:9: ( '[' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:100:11: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LSQUARE"

    // $ANTLR start "RSQUARE"
    public final void mRSQUARE() throws RecognitionException {
        try {
            int _type = RSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:101:9: ( ']' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:101:11: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RSQUARE"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:102:8: ( ',' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:102:10: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "ABS"
    public final void mABS() throws RecognitionException {
        try {
            int _type = ABS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:105:8: ( 'abs' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:105:10: 'abs'
            {
            match("abs"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ABS"

    // $ANTLR start "ACOS"
    public final void mACOS() throws RecognitionException {
        try {
            int _type = ACOS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:106:8: ( 'acos' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:106:10: 'acos'
            {
            match("acos"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ACOS"

    // $ANTLR start "ASIN"
    public final void mASIN() throws RecognitionException {
        try {
            int _type = ASIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:107:8: ( 'asin' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:107:10: 'asin'
            {
            match("asin"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASIN"

    // $ANTLR start "ATAN"
    public final void mATAN() throws RecognitionException {
        try {
            int _type = ATAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:108:8: ( 'atan' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:108:10: 'atan'
            {
            match("atan"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ATAN"

    // $ANTLR start "SIN"
    public final void mSIN() throws RecognitionException {
        try {
            int _type = SIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:109:8: ( 'sin' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:109:10: 'sin'
            {
            match("sin"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIN"

    // $ANTLR start "COS"
    public final void mCOS() throws RecognitionException {
        try {
            int _type = COS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:110:8: ( 'cos' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:110:10: 'cos'
            {
            match("cos"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COS"

    // $ANTLR start "TAN"
    public final void mTAN() throws RecognitionException {
        try {
            int _type = TAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:111:8: ( 'tan' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:111:10: 'tan'
            {
            match("tan"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TAN"

    // $ANTLR start "EXP"
    public final void mEXP() throws RecognitionException {
        try {
            int _type = EXP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:112:8: ( 'exp' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:112:10: 'exp'
            {
            match("exp"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXP"

    // $ANTLR start "LN"
    public final void mLN() throws RecognitionException {
        try {
            int _type = LN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:113:8: ( 'ln' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:113:10: 'ln'
            {
            match("ln"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LN"

    // $ANTLR start "LOGTEN"
    public final void mLOGTEN() throws RecognitionException {
        try {
            int _type = LOGTEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:114:8: ( 'log10' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:114:10: 'log10'
            {
            match("log10"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LOGTEN"

    // $ANTLR start "RND"
    public final void mRND() throws RecognitionException {
        try {
            int _type = RND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:115:8: ( 'rnd' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:115:10: 'rnd'
            {
            match("rnd"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RND"

    // $ANTLR start "SQRT"
    public final void mSQRT() throws RecognitionException {
        try {
            int _type = SQRT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:116:8: ( 'sqrt' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:116:10: 'sqrt'
            {
            match("sqrt"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SQRT"

    // $ANTLR start "MAX"
    public final void mMAX() throws RecognitionException {
        try {
            int _type = MAX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:117:8: ( 'max' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:117:10: 'max'
            {
            match("max"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MAX"

    // $ANTLR start "MIN"
    public final void mMIN() throws RecognitionException {
        try {
            int _type = MIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:118:8: ( 'min' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:118:10: 'min'
            {
            match("min"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MIN"

    // $ANTLR start "DENSITYAT"
    public final void mDENSITYAT() throws RecognitionException {
        try {
            int _type = DENSITYAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:121:13: ( 'densityAt' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:121:15: 'densityAt'
            {
            match("densityAt"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DENSITYAT"

    // $ANTLR start "DEPTHFORFI"
    public final void mDEPTHFORFI() throws RecognitionException {
        try {
            int _type = DEPTHFORFI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:122:13: ( 'depthForFI' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:122:15: 'depthForFI'
            {
            match("depthForFI"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEPTHFORFI"

    // $ANTLR start "DEPTHFORVI"
    public final void mDEPTHFORVI() throws RecognitionException {
        try {
            int _type = DEPTHFORVI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:123:13: ( 'depthForVI' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:123:15: 'depthForVI'
            {
            match("depthForVI"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEPTHFORVI"

    // $ANTLR start "FULLIRRADAT"
    public final void mFULLIRRADAT() throws RecognitionException {
        try {
            int _type = FULLIRRADAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:124:13: ( 'fullIrradAt' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:124:15: 'fullIrradAt'
            {
            match("fullIrradAt"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FULLIRRADAT"

    // $ANTLR start "VISIRRADAT"
    public final void mVISIRRADAT() throws RecognitionException {
        try {
            int _type = VISIRRADAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:125:13: ( 'visIrradAt' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:125:15: 'visIrradAt'
            {
            match("visIrradAt"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VISIRRADAT"

    // $ANTLR start "SALINITYAT"
    public final void mSALINITYAT() throws RecognitionException {
        try {
            int _type = SALINITYAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:126:13: ( 'salinityAt' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:126:15: 'salinityAt'
            {
            match("salinityAt"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SALINITYAT"

    // $ANTLR start "TEMPAT"
    public final void mTEMPAT() throws RecognitionException {
        try {
            int _type = TEMPAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:127:13: ( 'temperatureAt' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:127:15: 'temperatureAt'
            {
            match("temperatureAt"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TEMPAT"

    // $ANTLR start "UVIRRADAT"
    public final void mUVIRRADAT() throws RecognitionException {
        try {
            int _type = UVIRRADAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:128:13: ( 'UVIrradAt' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:128:15: 'UVIrradAt'
            {
            match("UVIrradAt"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UVIRRADAT"

    // $ANTLR start "VARHIST"
    public final void mVARHIST() throws RecognitionException {
        try {
            int _type = VARHIST;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:129:13: ( 'varhist' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:129:15: 'varhist'
            {
            match("varhist"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VARHIST"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:132:16: ( ( '0' .. '9' ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:5: ( ( MINUS )? ( DIGIT )+ ( '.' ( DIGIT )* ( EXPONENT )? )? | ( MINUS )? ( DIGIT )+ EXPONENT )
            int alt10=2;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:9: ( MINUS )? ( DIGIT )+ ( '.' ( DIGIT )* ( EXPONENT )? )?
                    {
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:9: ( MINUS )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='-') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
                            {
                            if ( input.LA(1)=='-' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:18: ( DIGIT )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt4 >= 1 ) break loop4;
                                EarlyExitException eee =
                                    new EarlyExitException(4, input);
                                throw eee;
                        }
                        cnt4++;
                    } while (true);


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:25: ( '.' ( DIGIT )* ( EXPONENT )? )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='.') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:26: '.' ( DIGIT )* ( EXPONENT )?
                            {
                            match('.'); 

                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:30: ( DIGIT )*
                            loop5:
                            do {
                                int alt5=2;
                                int LA5_0 = input.LA(1);

                                if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
                                    alt5=1;
                                }


                                switch (alt5) {
                            	case 1 :
                            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
                            	    {
                            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                            	        input.consume();
                            	    }
                            	    else {
                            	        MismatchedSetException mse = new MismatchedSetException(null,input);
                            	        recover(mse);
                            	        throw mse;
                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop5;
                                }
                            } while (true);


                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:37: ( EXPONENT )?
                            int alt6=2;
                            int LA6_0 = input.LA(1);

                            if ( (LA6_0=='E'||LA6_0=='e') ) {
                                alt6=1;
                            }
                            switch (alt6) {
                                case 1 :
                                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:135:37: EXPONENT
                                    {
                                    mEXPONENT(); 


                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:136:9: ( MINUS )? ( DIGIT )+ EXPONENT
                    {
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:136:9: ( MINUS )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='-') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
                            {
                            if ( input.LA(1)=='-' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:136:18: ( DIGIT )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
                    } while (true);


                    mEXPONENT(); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:141:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( DIGIT )+ )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:141:12: ( 'e' | 'E' ) ( '+' | '-' )? ( DIGIT )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:141:22: ( '+' | '-' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='+'||LA11_0=='-') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:141:33: ( DIGIT )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:143:10: ( '=' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:143:12: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUALS"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:144:10: ( '+' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:144:12: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:145:10: ( '-' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:145:12: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "MUL"
    public final void mMUL() throws RecognitionException {
        try {
            int _type = MUL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:146:10: ( '*' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:146:12: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MUL"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:147:10: ( '/' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:147:12: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "POW"
    public final void mPOW() throws RecognitionException {
        try {
            int _type = POW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:148:10: ( '^' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:148:12: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "POW"

    // $ANTLR start "LBRACKET"
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:149:10: ( '(' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:149:12: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LBRACKET"

    // $ANTLR start "RBRACKET"
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:150:10: ( ')' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:150:12: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RBRACKET"

    // $ANTLR start "GREATEREQUALS"
    public final void mGREATEREQUALS() throws RecognitionException {
        try {
            int _type = GREATEREQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:153:15: ( '>=' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:153:17: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATEREQUALS"

    // $ANTLR start "LESSEQUALS"
    public final void mLESSEQUALS() throws RecognitionException {
        try {
            int _type = LESSEQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:154:15: ( '<=' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:154:17: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESSEQUALS"

    // $ANTLR start "NEQUALS"
    public final void mNEQUALS() throws RecognitionException {
        try {
            int _type = NEQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:155:15: ( '<>' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:155:17: '<>'
            {
            match("<>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEQUALS"

    // $ANTLR start "GREATERTHAN"
    public final void mGREATERTHAN() throws RecognitionException {
        try {
            int _type = GREATERTHAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:156:15: ( '>' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:156:17: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATERTHAN"

    // $ANTLR start "LESSTHAN"
    public final void mLESSTHAN() throws RecognitionException {
        try {
            int _type = LESSTHAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:157:15: ( '<' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:157:17: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESSTHAN"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:158:15: ( 'and' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:158:18: 'and'
            {
            match("and"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:159:15: ( 'or' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:159:17: 'or'
            {
            match("or"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:160:15: ( 'not' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:160:17: 'not'
            {
            match("not"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:164:7: ( 'all' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:164:9: 'all'
            {
            match("all"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ALL"

    // $ANTLR start "SOME"
    public final void mSOME() throws RecognitionException {
        try {
            int _type = SOME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:165:7: ( 'some' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:165:9: 'some'
            {
            match("some"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SOME"

    // $ANTLR start "NONE"
    public final void mNONE() throws RecognitionException {
        try {
            int _type = NONE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:166:7: ( 'none' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:166:9: 'none'
            {
            match("none"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NONE"

    // $ANTLR start "VAVERAGE"
    public final void mVAVERAGE() throws RecognitionException {
        try {
            int _type = VAVERAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:167:10: ( 'average' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:167:12: 'average'
            {
            match("average"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VAVERAGE"

    // $ANTLR start "VPRODUCT"
    public final void mVPRODUCT() throws RecognitionException {
        try {
            int _type = VPRODUCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:168:10: ( 'product' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:168:12: 'product'
            {
            match("product"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VPRODUCT"

    // $ANTLR start "VSUM"
    public final void mVSUM() throws RecognitionException {
        try {
            int _type = VSUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:169:7: ( 'sum' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:169:9: 'sum'
            {
            match("sum"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VSUM"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:172:17: ( ( 'a' .. 'z' | 'A' .. 'Z' ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "VAR"
    public final void mVAR() throws RecognitionException {
        try {
            int _type = VAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:173:5: ( ( LETTER ) ( LETTER | DIGIT | '_' )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:173:7: ( LETTER ) ( LETTER | DIGIT | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:173:15: ( LETTER | DIGIT | '_' )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0 >= '0' && LA13_0 <= '9')||(LA13_0 >= 'A' && LA13_0 <= 'Z')||LA13_0=='_'||(LA13_0 >= 'a' && LA13_0 <= 'z')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VAR"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:177:9: ( ( '//' ) (~ '\\n' )* )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:177:11: ( '//' ) (~ '\\n' )*
            {
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:177:11: ( '//' )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:177:12: '//'
            {
            match("//"); 



            }


            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:177:17: (~ '\\n' )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0 >= '\u0000' && LA14_0 <= '\t')||(LA14_0 >= '\u000B' && LA14_0 <= '\uFFFF')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "IGNORE"
    public final void mIGNORE() throws RecognitionException {
        try {
            int _type = IGNORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:182:9: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:182:11: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IGNORE"

    // $ANTLR start "UNKNOWN"
    public final void mUNKNOWN() throws RecognitionException {
        try {
            int _type = UNKNOWN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:185:9: ( ( . ) )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:185:11: ( . )
            {
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:185:11: ( . )
            // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:185:12: .
            {
            matchAny(); 

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNKNOWN"

    public void mTokens() throws RecognitionException {
        // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:8: ( RULENAME | COLON | IF | THEN | ELSE | UPTAKE | RELEASE | INGEST | CHANGE | PCHANGE | DIVIDE | INTEGRATE | BAR | OTHERWISE | TO | CREATE | WITH | LSQUARE | RSQUARE | COMMA | ABS | ACOS | ASIN | ATAN | SIN | COS | TAN | EXP | LN | LOGTEN | RND | SQRT | MAX | MIN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | VISIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | VARHIST | FLOAT | EQUALS | PLUS | MINUS | MUL | DIV | POW | LBRACKET | RBRACKET | GREATEREQUALS | LESSEQUALS | NEQUALS | GREATERTHAN | LESSTHAN | AND | OR | NOT | ALL | SOME | NONE | VAVERAGE | VPRODUCT | VSUM | VAR | COMMENT | IGNORE | UNKNOWN )
        int alt15=70;
        alt15 = dfa15.predict(input);
        switch (alt15) {
            case 1 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:10: RULENAME
                {
                mRULENAME(); 


                }
                break;
            case 2 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:19: COLON
                {
                mCOLON(); 


                }
                break;
            case 3 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:25: IF
                {
                mIF(); 


                }
                break;
            case 4 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:28: THEN
                {
                mTHEN(); 


                }
                break;
            case 5 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:33: ELSE
                {
                mELSE(); 


                }
                break;
            case 6 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:38: UPTAKE
                {
                mUPTAKE(); 


                }
                break;
            case 7 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:45: RELEASE
                {
                mRELEASE(); 


                }
                break;
            case 8 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:53: INGEST
                {
                mINGEST(); 


                }
                break;
            case 9 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:60: CHANGE
                {
                mCHANGE(); 


                }
                break;
            case 10 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:67: PCHANGE
                {
                mPCHANGE(); 


                }
                break;
            case 11 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:75: DIVIDE
                {
                mDIVIDE(); 


                }
                break;
            case 12 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:82: INTEGRATE
                {
                mINTEGRATE(); 


                }
                break;
            case 13 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:92: BAR
                {
                mBAR(); 


                }
                break;
            case 14 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:96: OTHERWISE
                {
                mOTHERWISE(); 


                }
                break;
            case 15 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:106: TO
                {
                mTO(); 


                }
                break;
            case 16 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:109: CREATE
                {
                mCREATE(); 


                }
                break;
            case 17 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:116: WITH
                {
                mWITH(); 


                }
                break;
            case 18 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:121: LSQUARE
                {
                mLSQUARE(); 


                }
                break;
            case 19 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:129: RSQUARE
                {
                mRSQUARE(); 


                }
                break;
            case 20 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:137: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 21 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:143: ABS
                {
                mABS(); 


                }
                break;
            case 22 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:147: ACOS
                {
                mACOS(); 


                }
                break;
            case 23 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:152: ASIN
                {
                mASIN(); 


                }
                break;
            case 24 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:157: ATAN
                {
                mATAN(); 


                }
                break;
            case 25 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:162: SIN
                {
                mSIN(); 


                }
                break;
            case 26 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:166: COS
                {
                mCOS(); 


                }
                break;
            case 27 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:170: TAN
                {
                mTAN(); 


                }
                break;
            case 28 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:174: EXP
                {
                mEXP(); 


                }
                break;
            case 29 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:178: LN
                {
                mLN(); 


                }
                break;
            case 30 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:181: LOGTEN
                {
                mLOGTEN(); 


                }
                break;
            case 31 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:188: RND
                {
                mRND(); 


                }
                break;
            case 32 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:192: SQRT
                {
                mSQRT(); 


                }
                break;
            case 33 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:197: MAX
                {
                mMAX(); 


                }
                break;
            case 34 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:201: MIN
                {
                mMIN(); 


                }
                break;
            case 35 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:205: DENSITYAT
                {
                mDENSITYAT(); 


                }
                break;
            case 36 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:215: DEPTHFORFI
                {
                mDEPTHFORFI(); 


                }
                break;
            case 37 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:226: DEPTHFORVI
                {
                mDEPTHFORVI(); 


                }
                break;
            case 38 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:237: FULLIRRADAT
                {
                mFULLIRRADAT(); 


                }
                break;
            case 39 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:249: VISIRRADAT
                {
                mVISIRRADAT(); 


                }
                break;
            case 40 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:260: SALINITYAT
                {
                mSALINITYAT(); 


                }
                break;
            case 41 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:271: TEMPAT
                {
                mTEMPAT(); 


                }
                break;
            case 42 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:278: UVIRRADAT
                {
                mUVIRRADAT(); 


                }
                break;
            case 43 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:288: VARHIST
                {
                mVARHIST(); 


                }
                break;
            case 44 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:296: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 45 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:302: EQUALS
                {
                mEQUALS(); 


                }
                break;
            case 46 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:309: PLUS
                {
                mPLUS(); 


                }
                break;
            case 47 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:314: MINUS
                {
                mMINUS(); 


                }
                break;
            case 48 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:320: MUL
                {
                mMUL(); 


                }
                break;
            case 49 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:324: DIV
                {
                mDIV(); 


                }
                break;
            case 50 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:328: POW
                {
                mPOW(); 


                }
                break;
            case 51 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:332: LBRACKET
                {
                mLBRACKET(); 


                }
                break;
            case 52 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:341: RBRACKET
                {
                mRBRACKET(); 


                }
                break;
            case 53 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:350: GREATEREQUALS
                {
                mGREATEREQUALS(); 


                }
                break;
            case 54 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:364: LESSEQUALS
                {
                mLESSEQUALS(); 


                }
                break;
            case 55 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:375: NEQUALS
                {
                mNEQUALS(); 


                }
                break;
            case 56 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:383: GREATERTHAN
                {
                mGREATERTHAN(); 


                }
                break;
            case 57 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:395: LESSTHAN
                {
                mLESSTHAN(); 


                }
                break;
            case 58 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:404: AND
                {
                mAND(); 


                }
                break;
            case 59 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:408: OR
                {
                mOR(); 


                }
                break;
            case 60 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:411: NOT
                {
                mNOT(); 


                }
                break;
            case 61 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:415: ALL
                {
                mALL(); 


                }
                break;
            case 62 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:419: SOME
                {
                mSOME(); 


                }
                break;
            case 63 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:424: NONE
                {
                mNONE(); 


                }
                break;
            case 64 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:429: VAVERAGE
                {
                mVAVERAGE(); 


                }
                break;
            case 65 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:438: VPRODUCT
                {
                mVPRODUCT(); 


                }
                break;
            case 66 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:447: VSUM
                {
                mVSUM(); 


                }
                break;
            case 67 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:452: VAR
                {
                mVAR(); 


                }
                break;
            case 68 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:456: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 69 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:464: IGNORE
                {
                mIGNORE(); 


                }
                break;
            case 70 :
                // C:\\Users\\David\\workspace\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:471: UNKNOWN
                {
                mUNKNOWN(); 


                }
                break;

        }

    }


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA15 dfa15 = new DFA15(this);
    static final String DFA10_eotS =
        "\2\uffff\1\3\2\uffff";
    static final String DFA10_eofS =
        "\5\uffff";
    static final String DFA10_minS =
        "\1\55\2\60\2\uffff";
    static final String DFA10_maxS =
        "\2\71\1\145\2\uffff";
    static final String DFA10_acceptS =
        "\3\uffff\1\1\1\2";
    static final String DFA10_specialS =
        "\5\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\1\2\uffff\12\2",
            "\12\2",
            "\12\2\13\uffff\1\4\37\uffff\1\4",
            "",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "134:1: FLOAT : ( ( MINUS )? ( DIGIT )+ ( '.' ( DIGIT )* ( EXPONENT )? )? | ( MINUS )? ( DIGIT )+ EXPONENT );";
        }
    }
    static final String DFA15_eotS =
        "\1\uffff\1\46\1\uffff\10\53\1\uffff\2\53\3\uffff\7\53\1\130\4\uffff"+
        "\1\135\3\uffff\1\142\1\145\1\53\5\uffff\1\150\1\53\1\uffff\1\53"+
        "\1\154\16\53\1\uffff\1\53\1\175\1\53\3\uffff\14\53\1\u008b\7\53"+
        "\17\uffff\1\53\2\uffff\3\53\1\uffff\1\u0098\2\53\1\u009b\2\53\1"+
        "\u009e\2\53\1\u00a1\6\53\1\uffff\1\53\1\u00a9\3\53\1\u00ad\1\u00ae"+
        "\1\53\1\u00b0\3\53\1\u00b4\1\uffff\1\53\1\u00b6\1\u00b7\4\53\1\u00bc"+
        "\3\53\1\u00c0\1\uffff\1\53\1\u00c2\1\uffff\2\53\1\uffff\2\53\1\uffff"+
        "\6\53\1\u00cd\1\uffff\1\u00ce\1\u00cf\1\u00d0\2\uffff\1\53\1\uffff"+
        "\1\u00d2\1\53\1\u00d4\1\uffff\1\53\2\uffff\4\53\1\uffff\1\u00da"+
        "\2\53\1\uffff\1\53\1\uffff\12\53\4\uffff\1\53\1\uffff\1\53\1\uffff"+
        "\1\u00ea\4\53\1\uffff\1\u00ef\2\53\1\u00f2\1\53\1\u00f4\1\u00f5"+
        "\2\53\1\u00f8\5\53\1\uffff\4\53\1\uffff\2\53\1\uffff\1\u0104\2\uffff"+
        "\1\u0105\1\u0106\1\uffff\3\53\1\u010a\3\53\1\u010e\3\53\3\uffff"+
        "\3\53\1\uffff\3\53\1\uffff\1\53\1\u011a\1\53\1\u011c\2\53\1\u011f"+
        "\3\53\1\u0123\1\uffff\1\53\1\uffff\1\u0125\1\u0126\1\uffff\1\u0127"+
        "\1\53\1\u0129\1\uffff\1\53\3\uffff\1\u012b\1\uffff\1\53\1\uffff"+
        "\1\u012d\1\uffff";
    static final String DFA15_eofS =
        "\u012e\uffff";
    static final String DFA15_minS =
        "\2\0\1\uffff\1\146\1\141\1\154\1\160\1\145\1\150\1\143\1\145\1\uffff"+
        "\1\162\1\151\3\uffff\1\142\1\141\1\156\1\141\1\165\1\141\1\126\1"+
        "\60\4\uffff\1\57\3\uffff\2\75\1\157\5\uffff\1\60\1\147\1\uffff\1"+
        "\145\1\60\1\156\1\155\1\163\1\160\1\164\1\154\1\144\1\141\1\145"+
        "\1\163\1\150\1\157\1\166\1\156\1\uffff\1\150\1\60\1\164\3\uffff"+
        "\1\163\1\157\1\151\1\141\1\144\1\154\1\145\1\156\1\162\1\154\2\155"+
        "\1\60\1\147\1\170\1\156\1\154\1\163\1\162\1\111\17\uffff\1\156\2"+
        "\uffff\2\145\1\156\1\uffff\1\60\1\160\1\145\1\60\1\141\1\145\1\60"+
        "\1\156\1\141\1\60\1\141\1\144\1\151\1\163\1\164\1\145\1\uffff\1"+
        "\150\1\60\1\163\2\156\2\60\1\162\1\60\1\164\1\151\1\145\1\60\1\uffff"+
        "\1\61\2\60\1\154\1\111\1\150\1\162\1\60\1\145\1\163\1\147\1\60\1"+
        "\uffff\1\145\1\60\1\uffff\1\153\1\141\1\uffff\1\147\1\164\1\uffff"+
        "\1\156\1\165\1\144\1\151\1\150\1\162\1\60\1\uffff\3\60\2\uffff\1"+
        "\141\1\uffff\1\60\1\156\1\60\1\uffff\1\60\2\uffff\1\111\1\162\1"+
        "\151\1\162\1\uffff\1\60\1\164\1\162\1\uffff\1\162\1\uffff\1\145"+
        "\1\163\2\145\1\147\1\143\1\145\1\164\1\106\1\167\4\uffff\1\147\1"+
        "\uffff\1\151\1\uffff\1\60\2\162\1\163\1\141\1\uffff\1\60\2\141\1"+
        "\60\1\145\2\60\1\145\1\164\1\60\1\171\1\157\1\151\1\145\1\164\1"+
        "\uffff\1\162\1\141\1\164\1\144\1\uffff\2\164\1\uffff\1\60\2\uffff"+
        "\2\60\1\uffff\1\101\1\162\1\163\1\60\1\171\1\141\1\144\1\60\1\101"+
        "\1\145\1\165\3\uffff\1\164\1\106\1\145\1\uffff\1\101\1\144\1\101"+
        "\1\uffff\1\164\1\60\1\162\1\60\2\111\1\60\1\164\1\101\1\164\1\60"+
        "\1\uffff\1\145\1\uffff\2\60\1\uffff\1\60\1\164\1\60\1\uffff\1\101"+
        "\3\uffff\1\60\1\uffff\1\164\1\uffff\1\60\1\uffff";
    static final String DFA15_maxS =
        "\2\uffff\1\uffff\1\156\1\157\1\170\1\160\1\156\2\162\1\151\1\uffff"+
        "\1\164\1\151\3\uffff\1\166\1\165\1\157\1\151\1\165\1\151\1\126\1"+
        "\71\4\uffff\1\57\3\uffff\1\75\1\76\1\157\5\uffff\1\172\1\164\1\uffff"+
        "\1\145\1\172\1\156\1\155\1\163\1\160\1\164\1\154\1\144\1\141\1\145"+
        "\1\163\1\150\1\157\1\166\1\160\1\uffff\1\150\1\172\1\164\3\uffff"+
        "\1\163\1\157\1\151\1\141\1\144\1\154\1\145\1\156\1\162\1\154\2\155"+
        "\1\172\1\147\1\170\1\156\1\154\1\163\1\162\1\111\17\uffff\1\164"+
        "\2\uffff\2\145\1\156\1\uffff\1\172\1\160\1\145\1\172\1\141\1\145"+
        "\1\172\1\156\1\141\1\172\1\141\1\144\1\151\1\163\1\164\1\145\1\uffff"+
        "\1\150\1\172\1\163\2\156\2\172\1\162\1\172\1\164\1\151\1\145\1\172"+
        "\1\uffff\1\61\2\172\1\154\1\111\1\150\1\162\1\172\1\145\1\163\1"+
        "\147\1\172\1\uffff\1\145\1\172\1\uffff\1\153\1\141\1\uffff\1\147"+
        "\1\164\1\uffff\1\156\1\165\1\144\1\151\1\150\1\162\1\172\1\uffff"+
        "\3\172\2\uffff\1\141\1\uffff\1\172\1\156\1\172\1\uffff\1\60\2\uffff"+
        "\1\111\1\162\1\151\1\162\1\uffff\1\172\1\164\1\162\1\uffff\1\162"+
        "\1\uffff\1\145\1\163\2\145\1\147\1\143\1\145\1\164\1\106\1\167\4"+
        "\uffff\1\147\1\uffff\1\151\1\uffff\1\172\2\162\1\163\1\141\1\uffff"+
        "\1\172\2\141\1\172\1\145\2\172\1\145\1\164\1\172\1\171\1\157\1\151"+
        "\1\145\1\164\1\uffff\1\162\1\141\1\164\1\144\1\uffff\2\164\1\uffff"+
        "\1\172\2\uffff\2\172\1\uffff\1\101\1\162\1\163\1\172\1\171\1\141"+
        "\1\144\1\172\1\101\1\145\1\165\3\uffff\1\164\1\126\1\145\1\uffff"+
        "\1\101\1\144\1\101\1\uffff\1\164\1\172\1\162\1\172\2\111\1\172\1"+
        "\164\1\101\1\164\1\172\1\uffff\1\145\1\uffff\2\172\1\uffff\1\172"+
        "\1\164\1\172\1\uffff\1\101\3\uffff\1\172\1\uffff\1\164\1\uffff\1"+
        "\172\1\uffff";
    static final String DFA15_acceptS =
        "\2\uffff\1\2\10\uffff\1\15\2\uffff\1\22\1\23\1\24\10\uffff\1\54"+
        "\1\55\1\56\1\60\1\uffff\1\62\1\63\1\64\3\uffff\1\103\1\105\1\106"+
        "\1\1\1\2\2\uffff\1\103\20\uffff\1\15\3\uffff\1\22\1\23\1\24\24\uffff"+
        "\1\54\1\57\1\55\1\56\1\60\1\104\1\61\1\62\1\63\1\64\1\65\1\70\1"+
        "\66\1\67\1\71\1\uffff\1\105\1\3\3\uffff\1\17\20\uffff\1\73\15\uffff"+
        "\1\35\14\uffff\1\33\2\uffff\1\34\2\uffff\1\37\2\uffff\1\32\7\uffff"+
        "\1\25\3\uffff\1\72\1\75\1\uffff\1\31\3\uffff\1\102\1\uffff\1\41"+
        "\1\42\4\uffff\1\74\3\uffff\1\4\1\uffff\1\5\12\uffff\1\21\1\26\1"+
        "\27\1\30\1\uffff\1\40\1\uffff\1\76\5\uffff\1\77\17\uffff\1\36\4"+
        "\uffff\1\10\2\uffff\1\6\1\uffff\1\11\1\20\2\uffff\1\13\13\uffff"+
        "\1\7\1\12\1\101\3\uffff\1\100\3\uffff\1\53\13\uffff\1\14\1\uffff"+
        "\1\43\2\uffff\1\16\3\uffff\1\52\1\uffff\1\44\1\45\1\50\1\uffff\1"+
        "\47\1\uffff\1\46\1\uffff\1\51";
    static final String DFA15_specialS =
        "\1\1\1\0\u012c\uffff}>";
    static final String[] DFA15_transitionS = {
            "\11\46\2\45\2\46\1\45\22\46\1\45\1\46\1\1\5\46\1\37\1\40\1\34"+
            "\1\33\1\20\1\30\1\46\1\35\12\31\1\2\1\46\1\42\1\32\1\41\2\46"+
            "\24\44\1\27\5\44\1\16\1\46\1\17\1\36\2\46\1\21\1\44\1\10\1\12"+
            "\1\5\1\25\2\44\1\3\2\44\1\23\1\24\1\43\1\14\1\11\1\44\1\7\1"+
            "\22\1\4\1\6\1\26\1\15\3\44\1\46\1\13\uff83\46",
            "\0\47",
            "",
            "\1\51\7\uffff\1\52",
            "\1\56\3\uffff\1\57\2\uffff\1\54\6\uffff\1\55",
            "\1\60\13\uffff\1\61",
            "\1\62",
            "\1\63\10\uffff\1\64",
            "\1\65\6\uffff\1\67\2\uffff\1\66",
            "\1\70\16\uffff\1\71",
            "\1\73\3\uffff\1\72",
            "",
            "\1\76\1\uffff\1\75",
            "\1\77",
            "",
            "",
            "",
            "\1\103\1\104\10\uffff\1\110\1\uffff\1\107\4\uffff\1\105\1\106"+
            "\1\uffff\1\111",
            "\1\114\7\uffff\1\112\5\uffff\1\115\1\uffff\1\113\3\uffff\1"+
            "\116",
            "\1\117\1\120",
            "\1\121\7\uffff\1\122",
            "\1\123",
            "\1\125\7\uffff\1\124",
            "\1\126",
            "\12\127",
            "",
            "",
            "",
            "",
            "\1\134",
            "",
            "",
            "",
            "\1\141",
            "\1\143\1\144",
            "\1\146",
            "",
            "",
            "",
            "",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\151\14\uffff\1\152",
            "",
            "\1\153",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172\1\uffff\1\173",
            "",
            "\1\174",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\176",
            "",
            "",
            "",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0094\5\uffff\1\u0093",
            "",
            "",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u0099",
            "\1\u009a",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u009c",
            "\1\u009d",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u009f",
            "\1\u00a0",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "",
            "\1\u00a8",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00af",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u00b5",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u00c1",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u00c3",
            "\1\u00c4",
            "",
            "\1\u00c5",
            "\1\u00c6",
            "",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "",
            "\1\u00d1",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00d3",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u00d5",
            "",
            "",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00db",
            "\1\u00dc",
            "",
            "\1\u00dd",
            "",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7",
            "",
            "",
            "",
            "",
            "\1\u00e8",
            "",
            "\1\u00e9",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00eb",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00f0",
            "\1\u00f1",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00f3",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00f6",
            "\1\u00f7",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00f9",
            "\1\u00fa",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "",
            "\1\u00fe",
            "\1\u00ff",
            "\1\u0100",
            "\1\u0101",
            "",
            "\1\u0102",
            "\1\u0103",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u0107",
            "\1\u0108",
            "\1\u0109",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u010f",
            "\1\u0110",
            "\1\u0111",
            "",
            "",
            "",
            "\1\u0112",
            "\1\u0113\17\uffff\1\u0114",
            "\1\u0115",
            "",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "",
            "\1\u0119",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u011b",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u011d",
            "\1\u011e",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u0124",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u0128",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u012a",
            "",
            "",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u012c",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( RULENAME | COLON | IF | THEN | ELSE | UPTAKE | RELEASE | INGEST | CHANGE | PCHANGE | DIVIDE | INTEGRATE | BAR | OTHERWISE | TO | CREATE | WITH | LSQUARE | RSQUARE | COMMA | ABS | ACOS | ASIN | ATAN | SIN | COS | TAN | EXP | LN | LOGTEN | RND | SQRT | MAX | MIN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | VISIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | VARHIST | FLOAT | EQUALS | PLUS | MINUS | MUL | DIV | POW | LBRACKET | RBRACKET | GREATEREQUALS | LESSEQUALS | NEQUALS | GREATERTHAN | LESSTHAN | AND | OR | NOT | ALL | SOME | NONE | VAVERAGE | VPRODUCT | VSUM | VAR | COMMENT | IGNORE | UNKNOWN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_1 = input.LA(1);

                        s = -1;
                        if ( ((LA15_1 >= '\u0000' && LA15_1 <= '\uFFFF')) ) {s = 39;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA15_0 = input.LA(1);

                        s = -1;
                        if ( (LA15_0=='\"') ) {s = 1;}

                        else if ( (LA15_0==':') ) {s = 2;}

                        else if ( (LA15_0=='i') ) {s = 3;}

                        else if ( (LA15_0=='t') ) {s = 4;}

                        else if ( (LA15_0=='e') ) {s = 5;}

                        else if ( (LA15_0=='u') ) {s = 6;}

                        else if ( (LA15_0=='r') ) {s = 7;}

                        else if ( (LA15_0=='c') ) {s = 8;}

                        else if ( (LA15_0=='p') ) {s = 9;}

                        else if ( (LA15_0=='d') ) {s = 10;}

                        else if ( (LA15_0=='|') ) {s = 11;}

                        else if ( (LA15_0=='o') ) {s = 12;}

                        else if ( (LA15_0=='w') ) {s = 13;}

                        else if ( (LA15_0=='[') ) {s = 14;}

                        else if ( (LA15_0==']') ) {s = 15;}

                        else if ( (LA15_0==',') ) {s = 16;}

                        else if ( (LA15_0=='a') ) {s = 17;}

                        else if ( (LA15_0=='s') ) {s = 18;}

                        else if ( (LA15_0=='l') ) {s = 19;}

                        else if ( (LA15_0=='m') ) {s = 20;}

                        else if ( (LA15_0=='f') ) {s = 21;}

                        else if ( (LA15_0=='v') ) {s = 22;}

                        else if ( (LA15_0=='U') ) {s = 23;}

                        else if ( (LA15_0=='-') ) {s = 24;}

                        else if ( ((LA15_0 >= '0' && LA15_0 <= '9')) ) {s = 25;}

                        else if ( (LA15_0=='=') ) {s = 26;}

                        else if ( (LA15_0=='+') ) {s = 27;}

                        else if ( (LA15_0=='*') ) {s = 28;}

                        else if ( (LA15_0=='/') ) {s = 29;}

                        else if ( (LA15_0=='^') ) {s = 30;}

                        else if ( (LA15_0=='(') ) {s = 31;}

                        else if ( (LA15_0==')') ) {s = 32;}

                        else if ( (LA15_0=='>') ) {s = 33;}

                        else if ( (LA15_0=='<') ) {s = 34;}

                        else if ( (LA15_0=='n') ) {s = 35;}

                        else if ( ((LA15_0 >= 'A' && LA15_0 <= 'T')||(LA15_0 >= 'V' && LA15_0 <= 'Z')||LA15_0=='b'||(LA15_0 >= 'g' && LA15_0 <= 'h')||(LA15_0 >= 'j' && LA15_0 <= 'k')||LA15_0=='q'||(LA15_0 >= 'x' && LA15_0 <= 'z')) ) {s = 36;}

                        else if ( ((LA15_0 >= '\t' && LA15_0 <= '\n')||LA15_0=='\r'||LA15_0==' ') ) {s = 37;}

                        else if ( ((LA15_0 >= '\u0000' && LA15_0 <= '\b')||(LA15_0 >= '\u000B' && LA15_0 <= '\f')||(LA15_0 >= '\u000E' && LA15_0 <= '\u001F')||LA15_0=='!'||(LA15_0 >= '#' && LA15_0 <= '\'')||LA15_0=='.'||LA15_0==';'||(LA15_0 >= '?' && LA15_0 <= '@')||LA15_0=='\\'||(LA15_0 >= '_' && LA15_0 <= '`')||LA15_0=='{'||(LA15_0 >= '}' && LA15_0 <= '\uFFFF')) ) {s = 38;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}