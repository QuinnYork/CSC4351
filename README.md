# CSC4351
Semester long class project for CSC4351 Compilers

Team: Quinn York and Kantonio Brownlee

# Project 1
For this project, we utilized JLex as a lexical analyzer framework and JCup as a parser to establish tokens gained from the lexical analysis (that will be used later). This is done to read different tokens in the language Tiger. We used ErrorMsg errorMsg, StringBuffer str and int depth as private variables to help with the analysis. We used 4 different states, with those being YYINITIAL, STRING, COMMENT, and IGNORE. 

In YYINITIAL we look for a start of a comment, white space, new lines, valid symbols, double quote, integers, and identifiers. Anything else throws an error. A string state starts when a double quote is found and keeps going until another double quote is found, all while adding chars and escape sequences to a StringBuffer that is returned when we exit the String state. A comment state starts when a "/*" is found and if anymore are found, the integer depth is incremented. Everytime a closing comment is found we decrement depth and once depth = 0 we begin YYINITIAL (and return nothing inside the comment). The ignore state is used to ignore the escape chars inside of the format escape sequence (other than new lines, in which we increment the line count).

# Project 2

#### For Project 2, we used JCup to implement a parser for the programming language Tiger. We did this by first creating terminals and non-terminals to use for our grammar. We started with Exp as our first non-terminal to parse through expressions and other terminals and non-terminals to parse symbols, funtions, and key words. We were having issues with our typedec and funcdec non-terminals. When compiling our code we had an output that would return a syntax error in the tiger code we would run. We also have a shift-reduce conflict in our if expression that needs resolving, and our Var non-terminals have no way of differntiating between the two ID's. Those were the main issues we had with our implementation.  
