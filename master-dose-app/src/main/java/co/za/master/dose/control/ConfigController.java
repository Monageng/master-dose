/*
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package co.za.master.dose.control;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.za.master.dose.model.ConfigData;
import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.utils.ImageHelper;
import co.za.master.dose.utils.MasterDoseCache;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConfigController implements Initializable {
	@FXML
	private TextField sensitivityTxt = new TextField();

	@FXML
	private TextField transmissionCountsTxt = new TextField();
	
	@FXML
	private TextField scatterCorrectionValueTxt	 = new TextField();
	
	
	@FXML
	private TextField femaleSValueTxt	 = new TextField();
	
	
	@FXML
	private TextField maleSValueTxt	 = new TextField();
	
	
	@FXML
	private ComboBox<String> imageTypeCombo = new ComboBox<>();
	
	@FXML
	protected void handleSaveConfigAction(ActionEvent event) {
		
	  	int i = JOptionPane.showConfirmDialog(null, "Do you want to save configurations?", "", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION);
	    
	  	if (i == 0) {
	  		MeasurementVO measurementVO = MasterDoseCache.instance
					.getMeasurementVO();
			if (ImageHelper.instance.isConfigValid(measurementVO.getConfigData())) {
				ImageHelper.instance.saveConfigData(measurementVO.getConfigData());
			}
	  	}
	}
	
	private void loadConfigs() {
		ConfigData configData = ImageHelper.instance.getConfigData();
		if (configData.getSensitivity() != 0) {
			sensitivityTxt.setText(configData.getSensitivity() + "");
		}
		
		if (configData.getTransmissionCounts() != 0) {
			transmissionCountsTxt.setText(configData.getTransmissionCounts() + "");
		}
		
		if (configData.getImageType() != null) {
			imageTypeCombo.setValue(configData.getImageType());
		}
		
		if (configData.getScatterCorrection() != 0) {
			scatterCorrectionValueTxt.setText(configData.getScatterCorrection() + "");
		}
		if (configData.getFemaleSValue() != 0) {
			femaleSValueTxt.setText(configData.getFemaleSValue() + "");
		}
		if (configData.getMaleSValue() != 0) {
			maleSValueTxt.setText(configData.getMaleSValue() + "");
		}
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadConfigs();
		sensitivityTxt.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
					sensitivityTxt.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						MeasurementVO measurementVO = MasterDoseCache.instance
								.getMeasurementVO();
						measurementVO.getConfigData().setSensitivity(Double.valueOf(newValue));
						sensitivityTxt.setText(newValue);
					}
				}
			}
		});
		
		transmissionCountsTxt.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
					transmissionCountsTxt.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						MeasurementVO measurementVO = MasterDoseCache.instance
								.getMeasurementVO();
						measurementVO.getConfigData().setTransmissionCounts(Double.valueOf(newValue));
						transmissionCountsTxt.setText(newValue);
					}
				}
			}
		});
		
		scatterCorrectionValueTxt.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
					scatterCorrectionValueTxt.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						MeasurementVO measurementVO = MasterDoseCache.instance
								.getMeasurementVO();
						measurementVO.getConfigData().setScatterCorrection(Double.valueOf(newValue));
						scatterCorrectionValueTxt.setText(newValue);
					}
				}
			}
		});
		

		maleSValueTxt.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,4})?")) {
					maleSValueTxt.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						MeasurementVO measurementVO = MasterDoseCache.instance
								.getMeasurementVO();
						measurementVO.getConfigData().setMaleSValue(Double.valueOf(newValue));
						maleSValueTxt.setText(newValue);
					}
				}
			}
		});

		femaleSValueTxt.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,4})?")) {
					femaleSValueTxt.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						MeasurementVO measurementVO = MasterDoseCache.instance
								.getMeasurementVO();
						measurementVO.getConfigData().setFemaleSValue(Double.valueOf(newValue));
						femaleSValueTxt.setText(newValue);
					}
				}
			}
		});
		imageTypeCombo.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				MeasurementVO measurementVO = MasterDoseCache.instance
						.getMeasurementVO();
				measurementVO.getConfigData().setImageType(newValue);				
			}
		});
		
		imageTypeCombo.getItems().setAll("PLANAR", "SPECT");
		
	};
}
