import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Whiteboard extends JFrame {
	private final String PROJECT_FRAME_NAME = "Whiteboard";
	WhiteBoardController controller;

	public Whiteboard(WhiteBoardController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		controller.attachView(this);
		showGUI();
	}
	
	private void showGUI(){
		JFrame frame = new JFrame(PROJECT_FRAME_NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(700,700));
		
		//create canvas
		Canvas canvas = new Canvas();
		
		//create control panel for buttons
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
		
		
		Box middle = Box.createHorizontalBox();
		middle.setAlignmentX(Box.LEFT_ALIGNMENT);
		JButton setColorButton = new JButton("Set Color");	
		middle.add(setColorButton);
		

		Box middleBot = Box.createHorizontalBox();
		middleBot.setAlignmentX(Box.LEFT_ALIGNMENT);
		JTextArea textArea = new JTextArea();
		JButton edwardianButton = new JButton("Edwardian Script");
		middleBot.add(textArea);
		middleBot.add(edwardianButton);
		
		Box bottom = Box.createHorizontalBox();
		bottom.setAlignmentX(Box.LEFT_ALIGNMENT);
		JButton frontButton = new JButton("Move to Front");
		JButton backButton = new JButton("Move to Back");
		JButton removeButton = new JButton("Remove Shape");
		bottom.add(frontButton);
		bottom.add(backButton);
		bottom.add(removeButton);
		
		Box saveBox = Box.createHorizontalBox();
		saveBox.setAlignmentX(LEFT_ALIGNMENT);
		JButton saveButton = new JButton("Save");
		JButton openButton = new JButton("Open");
		JButton saveImageButton = new JButton("Save Image");
		saveBox.add(saveButton);
		saveBox.add(openButton);
		saveBox.add(saveImageButton);
		
		Box leftPanel = Box.createVerticalBox();
		leftPanel.add(top);
		leftPanel.add(middle);
		leftPanel.add(middleBot);
		leftPanel.add(bottom);
		leftPanel.add(saveBox);
		
		
		controlPanel.add(leftPanel, BorderLayout.WEST);
		frame.add(controlPanel,BorderLayout.WEST);
		frame.add(canvas,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
