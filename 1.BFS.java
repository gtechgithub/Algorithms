package com;

import java.util.LinkedList;
import java.util.Queue;

/** Algorithm 

1. it uses Queue mechanism
2. it find out the shortest path and non repeating path



Step 1: add the start node into the queue

Step 2: while queue is available continue the loop

		Step 2.1: add the element of the queue in the visited queue and remove from queue
		
		Step 2.1: get the neighbour nodes for the queue element
		
		Step 3.1 while neigbours node is avaiable loop
		
			     Step 3.2 add the neighbour node into the queue if it is not visited and not available in the queue
		

Step 3: print the visited nodes 


 
****/

class BFSGraph {

	//this will contains all the nodes
	private LinkedList<Integer>[] nodesContainer;

	//this will contain the visted nodes
	private LinkedList<Integer> vistedNotes;

	private Queue<Integer> queue;

	public BFSGraph(int size) {
		nodesContainer =  new LinkedList[size];
		queue = new LinkedList();
		vistedNotes = new LinkedList();
	}
	
	public void addNodes(int node, int neighbour) {
		//initialize the linked list for the one neigbour of the node
		if (nodesContainer[node] == null) {
			nodesContainer[node] =  new LinkedList();
		}
		nodesContainer[node].add( Integer.valueOf(neighbour));
	}

	private LinkedList getLinkedListForArray(int node) {

		//get the linkedList for given node
		return nodesContainer[node];
	}
	
	public void printVisitedNodes() {
		
		System.out.println(vistedNotes);
	}
	
	public void findOutBFS(int startNode) {
	
		//add first node into the queue
		queue.add(Integer.valueOf(startNode));

		//loop while elements in queue is available
		while(!queue.isEmpty()) {
			
			//peek to just add and it will not remove from the queue
			int element = queue.peek();
			vistedNotes.add(element);
			
			//poll from the queue now to remove the element
			queue.poll();

			//get the linked list for the given nodes
			LinkedList listToTraverse = getLinkedListForArray(element);
			
			//while the linked list has the elements for the array
			while (!listToTraverse.isEmpty()) {
				
				Integer neighbour = (Integer) listToTraverse.peek();
				//if the node is not visited and not available in the queue then added into the queue
				if (! vistedNotes.contains(neighbour) && !queue.contains(neighbour) ) {
					queue.add(neighbour);
				}
				//remove the element from queue
				listToTraverse.poll();
			}
		}
	}


}

public class BFS {
	public static void main(String args[]) {
		
		BFSGraph graph = new BFSGraph(4);
		
		graph.addNodes(0, 1);
		graph.addNodes(0, 2);
		
		graph.addNodes(1, 2);
		
		graph.addNodes(2, 0);
		graph.addNodes(2, 3);
		
		graph.addNodes(3, 3);

		//findout BFS
		graph.findOutBFS(0);
		
		//print visisted nodes
		graph.printVisitedNodes();
		
	}
}



/************* output ****************


graph.findOutBFS(2);

2, 0 , 3 1

graph.findOutBFS(0);

0, 1 , 3 
*****************************************/
