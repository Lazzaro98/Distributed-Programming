package Agents;

import java.util.Random;

import Socket.Client;

// run: Ide u vc, spava Random 0-5000ms, ponovo ide u vc,...
public class SocketAgent1 extends Thread {

	private int id;
	private String host;
	private int port;
	private char gender;

	private Client client;

	public SocketAgent1(String host, int port, char gender) {
		this.client = new Client(host, port);
		this.gender = gender;
		if (gender == 'm' || gender == 'M')
			id = RMIAgent1.idm++;
		else if (gender == 'z' || gender == 'Z')
			id = RMIAgent1.idz++;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (this.gender == 'm' || this.gender == 'M')
					this.client.man(id);
				else if (this.gender == 'z' || this.gender == 'Z')
					this.client.woman(id);

				Thread.sleep((new Random()).nextInt(5000));

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
