package co.za.master.dose.utils;

import co.za.master.dose.model.MeasurementVO;

public class MasterDoseCache {
	
	 public static MasterDoseCache instance = new MasterDoseCache();
	 private MeasurementVO measurementVO = null;
	 
	 public MeasurementVO getMeasurementVO() {
		 if (measurementVO == null) {
			 measurementVO = new MeasurementVO();
		 }
		 return measurementVO;
	 }

}
