package Hexaware.stepDefinitions;

import org.testng.Assert;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.Checkout;
import Hexaware.pageobjects.ConfirmationPage;
import Hexaware.pageobjects.CountryPage;
import Hexaware.pageobjects.ListingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	public ListingPage listingPage;
	public Checkout chkoutPage;
	public CountryPage countryPage;
	public ConfirmationPage confrmPage;
	
	@Given("^I landed on Ecommerce Page$")
    public void i_landed_on_ecommerce_page() throws Throwable {
		listingPage = runApplication();
    }

    @When("^Add a product (.+) to Cart$")
    public void add_a_product_to_cart(String productname) throws Throwable {
    	chkoutPage = listingPage.addToCart(productname);
    	Assert.assertEquals(productname, splitString(chkoutPage.getSingleProductChoosedInTable(productname).getText(), "-")[0].trim());
    }
    
    @And("^Go to checkout and add code (.+) to discount$")
    public void go_to_checkout_and_add_code_to_discount(String codestring) throws Throwable {
    	countryPage = chkoutPage.placeOrder(codestring);
    }

    @Then("^I choose the country (.+) agree terms and place the order$")
    public void i_choose_the_country_and_agree_terms(String countryname) throws Throwable {
    	confrmPage = countryPage.proceedOrder(countryname);
    }
    
    @Then("^\"([^\"]*)\" message is displayed on confirmation page.$")
    public void something_message_is_displayed_on_confirmation_page(String msg) throws Throwable {
    	String message = splitString(confrmPage.getMessageFromConfirmation(), "You'll")[0].trim();
		Assert.assertEquals(message, msg);
    }

}
