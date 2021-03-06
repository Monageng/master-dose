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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import co.za.master.dose.model.ImageNumberEnum;
import co.za.master.dose.model.ImageTypeEnum;
import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.utils.ImageHelper;
import co.za.master.dose.utils.MasterDoseCache;

public class FirstImageController implements Initializable {
	@FXML
	private TextField first_anteria_right = new TextField();
	@FXML
	private TextField first_anteria_left = new TextField();
	@FXML
	private TextField first_anteria_tumour = new TextField();

	@FXML
	private TextField first_posteria_right = new TextField();
	@FXML
	private TextField first_posteria_left = new TextField();
	@FXML
	private TextField first_posteria_tumour = new TextField();

	@FXML
	private TextField firstInterval;

	@FXML
	protected void onEnter(ActionEvent event) {
		System.out.println(event.getSource());
	}


	@FXML
	protected void handleLoadFirstImagePosteriaAction(ActionEvent event) {
		System.out.println("Starting handleLoadFirstImagePosteriaAction");

		System.out.println("first_anteria_left " + first_anteria_left);
		MeasurementVO measurementVO = MasterDoseCache.instance
				.getMeasurementVO();
		measurementVO.getFirstMeasurementVO().setPosteriaLeftField(
				first_posteria_left);
		measurementVO.getFirstMeasurementVO().setPosteriaRightField(
				first_posteria_right);
		measurementVO.getFirstMeasurementVO().setPosteriaTumourField(
				first_posteria_tumour);
		ImageHelper.instance.showImage(measurementVO, ImageTypeEnum.Posteria,
				ImageNumberEnum.FirstImage);
	}

	@FXML
	protected void handleLoadFirstImageAnteriaAction(ActionEvent event) {
		System.out.println("Starting handleLoadFirstImageAnteriaAction");
		MeasurementVO measurementVO = MasterDoseCache.instance
				.getMeasurementVO();
		measurementVO.getFirstMeasurementVO().setAnteriaLeftField(
				first_anteria_left);
		measurementVO.getFirstMeasurementVO().setAnteriaRightField(
				first_anteria_right);
		measurementVO.getFirstMeasurementVO().setAnteriaTumourField(
				first_anteria_tumour);
		ImageHelper.instance.showImage(measurementVO, ImageTypeEnum.Anteria,
				ImageNumberEnum.FirstImage);
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Initialize first image");

		MeasurementVO measurementVO = MasterDoseCache.instance.getMeasurementVO();
		measurementVO.getFirstMeasurementVO().setInterval(6);
		
		firstInterval.setText("6");
		firstInterval.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> arg0,
					String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
					firstInterval.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						MeasurementVO measurementVO = MasterDoseCache.instance
								.getMeasurementVO();
						measurementVO.getFirstMeasurementVO().setInterval(
								Double.parseDouble(newValue));
						firstInterval.setText(newValue);
						System.out.println(newValue);
						System.out.println(firstInterval.getText());
					}
				}
			}
		});
	};
}
