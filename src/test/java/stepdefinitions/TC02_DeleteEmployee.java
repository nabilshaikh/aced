package stepdefinitions;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.assignment.base.Browser;
import org.test.assignment.helper.CommonMethods;
import org.test.assignment.helper.PropertiesFile;
import org.test.assignment.pages.AddNewEmployee;
import org.test.assignment.pages.Home;
import org.testng.Assert;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC02_DeleteEmployee {
	
	public static WebDriver driver;
	String url;
	Browser browser = new Browser(null);
	Home home;
	AddNewEmployee addnewemployee;
	boolean searchEmployee;
	WebElement deleteBtn;
	CommonMethods cm = new CommonMethods();
	
	@When("^I search for newly created employee as below and click on Delete Employee button coloured in red$")
	public void I_open_the_applicaion_and_create_a_new_employee_with_the_following_data(DataTable data) throws Throwable {
		
		List<Map<String, String>> list = data.asMaps(String.class, String.class);
		driver = browser.getDriver("chrome");
		url = PropertiesFile.getProperty("app_url");
		browser.launchApplication(driver, url);
		Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Axis Chemicals Employee Directory']")).isDisplayed());
		home = PageFactory.initElements(driver, Home.class);
		home.clickAddEmployeeBtn();
		addnewemployee = PageFactory.initElements(driver, AddNewEmployee.class);
		addnewemployee.enterFirstName(list.get(0).get("First Name"));
		addnewemployee.enterLastName(list.get(0).get("Last Name"));
		addnewemployee.selectJobTitle(list.get(0).get("Job Title"));
		addnewemployee.selectCurrentProject(list.get(0).get("Current Project"));
		addnewemployee.clickCreateEmployeeBtn();
		
		String findEmployeeName = "//*[text()='"+list.get(0).get("First Name")+"' and text()='"+list.get(0).get("Last Name")+"']";
		WebElement employee = driver.findElement(By.xpath(findEmployeeName));
		if (employee.isDisplayed()) {
			deleteBtn = driver.findElement(By.xpath(findEmployeeName+"/ancestor::div[@class='card-body']/a/span[text()='Delete Employee']"));
			deleteBtn.click();
			driver.switchTo().alert().accept();
		}
	}

	@Then("^Employee record should get deleted successfully$")
	public void employee_should_get_deleted_successfully() throws Throwable {
		
		try {			
			
			Assert.assertFalse(deleteBtn.isDisplayed());
			
		}/*catch(Exception e) {
			
			System.out.println(e);
		}*/
		finally {
			
			driver.quit();
		}		
	}
}
