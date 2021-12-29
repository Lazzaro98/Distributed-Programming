package Concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

//sleep: Thread.sleep((new Random()).nextInt(5000));
//eat: Thread.sleep((new Random()).nextInt(2000));
public class BeesConcurrent implements BeesConcurrentInterface {
	private static final int N = 10;
	private static final int H = 10;

	private static final int SLEEP_DURATION_IN_SECONDS = 5;
	private static final int EAT_DURATION_IN_SECONDS = 2;
	private static final int COLLECT_DURATION_IN_SECONDS = 4;

	Semaphore hive, full;
	int pot;

	private void sleep() {
		try {
			Thread.sleep((new Random()).nextInt(SLEEP_DURATION_IN_SECONDS * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void eat() {
		try {
			Thread.sleep((new Random()).nextInt(EAT_DURATION_IN_SECONDS * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void collect() {
		try {
			Thread.sleep((new Random()).nextInt(COLLECT_DURATION_IN_SECONDS * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public BeesConcurrent() {
		hive = new Semaphore(1);
		full = new Semaphore(0);
		pot = 0;
	}

	
	//stavicemo bez while-a. Necemo praviti endless loop od funkcije, jer cemo funkciju pozivati Remotely, da bismo mogli da dobijemo povratnu
	// informaciju da je funkcija izvrsena
	@Override
	public void bear() {
		//while (true) {
			sleep();
			full.acquireUninterruptibly();
			eat();
			pot = 0;
			System.out.println("Meda poruckao med.");
			hive.release();
		//}
	}

	@Override
	public void bee() {
		//while (true) {
			collect();
			hive.acquireUninterruptibly();
			pot++;
			if (pot == H) {
				full.release();
				System.out.println("Pcelice napunile pot.");
			} else
				hive.release();
		//}
	}

}
