package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UnisexBathroomRMIInterface extends Remote{
	public void man(int id) throws RemoteException;
	public void woman(int id) throws RemoteException;
}
