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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.za.master.dose.constants.MasterDoseConstants;
import co.za.master.dose.model.User;
import co.za.master.dose.utils.ImageHelper;
import co.za.master.dose.utils.MasterDoseCache;
import co.za.master.dose.utils.PasswordUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserManagementController implements Initializable {

	@FXML
	private TableView<User> userTableView;

	@FXML
	private TableColumn<User, String> name;
	@FXML
	private TableColumn<User, String> username;
	@FXML
	private TableColumn<User, String> password;
	@FXML
	private TableColumn<User, String> role;
	@FXML
	private TableColumn<User, String> status;

	@FXML
	private ComboBox<String> statusCombo;
	@FXML
	private ComboBox<String> roleCombo;

	private Map<String, User> userMap = new HashMap<String, User>();

	@FXML
	private TextField nameTxt;
	@FXML
	private TextField usernameTxt;
	@FXML
	private PasswordField pwdTxt;

	@FXML
	protected void onEnter(ActionEvent event) {
		System.out.println(event.getSource());
	}

	@FXML
	protected void handleAddUserAction(ActionEvent event) {

		int i = JOptionPane.showConfirmDialog(null, "Do you want to save user?", "", JOptionPane.YES_NO_OPTION,
				JOptionPane.YES_NO_OPTION);

		if (i == 0) {
			User user = new User(nameTxt.getText(), usernameTxt.getText().toLowerCase(), pwdTxt.getText(),
					roleCombo.getValue(), statusCombo.getValue());
			String salt = MasterDoseCache.instance.getMeasurementVO().getPasswordSalt();
			if (salt == null) {
				MasterDoseCache.instance.getMeasurementVO().setPasswordSalt(PasswordUtils.getSalt(30));
			}
			String securePassword = PasswordUtils.generateSecurePassword(pwdTxt.getText(),
					MasterDoseCache.instance.getMeasurementVO().getPasswordSalt());
			user.setPassword(securePassword);

			userMap.put(user.getUsername(), user);
			ImageHelper.instance.saveUser(userMap);
			userTableView.getItems().setAll(userMap.values());
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		userMap = ImageHelper.instance.getAllUsers();

		// Set the columns width auto size
		userTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		userTableView.getItems().setAll(userMap.values());
		userTableView.setEditable(true);

		name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
		status.setCellValueFactory(new PropertyValueFactory<User, String>("status"));

		statusCombo.getItems().setAll(MasterDoseConstants.ACCOUNT_STATUS_ACTIVE,
				MasterDoseConstants.ACCOUNT_STATUS_INACTVE, MasterDoseConstants.ACCOUNT_STATUS_LOCKED);
		roleCombo.getItems().setAll(MasterDoseConstants.ROLE_ADMIN, MasterDoseConstants.ROLE_USERS,
				MasterDoseConstants.ROLE_SUPPORT);
	}
}
