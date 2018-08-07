package pallyPart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	 String propfile = "D:\\Projects\\RnD\\Accessibility Testing\\Accesibility\\src\\main\\resources\\Property\\Config.properties";
	 static Properties prop;
	 BufferedReader br;
	
	public ReadPropertyFile() throws IOException {
		
		br = new BufferedReader(new FileReader(propfile));
		prop = new Properties();
		prop.load(br);
		
	}
	
	
	public static void main(String args[]) throws IOException {
		
		ReadPropertyFile rpf = new ReadPropertyFile();
		System.out.println(prop.getProperty("name"));
		
		
	}
	
	
}
