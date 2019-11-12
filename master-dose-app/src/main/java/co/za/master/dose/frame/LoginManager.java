package co.za.master.dose.frame;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.za.master.dose.model.User;
import co.za.master.dose.utils.MasterDoseCache;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Manages control flow for logins */
public class LoginManager {
	private Scene scene;
	private Stage stage;

	public LoginManager(Scene scene) {
		this.scene = scene;
	}

	public LoginManager(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
	}

	/**
	 * Callback method invoked to notify that a user has been authenticated. Will
	 * show the main application screen.
	 */
	public void authenticated(String sessionID) {
		showMainView(sessionID);
	}

	/**
	 * Callback method invoked to notify that a user has logged out of the main
	 * application. Will show the login application screen.
	 */
	public void logout() {
		showLoginScreen();
	}

	public void showLoginScreen() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Login.fxml"));
		
			
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
			scene.setRoot(loader.load());
			stage.setTitle("Master dose");
			LoginController controller = loader.<LoginController>getController();
			controller.initManager(this);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void showMainView(String sessionID) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomePage.fxml"));
			Parent root = loader.load();

			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/ConfigPage.fxml"));
			Parent configPage = loader2.load();

			FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/UserManagementPage.fxml"));
			Parent userManagementPage = loader3.load();

			TabPane tabPane = new TabPane();

			Tab tab1 = new Tab("Home", root);
			Tab tab2 = new Tab("Configuration", configPage);
			Tab tab3 = new Tab("User Management", userManagementPage);
			Tab tab4 = new Tab("Help", new Label("The page has manual how to use the application...Coming soon..."));

			tabPane.getTabs().add(tab1);
			tabPane.getTabs().add(tab2);
			User loggedOnUser = MasterDoseCache.instance.getMeasurementVO().getLoggedOnUser();
			if (loggedOnUser != null && loggedOnUser.getRole() != null
					&& loggedOnUser.getRole().equalsIgnoreCase("Admin")) {
				tabPane.getTabs().add(tab3);
			}

			tabPane.getTabs().add(tab4);

			VBox vBox = new VBox(tabPane);
			scene = new Scene(vBox, 450, 700);
			stage.setTitle("Master dose");
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}