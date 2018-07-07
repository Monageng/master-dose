package co.za.kbm.master.dose.app.utils;

import co.za.kbm.master.dose.app.model.MeasurementVO;


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
