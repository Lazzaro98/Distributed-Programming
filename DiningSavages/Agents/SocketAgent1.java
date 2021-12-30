package Agents;

import java.util.Random;

import Socket.Client;

public class SocketAgent1 extends Thread {

	private Client client;
	private int numOfTypes;

	public SocketAgent1(String host, int port, int numOfTypes) {
		client = new Client(host, port);
		this.numOfTypes = numOfTypes;
	}

	@Override
	public void run() {
		while (true) {
			client.eat((new Random()).nextInt(numOfTypes));
		}
	}

}
