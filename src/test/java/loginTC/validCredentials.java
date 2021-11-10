package loginTC;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.homePage;
import utility.BaseClass;
import utility.BasePage;
import utility.Config;

public class validCredentials extends BaseClass{

	homePage hp;
	BasePage bp;
	Actions a1;
	private static Config rc = new Config();
	private static String LPT = rc.getLPT();
	private static String CPT = rc.getCPT();
	private static String EMAIL = rc.getEmail();
	private static String PASS = rc.getPass();
	public static String className;

	@Test(priority=0)
	public void userLogin(){
		hp=  new homePage(driver);
		bp = new BasePage(driver);
		a1= new Actions(driver);
		takeScreen(this.getClass().getSimpleName());
		Assert.assertTrue(LPT.equalsIgnoreCase(driver.getTitle()));
		bp.waitUntilElementClickable(hp.banner());
		hp.welcomeIcon().click();
		bp.waitUntilElementClickable(hp.getHeaderLinks());
		bp.mouseHover(hp.signIn());
		hp.signIn().click();
		bp.waitUntilElementClickable(hp.getCustomerLoginArea());
		Assert.assertTrue(CPT.equalsIgnoreCase(driver.getTitle()));
		hp.emailText().sendKeys(EMAIL);
		hp.passText().sendKeys(PASS);
		takeScreen(this.getClass().getSimpleName());
		hp.signButton().click();
		try{
			bp.waitUntilElementClickable(hp.banner());
		}catch(Exception e){
			bp.waitUntilElementClickable(hp.getSuccessError());
			System.out.println("Please enter valid credentials" + e.getMessage());
		}
	}
}