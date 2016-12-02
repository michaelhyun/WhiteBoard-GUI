import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class DShape {
	public abstract void draw(Graphics g);
	
	public abstract boolean contains(Point point);
	
	public abstract String description();

	public abstract ArrayList<Point> getKnobs();
	
	public abstract void drawKnobs(Graphics g);

}
