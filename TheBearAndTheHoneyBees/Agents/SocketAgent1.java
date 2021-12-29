package Agents;

import Socket.Client;

public class SocketAgent1 extends Thread {
	private int port;
	private String host;

	private Client client;
	private int type;

	public SocketAgent1(String host,int port, int type) {
		super();
		this.port = port;
		this.host = host;
		this.type = type;
		this.client = new Client(port, host);
	}

	@Override
	public void run() {
		while (true) {
			if (this.type == 0)
				this.client.bear();
			else
				this.client.bee();
		}
	}

}
