package implementation;

import java.util.ArrayList;
import java.util.List;


/**
 * Dual linked list implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 * @Contributors Sriram Senthilnathan, RMIT University, Master of Information Technology
 */


public class DualLinkedListMultiset extends RmitMultiset
{
	ListNode list1Head,list2Head;
    private int listLength = 0;
	
    
    /* Default constructor creates the lists head for both lists */
    public DualLinkedListMultiset() {
    	list1Head = null;
    	list2Head = null;
    }
    
    
    @Override
	public void add(String item) {
        
    	boolean elementFound = false;
    	boolean elementAdded = false;
    	
    						/* Add operation inserts new item to both List 1 and List 2 */
    	
    	/*
    	 * If both lists head are null (no elements in lists) then the new item will be inserted as new heads.
    	 */
    	
    	if (list1Head == null)
        {
        	list1Head = new ListNode(item);
        	list2Head = new ListNode(item);
        	this.listLength++;
        }
    	
        else
        {
        	//Adding an element to the non-empty list1 and maintaining the list based on ascending order of element
        	{
        		ListNode nextNode = list1Head;
        		
        		/*
        		 * Iterating through the list to check if the item is already present in the list,
        		 * if present then just incrementing its instance count
        		 */
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
        		
        		/* If it is an new element, then adding it at the right place in the list in such a way that 
        		 *  the list1 is sill maintained based on the ascending order of element.
        		 */
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
        	}

        	//Adding an element to the list2 and maintaining the list based on Descending order of Instance Count values
        	{
    			ListNode prevNode2 = list2Head;
    			ListNode currNode2 = list2Head;
    			boolean elementFound2 = false;
    			
        		/* If an element is already present in the list, then just incrementing the instance count
        		 * and rearranging the list elements (if needed), so that even after the increment operation the list
        		 * is still maintained in descending order.
        		 */
        		while(currNode2 != null && !elementFound2)
        		{
        			if(currNode2.getVal().contentEquals(item))
        			{
        				//If the element is already present then incrementing the instance count.
        				currNode2.incrementOccurenceCount();
        			
        				/* Post incrementing, if the updated instance count is greater than the instance count value of 
        				 * previous element in the list, then rearranging happens.
        				 */
        				if (currNode2.getOccuranceCount() > prevNode2.getOccuranceCount())
        				{
        					
        					/* Case : Moving the element of updated instance value to Head.       					 */
        					if(prevNode2.equals(list2Head))
        					{
        						ListNode temp = list2Head;
        						list2Head.setNext(currNode2.getNext());
        						currNode2.setNext(temp);
        						list2Head = currNode2;
        					}

        					/* Case : Moving the element to the correct position in the list based on new instance value	 */

        					else
        					{
        						prevNode2.setNext(currNode2.getNext());
        					
        						ListNode pNode = list2Head;
        				    	ListNode cNode = list2Head;
        				    	boolean elementInserted = false;
        				    	
        				    	while(cNode != null && !elementInserted)
        				    	{
        				    		if(cNode.getOccuranceCount() < currNode2.getOccuranceCount())
        				    		{
        				    			if (pNode.equals(list2Head))
        				    			{
        				    				currNode2.setNext(list2Head.getNext());
        				    				list2Head.setNext(currNode2);
        				    			}
        				    			else if(cNode.equals(list2Head))
        				    			{
        				    				ListNode temp = list2Head;
        				    				currNode2.setNext(temp);
        				    				list2Head = currNode2;
        				    			}
        				    			else
        				    			{
        				    				currNode2.setNext(cNode);
        				    				pNode.setNext(currNode2);
        				    			}
        				    			elementInserted = true;
        				    		}
        				    		else
        				    		{
        				    			pNode = cNode;
        				    			cNode = cNode.getNext();
        				    		}   				  
        				    	}
        				    }
        				}
        			
        				elementFound2 = true;
        			}
        			
        			else
            		{
            			prevNode2 = currNode2;
            			currNode2 = currNode2.getNext();
           			}
        		}        				
        		
        		/* If the element is is not present in the list, then adding it at the end of the list2, as it will have
        		 * instance value of '1'.
        		 */
        		
        		if (!elementFound2)
        		{
        			
        			ListNode newNode2 = new ListNode(item);
        			prevNode2.setNext(newNode2);
        			
        		}      		
        	}
        }
    } // end of add()


    @Override
	public int search(String item) {

    	ListNode searchNode = this.list1Head;
    	int result = -1; 
    	boolean elementFound = false;
    	
    	/* Iterating through the list1 sequentially until the specified item is found in the list.
    	 * If item is present, then its instance count is returned otherwise it returns -1 to indicate search failed.
    	 */
    	
    	while(searchNode != null && !elementFound)
    	{
    		if (searchNode.getVal().contentEquals(item))
    		{
    			result = searchNode.getOccuranceCount();
    			elementFound = true;
    		}
    		searchNode = searchNode.getNext();
    	}
    	
    	return result;
    } // end of search()


