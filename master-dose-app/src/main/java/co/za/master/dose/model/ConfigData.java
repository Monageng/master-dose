package co.za.master.dose.model;

public class ConfigData {
	
	private double sensitivity;
	private double transmissionCounts;
	private double scatterCorrection;
	private double maleSValue;
	private double femaleSValue;
	
	private String imageType;
	
	public double getSensitivity() {
		return sensitivity;
	}
	public void setSensitivity(double sensitivity) {
		this.sensitivity = sensitivity;
	}
	
	public double getTransmissionCounts() {
		if (transmissionCounts == 0) {
			transmissionCounts = 5.37;
		}
		return transmissionCounts;
	}
	public void setTransmissionCounts(double transmissionCounts) {
		this.transmissionCounts = transmissionCounts;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public double getScatterCorrection() {
		return scatterCorrection;
	}
	public void setScatterCorrection(double scatterCorrection) {
		this.scatterCorrection = scatterCorrection;
	}
	public double getMaleSValue() {
		return maleSValue;
	}
	public void setMaleSValue(double maleSValue) {
		this.maleSValue = maleSValue;
	}
	public double getFemaleSValue() {
		return femaleSValue;
	}
	public void setFemaleSValue(double femaleSValue) {
		this.femaleSValue = femaleSValue;
	}

}
