/*
Arthur Flores
masc0809

*/
package data_structures;

import java.util.Iterator;

public class Queue<E> implements Iterable<E> {
    private LinearListADT<E> list;
    
    public Queue() {
    	list=new LinearList<E>();
    }
    public void enqueue(E obj) {
    	list.addLast(obj);
    }   
    public E dequeue() {
    	return list.removeFirst();
    }   
    public int size() {
    	return list.size();
    }
    public boolean isEmpty() {
    	return list.isEmpty();
    }
    public E peek() {
    	return list.get(1);
    }
    public boolean contains(E obj) {
    	return list.contains(obj);
    }
    public void makeEmpty() {
    	list.clear();
    } 
    public Iterator<E> iterator() {
    	return list.iterator();
     }
  }
