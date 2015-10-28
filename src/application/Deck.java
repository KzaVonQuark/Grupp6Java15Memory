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
		for (int i = 1; i < (deckSize / 2); i++) {
			deck.add(new Card(i + 1, frontImage, i + 1));
			deck.add(new Card(i + 1, frontImage, i + 1));
		}
	}

	// Deal card to gameboard
	public Card dealCard(int cardInDeck) {
		return deck.get(cardInDeck);
	}

	// Add card to players win hand
	public void addCardToDeck(CardImageView card) {
		for (Card playerCard : deck) {
			if (playerCard.getValue() == card.getCardValue()) {
				deck.push(playerCard);
			}
		}
	}

}
