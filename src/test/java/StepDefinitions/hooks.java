package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hooks {
	public static WebDriver driver=null;
	String projectPath = System.getProperty("user.dir");
	
	public hooks()
	{
		if(driver==null)
		{
			System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
		    driver = new ChromeDriver();
		}
	}
	
	public static void tearDown()
	{
		driver.close();
		driver.quit();
	}
}
