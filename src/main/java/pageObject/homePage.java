package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.BasePage;

public class homePage extends BasePage{

	public homePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="maincontent")
	private WebElement bannerImage;

	@FindBy(css=".slider-title")
	private WebElement sliderTitle;

	@FindBy(id="search")
	private WebElement searchText;

	@FindBy(css=".welcome")
	private WebElement welcomeIcon;

	@FindBy(css=".authorization-link a")
	private WebElement signIn;

	@FindBy(css=".base")
	private WebElement customerLoginText;

	@FindBy(id="email")
	private WebElement emailText;

	@FindBy(id="pass")
	private WebElement passText;

	@FindBy(id="send2")
	private WebElement signButton;

	@FindBy(css=".page-header .header.content .header-right-section .md-header-links .header.links")
	private WebElement headerLinks;

	@FindBy(css=".action.search")
	private WebElement searchIcon;

	@FindBy(css=".products.wrapper.grid.products-grid")
	private WebElement productGrid;

	@FindBy(css="[data-ui-id='message-success']")
	private WebElement successMsg;
	
	@FindBy(css="[.page.messages .messages [data-ui-id='message-success'] div")
	private WebElement successCartMsg;
	
	@FindBy(css="[data-ui-id='message-error']")
	private WebElement successError;
	
	@FindBy(css=".product-item-link")
	public List<WebElement> allItems;

	@FindBy(id="qs-option-0")
	private WebElement first;

	@FindBy(css="#magnifier-item-0")
	public WebElement magnifier;

	@FindBy(css="[data-option-label='WHITE']")
	private WebElement whiteColor;		

	@FindBy(id="qty")
	private WebElement qtyText;
	
	@FindBy(id="product-addtocart-button")
	private WebElement addCart;
	
	@FindBy(css="#maincontent > div.page.messages > div:nth-child(2) > div > div > div > a")
	private WebElement shoppingCartText;
	
	@FindBy(css=".action.showcart")
	private WebElement cartDetail;
	
	@FindBy(css=".block-minicart")
	private WebElement miniCartDetail;
	
	@FindBy(id="top-cart-btn-checkout")
	private WebElement checkOutButton;
	
	@FindBy(css=".payment-group")
	private WebElement paymentMethod;
	
	@FindBy(id="opc-sidebar")
	private WebElement orderSummary;
	
	@FindBy(id="cashondelivery")
	private WebElement COD;
	
	@FindBy(css=".payment-method-billing-address")
	private WebElement checkOutAddress;
	
	@FindBy(id="IXVO7Y2")
	private WebElement checkOutStreetAddress;
	
	@FindBy(id="U2NCGNM")
	private WebElement checkOutCity;
	
	@FindBy(id="EJSKGXO")
	private WebElement stateSelectDropDown;
	
	@FindBy(id="attribute93")
	private WebElement selectProductColor;
	
	@FindBy(id="attribute212")
	private WebElement selectProductSize;
	
	@FindBy(css="[value='555']")
	private WebElement selectAmsterdom;
	
	@FindBy(id="DV5S7G2")
	private WebElement zipCode;
	
	@FindBy(id="CT1FE3G")
	private WebElement contact;
	
	@FindBy(css=".action.action-update")
	private WebElement update;
	
	@FindBy(css="#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content > div.actions-toolbar > div > button")
	private WebElement placeorder;
	
	@FindBy(id="customer-email")
	private WebElement customerEMAIL;
	
	@FindBy(id="S0UYSFS")
	private WebElement customerNAME;
	
	@FindBy(id="HXO34TQ")
	private WebElement customerSURNAME;
	
	@FindBy(id="s_method_freeshipping_freeshipping")
	private WebElement freeShiping;
	
	@FindBy(css=".action.continue")
	private WebElement next;
	
	@FindBy(css="#maincontent > div.page.messages > div:nth-child(2) > div > div > div > a")
	private WebElement cartAddedText;
	
	@FindBy(id="shipping")
	private WebElement shippingDiv;
	
	public List<WebElement> getAllItems() {
		return allItems;
	}

	public WebElement getFirst() {
		return first;
	}

	public WebElement welcomeIcon() {
		return welcomeIcon;
	}

	public WebElement signIn() {
		return signIn;
	}

	public WebElement customerLoginText() {
		return customerLoginText;
	}

	public WebElement emailText() {
		return emailText;
	}

	public WebElement passText() {
		return passText;
	}

	public WebElement signButton() {
		return signButton;
	}

	public WebElement banner() {
		return bannerImage;
	}

	public WebElement titleSlider() {
		return sliderTitle;
	}

	public WebElement searchTextBar() {
		return searchText;
	}

	public WebElement getHeaderLinks() {
		return headerLinks;
	}

	public WebElement iconSearch() {
		return searchIcon;
	}

	public WebElement productGridWrapper() {
		return productGrid;
	}

	public WebElement getMagnifier() {
		return magnifier;
	}
	
	public WebElement selectWhite() {
		return whiteColor;
	}
	
	public WebElement setQTY() {
		return qtyText;
	}
	
	public WebElement cartButton() {
		return addCart;
	}
	
	public WebElement shoppingCart() {
		return shoppingCartText;
	}
	
	public WebElement getCartDetail() {
		return cartDetail;
	}
	
	public WebElement getMiniCartDetail() {
		return miniCartDetail;
	}
	
	public WebElement checkOut() {
		return checkOutButton;
	}
	
	public WebElement paymentMethod() {
		return paymentMethod;
	}
	
	public WebElement address() {
		return checkOutAddress;
	}
	
	public WebElement getOrderSummary() {
		return orderSummary;
	}
	
	public WebElement COD() {
		return COD;
	}
	
	public WebElement streetAddress() {
		return checkOutStreetAddress;
	}
	
	public WebElement getState() {
		return stateSelectDropDown;
	}
	
	public WebElement ZIP() {
		return zipCode;
	}
	
	public WebElement CONTACT() {
		return contact;
	}
	
	public WebElement getUpdate() {
		return update;
	}
	
	public WebElement placeOrder() {
		return placeorder;
	}
	
	public WebElement email() {
		return customerEMAIL;
	}
	
	public WebElement name() {
		return customerNAME;
	}
	
	public WebElement surname() {
		return customerSURNAME;
	}
	
	public WebElement city() {
		return checkOutCity;
	}
	
	public WebElement shiping() {
		return freeShiping;
	}
	
	public WebElement getNEXT() {
		return next;
	}
	
	public WebElement shippingDIV() {
		return shippingDiv;
	}
	
	public WebElement cartAddedText() {
		return cartAddedText;
	}
	
	public WebElement selectColor() {
		return selectProductColor;
	}
	
	public WebElement selectSize() {
		return selectProductSize;
	}
	
	public WebElement getSuccessMsg() {
		return successMsg;
	}
	
	public WebElement getSuccessError() {
		return successError;
	}
	
	public WebElement getAms() {
		return selectAmsterdom;
	}
	
	public WebElement getSuccessCartMsg() {
		return successCartMsg;
	}
}