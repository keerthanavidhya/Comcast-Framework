package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class getProductInfo {
	@Test(dataProvider = "getData")
	public void getProductDetails(String brandname , String productname) throws InterruptedException{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandname ,Keys.ENTER);
		String x= "//span[contains(text(),'"+productname+"')]/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span[1]/span[2]/span[2]";
		String price= driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		//span[starts-with(text(),'HP 15, 12th Gen Intel')]/../../../../div[3]/div[1]/div/div[1]/div[2]/div[1]/a/span[1]/span[2]/span[2]
	
	}
	@DataProvider
		public Object[][] getData() throws Throwable{
		ExcelUtility elib = new ExcelUtility();
		int rowCount = elib.getRowCount("product");
		
		Object[][] objArr = new Object[rowCount][2];
		for(int i =0; i<rowCount ; i++) {
			objArr[i][0] = elib.getDataFromExcel("Product", i+1, 0);
			objArr[i][1] = elib.getDataFromExcel("Product", i+1, 1);
			}
		return objArr;
			
		}
	

}
