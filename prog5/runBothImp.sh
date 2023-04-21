javac -g */*.java
java Translate.Main test.tig > actual.txt
rm */*.class
java Translate.Main test.tig > expected.txt
