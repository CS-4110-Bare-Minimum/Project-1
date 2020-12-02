
import java.util.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
%%
%class Toy
%standalone
%char
%state STRING
//definitions
digit = [0-9]
number = {digit}+
integer = (("0X" | "0x") + ({number} | [a-fA-F])+) | {number}
decimal = {number} + "." + {number}*
double = {decimal} (("E-"|"E+"|"e+"|"e-") + {number})?
letter = [a-zA-Z_]
identifier = {letter}({letter}|{digit})*
newline = \n|\r|\r\n
InputCharacter = [^\r\n]
SpecialChar = (\!|\.|\,|\\|\/|\^|\?|\#|\&|\%|\$])*
whitespace = [\t\ ]+
Comment = ({TraditionalComment} | {EndOfLineComment} | {DocumentationComment}) {newline}?
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {newline}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
strcon = ("\""|"\“") + ({letter}|{whitespace}|{digit}|{SpecialChar})* + ("\""|"\”")

%{
   String output = "";
   String temp = "";
   Trie indentifiers = new Trie();
%}
%eof{
      System.out.print("what should output file be called? ");
      Scanner kb = new Scanner(System.in);
      String file = kb.nextLine();
      try
      {
         BufferedWriter writer = new BufferedWriter(new FileWriter(file));
         writer.write(output);
         writer.close();
      }
      catch(IOException e)
      {
      }

      indentifiers.print();

%eof}
%%
//rules
"boolean"      {output += "boolean ";}
"break"        {output += "break ";}
"class"        {output += "class ";}
"double"       {output += "double ";}
"else"         {output += "else ";}
"extends"      {output += "extends ";}
"false"        {output += "booleanconstant ";} //false or booleanconstant
"for"          {output += "for ";}
"if"           {output += "if ";}
"implements"   {output += "implements ";}
"int"          {output += "int ";}
"interface"    {output += "interface ";}
"new"          {output += "new ";}
"newarray"     {output += "newarray ";}
"null"         {output += "null ";}
"println"      {output += "println ";}
"readln"       {output += "readln ";}
"return"       {output += "return ";}
"string"       {output += "string ";}
"this"         {output += "this";}
"true"         {output += "booleanconstant ";} //true or booleanconstant
"void"         {output += "void ";}
"while"        {output += "while ";}
{Comment}      {/* ignore */}
{integer}      {output += "intconstant ";}
{double}       {output += "doubleconstant ";}
{identifier}   {
                  output += "id ";
                  temp = yytext();
                  indentifiers.insert(temp);
               }
{newline}      {output += "\n";}
{whitespace}   {/* ignore */}
{strcon}       {output += "stringconstant ";}
"("            {output += "leftparen ";}
")"            {output += "rightparen ";}
"["            {output += "leftbracket ";}
"]"            {output += "rightbracket ";}
"{"            {output += "leftbrace ";}
"}"            {output += "rightbrace ";}
";"            {output += "semicolon ";}
">"            {output += "greater ";}
">="           {output += "greaterequal ";}
"<"            {output += "less ";}
"<="           {output += "lessequal ";}
"=="           {output += "equal ";}
"!="           {output += "notequal ";}
"&&"           {output += "and ";}
"||"           {output += "or ";}
"!"            {output += "not ";}
"="            {output += "assignop ";}
"+"            {output += "plus ";}
"-"            {output += "minus ";}
"*"            {output += "multiplication ";}
"/"            {output += "division ";}
"%"            {output += "mod ";}
","            {output += "comma ";}
"."            {output += "period ";}
