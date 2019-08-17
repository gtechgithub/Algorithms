package com;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*************************


1. it uses STACK

2. it will traverse or explore as far as possible i mean deeply 



algorithm
----------

1. input node and its neighbour into the array linked list

2. add the starting node in the stack

3. while the stack is not empty 

	3.1 pop from the stack and add into the visited queue
	3.2 look out for neighbour if not vised the add it in the stack
	
*************************/	
	
class DFSGraph {

	private LinkedList<Integer> [] nodesContainer;
	private Stack<Integer> stack;
	private LinkedList<Integer> vistedNodes;
	
	public DFSGraph(int size) {
		nodesContainer =  new LinkedList[size];
		stack = new Stack();
		vistedNodes = new LinkedList();
	}

	public void addNodes(int node, int neighbhour) {
		
		//initialize if null, otherwise it would initialize for second time 
		//and elements will be lost afterwards
		if (nodesContainer[node] == null ) {
			nodesContainer[node] =  new LinkedList();
		}
		nodesContainer[node].add(Integer.valueOf(neighbhour));
	}

	private List getNeighbhourNodes(int nodeElement) {
	
		return nodesContainer[nodeElement];
	}
	
	
	public void printVisitedNodes() {
		System.out.println(vistedNodes);
	}

	public void findoutDFS(int startNode) {
		
		//add the node of the start node in the stack
		stack.push(Integer.valueOf(startNode));
		
		//while stack is not empty
		while (! stack.isEmpty()) {
			
			int nodeElement = stack.pop();
			
			// add the element into the visited nodes
			vistedNodes.add(Integer.valueOf(nodeElement));
			
			List<Integer> neighbourNodes = getNeighbhourNodes(nodeElement);
			
			for(Iterator<Integer> iter = neighbourNodes.iterator(); iter.hasNext();) {
				
				//check the neighbour nodes is already in the visited list and if it is available in the stack
				Integer neighbhourNodeElem = iter.next();
				if (! vistedNodes.contains(neighbhourNodeElem) && ! stack.contains(neighbhourNodeElem) ) {
					stack.add(neighbhourNodeElem);
				}
				
			}
		}
		
	}

	
}


public class DFS {
	
	public static void main(String args[]) {
		
		DFSGraph graph = new DFSGraph(4);
		
		graph.addNodes(0, 1);
		graph.addNodes(0, 2);
		
		graph.addNodes(1, 2);
		
		graph.addNodes(2, 0);
		graph.addNodes(2, 3);
		
		graph.addNodes(3, 3);

		graph.findoutDFS(2);
		
		graph.printVisitedNodes();
		
	}
}


/************* output ****************


graph.findOutBFS(2);

2, 3, 0, 1

graph.findOutBFS(0);

0, 2, 3, 1 
*****************************************/
