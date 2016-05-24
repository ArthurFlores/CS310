import data_structures.*;
import java.util.Iterator;

public class Timer {
    private LinearListADT<Integer> list;
    
    public Timer() {
        list = new LinearList<Integer>();    
        runTests();
        }
        
    private void runTests() {
 	for(int i=1; i <= 10; i++)
            list.addLast(i); 
	list.insert(17,11);            
        System.out.println("Should print 1 .. 10"); 
        for(int x : list)
            System.out.print(x + " ");
        System.out.println("\n"); 
	list.clear();
        for(int i=0; i < 10; i++) {        
         list.addFirst(75);
         if(list.removeLast() != 75) System.out.println("ERROR, 3");  
         } 
         for(int x : list)
            System.out.print(x + " ");
        list.addLast(100);
	  for(int x : list){System.out.println("hello");
            System.out.print(x + " ");}
        if(list.removeFirst() != 100) System.out.println("ERROR, 4");
	  for(int x : list){ System.out.println("HI");
            System.out.print(x + " ");}
        list.addLast(200);
	  for(int x : list)
            System.out.print(x + " ");
        if(list.remove(1) != 200) System.out.println("ERROR, 5");
	}
    public static void main(String [] args) {
        try {
            new Timer();
            }
        catch(Exception e) {
            System.out.println("ERROR " + e);
            e.printStackTrace();
            }
        }
    }

