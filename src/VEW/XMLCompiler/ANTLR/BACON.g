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
	CHANGEASSIGN;
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
COLON    : (':')(IGNORE)*;

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

//Change syntax
BAR 	: '|';
OTHERWISE 
	: 'otherwise';
TO	: 'to';

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
//fragment COMMENT : ('//')(~'\n')*;
COMMENT : ('//')(~'\n')* {$channel=HIDDEN;};

// Whitespace
	
//NEWLINE : (COMMENT|(('\n')(IGNORE)*))+;
IGNORE 	: (' '| '\t'|'\r' | '\n') {$channel=HIDDEN;};

// Unrecognised Symbol
UNKNOWN	: (.);

/*********** PARSER CODE **************/


rules
	: pair (pair)* EOF -> ^(RULES pair pair*)
	; 
	
pair
	: ruleName rule2 -> ^(RULE ruleName rule2)
	| rule
	;
	
ruleName
	: RULENAME COLON -> RULENAME
	| VAR COLON -> VAR
	;

rule
	: rule2 -> ^(RULE rule2)
	;

rule2
	: assign
	| IF bExpr THEN rule -> ^(IF bExpr rule)
	| UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^(UPTAKE VAR expr)
	| RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^(RELEASE VAR expr)
	| INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^(INGEST VAR expr expr)
	//| CHANGE LBRACKET VAR RBRACKET -> ^(CHANGE VAR)
	//| PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^(PCHANGE VAR expr)
	| CHANGE LBRACKET expr RBRACKET changeExpr+ -> ^(CHANGE expr changeExpr+)
	| DIVIDE LBRACKET expr RBRACKET -> ^(DIVIDE expr)
	| CREATE LBRACKET VAR COMMA expr RBRACKET
		(WITH LSQUARE assignList RSQUARE)? -> ^(CREATE VAR expr (assignList)?)
	| LBRACKET rule2 RBRACKET -> rule2
	;

changeExpr 
	: BAR changeCond TO VAR -> ^(CHANGEASSIGN changeCond VAR)
	;

changeCond 
	: OTHERWISE
	| bExpr
	;

assign
	: VAR EQUALS expr -> ^(ASSIGN VAR expr)
	;

assignList
	: assign (COMMA assign)* -> ^(ASSIGNLIST assign (assign)*)
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
