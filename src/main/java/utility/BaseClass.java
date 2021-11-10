package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import loginTC.validCredentials;

public class BaseClass extends Initializer{

	public WebDriver driver;
	private WebDriverManager manager;
	private static Config rc = new Config();
	private static String URL = rc.getURL();
	protected static final Logger logger = LogManager.getLogger(BaseClass.class.getName());
	protected JavascriptExecutor js;

	File checkFile;
	File passFile;
	File failFile;
	File testCaseFolder;
	File DestFile;
	String pass;
	String fail;
	
	@BeforeSuite
	public void createFolder()
	{
		checkFile=new File(System.getProperty("user.dir")+"/Screenshot");
		pass = "PASS";
		fail = "FAIL";
		if(checkFile.exists()){
			passFile = new File(checkFile+"/"+pass);
			failFile = new File(checkFile+"/"+fail);
			if(!passFile.exists() || !failFile.exists()){
				passFile.mkdir();
				failFile.mkdir();
			}else{
				try {
					FileUtils.deleteDirectory(passFile);
					FileUtils.deleteDirectory(failFile);
					passFile.mkdir();
					failFile.mkdir();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}else{
			checkFile.mkdir();
			if(checkFile.exists()){
				passFile = new File(checkFile+"/"+pass);
				failFile = new File(checkFile+"/"+fail);
				if(!passFile.exists() || !failFile.exists()){
					passFile.mkdir();
					failFile.mkdir();
				}else{
					try {
						FileUtils.deleteDirectory(passFile);
						FileUtils.deleteDirectory(failFile);
						passFile.mkdir();
						failFile.mkdir();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	@Parameters({"browser"})
	@BeforeClass
	@Override
	public void browserSetUp(@Optional("Chrome")String browser) {
		DOMConfigurator.configure("log4j.xml");
		if(browser.equalsIgnoreCase("Chrome")){
			manager.chromedriver().setup();
			logger.info("Launching "+browser+" browser");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			manager.firefoxdriver().setup();
			logger.info("Launching "+browser+" browser");
			driver = new FirefoxDriver();
		}
		logger.info(browser+" browser started");
		driver.manage().window().maximize();
		driver.get(URL);
		logger.info("Application launched");
		if(driver.findElement(By.cssSelector(".action-close")).isDisplayed()){
			logger.info("Clicking close icon");
			driver.findElement(By.cssSelector(".action-close")).click();
			logger.info("Clicked close icon");
		}else{
			waitFor(2000);
		}
	}

	@AfterClass
	@Override
	public void quitBrowser() {
		String testCase;
		testCase = this.getClass().getSimpleName();
		try {
			File testCasePath =  new File(System.getProperty("user.dir")+"/Screenshot"+"/"+testCase);
			if(testCasePath.exists()){
				Files.move(testCasePath.toPath(), 
						new File(System.getProperty("user.dir")+"/Screenshot"+"/"+"PASS"+"/"+testCase).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			}else{
				waitFor(2000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Closing the browser");
		driver.close();
	}

	public void takeScreen(String testCase){
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		checkFile=new File(System.getProperty("user.dir")+"/Screenshot");
		if(checkFile.exists()){
			testCaseFolder = new File(checkFile+"/"+testCase);
			if(!testCaseFolder.exists()){
				testCaseFolder.mkdir();
				DestFile=new File(testCaseFolder.toString()+"/"+testCase+"_"+timestamp+".png");
				System.out.println(DestFile.toString());
				try {
					FileUtils.copyFile(srcFile, DestFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					DestFile=new File(testCaseFolder.toString()+"/"+testCase+"_"+timestamp+".png");
					FileUtils.copyFile(srcFile, DestFile);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
	}
	
	@AfterSuite
	public void copyToBackUp(){
		File  checkFile=new File(System.getProperty("user.dir")+"/Screenshot");
		try {
			String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
			Files.move(new File(System.getProperty("user.dir")+"/Screenshot").toPath(), new File(System.getProperty("user.dir")+"/ScreenshotBackup"+"/"+timestamp).toPath(), StandardCopyOption.REPLACE_EXISTING);
			FileUtils.deleteDirectory(checkFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}