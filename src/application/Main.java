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
	static int timeToSee = 2; // Man har 2 sekunder på sig att se korten innan
								// de försvinner eller flippas tillbaka.
	static CardImageView cardOne = null;
	static CardImageView cardTwo = null;
	// Dessa variabler kan flyttas till Rules senare, men stannar för tillfället
	// eftersom de behövs för metoden

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

			gameBoard.grid.setOnMouseClicked(new EventHandler<MouseEvent>() {

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

	// Ska flyttas till Rules (compareCards metoden som finns inklusive boolean
	// returnen som redan är kodad)
	public static void ConfirmPair(CardImageView a, CardImageView b) {
		if (a.getCard().getValue() == b.getCard().getValue())
			System.out.println("Du hittade ett par!");
		else
			System.out.println("Du hittade inget par!");
		Timeline delay = new Timeline(); // Delay timern innan korten vänds
											// tillbaka eller tas bort
		delay.setCycleCount(Timeline.INDEFINITE);
		// ska separeras från metod och flyttas till eventet
		KeyFrame cardFlip = new KeyFrame(Duration.seconds(1.0), e -> {
			if (timePassed + 1 >= timeToSee) {
				// +1 för nån anledning börjar Timern på -1 verkar det som.
				if (a.getCard().getValue() == b.getCard().getValue()) {
					a.Remove();
					b.Remove();
				} else {
					a.Flip();
					b.Flip();
					cardOne = null;
					cardTwo = null;
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
