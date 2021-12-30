package RMI;

import java.rmi.RemoteException;

import Concurrent.PotInterface;

public class RMI implements RMIInterface{

	
	PotInterface pot;
	
	
	public RMI(PotInterface pot) {
		this.pot = pot;
	}


	@Override
	public int cook() throws RemoteException {
		return this.pot.cook();
	}

}
