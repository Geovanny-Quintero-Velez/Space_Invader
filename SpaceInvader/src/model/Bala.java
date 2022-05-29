package model;

public class Bala extends Character {
	private boolean isUsed;
	public Bala(String path, int x, int y, int lenght, int width, int deltaX, int deltaY) {
		super(path, x, y, lenght, width, deltaX, deltaY);
		setUsed(false);
	}
	public boolean isUsed() {
		return isUsed;
	}
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	
}
