package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateContactwithOrgTest extends BaseClass {
	@Test
	public void createContactwithOrgTest() throws Throwable {
		
		//read test script data from excel
		String orgname = elib.getDataFromExcel("Contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("Contact", 7, 3) + jlib.getRandomNumber();
		//2: navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		//3:click on create organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		//4: enter all the details
		CreatingOrganizationPage cop = new CreatingOrganizationPage(driver);
		cop.createOrg(orgname);
		//verify Header msg
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		 String headerInfo = oip.getHeaderMsg().getText();
		 if(headerInfo.contains(orgname)) {
			 System.out.println(orgname + " is created == pass");
		 } else {
			 System.out.println(orgname + " is  not created == fail");
		 }
		 //5.navigate to contact
		 hp.getContactLink().click();
			//3:click on create contact button
		 ContactPage cp = new ContactPage(driver);
		 cp.getCreateNewContactBtn().click();
			//4: enter all the details
		 CreatingContactPage ccp = new CreatingContactPage(driver);
		 ccp.createContactWithOrg(contactLastName, orgname);
			
			//verify Header msg on contact page
		 ContactInfoPage cip = new ContactInfoPage(driver);
			 String headerInfo1 = cip.getHeaderContact().getText();
			 if(headerInfo1.contains(contactLastName)) {
				 System.out.println(contactLastName + " is created == pass");
			 } else {
				 System.out.println(contactLastName + " is  not created == fail");
			 }
			 
			// verify organization name on contact page to check a data flow
			String actOrgName  = cip.getOrgNameInfo().getText();
			if(actOrgName.trim().equals(orgname)) {
				System.out.println(orgname + " information is correct- data flow is done");
			} else {
				System.out.println(orgname + " information is correct- data flow is done");
			}
			
		 driver.quit();
	}

}
