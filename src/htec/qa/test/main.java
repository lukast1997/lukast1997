package htec.qa.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class main {

	public static void main(String[] args) throws InterruptedException {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Milica\\Desktop\\selenium\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				
				LoginPage testLoginPage = new LoginPage(driver);
				testLoginPage.testLoginButton();
				
				testLoginPage.testInvalidInput("Dragan", "jakasifra");
				driver.navigate().to(driver.getCurrentUrl());

				testLoginPage.testCorrectInputs("lukast1997@yahoo.com", "htectest1");
				
				TestCase testCase = new TestCase(driver);
				testCase.goToTestCasePage();
				Thread.sleep(2000);
				testCase.addNewTestCaseErrorMessages();
				
				for(int i=0; i<5;i++) {
					testCase.createValidTestCase("Test-"+ i, "rezultat", "step", 5);
					Thread.sleep(1000);
				}
				
				
//				driver.get("https://qa-sandbox.ni.htec.rs/login");
//				driver.manage ().window ().maximize();
//				driver.findElement(By.className("full-width-btn")).click();
//				String invalidEmail=driver.findElement(By.id("validation-msg")).getText();
//				System.out.println(invalidEmail);
//				String expectedInvalidEmailMessage="Email is required";
//				if (invalidEmail.equals(expectedInvalidEmailMessage))
//				{
//					System.out.println("Invalid Email message is working.");
//				}
//				else
//				{
//					System.out.println("Invalid Email message is not working.");
//				}
//				
//				String invalidPassword = driver.findElements(By.id("validation-msg")).get(1).getText();
//				System.out.println(invalidPassword);
//				String expectedInvalidPasswordMessage = "Password is required";
//				if (invalidPassword.equals(expectedInvalidPasswordMessage))
//				{
//					System.out.println("Invalid password message is working");
//					
//				}
//				else 
//				{
//					System.out.println("Invalid password messsage is not working");
//				}
//				
				
				
	}

}
