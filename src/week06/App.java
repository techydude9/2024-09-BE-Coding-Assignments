package week06;
/*
 * November 3 - 9, 2024 BE Coding Final
 * Author: Bob Ruzga
 */

public class App {

	public static void main(String[] args) {

		
	// Add your code here to instantiate a Deck	
    	Deck cardDeck = new Deck();
    
    // Call the describe method on the newly instantiated deck.
    	cardDeck.describe();
	
	
	// Deck shuffle() Method:
    	System.out.println("\nDeck shuffle() method");
    	cardDeck.shuffle();
	
	
	// Call the describe method on the newly shuffled deck.
		cardDeck.describe();

	// Testing Player
		Player players = new Player("Bob");
		players.describe();
		
		
		
		
		
	}  // end of main

}  // end of App class
