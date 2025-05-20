package com.comcast.crm.contactTest;


import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.baseutility.BaseClass;
import com.comcast.crm.listenerUtility.ListenImplementation;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
@Listeners(com.comcast.crm.listenerUtility.ListenImplementation.class)
public class CreateContactTest extends BaseClass {
	@Test(groups = {"smokeTest"})
	public void createContactTest() throws Throwable {
		// read test script data from excel
		ListenImplementation.test.log(Status.INFO, "Read data from Excel");
		String lastName = elib.getDataFromExcel("Contact", 1, 2)+ jlib.getRandomNumber();
		// 2: navigate to Contacts
		ListenImplementation.test.log(Status.INFO, "Navigate to contact page");
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		// 3:click on create Contacts button
		ListenImplementation.test.log(Status.INFO, "Create a new contact with lastname");
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();
		// 4: enter all the details
		CreatingContactPage ccp = new CreatingContactPage(driver);
		ccp.getLastnameEdit().sendKeys(lastName);
		ccp.getSaveBtn().click();
		ListenImplementation.test.log(Status.PASS,lastName+" is a new org created");
		// 5.verify Header msg
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actualHeaderMsg = cip.getHeaderContact().getText();
		boolean status = actualHeaderMsg.contains(lastName);
		Assert.assertEquals(status, true);

		// verify last name
		String actualLastName = cip.getLastNameInfo().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualLastName, lastName);
		soft.assertAll();
	}

	@Test(groups = {"regressionTest"})
	public void createContactWithSupportDate() throws Throwable {

		// read test script data from excel
		ListenImplementation.test.log(Status.INFO, "Read data from Excel");
		String lastName = elib.getDataFromExcel("Contact", 4, 2)+ jlib.getRandomNumber();
		// 2: navigate to contact
		ListenImplementation.test.log(Status.INFO, "Navigate to contact page");
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		// 3:click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();
		ListenImplementation.test.log(Status.PASS, "Create a new contact with start and end date");
		// 4: enter all the details
		CreatingContactPage ccp = new CreatingContactPage(driver);
		// logic to generate date and date after 30 days
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);
		ccp.createContactwithSupportDate(lastName, startDate, endDate);
		ListenImplementation.test.log(Status.PASS, lastName+ " contact created");
		// verify start date
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actualStartdate = cip.getStartDateInfo().getText();
		SoftAssert soft  = new SoftAssert();
		soft.assertEquals(actualStartdate, startDate);
		// verify end date
		String actualEnddate = cip.getEndDateInfo().getText();
		soft.assertEquals(actualEnddate, endDate);
		soft.assertAll();
	}

	@Test(groups = {"regressionTest"})
	public void createContactwithOrgTest() throws Throwable {

		// read test script data from excel
		ListenImplementation.test.log(Status.INFO, "Read data from Excel");
		String orgname = elib.getDataFromExcel("Contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("Contact", 7, 3) + jlib.getRandomNumber();
		// 2: navigate to organization
		ListenImplementation.test.log(Status.INFO, "Navigate to organization page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// 3:click on create organization button
		ListenImplementation.test.log(Status.INFO, "Navigate to new organization page");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		// 4: enter all the details
		CreatingOrganizationPage cop = new CreatingOrganizationPage(driver);
		cop.createOrg(orgname);
		ListenImplementation.test.log(Status.PASS, orgname+" is a new created org");
		// verify Header msg
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderMsg().getText();
		boolean status = headerInfo.contains(orgname);
		Assert.assertEquals(status, true);
		// 5.navigate to contact
		hp.getContactLink().click();
		ListenImplementation.test.log(Status.INFO, "Navigate to contact page");
		// 3:click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();
		ListenImplementation.test.log(Status.INFO, "Navigate to create new contact page");
		// 4: enter all the details
		CreatingContactPage ccp = new CreatingContactPage(driver);
		ccp.createContactWithOrg(contactLastName, orgname);
		ListenImplementation.test.log(Status.PASS, "New contat is created");

		// verify Header msg on contact page
		ContactInfoPage cip = new ContactInfoPage(driver);
		String headerInfo1 = cip.getHeaderContact().getText();
		boolean status1 = headerInfo1.contains(contactLastName);
		assertEquals(status1, true);
		// verify organization name on contact page to check a data flow
		String actOrgName = cip.getOrgNameInfo().getText();
		boolean stat = actOrgName.trim().equals(orgname);
		SoftAssert soft =  new SoftAssert();
		soft.assertEquals(stat, true);
		soft.assertAll();
	}
}
