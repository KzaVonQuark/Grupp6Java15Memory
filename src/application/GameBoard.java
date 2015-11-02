package application;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class GameBoard extends BorderPane {
	private GridPane grid;
	private Deck decks;
	private Queue<Player> q;
	private Player[] players;
	Rules rules = new Rules();
	private AudioClip swishSound;

	GameBoard(Player[] players, int deckSize) { // Get players from "participants". // % Player[] player
	
		this.q = new LinkedList<Player>();
		this.swishSound = new AudioClip(new File("src/Sounds/Swish.wav").toURI().toString());
		
		grid = new GridPane();
		this.setCenter(grid);
		decks = new Deck(36, "frontimage2");
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10));

		int row = 0;
		int col = 0;
		for (int i = 0; i < decks.getDeckSize(); i++) {
			if (col > (decks.getDeckSize()/6)-1) {
				col = 0;
				row++;
			}
			CardImageView imageView = new CardImageView(decks.dealCard(i).getFrontImage(), decks.dealCard(i));
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
			grid.add(imageView, col, row);
			col++;
			// if(i >= 4){
			// return;
			// }

			this.getGrid().setOnMouseClicked(me -> {
				Test tt = new Test(); // Trying player turn methods...
				try {
					CardImageView cardIv = (CardImageView) me.getPickResult().getIntersectedNode();
					if (!cardIv.equals(rules.getCardOne())) {// Check if player
						// choose
						// same card
						if (rules.getCardOne() == null) {
							rules.setCardOne(cardIv);
							System.out.println("Card 1 Selected! (" + rules.getCardOne().getCard().getValue() + ")");
							flipAnimation(rules.getCardOne());
						} else if (rules.getCardTwo() == null) {
							rules.setCardTwo(cardIv);
							flipAnimation(rules.getCardTwo());
							System.out.println("Card 2 Selected! (" + rules.getCardTwo().getCard().getValue() + ")");
							boolean turn = rules.confirmPair(rules.getCardOne(), rules.getCardTwo());

							tt.playerTurn(turn); // Checks and changes player

						}
					}
				} catch (ClassCastException e) {
				}
			});

		}

		// Leaderboard on gameboard
		/*
		 * Label leaderBoard = new Label(); String lb = ""; for (Player player :
		 * this.getPlayers()) { lb += player.getName() + ": " +
		 * player.getPoints() + " Points\n"; } leaderBoard.setText(lb);
		 * this.setLeft(leaderBoard);
		 */
	}
	
	public void addPlayers(Player[] players) {
		for (Player player : players) {
			this.q.add(player);
		}
	}

	public GridPane getGrid() {
		return grid;
	}

	public void setGrid(GridPane grid) {
		this.grid = grid;
	}

	public Deck getDecks() {
		return decks;
	}

	public void setDecks(Deck decks) {
		this.decks = decks;
	}

	public Queue<Player> getQ() {
		return q;
	}

	public void setQ(Queue<Player> q) {
		this.q = q;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	private void flipAnimation(CardImageView cardX) {

		Timeline flipAnimation = new Timeline();
		flipAnimation.setCycleCount(Timeline.INDEFINITE);
		KeyFrame flipFrames = new KeyFrame(Duration.seconds(0.02), e -> {
			if (cardX.getScaleX() < 0) {
				cardX.Flip();
			}
			if (!cardX.isFlipped()) {
				cardX.setScaleX(cardX.getScaleX() - 0.15);
			} else {
				cardX.setScaleX(cardX.getScaleX() + 0.25);
				if (cardX.getScaleX() >= 1.0) {
					cardX.setScaleX(1.0);
					flipAnimation.stop();
				}
			}
		});
		flipAnimation.getKeyFrames().add(flipFrames);
		this.swishSound.play();
		flipAnimation.play();
	}

}
