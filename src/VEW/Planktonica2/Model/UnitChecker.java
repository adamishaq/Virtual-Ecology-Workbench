package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;

import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;

public class UnitChecker {
	
	private static UnitChecker unitchecker;
	
	public Unit null_unit = new Unit(0,"null",1);
	
	public static UnitChecker getUnitChecker() {
		if (unitchecker == null)
			unitchecker = new UnitChecker();
		return unitchecker;
	}
	
	public boolean CheckUnitCompatability(Collection<Unit> first, Collection<Unit> second) {
		if (first == null || second == null || first.size() != second.size())
			return false;
		ArrayList<Unit> first_array = new ArrayList<Unit>();
		for (Unit u : first) {
			first_array.add(u);
			if (u.getName().equals("null"))
				return true;
		}
		ArrayList<Unit> second_array = new ArrayList<Unit>();
		for (Unit u : second) {
			second_array.add(u);
			if (u.getName().equals("null"))
				return true;
		}
		for (int i = 0; i < first_array.size(); i++) {
			Unit fu = first_array.get(i);
			boolean found = false;
			for (int j = 0; j < second_array.size(); j++) {
				Unit su = second_array.get(j);
				if (fu.equals(su)) {
					found = true;
					second_array.remove(j);
					j--;
				}
			}
			if (!found)
				return false;
		}
		return true;
	}
	
	public Collection<Unit> multiply_units(Collection<Unit> first, Collection<Unit> second) {
		ArrayList<Unit> first_array = clone_unit_list(first);
		ArrayList<Unit> second_array = clone_unit_list(second);
		return combine_units(first_array,second_array);
	}
	
	public Collection<Unit> divide_units(Collection<Unit> first, Collection<Unit> second) {
		ArrayList<Unit> first_array = clone_unit_list(first);
		ArrayList<Unit> second_array = clone_unit_list(second);
		for (Unit u : second_array) {
			u.setExponent(u.getExponent() * -1);
		}
		return combine_units(first_array,second_array);
	}
	
	private ArrayList<Unit> clone_unit_list(Collection<Unit> first) {
		ArrayList<Unit> first_array = new ArrayList<Unit>();
		for (Unit u : first)
			first_array.add(new Unit(u.getSize(),u.getName(),u.getExponent()));
		return first_array;
	}

	private Collection<Unit> combine_units(ArrayList<Unit> first_array, ArrayList<Unit> second_array) {
		for (int i = 0; i < first_array.size(); i++) {
			Unit fu = first_array.get(i);
			for (int j = 0; j < second_array.size(); j++) {
				Unit su = second_array.get(j);
				if (fu.same_unit(su)) {
					second_array.remove(j);
					j--;
					fu.setExponent(fu.getExponent() + su.getExponent());
				}
			}
		}
		first_array.addAll(second_array);
		// Remove all units that have a 0 exponent
		for (int i = 0; i < first_array.size(); i++) {
			if (first_array.get(i).getExponent() == 0 || first_array.get(i).getName().equals("dimensionless")) {
				first_array.remove(i);
				i--;
			}
		}
		if (first_array.isEmpty())
			first_array.add(new Unit(0,"dimensionless",1));
		return first_array;
	}

	public boolean contains_null(Collection<Unit> units) {
		for (Unit u : units) {
			if (u.getName().equals("null"))
				return true;
		}
		return false;
	}
}
