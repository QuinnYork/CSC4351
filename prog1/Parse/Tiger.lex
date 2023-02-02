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
private StringBuffer str;
private int depth;

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

%eof{
  {
    String[] arr = new String[4];
    arr[0] = "YYINITIAL"; arr[1] = "STRING"; arr[2] = "COMMENT"; arr[3] = "IGNORE";
    if (yy_lexical_state != YYINITIAL) {
      err("End of file reached in illegal state at: " + arr[yy_lexical_state]);
    }
  }
%eof}

%state STRING, COMMENT, IGNORE

ALPHA=[A-Za-z]
LOWER=[a-z]
DIGIT=[0-9]
WHITE_SPACE=[\t\f\b\r\v\ ]
CTRL_CHAR=[@-_]
%%
<YYINITIAL> {WHITE_SPACE}	{}
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

<YYINITIAL> "\"" { 
    yybegin(STRING); 
    str = new StringBuffer(""); }

<STRING> [^\\\"] { str.append(yytext()); }
<STRING> \\ { yybegin(IGNORE); }

<IGNORE> {WHITE_SPACE} { }
<IGNORE> \n { newline(); }
<IGNORE> \\ { yybegin(STRING); }
<IGNORE> . { err("Illegal token in ignore: " + yytext()); }

<STRING> \\n {
    str.append("\n"); }
<STRING> \\t {
    str.append("\t"); }
<STRING> "\\"" {
    str.append("\""); }
<STRING> \\\\ {
    str.append("\\"); }
<STRING> "\"" {
    yybegin(YYINITIAL); 
    return tok(sym.STRING, str.toString()); }
<STRING> (\\{DIGIT}*) {
    String s = yytext();
    if (s.length() <= 1)
      err("Illegal ascii escape sequence.");
    else {
      s = s.substring(1).trim();
      if (s.length() != 3)
        err("Illegal ascii code.");
      else {
        str.append((char)Integer.parseInt(s));
      }
    }
}
<STRING> (\\"^"({CTRL_CHAR})) { str.append(yytext()); }
<STRING> (\\"^"({LOWER})) {
    String s = yytext().substring(0, 2);
    System.out.print("yy: " + yytext() + " " + s);
    char c = yytext().charAt(2);
    s += yytext().charAt(2);
    
}

<YYINITIAL> {DIGIT}+ {
    return tok(sym.INT, yytext()); }

<YYINITIAL> {ALPHA}({ALPHA}|{DIGIT}|_)* {
    return tok(sym.ID, yytext()); }

<YYINITIAL> . { err("Illegal character: " + yytext()); }

<YYINITIAL> "/*" { yybegin(COMMENT); depth = 0; }
<COMMENT> "*/" { 
    if (depth == 0) 
      yybegin(YYINITIAL);
    else
      depth--;
}
<COMMENT> . { }
<COMMENT> "/*" { depth++; }