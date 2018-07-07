package co.za.kbm.master.dose.app.model;

import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SecondMeasurementVO {
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

}
