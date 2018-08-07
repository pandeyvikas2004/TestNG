package pallyPart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;



public class NewTest {
  
	WebDriver driver;
	GuruLogin gurulogin;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver","D:\\Software\\Drivers\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/V4/");
	
	}
	
	
	@Test
  public void f() throws InterruptedException, AWTException {
		// This is for page object model
				//gurulogin = new GuruLogin(driver);
				//gurulogin.login("vikas","pandey");
			
		// This is for page factory
		
				//PageFactoryGuruLogin pg = new PageFactoryGuruLogin(driver);
				//pg.login("vikas", "pandey");
		
		// Example of Java script
			
				//JavascriptExecutor js = (JavascriptExecutor)driver;
				//		js.executeScript("alert('Vikas!');");
				//		Thread.sleep(5000);
				//		driver.switchTo().alert().accept();
		
		// Example of robot class
		
		PageFactoryGuruLogin pg = new PageFactoryGuruLogin(driver);
				pg.setUserId("vikas");
				pg.userId.sendKeys(Keys.TAB);
				Robot rb = new Robot();
				rb.keyPress(KeyEvent.VK_A);
	 }
}
