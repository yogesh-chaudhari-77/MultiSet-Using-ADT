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
		long elapsedTime = (long)(endTime - startTime);  //Total elapsed time - nano seconds

		long convertedElapsedTime = convertTime(elapsedTime, timeUnit);

		return convertedElapsedTime;
	}

	
	public static long elapsedTime(long startTime, long endTime, String timeUnit) {

		long elapsedTime = (long)(endTime - startTime);  //Total elapsed time - nano seconds
		
		long convertedElapsedTime = convertTime(elapsedTime, timeUnit);
		
		return convertedElapsedTime;

	}
	

	public static long convertTime(long duration, String timeUnit) {

		long retTime = 0; 
		
		switch(timeUnit) {
		case "milli":
			retTime = TimeUnit.NANOSECONDS.toMillis(duration);		// Converted to milli seconds
			break;

			// 29-08-2020 - Required for analysis purpose
		case "seconds":
			retTime = TimeUnit.NANOSECONDS.toSeconds(duration);		// Converted to milli seconds
			break;
		}

		return retTime;
	}

}
