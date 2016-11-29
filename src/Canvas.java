import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private final int HEIGHT = 400, WIDTH = 400;
	private ArrayList<DShape> list;

	public Canvas() {
		// TODO Auto-generated constructor stub
		super.setBackground(Color.WHITE);
		super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		list = new ArrayList<DShape>();

	}

	public void addShape(DShapeModel model) {

		if (model instanceof DRectModel) {
			DShape rect = new DRect((DRectModel) model);
			rect.draw(this.getGraphics());
			list.add(rect);
		} else if (model instanceof DOvalModel) {
			DShape oval = new DOval((DOvalModel) model);
			oval.draw(this.getGraphics());
			list.add(oval);
		} else if (model instanceof DLineModel) {
			DShape line = new DLine((DLineModel) model);
			line.draw(this.getGraphics());
			list.add(line);
		} else if (model instanceof DTextModel) {
			DShape text = new DText((DTextModel) model);
			text.draw(this.getGraphics());
			list.add(text);
		}
	}

	public void removeShape(DShape shape) {
		try {
			list.remove(shape);
		} catch (Exception e) {
			System.out.println("Hello");
		}
	}

}
