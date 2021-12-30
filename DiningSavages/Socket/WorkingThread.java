package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Concurrent.Pot;
import Concurrent.PotInterface;

public class WorkingThread extends Thread {

	private PotInterface pot;
	private Socket client;

	public WorkingThread(Socket client, PotInterface pot2) {
		this.client = client;
		this.pot = pot2;
	}

	@Override
	public void run() {
		try (Socket client = this.client;
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			String operation = in.readUTF();
			if ("eat".equalsIgnoreCase(operation)) {
				Integer type = in.readInt();
				System.out.println(""+type);
				pot.eat(type);
				
				out.writeUTF("ACK");
				out.flush();
				System.out.println("SERVER: ACK-ed");
			} else {
				System.out.println("ERROR: Received request for an unknown operation.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
