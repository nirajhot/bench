package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.homePage;
import setEnum.common;
import utility.BaseClass;
import utility.BasePage;
import utility.Config;

public class orderWithGuestUser extends BaseClass{

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

	@DataProvider(name = "test-test-data")
	public Object[][] dataProvFuncTest(){
		return new Object[][]{
			{"Alcogel Clean Machines","CPW1020A"}
		};
	}

	@DataProvider(name = "select-test-data")
	public Object[][] dataProvSelect(){
		return new Object[][]{
			{"bovecay785@datakop.com", "Manoj", "Sharma"}
		};
	}

	@Test(priority=0,dataProvider ="test-data")
	public void searchProduct(String keyWord){
		hp=  new homePage(driver);
		bp = new BasePage(driver);
		a1= new Actions(driver);
		Assert.assertTrue(LPT.equalsIgnoreCase(driver.getTitle()));
		bp.waitUntilElementClickable(hp.banner());
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
		/*hp.searchTextBar().sendKeys(keyWord);
		waitFor(4000);
		a1.moveToElement(hp.getFirst()).build().perform();
		hp.getFirst().click();*/
		bp.waitUntilElementClickable(hp.productGridWrapper());
	}

	@Test(priority=1,dataProvider ="test-test-data")
	public void clickItemAddCart(String keyWord, String SKU){
		for(int i=0;i<hp.getAllItems().size();i++){
			WebElement selectPrice =hp.getAllItems().get(i);
			try{
				if(selectPrice.getText().contains(keyWord)){
					a1.moveToElement(driver.findElement(By.cssSelector("[alt='"+keyWord+"']")))
					.build().perform();
					waitFor(2000);
					driver.findElement(By.cssSelector("[data-product-sku='"+SKU+"']"+" .tocart.primary")).click();;
					bp.waitUntilElementClickable(hp.getMagnifier());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Test(priority=2,dataProvider ="test-test-data")
	public void processCheckOut(String keyWord, String SKU){
		String title = keyWord+" | BENCH/ Online Store";
		Assert.assertTrue(title.equalsIgnoreCase(driver.getTitle()));
		if(hp.customerLoginText().getText().equals(keyWord)){
			hp.selectWhite().click();
			hp.setQTY().click();
			hp.setQTY().clear();
			hp.setQTY().sendKeys("2");
			hp.cartButton().click();
			waitFor(5000);
			try{
				String getText = hp.cartAddedText().getText();
				if(getText.equals("shopping cart")){
					bp.waitUntilElementClickable(hp.getCartDetail());
					hp.getCartDetail().click();
					bp.waitUntilElementClickable(hp.getMiniCartDetail());
					hp.checkOut().click();
					Assert.assertTrue(CheckPT.equalsIgnoreCase(driver.getTitle()));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Test(priority=3,dataProvider ="select-test-data")
	public void accountCreation(String email, String name, String surname){
		bp.waitUntilElementClickable(hp.shippingDIV());
		bp.waitUntilElementClickable(hp.getOrderSummary());
		waitFor(5000);
		hp.email().click();
		hp.email().sendKeys(email);
		hp.name().click();
		hp.name().sendKeys(name);
		hp.surname().click();
		hp.surname().sendKeys(surname);
		hp.streetAddress().click();
		hp.streetAddress().sendKeys("ABC");
		hp.city().click();
		hp.city().sendKeys("ABC");
		Select drpCountry = new Select(hp.getState());
		drpCountry.selectByVisibleText("Alabama");
		hp.ZIP().click();
		hp.ZIP().sendKeys("12345");
		hp.CONTACT().click();
		hp.CONTACT().sendKeys("3210456987");
		hp.shiping().click();
		hp.getNEXT().click();
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