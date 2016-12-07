import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
	protected Socket socket;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("Client on thread:" + Thread.currentThread().getId() + " connected");
		InputStream in = null;
		BufferedReader bufferedReader = null;
		DataOutputStream out = null;
		try {
			in = socket.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(in));
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			return;
		}
		String line;
		while (true) {
			try {
				line = bufferedReader.readLine();
				System.out.println("Client on thread: "+ Thread.currentThread().getId() +" sent message: " + line);
				if ((line == null) || line.equalsIgnoreCase("QUIT")) {
					socket.close();
					return;
				} else {
					out.writeBytes(line + "\n\r");
					out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
