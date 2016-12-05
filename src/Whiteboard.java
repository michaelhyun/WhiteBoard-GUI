import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Whiteboard extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String PROJECT_FRAME_NAME = "Whiteboard";
	WhiteBoardController controller;

	public Whiteboard(WhiteBoardController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		controller.attachView(this);
		showGUI();
	}

	private void showGUI() {
		JFrame frame = new JFrame(PROJECT_FRAME_NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(700, 700));

		// create canvas
		Canvas canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(400, 400));

		// create control panel for buttons
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		Box top = Box.createHorizontalBox();
		top.setAlignmentX(Box.LEFT_ALIGNMENT);
		JLabel addLabel = new JLabel("Add");
		JButton rectButton = new JButton("Rect");
		JButton ovalButton = new JButton("Oval");
		JButton lineButton = new JButton("Line");
		JButton textButton = new JButton("Text");
		top.add(addLabel);
		top.add(rectButton);
		top.add(ovalButton);
		top.add(lineButton);
		top.add(textButton);

		// action listeners for adding rectangle
		rectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				DShapeModel model = new DRectModel();
				model.setX(10);
				model.setY(10);
				model.setHeight(20);
				model.setWidth(20);
				model.setColor(Color.GRAY);
				canvas.addShape(model);
			}

		});
		// action listeners for adding Oval
		ovalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				DShapeModel model = new DOvalModel();
				model.setX(10);
				model.setY(10);
				model.setWidth(20);
				model.setHeight(20);
				model.setColor(Color.GRAY);
				canvas.addShape(model);
			}

		});
		// action listeners for adding Line
		lineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				DShapeModel model = new DLineModel();
				model.setX(10);
				model.setY(10);
				model.setWidth(20);
				model.setHeight(20);
				model.setColor(Color.GRAY);
				canvas.addShape(model);
			}

		});
		// action listeners for adding Text
		textButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				DTextModel model = new DTextModel();

				// font must be set in this order
				model.setColor(Color.GRAY);
				model.setText("Hello");
				model.setFontName("Dialog");
				model.setFontStyle(Font.PLAIN);
				model.setFontSize(30);
				model.setX(10);
				model.setY(10);
				model.setWidth(20);
				model.setHeight(20);
				canvas.addShape(model);
			}

		});

		Box middle = Box.createHorizontalBox();
		middle.setAlignmentX(Box.LEFT_ALIGNMENT);
		JButton setColorButton = new JButton("Set Color");
		middle.add(setColorButton);

		// action listeners for setting color
		setColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Color newColor = JColorChooser.showDialog(null, "Choose a color: ", Color.GREEN);
				canvas.colorUpdated(newColor);
			}

		});

		Box middleBot = Box.createHorizontalBox();
		middleBot.setAlignmentX(Box.LEFT_ALIGNMENT);
		JTextArea textArea = new JTextArea();
		JButton edwardianButton = new JButton("Edwardian Script");
		middleBot.add(textArea);
		middleBot.add(edwardianButton);

		// action listeners for adding Text
		edwardianButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				DTextModel model = new DTextModel();
				String text = textArea.getText();
				textArea.setText("");
				model.setColor(Color.GRAY);
				model.setText(text);
				model.setFontName("EdwardianScriptITC");
				model.setFontStyle(Font.PLAIN);
				model.setFontSize(30);
				model.setX(0);
				model.setY(0);
				canvas.addShape(model);
			}

		});

		Box bottom = Box.createHorizontalBox();
		bottom.setAlignmentX(Box.LEFT_ALIGNMENT);
		JButton frontButton = new JButton("Move to Front");
		JButton backButton = new JButton("Move to Back");
		JButton removeButton = new JButton("Remove Shape");
		bottom.add(frontButton);
		bottom.add(backButton);
		bottom.add(removeButton);

		// action listeners for moving to front
		frontButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// move object in list
				canvas.moveToBack();

			}

		});
		// action listeners for moving to back
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// move object in list
				canvas.moveToFront();
			}

		});
		// action listeners for removing shape
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// remove shape
				canvas.removeShape();
			}

		});

		Box saveBox = Box.createHorizontalBox();
		saveBox.setAlignmentX(LEFT_ALIGNMENT);
		JButton saveButton = new JButton("Save");
		JButton openButton = new JButton("Open");
		JButton saveImageButton = new JButton("Save Image");
		saveBox.add(saveButton);
		saveBox.add(openButton);
		saveBox.add(saveImageButton);

		// action listeners for save
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// save file
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder;
				try {
					docBuilder = docFactory.newDocumentBuilder();
					// root elements
					Document doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("shapes");
					doc.appendChild(rootElement);
					rootElement = canvas.getRootElementForXML(rootElement);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					// StreamResult result = new StreamResult(new
					// File("C:\\file.xml"));

					StreamResult result = new StreamResult(System.out);
					transformer.transform(source, result);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		// action listeners for opening file
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// open file
			}

		});
		// action listeners for saving image
		saveImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// save image

			}

		});

		Box leftPanel = Box.createVerticalBox();
		leftPanel.add(top);
		leftPanel.add(middle);
		leftPanel.add(middleBot);
		leftPanel.add(bottom);
		leftPanel.add(saveBox);

		controlPanel.add(leftPanel, BorderLayout.WEST);

		frame.add(controlPanel, BorderLayout.WEST);
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

}
