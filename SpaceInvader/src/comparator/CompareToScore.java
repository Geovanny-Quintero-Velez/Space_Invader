package comparator;

import java.util.Comparator;

import model.Nave;

public class CompareToScore implements Comparator<Nave> {
	
	@Override
	public int compare(Nave arg0, Nave arg1) {
		return arg1.getPoints()-arg0.getPoints();
	}

}

