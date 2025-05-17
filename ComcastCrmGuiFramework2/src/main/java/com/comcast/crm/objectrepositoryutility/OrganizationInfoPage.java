package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement organizationNameInfo;
	@FindBy(id ="dtlview_Phone")
	private WebElement phoneInfo;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement industryInfo;
	@FindBy(id = "dtlview_Type")
	private WebElement typeInfo;

	public WebElement getPhoneInfo() {
		return phoneInfo;
	}

	public WebElement getIndustryInfo() {
		return industryInfo;
	}

	public WebElement getTypeInfo() {
		return typeInfo;
	}

	public WebElement getOrganizationNameInfo() {
		return organizationNameInfo;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}
}
