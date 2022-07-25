package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	TextField txtCrdNo;
	public void getIt(ActionEvent e) {
		
		
		
		String x=txtCrdNo.getText();
		System.out.println(x);
		
		
	}
	
	
	
	
}
