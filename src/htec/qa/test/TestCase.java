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
		
		driver.findElement (By.xpath ("//*[contains(text(),'New Test Case')]")).click();
		this.driver.findElement (By.name("title")).sendKeys(name);
		this.driver.findElement (By.name("expected_result")).sendKeys(expectedResult);
		this.driver.findElement (By.name("step-0")).sendKeys(testStep);
		for(int i=1;i<numberOfSteps;i++)
		{
			this.driver.findElement(By.className("full-width-btn--label")).click();
			this.driver.findElement(By.name("step-" + i)).sendKeys("Test step " + i);
		}
		this.driver.findElement (By.xpath ("//*[contains(text(),'Submit')]")).click();
	}
	
	void editTitle(int i) throws InterruptedException
	{
		String searcTearm = "//*[contains(text(),'Test-"+i+"')]";
		WebElement element =this.driver.findElement (By.xpath (searcTearm));
		element.click();
		String url = this.driver.getCurrentUrl();
		String[] parts = url.split("/");
		String id = parts[parts.length-1];
		Thread.sleep(1000);
		this.driver.findElement (By.name("title")).clear();
		String type;
		if(Integer.parseInt(id)%2==0)
		{
			type = "Even";
		}
		else
		{
			type = "odd";
		}
		this.driver.findElement (By.name("title")).sendKeys("The "+ id + " of this use case is "+ type);
		this.driver.findElement(By.className("form-element--textarea")).click();
		this.driver.findElement(By.className("form-element--textarea")).clear();
		this.driver.findElement(By.className("form-element--textarea")).sendKeys("This Description belongs to " + id + " which is "+ type);
		this.driver.findElement (By.name("expected_result")).clear();
		this.driver.findElement (By.name("expected_result")).sendKeys("This Expected Result belongs to " + id + " which is "+ type);
		List<WebElement> steps;
		steps = this.driver.findElements(By.xpath("//*[contains(@name, 'step-')]"));
		for(int j=0;j<steps.size();j++) {
			steps.get(j).clear();
			steps.get(j).sendKeys("This Step-"+j+" belongs to " + id + " which is "+ type);
		}
		this.driver.findElement(By.className("react-switch-bg")).click();
		WebElement button = this.driver.findElement(By.xpath ("//*[contains(text(),'Submit')]"));
		button.click();
		driver.navigate().to("https://qa-sandbox.ni.htec.rs/testcases");

	}
	
	void deleteCases() throws InterruptedException
	{
		Thread.sleep(1000);
		int size = this.driver.findElements(By.className("preview-card-body")).size();
		for(int i=0;i<size;i++) 
		{
			WebElement element = this.driver.findElement(By.className("preview-card"));
			element.click();
			this.driver.findElement(By.xpath("//*[contains(@class,'danger')]")).click();
			WebElement button = this.driver.findElement(By.className("confirmation-dialog--buttons--confirm"));
			button.click();
			driver.navigate().to("https://qa-sandbox.ni.htec.rs/testcases");
		}
	}
}

