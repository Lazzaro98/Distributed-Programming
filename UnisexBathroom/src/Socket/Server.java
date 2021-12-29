package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Concurrent.UnisexBathroomConcurrent;
import Concurrent.UnisexBathroomConcurrentInterface;

public class Server extends Thread {
	private int port;

	UnisexBathroomConcurrentInterface bathroom;

	public Server(int port, UnisexBathroomConcurrentInterface bathroom) {
		super();
		this.port = port;
		this.bathroom = bathroom;
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(this.port);
			while (true) {
				Socket client = serverSocket.accept();
				new WorkingThread(client, bathroom).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
