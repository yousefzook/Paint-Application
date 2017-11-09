package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import model.Save;
import view.Buttons;
import view.MainFrame;

public class ButtonHandler implements ActionListener {

	private boolean fillCheck = false;
	private MainController ctrl;

	private Buttons b;

	public ButtonHandler(Buttons button) {
		ctrl = MainController.getCtrl(MainFrame.getMainPanel());
		b = button;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// getting the clicked button

		Object source = event.getSource();

		if (source == b.getUndo()) {
			ctrl.clearLastShape();
		} else if (source == b.getRedo()) {
			ctrl.redoLastShape();
		} else if (source == b.getClear()) {

			if (!ctrl.deleteSelected()) {
				if (!ctrl.isAtFirst()) {
					Object[] options = { "Save", "Don't save", "Cancel" };
					int n = JOptionPane.showOptionDialog(null, " Do you want to save changes to Untitled work ?\n",
							"Paint", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
							options[2]);
					if (n == 0) {
						new Save().savePng();
						ctrl.clearDrawing();
					} else if (n == 1)
						ctrl.clearDrawing();
				} // end of if is AtFirst
			} // end of if deleteSelected

		} else if (source == b.getToggleLayer()) {
			ctrl.toggle();
		} else if (source == b.getSelect()) {
			b.getFill().setEnabled(false);
			ctrl.setShapeType("select");
		} else if (source == b.getEraser()) {
			ctrl.setShapeType("eraser");
		} else if (source == b.getColorPicker()) {
			Color c = JColorChooser.showDialog(null, "Choose a Color", Color.BLACK);
			ctrl.setStrokeColor(c);
		} else if (source == b.getLine()) {
			b.getFill().setEnabled(false);
			ctrl.setShapeType("line");
		} else if (source == b.getSquare()) {
			b.getFill().setEnabled(true);
			ctrl.setShapeType("sqr");
		} else if (source == b.getRectangle()) {
			b.getFill().setEnabled(true);
			ctrl.setShapeType("rect");
		} else if (source == b.getOval()) {
			b.getFill().setEnabled(true);
			ctrl.setShapeType("oval");
		} else if (source == b.getTriangle()) {
			b.getFill().setEnabled(true);
			ctrl.setShapeType("triangle");
		} else if (source == b.getRightTriangle()) {
			b.getFill().setEnabled(true);
			ctrl.setShapeType("rightTriangle");
		} else if (source == b.getFill()) {

			if (fillCheck) {
				fillCheck = false;
				ctrl.setShapeFilling(fillCheck);
			} else {
				fillCheck = true;
				Color filling = JColorChooser.showDialog(null, "Choose a Color", null);
				ctrl.setFillColor(filling);
				ctrl.setShapeFilling(fillCheck);
			}

		} else if (source == b.getPencil()) {
			b.getFill().setEnabled(false);
			ctrl.setShapeType("pencil");
		} else if (source == b.getBrush()) {
			b.getFill().setEnabled(false);
			ctrl.setShapeType("brush");
		} else if (source == b.getPaint()) {
			ctrl.setShapeType("paint");
		}
	} // end method actionPerformed

}
