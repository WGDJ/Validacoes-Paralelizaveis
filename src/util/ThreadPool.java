package util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool
{

	private ExecutorService executor;

	public ThreadPool() {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		this.executor = Executors.newFixedThreadPool(availableProcessors > 1 ? availableProcessors - 1 : 1);
	}

	public Object executeAsync(Callable callable) throws ExecutionException, InterruptedException {

		return this.executor.submit(callable);
	}

	public void aguardarProcessamento() throws InterruptedException {

		this.executor.shutdown();

		while (!this.executor.isTerminated()) {}
	}

}