import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private final int HEIGHT = 400, WIDTH = 400;
	private ArrayList<DShape> shapesList;
	private DShape selectedShape;
	private int selectedShapeIndex;
	private int xBeforeDrag = 0;
	private int yBeforeDrag = 0;

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
						repaint();
						selectedShapeIndex = i;
						selectedShape.drawKnobs(getGraphics());
						System.out.println(selectedShape.description());
						break;
					} else {
						selectedShape = null;
						repaint();
					}

				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("pressed");
				System.out.println(e.getX() + ", " + e.getY());
				if (selectedShape != null) {
					xBeforeDrag = e.getX();
					yBeforeDrag = e.getY();
				}
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
				if (selectedShape != null) {
					if (selectedShape.contains(point)) {
						DShapeModel model;
						if (selectedShape instanceof DRect) {
							model = ((DRect) selectedShape).model;
							model.setX(model.getX() + (point.x - xBeforeDrag));
							model.setY(model.getY() + (point.y - yBeforeDrag));
							selectedShape.modelChanged(model);
							shapesList.set(selectedShapeIndex, selectedShape);
							repaint();
						} else if (selectedShape instanceof DOval) {
							model = ((DOval) selectedShape).model;
							model.setX(model.getX() + (point.x - xBeforeDrag));
							model.setY(model.getY() + (point.y - yBeforeDrag));
							selectedShape.modelChanged(model);
							shapesList.set(selectedShapeIndex, selectedShape);
							repaint();
						} else if (selectedShape instanceof DText) {
							model = ((DText) selectedShape).model;
							model.setX(model.getX() + (point.x - xBeforeDrag));
							model.setY(model.getY() + (point.y - yBeforeDrag));
							selectedShape.modelChanged(model);
							shapesList.set(selectedShapeIndex, selectedShape);
							repaint();
						} else if (selectedShape instanceof DLine) {

						}
					}
				}
				xBeforeDrag = point.x;
				yBeforeDrag = point.y;
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

	public void colorUpdated(final Color c) {
		if (selectedShape != null) {
			DShapeModel model;
			if (selectedShape instanceof DRect) {
				model = ((DRect) selectedShape).model;
				model.setColor(c);
				selectedShape.modelChanged(model);
				shapesList.set(selectedShapeIndex, selectedShape);
				repaint();
			} else if (selectedShape instanceof DOval) {
				model = ((DOval) selectedShape).model;
				model.setColor(c);
				selectedShape.modelChanged(model);
				shapesList.set(selectedShapeIndex, selectedShape);
				repaint();
			} else if (selectedShape instanceof DText) {
				model = ((DText) selectedShape).model;
				model.setColor(c);
				selectedShape.modelChanged(model);
				shapesList.set(selectedShapeIndex, selectedShape);
				repaint();
			} else if (selectedShape instanceof DLine) {
				model = ((DLine) selectedShape).model;
				model.setColor(c);
				selectedShape.modelChanged(model);
				shapesList.set(selectedShapeIndex, selectedShape);
				repaint();
			}
		}

	}

	public void removeShape(DShape shape) {
		try {
			shapesList.remove(shape);
		} catch (Exception e) {
			System.out.println("Hello");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (DShape shape : shapesList) {
			shape.draw(g);
		}
		if (selectedShape != null) {
			selectedShape.drawKnobs(g);
		}
	}

}
