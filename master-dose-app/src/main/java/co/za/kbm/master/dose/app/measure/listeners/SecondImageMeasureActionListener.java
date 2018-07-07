package co.za.kbm.master.dose.app.measure.listeners;

import co.za.kbm.master.dose.app.model.ImageSideEnum;
import co.za.kbm.master.dose.app.model.ImageTypeEnum;
import co.za.kbm.master.dose.app.model.MeasurementVO;
import co.za.kbm.master.dose.app.utils.ImageHelper;
import co.za.kbm.master.dose.app.utils.MasterDoseCache;
import ij.ImagePlus;
import ij.WindowManager;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;


public class SecondImageMeasureActionListener implements MeasureActionListenerInterface {
	
//	private MeasurementVO bean;
	private ImageSideEnum imageSideEnum;
	private ImageTypeEnum imageTypeEnum;
	
	public SecondImageMeasureActionListener() {
	}
	
	public SecondImageMeasureActionListener(MeasurementVO bean,ImageSideEnum imageSideEnum, ImageTypeEnum imageTypeEnum) {
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
				System.out.println("SecondImageMeasureActionListener Mean : " + mean);

//				 Check image side
				if (imageSideEnum == ImageSideEnum.Background) {
					if (imageTypeEnum == ImageTypeEnum.Anteria) {
						MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().setAnteriaBackground(mean);
					} else {
						MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().setPosteriaBackground(mean);
					}
				} else {
					
					if (imageTypeEnum == ImageTypeEnum.Anteria) {
						if (MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getAnteriaBackground() < 1) {
							JOptionPane.showConfirmDialog(null, "Background measurement not taken, please take background measurements first", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
						}else {
							double dMean = ImageHelper.instance.getMean(imagePlus,MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getAnteriaBackground() + "");
							if (imageSideEnum == ImageSideEnum.Left) {
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().setAnteriaLeft(dMean);
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getAnteriaLeftField().setText("" + dMean);
							} else if (imageSideEnum == ImageSideEnum.Right) {
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().setAnteriaRight(dMean);
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getAnteriaRightField().setText("" + dMean);
							} else if (imageSideEnum == ImageSideEnum.Tumour) {
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().setAnteriaTumour(dMean);
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getAnteriaTumourField().setText("" + dMean);
							}
						}
						
					} else {
						if (MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getPosteriaBackground() < 1) {
							JOptionPane.showConfirmDialog(null, "Background measurement not taken, please take background measurements first", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
						} else {
							double dMean = ImageHelper.instance.getMean(imagePlus,MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getPosteriaBackground() + "");
							if (imageSideEnum == ImageSideEnum.Left) {
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().setPosteriaLeft(dMean);
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getPosteriaLeftField().setText("" + dMean);
							} else if (imageSideEnum == ImageSideEnum.Right) {
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().setPosteriaRight(dMean);
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getPosteriaRightField().setText("" + dMean);
							} else if (imageSideEnum == ImageSideEnum.Tumour) {
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().setPosteriaTumour(dMean);
								MasterDoseCache.instance.getMeasurementVO().getSecondMeasurementVO().getPosteriaTumourField().setText("" + dMean);;
							}
						}
					}
				}
			}
		}
}
