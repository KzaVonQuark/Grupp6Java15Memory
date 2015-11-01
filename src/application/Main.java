package application;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			GameBoard gameBoard = new GameBoard();
			FileManager fm = new FileManager();
			Rules rules = new Rules();

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
			ObservableList<String> scoreEntries = FXCollections.observableArrayList("Highest point", "Least Moves");
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
				start.centerBox.getChildren().addAll(start.choosePlayers, start.playFields, scoreType, HighScoreList);
				scoreType.setPromptText("Highscores");
				start.choosePlayers.setOnAction(event1 -> {
					start.fieldOption.getChildren().clear();
					start.fieldOption.getChildren().addAll(start.playersHeadLine, start.playersLabel);
					start.playersLabel.setText(start.playersLabel.getText() + start.choosePlayers.getValue() + "\n");
				});
				scoreType.setOnAction(event2 -> {
					highScoreEntries.clear();
					HighScoreList.setItems(highScoreEntries);
					highScoreEntries.setAll(fm.loadHighScore(scoreType.getValue()));
					HighScoreList.setItems(highScoreEntries);
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

			start.exitButton.setOnAction(event -> {
				Platform.exit();
			});

			gameBoard.grid.setOnMouseClicked(me -> {

				try {
					CardImageView cardIv = (CardImageView) me.getPickResult().getIntersectedNode();
					if (!cardIv.equals(rules.getCardOne())) {// Check if player
						// choose
						// same card
						if (rules.getCardOne() == null) {
							rules.setCardOne(cardIv);
							System.out.println("Card 1 Selected! (" + rules.getCardOne().getCard().getValue() + ")");
							flipAnimation(rules.getCardOne());
						} else if (rules.getCardTwo() == null) {
							rules.setCardTwo(cardIv);
							flipAnimation(rules.getCardTwo());
							System.out.println("Card 2 Selected! (" + rules.getCardTwo().getCard().getValue() + ")");
							rules.confirmPair(rules.getCardOne(), rules.getCardTwo());
						}
					}
				} catch (ClassCastException e) {
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void flipAnimation(CardImageView cardX){
		
		Timeline flipAnimation = new Timeline();
		flipAnimation.setCycleCount(Timeline.INDEFINITE);
		KeyFrame flipFrames = new KeyFrame(Duration.seconds(0.02), e -> {
			if (cardX.getScaleX() < 0) {
				cardX.Flip();
			}
			if (!cardX.isFlipped()) {
				cardX.setScaleX(cardX.getScaleX() - 0.15);
			}
			else{
				cardX.setScaleX(cardX.getScaleX() + 0.25);
				if(cardX.getScaleX()>=1.0){
					cardX.setScaleX(1.0);
					flipAnimation.stop();
				}
			}
		});
		flipAnimation.getKeyFrames().add(flipFrames);
		
		flipAnimation.play();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
