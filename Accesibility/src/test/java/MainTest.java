import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Com.AXE;

//import com.deque.axe.AXE;

public class MainTest {

	private static final URL scriptUrl = MainTest.class.getResource("/axe.min.js");
	public static TestName testName = new TestName();
	public static WebDriver driver;
//	public static String URL = "http://www.soprasteria.in/about-us/sopra-steria-india";
	public static String URL = "http://www.google.com/";
	
	public static String includeRules = "'best-practice'";
//	public static String includeRules = "'wcag2a', 'wcag2aa'";
	public static String excludeRules = "'experimental'";
	
	MainTest()
	{}
	
	public static JSONArray axeMethod(WebDriver driver)
	{
//		Reports.info("URL", driver.getCurrentUrl());
//		Following code will check accessblity for specific object
//		-------------------------- For Complete Accessibility of a page -------------------------------- 
//		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
//		-------------------------- On a Specific object defined in analyze as a parameter ------------------------
//		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze(driver.findElement(By.cssSelector("#lst-ib")));
//		-------------------------- If a specific rule is to be omitted from accessibility testing -------------------------
//		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).options("{rules:{'aria-required-attr':{enabled:false } } }").analyze(); // here in JSON format rules can be added
// 		-------------------------- If any of the rules like wca2 A or AA or AAA needs to be omitted 
		//		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).options("{runOnly: {type: 'tags',value: {include: ["+includeRules+"], exclude: ["+excludeRules+"]}}}").analyze(); // here in JSON format rules can be added
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).options("{runOnly: {type: 'tag',values: ["+includeRules+"]}}").analyze(); // here in JSON format rules can be added
		JSONArray violations = responseJSON.getJSONArray("violations");
//		AXE.writeResults(testName.getMethodName(), responseJSON);
		// Following code will add voilations in report
//		AXE.writeResults(testName.getMethodName(), responseJSON);
		
		// line 1291
		System.out.println(AXE.report(violations));
		return violations;
	}
	
	public static void accessblityTest()
	{
		Reports.info("URL", driver.getCurrentUrl());
		JSONArray violations = null;
		violations = axeMethod(driver);
		System.out.println(violations);   // Vishal  uncomment this
		checkAxeVoilations(violations);   // Vishal  uncomment this
	}
	
	public static void checkAxeVoilations(JSONArray violations)
	{
		for (Object allVoilations : violations) 
		{	
//            System.out.println(new JSONObject(value).getString("help"));
//            JSONObject parentObj = new JSONObject(allVoilations.toString());
            String help = new JSONObject(allVoilations.toString()).getString("help");
            JSONArray nodesarray = new JSONObject(allVoilations.toString()).getJSONArray("nodes");
           
            for(Object nodes : nodesarray)
            {
            	System.out.println(nodes.toString());
            	JSONObject anyObj = new JSONObject(nodes.toString());
            	System.out.println("IMPACT :"+anyObj.getString("impact"));
            	System.out.println("HTML :"+anyObj.getString("html"));
            	String sHTML = "HTML :"+anyObj.getString("html");
            	
            	sHTML = sHTML.replaceAll("<", "").replaceAll(">", "");
            	
            	String impact 		= "";
        		String message 		= "";
        		String sID 			= "";
        		JSONArray noneArray = anyObj.getJSONArray("none");
        		for(Object nonObj : noneArray)
        		{
        			JSONObject noneObj1 = new JSONObject(nonObj.toString());
        			message = "Message :- "+noneObj1.getString("message")+"<br>"+message; 
        			impact = "Impact :- "+noneObj1.getString("impact");
        			sID 	= "ID : "+noneObj1.getString("id");
        		}
        		if(!message.isEmpty())
            	{
            		Reports.fail(help, sHTML,impact, message, sID);
            	}
        		message = "";
        		impact = "";
        		sID ="";
            	JSONArray anyArray = anyObj.getJSONArray("any");
            	for(Object any :anyArray)
            	{	
            		JSONObject dataObj 	= new JSONObject(any.toString());
            		impact 				= "IMPACT : "+dataObj.getString("impact");
            		sID 				= "ID : "+dataObj.getString("id");
            		
            		System.out.println("IMPACT : "+dataObj.getString("impact"));
            		System.out.println("MESSAGE : "+dataObj.getString("message"));
            		System.out.println("ID : "+dataObj.getString("id"));
            		 
            		message = "Message :- "+dataObj.getString("message")+"<br>"+message;	
            	}
            	if(!message.isEmpty())
            	{
            		Reports.fail(help, sHTML,impact, message, sID);
            	}
            	message ="";
            	sHTML ="";
            }
            System.out.println("Out from nodes loop");

            
        } 
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		MainTest obj = new MainTest();
		try {
			Reports.SetUp();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		Reports.start("Accessblity test");
		System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,120);;
//		driver.get("https://www.killerfeatures.com/list-of/mobile/phones-with-stylus-price-list");
//		driver.get("https://www.google.co.in/?gfe_rd=cr&dcr=0&ei=wTpCWquhKtCdXsu1kuAP");
		driver.get(URL);
	
		

		accessblityTest();
		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		accessblityTest();
		
		driver.findElement(By.cssSelector("#identifierId")).sendKeys("SopraAutomation");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("sopra@123");
        driver.findElement(By.xpath("//span[text()[contains(.,'Next')]]")).click();
        /* Search EPO.org in Google */
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#lst-ib")).sendKeys("epo.org");
        driver.findElement(By.cssSelector("input[value='Google Search']")).click();
        accessblityTest();
//        
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a[href='https://www.epo.org/']"))));
//        driver.findElement(By.cssSelector("a[href='https://www.epo.org/']")).click();
//        accessblityTest();
//        /*Navigating to Contact us Page*/
//        driver.findElement(By.xpath("(//a[@href='service-support/contact-us.html'])[1]")).click();
//        accessblityTest();
//        /* Navigate to Write Customer Service */
//        driver.findElement(By.xpath("(//a[@href='contact-us/contact0-form.html'])[1]")).click(); // Will click on 'Write to Customer Services' link
//        accessblityTest();
//		
//        /* Navigate to Chrome History */
//        driver.navigate().to("chrome://history/");   
//        accessblityTest();  
		
		driver.close();
		
}
	}



