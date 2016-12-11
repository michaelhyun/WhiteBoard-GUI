import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.w3c.dom.Element;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private final int HEIGHT = 400, WIDTH = 400;
	ArrayList<DShape> shapesList;
	private List<Observer> observers = new ArrayList<Observer>();
	private DShape selectedShape;
	private int selectedShapeIndex;
	private int xBeforeDrag = 0;
	private int yBeforeDrag = 0;
	private Point movingKnob;
	private Point anchorKnob;
	Whiteboard whiteboard;

	public Canvas(Whiteboard whiteboard) {
		// TODO Auto-generated constructor stub
		this.whiteboard = whiteboard;
		super.setBackground(Color.WHITE);
		super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		shapesList = new ArrayList<DShape>();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				super.mouseClicked(m);

				for (int i = shapesList.size() - 1; i >= 0; i--) {
					DShape shape = shapesList.get(i);
					if (shape.contains(m.getPoint())) {
						selectedShape = shape;
						selectedShapeIndex = i;
						selectedShape.drawKnobs(getGraphics());
						// System.out.println(selectedShape.description());
						break;
					} else {
						selectedShape = null;
						repaint();
					}
				}

				if (selectedShape instanceof DText) {
					String shapeText = ((DText) selectedShape).model.getText();
					whiteboard.textArea.setEnabled(true);
					whiteboard.textArea.setText(shapeText);
					whiteboard.fontBox.setEnabled(true);
					whiteboard.fontBox.setSelectedItem(((DText) selectedShape).model.getFontName());
				} else {
					whiteboard.textArea.setText("");
					whiteboard.fontBox.setSelectedItem("Dialog");
					;
					whiteboard.textArea.setEnabled(false);
					whiteboard.fontBox.setEnabled(false);
				}
				repaint();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// System.out.println("pressed");
				// System.out.println(e.getX() + ", " + e.getY());
				if (selectedShape != null) {
					ArrayList<Point> knobPoints = selectedShape.getKnobs();
					if (knobPoints.size() == 4) { // moving all rectangle bounds

						for (Point point : knobPoints) {
							if ((e.getX() <= (point.getX() + Globals.KNOB_SIZE / 2))
									&& (e.getX() >= (point.getX() - Globals.KNOB_SIZE / 2))
									&& (e.getY() <= (point.getY() + Globals.KNOB_SIZE / 2))
									&& (e.getY() >= (point.getY() - Globals.KNOB_SIZE / 2))) {
								movingKnob = point;

								if (knobPoints.get(0).equals(movingKnob)) {
									anchorKnob = knobPoints.get(3);
								} else if (knobPoints.get(1).equals(movingKnob)) {
									anchorKnob = knobPoints.get(2);
								} else if (knobPoints.get(2).equals(movingKnob)) {
									anchorKnob = knobPoints.get(1);
								} else if (knobPoints.get(3).equals(movingKnob)) {
									anchorKnob = knobPoints.get(0);
								}
								System.out.println("MovingKnob selected");
							}
						}
					} else { // moving DLine
						for (Point point : knobPoints) {

							if ((e.getX() <= (point.x + Globals.KNOB_SIZE / 2))
									&& (e.getX() >= (point.x - Globals.KNOB_SIZE / 2))
									&& (e.getY() <= (point.y + Globals.KNOB_SIZE / 2))
									&& (e.getY() >= (point.y - Globals.KNOB_SIZE / 2))) {
								movingKnob = point;
							}
							if (knobPoints.get(0).equals(movingKnob)) {
								anchorKnob = knobPoints.get(1);
							} else if (knobPoints.get(1).equals(movingKnob)) {
								anchorKnob = knobPoints.get(0);
							}
						}

					}
					xBeforeDrag = e.getX();
					yBeforeDrag = e.getY();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// System.out.println("released");
				movingKnob = null;
				anchorKnob = null;
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point point = e.getPoint();
				if (selectedShape != null) {
					if (movingKnob != null) { // resizing object
						ArrayList<Point> knobPoints = selectedShape.getKnobs();
						if (selectedShape instanceof DRect) {
							DShapeModel model;
							model = ((DRect) selectedShape).model;
							int dx = (int) (point.x - anchorKnob.getX());
							int dy = (int) (point.y - anchorKnob.getY());
							int currentX = (int) model.getX();
							int currentY = (int) model.getY();

							if (anchorKnob.equals(knobPoints.get(0))) {
								resize(point, model, currentX, currentY, dx, dy);
							} else if (anchorKnob.equals(knobPoints.get(1))) {
								resize(point, model, point.x, currentY, -dx, dy);
							} else if (anchorKnob.equals(knobPoints.get(2))) {
								resize(point, model, currentX, point.y, dx, -dy);
							} else if (anchorKnob.equals(knobPoints.get(3))) {
								resize(point, model, point.x, point.y, -dx, -dy);
							}

						} else if (selectedShape instanceof DOval) {
							DShapeModel model;
							model = ((DOval) selectedShape).model;
							int dx = (int) (point.x - anchorKnob.getX());
							int dy = (int) (point.y - anchorKnob.getY());
							int currentX = (int) model.getX();
							int currentY = (int) model.getY();

							if (anchorKnob.equals(knobPoints.get(0))) {
								resize(point, model, currentX, currentY, dx, dy);
							} else if (anchorKnob.equals(knobPoints.get(1))) {
								resize(point, model, point.x, currentY, -dx, dy);
							} else if (anchorKnob.equals(knobPoints.get(2))) {
								resize(point, model, currentX, point.y, dx, -dy);
							} else if (anchorKnob.equals(knobPoints.get(3))) {
								resize(point, model, point.x, point.y, -dx, -dy);
							}

						} else if (selectedShape instanceof DLine) {
							DShapeModel model;
							model = ((DLine) selectedShape).model;
							int xChange = e.getX() - (int) movingKnob.getX();
							int yChange = e.getY() - (int) movingKnob.getY();
							if (((DLineModel) model).getP1() == movingKnob) {
								((DLineModel) model).setP1X(((DLineModel) model).getP1X() + xChange);
								((DLineModel) model).setP1Y(((DLineModel) model).getP1Y() + yChange);
								repaint();
							} else if (((DLineModel) model).getP2() == movingKnob) {
								((DLineModel) model).setP2X(((DLineModel) model).getP2X() + xChange);
								((DLineModel) model).setP2Y(((DLineModel) model).getP2Y() + yChange);
								repaint();
							}

						} else if (selectedShape instanceof DText) {
							DShapeModel model;
							model = ((DText) selectedShape).model;
							int dx = (int) (point.x - anchorKnob.getX());
							int dy = (int) (point.y - anchorKnob.getY());
							int currentX = (int) model.getX();
							int currentY = (int) model.getY();

							if (anchorKnob.equals(knobPoints.get(0))) {
								resize(point, model, currentX, currentY, dx, dy);
							} else if (anchorKnob.equals(knobPoints.get(1))) {
								resize(point, model, point.x, currentY, -dx, dy);
							} else if (anchorKnob.equals(knobPoints.get(2))) {
								resize(point, model, currentX, point.y, dx, -dy);
							} else if (anchorKnob.equals(knobPoints.get(3))) {
								resize(point, model, point.x, point.y, -dx, -dy);
							}
						}

					} else if (selectedShape.contains(point)) { // moving object
						DShapeModel model;
						if (selectedShape instanceof DRect) {
							model = ((DRect) selectedShape).model;
							moveShape(model, point);
						} else if (selectedShape instanceof DOval) {
							model = ((DOval) selectedShape).model;
							moveShape(model, point);
						} else if (selectedShape instanceof DText) {
							model = ((DText) selectedShape).model;
							moveShape(model, point);
						} else if (selectedShape instanceof DLine) {
							model = ((DLine) selectedShape).model;
							moveShape(model, point);
						}
					}
				}
				xBeforeDrag = point.x;
				yBeforeDrag = point.y;
			}
		});

	}

	public void moveShape(DShapeModel model, Point point) {
		if (model instanceof DLineModel) {

			((DLineModel) model).setP1(new Point(((DLineModel) model).getP1X() + (point.x - xBeforeDrag),
					((DLineModel) model).getP1Y() + (point.y - yBeforeDrag)));
			((DLineModel) model).setP2(new Point(((DLineModel) model).getP2X() + (point.x - xBeforeDrag),
					((DLineModel) model).getP2Y() + (point.y - yBeforeDrag)));
			repaint();
		}
		model.setX(model.getX() + (point.x - xBeforeDrag));
		model.setY(model.getY() + (point.y - yBeforeDrag));
		selectedShape.modelChanged(model);
		repaint();
	}

	public void resize(Point point, DShapeModel model, int x, int y, int width, int height) {

		if (width < 0) {
			model.setX(x - Math.abs(width));
			model.setWidth(Math.abs((int) (anchorKnob.getX() - point.x)));
		} else {
			model.setX(x);
			model.setWidth(width);
		}
		if (height < 0) {
			model.setY(y - Math.abs(height));
			model.setHeight(Math.abs((int) (anchorKnob.getY() - point.y)));
		} else {
			model.setY(y);
			model.setHeight(height);
		}

		selectedShape.modelChanged(model);
		repaint();
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

	public void moveToBack() {
		if (selectedShape != null) {
			shapesList.remove(selectedShape);
			shapesList.add(selectedShape);
			repaint();
		} else {
			System.out.print("No shape selected");
		}
	}

	public void moveToFront() {
		if (selectedShape != null) {
			shapesList.remove(selectedShape);
			shapesList.add(0, selectedShape);
			repaint();
		} else {
			System.out.print("No shape selected");
		}
	}

	public ArrayList<DShape> getShapesList() {
		return shapesList;
	}

	public int getSelectedShapeIndex() {
		return selectedShapeIndex;
	}

	public void removeShape() {

		if (selectedShape != null) {
			shapesList.remove(selectedShape);
			selectedShape = null;

			repaint();
		} else {
			System.out.print("No shape selected");
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
		} else {
			whiteboard.textArea.setEnabled(false);
			whiteboard.fontBox.setEnabled(false);
			whiteboard.fontBox.setSelectedItem("Dialog");
		}
		notifyAllObservers();

	}

	public Element getRootElementForXML(Element rootElement) {
		for (DShape dShape : shapesList) {
			if (dShape instanceof DRect) {
				DShapeModel model = ((DRect) dShape).model;
				model.addModelTo(rootElement);
			} else if (dShape instanceof DOval) {
				DShapeModel model = ((DOval) dShape).model;
				model.addModelTo(rootElement);
			} else if (dShape instanceof DLine) {
				DShapeModel model = ((DLine) dShape).model;
				model.addModelTo(rootElement);
			} else if (dShape instanceof DText) {
				DShapeModel model = ((DText) dShape).model;
				model.addModelTo(rootElement);
			}
		}
		return rootElement;
	}

	public void changeFont(String font) {
		if (selectedShape != null) {
			if (selectedShape instanceof DText) {
				DTextModel model;
				model = ((DText) selectedShape).model;
				model.setFontName(font);
				selectedShape.modelChanged(model);
				shapesList.set(selectedShapeIndex, selectedShape);
				repaint();
			}
		}
	}

	public void changeText(String text) {
		if (selectedShape != null) {
			if (selectedShape instanceof DText) {
				DTextModel model;
				model = ((DText) selectedShape).model;
				model.setText(text);
				selectedShape.modelChanged(model);
				shapesList.set(selectedShapeIndex, selectedShape);
				repaint();
			}
		}
	}
	
	public void clearCanvas(){
		shapesList.clear();
		selectedShape = null;
		repaint();
	}

	public void attach(Observer observer) {
		observers.add(observer);
	}

	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

}
