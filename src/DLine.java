import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class DLine extends DShape implements ModelListener{
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

	@Override
	public void modelChanged(DShapeModel model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Point> getKnobs() {
		// TODO Auto-generated method stub
		ArrayList<Point> points = new ArrayList<Point>();
		Point topLeft = new Point(model.getX(), model.getY());
		Point bottomRight = new Point(model.getX() + model.getWidth(), model.getY() + model.getHeight());
		Point bottomLeft = new Point(model.getX(), model.getY() + model.getHeight());
		Point topRight = new Point(model.getX() + model.getWidth(), model.getY());
		points.add(topLeft);
		points.add(topRight);
		points.add(bottomLeft);
		points.add(bottomRight);
		
		return points;
	}

}
