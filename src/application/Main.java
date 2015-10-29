package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	CardImageView firstCard = null;

	@Override
	public void start(Stage primaryStage) {
		try {

			GameBoard gameBoard = new GameBoard();
			FileManager fm = new FileManager();

			FreePane root = new FreePane();
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			StartMenu start = new StartMenu();
			root.setPane(start);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Java15:Grupp6:Memory");

			ObservableList<String> playerEntries = FXCollections.observableArrayList(fm.loadNames());
			ComboBox<String> getPlayer = new ComboBox<String>(playerEntries);
			ObservableList<String> scoreEntries = FXCollections.observableArrayList("Highest point", "Least Moves",
					"Fastest Time");
			ComboBox<String> scoreType = new ComboBox<String>(scoreEntries);
			ObservableList<Player> highScoreEntries = FXCollections.observableArrayList();
			ListView<Player> HighScoreList = new ListView<Player>();
			HighScoreList.setId("HighScoreList");

			start.playButton.setOnAction(e -> {
				root.fadeChange(gameBoard, Color.BLACK);
			});

			// Events
			/*
			 * addPlayer.setOnAction(event -> { if (!playerName.equals("")) { }
			 * });
			 * 
			 * loadPlayer.setOnAction(event -> { getPlayer.setOnAction(getEvent
			 * -> { }); });
			 */
			start.newGameButton.setOnAction(event -> {
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().addAll(start.choosePlayers, start.playFields);
				scoreType.setValue("Choose highscore");
				start.centerBox.getChildren().addAll(scoreType, HighScoreList);
				scoreType.setOnAction(event2 -> {
					highScoreEntries.setAll(fm.loadHighScore(scoreType.getValue()));
					HighScoreList.setItems(highScoreEntries);
					start.fieldOption.getChildren().addAll(HighScoreList);
				});
			});

			start.createButton.setOnAction(event -> {
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().add(start.creatorTexfield);
			});
			
			start.creatorTexfield.setOnAction(event -> {
				
				start.playersLabel.setText(start.playersLabel.getText() + start.creatorTexfield.getText() + "\n");
				start.creatorTexfield.clear();
				start.centerBox.getChildren().clear();
			});
			
			start.ExitButton.setOnAction(event -> {
				Platform.exit();
			});

			gameBoard.grid.setOnMouseClicked(me -> {
				try {

					CardImageView cardIv = (CardImageView) me.getPickResult().getIntersectedNode();
					cardIv.setImage(new Image(cardIv.getCard().getBackImage()));
					System.out.println(cardIv.getCard().getValue());
					if (!cardIv.equals(firstCard)) { // Check if player choose
														// same card
						if (firstCard != null) {
							if (firstCard.getCard().getValue() == cardIv.getCard().getValue()) {
								System.out.println("Du hittade ett par!");
							} else {
								System.out.println("Du hittade inget par!");
								// cardIv.setImage(new
								// Image(cardIv.getCard().getFrontImage()));
								firstCard.setImage(new Image(firstCard.getCard().getFrontImage()));
							}
							firstCard = null;
						} else {
							firstCard = cardIv;
						}
					}
				} catch (ClassCastException e) {
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
