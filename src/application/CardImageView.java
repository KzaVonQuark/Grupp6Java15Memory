package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardImageView extends ImageView {

	Card card;
	boolean flipped;

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
	public boolean isFlipped() {
		return flipped;
	}

	public void Flip() {
		if(!isFlipped()){
			//Animering insert h�r
			this.flipped = true;
			this.setImage(new Image(card.getBackImage()));
		}
		else{
			//Animering to Front insert h�r
			this.flipped = false;
			this.setImage(new Image(card.getFrontImage()));
		}
	}
	
	public void Remove(){
		this.card = null;
		this.setImage(null);
	}
}
