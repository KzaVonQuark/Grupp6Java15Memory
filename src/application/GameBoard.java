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
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class GameBoard extends BorderPane {

	private GridPane grid;
	private Deck decks;
	private Queue<Player> q;
	private List<Player> players;
	private AudioClip swishSound;
	private Label leaderBoard;
	private Label playerTurnInfo;
	private AudioClip backSound;
	
	Rules rules = new Rules();
	


	GameBoard(List<Player> players, int mode, int frontSelection) { // Get players from
												// "participants". // % Player[]
												// player

		this.q = new LinkedList<Player>();		
		this.players = new ArrayList<Player>();
		this.players = players;
		addPlayers(players);

		this.swishSound = new AudioClip(new File("src/Sounds/Swish.wav").toURI().toString());
		
        
		grid = new GridPane();
		this.setPadding(new Insets(10));
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
		decks = new Deck(cardsInDeck, "frontimage"+frontSelection);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		
        
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
		
		Label winner = new Label();
		
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
						if (rules.gameOver(decks)) {
							winner.setText(rules.winner(this.players).getName());
							grid.add(winner, 4, 4);
						}
					}
				}
			} catch (ClassCastException e) {
			}
		});

		// Leaderboard on gameboard
		VBox vBoxLB = new VBox();
		vBoxLB.setPrefWidth(175);
		Label lbHeader = new Label("Leaderboard");
		lbHeader.setFont(new Font(24));
		lbHeader.setPrefWidth(175);
		lbHeader.setAlignment(Pos.CENTER);
		Separator seperatorLB = new Separator();
		seperatorLB.setStyle("-fx-background: black;");
		seperatorLB.setPadding(new Insets(5, 0, 5, 0));
		this.leaderBoard = new Label(rules.leaderBoard(this.getPlayers()));
		this.leaderBoard.setFont(new Font(16));
		MusicImage musicImage = new MusicImage("images/RadioX.png", "images/Radio.png", "src/Sounds/BackgroundMusic.wav", false);
		vBoxLB.getChildren().addAll(lbHeader, seperatorLB, this.leaderBoard,musicImage);
		
		this.setLeft(vBoxLB);

		// Display whos turn it is
		VBox vBoxPT = new VBox();
		vBoxPT.setPrefWidth(175);
		Label ptHeader = new Label("Players turn");
		ptHeader.setFont(new Font(24));
		ptHeader.setPrefWidth(175);
		ptHeader.setAlignment(Pos.CENTER);
		Separator seperatorPT = new Separator();
		seperatorPT.setStyle("-fx-background: black;");
		seperatorPT.setPadding(new Insets(5, 0, 5, 0));
		this.playerTurnInfo = new Label(this.getQ().peek().getName());
		this.playerTurnInfo.setFont(new Font(18));
		this.playerTurnInfo.setPrefWidth(175);
		this.playerTurnInfo.setAlignment(Pos.CENTER);
		vBoxPT.getChildren().addAll(ptHeader, seperatorPT, this.playerTurnInfo);
		this.setRight(vBoxPT);

	}

	void playerTurn(boolean gotPair) {

		if (this.q.size()==1) {
			this.q.peek().setMoves(this.q.peek().getMoves() + 1);
		this.leaderBoard.setText(rules.leaderBoard(this.getPlayers()));
		}

		else {
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
			this.playerTurnInfo.setText(this.getQ().peek().getName());
		}
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

	public Label getLeaderBoard() {
		return leaderBoard;
	}

	public void setLeaderBoard(Label leaderBoard) {
		this.leaderBoard = leaderBoard;
	}

	public Label getPlayerTurnInfo() {
		return playerTurnInfo;
	}

	public void setPlayerTurnInfo(Label playerTurnInfo) {
		this.playerTurnInfo = playerTurnInfo;
	}

}
