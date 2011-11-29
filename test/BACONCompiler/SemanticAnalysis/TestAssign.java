package BACONCompiler.SemanticAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;

import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Local;
import VEW.Planktonica2.Model.StateVariable;
import VEW.Planktonica2.Model.Type;
import VEW.Planktonica2.Model.VarietyConcentration;
import VEW.Planktonica2.Model.VarietyType;
import VEW.Planktonica2.Model.VarietyVariable;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;
import VEW.XMLCompiler.ASTNodes.AssignNode;
import VEW.XMLCompiler.ASTNodes.ConstructedASTree;
import VEW.XMLCompiler.ASTNodes.IdNode;
import VEW.XMLCompiler.ASTNodes.NumNode;
import VEW.XMLCompiler.ASTNodes.RuleSequenceNode;


public class TestAssign {

	@Test
	public void testAssignToStateVariable() {
		FunctionalGroup group = new FunctionalGroup("");
		StateVariable stateVar = new StateVariable();
		stateVar.setName("testVar");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		stateVar.setVarType(tables.checkTypeTable("$float"));
		group.addToStateVarTable(stateVar);
		AssignNode assign = new AssignNode(new IdNode("testVar",0), new NumNode(10,0),0);
		assign.check(group, new ConstructedASTree(assign));
	}
	
	@Test
	public void testAssignToFoodSet() {
		FunctionalGroup group = new FunctionalGroup("");
		VarietyConcentration conc = new VarietyConcentration();
		conc.setName("foodset");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = tables.checkTypeTable("$float");
		conc.setVarType(new VarietyType("float", floatType));
		group.addToVarietyConcTable(conc);
		AssignNode assign = new AssignNode(new IdNode("foodset",0), new NumNode(5,0),0);
		ConstructedASTree constr = new ConstructedASTree(assign);
		assign.check(group, constr);
		if (!constr.hasExceptions())
			fail();
	}
	
	@Test
	public void testAssignFromFoodSet() {
		FunctionalGroup group = new FunctionalGroup("");
		VarietyConcentration conc = new VarietyConcentration();
		conc.setName("foodset");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		conc.setVarType(new VarietyType("float", floatType));
		group.addToVarietyConcTable(conc);
		VarietyVariable varState = new VarietyVariable(group);
		varState.setName("foodHappiness");
		varState.setVarType(new VarietyType("float", floatType));
		group.addToVarietyStateTable(varState);
		IdNode id = new IdNode("foodset",0);
		AssignNode assign = new AssignNode(new IdNode("foodHappiness",0), id,0);
		assign.check(group, new ConstructedASTree(assign));
	}
	
	@Test
	public void testDoubleAssignToLocal() {
		FunctionalGroup group = new FunctionalGroup("");
		Local loc = new Local();
		loc.setName("testLocal");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		loc.setVarType(floatType);
		group.addToLocalTable(loc);
		AssignNode assign1 = new AssignNode(new IdNode("testLocal",0), new NumNode(5,0),0);
		AssignNode assign2 = new AssignNode(new IdNode("testLocal",0), new NumNode(4,0),0);
		RuleSequenceNode seq = new RuleSequenceNode(assign1, new RuleSequenceNode(assign2));
		ConstructedASTree constr = new ConstructedASTree(seq);
		seq.check(group, constr);
		if (!constr.hasExceptions())
			fail();
	}
	
	@Test
	public void testScalarToVarietyAssign() {
		FunctionalGroup group = new FunctionalGroup("");
		StateVariable state = new StateVariable();
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		state.setName("stateVar");
		state.setVarType(floatType);
		group.addToStateVarTable(state);
		VarietyVariable var = new VarietyVariable(group);
		var.setName("varietyVar");
		var.setVarType(new VarietyType("float", floatType));
		group.addToVarietyStateTable(var);
		IdNode stateId = new IdNode("stateVar",0);
		IdNode varietyId = new IdNode("varietyVar",0);
		AssignNode assign = new AssignNode(varietyId, stateId,0);
		ConstructedASTree constr = new ConstructedASTree(assign);
		assign.check(group, new ConstructedASTree(assign));
		if (constr.hasExceptions()) {
			fail("Should be able to assign scalar to variety");
		}
		assign = new AssignNode(stateId, varietyId,0);
		constr = new ConstructedASTree(assign);
		assign.check(group, constr);
		if (!constr.hasExceptions())
			fail("Should not be able to assign variety to scalar");
	}
	
	@Test
	public void testAssignToAndFromGlobal() {
		FunctionalGroup group = new FunctionalGroup("");
		IdNode id = new IdNode("Salinity",0);
		AssignNode assign = new AssignNode(id, new NumNode(5,0),0);
		ConstructedASTree constr = new ConstructedASTree(assign);
		assign.check(group, constr);
		if (!constr.hasExceptions())
			fail("Should not be able to assign to a global");
		Local loc = new Local();
		loc.setName("local");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = (Type) tables.checkTypeTable("$float");
		loc.setVarType(floatType);
		group.addToLocalTable(loc);
		IdNode locId = new IdNode("local",0);
		assign = new AssignNode(locId, id,0);
		assign.check(group, new ConstructedASTree(assign));
		
		
	}
	

}
