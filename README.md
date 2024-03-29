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

Error reporting:
When errors were found, an error message is printed but does not stop the program. Once Semantic Analysis is done, the trees are printed with their respective types using the Print class. If an error was reported but flow wasn't stopped and the method needs a return value, a generic value would be used, such as INT for ExpTy.

Design Decisions:
We used a static boolean "inLoop" variable that would only be made true if we entered a loop and it was used by the BreakExp to see if a break was allowed. Once the loop finished, the boolean would be changed to false and processing would continue. We added new methods that the skeleton code didn't have like:
checkInt, checkOrderable (types must both be either a string or int), checkComparable (types must be equal), transArgs (returns an ExpTy of a argument from a call to a function), transLoopVarDec (enters a LoopVarEntry into the variable table), transTypeFields (used on records and function params; returns a RECORD type of the given FieldList). We also made a child class of VarEntry called "LoopVarEntry" that is strictly used for the loop variable initialized at the start of a for loop.

Problems:
We ran into some problems with ARRAY types where the element would be another array and our coerceTo function would be thrown off because the actual type of the array is not being obtained. Loops are a little funky and IfThenElse expressions don't type check correctly all the time, mostly when it calls a subscript of an array and the array has an element type of "array", like what was stated above.

# Project 4
For Project 4, we changed the Semant package to allocate locations for local variables, and to keep track of nesting levels. For part 1, the methods traverseVar, traverseExp and traverseDec were implemented in FindEscape. These mehtods were implemented to maintain a depth counter for each function declartation in the Tiger code. This implentation is important and has to occur before begining semantic analysis. For part 2, we implemented allocLocal in Mips.MipFrame and allocated formal parameters. Finally in part 3, we worked in the Semant file to allocate variables and construct frames. For implementing the project as a whole, we focused primarily on completing parts 1 and 2 then completed part 3 afterwards. For testing, we made a script to compare the results we got to what the expected result should be and the comparisons would always match and there are no real problems with our implmentation.

# Project 5
For Project 5, we generated Intermediate Code trees that represented what the source file is trying to do, machine independent. 

Everything that the project needed implemented was implemented however, we ran into problems with certain expressions like arrays and records/subscripts. Keeping track of frame pointers and displaying the correct tree using access.exp() when looking at variable reference was also complicated and so there is a few errors there. Calls inside of a function are messed up but not to a great extent and somewhere in the code we are generating big trees that shouldn't be there in the first place. Most expressions worked completely fine but, when you add them all together and do certain arithmetic, logic, etc. they fail in some cases. BADSUB and BADPTR not correctly implemented, along with Arrays + Subscripts in general.

# Project 6
In Project 6, we were given basic blocks gained from canonicalized trees and we used these blocks to select certain Mips instructions based off what the tree was trying to do.

For error/general correctness checking we generated a queens.s file that used the reference implementation and then another queens.s file that used our implementation. It's evident when comparing the two files that there are optimization problems when it came to our implementation, e.g., not a fully maximal munch of block tiles. Our algorithm for optimizing instruction selection is done somewhat well but, there are a couple of cases where the optimal instruction is not chosen. These were mainly the tiles that had a munch size >3, e.g., MOVE(MEM(BINOP(MEM(...)))). Overall though, most instructions are selected optimally.
