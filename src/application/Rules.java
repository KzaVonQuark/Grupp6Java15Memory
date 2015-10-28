package application;

public class Rules {

	// Compare match of cards
	public boolean compareCards(CardImageView card1, CardImageView card2) {
		
		return (card1.getCardValue() == card2.getCardValue()) ? true : false;
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
