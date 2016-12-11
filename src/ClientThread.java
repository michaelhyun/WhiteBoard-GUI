import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
			sendMessageToClient();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println("Server: " + inputLine);

				if (inputLine.equals("BYE"))
					break;
			}
			// test comment
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
		sendMessageToClient();
	}

	private void sendMessageToClient() {
		if (out != null) {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			Document doc = null;
			String xmlModel = "";
			try {
				docBuilder = docFactory.newDocumentBuilder();
				// root elements
				doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("shapes");
				doc.appendChild(rootElement);
				rootElement = canvas.getRootElementForXML(rootElement);
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(doc), new StreamResult(writer));
				xmlModel = writer.getBuffer().toString().replaceAll("\n|\r", "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// send xml representation of canvas
			out.println(xmlModel);
		}
	}
}
