package VEW.XMLCompiler.ANTLR;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains all the ANTLR internal tokens as ints.
 * 
 * Has methods for efficiently accessing and grouping said internal tokens and returning them as our internal Enums
 * 
 * @author David
 *
 */
public class SymbolSet {
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
    
    private static Map<Integer, UnaryPrimitive> symbolToUPrimitiveMap = null;
    
    /**
     * Creates all the maps that convert a given SymbolNumber into internal Enums.
     * 
     * These maps are static.
     */
    public static void initializeMaps() {
    	symbolToUPrimitiveMap = new HashMap<Integer, UnaryPrimitive>();
    	symbolToUPrimitiveMap.put(ABS, UnaryPrimitive.ABS);
    	symbolToUPrimitiveMap.put(ACOS, UnaryPrimitive.ACOS);
    	symbolToUPrimitiveMap.put(ASIN, UnaryPrimitive.ASIN);
    	symbolToUPrimitiveMap.put(ATAN, UnaryPrimitive.ATAN);
    	symbolToUPrimitiveMap.put(COS, UnaryPrimitive.COS);
    	symbolToUPrimitiveMap.put(DENSITYAT, UnaryPrimitive.DENSITYAT);
    	symbolToUPrimitiveMap.put(DEPTHFORFI, UnaryPrimitive.DEPTHFORFI);
    	symbolToUPrimitiveMap.put(DEPTHFORVI, UnaryPrimitive.DEPTHFORVI);
    	symbolToUPrimitiveMap.put(EXP, UnaryPrimitive.EXP);
    	symbolToUPrimitiveMap.put(FULLIRRADAT, UnaryPrimitive.FULLIRRADAT);
    	symbolToUPrimitiveMap.put(INTEGRATE, UnaryPrimitive.INTEGRATE);
    	symbolToUPrimitiveMap.put(LN, UnaryPrimitive.LN);
    	symbolToUPrimitiveMap.put(LOGTEN, UnaryPrimitive.LOGTEN);
    	symbolToUPrimitiveMap.put(RND, UnaryPrimitive.RND);
    	symbolToUPrimitiveMap.put(SALINITYAT, UnaryPrimitive.SALINITYAT);
    	symbolToUPrimitiveMap.put(SIN, UnaryPrimitive.SIN);
    	symbolToUPrimitiveMap.put(SQRT, UnaryPrimitive.SQRT);
    	symbolToUPrimitiveMap.put(TAN, UnaryPrimitive.TAN);
    	symbolToUPrimitiveMap.put(TEMPAT, UnaryPrimitive.TEMPAT);
    	symbolToUPrimitiveMap.put(UVIRRADAT, UnaryPrimitive.UVIRRADAT);
    }
    
    /**
     * Given a corresponding symbol number to a UnaryPrimitive, this method returns the related UnaryPrimitive from the map.
     * 
     * If the given Symbol with number == symbolType is not a UnaryPrimitive or the number is not in the list of numbers, this
     * function returns null.
     * 
     * @param symbolType the number corresponding to the type of symbol
     * @return an equivalent UnaryPrimitive to the given symbolType
     */
    public static UnaryPrimitive getUnaryPrimitiveOp(int symbolType) {
    	if (symbolToUPrimitiveMap == null) {
    		initializeMaps();
    	}
    	return symbolToUPrimitiveMap.get(symbolType);
    }
}
