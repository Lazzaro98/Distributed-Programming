package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	private String host;
	private int port;

	private BeesConcurrentRMIInterface honey_pot;

	public Client(String host, int port) {
		super();
		this.host = host;
		this.port = port;

		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			Registry reg = LocateRegistry.getRegistry(host, port);
			honey_pot = (BeesConcurrentRMIInterface) reg.lookup("/honeypot");

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public void bee() throws RemoteException {
		this.honey_pot.bee();
	}

	public void bear() throws RemoteException {
		this.honey_pot.bear();
	}

}
