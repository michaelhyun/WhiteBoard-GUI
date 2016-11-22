import javax.swing.JFrame;

public class Whiteboard extends JFrame {
	WhiteBoardController controller;

	public Whiteboard(WhiteBoardController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		controller.attachView(this);
	}
	
	
}
