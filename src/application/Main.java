package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene=new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	
}
