package RMI;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Concurrent.BeesConcurrentInterface;

public class Server extends Thread {
	private int port;
	BeesConcurrentInterface honey_pot;

	public Server(int port, BeesConcurrentInterface honey_pot) {
		super();
		this.port = port;
		this.honey_pot = honey_pot;

	}

	@Override
	public void run() {
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			BeesConcurrentRMIInterface honey_pot_rmi = new BeesConcurrentRMI(honey_pot);
			BeesConcurrentRMIInterface stub =  (BeesConcurrentRMIInterface) UnicastRemoteObject.exportObject(honey_pot_rmi, 0);
			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind("/honeypot", stub);
		} catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
