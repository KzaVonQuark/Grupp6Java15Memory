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
	private int timeToSeeCard = 2; // Man har 2 sekunder p� sig att se korten
									// innan
									// de f�rsvinner eller flippas tillbaka.
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

		Timeline delay = new Timeline(); // Delay timern innan korten v�nds
											// tillbaka eller tas bort
		delay.setCycleCount(Timeline.INDEFINITE);
		// ska separeras fr�n metod och flyttas till eventet
		KeyFrame cardFlip = new KeyFrame(Duration.seconds(1.0), e -> {
			if (this.timePassed + 1 >= this.timeToSeeCard) {
				// +1 f�r n�n anledning b�rjar Timern p� -1 verkar det
				// som.
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
			// System.out.println("Du hittade ett par!");
			return true;
		} else {
			// System.out.println("Du hittade inget par!");
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
	public void checkHighScore(List<Player> players) {
		FileManager fm = new FileManager();
		
		if (players.size() == 1) {
		for (Player player : players) {
				if (player.getMoves() < player.getLeastMoves() || player.getMoves() == player.getLeastMoves())
					player.setLeastMoves(player.getMoves());
			}
		fm.saveHighScore(players, "Moves");
		}
		else {
			for (Player player : players) {
				if (player.getPoints() > player.getHighestPoint())
					player.setHighestPoint(player.getPoints());
			}
		fm.saveHighScore(players, "Points");
		}


		for (Player player : players) {
			player.setMoves(0);
			player.setPoints(0);
		}

	}
}
