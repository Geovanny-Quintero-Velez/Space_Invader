package controller;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
			gr.drawImage(new Image("file:.\\src\\sprites\\fondo.png"), 0, 0,300,400);
			ArrayList<Alien> aliens=main.getGame().getAliens();
			Nave player=main.getGame().getPlayer();
			ArrayList<Bala> balas=main.getGame().getBalas();
			if(main.getGame().isOngoing()) {
				
				Image imageAl=new Image(aliens.get(0).getPath());
				Image imageP=new Image(player.getPath());
				if(balas.size()>0) {
					Image imageB=new Image(balas.get(0).getPath());
					for(Bala bala:balas) {
						if(!bala.isUsed()) {
							gr.drawImage(imageB, bala.getX(), bala.getY(),bala.getLenght(),bala.getWidth());
						}
					}
				}
				for(Alien alien:aliens) {
					gr.drawImage(imageAl, alien.getX(), alien.getY(),alien.getLenght(),alien.getWidth());
				}
				gr.drawImage(imageP,player.getX(),player.getY(),player.getLenght(),player.getWidth());
				
				
				gr.setFont(Font.font("Times New Roman",30));
				gr.setTextAlign(TextAlignment.CENTER);
				gr.fillText(main.getPlayer().getPoints()+"", 30, 30);
				
				
			}else {
				main.getGame().endGame();
				gr.setFont(Font.font("Times New Roman",30));
				gr.setTextAlign(TextAlignment.CENTER);
				gr.fillText(main.getPlayer().getPoints()+"", 30, 30);
				if(main.getGame().isVictory()) {
					gr.fillText("Has Ganado", 130,   180);
				}else gr.fillText("Has Perdido", 130,   180);;
				
			}
			
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
			while (main.getGame().isOngoing()) {
				try {
					Thread.sleep(1000/20);
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
