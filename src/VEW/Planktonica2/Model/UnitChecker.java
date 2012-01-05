package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;

public class UnitChecker {
	
	private static UnitChecker unitchecker;
	
	public static Unit null_unit = new Unit(0,"null",1);
	public static Unit dimensionless_unit = new Unit(0,"dimensionless",1);
	public static Collection<Unit> null_collection = new ArrayList<Unit>();
	public static Collection<Unit> dimensionless_collection = new ArrayList<Unit>();
	
	public static Collection<UnitEquivalence> equivalences = new ArrayList<UnitEquivalence>();
	
	public static UnitChecker getUnitChecker() {
		if (unitchecker == null) {
			unitchecker = new UnitChecker();
			populate_equivalences();
			null_collection.add(null_unit);
			dimensionless_collection.add(dimensionless_unit);
		}
		return unitchecker;
	}
	
	private static void populate_equivalences() {
		Collection<Unit> hours = new ArrayList<Unit>();
		hours.add(new Unit(0,"hours",1));
		Collection<Unit> secs = new ArrayList<Unit>();
		secs.add(new Unit(0,"sec",1));
		//Collection<Unit> seconds = new ArrayList<Unit>();
		//seconds.add(new Unit(0,"sec",2));
		equivalences.add(new UnitEquivalence(secs, 3600, hours));
		//equivalences.add(new UnitEquivalence(seconds, 3600, m));
	}
	
	private ArrayList<Unit> expandUnits(Collection<Unit> units) {
		ArrayList<Unit> expanded = new ArrayList<Unit>();
		for (Unit u : units) {
			int exp = u.getExponent();
			boolean negative = u.getExponent() < 0;
			if (negative)
				exp *= -1;
			for (int i = 0; i < exp; i++) {
				Unit new_u = new Unit(u.getSize(),u.getName(),
						negative ? -1 : 1);
				expanded.add(new_u);
			}
		}
		return expanded;
	}
	
	public boolean CheckUnitCompatability(Collection<Unit> first, Collection<Unit> second) {
		if (first == null || second == null)
			return false;
		ArrayList<Unit> first_array = new ArrayList<Unit>();
		for (Unit u : first) {
			first_array.add(u);
			if (u.getName().equals("null") || u.getName().equals("dimensionless"))
				return true;
		}
		ArrayList<Unit> second_array = new ArrayList<Unit>();
		for (Unit u : second) {
			second_array.add(u);
			if (u.getName().equals("null") || u.getName().equals("dimensionless"))
				return true;
		}
		ArrayList<Unit> leftover_array = new ArrayList<Unit>();
		for (int i = 0; i < first_array.size(); i++) {
			Unit fu = first_array.get(i);
			boolean found = false;
			found = check_for_unit(second_array, fu);
			if (!found) {
				leftover_array.add(fu);
			}
		}
		if (leftover_array.size() > 0) {
			leftover_array = expandUnits(leftover_array);
			if (second_array.size() == 0)
				return false;
			float scale = check_equivalences(leftover_array,expandUnits(second_array));
			System.out.println("Scale LHS by " + scale);
			return (scale != 0);
		}
		return (second_array.size() == 0);
	}

	private float check_equivalences(ArrayList<Unit> leftover_array,
			ArrayList<Unit> second_array) {
		float total_scale = 1;
		for (UnitEquivalence ue : equivalences) {
			if (contains_units(leftover_array,expandUnits(ue.getFirst()))
					&& contains_units(second_array,expandUnits(ue.getSecond()))) {
				for (Unit u : expandUnits(ue.getFirst()))
					check_for_unit(leftover_array, u);
				for (Unit u : expandUnits(ue.getSecond()))
					check_for_unit(second_array, u);
				total_scale *= ue.getScale_factor();
			} else if (contains_units(second_array,expandUnits(ue.getFirst()))
					&& contains_units(leftover_array,expandUnits(ue.getSecond()))) {
				for (Unit u : expandUnits(ue.getSecond()))
					check_for_unit(leftover_array, u);
				for (Unit u : expandUnits(ue.getFirst()))
					check_for_unit(second_array, u);
				total_scale *= (1 / ue.getScale_factor());
			}
		}
		if (leftover_array.size() != 0 || second_array.size() != 0)
			return 0;
		return total_scale;
	}

	private boolean contains_units(Collection<Unit> container, Collection<Unit> contained) {
		ArrayList<Unit> contain_copy = clone_unit_list(container);
		for (Unit u : contained) {
			if (!check_for_unit(contain_copy,u))
				return false;
		}
		return true;
	}
	
	private boolean check_for_unit(ArrayList<Unit> array, Unit u) {
		for (int j = 0; j < array.size(); j++) {
			Unit su = array.get(j);
			if (u.equals(su)) {
				array.remove(j);
				return true;
			}
		}
		return false;
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
	
	public Collection<Unit> add_units(Collection<Unit> first, Collection<Unit> second) {
		ArrayList<Unit> first_array = clone_unit_list(first);
		ArrayList<Unit> second_array = clone_unit_list(second);
		for (Unit u : first_array) {
			if (u.getName().equals("null")) {
				return UnitChecker.null_collection;
			} else if (u.getName().equals("dimensionless")) {
				return second_array;
			}
		}
		return first_array;
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

	public Collection<Unit> power_units(Collection<Unit> units, float value) {
		ArrayList<Unit> first_array = clone_unit_list(units);
		for (Unit u : first_array) {
			u.setExponent(u.getExponent() * ((int)value));
		}
		return first_array;
	}

	public boolean isDimensionless(Collection<Unit> units) {
		for (Unit u : units) {
			if (u.getName().equals("null") || u.getName().equals("dimensionless")) {
				return true;
			}
		}
		return false;
	}
}
