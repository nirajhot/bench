package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import loginTC.validCredentials;
import utility.BaseClass;

public class TestListener implements ITestListener{

	final String baseDir = "./Screenshot/";
	String className;
	String[] stringarray;
	validCredentials vc;
	String testCase;

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			Object currentClass = result.getInstance();
			if (currentClass instanceof BaseClass) {
				WebDriver driver = ((BaseClass) currentClass).driver;
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
				System.out.println("Hiu");
				//vc = new validCredentials();
				//testCase = vc.className;
				testCase = result.getTestClass().getRealClass().getSimpleName();
				System.out.println("Hi  "+testCase);
				File DestFile=new File(System.getProperty("user.dir")+"/Screenshot"+"/"+testCase+"/"+testCase+"_"+timestamp+"_fail"+".png");
				System.out.println("Hi123  "+System.getProperty("user.dir")+"/Screenshot"+"/"+testCase+"/"+testCase+"_"+timestamp+"_fail"+".png");
				FileUtils.copyFile(srcFile, DestFile);
				Files.move(new File(System.getProperty("user.dir")+"/Screenshot"+"/"+testCase).toPath(),
						new File(System.getProperty("user.dir")+"/Screenshot"+"/"+"FAIL"+"/"+testCase).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			Error e1 = new Error(e.getMessage());
			e1.setStackTrace(e.getStackTrace());
			throw e1;
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		
	}
}