package co.za.master.dose.image.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ij.ImagePlus;
import ij.WindowManager;

public class ExitActionListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		ImagePlus imagePlus = WindowManager.getCurrentImage();
		if (imagePlus != null) {
			imagePlus.close();
		}
	}

}
