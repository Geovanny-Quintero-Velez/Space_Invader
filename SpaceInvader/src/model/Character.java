package model;

public abstract class Character {
	private String path;
	protected int x;
	protected int y;
	private int lenght;
	private int width;
	protected double deltaX;
	protected double deltaY;
	
	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public Character(String path,int x,int y,int lenght,int width,double deltaX, double deltaY) {
		this.path=path;
		this.x=x;
		this.y=y;
		this.lenght=lenght;
		this.width=width;
		setDeltaX(deltaX);
		setDeltaY(deltaY);
	}
	
	public Character() {
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void move(int i) {
		if(i == 0) {
			x += deltaX;
		}else {
			y += deltaY;
		}
	}
}
