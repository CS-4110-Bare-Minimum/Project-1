# Project-1

Outline: You are to implement a lexical analyzer for a simple object-oriented programming language called Toy. 
Your program should be able to...
(1) translate any input Toy program into a sequence of tokens, and 
(2) create a symbol table using the trie structure for all keywords and user-defined identifiers.


JFlex Installation - https://www.jflex.de/  
JFlex Installation Help - https://www.jflex.de/manual.html#Installing  
JFlex 1.8.2 

How to compile and run
1. With JFlex installed and added to PATH, you need to open the command prompt/terminal window.
2. Change to the directory of the files using the cd command - cd target-path-here
3. Run JFlex by issuing the jflex command on the .jflex file - jflex project1.jflex
4. If done corectly, JFlex will output a .java file called Toy.java
5. You will then need to issue the javac command on the newly outputted .java file - javac Toy.java
6. To see the output of input1, type java Toy input1.txt
7. To see the output of input2, type java Toy input2.txt
