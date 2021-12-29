package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements ClientInterface {
	private int port;
	private String host;

	public Client(int port, String host) {
		super();
		this.port = port;
		this.host = host;
	}

	@Override
	public void bear() {
		try (Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			out.writeUTF("bear");
			out.flush();

			in.readUTF();// ACK

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bee() {
		try (Socket client = new Socket(host, port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
			out.writeUTF("bee");
			out.flush();

			in.readUTF();// ACK

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
