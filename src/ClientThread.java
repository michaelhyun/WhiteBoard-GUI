import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread implements Observer {
	protected Socket socket;
	private Canvas canvas;
	DataOutputStream out = null;

	public ClientThread(Socket socket, Canvas canvas) {
		this.socket = socket;
		this.canvas = canvas;
		this.canvas.attach(this);
	}

	@Override
	public void run() {
		System.out.println("Client on thread:" + Thread.currentThread().getId() + " connected");
		InputStream in = null;
		BufferedReader bufferedReader = null;
		
		try {
			in = socket.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(in));
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			return;
		}
		String line;
		
//		while (true) {
//			
//			try {
//				line = bufferedReader.readLine();
//				System.out.println("Client on thread: " + Thread.currentThread().getId() + " sent message: " + line);
//				if ((line == null) || line.equalsIgnoreCase("QUIT")) {
//					socket.close();
//					return;
//				} else {
//					out.writeBytes(line + "\n\r");
//					out.flush();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//				return;
//			}
//		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("selected shape index: " + canvas.getSelectedShapeIndex());
		try {
			out.writeBytes("oooo" + "\n\r");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
