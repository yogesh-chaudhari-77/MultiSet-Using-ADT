//package implementation;

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
	private String[] array;
	
	
	//Constructor creates an empty array
	public ArrayMultiset()
	{
		array = null;
	}
    
	
	//Accessor method to return the array elements 
	public String[] getArray()
	{
		return this.array;
	}
	
	
	//Setter method to assign values to the array
	public void setArray(String[] newArray)
	{
		this.array = arrayCopy(newArray);
	}
	
	
	@Override
	public void add(String elem) {
        // Implement me!
		boolean existingElement = false;
		
		// if the array is empty, add the element at the first place
    	if (array == null)
    	{
    		array = new String[2];
    		array[0] = elem;
    		array[1] = "1";
    	}
    	else
    	{
    		{
    			// if the new element is already present in the array, then just incrementing the instance count
    			for (int i = 0; i < array.length - 1; i = i+2)
    	       	{
    	        	if (array[i].contentEquals(elem))
    	        	{
    	    			array[i+1] = String.valueOf(Integer.parseInt(array[i+1]) + 1);
    	    			existingElement = true;
    	    			break;
    	        	}
    	       	}
    			// If the new element is not present already, then adding it at the end of the array
    	        if (! existingElement)
    	        {
    	        	int newSize = array.length + 2;
        			String[] newArray = new String[newSize];
      
        			for (int i = 0; i < array.length; i++) 
        			{
        	                newArray[i] = array[i];
        	        }
    	
        			newArray[array.length] = elem;
    	        	newArray[array.length + 1] = "1";
    	        	
    	        	array = newArray;
    	  	    }
    	       
    		}
    		
    	}// end of add()
    	
//    	printing for testing purpose
//    	for (int i = 0; i < array.length; i = i+2)
//		{
//			System.out.println("Array Element: " + array[i] + "      Instance Count: " + array[i+1]);
//		}
}

    @Override
	public int search(String elem) {
        // Implement me!
    	int foundNumber = -1;
    	for (int i = 0; i < array.length-1; i = i+2)
    	{
    		if ((array[i].compareTo(elem)) == 0)
    		{
    			foundNumber = Integer.parseInt(array[i+1]);
    			break;
    		}
    	}
    	if (foundNumber != -1)
    		return foundNumber;
    	else
            return searchFailed;
    	
    } // end of search()


    @Override
    public List<String> searchByInstance(int instanceCount) {

    	List<String> searchList = new ArrayList<String> ();

    	for (int i = 1; i < array.length; i = i+2)
    	{
    		if ((Integer.parseInt(array[i]) == instanceCount))
    		{
    			searchList.add(array[i-1]);
    		}
    	}
    	if (searchList.isEmpty())
    		return null;
    	else 
    		return searchList;
    } // end of searchByInstance


    @Override
	public boolean contains(String elem) {
        // Implement me!
    	boolean elementFound = false;
    	
    	for (int i = 0; i < array.length - 1; i = i+2)
    	{
    		if ((array[i].compareTo(elem)) == 0)
    		{
    			elementFound = true;
    			break;
    		}
    	}
    	
    	if (elementFound)
    		return true;
    	else 
    		return false;
    } // end of contains()


    @Override
	public void removeOne(String elem) {
        // Implement me!
    	boolean elementFound = false;
    	for (int i = 0; i < array.length - 1; i = i+2)
    	{
    		if ((array[i].compareTo(elem)) == 0)
    		{
    			array[i+1] = String.valueOf(Integer.parseInt(array[i+1]) - 1);
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
    			for (int i = 0; i < array.length; i = i+2)
    			{
    				if (Integer.parseInt(array[i+1]) > 0)
    				{
    					printString = printString + array[i] + ": " + array[i+1] + "\n";
    					//System.out.println(array[i] + ": " + array[i+1]);
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
    	
    	sortArray();
    	
    	if (array != null)
        {
        	for (int i = 0; i < array.length; i = i+2)
        	{
        		if ((array[i].charAt(0) >= low) && (array[i].charAt(0) <= up))
					{
        			printRangeString = printRangeString + array[i] + ": " + array[i+1] + "\n";	
//        			System.out.println(array[i] + ": " + array[i+1]);
					}
        	}
        }
    	
        return printRangeString;
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {
    	
    	ArrayMultiset arrayUnion = new ArrayMultiset();
    	ArrayMultiset multiset2 = (ArrayMultiset) other;
    	String array1[] = arrayCopy(this.array);
    	String array2[] = arrayCopy(multiset2.getArray());
    	    	
    	
    	for (int i = 0; i <array2.length - 1; i = i+2)
    	{
    		boolean existingElement = false;
    		
    		for (int j = 0; j < array1.length - 1; j = j+2)
    		{
    			// If an element is common between two sets, then just summing up the instance values
    			if (array1[j].contentEquals(array2[i]))
    			{
    				//System.out.println(" Common Element : " + array2[i]);
    				array1[j+1] = String.valueOf(Integer.parseInt(array[j+1]) + Integer.parseInt(array2[i+1]));
    				existingElement = true;
    				break;
    			}
    		}
    		// If an element is not common between two sets, then adding it at the end of the array
    		if (! existingElement)
    		{
    			int newSize = array1.length + 2;
    			String[] newArray = new String[newSize];

    			for (int k = 0; k < array1.length; k++) 
    			{
    				newArray[k] = array1[k];
    			}

    			newArray[array1.length] = array2[i];
    			newArray[array1.length + 1] = array2[i+1];

    			array1 = newArray;
    		}
    	}
    	//*
//    	System.out.println("Array 1 : " + array1.toString());
    	arrayUnion.setArray(array1);
       
        return arrayUnion;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {

    	ArrayMultiset arrayIntersect = new ArrayMultiset();
    	ArrayMultiset multiset2 = (ArrayMultiset) other;
    	String array[] = new String[0];
    	String array2[] = multiset2.getArray();
          	
    	for (int i = 0; i < this.array.length - 1 ; i = i+2)
    	{
    		for (int j = 0; j < array2.length - 1 ; j = j+2)
    		{
    			if (this.array[i].compareTo(array2[j]) == 0)
    			{
    				int newSize = array.length + 2;
        			String[] newArray = new String[newSize];
      
        			for (int k = 0; k < array.length; k++) 
        			{
        	                newArray[k] = array[k];
        	        }
        			
    				//System.out.println("Common Element is: " + this.array[i]);
    				if (Integer.parseInt(this.array[i+1]) <= Integer.parseInt(array2[j+1]))
    				{
    					newArray[array.length] = this.array[i];
        	        	newArray[array.length + 1] = this.array[i+1];
       				}
    				else 
    				{
    					newArray[array.length] = array2[j];
        	        	newArray[array.length + 1] = array2[j+1];
     				}
    				array = newArray;
					
    				break;
       			}
    		}
    	}
    	
    	arrayIntersect.setArray(array);
    	
        return arrayIntersect;

    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {

    	ArrayMultiset arrayDifference = new ArrayMultiset();
    	ArrayMultiset multiset2 = (ArrayMultiset) other;
    	String array1[] = arrayCopy(this.array);
    	String array2[] = arrayCopy(multiset2.getArray());
    	    	
    	for (int i = 0; i <array2.length - 1; i = i+2)
    	{
    		for (int j = 0; j < array1.length - 1; j = j+2)
    		{
    			// If an element is common between two sets, then just summing up the instance values
    			if (array1[j].contentEquals(array2[i]))
    			{
    				//System.out.println(" Common Element : " + array2[i]);
    				array1[j+1] = String.valueOf(Integer.parseInt(array[j+1]) - Integer.parseInt(array2[i+1]));
    				break;
    			}
    		}
       	}
    	//*
//    	System.out.println("Array 1 : " + array1.toString());
    	arrayDifference.setArray(array1);
       
        return arrayDifference;
    } // end of difference()
    
    
    // Array sort function - Sorting in descending order based on number of instances of each element
    public void sortArray() {
    	
    	for (int i = 1; i < array.length; i = i+2)
    	{
    		for (int j = i+2; j < array.length ; j = j+2)
    		{
    			if ((array[i].compareTo(array[j])) < 0)
    			{
    				String temp1, temp2;
    				temp1 = array[i-1];
    				temp2 = array[i];
    				array[i-1] = array[j-1];
    				array[i] = array[j];
    				array[j-1] = temp1;
    				array[j] = temp2;
    			}
    		}
    	}
      }
    

    //Array copy function
    public String[] arrayCopy(String[] arrayToCopy)
    {
    	String[] copiedArray = new String[arrayToCopy.length];
    	for (int a = 0; a < arrayToCopy.length ; a++)
    	{
    		copiedArray[a] = arrayToCopy[a];
    	}
    	
    	return copiedArray;
    }

} // end of class ArrayMultiset
