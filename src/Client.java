import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Client extends Thread {

	private Canvas canvas;
	private int portNumber;

	public Client(Canvas canvas, int portNumber) {
		this.canvas = canvas;
		this.portNumber = portNumber;
	}

	@Override
	public void run() {
		String serverHostname = new String("192.168.1.129");

		System.out.println("Attemping to connect to host " + serverHostname + " on port " + portNumber);

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
		while (true) {
			try {
				String serverMessage = in.readLine();
				System.out.println("message from server: " + serverMessage);
				canvas.removeAll();
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder;
				try {
					builder = factory.newDocumentBuilder();
					Document doc = builder.parse(new InputSource(new StringReader(serverMessage)));
					doc.getDocumentElement().normalize();

					NodeList nList = doc.getElementsByTagName("shape");

					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;
							String shapeType = eElement.getElementsByTagName("type").item(0).getTextContent();

							if (!shapeType.equals("text")) {
								DShapeModel shapeModel = null; // applies for Rect,
																// Oval, Line
								if (shapeType.equals("rect")) {
									shapeModel = new DRectModel();
								} else if (shapeType.equals("oval")) {
									shapeModel = new DOvalModel();
								} else if (shapeType.equals("line")) {
									shapeModel = new DLineModel();
								}
								shapeModel.setX(
										Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent()));
								shapeModel.setY(
										Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent()));
								shapeModel.setWidth(
										Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
								shapeModel.setHeight(
										Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent()));
								shapeModel.setColor(new Color(
										Integer.parseInt(eElement.getElementsByTagName("color").item(0).getTextContent())));
								canvas.addShape(shapeModel);
							} else {
								DTextModel textModel = new DTextModel();
								textModel.setX(
										Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent()));
								textModel.setY(
										Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent()));
								textModel.setWidth(
										Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
								textModel.setHeight(
										Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent()));
								textModel.setColor(new Color(
										Integer.parseInt(eElement.getElementsByTagName("color").item(0).getTextContent())));
								textModel.setText(eElement.getElementsByTagName("text").item(0).getTextContent());
								textModel.setFontName(eElement.getElementsByTagName("fontName").item(0).getTextContent());
								textModel.setFontStyle(Integer
										.parseInt(eElement.getElementsByTagName("fontStyle").item(0).getTextContent()));
								textModel.setFontSize(Integer
										.parseInt(eElement.getElementsByTagName("fontSize").item(0).getTextContent()));
								canvas.addShape(textModel);
							}

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (serverMessage.equals("BYE")) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		out.close();
		try {
			in.close();
			stdIn.close();
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
