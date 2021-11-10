package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.homePage;
import setEnum.common;
import utility.BaseClass;
import utility.BasePage;
import utility.Config;

public class orderWithRegUser extends BaseClass{

	homePage hp;
	BasePage bp;
	Actions a1;
	private static Config rc = new Config();
	private static String LPT = rc.getLPT();
	private static String CPT = rc.getCPT();
	private static String CheckPT = rc.getCheckOutPT();
	private static String SuccessPT = rc.getSuccessPT();
	private static String EMAIL = rc.getEmail();
	private static String PASS = rc.getPass();

	@DataProvider(name = "test-data")
	public Object[][] dataProvFunc(){
		return new Object[][]{
			{"glasses"}
		};
	}

	@DataProvider(name = "select-test-data")
	public Object[][] dataProvSelect(){
		return new Object[][]{
			{"Alcogel Clean Machines"}
		};
	}

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
		bp.waitUntilElementClickable(hp.banner());
	}

	@Test(priority=1,dataProvider ="test-data")
	public void searchProduct(String keyWord){
		takeScreen(this.getClass().getSimpleName());
		try {
			hp.searchTextBar().clear();
			hp.searchTextBar().sendKeys(keyWord);
			waitFor(4000);
			hp.searchTextBar().sendKeys(Keys.ENTER);
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			hp.searchTextBar().clear();
			hp.searchTextBar().sendKeys(keyWord);
			waitFor(4000);
			hp.searchTextBar().sendKeys(Keys.ENTER);
		}
		bp.waitUntilElementClickable(hp.productGridWrapper());
		takeScreen(this.getClass().getSimpleName());
	}

	@Test(priority=2,dataProvider ="select-test-data")
	public void clickItemAddCart(String keyWord){
		WebElement e1;
		try{
			e1 = driver.findElement(By.cssSelector("[alt='"+keyWord+"']"));
			e1.click();
		}catch(Exception e){
			 js = (JavascriptExecutor)driver;
			e1 = driver.findElement(By.cssSelector("[alt='"+keyWord+"']"));
			js.executeScript("arguments[0].click();", e1);
		}
		takeScreen(this.getClass().getSimpleName());
	}

	@Test(priority=3,dataProvider ="select-test-data")
	public void processCheckOut(String keyWord){
		String title = keyWord+" | BENCH/ Online Store";
		Assert.assertTrue(title.equalsIgnoreCase(driver.getTitle()));
		if(hp.customerLoginText().getText().equals(keyWord)){
			try{
				waitFor(2000);
				bp.selectValue(hp.selectColor(), common.VISIBLETEXT.toString(), "ASH GRAY");
				waitFor(2000);
				if(hp.selectSize().isEnabled()){
					waitFor(2000);
					js.executeScript("arguments[0].scrollIntoView();", hp.selectSize());
					bp.selectValue(hp.selectSize(), common.VISIBLETEXT.toString(), "L");
					waitFor(2000);
				}
				hp.setQTY().click();
				hp.setQTY().clear();
				hp.setQTY().sendKeys("1");
			}catch(Exception e){
				hp.setQTY().click();
				hp.setQTY().clear();
				hp.setQTY().sendKeys("1");
			}
			takeScreen(this.getClass().getSimpleName());
			hp.cartButton().click();
			takeScreen(this.getClass().getSimpleName());
			try{
				bp.waitUntilElementClickable(hp.getSuccessMsg());
			}catch(Exception e){
				bp.waitUntilElementClickable(hp.getSuccessError());
			}
			bp.waitUntilElementClickable(hp.getCartDetail());
			hp.getCartDetail().click();
			takeScreen(this.getClass().getSimpleName());
			bp.waitUntilElementClickable(hp.getMiniCartDetail());
			if(!hp.checkOut().isDisplayed()){
				throw new SkipException("Skipping this exception");
			}else{
				waitFor(2000);
				hp.checkOut().click();
				Assert.assertTrue(CheckPT.equalsIgnoreCase(driver.getTitle()));
			}
			takeScreen(this.getClass().getSimpleName());
		}
	}

	@Test(priority=4)
	public void payment(){
		bp.waitUntilElementClickable(hp.paymentMethod());
		bp.waitUntilElementClickable(hp.getOrderSummary());
		hp.COD().click();
		bp.waitUntilElementClickable(hp.address());
		waitFor(5000);
		hp.streetAddress().click();
		hp.streetAddress().sendKeys("ABC");
		hp.city().click();
		hp.city().sendKeys("ABC");
		bp.selectValue(hp.getState(), common.INDEX.toString(), "Alabama");
		hp.ZIP().click();
		hp.ZIP().sendKeys("12345");
		hp.CONTACT().click();
		hp.CONTACT().sendKeys("3210456987");
		takeScreen(this.getClass().getSimpleName());
		hp.getUpdate().click();
		boolean status = hp.placeOrder().isEnabled();
		if(status){
			hp.placeOrder().click();
		}
	}

	@Test(priority=5)
	public void successMsg(){
		Assert.assertTrue(SuccessPT.equalsIgnoreCase(driver.getTitle()));
		waitFor(1000);
	}
}