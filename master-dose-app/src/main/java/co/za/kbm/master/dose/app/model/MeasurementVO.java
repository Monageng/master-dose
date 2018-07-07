package co.za.kbm.master.dose.app.model;

import javafx.application.HostServices;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MeasurementVO {
	private TextField textField;
	private double dosage;
	
	private FirstMeasurementVO firstMeasurementVO = new FirstMeasurementVO();
	private SecondMeasurementVO secondMeasurementVO = new SecondMeasurementVO();
	private ThirdMeasurementVO thirdMeasurementVO = new ThirdMeasurementVO();
	private PatientDetails patientDetails = new PatientDetails();
	private HostServices hostServices;
	private LineChart<Number, Number> linechart;
	
}
