package application;

import javafx.scene.image.Image;

/**
 * 
 * @author Tomas Majling
 *
 */
public class Card {

	private int value;
	private Image frontImage;
	private Image backImage;

	public Card(int value, String frontImage, Image backImage) {
		this.value = value;
		this.frontImage = new Image("/images/" + frontImage);
		this.backImage = backImage;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Image getFrontImage() {
		return frontImage;
	}

	public void setFrontImage(Image frontImage) {
		this.frontImage = frontImage;
	}

	public Image getBackImage() {
		return backImage;
	}

	public void setBackImage(Image backImage) {
		this.backImage = backImage;
	}

}
