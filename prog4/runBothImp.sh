javac -g */*.java
java Semant.Main testcases/queens.tig > actual.txt
rm */*.class
java Semant.Main testcases/queens.tig > expected.txt
