import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class DRect extends DShape implements ModelListener{
	public DRectModel model;

	public DRect(DRectModel model) {
		this.model = model;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Rectangle2D rect;
		Graphics2D g2d = (Graphics2D) (g);
		g2d.setColor(model.getColor());
		rect = new Rectangle2D.Double(model.getX(), model.getY(), model.getWidth(), model.getHeight());
		g2d.fill(rect);
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
	public String description() {
		// TODO Auto-generated method stub
		return "Rectangle";
	}

	@Override
	public void modelChanged(DShapeModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawKnobs() {
		// TODO Auto-generated method stub
		
	}
}
