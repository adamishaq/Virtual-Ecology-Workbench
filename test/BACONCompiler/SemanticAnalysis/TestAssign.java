package BACONCompiler.SemanticAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;

import VEW.Planktonica2.ControllerStructure.*;
import VEW.XMLCompiler.ASTNodes.*;

public class TestAssign {

	@Test
	public void testAssignToStateVariable() {
		FunctionalGroup group = new FunctionalGroup();
		StateVariable stateVar = new StateVariable(group);
		stateVar.setName("testVar");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		stateVar.setVarType((Type) tables.checkTypeTable("$float"));
		group.addToStateVarTable(stateVar);
		AssignNode assign = new AssignNode(new IdNode("testVar"), new NumNode(10));
		assign.setCatagory(group);
		try {
			assign.check();
		}
		catch (SemanticCheckException ex) {
			fail();
		}
	}
	
	@Test
	public void testAssignToFoodSet() {
		FunctionalGroup group = new FunctionalGroup();
		VarietyConcentration conc = new VarietyConcentration(group);
		conc.setName("foodset");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		conc.setVarType(new VarietyType("float", floatType));
		group.addToVarietyConcTable(conc);
		AssignNode assign = new AssignNode(new IdNode("foodset"), new NumNode(5));
		assign.setCatagory(group);
		try {
			assign.check();
		}
		catch (SemanticCheckException ex) {
			return;
		}
		fail();
	}
	
	@Test
	public void testAssignFromFoodSet() {
		FunctionalGroup group = new FunctionalGroup();
		VarietyConcentration conc = new VarietyConcentration(group);
		conc.setName("foodset");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		conc.setVarType(new VarietyType("float", floatType));
		group.addToVarietyConcTable(conc);
		VarietyVariable varState = new VarietyVariable(group);
		varState.setName("foodHappiness");
		varState.setVarType(new VarietyType("float", floatType));
		group.addToVarietyStateTable(varState);
		IdNode id = new IdNode("foodset");
		id.setCatagory(group);
		AssignNode assign = new AssignNode(new IdNode("foodHappiness"), id);
		assign.setCatagory(group);
		try {
			assign.check();
		}
		catch (SemanticCheckException ex) {
			fail();
		}
	}
	
	@Test
	public void testDoubleAssignToLocal() {
		FunctionalGroup group = new FunctionalGroup();
		Local loc = new Local(group);
		loc.setName("testLocal");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		loc.setVarType(floatType);
		group.addToLocalTable(loc);
		AssignNode assign1 = new AssignNode(new IdNode("testLocal"), new NumNode(5));
		AssignNode assign2 = new AssignNode(new IdNode("testLocal"), new NumNode(4));
		assign1.setCatagory(group);
		assign2.setCatagory(group);
		RuleSequenceNode seq = new RuleSequenceNode(assign1, new RuleSequenceNode(assign2));
		try {
			seq.check();
		}
		catch (SemanticCheckException ex) {
			return;
		}
		fail();
	}
	
	@Test
	public void testScalarToVarietyAssign() {
		FunctionalGroup group = new FunctionalGroup();
		StateVariable state = new StateVariable(group);
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		state.setName("stateVar");
		state.setVarType(floatType);
		group.addToStateVarTable(state);
		VarietyVariable var = new VarietyVariable(group);
		var.setName("varietyVar");
		state.setVarType(new VarietyType("float", floatType));
		group.addToVarietyStateTable(var);
		IdNode stateId = new IdNode("stateVar");
		stateId.setCatagory(group);
		IdNode varietyId = new IdNode("varietyVar");
		varietyId.setCatagory(group);
		AssignNode assign = new AssignNode(varietyId, stateId);
		assign.setCatagory(group);
		try {
			assign.check();
		}
		catch (SemanticCheckException ex) {
			fail("Should not be able to assign scalar to variety");
		}
		assign = new AssignNode(stateId, varietyId);
		assign.setCatagory(group);
		try {
			assign.check();
			fail("Should not be able to assign variety to scalar");
		}
		catch (SemanticCheckException ex) {
			return;
		}
	}
	
	@Test
	public void testAssignToAndFromGlobal() {
		FunctionalGroup group = new FunctionalGroup();
		IdNode id = new IdNode("Salinity");
		id.setCatagory(group);
		AssignNode assign = new AssignNode(id, new NumNode(5));
		assign.setCatagory(group);
		try {
			assign.check();
			fail("Should not be able to assign to a global");
		}
		catch (SemanticCheckException ex) {
			
		}
		Local loc = new Local(group);
		loc.setName("local");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		loc.setVarType(floatType);
		group.addToLocalTable(loc);
		IdNode locId = new IdNode("local");
		locId.setCatagory(group);
		assign = new AssignNode(locId, id);
		assign.setCatagory(group);
		try {
			assign.check();
		}
		catch (SemanticCheckException ex) {
			fail("Should be able to assign the value of a global variable");
		}
		
		
	}
	

}
