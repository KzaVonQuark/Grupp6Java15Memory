package application;

import java.util.Queue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameBoard extends BorderPane {
	private GridPane grid;
	private Deck decks;
	private Queue<Player> q;
	private Player[] players;

	GameBoard() { // Get players from "participants". // % Player[] player
		
		grid = new GridPane();
		setCenter(grid);
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
		}
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

}
