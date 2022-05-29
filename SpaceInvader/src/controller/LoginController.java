package controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	Main main;

	@FXML
	TextField nameTF;
	
	@FXML
	Button startBtn;
	
	public void startPressed() {
		String name = nameTF.getText();
		main.showGame(name);
	}
	
	public void setMain(Main main) {
		this.main=main;
	}
}
