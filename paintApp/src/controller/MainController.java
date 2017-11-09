package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import model.DynamicLoading;
import shapes.Line;
import shapes.Oval;
import shapes.Rectangle;
import shapes.RightTriangle;
import shapes.Shapes;
import shapes.Square;
import shapes.Triangle;
import view.MainPanel;

public class MainController {

	private static MainController controller;
	// the linked list which has the appeared Shapes
	private LinkedList<Shapes> storedShapes = new LinkedList<Shapes>();
	// the linked list which has the removed or undid Shapes
	private LinkedList<Shapes> bufferShapes = new LinkedList<Shapes>();
	private Shapes currentShapeObject; // a reference to the current shape
	private Shapes copiedShape;
	private Shapes selectedShape;
	private String currentShapeType; // a string hold the current shpe type
	private Color strokeColor; // the color of Stroke
	private Color fillColor; // the fill color
	private float transparent;
	private BufferedImage loadedImage = null;
	// flags indicates isShapeFil , is there a loaded png , is Shpe is Edited
	// and is ctrldown
	private boolean shapeFill, imagLoaded, shapeEdited, ctrlDown;
	public int strokeWeight, draggedX, draggedY, brushSize, eraserSize, indexOfSelected; // index
																							// in
																							// linked
																							// list
																							// for
																							// selected
	int[] differenceX; // Array of Differences between current X and left x of a
						// shape while dragging
	int[] differenceY; // Array of Differences between Y and left Y of a shape
						// while dragging
	private DynamicLoading classLoader; // a reference for the ClassLoader
	private MainPanel panel;

	public static MainController getCtrl(MainPanel panel) {
		if (controller == null) {
			controller = new MainController(panel);
		}
		return controller;
	}

	private MainController(MainPanel p) {
		panel = p;
		indexOfSelected = -1;
		currentShapeObject = null;
		currentShapeType = "line";
		strokeColor = Color.BLACK;
		fillColor = Color.white;
		copiedShape = null;
		strokeWeight = 2;
		draggedX = 0;
		draggedX = 0;
		brushSize = 10;
		eraserSize = 10;
		shapeFill = false;
		imagLoaded = false;
		shapeEdited = false;
		ctrlDown = false;
		differenceX = new int[30];
		differenceY = new int[30];
		transparent = 1F;
	};

	public void setObject() {
		// set the object type when insert a class

		currentShapeType = "insertedShape";
	}

	// Setters and Getters
	public void setShapeType(String s) {
		// set the shape type by the string sent from

		currentShapeType = s;
	}

	public void setTansparent(float tr) {
		// set the shape transparent by the string sent from

		transparent = tr;
	}

	public void setStrokeColor(Color c) {
		// set the shape stroke color by the string sent from

		strokeColor = c;
	}

	public void setStrokeWeight(int str) {
		// set the shape Stroke Weight by the string sent from

		strokeWeight = str;
	}

	public void setFillColor(Color c) {
		// set the shape fill color by the string sent from

		fillColor = c;
	}

	public void setShapeFilling(boolean filled) {
		shapeFill = filled;
	}

	public Boolean isAtFirst() {
		// return true if the user draws nothing

		return storedShapes.isEmpty();
	}

	public void setBrushSize(int siz) {
		brushSize = siz;
	}

	public void setEraserSize(int siz) {
		eraserSize = siz;
	}

	public void clearLastShape() {
		// clear the last drawen shape

		if (!storedShapes.isEmpty()) {
			// x = the index which refers to the original object for the copy
			int x = storedShapes.getFirst().getPreModIndex();
			if (x != -1) {
				storedShapes.get(storedShapes.size() - 1 - x).setEdited(false);
			}

			// adding the removed shape form the drawn linked l
			bufferShapes.addFirst(storedShapes.removeFirst());
			panel.repaint();
		}
	}

	public void redoLastShape() {
		// redo the last undo
		if (!bufferShapes.isEmpty()) {

			// x = the index which refers to the original object for the copy
			int x = bufferShapes.getFirst().getPreModIndex();
			if (x != -1) {
				storedShapes.get(storedShapes.size() - 1 - x).setEdited(true);
			}
			storedShapes.addFirst(bufferShapes.removeFirst());
			panel.repaint();
		}
	}

	public void clearDrawing() {
		// function to clear the panel
		storedShapes.clear();
		bufferShapes.clear();
		loadedImage = null;
		imagLoaded = false;
		panel.repaint();
	}

	public void toggle() {
		// a function to change the layers
		for (int i = 0; i < storedShapes.size(); i++)
			if (storedShapes.get(i).checkSelected()) {
				storedShapes.addLast(storedShapes.remove(i));
				selectedShape = null;
			}
		panel.repaint();
	}

