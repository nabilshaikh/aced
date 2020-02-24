package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.test.assignment.base.Browser;
import org.test.assignment.helper.CommonMethods;
import org.test.assignment.helper.PropertiesFile;
import org.test.assignment.pages.AddNewEmployee;
import org.test.assignment.pages.Home;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC04_Adding9Users {
	
	public static WebDriver driver;
	String url;
	Browser browser = new Browser(null);
	Home home;
	AddNewEmployee addnewemployee;
	CommonMethods cm;
	
	@When("^I add new 9 employee records to make the total count to 11$")
	public void open_the() throws Throwable {
		
		driver = browser.getDriver("chrome");
		url = PropertiesFile.getProperty("app_url");
		browser.launchApplication(driver, url);
		home = PageFactory.initElements(driver, Home.class);
		
		for(int i=1; i<10; i++) {
			
			home.clickAddEmployeeBtn();
			addnewemployee = PageFactory.initElements(driver, AddNewEmployee.class);
			addnewemployee.enterFirstName("abc"+i);
			addnewemployee.enterLastName("def"+i);
			addnewemployee.selectJobTitle("Sr Developer");
			addnewemployee.selectCurrentProject("Professional");
			addnewemployee.clickCreateEmployeeBtn();
		}
 
	}

	@And("^delete few records in order of (\\d+), (\\d+) & (\\d+)$")
	public void delete_few_records_in_order_of(int a, int b, int c) throws Throwable {
		int[] removal = {a,b,c};
		String[] s = new String[removal.length];
		
		for(int i=0; i<removal.length; i++) {
			System.out.println(removal[i]);
			String text = driver.findElement(By.xpath("(//*[@class='card-title'])["+removal[i]+"]")).getText();
			s[i]=text.substring(0, 4);
		}
		for(int i=0; i<s.length;i++) {
			System.out.println(s[i]);
			driver.findElement(By.xpath("//*[contains(text(), '"+s[i]+"')]/parent::div/a/span[text()='Delete Employee']")).click();
			Thread.sleep(2000);
            driver.switchTo().alert().accept();   
            }
		}	    
	

	@Then("^All three records should get deleted successfully and verify the advertisement panel stays in correct position$")
	public void all_three_records_should_get_deleted_successfully_and_verify_the_advertisement_panel_stays_in_correct_position() throws Throwable {
	    home.verifyAdvertisementPanel();
	    driver.quit();
	}


}
