package Agents;

import java.rmi.RemoteException;
import java.util.Random;

import RMI.Client;

public class RMIAgent1 extends Thread{
	public static int idm = 1, idz = 1;
	private int id;
	private String host;
	private int port;
	private char gender;
	
	private Client client;

	public RMIAgent1(String host, int port, char gender) {
		this.client = new Client(host, port);
		this.gender = gender;
		if (gender == 'm' || gender == 'M')
			id = idm++;
		else if (gender == 'z' || gender == 'Z')
			id = idz++;
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
		} catch (InterruptedException | RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
