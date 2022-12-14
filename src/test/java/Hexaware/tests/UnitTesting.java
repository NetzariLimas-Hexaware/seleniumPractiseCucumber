package Hexaware.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.ListingPage;

public class UnitTesting extends BaseTest {
	
	@Test(groups= {"DisplayedElements"})
	public void isLogoDisplayed() throws IOException {
		ListingPage listingPage = new ListingPage(driver);
		listingPage.verifyLogoDisplayed();
	}
	
	@Test(groups= {"DisplayedElements"})
	public void AreProductsListed() {
		ListingPage listingPage = new ListingPage(driver);
		listingPage.verifyProductsAreListing();
	}
	
	@Test(groups= {"DisplayedElements"})
	public void AreCartIconDisplayed() {
		ListingPage listingPage = new ListingPage(driver);
		listingPage.verifyCartIconDisplayed();
	}
}
