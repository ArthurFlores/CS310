/*
Arthur Flores
masc0809

*/
package data_structures;

import java.util.Iterator;

public class Stack<E> implements Iterable<E> {
    private LinearListADT<E> list;
    
    public Stack() {
    	list=new LinearList<E>();
    }                      
    public void push(E obj) {
    	list.addFirst(obj);
    }
    public E pop() {
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
