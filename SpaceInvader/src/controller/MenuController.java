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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
				if(aliens.size()>0) {
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
					ArrayList<Bala> balasAliens=main.getGame().getBalasAliens();
					if(balasAliens.size()>0) {
						Image imageB=new Image(balasAliens.get(0).getPath());
						for(Bala bala:balasAliens) {
							if(!bala.isUsed()) {
								gr.drawImage(imageB, bala.getX(), bala.getY(),bala.getLenght(),bala.getWidth());
							}
						}
					}
					for(Alien alien:aliens) {
						gr.drawImage(imageAl, alien.getX(), alien.getY(),alien.getLenght(),alien.getWidth());
					}
					gr.drawImage(imageP,player.getX(),player.getY(),player.getLenght(),player.getWidth());
					
					gr.setFill(Color.TEAL);
					gr.setFont(Font.font("Verdana",FontWeight.BOLD,25));
					gr.setTextAlign(TextAlignment.CENTER);
					gr.fillText(main.getPlayer().getPoints()+"", 50, 30);
				}
			}else {
				main.getGame().endGame();
				gr.setFont(Font.font("Verdana", FontWeight.BOLD,20));
				gr.setTextAlign(TextAlignment.CENTER);
				gr.fillText(main.getPlayer().getPoints()+"", 30, 30);
				if(main.getGame().isVictory()) {
					gr.fillText("¡GANASTE!", 130, 180);
				}else {
					gr.fillText("¡PERDISTE!", 130, 180);
				}
				for(int i=0;i<10000;i++) {
					for(int j=0;j<10000;j++) {
						
					}
				}
				main.getTop().add(new Nave(main.getPlayer().getName(), main.getPlayer().getPoints()));
				main.showClassification();
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
					e.printStackTrace();
				}
			}
		});
		hilo.start();
	}

}
