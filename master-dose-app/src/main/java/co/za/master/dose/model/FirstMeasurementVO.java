package co.za.master.dose.model;

import javafx.scene.control.TextField;
import lombok.Data;

@Data
public class FirstMeasurementVO {
	
	private TextField anteriaLeftField;
	private TextField anteriaRightField;
	private TextField anteriaTumourField;
	
	private TextField posteriaLeftField;
	private TextField posteriaRightField;
	private TextField posteriaTumourField;
	
	private double anteriaBackground;
	private double anteriaLeft;
	private double anteriaRight;
	private double anteriaTumour;
	
	private double posteriaBackground;
	private double posteriaLeft;
	private double posteriaRight;
	private double posteriaTumour;
	
	private double leftImage;
	private double rightImage;
	private double tumourImage;
	
	private double interval;
	
	
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

	public double getAnteriaBackground() {
		return anteriaBackground;
	}

	public void setAnteriaBackground(double anteriaBackground) {
		this.anteriaBackground = anteriaBackground;
	}

	public double getPosteriaBackground() {
		return posteriaBackground;
	}

	public void setPosteriaBackground(double posteriaBackground) {
		this.posteriaBackground = posteriaBackground;
	}

	public double getAnteriaLeft() {
		return anteriaLeft;
	}

	public void setAnteriaLeft(double anteriaLeft) {
		this.anteriaLeft = anteriaLeft;
	}

	public double getAnteriaRight() {
		return anteriaRight;
	}

	public void setAnteriaRight(double anteriaRight) {
		this.anteriaRight = anteriaRight;
	}

	public double getAnteriaTumour() {
		return anteriaTumour;
	}

	public void setAnteriaTumour(double anteriaTumour) {
		this.anteriaTumour = anteriaTumour;
	}

	public TextField getAnteriaLeftField() {
		return anteriaLeftField;
	}

	public void setAnteriaLeftField(TextField anteriaLeftField) {
		this.anteriaLeftField = anteriaLeftField;
	}

	public TextField getAnteriaRightField() {
		return anteriaRightField;
	}

	public void setAnteriaRightField(TextField anteriaRightField) {
		this.anteriaRightField = anteriaRightField;
	}

	public TextField getAnteriaTumourField() {
		return anteriaTumourField;
	}

	public void setAnteriaTumourField(TextField anteriaTumourField) {
		this.anteriaTumourField = anteriaTumourField;
	}

	public TextField getPosteriaLeftField() {
		return posteriaLeftField;
	}

	public void setPosteriaLeftField(TextField posteriaLeftField) {
		this.posteriaLeftField = posteriaLeftField;
	}

	public TextField getPosteriaRightField() {
		return posteriaRightField;
	}

	public void setPosteriaRightField(TextField posteriaRightField) {
		this.posteriaRightField = posteriaRightField;
	}

	public TextField getPosteriaTumourField() {
		return posteriaTumourField;
	}

	public void setPosteriaTumourField(TextField posteriaTumourField) {
		this.posteriaTumourField = posteriaTumourField;
	}

	public double getPosteriaLeft() {
		return posteriaLeft;
	}

	public void setPosteriaLeft(double posteriaLeft) {
		this.posteriaLeft = posteriaLeft;
	}

	public double getPosteriaRight() {
		return posteriaRight;
	}

	public void setPosteriaRight(double posteriaRight) {
		this.posteriaRight = posteriaRight;
	}

	public double getPosteriaTumour() {
		return posteriaTumour;
	}

	public void setPosteriaTumour(double posteriaTumour) {
		this.posteriaTumour = posteriaTumour;
	}

	public double getInterval() {
		return interval;
	}

	public void setInterval(double interval) {
		this.interval = interval;
	}

	public String toString() {
		return "leftImage : " + leftImage + " rightImage : " + rightImage + " tumourImage : " + tumourImage + " interval : " + interval + " posteriaTumour : " + posteriaTumour +
				" posteriaRight : " + posteriaRight + " posteriaLeft : " + posteriaLeft + " posteriaBackground : " + posteriaBackground + " anteriaTumour : " + anteriaTumour +
				" anteriaRight : " + anteriaRight + " anteriaLeft : " + anteriaLeft + " anteriaBackground : " + anteriaBackground ;
	}
}
