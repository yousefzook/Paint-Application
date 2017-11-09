package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;;

public class MouseHandler extends MouseAdapter {
	private MainController ctrl;

	public MouseHandler(MainController c) {
		ctrl = c;
	}

	public void mousePressed(MouseEvent event) {

		ctrl.checkCtrlDown(event);
		switch (ctrl.getCurrentShapeType()) {
		case "insertedShape":
			ctrl.handle_insertedShape(event);
			break;
		case "line":
			ctrl.makeLine(event);

			break;
		case "rect":
			ctrl.makeRectangle(event);
			break;
		case "sqr":
			ctrl.makeSquare(event);

			break;
		case "oval":
			ctrl.makeOval(event);
			break;

		case "triangle":
			ctrl.makeTriangle(event);
			break;
		case "rightTriangle":
			ctrl.makeRightTriangle(event);
			break;
		case "eraser":
			ctrl.makeEraser(event);
			break;
		case "brush":
			ctrl.makeBrush(event);
			break;
		case "pencil":
			ctrl.makePencil(event);
			break;

		case "select":
			ctrl.handleSelect(event);
			break;

		case "paint":
			ctrl.handlePaint(event);

			break;
		default:
			break;
		}

	} // end method mousePressed

	public void mouseReleased(MouseEvent event) {
		// the mouse is released
		ctrl.handelMosueRel(event);

	} // end method mouseReleased

	public void mouseMoved(MouseEvent event) {
		// function to get the mouse coordinates

		ctrl.handleMouseMoved(event);

	} // end method mouseMoved

	public void mouseDragged(MouseEvent event) {
		// function done when the mouse is being dragged
		ctrl.handleMouseDrag(event);

	} // end method mouseDragged
}// end MouseHandler