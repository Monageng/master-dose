package co.za.master.dose.frame;


import java.util.List;

import javax.swing.JOptionPane;

import co.za.master.dose.common.model.LoginManager;
import co.za.master.dose.model.User;
import co.za.master.dose.utils.ImageHelper;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/** Controls the login screen */
public class LoginController {
  @FXML private TextField user;
  @FXML private TextField password;
  @FXML private Button loginButton;
  
  public void initialize() {}
  
  public void initManager(final LoginManager loginManager) {
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        String sessionID = authorize();
        if (sessionID != null) {
          loginManager.authenticated(sessionID);
        } else {
        	JOptionPane.showConfirmDialog(null, "Access denied, please check if username and password is correct", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }

  /**
   * Check authorization credentials.
   * 
   * If accepted, return a sessionID for the authorized session
   * otherwise, return null.
   */   
  private String authorize() {
	  List<User> list = ImageHelper.instance.getAllUsers();
	  for (User userDto : list) {
		  if (userDto.getUsername().trim().equalsIgnoreCase(user.getText().trim()) && (userDto.getPassword().trim().equalsIgnoreCase(password.getText().trim()))) {
			 return  generateSessionID();
		  }
	  }
	  
    return null;
  }
  
  private static int sessionID = 0;

  private String generateSessionID() {
    sessionID++;
    return "xyzzy - session " + sessionID;
  }
}