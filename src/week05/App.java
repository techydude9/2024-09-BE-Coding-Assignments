package week05;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		AsteriskLogger asterickLogIt = new AsteriskLogger();
		SpacedLogger spacedLogIt = new SpacedLogger();
		Scanner wordIn = new Scanner(System.in);
		
		//Prompt User to enter a word for this project. I added this so I could test several words
				String message = "";
				System.out.print("Please enter a word to use: ");
				message = wordIn.next();

		// SysOut a section header for the AsterickLogger Class
		System.out.println("------------------------------");
		System.out.println("--- AsteriskLogger Section\n");
		
		// Call the AsterickLogger using the instance asterickLogIt and run both methods
		asterickLogIt.log(message);
		asterickLogIt.error(message);
		
		// SysOut a section header for the SpacedLogger Class
		System.out.println("------------------------------");
		System.out.println("--- SpacedLogger Section\n");
		
		// Call the SpacedLogger using the instance spacedLogIt and run both methods
		spacedLogIt.log(message);
		spacedLogIt.error(message);
		
		// Print out a project end message
		System.out.println("\n\t******  Successful End of Week 5 Coding Project ******");
		
		// Close the Scanner
		wordIn.close();
		
	}

}

