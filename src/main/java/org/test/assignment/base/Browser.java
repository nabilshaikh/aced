package org.test.assignment.base;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.test.assignment.helper.PropertiesFile;

public class Browser {
	public static WebDriver driver;
	
	public Browser(WebDriver driver){
		Browser.driver = driver;
		}
	
	public WebDriver getDriver(String browser) throws Exception{
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			File file = new File(System.getProperty("user.dir") + "\\"
					+ "Drivers" + "\\" + "chromedriver.exe" );
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			driver = new ChromeDriver();
			return driver;			
		}

		else if(browser.equalsIgnoreCase("ie")) {

			File file = new File(System.getProperty("user.dir") + "\\"
					+ "Drivers" + "\\" + "IEDriverServer.exe" );
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
			
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("nativeEvents", false);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
			capabilities.setCapability("unexpectedAlertBehaviour", "accept");
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			capabilities.setCapability("disable-popup-blocking", true);
			
			return driver;			
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {

			File file = new File(System.getProperty("user.dir") + "\\"
					+ "Drivers" + "\\" + "geckodriver.exe" );
			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
			driver = new FirefoxDriver();
			return driver;			
		}
		
		else if(browser.equalsIgnoreCase("edge")) {

			File file = new File(System.getProperty("user.dir") + "\\"
					+ "Drivers" + "\\" + "MicrosoftWebDriver.exe" );
			System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
			driver = new EdgeDriver();
			return driver;			
		}
		
		else {
			throw new Exception("Browser is not correct");
		}
		
		
		}
	
	public void launchApplication(WebDriver driver, String url) throws IOException{
		driver.manage().window().maximize();		
		URL link = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) link.openConnection();
		connection.connect();
		int response = connection.getResponseCode();
		System.out.println(response);
		 driver.get(url);		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	
	
	public static boolean waitForPageReady() {

		boolean flag = false;
		while (!flag) {
			try {

				ExpectedCondition<Boolean> isPageReady = new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor) driver).executeScript("return document.readyState")
								.equals("complete");
					}
				};
				Wait<WebDriver> wait = new WebDriverWait(driver, 120);
				flag = wait.until(isPageReady);
				return flag;
			}

			catch (Throwable error) {
				System.out.println("in catch block");
				return flag;

			}
		}
		return flag;
	}
	
	
	public static void chromeKiller() throws Exception 
	{
		final String KILL = "taskkill /im ";
		String processName = "chromedriver.exe ";
		String postProcess = "/f";
		Runtime.getRuntime().exec(KILL + processName + postProcess);
	}
		
}
