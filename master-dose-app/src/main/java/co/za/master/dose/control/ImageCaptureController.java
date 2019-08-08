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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.za.master.dose.model.ImageMeasureItem;
import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.utils.ImageHelper;
import co.za.master.dose.utils.MasterDoseCache;
import javafx.beans.binding.Bindings;
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
	
	private List<ImageMeasureItem> users = new ArrayList<>();
	  
	@FXML private TableColumn<ImageMeasureItem, Integer> imageNumber;
	@FXML private TableColumn<ImageMeasureItem, String> imageType;
	@FXML private TableColumn<ImageMeasureItem, Integer> interval;
	@FXML private TableColumn<ImageMeasureItem, Double> leftMeanCount;
	@FXML private TableColumn<ImageMeasureItem, Double> rightMeanCount;
	@FXML private TableColumn<ImageMeasureItem, Double> tumourMeanCount;
	
	@FXML private  TextField intervalTxt;
	@FXML private  TextField imageNumberTxt;

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
	protected void handleLoadImageAction(ActionEvent event) {
//		ImageMeasureItem itemMeasure = new ImageMeasureItem();
//		itemMeasure.setImageNumber(users.size()+1);
//		itemMeasure.setImageType("Ant");
//		users.add(itemMeasure );
//		tableView.getItems().setAll(this.users);
//		
//		
//		System.out.println("Initialize first image " + tableView.getPrefHeight());
		
		MeasurementVO measurementVO = MasterDoseCache.instance.getMeasurementVO();
		measurementVO.setImageKey(imageNumberTxt.getText()+intervalTxt.getText());
		measurementVO.setImageNumber(users.size() + 1);
		measurementVO.setInterval(Integer.parseInt(intervalTxt.getText()));
		measurementVO.setTableView(tableView);
//		measurementVO.getFirstMeasurementVO().setPosteriaLeftField(
//				first_posteria_left);
//		measurementVO.getFirstMeasurementVO().setPosteriaRightField(
//				first_posteria_right);
//		measurementVO.getFirstMeasurementVO().setPosteriaTumourField(
//				first_posteria_tumour);
		ImageHelper.instance.showImageNew(measurementVO);
		
		
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
//		System.out.println("Initialize first image");
		
		 // Set the columns width auto size
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//		tableView.getColumns().get(0).prefWidthProperty().bind(tableView.widthProperty().multiply(0.33));    // 33% for id column size
//		tableView.getColumns().get(1).prefWidthProperty().bind(tableView.widthProperty().multiply(0.33));   // 33% for dt column size
//		tableView.getColumns().get(2).prefWidthProperty().bind(tableView.widthProperty().multiply(0.33));    // 33% for cv column size
//		tableView.getItems().setAll(this.users);
//		
		
		imageNumber.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Integer>("imageNumber"));
		imageType.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, String>("imageType"));
		interval.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Integer>("interval"));
		leftMeanCount.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Double>("leftMeanCount"));
		rightMeanCount.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Double>("rightMeanCount"));
		tumourMeanCount.setCellValueFactory(new PropertyValueFactory<ImageMeasureItem, Double>("tumourMeanCount"));
		
		tableView.getColumns().get(0).prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));    // 33% for id column size
		tableView.getColumns().get(1).prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));   // 33% for dt column size
		tableView.getColumns().get(2).prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));    // 33% for cv column size
		tableView.getColumns().get(3).prefWidthProperty().bind(tableView.widthProperty().multiply(0.10));    // 33% for cv column size
		tableView.getColumns().get(4).prefWidthProperty().bind(tableView.widthProperty().multiply(0.10));    // 33% for cv column size
		tableView.getColumns().get(5).prefWidthProperty().bind(tableView.widthProperty().multiply(0.10)); 
		
		tableView.setFixedCellSize(25);
	    tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(2.01)));
	    tableView.minHeightProperty().bind(tableView.prefHeightProperty());
	    tableView.maxHeightProperty().bind(tableView.prefHeightProperty());


		//tableView.getItems().setAll(this.users);
        
	}
}
