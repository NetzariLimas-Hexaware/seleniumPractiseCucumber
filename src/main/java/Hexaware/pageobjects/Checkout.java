package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponents;

public class Checkout extends AbstractComponents {
	WebDriver driver;
	public Checkout(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#productCartTables tbody tr")
	List<WebElement> productChoosed;
	
	@FindBy(css=".promoWrapper")
	WebElement promoWrapper;
	
	@FindBy(xpath="//button[contains(text(),'Place Order')]")
	WebElement placeOrderButton;
	
	By productChoosedBy = By.cssSelector("#productCartTables tbody tr");
	
	public List<WebElement> getAllProductCartChoosed() {
		waitElementoToAppear(productChoosedBy);
		return productChoosed;
	}
	
	public WebElement getSingleProductChoosedInTable(String productName) {
		return getAllProductCartChoosed().stream().filter(s->s.findElement(By.cssSelector("td:nth-child(2)"))
				.getText().contains(productName))
		.findAny().orElse(null);
	}
	
	public String applyCodeForDisccount(String codeString) {
		WebElement promoCodeInput = promoWrapper.findElement(By.cssSelector(".promoCode"));
		WebElement promoCodeButton = promoWrapper.findElement(By.cssSelector(".promoBtn"));
		promoCodeInput.sendKeys(codeString);
		promoCodeButton.click();
		
		WebElement promoBtnLoader = promoCodeButton.findElement(By.cssSelector(".promo-btn-loader"));
		waitForElementToDissappear(promoBtnLoader); //promo-btn-loader
		
		WebElement promoTextApllied = promoWrapper.findElement(By.cssSelector(".promoInfo"));
		
		return promoTextApllied.getText();
	}
	
	public CountryPage placeOrder(String codeString) {
		applyCodeForDisccount(codeString);
		placeOrderButton.click();
		
		return new CountryPage(driver);
	}

}
