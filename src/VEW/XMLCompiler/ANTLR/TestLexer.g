lexer grammar TestLexer;

// Keywords and Variables
// If statement:
IF   : 'if';
THEN : 'then';
ELSE : 'else';

// Biological Functions:
UPTAKE    : 'uptake';
RELEASE   : 'release';
INGEST 	  : 'ingest';
CHANGE    : 'change';
PCHANGE   : 'pchange';
DIVIDE	  : 'divide';
INTEGRATE : 'integrate';

// Creates Syntax
CREATE 	: 'create';
WITH 	: 'with';
LSQUARE : '[';
RSQUARE	: ']';	

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
TEMPAT	    : 'temperatureAt';
UVIRRADAT   : 'UVIrradAt';
VARHIST	    : 'varhist';

// Numerical Expressions
protected DIGIT : ('0'..'9');
FLOAT
    :   (DIGIT)+ '.' (DIGIT)*
    |   '.' (DIGIT)+
    |   (DIGIT)+;

// Arithmetic Operators
EQUALS 	 : '=';
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
NEQUALS	      : '<>';		
GREATERTHAN   : '>';
LESSTHAN      : '<';
AND	      :	'and';
OR	      : 'or';			

// Variable names
protected LETTER : ('a'..'z'|'A'..'Z');	
VAR  :	(LETTER|'_') (LETTER|DIGIT|'_')*;

// Whitespace Stuff
NEWLINE : '\n';
WS  :   ( ' '
        | '\t'
        | '\r'
        ) {$channel=HIDDEN;};

