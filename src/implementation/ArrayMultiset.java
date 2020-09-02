package implementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Array implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 * @contributor Sriram Senthilnathan, RMIT University, Master of Information Technology
 */

public class ArrayMultiset extends RmitMultiset
{
	private ArrayObject[] array;
	
	
	//Default constructor creates an empty array
	public ArrayMultiset()
	{
		array = null;
	}
    
	
	@Override
	
	/* 'elem' in the function argument is the new element to be added */
	public void add(String elem) 
	{

		boolean existingElement = false;
		
		// When the array is empty, size is set to 1 and new element is added at the first position of the array
    	if (array == null)
    	{
    		array = new ArrayObject[1];
    		array[0] = new ArrayObject(elem,1);
       	}
    	else
    	{
    		{
    			/* if the new element is already present in the array, then just locating that element in the array,
    			 * using loop and just incrementing its instance count record the addition of new element
    			 */
    			
    			for (int i = 0; i < array.length; i++)
    	       	{
    	        	if (array[i].getElement().contentEquals(elem))
    	        	{
    	    			array[i].incrementInstCount();
    	    			existingElement = true;
    	    			break;
    	        	}
    	       	}
    			
    			/* if the new element is not present in array already, then array size is incremented by 1 dynamically
    			 * and then adding the new element at end after copying all the existing  elements 
    			 * to array of increased size.
    			 */
    	        
    			if (! existingElement)
    	        {
    	        	int newSize = array.length + 1;
        			ArrayObject[] newArray = new ArrayObject[newSize];
      
        			for (int i = 0; i < array.length; i++) 
        			{
        	                newArray[i] = array[i];
        	        }
    	
        			newArray[array.length] = new ArrayObject(elem,1);
    	        	    	        	
    	        	array = newArray;
    	  	    }
    	       
    		}
    		
    	}
    }// end of add()

    
	@Override
	/* 'elem' in the function argument is the element to be searched for in the array multiset */
	public int search(String elem) 
    {
    	
    	int foundNumber = -1;
    	
    	/* If array is not empty, then iterating through all the elements in the array to see if the elem is present in the 
    	 * array multiset. If present then returning the number of instances of search element in the array
    	 * otherwise returning -1 to indicate element is not found in the multiset.
    	 */
    	if(array != null)
    	{	
    		for (int i = 0; i < array.length; i++)
    		{
    			if ((array[i].getElement().compareTo(elem)) == 0)
    			{
    				foundNumber = array[i].getInstCount();
    				break;
    			}
    		}
    	}
    
    	return foundNumber;
    	
    } // end of search()


    @Override
	/* List of Elements in the array having instance value equal to 'instanceCount' in the function argument needs to be returned */
    public List<String> searchByInstance(int instanceCount) {

    	List<String> searchList = new ArrayList<String> ();

    	/* If array is not empty, then iterating through all the elements in the array to see if their total number of 
    	 * instances value matches with the 'instanceCount'.If they do match then they are added to a list and list is returned. 
    	 * If there is no single match, then returning the empty list.
    	 */
    	
    	if (array != null)
    	{
    		for (int i = 0; i < array.length; i++)
    		{
    			if (array[i].getInstCount() == instanceCount)
    			{
    				searchList.add(array[i].getElement());
    			}
    		}
    	}
    	
    	return searchList;
    } // end of searchByInstance


    @Override
	/* 'elem' in the function argument is the element to be searched for in the array multiset */
	public boolean contains(String elem) {

    	boolean elementFound = false;
    	
    	/* If array is not empty, then iterating through all the elements in the array to see if the elem is present in the 
    	 * array multiset. If present then returning returning true, else false.
    	 */
    	
    	if (array != null)
    	{
	    	for (int i = 0; i < array.length; i++)
	    	{
	    		if ((array[i].getElement().compareTo(elem)) == 0)
	    		{
	    			elementFound = true;
	    			break;
	    		}
	    	}
    	}
    	
    	return elementFound;
    	
    } // end of contains()


    @Override
	/* 'elem' in the function argument is the element to be removed(only one instance) from array multiset */

	public void removeOne(String elem) 
    {
        
    	
    	/* If array is not empty then Iterating through the array to check if the element to be removed is present in the array, 
    	 * If found, then just decrementing  instance count of 'elem'.
    	 */
    	
    	if (array != null)
    	{
	    	for (int i = 0; i < array.length ; i++)
	    	{
	    		if ((array[i].getElement().compareTo(elem)) == 0)
	    		{
	    			array[i].decrementInstCount();
	    			
	    			/* 				Case : No more instances of this 'elem' in the array after decrement operation
	    			 * 
	    			 * 1.If there are one or more elements after 'elem' been removed, then swapping the position of last element of the 
	    			 * array with the 'elem', and then setting the last position of array to null.
	    			 * Or if the 'elem' is in the last position of the array already, then just setting it to null
	    			 * 
	    			 * 2. Finally shrinking the array dynamically by reducing its size by 1.
	    			 */
	    			
	    			if (array[i].getInstCount() <= 0)
	    			{
	    				if ( i != array.length - 1)
	    				{
	    					array[i].setObject(array[array.length-1].getElement(), array[array.length-1].getInstCount());
	    					array[array.length-1] = null;					 					
	    				}
	    				else
	    					array[i] = null;
	    				
	    				
	    				int newSize = array.length - 1;
						ArrayObject[] newArray = new ArrayObject[newSize];
	    			
						for (int j = 0; j < newSize; j++) 
						{
							newArray[j] = array[j];
						}
						array = newArray;
	    					
	    			}
	    			break;
	    		}
	    	}
    	}
    } // end of removeOne()


