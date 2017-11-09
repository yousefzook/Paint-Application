package view;

import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.MenuHandler;

public class Menu {
	// the file menu items
	private JMenuBar menu;
	private JMenu file;
	private JMenuItem newF, save;
	private JMenu saveAs;
	private JMenuItem png, xml, json, load, insert, exit;
	private ImageIcon icon;

	Menu(JFrame f) {
		menu = new JMenuBar();
		f.setJMenuBar(menu);
		createFileMenu();
		addHandler();
	}

	private void createFileMenu() {

		file = new JMenu("File");
		file.setFont(new Font("Arial", Font.PLAIN, 20));
		menu.add(file);

		icon = new ImageIcon(getClass().getResource("/material/newFile.png"));
		newF = new JMenuItem("New", icon);
		newF.setMnemonic(KeyEvent.VK_N);

		icon = new ImageIcon(getClass().getResource("/material/save.png"));
		save = new JMenuItem("Save", icon);
		save.setMnemonic(KeyEvent.VK_S);

		saveAs = new JMenu("Save As..");
		save.setMnemonic(KeyEvent.VK_S);

		icon = new ImageIcon(getClass().getResource("/material/png.png"));
		png = new JMenuItem("PNG", icon);
		png.setMnemonic(KeyEvent.VK_P);

		icon = new ImageIcon(getClass().getResource("/material/xml.png"));
		xml = new JMenuItem("XML", icon);
		xml.setMnemonic(KeyEvent.VK_X);

		icon = new ImageIcon(getClass().getResource("/material/json.png"));
		json = new JMenuItem("JSON", icon);
		json.setMnemonic(KeyEvent.VK_J);
		// adding the saveAs items
		saveAs.add(png);
		saveAs.add(xml);
		saveAs.add(json);

		icon = new ImageIcon(getClass().getResource("/material/openfolder.png"));
		load = new JMenuItem("Load", icon);
		load.setMnemonic(KeyEvent.VK_L);

		insert = new JMenuItem("Insert Plug-in");
		insert.setMnemonic(KeyEvent.VK_I);

		exit = new JMenuItem("EXit");
		exit.setMnemonic(KeyEvent.VK_E);
		// adding the file items
		file.add(newF);
		file.addSeparator();
		file.add(save);
		file.add(saveAs);
		file.add(load);
		file.add(insert);
		file.addSeparator();
		file.add(exit);

	}

	private void addHandler() {
		MenuHandler handler = new MenuHandler(this);
		png.addActionListener(handler);
		xml.addActionListener(handler);
		json.addActionListener(handler);
		load.addActionListener(handler);
		save.addActionListener(handler);
		exit.addActionListener(handler);
		newF.addActionListener(handler);
		insert.addActionListener(handler);
		file.addMenuListener(handler);
	}

	public JMenu getFile() {
		return file;
	}

	public JMenu getSaveAs() {
		return saveAs;
	}

	public JMenuItem getNewF() {
		return save;
	}

	public JMenuItem getSave() {
		return save;
	}

	public JMenuItem getPng() {
		return save;
	}

	public JMenuItem getXml() {
		return save;
	}

	public JMenuItem getJson() {
		return save;
	}

	public JMenuItem getLoad() {
		return save;
	}

	public JMenuItem getInsert() {
		return save;
	}

	public JMenuItem getExit() {
		return save;
	}

}
