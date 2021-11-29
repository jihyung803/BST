package assignment;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class BST<E> implements Tree<E> {
	protected TreeNode<E> root;
	protected int size = 0;
	protected java.util.Comparator<E> c;

	/** Create a default BST with a natural order comparator */
	public BST() {
		this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}

	/** Create a BST with a specified comparator */
	public BST(java.util.Comparator<E> c) {
		this.c = c;
	}

	/** Create a binary tree from an array of objects */
	public BST(E[] objects) {
		this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	@Override /** Returns true if the element is in the tree */
	public boolean search(E e) {
		TreeNode<E> current = root; // Start from the root

		while (current != null) {
			if (c.compare(e, current.element) < 0) {
				current = current.left;
			} else if (c.compare(e, current.element) > 0) {
				current = current.right;
			} else // element matches current.element
				return true; // Element is found
		}

		return false;
	}
	

	@Override /**
				 * Insert element e into the binary tree Return true if the element is inserted
				 * successfully
				 */
	public boolean insert(E e) {
		if (root == null)
			root = createNewNode(e); // Create a new root
		else {
			// Locate the parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null)
				if (c.compare(e, current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (c.compare(e, current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					return false; // Duplicate node not inserted

			// Create the new node and attach it to the parent node
			if (c.compare(e, parent.element) < 0)
				parent.left = createNewNode(e);
			else
				parent.right = createNewNode(e);
		}

		size++;
		return true; // Element inserted successfully
	}

	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<>(e);
	}

	@Override /** Inorder traversal from the root */
	public void inorder() {
		inorder(root);
	}

	/** Inorder traversal from a subtree */
	protected void inorder(TreeNode<E> root) {
		if (root == null)
			return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}

	@Override /** Postorder traversal from the root */
	public void postorder() {
		postorder(root);
	}
	
	public void weekAgo(String date) {
		weekAgo(root, date);
	}

	/** Postorder traversal from a subtree */
	protected void weekAgo(TreeNode<E> root, String date) {
		if (root == null)
			return;
		weekAgo(root.left, date);
		weekAgo(root.right, date);
		
		String tmp1 = root.element.toString().substring(3, 5);
		String tmp2 = date.substring(3, 5);
		int d1 = Integer.parseInt(tmp1);
		int d2 = Integer.parseInt(tmp2);
		tmp1 = root.element.toString().substring(0,2);
		tmp2 = date.substring(0,2);
		int m1 = Integer.parseInt(tmp1);
		if(m1 == 1 || m1 == 3 || m1 == 5 || m1 == 7 || m1 == 8 || m1 == 10 || m1 == 12)
		{
			m1 = m1 * 31;
		}
		else if(m1 == 2)
		{
			m1 = m1 * 28;
		}
		else
		{
			m1 = m1 * 30;
		}
		int m2 = Integer.parseInt(tmp2);
		if(m2 == 1 || m2 == 3 || m2 == 5 || m2 == 7 || m2 == 8 || m2 == 10 || m2 == 12)
		{
			m2 = m2 * 31;
		}
		else if(m2 == 2)
		{
			m2 = m2 * 28;
		}
		else
		{
			m2 = m2 * 30;
		}
		tmp1 = root.element.toString().substring(6, 8);
		tmp2 = date.substring(6, 8);
		int y1 = Integer.parseInt(tmp1);
		int y2 = Integer.parseInt(tmp2);
		y1 = y1 * 365;
		y2 = y2 * 365;

		if((d1+m1+y1)-(d2+m2+y2) <= 7 && (d1+m1+y1)-(d2+m2+y2) > 0) {
			System.out.println(root.element.toString().split("\\s*,\\s*")[1] + " " + root.element.toString().split("\\s*,\\s*")[0]);
		}
	}
	
	public void haveNext() {
		haveNext(root);
	}
	
	protected void haveNext(TreeNode<E> root) {
		if (root == null)
			return;
		haveNext(root.left);
		haveNext(root.right);
		if(root.element.hashCode() == 1) {
			System.out.println(root.element.toString().split("\\s*,\\s*")[1]);
		}
	}

	
	protected void postorder(TreeNode<E> root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}

	@Override /** Preorder traversal from the root */
	public void preorder() {
		preorder(root);
	}

	/** Preorder traversal from a subtree */
	protected void preorder(TreeNode<E> root) {
		if (root == null)
			return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}

	/**
	 * This inner class is static, because it does not access any instance members
	 * defined in its outer class
	 */
	public static class TreeNode<E> {
		protected E element;
		protected TreeNode<E> left;
		protected TreeNode<E> right;

		public TreeNode(E e) {
			element = e;
		}
	}

	@Override /** Get the number of nodes in the tree */
	public int getSize() {
		return size;
	}

	/** Returns the root of the tree */
	public TreeNode<E> getRoot() {
		return root;
	}

	/** Returns a path from the root leading to the specified element */
	public java.util.ArrayList<TreeNode<E>> path(E e) {
		java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
		TreeNode<E> current = root; // Start from the root

		while (current != null) {
			list.add(current); // Add the node to the list
			if (c.compare(e, current.element) < 0) {
				current = current.left;
			} else if (c.compare(e, current.element) > 0) {
				current = current.right;
			} else
				break;
		}

		return list; // Return an array list of nodes
	}

	@Override /**
				 * Delete an element from the binary tree. Return true if the element is deleted
				 * successfully Return false if the element is not in the tree
				 */
	public boolean delete(E e) {
		// Locate the node to be deleted and also locate its parent node
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		while (current != null) {
			if (c.compare(e, current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (c.compare(e, current.element) > 0) {
				parent = current;
				current = current.right;
			} else
				break; // Element is in the tree pointed at by current
		}

		if (current == null)
			return false; // Element is not in the tree

		// Case 1: current has no left child
		if (current.left == null) {
			// Connect the parent with the right child of the current node
			if (parent == null) {
				root = current.right;
			} else {
				if (c.compare(e, parent.element) < 0)
					parent.left = current.right;
				else
					parent.right = current.right;
			}
		} else {
			// Case 2: The current node has a left child
			// Locate the rightmost node in the left subtree of
			// the current node and also its parent
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;

			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right; // Keep going to the right
			}

			// Replace the element in current by the element in rightMost
			current.element = rightMost.element;

			// Eliminate rightmost node
			if (parentOfRightMost.right == rightMost)
				parentOfRightMost.right = rightMost.left;
			else
				// Special case: parentOfRightMost == current
				parentOfRightMost.left = rightMost.left;
		}

		size--;
		return true; // Element deleted successfully
	}

	@Override /** Obtain an iterator. Use inorder. */
	public java.util.Iterator<E> iterator() {
		return new InorderIterator();
	}

	// Inner class InorderIterator
	private class InorderIterator implements java.util.Iterator<E> {
		// Store the elements in a list
		private java.util.ArrayList<E> list = new java.util.ArrayList<>();
		private int current = 0; // Point to the current element in list

		public InorderIterator() {
			inorder(); // Traverse binary tree and store elements in list
		}

		/** Inorder traversal from the root */
		private void inorder() {
			inorder(root);
		}

		/** Inorder traversal from a subtree */
		private void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}

		@Override /** More elements for traversing? */
		public boolean hasNext() {
			if (current < list.size())
				return true;

			return false;
		}

		@Override /** Get the current element and move to the next */
		public E next() {
			return list.get(current++);
		}

		@Override // Remove the element returned by the last next()
		public void remove() {
			if (current == 0) // next() has not been called yet
				throw new IllegalStateException();

			delete(list.get(--current));
			list.clear(); // Clear the list
			inorder(); // Rebuild the list
		}
	}

	@Override /** Remove all elements from the tree */
	public void clear() {
		root = null;
		size = 0;
	}
	
	public void clearFile() {
		try {
			File file = new File("patient.txt");
			 if(!file.exists()) {
				 file.createNewFile();
			 }
			 
			FileWriter fw = new FileWriter(file, false);
			PrintWriter out = new PrintWriter(fw);
			out.write("");
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void writeFile() {
		writeFile(root);
	}
	
	protected void writeFile(TreeNode<E> root) {
		if (root == null)
			return;
		writeFile(root.left);
		writeFile(root.right);
		try {
			File file = new File("patient.txt");
			 if(!file.exists()) {
				 file.createNewFile();
			 }
			 
			FileWriter fw = new FileWriter(file, true);
			PrintWriter out = new PrintWriter(fw);
			String s = root.element.toString().split("\\s*,\\s*")[1] + ", " + root.element.toString().split(",")[2] + ", " + root.element.toString().split(",")[0] + ", " + root.element.toString().split(",")[3];
			out.write(s);
			out.write("\n");
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}