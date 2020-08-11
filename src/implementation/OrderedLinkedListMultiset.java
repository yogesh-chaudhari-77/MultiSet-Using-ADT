package implementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Ordered linked list implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */

public class OrderedLinkedListMultiset extends RmitMultiset
{

	ListNode head;

	public OrderedLinkedListMultiset() {
		head = null;
	}


	@Override
	public void add(String item) {

		// If there is no list, create a head node
		if(this.head == null) {
			head = new ListNode(item);
		}else {

			// List is already present and contains certains only one node - head
			ListNode prev = head;
			ListNode curr = head;

			while(curr != null) {

				if(item.compareTo(curr.getVal()) < 0) {
					
					ListNode newNode = new ListNode(item);
					newNode.setNext(prev.getNext());
					prev.setNext(newNode);
				}
				
				prev = curr;
				curr = curr.getNext();
			}
		}

	} // end of add()


	@Override
	public int search(String item) {

		int occuranceCount = 0;
		
		// Check if the list is there or not
		if(this.head != null) {
			
			ListNode currNode = head;
			
			// Iterate till end of the list is reached & current node value is less than provided item. List is ordered
			while( (currNode != null) && (currNode.getVal().compareTo(item) < 0) ) {
			
				if(currNode.getVal().compareTo(item) == 0) {
					occuranceCount += 1; 
				}
				
				currNode = currNode.getNext();
			}
		}
		
		return occuranceCount;
	} // end of search()


	@Override
	public List<String> searchByInstance(int instanceCount) {

		List<String> retList = new ArrayList<String>();
		
		// Check if the list is there or not
		if(this.head != null) {
			
			ListNode currNode = head;
			String currNodeVal = currNode.getVal();
			
			int foundInstanceCount = 0;

			// Iterate till end of the list is reached
			while(currNode != null) {
				
				if(currNode.getVal().compareTo(currNodeVal) == 0 ) {
					foundInstanceCount += 1;
				}else {
					
					if(instanceCount == foundInstanceCount) {
						retList.add(currNodeVal);
					}
					
					foundInstanceCount = 1;
					currNodeVal = currNode.getVal();
				}
				
				currNode = currNode.getNext();
			}
		}
		
		return retList;
	} // end of searchByInstance


	@Override
	public boolean contains(String item) {
		
		boolean contains = false;
		
		// Check if the list is there or not
		if(this.head != null) {
			
			ListNode currNode = head;
			
			// Iterate till end of the list is reached & current node value is less than provided item. List is ordered
			while( (currNode != null) && (currNode.getVal().compareTo(item) < 0) ) {
			
				if(currNode.getVal().compareTo(item) == 0) {
					contains = true;
					break;
				}
				
				currNode = currNode.getNext();
			}
		}
		
		return contains;
	} // end of contains()


	@Override
	public void removeOne(String item) {
		
		// Check if the list is there or not
		if(this.head != null) {
			
			ListNode currNode = head;
			ListNode prevNode = head;
			
			// Iterate till end of the list is reached & current node value is less than provided item. List is ordered
			while( (currNode != null) && (currNode.getVal().compareTo(item) < 0) ) {
			
				// Element is found in the list
				if(currNode.getVal().compareTo(item) == 0) {
					
					// set the previous element's next to current's element next. Set curr element to null to delete
					prevNode.setNext(currNode.getNext());
					currNode = null;
					break;
				}
				
				prevNode = currNode;
				currNode = currNode.getNext();
			}
		}
		
	} // end of removeOne()


	@Override
	public String print() {
		
		
		
		return new String();
	} // end of OrderedPrint


