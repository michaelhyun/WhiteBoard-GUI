import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DText extends DShape {
	DTextModel model;
	
	public DText(DTextModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Font f = new Font(model.getText(), model.getFontStyle(), model.getFontSize());
		g.setFont(f);
		g.drawString(model.getText(), model.getWidth(), model.getHeight());
	}
	
}
