package implementation;

public class TreeNode{
	
	// Stores the value of the node
	private String val;
	
	// Stores the occurance count of the value in the tree
	private int occuranceCount;
	
	// Pointer to left child
	private TreeNode left;
	
	// Pointer to right child
	private TreeNode right;
	
	// Pointer to parent node
	private TreeNode parent;
	
	public TreeNode() {
		val = null;
		occuranceCount = 0;
		left = null;
		right = null;
		parent = null;
	}
	
	public TreeNode(String val) {

		this.val = val;
		
		// New tree node is being created. So it must be 1
		this.occuranceCount = 1;
		
		left = null;
		right = null;
		parent = null;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public int getOccuranceCount() {
		return occuranceCount;
	}

	public void setOccuranceCount(int occuranceCount) {
		this.occuranceCount = occuranceCount;
	}
	
	
	// To be called after adding the node which is already present
	public void OccuranceCountPlus1() {
		this.setOccuranceCount( this.getOccuranceCount() + 1 );
	}

	// To be called after remove a node which is already present
	public void OccuranceCountMinus1() {
		this.setOccuranceCount( this.getOccuranceCount() - 1 );	
	}
}