package comparator;

import java.util.Comparator;

import model.Nave;

public class CompareToName implements Comparator<Nave> {

	@Override
	public int compare(Nave arg0, Nave arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}

}

