package VEW.Planktonica2.Model;

import java.util.ArrayList;
import java.util.Collection;

import VEW.Planktonica2.DisplayOptions;

public class UnitChecker {
	
	private static UnitChecker unitchecker;
	
	public static Unit null_unit = new Unit(0,"null",1);
	public static Unit dimensionless_unit = new Unit(0,"dimensionless",1);
	public static Collection<Unit> null_collection = new ArrayList<Unit>();
	public static Collection<Unit> dimensionless_collection = new ArrayList<Unit>();
	
	public static ArrayList<UnitEquivalence> base_equivalences = new ArrayList<UnitEquivalence>();
	public static ArrayList<UnitEquivalence> reciprocal_equivalences = new ArrayList<UnitEquivalence>();
	public static Collection<UnitEquivalence> equivalences = new ArrayList<UnitEquivalence>();
	
	public static UnitChecker getUnitChecker() {
		if (unitchecker == null) {
			unitchecker = new UnitChecker();
			//populate_equivalences();
			null_collection.add(null_unit);
			dimensionless_collection.add(dimensionless_unit);
		}
		return unitchecker;
	}
	
	private static void populate_equivalences() {
		// SI units:
		Unit seconds = new Unit(0,"s",1);
		Unit per_second = new Unit(0,"s",-1);
		Unit kg = new Unit(0,"kg",1);
		Unit meter = new Unit(0,"m",1);
		Unit kelvin = new Unit(0,"K",1);
		// Other Time units
		Collection<Unit> hours = new ArrayList<Unit>();
		hours.add(new Unit(0,"hours",1));
		Collection<Unit> secs = new ArrayList<Unit>();
		secs.add(seconds);
		Collection<Unit> mins = new ArrayList<Unit>();
		mins.add(new Unit(0,"mins",1));
		Collection<Unit> days = new ArrayList<Unit>();
		days.add(new Unit(0,"days",1));
		Collection<Unit> years = new ArrayList<Unit>();
		years.add(new Unit(0,"years",1));
		// Time Equivalences
		add_to_base(new UnitEquivalence(secs, 60, mins));
		add_to_base(new UnitEquivalence(mins, 60, hours));
		add_to_base(new UnitEquivalence(hours, 24, days));
		add_to_base(new UnitEquivalence(days, 365, years));
		
		// Energy
		// Watts
		Unit watts = new Unit(0,"W",1);
		Collection<Unit> watt = new ArrayList<Unit>();
		watt.add(watts);
		Collection<Unit> watt_equiv = new ArrayList<Unit>();
		watt_equiv.add(kg);
		watt_equiv.add(meter); watt_equiv.add(meter);
		watt_equiv.add(per_second); watt_equiv.add(per_second); watt_equiv.add(per_second);
		// Joules
		Collection<Unit> joule = new ArrayList<Unit>();
		joule.add(new Unit(0,"J",1));
		Collection<Unit> joule_equiv = new ArrayList<Unit>();
		joule_equiv.add(watts);
		joule_equiv.add(seconds);
		add_to_base(new UnitEquivalence(watt, 1, watt_equiv));
		add_to_base(new UnitEquivalence(joule, 1, joule_equiv));
	}
	
	private static boolean add_to_base(UnitEquivalence new_ue) {
		UnitEquivalence new_ue2 = new UnitEquivalence(new_ue.getSecond(), 
				(1 / new_ue.getScale_factor()), new_ue.getFirst());
		if (!contains_equivalence(base_equivalences,new_ue)
				&& !contains_equivalence(base_equivalences, new_ue2)) {
			base_equivalences.add(new_ue);
			reciprocal_equivalences.add(new_ue2);
			equivalences.clear();
			equivalences.addAll(base_equivalences);
			equivalences.addAll(reciprocal_equivalences);
			generate_transitive_equivalences();
			/*
			System.out.println("********************");
			for (UnitEquivalence ue : equivalences) {
				String equ = "";
				for (Unit u : simplify(ue.getFirst()))
					equ += "(" + (u.format().replaceAll("<sup>", "^")).replaceAll("</sup>", "") + ")";
				equ += " ---" + ue.getScale_factor() + "--> ";
				for (Unit u : simplify(ue.getSecond()))
					equ += "(" + (u.format().replaceAll("<sup>", "^")).replaceAll("</sup>", "") + ")";
				System.out.println(equ);
			}
			System.out.println("********************\n");
			*/
			return true;
		}
		return false;
	}

