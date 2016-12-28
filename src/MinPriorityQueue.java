/**Name: Zimu Cui.  zimucui@gmai.com
 *Class: COSI 21A
 *Programming Assignment #3, 12/10/2015
 *Description: This program forms a min-priority queue that is based on element's priority
 */

import java.util.*;

public class MinPriorityQueue {
	private Process[] queue;
	private int occupied;
	
	public MinPriorityQueue(int input) {
		this.queue = new Process[input+1];
		this.occupied = 0;        //this is the heap-size
	}
	
	/**O(1)*/
	/**
    *
    * @return size of the queue
    */
	public int size() {
		return occupied;
	}
	
	/**O(logn)*/
	/**
     * add element to heap
     * @param process
     */
	public void enqueue(Process input) {
		occupied ++;
		queue[occupied] = input;
		
		int temp = occupied;  
		while (parent(temp) > 1 && queue[parent(temp)].getPriority() > queue[temp].getPriority() ) {    /**test whether to go up or not*/
			swap(temp,parent(temp));
			temp = parent(temp);
		}
	}
	
	
	/**O(1)*/
	/**
    *
    * @param i
    * @return parent index
    */
	public int parent(int input) {
		return input/2;
	}
	
	/**O(1)*/
	/**
     * swap two elements in array
     * @param x
     * @param y
     */
	public void swap(int first, int second) {
		Process temp = queue[first];
		queue[first] = queue[second];
		queue[second] = temp;
	}
	
	/**O(1)*/
	/**
    *
    * @param i
    * @return left child index
    */
	public int left(int i){
        return 2*i;
    }
	
	/**O(1)*/
	/**
    *
    * @param i
    * @return right child index
    */
	public int right(int i){
        return 2*i + 1;
	}
	
	/**O(logn)*/
	/**
     * make subtree from i a heap
     * @param i
     */
	public void heapify(int i){
		int l = left(i);
	    int r = right(i);

	    int smallest = i;

	    if (l <= occupied && queue[l].getPriority() <= queue[i].getPriority()) {
	        smallest = l;
	    }

	    if( r <= occupied && queue[r].getPriority() < queue[smallest].getPriority()) {
	            smallest = r;
	    }

	    if (smallest != i) {
	        swap(i,smallest);
	        heapify(smallest);
	    }
	}
	
	/**O(logn)*/
	/**
    *
    * @return  minimum element
    */
	 public Process deleteMin()
	    {
	        if(occupied < 1){
	            throw new NoSuchElementException("empty queue");
	        }

	        Process min = queue[1];

	        queue[1] = queue[occupied];
	        
	        occupied--;

	        heapify(1);

	        return min;

	    }
}
