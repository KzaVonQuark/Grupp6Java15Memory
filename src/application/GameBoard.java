package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameBoard extends Application {
	@Override
	public void start(Stage primaryStage) {

		GridPane grid = new GridPane();
		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Deck decks = new Deck(10, "frontimage2");
		
		for(int i=0; i<decks.getDeckSize(); i++){
			ImageView imageView = new ImageView(decks.dealCard().getFrontImage());
			/*imageView.setFitHeight(100);
			imageView.setFitWidth(100);*/
			grid.add(imageView,i,0);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	FileManager fm = new FileManager();
	ArrayList<Player> players = new ArrayList<Player>();

	public GameBoard() {
		super();
	}
	
	// Overlay methods for adding new player or load existing player.
	public void addPlayers(String name) {
		Player newPlayer = new Player(name);
		this.players.add(newPlayer);
		fm.save(newPlayer);
	}
	
	public void addPlayers(Player player) {
		this.players.add(player);
	}
	// Overlay end.
	
	public void checkHighScore(Player[] inGame) {
		
		for (Player player : inGame) {
			if (player.getPoints() > player.getHighestPoint())
				player.setHighestPoint(player.getPoints());

			if (player.getMoves() < player.getLeastMoves())
				player.setLeastMoves(player.getMoves());

			if (player.getTime() < player.getFastestGame())
				player.setFastestGame(player.getTime());
		}
	}

}
