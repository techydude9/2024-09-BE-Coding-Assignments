package week05;

public class SpacedLogger implements Logger{
	
	// Using StringBuilder to create the required output spaced string of the inputted word
	StringBuilder spacedLn = new StringBuilder();
	
	public void log(String message) {
		
		// Build the new String. Add a space between each character until I reach the end of the message
		for (int i = 0; i < message.length(); i++) {
			spacedLn.append(message.charAt(i));
			if (i < message.length()- 1) {
			spacedLn.append(' ');
			}
		}

		System.out.println("\t" + spacedLn);
		
	} // End of log method
	
	public void error(String message) {
		
		System.out.println("ERROR: " + spacedLn);
		
	} // End of error method
}
