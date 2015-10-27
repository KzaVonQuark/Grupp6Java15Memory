package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			GameBoard gameBoard = new GameBoard();
			FileManager fm = new FileManager();
			String[] oldPlayers = new File("/PlayerScore/").list();
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Button addPlayer = new Button();
			TextField playerName = new TextField(); 
			Button loadPlayer = new Button();
			Button highScore = new Button();
			
			ObservableList<String> playerEntries = FXCollections.observableArrayList(oldPlayers);
			ComboBox<String> getPlayer = new ComboBox<String>(playerEntries);
			
			

			
			// Events			
			addPlayer.setOnAction(event -> {
				if (!playerName.equals("")) {
					gameBoard.addPlayers(playerName.getText());
				}
			});
			
			loadPlayer.setOnAction(event -> {
				/* clear buttons */
				/* add buttons + getPlayers */
				getPlayer.setOnAction(getEvent -> {
					gameBoard.loadPlayers(getPlayer.getValue());
				});
			});
			
			highScore.setOnAction(event -> {
				fm.highScore(oldPlayers);
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
