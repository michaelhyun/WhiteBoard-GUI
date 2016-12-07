
import java.awt.Point;

import org.w3c.dom.Element;

public class DLineModel extends DShapeModel {
	
	private Point p1;
	private Point p2;
	
	public Point getP1(){
		return p1;
	}

	public void setP1(Point point){
		this.p1 = point;
	}
	public Point getP2(){
		return p2;
	}

	public void setP2(Point point){
		this.p2 = point;
	}
	
	
	
	@Override
	public Element addModelTo(Element rootElement) {
		// TODO Auto-generated method stub
		Element shape = rootElement.getOwnerDocument().createElement("shape");

		Element typeElement = rootElement.getOwnerDocument().createElement("type");
		typeElement.appendChild(rootElement.getOwnerDocument().createTextNode("line"));
		shape.appendChild(typeElement);

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
		colorElement.appendChild(rootElement.getOwnerDocument().createTextNode(getColor().getRGB() + ""));
		shape.appendChild(colorElement);

		rootElement.appendChild(shape);

		return rootElement;
	}

}
