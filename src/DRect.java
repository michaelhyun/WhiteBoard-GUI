import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.text.TabStop;

public class DRect extends DShape implements ModelListener {
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

	public boolean contains(Point point) {
		int x = point.x;
		int y = point.y;

		if (model.getX() <= x && x <= (model.getX() + model.getWidth())) {
			if (model.getY() <= y && y <= (model.getY() + model.getHeight())) {
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

	@Override
	public void drawKnobs(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.RED);
		
		ArrayList<Point> knobs = getKnobs();
		
		Point topLeft = knobs.get(0);
		Point bottomRight = knobs.get(3);
		g.drawLine(topLeft.x, topLeft.y, bottomRight.x, bottomRight.y);
		
		Point topRight = knobs.get(1);
		Point bottomLeft = knobs.get(2);
		g.drawLine(topRight.x,topRight.y, bottomLeft.x, bottomRight.y);		
	}
}
