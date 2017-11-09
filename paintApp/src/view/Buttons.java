package view;

import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import controller.ButtonHandler;

public class Buttons {

	private JToggleButton undo, redo, clear, toggleLayer, select, pencil, brush, line, rectangle, square, oval,
			triangle, rightTriangle, colorPicker, eraser, fill, paint;
	private List<Button> Buttons;
	private ImageIcon icon;

	public Buttons() {
		initializeButtons();
		setButtons();
		addHandler();
	}

	private void initializeButtons() {
		Buttons = Arrays.asList(new Button(select = new JToggleButton(), "select"),
				new Button(brush = new JToggleButton(), "brush"), new Button(pencil = new JToggleButton(), "pencil"),
				new Button(line = new JToggleButton(), "line"),
				new Button(rectangle = new JToggleButton(), "rectangle"),
				new Button(square = new JToggleButton(), "square"), new Button(oval = new JToggleButton(), "oval"),
				new Button(triangle = new JToggleButton(), "triangle"),
				new Button(rightTriangle = new JToggleButton(), "rightTriangle"),
				new Button(colorPicker = new JToggleButton(), "colorPicker"),
				new Button(eraser = new JToggleButton(), "eraser"), new Button(fill = new JToggleButton(), "fill"),
				new Button(paint = new JToggleButton(), "paint"),
				new Button(toggleLayer = new JToggleButton(), "toggleLayer"),
				new Button(undo = new JToggleButton(), "undo"), new Button(redo = new JToggleButton(), "redo"),
				new Button(clear = new JToggleButton(), "clear"));
	}

	private void setButtons() {
		Buttons.forEach(b -> {
			icon = new ImageIcon(getClass().getResource("/material/" + b.name + ".png"));
			b.button.setIcon(icon);
			b.button.setBorder(BorderFactory.createEmptyBorder());
			b.button.setContentAreaFilled(false);
			b.button.setToolTipText(Character.toUpperCase(b.name.charAt(0)) + b.name.substring(1));
		});
	}

	private void addHandler() {
		ButtonHandler buttonHandler = new ButtonHandler(this);
		// adding handler to buttons
		Buttons.forEach(b -> b.button.addActionListener(buttonHandler));
	}

	public List<Button> getButtons() {
		return Buttons;
	}

	public JToggleButton getClear() {
		return clear;
	}

	public JToggleButton getUndo() {
		return undo;
	}

	public JToggleButton getRedo() {
		return redo;
	}

	public JToggleButton getToggleLayer() {
		return toggleLayer;
	}

	public JToggleButton getSelect() {
		return select;
	}

	public JToggleButton getPencil() {
		return pencil;
	}

	public JToggleButton getBrush() {
		return brush;
	}

	public JToggleButton getLine() {
		return line;
	}

	public JToggleButton getRectangle() {
		return rectangle;
	}

	public JToggleButton getSquare() {
		return square;
	}

	public JToggleButton getOval() {
		return oval;
	}

	public JToggleButton getTriangle() {
		return triangle;
	}

	public JToggleButton getRightTriangle() {
		return rightTriangle;
	}

	public JToggleButton getColorPicker() {
		return colorPicker;
	}

	public JToggleButton getEraser() {
		return eraser;
	}

	public JToggleButton getFill() {
		return fill;
	}

	public JToggleButton getPaint() {
		return paint;
	}

	// inner class Button
	class Button {
		JToggleButton button;
		String name;

		Button(JToggleButton toggleLayer, String n) {
			button = toggleLayer;
			name = n;
		}
	}
}
