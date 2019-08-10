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
		
		String key = MasterDoseCache.instance.getMeasurementVO().getImageKey() + imageTypeEnum.name();
		System.out.println("key : " +key);
		ImageMeasureItem imageMeasureItem = MasterDoseCache.instance.getMeasurementVO().getMap().get(key);
		if (imagePlus == null) {
//			System.out.println("Image is not loaded");
		} else {
			
//			 Check image side
			if (imageSideEnum == ImageSideEnum.Background) {
				double mean = ImageHelper.instance.getMeanCount(imagePlus);
				System.out.println("Background Mean : " + mean + " imageTypeEnum " + imageTypeEnum);
				if (imageMeasureItem == null) {
					imageMeasureItem = new ImageMeasureItem();
				}
				imageMeasureItem.setBackground(mean);
			} else {
				if (imageTypeEnum == ImageTypeEnum.Anteria) {
					if (imageMeasureItem == null || imageMeasureItem.getBackground() == 0.0 ) {
						JOptionPane.showConfirmDialog(null, "Background measurement not taken, please take background measurements first", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					}else {
						double dMean = ImageHelper.instance.getMean(imagePlus,imageMeasureItem.getBackground() + "");
						System.out.println("imageTypeEnum : " + imageTypeEnum + " FirstImageMeasureActionListener Mean : " + dMean);
						if (imageSideEnum == ImageSideEnum.Left) {
							imageMeasureItem.setLeftMeanCount(dMean);
						} else if (imageSideEnum == ImageSideEnum.Right) {
							imageMeasureItem.setRightMeanCount(dMean);
						} else if (imageSideEnum == ImageSideEnum.Tumour) {
							imageMeasureItem.setTumourMeanCount(dMean);
						}
					}
				} else {
					if (imageMeasureItem == null || imageMeasureItem.getBackground() == 0.0 ) {
						JOptionPane.showConfirmDialog(null, "Background measurement not taken, please take background measurements first", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					} else {
						double dMean = ImageHelper.instance.getMean(imagePlus,imageMeasureItem.getBackground() + "");
						System.out.println("imageTypeEnum : " + imageTypeEnum + " FirstImageMeasureActionListener Mean : " + dMean);
						if (imageSideEnum == ImageSideEnum.Left) {
							imageMeasureItem.setLeftMeanCount(dMean);
						} else if (imageSideEnum == ImageSideEnum.Right) {
							imageMeasureItem.setRightMeanCount(dMean);
						} else if (imageSideEnum == ImageSideEnum.Tumour) {
							imageMeasureItem.setTumourMeanCount(dMean);
						}
					}
				}
			}

			imageMeasureItem.setImageNumber(MasterDoseCache.instance.getMeasurementVO().getImageNumber());
			imageMeasureItem.setImageType(imageTypeEnum.name());
			imageMeasureItem.setInterval(MasterDoseCache.instance.getMeasurementVO().getInterval());
			MasterDoseCache.instance.getMeasurementVO().getMap().put(key, imageMeasureItem);
//			MasterDoseCache.instance.getMeasurementVO().setFirstMeasurementVO(bean.getFirstMeasurementVO());
			System.out.println("Printing first VO : " + imageMeasureItem);
//			tableView.getItems().setAll(this.users)
			MasterDoseCache.instance.getMeasurementVO().getTableView().getItems().setAll(MasterDoseCache.instance.getMeasurementVO().getMap().values());
		}
	}

}
