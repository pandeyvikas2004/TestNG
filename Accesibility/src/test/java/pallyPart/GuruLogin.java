package pallyPart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GuruLogin {

	WebDriver driver;
	
	By userId = By.name("uid");
	
	By password = By.name("password");
	
	By loginButton	= By.name("btnLogin");
	
	
	public GuruLogin(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void setUserId(String Username) {
		
		this.driver.findElement(userId).sendKeys(Username);
		
	}
	
	public void setPassword(String Password) {
		
		this.driver.findElement(password).sendKeys(Password);
		}
	
	public void clickLogin() {
		
		driver.findElement(loginButton).click();
		
	}

	public void login(String Username, String Password) {
		System.out.println("vikas");
		setUserId(Username);
		setPassword(Password);
		clickLogin();
	}


}
