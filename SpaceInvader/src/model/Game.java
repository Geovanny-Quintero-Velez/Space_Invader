package model;

import java.util.ArrayList;

public class Game {
	private ArrayList<Alien> aliens;
	private Nave player;
	private ArrayList<Bala> balas;
	private MoveAlien[] threads;
	public final int ALIENS_VALUE;
	private boolean victory;
	private boolean isOngoing;
	public void setBalas(ArrayList<Bala> balas) {
		this.balas = balas;
	}

	public final int HEIGHT;
	public final int WIDTH;
	private int sizeW;
	private int sizeL;
	private int deltaXN;
	private int deltaYN;
	private double deltaXA;
	private double deltaYA;
	private int amountAliens;
	public Game(String name) {
		isOngoing=true;
		ALIENS_VALUE=100;
		victory=true;
		balas=new ArrayList<>();
		HEIGHT=400;
		WIDTH=300;
		sizeW=15;
		sizeL=30;
		deltaYA=1;
		deltaXA=0;
		deltaYN=10;
		deltaXN=10;
		amountAliens=10;
		aliens = new ArrayList<>();
		threads=new MoveAlien[40];
		player=new Nave(name, "file:.\\src\\sprites\\nave.png", (WIDTH/2)-sizeW*2, HEIGHT-sizeL*2-15, sizeL, sizeW, deltaXN, deltaYN);
		createAliens(amountAliens);
	}
	
	public void createAliens(int amountAliens) {
		int posYA=0;
		int mult=0;
		for(int i=0;i<amountAliens;i++) {
			Alien alien=new Alien("file:.\\src\\sprites\\alien.png",(sizeW+10)*mult, posYA, sizeL, sizeW, deltaXA, deltaYA);
			aliens.add(alien);
			mult++;
			if(i%10==9&&i!=0) {
				posYA+=30;
				mult=0;
			}
			MoveAlien thread=new MoveAlien(alien);
			threads[i]=thread;
			thread.start();
		}
	}
	
	public ArrayList<Alien> getAliens() {
		return aliens;
	}

	public void setAliens(ArrayList<Alien> aliens) {
		this.aliens = aliens;
	}

	public Nave getPlayer() {
		return player;
	}

	public void setPlayer(Nave player) {
		this.player = player;
	}
	
	
	public void movePlayer(int i,int dir) {
		
		if(i==0) {
			
			if(dir==-1) {
				
				if(player.getX()- Math.abs(player.getDeltaX())>-10) {
					player.moveX(dir);
				}
			}else if(dir==1) {
				
				if(player.getX()+ Math.abs(player.getDeltaX()) <230) {
					player.moveX(dir);
				}
			}
			
			
		}else if(i==1) {
			player.moveY(dir);
		}
	}
	
	public void endGame() {
		//TODO do top list
		
	}
	
	public void disparar() {
		balas.add(new Bala("file:.\\src\\sprites\\bala.png",player.getX()+(player.getWidth()/2+5),player.getY(), 6, 8, 0, -10));
	}

	public ArrayList<Bala> getBalas() {
		return balas;
	}
	

	public void actualize() {
		for(int i=0;i<balas.size();i++) {
			Bala bala=balas.get(i);
			bala.move(1);
		}
		if(isOngoing&&aliens.size()==0) {
			balas.clear();
			amountAliens+=10;
			if(amountAliens<=40) {
				createAliens(amountAliens);
			}else {
				isOngoing=false;
			}
			
		}
		if(player.getPoints()==ALIENS_VALUE*100) {
			isOngoing=false;
		}
			
	}
	
	public boolean isVictory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	public boolean isOngoing() {
		return isOngoing;
	}

	public void setOngoing(boolean isOngoing) {
		this.isOngoing = isOngoing;
	}

	private class MoveAlien extends Thread{
		private Alien alien;
		
		public MoveAlien(Alien alien) {
			this.alien=alien;
		}
		
		
		@Override
		public void run() {
			while(alien!=null) {
				try {
					alien.move(1);
					int x1=alien.getX();
					int y1=alien.getY();
					int x2=alien.getX()+alien.getWidth();
					int y2=alien.getY()-alien.getLenght();
					for(int i=0;i<balas.size();i++) {
						Bala bala=balas.get(i);
						if(!bala.isUsed()) {
							int bX=bala.getX();
							int bY=bala.getY();
							if(bX>x1&&bX<x2&&bY<y2) {
								bala.setUsed(true);
								aliens.remove(alien);
								alien=null;
								player.increasePoints(ALIENS_VALUE);
							}
						}
					
					}
					if(y1>=player.getY()) {
						
						aliens.remove(alien);
						alien=null;
						victory=false;
						isOngoing=false;
					}
					sleep(100);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}

	}
	
}
