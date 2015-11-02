package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StartMenu extends BorderPane {

	TextField creatorTexfield;
	Text playersLabel;
	Label headLine, playersHeadLine;
	Button playButton, exitButton, newGameButton, createButton;
	ComboBox<String> choosePlayers, playFields;
	VBox alignmentBox, centerBox, fieldOption;
	HBox playersBox, labelBox;
	RadioButton small, medium, large;

	StartMenu() {
		
		FileManager fm = new FileManager();
		DropShadow shadow = new DropShadow(5, Color.WHITE);

		creatorTexfield = new TextField();
		creatorTexfield.setMaxWidth(175);
		creatorTexfield.setPromptText("Enter your ID");

		headLine = new Label("Memory");
		headLine.setAlignment(Pos.CENTER);
		headLine.setTextFill(Color.WHITESMOKE);
		headLine.setStyle("-fx-font: 100px Tahoma;");
		playersLabel = new Text("");
		playersLabel.setFill(Color.ORANGE);
		playersLabel.setFont(Font.font("kristen ITC", FontWeight.BOLD, 15));
		playersHeadLine = new Label("Participant");
		playersHeadLine.setTextFill(Color.ORANGE);
		playersHeadLine.setFont(Font.font("kristen ITC", FontWeight.BOLD, 20));
		playersHeadLine.setPrefSize(175, 10);

		playButton = new Button("Let's play");
		playButton.setTextFill(Color.ORANGE);
		playButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		playButton.setFont(Font.font("kristen ITC", FontWeight.BOLD, 20));
		exitButton = new Button("Leave Game");
		exitButton.setTextFill(playButton.getTextFill());
		exitButton.setStyle(playButton.getStyle());
		exitButton.setFont(playButton.getFont());
		newGameButton = new Button("New Game");
		newGameButton.setTextFill(playButton.getTextFill());
		newGameButton.setStyle(playButton.getStyle());
		newGameButton.setFont(playButton.getFont());
		createButton = new Button("Create");
		createButton.setTextFill(playButton.getTextFill());
		createButton.setStyle(playButton.getStyle());
		createButton.setFont(playButton.getFont());

		ObservableList<String> playerEntries = FXCollections.observableArrayList(fm.loadNames());
		choosePlayers = new ComboBox<>(playerEntries);
		choosePlayers.setPromptText("Choose Player");
		playFields = new ComboBox<>();
		playFields.setPromptText("Choose Theme");

		labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER);
		labelBox.getChildren().add(headLine);

		playersBox = new HBox(5);
		playersBox.setAlignment(Pos.TOP_CENTER);
		playersBox.getChildren().add(playersLabel);

		fieldOption = new VBox(5);
		fieldOption.setAlignment(Pos.TOP_CENTER);
		fieldOption.getChildren().addAll(playersHeadLine, playersLabel);

		centerBox = new VBox(5);
		centerBox.setAlignment(Pos.TOP_LEFT);

		alignmentBox = new VBox(5);
		alignmentBox.setPadding(new Insets(0, 10, 0, 10));
		alignmentBox.setAlignment(Pos.TOP_CENTER);
		alignmentBox.getChildren().addAll(newGameButton, createButton, exitButton);

		setTop(labelBox);
		setLeft(alignmentBox);
		setCenter(centerBox);
		setRight(fieldOption);

		setStyle("-fx-background-image: url(\"images/background.jpg\")");

		newGameButton.setOnMouseEntered(event -> {
			newGameButton.setEffect(shadow);
		});
		newGameButton.setOnMouseExited(event -> {
			newGameButton.setEffect(null);
		});

		createButton.setOnMouseEntered(event -> {
			createButton.setEffect(shadow);
		});
		createButton.setOnMouseExited(event -> {
			createButton.setEffect(null);
		});

		playButton.setOnMouseEntered(event -> {
			playButton.setEffect(shadow);

		});
		playButton.setOnMouseExited(event -> {
			playButton.setEffect(null);
		});

		exitButton.setOnMouseEntered(event -> {
			exitButton.setEffect(shadow);

		});
		exitButton.setOnMouseExited(event -> {
			exitButton.setEffect(null);
		});

	}
}
