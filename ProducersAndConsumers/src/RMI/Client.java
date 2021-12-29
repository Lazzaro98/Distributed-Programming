package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client<T> implements ProducerAndConsumerRMIInterface<T> {

	private String host;
	private int port;

	ProducerAndConsumerRMIInterface<T> pc;

	public Client(String host, int port) {
		super();
		this.host = host;
		this.port = port;

		try {
			if (System.getSecurityManager() == null)
				System.setSecurityManager(new SecurityManager());

			Registry reg = LocateRegistry.getRegistry(host, port);
			pc = (ProducerAndConsumerRMIInterface<T>) reg.lookup("/pc");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public T get() throws RemoteException {
		return this.pc.get();
	}

	@Override
	public void put(T item) throws RemoteException {
		this.pc.put(item);
	}

}
