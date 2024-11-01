package week05;

public class AsteriskLogger implements Logger{
	
	public void log(String message) {
		System.out.println("\t***" + message + "***");
	} // End of log method
	
	public void error(String message) {
		System.out.println("*********************************");
		System.out.println("*****Error: " + message + "***********");
		System.out.println("*********************************");
	} // End of error method

}  // End of AsteriskLogger Class
 