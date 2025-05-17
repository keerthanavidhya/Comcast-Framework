package com.comcast.crm.contactTest;


import org.testng.annotations.Test;
import com.comcast.crm.generic.baseutility.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateContactTest extends BaseClass {
	@Test(groups = {"smokeTest"})
	public void createContactTest() throws Throwable {
		// read test script data from excel
		String lastName = elib.getDataFromExcel("Contact", 1, 2)+ jlib.getRandomNumber();
		// 2: navigate to Contacts
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		// 3:click on create Contacts button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();
		// 4: enter all the details
		CreatingContactPage ccp = new CreatingContactPage(driver);
		ccp.getLastnameEdit().sendKeys(lastName);
		ccp.getSaveBtn().click();
		// 5.verify Header msg
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actualHeaderMsg = cip.getHeaderContact().getText();
		if (actualHeaderMsg.contains(lastName)) {
			System.out.println(actualHeaderMsg + " is correct == pass");
		} else {
			System.out.println(actualHeaderMsg + " is not correct == fail");
		}

		// verify last name
		String actualLastName = cip.getLastNameInfo().getText();
		if (actualLastName.equals(lastName)) {
			System.out.println(actualLastName + " is displayed == pass");
		} else {
			System.out.println(actualLastName + " is  not displayed == fail");
		}
	}

	@Test(groups = {"regressionTest"})
	public void createContactWithSupportDate() throws Throwable {

		// read test script data from excel
		String lastName = elib.getDataFromExcel("Contact", 4, 2)+ jlib.getRandomNumber();
		// 2: navigate to contact
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		// 3:click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();
		// 4: enter all the details
		CreatingContactPage ccp = new CreatingContactPage(driver);
		// logic to generate date and date after 30 days
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);
		ccp.createContactwithSupportDate(lastName, startDate, endDate);
		// verify start date
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actualStartdate = cip.getStartDateInfo().getText();
		if (actualStartdate.equals(startDate)) {
			System.out.println(actualStartdate + " is displayed == pass");
		} else {
			System.out.println(actualStartdate + " is  not displayed == fail");
		}
		// verify end date
		String actualEnddate = cip.getEndDateInfo().getText();
		if (actualEnddate.equals(endDate)) {
			System.out.println(actualEnddate + " is displayed == pass");
		} else {
			System.out.println(actualEnddate + " is  not displayed == fail");
		}
	}

	@Test(groups = {"regressionTest"})
	public void createContactwithOrgTest() throws Throwable {

		// read test script data from excel
		String orgname = elib.getDataFromExcel("Contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("Contact", 7, 3) + jlib.getRandomNumber();
		// 2: navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// 3:click on create organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		// 4: enter all the details
		CreatingOrganizationPage cop = new CreatingOrganizationPage(driver);
		cop.createOrg(orgname);
		// verify Header msg
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderMsg().getText();
		if (headerInfo.contains(orgname)) {
			System.out.println(orgname + " is created == pass");
		} else {
			System.out.println(orgname + " is  not created == fail");
		}
		// 5.navigate to contact
		hp.getContactLink().click();
		// 3:click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();
		// 4: enter all the details
		CreatingContactPage ccp = new CreatingContactPage(driver);
		ccp.createContactWithOrg(contactLastName, orgname);

		// verify Header msg on contact page
		ContactInfoPage cip = new ContactInfoPage(driver);
		String headerInfo1 = cip.getHeaderContact().getText();
		if (headerInfo1.contains(contactLastName)) {
			System.out.println(contactLastName + " is created == pass");
		} else {
			System.out.println(contactLastName + " is  not created == fail");
		}

		// verify organization name on contact page to check a data flow
		String actOrgName = cip.getOrgNameInfo().getText();
		if (actOrgName.trim().equals(orgname)) {
			System.out.println(orgname + " information is correct- data flow is done");
		} else {
			System.out.println(orgname + " information is correct- data flow is done");
		}
	}
}
