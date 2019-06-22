package co.za.master.dose.model;

import lombok.Data;

@Data
public class ConfigData {
	private double sensitivity;
	private double transmissionCounts;
	
	public double getSensitivity() {
		return sensitivity;
	}
	public void setSensitivity(double sensitivity) {
		this.sensitivity = sensitivity;
	}
	
	public double getTransmissionCounts() {
		return transmissionCounts;
	}
	public void setTransmissionCounts(double transmissionCounts) {
		this.transmissionCounts = transmissionCounts;
	}
	
}
