package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	static int timePassed = 0;
	static int timeToSee = 0;

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
				start.bottomButtons.getChildren().addAll(start.choosePlayers, start.playFields);
				scoreType.setValue("Choose highscore");
				start.bottomButtons.getChildren().addAll(scoreType, HighScoreList);
				scoreType.setOnAction(event2 -> {
					highScoreEntries.setAll(fm.loadHighScore(scoreType.getValue()));
					HighScoreList.setItems(highScoreEntries);
					start.fieldOption.getChildren().addAll(HighScoreList);
				});
			});

			start.ExitButton.setOnAction(event -> {
				Platform.exit();
			});

			gameBoard.grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
				CardImageView cardOne = null;
				CardImageView cardTwo = null;

				@Override
				public void handle(MouseEvent me) {
					// TODO Auto-generated method stub
					try {
						CardImageView cardIv = (CardImageView) me.getPickResult().getIntersectedNode();
						if (!cardIv.equals(cardOne)) {// Check if player
							// choose
							// same card
							if (cardOne == null) {
								cardOne = cardIv;
							} else if (cardTwo == null) {
								cardTwo = cardIv;
							}
							if (!cardOne.isFlipped()) {
								cardOne = cardIv;
								System.out.println("Card 1 Selected! (" + cardOne.getCard().getValue() + ")");
								cardOne.Flip();
							} else if (!cardTwo.isFlipped()) {
								cardTwo = cardIv;
								cardTwo.Flip();
								System.out.println("Card 2 Selected! (" + cardTwo.getCard().getValue() + ")");
								ConfirmPair(cardOne, cardTwo);
							}
						}
					} catch (ClassCastException e) {
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ConfirmPair(CardImageView a, CardImageView b) {
		if (a.getCard().getValue() == b.getCard().getValue())
			System.out.println("Du hittade ett par!");
		else
			System.out.println("Du hittade inget par!");
		Timeline delay = new Timeline();
		delay.setCycleCount(Timeline.INDEFINITE);

		KeyFrame cardFlip = new KeyFrame(Duration.seconds(1), e -> {
			if (timePassed > timeToSee) {
				if (a.getCard().getValue() == b.getCard().getValue()) {
					a.Remove();
					b.Remove();
				} else {
					a.Flip();
					b.Flip();
				}
				timePassed = 0;
				delay.stop();
			} else {
				timePassed++;
			}
		});
		delay.getKeyFrames().add(cardFlip);
		delay.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
