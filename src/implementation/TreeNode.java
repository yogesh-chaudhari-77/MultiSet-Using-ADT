package implementation;

public class TreeNode{
	
	private String val;
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode() {
		val = null;
		left = null;
		right = null;
	}
	
	public TreeNode(String val) {
		this.val = val;
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

}