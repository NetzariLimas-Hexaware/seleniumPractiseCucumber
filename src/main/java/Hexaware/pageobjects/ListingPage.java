package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponents;

public class ListingPage extends AbstractComponents {
	WebDriver driver;
	public ListingPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".product")
	List<WebElement> products;
	
	@FindBy(css=".greenLogo")
	WebElement companyLogo;
	
	@FindBy(css=".search-form")
	WebElement searchForm;
	
	@FindBy(css=".cart-icon")
	WebElement cartIcon;
	
	@FindBy(css=".cart-info tr td:nth-child(3)")
	List<WebElement> cartInfo;
	
	@FindBy(css="div[class='cart-preview active']")
	WebElement cartPreview;
	
	By productsBy = By.cssSelector(".product");
	By companyLogoBy = By.cssSelector(".greenLogo");
	By cartIconBy = By.cssSelector(".cart-icon");
	By cartPreviewBy = By.cssSelector("div[class='cart-preview active']");
		
	public void addToCartPushes() {
		
	}
	
	public void verifyProductsAreListing() {
		waitElementoToAppear(productsBy);
	}
	
	public List<WebElement> getAllProductListing() {
		verifyProductsAreListing();
		return products;
	}
	
	public WebElement getSingleProductByName(String productName) {
		return getAllProductListing().stream()
				.filter(s->s.findElement(By.cssSelector(".product-name"))
						.getText().contains(productName)).findAny().orElse(null);
	}
	
	public Checkout addToCart(String productName) throws InterruptedException {
		Thread.sleep(1000);
		getSingleProductByName(productName).findElement(By.tagName("button")).click();
		cartIcon.click();
		verifyCartPreview();
		proceedCheckout();
		return new Checkout(driver);
	}
	
	public void proceedToCheckOutNoItems() {
		cartIcon.click();
		verifyCartPreview();
	}
	
	public void verifyLogoDisplayed() {
		waitElementoToAppear(companyLogoBy);
	}
	
	public void searchSingleVegetable(String productName) {
		WebElement input = searchForm.findElement(By.tagName("input"));
		input.sendKeys(productName);
	}
	
	public void searchWithSearchButtonSingleVegetable(String productName) {
		WebElement button = searchForm.findElement(By.tagName("button"));
		searchSingleVegetable(productName);
		button.click();
	}
	
	public void verifyCartIconDisplayed(){
		waitElementoToAppear(cartIconBy);
	}
	
	public void verifyCartPreview() {
		waitElementoToAppear(cartPreviewBy);
	}
	
	public boolean proceedCheckout() {
		if(getProcedToCheckOutButton().isEnabled()) {			
			cartPreview.findElement(By.cssSelector("div[class='action-block'] button")).click();
			return true;
		}
		
		return false;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}
	
	public String getCartEmptyMessage() {
		return cartPreview.findElement(By.cssSelector("div[class='empty-cart'] h2")).getText();
	}
	
	public WebElement getProcedToCheckOutButton() {
		return cartPreview.findElement(By.cssSelector("div[class='action-block'] button"));
	}
}
