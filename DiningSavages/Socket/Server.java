package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Concurrent.Pot;
import Concurrent.PotInterface;

public class Server extends Thread{
	private int port;

	private ServerSocket serverSocket;
	private PotInterface pot;
	
	public Server(int port, PotInterface pot2) {
		super();
		this.port = port;
		this.pot = pot2;
	}
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			while(true) {
				Socket client = serverSocket.accept();
				new WorkingThread(client, pot).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.run();
	}
	
	
	
}
