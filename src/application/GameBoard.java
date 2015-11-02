package application;

import java.util.Queue;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameBoard extends BorderPane {
	GridPane grid;
	Deck decks;
	Queue<Player> q;
	Player[] players;

	GameBoard(Player[] players) { // Get players from "participants". // % Player[] players
		
		this.players = players;
		for (Player player : players) {
			this.q.add(player);
		}
		
		grid = new GridPane();
		setCenter(grid);
		decks = new Deck(36, "frontimage2");
		grid.setHgap(10);
		grid.setVgap(10);
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

}
