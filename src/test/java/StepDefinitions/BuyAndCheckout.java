package StepDefinitions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BuyAndCheckout {
	
	static boolean previousScenarioPassed=true;
	hooks hooks = new hooks();
	
	@Given("user in on demoblaze website")
	public void user_in_on_demoblaze_website() {
		hooks.driver.manage().window().maximize();
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

	@When("user press the Cart button present on navigation header")
	public void user_press_the_button_present_on_navigation_header() {
	    WebElement element = hooks.driver.findElement(By.xpath("/html/body/nav/div[1]/ul/li[4]/a"));
	    String link=element.getAttribute("href");
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

	@Then("user should reach the Cart page")
	public void user_should_reach_the_cart_page() {
	    if(hooks.driver.getPageSource().contains("Products"))
	    {
	    	System.out.println("reached cart page successfully");
	    }
	    else
	    {
	    	previousScenarioPassed=false;
			hooks.tearDown();
			throw new AssertionError("Can't reach Cart page");
	    }
	}

	@Given("user is on the cart page")
	public void user_is_on_the_cart_page() {
	    if(previousScenarioPassed)
	    {
	    	System.out.println("user is on the cart page");
	    }
	    else
	    {
	    	hooks.tearDown();
	    	throw new RuntimeException("User didn't reach to cart page");
	    }
	}

	@When("user press the Place Order button then a page should appear to enter details")
	public void user_press_the_button_then_a_page_should_appear_to_enter_details() {
	    try {
	    	WebElement element = hooks.driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/button"));
		    element.click();
	    } catch (ElementClickInterceptedException e) {
	    	
	    }
	    if(hooks.driver.getPageSource().contains("Purchase"))
	    {
	    	System.out.println("details entering page appears");
	    }
	    else
	    {
	    	previousScenarioPassed=false;
	    	hooks.tearDown();
	    	throw new AssertionError("details entering page does not appear");
	    }
	}

	@When("user does not enter the detail")
	public void user_does_not_enter_the_detail() {
	    System.out.println("user does not enter the required details");
	}

	@And("user clicks on Purchase button without adding credentials")
	public void user_clicks_on_button_without_adding_credentials() {
		
		WebElement element = hooks.driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]"));
		((JavascriptExecutor) hooks.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			
		}
	    element.click();
	    
	    try
	    {
	    	Thread.sleep(2000);
	    }
	    catch(Exception e)
	    {
	    	
	    }

	    try {
		    Alert alert = hooks.driver.switchTo().alert();
		    String alertText = alert.getText();
		    if(alertText == "Please fill out Name and Creditcard.")
		    {
		    	System.out.println("alert coming for filling name and creditcart");
		    }
		    // You can accept, dismiss, or perform any action on the alert as needed
		    alert.accept(); // or alert.dismiss();
		} catch (NoAlertPresentException e) {
			previousScenarioPassed=false;
			hooks.tearDown();
			throw new AssertionError("Alert not coming");
		}
	}

	@Then("a popup should appear asking to enter credentials")
	public void a_popup_should_appear_asking_to_enter_credentials() {
	    if(previousScenarioPassed)
	    {
	    	System.out.println("Alert appeard for entering required details");
	    }
	    else
	    {
	    	previousScenarioPassed=false;
	    	hooks.tearDown();
	    	throw new RuntimeException("Alert not appeared when details are not filled and button is clicked");
	    }
	}

	@When("user enters the detail")
	public void user_enters_the_detail() {
	    hooks.driver.findElement(By.id("name")).sendKeys("abc");
	    hooks.driver.findElement(By.id("card")).sendKeys("123");
	}
	
	@And("user clicks on Purchase button with adding credentials")
	public void user_clicks_on_button_with_adding_credentials() {
		
		WebElement element = hooks.driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]"));
		((JavascriptExecutor) hooks.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			
		}
	    element.click();
	    
	    try
	    {
	    	Thread.sleep(2000);
	    }
	    catch(Exception e)
	    {
	    	
	    }
	    if(hooks.driver.getPageSource().contains("Thank you for your purchase!"))
	    {
	    	System.out.println("Purchase successful!");
	    }
	    else
	    {
	    	previousScenarioPassed=false;
	    	hooks.tearDown();
	    	throw new AssertionError("Purchase is not successful");
	    }
	}

	@Then("user should see the transaction page")
	public void user_should_see_the_transaction_page() {
	    if(previousScenarioPassed)
	    {
	    	System.out.println("Purchase Successful!");
	    	hooks.tearDown();
	    }
	    else
	    {
	    	hooks.tearDown();
	    	throw new RuntimeException("Checkout can't be successful");
	    }
	}
}
