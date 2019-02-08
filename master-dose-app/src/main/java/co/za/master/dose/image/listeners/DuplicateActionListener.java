package co.za.master.dose.image.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import ij.ImagePlus;
import ij.WindowManager;
import ij.io.Opener;
import ij.process.ImageProcessor;

public class DuplicateActionListener implements ActionListener {



	public void actionPerformed(ActionEvent e) {

		ImagePlus imagePlus = WindowManager.getCurrentImage();

		final JFileChooser fc = new JFileChooser();
		JDialog dialog = new JDialog();
		int i = fc.showOpenDialog(dialog);
		if (i == 0) {
			Opener opener = new Opener();
			ImagePlus newImage = opener.openImage(fc.getSelectedFile().getPath());

			ImageProcessor ip = imagePlus.getProcessor();
			ImageProcessor newIP = ip.duplicate();
			newIP.drawRoi(imagePlus.getRoi());

			newImage.setProcessor(newIP);

			newImage.show();
			
//			ImageWindow imageWindow = WindowManager.getCurrentWindow();
//			imageWindow.setMenuBar(ImageHelper.instance.createImageMenuBar(new MeasurementVO(), ImageTypeEnum.Anteria, ImageNumberEnum.FirstImage));

		}
	}

}
