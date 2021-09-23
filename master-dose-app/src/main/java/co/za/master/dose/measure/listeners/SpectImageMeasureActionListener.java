package co.za.master.dose.measure.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import co.za.master.dose.model.ImageMeasureItem;
import co.za.master.dose.model.ImageSideEnum;
import co.za.master.dose.model.ImageTypeEnum;
import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.utils.ImageHelper;
import co.za.master.dose.utils.MasterDoseCache;
import ij.ImagePlus;
import ij.WindowManager;

public class SpectImageMeasureActionListener implements MeasureActionListenerInterface {

	private ImageSideEnum imageSideEnum;

	public SpectImageMeasureActionListener() {
	}

	public SpectImageMeasureActionListener(MeasurementVO bean, ImageSideEnum imageSideEnum,
			ImageTypeEnum imageTypeEnum) {
		this.imageSideEnum = imageSideEnum;
	}

	public void actionPerformed(ActionEvent e) {
		ImagePlus imagePlus = WindowManager.getCurrentImage();

		String key = MasterDoseCache.instance.getMeasurementVO().getImageKey() ;
		System.out.println("key : " + key);
		ImageMeasureItem imageMeasureItem = MasterDoseCache.instance.getMeasurementVO().getMap().get(key);
		if (imagePlus == null) {

		} else {

			if (imageSideEnum == ImageSideEnum.Background) {
				double mean = ImageHelper.instance.getMeanCount(imagePlus);
				System.out.println("Background Mean : " + mean );
				if (imageMeasureItem == null) {
					imageMeasureItem = new ImageMeasureItem();
				}
				imageMeasureItem.setBackground(mean);
			} else {
				if (imageMeasureItem == null || imageMeasureItem.getBackground() == 0.0) {
					JOptionPane.showConfirmDialog(null,
							"Background measurement not taken, please take background measurements first", "",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				} else {
					double dMean = ImageHelper.instance.getMean(imagePlus, imageMeasureItem.getBackground() + "");
					System.out.println(
							 " FirstImageMeasureActionListener Mean : " + dMean);
					if (imageSideEnum == ImageSideEnum.Left) {
						imageMeasureItem.setLeftMeanCount(dMean);
					} else if (imageSideEnum == ImageSideEnum.Right) {
						imageMeasureItem.setRightMeanCount(dMean);
					} else if (imageSideEnum == ImageSideEnum.Tumour) {
						imageMeasureItem.setTumourMeanCount(dMean);
					}
				}
			}

			imageMeasureItem.setImageNumber(MasterDoseCache.instance.getMeasurementVO().getImageNumber());
			imageMeasureItem.setInterval(MasterDoseCache.instance.getMeasurementVO().getInterval());
			imageMeasureItem.setImageType(MasterDoseCache.instance.getMeasurementVO().getConfigData().getImageType());
			MasterDoseCache.instance.getMeasurementVO().getMap().put(key, imageMeasureItem);
			System.out.println("Printing first VO : " + imageMeasureItem);
			MasterDoseCache.instance.getMeasurementVO().getTableView().getItems()
					.setAll(MasterDoseCache.instance.getMeasurementVO().getMap().values());
		}
	}

}
