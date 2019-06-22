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

public class ThirdImageController implements Initializable{
	@FXML
	private TextField third_anteria_right = new TextField();
	@FXML
	private TextField third_anteria_left = new TextField();
	@FXML
	private TextField third_anteria_tumour = new TextField();

	@FXML
	private TextField third_posteria_right = new TextField();
	@FXML
	private TextField third_posteria_left = new TextField();
	@FXML
	private TextField third_posteria_tumour = new TextField();
	
	@FXML
	private TextField thirdInterval ;
	
	@FXML
	protected void onEnter(ActionEvent event) {
		System.out.println(event.getSource());
	}
	
	@FXML
	protected void handleLoadImageAction(ActionEvent event) {
//		System.out.println("Starting handleLoadFirstImageAnteriaAction");
		MeasurementVO measurementVO = MasterDoseCache.instance
				.getMeasurementVO();
		measurementVO.getThirdMeasurementVO().setPosteriaLeftField(
				third_posteria_left);
		measurementVO.getThirdMeasurementVO().setPosteriaRightField(
				third_posteria_right);
		measurementVO.getThirdMeasurementVO().setPosteriaTumourField(
				third_posteria_tumour);
		
		measurementVO.getThirdMeasurementVO().setAnteriaLeftField(
				third_anteria_left);
		measurementVO.getThirdMeasurementVO().setAnteriaRightField(
				third_anteria_right);
		measurementVO.getThirdMeasurementVO().setAnteriaTumourField(
				third_anteria_tumour);
		
		ImageHelper.instance.showImage(measurementVO, ImageTypeEnum.Anteria,
				ImageNumberEnum.ThirdImage);
	}
	
	@FXML
	protected void handleLoadThirdImagePosteriaAction(ActionEvent event) {
//		System.out.println("Starting handleLoadThirdImagePosteriaAction");
		MeasurementVO measurementVO = MasterDoseCache.instance.getMeasurementVO();
		measurementVO.getThirdMeasurementVO().setPosteriaLeftField(
				third_posteria_left);
		measurementVO.getThirdMeasurementVO().setPosteriaRightField(
				third_posteria_right);
		measurementVO.getThirdMeasurementVO().setPosteriaTumourField(
				third_posteria_tumour);
		ImageHelper.instance.showImage(measurementVO, ImageTypeEnum.Posteria,
				ImageNumberEnum.ThirdImage);
	}

	@FXML
	protected void handleLoadThirdImageAnteriaAction(ActionEvent event) {
//		System.out.println("Starting handleLoadThirdImageAnteriaAction");
		MeasurementVO measurementVO = MasterDoseCache.instance.getMeasurementVO();
		measurementVO.getThirdMeasurementVO().setAnteriaLeftField(
				third_anteria_left);
		measurementVO.getThirdMeasurementVO().setAnteriaRightField(
				third_anteria_right);
		measurementVO.getThirdMeasurementVO().setAnteriaTumourField(
				third_anteria_tumour);
		ImageHelper.instance.showImage(measurementVO, ImageTypeEnum.Anteria,
				ImageNumberEnum.ThirdImage);
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
//		System.out.println("Initializing Third Image Controller***" + arg1);
		thirdInterval.setText("24");
		MeasurementVO measurementVO = MasterDoseCache.instance.getMeasurementVO();
	 	measurementVO.getThirdMeasurementVO().setInterval(24);
		thirdInterval.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0,
					String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
					thirdInterval.setText(oldValue);
                }  else {
                	if (!newValue.isEmpty()) {
                		MeasurementVO measurementVO = MasterDoseCache.instance.getMeasurementVO();
                	 	measurementVO.getThirdMeasurementVO().setInterval(Double.parseDouble(newValue));
                    }
                }
			}
		});
		
	}
}
