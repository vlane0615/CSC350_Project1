package project;
import java.util.concurrent.TimeUnit;

public class driver {
	//create a new array to track
	static pcb2[] pcb2_array = new pcb2[100];
	
	//create an array to keep track of 
	static pcb[] pcb_array = new pcb[20];
	static void create2(int parent) {
		for(int i = 0; i < pcb2_array.length; i++) {
			if(pcb2_array[i] == null) {
				pcb2_array[i] = new pcb2();
				pcb2_array[i].parent = parent;
				if(pcb2_array[parent].first_child == -1) {
					pcb2_array[parent].first_child = i;
				}
				else {
					int child = pcb2_array[parent].first_child;
					while(pcb2_array[child].younger_sibling != -1) {
						child = pcb2_array[child].younger_sibling;
					}
					//tell younger sibling that there is a new child
					pcb2_array[child].younger_sibling = i;
					//tell new child who its older sibling is
					pcb2_array[i].older_sibling = child;
					
				}
				break;
			}
			
		}
	}
	
	static void destroy2(int parent) {
		int child = pcb2_array[parent].first_child;
		while(child != -1) {
			destroy2(child);
			int temp = child;
			child = pcb2_array[child].younger_sibling;
			pcb2_array[temp] = null;
		}
		pcb2_array[parent].first_child = -1;
	}
	
	
	static void create(int parent){
		//allocate a free PCB[q] (new PCB)
		for(int i = 0; i < pcb_array.length; i++) {
			if(pcb_array[i] == null) {
				pcb_array[i] = new pcb();
				//record parent of q as p
				pcb_array[i].parent = parent;
				//we pass in p, q is [i] where a parent was not allocated
				//children = linked list belonging to a pcb with its children
				pcb_array[i].children.clear();
				pcb_array[parent].children.add(i);
				break;
			}
		
		}
		
	}

	static void destroy(int parent) {
		for(int i = 0; i < pcb_array[parent].children.size(); i++) {
		//call destroy on child recursively
			int child = pcb_array[parent].children.get(i);
			destroy(child);
			//deallocated element q
			pcb_array[child] = null;
		}
		pcb_array[parent].children.clear();
		
	}


	//main is test function
	public static void main (String args[]) {
		pcb_array[0] = new pcb();
		pcb2_array[0] = new pcb2();
		long startTime = System.nanoTime();
		 /* ... the code being measured starts ... */
				create(0);   /* creates 1st child of PCB[0] at PCB[1]*/
				create(0);   /* creates 2nd child of PCB[0] at PCB[2]*/
				create(2);  /* creates 1st child of PCB[2] at PCB[3] */
				create(0);   /* creates 3rd child of PCB[0] at PCB[4] */
				create(2);
				create(2);
				create(2);
				//destroy test case
				destroy(0);
			
				

				
        
        /* ... the code being measured ends ... */
 
        long endTime = System.nanoTime();
 
        long timeElapsed = endTime - startTime;
 
        System.out.println("LL Execution time in microsecond" + timeElapsed/1000);
	
	
        startTime = System.nanoTime();
		 /* ... the code being measured starts ... */
				create2(0);   /* creates 1st child of PCB[0] at PCB[1]*/
				create2(0);   /* creates 2nd child of PCB[0] at PCB[2]*/
				create2(2);  /* creates 1st child of PCB[2] at PCB[3] */
				create2(0);   /* creates 3rd child of PCB[0] at PCB[4] */
				create2(2);
				create2(2);
				create2(2);
				//destroy test case
				destroy2(0);
	
				
       
       /* ... the code being measured ends ... */

       endTime = System.nanoTime();

       timeElapsed = endTime - startTime;

       System.out.println("Sibling pointer execution time in microsecond" + timeElapsed/1000);
	
	
	
	
	}
	
	//Version 2 
	//create PCB 2 class
	//Rewrite create and destroy with new data representation.
	//if pcb[parent] is null...return
	//make test case better
	//add error check (print)
	

}
