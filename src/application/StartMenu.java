package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartMenu extends Application{


	@Override
	public void start(Stage primaryStage) {
		
		BorderPane rootNode = new BorderPane();
		Scene scene = new Scene(rootNode);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Start Menu");
		
		Label headLine = new Label("Memory");
		headLine.setAlignment(Pos.CENTER);
		
		Label playersLabel = new Label("Add number of players");
		Label fieldLabel = new Label("Play field selection");
		
		Button playButton = new Button("Let's play");
		Button resetButton = new Button("Reset");
		Button addButton = new Button("Add");
		Button createButton = new Button("Create");
		
		ComboBox<String> choosePlayers = new ComboBox<>();
		ComboBox<String> playFields = new ComboBox<>();
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER);
		labelBox.getChildren().add(headLine);
		rootNode.setTop(labelBox);
		HBox playersBox = new HBox(5);
		playersBox.setAlignment(Pos.CENTER);
		playersBox.getChildren().addAll(playersLabel, choosePlayers, addButton, createButton);
		HBox fieldOption = new HBox(5);
		fieldOption.setAlignment(Pos.CENTER);
		fieldOption.getChildren().addAll(fieldLabel, playFields);
		HBox bottomButtons = new HBox(5);
		bottomButtons.setAlignment(Pos.BASELINE_RIGHT);
		bottomButtons.getChildren().addAll(resetButton, playButton);
		
		VBox alignmentBox = new VBox(5);
		alignmentBox.setAlignment(Pos.CENTER);
		alignmentBox.getChildren().addAll(playersBox, fieldOption);
		
		rootNode.setCenter(alignmentBox);
		rootNode.setBottom(bottomButtons);

		
		}
	
	public static void main(String[] args) {
		launch(args);
	}

}