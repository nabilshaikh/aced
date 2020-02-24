package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.test.assignment.base.Browser;
import org.test.assignment.helper.PropertiesFile;
import org.test.assignment.pages.AddNewEmployee;
import org.test.assignment.pages.Home;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC06_VerifyToastAlert {
	
	public static WebDriver driver;
	String url;
	Browser browser = new Browser(null);
	Home home;
	AddNewEmployee addnewemployee;
	
	@Given("^Open the application and create new employee record$")
	public void open_the_app() throws Throwable {
		
		driver = browser.getDriver("chrome");
		url = PropertiesFile.getProperty("app_url");
		browser.launchApplication(driver, url);
		Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Axis Chemicals Employee Directory']")).isDisplayed());	
		home = PageFactory.initElements(driver, Home.class);
		home.clickAddEmployeeBtn();
		Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Add New Employee']")).isDisplayed());
	}

	@When("^I populate all the mandatory fields \"(.*?)\", \"(.*?)\" & \"(.*?)\" leaving behind Current Project field\"$")
	public void populate_mandatory_fields(String firstname, String lastname, String jobtitle){
		
		addnewemployee = PageFactory.initElements(driver, AddNewEmployee.class);
		addnewemployee.enterFirstName(firstname);
		addnewemployee.enterLastName(lastname);
		addnewemployee.selectJobTitle(jobtitle);
		addnewemployee.clickCreateEmployeeBtn();		
	}

	@Then("^Appropriate message should appear on alert$")
	public void appropriate_message_should_appear_on_alert() throws Throwable {

		addnewemployee = PageFactory.initElements(driver, AddNewEmployee.class);
		String capturetoast = driver.findElement(By.className("toast-body")).getText();
		Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Please select a project']")).isDisplayed());

		try {			
			Assert.assertEquals(capturetoast, "Employee not created, please enter a value for all fields");

		}
		finally {

			driver.quit();
		}	
	}
}
