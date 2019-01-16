package extenreports;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.Screenshots;

public class LoginTestWithScreenshot {
	
	private WebDriver driver;
	private String baseUrl;
	ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public void beforeClass() {
		baseUrl = "https://learn.letskodeit.com/";
		report = new ExtentReports("C:\\Udemy\\workspace\\TestNGTutorial\\test-output\\SajatReport\\loginscreenshot.html");
		test = report.startTest("Verify Welcome Text");
		//driver = new FirefoxDriver();
		 driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Browser Started...");

		// Maximize the browser's window
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser Maximized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		test.log(LogStatus.INFO, "Web application opened");
	}
	
	@Test
	public void test1_validLoginTest() throws Exception {

		WebElement signupLink = driver.findElement(
				By.xpath("EZT TÖRÖLD/html//div[@id='navbar']//ul[@class='nav navbar-nav navbar-right']//a[@href='/sign_in']"));
		signupLink.click();
		test.log(LogStatus.INFO, "Clicked on signup link");
		test.log(LogStatus.PASS, "Find signup");

		/*
		 * WebElement loginLink =
		 * driver.findElement(By.xpath("/html//a[@id='signUpDialogswitchDialogLink']"));
		 * loginLink.click();
		 */

		WebElement emailField = driver.findElement(By.xpath("/html//input[@id='user_email']"));
		emailField.sendKeys("test@email.com");
		test.log(LogStatus.INFO, "Email xpath find");
		test.log(LogStatus.PASS, "Enter email");

		WebElement passwordField = driver.findElement(By.xpath("/html//input[@id='user_password']"));
		passwordField.sendKeys("abcabc");
		test.log(LogStatus.INFO, "Enter password");

		WebElement goButton = driver.findElement(By.xpath("//form[@id='new_user']//input[@name='commit']"));
		goButton.click();
		test.log(LogStatus.PASS, "Clicked Go button");

		Thread.sleep(3000);

		WebElement welcomeText = null;

		try {
			welcomeText = driver.findElement(By.xpath(
					"/html/body/div[@class='view-school']//div[@class='course-listing']//a[@href='/courses/enrolled/144684']//div[@class='course-listing-title']"));
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Nincs meg az elem");
		}
		Assert.assertTrue(welcomeText != null);
		test.log(LogStatus.PASS, "Verified Welcome Text");
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(driver, testResult.getName());
			String imagePath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Verify Welcome Text Failed", imagePath);
		}
		driver.quit();
		report.endTest(test);
		report.flush();
	}
}