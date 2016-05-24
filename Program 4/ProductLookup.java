import java.util.Iterator;
import data_structures.*;

public class ProductLookup {
    private DictionaryADT<String,Double> dictionary;

    // creates a new Dictionary with max capacity.
    // suitable for hashtable implementations
    public ProductLookup(int capacity) {
        dictionary = new HashTable<String,Double>(capacity);
        }
        
    // creates a new Dictionary with no max capacity.
    // suitable for tree implementations
    public ProductLookup() {
    	dictionary=new BinarySearchTree<String,Double>();
	//dictionary = new RedBlackTree<String,Double>();
        }        

    // inserts a new Product and its price
    public boolean insertSku(String sku, Double price) {
    	return dictionary.add(sku,price);
        }

    // removes the key value pair that is identified by the key from the dictionary
    public boolean deleteSku(String sku) {
    	return dictionary.delete(sku);
        }

    // looks up the price of an Product
    public Double getPrice(String sku) {
    	return dictionary.getValue(sku);
        }
        
    // returns the first sku found with the given price, null otherwise
    public String getSku(Double price) {
    	return dictionary.getKey(price);
        }

    // returns true if the sku is already in the dictionary
    public boolean containsSku(String sku) {
    	return dictionary.contains(sku);
        }
    
    // returns all of the keys in the dictionary within the range start .. finish
    // inclusive, in sorted order. Neither value 'start' or 'finish' need be in the
    // dictionary.  Returns null if there are no keys in the range specified.    
    public String[] getRange(String start, String finish) {
    
    	LinearListADT<String> list = new LinearList<String>();
        Iterator<String> key = this.sku_s();
	int i=0;
	String [] array;
        while(key.hasNext()){
            String next = (String) key.next();
            if(next.compareTo(start) >=0 && next.compareTo(finish) <=0)
                list.addLast(next);
        }
	if(list.isEmpty()) return null;
	array = new String[list.size()];
        for(String entry: list)
            array[i++]=entry;
        return array;
        }
            
    // returns an Iterator of the sku's (the keys) in the dictionary,
    // in sorted order.
    public Iterator<String> sku_s() {
    	return dictionary.keys();
        }

    // returns the prices in the dictionary, in exactly the same order
    // as the sku_s() Iterator
    public Iterator<Double> prices() {
    	return dictionary.values();
        }
}   
