grammar BACON;

options {
	memoize=true;
	output=AST;
}

tokens {
	ASSIGN;
	RULE;
	RULES;
	ASSIGNLIST;
	EXPR;
	BEXPR;
	NEG;
}

@header {
package VEW.XMLCompiler.ANTLR;
import java.util.Map;
import java.util.HashMap;
}

@parser::members {
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

}

@lexer::header {
package VEW.XMLCompiler.ANTLR;
}

// Rules
RULENAME : ('"')(~'"')*('"');	
COLON    : (':')(IGNORE)*(NEWLINE)?;

// Keywords
// If statement:
IF   : 'if';
THEN : 'then';
ELSE : 'else';

// Biological Functions:
UPTAKE     : 'uptake';
RELEASE    : 'release';
INGEST     : 'ingest';
CHANGE     : 'change';
PCHANGE    : 'pchange';
DIVIDE	   : 'divide';
INTEGRATE  : 'integrate';

// Creates Syntax
CREATE	: 'create';
WITH	: 'with';
LSQUARE : '[';
RSQUARE	: ']';
COMMA 	: ',';

// Mathematical Functions
ABS    : 'abs';
ACOS   : 'acos';
ASIN   : 'asin';
ATAN   : 'atan';
SIN    : 'sin';
COS    : 'cos';
TAN    : 'tan';
EXP    : 'exp';
LN     : 'ln';
LOGTEN : 'log10';
RND    : 'rnd';
SQRT   : 'sqrt';
MAX    : 'max';
MIN    : 'min';

// System Variable Functions
DENSITYAT   : 'densityAt';
DEPTHFORFI  : 'depthForFI';
DEPTHFORVI  : 'depthForVI';
FULLIRRADAT : 'fullIrradAt';
VISIRRADAT  : 'visIrradAt';
SALINITYAT  : 'salinityAt';
TEMPAT      : 'temperatureAt';
UVIRRADAT   : 'UVIrradAt';
VARHIST     : 'varhist';

// Numerical Expressions
fragment DIGIT : ('0'..'9');

FLOAT
    :   (MINUS)? DIGIT+ ('.' DIGIT* EXPONENT?)?
    |   (MINUS)? DIGIT+ EXPONENT
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? DIGIT+ ;

// Arithmetic Operators
EQUALS   : '=';
PLUS     : '+' ;
MINUS    : '-' ;
MUL      : '*' ;
DIV      : '/' ;
POW      : '^' ;
LBRACKET : '(';
RBRACKET : ')';

// Boolean Operators
GREATEREQUALS : '>=';
LESSEQUALS    : '<=';
NEQUALS       : '<>';
GREATERTHAN   : '>';
LESSTHAN      : '<';
AND           :  'and';
OR            : 'or';
NOT           : 'not';	      
	
	
// Vector Functions
ALL 	 : 'all';
SOME	 : 'some';
NONE	 : 'none';
VAVERAGE : 'average';
VPRODUCT : 'product';
VSUM	 : 'sum';

// Variable names
fragment LETTER : ('a'..'z'|'A'..'Z');   
VAR : (LETTER)(LETTER|DIGIT|'_')*;

// Line Comments
fragment COMMENT : ('//')(~'\n')*;  

// Whitespace
NEWLINE : (COMMENT|(('\n')(IGNORE)*))+;
IGNORE 	: (' '| '\t'|'\r') {$channel=HIDDEN;};

// Unrecognised Symbol
UNKNOWN	: (.);

/*********** PARSER CODE **************/


rules
	: (NEWLINE)? pair (NEWLINE pair)* (NEWLINE)? -> ^(RULES pair pair*)
	; 
	
pair
	: ruleName rule2 -> ^(RULE ruleName rule2)
	| rule
	;
	
ruleName
	: RULENAME COLON (NEWLINE)? -> RULENAME
	| VAR COLON (NEWLINE)? -> VAR
	;

rule
	: rule2 -> ^(RULE rule2)
	;

rule2
	: assign
	| IF bExpr (NEWLINE)? THEN (NEWLINE)? rule -> ^(IF bExpr rule)
	| UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^(UPTAKE VAR expr)
	| RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^(RELEASE VAR expr)
	| INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^(INGEST VAR expr expr)
	| CHANGE LBRACKET VAR RBRACKET -> ^(CHANGE VAR)
	| PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^(PCHANGE VAR expr)
	| DIVIDE LBRACKET expr RBRACKET -> ^(DIVIDE expr)
	| CREATE LBRACKET VAR COMMA expr RBRACKET (NEWLINE)?
		(WITH LSQUARE assignList RSQUARE)? -> ^(CREATE VAR expr (assignList)?)
	| LBRACKET rule2 RBRACKET -> rule2
	;

assign
	: VAR EQUALS expr -> ^(ASSIGN VAR expr)
	;

assignList
	: assign (NEWLINE)? (COMMA assign (NEWLINE)?)* -> ^(ASSIGNLIST assign (assign)*)
	;
		
expr
	: expr2 (options {greedy = true;} : lowPrecMathOp^ expr2)*
	;
	
expr2
	: expr3 (options {greedy = true;} : medPrecMathOp^  expr3)*
	;
	
expr3
	: expr4 (options {greedy = true;} : highPrecMathOp^ expr4)*
	;
	

expr4
 	: LBRACKET expr RBRACKET -> expr
	| unaryPrimitives LBRACKET expr RBRACKET -> ^(unaryPrimitives expr)
	| MINUS expr -> ^(NEG expr)
	| FLOAT
	| VAR
	| IF bExpr THEN expr ELSE expr -> ^(IF bExpr expr expr)
	| binPrim LBRACKET expr COMMA expr RBRACKET -> ^(binPrim expr expr)
	| VARHIST LBRACKET VAR COMMA expr RBRACKET -> ^(VARHIST VAR expr)
	| vOp LBRACKET expr RBRACKET -> ^(vOp expr)
	;


bExpr
	: bExpr2 (lowPrecBoolOp^ bExpr2)*
	; 	

bExpr2
	: (expr comparators) =>  expr comparators^ expr
	| NOT LBRACKET bExpr RBRACKET -> ^(NOT bExpr)
	| LBRACKET bExpr RBRACKET -> bExpr
	| vBOp LBRACKET bExpr RBRACKET -> ^(vBOp bExpr)
	;



	
unaryPrimitives
	: ABS
	| ACOS
	| ASIN
	| ATAN
	| COS
	| EXP
	| LN
	| LOGTEN
	| RND
	| SIN
	| SQRT
	| TAN
	| DENSITYAT
	| DEPTHFORFI
	| DEPTHFORVI
	| FULLIRRADAT
	| SALINITYAT
	| TEMPAT
	| UVIRRADAT
	| INTEGRATE
	| VISIRRADAT
	;
	
lowPrecMathOp
	: PLUS
	| MINUS
	; 
	
medPrecMathOp
	: MUL
	| DIV
	;
	
highPrecMathOp
	: POW
	;

binPrim
	: MIN
	| MAX
	;
	
lowPrecBoolOp
	: AND
	| OR
	;

comparators
	: EQUALS
	| NEQUALS
	| GREATERTHAN
	| LESSTHAN
	| GREATEREQUALS
	| LESSEQUALS
	;
	
vOp
	: VSUM
	| VPRODUCT
	| VAVERAGE
	;
	
vBOp
	: ALL
	| SOME
	| NONE
	;
