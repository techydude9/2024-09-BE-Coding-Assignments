package week05;

import java.util.ArrayList;
import java.util.List;

public class SpacedLogger implements Logger{
	
	List<String> newStr = new ArrayList<>();
	List<String> asterickLn = new ArrayList<>();
	
	public void log(String message) {
		System.out.println("\t***" + message + "***");
		
	} // End of log method
	
	public void error(String message) {
		
		System.out.println("*****Error: " + message + "***********");
		
	} // End of error method
}
