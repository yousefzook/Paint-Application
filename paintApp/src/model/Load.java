package model;

import java.awt.AWTException;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import controller.MainController;
import shapes.Shapes;
import view.MainFrame;
import view.MainPanel;

public class Load {

	private MainController ctrl;

	public Load() {
		ctrl = MainController.getCtrl(MainFrame.getMainPanel());
	}

	public void load() throws IOException, AWTException {
		// function of loading
		String fileName = ctrl.getFile("Open");
		if (fileName != null) {
			if (fileName.endsWith(".json")) {
				ctrl.clearDrawing();
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
				try {
					ctrl.setStoredShapes((LinkedList<Shapes>) in.readObject());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			} else if (fileName.endsWith(".xml")) {
				ctrl.clearDrawing();
				XMLDecoder decode = null;
				try {
					decode = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
					ctrl.getShapes().clear();
					ctrl.setStoredShapes((LinkedList<Shapes>) decode.readObject());
					ctrl.getPanel().repaint();
					decode.close();
				} catch (FileNotFoundException ex) {
				}

			} else if (fileName.endsWith(".png")) {

				ctrl.clearDrawing();
				ctrl.setLoadedImage((ImageIO.read(new File(fileName))));
				ctrl.setLoaded(true);
				ctrl.getPanel().repaint();
			} else {
				JOptionPane.showMessageDialog(null, " Unsupported file type \n", "Paint", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
