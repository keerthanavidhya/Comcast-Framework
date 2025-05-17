package com.comcast.crm.organizationTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationWithPhoneNumber extends BaseClass{
	@Test
	public void createOrganizationWithPhoneNumber() throws Throwable {
		
		
		
	//read test script data from excel
		String orgname = elib.getDataFromExcel("Org", 7, 2) + jlib.getRandomNumber();
		String phoneNumber = elib.getDataFromExcel("Org", 7, 3);
		
		
		//2: navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		//3:click on create organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//4: enter all the details
		CreatingOrganizationPage cop = new CreatingOrganizationPage(driver);
		cop.getOrgNameEdit().sendKeys(orgname);
		cop.getPhoneEdt().sendKeys(phoneNumber);
		cop.getSaveBtn().click();
		
		//5.verify phone number
		OrganizationInfoPage oif = new OrganizationInfoPage(driver);
		String actualPhoneNumber = oif.getPhoneInfo().getText();
		if(actualPhoneNumber.equals(phoneNumber)) {
			System.out.println(actualPhoneNumber +  " information is correct === pass");
		} else {
			System.out.println(actualPhoneNumber +  " inforamtion is  not correct === fail");
		}
	}

}
