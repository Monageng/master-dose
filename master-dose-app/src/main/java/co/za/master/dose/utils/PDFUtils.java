package co.za.master.dose.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

public class PDFUtils {
	public static PDFUtils instance = new PDFUtils();
	public PdfPCell addFieldCell(String value) {
		PdfPCell pdfCell = new PdfPCell();
		Phrase phrase = new Phrase(value);

		Font f =  FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL);
		phrase.setFont(f);
		pdfCell.addElement(phrase);
		pdfCell.setBackgroundColor(BaseColor.LIGHT_GRAY);

		return pdfCell;
	}

	public PdfPCell addValueCell(String value) {
		PdfPCell pdfCell = new PdfPCell();
		Phrase phrase = new Phrase(value);
		Font f =  FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL);
		phrase.setFont(f);
		pdfCell.addElement(phrase);
		pdfCell.setBackgroundColor(new BaseColor(443344));
		return pdfCell;
	}

}
