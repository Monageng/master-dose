package co.za.master.dose.utils;

import co.za.master.dose.model.MeasurementVO;

public class ImageDataHelper {
	
	public static MeasurementVO createVO() {
		MeasurementVO vo = new MeasurementVO();
		
		vo.getFirstMeasurementVO().setRightImage(25.5);
		vo.getFirstMeasurementVO().setLeftImage(18.2);
		vo.getFirstMeasurementVO().setTumourImage(20.5);
		vo.getFirstMeasurementVO().setInterval(5);
		
		vo.getSecondMeasurementVO().setRightImage(8.5);
		vo.getSecondMeasurementVO().setLeftImage(10.5);
		vo.getSecondMeasurementVO().setTumourImage(5.5);
		vo.getSecondMeasurementVO().setInterval(5.2);
		
		vo.getThirdMeasurementVO().setRightImage(15.1);
		vo.getThirdMeasurementVO().setLeftImage(5.5);
		vo.getThirdMeasurementVO().setTumourImage(22.5);
		vo.getThirdMeasurementVO().setInterval(10.5);
		
		vo.getPatientDetails().setFirstName("Test Name");
		vo.getPatientDetails().setInitials("LL");
		vo.getPatientDetails().setPatientId("1414");
		vo.getPatientDetails().setSurname("Foo");
		vo.getPatientDetails().setTitle("Mr");
		
		MasterDoseCache.instance.getMeasurementVO().setFirstMeasurementVO(vo.getFirstMeasurementVO());
		MasterDoseCache.instance.getMeasurementVO().setSecondMeasurementVO(vo.getSecondMeasurementVO());
		MasterDoseCache.instance.getMeasurementVO().setThirdMeasurementVO(vo.getThirdMeasurementVO());
		
		return vo;
	}

}
