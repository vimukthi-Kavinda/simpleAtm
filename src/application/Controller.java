package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
	/*@FXML
	TextField txtCrdNo;
	@FXML
	Button btnProceed;*/
	
	Stage stage = new Stage();
	
	public void loadVerify(ActionEvent e) throws IOException  {
		System.out.println("hello");
		/***********************************************/
		  Parent part = FXMLLoader.load(getClass().getResource("verifyPin.fxml"));
	        
	        Scene scene = new Scene(part);
	        stage.setScene(scene);
	        stage.show();
		//////////////////////////////////////////////////////////
      /*  Parent root1 = (Parent)new FXMLLoader(getClass().getResource("verifyPin.fxml")).load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
      //////////////////  
        ((Node) e.getSource()).getScene().getWindow().hide();
		Main m=new Main();
		m.alterScene("verifyPin.fxml");
		
		//////////////////////////
		/*
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verifyPin.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();*/
		
		
		
	}
	
	public void loadOpFunc(ActionEvent e) throws IOException {
		Parent p=FXMLLoader.load(getClass().getResource("opSelect.fxml"));
		
		Scene scne=new Scene(p);
		stage.close();
		
		stage.setScene(scne);
		stage.show();
	} 
	
	public void loadBalance(ActionEvent e) throws IOException {
Parent p=FXMLLoader.load(getClass().getResource("showBalance.fxml"));
		
		Scene scne=new Scene(p);
		stage.close();
		
		stage.setScene(scne);
		stage.show();
		
	}
	
	
	
	
}
