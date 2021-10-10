package htec.qa.test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	LoginPage(WebDriver webDriver)
	{
		this.driver = webDriver;
	}
	
	void testLoginButton()
	{
		this.driver.get("https://qa-sandbox.ni.htec.rs/login");
		this.driver.manage ().window ().maximize();
		this.driver.findElement(By.className("full-width-btn")).click();
		String invalidEmail=this.driver.findElement(By.id("validation-msg")).getText();
		String expectedInvalidEmailMessage="Email is required";
		if (invalidEmail.equals(expectedInvalidEmailMessage))
		{
			System.out.println("Invalid Email message is working.");
		}
		else
		{
			System.out.println("Invalid Email message is not working.");
		}
		
		String invalidPassword = this.driver.findElements(By.id("validation-msg")).get(1).getText();
		System.out.println(invalidPassword);
		String expectedInvalidPasswordMessage = "Password is required";
		if (invalidPassword.equals(expectedInvalidPasswordMessage))
		{
			System.out.println("Invalid password message is working");
			
		}
		else 
		{
			System.out.println("Invalid password messsage is not working");
		}
	}
	
	void testInvalidInput (String email, String password) throws InterruptedException
	{
		this.driver.findElement(By.name("email")).sendKeys(email);
		this.driver.findElement(By.name("password")).sendKeys(password);
		this.driver.findElement(By.className("full-width-btn")).click();
		Thread.sleep(1000);
		String invalidInputs=this.driver.findElement(By.id("validation-msg")).getText();
		String expectedInvalidString = email.toLowerCase() + " is not authorized or wrong email/password combination";
		
		if (invalidInputs.equals(expectedInvalidString))
		{
			System.out.println("Invalid input message is correct");
		}
		else
		{
			System.out.println("Invalid input message is not shown");
		}
		this.driver.findElement(By.name("email")).clear();
		Thread.sleep(3000);
		this.driver.findElement(By.name("password")).click();
		this.driver.findElement(By.name("password")).clear();
		Thread.sleep(3000);


	}
	void testCorrectInputs (String email, String correctPassword) throws InterruptedException
	{
		this.driver.findElement(By.name("email")).sendKeys(email);
		this.driver.findElement(By.name("password")).sendKeys(correctPassword);
		this.driver.findElement(By.className("full-width-btn")).click();
		Thread.sleep(3000);
		String url = this.driver.getCurrentUrl();
		String expectedUrl = "https://qa-sandbox.ni.htec.rs/dashboard";
		System.out.println(url);
		if (url.equals(expectedUrl))
		{
			System.out.println("Login worikng.");
		}
		else
		{
			System.out.println("Login is not working.");
		}

	}
	
	
}
