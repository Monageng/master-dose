package co.za.master.dose.image.listeners;

import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class CropActionListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		ImagePlus imagePlus = WindowManager.getCurrentImage();
		JOptionPane.showConfirmDialog(null, "This feature is not yet supported", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
//		System.out.println("");
//		if (imagePlus == null) {
////			System.out.println("Image is not loaded");
//		} else {
//			IJ.run("Out [-]");
//		}
	}

}
