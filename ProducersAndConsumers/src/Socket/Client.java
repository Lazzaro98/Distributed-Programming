package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client<T> implements Client_interface<T> {

	private String host;
	private int port;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get() {
		T return_item = null;
		try (Socket client = new Socket(this.host, this.port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {

			out.writeUTF("get");// operation
			out.flush();
			return_item = (T) in.readObject();
			out.writeUTF("ACK");
			out.flush();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return return_item;
	}

	@Override
	public void put(T item) {
		try (Socket client = new Socket(this.host, this.port);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {

			out.writeUTF("put");// operation
			out.flush();
			out.writeObject(item);

			in.readUTF();// ACK
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
