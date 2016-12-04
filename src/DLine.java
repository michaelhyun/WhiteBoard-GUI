import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
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
		Point left = new Point(model.getX(), model.getY());
		Point right = new Point(model.getX() + model.getWidth(), model.getY() + model.getHeight());

		points.add(left);
		points.add(right);
		
		return points;
	}

	@Override
	public void drawKnobs(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) (g);
		g2d.setColor(Color.RED);
		
		ArrayList<Point> knobs = getKnobs();
		
		Point leftKnob = knobs.get(0);
		Point rightKnob = knobs.get(1);
		
		Rectangle2D left;
		left = new Rectangle2D.Double(leftKnob.getX() - Globals.KNOB_SIZE/2, leftKnob.getY() - Globals.KNOB_SIZE/2, Globals.KNOB_SIZE, Globals.KNOB_SIZE);

		Rectangle2D right;
		right = new Rectangle2D.Double(rightKnob.getX() - Globals.KNOB_SIZE/2,  rightKnob.getY() - Globals.KNOB_SIZE/2, Globals.KNOB_SIZE, Globals.KNOB_SIZE);
		
		g2d.fill(left);
		g2d.fill(right);
	}

}
