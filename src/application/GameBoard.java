package application;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class GameBoard extends BorderPane {

	private GridPane grid;
	private Deck decks;
	private Queue<Player> q;
	private List<Player> players;
	private AudioClip swishSound;
	private Label leaderBoard;
	private AudioClip backSound;
	
	Rules rules = new Rules();
	
//	this.backSound = new AudioClip(new File (" src/Sounds/BackgroundMusic.wav").toURI().toString());

	GameBoard(List<Player> players, int mode) { // Get players from
												// "participants". // % Player[]
												// player

		this.q = new LinkedList<Player>();
		this.players = new ArrayList<Player>();
		this.players = players;
		addPlayers(players);

		this.swishSound = new AudioClip(new File("src/Sounds/Swish.wav").toURI().toString());
		this.backSound = new AudioClip(new File ("src/Sounds/BackgroundMusic.wav").toURI().toString());
        
		grid = new GridPane();
		this.setPadding(new Insets(20));
		this.setCenter(grid);
		int cardsInDeck=0;
		if(mode==0){
			cardsInDeck=6*4;
		}
		else if(mode==1){
			cardsInDeck=6*6;
		}
		else{
			cardsInDeck=6*8;
		}
		decks = new Deck(cardsInDeck, "frontimage2");
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10));
		
        CheckBox musicCheck = new CheckBox("Want some Meomery Music?");
        setBottom(musicCheck);
        
        
        musicCheck.setOnAction(event -> {
        if (musicCheck.isSelected()) {
				backSound.play();
				backSound.setVolume(1.0);
				backSound.setCycleCount(5);
				
			} else {
				backSound.stop();
			
			}});
        
		int row = 0;
		int col = 0;
		for (int i = 0; i < decks.getDeckSize(); i++) {
			if (col > (decks.getDeckSize() / 6) - 1) {
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

		}
		this.getGrid().setOnMouseClicked(me -> {
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

						playerTurn(turn); // Checks and changes player

					}
				}
			} catch (ClassCastException e) {
			}
		});

		// Leaderboard on gameboard
		this.leaderBoard = new Label(rules.leaderBoard(this.getPlayers()));
		leaderBoard.setFont(new Font(24));
		leaderBoard.setAlignment(Pos.CENTER);
		this.setLeft(leaderBoard);

	}

	void playerTurn(boolean gotPair) {

		// Reads first element in queue.
		if (gotPair == true) {
			this.q.peek().setPoints(this.q.peek().getPoints() + 1);
			this.q.peek().setMoves(this.q.peek().getMoves() + 1);
			this.leaderBoard.setText(rules.leaderBoard(this.getPlayers()));
		}

		// Reads, removes and put element last in queue
		else {
			this.q.peek().setMoves(this.q.peek().getMoves() + 1);
			this.q.add(this.q.poll());
		}
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

	public void addPlayers(List<Player> players) {
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
