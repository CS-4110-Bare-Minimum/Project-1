import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

//switch : array [0..51] of integer;
//symbol : array [0..maxtransition] of char;
//next   : array [0..maxtransition] of integer;

//A: 65        Z: 90
//a: 97 (91)   z: 122 (116)

public class Trie {

	public static void main(String[] args) throws Exception {
		ArrayList<Integer> arraySwitch = new ArrayList<Integer>();
		ArrayList<Character> arraySymbol = new ArrayList<Character>();
		ArrayList<Integer> arrayNext = new ArrayList<Integer>();
		//inefficient?
		char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
						   'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		//Initialize switch
		for(int i = 0; i < 52; i++) {
			arraySwitch.add(-1);
		}
		
		File file = new File("src\\testTrie2.txt"); 
	    Scanner sc = new Scanner(file); 
	  
	    int index = 0;
	    
	    while (sc.hasNextLine()) {
	    	String input = sc.next();							//grab String
	    	char valueOfSymbol = input.charAt(0);				//get first char in string
	    	
	    	//get index for switch
	    	for(int j = 0; j < alphabet.length; j++) {
    			if(valueOfSymbol == alphabet[j]) {
    				index = j;
    				break;
    			}
    		}
	    	
	    	//if switch value is uninitialized...
	    	if(arraySwitch.get(index) == -1) {
	    		arraySwitch.set(index, arraySymbol.size());			//update switch	
		    	
	    		//put the rest of the string into symbol
	    		for(int k = 1; k < input.length(); k++) {
	    			arraySymbol.add(input.charAt(k));
	    			arrayNext.add(-1);
	    		}
	    		arraySymbol.add('*');								//Using '*' as the delim.
	    		arrayNext.add(-1);
	    	}
	    	//if switch value is already initialized
	    	else {
	    		//go through the rest of the string
	    		for(int k = 1; k < input.length(); k++) {
	    			char nextChar = input.charAt(k);
	    			
	    			if(nextChar != arraySymbol.get(k-1)) {
	    				arrayNext.set(k-1, arraySymbol.size());
	    				//Update size of symbol/next if needed
	    				if(arrayNext.get(k-1) >= arraySymbol.size()) {
	    					arraySymbol.add(null);
	    					arrayNext.add(-1);
	    				}
	    				if(arraySymbol.get(arrayNext.get(k-1)) == null) {
	    					arraySymbol.set(arrayNext.get(k-1), nextChar);
	    					//add rest of string into symbol
	    					while((k+1) < input.length()) {
	    						arraySymbol.add(input.charAt(k+1));
		    					arrayNext.add(-1);
	    						k++;
	    					}
	    					arraySymbol.add('*');
	    				}
	    			}
	    		}
	    	}
    		
	    }//end while
	    
	    //Print
	    System.out.print("        ");
	    for(int i = 0; i < 52; i++) {
			System.out.print(alphabet[i] + "   ");
		}
	    System.out.println();
	    System.out.print("switch: ");
	    for(int i = 0; i < arraySwitch.size(); i++) {
	    	System.out.print(arraySwitch.get(i) + "  ");
	    }
	    System.out.println();
	    System.out.print("symbol:   ");
	    for(int i = 0; i < arraySymbol.size(); i++) {
	    	System.out.print(arraySymbol.get(i) + "  ");
	    }
	    System.out.println();
	    System.out.print("next:   ");
	    for(int i = 0; i < arrayNext.size(); i++) {
	    	System.out.print(arrayNext.get(i) + "  ");
	    }
	    System.out.println();
	    
	    
	    sc.close();
	}//end main

}
