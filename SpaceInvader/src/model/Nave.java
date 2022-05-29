package model;

public class Nave extends Character {
	private int points;
	private String name;
	
	
	public Nave(String name, String path, int x, int y, int lenght, int width, int deltaX, int deltaY) {
		super(path, x, y, lenght, width, deltaX, deltaY);
		this.name = name;
		points=0;
	}
	
	public void increasePoints(int increase) {
		points = getPoints() + increase;
	}

	public void moveX(int dir) {
		if(dir==1) {
			 moveRight();
		}else if(dir==-1) {
			 moveLeft();
		}
		
	}
	
	public void moveLeft() {
		super.x-=super.deltaX;
	}
	public void moveRight() {
		super.x+=super.deltaX;
	}
	
	public void moveY(int dir) {
		setDeltaY(getDeltaX()*dir);
		super.y+=super.deltaY;
	}

	public int getPoints() {
		return points;
	}
	
	public String getName() {
		return name;
	}

}
