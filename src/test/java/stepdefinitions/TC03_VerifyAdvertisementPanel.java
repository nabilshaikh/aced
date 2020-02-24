package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.test.assignment.base.Browser;
import org.test.assignment.helper.PropertiesFile;
import org.test.assignment.pages.AddNewEmployee;
import org.test.assignment.pages.Home;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TC03_VerifyAdvertisementPanel {
	
	public static WebDriver driver;
	String url;
	Browser browser = new Browser(null);
	Home home;
	AddNewEmployee addnewemployee;
	
	@Given("^I create more than two employee records$")
	public void i_create_more_than_two_employee_records() throws Throwable {
	    
		driver = browser.getDriver("chrome");
		url = PropertiesFile.getProperty("app_url");
		browser.launchApplication(driver, url);
		home = PageFactory.initElements(driver, Home.class);
		home.verifyAdvertisementPanel();
		
		home.clickAddEmployeeBtn();
		addnewemployee = PageFactory.initElements(driver, AddNewEmployee.class);
		addnewemployee.enterFirstName("third");
		addnewemployee.enterLastName("record");
		addnewemployee.selectJobTitle("Sr Developer");
		addnewemployee.selectCurrentProject("Professional");
		addnewemployee.clickCreateEmployeeBtn();		
	}
	
	@Then("^Advertisement panel should appear on creating third record$")
	public void advertisement_panel_should_appear_on_creating_third_record() {
		
		home.verifyAdvertisementPanel();
		driver.quit();
	}

}
