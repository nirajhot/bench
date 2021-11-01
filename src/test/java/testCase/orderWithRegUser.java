package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
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
			{"Test"}
		};
	}

	@DataProvider(name = "select-test-data")
	public Object[][] dataProvSelect(){
		return new Object[][]{
			{"Test Product"}
		};
	}

	@Test(priority=0)
	public void userLogin(){
		hp=  new homePage(driver);
		bp = new BasePage(driver);
		a1= new Actions(driver);
		Assert.assertTrue(LPT.equalsIgnoreCase(driver.getTitle()));
		bp.waitUntilElementClickable(hp.banner());
		hp.welcomeIcon().click();
		bp.waitUntilElementClickable(hp.getHeaderLinks());
		hp.signIn().click();
		Assert.assertTrue(CPT.equalsIgnoreCase(driver.getTitle()));
		hp.emailText().sendKeys(EMAIL);
		hp.passText().sendKeys(PASS);
		hp.signButton().click();
		bp.waitUntilElementClickable(hp.banner());
	}

	@Test(priority=1,dataProvider ="test-data")
	public void searchProduct(String keyWord){
		hp.searchTextBar().sendKeys(keyWord);
		waitFor(4000);
		a1.moveToElement(hp.getFirst()).build().perform();
		hp.getFirst().click();
		bp.waitUntilElementClickable(hp.productGridWrapper());
	}

	@Test(priority=2,dataProvider ="select-test-data")
	public void clickItemAddCart(String keyWord){
		for(int i=0;i<hp.getAllItems().size();i++){
			WebElement selectPrice =hp.getAllItems().get(i);
			try{
				if(selectPrice.getText().contains(keyWord)){
					a1.moveToElement(driver.findElement(By.cssSelector("[alt='"+keyWord+"']")))
					.build().perform();
					waitFor(2000);
					driver.findElement(By.cssSelector("[data-product-sku='"+keyWord+"']"+" .tocart.primary")).click();;
					bp.waitUntilElementClickable(hp.getMagnifier());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Test(priority=3,dataProvider ="select-test-data")
	public void processCheckOut(String keyWord){
		Assert.assertTrue("Test Product | BENCH/ Online Store".equalsIgnoreCase(driver.getTitle()));
		if(hp.customerLoginText().getText().equals(keyWord)){
			hp.selectWhite().click();
			hp.setQTY().click();
			hp.setQTY().clear();
			hp.setQTY().sendKeys("2");
			hp.cartButton().click();
			//Assert.assertEquals(hp.shoppingCart().getText(), "shopping cart");
			bp.waitUntilElementClickable(hp.getCartDetail());
			hp.getCartDetail().click();
			bp.waitUntilElementClickable(hp.getMiniCartDetail());
			hp.checkOut().click();
			Assert.assertTrue(CheckPT.equalsIgnoreCase(driver.getTitle()));
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
		/*Select drpCountry = new Select(hp.getState());
		drpCountry.selectByVisibleText("Alabama");*/
		hp.ZIP().click();
		hp.ZIP().sendKeys("12345");
		hp.CONTACT().click();
		hp.CONTACT().sendKeys("3210456987");
		hp.getUpdate().click();
		boolean status = hp.placeOrder().isEnabled();
		if(status){
			hp.placeOrder().click();
		}
	}

	@Test(priority=5)
	public void successMsg(){
		Assert.assertTrue(SuccessPT.equalsIgnoreCase(driver.getTitle()));
		//Assert.assertEquals(hp.customerLoginText().getText(), "Thank you for your purchase!");
		waitFor(10000);
	}

}