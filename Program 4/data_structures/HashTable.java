package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class HashTable<K,V> implements DictionaryADT<K,V> {
    private LinearListADT<DictionaryNode<K,V>> [] list;
    int maxSize;
    int tableSize;
    int currentSize,modCounter;
    public HashTable (int max){
    	modCounter=0;
    	maxSize=max;
	tableSize=(int)(max*1.3f);
	list= new LinearList[tableSize];
	for(int i=0; i<tableSize; i++)
		list[i] = new LinearList<DictionaryNode<K,V>>();
    }
//Gets the hash code for the given key. mods value by tablesize so there is no negatives.
    private int getHashCode(K key){
    	return (key.hashCode() & 0x7FFFFFFF)%tableSize;
    }
// Returns true if the dictionary has an object identified by
// key in it, otherwise false.
    public boolean contains(K key){
    	return list[getHashCode(key)].contains(new DictionaryNode<K,V>(key,null));
    }


// Adds the given key/value pair to the dictionary.  Returns 
// false if the dictionary is full, or if the key is a duplicate.
// Returns true if addition succeeded.
    public boolean add(K key, V value){
    	if(isFull() || contains(key)) 
		return false;
	list[getHashCode(key)].addFirst(new DictionaryNode<K,V>(key,value));
	currentSize++;
	modCounter++;
	return true;
    }

// Deletes the key/value pair identified by the key parameter.
// Returns true if the key/value pair was found and removed,
// otherwise false.
    public boolean delete(K key){
        if(isEmpty() || !contains(key))
	    return false;
        list[getHashCode(key)].remove(new DictionaryNode<K,V>(key,null));
	currentSize--;
	modCounter++;
	return true;
    }

// Returns the value associated with the parameter key.  Returns
// null if the key is not found or the dictionary is empty.
    public V getValue(K key){
        if(isEmpty() || !contains(key))
	    return null;
    	DictionaryNode<K,V> tmp= list[getHashCode(key)].get(list[getHashCode(key)].locate(new DictionaryNode<K,V>(key,null)));
	if(tmp==null) return null;
    	return tmp.value;
    }

// Returns the key associated with the parameter value.  Returns
// null if the value is not found in the dictionary.  If more 
// than one key exists that matches the given value, returns the
// first one found. 
    public K getKey(V value){
        if(isEmpty())
	    return null;
        for (int i = 0; i < list.length; i++) {
            Iterator<DictionaryNode<K, V>> items = list[i].iterator();
            while (items.hasNext()) {
                DictionaryNode<K, V> tmp = items.next();
                if (((Comparable<V>)tmp.value).compareTo((V)value)==0)
                    return (K)tmp.key;
            }
        }
        return null;
    }

// Returns the number of key/value pairs currently stored 
// in the dictionary
    public int size(){
    	return currentSize;
    }

// Returns true if the dictionary has a max capacity and is at max capacity
    public boolean isFull(){
    	return currentSize==maxSize;
    }

// Returns true if the dictionary is empty
    public boolean isEmpty(){
    	return currentSize==0;
    }

// Returns the Dictionary object to an empty state.
    public void clear(){
    	for(LinearListADT x: list)
	    x.clear();
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
    
    abstract class IteratorHelper<E> implements Iterator<E>{
    	protected DictionaryNode<K,V> [] nodes;
	protected int index;
	protected long modCheck;
	
	public IteratorHelper(){
	    nodes=new DictionaryNode[currentSize];
	    index=0;
	    int j=0;
	    modCheck=modCounter;
	    for(int i=0; i<tableSize; i++)
	    	for(DictionaryNode n : list[i])
		    nodes[j++] = n;
	    nodes= (DictionaryNode<K,V>[]) shellSort(nodes);
	}
	public boolean hasNext(){
	    if(modCheck != modCounter)
	    	throw new ConcurrentModificationException(); 
	    return index<currentSize;
	}
	public abstract E next();
	   
	public void remove(){
	    throw new UnsupportedOperationException();
	}	
    }	
    
    class KeyIteratorHelper<K> extends IteratorHelper<K>{
    	public KeyIteratorHelper(){
	    super();
	}
	public K next(){
	    if(!hasNext())
	        throw new NoSuchElementException();
	    return (K) nodes[index++].key;
	}
    }
    
    class ValueIteratorHelper<V> extends IteratorHelper<V>{
        public ValueIteratorHelper(){
	    super();
	}
	public V next(){
            if(!hasNext())
	        throw new NoSuchElementException();
	    return (V) nodes[index++].value;
	}
    }
    
    class DictionaryNode<K,V> implements Comparable <DictionaryNode<K,V>>{
    	K key;
	V value;
	public DictionaryNode(K k, V v){
	    key=k;
	    value=v; 
	}
	public int compareTo(DictionaryNode<K,V> node){
	    return ((Comparable<K>)key).compareTo((K)node.key);
	}
     }
     
     private DictionaryNode<K,V>[] shellSort(DictionaryNode[] array){
	    DictionaryNode<K,V> [] n = array;
	    int in, out, h=1;
	    DictionaryNode<K,V> temp = null;
	    int size = n.length;
	    while(h<=size/3)
	        h =h*3+1;
	    while(h>0){
	    	for(out=h; out<size; out++){
		    temp=n[out];
		    in=out;
		    while(in>h-1 && n[in-h].compareTo(temp)>=0){
		    	n[in]=n[in-h];
			in -= h;
		    }
		n[in]=temp;
	    	}
	    h=(h-1)/3;
	    }
	return n;
    	}
	     
 }
