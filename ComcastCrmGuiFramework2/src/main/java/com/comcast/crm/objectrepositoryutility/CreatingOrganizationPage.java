package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingOrganizationPage extends WebDriverUtility {
	WebDriver driver ;
	public CreatingOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDd;
	@FindBy(name ="phone")
	private WebElement phoneEdit;
	@FindBy(name = "accounttype")
	private WebElement typeDd; 
	
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getIndustryDd() {
		return industryDd;
	}
	public WebElement getPhoneEdt() {
		return phoneEdit;
	}
	public WebElement getTypeDd() {
		return typeDd;
	}
	public void createOrg(String orgname) {
		orgNameEdit.sendKeys(orgname);
		saveBtn.click();
	}
	public void createOrg(String orgname , String industry, String accountType) {
	orgNameEdit.sendKeys(orgname);
	select(industryDd, industry);
	select(typeDd, accountType);
	saveBtn.click();
	}
}	
