package RMI;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

import Concurrent.Table;

public class Server extends Thread {
	private int port;

	private Table table;

	public Server(int port, Table table) {
		this.port = port;
		this.table = table;
	}

	@Override
	public void run() {
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			TableRMI tablermi = new TableRMIImpl(table);
			TableRMI stub = (TableRMI) UnicastRemoteObject.exportObject(tablermi, 0);
			Registry reg = LocateRegistry.createRegistry(port);
			reg.bind("/table", stub);
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
