/**
 * File: PriorityQueueHeap.java
 * Description: A PriorityQueue with a BinaryHeap data structures implemented
 * @author Anthony Turner
 *
 */
public class PriorityQueueHeap<E> {

    //Member variables
    private Object[] data;
    private Integer[]priority;
    private int current_size;

    /**
     * Initialize an empty priorityQueue with a given size
     * Precondition: size is >= 0
     * Postcondition: This priority queue is empty
     * @param size the current size of this queue
     * @throws IllegalArgumentException if the size is negative
     */
    public PriorityQueueHeap(int size){

        if (size < 0 )
            throw new IllegalArgumentException("Invalid size. Must be a positive value");

        data = new Object[size];
        priority = new Integer[size];
        current_size = 0;

    }


       /*
     * *****************************************************************************************
     *
     *                                  Fundamental and unique methods
     *
     * * ***************************************************************************************
     */

    /**
     * Adds a new item with the given priority to the Priority Queue
     * @param item The data of the element to add
     * @param selected_priority the element's priority
     * @throws IllegalArgumentException if the priority is negative or is > the highest priority
     */
    public void add(E item, int selected_priority){

        if( selected_priority < 0)
            throw new IllegalArgumentException("Illegal priority");


        if( current_size >= data.length){
            increaseArray();
        }

        int current_index = current_size;

        data[current_index] = item;
        priority[current_index] = selected_priority;
        current_size++;
        reheapUp(current_index);

    }

    private void increaseArray() {

        //Double the current array size
        Object newDataArr[] = new Object[data.length*2];
        Integer newPriorityArr[] = new Integer[data.length*2];

        for(int i = 0; i < data.length; i++){

            newDataArr[i] = data[i];
            newPriorityArr[i] = priority[i];
        }

        data = newDataArr;
        priority = newPriorityArr;

    }


    /*
     * Swaps each child with its parent if the child's priority is > parent
     */
    private void reheapUp(int index){

        if( index <= 0)
            return;

        int parent_of_index = getParentIndex(index);

        while( priority[index] > priority[parent_of_index] ){

            swap(index, parent_of_index );

            index = parent_of_index;
            parent_of_index = getParentIndex(index);

        }
    }

    /**
     *
     */
    public Object remove(){

        int index = 0;
        Object removed_data = data[index];

        int last_index = current_size -1;
        //Swap the last element putting it at the root
        data[index] = data[last_index];
        priority[index] = priority[last_index];

        data[last_index] = null;
        priority[last_index] = null;
        current_size--;

        printArray();
        printHeap();
        reheapDown(index);

        return removed_data;
    }


    private void reheapDown(int index){


        //Ensure the parent has at least a left child
        while( (getLeftChildIndex(index) < current_size) ){

            int greater_child_index;
        	int left_child_index = getLeftChildIndex(index);
            
        	if( getRightChildIndex(index) < current_size){//Determine if there is a right child to compare.
        		
            	int right_child_index = getRightChildIndex(index);
            	
            	 //Determine which child is the greater
                if(  hasHigherPriority(right_child_index, left_child_index ))
                    greater_child_index = right_child_index;

                else if(  hasHigherPriority(left_child_index, right_child_index )){
                    greater_child_index = left_child_index;

                }else{
                	
                	return;
                }

        	}else{//Otherwise, there isn't a right child, so the greater child is the left.
        		
        		greater_child_index =left_child_index; 
              
           	}
        	
        	//Compare the parent with the greater child
        	 if( hasHigherPriority(index, greater_child_index)){
             	//If the parent is the greater, then there is nothing to swap
             	return;
             }
            
            swap(index, greater_child_index);
            index = greater_child_index;
            printHeap();

        }
    }



    //Swaps two nodes
    private void swap(int index, int parent_index) {

        //Swap the child and parent's location
        Object child_node = data[index];//Get the child
        int node_priority = priority[index];

        data[index] = data[parent_index];//place the parent where the child was
        priority[index] = priority[parent_index];

        data[parent_index] = child_node;//place the child where the parent was
        priority[parent_index] = node_priority;

    }


       /*
     * *****************************************************************************************
     *
     *                                  Get and set methods
     *
     * * ***************************************************************************************
     */

    public int getParentIndex(int index){

        return (index -1) /2;
    }


    public int getLeftChildIndex(int index){

        return 2*index + 1;
    }


    public int getRightChildIndex(int index){

        return 2*index + 2;
    }




    /*
     * *****************************************************************************************
     *
     *                                  Print/display methods
     *
     * * ***************************************************************************************
     */

	public void printArray(){

		//Array
		System.out.print("Heap array: ");

		for(int i=0; i < current_size; i++)
			if(priority[i] != null)
				System.out.print( priority[i] + " ");
			else
				System.out.print( "-- ");

		System.out.println();

	}

	public void printHeap(){

		// heap format
		int indents = 32;
		int num_nodes = 1;
		int column = 0;
		int node_index = 0; 
		String spacer = "=============================================";
		System.out.println(spacer+spacer);

		while(node_index < current_size){

			if(column == 0)                  // first item in level?
				for(int i=0; i<indents; i++)  // preceding blanks
					System.out.print(' ');
			
			// display item
			System.out.print(priority[node_index++]);

			if(node_index == current_size)
				continue;

			column++;
			if(column==num_nodes){        // end of level?
				
				indents /= 2;               // half the blanks
				num_nodes *= 2;             // twice the items  (2^n)
				column = 0;                 // start over on new level
				System.out.println();
                System.out.println();

			}
			else                             // next node on level
				for(int i=0; i<indents*2-2; i++)
					System.out.print(' ');
		}  // end for
		System.out.println("\n"+spacer+spacer); // space for separating bottom line
	}


        public void showHeap(){

            printArray();
            printHeap();

        }

    /*
     * *****************************************************************************************
     *
     *                                  Predicate/boolean methods
     *
     * * ***************************************************************************************
     */

    /**
     * Determines if one index has a higher priority than another
     * @param index_one  the index to determine if it has higher priority than the other
     * @param index_two  the index to determine if it has a lower priority than the other
     * @return returns true if index one has a higher priority than index two
     * or false if index two has a higher priority than index one
     */
    public boolean hasHigherPriority(int index_one, int index_two){

        return priority[index_one] > priority[index_two]  ? true : false;
    }


}
