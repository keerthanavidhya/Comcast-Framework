package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseutility.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;

public class CreateContactWithSupportDate extends BaseClass {
	@Test
	public void createContactWithSupportDate() throws Throwable {
		
		//read test script data from excel
		String lastName = elib.getDataFromExcel("Contact", 4, 2);
		//2: navigate to contact
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		//3:click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();
		//4: enter all the details
		CreatingContactPage ccp = new CreatingContactPage(driver);
		//logic to generate date and date after 30 days
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);
		ccp.createContactwithSupportDate(lastName, startDate, endDate);
		 //verify  start date 
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actualStartdate = cip.getStartDateInfo().getText();
		 if(actualStartdate.equals(startDate)) {
			 System.out.println(actualStartdate + " is displayed == pass");
		 } else {
			 System.out.println(actualStartdate + " is  not displayed == fail"); 
		 }
		 //verify end date
		 String actualEnddate = cip.getEndDateInfo().getText();
		 if(actualEnddate.equals(endDate)) {
			 System.out.println(actualEnddate + " is displayed == pass");
		 } else {
			 System.out.println(actualEnddate + " is  not displayed == fail"); 
		 }
	}

}
