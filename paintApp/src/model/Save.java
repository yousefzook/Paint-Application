package model;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.MainController;
import view.MainFrame;

public class Save {

	private MainController ctrl;

	public Save() {
		ctrl = MainController.getCtrl(MainFrame.getMainPanel());
	}

	public void savePng() {
		BufferedImage paintImage = null;
		try {
			paintImage = new Robot().createScreenCapture(ctrl.getPanel().bounds());
			ctrl.setLoadedImage(paintImage);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		Graphics2D graphics2D = paintImage.createGraphics();
		ctrl.getPanel().paint(graphics2D);

		String fileName = ctrl.getFile("Save");
		if (fileName != null) {
			if (!fileName.endsWith(".png")) {
				fileName = fileName + ".png";
			}

			try {
				ImageIO.write(paintImage, "png", new File(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void saveXml() {
		// save the paint as xml file

		String fileName = ctrl.getFile("Save");
		if (fileName != null) {
			if (!fileName.endsWith(".xml")) {
				fileName = fileName + ".xml";
			}

			try {
				XMLEncoder o = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
				o.writeObject(ctrl.getShapes());
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void savejson() {
		// save as jason file
		//
		// String fileName = getFile("Save");
		// if (fileName != null) {
		//
		// if (!fileName.endsWith(".json")) {
		// fileName = fileName + ".json";
		// }
		//
		// XStream stream = new XStream(new JettisonMappedXmlDriver());
		// ObjectOutputStream out;
		// try {
		// out = stream.createObjectOutputStream(
		// new FileOutputStream(fileName));
		// out = new ObjectOutputStream(new FileOutputStream(fileName));
		// out.writeObject(storedShapes);
		// out.close();
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		// }

	}
}
