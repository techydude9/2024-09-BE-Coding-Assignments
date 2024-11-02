package week05;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		AsteriskLogger asterickLogIt = new AsteriskLogger();
		SpacedLogger spacedLogIt = new SpacedLogger();
		Scanner wordIn = new Scanner(System.in);
		
		System.out.println("------------------------------");
		System.out.println("--- AsteriskLogger Section\n");
		
		String message = "";
		System.out.print("Please enter a word to use: ");
		message = wordIn.next();
		
		
		asterickLogIt.log(message);
		asterickLogIt.error(message);
		
		System.out.println("------------------------------");
		System.out.println("--- SpacedLogger Section\n");
		
		spacedLogIt.log(message);
		spacedLogIt.equals(message);
		
		
		System.out.println("\n\t******  Successful End of Week 5 Coding Project ******");
		
		
		wordIn.close();
		
	}

}

