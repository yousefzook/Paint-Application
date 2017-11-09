package shapes;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Square extends Shapes {

	public Square() {
		super();
	}

	/**
	 * Overloaded constructor that takes coordinates, color and fill. It passes
	 * them into MyBoundedShape's constructor
	 */
	public Square(int x1, int y1, int x2, int y2, Color strokeCloor, int strokeWeight, Color fillCloor, boolean fill,
			float tr) {
		super(x1, y1, x2, y2, strokeCloor, strokeWeight, fillCloor, fill, tr);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(3, getTransparent()));

		g.setColor(getColor()); // sets the color
		g.setStroke(new BasicStroke(getStroke()));

		if (getFill()) // determines whether fill is true or false
		{
			g.setColor(getFillColor());
			g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getWidth()); // draws
																					// a
																					// filled
																					// rectangle
			g.setColor(getColor());
			g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getWidth());
		} else
			g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getWidth()); // draws

		if (this.checkSelected()) {
			g.setColor(Color.pink);
			g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
			g.drawRect(getUpperLeftX() - getStroke() / 2, getUpperLeftY() - getStroke() / 2, getWidth() + getStroke(),
					getWidth() + getStroke());
		}
		g.setStroke(new BasicStroke(getStroke()));
		g.setColor(getColor());
	}

	public Boolean isContain(int x, int y) {

		if ((x >= getUpperLeftX()) && (x <= getUpperLeftX() + getWidth()) && (y >= getUpperLeftY())
				&& (y <= getUpperLeftY() + getWidth())) {
			return true;
		}
		return false;

	}

	@Override
	public int isOnBorder(int x, int y) {

		int s = getStroke() + 2;

		if ((x >= getUpperLeftX() - s && x <= getUpperLeftX() + s)
				&& (y >= getUpperLeftY() - s && y <= getUpperLeftY() + s))
			return 0;
		else if ((x >= getUpperLeftX() + getWidth() - s && x <= getUpperLeftX() + getWidth() + s)
				&& (y >= getUpperLeftY() - s && y <= getUpperLeftY() + s))
			return 1;
		else if ((x >= getUpperLeftX() + getWidth() - s && x <= getUpperLeftX() + getWidth() + s)
				&& (y >= getUpperLeftY() + getHeight() - s && y <= getUpperLeftY() + getHeight() + s))
			return 2;
		else if ((x >= getUpperLeftX() - s && x <= getUpperLeftX() + s)
				&& (y >= getLowerRightY() - s && y <= getLowerRightY() + s))
			return 3;
		else if (x > getUpperLeftX() && (y <= getUpperLeftY() + s && y >= getUpperLeftY() - s))
			return 4;
		else if ((x >= getUpperLeftX() + getWidth() - s && x <= getUpperLeftX() + getWidth() + s)
				&& y > getUpperLeftY())
			return 5;

		else if (x > getUpperLeftX() && (y >= getLowerRightY() - s && y <= getLowerRightY() + s))
			return 6;

		else if ((x >= getUpperLeftX() - s && x <= getUpperLeftX() + s) && y < getLowerRightY())
			return 7;

		return -1;

	}
}