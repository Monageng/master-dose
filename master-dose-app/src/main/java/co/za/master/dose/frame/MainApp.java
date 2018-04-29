package co.za.master.dose.frame;

import co.za.master.dose.utils.MasterDoseCache;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

		Scene scene = new Scene(root, 600, 1000);

		primaryStage.setTitle("Master dose");
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
		
		MasterDoseCache.instance.getMeasurementVO().setHostServices( getHostServices());
		
	}

}
