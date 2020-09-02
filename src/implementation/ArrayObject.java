package implementation;

/**
 * @Author Sriram Senthilnathan, RMIT University, Master of Information Technology
 */
public class ArrayObject 
{

	private String element;
	private int instanceCount;
	
	// Constructor initializes the array object with element and its instance count.
	public ArrayObject(String element,int instanceCount)
	{
		this.element = element;
		this.instanceCount = instanceCount;
	}
	
	
	// Increasing instance count to represent element(which is already present in multiset) addition
	public void incrementInstCount()
	{
		this.instanceCount += 1;
	}
	
	
	// Increasing instance count to represent element(which is already present in multiset) deletion
	public void decrementInstCount()
	{
		this.instanceCount -= 1;
	}	
	
	
	//Returns the element
	public String getElement()
	{
		return this.element;
	}
	
	
	//Returns the instance count value
	public int getInstCount()
	{
		return this.instanceCount;
	}
	
	
	//Sets the array object with element and instance count values passed in arguments
	public void setObject(String element, int instanceCount)
	{
		this.element = element;
		this.instanceCount = instanceCount;
	}
	
	
	//Sets the instance count value to value passed in argument
	public void setInstanceCount(int instanceCount)
	{
		this.instanceCount = instanceCount;
	}
}
