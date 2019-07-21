package co.za.master.dose.utils;

import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.ImageWindow;
import ij.gui.Toolbar;
import ij.io.Opener;
import ij.measure.ResultsTable;
import ij.plugin.filter.Analyzer;
import ij.process.ImageProcessor;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Date;

import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import co.za.master.dose.constants.StyleSheetConstants;
import co.za.master.dose.image.listeners.OvalSelectionTypeActionListener;
import co.za.master.dose.image.listeners.ZoomInActionListener;
import co.za.master.dose.image.listeners.ZoomOutActionListener;
import co.za.master.dose.measure.listeners.FirstImageMeasureActionListener;
import co.za.master.dose.measure.listeners.MeasureActionListenerInterface;
import co.za.master.dose.measure.listeners.SecondImageMeasureActionListener;
import co.za.master.dose.measure.listeners.ThirdImageMeasureActionListener;
import co.za.master.dose.model.ConfigData;
import co.za.master.dose.model.FirstMeasurementVO;
import co.za.master.dose.model.ImageMeasureTime;
import co.za.master.dose.model.ImageNumberEnum;
import co.za.master.dose.model.ImageSideEnum;
import co.za.master.dose.model.ImageTypeEnum;
import co.za.master.dose.model.MeasurementBean;
import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.model.ROITypeEnum;
import co.za.master.dose.model.SecondMeasurementVO;
import co.za.master.dose.model.ThirdMeasurementVO;

public class ImageHelper {

	public static ImageHelper instance = new ImageHelper();
	
	public static void main(String[] arg) {
		MeasurementVO bean = ImageDataHelper.createVO();
		instance.generatedXsl(bean);
	}

	public void showImage(MeasurementVO measurementVO,
			ImageTypeEnum imageTypeEnum, ImageNumberEnum imageNumberEnum) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open File");
		File file = chooser.showOpenDialog(new Stage());
		if (WindowManager.getCurrentImage() != null) {
			WindowManager.getCurrentImage().close();
		}

		if (file != null) {
			String imagepath = file.getPath();
			// Image image = new Image(imagepath);
			Opener opener = new Opener();
			ImagePlus imagePlus = opener.openImage(imagepath);
			ImageProcessor ip = imagePlus.getProcessor();
			// imageView.setImage(image);
			ip = ip.resize(StyleSheetConstants.IMAGE_POPUP_WIDTH,
					StyleSheetConstants.IMAGE_POPUP_HEIGHT);
			imagePlus.setProcessor(ip);
			imagePlus.show();
			// imagePlus.getCanvas().setEnabled(true);
			ImageWindow imageWindow = WindowManager.getCurrentWindow();

			imageWindow.setMenuBar(ImageHelper.instance.createImageMenuBar(
					measurementVO, imageTypeEnum, imageNumberEnum));
			// co.za.master.dose.model.Toolbar toolbar = new
			// co.za.master.dose.model.Toolbar();
			Toolbar toolbar = new Toolbar();
			toolbar.setVisible(false);

			// toolbar.setBackground(Color.BLUE);
			// toolbar.setColor(Color.GREEN);
			// toolbar.setForegroundColor(Color.ORANGE);

			toolbar.setSize(StyleSheetConstants.IMAGE_POPUP_WIDTH,
					StyleSheetConstants.IMAGE_POPUP_HEIGHT);

			imageWindow.add(toolbar);
			imageWindow.updateImage(imagePlus);

		} else {
			// Alert alert = new Alert(Alert.AlertType.INFORMATION);
			// alert.setTitle("Information Dialog");
			// alert.setHeaderText("Please Select a File");
			/* alert.setContentText("You didn't select a file!"); */
			JOptionPane.showConfirmDialog(null, "Please Select a File", "",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

			// alert.showAndWait();
		}
	}

	private boolean validateForConfigData(ConfigData bean) {
		boolean isValid = true;
		// Validate
		if (bean.getSensitivity() < 1) {
			return false;
		}

		if (bean.getTransmissionCounts() < 1) {
			return false;
		}

		return isValid;
	}

