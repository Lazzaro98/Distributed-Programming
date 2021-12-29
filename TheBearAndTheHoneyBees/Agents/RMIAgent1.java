package Agents;

import java.rmi.RemoteException;

import Socket.Client;

public class RMIAgent1 extends Thread {
	private int port;
	private String host;

	private RMI.Client client;
	private int type;

	public RMIAgent1(String host, int port, int type) {
		super();
		this.port = port;
		this.host = host;
		this.type = type;
		this.client = new RMI.Client(host, port);
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (this.type == 0)
					this.client.bear();
				else
					this.client.bee();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
