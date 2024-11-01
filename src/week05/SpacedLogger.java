package week05;

public class SpacedLogger {
	
	public void log(String message) {
		System.out.println("\t***" + message + "***");
		
	} // End of log method
	
	public void error(String message) {
		
		System.out.println("*****Error: " + message + "***********");
		
	} // End of error method
}
