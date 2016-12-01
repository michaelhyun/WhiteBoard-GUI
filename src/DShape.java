import java.awt.Graphics;
import java.awt.Point;

public abstract class DShape {
	public abstract void draw(Graphics g);
	
	public abstract boolean contains(Point point);
	
	public abstract String description();

	public abstract void drawKnobs();

}
