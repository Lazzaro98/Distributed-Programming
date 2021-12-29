package RMI;

import java.rmi.RemoteException;

import Concurrent.UnisexBathroomConcurrent;

public class UnisexBathroomRMI implements UnisexBathroomRMIInterface{

	UnisexBathroomConcurrent bathroom;
	
	public UnisexBathroomRMI(UnisexBathroomConcurrent bathroom) {
		super();
		this.bathroom = bathroom;
	}

	@Override
	public void man(int id) throws RemoteException {
		this.bathroom.man(id);
	}

	@Override
	public void woman(int id) throws RemoteException {
		this.bathroom.woman(id);
	}

}
