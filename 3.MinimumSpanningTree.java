package com;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*************
 
algorithm
----------

Minimum spanning tree is an algorithm which spans across every node in the graph

the cost of spanning tree is the sum of the weights of all edges in tree or graph

store the nodes in the objects
	a) source
	b) destination
	c) cost
	d) visited as boolean

visited nodes as map 
    key node and cost

step 1: sort it first 

step 2: find  the Minimum cost of nodes and put in the visited nodes map

step 3: while visited nodes list has not fully visited nodes all nodes

		step 3.1: find the Minimum cost from the non visited nodes 
		
		step 3.2: check if the either of the node in nodes are visited 
		 
		step 3.3: if so add it in the visited nodes map

step 4: find the cost from the visited nodes map 		

************************************/

class Nodes{
	private String source;
	private String dest;

	
	public Nodes(String source, String dest) {
		this.source = source;
		this.dest = dest;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}

}

class MinimumSpanningTree {


	private Nodes nodes;
	private int cost;
	private boolean visited;

	
	MinimumSpanningTree(Nodes nodes, int cost){
		this.nodes = nodes;
		this.cost = cost;
	}
	
	public Nodes getNodes() {
		return nodes;
	}

	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}

	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	@Override
	public String toString() {
		return "source:" + nodes.getSource() + "\n" + "destination:"  + nodes.getDest() +  "\n" + "cost:" + cost + "\n" + "visited:" + visited;
	}
}

class SpanningTreeComparator implements Comparator<MinimumSpanningTree> {

	@Override
	public int compare(MinimumSpanningTree arg0, MinimumSpanningTree arg1) {
		
		//Internally the Sort method does call Compare method of the classes it is sorting. 
		//To compare two elements, 
		//it asks “Which is greater?” Compare method returns -1, 0 or 1 
		//to say if it is less than, equal, or greater to the other. 
		//It uses this result to then determine if they should be swapped for its sort.
				
		if (arg0.getCost() > arg1.getCost()) {
			return 1;
		}
		else if (arg0.getCost() > arg1.getCost()) {
			return 0;
		}
		else {
			return -1;
		}
	}
	
}

class MinimumSpanningTreeAlgorithm {
	
	private HashMap <Nodes,Integer> visitedNode;
	private LinkedList<MinimumSpanningTree> tree;

	
	public MinimumSpanningTreeAlgorithm(){
		visitedNode =  new HashMap();
		tree = new LinkedList();
	}
	
	public void addTreeNode(String source, String destination, int cost) {
		tree.add(new MinimumSpanningTree(new Nodes(source, destination),cost));
	}
	
	
	private void doSorting() {
		tree.sort(new SpanningTreeComparator());
	}
	
	private MinimumSpanningTree findShortCost() {
		
		
		for (Iterator<MinimumSpanningTree> iter = tree.iterator(); iter.hasNext();) {
			MinimumSpanningTree spanningTree = (MinimumSpanningTree) iter.next();
			
			if (!spanningTree.isVisited()) {
				spanningTree.setVisited(true);
				return spanningTree;
			}
		}
		return null;
	}

	private int getNumberOfNodesInSpanningTree() {
		
		List <String> nodes = new LinkedList();
		for (Iterator<MinimumSpanningTree> iter = tree.iterator(); iter.hasNext();) {
			
			MinimumSpanningTree spanningTreeNodes  = iter.next();
			
			if (!nodes.contains(spanningTreeNodes.getNodes().getSource())) {
				nodes.add(spanningTreeNodes.getNodes().getSource());
			}
			else if  (!nodes.contains(spanningTreeNodes.getNodes().getDest()) ) {
				nodes.add(spanningTreeNodes.getNodes().getDest());
			}
		}
		
		return nodes.size();
	}

	private int getNumberOfNodesInVisitedNodes() {
		
		List <String> visitedList = new LinkedList ();
		for(Map.Entry<Nodes, Integer> visitedNodesEntry : visitedNode.entrySet()) {
			
			if (!visitedList.contains(visitedNodesEntry.getKey().getSource())) {
				visitedList.add(visitedNodesEntry.getKey().getSource() );
			}
			
			if (!visitedList.contains(visitedNodesEntry.getKey().getDest())) {
				visitedList.add(visitedNodesEntry.getKey().getDest() );
			}
			
		}
		
		return visitedList.size();
	}
	
	
	public boolean isSourceNodeAlreadyVisited(String node) {
		
		for(Map.Entry<Nodes, Integer> nodesEntry: visitedNode.entrySet()) {
			
			if (nodesEntry.getKey().getSource().equals(node) ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isDestNodeAlreadyVisited(String node) {
		
		for(Map.Entry<Nodes, Integer> nodesEntry: visitedNode.entrySet()) {
			
			if (nodesEntry.getKey().getDest().equals(node) ) {
				return true;
			}
		}
		return false;
	}
	
	public void determineSpanningTreeCost() {

		//do first sorting to return the minimum spanning cost of the node
		doSorting();

		//find the shorttest path to be added
		MinimumSpanningTree minimumSpanningTree = findShortCost();
		
		if (null !=  minimumSpanningTree)
		{
			//get the cost of the first source node to be added
			visitedNode.put(minimumSpanningTree.getNodes(), minimumSpanningTree.getCost());
		}
		
		//get the number of nodes in the spanning tree
		int noOfNodes = getNumberOfNodesInSpanningTree();
		
		 
		//while all the nodes are not visited fully loop it
		while (noOfNodes > getNumberOfNodesInVisitedNodes()) {

			minimumSpanningTree = findShortCost();

			if (null != visitedNode && !isSourceNodeAlreadyVisited(minimumSpanningTree.getNodes().getSource())) {
				visitedNode.put(minimumSpanningTree.getNodes(),minimumSpanningTree.getCost());
			}
			else if  (null != visitedNode && !isDestNodeAlreadyVisited(minimumSpanningTree.getNodes().getDest())) {
				visitedNode.put(minimumSpanningTree.getNodes(),minimumSpanningTree.getCost());
			}
		}
		
	}
	
	public int getCostofVisitedNodes() {
		
		int sum = 0;
		
		for(Map.Entry<Nodes, Integer> entry : visitedNode.entrySet()) {
			sum += entry.getValue();
		}
		
		return sum;
	}
}


public class MinimumSpanningTreeEx {
	public static void main(String args[]) {
	
		MinimumSpanningTreeAlgorithm spanningTreeAlg =  new MinimumSpanningTreeAlgorithm();
		spanningTreeAlg.addTreeNode("C", "D", 5);
		spanningTreeAlg.addTreeNode("A", "B", 1);
		spanningTreeAlg.addTreeNode("A", "D", 4);
		spanningTreeAlg.addTreeNode("A", "C", 3);
		spanningTreeAlg.addTreeNode("B", "C", 2);
		
		spanningTreeAlg.determineSpanningTreeCost();
		
		System.out.println("cost of visited nodes:" + spanningTreeAlg.getCostofVisitedNodes());
	}
}



/************** output 


cost of visited nodes:7



**********************/
