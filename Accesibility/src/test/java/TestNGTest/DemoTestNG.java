package TestNGTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoTestNG {
	WebDriver driver;
  @Test
  public void setup() {
	  
	  System.setProperty("webdriver.chrome.driver","D:\\Software\\Drivers\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/image_upload/index.php");
		driver.close();
		
	  
  }
  @Test
  public void setup1() {
	  
	  System.setProperty("webdriver.chrome.driver","D:\\Software\\Drivers\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/image_upload/index.php");
		driver.close();
  }

  @Test
  public void setup2() {
	  
	  System.setProperty("webdriver.chrome.driver","D:\\Software\\Drivers\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/image_upload/index.php");
		driver.close();
  }

  @Test
  public void setup3() {
	  
	  System.setProperty("webdriver.chrome.driver","D:\\Software\\Drivers\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/image_upload/index.php");
		driver.close();
  }



}
