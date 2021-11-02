package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends Initializer{

	protected WebDriver driver;
	private WebDriverManager manager;
	private static Config rc = new Config();
	private static String URL = rc.getURL();
	protected static final Logger logger = LogManager.getLogger(BaseClass.class.getName());

	@SuppressWarnings("static-access")
	@Parameters({"browser"})
	@BeforeClass
	@Override
	public void browserSetUp(@Optional("Chrome")String browser) {
		DOMConfigurator.configure("log4j.xml");
		if(browser.equalsIgnoreCase("Chrome")){
			manager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			manager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		logger.info(browser+" browser started");
		driver.manage().window().maximize();
		driver.get(URL);
		logger.info("Application launched");
		waitFor(2000);
		if(driver.findElement(By.cssSelector(".action-close")).isDisplayed()){
			driver.findElement(By.cssSelector(".action-close")).click();
		}else{
			waitFor(2000);
		}
	}

	@AfterClass
	@Override
	public void quitBrowser() {
		logger.info("Closing the browser");
		driver.close();
	}

	public void takeScreen(){
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		File DestFile=new File("./Screenshot/"+timestamp+".png");
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*@AfterSuite
	public void deleteFolder(){
		File  checkFile=new File(System.getProperty("user.dir")+"/Screenshot");
		if(checkFile.exists()){
			try {
				FileUtils.copyDirectory(checkFile, new File(System.getProperty("user.dir")+"/ScreenshotBackup"));
				FileUtils.deleteDirectory(checkFile);
				checkFile.mkdir();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}	
	}*/
}