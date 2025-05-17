package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	WebDriver driver;
	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerContact;
	@FindBy(id ="dtlview_Last Name")
	private WebElement lastNameInfo;
	@FindBy(id ="mouseArea_Organization Name")
	private WebElement orgNameInfo;
	@FindBy(id="dtlview_Support Start Date")
	private WebElement startDateInfo;
	@FindBy(id="dtlview_Support End Date")
	private WebElement endDateInfo;
	public WebElement getHeaderContact() {
		return headerContact;
	}
	public WebElement getLastNameInfo() {
		return lastNameInfo;
	}
	public WebElement getOrgNameInfo() {
		return orgNameInfo;
	}
	public WebElement getStartDateInfo() {
		return startDateInfo;
	}
	public WebElement getEndDateInfo() {
		return endDateInfo;
	}

}
