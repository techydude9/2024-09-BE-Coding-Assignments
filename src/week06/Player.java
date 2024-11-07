package week06;

import java.util.ArrayList;
import java.util.List;

/*
 * November 3 - 9, 2024 BE Coding Final
 * Author: Bob Ruzga
 */

public class Player {
	String playerName;
	int playerScore;
	List<Card> playerHand = new ArrayList<>();
	
	Player(String playerName) {
		this.playerName = playerName;
		this.playerScore = 0;
	}

	// describe method
	public void describe() {
		System.out.println("\n---------------------------------------");
		System.out.println("Player: " + getPlayerName());
		System.out.println("\tHas a score of: " +getPlayerScore());
		System.out.println("\t and the following cards:");
		
		// test adding a card
		//playerHand.add.setName("Eight of Diamonds");
		// playerHand[0].setValue(8);
		
		int ctr = 0;
		
		for (Card aCard : playerHand) {
			aCard.describe();
			ctr =+ 1;
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
	
		
		
	
	
} // end of Player class
