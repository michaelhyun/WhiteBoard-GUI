import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread{
	
	private Canvas canvas;
	private int portNumber;
	
	public Client(Canvas canvas, int portNumber){
		this.canvas = canvas;
		this.portNumber = portNumber;
	}
	
	
	@Override
	public void run(){
		String serverHostname = new String("192.168.1.129");

		System.out.println("Attemping to connect to host " + serverHostname + " on port "+ portNumber);

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			echoSocket = new Socket(serverHostname, portNumber);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverHostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: " + serverHostname);
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Type Message (\"Bye.\" to quit)");
		while(true){
			try{
				String serverMessage = in.readLine();
				System.out.println("message from server: " + serverMessage);
				canvas.clearCanvas();
				if(serverMessage.equals("BYE")){
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}

		out.close();
		try{
			in.close();
			stdIn.close();
			echoSocket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
