package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.test.assignment.base.Browser;
import org.test.assignment.helper.PropertiesFile;
import org.test.assignment.pages.AddNewEmployee;
import org.test.assignment.pages.Home;
import org.testng.Assert;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC01_AddNewEmployee_IncompleteForm {
	
	public static WebDriver driver;
	String url;
	Browser browser = new Browser(null);
	Home home;
	AddNewEmployee addnewemployee;
	
	@Given("^Open the application$")
	public void open_the_application() throws Throwable {
		
		driver = browser.getDriver("chrome");
		url = PropertiesFile.getProperty("app_url");
		browser.launchApplication(driver, url);
		Assert.assertEquals(driver.getTitle(), "React App");	    
	}

	@When("^I click on Add Employee button from Home page$")
	public void i_click_on_Add_Employee_button(){
		
		home = PageFactory.initElements(driver, Home.class);
		home.clickAddEmployeeBtn();
		Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Add New Employee']")).isDisplayed());
	}

	@And("^Populate all the mandatory fields \"(.*?)\", \"(.*?)\" & \"(.*?)\" leaving behind Current Project field\"$")
	public void populate_all_the_mandatory_fields_leaving_behind_employee_current_project_field(String firstname, String lastname, String jobtitle) throws Throwable {
		
		addnewemployee = PageFactory.initElements(driver, AddNewEmployee.class);
		addnewemployee.enterFirstName(firstname);
		addnewemployee.enterLastName(lastname);
		addnewemployee.selectJobTitle(jobtitle);	    
	}

	@And("^Click on Create Employee button$")
	public void click_on_Create_Employee_button() throws Throwable {
		
		addnewemployee = PageFactory.initElements(driver, AddNewEmployee.class);
		addnewemployee.clickCreateEmployeeBtn();	    
	}

	@Then("^Error message should appear and new employee should not get created$")
	public void error_should_appear_as() throws Throwable {
	    
		addnewemployee = PageFactory.initElements(driver, AddNewEmployee.class);
		String capturetoast = driver.findElement(By.className("toast-body")).getText();
	    Assert.assertEquals(capturetoast, "Employee not created due, please enter a value for all fields");
	    Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Please select a project']")).isDisplayed());
	    driver.quit();
	}


}
