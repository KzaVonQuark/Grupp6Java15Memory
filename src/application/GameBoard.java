package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameBoard extends BorderPane {
	GridPane grid;
	Deck decks;

	GameBoard() {

		grid = new GridPane();
		setCenter(grid);
		decks = new Deck(12, "frontimage2");
		grid.setHgap(10);
		grid.setVgap(10);
		
		
		
		for (int i = 0; i < decks.getDeckSize(); i++) {
			CardImageView imageView = new CardImageView(decks.dealCard(i).getFrontImage(),
 decks.dealCard(i));
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
			grid.add(imageView, i, 0);
			
			if(i >= 4){
				return;
			}
		}

	}

}
