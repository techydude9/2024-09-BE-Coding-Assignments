package week06;

import java.util.Scanner;

/*
 * November 3 - 9, 2024 BE Coding Final
 * Author: Bob Ruzga
 */

public class App {

	public static void main(String[] args) {

		Scanner userIn = new Scanner(System.in);
		int tieCounter = 0;
		String winnerBanner = " *** WINNER ***";
		
	// Instantiate a Deck	
    	Deck cardDeck = new Deck();
    
    // Call the describe method on the newly instantiated deck to verify it created correctly & not shuffled.
    	cardDeck.describe();
	
	// Deck shuffle() Method:
    	System.out.println("\nShuffling the Deck! Please Wait");
    	cardDeck.shuffle();
	
	// Call the describe method to verify the deck shuffled.
		cardDeck.describe();

	// 	Create 2 Players by prompting for their names
		System.out.println("\nFirst Player's name: ");
		Player player1 = new Player(userIn.next());
		// testing only player1.describe();
		
		System.out.println("\nSecond Player's name: ");
		Player player2 = new Player(userIn.next());
		// testing only player2.describe();
		
	// Let's deal the cards to the two new players
		for (int i = 0; i < 52; i++) {
			if (i % 2 == 0) {
				player1.draw(cardDeck);
			} else {
				player2.draw(cardDeck);
			}
		}  // end of for of deal
			
	// Let the Battle Begin ....
		for (int i = 1; i < 27; i++) {
			Card p1Card = player1.flip();
			Card p2Card = player2.flip();
		
			System.out.println("\t\tRound " + i);
			System.out.println("Player 1 card: " + p1Card.getName());
			System.out.println("\tPlayer 2 card: " + p2Card.getName());
			
			if (p1Card.getValue() == p2Card.getValue()) {
				System.out.println("This round is a TIE - No Points Awarded");
				tieCounter +=1;
			} else if (p1Card.getValue() < p2Card.getValue()) {
				System.out.println("\tPlayer 2 wins this round ");
				player2.incrementScore();
			} else {
				System.out.println("Player 1 wins this round ");
				player1.incrementScore();
			}
			
			// Announce current results after this round
			System.out.println("   *** Current Scores: ***");
			System.out.println("*** P1: " + player1.getPlayerName() + ": Score: " + player1.getPlayerScore());
			System.out.println("*** P2: " + player2.getPlayerName() + ": Score: " + player2.getPlayerScore());
			System.out.println("-----------------------------");
		} // end of for of battle round
		
		// Announce the results of this Game!
		System.out.println("\n\t******** Results of this Game ********");
		
		if (player1.getPlayerScore() == player2.getPlayerScore()) {
			System.out.println("---------- Its a Tie Game - No Winner - Bummer -------");
			System.out.println("Player 1: " + player1.getPlayerName() + ": Score: " + player1.getPlayerScore());
			System.out.println("Player 2: " + player2.getPlayerName() + ": Score: " + player2.getPlayerScore());
		} else if (player1.getPlayerScore() > player2.getPlayerScore()) {
			System.out.println("Player 1: " + player1.getPlayerName() + ": Score: " + player1.getPlayerScore() + winnerBanner);
			System.out.println("Player 2: " + player2.getPlayerName() + ": Score: " + player2.getPlayerScore());
		} else {
			System.out.println("Player 1: " + player1.getPlayerName() + ": Score: " + player1.getPlayerScore());
			System.out.println("Player 2: " + player2.getPlayerName() + ": Score: " + player2.getPlayerScore() + winnerBanner);
		}
		
		System.out.println("Tie Counter = " + tieCounter);
		System.out.println("-------------------- Thanks for Playing --------------------------------");
		
		
	// close the Scanner
	userIn.close();
		
	}  // end of main

}  // end of App class
