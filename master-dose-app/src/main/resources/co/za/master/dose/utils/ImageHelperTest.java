package co.za.master.dose.utils;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import co.za.master.dose.model.ConfigData;
import co.za.master.dose.model.FirstMeasurementVO;
import co.za.master.dose.model.ImageMeasureItem;
import co.za.master.dose.model.ImageTypeEnum;
import co.za.master.dose.model.MeasurementVO;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;


public class ImageHelperTest {
	
	@Test
	public void testProcess() {
		// GIVEN
		
		// Interval 1
		 MeasurementVO measurementVO = MasterDoseCache.instance.getMeasurementVO();
		 FirstMeasurementVO firstMeasurementVO = new FirstMeasurementVO();
		 firstMeasurementVO.setAnteriaLeft(2590);
		 firstMeasurementVO.setPosteriaLeft(2600);
		 firstMeasurementVO.setAnteriaBackground(20);
		 firstMeasurementVO.setAnteriaBackground(20);
		 
		measurementVO.setFirstMeasurementVO(firstMeasurementVO );
		
		// Interval 2
		
		
		// Interval 3
		
		
		assertTrue(true);
		
		
		
	}
	
	@Test
	public void testCalculateMeanSquareRootNew() {
		MeasurementVO measurementVO = MasterDoseCache.instance.getMeasurementVO();
		Map<String, ImageMeasureItem> map= measurementVO.getMap();
		ImageMeasureItem item1Ant = new ImageMeasureItem();
		item1Ant.setImageNumber(1);
		item1Ant.setImageType(ImageTypeEnum.Anteria.name());
		item1Ant.setInterval(1);
		item1Ant.setLeftMeanCount(2.1);
		item1Ant.setRightMeanCount(1.52);
		item1Ant.setTumourMeanCount(2.3);
		map.put("1_1Anteria", item1Ant);
		
		ImageMeasureItem item1Post = new ImageMeasureItem();
		item1Post.setImageNumber(1);
		item1Post.setImageType(ImageTypeEnum.Posteria.name());
		item1Post.setInterval(1);
		item1Post.setLeftMeanCount(2.1);
		item1Post.setRightMeanCount(1.41);
		item1Post.setTumourMeanCount(0.52);
		map.put("1_1Posteria", item1Post);
		
		ImageMeasureItem item2Ant = new ImageMeasureItem();
		item2Ant.setImageNumber(2);
		item2Ant.setImageType(ImageTypeEnum.Anteria.name());
		item2Ant.setInterval(12);
		item2Ant.setLeftMeanCount(12.1);
		item2Ant.setRightMeanCount(1.52);
		item2Ant.setTumourMeanCount(2.3);
		map.put("2_2Anteria", item2Ant);
		
		ImageMeasureItem item2Post = new ImageMeasureItem();
		item2Post.setImageNumber(2);
		item2Post.setImageType(ImageTypeEnum.Posteria.name());
		item2Post.setInterval(12);
		item2Post.setLeftMeanCount(4.1);
		item2Post.setRightMeanCount(3.41);
		item2Post.setTumourMeanCount(2.52);
		map.put("2_2Posteria", item2Post);
		
		ImageMeasureItem item3Ant = new ImageMeasureItem();
		item3Ant.setImageNumber(3);
		item3Ant.setImageType(ImageTypeEnum.Anteria.name());
		item3Ant.setInterval(24);
		item3Ant.setLeftMeanCount(42.1);
		item3Ant.setRightMeanCount(4.52);
		item3Ant.setTumourMeanCount(3.3);
		map.put("3_3Anteria", item3Ant);
		
		ImageMeasureItem item3Post = new ImageMeasureItem();
		item3Post.setImageNumber(3);
		item3Post.setImageType(ImageTypeEnum.Posteria.name());
		item3Post.setInterval(24);
		item3Post.setLeftMeanCount(2.6);
		item3Post.setRightMeanCount(10.41);
		item3Post.setTumourMeanCount(1.52);
		map.put("3_3Posteria", item3Post);
		
		ConfigData configData = new ConfigData();
		configData.setSensitivity(1.2);
		configData.setTransmissionCounts(1.5);
		measurementVO.setConfigData(configData );
		Axis yAxix = null;
		Axis xAxix = null;
		LineChart<Number, Number> linechart = null;
		ImageHelper.instance.calculateMeanSquareRootNew(measurementVO);
		ImageHelper.instance.drawGraphNew(measurementVO, linechart);
		
	}
	

}
