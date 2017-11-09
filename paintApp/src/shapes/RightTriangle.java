package shapes;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class RightTriangle extends Shapes {

	public RightTriangle() {
		super();
	}

	public RightTriangle(int x1, int y1, int x2, int y2, Color sColor, int strokeWeight, Color fColor, boolean fill,
			float tr) {
		super(x1, y1, x2, y2, sColor, strokeWeight, fColor, fill, tr);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(getColor());
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(3, getTransparent()));
		g.setStroke(new BasicStroke(getStroke()));
		if (!getFill()) {
			if (getX2() < getX1() && getY2() < getY1()) {

				g.drawLine(getX1(), getY1(), getX2(), getY2());
				g.drawLine(getX2(), getY2(), getUpperRightX(), getUpperRightY());
				g.drawLine(getX1(), getY1(), getUpperRightX(), getUpperRightY());
			} else if (getX2() > getX1() && getY2() > getY1()) {
				g.drawLine(getX1(), getY1(), getX2(), getY2());
				g.drawLine(getX2(), getY2(), getLowerLeftX(), getLowerLeftY());
				g.drawLine(getX1(), getY1(), getLowerLeftX(), getLowerLeftY());

			} else if (getX2() < getX1() && getY2() > getY1()) {
				g.drawLine(getX1(), getY1(), getX2(), getY2());
				g.drawLine(getX2(), getY2(), getLowerRightX(), getLowerRightY());
				g.drawLine(getX1(), getY1(), getLowerRightX(), getLowerRightY());
			} else {
				g.drawLine(getX1(), getY1(), getX2(), getY2());
				g.drawLine(getX2(), getY2(), getUpperLeftX(), getUpperLeftY());
				g.drawLine(getX1(), getY1(), getUpperLeftX(), getUpperLeftY());
			}

		} else {
			if (getX2() < getX1() && getY2() < getY1()) {

				g.drawLine(getX1(), getY1(), getX2(), getY2());
				g.drawLine(getX2(), getY2(), getUpperRightX(), getUpperRightY());
				g.drawLine(getX1(), getY1(), getUpperRightX(), getUpperRightY());

				int[] xarr = new int[3];
				int[] yarr = new int[3];
				xarr[0] = getX1();
				xarr[1] = getX2();
				xarr[2] = getUpperRightX();
				yarr[0] = getY1();
				yarr[1] = getY2();
				yarr[2] = getUpperRightY();
				g.setColor(getFillColor());
				g.fillPolygon(xarr, yarr, 3);
				g.setColor(getColor());

			} else if (getX2() > getX1() && getY2() > getY1()) {
				g.drawLine(getX1(), getY1(), getX2(), getY2());
				g.drawLine(getX2(), getY2(), getLowerLeftX(), getLowerLeftY());
				g.drawLine(getX1(), getY1(), getLowerLeftX(), getLowerLeftY());
				int[] xarr = new int[3];
				int[] yarr = new int[3];
				xarr[0] = getX1();
				xarr[1] = getX2();
				xarr[2] = getLowerLeftX();
				yarr[0] = getY1();
				yarr[1] = getY2();
				yarr[2] = getLowerLeftY();
				g.setColor(getFillColor());
				g.fillPolygon(xarr, yarr, 3);
				g.setColor(getColor());

			} else if (getX2() < getX1() && getY2() > getY1()) {
				g.drawLine(getX1(), getY1(), getX2(), getY2());
				g.drawLine(getX2(), getY2(), getLowerRightX(), getLowerRightY());
				g.drawLine(getX1(), getY1(), getLowerRightX(), getLowerRightY());

				int[] xarr = new int[3];
				int[] yarr = new int[3];
				xarr[0] = getX1();
				xarr[1] = getX2();
				xarr[2] = getLowerRightX();
				yarr[0] = getY1();
				yarr[1] = getY2();
				yarr[2] = getLowerRightY();
				g.setColor(getFillColor());
				g.fillPolygon(xarr, yarr, 3);
				g.setColor(getColor());

			} else {
				g.drawLine(getX1(), getY1(), getX2(), getY2());
				g.drawLine(getX2(), getY2(), getUpperLeftX(), getUpperLeftY());
				g.drawLine(getX1(), getY1(), getUpperLeftX(), getUpperLeftY());

				int[] xarr = new int[3];
				int[] yarr = new int[3];
				xarr[0] = getX1();
				xarr[1] = getX2();
				xarr[2] = getUpperLeftX();
				yarr[0] = getY1();
				yarr[1] = getY2();
				yarr[2] = getUpperLeftY();
				g.setColor(getFillColor());
				g.fillPolygon(xarr, yarr, 3);
				g.setColor(getColor());
			}

		}

		if (this.checkSelected()) {
			g.setColor(Color.pink);
			g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
			g.drawRect(getUpperLeftX() - getStroke() / 2, getUpperLeftY() - getStroke() / 2, getWidth() + getStroke(),
					getHeight() + getStroke());
		}
		g.setStroke(new BasicStroke(getStroke()));
		g.setColor(getColor());
	}

	@Override
	public Boolean isContain(int x, int y) {
		// TODO Auto-generated method stub
		if ((x >= getUpperLeftX()) && (x <= getUpperLeftX() + getWidth()) && (y >= getUpperLeftY())
				&& (y <= getLowerRightY())) {
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
