package application;

import java.util.Queue;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameBoard extends BorderPane {
	GridPane grid;
	Deck decks;
	
	// % = Added by Linus.
	Queue<Player> q; // %

	
	GameBoard(Player[] players) { // Get players from "participants". // %
		for (Player player : players) {
			q.add(player);
		} 									//%

		grid = new GridPane();
		setCenter(grid);
		decks = new Deck(10, "frontimage2");
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		for (int i = 0; i < decks.getDeckSize(); i++) {
			CardImageView imageView = new CardImageView(decks.dealCard(i).getFrontImage(),
 decks.dealCard(i));
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
			grid.add(imageView, i, 0);
		}
	}

}