	public Boolean deleteSelected() {

		Boolean flag = false;
		for (int i = 0; i < storedShapes.size(); i++) {
			if (storedShapes.get(i).checkSelected()) {
				storedShapes.get(i).setPreModIndex(storedShapes.size() - 1 - indexOfSelected);
				storedShapes.get(i).setEdited(true);
				storedShapes.addFirst(storedShapes.get(i));
				storedShapes.remove(indexOfSelected + 1);
				copiedShape.setEdited(true);
				storedShapes.add(indexOfSelected + 1, copiedShape);
				selectedShape = null;
				storedShapes.get(i).setSelected(false);
				flag = true;
				break;
			}
		}

		panel.repaint();
		return flag;
	}

	public String getFile(String s) {
		// function to show the file chooser and return the path

		JFileChooser test = new JFileChooser();
		test.showDialog(null, s);
		File file = test.getSelectedFile();
		if (file == null)
			return null;

		return file.getAbsolutePath();
	}

	public void loadPlugIn(String p) {
		// function to be done when loading a class

		classLoader = new DynamicLoading();
		classLoader.SetClassPath(p);
	}

	public MainPanel getPanel() {
		return panel;
	}

	public LinkedList<Shapes> getShapes() {
		return storedShapes;
	}

	public Shapes getCurrentObj() {
		return currentShapeObject;
	}

	public Shapes getSelectedShape() {
		return selectedShape;
	}

	public String getCurrentShapeType() {
		return currentShapeType;
	}

	public boolean IsImageLoaded() {
		return imagLoaded;
	}

	public void setLoaded(boolean b) {
		imagLoaded = b;
	}

	public Image getLoadedImage() {
		return loadedImage;
	}

	public void setLoadedImage(BufferedImage read) {
		loadedImage = read;
	}

	public void setStoredShapes(LinkedList<Shapes> readObject) {
		storedShapes = readObject;

	}

	////////////////////////////////////////// mouse handler goes here
	////////////////////////////////////////// ///////////////////////////////////////
	// TODO

	public void checkCtrlDown(MouseEvent event) {
		ctrlDown = event.isControlDown();
		if (shapeEdited) {
			selectedShape.setPreModIndex(storedShapes.size() - 1 - indexOfSelected);
			storedShapes.addFirst(selectedShape);
			storedShapes.remove(indexOfSelected + 1);
			copiedShape.setEdited(true);
			storedShapes.add(indexOfSelected + 1, copiedShape);
			shapeEdited = false;
		}
		if (!ctrlDown) {
			for (Shapes s : storedShapes) {
				s.setSelected(false);
			}
			selectedShape = null;
		}
	}

