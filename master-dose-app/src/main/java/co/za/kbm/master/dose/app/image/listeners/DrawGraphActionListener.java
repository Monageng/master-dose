package co.za.kbm.master.dose.app.image.listeners;

import co.za.kbm.master.dose.app.model.MeasurementVO;
import co.za.kbm.master.dose.app.utils.ImageHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class DrawGraphActionListener implements ActionListener {

	private MeasurementVO bean;
	
	public DrawGraphActionListener() {
		
	}
	
	public DrawGraphActionListener(MeasurementVO bean) {
		this.bean = bean;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			bean = ImageHelper.instance.populateMeasurementBean(bean);
			bean = ImageHelper.instance.calculateMeanSquareRoot(bean);
//			ImageHelper.instance.drawGraphNew(measurementBean, linechart);Graph(bean);
		} catch (Exception ex){
			JOptionPane.showConfirmDialog(null, "Please take measurements for all images", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}

}