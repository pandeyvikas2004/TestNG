

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {

	static ExtentReports report;
	static ExtentTest logger; 
	
	public static void SetUp(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String RepName = ((dateFormat.format(date)).replace(":","_")).replace("/","_"); //2016/11/16 12:08:43
		System.out.println(RepName);
		String sysPath = System.getProperty("user.dir")+"\\Reports\\"+ RepName;
		new File(sysPath).mkdir();
	 	report=new ExtentReports(sysPath+"\\Reports.html");
	}
	
	public static void start(String TCName) 
	{
	logger = report.startTest(TCName);
	}
	
public static void stop() {
		//System.out.println("stop");
		report.flush();
			}

	//public static class assertion {
		
	
   public static void verifyString(String Description,String exp,String act){
	   
	   if (exp.equalsIgnoreCase(act)) {
		  
		   logger.log(LogStatus.PASS,Description,"Expected: " + exp + "<br /> Actual: <b><strong>" + act);
	        
		      }
	   else {
		   
		   logger.log(LogStatus.FAIL,Description,"Expected: " + exp + "<br /> Actual: <b><font color=\"red\">" + act);
	        
		      }
   }
	
public static void verifyBoolean(String Description,boolean exp,boolean act){
	   
	   if (exp == act) {
		  
		   logger.log(LogStatus.PASS,Description,"<br /> Expected: <b>" + exp + "<br /> Actual: <b>" + act);
	        
		      }
	   else {
		   
		   logger.log(LogStatus.FAIL,Description,"<br /> Expected: <b>" + exp + "<br /> Actual: <b><font color=\"red\">" + act);
	        
		      }
   }
   
	   public static void verifyInt(String Description,int exp,int act){
		   
		   if (exp==act) {
			   logger.log(LogStatus.PASS,Description,"<br /> Expected: <b>" + exp + "<br /> Actual: <b>" + act);
			      }
		   else {
			   	logger.log(LogStatus.FAIL,Description,"<br /> Expected: <b>" + exp + "<br /> Actual: <b><font color=\"red\">" + act);
			    }
	}
	   public static void fail(String StepName,String Description)
	   {
		   logger.log(LogStatus.FAIL,StepName, "<b><font color=\"red\">"+Description);
		   report.flush();
	   }

	   public static void fail(String StepName,String sHTML, String String1, String String2, String String3)
	   {
		   logger.log(LogStatus.FAIL,StepName, "<b><font color=\"red\"><br>"+sHTML+"<br/>"+String1+ "<br/>"+String2+"<br/>"+String3);
		   report.flush();
	   }
public static void info(String StepName,String Description){
		   
		  	        logger.log(LogStatus.INFO, StepName, Description);
		  	      report.flush();
			      }
}

	
	
	

