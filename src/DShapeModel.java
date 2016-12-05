import java.awt.Color;
import org.w3c.dom.Element;

public abstract class DShapeModel {
	private int x, y, width, height;
	private Color color;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Element addModelTo(Element rootElement) {
		// TODO Auto-generated method stub
		Element shape = rootElement.getOwnerDocument().createElement("shape");

		Element xElement = rootElement.getOwnerDocument().createElement("x");
		xElement.appendChild(rootElement.getOwnerDocument().createTextNode(getX() + ""));
		shape.appendChild(xElement);

		Element yElement = rootElement.getOwnerDocument().createElement("y");
		yElement.appendChild(rootElement.getOwnerDocument().createTextNode(getY() + ""));
		shape.appendChild(yElement);

		Element widthElement = rootElement.getOwnerDocument().createElement("width");
		widthElement.appendChild(rootElement.getOwnerDocument().createTextNode(getWidth() + ""));
		shape.appendChild(widthElement);

		Element heightElement = rootElement.getOwnerDocument().createElement("height");
		heightElement.appendChild(rootElement.getOwnerDocument().createTextNode(getHeight() + ""));
		shape.appendChild(heightElement);

		Element colorElement = rootElement.getOwnerDocument().createElement("color");
		heightElement.appendChild(rootElement.getOwnerDocument().createTextNode(getColor().getRGB() + ""));
		shape.appendChild(colorElement);

		rootElement.appendChild(shape);

		return rootElement;
	}

}
