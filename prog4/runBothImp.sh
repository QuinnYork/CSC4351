javac -g */*.java
java Semant.Main testcases/merge.tig > actual.txt
rm */*.class
java Semant.Main testcases/merge.tig > expected.txt
