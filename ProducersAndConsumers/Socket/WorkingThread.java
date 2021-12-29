package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Concurrent.ProducerAndConsumer_concurrent_interface;

public class WorkingThread<T> extends Thread {

	private Socket client;
	private ProducerAndConsumer_concurrent_interface<T> pc;

	public WorkingThread(Socket client, ProducerAndConsumer_concurrent_interface<T> pc) {
		this.client = client;
		this.pc = pc;
	}

	@Override
	public void run() {

		try (Socket s = client;
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(s.getInputStream())) {

			String operation = in.readUTF();

			if ("get".equalsIgnoreCase(operation)) {
				T item = pc.get();
				out.writeObject(item);
				// System.out.println("SERVER: Received GET request. Sending results back to
				// client [ " + item + "]");
				in.readUTF(); // ACK
				// System.out.println("SERVER: Successfully delievered results to client.\n");
				System.out.println("SOCKET_SERVER: Successfully finished GET operation");
			} else if ("put".equalsIgnoreCase(operation)) {
				T item = (T) in.readObject();
				// System.out.println("SERVER: Received PUT request. [PUT: " + item + " ] ");
				pc.put(item);
				// System.out.println("SERVER: Successfully finished operation PUT. Sending ACK
				// to Client...");
				out.writeUTF("ACK");
				out.flush();
				// System.out.println("SERVER: Client acknowledged successfully.\n");
				System.out.println("SOCKET_SERVER: Successfully finished PUT operation[ PUT:" + item + " ]");
			} else {
				System.out.println("Unknown operation.");
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
