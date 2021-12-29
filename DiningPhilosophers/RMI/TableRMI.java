package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TableRMI extends Remote{
	public String probudi(int id) throws RemoteException;
}
