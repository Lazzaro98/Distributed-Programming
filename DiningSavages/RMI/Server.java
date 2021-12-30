package RMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Concurrent.PotInterface;

public class Server extends Thread {
	private int port;

	PotInterface pot;

	public Server(int port, PotInterface pot) {
		super();
		this.port = port;
		this.pot = pot;
	}

	@Override
	public void run() {
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			RMIInterface potrmi = new RMI(pot);
			RMIInterface stub = (RMIInterface) UnicastRemoteObject.exportObject(potrmi, 0);
			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind("/pot", stub);
			System.out.println("RMI server is listening...");
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}

		super.run();
	}

}
