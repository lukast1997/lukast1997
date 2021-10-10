package htec.qa.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class main {

	public static void main(String[] args) throws InterruptedException {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Milica\\Desktop\\selenium\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				
				LoginPage testLoginPage = new LoginPage(driver);
				testLoginPage.testLoginButton();
				
				testLoginPage.testInvalidInput("Invalid Email", "Password");
				driver.navigate().to(driver.getCurrentUrl());

				testLoginPage.testCorrectInputs("lukast1997@yahoo.com", "htectest1");
				
				TestCase testCase = new TestCase(driver);
				testCase.goToTestCasePage();
				Thread.sleep(2000);
				testCase.addNewTestCaseErrorMessages();
				
				for(int i=0; i<5;i++) {
					testCase.createValidTestCase("Test-"+ i, "rezultat", "step", 5);
					Thread.sleep(1500);
				}
				for(int i=0; i<5;i++) {
				testCase.editTitle(i);
				}
				testCase.deleteCases();
				
				System.out.println("Succesful Test!!!");			
	}
}
