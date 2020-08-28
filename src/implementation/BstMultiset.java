package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * BST implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class BstMultiset extends RmitMultiset
{


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
			root = new TreeNode();
			root.setVal(item);
		}else {

			// There is tree, figure out where to put it
			appendTreeNode(this.getRoot(), item);
		}

	} // end of add()


	private void appendTreeNode(TreeNode node, String item) {

		if(item.compareTo( node.getVal() ) < 0) {
			if( node.getLeft() == null ) {
				TreeNode childNode = new TreeNode(item);
				node.setLeft( childNode );
				childNode.setParent( node );
			}
			else {
				this.appendTreeNode(node.getLeft(), item);
			}
		}
		else if( item.compareTo(node.getVal()) > 0 || item.compareTo(node.getVal()) == 0) {		// Need to discuss this condition
			if ( node.getRight() == null ) {
				TreeNode childNode = new TreeNode(item);
				node.setRight(childNode);
				childNode.setParent( node );
			}
			else {
				this.appendTreeNode(node.getRight(), item);
			}
		}
	}


	@Override
	public int search(String item) {

		// In order traversal gives elements in the sorted order
		TreeNode t = this.getRoot();
		int occuranceCount = 0;

		// Navigate to that treeNode using power of BST
		while(t != null) {
			if(t.getVal().compareTo(item) < 0) {
				t = t.getRight();
			}else if(t.getVal().compareTo(item) > 0) {
				t = t.getLeft();
			}else {
				break;
			}
		}

		while(t != null && t.getVal().compareTo(item) <= 0) {

			if(t.getVal().compareTo(item) == 0) {
				occuranceCount += 1;
			}

			t = this.getInoderSucc(t);
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

		int occuranceCount = 0;
		Stack<TreeNode> poStack = new Stack<TreeNode>();
		poStack.push(this.getRoot());

		while(! poStack.empty() ) {

			TreeNode currNode = poStack.pop();
			String currNodeVal = currNode.getVal();

			if(retList.size() == 0) {
				retList.add(currNode.getVal());
				occuranceCount += 1;
			}else {

				if( retList.get( retList.size() - 1).compareTo(currNodeVal) == 0) {
					occuranceCount += 1;
				}else if( retList.get( retList.size() - 1).compareTo(currNodeVal) < 0) {

					if(occuranceCount != instanceCount) {
						retList.remove( retList.size() - 1);
					}

					retList.add(currNodeVal);
					occuranceCount = 1;
				}

			}

			// If current tree node has right child, push that on stack
			if(currNode.getRight() != null) {
				poStack.push(currNode.getRight());
			}

			if(currNode.getLeft() != null) {
				poStack.push(currNode.getLeft());
			}

		}

		return null;
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

		return false;
	} // end of contains()


	@Override
	public void removeOne(String item) {

		
		TreeNode t = getFirstOccurance(item);
		
		// Element is leaf element
		if(t.getLeft() == null && t.getRight() == null) {
			
			t = null;
			
		}else if ( t.getLeft() != null && t.getRight() == null) {
			
			t.getParent().setLeft(t.getLeft());
			t = null;
			
		}else if( t.getLeft() == null && t.getRight() != null ) {
			
			t.getParent().setRight(t.getLeft());
			t = null;
			
		}else if (t.getLeft() != null && t.getRight() != null) {
			
			TreeNode inOrderSucc = getInoderSucc(t);
			t.setVal( inOrderSucc.getVal() );
			inOrderSucc = null;
		}

	} // end of removeOne()


	@Override
	public String print() {

<<<<<<< Updated upstream
		// Placeholder, please update.
		return new String();
=======
		// Clone the multiset in Array implementation. This is required because, we need to print element based on it's onccurance count
		ArrayMultiset multisetArr = new ArrayMultiset();

		TreeNode curr = this.getSmallestElementFromTree( this.getRoot() );
		
		// Iterate over current list
		while(curr != null) {
			
			for (int i = 0; i < curr.getOccuranceCount(); i++) {
//				System.out.println(curr.getVal());
				multisetArr.add(curr.getVal());
			}
			
			curr = this.getInoderSucc(curr);
		}
		
		// This method returns the string representation of the multiset sorted based on occurance count
		return multisetArr.print();
		
>>>>>>> Stashed changes
	} // end of OrderedPrint


	@Override
	public String printRange(String lower, String upper) {

		StringBuilder retStr = new StringBuilder();

		TreeNode t = getSmallestElementFromTree(this.getRoot());
		while(t != null && t.getVal().compareTo(upper) <= 0) {

			if(t.getVal().compareTo(lower) >= 0) {
				retStr.append(t.getVal()+",");
			}
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
				retSet.add( firstTreePtr.getVal() );
				firstTreePtr = getInoderSucc(firstTreePtr);
			}else if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) > 0) {
				retSet.add( secTreePtr.getVal() );
				secTreePtr = getInoderSucc(secTreePtr);
			}else if(firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) == 0) {
				retSet.add( firstTreePtr.getVal() );
				firstTreePtr = getInoderSucc(firstTreePtr);
				secTreePtr = getInoderSucc(secTreePtr);
			}
		}

		// Exhausted second tree but first tree has elements
		while(firstTreePtr != null) {
			retSet.add(firstTreePtr.getVal());
			firstTreePtr = getInoderSucc(firstTreePtr);
		}

		// Exhausted first free tree but second tree has elements
		while(secTreePtr != null) {
			retSet.add(secTreePtr.getVal());
			secTreePtr = getInoderSucc(secTreePtr);
		}

		return retSet;
	} // end of union()


	@Override
	public RmitMultiset intersect(RmitMultiset other) {

		BstMultiset retSet = new BstMultiset();

		TreeNode firstTreePtr = getSmallestElementFromTree(this.getRoot());
		TreeNode secTreePtr = getSmallestElementFromTree(((BstMultiset) other).getRoot());

		while(firstTreePtr != null && secTreePtr != null) {

			if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) < 0) {
				
				firstTreePtr = getInoderSucc(firstTreePtr);
				
			}else if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) > 0) {
				
				secTreePtr = getInoderSucc(secTreePtr);
				
			}else if(firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) == 0) {
				
				retSet.add( firstTreePtr.getVal() );
				firstTreePtr = getInoderSucc(firstTreePtr);
				secTreePtr = getInoderSucc(secTreePtr);
			}
		}

		return retSet;

	} // end of intersect()


	@Override
	public RmitMultiset difference(RmitMultiset other) {

		BstMultiset retSet = new BstMultiset();

		TreeNode firstTreePtr = getSmallestElementFromTree(this.getRoot());
		TreeNode secTreePtr = getSmallestElementFromTree(((BstMultiset) other).getRoot());

		while(firstTreePtr != null && secTreePtr != null) {

			if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) < 0) {
				retSet.add( firstTreePtr.getVal() );
				firstTreePtr = getInoderSucc(firstTreePtr);
				
			}else if( firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) > 0) {
				secTreePtr = getInoderSucc(secTreePtr);
				
			}else if(firstTreePtr.getVal().compareTo( secTreePtr.getVal() ) == 0) {
				
				firstTreePtr = getInoderSucc(firstTreePtr);
				secTreePtr = getInoderSucc(secTreePtr);
			}
		}

		return retSet;

	} // end of difference()
	
	
	/*
	 * Find the inorder-successsor of the element
	 *
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

		while(parent != null && parent.getRight() == t) {
			t = parent;
			parent = t.getParent();
		}

		return parent;
	}

	
	private TreeNode getSmallestElementFromTree(TreeNode root){

		TreeNode t = root;
		while(t != null && t.getLeft() != null)
		{
			t = t.getLeft();
		}

		return t;
	}
	
	public TreeNode getFirstOccurance(String item) {
		
		TreeNode currNode = this.getRoot();
		
		while(currNode != null) {
			
			// Element could be in right sub tree
			if( currNode.getVal().compareTo(item) < 0) {
				
				currNode = currNode.getRight();
			}
			else if( currNode.getVal().compareTo(item) > 0) {
				
				// Element could be in left sub tree
				currNode = currNode.getLeft();
			}else {
				
				// Current node is the element I am looking for
				return currNode;
			}
		}
		
		return null;
	}

} // end of class BstMultiset
