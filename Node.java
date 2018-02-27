class Node{
   
   public char cData;              // data item 
   public int weight;             // weight of the subTree
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child
   public String code = " ";      // The code of this node from the root
   
  
   public Node()    //default
   {
	   
   }
// -------------------------------------------------------------
   public Node(int weight, char cData){  //create a node
   	
       this.weight = weight;
       this.cData = cData;
   	}
// -------------------------------------------------------------
   public void displayNode(){      // display ourself
      
      System.out.print('{');
      System.out.print(cData);
      System.out.print(", ");
      System.out.print(weight);
      System.out.print("} ");
      }
 // -------------------------------------------------------------
   }  // end class Node
////////////////////////////////////////////////////////////////////////////////////////////////
