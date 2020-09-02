package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 *
 * BST implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 * @contributor Yogeshwar Chaudhari, RMIT University, Master of Information Technology
 * 
 * References :
 * [1] Binary Search Tree - GeeksforGeeks
   Binary Search Tree - GeeksforGeeks (2020). Available at: https://www.geeksforgeeks.org/binary-search-tree-data-structure/#basic (Accessed: 26 August 2020).

   [2] Binary Search Tree | Set 1 (Search and Insertion) - GeeksforGeeks
   Binary Search Tree | Set 1 (Search and Insertion) - GeeksforGeeks (2014). Available at: https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/ (Accessed: 26 August 2020).

   [3] Binary Search Tree | Set 2 (Delete) - GeeksforGeeks
   Binary Search Tree | Set 2 (Delete) - GeeksforGeeks (2014). Available at: https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/ (Accessed: 26 August 2020).

   [4] Deletion in Binary Search Tree - javatpoint
   Deletion in Binary Search Tree - javatpoint (2020). Available at: https://www.javatpoint.com/deletion-in-binary-search-tree (Accessed: 29 August 2020).
	
   [5] Binary Search Tree (BST) insert, delete, successor, predecessor, traversal, unique trees - Algorithms and Problem Solving
   Binary Search Tree (BST) insert, delete, successor, predecessor, traversal, unique trees - Algorithms and Problem Solving (2015). Available at: http://www.zrzahid.com/binary-search-tree-bst-insert-delete-successor-predecessor-traversal/ (Accessed: 29 August 2020).
   
   [6] 
   Inorder Successor in Binary Search Tree - GeeksforGeeks
   Inorder Successor in Binary Search Tree - GeeksforGeeks (2011). Available at: https://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/ (Accessed: 29 August 2020).
   
   Lecture notes
 */
public class BstMultiset extends RmitMultiset
{

	// Starting point of the tree
	TreeNode root;

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public BstMultiset () {
		root = null;
	}


	@Override
	public void add(String item) {

		// There is no tree. Create a new root node and assign the value
		if(root == null) {
			root = new TreeNode(item);
		}else {

			// There is tree, figure out where to put it
			appendTreeNode(this.getRoot(), item);
		}

	} // end of add()


	/* [2]
	 * Custom method for special cases.
	 * Add item and occurance count at the same time. 
	 * This is to potentially save the repeated traversal required in case of union, interest and difference operations 
	 */
	private void add(String item, int occurance) {

		// There is no tree. Create a new root node and assign the value
		if(root == null) {
			root = new TreeNode(item);
			root.setOccuranceCount(occurance);
		}else {

			// There is tree, figure out where to put it
			appendTreeNode(this.getRoot(), item, occurance);
		}

	} // end of add()


	/* [2]
	 * Custom method for inserting node for special cases
	 * Instead of adding an element n times, this function can be used to add an node with given instance using one call
	 */
	private void appendTreeNode(TreeNode node, String item, int occurance) {

		if(item.compareTo( node.getVal() ) < 0) {
			if( node.getLeft() == null ) {
				TreeNode childNode = new TreeNode(item);
				childNode.setOccuranceCount(occurance);
				node.setLeft( childNode );
				childNode.setParent( node );
			}
			else {
				this.appendTreeNode(node.getLeft(), item, occurance);
			}
		}
		else if( item.compareTo(node.getVal()) > 0) {
			if ( node.getRight() == null ) {
				TreeNode childNode = new TreeNode(item);
				childNode.setOccuranceCount(occurance);
				node.setRight(childNode);
				childNode.setParent( node );
			}
			else {
				this.appendTreeNode(node.getRight(), item, occurance);
			}
		}else if(item.compareTo(node.getVal()) == 0) {

			// Node with same value found. Update the occurance count only
			// Expected to pass new occurance = old occurance count + extra occurance
			node.setOccuranceCount( occurance );

		}
	}


