package controller;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import model.Load;
import model.Save;
import view.MainFrame;
import view.Menu;

public class MenuHandler implements ActionListener, MenuListener {

	private MainController ctrl;
	private Menu menu;
	private Save save;
	private Load load;

	public MenuHandler(Menu menu) {
		ctrl = MainController.getCtrl(MainFrame.getMainPanel());
		this.menu = menu;
		save = new Save();
		load = new Load();
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		Object source = event.getSource();
		if (source == menu.getNewF()) {

			if (!ctrl.isAtFirst()) {
				Object[] options = { "Save", "Don't save", "Cancel" };
				int n = JOptionPane.showOptionDialog(null, " Do you want to save changes to Untitled work ?\n", "Paint",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				if (n == 0) {
					save.savePng();
					ctrl.clearDrawing();
				} else if (n == 1)
					ctrl.clearDrawing();
			} // end of is at first

		} else if (source == save) {
			save.savePng();
		} else if (source == menu.getPng()) {
			save.savePng();
		} else if (source == menu.getXml()) {
			save.saveXml();
		} else if (source == menu.getJson()) {
			save.savejson();
		} else if (source == menu.getLoad()) {

			if (!ctrl.isAtFirst()) {
				Object[] options = { "Save", "Don't save", "Cancel" };
				int n = JOptionPane.showOptionDialog(null, " Do you want to save changes ?\n", "Paint",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				if (n == 0) {
					save.savePng();
					try {
						load.load();
					} catch (IOException | AWTException e) {
						e.printStackTrace();
					}
				} else if (n == 1) {
					try {
						load.load();
					} catch (IOException | AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				try {
					load.load();
				} catch (IOException | AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} // end of else

		} else if (source == menu.getExit()) {

			if (!ctrl.isAtFirst()) {
				Object[] options = { "Save", "Don't save", "Cancel" };
				int n = JOptionPane.showOptionDialog(null, " Do you want to save changes to Untitled work ?\n", "Paint",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				if (n == 0) {
					save.savePng();
					System.exit(0);
				} else if (n == 1)
					System.exit(0);
			} else
				System.exit(0);

		} else if (source == menu.getInsert()) {
			String path = ctrl.getFile("Load class");
			// the path of loaded Class
			if (path != null) {
				if (path.endsWith(".jar")) {
					ctrl.loadPlugIn(path);
					JButton insertedShape = new JButton("inserted Shape");
					insertedShape.setContentAreaFilled(false);
					insertedShape.setSize(60, 60);
					insertedShape.setToolTipText("inserted Shape");
					((MainFrame) MainFrame.getFrame()).getWidgetJPanel().add(insertedShape);
					insertedShape.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub

							ctrl.setObject();
						}
					});
				} else
					JOptionPane.showMessageDialog(null, " Unsupported file type \n", "Paint",
							JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	@Override
	public void menuSelected(MenuEvent e) {
		// function when menu of file is selected

		Object source = e.getSource();
		if (source == menu.getFile()) {
			if (ctrl.isAtFirst()) {
				menu.getSave().setEnabled(false);
				menu.getSaveAs().setEnabled(false);
			} else {
				menu.getSave().setEnabled(true);
				menu.getSave().setEnabled(true);
			}
		} // end of if source == file
	}// end of menu selected

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

} // end private inner class ButtonHandler