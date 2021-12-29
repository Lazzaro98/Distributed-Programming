package RMI;

import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

import Concurrent.Table;

public class TableRMIImpl implements TableRMI {
	private Table table;

	public TableRMIImpl(Table table) {
		this.table = table;
	}

	@Override
	public String probudi(int id) throws RemoteException {
		return this.table.produbi(id);
	}

}
