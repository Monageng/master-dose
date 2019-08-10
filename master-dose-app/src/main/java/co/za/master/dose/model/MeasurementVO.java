package co.za.master.dose.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.HostServices;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.Data;

@Data
public class MeasurementVO {
	private TextField textField;
	private double dosage;
	
	private Map<String, ImageMeasureItem> map = new TreeMap<>();
	private Map<String, Double> squareRoot = new TreeMap<>();
	private Map<String, Integer> intervalMap = new HashMap<>();
	
	private String imageKey ;
	private int imageNumber;
	private int interval;
	
	private TableView<ImageMeasureItem> tableView;
	
	
	private FirstMeasurementVO firstMeasurementVO = new FirstMeasurementVO();
	private SecondMeasurementVO secondMeasurementVO = new SecondMeasurementVO();
	private ThirdMeasurementVO thirdMeasurementVO = new ThirdMeasurementVO();
	private PatientDetails patientDetails = new PatientDetails();
	private ConfigData configData = new ConfigData();
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
	public ConfigData getConfigData() {
		return configData;
	}
	public void setConfigData(ConfigData configData) {
		this.configData = configData;
	}
	public Map<String, ImageMeasureItem> getMap() {
		return map;
	}
	public void setMap(Map<String, ImageMeasureItem> map) {
		this.map = map;
	}
	public String getImageKey() {
		return imageKey;
	}
	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}
	public int getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}
	public TableView<ImageMeasureItem> getTableView() {
		return tableView;
	}
	public void setTableView(TableView<ImageMeasureItem> tableView) {
		this.tableView = tableView;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}

	public Map<String, Double> getSquareRoot() {
		return squareRoot;
	}
	public void setSquareRoot(Map<String, Double> squareRoot) {
		this.squareRoot = squareRoot;
	}
	public Map<String, Integer> getIntervalMap() {
		return intervalMap;
	}
	public void setIntervalMap(Map<String, Integer> intervalMap) {
		this.intervalMap = intervalMap;
	}

}
