package application;

import javafx.scene.image.ImageView;

public class CardImageView extends ImageView {

	Card card;

	public CardImageView(String url, Card card) {
		super(url);
		this.card = card;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

}
