package VEW.Planktonica2.Model;

import java.util.Collection;

public class UnitEquivalence {

	private Collection<Unit> first;
	private Collection<Unit> second;
	private float scale_factor;
	
	public UnitEquivalence(Collection<Unit> f, float sf, Collection<Unit> s) {
		this.setFirst(f);
		this.setScale_factor(sf);
		this.setSecond(s);
	}

	public void setFirst(Collection<Unit> first) {
		this.first = first;
	}

	public Collection<Unit> getFirst() {
		return first;
	}

	public void setSecond(Collection<Unit> second) {
		this.second = second;
	}

	public Collection<Unit> getSecond() {
		return second;
	}

	public void setScale_factor(float scale_factor) {
		this.scale_factor = scale_factor;
	}

	public float getScale_factor() {
		return scale_factor;
	}
	
}
