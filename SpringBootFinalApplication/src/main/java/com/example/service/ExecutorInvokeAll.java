package com.example.service;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorInvokeAll {

	public void runApp() throws InterruptedException, ExecutionException {


		// Use our friendly neighbourhood factory method of the Executors.====
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		
		List<Callable<Integer>> callableList = new ArrayList<Callable<Integer>>();

		for (int count = 0; count <= 500; count++) {
			callableList.add(getInstanceOfCallable(count));

		}

		// returns only after all tasks are complete=============
		List<Future<Integer>> resultFuture = executorService.invokeAll(callableList);
		
		// Prints 5050 all through
		for (Future<Integer> future : resultFuture) {
			// Didn't deliberately put a timeout here for the get method.
			// Remember, the invoke All does not return until the task is done.
			
			if (future.isDone()) {
				System.out.println("Status of future 1 : " + future.isDone() + ". Result of future 1 : " + future.get());
			}else{
				executorService.awaitTermination(Long.MAX_VALUE,TimeUnit.NANOSECONDS);
				System.out.println("Status of future 2 : " + future.isDone() + ". Result of future 2 : " + future.get());
			}
			
		}

		executorService.shutdown();

		// You might as well call a resultFuture.get(0).get().get() and that
		// would give you the same
		// result since all your worker threads hold reference to the same
		// atomicinteger sum.
		// System.out.println("Final Sum : "+sum);

	}

	// Adds count to the sum and returns the reference of the sum as the result
	private Callable<Integer> getInstanceOfCallable(final int count) {

		Callable<Integer> clientPlanCall = new Callable<Integer>() {

			public Integer call() {

				//System.out.println("count  " + count1 + " : " + count);
				//sum.addAndGet(count);                                                                 //business logic
				System.out.println("Intermediate count  : => "+count);

				return count;
			}
		};

		return clientPlanCall;
	}

	public static void main(String[] args) throws ExecutionException {

		try {
			new ExecutorInvokeAll().runApp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}