	private static void generate_transitive_equivalences() {
		ArrayList<UnitEquivalence> to_add = new ArrayList<UnitEquivalence>();
		for (UnitEquivalence ue : equivalences)
			to_add.add(ue);
		for (UnitEquivalence ue : equivalences) {
			for (UnitEquivalence ue2 : equivalences) {
				if (ue.getSecond().size() > 1) {
					Object[] units = ue.getSecond().toArray();
					for (int i = 0; i < units.length; i++) {
						Unit u = (Unit)units[i];
						if(matches(u,ue2.getFirst())) {
							//System.out.println("Found " + u.format() + " in " + ue2.getFirst().toString()
							//		+ " -> " + ue2.getSecond().toString());
							ArrayList<Unit> new_first = new ArrayList<Unit>();
							for (int j = 0; j < units.length; j++) {
								if (j != i) {
									new_first.add((Unit)units[j]);
								}
							}
							new_first.addAll(ue2.getSecond());
							new_first = simplify(new_first);
							UnitEquivalence new_ue = new UnitEquivalence(new_first,
									(ue.getScale_factor() * ue2.getScale_factor()), ue.getSecond());
							if (!contains_equivalence(to_add,new_ue))
								to_add.add(new_ue);
						}
					}
				}
				if (matches(ue.getSecond(),ue2.getSecond()) && !matches(ue.getFirst(),ue2.getFirst())) {
					UnitEquivalence new_ue = new UnitEquivalence(ue.getFirst(),
							(ue.getScale_factor() / ue2.getScale_factor()), ue2.getFirst());
					if (!contains_equivalence(to_add,new_ue))
						to_add.add(new_ue);
				}
				if (matches(ue.getSecond(),ue2.getFirst())) {
					UnitEquivalence new_ue = new UnitEquivalence(ue.getFirst(),
							(ue.getScale_factor() * ue2.getScale_factor()), ue2.getSecond());
					if (!contains_equivalence(to_add,new_ue))
						to_add.add(new_ue);
				}
			}
		}
		// If any new ones have been added, search for more
		if (to_add.size() > equivalences.size()) {
			equivalences = to_add;
			generate_transitive_equivalences();
		}
	}
	
	private static ArrayList<Unit> simplify(Collection<Unit> array) {
		ArrayList<Unit> simplified = new ArrayList<Unit>();
		Object[] units = array.toArray();
		for (int i = 0; i < units.length; i++) {
			if (units[i] != null) {
				Unit u = (Unit)units[i];
				Unit to_add = new Unit(u.getSize(),u.getName(),u.getExponent());
				for (int j = 0; j < units.length; j++) {
					if (j != i && units[j] != null && u.same_unit((Unit)units[j])){
						to_add.setExponent(to_add.getExponent() + ((Unit)units[j]).getExponent());
						units[j] = null;
					}
				}
				units[i] = null;
				if (to_add.getExponent() != 0)
					simplified.add(to_add);
			}
		}
		if (simplified.size() == 0)
			simplified.addAll(UnitChecker.dimensionless_collection);
		return simplified;
	}

	private static boolean contains_equivalence(
			ArrayList<UnitEquivalence> equivs, UnitEquivalence ue) {
		if (matches(ue.getFirst(),ue.getSecond()))
			return true;
		for (UnitEquivalence u : equivs) {
			if (matches(u.getFirst(),ue.getFirst()) && matches(u.getSecond(),ue.getSecond()))
				return true;
		}
		return false;
	}

	private static boolean matches(Collection<Unit> first, Collection<Unit> second) {
		if (first == null || second == null)
			return false;
		ArrayList<Unit> clone = clone_unit_list(second);
		for (Unit u : first) {
			if (!check_for_unit(clone,u))
				return false;
		}
		return clone.size() == 0;
	}
	
