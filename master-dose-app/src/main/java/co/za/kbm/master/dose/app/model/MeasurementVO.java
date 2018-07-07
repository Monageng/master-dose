package co.za.kbm.master.dose.app.model;

import javafx.application.HostServices;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;

public class MeasurementVO {
	private TextField textField;
	private double dosage;
	
	
	private FirstMeasurementVO firstMeasurementVO = new FirstMeasurementVO();
	private SecondMeasurementVO secondMeasurementVO = new SecondMeasurementVO();
	private ThirdMeasurementVO thirdMeasurementVO = new ThirdMeasurementVO();
	private PatientDetails patientDetails = new PatientDetails();
	private HostServices hostServices;
	private LineChart<Number, Number> linechart;
	
	public HostServices getHostServices() {
		return hostServices;
	}
	public FirstMeasurementVO getFirstMeasurementVO() {
		return firstMeasurementVO;
	}
	public void setFirstMeasurementVO(FirstMeasurementVO firstMeasurementVO) {
		this.firstMeasurementVO = firstMeasurementVO;
	}
	public SecondMeasurementVO getSecondMeasurementVO() {
		return secondMeasurementVO;
	}
	public void setSecondMeasurementVO(SecondMeasurementVO secondMeasurementVO) {
		this.secondMeasurementVO = secondMeasurementVO;
	}
	public ThirdMeasurementVO getThirdMeasurementVO() {
		return thirdMeasurementVO;
	}
	public void setThirdMeasurementVO(ThirdMeasurementVO thirdMeasurementVO) {
		this.thirdMeasurementVO = thirdMeasurementVO;
	}
	public TextField getTextField() {
		return textField;
	}
	public void setTextField(TextField textField) {
		this.textField = textField;
	}
	public double getDosage() {
		return dosage;
	}
	public void setDosage(double dosage) {
		this.dosage = dosage;
	}
	
	public PatientDetails getPatientDetails() {
		return patientDetails;
	}
	public void setPatientDetails(PatientDetails patientDetails) {
		this.patientDetails = patientDetails;
	}
	public String toString() {
		return "firstMeasurementVO : " +  firstMeasurementVO + " secondMeasurementVO : " + secondMeasurementVO + " thirdMeasurementVO : " + thirdMeasurementVO + " patientDetails : "  + patientDetails;
	}
	public void setHostServices(HostServices hostServices) {
		this.hostServices = hostServices;
	}
	public LineChart<Number, Number> getLinechart() {
		return linechart;
	}
	public void setLinechart(LineChart<Number, Number> linechart) {
		this.linechart = linechart;
	}

	

}
