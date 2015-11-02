package application;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Rules {

	private int timePassed = 0;
	private int timeToSeeCard = 2; // Man har 2 sekunder p� sig att se korten
									// innan
									// de f�rsvinner eller flippas tillbaka.
	private CardImageView cardOne = null;
	private CardImageView cardTwo = null;
	
	private AudioClip nopairSound = new AudioClip(new File("src/Sounds/Wrong.wav").toURI().toString());
	private AudioClip pairSound = new AudioClip(new File("src/Sounds/Point.wav").toURI().toString());

	public int getTimePassed() {
		return timePassed;
	}

	public void setTimePassed(int timePassed) {
		this.timePassed = timePassed;
	}

	public int getTimeToSeeCard() {
		return timeToSeeCard;
	}

	public void setTimeToSeeCard(int timeToSeeCard) {
		this.timeToSeeCard = timeToSeeCard;
	}

	public CardImageView getCardOne() {
		return cardOne;
	}

	public void setCardOne(CardImageView cardOne) {
		this.cardOne = cardOne;
	}

	public CardImageView getCardTwo() {
		return cardTwo;
	}

	public void setCardTwo(CardImageView cardTwo) {
		this.cardTwo = cardTwo;
	}

	// Compare match of cards
	public boolean compareCards(CardImageView card1, CardImageView card2) {

		return (card1.getCard().getValue() == card2.getCard().getValue()) ? true : false;
	}

	public void confirmPair(CardImageView card1, CardImageView card2) {
		
		if (this.compareCards(card1, card2)){
			pairSound.play();
			System.out.println("Du hittade ett par!");
		}else{
			 nopairSound.play();
			System.out.println("Du hittade inget par!");}
		Timeline delay = new Timeline(); // Delay timern innan korten v�nds
											// tillbaka eller tas bort
		delay.setCycleCount(Timeline.INDEFINITE);
		// ska separeras fr�n metod och flyttas till eventet
		KeyFrame cardFlip = new KeyFrame(Duration.seconds(1.0), e -> {
			if (this.timePassed + 1 >= this.timeToSeeCard) {
				// +1 f�r n�n anledning b�rjar Timern p� -1 verkar det som.
				if (this.compareCards(card1, card2)) {
					card1.Remove();
					card2.Remove();
					this.cardOne = null;
					this.cardTwo = null;
				} else {
					card1.Flip();
					card2.Flip();
					this.cardOne = null;
					this.cardTwo = null;
				}
				this.timePassed = 0;
				delay.stop();
			} else {
				this.timePassed++;
			}
		});
		delay.getKeyFrames().add(cardFlip);
		delay.play();
	}

	// Winning player
	public Player winner(Player[] players) {

		return players[0];
	}

	// Leaderboard realtime
	public Player[] leaderBoard(Player[] players) {

		for (int i = players.length - 1; i > 1; i--) {
			for (int j = 0; j < i; j++) {
				if (players[j].getPoints() > players[j + 1].getPoints()) {
					Player tempVal = players[j];
					players[j] = players[j + 1];
					players[j + 1] = tempVal;
				}
			}
		}

		return players;
	}

	// End of game
	public boolean gameOver(Deck dealerDeck) {

		return (dealerDeck.getDeck().size() == 0) ? true : false;
	}

	public void checkHighScore(Player[] inGame) {

		for (Player player : inGame) {
			if (player.getPoints() > player.getHighestPoint())
				player.setHighestPoint(player.getPoints());

			if (player.getMoves() < player.getLeastMoves())
				player.setLeastMoves(player.getMoves());

			if (player.getTime() < player.getFastestGame())
				player.setFastestGame(player.getTime());
		}
	}
}
