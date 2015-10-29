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
			flipped=true;
			setImage(new Image(card.getBackImage()));
		}
		else{
			flipped=false;
			setImage(new Image(card.getFrontImage()));
		}
	}
	
	public void Remove(){
		card=null;
		flipped=false;
		setImage(null);
	}
}
