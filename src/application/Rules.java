package application;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Rules {

	private int timePassed = 0;
	private int timeToSeeCard = 2;
	private CardImageView cardOne = null;
	private CardImageView cardTwo = null;

	private AudioClip noPairSound = new AudioClip(new File("src/sounds/Wrong.wav").toURI().toString());
	private AudioClip pairSound = new AudioClip(new File("src/sounds/Point.wav").toURI().toString());

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

	// Added return true/false if player got pair or not.
	public boolean confirmPair(CardImageView card1, CardImageView card2) {

		if (this.compareCards(card1, card2)) {
			pairSound.play();
		} else {
			noPairSound.play();
		}

		Timeline delay = new Timeline(); // Delaytimer when card should flip
											// back
		delay.setCycleCount(Timeline.INDEFINITE);
		// ska separeras fr�n metod och flyttas till eventet
		KeyFrame cardFlip = new KeyFrame(Duration.seconds(1.0), e -> {
			if (this.timePassed + 1 >= this.timeToSeeCard) {
				// Seems delay starts on -1 so +1 on timePassed
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

		if (this.compareCards(card1, card2)) {
			return true;
		} else {
			return false;
		}
	}

	// Leaderboard realtime
	public String leaderBoard(List<Player> players) {

		String tempStr = "";
		if (players.size() == 1)
			tempStr += players.get(0).getName() + " " + players.get(0).getMoves() + " Moves\n";
		else {

			Compare comp = new Compare();
			comp.setSortType("Points");
			List<Player> tempPlayers = players;
			Collections.sort(tempPlayers, comp);
			Player tempPlayerPrev;
			Player tempPlayer;

			ListIterator<Player> iterator = tempPlayers.listIterator();
			int standing = 1;
			while (iterator.hasNext()) {
				if (iterator.hasPrevious()) {
					tempPlayerPrev = iterator.previous();
					tempPlayer = iterator.next();
					tempPlayer = iterator.next();
					if (tempPlayerPrev.getPoints() != tempPlayer.getPoints()) {
						standing++;
					}
				} else {
					tempPlayer = iterator.next();
				}
				tempStr += standing + ") " + tempPlayer.getName() + " " + tempPlayer.getPoints() + " Points\n";
			}
		}

		return tempStr;
	}

	// End of game
	public boolean gameOver(Deck dealerDeck) {

		return (dealerDeck.getDeck().size() == 0) ? true : false;
	}

	// Winning player
	public String winner(List<Player> players) {
		players.get(0).setWonGames(players.get(0).getWonGames() + 1);
		return players.get(0).getName();
	}

	// Update Highscore
	public void checkHighScore(List<Player> players, int mode) {
		FileManager fm = new FileManager();
		String gameMode = "";
		if (mode == 0)
			gameMode = "Easy";
		else if (mode == 1)
			gameMode = "Normal";
		else
			gameMode = "Hard";
		
		if (players.size() == 1) {
		for (Player player : players) {
				if (player.getMoves() < player.getLeastMoves() || player.getLeastMoves() == 0)
					player.setLeastMoves(player.getMoves());
				else
					player.setMoves(player.getLeastMoves());
			}
		fm.saveHighScore(players, "Least Moves", gameMode);
		}
		else {
			for (Player player : players) {
				if (player.getPoints() > player.getHighestPoint())
					player.setHighestPoint(player.getPoints());
				else
					player.setPoints(player.getHighestPoint());
			}
		fm.saveHighScore(players, "Highest Points", gameMode);
		}

		for (Player player : players) {
			player.setMoves(500);
			player.setPoints(0);
		}

	}
}
