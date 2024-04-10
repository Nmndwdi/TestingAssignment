package StepDefinitions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddToCart {
	
	static boolean previousScenarioPassed=true;
	hooks hooks = new hooks();

	@Given("browser is open")
	public void browser_is_open() {
	    hooks.driver.manage().window().maximize();
	}

	@When("user enter the correct url of demoblaze")
	public void user_enter_the_correct_url_of_demoblaze() {
	    hooks.driver.navigate().to("https://www.demoblaze.com/index.html");
	    try
	    {
	    	Thread.sleep(2000);
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.getMessage());
	    }
	}

	@Then("user will reach to the product landing page")
	public void user_will_reach_to_the_product_landing_page() {
		if(hooks.driver.getPageSource().contains("PRODUCT STORE"))
		{
			System.out.println("reached product landing page correctly");
		}
		else
		{
			previousScenarioPassed=false;
			hooks.tearDown();
			throw new AssertionError("Can't reach product landing page");
		}
	}
	
	@Given("user have viewed the product landing page")
	public void user_have_viewed_the_product_landing_page() {
		if(!previousScenarioPassed)
		{
			throw new RuntimeException("User didn't reach to product landing page");
		}
		else
		{
			System.out.println("user viewed product landing page");
		}
	}

	@When("user click on a product")
	public void user_click_on_a_product() {
		WebElement element = hooks.driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div[1]/div/div/h4/a"));
		String link = element.getAttribute("href");
		hooks.driver.navigate().to(link);
		try
		{
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Then("user should see its product detail page")
	public void user_should_see_its_product_detail_page() {
	    if(hooks.driver.getPageSource().contains("Add to cart"))
	    {
	    	System.out.println("reached product detail page correctly");
	    }
	    else
		{
			previousScenarioPassed=false;
			hooks.tearDown();
			throw new AssertionError("Can't reach product detail page");
		}
	}

	@Given("user should be one the product details page")
	public void user_should_be_one_the_product_details_page() {
		if(!previousScenarioPassed)
		{
			throw new RuntimeException("User didn't reach to product details page");
		}
		else
		{
			System.out.println("user viewed product details page");
		}
	}

	@When("user press Add to Cart button")
	public void user_press_button() {
		WebElement element = hooks.driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a"));
		((JavascriptExecutor)hooks.driver).executeScript("arguments[0].click();", element);
		try
		{
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		try {
		    Alert alert = hooks.driver.switchTo().alert();
		    String alertText = alert.getText();
		    if(alertText == "Product added")
		    {
		    	System.out.println("Product added to Cart successfully");
		    }
		    // You can accept, dismiss, or perform any action on the alert as needed
		    alert.accept(); // or alert.dismiss();
		} catch (NoAlertPresentException e) {
			previousScenarioPassed=false;
			hooks.tearDown();
			throw new AssertionError("Can't add product to the cart");
		}
	}

	@Then("product should be added and a popup should be displayed")
	public void product_should_be_added_and_a_popup_should_be_displayed() {
		if(previousScenarioPassed)
	    {
	    	System.out.println("Product added to Cart successfully and popup is displayed");
	    	hooks.tearDown();
	    }
	    else
		{
	    	hooks.tearDown();
			throw new AssertionError("Can't add product to the cart");
		}
	}
}
