package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProducerAndConsumerRMIInterface<T> extends Remote{
	public T get() throws RemoteException;
	public void put(T item) throws RemoteException;
}