	@Override
	public String printRange(String lower, String upper) {

		StringBuilder retString = new StringBuilder();
		
		// Check if the list is there or not
		if(this.head != null) {

			ListNode currNode = head;

			// Iterate till end of the list is reached & current node value is less than provided item. List is ordered
			while( (currNode != null) && (currNode.getVal().compareTo(upper) <= 0) ) {

				// Element is found in the list
				if( ( currNode.getVal().compareTo(lower) > 0 ) && (currNode.getVal().compareTo(upper) < 0)) {

					// set the previous element's next to current's element next. Set curr element to null to delete
					retString.append(currNode.getVal()+",");
				}

				currNode = currNode.getNext();
			}
		}

		return retString.toString();
	} // end of printRange()


	@Override
	public RmitMultiset union(RmitMultiset other) {

		OrderedLinkedListMultiset retMultiset = new OrderedLinkedListMultiset();
		
		ListNode firstSetNode = this.head;
		ListNode secSetNode = ((OrderedLinkedListMultiset) other).getHead();
		
		while(firstSetNode != null && secSetNode != null) {
			
			if(firstSetNode.getVal().compareTo(secSetNode.getVal()) < 0){
				
				retMultiset.add(firstSetNode.getVal());
				firstSetNode = firstSetNode.getNext();
				
			}else if(firstSetNode.getVal().compareTo(secSetNode.getVal()) == 0){
				
				retMultiset.add(firstSetNode.getVal());
				firstSetNode = firstSetNode.getNext();
				secSetNode = secSetNode.getNext();
			}else {
				
				retMultiset.add(secSetNode.getVal());
				secSetNode = secSetNode.getNext();
			}
			
		}
		
		// We have reached at the end of the secondMultiset, but there are elements in firstList.
		while(firstSetNode != null) {
			retMultiset.add(firstSetNode.getVal());
			firstSetNode = firstSetNode.getNext();
		}
		
		// We have reached at the end of firstMultiset, but there are elements in secondList
		while(secSetNode != null) {
			retMultiset.add(secSetNode.getVal());
			secSetNode = secSetNode.getNext();
		}
		
		return (RmitMultiset) retMultiset;
	} // end of union()


	@Override
	public RmitMultiset intersect(RmitMultiset other) {

		OrderedLinkedListMultiset retMultiset = new OrderedLinkedListMultiset();
		
		ListNode firstSetNode = this.head;
		ListNode secSetNode = ((OrderedLinkedListMultiset) other).getHead();

		while(firstSetNode != null && secSetNode != null) {

			if(firstSetNode.getVal().compareTo(secSetNode.getVal()) == 0){

				retMultiset.add(firstSetNode.getVal());
				firstSetNode = firstSetNode.getNext();
				secSetNode = secSetNode.getNext();
			}
			else if(firstSetNode.getVal().compareTo(secSetNode.getVal()) < 0){
				
				firstSetNode = firstSetNode.getNext();

			}else {
				
				secSetNode = secSetNode.getNext();
			}
		}
		
		return (RmitMultiset) retMultiset;
	} // end of intersect()


	@Override
	public RmitMultiset difference(RmitMultiset other) {

		OrderedLinkedListMultiset retMultiset = new OrderedLinkedListMultiset();
		
		ListNode firstSetNode = this.head;
		ListNode secSetNode = ((OrderedLinkedListMultiset) other).getHead();

		while(firstSetNode != null && secSetNode != null) {

			if(firstSetNode.getVal().compareTo(secSetNode.getVal()) == 0){
				firstSetNode = firstSetNode.getNext();
				secSetNode = secSetNode.getNext();
			}
			else if(firstSetNode.getVal().compareTo(secSetNode.getVal()) < 0){

				retMultiset.add(firstSetNode.getVal());
				firstSetNode = firstSetNode.getNext();

			}else {
				secSetNode = secSetNode.getNext();
			}
		}
		
		return (RmitMultiset) retMultiset;
	} // end of difference()



	// Getter Setters Starts Here
	
	public ListNode getHead() {
		return head;
	}


	public void setHead(ListNode head) {
		this.head = head;
	}


	// Getter Setters Ends Here

} // end of class OrderedLinkedListMultiset
