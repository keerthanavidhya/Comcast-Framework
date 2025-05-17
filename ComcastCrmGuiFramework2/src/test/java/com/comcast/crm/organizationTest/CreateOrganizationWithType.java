package com.comcast.crm.organizationTest;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseutility.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationWithType extends BaseClass{
	@Test
	public void createOrganizationWithType() throws Throwable {
		//read test script data from excel
		String orgname = elib.getDataFromExcel("Org", 4, 2)+ jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("Org", 4, 3);
		String type =elib.getDataFromExcel("Org", 4, 4);
		
		//2: navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		//3:click on create organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//4: enter all the details
		CreatingOrganizationPage cop = new CreatingOrganizationPage(driver);
		cop.createOrg(orgname, industry, type);

	// verify the industry and type
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualIndustry = oip.getIndustryInfo().getText();
		if(actualIndustry.equals(industry)) {
			System.out.println(industry + " information is verified === pass");
		} else {
			System.out.println(industry + " information is not verified === fail");
		}
		String actualType  =oip.getTypeInfo().getText();
		if(actualType.equals(type)) {
			System.out.println(type + " information type is verified");
		} else {
			System.out.println(type + " information type is not verified");
		}
	}

}
