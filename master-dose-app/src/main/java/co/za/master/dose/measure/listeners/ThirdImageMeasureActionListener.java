package co.za.master.dose.measure.listeners;

import ij.ImagePlus;
import ij.WindowManager;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import co.za.master.dose.model.ImageSideEnum;
import co.za.master.dose.model.ImageTypeEnum;
import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.utils.ImageHelper;
import co.za.master.dose.utils.MasterDoseCache;

public class ThirdImageMeasureActionListener implements MeasureActionListenerInterface {

	private ImageSideEnum imageSideEnum;
	private ImageTypeEnum imageTypeEnum;
//	private MeasurementVO bean;
	
	
	public ThirdImageMeasureActionListener() {
	}
	
	public ThirdImageMeasureActionListener(MeasurementVO bean, ImageSideEnum imageSideEnum, ImageTypeEnum imageTypeEnum) {
		this.imageSideEnum = imageSideEnum;
		this.imageTypeEnum = imageTypeEnum;
//		this.bean = bean;
	}
	
	public void actionPerformed(ActionEvent e) {
		ImagePlus imagePlus = WindowManager.getCurrentImage();
		System.out.println("imageTypeEnum : " + imageTypeEnum);
		System.out.println("imageSideEnum : " + imageSideEnum);
		
		if (imagePlus == null) {
			System.out.println("Image is not loaded");
		} else {
			double mean = ImageHelper.instance.getMeanCount(imagePlus);
			System.out.println("FirstImageMeasureActionListener Mean : " + mean);

//			 Check image side
			if (imageSideEnum == ImageSideEnum.Background) {
				if (imageTypeEnum == ImageTypeEnum.Anteria) {
					MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().setAnteriaBackground(mean);
				} else {
					MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().setPosteriaBackground(mean);
				}
			} else {
				
				if (imageTypeEnum == ImageTypeEnum.Anteria) {
					if (MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getAnteriaBackground() < 1) {
						JOptionPane.showConfirmDialog(null, "Background measurement not taken, please take background measurements first", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					}else {
						double dMean = ImageHelper.instance.getMean(imagePlus,MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getAnteriaBackground() + "");
						if (imageSideEnum == ImageSideEnum.Left) {
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().setAnteriaLeft(dMean);
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getAnteriaLeftField().setText("" + dMean);
						} else if (imageSideEnum == ImageSideEnum.Right) {
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().setAnteriaRight(dMean);
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getAnteriaRightField().setText("" + dMean);
						} else if (imageSideEnum == ImageSideEnum.Tumour) {
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().setAnteriaTumour(dMean);
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getAnteriaTumourField().setText("" + dMean);
						}
					}
					
				} else {
					if (MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getPosteriaBackground() < 1) {
						JOptionPane.showConfirmDialog(null, "Background measurement not taken, please take background measurements first", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					} else {
						double dMean = ImageHelper.instance.getMean(imagePlus,MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getPosteriaBackground() + "");
						if (imageSideEnum == ImageSideEnum.Left) {
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().setPosteriaLeft(dMean);
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getPosteriaLeftField().setText("" + dMean);
						} else if (imageSideEnum == ImageSideEnum.Right) {
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().setPosteriaRight(dMean);
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getPosteriaRightField().setText("" + dMean);
						} else if (imageSideEnum == ImageSideEnum.Tumour) {
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().setPosteriaTumour(dMean);
							MasterDoseCache.instance.getMeasurementVO().getThirdMeasurementVO().getPosteriaTumourField().setText("" + dMean);;
						}
					}
				}
			}
		}
	}
}
