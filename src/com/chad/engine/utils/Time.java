package com.chad.engine.utils;

public class Time {
	
	/*
	 * A simple class for recording times
	 */
	
	// start time
	private long start;

	public Time() {
		// initialize start and end to the current time
		start = System.nanoTime();
	}
	
	// resets the start time
	public void reset() {
		start = System.nanoTime();
	}
	
	// returns the change in time since the last update
	public float getElapsed() { 
		long end = System.nanoTime();
		return (float) ((end - start) / 1000000000.0);
	}

}
