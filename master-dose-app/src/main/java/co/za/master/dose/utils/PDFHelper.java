package co.za.master.dose.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

import co.za.master.dose.model.MeasurementVO;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFHelper {
	
	public static String FILE_NAME = "Report";
	public static String IMAGE_NAME = "Image.png";
	public static void createPDFDynamic(MeasurementVO measurementVO, LineChart<Number, Number> linechart) {
		try {

			com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 20, 20, 50, 50);
			
			String fileName =ImageHelper.instance.getFileName(FILE_NAME + (new Date()).getTime() + ".pdf");
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();

			// ADD Title
			Paragraph title1 = new Paragraph("Medical Scan Report",FontFactory.getFont(FontFactory.TIMES_ROMAN,16, Font.BOLD,  new CMYKColor(0, 50, 255,17)));
			title1.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(title1);
			
			// ADD Paragraph
			Paragraph report = new Paragraph();
			String introContent = "This the report for the Altra scan, The aim of this process is to upload the scan then measure the Regision interest. The results will be "
					+ "used to create th graph and determine the dosage of XYZ chemotheraphy and Dignostic medication";
			Chunk chunk = new Chunk(introContent);
			chunk.setLineHeight(2);
			chunk.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN,	12, Font.NORMAL));
			Paragraph intro = new Paragraph(chunk );
			document.add(intro);
			
			
			// ADD Table with patient details
			PdfPTable t = new PdfPTable(new float[] {40f,60f});
			t.setSpacingBefore(25);
			t.setSpacingAfter(25);
			t.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			t.setWidthPercentage(60f);
			
			t.addCell(PDFUtils.instance.addFieldCell("Title"));
			t.addCell(PDFUtils.instance.addValueCell(measurementVO.getPatientDetails().getTitle()));
			t.addCell(PDFUtils.instance.addFieldCell("Initials"));
			t.addCell(PDFUtils.instance.addValueCell(measurementVO.getPatientDetails().getInitials()));
			t.addCell(PDFUtils.instance.addFieldCell("Name"));
			t.addCell(PDFUtils.instance.addValueCell(measurementVO.getPatientDetails().getFirstName()));
			t.addCell(PDFUtils.instance.addFieldCell("Surname"));
			t.addCell(PDFUtils.instance.addValueCell(measurementVO.getPatientDetails().getSurname()));
			t.addCell(PDFUtils.instance.addFieldCell("Patient ID"));
			t.addCell(PDFUtils.instance.addValueCell(measurementVO.getPatientDetails().getPatientId()));
			report.add(t);
			document.add(report);	
			
			CategoryAxis xAxis = new CategoryAxis();
			NumberAxis yAxis = new NumberAxis();
			LineChart<Number, Number> lChat = new LineChart(xAxis, yAxis);
			ImageHelper.instance.drawGraphNew(measurementVO, lChat);
			
			lChat.setPrefSize(500, 400);
			Scene scene = new Scene(lChat);
			WritableImage writableImage = scene.snapshot(null);
			ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", new File(IMAGE_NAME));
			
			Image image = Image.getInstance(IMAGE_NAME);
            document.add(image);
            document.close();
            
            FileUtils.instance.openFile(fileName);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
