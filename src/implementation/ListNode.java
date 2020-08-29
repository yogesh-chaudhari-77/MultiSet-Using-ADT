package implementation;


/**
 * @Author Yogeshwar Chaudhari, RMIT University, Master of Information Technology
 */
public class ListNode{

	// Stores the actual node value
	private String val;
	
	// Stores the occurance count of that value in the List
	private int occuranceCount;
	
	// Pointer to node successor
	private ListNode next;

	
	public ListNode() {
		val = null;
		occuranceCount = 0;
		next = null;
	}

	
	public ListNode(String val) {
		this.val = val;
		
		// Creating new node so the default occurance will be 1 only;
		this.occuranceCount = 1;
		
		this.next = null;
	}

	
	public ListNode(String val, int occuranceCount) {
		this.val = val;
		
		// Creating new node so the default occurance will be 1 only;
		this.occuranceCount = occuranceCount;
		
		this.next = null;
	}
	

	
	public String getVal() {
		return val;
	}

	
	public void setVal(String val) {
		this.val = val;
	}

	
	public ListNode getNext() {
		return next;
	}

	
	public void setNext(ListNode next) {
		this.next = next;
	}

	
	public int getOccuranceCount() {
		return occuranceCount;
	}

	
	public void setOccuranceCount(int occuranceCount) {
		this.occuranceCount = occuranceCount;
	}
	
	
	// To be called after adding the node which is already present
	public void incrementOccurenceCount() {
		this.setOccuranceCount( this.getOccuranceCount() + 1 );
	}

	// To be called after remove a node which is already present
	public void decrementOccurenceCount() {
		this.setOccuranceCount( this.getOccuranceCount() - 1 );	
	}
}
