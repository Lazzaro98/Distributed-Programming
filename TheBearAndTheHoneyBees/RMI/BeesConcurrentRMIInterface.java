package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BeesConcurrentRMIInterface extends Remote{
	public void bear() throws RemoteException;
	public void bee() throws RemoteException;
}
