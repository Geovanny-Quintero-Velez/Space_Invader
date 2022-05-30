package controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	Main main;

	@FXML
	TextField nameTF;
	
	@FXML
	Button startBtn;
	
	@FXML
	public void startPressed() {
		String name = nameTF.getText();
		if(main.binarySearch(0, main.getTop().size(), name)!=null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Name occupied");
			alert.setContentText("The name entered has already been used, please enter a new one.");
			alert.showAndWait();
			
		}else{
			main.showGame(name);
		}
			
		
	}
	
	
	
	public void setMain(Main main) {
		this.main=main;
	}
}
