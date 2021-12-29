package Concurrent;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;

public class ProducerAndConsumer_concurrent<T> implements ProducerAndConsumer_concurrent_interface<T> {

	T buffer[];
	int nextin, nextout, count;

	private int size;

	@SuppressWarnings("unchecked")
	public ProducerAndConsumer_concurrent(int size) {
		buffer = (T[]) new Object[size];
		nextin = nextout = count = 0;
		this.size = size;
	}

	@Override
	public synchronized T get() {
		T return_item = null;
		try {
			if (0 == count)
				wait();

			return_item = buffer[nextout];
			buffer[nextout] = null;
			nextout = (nextout + 1) % this.size;
			this.count--;

			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return return_item;
	}

	@Override
	public synchronized void put(T item) {
		try {
			if (this.size == count)
				wait();

			buffer[nextin] = item;
			nextin = (nextin + 1) % this.size;
			this.count++;

			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Elements of the buffer are: ");
		for(T item:buffer)
			sb.append(item+"  ");
		return sb.toString();
	}
	
	

}
