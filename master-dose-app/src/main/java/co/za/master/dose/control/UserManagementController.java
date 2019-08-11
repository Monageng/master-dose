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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.za.master.dose.model.User;
import co.za.master.dose.utils.ImageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserManagementController implements Initializable {

	@FXML
	private TableView<User> userTableView;
	
	@FXML private TableColumn<User, String> name;
	@FXML private TableColumn<User, String> username;
	@FXML private TableColumn<User, String> password;
	@FXML private TableColumn<User, String> role;
	@FXML private TableColumn<User, String> status;
	List<User> UserList = new ArrayList<User>();
	
	@FXML private TextField nameTxt;
	@FXML private TextField usernameTxt;
	@FXML private PasswordField pwdTxt;
	@FXML private TextField roleTxt;
	@FXML private TextField statusTxt;

	@FXML
	protected void onEnter(ActionEvent event) {
		System.out.println(event.getSource());
	}
	
	@FXML
	protected void handleAddUserAction(ActionEvent event) {
		User user = new User(nameTxt.getText(), usernameTxt.getText(), pwdTxt.getText(), roleTxt.getText(), statusTxt.getText());
		UserList.add(user);
		userTableView.getItems().setAll(UserList);
		
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		UserList = ImageHelper.instance.getAllUsers();
		
		 // Set the columns width auto size
		userTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//		tableView.getColumns().get(0).prefWidthProperty().bind(tableView.widthProperty().multiply(0.33));    // 33% for id column size
//		tableView.getColumns().get(1).prefWidthProperty().bind(tableView.widthProperty().multiply(0.33));   // 33% for dt column size
//		tableView.getColumns().get(2).prefWidthProperty().bind(tableView.widthProperty().multiply(0.33));    // 33% for cv column size
		userTableView.getItems().setAll(UserList);
		userTableView.setEditable(true);
		
		name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
		status.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
//		
//		userTableView.getColumns().get(0).prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));    // 33% for id column size
//		userTableView.getColumns().get(1).prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));   // 33% for dt column size
//		userTableView.getColumns().get(2).prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));    // 33% for cv column size
//		userTableView.getColumns().get(3).prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));    // 33% for cv column size
//		tableView.getColumns().get(4).prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));    // 33% for cv column size
//		tableView.getColumns().get(5).prefWidthProperty().bind(tableView.widthProperty().multiply(0.15)); 
//		
//		tableView.setFixedCellSize(25);
//	    tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(2.01)));
//	    tableView.minHeightProperty().bind(tableView.prefHeightProperty());
//	    tableView.maxHeightProperty().bind(tableView.prefHeightProperty());


		//tableView.getItems().setAll(this.users);
        
	}
}
