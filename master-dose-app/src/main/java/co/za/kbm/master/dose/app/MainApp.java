package co.za.kbm.master.dose.app;

import co.za.kbm.master.dose.app.utils.MasterDoseCache;
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
//		Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/MainPage.fxml"));
        Parent root = (Parent) loader.load();

//		Scene scene = new Scene(root);
        Scene scene = new Scene(root, 600, 1000);
//                scene.getStylesheets().add("/fxml/styles/Login.css");

        primaryStage.setTitle("Master dose");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        MasterDoseCache.instance.getMeasurementVO().setHostServices(getHostServices());
        System.out.println("MasterDoseCache.instance.getMeasurementVO() " +MasterDoseCache.instance.getMeasurementVO().toString());

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
//        Parent root = (Parent) loader.load();
////        Parent root = FXMLLoader.load(getClass().getResource("/FXMLDocument.fxml"));
//
//        Scene scene = new Scene(root);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

}
