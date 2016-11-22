import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		
		JLabel addLabel = new JLabel("Add");
		JButton rectButton = new JButton("Rect");
		JButton ovalButton = new JButton("Oval");
		JButton lineButton = new JButton("Line");
		JButton textButton = new JButton("Text");
		controlPanel.add(addLabel);
		controlPanel.add(rectButton);
		controlPanel.add(ovalButton);
		controlPanel.add(lineButton);
		controlPanel.add(textButton);

		frame.add(controlPanel,BorderLayout.WEST);
		frame.add(canvas,BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
