package model;

import java.util.ArrayList;

public class Game {
	private Alien[] aliens;
	private Nave player;
	private ArrayList<Bala> balas;
	public void setBalas(ArrayList<Bala> balas) {
		this.balas = balas;
	}

	public final int HEIGHT;
	public final int WIDTH;
	private int sizeW;
	private int sizeL;
	private int deltaXN;
	private int deltaYN;
	private int deltaXA;
	private int deltaYA;
	
	public Game(int amountAliens) {
		balas=new ArrayList<>();
		HEIGHT=400;
		WIDTH=300;
		sizeW=15;
		sizeL=30;
		deltaYA=1;
		deltaXA=10;
		deltaYN=10;
		deltaXN=10;
		aliens=new Alien[amountAliens];
		for(int i=0;i<amountAliens;i++) {
			aliens[i]=new Alien("file:.\\src\\sprites\\alien.png",sizeW*i+30, 0, sizeL, sizeW, 0, deltaYA);
		}
		player=new Nave("file:.\\src\\sprites\\nave.png", (WIDTH/2)-sizeW*2, HEIGHT-sizeL*2-15, sizeL, sizeW, deltaXN, deltaYN);
	}
	
	public Alien[] getAliens() {
		return aliens;
	}

	public void setAliens(Alien[] aliens) {
		this.aliens = aliens;
	}

	public Nave getPlayer() {
		return player;
	}

	public void setPlayer(Nave player) {
		this.player = player;
	}
	
	public void moveAlien() {
		for(int i=0;i<aliens.length;i++) {
			if(aliens[i].getDeltaY()+aliens[i].getY()>0) {
				aliens[i].move();
			}
		}
	}
	
	public void movePlayer(int i,int dir) {
		
		if(i==0) {
			
			if(dir==-1) {
				
				if(player.getX()- Math.abs(player.getDeltaX())>0) {
					player.moveX(dir);
				}
			}else if(dir==1) {
				System.out.println(player.getX());
				if(player.getX()+ Math.abs(player.getDeltaX()) <230) {
					player.moveX(dir);
				}
			}
			
			
			
		}else if(i==1) {
			player.moveY(dir);
		}
	}
	
	public void disparar() {
		balas.add(new Bala("file:.\\src\\sprites\\bala.png",player.getX()+(player.getWidth()/2+5),player.getY(), 20, 30, 0, -10));
	}

	public ArrayList<Bala> getBalas() {
		return balas;
	}

	public void actualize() {
		moveAlien();
		for(Bala bala:balas) {
			bala.move();
		}
		
	}
	
}
