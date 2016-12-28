/**Name: Zimu Cui.  zimucui@gmai.com
 *Class: COSI 21A
 *Programming Assignment #3, 12/10/2015
 *Description: This program forms a test class that execute the required result
 */

import java.io.*;
import java.util.*;


/**O(n)*/
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.println("name of the input file");
		String file = console.next();
		Scanner input = new Scanner(new File(file));      /**input a file*/
		MinPriorityQueue queue = new MinPriorityQueue(62887);
		HashTable hashtable = new HashTable(2029);
		while (input.hasNext()) {
			int id = input.nextInt();
			int priority = input.nextInt();
			String name = input.next();
			
			Process process = new Process(id,priority,name);     /**test whether the id has existed or not*/
			if (hashtable.search(id) == null) {
				queue.enqueue(process);
				hashtable.insert(process);
			}
		}
		
		while (queue.size() > 21) {
			Process waste = queue.deleteMin();
			waste = hashtable.delete(waste.getID());
		}
		
		Process last = queue.deleteMin();                    /**print the result*/
		String result = hashtable.delete(last.getID()).getName();
		while (queue.size() != 0) {
			Process last1 = queue.deleteMin();
			result = hashtable.delete(last1.getID()).getName() + " "  + result;
		}
		
		System.out.println(result);
	}
}

