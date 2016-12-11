import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;

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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
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
	JTextArea textArea;
	JComboBox<String> fontBox;
	Canvas canvas;
	

	public Whiteboard(WhiteBoardController controller) {
		// TODO Auto-generated constructor stub
		canvas = new Canvas(this);
		this.controller = controller;
		controller.attachView(this);
		showGUI();
	}
    
	private void showGUI() {
		JFrame frame = new JFrame(PROJECT_FRAME_NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setMinimumSize(new Dimension(800, 800));

		// create canvas
//		canvas.setMinimumSize(new Dimension(700, 700));

		// create control panel for buttons
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.setPreferredSize(new Dimension(400,700));


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
				DLineModel model = new DLineModel();
				model.setP1(new Point(10,10));
				model.setP2(new Point(40,40));
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
				model.setWidth(50);
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

		Box middleBox = Box.createHorizontalBox();
		middleBox.setAlignmentX(Box.LEFT_ALIGNMENT);
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(300,50));
		GraphicsEnvironment graphEnviron = 
			       GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = graphEnviron.getAvailableFontFamilyNames();
			
		fontBox = new JComboBox<String>(fonts);
		fontBox.setSelectedIndex(0);
        fontBox.setMaximumSize(new Dimension(70, 50));
		fontBox.setRenderer(new DefaultListCellRenderer() {

		   public Component getListCellRendererComponent(JList<?> list, Object value, int index) {
		      return getListCellRendererComponent(list, value, index);
		   }
		});
		middleBox.add(textArea);
		middleBox.add(fontBox);
		// action listeners for adding Text
		fontBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String fontName = (String) fontBox.getSelectedItem();
				canvas.changeFont(fontName);
			}

		});
		
		textArea.addKeyListener(new KeyAdapter() {

		      public void keyReleased(KeyEvent ke) {
				String text = textArea.getText();
				canvas.changeText(text);

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

		Box serverBox = Box.createHorizontalBox();
		serverBox.setAlignmentX(LEFT_ALIGNMENT);
		JButton serverStartButton = new JButton("Server Start");
		JButton clientStartButton = new JButton("Client Start");
		serverBox.add(serverStartButton);
		serverBox.add(clientStartButton);

		serverStartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int portNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter port number for server:"));
				new Thread(){
					@Override
					public void run(){
						ServerSocket serverSocket = null;
						Socket socket = null;

						try {
							serverSocket = new ServerSocket(portNumber);
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						
						for(;;){
							try{
								socket = serverSocket.accept();
							}catch (IOException e2) {
								e2.printStackTrace();
							}
							new ClientThread(socket,canvas).start();
						}
					}
				}.start();
				
			}

		});

		clientStartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Box leftPanel = Box.createVerticalBox();
		leftPanel.add(top);
		leftPanel.add(middle);
		leftPanel.add(middleBox);
		leftPanel.add(bottom);
		leftPanel.add(saveBox);
		leftPanel.add(serverBox);

		DataPanel dataPanel = new DataPanel();
		controlPanel.add(leftPanel, BorderLayout.NORTH);
		controlPanel.add(dataPanel, BorderLayout.SOUTH);
		
		frame.add(controlPanel, BorderLayout.WEST);
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	
	
	
	
	
	
	
	
private class DataPanel extends JPanel{
		
        JTable table;
        DataModel dataModel;
        String[] names;

        public DataPanel(){
            dataModel = new DataModel(this);
            this.setLayout(new BorderLayout());
            names = new String[]{"X", "Y", "Width", "Height"};

            Integer[][] tableData = new Integer[4][4];

            table = new JTable(dataModel.data, names);
//            table.setModel(dataModel);

            TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
            JLabel headerLabel = (JLabel) rendererFromHeader;
            headerLabel.setHorizontalAlignment(JLabel.CENTER);
            JScrollPane tableScroll = new JScrollPane(table);
            tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.add(tableScroll);
            this.setPreferredSize(new Dimension(300, 500));
        }
        
        
        

    private class DataModel extends AbstractTableModel implements ModelListener{
        private Integer[][] data;
        DataPanel tablePanel;
        public DataModel(DataPanel tablePanel){
            this.tablePanel = tablePanel;
            data = new Integer[0][4];
        }

        @Override
        public int getRowCount() {
            return canvas.shapesList.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

                DShape temp = canvas.shapesList.get(canvas.shapesList.size() - rowIndex - 1);
                
                DShapeModel model;
				if (temp instanceof DRect) {
					model = ((DRect) temp).model;
				} else if (temp instanceof DOval) {
					model = ((DOval) temp).model;
				} else if (temp instanceof DText) {
					model = ((DText) temp).model;
				} else if (temp instanceof DLine) {
					model = ((DLine) temp).model;
				}
				else{
					model = null;
				}
        		
                if (columnIndex == 0) {
                    return model.getX();
                } else if (columnIndex == 1) {
                    return model.getY();
                } else if (columnIndex == 2) {
                    return model.getWidth();
                } else {
                    return model.getHeight();
                }

        }

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 4;
		}

        public void fillTable(){
            data = new Integer[getRowCount()][4];
            for(int i = 0; i < getRowCount(); i++){
                for(int j = 0; j < 4; j++) {
                    data[i][j] = (int)getValueAt(i, j);
                }
            }
        }

        public void removed(){
            fillTable();
            fireTableDataChanged();
        }

        public void added(){
            fillTable();
            fireTableDataChanged();
        }

        @Override
        public void modelChanged(DShapeModel model) {
//            int index = 0;
//            for(DShape shape: canvas.shapesList){
//                if(shape.model.equals(model)){
//                    index = canvas.shapesList.indexOf(shape);
//                }
//            }
//            fireTableRowsUpdated(index, index);

        }
    }
}
}