    @Override
	public List<String> searchByInstance(int instanceCount) {

    	List<String> searchList = new ArrayList<String> ();
    	ListNode searchNode = this.list2Head;
    	boolean instanceFound = false;

    	/* For searching by the instance, list2 is being used because it is maintained based on the order of instance count
    	 * so it will be more efficient than using list1.
    	 * 
    	 * Iterating through the list2 until the specified instance count value is found in the list.
    	 * If found , then returning the list of elements having the instance count specified. 
    	 */
    	
    	while (searchNode != null)
    	{
    		if (searchNode.getOccuranceCount() == instanceCount)
    		{
    			searchList.add(searchNode.getVal());
        	}
    		searchNode = searchNode.getNext();
    	}
    	
    	return searchList;
    	
    } // end of searchByInstance    


    @Override
	public boolean contains(String item) {

    	ListNode searchNode = this.list1Head;
    	boolean elementFound = false;
    	
    	/* Iterating through the list1 sequentially until the specified item is found in the list.
    	 * If item is present, then returning 'true' to indicate that multiset contains this element.
    	 */
    	
    	while ((searchNode != null) && !elementFound)
    	{
    		if (searchNode.getVal().compareTo(item) == 0)
    		{
    			elementFound = true;
    		}
    		searchNode = searchNode.getNext();
    	}
    	
    	return elementFound;
    } // end of contains()


