/*  Your name
    Your masc account number
*/

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface LinearListADT<E> extends Iterable<E> {
    public static final int DEFAULT_MAX_CAPACITY = 100;

//  Adds the Object obj to the end of list.  
    public void addLast(E obj);
    
//  Adds the Object obj to the beginning of list.  
    public void addFirst(E obj);    
    
//  Inserts the Object obj at the position indicated.  If there is an element at
//  that location, all elements from that location to the end of the list are 
//  shifted down to make room for the new insertion.  The location is one based.
//  If the location > size()+1 then a RuntimeException is thrown. List elements 
//  must be contiguous.
    public void insert(E obj, int location);    

//  Removes the object located at the parameter location (one based).
//  Throws a RuntimeException if the location does not map to a valid position within the list.
    public E remove(int location);
    
//  Removes and returns the parameter object obj from the list if the list contains it, null otherwise.
//  The ordering of the list is preserved.  The list may contain duplicate elements.  This method
//  removes and returns the first matching element found when traversing the list from first position.
    public E remove(E obj);
    
//  Removes and returns the parameter object obj in first position in list if the list is not empty,  
//  null if the list is empty. The ordering of the list is preserved.
    public E removeFirst();   
    
//  Removes and returns the parameter object obj in last position in list if the list is not empty, 
//  null if the list is empty. The ordering of the list is preserved.
    public E removeLast();             

//  Returns the parameter object located at the parameter location position (one based).
//  Throws a RuntimeException if the location does not map to a valid position within the list.
    public E get(int location);      

//  Returns true if the parameter object obj is in the list, false otherwise.
    public boolean contains(E obj);  
    
//  Returns the one based location of the parameter object obj if it is in the list, -1 otherwise.
//  In the case of duplicates, this method returns the element closest to position #1.
    public int locate(E obj);       

//  The list is returned to an empty state.
    public void clear();

//  Returns true if the list is empty, otherwise false
    public boolean isEmpty();

//  Returns the number of Objects currently in the list.
    public int size();
    
//  Returns an Iterator of the values in the list, presented in
//  the same order as the underlying order of the list. (position #1 first)
    public Iterator<E> iterator();
    
}
