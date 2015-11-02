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

	
	GameBoard() { // Get players from "participants". // % Player[] players
		/*
		for (Player player : players) {
			q.add(player);
		} 									//%
*/
		grid = new GridPane();
		setCenter(grid);
		decks = new Deck(12, "frontimage2");
		grid.setHgap(10);
		grid.setVgap(10);
		
		
		

		for (int i = 0; i < decks.getDeckSize(); i++) {
			CardImageView imageView = new CardImageView(decks.dealCard(i).getBackImage(), decks.dealCard(i).getValue());
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
			grid.add(imageView, i, 0);
			
			if(i >= 4){
				return;
			}
		}
	}

}
