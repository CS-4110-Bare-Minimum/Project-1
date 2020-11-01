
import java.util.*;
%%
%class Toy
%standalone
%char
%state STRING
digit = [0-9]
number = {digit}+
integer = (("0X" | "0x") + ({number} | [a-fA-F])+) | {number}
decimal = {number} + "." + {number}*
double = {decimal} (("E-"|"E+"|"e+"|"e-") + {number})?
letter = [a-zA-Z_]
identifier = {letter}({letter}|{digit})*
newline = \n|\r|\r\n
InputCharacter = [^\r\n]
SpecialChar = (\!|\.|\,|\\|\/|\^])*
whitespace = [\t\ ]+
Comment = ({TraditionalComment} | {EndOfLineComment} | {DocumentationComment}) {newline}?
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {newline}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
strcon = ("\""|"\“") + ({letter}|{whitespace}|{digit}|{SpecialChar})* + ("\""|"\”")

%%
//rules
"boolean"      {System.out.print("boolean ");}
"break"        {System.out.print("break ");}
"class"        {System.out.print("class ");}
"double"       {System.out.print("double ");}
"else"         {System.out.print("else ");}
"extends"      {System.out.print("extends ");}
"false"        {System.out.print("booleanconstant ");} //false or booleanconstant
"for"          {System.out.print("for ");}
"if"           {System.out.print("if ");}
"implements"   {System.out.print("implements ");}
"int"          {System.out.print("int ");}
"interface"    {System.out.print("interface ");}
"new"          {System.out.print("new ");}
"newarray"     {System.out.print("newarray ");}
"null"         {System.out.print("null ");}
"println"      {System.out.print("println ");}
"readln"       {System.out.print("readln ");}
"return"       {System.out.print("return ");}
"string"       {System.out.print("string ");}
"this"         {System.out.print("this");}
"true"         {System.out.print("booleanconstant ");} //true or booleanconstant
"void"         {System.out.print("void ");}
"while"        {System.out.print("while ");}
{Comment}      {/* ignore */}
{integer}      {System.out.print("intconstant ");}
{double}       {System.out.print("doubleconstant ");}
{identifier}   {System.out.print("id ");}
{newline}      {System.out.println();}
{whitespace}   {/* ignore */}
{strcon}       {System.out.print("stringconstant ");}
"("            {System.out.print("leftparen ");}
")"            {System.out.print("rightparen ");}
"["            {System.out.print("leftbracket ");}
"]"            {System.out.print("rightbracket ");}
"{"            {System.out.print("leftbrace ");}
"}"            {System.out.print("rightbrace ");}
";"            {System.out.print("semicolon ");}
">"            {System.out.print("greater ");}
">="            {System.out.print("greaterequal ");}
"<"            {System.out.print("less ");}
"<="            {System.out.print("lessequal ");}
"=="            {System.out.print("equal ");}
"!="            {System.out.print("notequal ");}
"&&"            {System.out.print("and ");}
"||"            {System.out.print("or ");}
"!"            {System.out.print("not ");}
"="            {System.out.print("assignop ");}
"+"            {System.out.print("plus ");}
"-"            {System.out.print("minus ");}
"*"            {System.out.print("multiplication ");}
"/"            {System.out.print("division ");}
"%"            {System.out.print("mod ");}
","            {System.out.print("comma ");}
"."            {System.out.print("period ");}
