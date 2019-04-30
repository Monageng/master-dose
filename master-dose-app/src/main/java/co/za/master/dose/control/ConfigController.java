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

import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.utils.MasterDoseCache;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ConfigController implements Initializable {
	@FXML
	private TextField pixelsTxt	 = new TextField();
	@FXML
	private TextField sensitivityTxt = new TextField();
	@FXML
	private TextField acrylicDepthTxt = new TextField();

	@FXML
	private TextField transmissionCountsTxt = new TextField();
	
	@FXML
	protected void onEnter(ActionEvent event) {
		System.out.println(event.getSource());
	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		pixelsTxt.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
					pixelsTxt.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						MeasurementVO measurementVO = MasterDoseCache.instance
								.getMeasurementVO();
						measurementVO.getConfigData().setPixels(Double.valueOf(newValue));
						pixelsTxt.setText(newValue);
					}
				}
			}
		});
		
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
		
		acrylicDepthTxt.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
					acrylicDepthTxt.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						MeasurementVO measurementVO = MasterDoseCache.instance
								.getMeasurementVO();
						measurementVO.getConfigData().setAcrylicDepth(Double.valueOf(newValue));
						acrylicDepthTxt.setText(newValue);
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
//		firstInterval.textProperty().addListener(new ChangeListener<String>() {
//			public void changed(ObservableValue<? extends String> arg0,
//					String oldValue, String newValue) {
//				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
//					firstInterval.setText(oldValue);
//				} else {
//					if (!newValue.isEmpty()) {
//						MeasurementVO measurementVO = MasterDoseCache.instance
//								.getMeasurementVO();
//						measurementVO.getFirstMeasurementVO().setInterval(
//								Double.parseDouble(newValue));
//						firstInterval.setText(newValue);
////						System.out.println(newValue);
////						System.out.println(firstInterval.getText());
//					}
//				}
//			}
//		});
	};
}
