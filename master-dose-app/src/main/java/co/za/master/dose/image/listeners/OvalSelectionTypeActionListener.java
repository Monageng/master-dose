package co.za.master.dose.image.listeners;

import ij.IJ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.za.master.dose.model.ROITypeEnum;

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
		
		
		if (roiTypeEnum == ROITypeEnum.RECTANGULAR_ROI) {
			IJ.makeRectangle(108, 64, 36, 41);;
//			IJ.setTool("rectangle");	
		} else if (roiTypeEnum == ROITypeEnum.OVAL_ROI) {
			IJ.makeOval(108, 64, 36, 41);
//			IJ.setTool("oval");		
		} else if (roiTypeEnum == ROITypeEnum.POLYGON_ROI) {
			IJ.makePoint(108, 41);
//			IJ.setTool("polygon");	
		} else if (roiTypeEnum == ROITypeEnum.FREE_HAND_ROI) {
			IJ.makeLine(108, 64, 36, 41);
//			IJ.setTool("freehand");	
		} else if (roiTypeEnum == ROITypeEnum.STREIGHT_ROI) {
			IJ.makeLine(108, 64, 36, 41);
//			IJ.setTool(1);;	
		} 
	}

}
