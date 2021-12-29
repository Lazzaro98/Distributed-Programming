package Concurrent;

import java.io.Serializable;
import java.util.concurrent.Semaphore;

public class Table implements Serializable {
	int N;
	boolean[] aktivni;
	Semaphore[] forks;
	Philosopher[] philosophers;

	public Table(int N) {
		this.N = N;
		aktivni = new boolean[N + 1];
		forks = new Semaphore[N + 1];
		philosophers = new Philosopher[N + 1];
		for (int i = 0; i <= N; i++) {
			aktivni[i] = false;
			forks[i] = new Semaphore(1);
			philosophers[i] = new Philosopher(i, N, forks);
		}
	}

	public synchronized String produbi(int id) {
		if (id < 1 || id > N)
			return "ERROR: Nevalidan ID.";

		if (aktivni[id])
			return "ERROR: Filozofer_" + id + " je vec aktivan.";

		philosophers[id].start();
		aktivni[id] = true;
		return "SUCCESS: Filozofer_" + id + " je probudjen.";
	}

}
