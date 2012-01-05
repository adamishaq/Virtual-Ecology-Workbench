package VEW.Common;

public class Pair<A,B> {
	private A first;
	private B second;
	
	public Pair(A first, B second){
		this.setFirst(first);
		this.setSecond(second);
	}

	public A getFirst() {
		return first;
	}

	public void setFirst(A first) {
		this.first = first;
	}

	public B getSecond() {
		return second;
	}

	public void setSecond(B second) {
		this.second = second;
	}
}
