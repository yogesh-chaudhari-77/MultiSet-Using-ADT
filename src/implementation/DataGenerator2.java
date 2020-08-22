package implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.naming.InitialContext;

public class DataGenerator2 {
	private Random randomUtil = new Random();

	private static final int smallDatasetSize = 100;
	private static final int mediumDatasetSize = 1500;
	private static final int largeDatasetSize = 7500;

	String [] alphabetSet = { "a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z" };

	ArrayList<String> smallDataset = new ArrayList<String>();
	ArrayList<String> mediumDataset = new ArrayList<String>();
	ArrayList<String> largeDataset = new ArrayList<String>();

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


	public String getDataElement() {

		StringBuilder retStr = new StringBuilder();
		for(int i = 0; i < 5; i++) {
			int a = this.randomUtil.nextInt(26);
			retStr.append(this.alphabetSet[a]);
		}

		return retStr.toString();
	}



	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small
	 *  Distribution : Random
	 *  Operation : Add
	 */

	public void testArraySetAdd(ArrayMultiset arrayMultiset, ArrayList<String> dataset)
	{	
		// Testing best case - Adding duplicate element. First one.
		// Expecting no shifts
		String firstElement = arrayMultiset.getArray()[0].getElement();
		arrayMultiset.add(firstElement);

		// Worst case 1 - Element is not present in the list
		String uniqueElement = "YogeshShriramNotPresentInSet";
		arrayMultiset.add(uniqueElement);
		
		
		// Adding random duplicates
		int datasetSize = dataset.size();
		for(int i = 0; i < 5; i++) {
			arrayMultiset.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
		}
		
		
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			arrayMultiset.add( randomStrs[i] );
		}
	}


	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small
	 *  Distribution : Random
	 *  Operation : Remove 
	 */
	public void testArraySetRemove(ArrayMultiset arrayMultiset, ArrayList<String> dataset, int dataSetSize) {

		// Testing worst case - Element to be removed is first element with 1 occurance only. Expecting entire arrayShift
		String firstElement = arrayMultiset.getArray()[0].getElement();
		arrayMultiset.removeOne(firstElement);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";
		arrayMultiset.removeOne(largestElement);


		// Worst / Average case - In between elements
		// Random cases. Elements could be any where in the list
		for (int i = 0; i < 5; i++) {
			arrayMultiset.removeOne( dataset.get( this.randomUtil.nextInt( dataSetSize - 10 ) ) );
		}
	}


	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small
	 *  Distribution : Random
	 *  Operation : Print
	 */
	public void testArraySetPrint(ArrayMultiset arrayMultiset, ArrayList<String> dataset) {
		
		// Single occurance - Best case for array
		arrayMultiset.print();
		
		// Worst case - Duplicate elements are present

		int iterations = dataset.size();
		for(int a = 0 ; a < iterations; a++) {
			arrayMultiset.add( dataset.get( this.randomUtil.nextInt(iterations - 3) ) );
		}
		
		arrayMultiset.print();
	}


	/*
	 *  DataStructure : ArrayMultliset
	 *  Dataset : Small
	 *  Distribution : Random
	 *  Operation : Print
	 */
	public void testArraySetIntersect(ArrayMultiset first, ArrayMultiset second, int initialSetSize, int largeSetSize) {

		// Common elements in same order
		first.intersect(second);
		
		// Worst case - no elements are overlapping - Loop will run n1xn2 times
		second = new ArrayMultiset();
		for(int a = 0; a < initialSetSize; a++) {
			second.add( this.getDataElement() );
		}

		first.intersect(second);

		// Worse case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < largeSetSize; a++) {
			second.add( this.getDataElement() );
		}

		first.intersect(second);
	}

	
	public void testOrderedListAdd(OrderedLinkedListMultiset orderedList, ArrayList<String> dataset) {
		
		// Testing best case - Element to be added is equal to the head element
		String firstElement = orderedList.head.getVal();
		orderedList.add(firstElement);

		// Testing best case - Element to be added is equal to the head element
		String smallestElement = "a";
		orderedList.add(smallestElement);

		// Worst case - Adding an element which is not present in the string hence will be added at last
		// Expecting 1 complete traversal.
		String uniqueElement = "YogeshShriram";
		orderedList.add(uniqueElement);
		
		// Adding random duplicates
		int datasetSize = dataset.size();
		for(int i = 0; i < 5; i++) {
			orderedList.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
		}

		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			orderedList.add( randomStrs[i] );
		}
	}
	
	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Ascending order
	 *  Operation : Remove
	 */
	
	public void testOrderedListRemove(OrderedLinkedListMultiset orderedList, ArrayList<String> sortedDataSet, int datasetSize)
	{
		// Testing best case - Element to be removed is equal to the head element
		String firstElement = orderedList.getHead().getVal();
		orderedList.removeOne(firstElement);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";
		orderedList.removeOne(largestElement);
	
		
		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		for (int i = 0; i < 5; i++) {
			orderedList.removeOne( sortedDataSet.get( this.randomUtil.nextInt( datasetSize - 10 ) ) );
		}
	}
	
	
	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Ascending order
	 *  Operation : Intersection
	 */
	
	public void testOrderedListIntersect(OrderedLinkedListMultiset firstOrderedList, OrderedLinkedListMultiset secondOrderedList, int initialSetSize, int LargeDatasetSize)
	{
		
		// Testing best case - Both multiset contains the overlapping elements
		firstOrderedList.intersect(secondOrderedList);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondOrderedList = new OrderedLinkedListMultiset();
		for(int a = 0; a < initialSetSize; a++) {
			secondOrderedList.add( this.getDataElement() );
		}
		
		firstOrderedList.intersect(secondOrderedList);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < LargeDatasetSize; a++) {
			secondOrderedList.add( this.getDataElement() );
		}
		
		firstOrderedList.intersect(secondOrderedList);
	}
		
	
	/*
	 *  DataStructure : Ordered Linked List
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Ascending order
	 *  Operation : Print - Ordered by instance count
	 */
	public void testOrderedListPrint(OrderedLinkedListMultiset orderedList, ArrayList<String> dataset)
	{
		
		// Best case : All unique elements - Bubble sort can be stopped in first iteration
		orderedList.print();
		
		// Ordered List contains duplicate elements
		int iterations = dataset.size();
		for(int a = 0 ; a < iterations; a++) {
			orderedList.add( dataset.get( this.randomUtil.nextInt(iterations - 3) ) );
		}
		
		orderedList.print();
	}
	
	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small | Medium | Large
	 *  Distribution : BST Behaviour
	 *  Operation : Add
	 */
	public void testTreeSetAdd(BstMultiset bstSet, ArrayList<String> dataset) 
	{
		//  Best case - Element to be added is equal to the root element
		String rootVal = bstSet.getRoot().getVal();
		bstSet.add(rootVal);

		// Worst case - Adding an element which is not present in the string
		String uniqueElement = "YogeshShriram";
		bstSet.add(uniqueElement);
		
		// Adding random duplicates
		int datasetSize = dataset.size();
		for(int i = 0; i < 5; i++) {
			bstSet.add( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ));
		}

		// Usually cases - In between elements - Random insertions
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			bstSet.add( randomStrs[i] );
		}
		
	}
	
	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small | Medium | Large
	 *  Distribution : BST Behaviour
	 *  Operation : Remove
	 */
	
	public void testTreeSetRemove(BstMultiset bstSet, ArrayList<String> dataset, int datasetSize)
	{
		// Testing best case - Element to be removed is equal to the root element
		String rootVal = bstSet.getRoot().getVal();
		bstSet.removeOne(rootVal);
	
		
		// Average case - In between elements
		// Random cases. Elements could be any where in the tree
		for (int i = 0; i < 5; i++) {
			bstSet.removeOne( dataset.get( this.randomUtil.nextInt( datasetSize - 10 ) ) );
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

		// Testing best case - Both multiset contains the overlapping elements
		firstTree.intersect(secondTree);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondTree = new BstMultiset();
		for(int a = 0; a < initialDatasetSize; a++) {
			secondTree.add( this.getDataElement() );
		}
		
		firstTree.intersect(secondTree);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < largeDatasetSize; a++) {
			secondTree.add( this.getDataElement() );
		}
		
		firstTree.intersect(secondTree);
	}
	
	
	
	/*
	 *  DataStructure : Tree Set
	 *  Dataset : Small | Medium | Large
	 *  Distribution : Random - As per BST properties
	 *  Operation : Print - Ordered by instance count
	 */
	public void testTreeSetPrint(BstMultiset bstTree, ArrayList<String> dataset)
	{
		// Best case - Singly occurance - Loop can be terminated after 1st iterations
		bstTree.print();
		
		// Worst case - Tree contains duplicate elements
		int iterations = dataset.size();
		for(int a = 0 ; a < iterations / 2; a++) {
			bstTree.add( dataset.get( this.randomUtil.nextInt(iterations - 3) ) );
		}

		bstTree.print();
	}
	

	/*
	 *  DataStructure : Dual OrderedList List
	 *  Dataset : Small | Medium | Large
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Add
	 */
	
	public void testDualOrderedListAdd(DualLinkedListMultiset dualList, ArrayList<String> dataset)
	{

		// Testing best case - Element to be added is equal to the head element
		String firstElement = dualList.getHead1().getVal();
		dualList.add(firstElement);

		// Testing best case - Element to be added is less than head element.
		String smallestElement = "a";
		dualList.add(smallestElement);

		// Adding an element which is not present in the string
		String uniqueElement = "YogeshShriramNotPresentInList";
		dualList.add(uniqueElement);
		
		// Practical case - Adding random duplicates
		int datasetSize = dataset.size();
		for (int i = 0; i < 5; i++) {
			dualList.add( dataset.get( this.randomUtil.nextInt( datasetSize - 3) ) );
		}
		
		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			dualList.add( randomStrs[i] );
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
		// Testing best case - Element to be removed is equal to the head element
		String firstElement = dualList.getHead1().getVal();
		dualList.removeOne(firstElement);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";
		dualList.removeOne(largestElement);
	
		
		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		for (int i = 0; i < 5; i++) {
			dualList.removeOne( dataset.get(  this.randomUtil.nextInt( datasetSize - 10 ) ) );
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
		
		// Testing best case - Both multiset contains the overlapping elements
		FirstDualList.intersect(SecDualList);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		SecDualList = new DualLinkedListMultiset();
		for(int a = 0; a < initialDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}
		
		FirstDualList.intersect(SecDualList);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < LargeDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}
		
		FirstDualList.intersect(SecDualList);
	}
	
	
	/*
	 *  DataStructure : Dual Ordered Linked List
	 *  Dataset : Small | Medium | Large
	 *  Distribution : 1 list maintains the elements ordered by instance count. Other list maintains elements ordered by elements
	 *  Operation : Print - Ordered by instance count
	 */
	public void testDualOrderedListPrint(DualLinkedListMultiset dualList, ArrayList<String> dataset)
	{
		// Testing single occurance complexity
		dualList.print();

		// Populating duplicates - Sorting is not required for dual ordered list
		int datasetSize = dataset.size();
		for(int i = 0; i < datasetSize/2; i++) {
			dualList.add( dataset.get( this.randomUtil.nextInt(datasetSize - 5) ) );
		}

		dualList.print();
	}
	
	
	public void run_arraySet() {
		
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
		
		this.testArraySetAdd(smallArrSet, this.smallDataset);
		this.testArraySetAdd(mediumArrSet, this.mediumDataset);
		this.testArraySetAdd(largeArrSet, this.largeDataset);
		
		this.testArraySetRemove(smallArrSet, this.smallDataset, smallDatasetSize);
		this.testArraySetRemove(mediumArrSet, this.mediumDataset, mediumDatasetSize);
		this.testArraySetRemove(largeArrSet, this.largeDataset, largeDatasetSize);
		
		this.testArraySetPrint(smallArrSet, this.smallDataset);
		this.testArraySetPrint(mediumArrSet, this.mediumDataset);
		this.testArraySetPrint(largeArrSet, this.largeDataset);

		// Refrehing the dataasets to for the interesection cases
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
		
		this.testArraySetIntersect(smallArrSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);
		this.testArraySetIntersect(mediumArrSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);
		this.testArraySetIntersect(largeArrSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);
	}
	
	
	public void run_orderedListSet() {
		
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
		
		this.testOrderedListAdd(smallSet, this.smallDataset);
		this.testOrderedListAdd(mediumSet, this.mediumDataset);
		this.testOrderedListAdd(largeSet, this.largeDataset);
		
		this.testOrderedListRemove(smallSet, this.sortedSmallDataset, smallDatasetSize);
		this.testOrderedListRemove(mediumSet, this.sortedMediumDataset, mediumDatasetSize);
		this.testOrderedListRemove(largeSet, this.sortedLargeDataset, largeDatasetSize);
		
		this.testOrderedListPrint(smallSet, this.smallDataset);
		this.testOrderedListPrint(mediumSet, this.mediumDataset);
		this.testOrderedListPrint(largeSet, this.largeDataset);

		// Refrehing the dataasets to for the interesection cases
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
		
		this.testOrderedListIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);
		this.testOrderedListIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);
		this.testOrderedListIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);
	}
	
	
	public void run_bstTreeSet() {
		
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
		
		this.testTreeSetAdd(smallSet, this.sortedSmallDataset);
		this.testTreeSetAdd(mediumSet, this.sortedMediumDataset);
		this.testTreeSetAdd(largeSet, this.sortedLargeDataset);
		
		this.testTreeSetRemove(smallSet, this.smallDataset, smallDatasetSize);
		this.testTreeSetRemove(mediumSet, this.mediumDataset, mediumDatasetSize);
		this.testTreeSetRemove(largeSet, this.largeDataset, largeDatasetSize);
		
		this.testTreeSetPrint(smallSet, this.smallDataset);
		this.testTreeSetPrint(mediumSet, this.mediumDataset);
		this.testTreeSetPrint(largeSet, this.largeDataset);

		// Refrehing the dataasets to for the interesection cases
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
		
		this.testTreeSetIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);
		this.testTreeSetIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);
		this.testTreeSetIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);
	}
	
	
	public void run_dualSet() {
		
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
		
		this.testDualOrderedListAdd(smallSet, this.sortedSmallDataset);
		this.testDualOrderedListAdd(mediumSet, this.sortedMediumDataset);
		this.testDualOrderedListAdd(largeSet, this.sortedLargeDataset);
		
		this.testDualOrderedListRemove(smallSet, this.smallDataset, smallDatasetSize);
		this.testDualOrderedListRemove(mediumSet, this.mediumDataset, mediumDatasetSize);
		this.testDualOrderedListRemove(largeSet, this.largeDataset, largeDatasetSize);
		
		this.testDualOrderedListPrint(smallSet, this.smallDataset);
		this.testDualOrderedListPrint(mediumSet, this.mediumDataset);
		this.testDualOrderedListPrint(largeSet, this.largeDataset);

		// Refrehing the dataasets to for the interesection cases
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
		
		this.testDualOrderedListIntersect(smallSet, secSmallArrSet, smallDatasetSize, mediumDatasetSize);
		this.testDualOrderedListIntersect(mediumSet, secMediumArrSet, mediumDatasetSize, largeDatasetSize);
		this.testDualOrderedListIntersect(largeSet, seclLargeArrSet, largeDatasetSize, largeDatasetSize);
	}
	
	public void run() {
		
		long startTime = 0, endTime = 0, elapsedTime = 0, elapsedTimeInMS;
		
		startTime = System.nanoTime();
		
		run_arraySet();
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds  
		System.out.println(elapsedTimeInMS);
		
		startTime = System.nanoTime();
		
		run_orderedListSet();
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds  
		System.out.println(elapsedTimeInMS);
		
		startTime = System.nanoTime();
		
		run_bstTreeSet();
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds  
		System.out.println(elapsedTimeInMS);
		
		startTime = System.nanoTime();
		
		run_dualSet();
		
		endTime = System.nanoTime();
		elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		elapsedTimeInMS = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds  
		System.out.println(elapsedTimeInMS);
	}
	

	public static void main(String[] args) {
		
		DataGenerator2 driver = new DataGenerator2();
		driver.generateSmallDataset();
		driver.generateMediumDataset();
		driver.generateLargeDataset();
		driver.run();
	}
	
}