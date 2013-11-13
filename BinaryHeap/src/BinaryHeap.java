import java.util.Random;


/**
 * <b>File:</b> BinaryHeap.java
 * <b>Description: Binary Heap program uses a Binary Heap data structure within a PriorityQueue</b> 
 * @author Anthony Turner
 * <b>Course:</b> Advanced Data Structures CSIII
 *
 */
public class BinaryHeap {

	private PriorityQueueHeap<Integer>p_queue;
 
	public BinaryHeap(int size){
		
		p_queue = new PriorityQueueHeap<Integer>(size);
		start(size);
	}
	
	
    private void start(int size) {
		
    	final int MAX_VALUE = 1000;
    	Random rand = new Random();
    	
    	System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("********************************************************************************************************\n");
		System.out.println("        					 Stage 1\n");
		System.out.println("********************************************************************************************************\n");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

    	
		for(int i = 0; i < size; i++){
    		
    		int priority = rand.nextInt(MAX_VALUE);
    		p_queue.add(0, priority);
    		p_queue.showHeap();

    	}

    	System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("********************************************************************************************************\n");
		System.out.println("        					 Stage 2\n");
		System.out.println("********************************************************************************************************\n");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		
		for(int i = 0; i < size; i++){
    		
    		p_queue.remove();

    	}

	}

    public static void main(String[] args){

    	final int NUM_ITEMS = 20;
    	
    	new BinaryHeap(NUM_ITEMS);
    }
}
