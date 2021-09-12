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

package co.za.master.dose.frame;

import java.net.URL;
import java.util.ResourceBundle;

import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.utils.MasterDoseCache;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MainAppController implements Initializable {

	@FXML
	private TextField initialsTxt;
	
	@FXML
	private TextField firstNameTxt;

	@FXML
	private TextField surnameTxt;

	@FXML
	private TextField patientTxt;		

	@FXML
	private TextField patientNameTxt = new TextField();	
	
	@FXML
	private TextField patientIdTxt = new TextField();	
	
	@FXML
	private ComboBox<String> titleComboBox;
	
	@FXML
	private ComboBox<String> genderCombo = new ComboBox<>();;

	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Initialize master dose application");
		
		genderCombo.getItems().setAll("Male", "Female");
		
		patientNameTxt.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				
				if (!newValue.isEmpty()) {
					MeasurementVO measurementVO = MasterDoseCache.instance
							.getMeasurementVO();
					measurementVO.getPatientDetails().setFirstName(newValue);;
					patientNameTxt.setText(newValue);
				}
				
			}
		});
		
		
		patientIdTxt.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				
				if (!newValue.isEmpty()) {
					MeasurementVO measurementVO = MasterDoseCache.instance
							.getMeasurementVO();
					measurementVO.getPatientDetails().setPatientId(newValue);;
					patientIdTxt.setText(newValue);
				}
				
			}
		});
		
		genderCombo.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				MeasurementVO measurementVO = MasterDoseCache.instance
						.getMeasurementVO();
				measurementVO.getPatientDetails().setGender(newValue);				
			}
		});
	}
}
