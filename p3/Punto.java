package p3;

public class Punto implements Comparable<Punto> {
	double x;
	double y;
	public Punto(double x, double y) {
		this.x=x;
		this.y=y;
	}
	@Override
	public int compareTo(Punto o) {
		if (x-o.x<0) {
			return -1;
		}
		else if (x-o.x >0) {
			return 1;
		}
		else {
			return 0;
		}
	}

	
}