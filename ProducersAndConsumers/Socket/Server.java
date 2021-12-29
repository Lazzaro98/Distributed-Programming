package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Concurrent.ProducerAndConsumer_concurrent;
import Concurrent.ProducerAndConsumer_concurrent_interface;

public class Server extends Thread {

	public static final int N = 10;
	private int port;

	ServerSocket serverSocket;
	ProducerAndConsumer_concurrent_interface<Integer> pc;

	public Server(int port) {
		super();
		this.port = port;
		this.pc = new ProducerAndConsumer_concurrent<>(N);
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket client = serverSocket.accept();
				new WorkingThread(client, pc).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
