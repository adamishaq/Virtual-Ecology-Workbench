grammar TestLexer;

options {
	backtrack=true;
	memoize=true;
}

@header {
package VEW.XMLCompiler.ANTLR.output;
}

@lexer::header {
package VEW.XMLCompiler.ANTLR.output;
}


// Rules
RULENAME : ('"')(LETTER|DIGIT|'_'|IGNORE)*('"');	
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
SALINITYAT  : 'salinityAt';
TEMPAT      : 'temperatureAt';
UVIRRADAT   : 'UVIrradAt';
VARHIST     : 'varhist';

// Numerical Expressions
fragment DIGIT : ('0'..'9');
FLOAT
    :   (DIGIT)+ '.' (DIGIT)*
    |   '.' (DIGIT)+
    |   (DIGIT)+;

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
protected LETTER : ('a'..'z'|'A'..'Z');   
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
	: rule NEWLINE
	; 
	
ruleName
	: RULENAME COLON (NEWLINE)?
	| VAR COLON (NEWLINE)?
	;
	
rule
	: assign
	| IF bExpr THEN rule
	| UPTAKE LBRACKET VAR COMMA expr RBRACKET
	| RELEASE LBRACKET VAR COMMA expr RBRACKET
	| INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET
	| CHANGE LBRACKET VAR COMMA RBRACKET
	| PCHANGE LBRACKET VAR COMMA expr RBRACKET
	| DIVIDE LBRACKET expr RBRACKET
	| CREATE LBRACKET VAR COMMA expr RBRACKET 
		(WITH LSQUARE assignList RSQUARE)?
	| LBRACKET rule RBRACKET
	;
	
assign
	: VAR EQUALS expr
	;
	

assignList
	: assign (COMMA assign)*
	;
		
expr
	: expr2 (lowPrecMathOp expr2)*
	;
	
expr2
	: expr3 (medPrecMathOp  expr3)*
	;
	
expr3
	: expr4 (highPrecMathOp expr4)*
	;
	

expr4
 	: LBRACKET expr RBRACKET
	| unaryPrimitives LBRACKET expr RBRACKET
	| FLOAT
	| VAR
	| IF bExpr THEN expr ELSE LBRACKET expr RBRACKET
	| binPrim LBRACKET expr COMMA expr RBRACKET
	| VARHIST LBRACKET VAR COMMA expr RBRACKET
	;


bExpr
	: bExpr2 (lowPrecBoolOp bExpr2)*
	; 	

bExpr2
	: expr comparators expr
	| NOT LBRACKET bExpr RBRACKET
	| LBRACKET bExpr RBRACKET
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
