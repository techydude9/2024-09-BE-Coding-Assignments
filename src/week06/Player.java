package week06;

import java.util.ArrayList;
import java.util.List;

/*
 * November 3 - 9, 2024 BE Coding Final
 * Author: Bob Ruzga
 * 
 */

public class Player {
	String playerName;
	int playerScore;
	List<Card> playerHand;
	
	Player(String playerName) {
		this.playerName = playerName;
		this.playerScore = 0;
		this.playerHand = new ArrayList<>();
	}  // end of Player constructor

	// describe method
	public void describe() {
		System.out.println("\n---------------------------------------");
		System.out.println("Player: " + getPlayerName());
		System.out.println("\tHas a score of: " +getPlayerScore());
		System.out.println("\t and the following cards:");
			
		int ctr = 0;
		for (Card aCard : playerHand) {
			aCard.describe();
			ctr += 1;
		}
		
		System.out.println("\nTotal cards in hand: " + ctr);
		System.out.println("\n-----------------------------");
		
	} // end of describe method
	
	// getter/setter for playerName
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	// getter/setter for playerScore
	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	// getter/setter for playerHand
	public List<Card> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}
	// End of getters and setters
	
	// incrementScore method
	public void incrementScore() {
		this.playerScore += 1;
	}
	
	// draw cards for this player
	public void draw(Deck shuffledDeck) {
		this.playerHand.add(shuffledDeck.draw());
	 }
		
	// Flip a card for a players turn
	public Card flip() {
		return this.playerHand.remove(0);
	}
	
	
} // end of Player class
