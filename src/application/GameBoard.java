package application;

import java.util.Queue;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameBoard extends BorderPane {
	GridPane grid;
	Deck decks;
	Queue<Player> q;

	GameBoard() { // Get players from "participants". // % Player[] players
		
		this.q = null;
		grid = new GridPane();
		setCenter(grid);
		decks = new Deck(36, "frontimage2");
		grid.setHgap(10);
		grid.setVgap(10);

		int row = 0;
		int col = 0;
		for (int i = 0; i < decks.getDeckSize(); i++) {
			if (col > 5) {
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
