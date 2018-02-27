import java.util.Stack;
import java.io.*;
import java.util.*;

class Tree{
   
    Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { root = null; }            // no nodes in tree yet
// -------------------------------------------------------------
   public Tree(Tree t1, Tree t2) {       //combine two trees
	      root = new Node();
	      root.leftChild = t1.root;
	      root.rightChild = t2.root;
	      root.weight = t1.root.weight + t2.root.weight;   //the root becomes the combine weight of the sub trees	   
	    }
// -------------------------------------------------------------
   
   public Tree(int weight, char element) {    //create a tree containing a leaf node
     root = new Node(weight, element);
   }
// -------------------------------------------------------------
   public Node find(int key){      // find node with given key
                                 // (assumes non-empty tree)
      Node current = root;               // start at root
      while(current.cData != key){        // while no match,
         
         if(key < current.cData)         // go left?
            current = current.leftChild;
         else                            // or go right?
            current = current.rightChild;
         if(current == null)             // if no child,
            return null;                 // didn't find it
         }
      return current;                    // found it
      }  // end find()
// -------------------------------------------------------------
   public void insertNode(char ch, int fd){
   
   Node newNode = new Node();    // make new node
   newNode.cData = ch;           // insert data
   newNode.weight = fd;
   root = newNode;
   }
  //--------------------------------------------------------------   
   public void displayTree(){
      
      Stack<Node> globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 32;
      boolean isRowEmpty = false;
      System.out.println(
      "......................................................");
      while(isRowEmpty==false){
         
         Stack localStack = new Stack();
         isRowEmpty = true;

         for(int j=0; j<nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty()==false){
            
            Node temp = (Node)globalStack.pop();
            if(temp != null){
               
               if(temp.cData == 0){  
               
                  System.out.print(temp.weight);
                  localStack.push(temp.leftChild);
                  localStack.push(temp.rightChild);
               } else{

               System.out.print(temp.cData);              
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);
               }

               if(temp.leftChild != null || temp.rightChild != null)
                  isRowEmpty = false;
               }
            else
               {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
               }
            for(int j=0; j<nBlanks*2-2; j++)
               System.out.print(' ');
            }  // end while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
            globalStack.push((Node) localStack.pop() );
         }  // end while isRowEmpty is false
      System.out.println();
      System.out.println(
      "......................................................");
      }  // end displayTree()
// -------------------------------------------------------------
   public Tree createHuffmanTree(int[] counts) {
	    // Create a priority queue to hold trees
		PriorityQ PQ1 = new PriorityQ(counts.length);
	    for (int i = 0; i < counts.length; i++) {
	         if (counts[i] > 0)   
	            PQ1.insert(new Tree(counts[i], (char)i)); // A leaf node tree
	    }
	    
	    while (PQ1.getSize() > 1) { 
	      Tree t1 = PQ1.remove(); // Remove the smallest weight tree
	      Tree t2 = PQ1.remove(); // Remove the next smallest weight tree 
	      PQ1.insert(new Tree(t1, t2)); // Combine two trees
	    }

	    return PQ1.remove(); // return the huffman tree
	  } // end of createHuffmanTree()
 // -------------------------------------------------------------
   } // end class Tree
/////////////////////////////////////////////////////////////////////////////////////////
