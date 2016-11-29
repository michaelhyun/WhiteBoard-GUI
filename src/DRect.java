import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class DRect extends DShape {
	public DRectModel model;

	public DRect() {
		this.model = new DRectModel();

		model.setX(0);
		model.setY(0);
		model.setWidth(100);
		model.setHeight(50);
		model.setColor(Color.gray);

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
}
