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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
			highScoreList.setPrefWidth(175);
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

						if (players.get(i).getName().equals("")) {
							players.get(i).setName("Guest");
						}
						if (!players.get(i).getName().equals("Guest"))
							fm.save(players.get(i));
					}
				}
				// Psuedo kod for shuffling the order of the players. Can be
				// changed later.

				Collections.shuffle(players);
				int boardSize = 0;
				if (start.tg.getSelectedToggle().equals(start.mediumBoard)) {
					boardSize = 1;
				} else if (start.tg.getSelectedToggle().equals(start.largeBoard)) {
					boardSize = 2;
				}

				GameBoard gameBoard = new GameBoard(players, boardSize, start.frontSelection);
				gameBoard.getBackToStartMenu().setOnMousePressed(ae -> {
					start.centerBox.getChildren().clear();
					start.setBottom(null);
					start.participantsList.setText("");
					start.choosePlayers = new ComboBox<String>(FXCollections.observableArrayList(fm.loadNames()));
					start.choosePlayers.setPrefSize(150, 15);
					start.choosePlayers.setPromptText("Choose Player");
					root.fadeChange(start, Color.BLACK);
				});
				playSound.play();
				root.fadeChange(gameBoard, Color.BLACK);
			});

			start.newGameButton.setOnAction(event -> {
				fm.loadPlayer();
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().addAll(start.choosePlayers, start.smallBoard, start.mediumBoard,
						start.largeBoard, start.cardThemeLabel, start.picBox);
				start.setBottom(start.playBox);
				start.fieldOption.getChildren().clear();
				start.playersHeadLine.setText("Participants");
				start.fieldOption.getChildren().addAll(start.playersHeadLine, start.participantsList);
				start.choosePlayers.setOnAction(event2 -> {
					String[] temp = start.participantsList.getText().split("[\n]");
					boolean found = false;
					for (String p_list : temp) {
						if (p_list.equals(start.choosePlayers.getValue())) {
							found = true;
						}
					}
					if (!found) {
						start.fieldOption.getChildren().clear();
						start.fieldOption.getChildren().addAll(start.playersHeadLine, start.participantsList);
						start.participantsList
								.setText(start.participantsList.getText() + start.choosePlayers.getValue() + "\n");
					}
				});
				start.jBox.setOnMouseClicked(event3 -> {
					start.jBox.setStyle("-fx-background-color: #FFFFFF;");
					start.nBox.setStyle(null);
					start.wBox.setStyle(null);
					start.frontSelection = 3;
				});
				start.nBox.setOnMouseClicked(event3 -> {
					start.nBox.setStyle("-fx-background-color: #FFFFFF;");
					start.jBox.setStyle(null);
					start.wBox.setStyle(null);
					start.frontSelection = 2;
				});
				start.wBox.setOnMouseClicked(event3 -> {
					start.wBox.setStyle("-fx-background-color: #FFFFFF;");
					start.nBox.setStyle(null);
					start.jBox.setStyle(null);
					start.frontSelection = 1;
				});
			});

			start.createButton.setOnAction(event -> {
				start.centerBox.getChildren().clear();
				start.fieldOption.getChildren().clear();
				start.playersHeadLine.setText("Participants");
				start.fieldOption.getChildren().addAll(start.playersHeadLine, start.participantsList);
				start.centerBox.getChildren().add(start.creatorTexfield);
				start.setBottom(null);
			});

			start.creatorTexfield.setOnKeyReleased(ae -> {
				if (start.creatorTexfield.getLength() > 8) {
					start.creatorTexfield.setText(start.creatorTexfield.getText().substring(0, 8));
					start.creatorTexfield.positionCaret(8);
				}
			});

			start.creatorTexfield.setOnAction(event -> {
				if (start.creatorTexfield.getLength() > 8) {
					start.creatorTexfield.setText(start.creatorTexfield.getText().substring(0, 8));
				}
				start.creatorTexfield.setText((start.creatorTexfield.getText().replace(" ", "-")));
				String[] temp = start.participantsList.getText().split("[\n]");
				boolean found = false;
				System.out.println();
				for (String p_list : temp) {
					if (p_list.equals(start.creatorTexfield.getText())) {
						found = true;
					}
				}
				if (!found) {
					start.participantsList
							.setText(start.participantsList.getText() + start.creatorTexfield.getText() + "\n");
					start.creatorTexfield.clear();
					start.centerBox.getChildren().clear();
				}
			});

			start.exitButton.setOnAction(event -> {
				Platform.exit();
			});

			start.highScoreButton.setOnAction(event -> {
				start.setBottom(null);
				start.centerBox.getChildren().clear();
				start.centerBox.getChildren().addAll(start.solo, start.smallBoard, start.mediumBoard, start.largeBoard,
						start.clearHighScore);
				start.fieldOption.getChildren().clear();
				start.playersHeadLine.setText("Highscore");
				start.fieldOption.getChildren().addAll(start.playersHeadLine, highScoreList);
				start.tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
					@Override
					public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue,
							Toggle newValue) {
						highScoreEntries.clear();
						highScoreList.setItems(highScoreEntries);
						RadioButton check = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
						if (start.solo.isSelected())
							highScoreEntries.setAll(fm.loadHighScore("Least moves", check.getText()));
						else
							highScoreEntries.setAll(fm.loadHighScore("Highest point", check.getText()));
						highScoreList.setItems(highScoreEntries);
					}
				});

				start.solo.setOnAction(event3 -> {
					highScoreEntries.clear();
					highScoreList.setItems(highScoreEntries);
					if (start.smallBoard.isSelected() && start.solo.isSelected())
						highScoreEntries.setAll(fm.loadHighScore("Least moves", "Easy"));
					else if (start.mediumBoard.isSelected() && start.solo.isSelected())
						highScoreEntries.setAll(fm.loadHighScore("Least moves", "Normal"));
					else if (start.largeBoard.isSelected() && start.solo.isSelected())
						highScoreEntries.setAll(fm.loadHighScore("Least moves", "Hard"));
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
