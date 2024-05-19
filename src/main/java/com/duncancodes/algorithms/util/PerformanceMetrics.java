package com.duncancodes.algorithms.util;

import java.util.concurrent.Callable;

public class PerformanceMetrics {
	public static void measureExecution(Runnable task) {
		Runtime runtime = Runtime.getRuntime();

		// Memory usage before execution
		long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
		// Time before execution
		long startTime = System.nanoTime();

		// Execute the task
		task.run();

		// Time after execution
		long endTime = System.nanoTime();
		// Memory usage after execution
		long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

		// Calculate elapsed time and memory usage
		long elapsedTime = endTime - startTime;
		long memoryUsed = memoryAfter - memoryBefore;

		// Print results
		System.out.println("Elapsed time: " + elapsedTime + " ns");
		System.out.println("Memory used: " + memoryUsed + " bytes");
	}

	public static <T> T measureExecution(Callable<T> task) throws Exception {
		Runtime runtime = Runtime.getRuntime();

		// Memory usage before execution
		long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
		// Time before execution
		long startTime = System.nanoTime();

		// Execute the task and get the result
		T result = task.call();

		// Time after execution
		long endTime = System.nanoTime();
		// Memory usage after execution
		long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

		// Calculate elapsed time and memory usage
		long elapsedTime = endTime - startTime;
		long memoryUsed = memoryAfter - memoryBefore;

		// Print results
		System.out.println("Elapsed time: " + elapsedTime + " ns");
		System.out.println("Memory used: " + memoryUsed + " bytes");

		return result;
	}
}
