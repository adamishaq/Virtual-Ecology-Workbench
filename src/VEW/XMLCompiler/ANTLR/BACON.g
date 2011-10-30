grammar BACON;

options {
	backtrack=true;
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
	| IF bExpr THEN rule -> ^(IF bExpr rule)
	| UPTAKE LBRACKET VAR COMMA expr RBRACKET -> ^(UPTAKE VAR expr)
	| RELEASE LBRACKET VAR COMMA expr RBRACKET -> ^(RELEASE VAR expr)
	| INGEST LBRACKET VAR COMMA expr COMMA expr RBRACKET -> ^(INGEST VAR expr expr)
	| CHANGE LBRACKET VAR RBRACKET -> ^(CHANGE VAR)
	| PCHANGE LBRACKET VAR COMMA expr RBRACKET -> ^(PCHANGE VAR expr)
	| DIVIDE LBRACKET expr RBRACKET -> ^(DIVIDE expr)
	| CREATE LBRACKET VAR COMMA expr RBRACKET 
		(WITH LSQUARE assignList RSQUARE)? -> ^(CREATE VAR expr (assignList)?)
	| LBRACKET rule2 RBRACKET -> rule2
	;

assign
	: VAR EQUALS expr -> ^(ASSIGN VAR expr)
	;

assignList
	: assign (COMMA assign)* -> ^(ASSIGNLIST assign (assign)*)
	;
		
expr
	: expr2 (lowPrecMathOp^ expr2)*
	;
	
expr2
	: expr3 (medPrecMathOp^  expr3)*
	;
	
expr3
	: expr4 (highPrecMathOp^ expr4)*
	;
	

expr4
 	: LBRACKET expr RBRACKET -> expr
	| unaryPrimitives LBRACKET expr RBRACKET -> ^(unaryPrimitives expr)
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
	: expr comparators^ expr
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
