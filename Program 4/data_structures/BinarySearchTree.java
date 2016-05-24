package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class BinarySearchTree<K,V> implements DictionaryADT<K,V> {
    private Node<K,V> root;
    private int currentSize;
    private long modCounter;
    private K searchedKey;
    
    public BinarySearchTree(){
    	root = null;
	currentSize = 0;
	modCounter = 0;
	searchedKey = null;
    }
// Returns true if the dictionary has an object identified by
// key in it, otherwise false.
    public boolean contains(K key){
    	return find(key,root) != null;
    }


// Adds the given key/value pair to the dictionary.  Returns 
// false if the dictionary is full, or if the key is a duplicate.
// Returns true if addition succeeded.
    public boolean add(K key, V value){
    	if(contains(key))
	   return false;
    	if(root == null)
	    root = new Node<K,V>(key,value);
	else
	    insert(key,value,root,null,false);
	currentSize++;
	modCounter++;
    	return true;
    }

// Deletes the key/value pair identified by the key parameter.
// Returns true if the key/value pair was found and removed,
// otherwise false.
    public boolean delete(K key){
        if(isEmpty()) return false;
	if(!remove(key,root,null,false))
	    return false;
	currentSize--;
	modCounter++;
	return true;
    }
    
// Returns the value associated with the parameter key.  Returns
// null if the key is not found or the dictionary is empty.
    public V getValue(K key){
        if(isEmpty()) 
	    return null;
    	return find(key,root);
    }

// Returns the key associated with the parameter value.  Returns
// null if the value is not found in the dictionary.  If more 
// than one key exists that matches the given value, returns the
// first one found. 
    public K getKey(V value){
        if(isEmpty())
	    return null;
	searchedKey = null;
	findKey(value,root);
	return searchedKey;
    }

// Returns the number of key/value pairs currently stored 
// in the dictionary
    public int size(){
    	return currentSize;
    }

// Returns true if the dictionary has a max capacity and is at max capacity
    public boolean isFull(){
    	return false;
    }

// Returns true if the dictionary is empty
    public boolean isEmpty(){
    	return currentSize==0;
    }

// Returns the Dictionary object to an empty state.
    public void clear(){
    	root=null;
	currentSize=0;
	modCounter++;
    }

// Returns an Iterator of the keys in the dictionary, in ascending
// sorted order.  The iterator must be fail-fast.
    public Iterator<K> keys(){
    	return new KeyIteratorHelper();
    }

// Returns an Iterator of the values in the dictionary.  The
// order of the values must match the order of the keys. 
// The iterator must be fail-fast. 
    public Iterator<V> values(){
    	return new ValueIteratorHelper();
    }
    
//====================================================================
//private methods

//insert is a recursive method that inserts a key in value in the correct spot in 
//the binary search tree. Has no return value.
    private void insert(K k, V v, Node<K,V> n, Node<K,V> parent, boolean wasLeft){
    	if(n==null){				//at leaf node for insert
	    if(wasLeft) 
	        parent.leftChild = new Node<K,V>(k,v);
	    else 
	        parent.rightChild = new Node<K,V>(k,v);
	}
	else if(((Comparable<K>) k).compareTo((K)n.key) < 0)
	    insert(k,v,n.leftChild,n,true);	//go left
	else
	    insert(k,v,n.rightChild,n,false);	//go right
    }
//find searches for the key in the dictionary and returns the value. If the key
//is not there it returns null.
    private V find(K key, Node<K,V> n){
        if(n == null) 
	    return null;
	int comp = ((Comparable<K>)key).compareTo(n.key);
	if(comp < 0)
	    return find(key,n.leftChild);	//go left
	else if(comp > 0)
	    return find(key,n.rightChild);	//go right
	else
	    return (V) n.value;			//found it
    }
//remove searches for a key in the dictionary and deletes it. If key
//is not found false is returned.
    private boolean remove(K k, Node<K,V> n, Node<K,V> parent, boolean wasLeft){
       if(n == null) return false;
       int comp = ((Comparable<K>)k).compareTo(n.key);
       if(comp < 0)
           return remove(k,n.leftChild,n,true);		//go left
       else if(comp > 0)
           return remove(k,n.rightChild,n,false);	//go right
       else{						//found!
           if(n.leftChild == null && n.rightChild == null){	//no children
	       if(parent == null)
	           root = null;
	       else if(wasLeft)
	           parent.leftChild = null;
	       else
	           parent.rightChild = null;
	   }
	   else if(n.leftChild == null){	//1 right child
	       if(parent == null)
	           root = n.rightChild;
	       else if(wasLeft)
	           parent.leftChild = n.rightChild;
	       else
	           parent.rightChild = n.rightChild;
	   }
	   else if(n.rightChild == null){	//1 left child
	   	if(parent == null)
		    root = n.leftChild;
		else if(wasLeft)
		    parent.leftChild = n.leftChild;
		else
		    parent.rightChild = n.leftChild;
	   }
	   else{				//two children
	   	Node<K,V> tmp = getSuccessor(n.rightChild);
		if(tmp == null){
		    n.key = n.rightChild.key;
		    n.value = n.rightChild.value;
		    n.rightChild = n.rightChild.rightChild;
		}
		else{
		    n.key = tmp.key;
		    n.value = tmp.value;
		}
	   }
       }
       return true;
    }
// getSuccessor method returns the successor in the dictionary.
    private Node<K,V> getSuccessor(Node<K,V> n){
    	Node<K,V> parent = null;
	while(n.leftChild != null){
	    parent = n;
	    n = n.leftChild;
	}
	if(parent == null)
	    return null;
	else
	    parent.leftChild = n.rightChild;
	return n;
    }
//findKey searches for a value in the dictionary and then once found
//returns the key associated with that value. If value is not 
//found it returns null.
    private void findKey(V v, Node<K,V> n){
        if(n == null) 
	    return;
	if(((Comparable<V>)v).compareTo((V)n.value) == 0){
	    searchedKey = n.key;
	    return;
	}
	    findKey(v,n.rightChild);	        //go left
	    findKey(v,n.leftChild);		//go right
    }
	
//====================================================================
//node
    private class Node<K,V>{
        private K key;
	private V value;
	private Node<K,V> leftChild,rightChild;
	
	public Node(K k, V v){
	    key=k;
	    value=v;
	    leftChild=rightChild=null;
	}
     }
     
     
//======================================================================
//Iterators     
     abstract class IteratorHelper<E> implements Iterator<E>{
	protected int idx,index;
	protected long modCheck;
	protected Node<K,V> [] nodeArray;
	
	public IteratorHelper(){
	    nodeArray=new Node[currentSize];
	    index=idx=0;
	    modCheck=modCounter;
	    orderArray(root);
	}
	public boolean hasNext(){
	    if(modCheck != modCounter)
	    	throw new ConcurrentModificationException(); 
	    return idx < currentSize;
	}
	public abstract E next();
	   
	public void remove(){
	    throw new UnsupportedOperationException();
	}	
	private void orderArray(Node<K,V> n){
            if(n!=null){
	        orderArray(n.leftChild);
	        nodeArray[index++] =  n;
                orderArray(n.rightChild);
	    }
        }
    }	
    
    class KeyIteratorHelper<K> extends IteratorHelper<K>{
    	public KeyIteratorHelper(){
	    super();
	}
	public K next(){
	    if(!hasNext())
	        throw new NoSuchElementException();
	    return (K) nodeArray[idx++].key;
	}
    }
    
    class ValueIteratorHelper<V> extends IteratorHelper<V>{
        public ValueIteratorHelper(){
	    super();
	}
	public V next(){
            if(!hasNext())
	        throw new NoSuchElementException();
	    return (V) nodeArray[idx++].value;
	}
    }
	
}
