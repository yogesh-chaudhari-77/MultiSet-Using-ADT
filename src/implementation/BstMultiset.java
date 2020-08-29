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

	public BstMultiset() {
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
	
	
	/*
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

	
	/*
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
	// [3]
	public void removeOne(String item) {

		TreeNode t = getFirstOccurance(item);
		
		// Element is leaf element
		if(t.getLeft() == null && t.getRight() == null) {
			
			// Item found 
			if(t.getVal().compareTo(item) == 0) {
				
				// Decrement occurance count
				t.OccuranceCountMinus1();
				
				// If the occurance count is 0 then empty that node
				if(t.getOccuranceCount() == 0) {
					t = null;
					return;
				}
			}else {
				System.out.println("BSTTree : No such element exist in the multiset");
			}
			
		}else if ( t.getLeft() != null && t.getRight() == null) {
			// Node to be deleted has left child but no right child.
			
			// Decrement occurance count
			t.OccuranceCountMinus1();		

			// If the occurance count gets to 0, then need to actually delete element
			if(t.getOccuranceCount() == 0) {
				
				if(t.getParent().getLeft() == t) {
					// Set the parent's left to deleted node's left
					t.getParent().setLeft(t.getLeft());	
				}else {
					// Set the parent's right to deleted node's left
					t.getParent().setRight(t.getLeft());
				}
				
				// Set the deleted node's left's parent to deleted node's parent
				t.getLeft().setParent( t.getParent() );
				t = null;
				return;
			}
			
		}else if( t.getLeft() == null && t.getRight() != null ) {
			// Similar to above case. Node to be deleted has right child but no left child
			
			t.OccuranceCountMinus1();
			

			// Update the parent and child pointers if the node has to be actually deleted
			if(t.getOccuranceCount() == 0) {
				
				if(t.getParent().getLeft() == t) {
					// Set the parent's left to deleted node's right
					t.getParent().setLeft(t.getRight());	
				}else {
					
					//Set the parent's right to deleted node's right
					t.getParent().setRight(t.getRight());
				}
				
				t.getRight().setParent(t.getParent());
				t = null;
				return;
			}
			
		}else if (t.getLeft() != null && t.getRight() != null) {
			// node to be deleted has 2 children
			
			t.OccuranceCountMinus1();
			
			if(t.getOccuranceCount() == 0) {
				TreeNode inOrderSucc = getInoderSucc(t);
				
				t.setVal( inOrderSucc.getVal() );
				t.setOccuranceCount( inOrderSucc.getOccuranceCount() );
				
				// Check if the inorder successor has right child
				if(inOrderSucc.getRight() != null) {

					// And the deleted node also has right child
					if(t.getRight() != null) {
						
						// Inorder's right becomes left of inorder's parent
						inOrderSucc.getParent().setLeft(inOrderSucc.getRight());
						inOrderSucc.getRight().setParent(inOrderSucc.getParent());
						inOrderSucc = null;

					}else {
						// Right child to be attached as swapped nodes right child
						t.setRight( inOrderSucc.getRight() );
						inOrderSucc.getRight().setParent(t);
						inOrderSucc.setParent(null);
					}
					return;
					
				}else if (t.getRight() != null) {
					//Check if the node to be deleted has right child, If it has then we need to delete the inorder successor from it's right subtree
					
					// Updating parent pointers for the swapped in-order node
					if(inOrderSucc.getParent().getLeft().equals(inOrderSucc)) {
						inOrderSucc.getParent().setLeft(null); 
					}else {
						inOrderSucc.getParent().setRight(null); 
					}

					inOrderSucc.setParent(null);
					inOrderSucc = null;
				}
				else {
					
					// If there is no right child then set the right to null
					t.setRight(null);
				}
			}
		}

	} // end of removeOne()


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

		// Union of the multiset is the max multiplicity of an element from given multisets
		// Inorder traversal of a tree returns the the sorted list.

		TreeNode firstTreePtr = getSmallestElementFromTree(this.getRoot());
		TreeNode secTreePtr = getSmallestElementFromTree(((BstMultiset) other).getRoot());

		while(firstTreePtr != null && secTreePtr != null) {

			if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) < 0) {
				
				retSet.add( firstTreePtr.getVal(), firstTreePtr.getOccuranceCount() );
				firstTreePtr = getInoderSucc(firstTreePtr);
				
			}else if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) > 0) {
				retSet.add( secTreePtr.getVal(), secTreePtr.getOccuranceCount() );
				secTreePtr = getInoderSucc(secTreePtr);
				
			}else if(firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) == 0) {
				
				retSet.add( firstTreePtr.getVal(), (firstTreePtr.getOccuranceCount() + secTreePtr.getOccuranceCount() ));
				firstTreePtr = getInoderSucc(firstTreePtr);
				secTreePtr = getInoderSucc(secTreePtr);
			}
		}

		// Exhausted second tree but first tree has elements
		while(firstTreePtr != null) {
			retSet.add(firstTreePtr.getVal(), firstTreePtr.getOccuranceCount());
			firstTreePtr = getInoderSucc(firstTreePtr);
		}

		// Exhausted first free tree but second tree has elements
		while(secTreePtr != null) {
			retSet.add( secTreePtr.getVal(), secTreePtr.getOccuranceCount() );
			secTreePtr = getInoderSucc(secTreePtr);
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
				firstTreePtr = getInoderSucc(firstTreePtr);
				
			}else if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) > 0) {
				// First node is larger than second element that also means no common elements. Just advance the second tree

				secTreePtr = getInoderSucc(secTreePtr);
				
			}else if(firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) == 0) {
				// Common element found. 
				
				retSet.add( firstTreePtr.getVal(), Math.min(firstTreePtr.getOccuranceCount(), secTreePtr.getOccuranceCount() ));
				
				// Since common element, advance both cursors
				firstTreePtr = getInoderSucc(firstTreePtr);
				secTreePtr = getInoderSucc(secTreePtr);
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
				firstTreePtr = getInoderSucc(firstTreePtr);
				
			}else if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) > 0) {
				// First node is greater than second node. Hence we don't need it. Just advance the second tree in this case 
				
				secTreePtr = getInoderSucc(secTreePtr);
				
			}else if(firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) == 0) {
				
				// multiset one contains more occurance than set 2
				if(firstTreePtr.getOccuranceCount() > secTreePtr.getOccuranceCount()) {
				
					retSet.add(firstTreePtr.getVal(), (firstTreePtr.getOccuranceCount() - secTreePtr.getOccuranceCount()));
				}
				
				// Advance both trees
				firstTreePtr = getInoderSucc(firstTreePtr);
				secTreePtr = getInoderSucc(secTreePtr);
			}
		}
		
		// 18-08-2020 - All remaining elements if any in the firstSet will be part of the results
		while(firstTreePtr != null) {
			retSet.add(firstTreePtr.getVal(), firstTreePtr.getOccuranceCount());
			firstTreePtr = getInoderSucc(firstTreePtr);
		}

		return retSet;

	} // end of difference()
	
	
	/*
	 * Find the inorder-successsor of the element
	 */
	private TreeNode getInoderSucc(TreeNode givenEle) {

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

	
	// Leftmost element in the left sub tree is the smallest element
	private TreeNode getSmallestElementFromTree(TreeNode root){

		TreeNode t = root;
		// Stop when the left child is null
		while(t != null && t.getLeft() != null)
		{
			t = t.getLeft();
		}

		return t;
	}
	
	
	/*
	 * Returns the first occurance of the node in the tree.
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

} // end of class BstMultiset
