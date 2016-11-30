import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class DOval extends DShape {
	DOvalModel model;

	public DOval(DOvalModel model) {
		this.model = model;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Ellipse2D ellipse;
		Graphics2D g2d = (Graphics2D) (g);
		g2d.setColor(model.getColor());
		ellipse = new Ellipse2D.Double(model.getX(), model.getY(), model.getWidth(), model.getHeight());
		g2d.fill(ellipse);
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

}
