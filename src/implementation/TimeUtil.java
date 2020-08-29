package implementation;

import java.util.concurrent.TimeUnit;

public class TimeUtil {
	
	
	/*
	 * This records the time at which the function started running
	 * returns time in Nano seconds format
	 */
	public static long startTime() {
		long startTime = System.nanoTime();
		return startTime;
	}
	
	
	/*
	 * Returns the elapsed time between startTime and endTime
	 * @param startTime - nanoTime
	 * @param timeUnit - milli, seconds, micro
	 */
	public static long elapsedTime(long startTime, String timeUnit) {
		
		long endTime = System.nanoTime();
		long elapsedTime = (endTime - startTime);  //Total elapsed time - nano seconds
		
		
		switch(timeUnit) {
			case "milli":
				elapsedTime = TimeUnit.NANOSECONDS.toMillis(elapsedTime);		// Converted to milli seconds
			break;
		}
		
		return elapsedTime;
	}
	


}