	/* [2]
	 * Recursive function.
	 * Inserts element as the left or right child.
	 * Also sets the parent node of the newly added node 
	 */
	private void appendTreeNode(TreeNode node, String item) {

		// If the item is smaller then look into left sub tree
		if(item.compareTo( node.getVal() ) < 0) {

			// Insert new node as left child
			if( node.getLeft() == null ) {
				TreeNode childNode = new TreeNode(item);
				node.setLeft( childNode );
				childNode.setParent( node );
			}
			else {

				// Traverse recursively to reach to appropriate node in left subtree
				this.appendTreeNode(node.getLeft(), item);
			}
		}
		else if( item.compareTo(node.getVal()) > 0) {
			// Item is bigger then look into right sub tree

			// Insert new node as right child of this node
			if ( node.getRight() == null ) {
				TreeNode childNode = new TreeNode(item);
				node.setRight(childNode);
				childNode.setParent( node );
			}
			else {

				// Traverse recursively to reach to appropriate node in right subtree
				this.appendTreeNode(node.getRight(), item);
			}
		}else if(item.compareTo(node.getVal()) == 0) {

			// Node with same value found. Update the occurance count only
			node.setOccuranceCount( node.getOccuranceCount() + 1);

		}
	}

	
	/**
	 * [3] [4] [5]
	 */
	@Override
	public void removeOne(String item){
		
		// Find the require treeNode
		TreeNode toBeDeleted = getFirstOccurance(item);
		
		if(toBeDeleted != null) {
			removeTreeNode(toBeDeleted);
		}
	}


	/**
	 *  [3] [4] [5]
	 *  Removes the given node from the tree if the occurance count reaches 0
	 *  Otherwise just decreaments the occurance count
	 * @param givenNode
	 */
	private void removeTreeNode(TreeNode givenNode) {
		
		// Basic error checking
		if(givenNode == null){
			return;
		}

		// Scenario 1 = Leaf Node
		if(givenNode.getLeft() == null && givenNode.getRight() == null){

			// Simply decrement the occurance count
			givenNode.OccuranceCountMinus1();

			// If occurance count is zero then actually remove the node from tree
			if(givenNode.getOccuranceCount() == 0) {
				
				// Update parents left or right pointers and remove node from tree
				if(givenNode == givenNode.getParent().getLeft()){
					givenNode.getParent().setLeft(null);
				}
				else{
					givenNode.getParent().setRight(null);
				}
				givenNode = null;
			}
		}
		else if(givenNode.getLeft() == null || givenNode.getRight() == null){
			// Scenario 2 = Only 1 child
			
			// Simply decrement the occurance count
			givenNode.OccuranceCountMinus1();
			
			// If occurance count is zero then actually remove the node from tree
			if(givenNode.getOccuranceCount() == 0) {
				
				TreeNode parent = givenNode.getParent();		// Get pointer to parent node

				// node has no left child, replace that node with node's right child otherwise replace with left child
				if(givenNode.getLeft() == null) {
					givenNode = givenNode.getRight();
				}else {
					givenNode = givenNode.getLeft();
				}
				
				// Update the replaced node's parent as deleted node's parent
				givenNode.setParent(parent);
			}
		}
		else{
			// Scenario 3 - Node with 2 children
			
			// Decrement the occurance count
			givenNode.OccuranceCountMinus1();
			
			// if occurance count is 0 after removal one instance, then that element to be replaced by inorder successor
			if(givenNode.getOccuranceCount() == 0) {

				// Find the inorder successor 
				TreeNode inOrderSucc = this.getInoderSucc(givenNode);

				givenNode.setVal(inOrderSucc.getVal());
				givenNode.setOccuranceCount(inOrderSucc.getOccuranceCount());
				
				// Updating inOrderSucc occurance count 1 so that it will be removed from tree in next call
				inOrderSucc.setOccuranceCount(1);

				// Remove the inorder of replaced node, starting from same node - Important
				removeTreeNode(inOrderSucc);
			}
		}
	}

	
	/**
	 * [2]
	 */
	@Override
	public int search(String item) {

		// In order traversal gives elements in the sorted order
		TreeNode t = this.getRoot();
		int occuranceCount = -1;

		// Navigate to that treeNode using power of BST
		while(t != null) {

			// Item is bigger than current node, look into right subtree
			if(t.getVal().compareTo(item) < 0) {
				t = t.getRight();
			}else if(t.getVal().compareTo(item) > 0) {

				// Item is smaller than current node, look into left subtree
				t = t.getLeft();
			}else if(t.getVal().compareTo(item) == 0){

				// Item found. Return the occurance count
				occuranceCount = t.getOccuranceCount();
				break;
			}
		}

		return occuranceCount;
	} // end of search()


