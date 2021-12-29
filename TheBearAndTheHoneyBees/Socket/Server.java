package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Concurrent.BeesConcurrentInterface;

public class Server extends Thread{
	private int port;

	private BeesConcurrentInterface honeyPot;
	private ServerSocket serverSocket;
	public Server(int port, BeesConcurrentInterface honeyPot) {
		super();
		this.port = port;
		this.honeyPot = honeyPot;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			while(true) {
				Socket client = serverSocket.accept();
				new WorkingThread(client, honeyPot).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
