package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import controller.MainController;
import controller.SliderHandler;
import controller.WindowHandler;
import view.Buttons.Button;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.FlowLayout;

/**
 * the main frame that holds the GUI Units.
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1450;
	private static final int HEIGHT = 800;

	private static MainPanel panel; // reference of the mainPanel
	// the buttons on frame
	// booleans to check the fill
	// a slider for the transparent
	private JSlider slider;
	// combo box for stroke
	private JComboBox<Object> stroke;
	// array of stroke levels
	private Object[] strokeLevels;
	// the panel of buttons and its padder
	private JPanel widgetJPanel;
	private JPanel widgetPadder;

	private static JFrame frame;

	public static JFrame getFrame() {
		if (frame == null)
			frame = new MainFrame();
		return frame;
	}

	// the constructor of mainFrame to initialize
	private MainFrame() {

		super("Paint"); // Show the upper name
		panel = MainPanel.getPanel();
		// initializing the buttons
		init();
	}

	private void initializeStrLevels() {
		strokeLevels = new Object[4];
		for (int i = 0; i < strokeLevels.length; i++) {
			strokeLevels[i] = new ImageIcon(getClass().getResource("/material/stroke" + (i + 1) + ".png"));
		}
	}

	private void init() {
		initializeStrLevels();
		setStrokeLevel();
		setSlider();
		addHandlers();
		setWidgetJPanel();
		// set the menu of file
		add(widgetPadder, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}

	private void setSlider() {
		// set the slider
		slider = new JSlider(0, 1, 100, 100);
		slider.setBounds(300, 15, 200, 40);
		slider.setMinorTickSpacing(0);
		slider.setMajorTickSpacing(0);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels(6));
		slider.setToolTipText("Slide to select transparency");
		add(slider, BorderLayout.SOUTH);
	}

	private void setStrokeLevel() {
		// set the stroke
		stroke = new JComboBox<Object>(strokeLevels);
		stroke.setToolTipText("Stroke width");

	}

	private void setWidgetJPanel() {
		new Menu(this);
		widgetJPanel = new JPanel();
		widgetJPanel.setLayout(new GridLayout(1, 1, 1, 10));
		widgetPadder = new JPanel();
		widgetPadder.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 20));
		// Separators between buttons
		JSeparator sep1 = new JSeparator(SwingConstants.VERTICAL);
		JSeparator sep2 = new JSeparator(SwingConstants.VERTICAL);
		// adding buttons to the panel
		List<Button> buttons = new Buttons().getButtons();
		for (int i = 0; i < buttons.size(); i++) {
			widgetJPanel.add(buttons.get(i).button);
			if (buttons.get(i).name.equals("rightTriangle")) {
				widgetJPanel.add(sep1);
				widgetJPanel.add(stroke);
			} else if (buttons.get(i).name.equals("toggleLayer"))
				widgetJPanel.add(sep2);
		}
		widgetPadder.add(widgetJPanel);
	}

	private void addHandlers() {
		// adding handler to slider
		slider.addChangeListener(new SliderHandler());
		addWindowListener(new WindowHandler());
		stroke.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// check if the combo box of stroke is selected

				if (e.getStateChange() == ItemEvent.SELECTED)
					if (e.getSource() == stroke)
						MainController.getCtrl(panel).setStrokeWeight(2 + 2 * stroke.getSelectedIndex());

			}// end of itemStateChanged
		});
		//
	}

	public JPanel getWidgetJPanel() {

		return widgetJPanel;
	}

	public static MainPanel getMainPanel() {

		return panel;
	}
} // end class MainFrame