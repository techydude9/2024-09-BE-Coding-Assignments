package week06;
/*
 * November 3 - 9, 2024 BE Coding Final
 * Author: Bob Ruzga
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	List<Card> cards = new ArrayList<Card>();
	
	Deck () {
		String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};
		String[] numberLits = {"Two", "Three", "Four", "Five", "Six", "Seven",
								"Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
		String tempCardName = " ";
		
		for (String suit : suits) {
			int cardVal = 2;
			for (String cardLit : numberLits) {
				tempCardName = cardLit + " of " + suit;
				cards.add(new Card(tempCardName, cardVal));
				cardVal++;
			}
		}
	}  // End of Deck
	
	
	// getters and setters
	public List<Card> getCards() {
		return cards;
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	// end of getters and setters
	
	// describe method
	public void describe() {
		for (Card card : this.cards) {
			card.describe();
		}
	}
	
	// shuffle method
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
	
	// draw a card method
	public Card draw() {
		Card card = this.cards.remove(0);
		return card;
	}
	
} // end of Deck class
