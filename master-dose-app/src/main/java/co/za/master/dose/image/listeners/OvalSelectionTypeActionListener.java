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
				
		if (roiTypeEnum == ROITypeEnum.RECTANGULAR_ROI) {
			IJ.setTool("rectangle");	
		} else if (roiTypeEnum == ROITypeEnum.OVAL_ROI) {
			IJ.setTool("oval");		
		} else if (roiTypeEnum == ROITypeEnum.POLYGON_ROI) {
			IJ.setTool("polygon");	
		} else if (roiTypeEnum == ROITypeEnum.FREE_HAND_ROI) {
			IJ.setTool(6);	
		} else if (roiTypeEnum == ROITypeEnum.STREIGHT_ROI) {
			IJ.setTool(4);;	
		}  else if (roiTypeEnum == ROITypeEnum.HAND) {
			IJ.setTool("hand");;	
		} 
	}

}