//import java.io.IOException;
//import java.net.URL;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.junit.rules.TestName;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//
//import Com.AXE;
//import Com.AXE.Builder;
//
////import com.deque.axe.AXE;
//
//public class MainTest {
//
//	private static final URL scriptUrl = MainTest.class.getResource("/axe.min.js");
//	public static TestName testName = new TestName();
////	public static String URL = "http://www.soprasteria.in/about-us/sopra-steria-india";
//	public static String URL = "https://accounts.google.com/signin/v2/identifier?hl=en&passive=true&continue=https%3A%2F%2Fwww.google.co.in%2F%3Fgfe_rd%3Dcr%26dcr%3D0%26ei%3DlkxHWo_MIc6dXpKajsAG&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
//	
//	void Maintest()throws IOException
//	{
//		
//	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		try {
//			Reports.SetUp();
//			}
//			catch(Exception e)
//			{
//				System.out.println(e.getMessage());
//			}
//		Reports.start("Accessblity test");
//		
//		System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//			
////		driver.get("https://www.killerfeatures.com/list-of/mobile/phones-with-stylus-price-list");
////		driver.get("https://www.google.co.in/?gfe_rd=cr&dcr=0&ei=wTpCWquhKtCdXsu1kuAP");
//		driver.get(URL);
//		
//	
//		Reports.info("URL", URL);
//		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
//							
//		JSONArray violations = responseJSON.getJSONArray("violations");
//		System.out.println(violations);
//	
//		// Code added by Vishal
////		System.out.println(responseJSON.isNull("violations"));
//		int counter = 1;
//		for (Object allVoilations : violations) 
//		{	
////            System.out.println(new JSONObject(value).getString("help"));
//            JSONObject parentObj = new JSONObject(allVoilations.toString());
//            
//            System.out.println("$$$$$$$$$$$$$$$$$$");
//            System.out.println("$$$$$$$$$$$$$$$$$$");
//            System.out.println("$$$$$$$$$$$$$$$$$$");
//            System.out.println("HELP :"+parentObj.getString("help"));
//            String help = parentObj.getString("help");
//            JSONArray nodesarray = parentObj.getJSONArray("nodes");
//           
//            for(Object nodes : nodesarray)
//            {
//            	System.out.println(nodes.toString());
//            	JSONObject anyObj = new JSONObject(nodes.toString());
//            	System.out.println("IMPACT :"+anyObj.getString("impact"));
//            	System.out.println("HTML :"+anyObj.getString("html"));
//            	String sHTML = "HTML :"+anyObj.getString("html");
//            	
//            	sHTML = sHTML.replaceAll("<", "").replaceAll(">", "");
//            	
//            	String impact 		= "";
//        		String message 		= "";
//        		String sID 			= "";
//        		JSONArray noneArray = anyObj.getJSONArray("none");
//        		for(Object nonObj : noneArray)
//        		{
//        			JSONObject noneObj1 = new JSONObject(nonObj.toString());
//        			message = "Message :- "+noneObj1.getString("message")+"<br>"+message; 
//        			impact = "Impact :- "+noneObj1.getString("impact");
//        			sID 	= "ID : "+noneObj1.getString("id");
//        		}
//        		if(!message.isEmpty())
//            	{
//            		Reports.fail(help, sHTML,impact, message, sID);
//            	}
//        		message = "";
//        		impact = "";
//        		sID ="";
//            	JSONArray anyArray = anyObj.getJSONArray("any");
//            	for(Object any :anyArray)
//            	{	
//            		String newVal = "";
//            		String oldVal = "";
//            		
//            		JSONObject dataObj 	= new JSONObject(any.toString());
//            		impact 				= "IMPACT : "+dataObj.getString("impact");
////            		message 			= "MESSAGE : "+dataObj.getString("message");
//            		sID 				= "ID : "+dataObj.getString("id");
//            		
////            		System.out.println("target : "+dataObj.getString("target"));
//            		System.out.println("IMPACT : "+dataObj.getString("impact"));
//            		System.out.println("MESSAGE : "+dataObj.getString("message"));
//            		System.out.println("ID : "+dataObj.getString("id"));
//            		 
////            		if(sHTML.equalsIgnoreCase(sHTML) && impact)
////            		{
//            			message = "Message :- "+dataObj.getString("message")+"<br>"+message;	
////            		}
//            
////            		Reports.fail(help, sHTML,impact, message, sID);
////            		Reports.fail(help, "MESSAGE : "+dataObj.getString("message"));
////            		Reports.fail(help, "ID : "+dataObj.getString("id"));
////            		System.out.println(dataObj.getString("data"));
////            		JSONArray dataArray = dataObj.getJSONArray("data");
////            		for(Object data : dataArray)
////            		{
////            			System.out.println(data);
////            		}
//            		
//            		
////            		String any1 =  any.toString();
////            		Reports.fail(help, any1);
////            		System.out.println("ANY : "+any.toString());
//            	}
//            	if(!message.isEmpty())
//            	{
//            		Reports.fail(help, sHTML,impact, message, sID);
//            	}
//            	message ="";
//            	sHTML ="";
//            }
//            System.out.println("Out from nodes loop");
////            JSONObject res = obj.getJSONArray("nodes").getJSONObject(counter);
////            JSONObject obj1 = new JSONObject(value.toString());
////            JSONArray res1 = obj1.getJSONArray("any");
////            for(Object o : res1)
////            {
////            	JSONObject anyObject = new JSONObject(o.toString());
////            	JSONObject res3 = anyObject.getJSONArray("data").getJSONObject(1);
////            	System.out.println(res3);
////            	
////            }
////            
////            System.out.println(res.getString("impact"));
////            System.out.println(res.getString("html"));
////            counter ++;
//            
//        } 
//		
////		for(int i = 0; i<=violations.length()-1; i++)
////		{
////			System.out.println(violations.get(i));
////			System.out.println(violations.getString(i).length());
////		}
////		for(int i = 0; i<=violations.length()-1; i++)
////		{
////			System.out.println(violations.getString(i).toString());
////		}
////		
//		
//		
//		
//		
//		///////////////////////////////////////
//		
//		
////		if (violations.length() == 0) {
////		//	assertTrue("No violations found", true);
////			System.out.println("No Voilations found");
////		} else {
////			AXE.writeResults(testName.getMethodName(), responseJSON);
////			System.out.println(testName.getMethodName());
////			//assertTrue(AXE.report(violations), false);
////			System.out.println(AXE.report(violations));
////			System.out.println("Voilations found");
////		}
//
//}
//		
//	}
//
//
