package com.comcast.crm.generic.baseutility;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtiliityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	public DataBaseUtility dlib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility(); 
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wblib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	
	@BeforeSuite(groups = {"smokeTest" , "regressionTest"})
	public void configureBs() throws Throwable {
		System.out.println("====Connect to DB, Report Configure=====");
		dlib.getDbConnection();
		
		
	}
	//@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest" , "regressionTest"})
	public void configureBc() throws Throwable {
		//String BROWSER = flib.getDataFromPropertiesFile("browser");
		//String BROWSER = browser;
		String BROWSER = System.getProperty("browser", flib.getDataFromPropertiesFile("browser"));
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if(BROWSER.equals("firefox")){
			driver = new FirefoxDriver();
		} else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtiliityClassObject.setDriver(driver);
		
	}
	@BeforeMethod(groups = {"smokeTest" , "regressionTest"})
	public void configureBM() throws Throwable {
		System.out.println("login");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
		
	}
	@AfterMethod(groups = {"smokeTest" , "regressionTest"})
	public void configureAM() {
		System.out.println("logout");
		HomePage hp = new HomePage(driver);
		hp.logout();
		
	}
	@AfterClass(groups = {"smokeTest" , "regressionTest"})
	public void configureAC() {
		System.out.println("Close the browser");
		driver.quit();
	}
	@AfterSuite(groups = {"smokeTest" , "regressionTest"})
	public void configureAS() throws Throwable {
		System.out.println("Close the DB connection, report backup");
		dlib.closeDbConnection();
	}

}
