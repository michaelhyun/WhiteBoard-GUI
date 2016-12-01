import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

public class DText extends DShape implements ModelListener{
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
		
		//this is a bad solution, but I need to modify the model based on the graphics context
		model.setWidth(metrics.stringWidth(model.getText()));
		model.setHeight(metrics.getHeight());
		//end bad code ^
		
		//draw the string
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
	public void drawKnobs() {
		// TODO Auto-generated method stub
		
	}

}
