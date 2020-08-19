//package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Dual linked list implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class DualLinkedListMultiset extends RmitMultiset
{
	ListNode list1Head,list2Head;
    private int listLength = 0;
	
    public DualLinkedListMultiset() {
    	list1Head = null;
    	list2Head = null;
    }
    
    
    @Override
	public void add(String item) {
        
    	boolean elementFound = false;
    	boolean elementAdded = false;
    	
    	
    	if (list1Head == null)
        {
        	list1Head = new ListNode(item);
        	list2Head = new ListNode(item);
        	this.listLength++;
        }
        else
        {
        	//Adding an element to the non-empty list1 and maintaining order based on element
        	{
        		ListNode nextNode = list1Head;
        		//If an element is already present in the list, then just incrementing the instance count
        		while(nextNode != null)
        		{
        			if(nextNode.getVal().contentEquals(item))
        			{
        				nextNode.incrementOccurenceCount();
        				elementFound = true;
        				break;
        			}
        			else
        				nextNode = nextNode.getNext();
        		}
        		
        		//If it is an new element, then adding it at the right place based on alphabetical order
        		if (!elementFound)
        		{
        			ListNode currentNode1 = list1Head;
        			ListNode previousNode1 = list1Head;
        			ListNode newNode1 = new ListNode(item);
        			
        			
        			//New element to be inserted at the head.
        			if (currentNode1.getVal().compareTo(item) > 0)
        			{
        				newNode1.setNext(currentNode1);
        				list1Head = newNode1;
        				elementAdded = true;
        	        	this.listLength++;
        			}

        			
        			currentNode1 = list1Head.getNext();
        			
        			
        			//New Element to be inserted between the elements already present in the list
        			while (currentNode1 != null && !elementAdded)
        			{
        				if(currentNode1.getVal().compareTo(item) > 0)
        				{
        					newNode1.setNext(currentNode1);
        					previousNode1.setNext(newNode1);
        					elementAdded = true;
        		        	this.listLength++;
        				}
        				else
        					previousNode1 = currentNode1;
        					currentNode1 = currentNode1.getNext();
        			}
        			//New Element to be inserted at end of the list
        			if (!elementAdded)
        			{
        				previousNode1.setNext(newNode1);
        	        	this.listLength++;
        			}
        		}
//        	System.out.println("Checkpoint 1");
        	}

        	//Adding an element to the list2 and maintaining order based on Instance Count
        	{
        			ListNode prevNode2 = list2Head;
        			ListNode currNode2 = list2Head;
        			ListNode nextNode2 = currNode2.getNext();
        			boolean elementFound2 = false;
            		//If an element is already present in the list, then just incrementing the instance count
            		while(currNode2 != null && !elementFound2)
            		{
            			if(currNode2.getVal().contentEquals(item))
            			{
            				currNode2.incrementOccurenceCount();
            				while(currNode2.getNext() != null)
            				{
            					if (currNode2.getOccuranceCount() > currNode2.getNext().getOccuranceCount())
            					{
            						ListNode temp = currNode2.getNext().getNext();

            						if (currNode2.equals(list2Head))
            						{
            							list2Head = (currNode2.getNext());
            							list2Head.setNext(currNode2);
            							prevNode2 = currNode2.getNext();
            							currNode2.setNext(temp);
                						
            						}
            						else
            						{
            						prevNode2.setNext(currNode2.getNext());
            						currNode2.getNext().setNext(currNode2);
            						currNode2.setNext(temp);
            						prevNode2 = prevNode2.getNext();
            						}
            					}
            					else 
            						break;
            				}
            				elementFound2 = true;
            			}
            			else
            			{
            				prevNode2 = currNode2;
            				currNode2 = currNode2.getNext();
            			}            		
            		}
            		
            		if (!elementFound2)
            		{
            			ListNode temp2 = list2Head;
            			ListNode newNode2 = new ListNode(item);
            			newNode2.setNext(temp2);
            			list2Head = newNode2;
            		}      		
        	}
        }	
    	
    	ListNode printNode = list1Head;
    	System.out.println("List 1 based on Element order");
    	while(printNode != null)
    	{
    		System.out.println ("Node Value: " + printNode.getVal() + "       Instance Count: " + printNode.getOccuranceCount());
    		printNode = printNode.getNext();
    	}
    	
    	ListNode printNode2 = list2Head;
    	System.out.println("List 2 based on Instance count order");
    	while(printNode2 != null)
    	{
    		System.out.println ("Node Value: " + printNode2.getVal() + "       Instance Count: " + printNode2.getOccuranceCount());
    		printNode2 = printNode2.getNext();
    	}
    	System.out.println("List length : " + this.listLength);
    	// Implement me!
    } // end of add()


    @Override
	public int search(String item) {
        // Implement me!
    	ListNode searchNode = this.list1Head;
    	int result = -1; 
    	boolean elementFound = false;
    	
    	while(searchNode != null)
    	{
    		if (searchNode.getVal().contentEquals(item))
    		{
    			result = searchNode.getOccuranceCount();
    			elementFound = true;
    		}
    		searchNode = searchNode.getNext();
    	}
    	if (elementFound)
    		return result;
    	else
            return searchFailed;
    } // end of search()


    @Override
	public List<String> searchByInstance(int instanceCount) {

    	List<String> searchList = new ArrayList<String> ();
    	
    	ListNode searchNode = this.list2Head;
    	boolean instanceFound = false;
    	
    	while ((searchNode != null) && (searchNode.getOccuranceCount() <= instanceCount))
    	{
    		if (searchNode.getOccuranceCount() == instanceCount)
    		{
    			searchList.add(searchNode.getVal());
    			instanceFound = true;
    		}
    		searchNode = searchNode.getNext();
    	}
    	
    	if(instanceFound)
    		return searchList;
    	else
            return null;
    } // end of searchByInstance    


    @Override
	public boolean contains(String item) {
        // Implement me!
    	ListNode searchNode = this.list1Head;
    	boolean elementFound = false;
    	
    	while ((searchNode != null) && !elementFound)
    	{
    		if (searchNode.getVal().compareTo(item) == 0)
    		{
    			elementFound = true;
    		}
    		searchNode = searchNode.getNext();
    	}
    	
    	if (elementFound)
    		return true;
    	else
            return false;
    } // end of contains()


    @Override
	public void removeOne(String item) {
        // Implement me!
    	ListNode prevNode = list2Head;
    	ListNode superPrevNode = list2Head;
    	ListNode currNode = list2Head;
    	boolean elementRemoved = false;
    	
    	while(currNode != null && !elementRemoved)
    	{
    		if (currNode.getVal().compareTo(item) == 0)
    		{
    			currNode.decrementOccurenceCount();
    			if(currNode.getOccuranceCount() <= 0 )
    			{
    				if(currNode.equals(list2Head))
    				{
    					list2Head = list2Head.getNext();
    					currNode.setNext(null);
    				}
    				else
    				{
    					prevNode.setNext(currNode.getNext());
    					currNode.setNext(null);
    				}
    				this.listLength--;
    			}
    			else if(currNode.getOccuranceCount() < prevNode.getOccuranceCount())
    			{
    				prevNode.setNext(currNode.getNext());
    				superPrevNode.setNext(currNode);
    				currNode.setNext(prevNode);
    			}
    			elementRemoved = true;
       		}
    		else
    		{
    			superPrevNode = prevNode;
    			prevNode = currNode;
    			currNode = currNode.getNext();
    		}
    	}
    	
    	prevNode = list1Head;
    	currNode = list1Head;
    	elementRemoved = false;
    	
    	while(currNode != null && !elementRemoved)
    	{
    		if (currNode.getVal().compareTo(item) == 0)
    		{
    			currNode.decrementOccurenceCount();
    			if(currNode.getOccuranceCount() <= 0 )
    			{
    				if(currNode.equals(list1Head))
    				{
    					list1Head = list1Head.getNext();
    					currNode.setNext(null);
    				}
    				else
    				{
    					prevNode.setNext(currNode.getNext());
    					currNode.setNext(null);
    				}
    			}
    			elementRemoved = true;
    		}
    		else
    		{
    			prevNode = currNode;
    			currNode = currNode.getNext();
    		}
    	}
    } // end of removeOne()


    @Override
	public String print() {
    	
    	ListNode printNode = this.list2Head;
    	String printString = new String();

    	if (list2Head.getNext() != null)
    	{
    		printNode = reverseOrder();
    		while(printNode != null)
    		{
    			printString = printString + printNode.getVal() + ": " + printNode.getOccuranceCount() + "\n";
    			printNode = printNode.getNext();
    		}
    	}
    	else
    		printString = printString + printNode.getVal() + ": " + printNode.getOccuranceCount() + "\n";
    	
        return printString;
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {
    	
    	ListNode printRange = this.list1Head;
    	String printString = new String();
    	char low = lower.charAt(0);
    	char up = upper.charAt(0);
    	
    	if(printRange != null)
    	{
	    	while((printRange != null) && (printRange.getVal().charAt(0) <= up))
	    	{
	    		if((printRange.getVal().charAt(0) >= low) && (printRange.getVal().charAt(0) <= up))
				{
	    			printString = printString + printRange.getVal() + ": " + printRange.getOccuranceCount() + "\n";
				}
	    		printRange = printRange.getNext();
	    	}
    	}
        // Placeholder, please update.
        return printString;
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {
    	
    	DualLinkedListMultiset newMultiset = new DualLinkedListMultiset();
    	ListNode a1 = this.list1Head;
    	ListNode a2 = ((DualLinkedListMultiset) other).getHead1();

    	while(a1 != null && a2 != null) 
    	{

    		//If element present only in list1, adding it to the new list
    		if(a1.getVal().compareTo(a2.getVal()) < 0)
    		{

    			newMultiset.add(a1.getVal(), a1.getOccuranceCount());
    			a1 = a1.getNext();
    		}
    		//If both list same element, then summing up the instance counts and adding it to the new list
    		else if(a1.getVal().compareTo(a2.getVal()) == 0)
    		{
    			newMultiset.add(a1.getVal(), (a1.getOccuranceCount() + a2.getOccuranceCount()));

    			a1 = a1.getNext();
    			a2 = a2.getNext();
    		}
    		//If element present only in list2, adding it to the new list
    		else 
    		{
    			newMultiset.add(a2.getVal(), a2.getOccuranceCount());
    			a2 = a2.getNext();
    		}
    	}

    	// Adding the remaining elements present in the list1(if any) to the new list.
    	while(a1 != null) 
    	{
    		newMultiset.add(a1.getVal(), a1.getOccuranceCount());
    		a1 = a1.getNext();
    	}

    	// Adding the remaining elements present in the list2(if any) to the new list.
    	while(a2 != null) 
    	{
    		newMultiset.add(a2.getVal(), a2.getOccuranceCount());
    		a2 = a2.getNext();
    	}

    	return newMultiset;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {
    	
    	DualLinkedListMultiset newMultiset = new DualLinkedListMultiset();
    	ListNode a1 = this.list1Head;
    	ListNode a2 = ((DualLinkedListMultiset) other).getHead1();
    	int instanceCount = -1;
    	
    	while(a1 != null && a2 != null) {

			/* If there is a common element in both the list, then finding the minimum of instance counts from two
			 * lists.
			 * Then adding that element with minimum instance count value to the new list
			 */
			if(a1.getVal().compareTo(a2.getVal()) == 0)
			{

				if (a1.getOccuranceCount() > a2.getOccuranceCount())
					instanceCount = a2.getOccuranceCount();
				else
					instanceCount = a1.getOccuranceCount();
				
				newMultiset.add(a1.getVal(), instanceCount);
				a1 = a1.getNext();
				a2 = a2.getNext();
				
			}
			else if(a1.getVal().compareTo(a2.getVal()) < 0)
			{

				a1 = a1.getNext();
			}
			else 
			{
				a2 = a2.getNext();
			}
		}
        return newMultiset;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {

    	DualLinkedListMultiset newMultiset = new DualLinkedListMultiset();
    	ListNode a1 = this.list1Head;
    	ListNode a2 = ((DualLinkedListMultiset) other).getHead1();
    	
    	while(a1 != null && a2 != null) 
    	{

			/* If both list has same elements and instance count of list1 is greater, then subtracting the instance
			 * count of list 2 from list1.
			 * then adding the element to the list with new instanceCount value
			 */
			if(a1.getVal().compareTo(a2.getVal()) == 0)
			{
				
				if(a1.getOccuranceCount() > a2.getOccuranceCount()) 
				{
					newMultiset.add(a1.getVal(), ( a1.getOccuranceCount() - a2.getOccuranceCount() ));
				}
				
				a1 = a1.getNext();
				a2 = a2.getNext();
			}
			//If an element is present only in list1, then adding it to the new list 
			else if(a1.getVal().compareTo(a2.getVal()) < 0)
			{
				newMultiset.add(a1.getVal(), a1.getOccuranceCount());
				a1 = a1.getNext();

			}
			//If an element is present only in list2, then it is not added to the new list
			else
			{
				a2 = a2.getNext();
			}
		}
		
		
		// Adding all remaining elements from list1 to the new list
		while(a1 != null) {
			newMultiset.add(a1.getVal(), a1.getOccuranceCount());
			a1 = a1.getNext();
		}
    	
        return newMultiset;
    } // end of difference()

    
    
//    public ListNode copyList(ListNode list1){
//        if (list1 == null)
//            return null;
//
//        ListNode copyList = new ListNode(list1.getVal(),list1.getOccuranceCount());
//        ListNode tempList = copyList;
//        ListNode currNode = list1;
//
//        while (currNode.getNext() != null){
//        	currNode = currNode.getNext();
//        	tempList.setNext(new ListNode(currNode.getVal(),currNode.getOccuranceCount()));
//        	tempList = tempList.getNext();
//        }
//
//        return copyList;
//    }
    
    public ListNode getHead1()
    {
    	return this.list1Head;
    }
    
    public ListNode getHead2()
    {
    	return this.list2Head;
    }
    
    
    public void add(String item, int instanceCount) {
        
    	boolean elementFound = false;
    	boolean elementAdded = false;
    	
    	
    	if (list1Head == null && list2Head == null)
        {
        	list1Head = new ListNode(item,instanceCount);
        	list2Head = new ListNode(item,instanceCount);
        	this.listLength++;
        }
        else
        {
        	//Adding an element to the non-empty list1 and maintaining order based on element
        	{
        		ListNode nextNode = list1Head;
        		//If an element is already present in the list, then just incrementing the instance count
        		while(nextNode != null)
        		{
        			if(nextNode.getVal().contentEquals(item))
        			{
        				nextNode.incrementOccurenceCount();
        				elementFound = true;
        				break;
        			}
        			else
        				nextNode = nextNode.getNext();
        		}
        		
        		//If it is an new element, then adding it at the right place based on alphabetical order
        		if (!elementFound)
        		{
        			ListNode currentNode1 = list1Head;
        			ListNode previousNode1 = list1Head;
        			ListNode newNode1 = new ListNode(item,instanceCount);
        			
        			
        			//New element to be inserted at the head.
        			if (currentNode1.getVal().compareTo(item) > 0)
        			{
        				newNode1.setNext(currentNode1);
        				list1Head = newNode1;
        				elementAdded = true;
        	        	this.listLength++;
        			}

        			
        			currentNode1 = list1Head.getNext();
        			
        			
        			//New Element to be inserted between the elements already present in the list
        			while (currentNode1 != null && !elementAdded)
        			{
        				if(currentNode1.getVal().compareTo(item) > 0)
        				{
        					newNode1.setNext(currentNode1);
        					previousNode1.setNext(newNode1);
        					elementAdded = true;
        		        	this.listLength++;
        				}
        				else
        					previousNode1 = currentNode1;
        					currentNode1 = currentNode1.getNext();
        			}
        			//New Element to be inserted at end of the list
        			if (!elementAdded)
        			{
        				previousNode1.setNext(newNode1);
        	        	this.listLength++;
        			}
        		}
        	}

        	//Adding an element to the list2 and maintaining order based on Instance Count
        	{
        			elementAdded = false;
            		
            			ListNode newNode2 = new ListNode(item,instanceCount);
            			
            			//Inserting at head
            			if (list2Head.getOccuranceCount() > instanceCount)
            			{	
            				ListNode temp2 = list2Head;
            				newNode2.setNext(temp2);
            				list2Head = newNode2;
            			}
            			//Inserting at middle of the list based on instance count value
            			else
            			{
            				ListNode prevNode = list2Head;
            				ListNode currNode = list2Head.getNext();
            				while(currNode != null && !elementAdded)
            				{
            					if (currNode.getOccuranceCount() > instanceCount)
            					{
            						if (prevNode.equals(list2Head))
            						{
            							list2Head.setNext(newNode2);
            							newNode2.setNext(currNode);
                						
            						}
            						else
            						{
            							prevNode.setNext(newNode2);
            							newNode2.setNext(currNode);
            						}
            						elementAdded = true;
            					}
            					else
            					{
            						prevNode = currNode;
            						currNode = currNode.getNext();
            					}
            				}
            				//Inserting at the end of the list.
            				if (!elementAdded)
            				{
            					prevNode.setNext(newNode2);
            				}
            			}      		
        		}
        }	
    	
    	ListNode printNode = list1Head;
    	System.out.println("List 1 based on Element order");
    	while(printNode != null)
    	{
    		System.out.println ("Node Value: " + printNode.getVal() + "       Instance Count: " + printNode.getOccuranceCount());
    		printNode = printNode.getNext();
    	}
    	
    	ListNode printNode2 = list2Head;
    	System.out.println("List 2 based on Instance count order");
    	while(printNode2 != null)
    	{
    		System.out.println ("Node Value: " + printNode2.getVal() + "       Instance Count: " + printNode2.getOccuranceCount());
    		printNode2 = printNode2.getNext();
    	}
    	System.out.println("List length : " + this.listLength);
    	// Implement me!
    } // end of add()
    
    
    public ListNode reverseOrder ()
    {
    	boolean elementAdded = false;
    	ListNode reverseList2 = list2Head;
    	if( reverseList2 == null)
    	{
    				ListNode prevNode = reverseList2;
    				ListNode currNode = reverseList2;
    				ListNode nextNode = reverseList2.getNext();

    				while(currNode != null )
    				{
    					if (currNode.getOccuranceCount() < nextNode.getOccuranceCount())
    					{
    						if (prevNode.equals(reverseList2))
    						{
    							reverseList2.setNext(nextNode);
    							nextNode.setNext(currNode);
        						
    						}
    						else
    						{
    							prevNode.setNext(nextNode);
    							nextNode.setNext(currNode);
    						}
    						elementAdded = true;
    					}
    					else
    					{
    						prevNode = currNode;
    						currNode = currNode.getNext();
    						nextNode = nextNode.getNext();
    					}
    				}
    				//Inserting at the end of the list.
    				if (!elementAdded)
    				{
    					prevNode.setNext(nextNode);
    				}
    	}
    	return reverseList2;
	}

} // end of class DualLinkedListMultiset
