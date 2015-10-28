package application;

import javafx.scene.image.ImageView;

public class CardImageView extends ImageView {

	int cardValue;

	public CardImageView(String url, int cardValue) {
		super(url);
		this.cardValue = cardValue;
	}

	public int getCardValue() {
		return cardValue;
	}

	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}

}