	/**
	 * [2]
	 */
	@Override
	public List<String> searchByInstance(int instanceCount) {

		// Reference : https://www.techiedelight.com/preorder-tree-traversal-iterative-recursive/

		List<String> retList = new ArrayList<String>();

		if(this.getRoot() == null) {
			return retList;
		}

		Stack<TreeNode> poStack = new Stack<TreeNode>();
		poStack.push(this.getRoot());

		while(! poStack.empty() ) {

			TreeNode currNode = poStack.pop();

			// If the occurance count matches with the required instance count then add this to list
			if(currNode.getOccuranceCount() == instanceCount) {
				retList.add(currNode.getVal());
			}

			// If current tree node has right child, push that on stack
			// This is to process the left child first
			if(currNode.getRight() != null) {
				poStack.push(currNode.getRight());
			}

			// If the current node has left child, push that on stack
			if(currNode.getLeft() != null) {
				poStack.push(currNode.getLeft());
			}

		}

		return retList;
	} // end of searchByInstance    


	/**
	 * [2]
	 */
	@Override
	public boolean contains(String item) {

		TreeNode t = this.getRoot();

		// Navigate to that treeNode using power of BST
		while(t != null) {
			if(t.getVal().compareTo(item) < 0) {

				// Find in right subtree
				t = t.getRight();

			}else if(t.getVal().compareTo(item) > 0) {

				// Find in left sub tree
				t = t.getLeft();

			}else {

				// Found
				return true;
			}
		}


		// Not found
		return false;
	} // end of contains()



	@Override
	public String print() {

		// Clone the multiset in Array implementation. This is required because, we need to print element based on it's onccurance count
		ArrayMultiset multisetArr = new ArrayMultiset();

		TreeNode curr = this.getSmallestElementFromTree( this.getRoot() );

		// Iterate over current list
		while(curr != null) {

			for (int i = 0; i < curr.getOccuranceCount(); i++) {
				multisetArr.add(curr.getVal());
			}

			curr = this.getInoderSucc(curr);
		}

		// This method returns the string representation of the multiset sorted based on occurance count
		return multisetArr.print();

	} // end of OrderedPrint


	@Override
	public String printRange(String lower, String upper) {

		StringBuilder retStr = new StringBuilder();

		// Using inorder-traversal
		TreeNode t = getSmallestElementFromTree(this.getRoot());

		while(t != null && t.getVal().compareTo(upper) <= 0) {

			if( ( t.getVal().compareTo(lower) >= 0 ) && (t.getVal().compareTo(upper) <= 0) ) {
				retStr.append(t.getVal()+":"+t.getOccuranceCount()+"\n");
			}

			t = this.getInoderSucc(t);
		}

		return retStr.toString();
	} // end of printRange()


