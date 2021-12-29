package Test;

import Agents.RMIAgent1;
import Agents.SocketAgent1;
import Concurrent.UnisexBathroomConcurrent;
import Socket.Server;

public class Test {
	public static final String host = "localhost";
	public static final int port_socket = 1233;
	public static final int port_rmi = 1234;

	public static void main(String[] args) throws InterruptedException {
		UnisexBathroomConcurrent bathroom = new UnisexBathroomConcurrent();

		Server serversocket = new Server(port_socket, bathroom);
		RMI.Server serverrmi = new RMI.Server(port_rmi, bathroom);
		serversocket.start();
		serverrmi.start();

		Thread.sleep(1000); // sacekamo da se podignu serveri

		// dodamo socket test agente
		SocketAgent1 ms1 = new SocketAgent1(host, port_socket, 'm');
		SocketAgent1 ms2 = new SocketAgent1(host, port_socket, 'm');
		SocketAgent1 zs1 = new SocketAgent1(host, port_socket, 'z');
		SocketAgent1 zs2 = new SocketAgent1(host, port_socket, 'z');
		ms1.start();
		ms2.start();
		zs1.start();
		zs2.start();

		// dodamo RMI test agente
		RMIAgent1 mr1 = new RMIAgent1(host, port_rmi, 'm');
		RMIAgent1 mr2 = new RMIAgent1(host, port_rmi, 'm');
		RMIAgent1 zr1 = new RMIAgent1(host, port_rmi, 'z');
		RMIAgent1 zr2 = new RMIAgent1(host, port_rmi, 'z');
		mr1.start();
		mr2.start();
		zr1.start();
		zr2.start();

	}
}
