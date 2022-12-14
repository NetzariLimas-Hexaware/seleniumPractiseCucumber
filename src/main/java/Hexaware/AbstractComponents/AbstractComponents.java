package Hexaware.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void waitElementoToAppear(By element){
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public void waitForElementToDissappear(WebElement promoBtnLoader) {
		wait.until(ExpectedConditions.invisibilityOf(promoBtnLoader));
	}

}
