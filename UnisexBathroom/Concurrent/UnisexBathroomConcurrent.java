package Concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class UnisexBathroomConcurrent implements UnisexBathroomConcurrentInterface {

	public static final int N = 15;

	Semaphore toilet, entry, ticket, mutexMan, mutexWoman;
	int womanCount = 0, manCount = 0;

	public UnisexBathroomConcurrent() {
		super();
		toilet = new Semaphore(1);
		entry = new Semaphore(1);
		ticket = new Semaphore(N);
		mutexMan = new Semaphore(1);
		mutexWoman = new Semaphore(1);
	}

	// using toaled: Thread.sleep((new Random).nextInt(2000));
	@Override
	public void man(int id) {

		try {
			entry.acquireUninterruptibly();
			mutexMan.acquireUninterruptibly();
			manCount++;
			if (manCount == 1)
				toilet.acquireUninterruptibly();
			mutexMan.release();

			entry.release();

			ticket.acquireUninterruptibly();
			System.out.println("MAN_" + id + " just entered the bathroom.");
			// usingToilet
			Thread.sleep((new Random()).nextInt(2000));
			// usingToilet
			ticket.release();
			System.out.println("MAN_" + id + "just left the bathroom.");

			mutexMan.acquireUninterruptibly();
			manCount--;
			if (manCount == 0)
				toilet.release();
			mutexMan.release();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void woman(int id) {
		try {
			entry.acquireUninterruptibly();

			mutexWoman.acquireUninterruptibly();
			womanCount++;
			if (womanCount == 1)
				toilet.acquireUninterruptibly();
			mutexWoman.release();

			entry.release();

			ticket.acquireUninterruptibly();
			System.out.println("WOMAN_" + id + " just entered the bathroom.");

			// usingToilet
			Thread.sleep((new Random()).nextInt(2000));
			// usingToilet
			ticket.release();
			System.out.println("WOMAN_" + id + "just left the bathroom.");

			mutexWoman.acquireUninterruptibly();
			womanCount--;
			if (womanCount == 0)
				toilet.release();
			mutexWoman.release();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
