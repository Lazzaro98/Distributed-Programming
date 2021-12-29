package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Concurrent.UnisexBathroomConcurrentInterface;

public class WorkingThread extends Thread{

	private Socket client;
	private UnisexBathroomConcurrentInterface bathroom;
	public WorkingThread(Socket client, UnisexBathroomConcurrentInterface bathroom) {
		this.client = client;
		this.bathroom = bathroom;
	}

	@Override
	public void run() {
		try(ObjectOutputStream out = new ObjectOutputStream(this.client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(this.client.getInputStream())) {
			String operation = in.readUTF();
			if("man".equalsIgnoreCase(operation)) {
				Integer id = in.readInt();
				bathroom.man(id);
				out.writeUTF("ACK");
				out.flush();
			}else if ("woman".equalsIgnoreCase(operation)) {
				Integer id = in.readInt();
				bathroom.woman(id);
				out.writeUTF("ACK");
				out.flush();
			}else {
				System.out.println("Unknown operation received.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
