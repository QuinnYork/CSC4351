# Project 4
For Project 4, we changed the Semant package to allocate locations for local variables, and to keep track of nesting levels. For part 1, the methods traverseVar, traverseExp and traverseDec
were implemented in FindEscape. These mehtods were implemented to maintain a depth counter for each function declartation in the Tiger code. For part 2, we implemented allocLocal 
in Mips.MipFrame and allocated formal parameters. Finally in part 3, we worked in the Semant file to allocate variables and construct frames. For implementing the project as
a whole, we focused primarily on completing parts 1 and 2 then completed part 3 afterwards. For testing, we made a script to compare the results we got to what the expected result
should be and the comparisons would always match and thre are no real problems with our implmentation.
