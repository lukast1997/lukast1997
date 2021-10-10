package htec.qa.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCase {
	
	private WebDriver driver;

	TestCase(WebDriver webDriver)
	{
		this.driver = webDriver;
	}
	
	void goToTestCasePage ()
	{
		driver.navigate().to("https://qa-sandbox.ni.htec.rs/testcases");
	}
	void addNewTestCaseErrorMessages () throws InterruptedException
	{
		this.driver.findElement(By.className("navigate-buttons")).click();
		Thread.sleep(1000);
		String url = this.driver.getCurrentUrl();
		String expectedURL = "https://qa-sandbox.ni.htec.rs/new-testcase";
		if (url.equals(expectedURL))
		{
			driver.findElement (By.xpath ("//*[contains(text(),'Submit')]")).click();
			List<WebElement> list;
			list = this.driver.findElements(By.id("validation-msg"));
			
			if (!list.get(0).getText().equals("Title is required"))
				System.out.println("Title error messege isn't working.");
			if (!list.get(1).getText().equals("Expected result is required"))
				System.out.println("Expected result error messege isn't working.");
			if (!list.get(2).getText().equals("There must be at least one test step"))
				System.out.println("Test steps error messege isn't working.");		
		}
		driver.navigate().to("https://qa-sandbox.ni.htec.rs/testcases");

	}
	void createValidTestCase (String name, String expectedResult, String testStep, int numberOfSteps) throws InterruptedException
	{
		
		Thread.sleep(1000);
		driver.findElement (By.xpath ("//*[contains(text(),'New Test Case')]")).click();
		Thread.sleep(1000);
		this.driver.findElement (By.name("title")).sendKeys(name);
		this.driver.findElement (By.name("expected_result")).sendKeys(expectedResult);
		this.driver.findElement (By.name("step-0")).sendKeys(testStep);
		for(int i=1;i<numberOfSteps;i++)
		{
			this.driver.findElement(By.className("full-width-btn--label")).click();
			this.driver.findElement(By.name("step-" + i)).sendKeys("Test step " + i);
		}
		driver.findElement (By.xpath ("//*[contains(text(),'Submit')]")).click();
	}
	
	
}
