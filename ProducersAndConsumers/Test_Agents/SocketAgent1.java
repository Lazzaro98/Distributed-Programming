package Test_Agents;

import java.util.Random;

import Socket.Client;

// Agent1 : put 2 random numbers, sleep 2s, get 3 numbers
public class SocketAgent1 extends Thread {

	private static int ids = 1;
	private Client client;

	private String host;
	private int port;

	public SocketAgent1(String host, int port) {
		super("SOCKET_CLIENT_" + ids++);
		this.host = host;
		this.port = port;
		this.client = new Client<Integer>(host, port);
	}

	@Override
	public void run() {
		try {
			while (true) {
				Random r = new Random();
				Integer random1 = r.nextInt(100);
				client.put(random1);
				Integer random2 = r.nextInt(100);
				client.put(random2);

				System.out.println(
						"Client[ " + getName() + " ] has just put 2 elements: " + random1 + "  and  " + random2);

				Thread.sleep(2000);

				Integer item1 = (Integer) client.get();
				Integer item2 = (Integer) client.get();
				// Integer item3 = (Integer) client.get();

				System.out.println(
						"CLIENT [ " + getName() + " ] has just gotten 3 elements: " + item1 + "  and  " + item2);

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