	@Override
	public RmitMultiset union(RmitMultiset other) {

		BstMultiset retSet = new BstMultiset();

		// Union of the multiset is the addition of multiplicity of an element from given multisets
		// Inorder traversal of a tree returns the the sorted list. 
		// Traversing tree in in-order fashion

		TreeNode firstTreePtr = getSmallestElementFromTree(this.getRoot());
		TreeNode secTreePtr = getSmallestElementFromTree(((BstMultiset) other).getRoot());

		// Traverse both trees at the same time
		while(firstTreePtr != null && secTreePtr != null) {

			// If the first node is smaller then add that to retSet and advance to it's inorder-successor
			if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) < 0) {

				retSet.add( firstTreePtr.getVal(), firstTreePtr.getOccuranceCount() );
				firstTreePtr = getInoderSucc2(firstTreePtr);

			}else if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) > 0) {
				// If the second node is smaller, add that to retSet and advance to it's inorder-successor
				
				retSet.add( secTreePtr.getVal(), secTreePtr.getOccuranceCount() );
				secTreePtr = getInoderSucc2(secTreePtr);

			}else if(firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) == 0) {
				// Both node have same value, add node to BST with (ocurr(n1) + ocurr(n2)) and advance both to their respective in-order
				
				retSet.add( firstTreePtr.getVal(), (firstTreePtr.getOccuranceCount() + secTreePtr.getOccuranceCount() ));
				firstTreePtr = getInoderSucc2(firstTreePtr);
				secTreePtr = getInoderSucc2(secTreePtr);
			}
		}

		// Exhausted second tree but first tree has elements
		while(firstTreePtr != null) {
			retSet.add(firstTreePtr.getVal(), firstTreePtr.getOccuranceCount());
			firstTreePtr = getInoderSucc2(firstTreePtr);
		}

		// Exhausted first free tree but second tree has elements
		while(secTreePtr != null) {
			retSet.add( secTreePtr.getVal(), secTreePtr.getOccuranceCount() );
			secTreePtr = getInoderSucc2(secTreePtr);
		}

		return retSet;
	} // end of union()


	@Override
	public RmitMultiset intersect(RmitMultiset other) {

		BstMultiset retSet = new BstMultiset();

		// Start with the smallest nodes in the both trees
		TreeNode firstTreePtr = getSmallestElementFromTree(this.getRoot());
		TreeNode secTreePtr = getSmallestElementFromTree(((BstMultiset) other).getRoot());

		while(firstTreePtr != null && secTreePtr != null) {

			// First node is smaller is second node. That means no common elements.
			if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) < 0) {

				// Just advance the first node
				firstTreePtr = getInoderSucc2(firstTreePtr);

			}else if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) > 0) {
				// First node is larger than second element that also means no common elements. Just advance the second tree

				secTreePtr = getInoderSucc2(secTreePtr);

			}else if(firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) == 0) {
				// Common element found. 

				retSet.add( firstTreePtr.getVal(), Math.min(firstTreePtr.getOccuranceCount(), secTreePtr.getOccuranceCount() ));

				// Since common element, advance both cursors
				firstTreePtr = getInoderSucc2(firstTreePtr);
				secTreePtr = getInoderSucc2(secTreePtr);
			}
		}

		return retSet;

	} // end of intersect()


	@Override
	public RmitMultiset difference(RmitMultiset other) {

		BstMultiset retSet = new BstMultiset();

		// We will be starting from the smallest element in both tress. 
		// This way we can traverse both trees at the same time.
		TreeNode firstTreePtr = getSmallestElementFromTree(this.getRoot());
		TreeNode secTreePtr = getSmallestElementFromTree(((BstMultiset) other).getRoot());

		while(firstTreePtr != null && secTreePtr != null) {

			// First node is smaller than second node
			if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) < 0) {

				// Add first node as we are doing A - B
				retSet.add( firstTreePtr.getVal(), firstTreePtr.getOccuranceCount() );

				// Get the inorder successor of the tree
				firstTreePtr = getInoderSucc2(firstTreePtr);

			}else if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) > 0) {
				// First node is greater than second node. Hence we don't need it. Just advance the second tree in this case 

				secTreePtr = getInoderSucc2(secTreePtr);

			}else if(firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) == 0) {

				// multiset one contains more occurance than set 2
				if(firstTreePtr.getOccuranceCount() > secTreePtr.getOccuranceCount()) {

					retSet.add(firstTreePtr.getVal(), (firstTreePtr.getOccuranceCount() - secTreePtr.getOccuranceCount()));
				}

				// Advance both trees
				firstTreePtr = getInoderSucc2(firstTreePtr);
				secTreePtr = getInoderSucc2(secTreePtr);
			}
		}

		// 18-08-2020 - All remaining elements if any in the firstSet will be part of the results
		while(firstTreePtr != null) {
			retSet.add(firstTreePtr.getVal(), firstTreePtr.getOccuranceCount());
			firstTreePtr = getInoderSucc2(firstTreePtr);
		}

		return retSet;

	} // end of difference()


	/* [6]
	 * Find the inorder-successsor of the given element
	 */
	public TreeNode getInoderSucc(TreeNode givenEle) {

		TreeNode t = givenEle;

		// Right subtree is not empty. in order successor is smallest element in the right substree.
		if(t.getRight() != null) {

			t = t.getRight();
			while(t.getLeft() != null) {
				t = t.getLeft();
			}

			return t;

		}else{
			// Node with no right child, inorder successor lies in the tree above
			
			TreeNode currNode = this.getRoot();
			TreeNode inOrderSucc = null;

			// Start from root node
			while(currNode != null){
				
				// current node is greater than given element - mark as potential inorder successor and begin search in left subtree
				if(currNode.getVal().compareTo( givenEle.getVal() ) > 0){
					inOrderSucc = currNode;
					currNode = currNode.getLeft();
				}
				else if(currNode.getVal().compareTo(givenEle.getVal()) < 0){
					// Current element is smaller than given element - search in right subtree
					currNode = currNode.getRight();
				}
				else{
					// We have reached to given element
					break;
				}
			}

			return inOrderSucc;
		}
	}
	
	
	/*
	 * Find the inorder-successsor of the element
	 */
	private TreeNode getInoderSucc2(TreeNode givenEle) {

		// Right subtree is not empty. in order successor is smallest element in the right substree.
		TreeNode t = givenEle;

		if(t.getRight() != null) {

			t = t.getRight();
			while(t.getLeft() != null) {
				t = t.getLeft();
			}

			return t;
		}

		// Right Subtree is empty. 
		// Start from given element. This is required to reduce the time complexity and well as same elements can occure in the multiset
		TreeNode parent = t.getParent();

		// Traverse upwards to find successor
		while(parent != null && parent.getRight() == t) {
			t = parent;
			parent = t.getParent();
		}

		return parent;
	}


	/*
	 *  Returns the node which is smallest in the BST
	 *  Leftmost element in the left sub tree is the smallest element
	 */
	private TreeNode getSmallestElementFromTree(TreeNode root){

		TreeNode t = root;
		
		// Stop when the left child is null - meaning reached to leftmost node
		while(t != null && t.getLeft() != null)
		{
			t = t.getLeft();
		}

		return t;
	}

	/**
	 * Returns the first occurance of the node in the tree.
	 * @param item : String
	 * @return Treenode or null
	 */
	public TreeNode getFirstOccurance(String item) {

		TreeNode currNode = this.getRoot();

		while(currNode != null) {

			// Current node is smaller than item. Element could be in right sub tree
			if( currNode.getVal().compareTo(item) < 0) {

				currNode = currNode.getRight();
			}
			else if( currNode.getVal().compareTo(item) > 0) {
				// Current node's val larger than item Element could be in left sub tree

				currNode = currNode.getLeft();
			}else {

				// Current node is the element I am looking for
				return currNode;
			}
		}

		return null;
	}

} // end of class 
