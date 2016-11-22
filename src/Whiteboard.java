import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

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
		
		Canvas canvas = new Canvas();
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
