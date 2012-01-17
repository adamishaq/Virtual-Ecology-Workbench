package unit_type;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;


public class TestUnits {

	@Test
	public void testUnitEquivalence() {
		Unit seconds1 = new Unit(0,"seconds",1);
		Collection<Unit> sec1 = new ArrayList<Unit>();
		sec1.add(seconds1);
		Unit seconds2 = new Unit(0,"seconds",1);
		Collection<Unit> sec2 = new ArrayList<Unit>();
		sec2.add(seconds2);
		Unit seconds3 = new Unit(0,"seconds",2);
		Collection<Unit> sec3 = new ArrayList<Unit>();
		sec3.add(seconds3);
		Unit meters = new Unit(0,"meters",1);
		Collection<Unit> meter = new ArrayList<Unit>();
		meter.add(meters);
		// Check two units that should be compatible
		if(UnitChecker.getUnitChecker().CheckUnitCompatability(sec1, sec2) != 1)
			fail("seconds ^1 should be compatible with seconds ^1");
		// Check two units with the same name but different exponents
		if(UnitChecker.getUnitChecker().CheckUnitCompatability(sec1, sec3) != 0)
			fail("seconds ^1 should not be compatible with seconds ^2");
		// Check two units with different names
		if(UnitChecker.getUnitChecker().CheckUnitCompatability(sec1, meter) != 0)
			fail("seconds ^1 should not be compatible with meters ^1");
		// Check all units match null and dimensionless
		if (UnitChecker.getUnitChecker().CheckUnitCompatability(UnitChecker.null_collection, sec1) != 1
			|| UnitChecker.getUnitChecker().CheckUnitCompatability(UnitChecker.null_collection, meter) != 1
			|| UnitChecker.getUnitChecker().CheckUnitCompatability(UnitChecker.dimensionless_collection, sec1) != 1
			|| UnitChecker.getUnitChecker().CheckUnitCompatability(UnitChecker.dimensionless_collection, meter) != 1) {
			fail("all units should be compatible with null and dimensionless");
		}
	}
	
	@Test
	public void testUnitMultiplication() {
		Unit seconds1 = new Unit(0,"seconds",1);
		Collection<Unit> sec1 = new ArrayList<Unit>();
		sec1.add(seconds1);
		Unit seconds2 = new Unit(0,"seconds",1);
		Collection<Unit> sec2 = new ArrayList<Unit>();
		sec2.add(seconds2);
		Unit seconds3 = new Unit(0,"seconds",2);
		Collection<Unit> sec3 = new ArrayList<Unit>();
		sec3.add(seconds3);
		Unit seconds4 = new Unit(0,"seconds",4);
		Collection<Unit> sec4 = new ArrayList<Unit>();
		sec4.add(seconds4);
		Unit meters = new Unit(0,"meters",1);
		Collection<Unit> meter = new ArrayList<Unit>();
		meter.add(meters);
		sec4.add(meters);
		// Check basic multiplication of units
		Collection<Unit> test = UnitChecker.getUnitChecker().multiply_units(sec1, sec2);
		if(UnitChecker.getUnitChecker().CheckUnitCompatability(test, sec3) != 1)
			fail("(seconds ^1  * seconds ^1) should be compatible with seconds ^2");
		// Check complex multiplication of units
		Collection<Unit> test2 = UnitChecker.getUnitChecker().multiply_units(sec3, meter);
		test = UnitChecker.getUnitChecker().multiply_units(test, test2);
		if(UnitChecker.getUnitChecker().CheckUnitCompatability(test, sec4) != 1)
			fail("Both results should be sec^4 * meters");
	}
	
	@Test
	public void testUnitDivision() {
		Unit seconds1 = new Unit(0,"seconds",1);
		Collection<Unit> sec1 = new ArrayList<Unit>();
		sec1.add(seconds1);
		Unit seconds2 = new Unit(0,"seconds",1);
		Collection<Unit> sec2 = new ArrayList<Unit>();
		sec2.add(seconds2);
		Unit seconds3 = new Unit(0,"seconds",2);
		Collection<Unit> sec3 = new ArrayList<Unit>();
		sec3.add(seconds3);
		Unit seconds4 = new Unit(0,"seconds",4);
		Collection<Unit> sec4 = new ArrayList<Unit>();
		sec4.add(seconds4);
		Unit meters = new Unit(0,"meters",1);
		Collection<Unit> meter = new ArrayList<Unit>();
		meter.add(meters);
		sec4.add(meters);
		// Check division of units
		Collection<Unit> test = UnitChecker.getUnitChecker().divide_units(sec3, sec2);
		if(UnitChecker.getUnitChecker().CheckUnitCompatability(test, sec1) != 1)
			fail("(seconds ^2  / seconds ^1) should be compatible with seconds ^1");
		// Check complex division of units
		test = UnitChecker.getUnitChecker().divide_units(sec4, sec1);
		test = UnitChecker.getUnitChecker().divide_units(test, sec1);
		test = UnitChecker.getUnitChecker().divide_units(test, sec1);
		test = UnitChecker.getUnitChecker().divide_units(test, sec1);
		if(UnitChecker.getUnitChecker().CheckUnitCompatability(test, meter) != 1)
			fail("Complex divide failed");
	}
	

}
