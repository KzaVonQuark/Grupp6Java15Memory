package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class StartMenu extends BorderPane {
	int frontSelection=3;
	TextField creatorTexfield;
	Text participantsList;
	Label headLine, playersHeadLine, cardThemeLabel;
	Button playButton, exitButton, newGameButton, createButton, highScoreButton, clearHighScore;

	ComboBox<String> choosePlayers, scoreType;
	ListView<Player> HighScoreList;
	VBox alignmentBox, centerBox, fieldOption;
	HBox playersBox, labelBox, picBox;
	RadioButton smallBoard, mediumBoard, largeBoard, solo;
	ToggleGroup tg, pictg;
	ImageView javaFront, nackademinFront, wildcardFront;
	HBox jBox, nBox, wBox;
	
	DropShadow dShadow = new DropShadow(5, Color.WHITE);
	DropShadow headShadow = new DropShadow(10, Color.WHITESMOKE);
	
	StartMenu() {
		
		FileManager fm = new FileManager();
		
		headShadow.setSpread(0.5);
		jBox=new HBox();
		nBox=new HBox();
		wBox=new HBox();
		javaFront = new ImageView("/images/frontimage3.png");
		jBox.getChildren().add(javaFront);
		nackademinFront = new ImageView("/images/frontimage2.png");
		nBox.getChildren().add(nackademinFront);
		wildcardFront = new ImageView("/images/frontimage1.png");
		wBox.getChildren().add(wildcardFront);
		
		jBox.setStyle("-fx-background-color: #FFFFFF;");
		jBox.setAlignment(Pos.CENTER);
		jBox.setMinHeight(110);
		jBox.setMinWidth(110);
		nBox.setAlignment(Pos.CENTER);
		nBox.setMinHeight(110);
		nBox.setMinWidth(110);
		wBox.setAlignment(Pos.CENTER);
		wBox.setMinHeight(110);
		wBox.setMinWidth(110);
		
		creatorTexfield = new TextField();
		creatorTexfield.setMaxWidth(175);
		creatorTexfield.setPromptText("Enter your Name");

		headLine = new Label("Memory");
		headLine.setEffect(headShadow);
		headLine.setAlignment(Pos.CENTER);
		headLine.setTextFill(Color.ORANGE);
		headLine.setStyle("-fx-font: 100px Tahoma;");
		participantsList = new Text("");
		participantsList.setFill(Color.ORANGE);
		participantsList.setFont(Font.font("kristen ITC", FontWeight.BOLD, 15));
		participantsList.setTextAlignment(TextAlignment.CENTER);
		playersHeadLine = new Label("Participant");
		playersHeadLine.setTextFill(Color.ORANGE);
		playersHeadLine.setFont(Font.font("kristen ITC", FontWeight.BOLD, 20));
		playersHeadLine.setTextAlignment(TextAlignment.CENTER);
		cardThemeLabel = new Label("Choose Card Theme: ");
		cardThemeLabel.setTextFill(Color.ORANGE);
		cardThemeLabel.setFont(Font.font("kristen ITC", FontWeight.BOLD, 15));
		
		
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
		createButton = new Button("Create New Player");
		createButton.setTextFill(playButton.getTextFill());
		createButton.setStyle(playButton.getStyle());
		createButton.setFont(playButton.getFont());
		highScoreButton = new Button("Highscores");
		highScoreButton.setTextFill(playButton.getTextFill());
		highScoreButton.setStyle(playButton.getStyle());
		highScoreButton.setFont(playButton.getFont());
		clearHighScore = new Button("Clear High Score");
		clearHighScore.setTextFill(playButton.getTextFill());
		clearHighScore.setStyle(playButton.getStyle());
		clearHighScore.setFont(playButton.getFont());
		
		solo = new RadioButton("Solo game");
		solo.setTextFill(Color.ORANGE);
		solo.setFont(Font.font("kristen ITC", FontWeight.BOLD, 15));
		smallBoard = new RadioButton("Easy");
		smallBoard.setTextFill(Color.ORANGE);
		smallBoard.setFont(Font.font("kristen ITC", FontWeight.BOLD, 15));
		mediumBoard = new RadioButton("Normal");
		mediumBoard.setTextFill(Color.ORANGE);
		mediumBoard.setFont(Font.font("kristen ITC", FontWeight.BOLD, 15));
		largeBoard = new RadioButton("Hard");
		largeBoard.setTextFill(Color.ORANGE);
		largeBoard.setFont(Font.font("kristen ITC", FontWeight.BOLD, 15));
		
		tg = new ToggleGroup();
		smallBoard.setToggleGroup(tg);
		mediumBoard.setToggleGroup(tg);
		mediumBoard.setSelected(true);
		largeBoard.setToggleGroup(tg);
		
		ObservableList<String> playerEntries = FXCollections.observableArrayList(fm.loadNames());
		choosePlayers = new ComboBox<>(playerEntries);
		choosePlayers.setPrefSize(150, 15);
		choosePlayers.setPromptText("Choose Player");
		ObservableList<String> scoreEntries = FXCollections.observableArrayList("Highest point", "Least Moves");
		scoreType = new ComboBox<String>(scoreEntries);
		scoreType.setValue("Highest point");
		
		picBox = new HBox(5);
//		picBox.getChildren().addAll(javaFront, nackademinFront, wildcardFront);
		picBox.getChildren().addAll(jBox,nBox,wBox);
		
		labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER);
		labelBox.getChildren().add(headLine);

		playersBox = new HBox(5);
		playersBox.setAlignment(Pos.TOP_CENTER);
		playersBox.getChildren().add(participantsList);

		fieldOption = new VBox(5);
		fieldOption.setAlignment(Pos.TOP_CENTER);
		fieldOption.setPrefWidth(175);
		fieldOption.getChildren().addAll(playersHeadLine, participantsList);

		centerBox = new VBox(10);
		centerBox.setAlignment(Pos.TOP_LEFT);

		alignmentBox = new VBox(5);
		alignmentBox.setPadding(new Insets(0, 10, 0, 10));
		alignmentBox.setAlignment(Pos.TOP_CENTER);
		alignmentBox.getChildren().addAll(newGameButton, createButton, highScoreButton,  exitButton);

		setTop(labelBox);
		setLeft(alignmentBox);
		setCenter(centerBox);
		setRight(fieldOption);

		this.getStyleClass().add("startMenu");
		
		
		newGameButton.setOnMouseEntered(event -> {
			newGameButton.setEffect(dShadow);
		});
		newGameButton.setOnMouseExited(event -> {
			newGameButton.setEffect(null);
		});
		createButton.setOnMouseEntered(event -> {
			createButton.setEffect(dShadow);
		});
		createButton.setOnMouseExited(event -> {
			createButton.setEffect(null);
		});
		playButton.setOnMouseEntered(event -> {
			playButton.setEffect(dShadow);
		});
		playButton.setOnMouseExited(event -> {
			playButton.setEffect(null);
		});
		highScoreButton.setOnMouseEntered(event -> {
			highScoreButton.setEffect(dShadow);
		});
		highScoreButton.setOnMouseExited(event -> {
			highScoreButton.setEffect(null);
		});
		exitButton.setOnMouseEntered(event -> {
			exitButton.setEffect(dShadow);
		});
		exitButton.setOnMouseExited(event -> {
			exitButton.setEffect(null);
		});
		smallBoard.setOnMouseEntered(event -> {
			smallBoard.setEffect(dShadow);
		});
		smallBoard.setOnMouseExited(event -> {
			smallBoard.setEffect(null);
		});
		mediumBoard.setOnMouseEntered(event -> {
			mediumBoard.setEffect(dShadow);
		});
		mediumBoard.setOnMouseExited(event -> {
			mediumBoard.setEffect(null);
		});				
		largeBoard.setOnMouseEntered(event -> {
			largeBoard.setEffect(dShadow);
		});
		largeBoard.setOnMouseExited(event -> {
			largeBoard.setEffect(null);
		});
		wildcardFront.setOnMouseEntered(event ->{
			wildcardFront.setEffect(dShadow);
		});
		wildcardFront.setOnMouseExited(event ->{
			wildcardFront.setEffect(null);
		});
		nackademinFront.setOnMouseEntered(event ->{
			nackademinFront.setEffect(dShadow);
		});
		nackademinFront.setOnMouseExited(event ->{
			nackademinFront.setEffect(null);
		});
		javaFront.setOnMouseEntered(event ->{
			javaFront.setEffect(dShadow);
		});
		javaFront.setOnMouseExited(event ->{
			javaFront.setEffect(null);
		});
	}
	}
