package week06;

import java.util.Scanner;

/*
 * November 3 - 9, 2024 BE Coding Final
 * Author: Bob Ruzga
 */

public class App {

	public static void main(String[] args) {

		Scanner userIn = new Scanner(System.in);
		
	// Instantiate a Deck	
    	Deck cardDeck = new Deck();
    
    // Call the describe method on the newly instantiated deck to verify it created correctly.
    	cardDeck.describe();
	
	// Deck shuffle() Method:
    	System.out.println("\nDeck shuffle() method");
    	cardDeck.shuffle();
	
	// Call the describe method to verify the deck shuffled.
		cardDeck.describe();

	// 	Create 2 Players by prompting for their names
		System.out.println("\nFirst Player's name: ");
		Player player1 = new Player(userIn.next());
		player1.describe();
		
		System.out.println("\nSecond Player's name: ");
		Player player2 = new Player(userIn.next());
		player2.describe();
		
	// Let's deal the cards to the two new players
		for (int i = 0; i < 52; i++) {
			if (i % 2 == 0) {
				player1.draw(cardDeck);
			} else {
				player2.draw(cardDeck);
			}
		}
		
		// Using for testing only
		player1.describe();
		player2.describe();
		// remove after testing
		
		// close the Scanner
		userIn.close();
		
	}  // end of main

}  // end of App class
