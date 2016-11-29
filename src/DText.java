import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DText extends DShape {
	DTextModel model;
	
	public DText() {
		// TODO Auto-generated constructor stub
		this.model = new DTextModel();
		model.setX(0);
		model.setY(0);
		model.setWidth(0);
		model.setHeight(100);
		model.setColor(Color.gray);
		model.setText("Hello, World");
		model.setFontName("Dialog");
		model.setFontStyle(Font.PLAIN);
		model.setFontSize(30);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Font f = new Font(model.getText(), model.getFontStyle(), model.getFontSize());
		g.setFont(f);
		g.drawString(model.getText(), model.getWidth(), model.getHeight());
	}
	
}
