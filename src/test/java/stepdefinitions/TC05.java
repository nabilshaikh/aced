package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.assignment.base.Browser;
import org.test.assignment.helper.PropertiesFile;
import org.test.assignment.pages.AddNewEmployee;
import org.test.assignment.pages.Home;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC05 {
	
	public static WebDriver driver;
	String url;
	Browser browser = new Browser(null);
	Home home;
	AddNewEmployee addnewemployee;
	String firstname;
	public static WebElement findEmployee; 
	
	@When("^I open the application and create new employee$")
	public void i_open_the_application_and_create_new_employee() throws Throwable {
		
		driver = browser.getDriver("chrome");
		url = PropertiesFile.getProperty("app_url");
		browser.launchApplication(driver, url);
		home = PageFactory.initElements(driver, Home.class);
		home.clickAddEmployeeBtn();
	}
	
	@And("^save the details by entering \"(.*?)\",\"(.*?)\",\"(.*?)\" and \"(.*?)\"$")
	public void save_the_details_by_entering_and(String firstname, String lastname, String jobtitle, String currentproject) throws Throwable {
		addnewemployee = PageFactory.initElements(driver, AddNewEmployee.class);
		addnewemployee.enterFirstName(firstname);
		addnewemployee.enterLastName(lastname);
		addnewemployee.selectJobTitle(jobtitle);
		addnewemployee.selectCurrentProject(currentproject);
		addnewemployee.clickCreateEmployeeBtn();
		findEmployee= driver.findElement(By.xpath("//*[text()='"+firstname+"']"));
		System.out.println(findEmployee.getText().toString());
	}

	@And("^close the browser$")
	public void close_the_browser() throws Throwable {
		
		driver.close();	    
	}

	@And("^reopen the browser$")
	public void reopen_the_browser() throws Throwable {
	    
		driver = browser.getDriver("chrome");
		url = PropertiesFile.getProperty("app_url");
		browser.launchApplication(driver, url);		
	}

	@Then("^newly created employee \"(.*?)\" should be present$")
	public void newly_created_employee_details_should_be_present(String firstname) throws Throwable {
	    
		home = PageFactory.initElements(driver, Home.class);
		Assert.assertEquals(true, findEmployee.isDisplayed());
		/*
		boolean passFail = false;
		try {
	        if (driver.findElement(By.xpath("//*[text()='"+firstname+"']")).isDisplayed())
	        	Assert.assertTrue(passFail=true);
	    } catch (NoSuchElementException e) {
	        System.err.println("Unable to locate element");
	    } catch (Exception e) {
	        System.err.println("Unable to check display status of element");
	        e.printStackTrace();
	    }
		finally {driver.quit();}*/

	}

}
