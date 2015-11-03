package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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
				List<Player> players = new ArrayList<Player>();
				// Random rand = new Random();
				for (int i = 0; i < temp.length; i++) {
					if (fm.playerMap.containsKey(temp[i]))
						players.add(fm.playerMap.get(temp[i]));
					else {
						players.add(new Player(temp[i]));

						if(players.get(i).getName().equals("")){
							players.get(i).setName("Guest");
						}
						if(!players.get(i).getName().equals("Guest"))
						fm.save(players.get(i));
					}
				}
				// Psuedo kod for shuffling the order of the players. Can be
				// changed later.

				Collections.shuffle(players);
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

			start.highScoreButton.setOnAction(event -> {

				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().addAll(start.scoreType, start.smallBoard, start.mediumBoard,
						start.largeBoard, start.clearHighScore);
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
				
				start.scoreType.setOnAction(event2 -> {
					highScoreEntries.clear();
					highScoreList.setItems(highScoreEntries);
					if (start.smallBoard.isSelected())
						highScoreEntries.setAll(fm.loadHighScore(start.scoreType.getValue(), "Easy"));
					else if (start.mediumBoard.isSelected())
						highScoreEntries.setAll(fm.loadHighScore(start.scoreType.getValue(), "Normal"));
					else if (start.largeBoard.isSelected())
						highScoreEntries.setAll(fm.loadHighScore(start.scoreType.getValue(), "Hard"));
					highScoreList.setItems(highScoreEntries);
				});
				
				start.clearHighScore.setOnAction(event3 -> {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirm clear");
					String s = "Do you really want to clear Highscore!?";
					alert.setContentText(s);
					Optional<ButtonType> result = alert.showAndWait();
					if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
						if (start.smallBoard.isSelected())
							fm.clearHighScore("Easy");
						else if (start.mediumBoard.isSelected())
							fm.clearHighScore("Normal");
						else if (start.largeBoard.isSelected())
							fm.clearHighScore("Hard");
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
