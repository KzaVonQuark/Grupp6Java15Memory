package application;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 
 * @author Tomas Majling
 *
 */
public class Deck {

	// --------- Instance Variables ---------- //
	private LinkedList<Card> deck = new LinkedList<>();

	// --------- Constructors ----------- //

	// Create a deck with fixed size and front image
	public Deck(int deckSize, String frontImage) {
		populateDeck(deckSize, frontImage);
		shuffleDeck();
	}

	// Create an empty deck
	public Deck() {
	}

	// ---------- Getters ------------ //
	public LinkedList<Card> getDeck() {
		return deck;
	}

	public int getDeckSize() {
		return deck.size();
	}

	// --------- Methods ---------- //
	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public void populateDeck(int deckSize, String frontImage) {
		LinkedList<Integer> imageList = new LinkedList<Integer>();
		for (int i = 0; i < 24; i++) {
			imageList.add(i+1);
		}
		Collections.shuffle(imageList);
		int selectedMotive;
		for (int i = 0; i < (deckSize / 2); i++) {
			selectedMotive=imageList.pop();
			deck.add(new Card(selectedMotive, frontImage, selectedMotive));
			deck.add(new Card(selectedMotive, frontImage, selectedMotive));
		}
	}

	// Deal card to gameboard
	public Card dealCard(int cardInDeck) {
		return deck.get(cardInDeck);
	}

	// Add card to players win hand
	public void addCardToDeck(Card card) {
		for (Card playerCard : deck) {
			if (playerCard.getValue() == card.getValue()) {
				deck.push(playerCard);
			}
		}
	}

}
