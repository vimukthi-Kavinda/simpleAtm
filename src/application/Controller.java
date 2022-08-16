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
import javafx.scene.control.PasswordField;
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
	@FXML
	Label lblAccno;
	@FXML
	Label lblNm;
	@FXML
	Label lblBal;
	@FXML
	PasswordField pwdPin;
	@FXML
	TextField txtIpBl;
	@FXML
	TextField txtaccNo;
	
	
	
	Stage stage = new Stage();
	Connection c=null;
	static String crdNo,nameCurr;
	static double inputVal,curr_balance; 
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
	       //System.out.println(s);
	       
	        stage.show();
	        
	        ((Node) e.getSource()).getScene().getWindow().hide();
	        
		 
		}
	
	
	public void loadOpFunc(ActionEvent e) throws IOException, ClassNotFoundException, SQLException {
		if(verifyPin()) {
		Parent p=FXMLLoader.load(getClass().getResource("opSelect.fxml"));
		
		Scene scne=new Scene(p);
		//stage.close();
		
		stage.setScene(scne);
		stage.show();
		 ((Node) e.getSource()).getScene().getWindow().hide();
		 }
		else {
			loadError("Pin does not match",e);
		}
	} 
	
	public void loadBalance(ActionEvent e) throws IOException, ClassNotFoundException, SQLException {
Parent p=FXMLLoader.load(getClass().getResource("showBalance.fxml"));
		String x[]=getDetails();
		Scene scne=new Scene(p);
		//stage.close();
		Label l1=(Label)p.lookup("#lblAccno");
		Label l2=(Label)p.lookup("#lblNm");
		Label l3=(Label)p.lookup("#lblBal");
		nameCurr=x[0];
		l1.setText(crdNo);
		l2.setText(x[0]);
		l3.setText(x[1]);
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
	crdNo=txtCrdNo.getText();
	String sql="select * from account where cardno=?";
	
	PreparedStatement pstm=c.prepareStatement(sql);
	pstm.setString(1, crdNo);
	ResultSet rs=pstm.executeQuery();
	
	if(rs.next()) {
		 
		 c.close();
		
		return true;
	}
	c.close();
	return false;
}
public boolean verifyAccount(String crd) throws ClassNotFoundException, SQLException {
	
	c=SqliteConnection.connector();
	
	String sql="select * from account where cardno=?";
	
	PreparedStatement pstm=c.prepareStatement(sql);
	pstm.setString(1, crd);
	ResultSet rs=pstm.executeQuery();
	
	if(rs.next()) {
		 
		 c.close();
		
		return true;
	}
	c.close();
	return false;
}
public boolean verifyPin() throws ClassNotFoundException, SQLException {
	c=SqliteConnection.connector();
	String sq="select pin_no from account where cardno=?";
	PreparedStatement pstm=c.prepareStatement(sq);
	//System.out.println(crdNo);
	pstm.setString(1, crdNo);
	
	ResultSet rst=pstm.executeQuery();
	
	if(rst.next()) {
		
	String x=rst.getString(1);
	c.close();
	if(x.equals(pwdPin.getText()))
	return true;
	}
		return false;
	
	
} 
public String[] getDetails() throws ClassNotFoundException, SQLException {
	String x[]=new String[2];
	Connection c=SqliteConnection.connector();
	String s1="select amount,owner from account where cardno=?";
	String s2="select name from customer where id=?";
	String id=null;
	PreparedStatement pstm=c.prepareStatement(s1);
	pstm.setString(1, crdNo);
	ResultSet rst=pstm.executeQuery();
	if(rst.next()) {
		x[1]=rst.getString(1);
		id=rst.getString(2);
		PreparedStatement pstm1=c.prepareStatement(s2);
		pstm1.setString(1, id);
		ResultSet rst1=pstm1.executeQuery();
		if(rst1.next()) {
			x[0]=rst1.getString(1);
		}
	}
	
	return x ;
}
public boolean checkBalance() throws ClassNotFoundException, SQLException { 
	Connection c=SqliteConnection.connector();
	String s="select amount from account where cardno=?";
	PreparedStatement pstm=c.prepareStatement(s);
	pstm.setString(1, crdNo);
	ResultSet re=pstm.executeQuery();
	inputVal=Double.parseDouble(txtIpBl.getText());
	
	if(re.next()) {
		curr_balance=Double.parseDouble(re.getString(1));
	if (curr_balance > inputVal) {
		c.close();
		return true;
	}}
	c.close();
	return false;
	}
public void transferFromMyAcc(ActionEvent e) throws ClassNotFoundException, SQLException, IOException {
	String transAcc=txtaccNo.getText();
	System.out.println(transAcc);
	System.out.println(checkBalance());
	System.out.println(	verifyAccount(transAcc));

Connection c=SqliteConnection.connector();
	if(checkBalance()) {
		if(verifyAccount(transAcc)) {
			double val=0;
			String s1="select amount from account where cardno=?";
			PreparedStatement ptm = c.prepareStatement(s1);
			ptm.setString(1, transAcc);
			
			ResultSet rs=ptm.executeQuery();
			if(rs.next()) {
				val=rs.getDouble(1);
			}
			
			val+=Double.parseDouble(txtIpBl.getText());
			String s2="update account set amount=? where cardno=?";
			PreparedStatement pstm=c.prepareStatement(s2);
			pstm.setDouble(1, val);
			pstm.setString(2,transAcc );
			if(pstm.executeUpdate()==1){
				loadSuccess(e);
				
			}
		}
	}
	
}

public void loadSuccess(ActionEvent e) throws IOException {
	Parent p=FXMLLoader.load(getClass().getResource("succesTransaction.fxml"));
	Scene s=new Scene(p);
	Label l=(Label)p.lookup("#lblAccno");
	l.setText(crdNo);
	Label l1=(Label)p.lookup("#lblNm");
	l1.setText(nameCurr);
	Stage stg=new Stage();
	stg.setScene(s);
	stg.show();
	((Node)e.getSource()).getScene().getWindow().hide();
}
public void deposit() {
	
}
public void voidwithdraw() {
	
	
}

	
}
