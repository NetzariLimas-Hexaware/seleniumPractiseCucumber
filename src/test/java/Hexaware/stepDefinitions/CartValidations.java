package Hexaware.stepDefinitions;

import org.testng.Assert;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.ListingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartValidations extends BaseTest {
	public ListingPage listingPage;
	@When("^Click to Cart to open it$")
    public void click_to_cart_to_open_it() throws Throwable {
		ListingPage listingPage = new ListingPage(driver);
		listingPage.proceedToCheckOutNoItems();
    }

    @Then("^I click proceed Checkout and it should not work$")
    public void i_click_proceed_checkout_and_it_should_not_work() throws Throwable {
    	Assert.assertFalse(listingPage.proceedCheckout(), "Can't proceed to Checkout With no Items in the Cart.");
    }

    @And("^Validate cart message \"([^\"]*)\" is actually empty$")
    public void validate_cart_message_something_is_actually_empty(String strArg1) throws Throwable {
    	String message = listingPage.getCartEmptyMessage();
		Assert.assertEquals(message, strArg1);
    }
}
