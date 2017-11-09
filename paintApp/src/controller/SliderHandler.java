package controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.MainFrame;

public class SliderHandler implements ChangeListener {

	private MainController ctrl;

	public SliderHandler() {
		ctrl = MainController.getCtrl(MainFrame.getMainPanel());
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// check the slider state

		int value = ((JSlider) e.getSource()).getValue();
		// TopPanel.this.thicknessLabel.setText(value);
		ctrl.setTansparent((value / 100.0F));
	}

}
