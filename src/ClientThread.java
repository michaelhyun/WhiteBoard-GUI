import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread implements Observer {
	protected Socket socket;
	private Canvas canvas;
	PrintWriter out = null;

	public ClientThread(Socket socket, Canvas canvas) {
		this.socket = socket;
		this.canvas = canvas;
		this.canvas.attach(this);
	}

	@Override
	public void run() {
		System.out.println("Client on thread:" + Thread.currentThread().getId() + " connected");
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println("Server: " + inputLine);

				if (inputLine.equals("BYE"))
					break;
			}

			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (out != null) {
			out.println("selected shape index: " + canvas.getSelectedShapeIndex());
			//send xml
		}
	}
}