	private static boolean matches(Unit first, Collection<Unit> second) {
		Object[] sec = second.toArray();
		return (second != null && second.size() == 1 && ((Unit)sec[0]).equals(first));
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
	
	public float CheckUnitCompatability(Collection<Unit> first, Collection<Unit> second) {
		if (first == null || second == null)
			return 0;
		ArrayList<Unit> first_array = new ArrayList<Unit>();
		for (Unit u : first) {
			first_array.add(u);
			if (u.getName().equals("null") || u.getName().equals("dimensionless"))
				return 1;
		}
		ArrayList<Unit> second_array = new ArrayList<Unit>();
		for (Unit u : second) {
			second_array.add(u);
			if (u.getName().equals("null") || u.getName().equals("dimensionless"))
				return 1;
		}
		// Put the units in base form if possible
		float scale = 1;
		if (DisplayOptions.getOptions().ATTEMPT_TYPE_CONVERSION && base_form(first_array) != null) {
			UnitBaseForm ubf = base_form(first_array);
			if (ubf.getBaseform().contains(dimensionless_unit))
				return 1;
			first_array = ubf.getBaseform();
			if (scale == 1)
				scale = ubf.getScalefactor();
			else
				scale *= ubf.getScalefactor();
		}
		if (DisplayOptions.getOptions().ATTEMPT_TYPE_CONVERSION && base_form(second_array) != null) {
			UnitBaseForm ubf = base_form(second_array);
			if (ubf.getBaseform().contains(dimensionless_unit))
				return 1;
			second_array = ubf.getBaseform();
			scale /= ubf.getScalefactor();
			//scale = (float)Math.round(scale * 100000) / 100000;
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
		if (DisplayOptions.getOptions().ATTEMPT_TYPE_CONVERSION && leftover_array.size() > 0) {
			leftover_array = expandUnits(leftover_array);
			if (second_array.size() == 0)
				return 0;
			scale = check_equivalences(leftover_array,expandUnits(second_array));
			return scale;
		}
		return ((second_array.size() == 0 && leftover_array.size() == 0) ? scale : 0);
	}

	private float check_equivalences(ArrayList<Unit> leftover_array,
			ArrayList<Unit> second_array) {
		float total_scale = 1;
		leftover_array = expandUnits(simplify(leftover_array));
		second_array = expandUnits(simplify(second_array));
		boolean repeat = true;
		while (repeat) {
			repeat = false;
			for (UnitEquivalence ue : equivalences) {
				if ((contains_units(leftover_array,expandUnits(ue.getFirst()))
						&& contains_units(second_array,expandUnits(ue.getSecond())))) {
					for (Unit u : expandUnits(ue.getFirst()))
						check_for_unit(leftover_array, u);
					for (Unit u : expandUnits(ue.getSecond()))
						check_for_unit(second_array, u);
					total_scale *= ue.getScale_factor();
					repeat = true;
				} else if (contains_units(second_array,expandUnits(ue.getFirst()))
						&& contains_units(leftover_array,expandUnits(ue.getSecond()))) {
					for (Unit u : expandUnits(ue.getSecond()))
						check_for_unit(leftover_array, u);
					for (Unit u : expandUnits(ue.getFirst()))
						check_for_unit(second_array, u);
					total_scale *= (1 / ue.getScale_factor());
					repeat = true;
				} else if (contains_units(leftover_array,inverse(expandUnits(ue.getFirst())))
						&& contains_units(second_array,inverse(expandUnits(ue.getSecond())))) {
					for (Unit u : inverse(expandUnits(ue.getFirst())))
						check_for_unit(leftover_array, u);
					for (Unit u : inverse(expandUnits(ue.getSecond())))
						check_for_unit(second_array, u);
					total_scale *= ue.getScale_factor();
					repeat = true;
				}
			}
		}
		if (leftover_array.size() != 0 || second_array.size() != 0) {
			/*System.out.println("+++++++++++++++++");
			for (Unit u : leftover_array) {
				System.out.println("Unmatched 1:" + u.format());
			}
			for (Unit u : second_array) {
				System.out.println("Unmatched 2:" + u.format());
			}*/
			return 0;
		}
		return total_scale;
	}

	private Collection<Unit> inverse(ArrayList<Unit> units) {
		Collection<Unit> invert = new ArrayList<Unit>();
		for (Unit u : units) {
			invert.add(new Unit(u.getSize(),u.getName(),(-1 * u.getExponent())));
		}
		return invert;
	}

	private boolean contains_units(Collection<Unit> container, Collection<Unit> contained) {
		ArrayList<Unit> contain_copy = clone_unit_list(container);
		for (Unit u : contained) {
			if (!check_for_unit(contain_copy,u))
				return false;
		}
		return true;
	}
	
	private UnitBaseForm base_form(Collection<Unit> units) {
		ArrayList<Unit> base = new ArrayList<Unit>();
		units = simplify(units);
		float scale = 1;
		for (Unit u : units) {
			if (isBaseUnit(u)) {
				base.add(u);
			} else {
				boolean found = false;
				for (UnitEquivalence ue : equivalences) {
					if (matches(u,ue.getFirst()) && allBaseUnits(ue.getSecond())) {
						base.addAll(ue.getSecond());
						scale *= ue.getScale_factor();
						found = true;
						break;
					}
				}
				if (!found)
					return null;
			}		
		}
		return new UnitBaseForm(simplify(base),scale);
	}
	
	private boolean isBaseUnit(Unit u) {
		return (u.getName().equals("s") || u.getName().equals("m") || u.getName().equals("kg")
				|| u.getName().equals("A") || u.getName().equals("K") || u.getName().equals("cd")
				|| u.getName().equals("mol"));
	}
	
	private boolean allBaseUnits(Collection<Unit> units) {
		for (Unit u : units) {
			if (!isBaseUnit(u))
				return false;
		}
		return true;
	}
	
	private static boolean check_for_unit(ArrayList<Unit> array, Unit u) {
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
	
	private static ArrayList<Unit> clone_unit_list(Collection<Unit> first) {
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
	
	public Collection<String> getEquivalences() {
		ArrayList<String> equivs = new ArrayList<String>();
		for (UnitEquivalence ue : base_equivalences) {
			String equ = "";
			for (Unit u : simplify(ue.getFirst()))
				equ += "(" + (u.format().replaceAll("<sup>", "^")).replaceAll("</sup>", "") + ")";
			equ += " ---" + ue.getScale_factor() + "--> ";
			for (Unit u : simplify(ue.getSecond()))
				equ += "(" + (u.format().replaceAll("<sup>", "^")).replaceAll("</sup>", "") + ")";
			equivs.add(equ);
		}
		return equivs;
	}

	public void remove_base_equivalence(int remove) {
		UnitChecker.base_equivalences.remove(remove);
		equivalences.clear();
		equivalences.addAll(base_equivalences);
		generate_transitive_equivalences();
	}

	public boolean add_equivalence(Collection<Unit> first,
			float scale_factor, Collection<Unit> second) {
		UnitEquivalence new_ue = new UnitEquivalence(first,
				scale_factor, second);
		return add_to_base(new_ue);
	}

	public String getOutputEquivalence() {
		String equivs = "";
		for (UnitEquivalence ue : base_equivalences) {
			equivs += "<unit>";
			Object[] units = ue.getFirst().toArray();
			for (int i = 0; i < ue.getFirst().size(); i++) {
				Unit u = (Unit) units[i];
				if (i == 0) 
					equivs += u.getSize() + "," + u.getName() + "," + u.getExponent();
				else
					equivs += "," + u.getSize() + "," + u.getName() + "," + u.getExponent();	
			}
			equivs += "</unit><scale>" + ue.getScale_factor() + "</scale><unit>";
			units = ue.getSecond().toArray();
			for (int i = 0; i < ue.getSecond().size(); i++) {
				Unit u = (Unit) units[i];
				if (i == 0) 
					equivs += u.getSize() + "," + u.getName() + "," + u.getExponent();
				else
					equivs += "," + u.getSize() + "," + u.getName() + "," + u.getExponent();	
			}
			equivs += "</unit>\n";
		}
		return equivs;
	}
}
