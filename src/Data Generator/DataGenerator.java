import implementation.*;

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
	private static final int doubleSmallDatasetSize = 2500;
	private static final int mediumDatasetSize = 5000;
	private static final int doubleMediumDatasetSize = 7500;
	private static final int largeDatasetSize = 10000;

	// Alphabet set used for random selection of characters for word generation
	String [] alphabetSet = { "a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z" };


	// Different sized datasets with random values in any order
	ArrayList<String> smallDataset = new ArrayList<String>();
	ArrayList<String> mediumDataset = new ArrayList<String>();
	ArrayList<String> largeDataset = new ArrayList<String>();
	ArrayList<String> doubleSmallDataset = new ArrayList<String>();
	ArrayList<String> doubleMediumDataset = new ArrayList<String>();


	// Respective datasets in sorted orders
	ArrayList<String> sortedSmallDataset = new ArrayList<String>();
	ArrayList<String> sortedMediumDataset = new ArrayList<String>();
	ArrayList<String> sortedLargeDataset = new ArrayList<String>();
	ArrayList<String> sortedDoubleSmallDataset = new ArrayList<String>();
	ArrayList<String> sortedDoubleMediumDataset = new ArrayList<String>();


	// Generates the small dataset of random elements contains elements in random order
	public void generateSmallDataset() {

		for(int i = 0; i < smallDatasetSize; i++) {
			String a = this.getDataElement();
			this.smallDataset.add(a);
			this.sortedSmallDataset.add(a);
		}

		Collections.sort(this.sortedSmallDataset);
		System.out.println("\nSmall Dataset Size : "+this.smallDataset.size());
	}


	// Generates the medium dataset ( 5000 elements ) in random order
	public void generateMediumDataset() {

		for(int i = 0; i < mediumDatasetSize; i++) {
			String a = this.getDataElement();
			this.mediumDataset.add(a);
			this.sortedMediumDataset.add(a);
		}

		Collections.sort(this.sortedMediumDataset);
		System.out.println("Medium Dataset Size : "+this.mediumDataset.size());
	}


	// Generates the large dataset (10000 elements) of random elements in random order
	public void generateLargeDataset() {

		for(int i = 0; i < largeDatasetSize; i++) {
			String a = this.getDataElement();
			this.largeDataset.add(a);
			this.sortedLargeDataset.add(a);
		}

		Collections.sort(this.sortedLargeDataset);
		System.out.println("Large Dataset Size : "+this.largeDataset.size());
	}


	// Generates the double small dataset (2500 elements) of random elements in random order
	public void generateDoubleSmallDataset() {

		for(int i = 0; i < doubleSmallDatasetSize; i++) {
			String a = this.getDataElement();
			this.doubleSmallDataset.add(a);
			this.sortedDoubleSmallDataset.add(a);
		}

		Collections.sort(this.sortedDoubleSmallDataset);
		System.out.println("Double Small Dataset Size : "+this.doubleSmallDataset.size());
	}

	// Generates the large dataset (100000 elements) of random elements in random order
	public void generateDoubleMediumDataset() {

		for(int i = 0; i < doubleMediumDatasetSize; i++) {
			String a = this.getDataElement();
			this.doubleMediumDataset.add(a);
			this.sortedDoubleMediumDataset.add(a);
		}

		Collections.sort(this.sortedDoubleMediumDataset);
		System.out.println("Double medium Dataset Size : "+this.doubleMediumDataset.size());
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

	public void testArraySetAdd(ArrayMultiset arrayMultiset, ArrayList<String> dataset, String setSizeStr )
	{	
		long startTime = 0, endTime = 0;
		double elapsedTime = 0;

		// Testing best case - Adding duplicate element. First one.
		// Expecting no shifts
		String firstElement = arrayMultiset.getArray()[0].getElement();

		startTime = System.nanoTime();		
		arrayMultiset.add(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",ArrayAdd," + setSizeStr + ",BestCase," + elapsedTime);


		// Worst case 1 - Element is not present in the list

		startTime = System.nanoTime();		
		String uniqueElement = "YogeshShriramNotPresentInSet";
		arrayMultiset.add(uniqueElement);

		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");

		System.out.println(",ArrayAdd," + setSizeStr + ",Worst Case," + elapsedTime);


		// Adding random duplicates
		
		int datasetSize = dataset.size();
		for(int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			arrayMultiset.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",ArrayAdd," + setSizeStr + ",Random Case1," + elapsedTime);
		}


		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			arrayMultiset.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",ArrayAdd," + setSizeStr + ",Random Case2," + elapsedTime);

		}
	}


	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random
	 *  Operation : Remove 
	 */
	public void testArraySetRemove(ArrayMultiset arrayMultiset, ArrayList<String> dataset, int dataSetSize, String setSizeStr) {

		long startTime = 0, endTime = 0;
		double elapsedTime = 0;

		// Testing best case - 0th element has more than 1 instance count - 
		String firstElement = arrayMultiset.getArray()[0].getElement();
		arrayMultiset.add(firstElement);					// Duplicating the element
		
		startTime = System.nanoTime();
		arrayMultiset.removeOne(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,ArrayRemove," + setSizeStr + ",Best Case," + elapsedTime);
		
		
		
		// Testing worst case - Element to be removed is first element with 1 occurance only. Expecting entire arrayShift
		arrayMultiset.getArray()[0].setInstanceCount(1);
		firstElement = arrayMultiset.getArray()[0].getElement();

		startTime = System.nanoTime();		
		arrayMultiset.removeOne(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,ArrayRemove," + setSizeStr + ",Worst Case1," + elapsedTime);


		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";

		startTime = System.nanoTime();		
		arrayMultiset.removeOne(largestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",ArrayRemove," + setSizeStr + ",Worst Case2," + elapsedTime);

		// Worst / Average case - In between elements
		// Random cases. Elements could be any where in the list
		for (int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			arrayMultiset.removeOne( dataset.get( this.randomUtil.nextInt( dataSetSize - 10 ) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",ArrayRemove," + setSizeStr + ",Average case," + elapsedTime);

		}
	}


	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random
	 *  Operation : Print
	 */
	public void testArraySetPrint(ArrayMultiset arrayMultiset, ArrayList<String> dataset, String setSizeStr) {

		long startTime = 0, endTime = 0;
		double elapsedTime = 0;


		// Single occurance - Best case for array

		startTime = System.nanoTime();		
		arrayMultiset.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,ArrayPrint," + setSizeStr + ",Best case," + elapsedTime);

		
		// Worst case - Random duplicate elements are present
		int iterations = dataset.size();
		for(int a = 0 ; a < iterations; a++) {
			arrayMultiset.add( dataset.get( this.randomUtil.nextInt(iterations - 3) ) );
		}


		startTime = System.nanoTime();		
		arrayMultiset.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",ArrayPrint," + setSizeStr + ",Worst case," + elapsedTime);

	}


	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random
	 *  Operation : Print
	 */
	public void testArraySetIntersect(ArrayMultiset first, ArrayMultiset second, int initialSetSize, int largeSetSize, String setSizeStr) {

		long startTime = 0, endTime = 0;
		double elapsedTime = 0;

		
		// Worst case - Common elements in same order
		startTime = System.nanoTime();
		first.intersect(second);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,ArrayIntersect," + setSizeStr + ",Best case1," + elapsedTime);

		
		// Best case - no elements are overlapping - Loop will run n1xn2 times - No add operation required
		second = new ArrayMultiset();
		for(int a = 0; a < initialSetSize; a++) {
			second.add( this.getDataElement() );
		}

		startTime = System.nanoTime();		
		first.intersect(second);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",ArrayIntersect," + setSizeStr + ",Worst Case1," + elapsedTime);

		
		// Worse case - 1 set is relatively large and another relatively small
		for(int a = 0; a < largeSetSize; a++) {
			second.add( this.getDataElement() );
		}

		startTime = System.nanoTime();		
		first.intersect(second);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",ArrayIntersect," + setSizeStr + ",Worst case2," + elapsedTime);
		
	}


	/*
	 *  DataStructure : OrderedLinkedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Alphabetically ascending order of elements
	 *  Operation : Print
	 */
	public void testOrderedListAdd(OrderedLinkedListMultiset orderedList, ArrayList<String> dataset, String setSizeStr) {

		long startTime = 0;
		double elapsedTime = 0;

		// Testing best case - Element to be added is equal to the head element
		String firstElement = orderedList.getHead().getVal();	

		startTime = System.nanoTime();		
		orderedList.add(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,OrderListAdd," + setSizeStr + ",Best case1," + elapsedTime);


		// Testing best case - Element to be added is equal to the head element
		String smallestElement = "a";

		startTime = System.nanoTime();		
		orderedList.add(smallestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",OrderListAdd," + setSizeStr + ",Best case2," + elapsedTime);

		// Worst case - Adding an element which is not present in the string hence will be added at last
		// Expecting 1 complete traversal.
		String uniqueElement = "YogeshShriram";

		startTime = System.nanoTime();		
		orderedList.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",OrderListAdd," + setSizeStr + ",Worst Case1," + elapsedTime);

		// Adding random duplicates
		int datasetSize = dataset.size();
		for(int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			orderedList.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",OrderListAdd," + setSizeStr + ",Worst Case2," + elapsedTime);

		}

		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			orderedList.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",OrderListAdd," + setSizeStr + ",Average Case," + elapsedTime);

		}
	}

	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Alphabetically ascending order of elements in the list 
	 *  Operation : Remove
	 */

	public void testOrderedListRemove(OrderedLinkedListMultiset orderedList, ArrayList<String> sortedDataSet, int datasetSize, String setSizeStr)
	{
		long startTime = 0;
		double elapsedTime = 0;

		// Testing best case - Element to be removed is equal to the head element
		String firstElement = orderedList.getHead().getVal();

		startTime = System.nanoTime();		
		orderedList.removeOne(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,OrderListRemove," + setSizeStr + ",Best Case," + elapsedTime);


		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";

		startTime = System.nanoTime();		
		orderedList.removeOne(largestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",OrderListRemove," + setSizeStr + ",Worst Case," + elapsedTime);



		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		for (int i = 0; i < 5; i++) {
			startTime = System.nanoTime();		
			orderedList.removeOne( sortedDataSet.get( this.randomUtil.nextInt( datasetSize - 10 ) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",OrderListRemove," + setSizeStr + ",Average Case," + elapsedTime);

		}
	}


	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Alphabetically ascending order of elements in the list 
	 *  Operation : Intersection
	 */

	public void testOrderedListIntersect(OrderedLinkedListMultiset firstOrderedList, OrderedLinkedListMultiset secondOrderedList, int initialSetSize, int LargeDatasetSize, String setSizeStr)
	{
		long startTime = 0;
		double elapsedTime = 0;


		// Testing worst case - Both multiset contains the overlapping elements. running time is n1 as n1 == n2
		startTime = System.nanoTime();		
		firstOrderedList.intersect(secondOrderedList);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,OrderListIntersect," + setSizeStr + ",Worst Case," + elapsedTime);



		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times - No add operations
		secondOrderedList = new OrderedLinkedListMultiset();
		for(int a = 0; a < initialSetSize; a++) {
			secondOrderedList.add( this.getDataElement() );
		}

		startTime = System.nanoTime();		
		firstOrderedList.intersect(secondOrderedList);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",OrderListIntersect," + setSizeStr + ",Best Case1," + elapsedTime);


		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < LargeDatasetSize; a++) {

			secondOrderedList.add( this.getDataElement() );
		}

		startTime = System.nanoTime();		
		firstOrderedList.intersect(secondOrderedList);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",OrderListIntersect," + setSizeStr + ",Best Case2," + elapsedTime);

	}


	/*
	 *  DataStructure : Ordered Linked List
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Ascending order
	 *  Operation : Print - Ordered by instance count
	 */
	public void testOrderedListPrint(OrderedLinkedListMultiset orderedList, ArrayList<String> dataset, String setSizeStr)
	{
		long startTime = 0;
		double elapsedTime = 0;


		// Best case : All unique elements - Bubble sort can be stopped in first iteration
		startTime = System.nanoTime();		
		orderedList.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,OrderListPrint," + setSizeStr + ",Best Case," + elapsedTime);



		// Ordered List contains duplicate elements
		// Higher complexity is expected as swapping would be required for sorting elements based on occurance count for print operations
		int iterations = dataset.size();
		for(int a = 0 ; a < iterations; a++) {
			orderedList.add( dataset.get( this.randomUtil.nextInt(iterations - 3) ) );
		}

		startTime = System.nanoTime();		
		orderedList.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",OrderListPrint," + setSizeStr + ",Worst Case," + elapsedTime);
	}

	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small | Medium | Large
	 *  Distribution : BST Behaviour
	 *  Operation : Add
	 */
	public void testTreeSetAdd(BstMultiset bstSet, ArrayList<String> dataset,String setSizeStr) 
	{
		long startTime = 0;
		double elapsedTime = 0;


		//  Best case - Element to be added is equal to the root element
		String rootVal = bstSet.getRoot().getVal();

		startTime = System.nanoTime();		
		bstSet.add(rootVal);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,BST Add(Normal Tree)," + setSizeStr + ",Best Case," + elapsedTime);


		// Worst case - Adding an element which is not present in the string
		String uniqueElement = "YogeshSriram";
		startTime = System.nanoTime();		
		bstSet.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",BST Add(Normal Tree)," + setSizeStr + ",Worst Case," + elapsedTime);


		// Adding random duplicates
		int datasetSize = dataset.size();
		for(int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			bstSet.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",BST Add(Normal Tree)," + setSizeStr + ",Average Case," + elapsedTime);

		}

		// Usual cases - In between elements - Random insertions
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			bstSet.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",BST Add(Normal Tree)," + setSizeStr + ",Average Case," + elapsedTime);

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

		startTime = System.nanoTime();		
		bstSet.add(rootVal);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",BST Add(Left Skewed Tree)," + setSizeStr + ",Best Case," + elapsedTime);



		// Worst case - Adding an element which is not present in the string
		startTime = System.nanoTime();		
		bstSet.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",BST Add(Left Skewed Tree)," + setSizeStr + ",Worst Case," + elapsedTime);


		// Adding random duplicates
		for(int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			bstSet.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",BST Add(Left Skewed Tree)," + setSizeStr + ",Average Case1," + elapsedTime);

		}

		// Usually cases - In between elements - Random insertions
		for (int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			bstSet.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",BST Add(Left Skewed Tree)," + setSizeStr + ",Average Case2," + elapsedTime);

		}

		/*
		 * Left Skewed Tree Ends Here
		 */


		/* Right Skewed Tree
		 * Elements have been added in increasing order.
		 */
		Collections.sort(dataset);
		for(String ele : dataset) {

			bstSet.add(ele);
		}

		//  Best case - Element to be added is equal to the root element
		rootVal = bstSet.getRoot().getVal();

		startTime = System.nanoTime();		
		bstSet.add(rootVal);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",BST Add(Right Skewed Tree)," + setSizeStr + ",Best Case," + elapsedTime);


		// Worst case - Adding an element which is not present in the string
		startTime = System.nanoTime();		
		bstSet.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",BST Add(Right Skewed Tree)," + setSizeStr + ",Worst Case," + elapsedTime);


		// Adding random duplicates
		for(int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			bstSet.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",BST Add(Right Skewed Tree)," + setSizeStr + ",Averegae Case1," + elapsedTime);

		}

		// Usually cases - In between elements - Random insertions
		for (int i = 0; i < 5; i++) {
			startTime = System.nanoTime();		
			bstSet.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",BST Add(Right Skewed Tree)," + setSizeStr + ",Average Case2," + elapsedTime);

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

	public void testTreeSetRemove(BstMultiset bstSet, ArrayList<String> dataset, int datasetSize, String setSizeStr)
	{

		long startTime = 0;
		double elapsedTime = 0;

		// Testing best case - Element to be removed is equal to the root element - more than 1 instance count
		String rootVal = bstSet.getRoot().getVal();
		bstSet.add(rootVal); // instance count becomes 2

		startTime = System.nanoTime();		
		bstSet.removeOne(rootVal);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,BST Remove," + setSizeStr + ",Best Case," + elapsedTime);


		// Average case - In between elements
		// Random cases. Elements could be any where in the tree
		for (int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			bstSet.removeOne( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",BST Remove," + setSizeStr + ",Average Case," + elapsedTime);
		}

	}


	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small | Medium | Large
	 *  Distribution : BST Behaviour
	 *  Operation : Intersection
	 */

	public void testTreeSetIntersect(BstMultiset firstTree, BstMultiset secondTree, int initialDatasetSize, int largeDatasetSize, String setSizeStr)
	{
		long startTime = 0;
		double elapsedTime = 0;

		// Testing worst case - Both multiset contains the overlapping elements
		startTime = System.nanoTime();		
		firstTree.intersect(secondTree);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,BST Intersect," + setSizeStr + ",Worst Case," + elapsedTime);


		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondTree = new BstMultiset();
		for(int a = 0; a < initialDatasetSize; a++) {
			secondTree.add( this.getDataElement() );
		}

		startTime = System.nanoTime();		
		firstTree.intersect(secondTree);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",BST Intersect," + setSizeStr + ",Best Case1," + elapsedTime);


		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < largeDatasetSize; a++) {
			secondTree.add( this.getDataElement() );
		}

		startTime = System.nanoTime();		
		firstTree.intersect(secondTree);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",BST Intersect," + setSizeStr + ",Best Case2," + elapsedTime);

	}



	/*
	 *  DataStructure : Tree Set
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random - As per BST properties
	 *  Operation : Print - Ordered by instance count
	 */
	public void testTreeSetPrint(BstMultiset bstTree, ArrayList<String> dataset, String setSizeStr)
	{
		long startTime = 0;
		double elapsedTime = 0;

		// Best case - Singly occurance - Loop can be terminated after 1st iterations
		startTime = System.nanoTime();		
		bstTree.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,BST Print," + setSizeStr + ",Best Case," + elapsedTime);

		// Worst case - Tree contains duplicate elements
		int iterations = dataset.size();
		for(int a = 0 ; a < iterations / 2; a++) {
			bstTree.add( dataset.get( this.randomUtil.nextInt(iterations - 3) ) );
		}

		startTime = System.nanoTime();		
		bstTree.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",BST Print," + setSizeStr + ",Worst Case," + elapsedTime);

	}


	/*
	 *  DataStructure : Dual OrderedList List
	 *  Dataset : Small | Medium | Large
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Add
	 */

	public void testDualOrderedListAdd(DualLinkedListMultiset dualList, ArrayList<String> dataset, String setSizeStr)
	{
		long startTime = 0;
		double elapsedTime = 0;

		// Testing best case - Element to be added is equal to the head element
		String firstElement = dualList.getHead1().getVal();

		startTime = System.nanoTime();		
		dualList.add(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,DualOrderedLL Add," + setSizeStr + ",Best Case1," + elapsedTime);


		// Testing best case - Element to be added is less than head element.
		String smallestElement = "a";

		startTime = System.nanoTime();		
		dualList.add(smallestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",DualOrderedLL Add," + setSizeStr + ",Best Case2," + elapsedTime);


		// Adding an element which is not present in the string
		String uniqueElement = "YogeshShriramNotPresentInList";

		startTime = System.nanoTime();		
		dualList.add(uniqueElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",DualOrderedLL Add," + setSizeStr + ",Worst Case1," + elapsedTime);

		// Practical case - Adding random duplicates
		int datasetSize = dataset.size();
		for (int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			dualList.add( dataset.get( this.randomUtil.nextInt( datasetSize - 3) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",DualOrderedLL Add," + setSizeStr + ",Worst Case2," + elapsedTime);

		}

		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			dualList.add( randomStrs[i] );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",DualOrderedLL Add," + setSizeStr + ",Average Case," + elapsedTime);

		}

	}


	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Remove
	 */

	public void testDualOrderedListRemove(DualLinkedListMultiset dualList, ArrayList<String> dataset, int datasetSize, String setSizeStr)
	{
		long startTime = 0;
		double elapsedTime = 0;

		// Testing best case - Element to be removed is equal to the head element
		String firstElement = dualList.getHead1().getVal();

		startTime = System.nanoTime();		
		dualList.removeOne(firstElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,DualOrderedLL Remove," + setSizeStr + ",Best Case," + elapsedTime);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";

		startTime = System.nanoTime();		
		dualList.removeOne(largestElement);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",DualOrderedLL Remove," + setSizeStr + ",Worst Case," + elapsedTime);


		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		for (int i = 0; i < 5; i++) {

			startTime = System.nanoTime();		
			dualList.removeOne( dataset.get(  this.randomUtil.nextInt( datasetSize - 10 ) ) );
			elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
			System.out.println(",DualOrderedLL Remove," + setSizeStr + ",Average Case," + elapsedTime);

		}
	}



	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Small
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Intersection
	 */

	public void testDualOrderedListIntersect(DualLinkedListMultiset FirstDualList, DualLinkedListMultiset SecDualList, int initialDatasetSize, int LargeDatasetSize, String setSizeStr)
	{

		long startTime = 0;
		double elapsedTime = 0;


		// Testing Worst case - Both multiset contains the overlapping elements
		startTime = System.nanoTime();		
		FirstDualList.intersect(SecDualList);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,DualOrderedLL Intersect," + setSizeStr + ",Worst Case," + elapsedTime);


		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		SecDualList = new DualLinkedListMultiset();
		for(int a = 0; a < initialDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}

		startTime = System.nanoTime();		
		FirstDualList.intersect(SecDualList);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",DualOrderedLL Intersect," + setSizeStr + ",Best Case1," + elapsedTime);


		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < LargeDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}

		startTime = System.nanoTime();		
		FirstDualList.intersect(SecDualList);
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",DualOrderedLL Intersect," + setSizeStr + ",Best Case2," + elapsedTime);

	}


	/*
	 *  DataStructure : Dual Ordered Linked List
	 *  Dataset : Small | Medium | Large
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Print - Ordered by instance count
	 */
	public void testDualOrderedListPrint(DualLinkedListMultiset dualList, ArrayList<String> dataset, String setSizeStr)
	{
		long startTime = 0;
		double elapsedTime = 0;

		// Testing single occurance complexity
		startTime = System.nanoTime();		
		dualList.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println("\n,DualOrderedLL Print," + setSizeStr + ",Best Case," + elapsedTime);


		// Populating duplicates - Sorting is not required for dual ordered list
		int datasetSize = dataset.size();
		for(int i = 0; i < datasetSize/2; i++) {
			dualList.add( dataset.get( this.randomUtil.nextInt(datasetSize - 5) ) );
		}

		startTime = System.nanoTime();		
		dualList.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",DualOrderedLL Print," + setSizeStr + ",Worst Case," + elapsedTime);
		
		
		startTime = System.nanoTime();
		dualList.print();
		elapsedTime = TimeUtil.elapsedTime(startTime, System.nanoTime(), "seconds");
		System.out.println(",DualOrderedLL Print," + setSizeStr + ",Average Case," + elapsedTime);

	}


	/*
	 *	Runs the testing for the addition, removeOne, intersection, print functions on Array Implementations of Multiset
	 *	The respective functions contains the testing code the best and worst cases
	 *	The test runs on each dataset size 
	 */
	public void run_arraySet() {

		/*
		 * long startTime = 0, endTime = 0, elapsedTime = 0, elapsedTimeInMS; double
		 * elapsedTimeInS;
		 */

		// Initialising the varied sized Array Multisets
		ArrayMultiset smallArrSet = new ArrayMultiset();
		ArrayMultiset mediumArrSet = new ArrayMultiset();
		ArrayMultiset largeArrSet = new ArrayMultiset();
		ArrayMultiset doubleSmallArrSet = new ArrayMultiset();
		ArrayMultiset doubleMediumArrSet = new ArrayMultiset();

		for (String ele : this.smallDataset) {
			smallArrSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumArrSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeArrSet.add(ele);
		}

		for (String ele : this.doubleSmallDataset) {
			doubleSmallArrSet.add(ele);
		}

		for (String ele : this.doubleMediumDataset) {
			doubleMediumArrSet.add(ele);
		}

		// Finished initialising and populating the multisets


		// Testing add scenario on each datasize - Each function include best and worst cases

		this.testArraySetAdd(smallArrSet, this.smallDataset, "small");

		this.testArraySetAdd(mediumArrSet, this.mediumDataset, "medium");

		this.testArraySetAdd(largeArrSet, this.largeDataset, "large");

		// New Datasets
		this.testArraySetAdd(doubleSmallArrSet, this.doubleSmallDataset, "Double Small");

		this.testArraySetAdd(doubleMediumArrSet, this.doubleMediumDataset, "Double Medium");


		// Testing remove scenario on each datasize - Each function include best and worst cases

		this.testArraySetRemove(smallArrSet, this.smallDataset, smallDatasetSize, "small");

		this.testArraySetRemove(mediumArrSet, this.mediumDataset, mediumDatasetSize, "medium");

		this.testArraySetRemove(largeArrSet, this.largeDataset, largeDatasetSize, "large");

		// New Datasets
		this.testArraySetRemove(doubleSmallArrSet, this.doubleSmallDataset, doubleSmallDatasetSize, "Double Small");

		this.testArraySetRemove(doubleMediumArrSet, this.doubleMediumDataset, doubleMediumDatasetSize, "Double Medium");


		// Testing print scenario on each datasize - Each function include best and worst cases

		this.testArraySetPrint(smallArrSet, this.smallDataset, "small");

		this.testArraySetPrint(mediumArrSet, this.mediumDataset, "medium");

		this.testArraySetPrint(largeArrSet, this.largeDataset, "large");

		// New Datasets
		this.testArraySetPrint(doubleSmallArrSet, this.doubleSmallDataset, "Double Small");

		this.testArraySetPrint(doubleMediumArrSet, this.doubleMediumDataset, "Double Medium");



		// Refrehing the datasets to for the interesection cases - the above operations would affect the multisets
		smallArrSet = new ArrayMultiset();
		ArrayMultiset secSmallArrSet = new ArrayMultiset();

		mediumArrSet = new ArrayMultiset();
		ArrayMultiset secMediumArrSet = new ArrayMultiset();

		largeArrSet = new ArrayMultiset();
		ArrayMultiset seclLargeArrSet = new ArrayMultiset();

		doubleSmallArrSet = new ArrayMultiset();
		ArrayMultiset secDoubleSmallArrSet = new ArrayMultiset();

		doubleMediumArrSet = new ArrayMultiset();
		ArrayMultiset secDoubleMediumArrSet = new ArrayMultiset();

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

		for (String ele : this.doubleSmallDataset) {
			doubleSmallArrSet.add(ele);
			secDoubleSmallArrSet.add(ele);
		}

		for (String ele : this.doubleMediumDataset) {
			doubleMediumArrSet.add(ele);
			secDoubleMediumArrSet.add(ele);
		}

		// Testing the intersect scenario on each datasize

		this.testArraySetIntersect(smallArrSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize, "small");

		this.testArraySetIntersect(mediumArrSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize, "medium");

		this.testArraySetIntersect(largeArrSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize, "large");

		this.testArraySetIntersect(doubleSmallArrSet, secDoubleSmallArrSet, doubleSmallDatasetSize, mediumDatasetSize, "Double Small");

		this.testArraySetIntersect(doubleMediumArrSet, secDoubleMediumArrSet, doubleMediumDatasetSize, largeDatasetSize, "Double Medium");

	}


	/*
	 *	Runs the testing for the addition, removeOne, intersection, print functions for orderedListSet
	 *	The respective functions contains the testing code the best and worst cases
	 *	The test runs on each dataset size 
	 */
	public void run_orderedListSet() {

		// Initialising the varied sized OrderedList Multisets
		OrderedLinkedListMultiset smallSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset mediumSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset largeSet = new OrderedLinkedListMultiset();

		OrderedLinkedListMultiset doubleSmallSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset doubleMediumSet = new OrderedLinkedListMultiset();

		for (String ele : this.smallDataset) {
			smallSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeSet.add(ele);
		}

		for (String ele : this.doubleSmallDataset) {
			doubleSmallSet.add(ele);
		}

		for (String ele : this.doubleMediumDataset) {
			doubleMediumSet.add(ele);
		}
		// Finished initialising and populating the multisets

		// Testing add scenario on each datasize - Each function include best and worst cases


		this.testOrderedListAdd(smallSet, this.smallDataset, "small");

		this.testOrderedListAdd(mediumSet, this.mediumDataset, "medium");

		this.testOrderedListAdd(largeSet, this.largeDataset, "large");


		// New Datasets
		this.testOrderedListAdd(doubleSmallSet, this.doubleSmallDataset, "Double Small");

		this.testOrderedListAdd(doubleMediumSet, this.doubleMediumDataset, "Double Medium");


		// Testing remove scenario on each datasize - Each function include best and worst cases

		this.testOrderedListRemove(smallSet, this.sortedSmallDataset, smallDatasetSize, "small");

		this.testOrderedListRemove(mediumSet, this.sortedMediumDataset, mediumDatasetSize, "medium");

		this.testOrderedListRemove(largeSet, this.sortedLargeDataset, largeDatasetSize, "large");


		// New Datasets
		this.testOrderedListRemove(doubleSmallSet, this.doubleSmallDataset, doubleSmallDatasetSize, "Double Small");

		this.testOrderedListRemove(doubleMediumSet, this.doubleMediumDataset, doubleMediumDatasetSize, "Double Medium");



		// Testing Print scenario on each datasize - Each function include best and worst cases

		this.testOrderedListPrint(smallSet, this.smallDataset, "small");

		this.testOrderedListPrint(mediumSet, this.mediumDataset, "medium");

		this.testOrderedListPrint(largeSet, this.largeDataset, "large");


		// New Datasets
		this.testOrderedListPrint(doubleSmallSet, this.doubleSmallDataset, "Double Small");

		this.testOrderedListPrint(doubleMediumSet, this.doubleMediumDataset, "Double Medium");



		// Refrehing the datasets to for the interesection cases - the above operations would affect the multisets
		smallSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset secSmallArrSet = new OrderedLinkedListMultiset();

		mediumSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset secMediumArrSet = new OrderedLinkedListMultiset();

		largeSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset seclLargeArrSet = new OrderedLinkedListMultiset();

		doubleSmallSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset secDoubleSmallSet = new OrderedLinkedListMultiset();

		doubleMediumSet = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset secDoubleMediumSet = new OrderedLinkedListMultiset();

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

		for (String ele : this.doubleSmallDataset) {
			doubleSmallSet.add(ele);
			secDoubleSmallSet.add(ele);
		}

		for (String ele : this.doubleMediumDataset) {
			doubleMediumSet.add(ele);
			secDoubleMediumSet.add(ele);
		}

		// Testing the intersect scenario on each datasize

		this.testOrderedListIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize, "small");

		this.testOrderedListIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize, "medium");

		this.testOrderedListIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize, "large");

		this.testOrderedListIntersect(doubleSmallSet, secDoubleSmallSet, doubleSmallDatasetSize, mediumDatasetSize, "Double Small");

		this.testOrderedListIntersect(doubleMediumSet, secDoubleMediumSet, doubleMediumDatasetSize, largeDatasetSize, "Double Medium");

	}


	/*
	 *	Runs the testing for the addition, removeOne, intersection, print functions for the bstTreeSet
	 *	The respective functions contains the testing code the best and worst cases
	 *	The test runs on each dataset size
	 */
	public void run_bstTreeSet() {

		// Initialising the varied sized BST Tree Multisets
		BstMultiset smallSet = new BstMultiset();
		BstMultiset mediumSet = new BstMultiset();
		BstMultiset largeSet = new BstMultiset();

		BstMultiset doubleSmallSet = new BstMultiset();
		BstMultiset doubleMediumSet = new BstMultiset();


		for (String ele : this.smallDataset) {
			smallSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeSet.add(ele);
		}

		for (String ele : this.doubleSmallDataset) {
			doubleSmallSet.add(ele);
		}

		for (String ele : this.doubleMediumDataset) {
			doubleMediumSet.add(ele);
		}
		// Finished initialising and populating the multisets


		// Testing add scenario on each datasize - Each function include best and worst cases

		this.testTreeSetAdd(smallSet, this.sortedSmallDataset, "small");

		this.testTreeSetAdd(mediumSet, this.sortedMediumDataset, "medium");

		this.testTreeSetAdd(largeSet, this.sortedLargeDataset, "large");

		// New Datasets
		this.testTreeSetAdd(doubleSmallSet, this.doubleSmallDataset, "Double Small");

		this.testTreeSetAdd(doubleMediumSet, this.doubleMediumDataset, "Double Medium");


		// Testing remove scenario on each datasize - Each function include best and worst cases

		this.testTreeSetRemove(smallSet, this.smallDataset, smallDatasetSize, "small");

		this.testTreeSetRemove(mediumSet, this.mediumDataset, mediumDatasetSize, "medium");

		this.testTreeSetRemove(largeSet, this.largeDataset, largeDatasetSize, "large");

		// New Datasets
		this.testTreeSetRemove(doubleSmallSet, this.doubleSmallDataset, doubleSmallDatasetSize, "Double Small");

		this.testTreeSetRemove(doubleMediumSet, this.doubleMediumDataset, doubleMediumDatasetSize, "Double Medium");




		// Testing Print scenario on each datasize - Each function include best and worst cases

		this.testTreeSetPrint(smallSet, this.smallDataset, "small");

		this.testTreeSetPrint(mediumSet, this.mediumDataset, "medium");

		this.testTreeSetPrint(largeSet, this.largeDataset, "large");		

		//New Datasets
		this.testTreeSetPrint(doubleSmallSet, this.doubleSmallDataset, "Double Small");

		this.testTreeSetPrint(doubleMediumSet, this.doubleMediumDataset, "Double Medium");

		// Refrehing the datasets to for the interesection cases - the above operations would affect the multisets
		smallSet = new BstMultiset();
		BstMultiset secSmallArrSet = new BstMultiset();

		mediumSet = new BstMultiset();
		BstMultiset secMediumArrSet = new BstMultiset();

		largeSet = new BstMultiset();
		BstMultiset seclLargeArrSet = new BstMultiset();

		doubleSmallSet = new BstMultiset();
		BstMultiset secDoubleSmallSet = new BstMultiset();

		doubleMediumSet = new BstMultiset();
		BstMultiset secDoubleMediumSet = new BstMultiset();

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

		for (String ele : this.doubleSmallDataset) {
			doubleSmallSet.add(ele);
			secDoubleSmallSet.add(ele);
		}

		for (String ele : this.doubleMediumDataset) {
			doubleMediumSet.add(ele);
			secDoubleMediumSet.add(ele);
		}

		// Testing the intersect scenario on each datasize

		this.testTreeSetIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize, "small");

		this.testTreeSetIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize, "medium");

		this.testTreeSetIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize, "large");

		// New Datasets
		this.testTreeSetIntersect(doubleSmallSet, secDoubleSmallSet, doubleSmallDatasetSize, mediumDatasetSize, "Double Small");

		this.testTreeSetIntersect(doubleMediumSet, secDoubleMediumSet, doubleMediumDatasetSize, largeDatasetSize, "Double Medium");

	}


	/*
	 *	Runs the testing for the addition, removeOne, intersection, print functions
	 *	The respective functions contains the testing code the best and worst cases
	 *	The test runs on each dataset size 
	 */
	public void run_dualSet() {

		// Initialising the varied sized DualLinkedList Multisets
		DualLinkedListMultiset smallSet = new DualLinkedListMultiset();
		DualLinkedListMultiset mediumSet = new DualLinkedListMultiset();
		DualLinkedListMultiset largeSet = new DualLinkedListMultiset();

		DualLinkedListMultiset doubleSmallSet = new DualLinkedListMultiset();
		DualLinkedListMultiset doubleMediumSet = new DualLinkedListMultiset();


		for (String ele : this.smallDataset) {
			smallSet.add(ele);
		}

		for (String ele : this.mediumDataset) {
			mediumSet.add(ele);
		}

		for (String ele : this.largeDataset) {
			largeSet.add(ele);
		}

		for (String ele : this.doubleSmallDataset) {
			doubleSmallSet.add(ele);
		}

		for (String ele : this.doubleMediumDataset) {
			doubleMediumSet.add(ele);
		}
		// Finished initialising and populating the multisets

		// Testing add scenario on each datasize - Each function include best and worst cases


		this.testDualOrderedListAdd(smallSet, this.sortedSmallDataset, "small");

		this.testDualOrderedListAdd(mediumSet, this.sortedMediumDataset, "medium");

		this.testDualOrderedListAdd(largeSet, this.sortedLargeDataset, "large");

		// New Datasets
		this.testDualOrderedListAdd(doubleSmallSet, this.doubleSmallDataset, "Double Small");

		this.testDualOrderedListAdd(doubleMediumSet, this.doubleMediumDataset, "Double Medium");


		// Testing remove scenario on each datasize - Each function include best and worst cases

		this.testDualOrderedListRemove(smallSet, this.smallDataset, smallDatasetSize, "small");

		this.testDualOrderedListRemove(mediumSet, this.mediumDataset, mediumDatasetSize, "medium");

		this.testDualOrderedListRemove(largeSet, this.largeDataset, largeDatasetSize, "large");		

		// New Datasets
		this.testDualOrderedListRemove(doubleSmallSet, this.doubleSmallDataset, doubleSmallDatasetSize, "Double Small");

		this.testDualOrderedListRemove(doubleMediumSet, this.doubleMediumDataset, doubleMediumDatasetSize, "Double Medium");

		// Testing Print scenario on each datasize - Each function include best and worst cases

		this.testDualOrderedListPrint(smallSet, this.smallDataset, "small");

		this.testDualOrderedListPrint(mediumSet, this.mediumDataset, "medium");

		this.testDualOrderedListPrint(largeSet, this.largeDataset, "large");

		// New Datasets
		this.testDualOrderedListPrint(doubleSmallSet, this.doubleSmallDataset, "Double Small");

		this.testDualOrderedListPrint(doubleMediumSet, this.doubleMediumDataset, "Double Medium");


		// Refrehing the datasets to for the interesection cases - the above operations would affect the multisets
		smallSet = new DualLinkedListMultiset();
		DualLinkedListMultiset secSmallArrSet = new DualLinkedListMultiset();
		
		mediumSet = new DualLinkedListMultiset();
		DualLinkedListMultiset secMediumArrSet = new DualLinkedListMultiset();
		
		largeSet = new DualLinkedListMultiset();
		DualLinkedListMultiset seclLargeArrSet = new DualLinkedListMultiset();
		
		doubleSmallSet = new DualLinkedListMultiset();
		DualLinkedListMultiset secDoubleSmallSet = new DualLinkedListMultiset();
		
		doubleMediumSet = new DualLinkedListMultiset();
		DualLinkedListMultiset secDoubleMediumSet = new DualLinkedListMultiset();

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

		for (String ele : this.doubleSmallDataset) {
			doubleSmallSet.add(ele);
			secDoubleSmallSet.add(ele);
		}

		for (String ele : this.doubleMediumDataset) {
			doubleMediumSet.add(ele);
			secDoubleMediumSet.add(ele);
		}
		// Testing the intersect scenario on each datasize

		this.testDualOrderedListIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize, "small");

		this.testDualOrderedListIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize, "medium");

		this.testDualOrderedListIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize, "large");

		this.testDualOrderedListIntersect(doubleSmallSet, secDoubleSmallSet, doubleSmallDatasetSize, mediumDatasetSize, "Double Small");
		
		this.testDualOrderedListIntersect(doubleMediumSet, secDoubleMediumSet, doubleMediumDatasetSize, largeDatasetSize, "Double Medium");
		
	}


	/*
	 * Calls the testing methods on arrays, orderedList, tree and DualOrderedList
	 */
	public void run() {

		run_arraySet();

		run_orderedListSet();

		run_bstTreeSet();

		run_dualSet();

	}


	/*
	 * Generats the small, medium, and large datasets with the defined sizes
	 * Initiates the testing
	 */
	public static void main(String[] args) {

		DataGenerator driver = new DataGenerator();

		// Prepare datasets
		driver.generateSmallDataset();
		driver.generateMediumDataset();
		driver.generateLargeDataset();

		// Prepare additional 2 datasets
		driver.generateDoubleSmallDataset();
		driver.generateDoubleMediumDataset();

		// Initiate testing
		driver.run();
	}

	
	// Accessor methods starts here

	public ArrayList<String> getSmallDataset() {
		return smallDataset;
	}


	public void setSmallDataset(ArrayList<String> smallDataset) {
		this.smallDataset = smallDataset;
	}


	public ArrayList<String> getMediumDataset() {
		return mediumDataset;
	}


	public void setMediumDataset(ArrayList<String> mediumDataset) {
		this.mediumDataset = mediumDataset;
	}


	public ArrayList<String> getLargeDataset() {
		return largeDataset;
	}


	public void setLargeDataset(ArrayList<String> largeDataset) {
		this.largeDataset = largeDataset;
	}


	public ArrayList<String> getDoubleSmallDataset() {
		return doubleSmallDataset;
	}


	public void setDoubleSmallDataset(ArrayList<String> doubleSmallDataset) {
		this.doubleSmallDataset = doubleSmallDataset;
	}


	public ArrayList<String> getDoubleMediumDataset() {
		return doubleMediumDataset;
	}


	public void setDoubleMediumDataset(ArrayList<String> doubleMediumDataset) {
		this.doubleMediumDataset = doubleMediumDataset;
	}


	public ArrayList<String> getSortedSmallDataset() {
		return sortedSmallDataset;
	}


	public void setSortedSmallDataset(ArrayList<String> sortedSmallDataset) {
		this.sortedSmallDataset = sortedSmallDataset;
	}


	public ArrayList<String> getSortedMediumDataset() {
		return sortedMediumDataset;
	}


	public void setSortedMediumDataset(ArrayList<String> sortedMediumDataset) {
		this.sortedMediumDataset = sortedMediumDataset;
	}


	public ArrayList<String> getSortedLargeDataset() {
		return sortedLargeDataset;
	}


	public void setSortedLargeDataset(ArrayList<String> sortedLargeDataset) {
		this.sortedLargeDataset = sortedLargeDataset;
	}


	public ArrayList<String> getSortedDoubleSmallDataset() {
		return sortedDoubleSmallDataset;
	}


	public void setSortedDoubleSmallDataset(ArrayList<String> sortedDoubleSmallDataset) {
		this.sortedDoubleSmallDataset = sortedDoubleSmallDataset;
	}


	public ArrayList<String> getSortedDoubleMediumDataset() {
		return sortedDoubleMediumDataset;
	}


	public void setSortedDoubleMediumDataset(ArrayList<String> sortedDoubleMediumDataset) {
		this.sortedDoubleMediumDataset = sortedDoubleMediumDataset;
	}
	// Accessor methods ends here
}
