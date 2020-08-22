package implementation;

public class ArrayObject {

	private String element;
	private int instanceCount;
	
	public ArrayObject(String element,int instanceCount)
	{
		this.element = element;
		this.instanceCount = instanceCount;
	}
	
	
	public void incrementInstCount()
	{
		this.instanceCount += 1;
	}
	
	
	public void decrementInstCount()
	{
		this.instanceCount -= 1;
	}	
	
	
	public String getElement()
	{
		return this.element;
	}
	
	
	public int getInstCount()
	{
		return this.instanceCount;
	}
	
	
	public void setObject(String element, int instanceCount)
	{
		this.element = element;
		this.instanceCount = instanceCount;
	}
	
	
	public void setInstanceCount(int instanceCount)
	{
		this.instanceCount = instanceCount;
	}
}
