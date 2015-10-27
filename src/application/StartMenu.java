package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
		
		playersLabel = new Label("Add number of players");
		fieldLabel = new Label("Play field selection");
		
		playButton = new Button("Let's play");
		resetButton = new Button("Reset");
		addButton = new Button("Add");
		createButton = new Button("Create");
		
		choosePlayers = new ComboBox<>();
		playFields = new ComboBox<>();
		
		labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER);
		labelBox.getChildren().add(headLine);
		setTop(labelBox);
		playersBox = new HBox(5);
		playersBox.setAlignment(Pos.CENTER);
		playersBox.getChildren().addAll(playersLabel, choosePlayers, addButton, createButton);
		fieldOption = new HBox(5);
		fieldOption.setAlignment(Pos.CENTER);
		fieldOption.getChildren().addAll(fieldLabel, playFields);
		bottomButtons = new HBox(5);
		bottomButtons.setAlignment(Pos.BASELINE_RIGHT);
		bottomButtons.getChildren().addAll(resetButton, playButton);
		
		alignmentBox = new VBox(5);
		alignmentBox.setAlignment(Pos.CENTER);
		alignmentBox.getChildren().addAll(playersBox, fieldOption);
		
		setCenter(alignmentBox);
		setBottom(bottomButtons);
		}
		
		}
	