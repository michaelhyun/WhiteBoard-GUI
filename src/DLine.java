import java.awt.Color;
import java.awt.Graphics;

public class DLine extends DShape {
	DLineModel model;

	public DLine() {
		// TODO Auto-generated constructor stub
		model = new DLineModel();
		model.setX(0);
		model.setY(0);
		model.setWidth(100);
		model.setHeight(50);
		model.setColor(Color.gray);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(model.getColor());
		g.drawLine(model.getX(), model.getY(), model.getX() + model.getWidth(), model.getY() + model.getHeight());
	}

}
