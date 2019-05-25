package co.za.master.dose.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import co.za.master.dose.model.FirstMeasurementVO;
import co.za.master.dose.model.MeasurementVO;


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
	
	

}
