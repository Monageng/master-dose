package co.za.master.dose.image.listeners;

import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ROIManagerActionListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		ImagePlus imagePlus = WindowManager.getCurrentImage();
		if (imagePlus != null) {
			IJ.run("ROI Manager...");
		}
	}

}
