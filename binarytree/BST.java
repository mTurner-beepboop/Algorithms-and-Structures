package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Binary search tree class with functionality for search, min, max, insert and
 * delete, etc
 * 
 * @author Mark Turner
 */
public class BST {

	private Node root;

	/**
	 * Class for each leaf of the binary tree
	 * 
	 * @author Mark Turner
	 */
	protected class Node {
		private int key; // sorted by key
		private Node left, right, p; // Left node, Right node, previous node
		private int size; // number of nodes
		private int count; // amount of instances

		public Node(int key, int size) {
			this.key = key;
			this.size = size;
			this.left = null;
			this.right = null;
			this.p = null;
			this.count = 1;
		}
		
		public Node(int key, int size, int count) {
			this.key = key;
			this.size = size;
			this.left = null;
			this.right = null;
			this.p = null;
			this.count = count;
		}

		public void incrementCount() {
			this.count++;
		}

		public void decrementCount() {
			this.count--;
		}

		@Override
		public String toString() {
			String ret = "[Key: " + this.key + ", Size: " + this.size + ", Count: " + this.count;
			if (this.p != null) {
				ret += ", Previous val: " + this.p.key;
			} else {
				ret += ", Previous val: null";
			}
			if (this.left != null) {
				ret += ", Left val: " + this.left.key;
			} else {
				ret += ", Left val: null";
			}
			if (this.right != null) {
				ret += ", Right val: " + this.right.key;
			} else {
				ret += ", Right val: null";
			}

			return ret + "]";
		}
	}

	public BST() {
		this.root = null;
	}
	
	
	
	//Insertion algorithms below
	
	

	/**
	 * Insert a leaf into the binary tree in its correct position and update size
	 * associated with each node traversed
	 * 
	 * @param value to be inserted
	 */
	public void insert(int key) {
		// Check if the root of the tree is null, if so, create node at root
		if (this.root == null) {
			this.root = new Node(key, 1);
			return;
		}
		
		//Check if the node already exists in the tree
		try {
			Node temp = this.search(key);
			temp.incrementCount();
			return;
		}
		//Otherwise insert a new node into the tree
		catch(NoSuchElementException e) {
			Node prev = null;
			Node temp = this.root;
			while (temp != null) {
				prev = temp; // Log the previous node
				temp.size++; // Increase the number of nodes associated
				// If the value given is less than current node's key, it belongs to left
				if (key < temp.key) {
					temp = temp.left;
				}
				// If it's greater than the current node
				else {
					temp = temp.right;
				}
			}
			temp = new Node(key, 1);
			temp.p = prev;
	
			// Update the previous node to link to the new node
			if (temp.key < prev.key) {
				prev.left = temp;
			} else {
				prev.right = temp;
			}
		}
	}
	
	/**
	 * Method to insert a new node at the root
	 * 
	 * @param key
	 */
	public void insertRoot(int key) {
		//First insert the item into the node
		this.insert(key);
		//Retrieve the newly created node and its location
		Node inserted = this.search(key);
		//Rotate until it's at the top
		this.rotateToRoot(inserted);
	}
	
	
	
	
	//Rotation algorithms below
	
	
	
	/**
	 * Rotates the tree around node x
	 * 
	 * @param node to be rotated around
	 */
	private void leftRotate(Node x) {
		Node temp = x.right;
		x.right = temp.left;
		
		if (temp.left != null) {
			temp.left.p = x;
		}
		temp.p = x.p;
		if (x.p == null) { //Case x is the root
			this.root = temp;
		}
		else if (x == x.p.left) { //Case x is a left child
			x.p.left = temp;
		}
		else { //Case x is a right child
			x.p.right = temp;
		}
		temp.left = x;
		x.p = temp;
	}
	
	/**
	 * Rotate the tree around node x
	 * 
	 * @param node to be rotated around
	 */
	private void rightRotate(Node x) {
		Node temp  = x.left;
		x.left = temp.right;
		
		if (temp.right != null) {
			temp.right.p = x;
		}
		temp.p = x.p;
		if (x.p == null) {
			this.root = temp;
		}
		else if(x == x.p.right) {
			x.p.right = temp;
		}
		else {
			x.p.left = temp;
		}
		temp.right = x;
		x.p = temp;
	}
	
	/**
	 * Helper method to rotate a node to the root
	 * 
	 * @param Node to be made root
	 */
	public void rotateToRoot(Node temp) {
		while(temp != root) {
			//Case inserted is left child
			if (temp.p.left == temp) {
				this.rightRotate(temp.p);
			}
			else {
				this.leftRotate(temp.p);
			}
		}
	}
	
	public void balanceBST() {
		//Perform inorder traversal from root and log the values
		List<Node> values = inorderValues(this.root);
		
		//Pick middle element of values as the root of the tree
		Node newRoot = values.get(values.size()/2);
		
		//Call recursive func to do same for left and right subtrees
		this.rotateToRoot(newRoot);
		this.balanceBST(newRoot.left, newRoot);
		this.balanceBST(newRoot.right, newRoot);
	}
	
	private void balanceBST(Node x, Node parent) {
		if (x == null) {
			return;
		}
		
		List<Node> values = inorderValues(x);
		Node newRoot = values.get(values.size()/2);
		
		while(newRoot.p != parent) {
			if (newRoot.p.left == newRoot) {
				this.rightRotate(newRoot.p);
			}
			else {
				this.leftRotate(newRoot.p);
			}
		}
		this.balanceBST(newRoot.left, newRoot);
		this.balanceBST(newRoot.right, newRoot);
	}
	
