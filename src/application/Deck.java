package application;

import javafx.scene.image.Image;

/**
 * 
 * @author Tomas Majling
 *
 */
public class Deck {

	private Card[] deck;
	private String frontImage;

	public Deck(int deckSize, String frontImage) {
		this.deck = new Card[deckSize];
		this.frontImage = frontImage;
		populateDeck();
	}

	public void populateDeck() {

		int cardsToCreate = deck.length / 2;
		
		for(int i = 0; i < cardsToCreate; i++) {
			deck[i] = new Card(i + 1, this.frontImage, new Image("/images/" + i + ".png"));
			deck[i + 1] = new Card(i + 1, this.frontImage, new Image("/images/" + i + ".png"));
		}

	}

}
