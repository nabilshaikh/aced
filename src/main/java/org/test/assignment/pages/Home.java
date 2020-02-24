package org.test.assignment.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.assignment.base.Browser;
import org.test.assignment.helper.CommonMethods;
import org.testng.Assert;

public class Home extends Browser{
	
	public Home(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	CommonMethods cm = new CommonMethods();
	WebDriverWait wait = new WebDriverWait(driver, 20);
	
	/* ================================= Page Elements ================================= */

	public WebElement elementAddEmpBtn() {

		return cm.findElementByXpath(driver, "//a[text()='Add Employee']");
	}
	
	/* ==================================== Actions ==================================== */
	
	public void clickAddEmployeeBtn() {
		
		WebElement addempbtn = elementAddEmpBtn();
		cm.click(driver, addempbtn);
	}
	
	public void verifyAdvertisementPanel() {
		
		try {
			List<WebElement> li = driver.findElements(By.className("card-title"));
			if(li.size()>=2 && li.size()%2==0) {

				System.out.println((li.size()/2)-1);
				Assert.assertEquals(driver.findElements(By.xpath("//*[text()='Advertisement']")).size(), (li.size()/2)-1);
			}

			if(li.size()>=3 && li.size()%2!=0) {
				System.out.println(li.size()/2);
				Assert.assertEquals(driver.findElements(By.xpath("//*[text()='Advertisement']")).size(), li.size()/2);
			}
			else
				System.out.println("Add a new employee record to get the advertisement panel");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
