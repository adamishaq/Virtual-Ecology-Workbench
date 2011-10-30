// $ANTLR 3.4 E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g 2011-10-30 19:10:35

package VEW.XMLCompiler.ANTLR.output;


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
    public String getGrammarFileName() { return "E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g"; }

    // $ANTLR start "RULENAME"
    public final void mRULENAME() throws RecognitionException {
        try {
            int _type = RULENAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:10: ( ( '\"' ) ( LETTER | DIGIT | '_' | IGNORE )* ( '\"' ) )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:12: ( '\"' ) ( LETTER | DIGIT | '_' | IGNORE )* ( '\"' )
            {
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:12: ( '\"' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:13: '\"'
            {
            match('\"'); 

            }


            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:17: ( LETTER | DIGIT | '_' | IGNORE )*
            loop1:
            do {
                int alt1=5;
                switch ( input.LA(1) ) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt1=1;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt1=2;
                    }
                    break;
                case '_':
                    {
                    alt1=3;
                    }
                    break;
                case '\t':
                case '\r':
                case ' ':
                    {
                    alt1=4;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:18: LETTER
            	    {
            	    mLETTER(); 


            	    }
            	    break;
            	case 2 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:25: DIGIT
            	    {
            	    mDIGIT(); 


            	    }
            	    break;
            	case 3 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:31: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 4 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:35: IGNORE
            	    {
            	    mIGNORE(); 


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:43: ( '\"' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:25:44: '\"'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:26:10: ( ( ':' ) ( IGNORE )* ( NEWLINE )? )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:26:12: ( ':' ) ( IGNORE )* ( NEWLINE )?
            {
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:26:12: ( ':' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:26:13: ':'
            {
            match(':'); 

            }


            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:26:17: ( IGNORE )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\t'||LA2_0=='\r'||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:26:18: IGNORE
            	    {
            	    mIGNORE(); 


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:26:26: ( NEWLINE )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\n'||LA3_0=='/') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:26:27: NEWLINE
                    {
                    mNEWLINE(); 


                    }
                    break;

            }


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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:30:6: ( 'if' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:30:8: 'if'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:31:6: ( 'then' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:31:8: 'then'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:32:6: ( 'else' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:32:8: 'else'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:35:12: ( 'uptake' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:35:14: 'uptake'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:36:12: ( 'release' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:36:14: 'release'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:37:12: ( 'ingest' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:37:14: 'ingest'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:38:12: ( 'change' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:38:14: 'change'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:39:12: ( 'pchange' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:39:14: 'pchange'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:40:11: ( 'divide' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:40:13: 'divide'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:41:12: ( 'integrate' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:41:14: 'integrate'
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

    // $ANTLR start "CREATE"
    public final void mCREATE() throws RecognitionException {
        try {
            int _type = CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:44:8: ( 'create' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:44:10: 'create'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:45:6: ( 'with' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:45:8: 'with'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:46:9: ( '[' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:46:11: '['
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:47:9: ( ']' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:47:11: ']'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:48:8: ( ',' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:48:10: ','
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:51:8: ( 'abs' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:51:10: 'abs'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:52:8: ( 'acos' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:52:10: 'acos'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:53:8: ( 'asin' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:53:10: 'asin'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:54:8: ( 'atan' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:54:10: 'atan'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:55:8: ( 'sin' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:55:10: 'sin'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:56:8: ( 'cos' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:56:10: 'cos'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:57:8: ( 'tan' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:57:10: 'tan'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:58:8: ( 'exp' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:58:10: 'exp'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:59:8: ( 'ln' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:59:10: 'ln'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:60:8: ( 'log10' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:60:10: 'log10'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:61:8: ( 'rnd' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:61:10: 'rnd'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:62:8: ( 'sqrt' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:62:10: 'sqrt'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:63:8: ( 'max' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:63:10: 'max'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:64:8: ( 'min' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:64:10: 'min'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:67:13: ( 'densityAt' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:67:15: 'densityAt'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:68:13: ( 'depthForFI' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:68:15: 'depthForFI'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:69:13: ( 'depthForVI' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:69:15: 'depthForVI'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:70:13: ( 'fullIrradAt' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:70:15: 'fullIrradAt'
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

    // $ANTLR start "SALINITYAT"
    public final void mSALINITYAT() throws RecognitionException {
        try {
            int _type = SALINITYAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:71:13: ( 'salinityAt' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:71:15: 'salinityAt'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:72:13: ( 'temperatureAt' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:72:15: 'temperatureAt'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:73:13: ( 'UVIrradAt' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:73:15: 'UVIrradAt'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:74:13: ( 'varhist' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:74:15: 'varhist'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:77:16: ( ( '0' .. '9' ) )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:79:5: ( ( DIGIT )+ '.' ( DIGIT )* | '.' ( DIGIT )+ | ( DIGIT )+ )
            int alt8=3;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:79:9: ( DIGIT )+ '.' ( DIGIT )*
                    {
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:79:9: ( DIGIT )+
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
                    	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
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


                    match('.'); 

                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:79:22: ( DIGIT )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
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


                    }
                    break;
                case 2 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:80:9: '.' ( DIGIT )+
                    {
                    match('.'); 

                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:80:13: ( DIGIT )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
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
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);


                    }
                    break;
                case 3 :
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:81:9: ( DIGIT )+
                    {
                    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:81:9: ( DIGIT )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
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
                    	    if ( cnt7 >= 1 ) break loop7;
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);


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

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:84:10: ( '=' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:84:12: '='
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:85:10: ( '+' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:85:12: '+'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:86:10: ( '-' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:86:12: '-'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:87:10: ( '*' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:87:12: '*'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:88:10: ( '/' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:88:12: '/'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:89:10: ( '^' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:89:12: '^'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:90:10: ( '(' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:90:12: '('
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:91:10: ( ')' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:91:12: ')'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:94:15: ( '>=' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:94:17: '>='
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:95:15: ( '<=' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:95:17: '<='
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:96:15: ( '<>' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:96:17: '<>'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:97:15: ( '>' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:97:17: '>'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:98:15: ( '<' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:98:17: '<'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:99:15: ( 'and' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:99:18: 'and'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:100:15: ( 'or' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:100:17: 'or'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:101:15: ( 'not' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:101:17: 'not'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:105:7: ( 'all' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:105:9: 'all'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:106:7: ( 'some' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:106:9: 'some'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:107:7: ( 'none' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:107:9: 'none'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:108:10: ( 'average' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:108:12: 'average'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:109:10: ( 'product' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:109:12: 'product'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:110:7: ( 'sum' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:110:9: 'sum'
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:113:17: ( ( 'a' .. 'z' | 'A' .. 'Z' ) )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:114:5: ( ( LETTER ) ( LETTER | DIGIT | '_' )* )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:114:7: ( LETTER ) ( LETTER | DIGIT | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:114:15: ( LETTER | DIGIT | '_' )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= '0' && LA9_0 <= '9')||(LA9_0 >= 'A' && LA9_0 <= 'Z')||LA9_0=='_'||(LA9_0 >= 'a' && LA9_0 <= 'z')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
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
            	    break loop9;
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:117:18: ( ( '//' ) (~ '\\n' )* )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:117:20: ( '//' ) (~ '\\n' )*
            {
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:117:20: ( '//' )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:117:21: '//'
            {
            match("//"); 



            }


            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:117:26: (~ '\\n' )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0 >= '\u0000' && LA10_0 <= '\t')||(LA10_0 >= '\u000B' && LA10_0 <= '\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:
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
            	    break loop10;
                }
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:9: ( ( COMMENT | ( ( '\\n' ) ( IGNORE )* ) )+ )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:11: ( COMMENT | ( ( '\\n' ) ( IGNORE )* ) )+
            {
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:11: ( COMMENT | ( ( '\\n' ) ( IGNORE )* ) )+
            int cnt12=0;
            loop12:
            do {
                int alt12=3;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='/') ) {
                    alt12=1;
                }
                else if ( (LA12_0=='\n') ) {
                    alt12=2;
                }


                switch (alt12) {
            	case 1 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:12: COMMENT
            	    {
            	    mCOMMENT(); 


            	    }
            	    break;
            	case 2 :
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:20: ( ( '\\n' ) ( IGNORE )* )
            	    {
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:20: ( ( '\\n' ) ( IGNORE )* )
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:21: ( '\\n' ) ( IGNORE )*
            	    {
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:21: ( '\\n' )
            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:22: '\\n'
            	    {
            	    match('\n'); 

            	    }


            	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:27: ( IGNORE )*
            	    loop11:
            	    do {
            	        int alt11=2;
            	        int LA11_0 = input.LA(1);

            	        if ( (LA11_0=='\t'||LA11_0=='\r'||LA11_0==' ') ) {
            	            alt11=1;
            	        }


            	        switch (alt11) {
            	    	case 1 :
            	    	    // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:120:28: IGNORE
            	    	    {
            	    	    mIGNORE(); 


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop11;
            	        }
            	    } while (true);


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

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "IGNORE"
    public final void mIGNORE() throws RecognitionException {
        try {
            int _type = IGNORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:121:9: ( ( ' ' | '\\t' | '\\r' ) )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:121:11: ( ' ' | '\\t' | '\\r' )
            {
            if ( input.LA(1)=='\t'||input.LA(1)=='\r'||input.LA(1)==' ' ) {
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
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:124:9: ( ( . ) )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:124:11: ( . )
            {
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:124:11: ( . )
            // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:124:12: .
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
        // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:8: ( RULENAME | COLON | IF | THEN | ELSE | UPTAKE | RELEASE | INGEST | CHANGE | PCHANGE | DIVIDE | INTEGRATE | CREATE | WITH | LSQUARE | RSQUARE | COMMA | ABS | ACOS | ASIN | ATAN | SIN | COS | TAN | EXP | LN | LOGTEN | RND | SQRT | MAX | MIN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | VARHIST | FLOAT | EQUALS | PLUS | MINUS | MUL | DIV | POW | LBRACKET | RBRACKET | GREATEREQUALS | LESSEQUALS | NEQUALS | GREATERTHAN | LESSTHAN | AND | OR | NOT | ALL | SOME | NONE | VAVERAGE | VPRODUCT | VSUM | VAR | NEWLINE | IGNORE | UNKNOWN )
        int alt13=66;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:10: RULENAME
                {
                mRULENAME(); 


                }
                break;
            case 2 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:19: COLON
                {
                mCOLON(); 


                }
                break;
            case 3 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:25: IF
                {
                mIF(); 


                }
                break;
            case 4 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:28: THEN
                {
                mTHEN(); 


                }
                break;
            case 5 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:33: ELSE
                {
                mELSE(); 


                }
                break;
            case 6 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:38: UPTAKE
                {
                mUPTAKE(); 


                }
                break;
            case 7 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:45: RELEASE
                {
                mRELEASE(); 


                }
                break;
            case 8 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:53: INGEST
                {
                mINGEST(); 


                }
                break;
            case 9 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:60: CHANGE
                {
                mCHANGE(); 


                }
                break;
            case 10 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:67: PCHANGE
                {
                mPCHANGE(); 


                }
                break;
            case 11 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:75: DIVIDE
                {
                mDIVIDE(); 


                }
                break;
            case 12 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:82: INTEGRATE
                {
                mINTEGRATE(); 


                }
                break;
            case 13 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:92: CREATE
                {
                mCREATE(); 


                }
                break;
            case 14 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:99: WITH
                {
                mWITH(); 


                }
                break;
            case 15 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:104: LSQUARE
                {
                mLSQUARE(); 


                }
                break;
            case 16 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:112: RSQUARE
                {
                mRSQUARE(); 


                }
                break;
            case 17 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:120: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 18 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:126: ABS
                {
                mABS(); 


                }
                break;
            case 19 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:130: ACOS
                {
                mACOS(); 


                }
                break;
            case 20 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:135: ASIN
                {
                mASIN(); 


                }
                break;
            case 21 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:140: ATAN
                {
                mATAN(); 


                }
                break;
            case 22 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:145: SIN
                {
                mSIN(); 


                }
                break;
            case 23 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:149: COS
                {
                mCOS(); 


                }
                break;
            case 24 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:153: TAN
                {
                mTAN(); 


                }
                break;
            case 25 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:157: EXP
                {
                mEXP(); 


                }
                break;
            case 26 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:161: LN
                {
                mLN(); 


                }
                break;
            case 27 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:164: LOGTEN
                {
                mLOGTEN(); 


                }
                break;
            case 28 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:171: RND
                {
                mRND(); 


                }
                break;
            case 29 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:175: SQRT
                {
                mSQRT(); 


                }
                break;
            case 30 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:180: MAX
                {
                mMAX(); 


                }
                break;
            case 31 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:184: MIN
                {
                mMIN(); 


                }
                break;
            case 32 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:188: DENSITYAT
                {
                mDENSITYAT(); 


                }
                break;
            case 33 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:198: DEPTHFORFI
                {
                mDEPTHFORFI(); 


                }
                break;
            case 34 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:209: DEPTHFORVI
                {
                mDEPTHFORVI(); 


                }
                break;
            case 35 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:220: FULLIRRADAT
                {
                mFULLIRRADAT(); 


                }
                break;
            case 36 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:232: SALINITYAT
                {
                mSALINITYAT(); 


                }
                break;
            case 37 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:243: TEMPAT
                {
                mTEMPAT(); 


                }
                break;
            case 38 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:250: UVIRRADAT
                {
                mUVIRRADAT(); 


                }
                break;
            case 39 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:260: VARHIST
                {
                mVARHIST(); 


                }
                break;
            case 40 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:268: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 41 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:274: EQUALS
                {
                mEQUALS(); 


                }
                break;
            case 42 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:281: PLUS
                {
                mPLUS(); 


                }
                break;
            case 43 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:286: MINUS
                {
                mMINUS(); 


                }
                break;
            case 44 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:292: MUL
                {
                mMUL(); 


                }
                break;
            case 45 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:296: DIV
                {
                mDIV(); 


                }
                break;
            case 46 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:300: POW
                {
                mPOW(); 


                }
                break;
            case 47 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:304: LBRACKET
                {
                mLBRACKET(); 


                }
                break;
            case 48 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:313: RBRACKET
                {
                mRBRACKET(); 


                }
                break;
            case 49 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:322: GREATEREQUALS
                {
                mGREATEREQUALS(); 


                }
                break;
            case 50 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:336: LESSEQUALS
                {
                mLESSEQUALS(); 


                }
                break;
            case 51 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:347: NEQUALS
                {
                mNEQUALS(); 


                }
                break;
            case 52 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:355: GREATERTHAN
                {
                mGREATERTHAN(); 


                }
                break;
            case 53 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:367: LESSTHAN
                {
                mLESSTHAN(); 


                }
                break;
            case 54 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:376: AND
                {
                mAND(); 


                }
                break;
            case 55 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:380: OR
                {
                mOR(); 


                }
                break;
            case 56 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:383: NOT
                {
                mNOT(); 


                }
                break;
            case 57 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:387: ALL
                {
                mALL(); 


                }
                break;
            case 58 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:391: SOME
                {
                mSOME(); 


                }
                break;
            case 59 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:396: NONE
                {
                mNONE(); 


                }
                break;
            case 60 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:401: VAVERAGE
                {
                mVAVERAGE(); 


                }
                break;
            case 61 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:410: VPRODUCT
                {
                mVPRODUCT(); 


                }
                break;
            case 62 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:419: VSUM
                {
                mVSUM(); 


                }
                break;
            case 63 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:424: VAR
                {
                mVAR(); 


                }
                break;
            case 64 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:428: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 65 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:436: IGNORE
                {
                mIGNORE(); 


                }
                break;
            case 66 :
                // E:\\Flash backup\\Programming\\workspace\\git\\Virtual-Ecology-Workbench\\src\\VEW\\XMLCompiler\\ANTLR\\BACON.g:1:443: UNKNOWN
                {
                mUNKNOWN(); 


                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA8_eotS =
        "\1\uffff\1\4\3\uffff";
    static final String DFA8_eofS =
        "\5\uffff";
    static final String DFA8_minS =
        "\2\56\3\uffff";
    static final String DFA8_maxS =
        "\2\71\3\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA8_specialS =
        "\5\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "78:1: FLOAT : ( ( DIGIT )+ '.' ( DIGIT )* | '.' ( DIGIT )+ | ( DIGIT )+ );";
        }
    }
    static final String DFA13_eotS =
        "\1\uffff\1\47\1\uffff\11\54\3\uffff\7\54\1\uffff\1\47\4\uffff\1"+
        "\131\3\uffff\1\136\1\141\2\54\6\uffff\1\145\1\54\1\uffff\20\54\3"+
        "\uffff\14\54\1\u0085\6\54\17\uffff\1\u008c\1\54\2\uffff\3\54\1\u0092"+
        "\2\54\1\u0095\2\54\1\u0098\2\54\1\u009b\6\54\1\u00a2\3\54\1\u00a6"+
        "\1\u00a7\1\54\1\u00a9\3\54\1\u00ad\1\uffff\1\54\1\u00af\1\u00b0"+
        "\3\54\1\uffff\1\u00b4\3\54\1\u00b8\1\uffff\1\54\1\u00ba\1\uffff"+
        "\2\54\1\uffff\2\54\1\uffff\5\54\1\u00c4\1\uffff\1\u00c5\1\u00c6"+
        "\1\u00c7\2\uffff\1\54\1\uffff\1\u00c9\1\54\1\u00cb\1\uffff\1\54"+
        "\2\uffff\3\54\1\uffff\1\u00d0\2\54\1\uffff\1\54\1\uffff\11\54\4"+
        "\uffff\1\54\1\uffff\1\54\1\uffff\1\u00df\3\54\1\uffff\1\u00e3\2"+
        "\54\1\u00e6\1\54\1\u00e8\1\u00e9\2\54\1\u00ec\4\54\1\uffff\3\54"+
        "\1\uffff\2\54\1\uffff\1\u00f6\2\uffff\1\u00f7\1\u00f8\1\uffff\2"+
        "\54\1\u00fb\3\54\1\u00ff\2\54\3\uffff\2\54\1\uffff\3\54\1\uffff"+
        "\1\u0108\1\54\1\u010a\4\54\1\u010f\1\uffff\1\54\1\uffff\1\u0111"+
        "\1\u0112\1\u0113\1\54\1\uffff\1\54\3\uffff\1\u0116\1\54\1\uffff"+
        "\1\u0118\1\uffff";
    static final String DFA13_eofS =
        "\u0119\uffff";
    static final String DFA13_minS =
        "\1\0\1\11\1\uffff\1\146\1\141\1\154\1\160\1\145\1\150\1\143\1\145"+
        "\1\151\3\uffff\1\142\1\141\1\156\1\141\1\165\1\126\1\141\1\uffff"+
        "\1\60\4\uffff\1\57\3\uffff\2\75\1\162\1\157\6\uffff\1\60\1\147\1"+
        "\uffff\1\145\1\156\1\155\1\163\1\160\1\164\1\154\1\144\1\141\1\145"+
        "\1\163\1\150\1\157\1\166\1\156\1\164\3\uffff\1\163\1\157\1\151\1"+
        "\141\1\144\1\154\1\145\1\156\1\162\1\154\2\155\1\60\1\147\1\170"+
        "\1\156\1\154\1\111\1\162\17\uffff\1\60\1\156\2\uffff\2\145\1\156"+
        "\1\60\1\160\1\145\1\60\1\141\1\145\1\60\1\156\1\141\1\60\1\141\1"+
        "\144\1\151\1\163\1\164\1\150\1\60\1\163\2\156\2\60\1\162\1\60\1"+
        "\164\1\151\1\145\1\60\1\uffff\1\61\2\60\1\154\1\162\1\150\1\uffff"+
        "\1\60\1\145\1\163\1\147\1\60\1\uffff\1\145\1\60\1\uffff\1\153\1"+
        "\141\1\uffff\1\147\1\164\1\uffff\1\156\1\165\1\144\1\151\1\150\1"+
        "\60\1\uffff\3\60\2\uffff\1\141\1\uffff\1\60\1\156\1\60\1\uffff\1"+
        "\60\2\uffff\1\111\1\162\1\151\1\uffff\1\60\1\164\1\162\1\uffff\1"+
        "\162\1\uffff\1\145\1\163\2\145\1\147\1\143\1\145\1\164\1\106\4\uffff"+
        "\1\147\1\uffff\1\151\1\uffff\1\60\1\162\1\141\1\163\1\uffff\1\60"+
        "\2\141\1\60\1\145\2\60\1\145\1\164\1\60\1\171\1\157\1\145\1\164"+
        "\1\uffff\1\162\1\144\1\164\1\uffff\2\164\1\uffff\1\60\2\uffff\2"+
        "\60\1\uffff\1\101\1\162\1\60\1\171\1\141\1\101\1\60\1\145\1\165"+
        "\3\uffff\1\164\1\106\1\uffff\1\101\1\144\1\164\1\uffff\1\60\1\162"+
        "\1\60\2\111\1\164\1\101\1\60\1\uffff\1\145\1\uffff\3\60\1\164\1"+
        "\uffff\1\101\3\uffff\1\60\1\164\1\uffff\1\60\1\uffff";
    static final String DFA13_maxS =
        "\1\uffff\1\172\1\uffff\1\156\1\150\1\170\1\160\1\156\2\162\2\151"+
        "\3\uffff\1\166\1\165\1\157\1\151\1\165\1\126\1\141\1\uffff\1\71"+
        "\4\uffff\1\57\3\uffff\1\75\1\76\1\162\1\157\6\uffff\1\172\1\164"+
        "\1\uffff\1\145\1\156\1\155\1\163\1\160\1\164\1\154\1\144\1\141\1"+
        "\145\1\163\1\150\1\157\1\166\1\160\1\164\3\uffff\1\163\1\157\1\151"+
        "\1\141\1\144\1\154\1\145\1\156\1\162\1\154\2\155\1\172\1\147\1\170"+
        "\1\156\1\154\1\111\1\162\17\uffff\1\172\1\164\2\uffff\2\145\1\156"+
        "\1\172\1\160\1\145\1\172\1\141\1\145\1\172\1\156\1\141\1\172\1\141"+
        "\1\144\1\151\1\163\1\164\1\150\1\172\1\163\2\156\2\172\1\162\1\172"+
        "\1\164\1\151\1\145\1\172\1\uffff\1\61\2\172\1\154\1\162\1\150\1"+
        "\uffff\1\172\1\145\1\163\1\147\1\172\1\uffff\1\145\1\172\1\uffff"+
        "\1\153\1\141\1\uffff\1\147\1\164\1\uffff\1\156\1\165\1\144\1\151"+
        "\1\150\1\172\1\uffff\3\172\2\uffff\1\141\1\uffff\1\172\1\156\1\172"+
        "\1\uffff\1\60\2\uffff\1\111\1\162\1\151\1\uffff\1\172\1\164\1\162"+
        "\1\uffff\1\162\1\uffff\1\145\1\163\2\145\1\147\1\143\1\145\1\164"+
        "\1\106\4\uffff\1\147\1\uffff\1\151\1\uffff\1\172\1\162\1\141\1\163"+
        "\1\uffff\1\172\2\141\1\172\1\145\2\172\1\145\1\164\1\172\1\171\1"+
        "\157\1\145\1\164\1\uffff\1\162\1\144\1\164\1\uffff\2\164\1\uffff"+
        "\1\172\2\uffff\2\172\1\uffff\1\101\1\162\1\172\1\171\1\141\1\101"+
        "\1\172\1\145\1\165\3\uffff\1\164\1\126\1\uffff\1\101\1\144\1\164"+
        "\1\uffff\1\172\1\162\1\172\2\111\1\164\1\101\1\172\1\uffff\1\145"+
        "\1\uffff\3\172\1\164\1\uffff\1\101\3\uffff\1\172\1\164\1\uffff\1"+
        "\172\1\uffff";
    static final String DFA13_acceptS =
        "\2\uffff\1\2\11\uffff\1\17\1\20\1\21\7\uffff\1\50\1\uffff\1\51\1"+
        "\52\1\53\1\54\1\uffff\1\56\1\57\1\60\4\uffff\1\77\1\100\1\101\1"+
        "\102\1\1\1\2\2\uffff\1\77\20\uffff\1\17\1\20\1\21\23\uffff\1\50"+
        "\1\51\1\52\1\53\1\54\1\100\1\55\1\56\1\57\1\60\1\61\1\64\1\62\1"+
        "\63\1\65\2\uffff\1\101\1\3\37\uffff\1\32\6\uffff\1\67\5\uffff\1"+
        "\30\2\uffff\1\31\2\uffff\1\34\2\uffff\1\27\6\uffff\1\22\3\uffff"+
        "\1\66\1\71\1\uffff\1\26\3\uffff\1\76\1\uffff\1\36\1\37\3\uffff\1"+
        "\70\3\uffff\1\4\1\uffff\1\5\11\uffff\1\16\1\23\1\24\1\25\1\uffff"+
        "\1\35\1\uffff\1\72\4\uffff\1\73\16\uffff\1\33\3\uffff\1\10\2\uffff"+
        "\1\6\1\uffff\1\11\1\15\2\uffff\1\13\11\uffff\1\7\1\12\1\75\2\uffff"+
        "\1\74\3\uffff\1\47\10\uffff\1\14\1\uffff\1\40\4\uffff\1\46\1\uffff"+
        "\1\41\1\42\1\44\2\uffff\1\43\1\uffff\1\45";
    static final String DFA13_specialS =
        "\1\0\u0118\uffff}>";
    static final String[] DFA13_transitionS = {
            "\11\47\1\46\1\45\2\47\1\46\22\47\1\46\1\47\1\1\5\47\1\36\1\37"+
            "\1\33\1\31\1\16\1\32\1\27\1\34\12\26\1\2\1\47\1\41\1\30\1\40"+
            "\2\47\24\44\1\24\5\44\1\14\1\47\1\15\1\35\2\47\1\17\1\44\1\10"+
            "\1\12\1\5\1\23\2\44\1\3\2\44\1\21\1\22\1\43\1\42\1\11\1\44\1"+
            "\7\1\20\1\4\1\6\1\25\1\13\3\44\uff85\47",
            "\1\50\3\uffff\1\50\22\uffff\1\50\1\uffff\1\50\15\uffff\12\50"+
            "\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\52\7\uffff\1\53",
            "\1\56\3\uffff\1\57\2\uffff\1\55",
            "\1\60\13\uffff\1\61",
            "\1\62",
            "\1\63\10\uffff\1\64",
            "\1\65\6\uffff\1\67\2\uffff\1\66",
            "\1\70\16\uffff\1\71",
            "\1\73\3\uffff\1\72",
            "\1\74",
            "",
            "",
            "",
            "\1\100\1\101\10\uffff\1\105\1\uffff\1\104\4\uffff\1\102\1\103"+
            "\1\uffff\1\106",
            "\1\111\7\uffff\1\107\5\uffff\1\112\1\uffff\1\110\3\uffff\1"+
            "\113",
            "\1\114\1\115",
            "\1\116\7\uffff\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "",
            "\12\123",
            "",
            "",
            "",
            "",
            "\1\130",
            "",
            "",
            "",
            "\1\135",
            "\1\137\1\140",
            "\1\142",
            "\1\143",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\146\14\uffff\1\147",
            "",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166\1\uffff\1\167",
            "\1\170",
            "",
            "",
            "",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
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
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u008e\5\uffff\1\u008d",
            "",
            "",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0093",
            "\1\u0094",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0096",
            "\1\u0097",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0099",
            "\1\u009a",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00a8",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00ae",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00b9",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00bb",
            "\1\u00bc",
            "",
            "\1\u00bd",
            "\1\u00be",
            "",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\1\u00c8",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00ca",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00cc",
            "",
            "",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00d1",
            "\1\u00d2",
            "",
            "\1\u00d3",
            "",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "",
            "",
            "",
            "",
            "\1\u00dd",
            "",
            "\1\u00de",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00e4",
            "\1\u00e5",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00e7",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00ea",
            "\1\u00eb",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00ed",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3",
            "",
            "\1\u00f4",
            "\1\u00f5",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00f9",
            "\1\u00fa",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00fe",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0100",
            "\1\u0101",
            "",
            "",
            "",
            "\1\u0102",
            "\1\u0103\17\uffff\1\u0104",
            "",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0109",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u0110",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0114",
            "",
            "\1\u0115",
            "",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0117",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( RULENAME | COLON | IF | THEN | ELSE | UPTAKE | RELEASE | INGEST | CHANGE | PCHANGE | DIVIDE | INTEGRATE | CREATE | WITH | LSQUARE | RSQUARE | COMMA | ABS | ACOS | ASIN | ATAN | SIN | COS | TAN | EXP | LN | LOGTEN | RND | SQRT | MAX | MIN | DENSITYAT | DEPTHFORFI | DEPTHFORVI | FULLIRRADAT | SALINITYAT | TEMPAT | UVIRRADAT | VARHIST | FLOAT | EQUALS | PLUS | MINUS | MUL | DIV | POW | LBRACKET | RBRACKET | GREATEREQUALS | LESSEQUALS | NEQUALS | GREATERTHAN | LESSTHAN | AND | OR | NOT | ALL | SOME | NONE | VAVERAGE | VPRODUCT | VSUM | VAR | NEWLINE | IGNORE | UNKNOWN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_0 = input.LA(1);

                        s = -1;
                        if ( (LA13_0=='\"') ) {s = 1;}

                        else if ( (LA13_0==':') ) {s = 2;}

                        else if ( (LA13_0=='i') ) {s = 3;}

                        else if ( (LA13_0=='t') ) {s = 4;}

                        else if ( (LA13_0=='e') ) {s = 5;}

                        else if ( (LA13_0=='u') ) {s = 6;}

                        else if ( (LA13_0=='r') ) {s = 7;}

                        else if ( (LA13_0=='c') ) {s = 8;}

                        else if ( (LA13_0=='p') ) {s = 9;}

                        else if ( (LA13_0=='d') ) {s = 10;}

                        else if ( (LA13_0=='w') ) {s = 11;}

                        else if ( (LA13_0=='[') ) {s = 12;}

                        else if ( (LA13_0==']') ) {s = 13;}

                        else if ( (LA13_0==',') ) {s = 14;}

                        else if ( (LA13_0=='a') ) {s = 15;}

                        else if ( (LA13_0=='s') ) {s = 16;}

                        else if ( (LA13_0=='l') ) {s = 17;}

                        else if ( (LA13_0=='m') ) {s = 18;}

                        else if ( (LA13_0=='f') ) {s = 19;}

                        else if ( (LA13_0=='U') ) {s = 20;}

                        else if ( (LA13_0=='v') ) {s = 21;}

                        else if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {s = 22;}

                        else if ( (LA13_0=='.') ) {s = 23;}

                        else if ( (LA13_0=='=') ) {s = 24;}

                        else if ( (LA13_0=='+') ) {s = 25;}

                        else if ( (LA13_0=='-') ) {s = 26;}

                        else if ( (LA13_0=='*') ) {s = 27;}

                        else if ( (LA13_0=='/') ) {s = 28;}

                        else if ( (LA13_0=='^') ) {s = 29;}

                        else if ( (LA13_0=='(') ) {s = 30;}

                        else if ( (LA13_0==')') ) {s = 31;}

                        else if ( (LA13_0=='>') ) {s = 32;}

                        else if ( (LA13_0=='<') ) {s = 33;}

                        else if ( (LA13_0=='o') ) {s = 34;}

                        else if ( (LA13_0=='n') ) {s = 35;}

                        else if ( ((LA13_0 >= 'A' && LA13_0 <= 'T')||(LA13_0 >= 'V' && LA13_0 <= 'Z')||LA13_0=='b'||(LA13_0 >= 'g' && LA13_0 <= 'h')||(LA13_0 >= 'j' && LA13_0 <= 'k')||LA13_0=='q'||(LA13_0 >= 'x' && LA13_0 <= 'z')) ) {s = 36;}

                        else if ( (LA13_0=='\n') ) {s = 37;}

                        else if ( (LA13_0=='\t'||LA13_0=='\r'||LA13_0==' ') ) {s = 38;}

                        else if ( ((LA13_0 >= '\u0000' && LA13_0 <= '\b')||(LA13_0 >= '\u000B' && LA13_0 <= '\f')||(LA13_0 >= '\u000E' && LA13_0 <= '\u001F')||LA13_0=='!'||(LA13_0 >= '#' && LA13_0 <= '\'')||LA13_0==';'||(LA13_0 >= '?' && LA13_0 <= '@')||LA13_0=='\\'||(LA13_0 >= '_' && LA13_0 <= '`')||(LA13_0 >= '{' && LA13_0 <= '\uFFFF')) ) {s = 39;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}