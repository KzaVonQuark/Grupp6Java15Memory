package application;

import java.util.Collections;
import java.util.LinkedList;

import javafx.scene.image.Image;

/**
 * 
 * @author Tomas Majling
 *
 */
public class Deck {

	// --------- Instance Variables ---------- //

	private LinkedList<Card> deck = new LinkedList<>();
	private int deckSize;
	private String frontImage;

	// --------- Constructor ----------- //

	public Deck(int deckSize, String frontImage) {
		this.frontImage = frontImage;
		populateDeck();
		shuffleDeck();
	}

	// ---------- Getters ------------ //

	public LinkedList<Card> getDeck() {
		return deck;
	}

	public int getDeckSize() {
		return deckSize;
	}

	public String getFrontImage() {
		return frontImage;
	}

	// --------- Methods ---------- //

	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public void populateDeck() {
		for (int i = 0; i < (deckSize / 2); i++) {
			deck.add(new Card(i + 1, this.frontImage, new Image("/images/" + i + ".png")));
			deck.add(new Card(i + 1, this.frontImage, new Image("/images/" + i + ".png")));
		}
	}

	public Card dealCard() {
		return deck.pop();
	}

}
