//package implementation;

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
    		array = new String[2];
    		array[0] = elem;
    		array[1] = "1";
    	}
    	else
    	{
    		// if the new element is already present in the array, then just incrementing the instance count
    		if (array[0].contentEquals(elem))
    			array[1] = String.valueOf(Integer.parseInt(array[1]) + 1);
    		
    		else
    		{
    			// if the new element is already present in the array, then just incrementing the instance count
    			for (int i = 0; i < array.length; i = i+2)
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
    	for (int i = 0; i < array.length; i = i+2)
		{
			System.out.println("Array Element: " + array[i] + "      Instance Count: " + array[i+1]);
		}
}

    @Override
	public int search(String elem) {
        // Implement me!

        // Placeholder, please update.
        return searchFailed;
    } // end of search()


    @Override
    public List<String> searchByInstance(int instanceCount) {

        // Placeholder, please update.
        return null;
    } // end of searchByInstance


    @Override
	public boolean contains(String elem) {
        // Implement me!

        // Placeholder, please update.
        return false;
    } // end of contains()


    @Override
	public void removeOne(String elem) {
        // Implement me!
    } // end of removeOne()


    @Override
	public String print() {

        // Placeholder, please update.
        return new String();
    } // end of OrderedPrint


    @Override
	public String printRange(String lower, String upper) {

        // Placeholder, please update.
        return new String();
    } // end of printRange()


    @Override
	public RmitMultiset union(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of union()


    @Override
	public RmitMultiset intersect(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of intersect()


    @Override
	public RmitMultiset difference(RmitMultiset other) {

        // Placeholder, please update.
        return null;
    } // end of difference()

} // end of class ArrayMultiset
