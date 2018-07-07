package co.za.master.dose.samples;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionListener;

import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.ImageWindow;
import ij.io.Opener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SampleROI extends Application {
	static ImagePlus imagePlus = new ImagePlus();

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
				load();
			}
		});

		
		Button btn1 = new Button();
		btn1.setText("Zoom++");
		btn1.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
				zoom();
			}
		});
		
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		root.getChildren().add(btn1);

		Scene scene = new Scene(root, 300, 250);

		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private static void zoom() {
		
		
//		Opener opener = new Opener();
//		ImagePlus imagePlus = opener.openImage("/home/monageng/heart.jpg");
		ij.process.ImageProcessor ip = imagePlus.getProcessor();
//		ip = ip.resize(StyleSheetConstants.IMAGE_POPUP_WIDTH, StyleSheetConstants.IMAGE_POPUP_HEIGHT);
//		imagePlus.setProcessor(ip);
		imagePlus.show();
		System.out.println("SHOW****");
//		imagePlus.getCanvas().setEnabled(true);
		IJ.showMessage("Zooming");
		ImageWindow imageWindow  = WindowManager.getCurrentWindow();
		imagePlus=WindowManager.getCurrentImage();
		IJ.run(imagePlus, "Enlarge...", "enlarge="+50);
//		ImageWindow imageWindow  = WindowManager.getCurrentWindow();
		
//		imageWindow.setMenuBar(ImageUtils.instance.createImageMenuBar(measurementPanel, imageTypeEnum, imageNumberEnum) );
	
}
	
	private static void load() {
		
		
			Opener opener = new Opener();
			imagePlus = opener.openImage("/home/monageng/heart.jpg");
//			ij.process.ImageProcessor ip = imagePlus.getProcessor();
//			ip = ip.resize(StyleSheetConstants.IMAGE_POPUP_WIDTH, StyleSheetConstants.IMAGE_POPUP_HEIGHT);
//			imagePlus.setProcessor(ip);
			imagePlus.show();
//			System.out.println("SHOW****");
			imagePlus.getCanvas().setEnabled(true);
			IJ.showMessage("test");
//			IJ.run(imagePlus, "Enlarge...", "enlarge="+50);
//			System.out.println(" " + IJ.setTool("Oval"));
			ImageWindow imageWindow  = WindowManager.getCurrentWindow();
			IJ.showMessage("imageWindow" + imageWindow);
			MenuBar mb = new MenuBar();
			Menu ovalM = new Menu();
			ovalM.setLabel("Oval");
			MenuItem ovalMI = new MenuItem();
			ovalMI.setLabel("Test");
			ovalM.add(ovalMI );
			ovalMI.addActionListener(new ActionListener() {
				
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Open");
					System.out.println("IJ.getInstance() " +IJ.getInstance());
				}
			});
			mb.add(ovalM );
			mb.setName("Type");
			imageWindow.setMenuBar(mb );
//			imageWindow.setMenuBar(ImageUtils.instance.createImageMenuBar(measurementPanel, imageTypeEnum, imageNumberEnum) );
		
	}

}
