package implementation;

import java.util.ArrayList;
import java.util.List;

import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;


/**
 * BST implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class BstMultiset extends RmitMultiset
{

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}


	TreeNode root;
	
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
    		if( node.getLeft() == null )
                node.setLeft( new TreeNode(item) );
            else {
            	this.appendTreeNode(node.getLeft(), item);
            }
    	}
    	else if( item.compareTo(node.getVal()) > 0 || item.compareTo(node.getVal()) == 0) {		// Need to discuss this condition
    		if ( node.getRight() == null ) {
    			node.setRight(new TreeNode(item));
    		}
            else {
            	this.appendTreeNode(node.getRight(), item);
            }
    	}
    }
    
	
    @Override
	public int search(String item) {

    	// Pre-order traversal gives elements in sorted order
    	class InnerClass{
    		public int occuranceCount = 0;
    		
    		public int searchUsingPreorderTraversal(TreeNode node, String item) {
    			
    			if( node != null ) {
    	        	if(node.getVal().compareTo(item) == 0) {
    	        		this.occuranceCount += 1;
    	        	}else if(node.getVal().compareTo(item) > 0) {
    	        		
    	        		// There is no point in 
    	        		return occuranceCount;
    	        	}
    	            this.searchUsingPreorderTraversal(node.getLeft(), item);
    	            this.searchUsingPreorderTraversal(node.getRight(), item);
    	        }

				return occuranceCount;
    		}
    	}
    	
    	int occuranceCount = (new InnerClass()).searchUsingPreorderTraversal(this.getRoot(), item);
    	
        return occuranceCount;
    } // end of search()

    
	@Override
	public List<String> searchByInstance(int instanceCount) {

		List<String> retList = new ArrayList<String>();
		
		class InnerClass{
    		public int occuranceCount = 0;
    		public ArrayList<String> preorderList = new ArrayList<String>();
    		
    		public ArrayList<String> preorderTraversal(TreeNode node, String item) {
    			
    			prevNode = node;
    			
    			if( node != null ) {
    				if(prevNode.getVal().compareTo(node.getVal()) == 0) {
    					occuranceCount += 1;
    				}else {
    					
    				}
    	            this.preorderTraversal(node.getLeft(), item);
    	            this.preorderTraversal(node.getRight(), item);
    	        }

				return this.preorderList;
    		}
    	}
		
		
        return null;
    } // end of searchByInstance    


    @Override
	public boolean contains(String item) {
        
    	class InnerClass {
    		public boolean findElement(TreeNode node, String item) {
    			if(node != null) {
    	    		if(item.compareTo(node.getVal()) < 0) {
    	    			this.findElement(node, item);
    	    		}else if(item.compareTo(node.getVal()) > 0) {
    	    			this.findElement(node, item);
    	    		}else if(item.compareTo(node.getVal()) == 0){
    	    			return true;
    	    		}
    	    	}
    			
    			return false;
    		}
    	}
    	
    	boolean searchResult = (new InnerClass()).findElement(root, item);
    	
        return searchResult;
    } // end of contains()


    @Override
	public void removeOne(String item) {
        // Implement me!
    } // end of removeOne()


    @Override
	public String print() {

        // Placeholder, please update.
        return new String();
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {

        // Placeholder, please update.
        return new String();
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {

        return null;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of difference()

} // end of class BstMultiset
