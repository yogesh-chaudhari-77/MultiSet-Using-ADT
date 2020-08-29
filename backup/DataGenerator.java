package implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.naming.InitialContext;

public class DataGenerator {
	private Random randomUtil = new Random();

	// Constants
	private static final int smallDatasetSize = 100;
	private static final int mediumDatasetSize = 1500;
	private static final int largeDatasetSize = 4500;

	// Alphabet set used for random selection of characters for word generation
	String [] alphabetSet = { "a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z" };


	// Different sized datasets with random values in any order
	ArrayList<String> smallDataset = new ArrayList<String>();
	ArrayList<String> mediumDataset = new ArrayList<String>();
	ArrayList<String> largeDataset = new ArrayList<String>();


	// Respective datasets in sorted orders
	ArrayList<String> sortedSmallDataset = new ArrayList<String>();
	ArrayList<String> sortedMediumDataset = new ArrayList<String>();
	ArrayList<String> sortedLargeDataset = new ArrayList<String>();


	// Generates the small dataset of random elements contains elements in random order
	public void generateSmallDataset() {

		for(int i = 0; i < smallDatasetSize; i++) {
			String a = this.getDataElement();
			this.smallDataset.add(a);
			this.sortedSmallDataset.add(a);
		}

		Collections.sort(this.sortedSmallDataset);
		System.out.println("Small Dataset Size : "+this.smallDataset.size());
	}


	// Generates the medium dataset ( 3000 elements ) in random order
	public void generateMediumDataset() {

		for(int i = 0; i < mediumDatasetSize; i++) {
			String a = this.getDataElement();
			this.mediumDataset.add(a);
			this.sortedMediumDataset.add(a);
		}

		Collections.sort(this.sortedMediumDataset);
		System.out.println("Small Dataset Size : "+this.mediumDataset.size());
	}


	// Generates the large dataset (100000 elements) of random elements in random order
	public void generateLargeDataset() {

		for(int i = 0; i < largeDatasetSize; i++) {
			String a = this.getDataElement();
			this.largeDataset.add(a);
			this.sortedLargeDataset.add(a);
		}

		Collections.sort(this.sortedLargeDataset);
		System.out.println("Small Dataset Size : "+this.largeDataset.size());
	}


	// Generates a 5 letter long random word using combination of alphabetical characters only
	public String getDataElement() {

		StringBuilder retStr = new StringBuilder();

		// Initiate forming a 5 letter word
		for(int i = 0; i < 5; i++) {

			// Select random alphabate from assorted list of 26 alphabates
			int a = this.randomUtil.nextInt(26);
			retStr.append(this.alphabetSet[a]);
		}

		return retStr.toString();
	}



	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random
	 *  Operation : Add
	 */

