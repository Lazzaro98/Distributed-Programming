package Concurrent;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
	int id;
	int firstodd, secondeven;
	Semaphore[] forks;
	int n;

	public Philosopher(int id, int n, Semaphore[] forks) {
		super("Philosopher_" + id);
		// n++;
		this.id = id;
		this.forks = forks;

		if ((id % 2) == 1) {
			firstodd = id;
			secondeven = (id + 1) % n;
		} else {
			firstodd = (id + 1) % n;
			secondeven = id;
		}
		this.n = n;
	}

	public int getN() {
		return n;
	}

	private void think() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void eat() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			think();
			System.out.println(getName() + " zavrsio s mozganjem. " + firstodd);
			forks[firstodd].acquireUninterruptibly();
			forks[secondeven].acquireUninterruptibly();
			System.out.println(getName() + " pocinje da rucka.");
			eat();
			System.out.println(getName() + " poruckao.");
			forks[secondeven].release();
			forks[firstodd].release();
		}
	}
}
