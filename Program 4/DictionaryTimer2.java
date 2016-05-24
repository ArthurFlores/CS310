/*  DictionaryTimer.java
    CS310, Spring 2015
    A sample driver for doing timing tests on insert and search operations.
        STRUCTURE_SIZE - Size of the dictionary.
        STEP_SIZE      - Increase the size of the dictionary by this amount
                         for each iteration
        ITERATIONS     - Number of timing tests to perform
        M_NUMBER       - Number of search/insert/delete operations to be timed
                         for each size.
    Try testing with the supplied default values, and make modifications
    as needed depending on the implementation tested and the speed of the
    machine you are using.
    
*/    
    
import data_structures.*;

public class DictionaryTimer2 {
    public static void main(String [] args) {
        new DictionaryTimer2();
        }
        
    public DictionaryTimer2() {
        int STRUCTURE_SIZE = 10;
        int STEP_SIZE = 1;
        int ITERATIONS = 10;
        int M_NUMBER = 10;
        int maxSize = 10;
        long start, stop;
        DictionaryADT dictionary;
//======================================================================

        Integer [] array = new Integer[maxSize];
	//int [] array={50,40,60,45,30,20};

            

         dictionary = new HashTable(STRUCTURE_SIZE+M_NUMBER);
//            dictionary = new BinarySearchTree();
 //          dictionary = new RedBlackTree();
// Change the line above to test different implementations.
//======================================================================  
        System.gc(); //run the garbage collector 
        
         

	    for(int j=1; j<=10; j++)
	        if(dictionary.add(j,j)) array[j-1]=j;
	    for(Integer x : array)
	         System.out.println(x);
	    
	if(!dictionary.add(5,5)) System.out.println("Not added, Duplicate");
	
	if(dictionary.add(11,5)) System.out.println("added");
	
	if(dictionary.delete(5)) System.out.println("remove");
	
	if(dictionary.contains(4)) System.out.println("contains");
	
	System.out.println(dictionary.getKey(5));
	
	System.out.println(dictionary.getValue(5));
	
	dictionary.clear();
	
	System.out.println();
//========================================================================
System.out.println("Test with empty list");

    if(!dictionary.delete(5)) System.out.println("nothing to delete");
    
    if(!dictionary.contains(5)) System.out.println("nothing to contains/find");
	
    System.out.println("Should return null: "+ dictionary.getKey(5));
	
    System.out.println("Should return null: "+ dictionary.getValue(5));
    
    if(dictionary.add(11,5)) System.out.println("added with empty list");

    dictionary.clear();
    System.out.println();
//=========================================================================
System.out.println("test for things that arent there");

    for(int j=1; j<=10; j++)
       dictionary.add(j,j);

    if(!dictionary.contains(11)) System.out.println("nothing to contains/find");
	
    System.out.println("Should return null: "+ dictionary.getKey(11));
	
    System.out.println("Should return null: "+ dictionary.getValue(11));

    dictionary.clear();
    System.out.println();
//===========================================================================
System.out.println("test for add things");
       System.out.println("Should be null:"+ dictionary.getValue(50)); 
	    for(int j=0; j<10; j++)
	        if(dictionary.add(array[j],array[j]));
	    for(int x : array)
	        System.out.println(x);
	
	    //for(Object x : dictionary.keys())
              //  System.out.println(x);
		
	if(dictionary.add(50,5)) System.out.println("added");
	
	if(dictionary.add(11,5)) System.out.println("added");
	
	System.out.println("Should be 5: "+dictionary.getValue(50));
	
	System.out.println("Should be null: "+dictionary.getValue(100));

        dictionary.clear();
        System.out.println();
//===========================================================================
System.out.println("test for delete things");
	  
	    for(int j=0; j<10; j++)
	        if(dictionary.add(array[j],array[j]));
	    for(int x : array)
	         System.out.println(x);
	
	if(!dictionary.delete(6)) System.out.println("Nothing removed: GOOD");
	
	if(dictionary.delete(50)) System.out.println("1 removed list should not have 1");
	for(int x : array)
	    System.out.println(x);
	 





	    /*for(int j=1; j<=10; j++)
	        if(dictionary.delete(j)) array[j-1]=0;
	    for(Integer x : array)
	         System.out.println(x);
	    */
            //system should now be empty
            dictionary.clear();             
            System.out.println();
        }
}    