	public void testArraySetAdd(ArrayMultiset arrayMultiset, ArrayList<String> dataset)
	{	
		// Testing best case - Adding duplicate element. First one.
		// Expecting no shifts

//		long startTime = 0, elapsedTime = 0;
//		double elapsedTimeInS = 0.0;

		long startTime = 0, endTime = 0, elapsedTime = 0, elapsedTimeInMS;
		double elapsedTimeInS;

		

		String firstElement = arrayMultiset.getArray()[0].getElement();

		startTime = System.nanoTime();		
		
		arrayMultiset.add(firstElement);
		
		endTime = System.nanoTime();
//		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
//		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
//		elapsedTimeInS = (double)elapsedTime/1000000;						// Converted to Seconds
//		System.out.print(",Best Case," + elapsedTimeInS +",");


		// Worst case 1 - Element is not present in the list

		startTime = TimeUtil.startTime();
		String uniqueElement = "YogeshShriramNotPresentInSet";
		arrayMultiset.add(uniqueElement);
		
//		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
//		elapsedTimeInS = (double)elapsedTime /1000;
//		System.out.print(",Worst Case," + elapsedTimeInS +",");


		// Adding random duplicates

		int datasetSize = dataset.size();
		for(int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			arrayMultiset.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

			elapsedTimeInS = (double)elapsedTime /1000;

		}


		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			arrayMultiset.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
			elapsedTimeInS = (double)elapsedTime /1000;
		}
	}


	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random
	 *  Operation : Remove 
	 */
	public void testArraySetRemove(ArrayMultiset arrayMultiset, ArrayList<String> dataset, int dataSetSize) {

		long startTime = 0, elapsedTime = 0;


		// Testing worst case - Element to be removed is first element with 1 occurance only. Expecting entire arrayShift

		String firstElement = arrayMultiset.getArray()[0].getElement();

		startTime = TimeUtil.startTime();
		arrayMultiset.removeOne(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");


		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";

		startTime = TimeUtil.startTime();
		arrayMultiset.removeOne(largestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Worst / Average case - In between elements
		// Random cases. Elements could be any where in the list
		for (int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			arrayMultiset.removeOne( dataset.get( this.randomUtil.nextInt( dataSetSize - 10 ) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}
	}


	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random
	 *  Operation : Print
	 */
	public void testArraySetPrint(ArrayMultiset arrayMultiset, ArrayList<String> dataset) {

		long startTime = 0, elapsedTime = 0;


		// Single occurance - Best case for array

		startTime = TimeUtil.startTime();
		arrayMultiset.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Worst case - Random duplicate elements are present

		int iterations = dataset.size();
		for(int a = 0 ; a < iterations; a++) {
			arrayMultiset.add( dataset.get( this.randomUtil.nextInt(iterations - 3) ) );
		}


		startTime = TimeUtil.startTime();
		arrayMultiset.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
	}


	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random
	 *  Operation : Print
	 */
	public void testArraySetIntersect(ArrayMultiset first, ArrayMultiset second, int initialSetSize, int largeSetSize) {

		long startTime = 0, elapsedTime = 0;

		// Common elements in same order

		startTime = TimeUtil.startTime();
		first.intersect(second);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Worst case - no elements are overlapping - Loop will run n1xn2 times
		second = new ArrayMultiset();
		for(int a = 0; a < initialSetSize; a++) {
			second.add( this.getDataElement() );
		}

		startTime = TimeUtil.startTime();
		first.intersect(second);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Worse case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < largeSetSize; a++) {
			second.add( this.getDataElement() );
		}

		startTime = TimeUtil.startTime();
		first.intersect(second);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
	}


	/*
	 *  DataStructure : OrderedLinkedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Alphabetically ascending order of elements
	 *  Operation : Print
	 */
	public void testOrderedListAdd(OrderedLinkedListMultiset orderedList, ArrayList<String> dataset) {

		long startTime = 0, elapsedTime = 0;

		// Testing best case - Element to be added is equal to the head element
		String firstElement = orderedList.head.getVal();		

		startTime = TimeUtil.startTime();
		orderedList.add(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");


		// Testing best case - Element to be added is equal to the head element
		String smallestElement = "a";

		startTime = TimeUtil.startTime();
		orderedList.add(smallestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Worst case - Adding an element which is not present in the string hence will be added at last
		// Expecting 1 complete traversal.
		String uniqueElement = "YogeshShriram";

		startTime = TimeUtil.startTime();
		orderedList.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Adding random duplicates
		int datasetSize = dataset.size();
		for(int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			orderedList.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			orderedList.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}
	}

	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Alphabetically ascending order of elements in the list 
	 *  Operation : Remove
	 */

	public void testOrderedListRemove(OrderedLinkedListMultiset orderedList, ArrayList<String> sortedDataSet, int datasetSize)
	{
		long startTime = 0, elapsedTime = 0;

		// Testing best case - Element to be removed is equal to the head element
		String firstElement = orderedList.getHead().getVal();

		startTime = TimeUtil.startTime();
		orderedList.removeOne(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";

		startTime = TimeUtil.startTime();
		orderedList.removeOne(largestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");


		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		for (int i = 0; i < 5; i++) {
			startTime = TimeUtil.startTime();
			orderedList.removeOne( sortedDataSet.get( this.randomUtil.nextInt( datasetSize - 10 ) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}
	}


	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Alphabetically ascending order of elements in the list 
	 *  Operation : Intersection
	 */

	public void testOrderedListIntersect(OrderedLinkedListMultiset firstOrderedList, OrderedLinkedListMultiset secondOrderedList, int initialSetSize, int LargeDatasetSize)
	{
		long startTime = 0, elapsedTime = 0;


		// Testing best case - Both multiset contains the overlapping elements. running time is n1 as n1 == n2
		startTime = TimeUtil.startTime();
		firstOrderedList.intersect(secondOrderedList);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");


		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondOrderedList = new OrderedLinkedListMultiset();
		for(int a = 0; a < initialSetSize; a++) {
			secondOrderedList.add( this.getDataElement() );
		}

		startTime = TimeUtil.startTime();
		firstOrderedList.intersect(secondOrderedList);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < LargeDatasetSize; a++) {

			secondOrderedList.add( this.getDataElement() );
		}

		startTime = TimeUtil.startTime();
		firstOrderedList.intersect(secondOrderedList);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
	}


	/*
	 *  DataStructure : Ordered Linked List
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Ascending order
	 *  Operation : Print - Ordered by instance count
	 */
	public void testOrderedListPrint(OrderedLinkedListMultiset orderedList, ArrayList<String> dataset)
	{
		long startTime = 0, elapsedTime = 0;


		// Best case : All unique elements - Bubble sort can be stopped in first iteration
		startTime = TimeUtil.startTime();
		orderedList.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");


		// Ordered List contains duplicate elements
		// Higher complexity is expected as swapping would be required for sorting elements based on occurance count for print operations
		int iterations = dataset.size();
		for(int a = 0 ; a < iterations; a++) {
			orderedList.add( dataset.get( this.randomUtil.nextInt(iterations - 3) ) );
		}

		startTime = TimeUtil.startTime();
		orderedList.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
	}

	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small | Medium | Large
	 *  Distribution : BST Behaviour
	 *  Operation : Add
	 */
	public void testTreeSetAdd(BstMultiset bstSet, ArrayList<String> dataset) 
	{
		long startTime = 0, elapsedTime = 0;


		//  Best case - Element to be added is equal to the root element
		String rootVal = bstSet.getRoot().getVal();

		startTime = TimeUtil.startTime();
		bstSet.add(rootVal);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Worst case - Adding an element which is not present in the string
		String uniqueElement = "YogeshShriram";
		startTime = TimeUtil.startTime();
		bstSet.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Adding random duplicates
		int datasetSize = dataset.size();
		for(int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			bstSet.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

		// Usual cases - In between elements - Random insertions
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			bstSet.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}


		/* Left Skewed Tree
		 * Elements have been added in decreseing order.
		 */
		Collections.reverse(dataset);
		for(String ele : dataset) {
			bstSet.add(ele);
		}

		//  Best case - Element to be added is equal to the root element
		rootVal = bstSet.getRoot().getVal();

		startTime = TimeUtil.startTime();
		bstSet.add(rootVal);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");


		// Worst case - Adding an element which is not present in the string
		startTime = TimeUtil.startTime();
		bstSet.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Adding random duplicates
		for(int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			bstSet.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

		// Usually cases - In between elements - Random insertions
		for (int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			bstSet.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

		/*
		 * Left Skewed Tree Ends Here
		 */


		/* Right Skewed Tree
		 * Elements have been added in increasing order.
		 */
		Collections.sort(dataset);
		for(String ele : dataset) {

			startTime = TimeUtil.startTime();
			bstSet.add(ele);
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

		//  Best case - Element to be added is equal to the root element
		rootVal = bstSet.getRoot().getVal();

		startTime = TimeUtil.startTime();
		bstSet.add(rootVal);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Worst case - Adding an element which is not present in the string
		startTime = TimeUtil.startTime();
		bstSet.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Adding random duplicates
		for(int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			bstSet.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

		// Usually cases - In between elements - Random insertions
		for (int i = 0; i < 5; i++) {
			startTime = TimeUtil.startTime();
			bstSet.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

		/*
		 * Right Skewed Tree Ends Here
		 */
	}

	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small | Medium | Large
	 *  Distribution : BST Behaviour
	 *  Operation : Remove
	 */

	public void testTreeSetRemove(BstMultiset bstSet, ArrayList<String> dataset, int datasetSize)
	{

		long startTime = 0, elapsedTime = 0;

		// Testing best case - Element to be removed is equal to the root element
		String rootVal = bstSet.getRoot().getVal();

		startTime = TimeUtil.startTime();
		bstSet.removeOne(rootVal);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Average case - In between elements
		// Random cases. Elements could be any where in the tree
		for (int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			bstSet.removeOne( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

	}


	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small | Medium | Large
	 *  Distribution : BST Behaviour
	 *  Operation : Intersection
	 */

	public void testTreeSetIntersect(BstMultiset firstTree, BstMultiset secondTree, int initialDatasetSize, int largeDatasetSize)
	{
		long startTime = 0, elapsedTime = 0;

		// Testing best case - Both multiset contains the overlapping elements
		startTime = TimeUtil.startTime();
		firstTree.intersect(secondTree);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondTree = new BstMultiset();
		for(int a = 0; a < initialDatasetSize; a++) {
			secondTree.add( this.getDataElement() );
		}

		startTime = TimeUtil.startTime();
		firstTree.intersect(secondTree);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < largeDatasetSize; a++) {
			secondTree.add( this.getDataElement() );
		}

		startTime = TimeUtil.startTime();
		firstTree.intersect(secondTree);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
	}



	/*
	 *  DataStructure : Tree Set
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random - As per BST properties
	 *  Operation : Print - Ordered by instance count
	 */
	public void testTreeSetPrint(BstMultiset bstTree, ArrayList<String> dataset)
	{
		long startTime = 0, elapsedTime = 0;

		// Best case - Singly occurance - Loop can be terminated after 1st iterations
		startTime = TimeUtil.startTime();
		bstTree.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Worst case - Tree contains duplicate elements
		int iterations = dataset.size();
		for(int a = 0 ; a < iterations / 2; a++) {
			bstTree.add( dataset.get( this.randomUtil.nextInt(iterations - 3) ) );
		}

		startTime = TimeUtil.startTime();
		bstTree.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
	}


	/*
	 *  DataStructure : Dual OrderedList List
	 *  Dataset : Small | Medium | Large
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Add
	 */

	public void testDualOrderedListAdd(DualLinkedListMultiset dualList, ArrayList<String> dataset)
	{
		long startTime = 0, elapsedTime = 0;

		// Testing best case - Element to be added is equal to the head element
		String firstElement = dualList.getHead1().getVal();

		startTime = TimeUtil.startTime();
		dualList.add(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Testing best case - Element to be added is less than head element.
		String smallestElement = "a";

		startTime = TimeUtil.startTime();
		dualList.add(smallestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Adding an element which is not present in the string
		String uniqueElement = "YogeshShriramNotPresentInList";

		startTime = TimeUtil.startTime();
		dualList.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Practical case - Adding random duplicates
		int datasetSize = dataset.size();
		for (int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			dualList.add( dataset.get( this.randomUtil.nextInt( datasetSize - 3) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			dualList.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}

	}


	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Remove
	 */

	public void testDualOrderedListRemove(DualLinkedListMultiset dualList, ArrayList<String> dataset, int datasetSize)
	{
		long startTime = 0, elapsedTime = 0;

		// Testing best case - Element to be removed is equal to the head element
		String firstElement = dualList.getHead1().getVal();

		startTime = TimeUtil.startTime();
		dualList.removeOne(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";

		startTime = TimeUtil.startTime();
		dualList.removeOne(largestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");


		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		for (int i = 0; i < 5; i++) {

			startTime = TimeUtil.startTime();
			dualList.removeOne( dataset.get(  this.randomUtil.nextInt( datasetSize - 10 ) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
		}
	}



	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Small
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Intersection
	 */

	public void testDualOrderedListIntersect(DualLinkedListMultiset FirstDualList, DualLinkedListMultiset SecDualList, int initialDatasetSize, int LargeDatasetSize)
	{

		long startTime = 0, elapsedTime = 0;


		// Testing best case - Both multiset contains the overlapping elements
		startTime = TimeUtil.startTime();
		FirstDualList.intersect(SecDualList);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		SecDualList = new DualLinkedListMultiset();
		for(int a = 0; a < initialDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}

		startTime = TimeUtil.startTime();
		FirstDualList.intersect(SecDualList);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < LargeDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}

		startTime = TimeUtil.startTime();
		FirstDualList.intersect(SecDualList);
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
	}


	/*
	 *  DataStructure : Dual Ordered Linked List
	 *  Dataset : Small | Medium | Large
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Print - Ordered by instance count
	 */
	public void testDualOrderedListPrint(DualLinkedListMultiset dualList, ArrayList<String> dataset)
	{
		long startTime = 0, elapsedTime = 0;

		// Testing single occurance complexity
		startTime = TimeUtil.startTime();
		dualList.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");

		// Populating duplicates - Sorting is not required for dual ordered list
		int datasetSize = dataset.size();
		for(int i = 0; i < datasetSize/2; i++) {
			dualList.add( dataset.get( this.randomUtil.nextInt(datasetSize - 5) ) );
		}

		startTime = TimeUtil.startTime();
		dualList.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, "milli");
	}


	/*
	 *	Runs the testing for the addition, removeOne, intersection, print functions on Array Implementations of Multiset
	 *	The respective functions contains the testing code the best and worst cases
	 *	The test runs on each dataset size 
	 */
	public void run_arraySet() {
		
		long startTime = 0, endTime = 0, elapsedTime = 0, elapsedTimeInMS;
		double elapsedTimeInS;	

		// Initialising the varied sized Array Multisets
		ArrayMultiset smallArrSet = new ArrayMultiset();
		ArrayMultiset mediumArrSet = new ArrayMultiset();
		ArrayMultiset largeArrSet = new ArrayMultiset();

		for (String ele : this.smallDataset) {
			smallArrSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumArrSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeArrSet.add(ele);
		}
		// Finished initialising and populating the multisets


		// Testing add scenario on each datasize - Each function include best and worst cases
		startTime = System.nanoTime();		
		
		this.testArraySetAdd(smallArrSet, this.smallDataset);		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayAdd,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();	
		
		this.testArraySetAdd(mediumArrSet, this.mediumDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayAdd,MediumDataset," + elapsedTimeInS +"\n" );
		
		
		startTime = System.nanoTime();	

		this.testArraySetAdd(largeArrSet, this.largeDataset);

		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayAdd,largeDataset," + elapsedTimeInS +"\n" );
		
		
		// Testing remove scenario on each datasize - Each function include best and worst cases
		
		startTime = System.nanoTime();		
		
		this.testArraySetRemove(smallArrSet, this.smallDataset, smallDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayRemove,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testArraySetRemove(mediumArrSet, this.mediumDataset, mediumDatasetSize);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayRemove,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testArraySetRemove(largeArrSet, this.largeDataset, largeDatasetSize);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayRemove,LargeDataset," + elapsedTimeInS +"\n" );

		// Testing Print scenario on each datasize - Each function include best and worst cases
		startTime = System.nanoTime();		

		this.testArraySetPrint(smallArrSet, this.smallDataset);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayPrint,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testArraySetPrint(mediumArrSet, this.mediumDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayPrint,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testArraySetPrint(largeArrSet, this.largeDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayPrint,LargeDataset," + elapsedTimeInS +"\n" );
		

  /*
		this.testArraySetAdd(smallArrSet, this.smallDataset);
		this.testArraySetAdd(mediumArrSet, this.mediumDataset);
		this.testArraySetAdd(largeArrSet, this.largeDataset);

		// Testing remove scenario on each datasize - Each function include best and worst cases
		this.testArraySetRemove(smallArrSet, this.smallDataset, smallDatasetSize);
		this.testArraySetRemove(mediumArrSet, this.mediumDataset, mediumDatasetSize);
		this.testArraySetRemove(largeArrSet, this.largeDataset, largeDatasetSize);

		// Testing Print scenario on each datasize - Each function include best and worst cases
		this.testArraySetPrint(smallArrSet, this.smallDataset);
		this.testArraySetPrint(mediumArrSet, this.mediumDataset);
		this.testArraySetPrint(largeArrSet, this.largeDataset);
  */

		// Refrehing the datasets to for the interesection cases - the above operations would affect the multisets
		smallArrSet = new ArrayMultiset();
		ArrayMultiset secSmallArrSet = new ArrayMultiset();

		mediumArrSet = new ArrayMultiset();
		ArrayMultiset secMediumArrSet = new ArrayMultiset();

		largeArrSet = new ArrayMultiset();
		ArrayMultiset seclLargeArrSet = new ArrayMultiset();

		for (String ele : this.smallDataset) {
			smallArrSet.add(ele);
			secSmallArrSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumArrSet.add(ele);
			secMediumArrSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeArrSet.add(ele);
			seclLargeArrSet.add(ele);
		}

		// Testing the intersect scenario on each datasize

		startTime = System.nanoTime();		

		this.testArraySetIntersect(smallArrSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayIntersect,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testArraySetIntersect(mediumArrSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayIntersect,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testArraySetIntersect(largeArrSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("ArrayIntersect,LargeDataset," + elapsedTimeInS +"\n" );

    /*
		this.testArraySetIntersect(smallArrSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);
		this.testArraySetIntersect(mediumArrSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);
		this.testArraySetIntersect(largeArrSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);
    */
	}


	/*
	 *	Runs the testing for the addition, removeOne, intersection, print functions for orderedListSet
	 *	The respective functions contains the testing code the best and worst cases
	 *	The test runs on each dataset size 
	 */
	public void run_orderedListSet() {

		long startTime = 0, endTime = 0, elapsedTime = 0, elapsedTimeInMS;
		double elapsedTimeInS;
		
		// Initialising the varied sized OrderedList Multisets
		OrderedLinkedListMultiset smallSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset mediumSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset largeSet = new OrderedLinkedListMultiset();

		for (String ele : this.smallDataset) {
			smallSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeSet.add(ele);
		}
		// Finished initialising and populating the multisets
		
		// Testing add scenario on each datasize - Each function include best and worst cases
		startTime = System.nanoTime();		
		
		this.testOrderedListAdd(smallSet, this.smallDataset);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListAdd,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();	
		
		this.testOrderedListAdd(mediumSet, this.mediumDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListAdd,MediumDataset," + elapsedTimeInS +"\n" );
		
		
		startTime = System.nanoTime();	

		this.testOrderedListAdd(largeSet, this.largeDataset);

		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListAdd,largeDataset," + elapsedTimeInS +"\n" );
		
		
		// Testing remove scenario on each datasize - Each function include best and worst cases
		
		startTime = System.nanoTime();		
		
		this.testOrderedListRemove(smallSet, this.sortedSmallDataset, smallDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListRemove,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testOrderedListRemove(mediumSet, this.sortedMediumDataset, mediumDatasetSize);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListRemove,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testOrderedListRemove(largeSet, this.sortedLargeDataset, largeDatasetSize);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListRemove,LargeDataset," + elapsedTimeInS +"\n" );

		// Testing Print scenario on each datasize - Each function include best and worst cases
		startTime = System.nanoTime();		
		
		this.testOrderedListPrint(smallSet, this.smallDataset);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListPrint,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testOrderedListPrint(mediumSet, this.mediumDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListPrint,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testOrderedListPrint(largeSet, this.largeDataset);		

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListPrint,LargeDataset," + elapsedTimeInS +"\n" );

/*
		// Testing add scenario on each datasize - Each function include best and worst cases
		this.testOrderedListAdd(smallSet, this.smallDataset);
		this.testOrderedListAdd(mediumSet, this.mediumDataset);
		this.testOrderedListAdd(largeSet, this.largeDataset);


		// Testing remove scenario on each datasize - Each function include best and worst cases
		this.testOrderedListRemove(smallSet, this.sortedSmallDataset, smallDatasetSize);
		this.testOrderedListRemove(mediumSet, this.sortedMediumDataset, mediumDatasetSize);
		this.testOrderedListRemove(largeSet, this.sortedLargeDataset, largeDatasetSize);

		// Testing Print scenario on each datasize - Each function include best and worst cases
		this.testOrderedListPrint(smallSet, this.smallDataset);
		this.testOrderedListPrint(mediumSet, this.mediumDataset);
		this.testOrderedListPrint(largeSet, this.largeDataset);
*/

		// Refrehing the datasets to for the interesection cases - the above operations would affect the multisets
		smallSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset secSmallArrSet = new OrderedLinkedListMultiset();

		mediumSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset secMediumArrSet = new OrderedLinkedListMultiset();

		largeSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset seclLargeArrSet = new OrderedLinkedListMultiset();

		for (String ele : this.smallDataset) {
			smallSet.add(ele);
			secSmallArrSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumSet.add(ele);
			secMediumArrSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeSet.add(ele);
			seclLargeArrSet.add(ele);
		}

		// Testing the intersect scenario on each datasize
		startTime = System.nanoTime();		

		this.testOrderedListIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListIntersect,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testOrderedListIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListIntersect,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testOrderedListIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("OrderedLinkedListIntersect,LargeDataset," + elapsedTimeInS +"\n" );
		
		
/*
		this.testOrderedListIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);
		this.testOrderedListIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);
		this.testOrderedListIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);
*/
	}


	/*
	 *	Runs the testing for the addition, removeOne, intersection, print functions for the bstTreeSet
	 *	The respective functions contains the testing code the best and worst cases
	 *	The test runs on each dataset size
	 */
	public void run_bstTreeSet() {
		
		long startTime = 0, endTime = 0, elapsedTime = 0, elapsedTimeInMS;
		double elapsedTimeInS;		

		// Initialising the varied sized BST Tree Multisets
		BstMultiset smallSet = new BstMultiset();
		BstMultiset mediumSet = new BstMultiset();
		BstMultiset largeSet = new BstMultiset();

		for (String ele : this.smallDataset) {
			smallSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeSet.add(ele);
		}
		// Finished initialising and populating the multisets
		
		// Testing add scenario on each datasize - Each function include best and worst cases
		startTime = System.nanoTime();		
		
		this.testTreeSetAdd(smallSet, this.sortedSmallDataset);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTAdd,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();	
		
		this.testTreeSetAdd(mediumSet, this.sortedMediumDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTAdd,MediumDataset," + elapsedTimeInS +"\n" );
		
		
		startTime = System.nanoTime();	

		this.testTreeSetAdd(largeSet, this.sortedLargeDataset);

		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTAdd,largeDataset," + elapsedTimeInS +"\n" );
		
		
		// Testing remove scenario on each datasize - Each function include best and worst cases
		startTime = System.nanoTime();		
		
		this.testTreeSetRemove(smallSet, this.smallDataset, smallDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTRemove,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testTreeSetRemove(mediumSet, this.mediumDataset, mediumDatasetSize);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTRemove,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testTreeSetRemove(largeSet, this.largeDataset, largeDatasetSize);		
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTRemove,LargeDataset," + elapsedTimeInS +"\n" );

		// Testing Print scenario on each datasize - Each function include best and worst cases
		startTime = System.nanoTime();		
		
		this.testTreeSetPrint(smallSet, this.smallDataset);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTPrint,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testTreeSetPrint(mediumSet, this.mediumDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTPrint,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testTreeSetPrint(largeSet, this.largeDataset);		

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTPrint,LargeDataset," + elapsedTimeInS +"\n" );

		
/*

		// Testing add scenario on each datasize - Each function include best and worst cases
		this.testTreeSetAdd(smallSet, this.sortedSmallDataset);
		this.testTreeSetAdd(mediumSet, this.sortedMediumDataset);
		this.testTreeSetAdd(largeSet, this.sortedLargeDataset);

		// Testing remove scenario on each datasize - Each function include best and worst cases
		this.testTreeSetRemove(smallSet, this.smallDataset, smallDatasetSize);
		this.testTreeSetRemove(mediumSet, this.mediumDataset, mediumDatasetSize);
		this.testTreeSetRemove(largeSet, this.largeDataset, largeDatasetSize);

		// Testing Print scenario on each datasize - Each function include best and worst cases
		this.testTreeSetPrint(smallSet, this.smallDataset);
		this.testTreeSetPrint(mediumSet, this.mediumDataset);
		this.testTreeSetPrint(largeSet, this.largeDataset);

*/
		// Refrehing the datasets to for the interesection cases - the above operations would affect the multisets
		smallSet = new BstMultiset();
		BstMultiset secSmallArrSet = new BstMultiset();

		mediumSet = new BstMultiset();
		BstMultiset secMediumArrSet = new BstMultiset();

		largeSet = new BstMultiset();
		BstMultiset seclLargeArrSet = new BstMultiset();

		for (String ele : this.smallDataset) {
			smallSet.add(ele);
			secSmallArrSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumSet.add(ele);
			secMediumArrSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeSet.add(ele);
			seclLargeArrSet.add(ele);
		}

		// Testing the intersect scenario on each datasize
		startTime = System.nanoTime();		

		this.testTreeSetIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTIntersect,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testTreeSetIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTIntersect,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testTreeSetIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("BSTIntersect,LargeDataset," + elapsedTimeInS +"\n" );
		
		
/*
		this.testTreeSetIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);
		this.testTreeSetIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);
		this.testTreeSetIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);
*/
	}


	/*
	 *	Runs the testing for the addition, removeOne, intersection, print functions
	 *	The respective functions contains the testing code the best and worst cases
	 *	The test runs on each dataset size 
	 */
	public void run_dualSet() {
		
		long startTime = 0, endTime = 0, elapsedTime = 0, elapsedTimeInMS;
		double elapsedTimeInS;

		// Initialising the varied sized DualLinkedList Multisets
		DualLinkedListMultiset smallSet = new DualLinkedListMultiset();
		DualLinkedListMultiset mediumSet = new DualLinkedListMultiset();
		DualLinkedListMultiset largeSet = new DualLinkedListMultiset();

		for (String ele : this.smallDataset) {
			smallSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeSet.add(ele);
		}
		// Finished initialising and populating the multisets

		// Testing add scenario on each datasize - Each function include best and worst cases

		startTime = System.nanoTime();		
		
		this.testDualOrderedListAdd(smallSet, this.sortedSmallDataset);
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListAdd,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();	
		
		this.testDualOrderedListAdd(mediumSet, this.sortedMediumDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListAdd,MediumDataset," + elapsedTimeInS +"\n" );
		
		
		startTime = System.nanoTime();	

		this.testDualOrderedListAdd(largeSet, this.sortedLargeDataset);

		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListAdd,largeDataset," + elapsedTimeInS +"\n" );
		
		
		// Testing remove scenario on each datasize - Each function include best and worst cases
		startTime = System.nanoTime();		
		
		this.testDualOrderedListRemove(smallSet, this.smallDataset, smallDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListRemove,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testDualOrderedListRemove(mediumSet, this.mediumDataset, mediumDatasetSize);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListRemove,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testDualOrderedListRemove(largeSet, this.largeDataset, largeDatasetSize);		
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListRemove,LargeDataset," + elapsedTimeInS +"\n" );

		// Testing Print scenario on each datasize - Each function include best and worst cases
		startTime = System.nanoTime();		
		
		this.testDualOrderedListPrint(smallSet, this.smallDataset);
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListPrint,SmallDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testDualOrderedListPrint(mediumSet, this.mediumDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListPrint,MediumDataset," + elapsedTimeInS +"\n" );
		
		startTime = System.nanoTime();		

		this.testDualOrderedListPrint(largeSet, this.largeDataset);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListPrint,LargeDataset," + elapsedTimeInS +"\n" );
		
		
/*
		this.testDualOrderedListAdd(smallSet, this.sortedSmallDataset);
		this.testDualOrderedListAdd(mediumSet, this.sortedMediumDataset);
		this.testDualOrderedListAdd(largeSet, this.sortedLargeDataset);


		// Testing remove scenario on each datasize - Each function include best and worst cases
		this.testDualOrderedListRemove(smallSet, this.smallDataset, smallDatasetSize);
		this.testDualOrderedListRemove(mediumSet, this.mediumDataset, mediumDatasetSize);
		this.testDualOrderedListRemove(largeSet, this.largeDataset, largeDatasetSize);

		// Testing Print scenario on each datasize - Each function include best and worst cases
		this.testDualOrderedListPrint(smallSet, this.smallDataset);
		this.testDualOrderedListPrint(mediumSet, this.mediumDataset);
		this.testDualOrderedListPrint(largeSet, this.largeDataset);

*/
		// Refrehing the datasets to for the interesection cases - the above operations would affect the multisets
		smallSet = new DualLinkedListMultiset();
		DualLinkedListMultiset secSmallArrSet = new DualLinkedListMultiset();
		mediumSet = new DualLinkedListMultiset();
		DualLinkedListMultiset secMediumArrSet = new DualLinkedListMultiset();
		largeSet = new DualLinkedListMultiset();
		DualLinkedListMultiset seclLargeArrSet = new DualLinkedListMultiset();

		for (String ele : this.smallDataset) {
			smallSet.add(ele);
			secSmallArrSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumSet.add(ele);
			secMediumArrSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeSet.add(ele);
			seclLargeArrSet.add(ele);
		}

		
		// Testing the intersect scenario on each datasize
		startTime = System.nanoTime();		

		this.testDualOrderedListIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListIntersect,SmallDataset," + elapsedTimeInS +"\n" );

		startTime = System.nanoTime();		

		this.testDualOrderedListIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListIntersect,MediumDataset," + elapsedTimeInS +"\n" );

		startTime = System.nanoTime();		

		this.testDualOrderedListIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTime/1000000000;						// Converted to Seconds
		System.out.print("DualLinkedListIntersect,LargeDataset," + elapsedTimeInS +"\n" );

/*
		// Testing the intersect scenario on each datasize
		this.testDualOrderedListIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);
		this.testDualOrderedListIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);
		this.testDualOrderedListIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);
*/
	}


	/*
	 * Calls the testing methods on arrays, orderedList, tree and DualOrderedList
	 */
	public void run() {

		long startTime = 0, endTime = 0, elapsedTime = 0, elapsedTimeInMS;

		double elapsedTimeInS;

		startTime = System.nanoTime();

		run_arraySet();

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds

		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTimeInMS/1000;						// Converted to Seconds
		System.out.println("Array Total: " + elapsedTimeInMS);

		startTime = System.nanoTime();

		run_orderedListSet();

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds  

		elapsedTimeInS = (double)elapsedTimeInMS/1000;						// Converted to Seconds
		System.out.println("OrderedList Total: " + elapsedTimeInMS);
    
		startTime = System.nanoTime();

		run_bstTreeSet();

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds  

		elapsedTimeInS = (double)elapsedTimeInMS/1000;						// Converted to Seconds
		System.out.println("BST Total: " + elapsedTimeInMS);								

		startTime = System.nanoTime();

		run_dualSet();

		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds

		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
		elapsedTimeInS = (double)elapsedTimeInMS/1000;						// Converted to Seconds
		System.out.println("DualLinkedList Total: " + elapsedTimeInMS);

	}


	/*
	 * Generats the small, medium, and large datasets with the defined sizes
	 * Initiates the testing
	 */
	public static void main(String[] args) {

		DataGenerator driver = new DataGenerator();
		driver.generateSmallDataset();
		driver.generateMediumDataset();
		driver.generateLargeDataset();
		driver.run();
	}

}