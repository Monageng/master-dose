package co.za.master.dose.image.listeners;

import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import co.za.master.dose.model.PointBean;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.PolygonRoi;
import ij.gui.Roi;

public class MouseListener implements java.awt.event.MouseListener {
	static List<PointBean> list = new ArrayList<PointBean>();
	static boolean isDone =false;

	private ImagePlus imagePlus;
	private int roiType;
	
	public MouseListener(ImagePlus imagePlus, int roiType) {
		this.imagePlus = imagePlus;
		this.roiType = roiType;
		list.clear();
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		PointBean bean = new PointBean(e.getX(), e.getY());

		list.add(bean);

		Polygon poly = new Polygon();
		for (PointBean b : list) {
//					if (list.size() > 1 && b.getX() == e.getX() && b.getY() == e.getY()) {
//						isDone = true;
//					}
			poly.addPoint(b.getX(), b.getY());
		}
		if (!isDone) {
			if (roiType == Roi.POLYGON) {
				PolygonRoi polygonRoi = new PolygonRoi(poly, Roi.POLYGON);
				imagePlus.setRoi(polygonRoi);
				imagePlus.updateAndDraw();
				imagePlus.updateImage();
				IJ.log("mouseReleased 1" + imagePlus.getRoi());
			}
			
		} else {
			IJ.log("isDone 1" + true);
		}

	}

}