    @Override
	public void removeOne(String item) 
    {

    						/* Removing the specified element from Linked List 1	*/
    	
    	ListNode prevNode = list1Head;
    	ListNode currNode = list1Head;
    	boolean elementRemoved = false;
    	
    	while(currNode != null && !elementRemoved)
    	{
    		if (currNode.getVal().compareTo(item) == 0)
    		{
    			//Decreasing the instance count of the element by '1' being removed to indicate the delete operation
    			currNode.decrementOccurenceCount();
    			
    			
    			/* After decrement, if the instance count is '0' then removing that element completely from the list
    			 */
    			if(currNode.getOccuranceCount() <= 0 )
    			{
    				/* If the element to be deleted is the head , then second element in the list is made as new head */
    				if(currNode.equals(list1Head))
    				{
    					list1Head = list1Head.getNext();
    					currNode.setNext(null);
    				}
    				/* If the element to be removed is anywhere other than head in the list,
    				 * then making it previous element to point to next element(to element being deleted) in the list
    				 * and the element is removed by setting its next value to null.
    				 */
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
    		
    						/* Removing the specified element from Linked List 2	*/

    	
    	prevNode = list2Head;
    	currNode = list2Head;
    	elementRemoved = false;
    	
    	while(currNode != null && !elementRemoved)
    	{
    		if (currNode.getVal().compareTo(item) == 0)
    		{
    			//Decreasing the instance count of the element by '1' being removed to indicate the delete operation
    			currNode.decrementOccurenceCount();
    			
    			/* After decrement, the list2 might needs to be rearranged to still maintain the descending
    			 * order of instance count.    			 
    			 */
    			
    			/* Case: The instance count becomes zero after decrement, so the element needs to be removed and list2
    			 * 		 is reordered 
    			 */
    			if(currNode.getOccuranceCount() <= 0 )
    			{
    				if(prevNode.equals(list2Head))
    				{
    					list2Head.setNext(currNode.getNext());
    				}
    				else
    					prevNode.setNext(currNode.getNext());
    				
    				currNode.setNext(null);
    				elementRemoved = true;
    			}
    			
    			/* Case: the instance count is not zero after decrement, so the list2 needs is reordered */
    			if(!elementRemoved)
    			{
    				
    				/* If the instance count of element removed becomes lesser then its next 's count
    				 * then the list2 is reordered.
    				 */
    				if (currNode.getOccuranceCount() < currNode.getNext().getOccuranceCount())
    				{
    					//If element is list2head, then its next element is swapped to become new list head. 
    					if(currNode.equals(list2Head))
    					{
    						ListNode temp = list2Head;
    						list2Head = currNode.getNext();
    						temp.setNext(list2Head.getNext());
    						list2Head.setNext(temp);
    					}
    					// otherwise the reordering happens at the right place in the list.
    					else
    					{
    						if (prevNode.equals(list2Head))
    						{
    							currNode.getNext().setNext(currNode);
    							list2Head.setNext(currNode.getNext());
    						}
    						else
    						{
    							currNode.getNext().setNext(currNode);
    							prevNode.setNext(currNode.getNext());
    						}
    					}
    				}
					elementRemoved = true;
    			}
       		}		
    		else
    		{
    			prevNode = currNode;
    			currNode = currNode.getNext();
    		}
    	}
    } // end of removeOne()


    @Override
	public String print() 
    {
    	String printString = new String();
    	
    	ListNode printNode = list2Head;

    	/* Iterating through the list2 which already stores the element in the decreasing order of instance count values
    	 * and printing it one by one
    	 */
    	while(printNode != null)
    	{
    		printString = printString + printNode.getVal() + ":" + printNode.getOccuranceCount() + "\n";
	    	printNode = printNode.getNext();
    	}
    	 	 
        return printString;
    }
    
    @Override
	public String printRange(String lower, String upper) {
    	
    	ListNode printRange = this.list1Head;
    	String printString = new String();
    	    	
    	if(printRange != null)
    	{
    		/*Iterating through the list1 to find the elements that comes 
        	 * between the given range(inclusive), if found adding it to a string (for each element) 
        	 * and returning a single string.
        	 */
    		while((printRange != null) && (printRange.getVal().compareTo(upper) <= 0))
	    	{
	    		if((printRange.getVal().compareTo(lower) >= 0) && (printRange.getVal().compareTo(upper) <= 0))
				{
	    			printString = printString + printRange.getVal() + ":" + printRange.getOccuranceCount() + "\n";
				}
	    		printRange = printRange.getNext();
	    	}
    	}

    	return printString;
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {
    	
    	DualLinkedListMultiset newMultiset = new DualLinkedListMultiset();
    	ListNode a1 = this.list1Head;
    	ListNode a2 = ((DualLinkedListMultiset) other).getHead1();

    	/*Iterating through both the multisets at the same time*/
    	
    	while(a1 != null && a2 != null) 
    	{

    		//If element present only in multiset1, adding it to the new multiset
    		if(a1.getVal().compareTo(a2.getVal()) < 0)
    		{

    			newMultiset.add(a1.getVal(), a1.getOccuranceCount());
    			a1 = a1.getNext();
    		}
    		//If both multisets have same element, then summing up the instance counts and adding it to the new multiset
    		else if(a1.getVal().compareTo(a2.getVal()) == 0)
    		{
    			newMultiset.add(a1.getVal(), (a1.getOccuranceCount() + a2.getOccuranceCount()));

    			a1 = a1.getNext();
    			a2 = a2.getNext();
    		}
    		//If element present only in multiset2, adding it to the new multiset
    		else 
    		{
    			newMultiset.add(a2.getVal(), a2.getOccuranceCount());
    			a2 = a2.getNext();
    		}
    	}

    	/* Adding the remaining elements present in the multiset1(if any) to the new multiset. This happens when one multiset1 has more
    	 * elements than multiset2
    	 */
    	while(a1 != null) 
    	{
    		newMultiset.add(a1.getVal(), a1.getOccuranceCount());
    		a1 = a1.getNext();
    	}

    	/* Adding the remaining elements present in the multiset2(if any) to the new multiset. This happens when one multiset2 has more
    	 * elements than multiset2
    	 */    	
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
    	
    	/*Iterating through both the multisets at the same time*/

    	while(a1 != null && a2 != null) 
    	{

			/* If there is a common element in both the multisets, then finding the minimum of instance counts from two
			 * multisets.
			 * Then adding that element with minimum instance count value to the new multiset
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
    	
    	/*Iterating through both the multisets at the same time*/

    	while(a1 != null && a2 != null) 
    	{

			/* If both multisets have same elements and instance count of multiset1 is greater, then subtracting the instance
			 * count of multiset2 from multiset1.
			 * then adding the element to the new multiset with new instanceCount value
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
			//If an element is present only in multiset1, then adding it to the new multiset 
			else if(a1.getVal().compareTo(a2.getVal()) < 0)
			{
				newMultiset.add(a1.getVal(), a1.getOccuranceCount());
				a1 = a1.getNext();

			}
			//If an element is present only in multiset2, then it is not added to the new multiset
			else
			{
				a2 = a2.getNext();
			}
		}
		
		
		// Adding all remaining elements from multiset1 to the new list.This happens when multiset1 has more number of elements than multiset2
		
    	while(a1 != null) 
    	{
			newMultiset.add(a1.getVal(), a1.getOccuranceCount());
			a1 = a1.getNext();
		}
    	
        return newMultiset;
    } // end of difference()

    
    //Returns the head node of list1
    public ListNode getHead1()
    {
    	return this.list1Head;
    }
    
    //Returns the head node of list2
    public ListNode getHead2()
    {
    	return this.list2Head;
    }
    
    
    /* Custom add function to be used for creating new multiset out of the Union, Intersection and
     * Difference operations. 
     */
    public void add(String item, int instanceCount) 
    {

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
    			if (list2Head.getOccuranceCount() < instanceCount)
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
    					if (currNode.getOccuranceCount() < instanceCount)
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
    } // end of add()

} // end of class DualLinkedListMultiset

