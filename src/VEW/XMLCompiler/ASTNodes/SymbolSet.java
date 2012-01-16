package VEW.XMLCompiler.ASTNodes;

import java.util.HashMap;
import java.util.Map;

import VEW.XMLCompiler.ANTLR.BACONParser;

/**
 * Contains all the ANTLR internal tokens as ints.
 * 
 * Has methods for efficiently accessing and grouping said internal tokens and returning them as our internal Enums
 * 
 * @author David Coulden
 *
 */
public class SymbolSet {
    
    private static Map<Integer, UnaryPrimitive> symbolToUPrimitiveMap = null;
    
    /**
     * Creates all the maps that convert a given SymbolNumber into internal Enums.
     * 
     * These maps are static.
     */
    public static void initializeMaps() {
    	symbolToUPrimitiveMap = new HashMap<Integer, UnaryPrimitive>();
    	symbolToUPrimitiveMap.put(BACONParser.ABS, UnaryPrimitive.ABS);
    	symbolToUPrimitiveMap.put(BACONParser.ACOS, UnaryPrimitive.ACOS);
    	symbolToUPrimitiveMap.put(BACONParser.ASIN, UnaryPrimitive.ASIN);
    	symbolToUPrimitiveMap.put(BACONParser.ATAN, UnaryPrimitive.ATAN);
    	symbolToUPrimitiveMap.put(BACONParser.COS, UnaryPrimitive.COS);
    	symbolToUPrimitiveMap.put(BACONParser.DENSITYAT, UnaryPrimitive.DENSITYAT);
    	symbolToUPrimitiveMap.put(BACONParser.DEPTHFORFI, UnaryPrimitive.DEPTHFORFI);
    	symbolToUPrimitiveMap.put(BACONParser.DEPTHFORVI, UnaryPrimitive.DEPTHFORVI);
    	symbolToUPrimitiveMap.put(BACONParser.EXP, UnaryPrimitive.EXP);
    	symbolToUPrimitiveMap.put(BACONParser.FULLIRRADAT, UnaryPrimitive.FULLIRRADAT);
    	symbolToUPrimitiveMap.put(BACONParser.INTEGRATE, UnaryPrimitive.INTEGRATE);
    	symbolToUPrimitiveMap.put(BACONParser.LN, UnaryPrimitive.LN);
    	symbolToUPrimitiveMap.put(BACONParser.LOGTEN, UnaryPrimitive.LOGTEN);
    	symbolToUPrimitiveMap.put(BACONParser.RND, UnaryPrimitive.RND);
    	symbolToUPrimitiveMap.put(BACONParser.SALINITYAT, UnaryPrimitive.SALINITYAT);
    	symbolToUPrimitiveMap.put(BACONParser.SIN, UnaryPrimitive.SIN);
    	symbolToUPrimitiveMap.put(BACONParser.SQRT, UnaryPrimitive.SQRT);
    	symbolToUPrimitiveMap.put(BACONParser.TAN, UnaryPrimitive.TAN);
    	symbolToUPrimitiveMap.put(BACONParser.TEMPAT, UnaryPrimitive.TEMPAT);
    	symbolToUPrimitiveMap.put(BACONParser.UVIRRADAT, UnaryPrimitive.UVIRRADAT);
    	symbolToUPrimitiveMap.put(BACONParser.VISIRRADAT, UnaryPrimitive.VISIRRADAT);
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
