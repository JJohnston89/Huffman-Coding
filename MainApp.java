/*This is the main driver of the project. 
 *The input is a text file. The user is prompt by a menu. 
 *The user can view the Huffman tree, view the code table, view the encoded message, or view the decoded message. 
 *Name: Josh Johnston
 *Date: 7/27/2015
 */
 
 import java.util.Stack;
 import java.io.*;
 import java.util.*;


public class MainApp{
	public static void main(String[] args) throws FileNotFoundException{
		
		@SuppressWarnings("resource")  
		Scanner input = new Scanner(System.in);	            //created a scanner object	
		Tree theTree = new Tree();                          //created a tree object
		
		char[] textCh = getData(args[0]);                   //getData returns an char array
		int[] counts = getCharacterFrequency(textCh);       //get the count for A-G
		Tree HuffManTree = theTree.createHuffmanTree(counts);  //Create a Huffman tree
		String[] codes = getCode(HuffManTree.root);          //get codes
		String encodedMessage = encodeMessage(textCh, codes);       //get the encoded message	
		String decodeMessage = decodeMessage(HuffManTree, encodedMessage);  //decode the message
		
        while(true)  
        { 
          System.out.print("Enter a number ");  //display menu
          System.out.print("(1)display huffman tree, (2)display code table, "
        		   + "(3)display binary encoding message, (4)display decoded message: ");
        
        int choice = input.nextInt();
        
        switch(choice)
           {
           case 1:        	    
                HuffManTree.displayTree();   //display tree
                break;
                
           case 2:
        	    displayCodeTable(counts, codes);  //display code table           
                break;
           case 3:
        	    displayBinaryEncoding(encodedMessage);  //display encoded message         
                break;
                
           case 4:
        	    displayDecodedMessage(decodeMessage);   //display the decoded message
        	    break;           
           
           default:
              System.out.print("Invalid entry\n");   //wrong input, so print invalid
           }  // end switch
        
        }  // end while
		
	} // end of main()
// ---------------------------------------------------------------------------------
	
