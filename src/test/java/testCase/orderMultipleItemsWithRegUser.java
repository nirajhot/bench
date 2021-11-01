package testCase;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import excelUtils.readData;
import pageObject.homePage;
import setEnum.common;
import utility.BaseClass;
import utility.BasePage;
import utility.Config;

public class orderMultipleItemsWithRegUser extends BaseClass{

	homePage hp;
	BasePage bp;
	readData rd;

	private static Config rc = new Config();
	private static String LPT = rc.getLPT();
	private static String CPT = rc.getCPT();
	private static String CheckPT = rc.getCheckOutPT();
	private static String SuccessPT = rc.getSuccessPT();
	private static String EMAIL = rc.getEmail();
	private static String PASS = rc.getPass();

	@BeforeTest
	public void getData() throws IOException{
		rd=  new readData();
		rd.setExcelFile("TWO");
	}

	@Test(priority=0)
	public void userLogin(){
		hp=  new homePage(driver);
		bp = new BasePage(driver);
		Assert.assertTrue(LPT.equalsIgnoreCase(driver.getTitle()));
		bp.waitUntilElementClickable(hp.banner());
		hp.welcomeIcon().click();
		bp.waitUntilElementClickable(hp.getHeaderLinks());
		bp.mouseHover(hp.signIn());
		hp.signIn().click();
		Assert.assertTrue(CPT.equalsIgnoreCase(driver.getTitle()));
		hp.emailText().sendKeys(EMAIL);
		hp.passText().sendKeys(PASS);
		hp.signButton().click();
		bp.waitUntilElementClickable(hp.banner());
	}

	@Test(priority=1)
	public void searchProduct(){
		for(int i=0;i<=rd.getRowCountInSheet();i++)
		{
			try {
				hp.searchTextBar().clear();
				hp.searchTextBar().sendKeys(rd.getCellData(i, 0));
				waitFor(4000);
				hp.searchTextBar().sendKeys(Keys.ENTER);
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex)
			{
				hp.searchTextBar().clear();
				hp.searchTextBar().sendKeys(rd.getCellData(i, 0));
				waitFor(4000);
				hp.searchTextBar().sendKeys(Keys.ENTER);
			}
			bp.waitUntilElementClickable(hp.productGridWrapper());
			WebElement e1;
			try{
				e1 = driver.findElement(By.cssSelector("[alt='"+rd.getCellData(i, 1)+"']"));
				e1.click();
			}catch(Exception e){
				JavascriptExecutor js = (JavascriptExecutor)driver;
				e1 = driver.findElement(By.cssSelector("[alt='"+rd.getCellData(i, 1)+"']"));
				js.executeScript("arguments[0].click();", e1);
			}
			String title = rd.getCellData(i, 1)+" | BENCH/ Online Store";
			Assert.assertTrue(title.equalsIgnoreCase(driver.getTitle()));

			if(hp.customerLoginText().getText().equals(rd.getCellData(i, 1))){
				try{
					waitFor(2000);
					bp.selectValue(hp.selectColor(), common.VISIBLETEXT.toString(), rd.getCellData(i, 2));
					waitFor(2000);
					if(hp.selectSize().isEnabled()){
						waitFor(2000);
						bp.selectValue(hp.selectSize(), common.VISIBLETEXT.toString(), rd.getCellData(i, 3));
						waitFor(2000);
					}
					hp.setQTY().click();
					hp.setQTY().clear();
					hp.setQTY().sendKeys(rd.getCellData(i, 4));
				}catch(Exception e){
					hp.setQTY().click();
					hp.setQTY().clear();
					hp.setQTY().sendKeys((rd.getCellData(i, 4)));
				}
				hp.cartButton().click();
				bp.waitUntilElementClickable(hp.getSuccessMsg());
			}else{
				System.out.println("Selected item is not same for checkout");
			}
		}
	}
	
	@Test(priority=2)
	public void checkOut(){
		bp.waitUntilElementClickable(hp.getCartDetail());
		hp.getCartDetail().click();
		bp.waitUntilElementClickable(hp.getMiniCartDetail());
		hp.checkOut().click();
		Assert.assertTrue(CheckPT.equalsIgnoreCase(driver.getTitle()));
	}
}