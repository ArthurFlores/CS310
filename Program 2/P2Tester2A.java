/*  Tester program for CS310 SP 2015 Program #1
    Alan Riggins
*/    

import data_structures.*;
import java.util.Iterator;

public class P2Tester2A {
    private LinearListADT<Integer> list;
    
    public P2Tester2A() {
        list = new ArrayLinearList<Integer>();    
        runTests();
        }
        
    private void runTests() {
 	for(int g=10000; g<=100000; g+=10000){
	list.clear();
    	for(int i=1; i<g; i++)
		list.addLast(i);
	long start = System.currentTimeMillis();
        for(int j=1; j <=g; j++){
            list.locate(j);
	    }
        long stop = System.currentTimeMillis();
        System.out.println("Operation took " + ((stop-start)) + " milliseconds");
	}
	System.out.println("Done.");
        }
        
    public static void main(String [] args) {
        try {
            new P2Tester2A();
            }
        catch(Exception e) {
            System.out.println("ERROR " + e);
            e.printStackTrace();
            }
        }
    }
