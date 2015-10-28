package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameBoard extends BorderPane {
	GridPane grid;
	Deck decks;

	GameBoard() {

		grid = new GridPane();
		setCenter(grid);
		decks = new Deck(10, "frontimage2");

		for (int i = 0; i < decks.getDeckSize(); i++) {
			ImageView imageView = new ImageView(decks.dealCard().getFrontImage());
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
			grid.add(imageView, i, 0);
		}

	}

}
