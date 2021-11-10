package utility;

import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{

	protected WebDriver driver;
	protected WebDriverWait wait;
	Actions a1;

	public BasePage(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		PageFactory.initElements(driver, this);
	}

	public void waitUntilElementVisible(WebElement element){
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilElementClickable(WebElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public boolean isElementDisplayed(WebElement element){
		try{
			element.isDisplayed();
			return true;
		}catch(NoSuchElementException e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean isTextPresent(String text){
		try{
			boolean b = driver.getPageSource().contains(text);
			return b;
		}
		catch(Exception e){
			return false;
		}
	}

	public void selectValue(WebElement element, String type , String value){

		Select select = new Select(element);
		switch (type){
		case "index":
			select.selectByIndex(Integer.parseInt(value));
			break;
		case "value":
			select.selectByValue(value);
			break;
		case "visibleText":
			select.selectByVisibleText(value);
			break;
		default :
			System.out.println("Please pass proper value");
			break;
		}
	}

	public void mouseHover(WebElement element){
		a1= new Actions(driver);
		a1.moveToElement(element).build().perform();
	}
}