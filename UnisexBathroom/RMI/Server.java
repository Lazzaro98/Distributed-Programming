package RMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Concurrent.UnisexBathroomConcurrent;

public class Server extends Thread {
	private int port;
	private UnisexBathroomConcurrent bathroom;

	public Server(int port, UnisexBathroomConcurrent bathroom) {
		super();
		this.port = port;
		this.bathroom = bathroom;
	}

	@Override
	public void run() {
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			UnisexBathroomRMIInterface bathroomrmi = new UnisexBathroomRMI(bathroom);
			UnisexBathroomRMIInterface stub = (UnisexBathroomRMIInterface) UnicastRemoteObject.exportObject(bathroomrmi,
					0);
			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind("/bathroom", stub);
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

}
