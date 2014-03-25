grammar Calculator;

eval
 : expr EOF
 ;

expr
 : '-' expr         #unaryminus_expr
 | '+' expr         #unaryplus_expr
 | 'log' expr		#log_expr 
 | expr '*' expr    #mult_expr
 | expr '/' expr    #div_expr
 | expr '+' expr    #add_expr
 | expr '-' expr    #min_expr
 | '(' expr ')'     #par_expr
 | NUMBER           #num_expr
 | IDENTIFIER       #id_expr
 ;

NUMBER
 : [0-9]+ ('.' [0-9]*)?
 | '.' [0-9]+
 ;

IDENTIFIER
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

SPACES
 : [ \t\n\r]+ -> skip
 ;
