package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	private String host;
	private int port;

	public Client(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public void eat(int type) {
		try (Socket s = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(s.getInputStream())) {

			out.writeUTF("eat");
			out.flush();
			out.writeInt(type);
			out.flush();

			in.readUTF();// ack
			System.out.println("CLIENT: ACK-ed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
