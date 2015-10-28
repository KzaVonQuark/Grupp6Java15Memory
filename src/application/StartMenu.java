package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartMenu extends BorderPane{

	Label headLine, playersLabel, fieldLabel;
	Button  playButton, ExitButton, newGameButton, createButton;
	ComboBox<String> choosePlayers, playFields;
	VBox alignmentBox, bottomButtons, fieldOption;
	HBox playersBox, labelBox; 
	
	StartMenu()
		{
		
		headLine = new Label("Memory");
		headLine.setAlignment(Pos.CENTER);
		headLine.setTextFill(Color.WHITESMOKE);
		headLine.setStyle("-fx-font: 100px Tahoma;");
		
		playersLabel = new Label("Add number of players");
		playersLabel.setPrefSize(175, 10);
		playersLabel.setTextFill(Color.GHOSTWHITE);
		playersLabel.setFont(Font.font(15));
		fieldLabel = new Label("Play field selection");
		fieldLabel.setTextFill(Color.GHOSTWHITE);
		fieldLabel.setFont(Font.font(15));
		fieldLabel.setPrefSize(175, 10);
		
		playButton = new Button("Let's play");
		playButton.setTextFill(Color.WHITESMOKE);
        playButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        playButton.setFont(Font.font(20));
		ExitButton = new Button("Leave Game");
		ExitButton.setTextFill(playButton.getTextFill());
		ExitButton.setStyle(playButton.getStyle());
		ExitButton.setFont(Font.font(20));
		newGameButton = new Button("New Game");
		newGameButton.setTextFill(playButton.getTextFill());
		newGameButton.setStyle(playButton.getStyle());
		newGameButton.setFont(Font.font(20));
		createButton = new Button("Create");
		createButton.setTextFill(playButton.getTextFill());
		createButton.setStyle(playButton.getStyle());
		createButton.setFont(Font.font(20));
		
		choosePlayers = new ComboBox<>();
		choosePlayers.setPromptText("players");
		playFields = new ComboBox<>();
		playFields.setPromptText("theme");
		
		labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER);
		labelBox.getChildren().add(headLine);
		
		playersBox = new HBox(5);
		playersBox.setAlignment(Pos.TOP_CENTER);
		playersBox.getChildren().add(playersLabel);
		
		fieldOption = new VBox(5);
		fieldOption.setAlignment(Pos.TOP_CENTER);
		fieldOption.getChildren().addAll(fieldLabel, playersLabel);
		
		bottomButtons = new VBox(5);
		bottomButtons.setAlignment(Pos.TOP_LEFT);
		
		alignmentBox = new VBox(5);
		alignmentBox.setAlignment(Pos.TOP_CENTER);
		alignmentBox.getChildren().addAll(newGameButton, createButton, playButton, ExitButton);
		
		setTop(labelBox);
		setLeft(alignmentBox);
		setCenter(bottomButtons);
		setRight(fieldOption);
		
		setStyle("-fx-background-image: url(\"images/background.jpg\"); -fx-backgroundsize: 800,600;");
		}
}
	