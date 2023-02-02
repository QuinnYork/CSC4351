package Parse;
import ErrorMsg.ErrorMsg;

%% 

%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol
%char

%{
private void newline() {
  errorMsg.newline(yychar);
}

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private java_cup.runtime.Symbol tok(int kind) {
    return tok(kind, null);
}

private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}

private ErrorMsg errorMsg;

Yylex(java.io.InputStream s, ErrorMsg e) {
  this(s);
  errorMsg=e;
}

%}

%eofval{
	{
	 return tok(sym.EOF, null);
  }
%eofval}    

%state STRING
%state COMMENT

ALPHA=[A-Za-z]
DIGIT=[0-9]
WHITE_SPACE=[\n\t\f\b\r\v]

%%
<YYINITIAL> " "	{}
<YYINITIAL> \n {newline();}
<YYINITIAL> ","	{return tok(sym.COMMA, null);}
<YYINITIAL> ":"	{return tok(sym.COLON, null);}
<YYINITIAL> ";"	{return tok(sym.SEMICOLON, null);}
<YYINITIAL> "("	{return tok(sym.LPAREN, null);}
<YYINITIAL> ")"	{return tok(sym.RPAREN, null);}
<YYINITIAL> "["	{return tok(sym.LBRACE, null);}
<YYINITIAL> "]"	{return tok(sym.RBRACE, null);}
<YYINITIAL> "{"	{return tok(sym.LBRACK, null);}
<YYINITIAL> "}"	{return tok(sym.RBRACK, null);}
<YYINITIAL> "."	{return tok(sym.DOT, null);}
<YYINITIAL> "+"	{return tok(sym.PLUS, null);}
<YYINITIAL> "-"	{return tok(sym.MINUS, null);}
<YYINITIAL> "*"	{return tok(sym.TIMES, null);}
<YYINITIAL> "/"	{return tok(sym.DIVIDE, null);}
<YYINITIAL> "="	{return tok(sym.EQ, null);}
<YYINITIAL> "<>"	{return tok(sym.NEQ, null);}
<YYINITIAL> "<"	{return tok(sym.LT, null);}
<YYINITIAL> "<="	{return tok(sym.LE, null);}
<YYINITIAL> ">"	{return tok(sym.GT, null);}
<YYINITIAL> ">="	{return tok(sym.GE, null);}
<YYINITIAL> "&"	{return tok(sym.AND, null);}
<YYINITIAL> "|"	{return tok(sym.OR, null);}
<YYINITIAL> ":="	{return tok(sym.ASSIGN, null);}

<YYINITIAL> "while" {return tok(sym.WHILE); }
<YYINITIAL> "for" {return tok(sym.FOR); }
<YYINITIAL> "to" {return tok(sym.TO); }
<YYINITIAL> "break" {return tok(sym.BREAK); }
<YYINITIAL> "let" {return tok(sym.LET); }
<YYINITIAL> "in" {return tok(sym.IN)); }
<YYINITIAL> "end" {return tok(sym.END); }
<YYINITIAL> "function" {return tok(sym.FUNCTION); }
<YYINITIAL> "var" {return tok(sym.VAR); }
<YYINITIAL> "type" {return tok(sym.TYPE); }
<YYINITIAL> "array" {return tok(sym.ARRAY); }
<YYINITIAL> "if" {return tok(sym.IF); }
<YYINITIAL> "then" {return tok(sym.THEN); }
<YYINITIAL> "else" {return tok(sym.ELSE); }
<YYINITIAL> "do" {return tok(sym.DO); }
<YYINITIAL> "of" {return tok(sym.OF); }
<YYINITIAL> "nil" {return tok(sym.NIL); }

<YYINITIAL> "\"" { 
    yybegin(STRING); 
    String str = ""; }

// for comments: "/*" begins comment, "*/" ends comment
// need to keep track of comment count

<STRING> {ALPHA} { str += yytext(); }
<STRING> "\"" { 
    yybegin(YYINITIAL); 
    return tok(sym.STRING, yytext()); }

// still need to look at all escape sequences in a string
// e.g.:
<STRING> "\n" {
    str += "\n"; }
<YYINITIAL> {DIGIT}+ {
    return tok(sym.INT, yytext()); }

<YYINITIAL> {ALPHA}({ALPHA}|{DIGIT}|_)* {
    return tok(sym.ID, yytext()); }

<YYINITIAL> . { err("Illegal character: " + yytext()); }
