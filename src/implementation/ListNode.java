package implementation;

public class ListNode{

	private String val;
	private ListNode next;

	public ListNode() {
		val = null;
		next = null;
	}

	public ListNode(String val) {
		this.val = val;
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

}