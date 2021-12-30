package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	private String host;
	private int port;
	
	RMIInterface pot;
	
	public Client(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		
		if(System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		try {
			Registry reg = LocateRegistry.getRegistry(host, port);
			pot = (RMIInterface) reg.lookup("/pot");
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public int cook(){
		int ret = -1;
		try {
			ret = this.pot.cook();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
}