	public void handle_insertedShape(MouseEvent event) {
		try {
			currentShapeObject = (Shapes) classLoader.loadClass().newInstance();
			currentShapeObject.setX1(event.getX());
			currentShapeObject.setY1(event.getY());
			currentShapeObject.setX2(event.getX());
			currentShapeObject.setY2(event.getY());
			currentShapeObject.setStrokeColor(strokeColor);
			currentShapeObject.setStroke(strokeWeight);
			currentShapeObject.setFill(shapeFill);
			currentShapeObject.setFillColor(fillColor);
			currentShapeObject.setTransparent(transparent);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	public void makeLine(MouseEvent event) {
		currentShapeObject = new Line(event.getX(), event.getY(), event.getX(), event.getY(), strokeColor, strokeWeight,
				transparent);
	}

	public void makeRectangle(MouseEvent event) {
		currentShapeObject = new Rectangle(event.getX(), event.getY(), event.getX(), event.getY(), strokeColor,
				strokeWeight, fillColor, shapeFill, transparent);
	}

	public void makeSquare(MouseEvent event) {
		currentShapeObject = new Square(event.getX(), event.getY(), event.getX(), event.getY(), strokeColor,
				strokeWeight, fillColor, shapeFill, transparent);
	}

	public void makeOval(MouseEvent event) {
		currentShapeObject = new Oval(event.getX(), event.getY(), event.getX(), event.getY(), strokeColor, strokeWeight,
				fillColor, shapeFill, transparent);
	}

	public void makeTriangle(MouseEvent event) {
		currentShapeObject = new Triangle(event.getX(), event.getY(), event.getX(), event.getY(), strokeColor,
				strokeWeight, fillColor, shapeFill, transparent);
	}

	public void makeRightTriangle(MouseEvent event) {
		currentShapeObject = new RightTriangle(event.getX(), event.getY(), event.getX(), event.getY(), strokeColor,
				strokeWeight, fillColor, shapeFill, transparent);
	}

	public void makeEraser(MouseEvent event) {
		currentShapeObject = new Line(event.getX(), event.getY(), event.getX(), event.getY(), Color.WHITE, eraserSize,
				transparent);
	}

	public void makeBrush(MouseEvent event) {
		currentShapeObject = new Oval(event.getX(), event.getY(), event.getX(), event.getY(), fillColor, strokeWeight,
				fillColor, true, transparent);
	}

	public void makePencil(MouseEvent event) {
		currentShapeObject = new Line(event.getX(), event.getY(), event.getX(), event.getY(), strokeColor, strokeWeight,
				transparent);
	}

	public void handleSelect(MouseEvent event) {
		indexOfSelected = -1;
		for (Shapes s : storedShapes) {
			indexOfSelected++;
			if (s.isContain(event.getX(), event.getY()) && !s.getEdited()) {
				s.setSelected(true);
				s.setTransparent(transparent);
				panel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				Class<? extends Shapes> shapetype = s.getClass();
				try {
					copiedShape = (Shapes) shapetype.newInstance();
					copiedShape.setFill(s.getFill());
					copiedShape.setFillColor(s.getFillColor());
					copiedShape.setStrokeColor(s.getColor());
					copiedShape.setStroke(s.getStroke());
					copiedShape.setTransparent(s.getTransparent());
					copiedShape.setX1(s.getX1());
					copiedShape.setY1(s.getY1());
					copiedShape.setX2(s.getX2());
					copiedShape.setY2(s.getY2());
					copiedShape.setPreModIndex(s.getPreModIndex());

				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				selectedShape = s;
				break;
			}
		}

		panel.repaint();
		currentShapeObject = null;
		int i = 0;
		if (!storedShapes.isEmpty()) {
			for (Shapes s : storedShapes)
				if (s.checkSelected()) {
					differenceX[i] = event.getX() - s.getUpperLeftX();
					differenceY[i] = event.getY() - s.getUpperLeftY();
					i++;
				}
		}

	}

	public void handlePaint(MouseEvent event) {
		indexOfSelected = -1;
		Boolean noShapeSelected = true;
		for (Shapes s : storedShapes) {
			indexOfSelected++;
			if (s.isContain(event.getX(), event.getY())) {
				Class<? extends Shapes> shapetype = s.getClass();
				try {
					copiedShape = (Shapes) shapetype.newInstance();
					copiedShape.setFill(s.getFill());
					copiedShape.setFillColor(s.getFillColor());
					copiedShape.setStrokeColor(s.getColor());
					copiedShape.setStroke(s.getStroke());
					copiedShape.setTransparent(s.getTransparent());
					copiedShape.setX1(s.getX1());
					copiedShape.setY1(s.getY1());
					copiedShape.setX2(s.getX2());
					copiedShape.setY2(s.getY2());
					copiedShape.setPreModIndex(s.getPreModIndex());

				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}

				shapeEdited = true;
				s.setFill(true);
				s.setFillColor(fillColor);
				selectedShape = s;
				noShapeSelected = false;
				break;
			}
		}
		if (noShapeSelected) {
			panel.setBackground(fillColor);
			if (strokeColor.equals(fillColor)) {
				strokeColor = Color.white;
			}

		}

		currentShapeObject = null;
	}

	public void handelMosueRel(MouseEvent event) {

		if (currentShapeType != "select" && currentShapeType != "paint") {
			if (event == null) {

				if (currentShapeType == "eraser") {
					currentShapeObject.setX2(draggedX);
					currentShapeObject.setY2(draggedY);
				} else if (currentShapeType == "brush") {
					currentShapeObject.setX2(draggedX + brushSize);
					currentShapeObject.setY2(draggedY + brushSize);
				} else if (currentShapeType == "pencil") {
					currentShapeObject.setX2(draggedX);
					currentShapeObject.setY2(draggedY);
				}
			} else {

				currentShapeObject.setX2(event.getX());
				currentShapeObject.setY2(event.getY());
			}

			storedShapes.addFirst(currentShapeObject);
			currentShapeObject = null;
			bufferShapes.clear();
		}

		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel.repaint();
	}

	public void handleMouseMoved(MouseEvent event) {
		panel.getCoordinates().setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));
		int x = 0;
		for (Shapes ss : storedShapes)
			if (ss.checkSelected() && (x = ss.isOnBorder(event.getX(), event.getY())) != -1) {
				switch (x) {
				case 0:
					panel.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
					break;
				case 1:
					panel.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
					break;
				case 2:
					panel.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
					break;
				case 3:
					panel.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
					break;
				case 4:
					panel.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
					break;
				case 5:
					panel.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
					break;
				case 6:
					panel.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
					break;
				case 7:
					panel.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
					break;

				default:
					break;
				}
			}
		if (x == -1) {
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	public void handleMouseDrag(MouseEvent event) {
		if ((currentShapeType != "select") && (currentShapeType != "paint")) {
			currentShapeObject.setX2(event.getX());
			currentShapeObject.setY2(event.getY());
			if ((currentShapeType == "eraser") || (currentShapeType == "brush") || (currentShapeType == "pencil")) {
				currentShapeObject.setX2(event.getX());
				currentShapeObject.setY2(event.getY());
				draggedX = event.getX();
				draggedY = event.getY();
				// mouseReleased(null);
				panel.repaint();
				if (currentShapeType == "eraser") {
					currentShapeObject = new Line(event.getX(), event.getY(), event.getX(), event.getY(), Color.WHITE,
							eraserSize, transparent);
				} else if (currentShapeType == "pencil") {
					currentShapeObject = new Line(event.getX(), event.getY(), event.getX(), event.getY(), strokeColor,
							strokeWeight, transparent);
				} else if (currentShapeType == "brush") {
					currentShapeObject = new Oval(event.getX(), event.getY(), event.getX(), event.getY(), strokeColor,
							strokeWeight, strokeColor, true, transparent);
				}
			}
		}
		int x = 0;
		int i = 0;
		for (Shapes ss : storedShapes) {
			if ((ss.checkSelected().booleanValue()) && ((x = ss.isOnBorder(event.getX(), event.getY())) != -1)) {
				shapeEdited = true;
				switch (x) {
				case 0:
					panel.setCursor(Cursor.getPredefinedCursor(6));
					if (ss.getX1() < ss.getX2()) {
						ss.setX1(event.getX());
					} else {
						ss.setX2(event.getX());
					}
					if (ss.getY1() < ss.getY2()) {
						ss.setY1(event.getY());
					} else {
						ss.setY2(event.getY());
					}
					break;
				case 1:
					panel.setCursor(Cursor.getPredefinedCursor(7));
					if (ss.getX1() < ss.getX2()) {
						ss.setX2(event.getX());
					} else {
						ss.setX1(event.getX());
					}
					if (ss.getY1() < ss.getY2()) {
						ss.setY1(event.getY());
					} else {
						ss.setY2(event.getY());
					}
					break;
				case 2:
					panel.setCursor(Cursor.getPredefinedCursor(5));
					if (ss.getX1() < ss.getX2()) {
						ss.setX2(event.getX());
					} else {
						ss.setX1(event.getX());
					}
					if (ss.getY1() < ss.getY2()) {
						ss.setY2(event.getY());
					} else {
						ss.setY1(event.getY());
					}
					break;
				case 3:
					panel.setCursor(Cursor.getPredefinedCursor(4));
					if (ss.getX1() < ss.getX2()) {
						ss.setX1(event.getX());
					} else {
						ss.setX2(event.getX());
					}
					if (ss.getY1() < ss.getY2()) {
						ss.setY2(event.getY());
					} else {
						ss.setY1(event.getY());
					}
					break;
				case 4:
					panel.setCursor(Cursor.getPredefinedCursor(8));
					if (ss.getY1() < ss.getY2()) {
						ss.setY1(event.getY());
					} else {
						ss.setY2(event.getY());
					}
					break;
				case 5:
					panel.setCursor(Cursor.getPredefinedCursor(11));
					if (ss.getX1() < ss.getX2()) {
						ss.setX2(event.getX());
					} else {
						ss.setX1(event.getX());
					}
					break;
				case 6:
					panel.setCursor(Cursor.getPredefinedCursor(9));
					if (ss.getY1() < ss.getY2()) {
						ss.setY2(event.getY());
					} else {
						ss.setY1(event.getY());
					}
					break;
				case 7:
					panel.setCursor(Cursor.getPredefinedCursor(10));
					if (ss.getX1() < ss.getX2()) {
						ss.setX1(event.getX());
					} else {
						ss.setX2(event.getX());
					}
					break;
				}
			}
		}
		i = 0;
		if (x == -1) {
			panel.setCursor(Cursor.getPredefinedCursor(13));
			for (Shapes s : storedShapes) {
				if (s.checkSelected().booleanValue()) {
					shapeEdited = true;
					int w = s.getWidth();
					int h = s.getHeight();
					if (s.getX1() < s.getX2()) {
						s.setX1(event.getX() - differenceX[i]);
						s.setX2(event.getX() - differenceX[i] + w);
					} else {
						s.setX2(event.getX() - differenceX[i]);
						s.setX1(event.getX() - differenceX[i] + w);
					}
					if (s.getY1() < s.getY2()) {
						s.setY1(event.getY() - differenceY[i]);
						s.setY2(event.getY() - differenceY[i] + h);
					} else {
						s.setY2(event.getY() - differenceY[i]);
						s.setY1(event.getY() - differenceY[i] + h);
					}
					i++;
				}
			}
		}
		panel.repaint();
	} // end method mouseDragged

}
