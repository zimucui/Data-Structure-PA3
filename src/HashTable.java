/**Name: Zimu Cui.  zimucui@gmai.com
 *Class: COSI 21A
 *Programming Assignment #3, 12/10/2015
 *Description: This program forms cuckoo hashtable that is helps to verify duplicate
 */
public class HashTable {
	private Process[] hashtable;
	private int occupied;
	private int capacity;
	
	public HashTable(int input) {
		this.hashtable = new Process[input];
		this.occupied = 0;
		this.capacity = input;
	}
	
	/**O(1)*/
	/**
    *
    * @param id
    * @return hash value 1
    */
	public int hash1(int id) {
		return (id % capacity);
	}
	
	
	
	/**O(1)*/
	/**
    *
    * @param id
    * @return hash value 2
    */
	public int hash2(int id) {
		return ((id*2 + 1) % capacity);
	}
	
	
	
	/**O(n)*/
	/**
     * increase capacity and rehash
     */
	public void rehash() {
		capacity = capacity*2;
		Process[] oldarray = new Process[capacity];
		Process[] temp = oldarray;
		
		oldarray = hashtable;       /**increase hashtable's size*/
		hashtable = temp;
		
		for (int i = 0; i < oldarray.length; i++) {
			if (oldarray[i] != null) {                 /**some slots in oldarray could be empty*/
				insert(oldarray[i]);
			}
		}
	}
	
	
	/**O(n)*/
	/**
     * insert to hash table
     * @param process
     */
	public void insert(Process input) {
		if (search(input.getID()) != null) {
			return;
		}
		
		double loadfactor = occupied/(double) capacity;
		
		if (loadfactor > 0.6) {               /**check the loadfactor to see whether to rehash or not*/
			rehash();
		}
		
		int position = hash1(input.getID());
		
		for (int i = 0; i < capacity; i++) {              /**put an upper limit here, which means we believe that with this amount of times, we can finish our job*/
			if (hashtable[position] == null) {
				hashtable[position] = input;
				occupied ++;
				return;              /**the time we stop*/
			}
			
			Process temp = hashtable[position];            /**exchange two elements*/
			hashtable[position] = input;
			input = temp;                        
			
			if (position == hash1(input.getID())) {              /**move the bumped element to a new position*/
				position = hash2(input.getID());
			} else {
				position = hash1(input.getID());
			}
		}
	}
	
	
	/**O(1)*/
	/**
     * find element by id
     * @param id
     * @return value corresponding to id
     */
	public Process search(int id) {
		int h1 = hash1(id);
		int h2 = hash2(id);
		
		if (hashtable[h1] != null && hashtable[h1].getID() == id) {
			return hashtable[h1];
		}
		
		if (hashtable[h2] != null && hashtable[h2].getID() == id) {
			return hashtable[h2];
		}
		return null;
	}
	
	/**O(1)*/
	/**
     * remove key id
     * @param id
     * @return value corresponding to id
     */
	public Process delete(int id) {
		Process result = null;
		
		int h1 = hash1(id);
		int h2 = hash2(id);
		
		if (hashtable[h1] != null && hashtable[h1].getID() == id) {
			result = hashtable[h1];
			hashtable[h1] = null;
			occupied --;
		}
		
		if (hashtable[h2] != null && hashtable[h2].getID() == id) {
			result = hashtable[h2];
			hashtable[h2] = null;
			occupied --;
		}
		return result;
	}
	
}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		