package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	private String host;
	private int port;
	UnisexBathroomRMIInterface bathroom;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;

		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());

		try {
			Registry reg = LocateRegistry.getRegistry(host, port);
			bathroom = (UnisexBathroomRMIInterface) reg.lookup("/bathroom");
			
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public void man(int id) throws RemoteException {
		this.bathroom.man(id);
	}
	public void woman(int id) throws RemoteException {
		this.bathroom.woman(id);
	}

}
