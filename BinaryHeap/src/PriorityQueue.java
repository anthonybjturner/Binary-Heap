import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * File: PriorityQueue.java
 * Description: A queue with priorities assigned to each item
 * This implementation differs from the PriorityQueueHeap
 *
 * @author Anthony Turner
 *
 */
public class PriorityQueue<E> {

	//Member variables
	private Queue<E>[] queues;
	private int total_size;
	private int current_highest_priority;//The index of the highest priority
	
	/**
	 * Initialize an empty priority queue with a maximum priority
	 * Precondition: max_priority is >= 0
	 * Postcondition: This priority queue is empty
	 * @param max_priority the highest priority allowed in this priority queue
	 * @throws IllegalArgumentException if the max priority is negative
	 */
	@SuppressWarnings("unchecked")
	public PriorityQueue(int max_priority){
		
		if (max_priority < 0 )
			throw new IllegalArgumentException("Invalid maximum priority. Must be a positive value");
		
		queues = new Queue[max_priority+1];//(Queue<E>[]) new Object[max_priority+1];
		total_size = 0;
		current_highest_priority = max_priority;
		
		initQueues();
		
	}

	//Initialize the PriorityQueue
	private void initQueues() {
		
		for(int i = 0; i < queues.length; i++){
			
			queues[i] = new LinkedList<E>();
			
		}
	}

	/**
	 * Gets the number of items in this Priority Queue
	 * @return the number of items in the queue
	 */
	public int getSize() {
		return total_size;
	}

	/**
	 * Gets the index of the highest priority in this PriorityQueue
	 * @return the highest priority
	 */
	public int getHighestPriority() {
		return current_highest_priority;
	}

	/**
	 * Sets the highest priority for this PriorityQueue
	 * @param highest_priority the index of the highest priority
	 */
	public void setHighestPriority(int highest_priority) {
		this.current_highest_priority = highest_priority;
	}
	
	/**
	 * Adds a new item with the given priority
	 * @param item The item to add
	 * @param priority the item's priority
	 * @throws IllegalArgumentException if the priority is negative or is > the highest priority
	 */
	public void add(E item, int priority){
		
		if( priority < 0 || priority > current_highest_priority)
			throw new IllegalArgumentException("Illegal priority");
		
		queues[priority].add(item);
		total_size++;
	}
	
	/**
	 * Removes the highest priority item from the priority queue.
	 * 
	 * @return returns the highest priority item in queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public E remove(){
		
		if( total_size == 0)
			throw new NoSuchElementException("Empty priority queue");
				
		findNonEmptyQueue();

		E item = queues[current_highest_priority].remove();
		total_size--;
			
		return item;
	}
	
	/*
	 * 	Searches for a non-empty queue
	 *  @throws NoSuchElementException if the priority queue is empty
	 */
	private void findNonEmptyQueue() {
		
		while( isCurrentQueueEmpty() ){
			
			if( current_highest_priority <0 ){
				
				throw new NoSuchElementException("Empty priority queue");
			}
			current_highest_priority--;
		}
	}

	/**
	 * Determines if the current highest priority is empty
	 * Precondition: The priority queue has items and is not empty
	 * @return returns true if the current highest priority is empty or false if not
	 */
	public boolean isCurrentQueueEmpty(){
		
		return queues[current_highest_priority].isEmpty();
 
	}
	
	public void printQueues(){
		
		for(int i = queues.length-1; i >= 0; i--){
			
			print("Priority " + i + ": " );
			
			Iterator<E> it = queues[i].iterator();
			
			while( it.hasNext() ){
				
				E item = it.next();
				print("[" + item.toString() + "] " );
			}
			
			println("");
			
		}
		
	}
	
	public void println(String msg){
		
		System.out.println(msg);
	}
	
	public void print(String msg){
		
		System.out.print(msg);
	}
	
	
}
