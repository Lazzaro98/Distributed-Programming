package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import Concurrent.Philosopher;
import Concurrent.Table;

public class Server extends Thread {

	private int port;
	private ServerSocket serverSocket;
	private Table table;

	public Server(int port, Table table) {
		this.port = port;
		this.table = table;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket client = serverSocket.accept();
				new WorkingThread(client, table).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
