grammar Select;

options {
  output=AST;
}

tokens {
  // imaginary tokens
  ROOT;
  ATTR_LIST;
  UNARY_MINUS;
  INTENT_LIST;

  // literal tokens
  Eq     = '=';
  NEq    = '!=';
  LT     = '<';
  LTEq   = '<=';
  GT     = '>';
  GTEq   = '>=';
  Minus  = '-';
  Not    = '!';
  Select = 'select';
  From   = 'from';
  Where  = 'where';
  And    = 'and';
  Or     = 'or';
  Action_Intent = 'action_intents';

}
@parser::header {
  package com.dalsps.query;
  
}

@lexer::header {
  package com.dalsps.query;
}
parse
 : select_stat EOF -> ^(ROOT select_stat)
 ;

select_stat
 : Select attr_list From Id where_stat (Action_Intent intent_list)?  ';' -> ^(Select attr_list Id where_stat (intent_list)? ) 
 ;

attr_list
 : Id (',' Id)* -> ^(ATTR_LIST Id+)
 ;

where_stat
 : Where expr -> expr
 |            -> ^(Eq Int["1"] Int["1"]) 
                 // no 'where', insert '1=1' which is always true
 ;

expr
 : or_expr
 ;

or_expr
 : and_expr (Or^ and_expr)*
 ;

and_expr
 : eq_expr (And^ eq_expr)*
 ;

eq_expr
 : rel_expr ((Eq | NEq)^ rel_expr)*
 ;

rel_expr
 : unary_expr ((LT | LTEq | GT | GTEq)^ unary_expr)?
 ;

unary_expr
 : Minus atom -> ^(UNARY_MINUS atom)
 | Not atom   -> ^(Not atom)
 | atom
 ;

atom
 : Str
 | Int
 | Id
 | '(' expr ')' -> expr
 ;

intent_list
	:	
	 Id (',' Id)* -> ^(INTENT_LIST Id+)
	;
			



Id    : ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_'|'\.'| Digit)*;
	
Str   : '\'' ('\'\'' | ~('\'' | '\r' | '\n'))* '\'' 
        {
         // strip the surrounding quotes and replace '' with '
         setText($text.substring(1, $text.length() - 1).replace("''", "'"));
        }
      ;
Int   : Digit+;

Space : (' ' | '\t' | '\r' | '\n') {skip();};

fragment Digit : '0'..'9';