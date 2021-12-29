package Test_Agents;

import java.rmi.RemoteException;
import java.util.Random;

import RMI.Client;

public class RMIAgent1 extends Thread {
	Client<Integer> client;

	private static int ids = 1;

	private String host;
	private int port;

	public RMIAgent1(String host, int port) {
		super("RMI_CLIENT_" + ids++);
		this.host = host;
		this.port = port;
		this.client = new Client<Integer>(host, port);
	}

	@Override
	public void run() {
		try {
			while (true) {
				Random r = new Random();
				Integer item1 = r.nextInt(100);
				Integer item2 = r.nextInt(100);

				this.client.put(item1);
				this.client.put(item2);

				System.out.println("CLIENT [ " + getName() + " ] " + " just put " + item1 + " and " + item2);
				Thread.sleep(2000);

				Integer received_item1 = this.client.get();
				Integer received_item2 = this.client.get();
				// Integer received_item3 = this.client.get();

				System.out
						.println("CLIENT [ " + getName() + " ] just got " + received_item1 + " and " + received_item2);

			}
		} catch (RemoteException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
