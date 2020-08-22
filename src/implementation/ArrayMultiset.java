package implementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Array implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */

public class ArrayMultiset extends RmitMultiset
{
	private ArrayObject[] array;
	
	
	//Constructor creates an empty array
	public ArrayMultiset()
	{
		array = null;
	}
    
	
	@Override
	public void add(String elem) {
        // Implement me!
		boolean existingElement = false;
		
		// if the array is empty, add the element at the first place
    	if (array == null)
    	{
    		array = new ArrayObject[1];
    		array[0] = new ArrayObject(elem,1);
       	}
    	else
    	{
    		{
    			// if the new element is already present in the array, then just incrementing the instance count
    			for (int i = 0; i < array.length; i++)
    	       	{
    	        	if (array[i].getElement().contentEquals(elem))
    	        	{
    	    			array[i].incrementInstCount();
    	    			existingElement = true;
    	    			break;
    	        	}
    	       	}
    			// If the new element is not present already, then adding it at the end of the array
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
    		
    	}// end of add()
    	
}

    @Override
	public int search(String elem) {
        // Implement me!
    	int foundNumber = -1;
    	for (int i = 0; i < array.length; i++)
    	{
    		if ((array[i].getElement().compareTo(elem)) == 0)
    		{
    			foundNumber = array[i].getInstCount();
    			break;
    		}
    	}
    		return foundNumber;
    	
    } // end of search()


    @Override
    public List<String> searchByInstance(int instanceCount) {

    	List<String> searchList = new ArrayList<String> ();

    	for (int i = 0; i < array.length; i++)
    	{
    		if (array[i].getInstCount() == instanceCount)
    		{
    			searchList.add(array[i].getElement());
    		}
    	}
    		return searchList;
    } // end of searchByInstance


    @Override
	public boolean contains(String elem) {
        // Implement me!
    	boolean elementFound = false;
    	
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
	public void removeOne(String elem) {
        
    	boolean elementFound = false;
    	
    	//If the element to be removed is found in the array, then just decrementing its instance count
    	for (int i = 0; i < array.length ; i++)
    	{
    		if ((array[i].getElement().compareTo(elem)) == 0)
    		{
    			array[i].decrementInstCount();
    			
    			/* Case : No more instances of this element in the array after decrement operation
    			 * 1.If there are one or more elements after the element been removed, then swapping the last element of the 
    			 * array with the element being removed, and then setting the last position of array to null.
    			 * Else if the element been removed is in the last position of the array already, then just setting it to null
    			 * 
    			 * 2. Finally shrinking the array by reducing its size by 1.
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
    			elementFound = true;
    			break;
    		}
    	}
    	if (!elementFound)
    		System.out.println("No such element exist in the multiset");
    } // end of removeOne()


    @Override
	public String print() {

        /* 1. Sort the array to be in descending order based on number of instances
         * 2. Print the elements of the sorted array
         */
    	sortArray();
    	
    	String printString = new String();
    	try 
    	{
    		if (array != null)
    		{
    			for (int i = 0; i < array.length; i++)
    			{
    				if (array[i].getInstCount() > 0)
    				{
    					printString = printString + array[i].getElement() + ": " + array[i].getInstCount() + "\n";
    				}
    			}
    		}
    	}
    	
    	catch (NullPointerException e)
    	{
    		System.out.println("No Elements in the Multiset");
    	}	
    	
    	return printString;
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {

    	char low = lower.charAt(0);
    	char up = upper.charAt(0);
    	String printRangeString = new String();
    	
    	/* Sorting the array to maintain descending order of instance count and then printing the elements
    	 * between the given range(inclusive) one by one*/
    	
    	sortArray();
    	
    	if (array != null)
        {
        	for (int i = 0; i < array.length; i++)
        	{
        		if ((array[i].getElement().charAt(0) >= low) && (array[i].getElement().charAt(0) <= up))
					{
        				printRangeString = printRangeString + array[i].getElement() + ": " + array[i].getInstCount() + "\n";	
					}
        	}
        }
    	
        return printRangeString;
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {
    	
    	ArrayMultiset arrayUnion = new ArrayMultiset();
    	ArrayMultiset multiset2 = (ArrayMultiset) other;
    	
    	/* Copying all the elements from set1 to array of new set*/
    	for (int i = 0; i < this.array.length ; i++)
    	{
    		arrayUnion.add(array[i].getElement(),array[i].getInstCount());
    	}
    	
    	    	
    	/* Now traversing through all the elements in the set2 and comparing it with
    	 * the elements in the array of new set.
    	 * - If it is an common element between both set1 and set2, then summing up their instance count 
    	 *   and updating that value in the array of new set.
    	 * - If it is not an common element then just adding that element in the end of the array,
    	 * 	 so that all the elements from both set1 and set2 will be added to new array	
    	 */
    	for (int i = 0; i < multiset2.array.length; i++)
    	{
    		boolean existingElement = false;
    		
    		for (int j = 0; j < arrayUnion.array.length; j++)
    		{
    			// If an element is common between two sets, then just summing up the instance values
    			if (arrayUnion.array[j].getElement().contentEquals(multiset2.array[i].getElement()))
    			{
    				//System.out.println(" Common Element : " + array2[i]);
    				arrayUnion.array[j].setInstanceCount(arrayUnion.array[j].getInstCount() + multiset2.array[i].getInstCount());
    				existingElement = true;
    				break;
    			}
    		}
    		// If an element is not common between two sets, then adding it at the end of the array
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

    	ArrayMultiset arrayIntersect = new ArrayMultiset();
    	ArrayMultiset multiset2 = (ArrayMultiset) other;
    	
          	
    	for (int i = 0; i < this.array.length; i++)
    	{
    		for (int j = 0; j < multiset2.array.length; j++)
    		{
    			if (this.array[i].getElement().compareTo(multiset2.array[j].getElement()) == 0)
    			{
    				if(arrayIntersect.array != null)
    				{
	    				int newSize = arrayIntersect.array.length + 1;
	        			ArrayObject[] newArray = new ArrayObject[newSize];
	      
	        			for (int k = 0; k < arrayIntersect.array.length; k++) 
	        			{
	        	                newArray[k] = arrayIntersect.array[k];
	        	        }
	        			
	    				//System.out.println("Common Element is: " + this.array[i]);
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

    	ArrayMultiset arrayDifference = new ArrayMultiset();
    	ArrayMultiset multiset2 = (ArrayMultiset) other;
    	
    	    	
    	for (int i = 0; i < this.array.length ; i++)
    	{
    		arrayDifference.add(array[i].getElement(),array[i].getInstCount());
    	}
    	
    	for (int i = 0; i < multiset2.array.length ; i++)
    	{
    		for (int j = 0; j < arrayDifference.array.length ; j++)
    		{
    			// If an element is common between two sets, then just summing up the instance values
    			if (arrayDifference.array[j].getElement().contentEquals(multiset2.array[i].getElement()))
    			{
    				//System.out.println(" Common Element : " + array2[i]);
    				arrayDifference.array[j].setInstanceCount((arrayDifference.array[j].getInstCount() - multiset2.array[i].getInstCount()));
    				break;
    			}
    		}
       	}
    	//*
       
        return arrayDifference;
    } // end of difference()
    
    
    // Array sort function - Sorting in descending order based on number of instances of each element
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

    
    public void add(String elem, int instanceCount) {
	// if the array is empty, add the element at the first place
    	if (array == null)
    	{
    		array = new ArrayObject[1];
    		array[0] = new ArrayObject(elem, instanceCount);
       	}
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
} // end of class ArrayMultiset


