package application;

import java.util.TreeMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameBoard extends Application{
	@Override
	public void start(Stage primaryStage) {
		
		GridPane grid = new GridPane();
		Scene scene = new Scene(grid,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
		
		
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
	FileManager fileManager = new FileManager();
	TreeMap<Integer, Player> players = new TreeMap<Integer, Player>();
	Player player;

	
	private int index;;
	
	public GameBoard() {
		super();
		this.index = 1;
	}
	
	public void addPlayers(String name) {
		this.players.put(this.index, new Player(name));
		this.index++;
	}
	
	public void loadPlayers(String fileName) {
		fileManager.load(this.players, fileName);
		this.players.put(this.index, player);
		this.index++;
	}
	
	public void loadHighScore(String[] oldPlayers) {
		
	}
	
	public void savePlayers() {
		
	}

	
}
