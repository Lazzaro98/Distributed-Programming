package Test;

import Socket.Server;
import Test_Agents.RMIAgent1;
import Test_Agents.SocketAgent1;

public class main {
	public static final int port = 1234;
	public static final String host = "localhost";

	public static void main(String[] args) throws InterruptedException {

//		Server server = new Server(port);
//		server.start();
//
//		Thread.sleep(2000);
//
//		SocketAgent1 agent1 = new SocketAgent1(host, port);
//		agent1.start();
//		SocketAgent1 agent2 = new SocketAgent1(host, port);
//		agent2.start();

		RMI.Server<Integer> server = new RMI.Server<>(port);
		server.start();

		Thread.sleep(2000);
		RMIAgent1 agent1 = new RMIAgent1(host, port);
		RMIAgent1 agent3 = new RMIAgent1(host, port);
		RMIAgent1 agent2 = new RMIAgent1(host, port);
		agent1.start();
		agent2.start();
		agent3.start();
	}
}
