import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private final int HEIGHT = 400, WIDTH = 400;
	private ArrayList<DShape> shapesList;

	public Canvas() {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.WHITE);
		super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		shapesList = new ArrayList<DShape>();

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
		
		for(DShape shape: shapesList){
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
