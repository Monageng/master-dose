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
import co.za.master.dose.utils.ImageHelper;
import co.za.master.dose.utils.MasterDoseCache;
import co.za.master.dose.utils.PDFHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

public class GraphController implements Initializable {
	@FXML
	private LineChart<Number, Number> linechart;

	@FXML
	protected void handleGenerateReportAction (ActionEvent event) {
		System.out.println("Starting to generate report : ");
		ImageHelper imageHelper = new ImageHelper();
//		MeasurementVO vo = ImageDataHelper.createVO();
		MeasurementVO vo = MasterDoseCache.instance.getMeasurementVO();
//		vo = ImageHelper.instance.calculateMeanSquareRoot(vo);
		
		System.out.println("VO " + vo);
		imageHelper.drawGraphNew(vo, linechart);
		PDFHelper.createPDFDynamic(vo, linechart);
	}
	
	@FXML
	protected void handleCalculateDosageAction(ActionEvent event) {
		ImageHelper imageHelper = new ImageHelper();
//		MeasurementVO vo = ImageDataHelper.createVO();
		 MeasurementVO vo = MasterDoseCache.instance.getMeasurementVO();
		vo = ImageHelper.instance.calculateMeanSquareRoot(vo);
		
		System.out.println("VO " + vo);
		imageHelper.drawGraphNew(vo, linechart);
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Initializing Graph Controller");
	}
}
