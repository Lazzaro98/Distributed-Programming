package RMI;

import java.rmi.RemoteException;

import Concurrent.BeesConcurrentInterface;

public class BeesConcurrentRMI implements BeesConcurrentRMIInterface {

	BeesConcurrentInterface honey_pot;

	public BeesConcurrentRMI(BeesConcurrentInterface honey_pot) {
		super();
		this.honey_pot = honey_pot;
	}

	@Override
	public void bear() throws RemoteException {
		this.honey_pot.bear();
	}

	@Override
	public void bee() throws RemoteException {
		this.honey_pot.bee();
	}

}
