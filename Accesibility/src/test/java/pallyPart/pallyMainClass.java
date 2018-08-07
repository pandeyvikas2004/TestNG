package pallyPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.exec.launcher.Java13CommandLauncher;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.gargoylesoftware.htmlunit.javascript.configuration.JavaScriptConfiguration;

public class pallyMainClass {
	public static void main(String args[]) 
	{ 
		try 
		{ 
			
//			Process process = Runtime.getRuntime().exec("D:\\Users\\vnihaniwal\\AppData\\Roaming\\npm\\node_modules\\pa11y\\node_modules\\phantomjs-prebuilt\\lib\\phantom\\bin\\phantomjs D:\\SeleniumProjects\\Accesibility\\Vishaljs.js");
//			int exitStatus = process.waitFor();
			
			Process p=Runtime.getRuntime().exec("cmd /c pa11y --reporter html https://accounts.google.com/signin/v2/identifier?hl=en&passive=true&continue=https%3A%2F%2Fwww.google.co.in%2F%3Fgfe_rd%3Dcr%26dcr%3D0%26ei%3DlkxHWo_MIc6dXpKajsAG&flowName=GlifWebSignIn&flowEntry=ServiceLogin > report123.html"); 
//			Process p=Runtime.getRuntime().exec("cmd /c pa11y --reporter html https://www.killerfeatures.com > D:\\Users\\vnihaniwal\\Desktop\\report.html");
			p.waitFor(); 
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String line=reader.readLine(); 
			while(line!=null) 
			{ 
				System.out.println(line); 
				line=reader.readLine(); 
			} 
		} 
		catch(IOException e1) {
			System.out.println("errors");
		} 
		catch(InterruptedException e2) {} 
	
		System.out.println("Done"); 
	} 
}
