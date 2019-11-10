package co.za.master.dose.frame;


import javax.swing.JOptionPane;

import co.za.master.dose.utils.ImageHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** Controls the login screen */
public class LoginController {
  @FXML private TextField user;
  @FXML private TextField password;
  @FXML private Button loginButton;
  
  public void initialize() {}
  
  public void initManager(final LoginManager loginManager) {
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        String sessionID = ImageHelper.instance.authorize(password.getText().trim(), user.getText().trim());
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

  

}