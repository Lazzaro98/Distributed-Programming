package Test;

import Agents.RMIAgent1;
import Agents.SocketAgent1;
import Concurrent.BeesConcurrent;
import Concurrent.BeesConcurrentInterface;
import Socket.Server;

public class main {
	public static final int port_socket = 1234;
	public static final int port_rmi = 1233;

	public static final String host = "localhost";

	public static void main(String[] args) throws InterruptedException {
		BeesConcurrentInterface honey_pot = new BeesConcurrent();

		Server server_socket = new Server(port_socket, honey_pot);
		server_socket.start();
		RMI.Server server_rmi = new RMI.Server(port_rmi, honey_pot);
		server_rmi.start();
		Thread.sleep(1000);// sacekamo da se digne server

		RMIAgent1 bear = new RMIAgent1(host, port_rmi, 0);
		RMIAgent1 bee1 = new RMIAgent1(host, port_rmi, 1);
		RMIAgent1 bee2 = new RMIAgent1(host, port_rmi, 1);
		RMIAgent1 bee3 = new RMIAgent1(host, port_rmi, 1);
		bear.start();
		bee1.start();
		bee2.start();
		bee3.start();

		SocketAgent1 bear1 = new SocketAgent1(host, port_socket, 1);
		SocketAgent1 bee11 = new SocketAgent1(host, port_socket, 0);
		SocketAgent1 bee12 = new SocketAgent1(host, port_socket, 0);
		SocketAgent1 bee13 = new SocketAgent1(host, port_socket, 0);
		bear1.start();
		bee11.start();
		bee12.start();
		bee13.start();

	}
}
