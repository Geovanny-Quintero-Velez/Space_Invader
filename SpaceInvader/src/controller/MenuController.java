package controller;

import java.util.ArrayList;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Alien;
import model.Bala;
import model.Nave;

public class MenuController {
	@FXML
	Canvas canvas1;
	
	public Main main;
	
	GraphicsContext gr;
	
	public void setMain(Main main) {
		this.main=main;
	}
	
	public void initialize() {
		canvas1.setFocusTraversable(true);
		gr=canvas1.getGraphicsContext2D();
	}
	
	public void paint() {
		Platform.runLater(()->{
			Alien[] aliens=main.getGame().getAliens();
			Nave player=main.getGame().getPlayer();
			ArrayList<Bala> balas=main.getGame().getBalas();
			Image imageAl=new Image(aliens[0].getPath());
			Image imageP=new Image(player.getPath());
			if(balas.size()>0) {
				Image imageB=new Image(balas.get(0).getPath());
				for(Bala bala:balas) {
					gr.drawImage(imageB, bala.getX(), bala.getY(),bala.getLenght(),bala.getWidth());
				}
			}
			for(int i=0;i<aliens.length;i++) {
				gr.drawImage(imageAl, aliens[i].getX(), aliens[i].getY(),aliens[i].getLenght(),aliens[i].getWidth());
			}
			gr.drawImage(imageP,player.getX(),player.getY(),player.getLenght(),player.getWidth());
		});
		
	}
	@FXML
	public void movePlayer(KeyEvent e) {
		if (e.getCode() == KeyCode.LEFT) {
			main.movePlayer(0, -1);
		} else if (e.getCode() == KeyCode.RIGHT) {
			main.movePlayer(0, 1);
		} else if (e.getCode() == KeyCode.SPACE) {
			main.disparar();
		}
	}
	
	public void actualize() {
		Thread hilo = new Thread(() -> {
			while (true) {
				try {

					Thread.sleep(1000/50);
					main.actualizeGame();
					paint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		hilo.start();
	}
}
