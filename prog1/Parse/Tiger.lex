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
%state IDENT

ALPHA=[A-Za-z]
DIGIT=[0-9]
WHITE_SPACE=[\n\t]

%%
<YYINITIAL> " "	{}
<YYINITIAL> \n	{newline();}
<YYINITIAL> ","	{return tok(sym.COMMA, null);}
<YYINITIAL> . { err("Illegal character: " + yytext()); }
<YYINITIAL> "\"" { 
    yybegin(STRING); 
    String str = ""; }
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

// this will lead into an identifier. I have to create a bigger range
// of chars than just the alphabet since identifiers have special
// reqs
<YYINITIAL> {ALPHA} 
