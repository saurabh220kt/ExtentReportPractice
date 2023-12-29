package Dpack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoTest {

	WebDriver driver;
	ExtentReports extent;
	
	@BeforeMethod
	public void configuration() {
		
		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html"; //path where you want to generate the reports
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		
		reporter.config().setReportName("Omayo Test Report");
		reporter.config().setDocumentTitle("Omayo Test Report Title");
		
		extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Opertaing System", "Windows 10");
		extent.setSystemInfo("Tested By", "Saurabh");
		
		
	}
	
	
	@Test	
	public void testOne() {

		ExtentTest eTest = extent.createTest("Test One Created");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		eTest.info("Chrome Browser Launched");
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://omayo.blogspot.com/");
		eTest.info("Naviagted to Omayo Page");

		//String actualText = driver.findElement(By.id("pah")).getText();
		
		eTest.fail("Test One failed"); //This is for failing the extentreport test
		Assert.assertEquals(driver.findElement(By.id("pah")).getText(), "PracticeAutomationHereABC");
		
		

//		String actaulText = driver.findElement(By.id("pah")).getText()
//		if (actualText.equals("PracticeAutomationHere")) {
//			
//			System.out.println("Test Passed");
//			
//		} else {
//			
//			System.out.println("Test Failed");
//		}

	
	}
	
	@AfterMethod
	
	public void tearDown() {
		
		driver.close();
		extent.flush();
		
		
	}

}
