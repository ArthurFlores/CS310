package data_structures;

import java.util.Iterator;
import java.util.TreeMap;

public class RedBlackTree<K,V> implements DictionaryADT<K,V>{
    TreeMap redBlackTree;
    K key;
    V value;
    public RedBlackTree(){
    	redBlackTree = new TreeMap();
	key=null;
	value=null;
    }
// Returns true if the dictionary has an object identified by
// key in it, otherwise false.
    public boolean contains(K key){
    	return redBlackTree.containsKey(key);
    }


// Adds the given key/value pair to the dictionary.  Returns 
// false if the dictionary is full, or if the key is a duplicate.
// Returns true if addition succeeded.
    public boolean add(K key, V value){
        if(redBlackTree.containsKey(key))
    	    return false;
	else{
	    redBlackTree.put(key,value);
	    return true;
	}
    }

// Deletes the key/value pair identified by the key parameter.
// Returns true if the key/value pair was found and removed,
// otherwise false.
    public boolean delete(K key){
    	return redBlackTree.remove(key) != null;
    }

// Returns the value associated with the parameter key.  Returns
// null if the key is not found or the dictionary is empty.
    public V getValue(K key){
        if(isEmpty() || !contains(key))
	    return null;
    	return (V) redBlackTree.get(key);
    }

// Returns the key associated with the parameter value.  Returns
// null if the value is not found in the dictionary.  If more 
// than one key exists that matches the given value, returns the
// first one found. 
    public K getKey(V value){
        key = null;
        Iterator<V> values = values();
        Iterator<K> keys = keys();
        while(values.hasNext()) {
            if(((Comparable<V>)values.next()).compareTo(value) == 0) {
                key = keys.next();
                return key;
            }
            keys.next();
        }
        return null;
    }

// Returns the number of key/value pairs currently stored 
// in the dictionary
    public int size(){
    	return redBlackTree.size();
    }

// Returns true if the dictionary has a max capacity and is at max capacity
    public boolean isFull(){
    	return false;
    }

// Returns true if the dictionary is empty
    public boolean isEmpty(){
    	return redBlackTree.isEmpty();
    }

// Returns the Dictionary object to an empty state.
    public void clear(){
    	redBlackTree.clear();
    }

// Returns an Iterator of the keys in the dictionary, in ascending
// sorted order.  The iterator must be fail-fast.
    public Iterator<K> keys(){
    	return redBlackTree.navigableKeySet().iterator();
    }

// Returns an Iterator of the values in the dictionary.  The
// order of the values must match the order of the keys. 
// The iterator must be fail-fast. 
    public Iterator<V> values(){
    	return redBlackTree.values().iterator();
    }

}
