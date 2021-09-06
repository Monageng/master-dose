package co.za.master.dose.frame;


import java.util.Calendar;

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
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.DATE, 15);
    	cal.set(Calendar.MONTH, Calendar.DECEMBER);
    	cal.set(Calendar.YEAR, 2021);
    	
    	Calendar curDate = Calendar.getInstance();
    	if (curDate.getTime().before(cal.getTime())) {
    		String sessionID = ImageHelper.instance.authorize(password.getText().trim(), user.getText().trim());
            if (sessionID != null) {
              loginManager.authenticated(sessionID);
            } else {
            	JOptionPane.showConfirmDialog(null, "Access denied, please check if username and password is correct", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            }
    	} else {
    		JOptionPane.showConfirmDialog(null, "Your trial version has expired, please contact your administrator", "", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
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