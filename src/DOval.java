import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class DOval extends DShape {
	DOvalModel model;

	public DOval() {
		this.model = new DOvalModel();
		model.setX(0);
		model.setY(0);
		model.setWidth(100);
		model.setHeight(50);
		model.setColor(Color.gray);
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

}
