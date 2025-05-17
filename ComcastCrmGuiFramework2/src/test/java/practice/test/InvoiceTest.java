package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.baseutility.BaseClass;
//@Listeners(com.comcast.crm.listenerUtility.ListenImplementation.class)
public class InvoiceTest{
	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListenerImpl.class)
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		//String actTitle = driver.getTitle();
		Assert.assertEquals(" ", " ");
		 System.out.println("step 1");
		 System.out.println("step 2");
		 System.out.println("step 3"); 
	}
	
	

}
