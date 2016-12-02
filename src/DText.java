import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DText extends DShape implements ModelListener {
	DTextModel model;

	public DText(DTextModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
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
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

		Font f = new Font(model.getText(), model.getFontStyle(), model.getFontSize());
		g.setFont(f);

		FontMetrics metrics = g.getFontMetrics(f);
		int x = model.getX();
		int y = metrics.getHeight() + model.getY();

		// this is a bad solution, but I need to modify the model based on the
		// graphics context
		model.setWidth(metrics.stringWidth(model.getText()));
		model.setHeight(metrics.getHeight());
		// end bad code ^

		// draw the string
		g.drawString(model.getText(), x, y);

	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "Text Box";
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
		Graphics2D g2d = (Graphics2D) (g);
		g2d.setColor(Color.RED);

		ArrayList<Point> knobs = getKnobs();

		Point topLeft = knobs.get(0);
		Point bottomRight = knobs.get(3);
		Point topRight = knobs.get(1);
		Point bottomLeft = knobs.get(2);

		Rectangle2D topLeftRect;
		topLeftRect = new Rectangle2D.Double(topLeft.getX() - 5, topLeft.getY() - 5, 10, 10);

		Rectangle2D topRightRect;
		topRightRect = new Rectangle2D.Double(topRight.getX() - 5, topRight.getY() - 5, 10, 10);

		Rectangle2D bottomLeftRect;
		bottomLeftRect = new Rectangle2D.Double(bottomLeft.getX() - 5, bottomLeft.getY() - 5, 10, 10);

		Rectangle2D bottomRightRect;
		bottomRightRect = new Rectangle2D.Double(bottomRight.getX() - 5, bottomRight.getY() - 5, 10, 10);

		g2d.fill(topLeftRect);
		g2d.fill(topRightRect);
		g2d.fill(bottomLeftRect);
		g2d.fill(bottomRightRect);
	}

}
