import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
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

	private Font computeFont(Graphics g) {
		double size = 1.0;
		double previous = 1;
		Font theFont = new Font(model.getFontName(), Font.PLAIN, (int) size);
		FontMetrics fontMetrics = g.getFontMetrics(theFont);

		while (fontMetrics.getHeight() < model.getHeight()) {
			previous = size;
			size = (size * 1.10) + 1;
			theFont = new Font(this.model.getFontName(), Font.PLAIN, (int) size);
			fontMetrics = g.getFontMetrics(theFont);
		}
		return new Font(model.getFontName(), Font.PLAIN, (int) previous);
	}

	@SuppressWarnings("unused")
	@Override
	public void draw(Graphics g) {
		Font f = computeFont(g);
		FontMetrics metrics = g.getFontMetrics(f);

		Graphics2D g2d = (Graphics2D) g;
		Shape oldClip = g.getClip();
		Rectangle rect = new Rectangle(model.getX(), model.getY(), model.getWidth(), model.getHeight());
		g.setClip(oldClip.getBounds().createIntersection(rect.getBounds2D()));
		g.setColor(model.getColor());
		g.setFont(f);
		g.drawString(model.getText(), model.getX(), model.getY() + metrics.getAscent());
		g.setClip(oldClip);

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
		topLeftRect = new Rectangle2D.Double(topLeft.getX() - Globals.KNOB_SIZE / 2,
				topLeft.getY() - Globals.KNOB_SIZE / 2, Globals.KNOB_SIZE, Globals.KNOB_SIZE);

		Rectangle2D topRightRect;
		topRightRect = new Rectangle2D.Double(topRight.getX() - Globals.KNOB_SIZE / 2,
				topRight.getY() - Globals.KNOB_SIZE / 2, Globals.KNOB_SIZE, Globals.KNOB_SIZE);

		Rectangle2D bottomLeftRect;
		bottomLeftRect = new Rectangle2D.Double(bottomLeft.getX() - Globals.KNOB_SIZE / 2,
				bottomLeft.getY() - Globals.KNOB_SIZE / 2, Globals.KNOB_SIZE, Globals.KNOB_SIZE);

		Rectangle2D bottomRightRect;
		bottomRightRect = new Rectangle2D.Double(bottomRight.getX() - Globals.KNOB_SIZE / 2,
				bottomRight.getY() - Globals.KNOB_SIZE / 2, Globals.KNOB_SIZE, Globals.KNOB_SIZE);

		g2d.fill(topLeftRect);
		g2d.fill(topRightRect);
		g2d.fill(bottomLeftRect);
		g2d.fill(bottomRightRect);
	}

}
