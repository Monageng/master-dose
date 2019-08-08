package co.za.master.dose.measure.listeners;

import ij.ImagePlus;
import ij.WindowManager;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import co.za.master.dose.model.ImageMeasureItem;
import co.za.master.dose.model.ImageSideEnum;
import co.za.master.dose.model.ImageTypeEnum;
import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.utils.ImageHelper;
import co.za.master.dose.utils.MasterDoseCache;

public class ImageMeasureActionListener implements MeasureActionListenerInterface {

	private ImageSideEnum imageSideEnum;
	private ImageTypeEnum imageTypeEnum;
	
	public ImageMeasureActionListener() {
	}
	
	public ImageMeasureActionListener(MeasurementVO bean, ImageSideEnum imageSideEnum, ImageTypeEnum imageTypeEnum) {
		this.imageSideEnum = imageSideEnum;
		this.imageTypeEnum = imageTypeEnum;
	}
	
	public void actionPerformed(ActionEvent e) {
		ImagePlus imagePlus = WindowManager.getCurrentImage();
		
		MeasurementVO bean = MasterDoseCache.instance.getMeasurementVO();
		if (imagePlus == null) {
//			System.out.println("Image is not loaded");
		} else {
			
//			 Check image side
			if (imageSideEnum == ImageSideEnum.Background) {
				double mean = ImageHelper.instance.getMeanCount(imagePlus);
				System.out.println("Background Mean : " + mean + " imageTypeEnum " + imageTypeEnum);
				String key = MasterDoseCache.instance.getMeasurementVO().getImageKey() + imageTypeEnum.name();
				System.out.println("key : " +key);
				if (imageTypeEnum == ImageTypeEnum.Anteria) {
					
					ImageMeasureItem imageMeasureItem = MasterDoseCache.instance.getMeasurementVO().getMap().get(key);
					if (imageMeasureItem == null) {
						imageMeasureItem = new ImageMeasureItem();
						imageMeasureItem.setImageNumber(MasterDoseCache.instance.getMeasurementVO().getImageNumber());
						imageMeasureItem.setBackground(mean);
					}
					
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
						System.out.println("imageTypeEnum : " + imageTypeEnum + " FirstImageMeasureActionListener Mean : " + dMean);
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
					if (MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO().getPosteriaBackground() < 1) {
						JOptionPane.showConfirmDialog(null, "Background measurement not taken, please take background measurements first", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					} else {
						double dMean = ImageHelper.instance.getMean(imagePlus,bean.getFirstMeasurementVO().getPosteriaBackground() + "");
						System.out.println("imageTypeEnum : " + imageTypeEnum + " FirstImageMeasureActionListener Mean : " + dMean);
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
			System.out.println("Printing first VO : " + MasterDoseCache.instance.getMeasurementVO().getFirstMeasurementVO());
		}
	}

}
