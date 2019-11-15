package co.za.master.dose.model;

import lombok.Data;

@Data
public class ConfigData {
	
	private double sensitivity;
	private double transmissionCounts;
	private double spectValue;
	
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
	public double getSpectValue() {
		return spectValue;
	}
	public void setSpectValue(double spectValue) {
		this.spectValue = spectValue;
	}
	
}
