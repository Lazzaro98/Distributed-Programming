package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	private String host;
	private int port;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void probudi(int id) {
		try (Socket s = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(s.getInputStream())) {
			out.writeInt(id);
			out.flush();
			String msg = in.readUTF();
			System.out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
