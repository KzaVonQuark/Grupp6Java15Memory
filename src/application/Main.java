package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			GameBoard gameBoard = new GameBoard();
			FileManager fm = new FileManager(); 
			
			FreePane root = new FreePane();
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			StartMenu start = new StartMenu();
			root.setPane(start);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Button addPlayer = new Button();
			TextField playerName = new TextField(); 
			Button loadPlayer = new Button();
			Button highScore = new Button();
			
			ObservableList<String> playerEntries = FXCollections.observableArrayList(fm.loadNames());
			ComboBox<String> getPlayer = new ComboBox<String>(playerEntries);
			ObservableList<String> scoreEntries = FXCollections.observableArrayList("Highest point", "Least Moves", "Fastest Time");
			ComboBox<String> scoreType = new ComboBox<String>(scoreEntries);
			ObservableList<Player> highScoreEntries = FXCollections.observableArrayList();
			ListView<Player> HighScoreList = new ListView<Player>(); 
			
			
			
			start.playButton.setOnAction(e->{
				root.fadeChange(gameBoard, Color.BLACK);
			});

			
			// Events			
			addPlayer.setOnAction(event -> {
				if (!playerName.equals("")) {
				}
			});
			
			loadPlayer.setOnAction(event -> {
				getPlayer.setOnAction(getEvent -> {					
				});
			});
			
			start.addButton.setOnAction(event -> {
				scoreType.setValue("Choose highscore");
				start.bottomButtons.getChildren().addAll(scoreType, HighScoreList);
				scoreType.setOnAction(event2 -> {
					highScoreEntries.setAll(fm.loadHighScore(scoreType.getValue()));
					HighScoreList.setItems(highScoreEntries);
				});
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
