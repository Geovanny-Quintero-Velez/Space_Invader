package controller;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ClassificationController {

	@FXML
	TextArea classTA;
	
	private Main main;
	
	public void initialize() {
		String name = main.getGame().getPlayer().getName();
		int points = main.getGame().getPlayer().getPoints();
		String message = name +" - "+points;
		classTA.setText(message);
	}
	
	public void setMain(Main main) {
		this.main=main;
	}
}
