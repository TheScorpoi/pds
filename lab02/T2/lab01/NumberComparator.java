package lab02.T2.lab01;

import java.util.Comparator;

public class NumberComparator implements Comparator<WordFound> {
	@Override
	public int compare(WordFound w1,WordFound w2) {
		if(w1.getNumber() > w2.getNumber())
			return 1;
		else if (w1.getNumber() < w2.getNumber())
			return -1;
		else return 0 ;
	}
}
