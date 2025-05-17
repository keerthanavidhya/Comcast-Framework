package com.comcast.crm.organizationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseutility.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtiliityClassObject;
import com.comcast.crm.listenerUtility.ListenImplementation;
import com.comcast.crm.objectrepositoryutility.CreatingOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationTest extends BaseClass {
	@Test(groups = {"smokeTest"})
	public void createOrganizationTest() throws Throwable {
		// read test script data from excel
		String orgname = elib.getDataFromExcel("Org", 1, 2) + jlib.getRandomNumber();

		// 2: navigate to organization
		UtiliityClassObject.getTest().log(Status.INFO, "navigate to organization");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// 3:click on create organization button
		UtiliityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// 4: enter all the details
		UtiliityClassObject.getTest().log(Status.INFO, "create a new org");
		CreatingOrganizationPage cop = new CreatingOrganizationPage(driver);
		cop.createOrg(orgname);
		UtiliityClassObject.getTest().log(Status.INFO, orgname+"is a new org created");

		// 5.verify Header msg
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualHeadermsg = oip.getHeaderMsg().getText();
		boolean status = actualHeadermsg.contains(orgname);
		Assert.assertEquals(status, true);
		// verify org name
		String actualOrgName = oip.getOrganizationNameInfo().getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualOrgName, orgname);
	}

	@Test(groups = {"regressionTest"})
	public void createOrganizationWithPhoneNumber() throws Throwable {
		// read test script data from excel
		String orgname = elib.getDataFromExcel("Org", 7, 2) + jlib.getRandomNumber();
		String phoneNumber = elib.getDataFromExcel("Org", 7, 3);
		// 2: navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// 3:click on create organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// 4: enter all the details
		CreatingOrganizationPage cop = new CreatingOrganizationPage(driver);
		cop.getOrgNameEdit().sendKeys(orgname);
		cop.getPhoneEdt().sendKeys(phoneNumber);
		cop.getSaveBtn().click();

		// 5.verify phone number
		OrganizationInfoPage oif = new OrganizationInfoPage(driver);
		String actualPhoneNumber = oif.getPhoneInfo().getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualPhoneNumber, phoneNumber);
	}

	@Test(groups = {"regressionTest"})
	public void createOrganizationWithType() throws Throwable {
		// read test script data from excel
		String orgname = elib.getDataFromExcel("Org", 4, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("Org", 4, 3);
		String type = elib.getDataFromExcel("Org", 4, 4);

		// 2: navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// 3:click on create organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// 4: enter all the details
		CreatingOrganizationPage cop = new CreatingOrganizationPage(driver);
		cop.createOrg(orgname, industry, type);

		// verify the industry and type
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualIndustry = oip.getIndustryInfo().getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualIndustry, industry);
		String actualType = oip.getTypeInfo().getText();
		softAssert.assertEquals(actualType, type);
	}
}
