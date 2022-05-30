package controller;

import java.util.ArrayList;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Nave;

public class ClassificationController {

	@FXML
	TextArea classTA;
	
	
	private Main main;
	
	@FXML
	public void initialize() {
		
	}
	
	public void setMain(Main main) {
		this.main=main;
		String message = "";
		ArrayList<Nave> top = main.getTop();
		int maxPlayers = 5;
		
		if(top.size()<=5) maxPlayers = top.size();
		
		for(int i=0;i<maxPlayers;i++) {
			message += (i+1)+". "+top.get(i).getName() +" - "+top.get(i).getPoints()+"\n";
		}
		for(int i=0;i<5-top.size();i++) {
			message += "N/A - N/A"+"\n";
		}
		classTA.setText(message);
	}
}