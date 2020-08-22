package implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

	private Random randomUtil = new Random();

	private static final int smallDatasetSize = 100;
	private static final int mediumDatasetSize = 5000;
	private static final int largeDatasetSize = 100000;

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
	 *  
	 */

	//	{
	//		// Create a multiset before performing operation.
	//		ArrayMultiset smallRandomMultiset = new ArrayMultiset();
	//
	//		for(String element : this.smallDataset) {
	//			smallRandomMultiset.add(element);
	//		}
	//
	//
	//		String firstElement = smallRandomMultiset[0].getVal
	//		smallRandomMultiset.add(elem);
	//	}


	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Add
	 */
	
	public void testOrderedListWithSmallDatadaset()
	{
		// Create a multiset before performing the operation
		OrderedLinkedListMultiset smallOrderedList = new OrderedLinkedListMultiset();
		for(String element : this.smallDataset) {
			smallOrderedList.add(element);
		}


		// Testing best case - Element to be added is equal to the head element
		String firstElement = smallOrderedList.head.getVal();
		smallOrderedList.add(firstElement);

		// Testing best case - Element to be added is equal to the head element
		String smallestElement = "a";
		smallOrderedList.add(smallestElement);

		// Adding an element which is not present in the string
		String uniqueElement = "YogeshShriram";
		smallOrderedList.add(uniqueElement);

		// Adding random element
		String alreadyPresentElement = this.smallDataset.get( this.smallDataset.size() / 2 );
		smallOrderedList.add(alreadyPresentElement);
		
		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			
			smallOrderedList.add( randomStrs[i] );
		}
		
		//System.out.println(smallOrderedList.print());
	}


	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Add
	 */
	
	public void testOrderedListWithMediumDatadaset()
	{

		OrderedLinkedListMultiset mediumOrderedList = new OrderedLinkedListMultiset();

		// Preparing medium sized dataset
		for(String element : this.mediumDataset) {
			mediumOrderedList.add(element);
		}


		// Testing best case - Element to be added is smaller than head element 
		// Hence the new element will become new head - No shifting required
		String smallestElement = "a";
		mediumOrderedList.add(smallestElement); 

		// Testing best case - Element to be added is equal to the head element
		String firstElement = mediumOrderedList.getHead().getVal();
		mediumOrderedList.add(firstElement);

		// Worst case - Adding an element which is not present in the string
		// Full list traversal is required. Element will be added at the end
		String uniqueElement = "YogeshShriram";
		mediumOrderedList.add(uniqueElement);

		// Average case - Adding random element which is already present in the list
		// Some list traversal is required. Average case
		String alreadyPresentElement = this.mediumDataset.get((this.mediumDataset.size() / 2));
		mediumOrderedList.add(alreadyPresentElement);
		
		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			mediumOrderedList.add( randomStrs[i] );
		}
		
		System.out.println(mediumOrderedList.print());
	}
	

	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Add
	 */
	public void testOrderedListWithLargeDatadaset()
	{

		OrderedLinkedListMultiset largeOrderedList = new OrderedLinkedListMultiset();

		// Populate the dataset
		for(String element : this.largeDataset) {
			largeOrderedList.add(element);
		}


		// Testing best case - Element to be added is equal to the head element
		String firstElement = largeOrderedList.getHead().getVal();
		largeOrderedList.add(firstElement);

		// Testing first case - Element to be added is equal to the head element
		String smallestElement = "a";
		largeOrderedList.add(smallestElement);

		// Adding an element which is not present in the string
		String uniqueElement = "YogeshShriram";
		largeOrderedList.add(uniqueElement);

		// Adding random element which is already present in the list
		String alreadyPresentElement = this.largeDataset.get(  this.largeDataset.size() / 2);
		largeOrderedList.add(alreadyPresentElement);
		
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"}; 
		for (int i = 0; i < 5; i++) {
			largeOrderedList.add( randomStrs[i] );
		}
		
		System.out.println(largeOrderedList.print());
	}
	
	
	

	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Remove
	 */
	
	public void testOrderedListRemoveWithSmallDatadaset()
	{
		// Create a multiset before performing the operation
		OrderedLinkedListMultiset smallOrderedList = new OrderedLinkedListMultiset();
		for(String element : this.smallDataset) {
			smallOrderedList.add(element);
		}

		// Testing best case - Element to be removed is equal to the head element
		String firstElement = smallOrderedList.head.getVal();
		smallOrderedList.removeOne(firstElement);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";
		smallOrderedList.removeOne(largestElement);
	
		
		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 3, 20, 40, 60, 90 };
		for (int i = 0; i < 5; i++) {
			smallOrderedList.removeOne( this.sortedSmallDataset.get(randomIndexes[i]) );
		}
	}


	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Remove
	 */
	
	public void testOrderedListRemoveWithMediumDatadaset()
	{

		OrderedLinkedListMultiset mediumOrderedList = new OrderedLinkedListMultiset();

		// Preparing medium sized dataset
		for(String element : this.mediumDataset) {
			mediumOrderedList.add(element);
		}


		// Testing best case - Element to be deleted is equal to the head element
		String firstElement = mediumOrderedList.getHead().getVal();
		mediumOrderedList.removeOne(firstElement);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String uniqueElement = "YogeshSriramNotInTheList";
		mediumOrderedList.removeOne(uniqueElement);

		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 10, 1000, 2000, 3000, 4777};
		for (int i = 0; i < 5; i++) {
			mediumOrderedList.removeOne( this.sortedMediumDataset.get(randomIndexes[i]) );
		}
	}
	

	/*
	 *  DataStructure : Ordered Linked List
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Remove
	 */
	public void testOrderedListRemoveWithLargeDatadaset()
	{

		OrderedLinkedListMultiset largeOrderedList = new OrderedLinkedListMultiset();

		// Populate the dataset
		for(String element : this.largeDataset) {
			largeOrderedList.add(element);
		}


		// Testing best case - Element to be deleted is equal to the head element
		String firstElement = largeOrderedList.getHead().getVal();
		largeOrderedList.removeOne(firstElement);

		
		// Element to be deleted is largest element and not present in the list
		String uniqueElement = "YogeshSriramNotInTheList";
		largeOrderedList.removeOne(uniqueElement);


		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 10, 10000, 25000, 50000, 75000, 90000};
		for (int i = 0; i < 6; i++) {
			largeOrderedList.removeOne( this.sortedLargeDataset.get(randomIndexes[i]) );
		}
	}
	
	
	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Add
	 */
	public void testTreeSetWithSmallDatadaset()
	{

		// Create a multiset before performing the operation
		BstMultiset smallBSTSet = new BstMultiset ();

		for(String element : this.smallDataset) {
			smallBSTSet.add(element);
		}


		// Testing best case - Element to be added is equal to the head element
		String firstElement = smallBSTSet.getRoot().getVal();
		smallBSTSet.add(firstElement);

		// Testing best case - Element to be added is equal to the head element
		String smallestElement = "a";
		smallBSTSet.add(smallestElement);

		// Adding an element which is not present in the string
		String uniqueElement = "YogeshShriram";
		smallBSTSet.add(uniqueElement);

		// Adding random element
		String alreadyPresentElement = this.smallDataset.get( this.smallDataset.size() / 2 );
		smallBSTSet.add(alreadyPresentElement);
		
		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			smallBSTSet.add( randomStrs[i] );
		}
	}


	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Add
	 */
	public void testTreeSetWithMediumDatadaset()
	{

		BstMultiset mediumBSTSet = new BstMultiset();

		// Preparing medium sized dataset
		for(String element : this.mediumDataset) {
			mediumBSTSet.add(element);
		}


		// Element to be added is smaller than head element 
		// Hence the new element will become new head - No shifting required
		String smallestElement = "a";
		mediumBSTSet.add(smallestElement); 

		// Testing best case - Element to be added is equal to the head element
		String firstElement = mediumBSTSet.getRoot().getVal();
		mediumBSTSet.add(firstElement);

		// Worst case - Adding an element which is not present in the string
		// Full list traversal is required. Element will be added at the end
		String uniqueElement = "YogeshShriram";
		mediumBSTSet.add(uniqueElement);

		// Average case - Adding random element which is already present in the list
		// Some list traversal is required. Average case
		String alreadyPresentElement = this.mediumDataset.get((this.mediumDataset.size() / 2));
		mediumBSTSet.add(alreadyPresentElement);
		
		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			mediumBSTSet.add( randomStrs[i] );
		}
	}


	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Add
	 */
	public void testTreeSetWithLargeDatadaset()
	{
		BstMultiset mediumBSTSet = new BstMultiset();

		// Populate the dataset
		for(String element : this.largeDataset) {
			mediumBSTSet.add(element);
		}


		// Testing best case - Element to be added is equal to the head element
		String firstElement = mediumBSTSet.getRoot().getVal();
		mediumBSTSet.add(firstElement);

		// Testing first case - Element to be added is equal to the head element
		String smallestElement = "a";
		mediumBSTSet.add(smallestElement);

		// Adding an element which is not present in the string
		String uniqueElement = "YogeshShriram";
		mediumBSTSet.add(uniqueElement);

		// Adding random element which is already present in the list
		String alreadyPresentElement = this.largeDataset.get(  this.largeDataset.size() / 2);
		mediumBSTSet.add(alreadyPresentElement);
		
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"}; 
		for (int i = 0; i < 5; i++) {
			mediumBSTSet.add( randomStrs[i] );
		}
	}
	
	
	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Remove
	 */
	
	public void testTreeSetRemoveWithSmallDatadaset()
	{
		// Create a multiset before performing the operation
		BstMultiset firstBSTSet = new BstMultiset();
		
		for(String element : this.smallDataset) {
			firstBSTSet.add(element);
		}

		// Testing best case - Element to be removed is equal to the root element
		String firstElement = firstBSTSet.getRoot().getVal();
		firstBSTSet.removeOne(firstElement);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire right sub tree to be traversed
		String largestElement = "YogeshSriramNotInList";
		firstBSTSet.removeOne(largestElement);
	
		
		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 3, 20, 40, 60, 90 };
		for (int i = 0; i < 5; i++) {
			firstBSTSet.removeOne( this.sortedSmallDataset.get(randomIndexes[i]) );
		}
	}


	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Remove
	 */
	
	public void testTreeSetRemoveWithMediumDatadaset()
	{

		BstMultiset multiset = new BstMultiset();

		// Preparing medium sized dataset
		for(String element : this.mediumDataset) {
			multiset.add(element);
		}


		// Testing best case - Element to be deleted is equal to the head element
		String firstElement = multiset.getRoot().getVal();
		multiset.removeOne(firstElement);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire right sub tree need to be traverserved
		String uniqueElement = "YogeshShriramNotInTheList";
		multiset.removeOne(uniqueElement);

		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 10, 1000, 2000, 3000, 4777};
		for (int i = 0; i < 5; i++) {
			multiset.removeOne( this.sortedMediumDataset.get(randomIndexes[i]) );
		}
	}
	

	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Large
	 *  Distribution : BST distribtion (Not Hight Balanced)
	 *  Operation : Remove
	 */
	public void testTreeSetRemoveWithLargeDatadaset()
	{

		BstMultiset multiset = new BstMultiset();

		// Populate the dataset
		for(String element : this.largeDataset) {
			multiset.add(element);
		}


		// Testing best case - Element to be deleted is equal to the root element
		String firstElement = multiset.getRoot().getVal();
		multiset.removeOne(firstElement);

		
		// Element to be deleted is largest element and not present in the tree
		// Entire right subtree needs to be traversed though
		String uniqueElement = "YogeshShriramNotInTheList";
		multiset.removeOne(uniqueElement);


		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 10, 10000, 25000, 50000, 75000, 90000};
		for (int i = 0; i < 6; i++) {
			multiset.removeOne( this.sortedLargeDataset.get(randomIndexes[i]) );
		}
	}
	
	
	/*
	 *  DataStructure : Ordered Linked List
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Print - Ordered by instance count
	 */
	public void testOrderedListPrintWithSmallDatadaset()
	{
		// Create a multiset before performing the operation
		OrderedLinkedListMultiset smallOrderedList = new OrderedLinkedListMultiset();
		for(String element : this.smallDataset) {
			smallOrderedList.add(element);
		}

		smallOrderedList.print();
		
		// Add duplicate data to achieve higher complexity
	}
	
	/*
	 *  DataStructure : Ordered Linked List
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Print - Ordered by instance count
	 */
	public void testOrderedListPrintWithMediumDatadaset()
	{
		// Create a multiset before performing the operation
		OrderedLinkedListMultiset mediumOrderedList = new OrderedLinkedListMultiset();
		for(String element : this.smallDataset) {
			mediumOrderedList.add(element);
		}

		mediumOrderedList.print();
	}
	
	
	/*
	 *  DataStructure : Ordered Linked List
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Print - Ordered by instance count
	 */
	public void testOrderedListPrintWithLargeDatadaset()
	{
		// Create a multiset before performing the operation
		OrderedLinkedListMultiset largeOrderedList = new OrderedLinkedListMultiset();
		for(String element : this.smallDataset) {
			largeOrderedList.add(element);
		}

		largeOrderedList.print();
	}
	
	
	/*
	 *  DataStructure : Tree Set
	 *  Dataset : Small
	 *  Distribution : Random - As per BST properties
	 *  Operation : Print - Ordered by instance count
	 */
	public void testTreeSetPrintWithSmallDatadaset()
	{
		// Create a multiset before performing the operation
		BstMultiset smallTreeSet = new BstMultiset();
		for(String element : this.smallDataset) {
			smallTreeSet.add(element);
		}

		smallTreeSet.print();
	}
	
	
	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Medium
	 *  Distribution : Random - BST Property
	 *  Operation : Print - Ordered by instance count
	 */
	public void testTreeSetPrintWithMediumDatadaset()
	{
		// Create a multiset before performing the operation
		BstMultiset mediumTreeSet = new BstMultiset();
		for(String element : this.smallDataset) {
			mediumTreeSet.add(element);
		}

		mediumTreeSet.print();
	}
	
	
	/*
	 *  DataStructure : Tree Set
	 *  Dataset : Large
	 *  Distribution : Random
	 *  Operation : Print - Ordered by instance count
	 */
	public void testTreeSetPrintWithLargeDatadaset()
	{
		// Create a multiset before performing the operation
		BstMultiset largeTreeSet = new BstMultiset();
		for(String element : this.smallDataset) {
			largeTreeSet.add(element);
		}

		largeTreeSet.print();
	}
	
	
	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Intersection
	 */
	
	public void testOrderedListIntersectWithSmallDatadaset()
	{
		// New multiset instances
		OrderedLinkedListMultiset firstOrderedList = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset secondOrderedList = new OrderedLinkedListMultiset();
		
		// Populating multiset
		for(String element : this.smallDataset) {
			firstOrderedList.add(element);
			secondOrderedList.add(element);
		}
		
		// Testing best case - Both multiset contains the overlapping elements
		firstOrderedList.intersect(secondOrderedList);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondOrderedList = new OrderedLinkedListMultiset();
		for(int a = 0; a < smallDatasetSize; a++) {
			secondOrderedList.add( this.getDataElement() );
		}
		
		firstOrderedList.intersect(secondOrderedList);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < mediumDatasetSize; a++) {
			secondOrderedList.add( this.getDataElement() );
		}
		
		firstOrderedList.intersect(secondOrderedList);
	}
	
	
	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Intersection
	 */
	
	public void testOrderedListIntersectWithMediumDatadaset()
	{
		// New multiset instances
		OrderedLinkedListMultiset firstOrderedList = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset secondOrderedList = new OrderedLinkedListMultiset();
		
		// Populating multiset
		for(String element : this.mediumDataset) {
			firstOrderedList.add(element);
			secondOrderedList.add(element);
		}
		
		// Testing best case - Both multiset contains the overlapping elements
		firstOrderedList.intersect(secondOrderedList);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondOrderedList = new OrderedLinkedListMultiset();
		for(int a = 0; a < mediumDatasetSize; a++) {
			secondOrderedList.add( this.getDataElement() );
		}
		
		firstOrderedList.intersect(secondOrderedList);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < largeDatasetSize; a++) {
			secondOrderedList.add( this.getDataElement() );
		}
		
		firstOrderedList.intersect(secondOrderedList);
	}
	
	
	/*
	 *  DataStructure : OrderedList
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Intersection
	 */
	
	public void testOrderedListIntersectWithLargeDatadaset()
	{
		// New multiset instances
		OrderedLinkedListMultiset firstOrderedList = new OrderedLinkedListMultiset();
		OrderedLinkedListMultiset secondOrderedList = new OrderedLinkedListMultiset();
		
		// Populating multiset
		for(String element : this.largeDataset) {
			firstOrderedList.add(element);
			secondOrderedList.add(element);
		}
		
		// Testing best case - Both multiset contains the overlapping elements
		firstOrderedList.intersect(secondOrderedList);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondOrderedList = new OrderedLinkedListMultiset();
		for(int a = 0; a < largeDatasetSize; a++) {
			secondOrderedList.add( this.getDataElement() );
		}
		
		firstOrderedList.intersect(secondOrderedList);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		firstOrderedList = new OrderedLinkedListMultiset();
		for(int a = 0; a < smallDatasetSize; a++) {
			firstOrderedList.add( this.getDataElement() );
		}
		
		firstOrderedList.intersect(secondOrderedList);
	}
	
	
	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Small
	 *  Distribution : BST Behaviour
	 *  Operation : Intersection
	 */
	
	public void testTreeSetIntersectWithSmallDatadaset()
	{
		// New multiset instances
		BstMultiset firstTree = new BstMultiset();
		BstMultiset secondTree = new BstMultiset();
		
		// Populating multiset
		for(String element : this.smallDataset) {
			firstTree.add(element);
			secondTree.add(element);
		}
		
		// Testing best case - Both multiset contains the overlapping elements
		firstTree.intersect(secondTree);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondTree = new BstMultiset();
		for(int a = 0; a < smallDatasetSize; a++) {
			secondTree.add( this.getDataElement() );
		}
		
		firstTree.intersect(secondTree);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < mediumDatasetSize; a++) {
			secondTree.add( this.getDataElement() );
		}
		
		firstTree.intersect(secondTree);
	}
	
	
	/*
	 *  DataStructure : TreeSet
	 *  Dataset : Medium
	 *  Distribution : BST Behaviour
	 *  Operation : Intersection
	 */
	
	public void testTreeSetIntersectWithMediumDatadaset()
	{
		// New multiset instances
		BstMultiset firstTree = new BstMultiset();
		BstMultiset secondTree = new BstMultiset();
		
		// Populating multiset
		for(String element : this.mediumDataset) {
			firstTree.add(element);
			secondTree.add(element);
		}
		
		// Testing best case - Both multiset contains the overlapping elements
		firstTree.intersect(secondTree);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondTree = new BstMultiset();
		for(int a = 0; a < mediumDatasetSize; a++) {
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
	 *  DataStructure : TreeSet
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Intersection
	 */
	
	public void testTreeSetIntersectWithLargeDatadaset()
	{
		// New multiset instances
		BstMultiset firstTree = new BstMultiset();
		BstMultiset secondTree = new BstMultiset();
		
		// Populating multiset
		for(String element : this.largeDataset) {
			firstTree.add(element);
			secondTree.add(element);
		}
		
		// Testing best case - Both multiset contains the overlapping elements
		firstTree.intersect(secondTree);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		secondTree = new BstMultiset();
		for(int a = 0; a < largeDatasetSize; a++) {
			secondTree.add( this.getDataElement() );
		}
		
		firstTree.intersect(secondTree);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		firstTree = new BstMultiset();
		for(int a = 0; a < smallDatasetSize; a++) {
			firstTree.add( this.getDataElement() );
		}
		
		firstTree.intersect(secondTree);
	}
	
	
	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Add
	 */
	
	public void testDualOrderedListWithSmallDatadaset()
	{
		// Create a multiset before performing the operation
		DualLinkedListMultiset dualList = new DualLinkedListMultiset();
		for(String element : this.smallDataset) {
			dualList.add(element);
		}


		// Testing best case - Element to be added is equal to the head element
		String firstElement = dualList.getHead1().getVal();
		dualList.add(firstElement);

		// Testing best case - Element to be added is equal to the head element
		String smallestElement = "a";
		dualList.add(smallestElement);

		// Adding an element which is not present in the string
		String uniqueElement = "YogeshShriram";
		dualList.add(uniqueElement);

		// Adding random element
		String alreadyPresentElement = this.smallDataset.get( this.smallDataset.size() / 2 );
		dualList.add(alreadyPresentElement);
		
		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			
			dualList.add( randomStrs[i] );
		}
		
		//System.out.println(smallOrderedList.print());
	}


	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Add
	 */
	
	public void testDualOrderedListWithMediumDatadaset()
	{

		DualLinkedListMultiset dualList = new DualLinkedListMultiset();

		// Preparing medium sized dataset
		for(String element : this.mediumDataset) {
			dualList.add(element);
		}


		// Testing best case - Element to be added is smaller than head element 
		// Hence the new element will become new head - No shifting required
		String smallestElement = "a";
		dualList.add(smallestElement); 

		// Testing best case - Element to be added is equal to the head element
		String firstElement = dualList.getHead1().getVal();
		dualList.add(firstElement);

		// Worst case - Adding an element which is not present in the string
		// Full list traversal is required. Element will be added at the end
		String uniqueElement = "YogeshShriramNotPresent";
		dualList.add(uniqueElement);

		// Average case - Adding random element which is already present in the list
		// Some list traversal is required. Average case
		String alreadyPresentElement = this.mediumDataset.get((this.mediumDataset.size() / 2));
		dualList.add(alreadyPresentElement);
		
		// Average case - In between elements
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"};
		for (int i = 0; i < 5; i++) {
			dualList.add( randomStrs[i] );
		}
		
		//System.out.println(dualList.print());
	}
	

	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Add
	 */
	public void testDualOrderedListWithLargeDatadaset()
	{

		DualLinkedListMultiset dualList = new DualLinkedListMultiset();

		// Populate the dataset
		for(String element : this.largeDataset) {
			dualList.add(element);
		}


		// Testing best case - Element to be added is equal to the head element
		String firstElement = dualList.getHead1().getVal();
		dualList.add(firstElement);

		// Testing first case - Element to be added is equal to the head element
		String smallestElement = "a";
		dualList.add(smallestElement);

		// Adding an element which is not present in the string
		String uniqueElement = "YogeshShriramNotInTheList";
		dualList.add(uniqueElement);

		// Adding random element which is already present in the list
		String alreadyPresentElement = this.largeDataset.get(  this.largeDataset.size() / 2);
		dualList.add(alreadyPresentElement);
		
		// Random cases. Elements could be added in between
		String [] randomStrs = { "aaaaa", "ddddd", "hhhhh", "kkkkk", "vvvvv", "rrrrr", "zzzzz"}; 
		for (int i = 0; i < 5; i++) {
			dualList.add( randomStrs[i] );
		}
		
		//System.out.println(dualList.print());
	}
	
	
	

	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Remove
	 */
	
	public void testDualOrderedListRemoveWithSmallDatadaset()
	{
		// Create a multiset before performing the operation
		DualLinkedListMultiset dualList = new DualLinkedListMultiset();
		for(String element : this.smallDataset) {
			dualList.add(element);
		}

		// Testing best case - Element to be removed is equal to the head element
		String firstElement = dualList.getHead1().getVal();
		dualList.removeOne(firstElement);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String largestElement = "YogeshSriramNotInTheList";
		dualList.removeOne(largestElement);
	
		
		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 3, 20, 40, 60, 90 };
		for (int i = 0; i < 5; i++) {
			dualList.removeOne( this.sortedSmallDataset.get(randomIndexes[i]) );
		}
	}


	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Remove
	 */
	
	public void testDualOrderedListRemoveWithMediumDatadaset()
	{

		DualLinkedListMultiset dualList = new DualLinkedListMultiset();

		// Preparing medium sized dataset
		for(String element : this.mediumDataset) {
			dualList.add(element);
		}


		// Testing best case - Element to be deleted is equal to the head element
		String firstElement = dualList.getHead1().getVal();
		dualList.removeOne(firstElement);

		// Testing worst case - Largest element to be deleted but not in the list
		// Entire list to be traversed
		String uniqueElement = "YogeshSriramNotInTheList";
		dualList.removeOne(uniqueElement);

		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 10, 1000, 2000, 3000, 4777};
		for (int i = 0; i < 5; i++) {
			dualList.removeOne( this.sortedMediumDataset.get(randomIndexes[i]) );
		}
	}
	

	/*
	 *  DataStructure : Dual Ordered Linked List
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Remove
	 */
	public void testDualOrderedListRemoveWithLargeDatadaset()
	{

		DualLinkedListMultiset dualList = new DualLinkedListMultiset();

		// Populate the dataset
		for(String element : this.largeDataset) {
			dualList.add(element);
		}


		// Testing best case - Element to be deleted is equal to the head element
		String firstElement = dualList.getHead1().getVal();
		dualList.removeOne(firstElement);

		
		// Element to be deleted is largest element and not present in the list
		String uniqueElement = "YogeshSriramNotInTheList";
		dualList.removeOne(uniqueElement);


		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 10, 10000, 25000, 50000, 75000, 90000};
		for (int i = 0; i < 6; i++) {
			dualList.removeOne( this.sortedLargeDataset.get(randomIndexes[i]) );
		}
	}
	
	
	
	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Intersection
	 */
	
	public void testDualOrderedListIntersectWithSmallDatadaset()
	{
		// New multiset instances
		DualLinkedListMultiset FirstDualList = new DualLinkedListMultiset();
		DualLinkedListMultiset SecDualList = new DualLinkedListMultiset();
		
		// Populating multiset
		for(String element : this.smallDataset) {
			FirstDualList.add(element);
			SecDualList.add(element);
		}
		
		// Testing best case - Both multiset contains the overlapping elements
		FirstDualList.intersect(SecDualList);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		SecDualList = new DualLinkedListMultiset();
		for(int a = 0; a < smallDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}
		
		FirstDualList.intersect(SecDualList);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < mediumDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}
		
		FirstDualList.intersect(SecDualList);
	}
	
	
	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Intersection
	 */
	
	public void testDualOrderedListIntersectWithMediumDatadaset()
	{
		// New multiset instances
		DualLinkedListMultiset FirstDualList = new DualLinkedListMultiset();
		DualLinkedListMultiset SecDualList = new DualLinkedListMultiset();
		
		// Populating multiset
		for(String element : this.mediumDataset) {
			FirstDualList.add(element);
			SecDualList.add(element);
		}
		
		// Testing best case - Both multiset contains the overlapping elements
		FirstDualList.intersect(SecDualList);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		SecDualList = new DualLinkedListMultiset();
		for(int a = 0; a < mediumDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}
		
		FirstDualList.intersect(SecDualList);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		for(int a = 0; a < largeDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}
		
		FirstDualList.intersect(SecDualList);
	}
	
	
	/*
	 *  DataStructure : Dual OrderedList
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Intersection
	 */
	
	public void testDualOrderedListIntersectWithLargeDatadaset()
	{
		// New multiset instances
		DualLinkedListMultiset FirstDualList = new DualLinkedListMultiset();
		DualLinkedListMultiset SecDualList = new DualLinkedListMultiset();
		
		// Populating multiset
		for(String element : this.largeDataset) {
			FirstDualList.add(element);
			SecDualList.add(element);
		}
		
		// Testing best case - Both multiset contains the overlapping elements
		FirstDualList.intersect(SecDualList);
		
		// Testing best case - no elements are overlapping - Expecting loop to run only min(n1,n2) times
		SecDualList = new DualLinkedListMultiset();
		for(int a = 0; a < largeDatasetSize; a++) {
			SecDualList.add( this.getDataElement() );
		}
		
		FirstDualList.intersect(SecDualList);
		
		// Testing best case - 1 set is relatively large and another relatively small - running time would be min(n1,n2)
		FirstDualList = new DualLinkedListMultiset();
		for(int a = 0; a < smallDatasetSize; a++) {
			FirstDualList.add( this.getDataElement() );
		}
		
		FirstDualList.intersect(SecDualList);
	}
	
	
	
	/*
	 *  DataStructure : Dual Ordered Linked List
	 *  Dataset : Small
	 *  Distribution : Ascending order
	 *  Operation : Print - Ordered by instance count
	 */
	public void testDualOrderedListPrintWithSmallDatadaset()
	{
		// Create a multiset before performing the operation
		DualLinkedListMultiset dualList = new DualLinkedListMultiset();

		for(String element : this.smallDataset) {
			dualList.add(element);
		}

		// Testing single occurance complexity
		dualList.print();

		// Populating duplicates
		for(int i = 0; i<50; i++) {
			dualList.add( this.smallDataset.get( this.randomUtil.nextInt(90) ) );
		}

		dualList.print();

		// Add duplicate data to achieve higher complexity
	}

	/*
	 *  DataStructure : Dual Ordered Linked List
	 *  Dataset : Medium
	 *  Distribution : Ascending order
	 *  Operation : Print - Ordered by instance count
	 */
	public void testDualOrderedListPrintWithMediumDatadaset()
	{
		// Create a multiset before performing the operation
		DualLinkedListMultiset dualList = new DualLinkedListMultiset();

		for(String element : this.smallDataset) {
			dualList.add(element);
		}

		dualList.print();

		// Populating duplicates
		for(int i = 0; i<1000; i++) {
			dualList.add( this.smallDataset.get( this.randomUtil.nextInt(90) ) );
		}

		dualList.print();
	}


	/*
	 *  DataStructure : Dual Ordered Linked List
	 *  Dataset : Large
	 *  Distribution : Ascending order
	 *  Operation : Print - Ordered by instance count
	 */
	public void testDualOrderedListPrintWithLargeDatadaset()
	{
		// Create a multiset before performing the operation
		DualLinkedListMultiset dualList = new DualLinkedListMultiset();

		for(String element : this.smallDataset) {
			dualList.add(element);
		}

		dualList.print();

		// Populating duplicates - 1/3 of original list
		for(int i = 0; i<25000; i++) {
			dualList.add( this.smallDataset.get( this.randomUtil.nextInt(90) ) );
		}

		dualList.print();
	}
	
	
	public static void main(String[] args) {
		
		DataGenerator dg = new DataGenerator();
		
		dg.generateSmallDataset();
		dg.generateMediumDataset();
		dg.generateLargeDataset();
		
		long startTime = System.nanoTime();
		
		// Testing ordered list implementation - Add
		dg.testOrderedListWithSmallDatadaset();
		dg.testOrderedListWithMediumDatadaset();
		dg.testOrderedListWithLargeDatadaset();
		
		
		// Testing ordered list implementation - remove operation
		dg.testOrderedListRemoveWithSmallDatadaset();
		dg.testOrderedListRemoveWithMediumDatadaset();
		dg.testOrderedListRemoveWithLargeDatadaset();
		
		
		// Testing ordered list implementation - print operation
		dg.testOrderedListPrintWithSmallDatadaset();
		dg.testOrderedListPrintWithMediumDatadaset();
		dg.testOrderedListPrintWithLargeDatadaset();
		
		
		// Testing ordered list implementation - intersection operation
		dg.testOrderedListIntersectWithSmallDatadaset();
		dg.testOrderedListIntersectWithMediumDatadaset();
		dg.testOrderedListIntersectWithLargeDatadaset();
		
				
		// Testing BST implementation for the add operation
		dg.testTreeSetWithSmallDatadaset();
		dg.testTreeSetWithMediumDatadaset();
		dg.testTreeSetWithLargeDatadaset();
		
		
		// Testing BST implementation - Remove operation
		dg.testTreeSetRemoveWithSmallDatadaset();
		dg.testTreeSetRemoveWithMediumDatadaset();
		dg.testTreeSetRemoveWithLargeDatadaset();

		
		// Testing BST implementation for the - intersection
		dg.testTreeSetIntersectWithSmallDatadaset();
		dg.testTreeSetIntersectWithMediumDatadaset();
		dg.testTreeSetIntersectWithLargeDatadaset();
		
		
		// Testing treeset list implementaion print operation
		dg.testTreeSetPrintWithSmallDatadaset();
		dg.testTreeSetPrintWithMediumDatadaset();
		dg.testTreeSetPrintWithLargeDatadaset();
		
		
		// Testing Dual Ordered List - Add 
		dg.testDualOrderedListWithSmallDatadaset();
		dg.testDualOrderedListWithMediumDatadaset();
		dg.testDualOrderedListWithLargeDatadaset();
		
		
		// Testing Dual Ordered List - Remove
		dg.testDualOrderedListRemoveWithSmallDatadaset();
		dg.testDualOrderedListRemoveWithMediumDatadaset();
		dg.testDualOrderedListRemoveWithLargeDatadaset();
		
		
		// Testing Dual Ordered List - Intersect
		
		
		// Testing Dual Ordered List Print()
		
		long endTime = System.nanoTime();
		long durationInNano = (endTime - startTime);  //Total execution time in nano seconds

		//Same duration in millis

		long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);  //Total execution time in nano seconds

		System.out.println(durationInNano);
		System.out.println(durationInMillis / 1000);
		
		// Testing the Dual ordered implementation for the add operation
		
	}
	
}


