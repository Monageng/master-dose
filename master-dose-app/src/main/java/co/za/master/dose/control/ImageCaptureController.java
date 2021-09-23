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

import co.za.master.dose.model.ImageMeasureItem;
import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.utils.ImageHelper;
import co.za.master.dose.utils.MasterDoseCache;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ImageCaptureController implements Initializable {

	@FXML
	private TableView<ImageMeasureItem> tableView;

	@FXML
	private TableColumn<ImageMeasureItem, Integer> imageNumber;
	@FXML
	private TableColumn<ImageMeasureItem, String> imageType;
	@FXML
	private TableColumn<ImageMeasureItem, Integer> interval;
	@FXML
	private TableColumn<ImageMeasureItem, Double> leftMeanCount;
	@FXML
	private TableColumn<ImageMeasureItem, Double> rightMeanCount;
	@FXML
	private TableColumn<ImageMeasureItem, Double> tumourMeanCount;

	@FXML
	private TextField intervalTxt;
	@FXML
	private TextField imageNumberTxt;

	@FXML
	protected void onEnter(ActionEvent event) {
		System.out.println(event.getSource());
	}

	@FXML
	protected void handleLoadFirstImagePosteriaAction(ActionEvent event) {

	}

	@FXML
	protected void handleLoadFirstImageAnteriaAction(ActionEvent event) {

	}

	@FXML
	protected void handleClearImageAction(ActionEvent event) {
		MasterDoseCache.instance.getMeasurementVO().getMap().clear();
				
		MasterDoseCache.instance.getMeasurementVO().getTableView().getItems()
				.setAll(MasterDoseCache.instance.getMeasurementVO().getMap().values());

		imageNumberTxt.clear();
		intervalTxt.clear();
		
		MasterDoseCache.instance.getMeasurementVO().getLinechart().getData().clear();
		MasterDoseCache.instance.getMeasurementVO().getLinechart().setTitle("Dosage is 0");;
	
	}

	@FXML
	protected void handleLoadImageAction(ActionEvent event) {

		if (imageNumberTxt.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(null, "Please capture image number ", "", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
		} else if (intervalTxt.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(null, "Please capture interval ", "", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
		} else if (MasterDoseCache.instance.getMeasurementVO().getPatientDetails().getGender() == null) {
			JOptionPane.showConfirmDialog(null, "Please capture patient's gender ", "", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
		} else if (MasterDoseCache.instance.getMeasurementVO().getPatientDetails().getFirstName() == null) {
			JOptionPane.showConfirmDialog(null, "Please capture patient's first name ", "",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		} else if (MasterDoseCache.instance.getMeasurementVO().getPatientDetails().getSurname() == null) {
			JOptionPane.showConfirmDialog(null, "Please capture patient's surname ", "", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
		} else {
			MeasurementVO measurementVO = MasterDoseCache.instance.getMeasurementVO();
			measurementVO.setImageKey(imageNumberTxt.getText() + "_" + intervalTxt.getText());
			measurementVO.setImageNumber(Integer.parseInt(imageNumberTxt.getText()));
			measurementVO.setInterval(Integer.parseInt(intervalTxt.getText()));
			measurementVO.setTableView(tableView);
			ImageHelper.instance.showImageNew(measurementVO);
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		imageNumber.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Integer>("imageNumber"));
		imageType.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, String>("imageType"));
		interval.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Integer>("interval"));
		leftMeanCount.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Double>("leftMeanCount"));
		rightMeanCount.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Double>("rightMeanCount"));
		tumourMeanCount.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Double>("tumourMeanCount"));

		tableView.getColumns().get(0).prefWidthProperty().bind(tableView.widthProperty().multiply(0.15)); // 33% for id
																											// column
																											// size
		tableView.getColumns().get(1).prefWidthProperty().bind(tableView.widthProperty().multiply(0.20)); // 33% for dt
																											// column
																											// size
		tableView.getColumns().get(2).prefWidthProperty().bind(tableView.widthProperty().multiply(0.20)); // 33% for cv
																											// column
																											// size
		tableView.getColumns().get(3).prefWidthProperty().bind(tableView.widthProperty().multiply(0.15)); // 33% for cv
																											// column
																											// size
		tableView.getColumns().get(4).prefWidthProperty().bind(tableView.widthProperty().multiply(0.15)); // 33% for cv
																											// column
																											// size
		tableView.getColumns().get(5).prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));

		tableView.setFixedCellSize(25);
		tableView.prefHeightProperty()
				.bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(2.01)));
		tableView.minHeightProperty().bind(tableView.prefHeightProperty());
		tableView.maxHeightProperty().bind(tableView.prefHeightProperty());

		intervalTxt.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
					intervalTxt.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						intervalTxt.setText(newValue);
					}
				}
			}
		});

		imageNumberTxt.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?")) {
					imageNumberTxt.setText(oldValue);
				} else {
					if (!newValue.isEmpty()) {
						imageNumberTxt.setText(newValue);
					}
				}
			}
		});

	}
}
