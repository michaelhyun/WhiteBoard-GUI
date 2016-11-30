import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class DText extends DShape {
	DTextModel model;
	
	public DText(DTextModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}

	public boolean contains(Point point){
		int x = point.x;
		int y = point.y;
		
		if(model.getX() <= x && x <= (model.getX() + model.getWidth())){
			if(model.getY() <= y && y <= (model.getY() + model.getHeight())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Font f = new Font(model.getText(), model.getFontStyle(), model.getFontSize());
		g.setFont(f);
		g.drawString(model.getText(), model.getWidth(), model.getHeight());
	}
	
}
