import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel {
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
				for (DShape shape : shapesList) { //need to go backwards
					if (shape.contains(me.getPoint())) {
						if (shape instanceof DRect) {
							selectedShape = shape;
						} else if (shape instanceof DOval) {
							selectedShape = shape;
						} else if (shape instanceof DLine) {
							selectedShape = shape;
						} else if (shape instanceof DText) {
							selectedShape = shape;
						}
					}
				}
				
				//need to do something to selected shape here
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
