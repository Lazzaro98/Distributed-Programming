package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements ClientInterface {

	private String host;
	private int port;

	public Client(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	@Override
	public void man(int id) {
		try (Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			out.writeUTF("man");
			out.flush();
			out.writeInt(id);
			out.flush();

			in.readUTF();// ACK

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void woman(int id) {
		try (Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			out.writeUTF("woman");
			out.flush();
			out.writeInt(id);
			out.flush();

			in.readUTF();// ACK

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
