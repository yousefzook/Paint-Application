package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import controller.MainController;
import controller.MouseHandler;
import javax.swing.JLabel;
import java.awt.BorderLayout;

/**
 * The paint Panel Class. to paint the shapes on it
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L; // Serialize the class
	private JLabel coordinates; // a label to show Mouse Coordinates
	private MainController ctrl;
	private static MainPanel panel;

	public static MainPanel getPanel() {
		if (panel == null)
			panel = new MainPanel();
		return panel;
	}

	// the MainPanel constructors which has the coordinates label
	private MainPanel() {
		// the coordinates of the panel
		coordinates = new JLabel("");
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		add(coordinates, BorderLayout.SOUTH);
		ctrl = MainController.getCtrl(this);
		MouseHandler handler = new MouseHandler(ctrl);
		addMouseListener(handler);
		addMouseMotionListener(handler);

	}// end of constructor

	public JLabel getCoordinates() {
		return coordinates;
	}

	public void paintComponent(Graphics g) {
		// the paint method
		super.paintComponent(g);

		if (ctrl.IsImageLoaded())
			g.drawImage(ctrl.getLoadedImage(), 0, 0, this);

		Graphics2D g2 = (Graphics2D) g;
		int size = ctrl.getShapes().size();
		for (int i = size - 1; i >= 0; i--) {
			if (!ctrl.getShapes().get(i).getEdited())
				ctrl.getShapes().get(i).draw(g2);
		}

		if (ctrl.getCurrentObj() != null) {
			ctrl.getCurrentObj().draw(g2);
		}
		if (ctrl.getSelectedShape() != null) {
			ctrl.getSelectedShape().draw(g2);
		}
	}

} // end class MainPanel