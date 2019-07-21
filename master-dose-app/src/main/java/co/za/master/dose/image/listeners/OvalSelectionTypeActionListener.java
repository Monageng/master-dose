package co.za.master.dose.image.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.za.master.dose.model.ROITypeEnum;
import ij.IJ;

public class OvalSelectionTypeActionListener implements ActionListener {
	
	private ROITypeEnum roiTypeEnum;
	public OvalSelectionTypeActionListener(ROITypeEnum roiTypeEnum) {
		this.roiTypeEnum = roiTypeEnum;
	}
	
	public void actionPerformed(ActionEvent e) {
		IJ.showMessage("STARTING");
//		IJ.doCommand("ROI Manager...");
		
//		ImagePlus imagePlus = WindowManager.getCurrentImage();
//		IJ.run("ROI Manager...")
//		imagePlus.getCanvas().removeMouseListener(imagePlus.getCanvas().getMouseListeners()[0]);
						
		if (roiTypeEnum == ROITypeEnum.RECTANGULAR_ROI) {
//			IJ.makeRectangle(108, 64, 36, 41);;
			IJ.setTool("rectangle");	
		} else if (roiTypeEnum == ROITypeEnum.OVAL_ROI) {
//			IJ.makeOval(108, 64, 36, 41);
			IJ.setTool("oval");		
		} else if (roiTypeEnum == ROITypeEnum.POLYGON_ROI) {
//			IJ.make(108, 64, 36, 41);
			IJ.setTool("polygon");	
//			imagePlus.getCanvas().addMouseListener(new co.za.master.dose.image.listeners.MouseListener(imagePlus, Roi.POLYGON));
		} else if (roiTypeEnum == ROITypeEnum.FREE_HAND_ROI) {
//			IJ.makeOval(108, 64, 36, 41);
			IJ.setTool(6);	
		} else if (roiTypeEnum == ROITypeEnum.STREIGHT_ROI) {
//			IJ.makeLine(108, 64, 36, 41);
			IJ.setTool(4);;	
		}  else if (roiTypeEnum == ROITypeEnum.HAND) {
//			IJ.makeLine(108, 64, 36, 41);
			IJ.setTool("hand");;	
		} 
	}

}
