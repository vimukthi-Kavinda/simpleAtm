package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
	@FXML
	TextField txtCrdNo;
	@FXML
	Button btnProceed;
	@FXML
	Label lblErrr;
	Stage stage = new Stage();
	Connection c=null;
	
	public void loadVerify(ActionEvent e) throws IOException, ClassNotFoundException, SQLException  {
		
		/***********************************************/
		
		 
		 if(verifyAccount()) { 
			 
			 Parent part = FXMLLoader.load(getClass().getResource("verifyPin.fxml"));
	        
	        Scene scene = new Scene(part);
	        stage.setScene(scene);
	        
	        stage.show();
	        ((Node) e.getSource()).getScene().getWindow().hide();}
		 
		 else {
			 loadError("No account found",e);
		 }
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
		
		public void loadError(String s,ActionEvent e) throws IOException {
			
			Parent part = FXMLLoader.load(getClass().getResource("error.fxml"));
			
			Label l=(Label)part.lookup("#lblErrr");
			l.setText(s);
	        Scene scene = new Scene(part);
	        
	        stage.setScene(scene);
	       System.out.println(s);
	       
	        stage.show();
	        
	        ((Node) e.getSource()).getScene().getWindow().hide();
	        
		 
		}
	
	
	public void loadOpFunc(ActionEvent e) throws IOException {
		Parent p=FXMLLoader.load(getClass().getResource("opSelect.fxml"));
		
		Scene scne=new Scene(p);
		//stage.close();
		
		stage.setScene(scne);
		stage.show();
		 ((Node) e.getSource()).getScene().getWindow().hide();
	} 
	
	public void loadBalance(ActionEvent e) throws IOException {
Parent p=FXMLLoader.load(getClass().getResource("showBalance.fxml"));
		
		Scene scne=new Scene(p);
		//stage.close();
		
		stage.setScene(scne);
		stage.show();
		 ((Node) e.getSource()).getScene().getWindow().hide();
		
	}
	
	public void exitFunction(ActionEvent e) throws IOException {
		new Main().start(stage);
		((Node)e.getSource()).getScene().getWindow().hide();
	}
	
public void loadDeposit(ActionEvent e) throws IOException {
	
	Parent p =FXMLLoader.load(getClass().getResource("depositCash.fxml"));
	stage.setScene(new Scene(p));
	stage.show();
	
	((Node)e.getSource()).getScene().getWindow().hide();
	
}

public void loadWithdraw(ActionEvent e) throws IOException {
	Parent p =FXMLLoader.load(getClass().getResource("withdrawCash.fxml"));
	stage.setScene(new Scene(p));
	stage.show();
	
	((Node)e.getSource()).getScene().getWindow().hide();
	
}

public void loadTransfer(ActionEvent e) throws IOException {
	Parent p =FXMLLoader.load(getClass().getResource("transferCash.fxml"));
	stage.setScene(new Scene(p));
	stage.show();
	
	((Node)e.getSource()).getScene().getWindow().hide();
	
}


public boolean verifyAccount() throws ClassNotFoundException, SQLException {
	
	c=SqliteConnection.connector();
	String crdNo=txtCrdNo.getText();
	String sql="select * from account where cardno=?";
	
	PreparedStatement pstm=c.prepareStatement(sql);
	pstm.setString(1, crdNo);
	ResultSet rs=pstm.executeQuery();
	if(rs.next()) {
		 System.out.print("hdhdfh");
		return true;
	}
	 
	return false;
}
//verifyPin() account no match it pin
//getDetails() get all details of account owner
//checkBalance() if current balance > input value
//transferFromMyAcc()


	
}
