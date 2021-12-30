package Agents;

import RMI.Client;

public class RMIAgent1 extends Thread {

	private Client client;
	private int numOfTypes;

	public RMIAgent1(String host, int port, int numOfTypes) {
		this.numOfTypes = numOfTypes;
		client = new Client(host, port);
	}

	@Override
	public void run() {
		while (true) {
			client.cook();
		}
	}

}
