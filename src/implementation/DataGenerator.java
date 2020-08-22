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
	 *  DataStructure : ArrayMultliset
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
	 *  DataStructure : ArrayMultliset
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
	 *  DataStructure : ArrayMultliset
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
	 *  DataStructure : ArrayMultliset
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
		String smallestElement = "YogeshSriram";
		smallOrderedList.removeOne(smallestElement);
	
		
		// Average case - In between elements
		// Random cases. Elements could be any where in the list
		int [] randomIndexes = { 3, 20, 40, 60, 90 };
		for (int i = 0; i < 5; i++) {
			smallOrderedList.removeOne( this.sortedSmallDataset.get(randomIndexes[i]) );
		}
	}


	/*
	 *  DataStructure : ArrayMultliset
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
		String uniqueElement = "YogeshShriram";
		mediumOrderedList.add(uniqueElement);

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
		largeOrderedList.add(firstElement);

		
		// Element to be deleted is largest element and not present in the list
		String uniqueElement = "YogeshShriram";
		largeOrderedList.add(uniqueElement);


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
		
		
		// Testing ordered list implementation print operation
		dg.testOrderedListPrintWithSmallDatadaset();
		dg.testOrderedListPrintWithMediumDatadaset();
		dg.testOrderedListPrintWithLargeDatadaset();
		
		
		// Testing treeset list implementaion print operation
		dg.testTreeSetPrintWithSmallDatadaset();
		dg.testTreeSetPrintWithMediumDatadaset();
		dg.testTreeSetPrintWithLargeDatadaset();
		
		
		// Testing BST implementation for the add operation
		dg.testTreeSetWithSmallDatadaset();
		dg.testTreeSetWithMediumDatadaset();
		dg.testTreeSetWithLargeDatadaset();
		
		
		// Testing BST implementation for the - intersection

		long endTime = System.nanoTime();
		long durationInNano = (endTime - startTime);  //Total execution time in nano seconds

		//Same duration in millis

		long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);  //Total execution time in nano seconds

		System.out.println(durationInNano);
		System.out.println(durationInMillis / 1000);
		
		// Testing the Dual ordered implementation for the add operation
		
	}
	
}


