package org.test.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.assignment.base.Browser;
import org.test.assignment.helper.CommonMethods;

public class AddNewEmployee extends Browser{

	public AddNewEmployee(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	CommonMethods cm = new CommonMethods();
	WebDriverWait wait = new WebDriverWait(driver, 20);
	
	/* ================================= Page Elements ================================= */

	public WebElement elementFirstName() {

		return cm.findElementByXpath(driver, "//input[@id='firstName']");
	}
	
	public WebElement elementLastName() {

		return cm.findElementByXpath(driver, "//input[@id='lastName']");
	}
	
	public WebElement elementCreateEmployeeBtn() {

		return cm.findElementByXpath(driver, "//button[text()='Create Employee']");
	}
	
	
	/* ==================================== Actions ==================================== */
	
	public void enterFirstName(String firstname) {
		
		WebElement firstnm = elementFirstName();
		cm.enterText(driver, firstnm, firstname);
	}

	public void enterLastName(String lastname) {

		WebElement lastnm = elementLastName();
		cm.enterText(driver, lastnm, lastname);
	}
	
	public void selectJobTitle(String value) {

		cm.selectDropdownByValue(driver, "//select[@id='title']", value);
		//cm.tp(driver, "//select[@id='title']", value);
	}
	
	public void selectCurrentProject(String value) {

		cm.selectDropdownByValue(driver, "//select[@id='project']", value);
	}
	
	public void clickCreateEmployeeBtn() {

		WebElement button = elementCreateEmployeeBtn();
		cm.click(driver, button);
	}

}
