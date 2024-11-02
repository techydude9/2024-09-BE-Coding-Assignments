package week05;

import java.util.ArrayList;
import java.util.List;

public class AsteriskLogger implements Logger{
	
	List<String> newStr = new ArrayList<>();
	List<String> asteriskLn = new ArrayList<>();
	StringBuffer tempStr = new StringBuffer();
	
	public void log(String message) {
		System.out.println("\t***" + message + "***");
	} // End of log method
	
	public void error(String message) {
		String printStarLn = "";
		
		// This for loop creates the askterisk banner to be the same size as the message plus the preset error msg
		for (int i = 0; i < message.length() + 13; i++) {
			asteriskLn.add("*");
		}
		
		for (String c : asteriskLn) {
			tempStr.append(c);
		}
		printStarLn = tempStr.toString();
		
		System.out.println(printStarLn);
		System.out.println("***Error: " + message + "***");
		System.out.println(printStarLn);
	} // End of error method

}  // End of AsteriskLogger Class
