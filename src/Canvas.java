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
	
	
	public void addShape(DShapeModel model){
		
		if (model instanceof DRectModel){
			model.setX(0);
			model.setY(0);
			model.setHeight(50);
			model.setWidth(100);
			model.setColor(Color.GRAY);
			DShape rect = new DRect(model);
	    	rect.draw(this.getGraphics());
	    	list.add(rect);
		}
		else if (model instanceof DOvalModel){
			model.setX(0);
			model.setY(0);
			model.setWidth(100);S
			model.setHeight(50);
			model.setColor(Color.GRAY);
			DShape oval = new DOval(model);
	    	oval.draw(this.getGraphics());
	    	list.add(oval);
		}
		else if (model instanceof DLineModel){
			model.setX(0);
			model.setY(0);
			model.setWidth(100);
			model.setHeight(50);
			model.setColor(Color.GRAY);
			DShape line = new DLine(model);
	    	line.draw(this.getGraphics());
	    	list.add(line);
		}
		else if (model instanceof DTextModel){
			model = (DTextModel) model;
			model.setX(0);
			model.setY(0);
			model.setWidth(0);
			model.setHeight(100);
			model.setColor(Color.GRAY);
			model.setText("Hello, World");
			model.setFontName("Dialog");
			model.setFontStyle(Font.PLAIN);
			model.setFontSize(30);
			DShape text = new DText(model);
	    	text.draw(this.getGraphics());
	    	list.add(text);
		}
	}
	
	public void removeShape(DShape shape){
		try{
		list.remove(shape);
		}
		catch(Exception e){
			System.out.println("Hello");
		}
	}
	
	

}
