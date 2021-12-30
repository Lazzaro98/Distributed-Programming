package Concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Pot implements PotInterface {

	private int numOfTypes;
	private int sizeOfPot;

	private Semaphore cookerSleep;
	private Semaphore[] mutex;
	private Semaphore[] okToEat;
	private int[] food;

	private Semaphore queueMutex;
	private List<Integer> queue = new LinkedList<Integer>();

	public Pot(int numOfTypes, int sizeOfPot) {
		this.numOfTypes = numOfTypes;
		this.sizeOfPot = sizeOfPot;

		cookerSleep = new Semaphore(0);
		queueMutex = new Semaphore(1);

		mutex = new Semaphore[numOfTypes];
		okToEat = new Semaphore[numOfTypes];
		food = new int[numOfTypes];

		for (int i = 0; i < numOfTypes; i++) {
			mutex[i] = new Semaphore(1);
			okToEat[i] = new Semaphore(0);
			food[i] = 0;
		}
	}

	@Override
	public int cook() {
		System.out.println("test");
		cookerSleep.acquireUninterruptibly();

		queueMutex.acquireUninterruptibly();
		int type = queue.remove(0);
		queueMutex.release();

		food[type] = this.sizeOfPot;
		okToEat[type].release();
		System.out.println("Kuvar je skarabudzio jelo sa ID-jem " + type);
		return type;
	}

	@Override
	public void eat(int type) {
		mutex[type].acquireUninterruptibly();

		if (food[type] == 0) {
			queueMutex.acquireUninterruptibly();
			queue.add(type);
			queueMutex.release();
			cookerSleep.release();
			okToEat[type].acquireUninterruptibly();
		}

		food[type]--;
		mutex[type].release();
		System.out.println("Ljudozder je pokrkao jelo sa rednim brojem " + type);
	}

}
