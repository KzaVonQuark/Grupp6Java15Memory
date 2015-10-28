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
	Button  playButton, resetButton, addButton, createButton;
	ComboBox<String> choosePlayers, playFields;
	VBox alignmentBox;
	HBox bottomButtons, fieldOption, playersBox, labelBox; 
	
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
		resetButton = new Button("Reset");
		resetButton.setTextFill(Color.WHITESMOKE);
		resetButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		resetButton.setFont(Font.font(20));
		addButton = new Button("Add");
		addButton.setTextFill(Color.WHITESMOKE);
		addButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		addButton.setFont(Font.font(20));
		createButton = new Button("Create");
		createButton.setTextFill(Color.WHITESMOKE);
		createButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		createButton.setFont(Font.font(20));
		
		choosePlayers = new ComboBox<>();
		playFields = new ComboBox<>();
		
		labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER);
		labelBox.getChildren().add(headLine);
		
		playersBox = new HBox(5);
		playersBox.setAlignment(Pos.CENTER);
		playersBox.getChildren().addAll(playersLabel, choosePlayers);
		
		fieldOption = new HBox(5);
		fieldOption.setAlignment(Pos.CENTER);
		fieldOption.getChildren().addAll(fieldLabel, playFields);
		
		bottomButtons = new HBox(5);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.getChildren().addAll(resetButton, playButton);
		
		alignmentBox = new VBox(5);
		alignmentBox.setAlignment(Pos.CENTER);
		alignmentBox.getChildren().addAll(addButton, createButton, playersBox, fieldOption, bottomButtons);
		
		setTop(labelBox);
		setCenter(alignmentBox);
		
		setStyle("-fx-background-image: url(\"images/background.jpg\"); -fx-backgroundsize: 800,600;");
		}
}
	