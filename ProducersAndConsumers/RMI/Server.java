package RMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server<T> extends Thread {

	private int port;

	public Server(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			ProducerAndConsumerRMIInterface<T> pc = new ProducerAndConsumerRMI<T>();
			ProducerAndConsumerRMIInterface<T> stub = (ProducerAndConsumerRMIInterface<T>) UnicastRemoteObject
					.exportObject(pc, 0);

			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind("/pc", stub);
			System.out.println("Server is ready and is listening for new connections...");
		} catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
