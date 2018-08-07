package pallyPart;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Sikuliexample {
	
	
	WebDriver driver;
@BeforeTest
public void setup() {
	System.setProperty("webdriver.chrome.driver","D:\\Software\\Drivers\\ChromeDriver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("http://demo.guru99.com/test/image_upload/index.php");
}

	
	
	
  @Test
  public void f() {

	  Screen s = new Screen();
	  
	  Pattern fileInputTextBox = new Pattern(filepath + "FileTextBox.PNG");
      Pattern openButton = new Pattern(filepath + "OpenButton.PNG");
	  
	  
	  
  }
}
