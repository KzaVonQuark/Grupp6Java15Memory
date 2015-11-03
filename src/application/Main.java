package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
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

			ObservableList<Player> highScoreEntries = FXCollections.observableArrayList();
			ListView<Player> highScoreList = new ListView<Player>();
			highScoreList.setId("HighScoreList");
			start.playButton.setOnAction(e -> {

				// int i = 0;
				String[] temp = start.participantsList.getText().split("[\n]");
				Player[] players = new Player[temp.length];
				// Random rand = new Random();
				for (int i = 0; i < temp.length; i++) {
					if (fm.playerMap.containsKey(temp[i]))
						players[i] = fm.playerMap.get(temp[i]);
					else {
						players[i] = new Player(temp[i]);
						fm.save(players[i]);
					}
				}
				// Psuedo kod for shuffling the order of the players. Can be
				// changed later.
				ArrayList<Player> shufflePlayers = new ArrayList<Player>();
				for (Player player_entity : players) {
					shufflePlayers.add(player_entity);
				}
				Collections.shuffle(shufflePlayers);
				players = shufflePlayers.toArray(new Player[players.length]);
				int boardSize=0;
				if(start.tg.getSelectedToggle().equals(start.mediumBoard))boardSize=1;
				else if(start.tg.getSelectedToggle().equals(start.largeBoard))boardSize=2;
				GameBoard gameBoard = new GameBoard(players, boardSize);
				playSound.play();
				root.fadeChange(gameBoard, Color.BLACK);
			});

			start.newGameButton.setOnAction(event -> {
				fm.load();
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().addAll(start.choosePlayers, start.smallBoard, start.mediumBoard,
						start.largeBoard, start.playButton);
				start.choosePlayers.setOnAction(event2 -> {
					start.fieldOption.getChildren().clear();
					start.fieldOption.getChildren().addAll(start.playersHeadLine, start.participantsList);
					start.participantsList
							.setText(start.participantsList.getText() + start.choosePlayers.getValue() + "\n");
				});
			});

			start.createButton.setOnAction(event -> {
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().add(start.creatorTexfield);
			});

			start.creatorTexfield.setOnAction(event -> {

				start.participantsList
						.setText(start.participantsList.getText() + start.creatorTexfield.getText() + "\n");
				start.creatorTexfield.clear();
				start.centerBox.getChildren().clear();
			});

			start.exitButton.setOnAction(event -> {
				Platform.exit();
			});

			start.highScoreButton.setOnAction(even -> {
				
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().addAll(start.scoreType, start.smallBoard, start.mediumBoard,
														start.largeBoard);
				start.fieldOption.getChildren().add(highScoreList);
				start.tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
						@Override
						public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue,
								Toggle newValue) {
							
							highScoreEntries.clear();
							highScoreList.setItems(highScoreEntries);
							RadioButton check = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
							highScoreEntries.setAll(fm.loadHighScore(start.scoreType.getValue(), check.getText()));
							highScoreList.setItems(highScoreEntries);
						}
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
