package extent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utility.WebUtil;

/**
 * Extent Manager class to create extent report instance
 */
public class ExtentManager {
	private static ExtentReports extent;
	
	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			Path dirPath = Paths.get(System.getProperty("user.dir"), "extentReport");
			Path filePath = dirPath.resolve("ExtentReport_" + WebUtil.getTimeStamp() + ".html");
			File extFile;
			
			try {
				File dir = new File(dirPath.toString());
				if (! dir.exists()) {
					Files.createDirectories(dirPath);
				}
				extFile = new File(filePath.toString());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
			ExtentHtmlReporter html = new ExtentHtmlReporter(extFile);
			html.config().setDocumentTitle("");
			html.config().setTheme(Theme.DARK);
			html.setAppendExisting(false);
			
			extent = new ExtentReports();
			extent.attachReporter(html);
		}
		return extent;
	}

}