	private List<Node> inorderValues(Node x){
		List<Node> values = new ArrayList<Node>();
		if (x != null) {
			values.addAll(this.inorderValues(x.left));
			values.add(x);
			values.addAll(this.inorderValues(x.right));
		}
		return values;
	}
	
	
	
	//Deletion algorithms below
	
	

	/**
	 * Delete a node from the tree and rejoins the branches broken
	 * 
	 * @param key of the node to be deleted
	 */
	public void delete(int key) {
		Node temp;
		try {
			temp = this.search(key);
		} catch (NoSuchElementException e) {
			System.out.println("No such element in list, [" + key + "]");
			return;
		}
		// If the node in question has a count greater than 1, simply remove one from
		// the count
		if (temp.count > 1) {
			temp.decrementCount();
		}
		// Otherwise get rid of the node
		else {
			this.delete(temp);
		}
	}

	/**
	 * Deletes a node from the tree and rejoins the branches broken
	 * 
	 * @param node to be deleted
	 */
	public void delete(Node key) {
		// Case key has one child on right
		if (key.left == null) {
			this.transplant(key, key.right);
		}
		// Case key has one child on left
		else if (key.right == null) {
			this.transplant(key, key.left);
		}
		// Case key has 2 children
		else {
			Node temp = this.minimum(key.right);
			if (temp.p != key) {
				this.transplant(temp, temp.right);
				temp.right = key.right;
				temp.right.p = temp;
			}
			this.transplant(key, temp);
			temp.left = key.left;
			temp.left.p = temp;
		}
		
		//Traverse back up the tree and decrease number of nodes associated
		Node temp = key.p;
		while (temp != null) {
			temp.size -= 1;
		}
	}

	/**
	 * Helper method to move subtree rooted at u to v
	 * 
	 * @param u, v
	 */
	private void transplant(Node u, Node v) {
		// Case u is the root
		if (u.p == null) {
			this.root = v;
		}
		// Case u is a left node
		else if (u == u.p.left) {
			u.p.left = v;
		}
		// Case u is right node
		else {
			u.p.right = v;
		}

		// Change the previous node in v to the previous node in u
		if (v != null) {
			v.p = u.p;
		}
	}
	
	
	
	//Searching algorithms below
	
	

	/**
	 * Searches for the minimum node from a parent node specified
	 * 
	 * @param parent
	 * @return minimum node
	 */
	public Node minimum(Node parent) {
		Node temp = parent;
		// Continually move left from the parent node
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}

	/**
	 * Searches for the maximum node from a parent node specified
	 * 
	 * @param parent
	 * @return maximum node
	 */
	public Node maximum(Node parent) {
		Node temp = parent;
		// Continually move right from parent node
		while (temp.right != null) {
			temp = temp.right;
		}
		return temp;
	}

	/**
	 * Searches the tree for a node with the value given
	 * 
	 * @param key
	 * @return node with key in tree
	 */
	public Node search(int key) throws NoSuchElementException {
		Node temp = this.root;
		while (temp != null && key != temp.key) {
			if (key < temp.key) {
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}
		if (temp == null) {
			throw new NoSuchElementException();
		}
		else {
			//Rotate the searched item up
			this.rotateToRoot(temp);
			return temp;
		}
	}

	
	
	//Traversal algorithms below
	
	
	
	/**
	 * Traverses the array in order and prints a string of all values
	 */
	public void inorder() {
		if (this.root != null) {
			this.inorder(root.left);
			System.out.print("[" + root.key + "] ");
			this.inorder(root.right);
		} else {
			System.out.print("[Empty]");
		}
		System.out.println();
	}
	private void inorder(Node x) {
		if (x != null) {
			this.inorder(x.left);
			System.out.print("[" + x.key + "] ");
			this.inorder(x.right);
		}
	}

	/**
	 * Traverses the tree and prints a semi ordered string of all values
	 */
	public void preorder() {
		if (this.root != null) {
			System.out.print("[" + this.root.key + "] ");
			this.preorder(this.root.left);
			this.preorder(this.root.right);
		} else {
			System.out.print("[Empty]");
		}
		System.out.println();
	}
	private void preorder(Node x) {
		if (x != null) {
			System.out.print("[" + x.key + "] ");
			this.preorder(x.left);
			this.preorder(x.right);
		}
	}

	/**
	 * Traverses the tree and prints a backwards semi ordered string of all values
	 */
	public void postorder() {
		if (this.root != null) {
			this.postorder(this.root.left);
			this.postorder(this.root.right);
			System.out.print("[" + this.root.key + "] ");
		} else {
			System.out.print("[Empty]");
		}
		System.out.println();
	}
	private void postorder(Node x) {
		if (x != null) {
			this.postorder(x.left);
			this.postorder(x.right);
			System.out.print("[" + x.key + "] ");
		}
	}

	/**
	 * Checks if the tree is a valid binary search tree
	 * 
	 * @return boolean indication if it's valid
	 */
	public boolean checkBST() {
		return checkBST(this.root);
	}
	/**
	 * Recursive helper function for check valid
	 * 
	 * @param parent node
	 * @return boolean indication if subtree is valid
	 */
	public boolean checkBST(Node x) {
		if (x != null) {
			if (!this.checkBST(x.left)) {
				return false;
			} else {
				if (x.right != null) {
					if (x.key > x.right.key) {
						return false;
					}
				} else if (x.left != null) {
					if (x.key < x.left.key) {
						return false;
					}
				}
			}

			return this.checkBST(x.right);
		}

		return true;
	}
}
