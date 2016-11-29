import java.awt.Color;
import java.awt.Graphics;

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

}
