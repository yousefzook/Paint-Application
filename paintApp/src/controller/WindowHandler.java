package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

import model.Save;
import view.MainFrame;

public class WindowHandler implements WindowListener {

	private MainController ctrl;

	public WindowHandler() {
		ctrl = MainController.getCtrl(MainFrame.getMainPanel());
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// check if the user clicked the close button
		if (!ctrl.isAtFirst()) {
			Object[] options = { "Save", "Don't save", "Cancel" };
			int n = JOptionPane.showOptionDialog(null, " Do you want to save changes to Untitled work ?\n", "Paint",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			if (n == 0) {
				new Save().savePng();
				System.exit(0);
			} else if (n == 1)
				System.exit(0);
			else if (n == 2)
				MainFrame.getFrame().setDefaultCloseOperation(MainFrame.DO_NOTHING_ON_CLOSE);
		} else
			System.exit(0);

	} // end of windowClosing function

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}
}
