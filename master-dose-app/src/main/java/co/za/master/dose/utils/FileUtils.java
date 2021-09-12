package co.za.master.dose.utils;

import java.awt.Desktop;
import java.io.File;

public class FileUtils {
	public static FileUtils instance = new FileUtils();

	public static void main(String[] args) {
		instance.openFile("/home/adminuser/git/master-dose/master-dose-app/exports/Report.pdf1561222159106");
	}
	public void openFile(String fileName) {

		try {
			Desktop desktop = Desktop.getDesktop();
			if (Desktop.isDesktopSupported() ) {
				File file = new File(fileName);
				desktop.open(file);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public File createDirectory(String dirName) throws Exception {
		try {
			File dir = new File(dirName);
			if (!dir.exists()) {
				boolean success = dir.mkdirs();
				if (!success) {
					throw new RuntimeException("Directory not created");
				}
			}

			return dir;
		} catch (Exception e) {
			throw e;
		}
	}

	public File createFile(String dirName, String fileName) {
		File file = null;
		try {
			File dir = new File(dirName);

			if (!dir.exists()) {
				dir.mkdirs();
			} else {
				file = new File(dir.getPath() + "/" + fileName);
				if (!file.exists()) {
					System.out.println("File does not exists");
					file.createNewFile();
				}

			}

			System.out.println("File exists : " + file.getPath());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public void removeDirectory(String dirName) {
		try {
			File dir = new File(dirName);
			if (!dir.exists()) {
				throw new RuntimeException("Directory does not exists");
			} else {
				dir.delete();
			}
		} catch (Exception e) {
			// throw e;
		}
	}
}
