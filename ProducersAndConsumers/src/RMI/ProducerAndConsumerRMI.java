package RMI;

import java.rmi.RemoteException;

import Concurrent.ProducerAndConsumer_concurrent;
import Concurrent.ProducerAndConsumer_concurrent_interface;

public class ProducerAndConsumerRMI<T> implements ProducerAndConsumerRMIInterface<T>{

	public static final int size = 10;
	
	ProducerAndConsumer_concurrent_interface<T> pc = new ProducerAndConsumer_concurrent<>(size);
	
	@Override
	public T get() throws RemoteException {
		return pc.get();
	}

	@Override
	public void put(T item) throws RemoteException {
		pc.put(item);
	}

}
