package Hexaware.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".wrapperTwo")
	WebElement confirmationMessage;
		
	public String getMessageFromConfirmation() {
		return confirmationMessage.getText();
	}
}
