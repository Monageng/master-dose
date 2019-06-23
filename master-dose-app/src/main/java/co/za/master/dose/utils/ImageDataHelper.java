package co.za.master.dose.utils;

import co.za.master.dose.model.MeasurementVO;

public class ImageDataHelper {
	
	public static MeasurementVO createVO() {
		MeasurementVO vo = new MeasurementVO();
		
		vo.getFirstMeasurementVO().setRightImage(25.5);
		vo.getFirstMeasurementVO().setLeftImage(18.2);
		vo.getFirstMeasurementVO().setTumourImage(20.5);
		vo.getFirstMeasurementVO().setInterval(5);
		
		vo.getFirstMeasurementVO().setAnteriaBackground(2.2);
		vo.getFirstMeasurementVO().setAnteriaLeft(52.3);
		vo.getFirstMeasurementVO().setAnteriaRight(10.5);
		vo.getFirstMeasurementVO().setAnteriaTumour(12.05);
		
		vo.getFirstMeasurementVO().setPosteriaBackground(4.2);
		vo.getFirstMeasurementVO().setPosteriaLeft(15.3);
		vo.getFirstMeasurementVO().setPosteriaRight(21.5);
		vo.getFirstMeasurementVO().setPosteriaTumour(19.05);
		
		vo.getSecondMeasurementVO().setRightImage(10.5);
		vo.getSecondMeasurementVO().setLeftImage(20.5);
		vo.getSecondMeasurementVO().setTumourImage(5.20);
		vo.getSecondMeasurementVO().setInterval(8.2);
		
		vo.getSecondMeasurementVO().setAnteriaBackground(4.2);
		vo.getSecondMeasurementVO().setAnteriaLeft(68.3);
		vo.getSecondMeasurementVO().setAnteriaRight(50.5);
		vo.getSecondMeasurementVO().setAnteriaTumour(25.05);
		
		vo.getSecondMeasurementVO().setPosteriaBackground(25.2);
		vo.getSecondMeasurementVO().setPosteriaLeft(65.3);
		vo.getSecondMeasurementVO().setPosteriaRight(10.5);
		vo.getSecondMeasurementVO().setPosteriaTumour(37.05);
		
		
		
		vo.getThirdMeasurementVO().setRightImage(15.1);
		vo.getThirdMeasurementVO().setLeftImage(5.5);
		vo.getThirdMeasurementVO().setTumourImage(22.5);
		vo.getThirdMeasurementVO().setInterval(10.5);
		
		vo.getThirdMeasurementVO().setAnteriaBackground(4.2);
		vo.getThirdMeasurementVO().setAnteriaLeft(25.3);
		vo.getThirdMeasurementVO().setAnteriaRight(15.5);
		vo.getThirdMeasurementVO().setAnteriaTumour(52.05);
		
		vo.getThirdMeasurementVO().setPosteriaBackground(20.2);
		vo.getThirdMeasurementVO().setPosteriaLeft(26.3);
		vo.getThirdMeasurementVO().setPosteriaRight(39.5);
		vo.getThirdMeasurementVO().setPosteriaTumour(14.05);
		
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
