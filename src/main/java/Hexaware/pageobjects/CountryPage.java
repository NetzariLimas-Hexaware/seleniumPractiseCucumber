package Hexaware.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Hexaware.AbstractComponents.AbstractComponents;

public class CountryPage extends AbstractComponents {
	WebDriver driver;
	public CountryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(tagName="select")
	WebElement selectCountry;
	
	@FindBy(css=".chkAgree")
	WebElement checkAgr;
	
	@FindBy(tagName="button")
	WebElement proceedButton;
	
	public void selectCountry(String country) {
		Select sel = new Select(selectCountry);
		sel.selectByValue(country);
	}
	
	public void acceptTermsAndConditions() {
		checkAgr.click();
	}
	
	public ConfirmationPage proceedOrder(String country) {
		selectCountry(country);
		acceptTermsAndConditions();
		proceedButton.click();
		
		return new ConfirmationPage(driver);
	}
}
