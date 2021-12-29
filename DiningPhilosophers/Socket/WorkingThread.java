package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import Concurrent.Philosopher;
import Concurrent.Table;

public class WorkingThread extends Thread {
	private Socket client;
	private Table table;

	public WorkingThread(Socket client2, Table table) {
		this.client = client2;
		this.table = table;
	}

	@Override
	public void run() {

		try (Socket s = this.client;
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(s.getInputStream())) {

			int id = in.readInt();
			String ret = this.table.produbi(id);
			out.writeUTF(ret);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
