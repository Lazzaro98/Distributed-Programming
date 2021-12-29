package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	private String host;
	private int port;

	TableRMI tablermi;

	public Client(String host, int port) {
		super();
		this.host = host;
		this.port = port;

		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			Registry reg = LocateRegistry.getRegistry(host, port);
			tablermi = (TableRMI) reg.lookup("/table");

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

	}

	public void probudi(int id) {
		try {
			System.out.println(this.tablermi.probudi(id));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
