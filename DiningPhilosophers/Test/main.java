package Test;

import java.util.concurrent.Semaphore;

import Concurrent.Table;
import Socket.Client;
import Socket.Server;

public class main {

	public static final String host = "localhost";
	public static final int port_socket = 1234;
	public static final int port_rmi = 1233;

	public static final int N = 5;

	public static void main(String[] args) throws InterruptedException {

		Table table = new Table(N);
		Server server_socket = new Server(port_socket, table);
		server_socket.start();
		RMI.Server server_rmi = new RMI.Server(port_rmi, table);
		server_rmi.start();
		
		Thread.sleep(1000);//cekamo da se dignu serveri
		
		Client client_socket = new Client(host, port_socket);
		RMI.Client client_rmi = new RMI.Client(host, port_rmi);
		client_socket.probudi(1);
		client_socket.probudi(1);
		client_rmi.probudi(2);
		client_rmi.probudi(3);
		client_socket.probudi(3);
		client_socket.probudi(4);
		client_rmi.probudi(5);
		client_rmi.probudi(6);
		client_socket.probudi(7);
	}

}