	public static char[] getData(String file) throws FileNotFoundException{
		int i = 0;
		File text = new File(file);   //created a file object
		
		if(text.canRead() == false){   //checking to see if the file can be read or not
			System.out.println("Can't read file");
			System.exit(0);			
		}
		Scanner scan = new Scanner(text);  //created a scanner object to read file
	        scan.useDelimiter("");
	    
	    char[] textCh = new char[(int)text.length()]; //create a char array to hold every char in the file
	       
	    while(scan.hasNext()){	    
	       textCh[i] = scan.next().charAt(0);
	       i++;
	     }
	     scan.close();  //closed the file
	     
	     return textCh;	   
	} //end of getData()
// -------------------------------------------------------------
	public static int[] getCharacterFrequency(char[] text){
	    int[] counts = new int[256]; // 256 ASCII characters
	    
	    for(int i = 0; i < text.length; i++){
	    	
	    		if(text[i] >= 'A' && text[i] <= 'G')    	
	    			counts[(int)text[i]]++; // Count only the characters A through G in text
	    	}
	    return counts;
	  } //end of getCharacterFrequency()
// ---------------------------------------------------------------	
	public static String[] getCode(Node root){
	    if(root == null)   //if root is null return null
	       return null;    
	    
	    String[] codes = new String[256]; //create a string array to hold codes
	    assignCode(root, codes); //get the codes
	    return codes;
	  }//end of getCodes()
// ---------------------------------------------------------------	
	private static void assignCode(Node root, String[] codes){
		//Recursively get codes to the leaf node
		if(root.leftChild != null){
			root.leftChild.code = root.code + "0";   //each left child is assigned 0
			assignCode(root.leftChild, codes); //call it self again
	      
			root.rightChild.code = root.code + "1";  //each right child is assigned 1
			assignCode(root.rightChild, codes);  //call it self again
	    	}
	    else{
	    	codes[(int)root.cData] = root.code;  //assigned the code to a char
	    }
	  }// end of assignCode()
// ---------------------------------------------------------------	  
	 public static void displayCodeTable(int[] counts, String[] codes){
		 //display the code table
		 System.out.printf("%-15s%-15s%-15s\n",
			                "Character", "Frequency", "Code");
		 
		  for(int i = 0; i < codes.length; i++){
		       if(counts[i] != 0)             
		    	   System.out.printf("%-15s%-15d%-15s\n",
		           (char)i + "", counts[i], codes[i]);
	             }
	 }//end of displayCodeTable()
// ---------------------------------------------------------------		  
	 public static String encodeMessage(char[] inputMessage, String[] codes){
		//use Huffman code to encode input
		  StringBuilder stringBuilder = new StringBuilder(); //created a stringBuilder object
		 
		  for(int i = 0; i < inputMessage.length; i++){
	            
			  String code = codes[inputMessage[i]];   
	            
			  if(code != null){
	            
				  for(int j = 0; j < code.length(); j++){
	            		            	
					  if(code.charAt(j) != ' ')	            		
					    stringBuilder.append(code.charAt(j)); //appending the string bit by bit without spaces					  
					  }
				  }				  
			  }
		  String finalString = stringBuilder.toString(); //getting the final string from stringBuilder
		  return finalString;
	}//end of encodeMessage
// ---------------------------------------------------------------	  
	  public static void displayBinaryEncoding(String encodedMessage){
		  int countBits = 0;
		  int countBytes = 0;
		 
		  char[] a = encodedMessage.toCharArray(); //converted the string into a char array
		  
		  for(int i = 0; i < a.length; i++){
			  
			  if(countBits != 8 && countBytes != 3){   //if bits is not 8 and bytes is not 3, print
				  System.out.printf("%c",a[i]);
			      countBits++;    //count the bit
				   }
				   else if(countBits == 8){    //if the bits is 8 print a space
					   System.out.print(" ");					   
				       countBits = 0;       //resetting the count for bits to 0
				       countBytes++;        //add one to byte count
				       
				       if(countBytes == 3){      //if bytes is 3 print a new line
				    	   System.out.println();
				    	   countBytes = 0;       //reset count for bytes to 0
				    	   System.out.printf("%c",a[i]);  //print
				    	   countBits++;         //add one to count for bit
				       }
				       else{
				       System.out.printf("%c",a[i]);
				       countBits++;
				       }				  
				   }		  
		  	}
		  	System.out.println();
	  
	  
} //end of displayBinaryEncoding()
// ---------------------------------------------------------------	  
	  public static String decodeMessage(Tree huffManTree, String encodedMessage){
		  
		     char[] input = encodedMessage.toCharArray(); //convert the string into a char array
	         String result = "";
	         Node position = huffManTree.root;
	         
	         for(int i = 0; i < input.length; i++){
	        	 if(input[i] == '1')                //if input[i] is 1, then the position is right child
	        		 position = position.rightChild;
	        	 	
	        	 	if(input[i] == '0')             //if input[i] is 0, then the position is left child
	        	 		position = position.leftChild;
	        	 	
	        	 if(position.leftChild == null && position.rightChild == null){ //if the position is at a leaf
	        		 result = result + position.cData;                          //add the leafs data to the result
	        		 position = huffManTree.root;  //return to the root of the tree
	        	 	}
	         }
	         return result;		  
	  }//end of decodeMessage()
// ---------------------------------------------------------------	  
	  public static void displayDecodedMessage(String decodedMessage){
		  
		  char[] output = decodedMessage.toCharArray();  //converting a string into a char array
	         
		  for(int k = 0; k < output.length; k++){
	        	 
			  System.out.print(output[k]);  //print each char
	         }
	         System.out.println();
	  }//end of displayDecodedMessage()
// ---------------------------------------------------------------
}//end of MainApp class
/////////////////////////////////////////////////////////////////////////////////////////////////////
