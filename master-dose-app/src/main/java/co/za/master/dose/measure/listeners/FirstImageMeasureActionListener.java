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

public class FirstImageMeasureActionListener implements MeasureActionListenerInterface {

//	private MeasurementVO bean;
	private ImageSideEnum imageSideEnum;
	private ImageTypeEnum imageTypeEnum;
	
	public FirstImageMeasureActionListener() {
	}
	
	public FirstImageMeasureActionListener(MeasurementVO bean, ImageSideEnum imageSideEnum, ImageTypeEnum imageTypeEnum) {
		this.imageSideEnum = imageSideEnum;
		this.imageTypeEnum = imageTypeEnum;
//		this.bean = bean;
	}
	
	public void actionPerformed(ActionEvent e) {
		ImagePlus imagePlus = WindowManager.getCurrentImage();
//		System.out.println("imageTypeEnum : " + imageTypeEnum);
//		System.out.println("imageSideEnum : " + imageSideEnum);
		
		MeasurementVO bean = MasterDoseCache.instance.getMeasurementVO();
		if (imagePlus == null) {
//			System.out.println("Image is not loaded");
		} else {
			double mean = ImageHelper.instance.getMeanCount(imagePlus);
			System.out.println("FirstImageMeasureActionListener Mean : " + mean + " imageTypeEnum " + imageTypeEnum);

//			 Check image side
			if (imageSideEnum == ImageSideEnum.Background) {
				if (imageTypeEnum == ImageTypeEnum.Anteria) {
					MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().setAnteriaBackground(mean);
				} else {
					MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().setPosteriaBackground(mean);
				}
			} else {
				
				if (imageTypeEnum == ImageTypeEnum.Anteria) {
										
					if (MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().getAnteriaBackground() < 1) {
						JOptionPane.showConfirmDialog(null, "Background measurement not taken, please take background measurements first", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					}else {
						double dMean = ImageHelper.instance.getMean(imagePlus,bean.getFirstMeasurementVO().getAnteriaBackground() + "");
						System.out.println("FirstImageMeasureActionListener Mean : " + dMean);
						if (imageSideEnum == ImageSideEnum.Left) {
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().setAnteriaLeft(dMean);
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().getAnteriaLeftField().setText("" + dMean);
						} else if (imageSideEnum == ImageSideEnum.Right) {
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().setAnteriaRight(dMean);
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().getAnteriaRightField().setText("" + dMean);
						} else if (imageSideEnum == ImageSideEnum.Tumour) {
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().setAnteriaTumour(dMean);
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().getAnteriaTumourField().setText("" + dMean);
						}
					}
					
				} else {
					if (bean.getFirstMeasurementVO().getPosteriaBackground() < 1) {
						JOptionPane.showConfirmDialog(null, "Background measurement not taken, please take background measurements first", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					} else {
						double dMean = ImageHelper.instance.getMean(imagePlus,bean.getFirstMeasurementVO().getPosteriaBackground() + "");
						if (imageSideEnum == ImageSideEnum.Left) {
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().setPosteriaLeft(dMean);
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().getPosteriaLeftField().setText("" + dMean);
						} else if (imageSideEnum == ImageSideEnum.Right) {
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().setPosteriaRight(dMean);
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().getPosteriaRightField().setText("" + dMean);
						} else if (imageSideEnum == ImageSideEnum.Tumour) {
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().setPosteriaTumour(dMean);
							MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().getPosteriaTumourField().setText("" + dMean);;
						}
					}
				}
			}
//			MasterDoseCache.instance.getMeasurementVO().setFirstMeasurementVO(bean.getFirstMeasurementVO());
//			System.out.println("Printing first VO : " + MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO());
		}
	}

}
