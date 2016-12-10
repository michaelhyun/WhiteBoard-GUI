import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DLine extends DShape implements ModelListener {
	DLineModel model;

	public DLine(DLineModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(model.getColor());
		g.drawLine(model.getP1X(), model.getP1Y(), model.getP2X(),model.getP2Y());
	}
	

	public boolean contains(Point point) {
		int x = point.x;
		int y = point.y;
		int xDiff = model.getP1X() - model.getP2X();
		int yDiff = model.getP1Y() - model.getP2Y();
		
		if(xDiff < 0 && yDiff < 0){ //bottom right
			if (model.getP1X() <= x && x <= model.getP2X()) {
				if (model.getP1Y() <= y && y <= model.getP2Y()) {
					return true;
				}
			}
		}
		else if(xDiff < 0 && yDiff > 0){ //top right
			if (model.getP1X() <= x && x <= model.getP2X()) {
				if (model.getP1Y() >= y && y >= model.getP2Y()) {
					return true;
				}
			}
		}
		else if(xDiff > 0 && yDiff < 0){ //bottom left
			if (model.getP1X() >= x && x >= model.getP2X()) {
				if (model.getP1Y() <= y && y <= model.getP2Y()) {
					return true;
				}
			}
		}
		else if(xDiff > 0 && yDiff > 0){ //top left
			if (model.getP1X() >= x && x >= model.getP2X()) {
				if (model.getP1Y() >= y && y >= model.getP2Y()) {
					return true;
				}
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
		Point left = model.getP1();
		Point right = model.getP2();

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
		left = new Rectangle2D.Double(leftKnob.getX() - Globals.KNOB_SIZE / 2, leftKnob.getY() - Globals.KNOB_SIZE / 2,
				Globals.KNOB_SIZE, Globals.KNOB_SIZE);

		Rectangle2D right;
		right = new Rectangle2D.Double(rightKnob.getX() - Globals.KNOB_SIZE / 2,
				rightKnob.getY() - Globals.KNOB_SIZE / 2, Globals.KNOB_SIZE, Globals.KNOB_SIZE);

		g2d.fill(left);
		g2d.fill(right);
	}

}
