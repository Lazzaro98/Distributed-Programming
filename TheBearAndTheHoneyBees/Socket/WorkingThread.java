package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Concurrent.BeesConcurrentInterface;

public class WorkingThread extends Thread {

	private Socket client;
	private BeesConcurrentInterface honeyPot;

	public WorkingThread(Socket client, BeesConcurrentInterface honeyPot) {
		this.client = client;
		this.honeyPot = honeyPot;
	}

	@Override
	public void run() {

		try (Socket s = this.client;
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(s.getInputStream())) {
			String operation = in.readUTF();
			if("bee".equalsIgnoreCase(operation)) {
				honeyPot.bee();
				
				out.writeUTF("ACK");
				out.flush();
			}
			else if ("bear".equalsIgnoreCase(operation)) {
				honeyPot.bear();
				
				out.writeUTF("ACK");
				out.flush();
			} else {
				System.out.println("SERVER: Received unknown operation.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
