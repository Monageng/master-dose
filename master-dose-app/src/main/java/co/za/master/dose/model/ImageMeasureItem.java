package co.za.master.dose.model;

import java.io.Serializable;

public class ImageMeasureItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1265126822317742142L;

	private int imageNumber;
	private String imageType;
	private int interval;
	private double leftMeanCount;
	private double rightMeanCount;
	private double tumourMeanCount;
	private double background;
	
	private double leftImage;
	private double rightImage;
	private double tumourImage;
	
	public double getLeftImage() {
		return leftImage;
	}
	public void setLeftImage(double leftImage) {
		this.leftImage = leftImage;
	}
	public double getRightImage() {
		return rightImage;
	}
	public void setRightImage(double rightImage) {
		this.rightImage = rightImage;
	}
	public double getTumourImage() {
		return tumourImage;
	}
	public void setTumourImage(double tumourImage) {
		this.tumourImage = tumourImage;
	}
	public double getBackground() {
		return background;
	}
	public int getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public double getLeftMeanCount() {
		return leftMeanCount;
	}
	public void setLeftMeanCount(double leftMeanCount) {
		this.leftMeanCount = leftMeanCount;
	}
	public double getRightMeanCount() {
		return rightMeanCount;
	}
	public void setRightMeanCount(double rightMeanCount) {
		this.rightMeanCount = rightMeanCount;
	}
	public double getTumourMeanCount() {
		return tumourMeanCount;
	}
	public void setTumourMeanCount(double tumourMeanCount) {
		this.tumourMeanCount = tumourMeanCount;
	}
	public void setBackground(double background) {
		this.background = background;
	}
	
	public String toString() {
		return "ImageMeasureItem : imageType = " + imageType + " ImageNo : " + imageNumber + " background : " + background + " leftMeanCount : " + leftMeanCount + " rightMeanCount : " + rightMeanCount + " tumourCount : " + tumourMeanCount; 
	}
}

