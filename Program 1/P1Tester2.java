/*  Tester program for CS310 SP 2015 Program #1
    Alan Riggins
*/    

import data_structures.*;
import java.util.Iterator;

public class P1Tester2 {
    private LinearListADT<Integer> list;
    
    public P1Tester2() {
        list = new ArrayLinearList<Integer>();    
        runTests();
        }
        
    private void runTests() { 
        
	for(int i=1; i <=8; i++)
		list.addFirst(i);
	list.remove(1);
	list.remove(1);
	list.contains(5);
	
	for(int x : list)
		System.out.println(x+" ");
	
	
	
	
        }
        
    public static void main(String [] args) {
        try {
            new P1Tester2();
            }
        catch(Exception e) {
            System.out.println("ERROR " + e);
            e.printStackTrace();
            }
        }
    }
