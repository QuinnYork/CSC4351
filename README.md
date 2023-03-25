# CSC4351
Semester long class project for CSC4351 Compilers

Team: Quinn York and Kantonio Brownlee

# Project 1
For this project, we utilized JLex as a lexical analyzer framework and JCup as a parser to establish tokens gained from the lexical analysis (that will be used later). This is done to read different tokens in the language Tiger. We used ErrorMsg errorMsg, StringBuffer str and int depth as private variables to help with the analysis. We used 4 different states, with those being YYINITIAL, STRING, COMMENT, and IGNORE. 

In YYINITIAL we look for a start of a comment, white space, new lines, valid symbols, double quote, integers, and identifiers. Anything else throws an error. A string state starts when a double quote is found and keeps going until another double quote is found, all while adding chars and escape sequences to a StringBuffer that is returned when we exit the String state. A comment state starts when a "/*" is found and if anymore are found, the integer depth is incremented. Everytime a closing comment is found we decrement depth and once depth = 0 we begin YYINITIAL (and return nothing inside the comment). The ignore state is used to ignore the escape chars inside of the format escape sequence (other than new lines, in which we increment the line count).

# Project 2
For Project 2, we used JCup to implement a parser for the programming language Tiger. We did this by first creating terminals and non-terminals to use for our grammar. We started with Exp as our first non-terminal to parse through expressions and other terminals and non-terminals to parse symbols, funtions, and key words. We were having issues with our typedec and funcdec non-terminals. When compiling our code we had an output that would return a syntax error in the tiger code we would run. We also have a shift-reduce conflict in our if expression that needs resolving, and our Var non-terminals have no way of differntiating between the two ID's. Those were the main issues we had with our implementation.  

# Project 3
For Project 3, we used Java to implement a type checker for the language CTiger. This is done by using the Parse trees built by the Parser and the data it stored in the trees. As the program ran through these Parse trees, it performed Semantic Analysis to ensure the procedures listed in the file were valid when comparing with the language construct. 

##Error reporting:
When errors were found, an error message is printed but does not stop the program. Once Semantic Analysis is done, the trees are printed with their respective types using the Print class. If an error was reported but flow wasn't stopped and the method needs a return value, a generic value would be used, such as INT for ExpTy.

##Design Decisions:
We used a static boolean "inLoop" variable that would only be made true if we entered a loop and it was used by the BreakExp to see if a break was allowed. Once the loop finished, the boolean would be changed to false and processing would continue. We added new methods that the skeleton code didn't have like:
checkInt, checkOrderable (types must both be either a string or int), checkComparable (types must be equal), transArgs (returns an ExpTy of a argument from a call to a function), transLoopVarDec (enters a LoopVarEntry into the variable table), transTypeFields (used on records and function params; returns a RECORD type of the given FieldList). We also made a child class of VarEntry called "LoopVarEntry" that is strictly used for the loop variable initialized at the start of a for loop.

##Problems:
We ran into some problems with ARRAY types where the element would be another array and our coerceTo function would be thrown off because the actual type of the array is not being obtained. Loops are a little funky and IfThenElse expressions don't type check correctly all the time, mostly when it calls a subscript of an array and the array has an element type of "array", like what was stated above.
