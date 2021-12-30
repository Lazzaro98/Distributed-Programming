package Test;

import Agents.RMIAgent1;
import Agents.SocketAgent1;
import Concurrent.Pot;
import Concurrent.PotInterface;
import Socket.Client;
import Socket.Server;

public class main {
	public static final int port_socket = 1234;
	public static final int port_rmi = 1233;
	public static final String host = "localhost";

	public static void main(String[] args) {
		PotInterface pot = new Pot(10, 20);

		Server server_socket = new Server(port_socket, pot);
		server_socket.start();
		RMI.Server server_rmi = new RMI.Server(port_rmi, pot);
		server_rmi.start();
		// dizanje servera
		SocketAgent1 savage1 = new SocketAgent1(host, port_socket, 5);
		savage1.start();
		RMIAgent1 client_rmi = new RMIAgent1(host, port_rmi, 5);
		client_rmi.start();

	}
}
