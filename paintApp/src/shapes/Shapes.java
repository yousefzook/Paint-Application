package shapes;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * this class is the abstract class for all the shapes
 */
public abstract class Shapes {
	private int x1, y1, x2, y2; // coordinates of shape
	// if this shape is a copy an index referred to the index of original shape
	private int strokeWeight, preModIndex = -1;
	private Color strokeColor; // color of shape
	private Color fillColor;
	private boolean fill, selected, edited;
	private float transparent;

	// the initializing constructor
	public Shapes() {
		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
		preModIndex = -1;
		edited = false;
		strokeColor = Color.black;
		fillColor = null;
		strokeWeight = 1;
		this.selected = false;
		this.fill = false;
		this.transparent = 1F;
	}

	/**
	 * overloaded constructor which takes variables for coordinates and color
	 * assigning them to the instance variables in the class
	 */
	public Shapes(int x1, int y1, int x2, int y2, Color sColor, int strokeW, Color fColor, boolean fill, float tr) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.strokeColor = sColor;
		this.strokeWeight = strokeW;
		this.fillColor = fColor;
		this.fill = fill;
		this.transparent = tr;

	}

	public void setEdited(Boolean e) {
		// set the edited = true if the shape is edited
		this.edited = e;
	}

	public Boolean getEdited() {
		// returned true id the shape is edited

		return edited;
	}

	public void setPreModIndex(int ind) {
		// set the index of the original shape
		// and returns -1 if it is original

		this.preModIndex = ind;
	}

	public int getPreModIndex() {
		// return the index of the original shape
		// and returns -1 if it is original

		return preModIndex;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public Boolean checkSelected() {

		return selected;
	}

	public void setSelected(Boolean state) {

		this.selected = state;
	}

	public int getStroke() {
		return strokeWeight;
	}

	public int getUpperLeftX() {
		return Math.min(getX1(), getX2());
	}

	public int getUpperLeftY() {
		return Math.min(getY1(), getY2());
	}

	public int getLowerLeftX() {
		return getUpperLeftX();
	}

	public int getLowerLeftY() {
		return Math.max(getY1(), getY2());
	}

	public int getUpperRightX() {
		return Math.max(getX1(), getX2());
	}

	public int getUpperRightY() {
		return getUpperLeftY();
	}

	public int getLowerRightX() {
		return Math.max(getX1(), getX2());
	}

	public int getLowerRightY() {
		return Math.max(getY1(), getY2());
	}

	public int getWidth() {
		return Math.abs(getX1() - getX2());
	}

	public int getHeight() {
		return Math.abs(getY1() - getY2());
	}

	public float getTransparent() {

		return this.transparent;
	}

	public void setTransparent(float t) {

		this.transparent = t;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public void setFillColor(Color color) {
		this.fillColor = color;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	public Color getColor() {
		return this.strokeColor;
	}

	public void setStrokeColor(Color c) {

		this.strokeColor = c;
	}

	public void setStroke(int str) {

		this.strokeWeight = str;
	}

	public Color getFillColor() {
		return this.fillColor;
	}

	public boolean getFill() {
		return this.fill;
	}

	abstract public void draw(Graphics2D g);

	// check if a shape contains the mouse point
	abstract public Boolean isContain(int x, int y);

	// check the mouse point is on the border of the shape
	abstract public int isOnBorder(int x, int y);

}