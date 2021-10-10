package co.za.master.dose.utils;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import co.za.master.dose.constants.MasterDoseConstants;
import co.za.master.dose.constants.StyleSheetConstants;
import co.za.master.dose.image.listeners.ExitActionListener;
import co.za.master.dose.image.listeners.InvertImageActionListener;
import co.za.master.dose.image.listeners.OvalSelectionTypeActionListener;
import co.za.master.dose.image.listeners.ROIManagerActionListener;
import co.za.master.dose.image.listeners.ZoomInActionListener;
import co.za.master.dose.image.listeners.ZoomOutActionListener;
import co.za.master.dose.measure.listeners.ImageMeasureActionListener;
import co.za.master.dose.measure.listeners.MeasureActionListenerInterface;
import co.za.master.dose.measure.listeners.SpectImageMeasureActionListener;
import co.za.master.dose.model.ConfigData;
import co.za.master.dose.model.ImageMeasureItem;
import co.za.master.dose.model.ImageNumberEnum;
import co.za.master.dose.model.ImageSideEnum;
import co.za.master.dose.model.ImageTypeEnum;
import co.za.master.dose.model.MeasurementVO;
import co.za.master.dose.model.ROITypeEnum;
import co.za.master.dose.model.User;
import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.ImageWindow;
import ij.gui.Toolbar;
import ij.io.Opener;
import ij.measure.ResultsTable;
import ij.plugin.filter.Analyzer;
import ij.process.ImageProcessor;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageHelper {

	public static ImageHelper instance = new ImageHelper();

	private static final String DELIMINATOR = ",";

	public static void main(String[] arg) {
	}

	public void saveConfigData(ConfigData configData) {
		BufferedReader br = null;
		try {
			File file = new File(getFileName(MasterDoseConstants.FILE_PATH_CONFIG_DATA));
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter csvWriter = new FileWriter(file);
			if (configData != null) {
				csvWriter.append(configData.getSensitivity() + "").append(DELIMINATOR)
						.append(configData.getTransmissionCounts() + "").append(DELIMINATOR)
						.append(configData.getImageType()).append(DELIMINATOR)
						.append(configData.getScatterCorrection() + "").append(DELIMINATOR)
						.append(configData.getMaleSValue() + "").append(DELIMINATOR)
						.append(configData.getFemaleSValue() + "");
			}
			csvWriter.flush();
			csvWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ConfigData getConfigData() {
		BufferedReader br = null;

		ConfigData configData = new ConfigData();
		try {
			File file = new File(getFileName(MasterDoseConstants.FILE_PATH_CONFIG_DATA));
			System.out.println("File path configs " + file.getAbsolutePath());
			if (!file.exists()) {
				file.createNewFile();
			}

			String line = "";

			br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				String[] configs = line.split(DELIMINATOR);
				configData.setSensitivity(Double.parseDouble(configs[0]));
				configData.setTransmissionCounts(Double.parseDouble(configs[1]));
				configData.setImageType(configs[2]);
				configData.setScatterCorrection(Double.parseDouble(configs[3]));
				configData.setMaleSValue(Double.parseDouble(configs[4]));
				configData.setFemaleSValue(Double.parseDouble(configs[5]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		MasterDoseCache.instance.getMeasurementVO().setConfigData(configData);
		return configData;
	}

	public String getFileName(String file) {
		String fileName = "";
		String home = System.getProperty("user.home");
		String OS = System.getProperty("os.name").toLowerCase();

		if (OS.contains("Linux") || OS.contains("linux")) {
			fileName = home + "/master-dose/" + file;
		} else {
			fileName = home + "\\master-dose\\" + file;
		}

		System.out.println("OS" + OS + " fileName " + fileName);
		return fileName;

	}

	public Map<String, User> getAllUsers() {
		BufferedReader br = null;
		Map<String, User> userMap = new HashMap<String, User>();

		try {

			File file = new File(getFileName(MasterDoseConstants.FILE_PATH_USERS));
			if (!file.exists()) {
				file.createNewFile();
			}

			String line = "";

			br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				String[] users = line.split(DELIMINATOR);
				User user = new User(users[0], users[1].toLowerCase(), users[2], users[3], users[4]);
				userMap.put(user.getUsername(), user);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return userMap;
	}

	public String authorize(String providedPassword, String username) {
		Map<String, User> list = ImageHelper.instance.getAllUsers();
		String salt = MasterDoseCache.instance.getMeasurementVO().getPasswordSalt();

		User userDto = list.get(username.toLowerCase());
		if (userDto == null) {
			return null;
		}

		boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, userDto.getPassword(), salt);
		if (passwordMatch) {
			MasterDoseCache.instance.getMeasurementVO().setLoggedOnUser(userDto);
			return generateSessionID();
		}
		return null;
	}

	private static int sessionID = 0;

	private String generateSessionID() {
		sessionID++;
		return "xyzzy - session " + sessionID;
	}

	public void saveUser(Map<String, User> userMap) {
		BufferedReader br = null;
		try {
			File file = new File(getFileName(MasterDoseConstants.FILE_PATH_USERS));
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter csvWriter = new FileWriter(file);

			for (User user : userMap.values()) {
				csvWriter.append(user.getName()).append(DELIMINATOR).append(user.getUsername()).append(DELIMINATOR)
						.append(user.getPassword()).append(DELIMINATOR).append(user.getRole()).append(DELIMINATOR)
						.append(user.getStatus()).append("\n");
			}

			csvWriter.flush();
			csvWriter.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void showImageNew(MeasurementVO measurementVO) {
		FileChooser chooser = new FileChooser();
		IJ.run("Invert", "stack");
		chooser.setTitle("Open File");
		File file = chooser.showOpenDialog(new Stage());
		if (WindowManager.getCurrentImage() != null) {
			WindowManager.getCurrentImage().close();
		}

		if (file != null) {
			String imagepath = file.getPath();
			Opener opener = new Opener();
			ImagePlus imagePlus = opener.openImage(imagepath);
			ImageProcessor ip = imagePlus.getProcessor();
			imagePlus.setProcessor(ip);
			imagePlus.show();
			ImageWindow imageWindow = WindowManager.getCurrentWindow();

			imageWindow.setMenuBar(ImageHelper.instance.createImageMenuBarNew(measurementVO));
			Toolbar toolbar = new Toolbar();
			toolbar.setVisible(false);
			toolbar.setSize(StyleSheetConstants.IMAGE_POPUP_WIDTH, StyleSheetConstants.IMAGE_POPUP_HEIGHT);

			imageWindow.add(toolbar);
			imageWindow.updateImage(imagePlus);

		} else {
			JOptionPane.showConfirmDialog(null, "Please Select a File", "", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean validateForConfigData(ConfigData bean) {
		boolean isValid = true;

		// Validate if the image type is plainer
		if (bean.getImageType().equalsIgnoreCase(MasterDoseConstants.IMAGE_TYPE_PLAINER)) {
			if (bean.getSensitivity() < 1) {
				return false;
			}

			if (bean.getTransmissionCounts() < 1) {
				return false;
			}
		}

		return isValid;
	}

	public boolean validateForDosageCalculation(MeasurementVO bean) {
		boolean isValid = true;
		// Validate
		if (!validateForConfigData(bean.getConfigData())) {
			JOptionPane.showConfirmDialog(null, "Please capture sensitivity and transmission values", "",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return isValid;
	}

	public double covertToBecquerel(double meanValue) {
		double decquerel = Math.pow(3.7, 10d);

		MathContext mc = new MathContext(7, RoundingMode.HALF_UP);
		BigDecimal decquerelDecimal = new BigDecimal(decquerel, mc);
		BigDecimal meanDecimal = new BigDecimal(meanValue, mc);
		BigDecimal resultsDecimal = decquerelDecimal.multiply(meanDecimal);

		System.out.println(
				"covertToBecquerel : meanValue = " + meanValue + " resultsDecimal :  " + resultsDecimal.doubleValue());
		return resultsDecimal.doubleValue();
	}

	public double getSquareRootOfImages(double anteria, double posteria, double sensitivity, double transmition) {
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
			System.out.println("result is a negative number  calculated on + square root *** ");
		}

		System.out.println("Result  : " + absoluteCount + " square root : " + sqRoot + "");
		return absoluteCount;
	}

	public double getSquareRootOfImagesWithTypes(double anteria, double posteria, double sensitivity,
			double transmition, String imageType, double scatterCorrection) {
		System.out.println("Anteria : " + anteria + " Posteria : " + posteria);
		double result = anteria * posteria;
		double sqRoot = 0;
		double absoluteCount = 0;

		if (imageType.equalsIgnoreCase(MasterDoseConstants.IMAGE_TYPE_SPECT)) {

			absoluteCount = result - scatterCorrection;
//			double countWithoutScatter = absoluteCount - scatterCorrection;

		} else {
			if (result > 0) {
				sqRoot = Math.sqrt(result);
				double countWithoutScatter = sqRoot - scatterCorrection;
				absoluteCount = (countWithoutScatter / sensitivity) * transmition;
				// TODO (GEOMEAN / Sensitivity)*Transmission
				System.out.println("result is a positive number *** ");
			} else {
				result = result * -1;
				sqRoot = Math.sqrt(result);
				double countWithoutScatter = sqRoot - scatterCorrection;
				absoluteCount = (countWithoutScatter / sensitivity) * transmition;
				System.out.println("result is a negative number  calculated on + square root *** ");
			}
		}
		System.out.println("Result  : " + absoluteCount + " square root : " + sqRoot + "");
		return absoluteCount;
	}

	
	public double getAbsoluteCountForSpect(double count , MeasurementVO bean) {
		System.out.println("count : " + count );
		
		double countWithoutScatter = count - bean.getConfigData().getScatterCorrection();
		double absoluteCount = (countWithoutScatter /  bean.getConfigData().getSensitivity()) * bean.getConfigData().getTransmissionCounts();
		
		System.out.println("Result  : " + absoluteCount );
		return absoluteCount;
	}
	
	public MeasurementVO calculateMeanSquareRootNew(MeasurementVO bean) {
		ImageMeasureItem posteria = null;
		ImageMeasureItem anteria = null;
		Map<String, Double> map = new HashMap<>();
		Map<String, Integer> intervalMap = new HashMap<>();
		String imageType = bean.getConfigData().getImageType();

		for (String key : bean.getMap().keySet()) {
			String imageNo = key.substring(0, key.indexOf("_"));
			if (imageType.equalsIgnoreCase(MasterDoseConstants.IMAGE_TYPE_PLAINER)) {
				if (key.contains(ImageTypeEnum.Anterior.name())) {
					anteria = bean.getMap().get(key);
				} else if (key.contains(ImageTypeEnum.Posterior.name())) {
					posteria = bean.getMap().get(key);
				}

				if (posteria != null && anteria != null) {
					double squareLeftResults = getSquareRootOfImagesWithTypes(anteria.getLeftMeanCount(),
							posteria.getLeftMeanCount(), bean.getConfigData().getSensitivity(),
							bean.getConfigData().getTransmissionCounts(), bean.getConfigData().getImageType(),
							bean.getConfigData().getScatterCorrection());
					double squareRightResults = getSquareRootOfImagesWithTypes(anteria.getRightMeanCount(),
							posteria.getRightMeanCount(), bean.getConfigData().getSensitivity(),
							bean.getConfigData().getTransmissionCounts(), bean.getConfigData().getImageType(),
							bean.getConfigData().getScatterCorrection());
					double squareTumourResults = getSquareRootOfImagesWithTypes(anteria.getTumourMeanCount(),
							posteria.getTumourMeanCount(), bean.getConfigData().getSensitivity(),
							bean.getConfigData().getTransmissionCounts(), bean.getConfigData().getImageType(),
							bean.getConfigData().getScatterCorrection());
					map.put(imageNo + "_Left", squareLeftResults);
					map.put(imageNo + "_Right", squareRightResults);
					map.put(imageNo + "_Tumour", squareTumourResults);
					intervalMap.put(imageNo, anteria.getInterval());
					posteria = null;
					anteria = null;
				}
			} else {
				ImageMeasureItem imageMeasureItem = bean.getMap().get(key);
				
				map.put(imageNo + "_Left", getAbsoluteCountForSpect(imageMeasureItem.getLeftMeanCount(), bean));
				map.put(imageNo + "_Right", getAbsoluteCountForSpect(imageMeasureItem.getRightMeanCount(), bean));
				map.put(imageNo + "_Tumour", getAbsoluteCountForSpect(imageMeasureItem.getTumourMeanCount(),bean));
				intervalMap.put(imageNo, imageMeasureItem.getInterval());
			}
		}

		bean.setIntervalMap(intervalMap);
		bean.setSquareRoot(map);
		return bean;
	}

	public double getMean(ImagePlus imagePlus, String backgroundCount) {
		System.out.println(" ***************START getMean *********************");
		double mean = getMeanCount(imagePlus);
		double dBGCount = Double.parseDouble(backgroundCount);
		double results = mean - dBGCount;

		MathContext mc = new MathContext(6, RoundingMode.HALF_UP);
		BigDecimal decimal = new BigDecimal(results, mc);

		System.out.println(" mean 						: " + mean);
		System.out.println(" Background 				: " + dBGCount);
		System.out.println(" Results(mean - Background) :  " + results);
		System.out.println(" decimal (Round 4 Digits	:" + decimal.doubleValue());

		System.out.println(" ***************END getMean*********************");

		return decimal.doubleValue();

	}

	public MenuBar createImageMenuBar(MeasurementVO bean, ImageTypeEnum imageTypeEnum,
			ImageNumberEnum imageNumberEnum) {
		MenuBar mb = new MenuBar();
		mb.add(buildImageMenu(bean));
		mb.add(buildROITypeMenu());
		mb.setHelpMenu(new Menu("Help"));

		return mb;
	}

	public MenuBar createImageMenuBarNew(MeasurementVO bean) {
		MenuBar mb = new MenuBar();
		mb.add(buildFileMenu(bean));
		mb.add(buildEditMenu(bean));
		mb.add(buildImageMenu(bean));

		if (bean.getConfigData().getImageType() != null
				&& bean.getConfigData().getImageType().equalsIgnoreCase(MasterDoseConstants.IMAGE_TYPE_PLAINER)) {
			mb.add(buildPlannarImageMeasureMenuNew(bean));
		} else {
			mb.add(buildSpectImageMeasureMenuNew(bean));
		}
//		mb.add(buildImageMeasureMenuNew(bean));
		mb.setHelpMenu(new Menu("Help"));

		return mb;
	}

	public MenuItem createMenuItem(String menuLabel, MeasureActionListenerInterface actionListener) {
		MenuItem menuItem = new MenuItem(menuLabel);
		menuItem.addActionListener(actionListener);
		return menuItem;
	}

	public Menu buildROITypeMenu() {

		Menu imgMenu = new Menu("ROI Type");
		MenuItem rectROI = new MenuItem("Rectangular ROI");
		OvalSelectionTypeActionListener rectAL = new OvalSelectionTypeActionListener(ROITypeEnum.RECTANGULAR_ROI);
		rectROI.addActionListener(rectAL);

		MenuItem ovalROI = new MenuItem("Oval ROI");
		OvalSelectionTypeActionListener ovalAL = new OvalSelectionTypeActionListener(ROITypeEnum.OVAL_ROI);
		ovalROI.addActionListener(ovalAL);

		MenuItem polyROI = new MenuItem("Polygon ROI");
		OvalSelectionTypeActionListener polyAL = new OvalSelectionTypeActionListener(ROITypeEnum.POLYGON_ROI);
		polyROI.addActionListener(polyAL);

		MenuItem freeHandROI = new MenuItem("Free Hand ROI");
		OvalSelectionTypeActionListener FreeAL = new OvalSelectionTypeActionListener(ROITypeEnum.FREE_HAND_ROI);
		freeHandROI.addActionListener(FreeAL);

		MenuItem straightROI = new MenuItem("Straight ROI");
		OvalSelectionTypeActionListener straightAL = new OvalSelectionTypeActionListener(ROITypeEnum.STREIGHT_ROI);
		straightROI.addActionListener(straightAL);

		MenuItem handROI = new MenuItem("Hand");
		OvalSelectionTypeActionListener handROIAL = new OvalSelectionTypeActionListener(ROITypeEnum.HAND);
		handROI.addActionListener(handROIAL);

		imgMenu.add(rectROI);
		imgMenu.add(ovalROI);
		imgMenu.add(polyROI);
		imgMenu.add(freeHandROI);
		imgMenu.add(straightROI);
		imgMenu.add(handROI);
		return imgMenu;
	}

	public Menu buildFileMenu(MeasurementVO bean) {

		Menu fileMenu = new Menu("File");

		MenuItem exitMenu = new MenuItem("Exit");
		ExitActionListener exitActionListener = new ExitActionListener();
		exitMenu.addActionListener(exitActionListener);
		fileMenu.add(exitMenu);
		return fileMenu;
	}

	public Menu buildEditMenu(MeasurementVO bean) {

		Menu editMenu = new Menu("Edit");

		MenuItem rOIManager = new MenuItem("ROI Manager");
		ROIManagerActionListener roiManagerActionListener = new ROIManagerActionListener();
		rOIManager.addActionListener(roiManagerActionListener);

		MenuItem invertMenu = new MenuItem("Invert");
		InvertImageActionListener invertActionListener = new InvertImageActionListener();
		invertMenu.addActionListener(invertActionListener);

		editMenu.add(rOIManager);
		editMenu.add(invertMenu);

		return editMenu;
	}

	public Menu buildImageMenu(MeasurementVO bean) {

		Menu imgMenu = new Menu("Image");

		MenuItem rOIManager = new MenuItem("ROI Manager");
		ROIManagerActionListener roiManagerActionListener = new ROIManagerActionListener();
		rOIManager.addActionListener(roiManagerActionListener);

		Menu rOIType = buildROITypeMenu();

		MenuItem zoomInMenu = new MenuItem("Zoom In");
		ZoomInActionListener zoomInActionListener = new ZoomInActionListener();
		zoomInMenu.addActionListener(zoomInActionListener);

		MenuItem zoomOutMenu = new MenuItem("Zoom Out");
		ZoomOutActionListener zoomOutActionListener = new ZoomOutActionListener();
		zoomOutMenu.addActionListener(zoomOutActionListener);

		// imgMenu.add(duplicateMenu);
		imgMenu.add(rOIManager);
		imgMenu.add(rOIType);
		imgMenu.add(zoomInMenu);
		imgMenu.add(zoomOutMenu);

		return imgMenu;
	}

	public Menu buildSpectImageMeasureMenuNew(MeasurementVO bean) {
		Menu measureMenu = new Menu("Analyse");

		measureMenu.add(createMenuItem("Background",
				new SpectImageMeasureActionListener(bean, ImageSideEnum.Background, null)));
		measureMenu.add(createMenuItem("Left", new SpectImageMeasureActionListener(bean, ImageSideEnum.Left, null)));
		measureMenu.add(createMenuItem("Right", new SpectImageMeasureActionListener(bean, ImageSideEnum.Right, null)));
		measureMenu
				.add(createMenuItem("Tumour", new SpectImageMeasureActionListener(bean, ImageSideEnum.Tumour, null)));

		return measureMenu;
	}

	public Menu buildPlannarImageMeasureMenuNew(MeasurementVO bean) {
		Menu measureMenu = new Menu("Analyse");

		Menu submenuAnt = new Menu("Anterior");
		Menu submenuPost = new Menu("Posterior");

		createSubmenuItemNew(bean, submenuAnt, ImageTypeEnum.Anterior);
		createSubmenuItemNew(bean, submenuPost, ImageTypeEnum.Posterior);

		measureMenu.add(submenuAnt);
		measureMenu.add(submenuPost);

		return measureMenu;
	}

	public Menu buildImageMeasureMenuNew(MeasurementVO bean) {
		Menu measureMenu = new Menu("Analyse");

		Menu submenuAnt = new Menu("Anterior");
		Menu submenuPost = new Menu("Posterior");

		createSubmenuItemNew(bean, submenuAnt, ImageTypeEnum.Anterior);
		createSubmenuItemNew(bean, submenuPost, ImageTypeEnum.Posterior);

		measureMenu.add(submenuAnt);
		measureMenu.add(submenuPost);

		return measureMenu;
	}

	private void createSubmenuItemNew(MeasurementVO bean, Menu measureMenu, ImageTypeEnum imageTypeEnum) {

		measureMenu.add(createMenuItem("Background",
				new ImageMeasureActionListener(bean, ImageSideEnum.Background, imageTypeEnum)));
		measureMenu
				.add(createMenuItem("Left", new ImageMeasureActionListener(bean, ImageSideEnum.Left, imageTypeEnum)));
		measureMenu
				.add(createMenuItem("Right", new ImageMeasureActionListener(bean, ImageSideEnum.Right, imageTypeEnum)));
		measureMenu.add(
				createMenuItem("Tumour", new ImageMeasureActionListener(bean, ImageSideEnum.Tumour, imageTypeEnum)));
	}

	public double getMeanCount(ImagePlus imagePlus) {
		IJ.run("Set Measurements...", "area integrated redirect=None decimal=3");

		Analyzer analyzer = new Analyzer(imagePlus);

		analyzer.measure();
		// analyzer.summarize();
		ResultsTable rt = Analyzer.getResultsTable();
		int count = rt.getCounter() - 1;
		if (count < 0)
			count = 0;
		double integrated = rt.getValueAsDouble(ResultsTable.INTEGRATED_DENSITY, count);

		MathContext mc = new MathContext(6, RoundingMode.HALF_UP);
		BigDecimal decimal = new BigDecimal(integrated, mc);
		return decimal.doubleValue();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void drawGraphNew(MeasurementVO measurementBean, LineChart<Number, Number> linechart) {
		linechart.setAnimated(false);
		linechart.getXAxis().setAutoRanging(true);
		linechart.getYAxis().setAutoRanging(true);

		linechart.getData().clear();

		XYChart.Series rightImageDataSet = getImageDataSetNew(measurementBean, "Right");
		XYChart.Series leftImageDataSet = getImageDataSetNew(measurementBean, "Left");
		XYChart.Series tumourImageDataSet = getImageDataSetNew(measurementBean, "Tumour");
		linechart.getData().addAll(rightImageDataSet, leftImageDataSet, tumourImageDataSet);
		linechart.setLegendVisible(true);
		linechart.setCreateSymbols(true);

		calculateDosageNew(measurementBean);
		System.out.println("Dosage " + measurementBean.getDosage());
		linechart.setTitle("Dosage is " + measurementBean.getDosage());

		linechart.setLegendSide(Side.RIGHT);

		String OS = System.getProperty("os.name").toLowerCase();
		System.out.println("OS" + OS);
		linechart.getStylesheets().clear();
		if (OS == "linux") {
			File f = new File("src/main/java/co/za/master/dose/frame/Login.css");
			linechart.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
		} else if (OS.contains("windows")) {
			File f = new File("src/main/java/co/za/master/dose/frame/Login.css");
			linechart.getStylesheets().add(f.getAbsolutePath());
		}
		
		measurementBean.setLinechart(linechart);
		MasterDoseCache.instance.getMeasurementVO().setLinechart(linechart);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private XYChart.Series getImageDataSetNew(MeasurementVO vo, String side) {
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();

		Map<String, Double> map = new TreeMap<String, Double>(vo.getSquareRoot());

		for (String key : map.keySet()) {
			if (key.contains(side)) {
				String imageNo = key.substring(0, key.indexOf("_"));
				Integer interval = vo.getIntervalMap().get(imageNo);
				series.getData().add(new XYChart.Data(interval + "", map.get(key)));
			}
		}

		series.setName(side + " Image");
		return series;
	}

	private double getSumGradient(List<Double> gradientList) {
		double sumGradient = 0;
		double S_VALUE = MasterDoseConstants.S_FACTOR;// 0.0029;
		for (double d : gradientList) {
			sumGradient += d;
		}
		System.out.println("sumGradient : " + sumGradient);
		sumGradient = (sumGradient) * S_VALUE;
		return sumGradient;
	}
	
	private double getSumGradient(List<Double> gradientList, MeasurementVO vo) {
		double sumGradient = 0;
		
		double S_VALUE = 0;// 0.0029;
		
		if("Male".equalsIgnoreCase(vo.getPatientDetails().getGender())) {
			S_VALUE = vo.getConfigData().getMaleSValue();
		} else if("Female".equalsIgnoreCase(vo.getPatientDetails().getGender())) { 
			S_VALUE = vo.getConfigData().getFemaleSValue();
		} 
		
		for (double d : gradientList) {
			sumGradient += d;
		}
		System.out.println("sumGradient : " + sumGradient);
		sumGradient = (sumGradient) * S_VALUE;
		return sumGradient;
	}

	private List<Double> getGradientList(int size, List<Integer> intervalList, List<Double> imageList) {
		List<Double> gradientList = new ArrayList<>();

		for (int i = size; i > 1; i--) {
			double g2 = imageList.get(i - 1);
			double g1 = imageList.get(i - 2);
			double t2 = intervalList.get(i - 1);
			double t1 = intervalList.get(i - 2);
			double gradientL1 = ((g2 + g1) / 2) * (t2 - t1);
			gradientList.add(gradientL1);
		}
		return gradientList;
	}

	private void calculateDosageNew(MeasurementVO vo) {

		Map<String, Double> sortedMap = new TreeMap<String, Double>(vo.getSquareRoot());

		List<Integer> intervalList = new ArrayList<>();
		List<Double> leftList = new ArrayList<>();
		List<Double> rightList = new ArrayList<>();
		List<Double> tumourList = new ArrayList<>();

		for (String key : sortedMap.keySet()) {
			String interval = key.substring(0, key.indexOf("_"));
			if (key.contains("Left")) {
				intervalList.add(vo.getIntervalMap().get(interval));
				leftList.add(sortedMap.get(key));
			} else if (key.contains("Right")) {
				rightList.add(sortedMap.get(key));
			} else if (key.contains("Tumour")) {
				tumourList.add(sortedMap.get(key));
			}
		}

		int size = leftList.size();
		List<Double> leftGradientList = getGradientList(size, intervalList, leftList);
		List<Double> rightGradientList = getGradientList(size, intervalList, rightList);
		List<Double> tumourGradientList = getGradientList(size, intervalList, tumourList);

		double sumLGradient = getSumGradient(leftGradientList, vo);
		System.out.println("sumLGradient : " + sumLGradient);

		double sumRGradient = getSumGradient(rightGradientList, vo);
		System.out.println("sumRGradient : " + sumRGradient);

		double sumTGradient = getSumGradient(tumourGradientList, vo);
		System.out.println("sumTGradient : " + sumTGradient);

		double totalD = sumLGradient + sumRGradient + sumTGradient;
		MathContext mc = new MathContext(4, RoundingMode.HALF_UP);
		BigDecimal dosage = new BigDecimal(totalD, mc);

		System.out.println("dosage : " + dosage);
		vo.setDosage(dosage.doubleValue());

	}

	public boolean isConfigValid(ConfigData configData) {

		if (configData.getSensitivity() == 0) {
			JOptionPane.showConfirmDialog(null, "Please capture sensitivity values", "", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (configData.getTransmissionCounts() == 0) {
			JOptionPane.showConfirmDialog(null, "Please capture transmission values", "", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (configData.getImageType() == null) {
			JOptionPane.showConfirmDialog(null, "Please select image type [PLAINER Or SPECT]", "",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
