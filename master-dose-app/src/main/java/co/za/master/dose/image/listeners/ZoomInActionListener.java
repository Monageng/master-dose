package co.za.master.dose.image.listeners;

import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomInActionListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		ImagePlus imagePlus = WindowManager.getCurrentImage();
		if (imagePlus == null) {
//			System.out.println("Image is not loaded");
		} else {
//			IJ.run("In [+]");
//			IJ.setTool("hand");
			IJ.setTool("zoom");
//			IJ.runPlugIn("ij.plugin.Zoom", null);
		}
	}

}
