package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingContactPage extends WebDriverUtility{
	WebDriver driver;
	public CreatingContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (name = "lastname")
	private WebElement lastnameEdit;
	@FindBy (name = "support_start_date")
	private WebElement startDateEdit;
	@FindBy (name ="support_end_date")
	private WebElement endDateEdit;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement selectOrgPlusBtn;
	@FindBy (xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	@FindBy(name = "search_text")
	private WebElement searchOrgEdit;
	@FindBy(xpath = "//input[@type='button']")
	private WebElement searchBtn;
	
	
	public WebElement getLastnameEdit() {
		return lastnameEdit;
	}
	public WebElement getStartDateEdit() {
		return startDateEdit;
	}
	public WebElement getEndDateEdit() {
		return endDateEdit;
	}
	public WebElement getSelectOrgPlusBtn() {
		return selectOrgPlusBtn;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void createContactwithSupportDate(String lastName,String startdate, String endDate) {
		lastnameEdit.sendKeys(lastName);
		startDateEdit.clear();
		startDateEdit.sendKeys(startdate);
		endDateEdit.clear();
		endDateEdit.sendKeys(endDate);
		saveBtn.click();
	}
	public void createContactWithOrg(String lastName, String orgName) {
		lastnameEdit.sendKeys(lastName);
		selectOrgPlusBtn.click();
		switchWindowBasedOnUrl(driver, "module=Account");
		searchOrgEdit.sendKeys(orgName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchWindowBasedOnUrl(driver, "module=Contacts");
		saveBtn.click();
		
		
	}
	
	

}
