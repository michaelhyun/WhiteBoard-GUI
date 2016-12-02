import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private final int HEIGHT = 400, WIDTH = 400;
	private ArrayList<DShape> shapesList;
	private DShape selectedShape;

	public Canvas() {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.WHITE);
		super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		shapesList = new ArrayList<DShape>();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);
				for (int i = shapesList.size() - 1; i >= 0; i--) {
					DShape shape = shapesList.get(i);
					if (shape.contains(me.getPoint())) {
						selectedShape = shape;
						System.out.println(selectedShape.description());
						break;
					}
				}
				if (selectedShape != null) {
					selectedShape.drawKnobs(getGraphics());
				}
				// ArrayList<Point> points = selectedShape.getKnobs();
				//
				// for(Point p : points){
				// Rectangle rectangle = new Rectangle(p.x,p.y, 9, 9);
				//
				// }
				// need to do something to selected shape here
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("pressed");
				System.out.println(e.getX() + ", " + e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("released");
				System.out.println(e.getX() + ", " + e.getY());
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("dragged");
				Point point = e.getPoint();
				System.out.println(point.getX() + ", " + point.getY());
			}
		});

	}

	public void addShape(DShapeModel model) {

		if (model instanceof DRectModel) {
			DShape rect = new DRect((DRectModel) model);
			shapesList.add(rect);
		} else if (model instanceof DOvalModel) {
			DShape oval = new DOval((DOvalModel) model);
			shapesList.add(oval);
		} else if (model instanceof DLineModel) {
			DShape line = new DLine((DLineModel) model);
			shapesList.add(line);
		} else if (model instanceof DTextModel) {
			DShape text = new DText((DTextModel) model);
			shapesList.add(text);
		}

		for (DShape shape : shapesList) {
			shape.draw(this.getGraphics());
		}
	}

	public void removeShape(DShape shape) {
		try {
			shapesList.remove(shape);
		} catch (Exception e) {
			System.out.println("Hello");
		}
	}

}