	public boolean validateForDosageCalculation(MeasurementVO bean) {
		boolean isValid = true;
		// Validate
		if (!validateForConfigData(bean.getConfigData())) {
			JOptionPane.showConfirmDialog(null,
					"Please capture sensitivity and transmission values", "",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (isTextFieldEmpty(bean.getFirstMeasurementVO().getAnteriaLeftField()))
			return false;
		if (isTextFieldEmpty(bean.getFirstMeasurementVO()
				.getAnteriaRightField()))
			return false;
		if (isTextFieldEmpty(bean.getFirstMeasurementVO()
				.getAnteriaTumourField()))
			return false;

		if (isTextFieldEmpty(bean.getFirstMeasurementVO()
				.getPosteriaLeftField()))
			return false;
		if (isTextFieldEmpty(bean.getFirstMeasurementVO()
				.getPosteriaRightField()))
			return false;
		if (isTextFieldEmpty(bean.getFirstMeasurementVO()
				.getPosteriaTumourField()))
			return false;

		if (isTextFieldEmpty(bean.getSecondMeasurementVO()
				.getAnteriaLeftField()))
			return false;
		if (isTextFieldEmpty(bean.getSecondMeasurementVO()
				.getAnteriaRightField()))
			return false;
		if (isTextFieldEmpty(bean.getSecondMeasurementVO()
				.getAnteriaTumourField()))
			return false;

		if (isTextFieldEmpty(bean.getSecondMeasurementVO()
				.getPosteriaLeftField()))
			return false;
		if (isTextFieldEmpty(bean.getSecondMeasurementVO()
				.getPosteriaRightField()))
			return false;
		if (isTextFieldEmpty(bean.getSecondMeasurementVO()
				.getPosteriaTumourField()))
			return false;

		if (isTextFieldEmpty(bean.getThirdMeasurementVO().getAnteriaLeftField()))
			return false;
		if (isTextFieldEmpty(bean.getThirdMeasurementVO()
				.getAnteriaRightField()))
			return false;
		if (isTextFieldEmpty(bean.getThirdMeasurementVO()
				.getAnteriaTumourField()))
			return false;

		if (isTextFieldEmpty(bean.getThirdMeasurementVO()
				.getPosteriaLeftField()))
			return false;
		if (isTextFieldEmpty(bean.getThirdMeasurementVO()
				.getPosteriaRightField()))
			return false;
		if (isTextFieldEmpty(bean.getThirdMeasurementVO()
				.getPosteriaTumourField()))
			return false;
		return isValid;
	}

	private boolean isTextFieldEmpty(TextField txtField) {
		if (txtField == null || txtField.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(null,
					"Please take measurements for all images", "",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}

	public double covertToBecquerel(double meanValue) {
		double decquerel = Math.pow(3.7, 10d);

		MathContext mc = new MathContext(7, RoundingMode.HALF_UP);
		BigDecimal decquerelDecimal = new BigDecimal(decquerel, mc);
		BigDecimal meanDecimal = new BigDecimal(meanValue, mc);
		BigDecimal resultsDecimal = decquerelDecimal.multiply(meanDecimal);

		System.out.println("covertToBecquerel : meanValue = " + meanValue
				+ " resultsDecimal :  " + resultsDecimal.doubleValue());
		return resultsDecimal.doubleValue();
	}

	public double getSquareRootOfImages(double anteria, double posteria,
			double sensitivity, double transmition) {
		System.out.println("Anteria : " + anteria + " Posteria : " + posteria);
		double result = anteria * posteria;
		double sqRoot = 0;
		double absoluteCount = 0;
		if (result > 0) {
			sqRoot = Math.sqrt(result);
			absoluteCount = (sqRoot / sensitivity) * transmition;
			// TODO (GEOMEAN / Sensitivity)*Transmission
			System.out.println("result is a positive number *** ");
		} else {
			result = result * -1;
			sqRoot = Math.sqrt(result);
			absoluteCount = (sqRoot / sensitivity) * transmition;
			System.out
					.println("result is a negative number  calculated on + square root *** ");
		}

		System.out.println("Result  : " + absoluteCount + " square root : "
				+ sqRoot + "");
		return absoluteCount;
	}

	public MeasurementVO calculateMeanSquareRoot(MeasurementVO bean) {
		bean.getFirstMeasurementVO().setLeftImage(
				getSquareRootOfImages(bean.getFirstMeasurementVO()
						.getAnteriaLeft(), bean.getFirstMeasurementVO()
						.getPosteriaLeft(), bean.getConfigData()
						.getSensitivity(), bean.getConfigData()
						.getTransmissionCounts()));
		bean.getFirstMeasurementVO().setRightImage(
				getSquareRootOfImages(bean.getFirstMeasurementVO()
						.getAnteriaRight(), bean.getFirstMeasurementVO()
						.getPosteriaRight(), bean.getConfigData()
						.getSensitivity(), bean.getConfigData()
						.getTransmissionCounts()));
		bean.getFirstMeasurementVO().setTumourImage(
				getSquareRootOfImages(bean.getFirstMeasurementVO()
						.getAnteriaTumour(), bean.getFirstMeasurementVO()
						.getPosteriaTumour(), bean.getConfigData()
						.getSensitivity(), bean.getConfigData()
						.getTransmissionCounts()));

		bean.getSecondMeasurementVO().setLeftImage(
				getSquareRootOfImages(bean.getSecondMeasurementVO()
						.getAnteriaLeft(), bean.getSecondMeasurementVO()
						.getPosteriaLeft(), bean.getConfigData()
						.getSensitivity(), bean.getConfigData()
						.getTransmissionCounts()));
		bean.getSecondMeasurementVO().setRightImage(
				getSquareRootOfImages(bean.getSecondMeasurementVO()
						.getAnteriaRight(), bean.getSecondMeasurementVO()
						.getPosteriaRight(), bean.getConfigData()
						.getSensitivity(), bean.getConfigData()
						.getTransmissionCounts()));
		bean.getSecondMeasurementVO().setTumourImage(
				getSquareRootOfImages(bean.getSecondMeasurementVO()
						.getAnteriaTumour(), bean.getSecondMeasurementVO()
						.getPosteriaTumour(), bean.getConfigData()
						.getSensitivity(), bean.getConfigData()
						.getTransmissionCounts()));

		bean.getThirdMeasurementVO().setLeftImage(
				getSquareRootOfImages(bean.getThirdMeasurementVO()
						.getAnteriaLeft(), bean.getThirdMeasurementVO()
						.getPosteriaLeft(), bean.getConfigData()
						.getSensitivity(), bean.getConfigData()
						.getTransmissionCounts()));
		bean.getThirdMeasurementVO().setRightImage(
				getSquareRootOfImages(bean.getThirdMeasurementVO()
						.getAnteriaRight(), bean.getThirdMeasurementVO()
						.getPosteriaRight(), bean.getConfigData()
						.getSensitivity(), bean.getConfigData()
						.getTransmissionCounts()));
		bean.getThirdMeasurementVO().setTumourImage(
				getSquareRootOfImages(bean.getThirdMeasurementVO()
						.getAnteriaTumour(), bean.getThirdMeasurementVO()
						.getPosteriaTumour(), bean.getConfigData()
						.getSensitivity(), bean.getConfigData()
						.getTransmissionCounts()));
		return bean;
	}

	public double getMean(ImagePlus imagePlus, String backgroundCount) {
		System.out
				.println(" ***************START getMean *********************");
		double mean = getMeanCount(imagePlus);
		double dBGCount = Double.parseDouble(backgroundCount);
		double results = mean - dBGCount;

		MathContext mc = new MathContext(4, RoundingMode.HALF_UP);
		BigDecimal decimal = new BigDecimal(results, mc);

		System.out.println(" mean 						: " + mean);
		System.out.println(" Background 				: " + dBGCount);
		System.out.println(" Results(mean - Background) :  " + results);
		System.out
				.println(" decimal (Round 4 Digits	:" + decimal.doubleValue());

		System.out.println(" ***************END getMean*********************");

		return decimal.doubleValue();

	}

	public MenuBar createImageMenuBar(MeasurementVO bean,
			ImageTypeEnum imageTypeEnum, ImageNumberEnum imageNumberEnum) {
		MenuBar mb = new MenuBar();
		mb.add(buildImageMenu(bean));
		mb.add(buildROITypeMenu());
		mb.add(buildImageMeasureMenu(bean, imageTypeEnum, imageNumberEnum));
		mb.setHelpMenu(new Menu("Help"));
		
		return mb;
	}

	public MenuItem createMenuItem(String menuLabel,
			MeasureActionListenerInterface actionListener) {
		MenuItem menuItem = new MenuItem(menuLabel);
		menuItem.addActionListener(actionListener);
		return menuItem;
	}

	public Menu buildROITypeMenu() {

		Menu imgMenu = new Menu("ROI Type");
		MenuItem rectROI = new MenuItem("Rectangular ROI");
		OvalSelectionTypeActionListener rectAL = new OvalSelectionTypeActionListener(
				ROITypeEnum.RECTANGULAR_ROI);
		rectROI.addActionListener(rectAL);

		MenuItem ovalROI = new MenuItem("Oval ROI");
		OvalSelectionTypeActionListener ovalAL = new OvalSelectionTypeActionListener(
				ROITypeEnum.OVAL_ROI);
		ovalROI.addActionListener(ovalAL);

		MenuItem polyROI = new MenuItem("Polygon ROI");
		OvalSelectionTypeActionListener polyAL = new OvalSelectionTypeActionListener(
				ROITypeEnum.POLYGON_ROI);
		polyROI.addActionListener(polyAL);

		MenuItem freeHandROI = new MenuItem("Free Hand ROI");
		OvalSelectionTypeActionListener FreeAL = new OvalSelectionTypeActionListener(
				ROITypeEnum.FREE_HAND_ROI);
		freeHandROI.addActionListener(FreeAL);

		MenuItem straightROI = new MenuItem("Straight ROI");
		OvalSelectionTypeActionListener straightAL = new OvalSelectionTypeActionListener(
				ROITypeEnum.STREIGHT_ROI);
		straightROI.addActionListener(straightAL);
		
		MenuItem handROI = new MenuItem("Hand");
		OvalSelectionTypeActionListener handROIAL = new OvalSelectionTypeActionListener(
				ROITypeEnum.HAND);
		handROI.addActionListener(handROIAL);

		imgMenu.add(rectROI);
		imgMenu.add(ovalROI);
		imgMenu.add(polyROI);
		imgMenu.add(freeHandROI);
		imgMenu.add(straightROI);
		imgMenu.add(handROI);
		return imgMenu;
	}

	public Menu buildImageMenu(MeasurementVO bean) {

		Menu imgMenu = new Menu("View");
		// MenuItem duplicateMenu = new MenuItem("Duplicate");
		// DuplicateActionListener duplicateActionListener = new
		// DuplicateActionListener();
		// duplicateMenu.addActionListener(duplicateActionListener);

		// MenuItem cropMenu = new MenuItem("Crop");

		MenuItem zoomInMenu = new MenuItem("Zoom In");
		ZoomInActionListener zoomInActionListener = new ZoomInActionListener();
		zoomInMenu.addActionListener(zoomInActionListener);

		MenuItem zoomOutMenu = new MenuItem("Zoom Out");
		ZoomOutActionListener zoomOutActionListener = new ZoomOutActionListener();
		zoomOutMenu.addActionListener(zoomOutActionListener);

		// imgMenu.add(duplicateMenu);
		// imgMenu.add(cropMenu);
		imgMenu.add(zoomInMenu);
		imgMenu.add(zoomOutMenu);

		return imgMenu;
	}

	public Menu buildFXImageMenu(MeasurementBean bean) {

		Menu imgMenu = new Menu("Image");
		MenuItem duplicateMenu = new MenuItem("Duplicate");
		// DuplicateActionListener duplicateActionListener = new
		// DuplicateActionListener(
		// measurementPanel);
		// duplicateMenu.addActionListener(duplicateActionListener);

		MenuItem cropMenu = new MenuItem("Crop");

		MenuItem zoomInMenu = new MenuItem("Zoom In");
		// ZoomInActionListener zoomInActionListener = new
		// ZoomInActionListener();
		// zoomInMenu.addActionListener(zoomInActionListener);

		MenuItem zoomOutMenu = new MenuItem("Zoom Out");
		// ZoomOutActionListener zoomOutActionListener = new
		// ZoomOutActionListener();
		// zoomOutMenu.addActionListener(zoomOutActionListener);

		// imgMenu = new
		// imgMenu.add(duplicateMenu);
		// imgMenu.add(cropMenu);
		// imgMenu.add(zoomInMenu);
		// imgMenu.add(zoomOutMenu);

		return imgMenu;
	}

	public Menu buildImageMeasureMenu(MeasurementVO bean,
			ImageTypeEnum imageTypeEnum, ImageNumberEnum imageNumberEnum) {
		Menu measureMenu = new Menu("Analyse");

		Menu submenuAnt = new Menu("Anterior");
		Menu submenuPost = new Menu("Posterior");

		createSubmenuItem(bean, submenuAnt, ImageTypeEnum.Anteria,
				imageNumberEnum);
		createSubmenuItem(bean, submenuPost, ImageTypeEnum.Posteria,
				imageNumberEnum);

		measureMenu.add(submenuAnt);
		measureMenu.add(submenuPost);

		return measureMenu;
	}

	private void createSubmenuItem(MeasurementVO bean, Menu measureMenu,
			ImageTypeEnum imageTypeEnum, ImageNumberEnum imageNumberEnum) {
		if (imageNumberEnum == ImageNumberEnum.FirstImage) {
			measureMenu.add(createMenuItem("Background",
					new FirstImageMeasureActionListener(bean,
							ImageSideEnum.Background, imageTypeEnum)));
			measureMenu.add(createMenuItem("Left",
					new FirstImageMeasureActionListener(bean,
							ImageSideEnum.Left, imageTypeEnum)));
			measureMenu.add(createMenuItem("Right",
					new FirstImageMeasureActionListener(bean,
							ImageSideEnum.Right, imageTypeEnum)));
			measureMenu.add(createMenuItem("Tumour",
					new FirstImageMeasureActionListener(bean,
							ImageSideEnum.Tumour, imageTypeEnum)));

		} else if (imageNumberEnum == ImageNumberEnum.SecondImage) {

			measureMenu.add(createMenuItem("Background",
					new SecondImageMeasureActionListener(bean,
							ImageSideEnum.Background, imageTypeEnum)));
			measureMenu.add(createMenuItem("Left",
					new SecondImageMeasureActionListener(bean,
							ImageSideEnum.Left, imageTypeEnum)));
			measureMenu.add(createMenuItem("Right",
					new SecondImageMeasureActionListener(bean,
							ImageSideEnum.Right, imageTypeEnum)));
			measureMenu.add(createMenuItem("Tumour",
					new SecondImageMeasureActionListener(bean,
							ImageSideEnum.Tumour, imageTypeEnum)));

		} else if (imageNumberEnum == ImageNumberEnum.ThirdImage) {
			measureMenu.add(createMenuItem("Background",
					new ThirdImageMeasureActionListener(bean,
							ImageSideEnum.Background, imageTypeEnum)));
			measureMenu.add(createMenuItem("Left",
					new ThirdImageMeasureActionListener(bean,
							ImageSideEnum.Left, imageTypeEnum)));
			measureMenu.add(createMenuItem("Right",
					new ThirdImageMeasureActionListener(bean,
							ImageSideEnum.Right, imageTypeEnum)));
			measureMenu.add(createMenuItem("Tumour",
					new ThirdImageMeasureActionListener(bean,
							ImageSideEnum.Tumour, imageTypeEnum)));

		}
	}

	public double getMeanCount(ImagePlus imagePlus) {
		IJ.run("Set Measurements...",
				"area mean standard min redirect=None decimal=3");
		Analyzer analyzer = new Analyzer(imagePlus);

		analyzer.measure();
		analyzer.summarize();
		ResultsTable rt = Analyzer.getResultsTable();
		int count = rt.getCounter() - 5;
		if (count < 0)
			count = 0;
		double stdDev = rt.getValueAsDouble(ResultsTable.STD_DEV, count);
		double mean = rt.getValueAsDouble(ResultsTable.MEAN, count);
		System.out.println("Standard deviation : " + stdDev + " > mean : "
				+ mean);
		return stdDev;
	}

	
	
	public void generatedXsl(MeasurementVO bean) {

		FileWriter csvWriter;
		String fileName = "readings"+ bean.getPatientDetails().getPatientId() +"_"+ (new Date()).getTime() + ".csv";
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				System.out.println("File does not exists");
				file.createNewFile();
			}
			System.out.println("FileName" + file.getAbsoluteFile());
			csvWriter = new FileWriter(file.getAbsoluteFile());
			
			csvWriter.append("Images,");
			csvWriter.append("Ant Background");
			csvWriter.append(",");
			csvWriter.append("Ant Left");
			csvWriter.append(",");
			csvWriter.append("Ant Right");
			csvWriter.append(",");
			csvWriter.append("Ant Tumour,");
			csvWriter.append("Post Background");
			csvWriter.append(",");
			csvWriter.append("Post Left");
			csvWriter.append(",");
			csvWriter.append("Post Right");
			csvWriter.append(",");
			csvWriter.append("Post Tumour");
			csvWriter.append("\n");
			
			FirstMeasurementVO vo = bean.getFirstMeasurementVO();
			csvWriter.append("Images 1,");
			csvWriter.append("" + vo.getAnteriaBackground() + ",");
			csvWriter.append("" + vo.getAnteriaLeft() + ",");
			csvWriter.append("" + vo.getAnteriaRight() + ",");
			csvWriter.append("" + vo.getAnteriaTumour() + ",");
			
			csvWriter.append("" + vo.getPosteriaBackground() + ",");
			csvWriter.append("" + vo.getPosteriaLeft() + ",");
			csvWriter.append("" + vo.getPosteriaRight() + ",");
			csvWriter.append("" + vo.getPosteriaTumour() + "\n");
			
			csvWriter.append("Image 2,");
			SecondMeasurementVO vo2 = bean.getSecondMeasurementVO();
			csvWriter.append("" + vo2.getAnteriaBackground() + ",");
			csvWriter.append("" + vo2.getAnteriaLeft() + ",");
			csvWriter.append("" + vo2.getAnteriaRight() + ",");
			csvWriter.append("" + vo2.getAnteriaTumour() + ",");
			
			csvWriter.append("" + vo2.getPosteriaBackground() + ",");
			csvWriter.append("" + vo2.getPosteriaLeft() + ",");
			csvWriter.append("" + vo2.getPosteriaRight() + ",");
			csvWriter.append("" + vo2.getPosteriaTumour() + "\n");
			
			csvWriter.append("Image 3,");
			ThirdMeasurementVO vo3 = bean.getThirdMeasurementVO();
			csvWriter.append("" + vo3.getAnteriaBackground() + ",");
			csvWriter.append("" + vo3.getAnteriaLeft() + ",");
			csvWriter.append("" + vo3.getAnteriaRight() + ",");
			csvWriter.append("" + vo3.getAnteriaTumour() + ",");
			
			csvWriter.append("" + vo3.getPosteriaBackground() + ",");
			csvWriter.append("" + vo3.getPosteriaLeft() + ",");
			csvWriter.append("" + vo3.getPosteriaRight() + ",");
			csvWriter.append("" + vo3.getPosteriaTumour() + "\n");
//			for (List<String> rowData : rows) {
//				csvWriter.append(String.join(",", rowData));
//				csvWriter.append("\n");
//			}

			csvWriter.flush();
			csvWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void drawGraphNew(MeasurementVO measurementBean,
			LineChart<Number, Number> linechart) {
		linechart.setAnimated(false);
		linechart.getXAxis().setAutoRanging(true);
		linechart.getYAxis().setAutoRanging(true);

		linechart.getData().clear();

		// System.out.println("measurementBean : " + measurementBean);

		XYChart.Series rightImageDataSet = getRightImageDataSet(measurementBean);
		XYChart.Series leftImageDataSet = getLeftImageDataSet(measurementBean);
		XYChart.Series tumourImageDataSet = getTumourImageDataSet(measurementBean);
		linechart.getData().addAll(rightImageDataSet, leftImageDataSet,
				tumourImageDataSet);
		linechart.setLegendVisible(true);
		linechart.setCreateSymbols(true);

		calculateDosage(measurementBean);
		// System.out.println("Dosage " + measurementBean.getDosage());
		linechart.setTitle("Dosage is " + measurementBean.getDosage());

		linechart.setLegendSide(Side.RIGHT);

		// Legend.LegendItem li1=new Legend.LegendItem("Right Image");
		//
		// Legend.LegendItem li2=new Legend.LegendItem("Left Image");
		// Legend.LegendItem li3=new Legend.LegendItem("Tumour Image");
		// legend.getItems().setAll(li1,li2,li3);
		// legend.getItems().get(0).getSymbol().getStyleClass().add("series-right-image");
		// Node line = rightImageDataSet.getNode();
		// System.out.println(linechart.getStyleClass().add("default-color0.chart-line-symbol"));
		// System.out.println(linechart.getStyleClass().add("default-color0.chart-line-symbol"));
		//

		String OS = System.getProperty("os.name").toLowerCase();
		System.out.println("OS" + OS);
		linechart.getStylesheets().clear();
		if (OS == "linux") {
			File f = new File("src/main/java/co/za/master/dose/frame/Login.css");
			linechart.getStylesheets().add(
					"file:///" + f.getAbsolutePath().replace("\\", "/"));
		} else if (OS == "windows") {
			File f = new File("src/main/java/co/za/master/dose/frame/Login.css");
			linechart.getStylesheets().add(f.getAbsolutePath());
		}

		// String css =
		// DynamicCSS.class.getResource("/jarcss.css").toExternalForm();
		// scene.getStylesheets().clear();
		// scene.getStylesheets().add(css)

		// System.out.println("line: " +
		// line.getStyleClass().add("default-color0.chart-line-symbol"));
		// line.setStyle(".default-color0.chart-series-line { -fx-background-color: #3095CE, white; -fx-stroke: #3095CE; }, .default-color0.chart-line-symbol { -fx-background-color: #3095CE;-fx-text-fill: #3095CE; }");

		// for(int i=0; i<3; i++){
		// for(Node n : linechart.lookupAll(".series"+i)){
		// System.out.println(n.getStyleClass());
		// }
		// }

		// legend.getItems().get(0).getStyleClass.add("default-color0.chart-series-line");
		// .default-color0.chart-series-line { -fx-background-color: -line-0,
		// white; -fx-stroke: -line-0; }
		// .default-color1.chart-series-line { -fx-background-color: -line-1,
		// white; -fx-stroke: -line-1; }
		// .default-color2.chart-series-line { -fx-background-color: -line-2,
		// white; -fx-stroke: -line-2; }

	}

	@SuppressWarnings("rawtypes")
	private XYChart.Series getRightImageDataSet(MeasurementVO vo) {
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();

		series.getData().add(
				new XYChart.Data(vo.getFirstMeasurementVO().getInterval() + "",
						vo.getFirstMeasurementVO().getRightImage()));

		series.getData().add(
				new XYChart.Data(
						vo.getSecondMeasurementVO().getInterval() + "", vo
								.getSecondMeasurementVO().getRightImage()));

		series.getData().add(
				new XYChart.Data(vo.getThirdMeasurementVO().getInterval() + "",
						vo.getThirdMeasurementVO().getRightImage()));

		series.setName("Right Image");

		// System.out.println("Firs Interval : " +
		// vo.getFirstMeasurementVO().getInterval() + " : " +
		// vo.getFirstMeasurementVO().getRightImage());
		// System.out.println("second Interval : " +
		// vo.getSecondMeasurementVO().getInterval()+ " : " +
		// vo.getSecondMeasurementVO().getRightImage());
		// System.out.println("Third Interval : " +
		// vo.getThirdMeasurementVO().getInterval()+ " : " +
		// vo.getThirdMeasurementVO().getRightImage());

		// System.out.println("Series : " + series.getNode());
		// series.getNode().getStyleClass().add("series-right-image");
		// data.getNode().setStyle(css);
		return series;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private XYChart.Series getLeftImageDataSet(MeasurementVO vo) {
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		series.getData().add(
				new XYChart.Data(vo.getFirstMeasurementVO().getInterval() + "",
						vo.getFirstMeasurementVO().getLeftImage()));
		series.getData().add(
				new XYChart.Data(
						vo.getSecondMeasurementVO().getInterval() + "", vo
								.getSecondMeasurementVO().getLeftImage()));
		series.getData().add(
				new XYChart.Data(vo.getThirdMeasurementVO().getInterval() + "",
						vo.getThirdMeasurementVO().getLeftImage()));
		series.setName("Left Image");
		// System.out.println("Left series : " + series.getData() );
		return series;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private XYChart.Series getTumourImageDataSet(MeasurementVO vo) {
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		series.getData().add(
				new XYChart.Data(vo.getFirstMeasurementVO().getInterval() + "",
						vo.getFirstMeasurementVO().getTumourImage()));
		series.getData().add(
				new XYChart.Data(
						vo.getSecondMeasurementVO().getInterval() + "", vo
								.getSecondMeasurementVO().getTumourImage()));
		series.getData().add(
				new XYChart.Data(vo.getThirdMeasurementVO().getInterval() + "",
						vo.getThirdMeasurementVO().getTumourImage()));
		series.setName("Tumour Image");
		return series;
	}

	// public ChartPanel getChartPanel(MeasurementVO measurementBean) {
	// JFreeChart chart = getFreeChart(measurementBean);
	// ChartPanel cp = new ChartPanel(chart);
	// cp.setPreferredSize(new Dimension(StyleSheetConstants.GRAPH_WIDTH,
	// StyleSheetConstants.GRAPH_HEIGHT));
	// return cp;
	// }

	public JFreeChart getChart(MeasurementVO measurementBean) {
		double[][] data1 = {
				{ measurementBean.getFirstMeasurementVO().getInterval(),
						measurementBean.getSecondMeasurementVO().getInterval(),
						measurementBean.getThirdMeasurementVO().getInterval() },
				{
						measurementBean.getFirstMeasurementVO().getRightImage(),
						measurementBean.getSecondMeasurementVO()
								.getRightImage(),
						measurementBean.getThirdMeasurementVO().getRightImage() } };
		double[][] data2 = {
				{ measurementBean.getFirstMeasurementVO().getInterval(),
						measurementBean.getSecondMeasurementVO().getInterval(),
						measurementBean.getThirdMeasurementVO().getInterval() },
				{
						measurementBean.getFirstMeasurementVO().getLeftImage(),
						measurementBean.getSecondMeasurementVO().getLeftImage(),
						measurementBean.getThirdMeasurementVO().getLeftImage() } };
		double[][] data3 = {
				{ measurementBean.getFirstMeasurementVO().getInterval(),
						measurementBean.getSecondMeasurementVO().getInterval(),
						measurementBean.getThirdMeasurementVO().getInterval() },
				{
						measurementBean.getFirstMeasurementVO()
								.getTumourImage(),
						measurementBean.getSecondMeasurementVO()
								.getTumourImage(),
						measurementBean.getThirdMeasurementVO()
								.getTumourImage() } };

		DefaultXYDataset ds = new DefaultXYDataset();
		ds.addSeries("Right Image", data1);
		ds.addSeries("Left Image", data2);
		ds.addSeries("Tumour Image", data3);

		calculateDosage(measurementBean);

		// JFreeChart chart = ChartFactory.createXYLineChart("Chart", "Time(h)",
		// "Y",ds);
		JFreeChart chart = ChartFactory.createXYLineChart("Chart", "Time(h)",
				"Y", ds, PlotOrientation.VERTICAL, true, true, false);
		//
		final XYPlot plot1 = chart.getXYPlot();
		// plot1.setBackgroundPaint(Color.lightGray);
		// plot1.setDomainGridlinePaint(Color.white);
		// plot1.setRangeGridlinePaint(Color.white);
		plot1.setDomainGridlinesVisible(true);
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
		plot1.setRenderer(renderer);
		renderer.setBaseShapesVisible(true);
		renderer.setBaseShapesFilled(true);

		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(2);
		XYItemLabelGenerator generator = new StandardXYItemLabelGenerator(
				StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT, format,
				format);
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);
		return chart;
	}

	// public JFreeChart getFreeChart(MeasurementVO measurementBean) {
	// double[][] data1 = {
	// {
	// ImageMeasureTime.FIRST_MEASUREMENT_TIME
	// .getMeasureTime(),
	// ImageMeasureTime.SECOND_MEASUREMENT_TIME
	// .getMeasureTime(),
	// ImageMeasureTime.THIRD_MEASUREMENT_TIME
	// .getMeasureTime() },
	// {
	// measurementBean.getFirstMeasurementVO().getRightImage(),
	// measurementBean.getSecondMeasurementVO()
	// .getRightImage(),
	// measurementBean.getThirdMeasurementVO().getRightImage() } };
	// double[][] data2 = {
	// {
	// ImageMeasureTime.FIRST_MEASUREMENT_TIME
	// .getMeasureTime(),
	// ImageMeasureTime.SECOND_MEASUREMENT_TIME
	// .getMeasureTime(),
	// ImageMeasureTime.THIRD_MEASUREMENT_TIME
	// .getMeasureTime() },
	// {
	// measurementBean.getFirstMeasurementVO().getLeftImage(),
	// measurementBean.getSecondMeasurementVO().getLeftImage(),
	// measurementBean.getThirdMeasurementVO().getLeftImage() } };
	// double[][] data3 = {
	// {
	// ImageMeasureTime.FIRST_MEASUREMENT_TIME
	// .getMeasureTime(),
	// ImageMeasureTime.SECOND_MEASUREMENT_TIME
	// .getMeasureTime(),
	// ImageMeasureTime.THIRD_MEASUREMENT_TIME
	// .getMeasureTime() },
	// {
	// measurementBean.getFirstMeasurementVO()
	// .getTumourImage(),
	// measurementBean.getSecondMeasurementVO()
	// .getTumourImage(),
	// measurementBean.getThirdMeasurementVO()
	// .getTumourImage() } };
	//
	// DefaultXYDataset ds = new DefaultXYDataset();
	// ds.addSeries("Right Image", data1);
	// ds.addSeries("Left Image", data2);
	// ds.addSeries("Tumour Image", data3);
	//
	// calculateDosage(measurementBean);
	//
	// // JFreeChart chart = ChartFactory.createXYLineChart("Chart", "Time(h)",
	// // "Y",ds);
	// JFreeChart chart = ChartFactory.createXYLineChart("Chart", "Time(h)",
	// "Y", ds, PlotOrientation.VERTICAL, true, true, false);
	// //
	// final XYPlot plot1 = chart.getXYPlot();
	// // plot1.setBackgroundPaint(Color.lightGray);
	// // plot1.setDomainGridlinePaint(Color.white);
	// // plot1.setRangeGridlinePaint(Color.white);
	// plot1.setDomainGridlinesVisible(true);
	// XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
	// plot1.setRenderer(renderer);
	// renderer.setBaseShapesVisible(true);
	// renderer.setBaseShapesFilled(true);
	//
	// NumberFormat format = NumberFormat.getNumberInstance();
	// format.setMaximumFractionDigits(2);
	// XYItemLabelGenerator generator = new StandardXYItemLabelGenerator(
	// StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT, format,
	// format);
	// renderer.setBaseItemLabelGenerator(generator);
	// renderer.setBaseItemLabelsVisible(true);
	// return chart;
	// }

	private void calculateDosage(MeasurementVO vo) {

		System.out.println("Calculate Dosage : " + vo.toString());
		double S_VALUE = 0.29;

		// ((L2+L1)/2)*(t2-t1)
		double gradientL1 = ((vo.getSecondMeasurementVO().getLeftImage() + vo
				.getFirstMeasurementVO().getLeftImage()) / 2)
				* (ImageMeasureTime.SECOND_MEASUREMENT_TIME.getMeasureTime() - ImageMeasureTime.FIRST_MEASUREMENT_TIME
						.getMeasureTime());
		System.out.println("gradient1 : " + gradientL1);

		// ((L3+L2)/2)*(t3-t1)
		double gradientL2 = ((vo.getThirdMeasurementVO().getLeftImage() + vo
				.getSecondMeasurementVO().getLeftImage()) / 2)
				* (ImageMeasureTime.THIRD_MEASUREMENT_TIME.getMeasureTime() - ImageMeasureTime.SECOND_MEASUREMENT_TIME
						.getMeasureTime());
		System.out.println("gradientL2 : " + gradientL2);

		// ((R2+R1)/2)*(t2-t1)
		double gradientR1 = ((vo.getSecondMeasurementVO().getRightImage() + vo
				.getFirstMeasurementVO().getRightImage()) / 2)
				* (ImageMeasureTime.SECOND_MEASUREMENT_TIME.getMeasureTime() - ImageMeasureTime.FIRST_MEASUREMENT_TIME
						.getMeasureTime());
		System.out.println("gradientR1 : " + gradientR1);

		// ((R3+R2)/2)*(t3-t1)
		double gradientR2 = ((vo.getThirdMeasurementVO().getRightImage() + vo
				.getSecondMeasurementVO().getRightImage()) / 2)
				* (ImageMeasureTime.THIRD_MEASUREMENT_TIME.getMeasureTime() - ImageMeasureTime.SECOND_MEASUREMENT_TIME
						.getMeasureTime());
		System.out.println("gradientR2 : " + gradientR2);

		// ((R2+R1)/2)*(t2-t1)
		double gradientT1 = ((vo.getSecondMeasurementVO().getTumourImage() + vo
				.getFirstMeasurementVO().getTumourImage()) / 2)
				* (ImageMeasureTime.SECOND_MEASUREMENT_TIME.getMeasureTime() - ImageMeasureTime.FIRST_MEASUREMENT_TIME
						.getMeasureTime());
		System.out.println("gradientT1 : " + gradientT1);

		// ((R3+R2)/2)*(t3-t1)
		double gradientT2 = ((vo.getThirdMeasurementVO().getTumourImage() + vo
				.getSecondMeasurementVO().getTumourImage()) / 2)
				* (ImageMeasureTime.THIRD_MEASUREMENT_TIME.getMeasureTime() - ImageMeasureTime.SECOND_MEASUREMENT_TIME
						.getMeasureTime());
		System.out.println("gradientT2 : " + gradientT2);

		double sumLGradient = ((gradientL1 + gradientL2) / 1000) * S_VALUE;

		System.out.println("sumLGradient : " + sumLGradient);

		double sumRGradient = ((gradientR1 + gradientR2) / 1000) * S_VALUE;
		System.out.println("sumRGradient :" + sumRGradient);

		double sumTGradient = ((gradientT1 + gradientT2) / 1000) * S_VALUE;
		System.out.println("sumTGradient :" + sumTGradient);

		double totalD = sumLGradient + sumRGradient + sumTGradient;
		MathContext mc = new MathContext(4, RoundingMode.HALF_UP);
		BigDecimal dosage = new BigDecimal(totalD, mc);

		System.out.println("dosage : " + dosage);
		vo.setDosage(dosage.doubleValue());
	}

	public MeasurementVO populateMeasurementBean(MeasurementVO bean)
			throws Exception {

		double antLeft1 = bean.getFirstMeasurementVO().getAnteriaLeft();
		double antLeft2 = bean.getSecondMeasurementVO().getAnteriaLeft();
		double antLeft3 = bean.getThirdMeasurementVO().getAnteriaLeft();

		double antRight1 = bean.getFirstMeasurementVO().getAnteriaRight();
		double antRight2 = bean.getSecondMeasurementVO().getAnteriaRight();
		double antRight3 = bean.getThirdMeasurementVO().getAnteriaRight();

		double antTumour1 = bean.getFirstMeasurementVO().getAnteriaTumour();
		double antTumour2 = bean.getSecondMeasurementVO().getAnteriaTumour();
		double antTumour3 = bean.getThirdMeasurementVO().getAnteriaTumour();

		double postLeft1 = bean.getFirstMeasurementVO().getPosteriaLeft();
		double postLeft2 = bean.getSecondMeasurementVO().getPosteriaLeft();
		double postLeft3 = bean.getThirdMeasurementVO().getPosteriaLeft();

		double postRight1 = bean.getFirstMeasurementVO().getPosteriaRight();
		double postRight2 = bean.getSecondMeasurementVO().getPosteriaRight();
		double postRight3 = bean.getThirdMeasurementVO().getPosteriaRight();

		double postTumour1 = bean.getFirstMeasurementVO().getPosteriaTumour();
		double postTumour2 = bean.getSecondMeasurementVO().getPosteriaTumour();
		double postTumour3 = bean.getThirdMeasurementVO().getPosteriaTumour();

		bean.getFirstMeasurementVO().setAnteriaLeft(
				ImageHelper.instance.covertToBecquerel(antLeft1));
		bean.getFirstMeasurementVO().setAnteriaRight(
				ImageHelper.instance.covertToBecquerel(antRight1));

		bean.getFirstMeasurementVO().setPosteriaLeft(
				ImageHelper.instance.covertToBecquerel(postLeft1));
		bean.getFirstMeasurementVO().setPosteriaRight(
				ImageHelper.instance.covertToBecquerel(postRight1));

		bean.getSecondMeasurementVO().setAnteriaLeft(
				ImageHelper.instance.covertToBecquerel(antLeft2));
		bean.getSecondMeasurementVO().setAnteriaRight(
				ImageHelper.instance.covertToBecquerel(antRight2));

		bean.getSecondMeasurementVO().setPosteriaLeft(
				ImageHelper.instance.covertToBecquerel(postLeft2));
		bean.getSecondMeasurementVO().setPosteriaRight(
				ImageHelper.instance.covertToBecquerel(postRight2));

		bean.getThirdMeasurementVO().setAnteriaLeft(
				ImageHelper.instance.covertToBecquerel(antLeft3));
		bean.getThirdMeasurementVO().setAnteriaRight(
				ImageHelper.instance.covertToBecquerel(antRight3));

		bean.getThirdMeasurementVO().setPosteriaLeft(
				ImageHelper.instance.covertToBecquerel(postLeft3));
		bean.getThirdMeasurementVO().setPosteriaRight(
				ImageHelper.instance.covertToBecquerel(postRight3));

		bean.getFirstMeasurementVO().setAnteriaTumour(
				ImageHelper.instance.covertToBecquerel(antTumour1));
		bean.getFirstMeasurementVO().setPosteriaTumour(
				ImageHelper.instance.covertToBecquerel(postTumour1));

		bean.getSecondMeasurementVO().setAnteriaTumour(
				ImageHelper.instance.covertToBecquerel(antTumour2));

		bean.getSecondMeasurementVO().setPosteriaTumour(
				ImageHelper.instance.covertToBecquerel(postTumour2));

		bean.getThirdMeasurementVO().setAnteriaTumour(
				ImageHelper.instance.covertToBecquerel(antTumour3));
		bean.getThirdMeasurementVO().setPosteriaTumour(
				ImageHelper.instance.covertToBecquerel(postTumour3));
		return bean;
	}
	//
	// public MeasurementBean createMeasureBean() {
	// MeasurementBean bean = new MeasurementBean();
	// bean.setFirstRightImage(25);;
	// bean.setFirstLeftImage(25);
	// bean.setFirstTumourImage(65);
	//
	// bean.setSecondRightImage(25);
	// bean.setSecondleftImage(53);
	// bean.setSecondTumourImage(522);
	//
	// bean.setThirdRightImage(51);
	// bean.setThirdLeftImage(26);
	// bean.setThirdTumourImage(27);
	// return bean;
	// }
}
