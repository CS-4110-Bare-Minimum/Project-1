import java.util.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

public class Trie
{
   private int switch_size = 52;
   private int switch_array[];// = new int[switch_size];
   private ArrayList<Character> symbol;// = new ArrayList<Character>();
   private ArrayList<Integer> next;// = new ArrayList<Integer>();
   private int next_open;// = 0;

   public Trie()
   {
      switch_array = new int[switch_size];
      symbol = new ArrayList<Character>();
      next = new ArrayList<Integer>();
      next_open = 0;
      
      for(int i  = 0; i < switch_size; i++)
      {
         switch_array[i] = -1;
      }
   }
   public void insert(String str)
   {
      int first = (int)str.charAt(0); //first letter
      int symbol_index = 0; //index of symbol array
      int string_index = 1; //index of string characters

      //turn first letter as ascii index into index of switch array
      if(first < 91 && first > 64)
      {
         //if first letter is uppercase
         first = first - 65;
      }
      else if(first < 123 && first > 96)
      {
         //if first letter is lowercase
         first = first - 97 + 26; 
      }

      //get location in symbol array
      symbol_index = switch_array[first];
      
      if(symbol_index == -1)
      {
         //if first letter hasn't been used yet
         switch_array[first] = next_open;
         for(int i = 1; i < str.length(); i++)
         {
            symbol.add(str.charAt(i));
            next.add(-1);
            next_open++;
         }
         symbol.add('@');
         next.add(-1);
         next_open++;
      }
      else
      {
         //if first letter has been used
         boolean done = false;
         while(string_index < str.length())
         {
            if(str.charAt(string_index) == symbol.get(symbol_index))
            {
               //if letters match
               string_index++;
               symbol_index++;
            }
            else
            {
               done = true;
            }
            if(done)
            {
               //if characters don't match
               if(next.get(symbol_index) == -1)
               {
                  //if next hasn't been used
                  next.set(symbol_index, next_open);
                  while(string_index < str.length())
                  {
                     symbol.add(str.charAt(string_index));
                     next.add(-1);
                     next_open++;
                     string_index++;
                  }
                  symbol.add('@');
                  next.add(-1);
                  next_open++;
               }
               else
               {
                  //next has been used
                  symbol_index = next.get(symbol_index);
                  done = false;
               }
            }
         }
      }
   }
   public boolean check(String str)
   {
      int first = (int) str.charAt(0);
      int index = str.indexOf('@');
      int temp;

      if(index != -1)
      {
         System.out.println("invalid identifier");
         return false;
      }

      if(first < 91 && first > 64)
      {
         first = first - 65;
      }
      else if(first < 123 && first > 96)
      {
         first = first - 97 + 26;
      }
      else
      {
         System.out.println("invalid identifier");
         return false;
      }
      //if first letter hasn't been used yet
      if(switch_array[first] == -1)
      {
         return false;
      }
      //first letter has been used
      else
      {
         temp = 1;
         index = switch_array[first];
         while(index < next_open && temp <= str.length())
         {
            if(temp == str.length())
            {
               if(symbol.get(index) == '@')
               {
                  return true;
               }
               else
               {
                  index = next.get(index);
               }
            }
            else
            {
               //if identifier doesn't match what is in symbol
               if(str.charAt(temp) != symbol.get(index))
               {
                  //if no alternate options
                  if(next.get(index) == -1)
                  {
                     return false;
                  }
                  //alternate options
                  else
                  {
                     index = next.get(index);
                  }
               }
               //if identifier matches what is in symbol
               else
               {
                  temp++;
                  index++;
               }
            }
         }
      }
      return false;
   }
   public void print()
   {
      String output = "";
      //print swtich array
      System.out.println("          A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P   Q   R   S   T");
      output += "switch:";
      System.out.print("switch:");
      for(int i = 0; i < 20; i++)
      {
         output += " ";
         output += switch_array[i];
         System.out.printf("%4d", switch_array[i]);
      }
      System.out.println();
      System.out.println("\n          U   V   W   X   Y   Z   a   b   c   d   e   f   g   h   i   j   k   l   m   n");
      System.out.print("switch:");
      for(int i = 20; i < 40; i++)
      {
         output += " ";
         output += switch_array[i];
         System.out.printf("%4d", switch_array[i]);
      }
      System.out.println();
      System.out.println("\n          o   p   q   r   s   t   u   v   w   x   y   z");
      System.out.print("switch:");
      for(int i = 40; i < 52; i++)
      {
         output += " ";
         output += switch_array[i];
         System.out.printf("%4d", switch_array[i]);
      }
      System.out.println();
      

      //print symbol array and next array
      char ch;
      output += "\nsymbol: ";
      System.out.print("\nsymbol: ");
      for(int i = 0; i < next_open; i++)
      {
         ch = symbol.get(i).charValue();
         output += " ";
         output += ch;
         System.out.print("   " + ch);
         if(i % 19 == 0 && i != 0)
         {
            System.out.print("\n        ");
         }
      }
      output += "\nnext:   ";
      System.out.print("\n\nnext:   ");
      for(int i = 0; i < next_open; i++)
      {
         output += " ";
         output += next.get(i).intValue();
         System.out.printf("%4d", next.get(i).intValue());
         if(i % 19 == 0 && i != 0)
         {
            System.out.print("\n        ");
         }
      }
      System.out.println();
      output += "\n";
      try
      {
         BufferedWriter writer = new BufferedWriter(new FileWriter("trie_table.txt"));
         writer.write(output);
         writer.close();
      }
      catch(IOException e)
      {
      }
   }
}