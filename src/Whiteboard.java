import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
		GraphicsEnvironment graphEnviron = 
			       GraphicsEnvironment.getLocalGraphicsEnvironment();
			Font[] allFonts = graphEnviron.getAllFonts();

		JComboBox<Font> fontBox = new JComboBox<>(allFonts);
		fontBox.setSelectedIndex(0);
        fontBox.setPreferredSize(new Dimension(100, 60));
		fontBox.setRenderer(new DefaultListCellRenderer() {
		   @Override
		   public Component getListCellRendererComponent(JList<?> list,
		         Object value, int index, boolean isSelected, boolean cellHasFocus) {
		      if (value != null) {
		         Font font = (Font) value;
		         value = font.getName();
		      }
		      return super.getListCellRendererComponent(list, value, index,
		            isSelected, cellHasFocus);
		   }
		});
		middleBot.add(textArea);
		middleBot.add(fontBox);

		// action listeners for adding Text
		fontBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Font font = (Font)fontBox.getSelectedItem();
				
				DTextModel model = new DTextModel();
				String text = textArea.getText();

//				textArea.setText("");
//				model.setColor(Color.GRAY);
//				model.setText(text);
//				model.setFontName("EdwardianScriptITC");
//				model.setFontStyle(Font.PLAIN);
//				model.setFontSize(30);
//				model.setX(0);
//				model.setY(0);
//				canvas.addShape(model);
				
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
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);

					fileChooser.showSaveDialog(null);
					StreamResult result = new StreamResult(fileChooser.getSelectedFile());

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
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);

				fileChooser.showOpenDialog(null);
				File fXmlFile = fileChooser.getSelectedFile();
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = null;
				try {
					dBuilder = dbFactory.newDocumentBuilder();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Document doc = null;
				try {
					doc = dBuilder.parse(fXmlFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
			}

		});
		// action listeners for saving image
		saveImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// save image
				BufferedImage bi = new BufferedImage(canvas.getSize().width, canvas.getSize().height,
						BufferedImage.TYPE_INT_ARGB);
				Graphics g = bi.createGraphics();
				canvas.paint(g); // this == JComponent
				g.dispose();
				try {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);

					fileChooser.showSaveDialog(null);
					ImageIO.write(bi, "png", fileChooser.getSelectedFile());
				} catch (Exception e) {
				}
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