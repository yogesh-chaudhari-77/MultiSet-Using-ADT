package implementation;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Ordered linked list implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 * @contributor Yogeshwar Chaudhari, RMIT University, Master of Information Technology
 * 
 * References
 * [1] Linked List | Set 1 (Introduction) - GeeksforGeeks
   Linked List | Set 1 (Introduction) - GeeksforGeeks (2013). Available at: https://www.geeksforgeeks.org/linked-list-set-1-introduction/ (Accessed: 26 August 2020).

 * [2] Linked List | Set 2 (Inserting a node) - GeeksforGeeks
   Linked List | Set 2 (Inserting a node) - GeeksforGeeks (2013). Available at: https://www.geeksforgeeks.org/linked-list-set-2-inserting-a-node/ (Accessed: 26 August 2020).
   
   [3] Linked List | Set 3 (Deleting a node) - GeeksforGeeks
   Linked List | Set 3 (Deleting a node) - GeeksforGeeks (2014). Available at: https://www.geeksforgeeks.org/linked-list-set-3-deleting-node/ (Accessed: 26 August 2020).
 */

public class OrderedLinkedListMultiset extends RmitMultiset
{

	ListNode head;

	public OrderedLinkedListMultiset() {
		head = null;
	}


	@Override
	//[2]
	public void add(String item) {

		// If there is no list, create a head node
		if(this.head == null) {
			
			this.head = new ListNode(item);
			
			return;
		}else {

			// List is already present

			// case 1 : Insert at the beginning of the list
			if(head.getVal().compareTo(item) > 0) {
				ListNode newNode = new ListNode(item);
				newNode.setNext(head);
				head = newNode;
				return;
			}

			// case 2 : List contains several elements, insert at middle or at the end.
			ListNode prev = head;
			ListNode curr = head;

			while(curr != null) {

				// Check if the similar element is already there in list
				if(curr.getVal().compareTo(item) == 0) {
					
					// If present, just increament it's occurance count
					curr.incrementOccurenceCount();
					return;
				}
				else if(item.compareTo(curr.getVal()) < 0) {

					// Appropriate place found. Insert an element;
					ListNode newNode = new ListNode(item);
					newNode.setNext(prev.getNext());
					prev.setNext(newNode);

					// We have added the new element. Return to caller.
					return;
					
				}else if(curr.getNext() == null) {
					
					// End of the list reached
					ListNode newNode = new ListNode(item);
					curr.setNext(newNode);
					return;
				}

				// Advance cursors
				prev = curr;
				curr = curr.getNext();
			}
		}

	} // end of add()

	
	// custom method for union
	// [2]
	public void add(String item, int occuranceCount) {

		// If there is no list, create a head node
		if(this.head == null) {
			head = new ListNode(item);
			head.setOccuranceCount(occuranceCount);
			return;
		}else {

			// List is already present

			// case 1 : Insert at the beginning of the list
			if(head.getVal().compareTo(item) > 0) {
				ListNode newNode = new ListNode(item);
				newNode.setOccuranceCount(occuranceCount);
				newNode.setNext(head);
				head = newNode;
				return;
			}

			// case 2 : List contains several elements, insert at middle or at the end.
			ListNode prev = head;
			ListNode curr = head;

			while(curr != null) {

				// Check if the similar element is already there in list
				if(curr.getVal().compareTo(item) == 0) {
					
					// If present, just increament it's occurance count
					curr.setOccuranceCount( curr.getOccuranceCount() + occuranceCount);
					return;
				}
				else if(item.compareTo(curr.getVal()) < 0) {

					// Appropriate place found. Insert an element;
					ListNode newNode = new ListNode(item);
					newNode.setOccuranceCount(occuranceCount);
					newNode.setNext(prev.getNext());
					prev.setNext(newNode);

					// We have added the new element. Return to caller.
					return;
					
				}else if(curr.getNext() == null) {
					
					// End of the list reached. Add new tail element.
					ListNode newNode = new ListNode(item);
					newNode.setOccuranceCount(occuranceCount);
					curr.setNext(newNode);
					return;
				}

				// Advance cursors
				prev = curr;
				curr = curr.getNext();
			}
		}

	} // end of add()

	
	@Override
	public int search(String item) {

		int occuranceCount = -1;

		// Check if the list is there or not
		if(this.head != null) {

			ListNode currNode = head;

			// Iterate till end of the list is reached & current node value is less than or equal to provided item. Taking advantage of ordered list
			while( (currNode != null) && (currNode.getVal().compareTo(item) <= 0) ) {

				if(currNode.getVal().compareTo(item) == 0) {
					// Element found. Get the occurance count and return
					occuranceCount = currNode.getOccuranceCount();
					break;
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

			// Iterate till end of the list is reached
			while(currNode != null) {

				// Counting the occurance of an individual element.
				if(currNode.getOccuranceCount() == instanceCount ) {
					retList.add( currNode.getVal() );
				}
				
				// Advance to next node
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

			// Iterate till end of the list is reached & current node value is less than or equal to provided item. List is ordered
			while( (currNode != null) && (currNode.getVal().compareTo(item) <= 0) ) {

				// Current node matches with the item, return true
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
	//[3]
	public void removeOne(String item) {

		// Check if the list is there or not
		if(this.head != null) {

			ListNode currNode = head;
			ListNode prevNode = head;

			// Iterate till end of the list is reached & current node value is less than or equal to provided item. List is ordered
			while( (currNode != null) && (currNode.getVal().compareTo(item) <= 0) ) {

				// Element is found in the list
				if(currNode.getVal().compareTo(item) == 0) {
					
					currNode.decrementOccurenceCount();
				
					// If the occurance count is 0 then we need to delete that node as well;
					if (currNode.getOccuranceCount() == 0) {
						
						// prevNode->currNode->nextNode => prevNode->nextNode
						prevNode.setNext(currNode.getNext());
						currNode = null;
					}
				
					break;
				}

				prevNode = currNode;
				currNode = currNode.getNext();
			}
		}

	} // end of removeOne()


	@Override
	public String print() {
		
		// Clone the multiset in Array implementation. This is required because, we need to print element based on it's onccurance count
		ArrayMultiset multisetArr = new ArrayMultiset();

		ListNode curr = this.getHead();
		
		// Iterate over current list
		while(curr != null) {
			
			for (int i = 0; i < curr.getOccuranceCount(); i++) {
				multisetArr.add(curr.getVal());
			}
			
			curr = curr.getNext();
		}
		
		// This method returns the string representation of the multiset sorted based on occurance count
		return multisetArr.print();
		
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
				if( ( currNode.getVal().compareTo(lower) >= 0 ) && (currNode.getVal().compareTo(upper) <= 0) ) {

					// set the previous element's next to current's element next. Set curr element to null to delete
					retString.append(currNode.getVal()+":"+currNode.getOccuranceCount()+"\n");
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

		// Traverse through both multiset at the same time.
		while(firstSetNode != null && secSetNode != null) {

			// Current pointer at firstList is pointing to smaller element than Current pointer at secondList
			if(firstSetNode.getVal().compareTo(secSetNode.getVal()) < 0){

				// Collect the element from firstList. This is to maintain the order
				retMultiset.add(firstSetNode.getVal(), firstSetNode.getOccuranceCount());
				firstSetNode = firstSetNode.getNext();

			}else if(firstSetNode.getVal().compareTo(secSetNode.getVal()) == 0){

				// Both pointers are pointing to elements having same values

				// Collect both elements
				retMultiset.add(firstSetNode.getVal(), (firstSetNode.getOccuranceCount() + secSetNode.getOccuranceCount()));

				// Advance both cursors
				firstSetNode = firstSetNode.getNext();
				secSetNode = secSetNode.getNext();

			}else {

				// Current pointer at secondList is pointing to smaller element than Current pointer at FirstList

				// Collect element from secondList and advance the cursor. This is to make sure that we collect elements in ascending order
				retMultiset.add(secSetNode.getVal(), secSetNode.getOccuranceCount());
				secSetNode = secSetNode.getNext();
			}

		}

		// We have reached at the end of the secondMultiset, but there are elements in firstList.
		while(firstSetNode != null) {
			retMultiset.add(firstSetNode.getVal(), firstSetNode.getOccuranceCount());
			firstSetNode = firstSetNode.getNext();
		}

		// We have reached at the end of firstMultiset, but there are elements in secondList
		while(secSetNode != null) {
			retMultiset.add(secSetNode.getVal(), secSetNode.getOccuranceCount());
			secSetNode = secSetNode.getNext();
		}

		return (RmitMultiset) retMultiset;
	} // end of union()


	@Override
	public RmitMultiset intersect(RmitMultiset other) {

		OrderedLinkedListMultiset retMultiset = new OrderedLinkedListMultiset();

		ListNode firstSetNode = this.head;
		ListNode secSetNode = ((OrderedLinkedListMultiset) other).getHead();

		// Do this until we reach at any of the lists end
		while(firstSetNode != null && secSetNode != null) {

			// If we find common elements in both list, collect it.
			if(firstSetNode.getVal().compareTo(secSetNode.getVal()) == 0){

				retMultiset.add(firstSetNode.getVal(), Math.min(firstSetNode.getOccuranceCount(), secSetNode.getOccuranceCount()));

				// Since common, advance both cursors
				firstSetNode = firstSetNode.getNext();
				secSetNode = secSetNode.getNext();
				
			}
			else if(firstSetNode.getVal().compareTo(secSetNode.getVal()) < 0){

				// This means that firstList cursor is pointing at smaller element that in secondList.
				// This also means that both are not common
				// Advance the firstList cursor
				firstSetNode = firstSetNode.getNext();
			}else {

				// This means that secondList cursor is pointing at smaller element that in firstList.
				// This also means that both are not common
				// Advance the secondList cursor
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
		
		// Traverse both sets at the same time
		while(firstSetNode != null && secSetNode != null) {

			// Both values are same, so could be part of set if
			if(firstSetNode.getVal().compareTo(secSetNode.getVal()) == 0){
				
				// Multiset one contains more occurance than set 2, hence difference occurance will be inserted into the tree
				if(firstSetNode.getOccuranceCount() > secSetNode.getOccuranceCount()) {
					retMultiset.add(firstSetNode.getVal(), ( firstSetNode.getOccuranceCount() - secSetNode.getOccuranceCount() ));
				}
				
				firstSetNode = firstSetNode.getNext();
				secSetNode = secSetNode.getNext();
			}
			else if(firstSetNode.getVal().compareTo(secSetNode.getVal()) < 0){

				// firstSetNode value is smaller than secSetNode. Make it part of result as it is an element which is not part of the secSet
				retMultiset.add(firstSetNode.getVal(), firstSetNode.getOccuranceCount());
				firstSetNode = firstSetNode.getNext();

			}else {
				
				// secSetNode value is larger than firstSetNode val. Do nothing. Just advance the cursor.
				secSetNode = secSetNode.getNext();
			}
		}
		
		
		// 17-08-2020 - All remaining elements if any in the firstSet will be part of the results
		while(firstSetNode != null) {
			retMultiset.add(firstSetNode.getVal());
			firstSetNode = firstSetNode.getNext();
		}

		return (RmitMultiset) retMultiset;
	} // end of difference()



	/*
	 * Getter Setters Starts Here
	 */

	public ListNode getHead() {
		return head;
	}


	public void setHead(ListNode head) {
		this.head = head;
	}

	/*
	 *  Getter Setters Ends Here
	 */

} // end of class OrderedLinkedListMultiset
