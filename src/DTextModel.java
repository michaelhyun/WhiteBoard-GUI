
import java.awt.Font;

import org.w3c.dom.Element;

public class DTextModel extends DShapeModel {
	private String text;
	private String fontName;
	private int fontStyle;
	private int fontSize;

	public String getText() {
		return text;
	}
	

	public void setText(String text) {
		this.text = text;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	@Override
	public Element addModelTo(Element rootElement) {
		Element shape = rootElement.getOwnerDocument().createElement("shape");

		Element typeElement = rootElement.getOwnerDocument().createElement("type");
		typeElement.appendChild(rootElement.getOwnerDocument().createTextNode("text"));
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
		
		Element textElement = rootElement.getOwnerDocument().createElement("text");
		textElement.appendChild(rootElement.getOwnerDocument().createTextNode(getText()));
		shape.appendChild(textElement);
		
		Element fontNameElement = rootElement.getOwnerDocument().createElement("fontName");
		fontNameElement.appendChild(rootElement.getOwnerDocument().createTextNode(getFontName()));
		shape.appendChild(fontNameElement);
		
		Element fontStyleElement = rootElement.getOwnerDocument().createElement("fontStyle");
		fontStyleElement.appendChild(rootElement.getOwnerDocument().createTextNode(getFontStyle()+""));
		shape.appendChild(fontStyleElement);
		
		Element fontSizeElement = rootElement.getOwnerDocument().createElement("fontSize");
		fontSizeElement.appendChild(rootElement.getOwnerDocument().createTextNode(getFontSize()+""));
		shape.appendChild(fontSizeElement);

		rootElement.appendChild(shape);

		return rootElement;
	}

}
