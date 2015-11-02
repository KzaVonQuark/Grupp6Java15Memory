package application;

import java.io.File;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Toggle;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	private AudioClip playSound = new AudioClip(new File("src/Sounds/Start.wav").toURI().toString());

	@Override
	public void start(Stage primaryStage) {
		try {

			FileManager fm = new FileManager();

			FreePane root = new FreePane();
			Scene scene = new Scene(root, 1280, 720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			StartMenu start = new StartMenu();
			root.setPane(start);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();
			primaryStage.setTitle("Java15:Grupp6:Memory");

			ObservableList<String> playerEntries = FXCollections.observableArrayList(fm.loadNames());
			ComboBox<String> getPlayer = new ComboBox<String>(playerEntries);
			ObservableList<String> scoreEntries = FXCollections.observableArrayList("Highest point", "Least Moves");
			ComboBox<String> scoreType = new ComboBox<String>(scoreEntries);
			scoreType.setPromptText("Highscores");
			ObservableList<Player> highScoreEntries = FXCollections.observableArrayList();
			ListView<Player> HighScoreList = new ListView<Player>();
			HighScoreList.setId("HighScoreList");

			start.playButton.setOnAction(e -> {
				
				int i = 0;
				String[] temp = start.participantsList.getText().split("[\n]");
				Player[] players = new Player[temp.length];
				Random rand = new Random();
				for (String name : temp) {
					i = rand.nextInt(temp.length);
					while (players[i] != null) {
						if (fm.playerMap.containsKey(name))
							players[i] = fm.playerMap.get(name);
						else
							players[i] = new Player(name);
					}
				}

				GameBoard gameBoard = new GameBoard(players, 2);

				gameBoard.addPlayers(players);
				playSound.play();
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
				fm.load();
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().addAll(start.choosePlayers, start.smallBoard, 
							start.mediumBoard, start.largeBoard, start.playButton);
				start.choosePlayers.setOnAction(event2 -> {
					start.fieldOption.getChildren().clear();
					start.fieldOption.getChildren().addAll(start.playersHeadLine, start.participantsList);
					start.participantsList.setText(start.participantsList.getText() + start.choosePlayers.getValue() + "\n");
				});
			});

			start.createButton.setOnAction(event -> {
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().add(start.creatorTexfield);
			});

			start.creatorTexfield.setOnAction(event -> {

				start.participantsList.setText(start.participantsList.getText() + start.creatorTexfield.getText() + "\n");
				start.creatorTexfield.clear();
				start.centerBox.getChildren().clear();
			});

			start.exitButton.setOnAction(event -> {
				Platform.exit();
			});

			start.highScoreButton.setOnAction(even ->{
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().addAll(scoreType, HighScoreList);
				scoreType.setOnAction(event -> {
					highScoreEntries.clear();
					HighScoreList.setItems(highScoreEntries);
					start.tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
						@Override
						public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue,
								Toggle newValue) {
							highScoreEntries.setAll(fm.loadHighScore(scoreType.getValue(),
									start.tg.getSelectedToggle().getUserData().toString()));
						}

					});
					HighScoreList.setItems(highScoreEntries);
				});

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		launch(args);
	}
}
