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
	
	private static Stage stg;
	@Override
	
	public void start(Stage primaryStage) throws IOException {
		stg=primaryStage;
		
		
		Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene=new Scene(parent);
		stg.setScene(scene);
		stg.show();
		
		
		
	}
	
	public void alterScene(String x) throws IOException {
		  stg.setScene(new Scene(FXMLLoader.load(getClass().getResource(x))));
	        
	}
	
	
}
