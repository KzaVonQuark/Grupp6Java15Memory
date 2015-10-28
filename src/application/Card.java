package application;

/**
 * 
 * @author Tomas Majling
 *
 */
public class Card {

	// --------- Instance Variables ---------- //
	private int value;
	private String frontImage;
	private String backImage;

	// --------- Constructor ----------- //
	public Card(int value, String frontImage, int backImage) {
		this.value = value;
		this.frontImage = "images/" + frontImage + ".png";
		this.backImage = "images/" + backImage + ".png";
	}

	// ---------- Getters and Setters ------------ //
	public int getValue() {
		return value;
	}

	public String getFrontImage() {
		return frontImage;
	}

	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}

	public String getBackImage() {
		return backImage;
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

}