    @Override
	public String print() {

        /* 1. Sort the array to be in descending order based on number of instances 
         * 2. Print the elements of the sorted array
         */
    	
    	//Array sorting using bubble sort
    	sortArray();
    	
    	
    	//Printing the sorted array
    	String printString = new String();
    	if (array != null)
		{
			for (int i = 0; i < array.length; i++)
			{
				if (array[i].getInstCount() > 0)
				{
					printString = printString + array[i].getElement() + ":" + array[i].getInstCount() + "\n";
				}
			}
		}
    	
    	return printString;
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {

    	
    	String printRangeString = new String();
    	
    	/* If array is not empty, then iterating through the multiset to find the elements that comes 
    	 * between the given range(inclusive), if found adding it to a string (for each element) and returning a single string
    	 */
    	    	
    	if (array != null)
        {
        	for (int i = 0; i < array.length; i++)
        	{
        		if ((array[i].getElement().compareTo(lower) >= 0) && (array[i].getElement().compareTo(upper) <= 0))
				{
    				printRangeString = printRangeString + array[i].getElement() + ":" + array[i].getInstCount() + "\n";	
				}
        	}
        }
    	
        return printRangeString;
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {
    	
    	
    	// 'arrayUnion' is the new multiset created out of union operation
    	
    	ArrayMultiset arrayUnion = new ArrayMultiset();
    	ArrayMultiset multiset2 = (ArrayMultiset) other;
    	
    	/* Copying all the elements from set1 to array of new multiset*/
    	
    	for (int i = 0; i < this.array.length ; i++)
    	{
    		arrayUnion.add(array[i].getElement(),array[i].getInstCount());
    	}
    	
    	    	
    	/* Now traversing through all the elements in the multiset2 and comparing it with
    	 * the elements in the array of arrayUnion.
    	 * 
    	 * - If it is an common element between both arrayUnion and multiset2, then summing up their instance count 
    	 *   and updating that value in the array of arrayUnion.
    	 *   
    	 * - If it is not an common element then just adding that element in the end of the array,
    	 * 	 so that all the elements from multiset2 will be added to arrayUnion.	
    	 */
    	
    	for (int i = 0; i < multiset2.array.length; i++)
    	{
    		boolean existingElement = false;
    		
    		for (int j = 0; j < arrayUnion.array.length; j++)
    		{
    			// If an element is common between two sets, then just summing up the instance values and updating in arrayUnion
    			
    			if (arrayUnion.array[j].getElement().contentEquals(multiset2.array[i].getElement()))
    			{
    				arrayUnion.array[j].setInstanceCount(arrayUnion.array[j].getInstCount() + multiset2.array[i].getInstCount());
    				existingElement = true;
    				break;
    			}
    		}
    		
    		/* If an element is not common between two sets, then adding it at the end of the arrayUnion using the same
    		 * dynamic allocation method used in the 'add()' function 
    		 */ 
    		
    		if (! existingElement)
    		{
    			int newSize = arrayUnion.array.length + 1;
    			ArrayObject[] newArray = new ArrayObject[newSize];

    			for (int k = 0; k < arrayUnion.array.length; k++) 
    			{
    				newArray[k] = arrayUnion.array[k];
    			}

    			newArray[arrayUnion.array.length] = multiset2.array[i];
    			
    			arrayUnion.array = newArray;
    		}
    	}

       
        return arrayUnion;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {

    	//arrayIntersect is the new multiset created out intersection of two multisets in input
    	
    	ArrayMultiset arrayIntersect = new ArrayMultiset();
    	ArrayMultiset multiset2 = (ArrayMultiset) other;
    	
        
    	// Iteration through the elements in multiset 1 one by one
    	
    	for (int i = 0; i < this.array.length; i++)
    	{
        	// Iteration through the elements in multiset 2 one by one and then comparing it with element from multiset1

    		for (int j = 0; j < multiset2.array.length; j++)
    		{
    			if (this.array[i].getElement().compareTo(multiset2.array[j].getElement()) == 0)
    			{
    				
    				/* If the there is a common element between both multisets, then adding it to the array of arrayIntersect
    				 * multiset by using the same dynamic allocation of array method from add() operation
    				 */
    				
    				
    				// If new array is not empty, then just increasing its size by 1 to add the new common element
    				if(arrayIntersect.array != null)
    				{
	    				int newSize = arrayIntersect.array.length + 1;
	        			ArrayObject[] newArray = new ArrayObject[newSize];
	      
	        			for (int k = 0; k < arrayIntersect.array.length; k++) 
	        			{
	        	                newArray[k] = arrayIntersect.array[k];
	        	        }
	        			
	    				//Checking the least instance count value for the common elements from both multisets and adding it to new array
	    				if (this.array[i].getInstCount() <= multiset2.array[j].getInstCount())
	    				{
	    					newArray[arrayIntersect.array.length] = new ArrayObject(this.array[i].getElement(),this.array[i].getInstCount());
	       				}
	    				else 
	    				{
	    					newArray[arrayIntersect.array.length] = new ArrayObject(multiset2.array[j].getElement(),multiset2.array[j].getInstCount());
	     				}
	    				arrayIntersect.array = newArray;
    				}
    				
    				// If new array is empty, then initializing the size of new array to 1 and adding the new common element
    				else
    				{
    					arrayIntersect.array = new ArrayObject[1];
    					if (this.array[i].getInstCount() <= multiset2.array[j].getInstCount())
	    				{
    						arrayIntersect.array[0] = new ArrayObject(this.array[i].getElement(),this.array[i].getInstCount());
	       				}
	    				else 
	    				{
	    					arrayIntersect.array[0] = new ArrayObject(multiset2.array[j].getElement(),multiset2.array[j].getInstCount());
	     				}    				
    				}
    				break;
       			}
    		}
    	}
    	    	
        return arrayIntersect;

    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {

    	// 'arrayDifference' is the new multiset created out of difference operation of two input multisets (multiset1 - multiset2)
    	
    	ArrayMultiset arrayDifference = new ArrayMultiset();
    	ArrayMultiset multiset2 = (ArrayMultiset) other;
    	
    	// Adding all the elements from multiset1 to arrayDifference multiset
    	
    	for (int i = 0; i < this.array.length ; i++)
    	{
    		arrayDifference.add(array[i].getElement(),array[i].getInstCount());
    	}
    	
    	/* Iterating through all the elements from multiset2 and comparing it with each element already present
    	 * in the new arrayDifference multiset.
    	 * If there is a common element between those two sets, then difference of their instance count values is calculated
    	 *  and that value is finally updated in the arrayDifference multiset.
    	 */
    	for (int i = 0; i < multiset2.array.length ; i++)
    	{
    		for (int j = 0; j < arrayDifference.array.length ; j++)
    		{
    			// If an element is common between two sets, then finding the difference of the instance values and updating in new array
    			if (arrayDifference.array[j].getElement().contentEquals(multiset2.array[i].getElement()))
    			{
    				arrayDifference.array[j].setInstanceCount((arrayDifference.array[j].getInstCount() - multiset2.array[i].getInstCount()));
    				break;
    			}
    		}
       	}
     
        return arrayDifference;
    } // end of difference()
    
    
    // Array sort function (bubble sort) - Sorting in descending order based on number of instances of each element
    public void sortArray() {
    	
    	if (array != null)
    	{
	    	for (int i = 0; i < array.length; i++)
	    	{
	    		for (int j = i+1; j < array.length ; j++)
	    		{
	    			if (array[i].getInstCount() < array[j].getInstCount())
	    			{
	    				ArrayObject temp;
	    				temp = array[i];
	    				array[i] = array[j];
	    				array[j] = temp;
	    				
	    			}
	    		}
	    	}
    	}
      }
    

    //Array copy function
    public ArrayObject[] arrayCopy(ArrayObject[] arrayToCopy)
    {
    	ArrayObject[] copiedArray = new ArrayObject[arrayToCopy.length];
    	for (int a = 0; a < arrayToCopy.length ; a++)
    	{
    		copiedArray[a] = arrayToCopy[a];
    	}
    	
    	return copiedArray;
    }

    
    /*Custom add operation - Used in Union, intersect and difference operations when adding an element to the new multisets with 
     * instanceCount values
     */
    public void add(String elem, int instanceCount) {
    	
    	// if the array is empty, add the element at the first place
    	if (array == null)
    	{
    		array = new ArrayObject[1];
    		array[0] = new ArrayObject(elem, instanceCount);
       	}
    	
    	//Otherwise using the dynamic allocation method to increase array size and add the new element to it.
    	else
    	{
    		int newSize = array.length + 1;
    		ArrayObject[] newArray = new ArrayObject[newSize];

    		for (int i = 0; i < array.length; i++) 
    		{
    			newArray[i] = array[i];
    		}

    		newArray[array.length] = new ArrayObject(elem,instanceCount);

    		array = newArray;    	       
		}
    		
    }// end of add()
    
    
    //Data generation setup code
    public ArrayObject[] getArray() {
		return array;
	}


	public void setArray(ArrayObject[] array) {
		this.array = array;
	}

    
} // end of class ArrayMultiset


