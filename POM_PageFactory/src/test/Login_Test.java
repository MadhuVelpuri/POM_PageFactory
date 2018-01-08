package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import pom.Guru99HomePage;
//import pom.Login_POM;
import pageFactory.Guru99HomePage_PF;
import pageFactory.Guru99Login;
import pom.Guru99HomePage_POM;
import pom.Login_POM;

//import pages.Guru99Login;

public class Login_Test {

	WebDriver driver;

	Login_POM objLogin;

	Guru99Login login_PF;
	Guru99HomePage_PF home_PF;

	Guru99HomePage_POM objHomePage;

	@BeforeTest

	public void setup() {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		/*
		 * FirefoxProfile fp = new FirefoxProfile();
		 * fp.setEnableNativeEvents(true); File pathToBinary = new
		 * File("D:\\Madhu\\ff47\\firefox.exe");
		 * 
		 * FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary); driver =
		 * new FirefoxDriver(ffBinary, fp);
		 * 
		 * driver = new FirefoxDriver();
		 */

		System.setProperty("webdriver.ie.driver", "D:\\Madhu\\WebMD\\Projects\\Java_TestNG\\IEDriverServer.exe");
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		driver = new InternetExplorerDriver(capabilities);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://demo.guru99.com/V4/");
	}

	/**
	 * 
	 * This test case will login in http://demo.guru99.com/V4/ using POM
	 * 
	 * Verify login page title as guru99 bank
	 * 
	 * Login to application
	 * 
	 * Verify the home page using Dashboard message
	 * 
	 * @throws InterruptedException
	 * 
	 */

	@Test(priority = 0)

	public void test_Home_Page_Appear_Correct_POM() throws InterruptedException {

		// Create Login Page object

		objLogin = new Login_POM(driver);

		// Verify login page title

		String loginPageTitle = objLogin.getLoginTitle();

		Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

		// login to application

		objLogin.loginToGuru99("mngr92506", "tyzyrer");
		Thread.sleep(5000);
		// go the next page

		objHomePage = new Guru99HomePage_POM(driver);

		// Verify home page

		Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr92506"));

	}

	@Test(priority = 1)
	public void test_Home_Page_Appear_Correct_PF() throws InterruptedException {

		login_PF = new Guru99Login(driver);
		String logintitle = login_PF.getLoginTitle();
		Thread.sleep(5000);
		Assert.assertTrue(logintitle.toLowerCase().contains("guru99 bank"));
		login_PF.loginToGuru99("mngr92506", "tyzyrer");
		Thread.sleep(5000);
		home_PF = new Guru99HomePage_PF(driver);
		String home_Title = home_PF.getHomePageDashboardUserName();
		Assert.assertTrue(home_Title.toLowerCase().contains("manger id : mngr92506"));
	}

	@AfterTest
	public void close() {
		driver.quit();
	}
}