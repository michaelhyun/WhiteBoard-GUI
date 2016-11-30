import java.awt.Graphics;
import java.awt.Point;

public class DLine extends DShape {
	DLineModel model;

	public DLine(DLineModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(model.getColor());
		g.drawLine(model.getX(), model.getY(), model.getX() + model.getWidth(), model.getY() + model.getHeight());
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
		return "Line";
	}

}
