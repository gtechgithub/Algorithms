package com;

import java.util.LinkedList;
import java.util.Queue;

class Node {
	int value;
	Node left;
	Node right;

	Node() {
		left = null;
		right = null;
	}

}

class BinaryTreePrg {

	Node node;

	/*
	 * BinaryTreePrg(){ node = new Node(); }
	 */

	public void createNode(int val) {

		// get the temp node
		Node temp = node;

		// create node for the first
		if (node == null) {
			node = new Node();
			node.value = val;
			return;
		}

		while (null != temp) {

			if (temp.value < val) {
				// right
				if (null == temp.right) {
					temp.right = new Node();
					temp.right.value = val;
					return;
				} else if (temp.right.value < val) {
					Node oldRightNode = temp.right;
					Node newNode = new Node();
					newNode.value = val;
					temp.right = newNode;
					newNode.left = oldRightNode;
					return;
				}
				else { // this case when the right.value > val
					temp = temp.right;
				}
			} else if (temp.value > val) {
				// left
				if (null == temp.left) {
					temp.left = new Node();
					temp.left.value = val;
					return;
				} else if (temp.left.value < val) {
					Node oldLeftNode = temp.left;
					Node newNode = new Node();
					newNode.value = val;
					temp.left = newNode;
					newNode.left = oldLeftNode;
					return;
				}
				else { //this case where left.value > val
					temp = temp.left;
				}
			}
		}

		// once traversed assign the value
		// temp = new Node();
	}

	public void deleteNode(int val) {

	}

	public void displayNodesUsingBFS() {

		Queue<Node> queue = new LinkedList<Node>();

		Node temp = node;
		System.out.println(temp.value);

		while (null != temp) {

			// add the left nodes
			if (null != temp.left) {
				System.out.println(temp.left.value  + "<====" );
				queue.add(temp.left);
			}

			// add the right nodes
			if (null != temp.right) {
				System.out.println("====>" + temp.right.value);

				queue.add(temp.right);
			}

			temp = queue.poll();
		}
	}
}

public class BinaryTree {
	public static void main(String args[]) {

		BinaryTreePrg prg = new BinaryTreePrg();

		prg.createNode(5);
		prg.createNode(8);

		prg.createNode(6);
		prg.createNode(1);
		prg.createNode(3);
		prg.createNode(2);
		prg.createNode(4);

		
		prg.displayNodesUsingBFS();

	}
}
