package pallyPart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PageFactoryGuruLogin {

	WebDriver driver;
	
	@FindBy(name = "uid")
	WebElement userId;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(name = "btnLogin")
	WebElement loginButton;
	
public PageFactoryGuruLogin(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		//AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,100);
		//PageFactory.initElements(factory, this);
		
	}
	
	public void setUserId(String Username) {
		
		userId.sendKeys(Username);
		
	}
	
	public void setPassword(String Password) {
		
		password.sendKeys(Password);
		}
	
	public void clickLogin() {
		
		loginButton.click();
		
	}

	public void login(String Username, String Password) {
		System.out.println("vikas");
		setUserId(Username);
		setPassword(Password);
		clickLogin();
	}

}
