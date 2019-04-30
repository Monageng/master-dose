package co.za.master.dose.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ConfigData {
	private double pixels;
	private double sensitivity;
	private double acrylicDepth;
	private double transmissionCounts;
	public double getPixels() {
		return pixels;
	}
	public void setPixels(double pixels) {
		this.pixels = pixels;
	}
	public double getSensitivity() {
		return sensitivity;
	}
	public void setSensitivity(double sensitivity) {
		this.sensitivity = sensitivity;
	}
	public double getAcrylicDepth() {
		return acrylicDepth;
	}
	public void setAcrylicDepth(double acrylicDepth) {
		this.acrylicDepth = acrylicDepth;
	}
	public double getTransmissionCounts() {
		return transmissionCounts;
	}
	public void setTransmissionCounts(double transmissionCounts) {
		this.transmissionCounts = transmissionCounts;
	}
	
}
