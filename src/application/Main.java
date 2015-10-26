package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Button addPlayer = new Button();
			TextField playerName = new TextField(); 
			Button loadPlayer = new Button();
			ComboBox<Player> getPlayer = new ComboBox<Player>();
			
			
			
			
			
			
			// Events
			
			addPlayer.setOnAction(event -> {
				
			});
			
			loadPlayer.setOnAction(event -> {
				
				getPlayer.setOnAction(getEvent -> {
					
				});
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
