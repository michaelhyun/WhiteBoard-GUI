import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private final int HEIGHT = 400, WIDTH = 400;
	
	public Canvas() {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.WHITE);
		super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	

}
