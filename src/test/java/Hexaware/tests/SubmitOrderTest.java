package Hexaware.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.Checkout;
import Hexaware.pageobjects.ConfirmationPage;
import Hexaware.pageobjects.CountryPage;
import Hexaware.pageobjects.ListingPage;

public class SubmitOrderTest extends BaseTest {
	
	@Test
	public void submitOrder() throws InterruptedException {
		ListingPage listingPage = new ListingPage(driver);
		Checkout chkoutPage = listingPage.addToCart("Cucumber");
		Assert.assertEquals("Cucumber", splitString(chkoutPage.getSingleProductChoosedInTable("Cucumber").getText(), "-")[0].trim());
		CountryPage countryPage = chkoutPage.placeOrder("rahulshettyacademy");
		ConfirmationPage confrmPage = countryPage.proceedOrder("Mexico");
		String message = splitString(confrmPage.getMessageFromConfirmation(), "You'll")[0].trim();
		System.out.println(message);
		Assert.assertEquals(message, "Thank you, your order has been placed successfully");
	}
	
	@Test
	public void validateProceedToCheckOutNoItems() {
		ListingPage listingPage = new ListingPage(driver);
		listingPage.proceedToCheckOutNoItems();
		String message = listingPage.getCartEmptyMessage();
		Assert.assertEquals(message, "You cart is empty!");
		Assert.assertFalse(listingPage.proceedCheckout(), "Can't proceed to Checkout With no Items in the Cart.");
	}
}
