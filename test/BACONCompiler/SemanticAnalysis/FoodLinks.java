package BACONCompiler.SemanticAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;

import VEW.Planktonica2.model.FunctionalGroup;
import VEW.Planktonica2.model.Type;
import VEW.Planktonica2.model.VarietyConcentration;
import VEW.Planktonica2.model.VarietyType;
import VEW.Planktonica2.model.VarietyVariable;
import VEW.XMLCompiler.ASTNodes.AmbientVariableTables;
import VEW.XMLCompiler.ASTNodes.BinOpNode;
import VEW.XMLCompiler.ASTNodes.ConstructedASTree;
import VEW.XMLCompiler.ASTNodes.IdNode;
import VEW.XMLCompiler.ASTNodes.MathematicalOperator;

public class FoodLinks {

	@Test
	public void testMultiplicationWithSameLinks() {
		FunctionalGroup group = new FunctionalGroup("");
		VarietyConcentration foodSet = new VarietyConcentration(group);
		foodSet.setName("foodset");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = tables.checkTypeTable("$float");
		foodSet.setVarType(new VarietyType("float", floatType));
		group.addToVarietyConcTable(foodSet);
		VarietyVariable state1 = new VarietyVariable(group);
		state1.setName("state1");
		state1.setVarType(new VarietyType("float", floatType));
		state1.setLinkConcentration(foodSet);
		group.addToVarietyStateTable(state1);
		VarietyVariable state2 = new VarietyVariable(group);
		state2.setName("state2");
		state2.setVarType(new VarietyType("float", floatType));
		state2.setLinkConcentration(foodSet);
		group.addToVarietyStateTable(state2);
		IdNode id1 = new IdNode("state1");
		IdNode id2 = new IdNode("state2");
		BinOpNode binOp = new BinOpNode(MathematicalOperator.MULTIPLY, id1, id2);
		ConstructedASTree constr = new ConstructedASTree(binOp);
		binOp.check(group, constr);
	}
	
	@Test
	public void testMultiplicationWithDifferentLinks() {
		FunctionalGroup group = new FunctionalGroup("");
		VarietyConcentration foodSet1 = new VarietyConcentration(group);
		foodSet1.setName("foodset1");
		AmbientVariableTables tables = AmbientVariableTables.getTables();
		Type floatType = tables.checkTypeTable("$float");
		foodSet1.setVarType(new VarietyType("float", floatType));
		group.addToVarietyConcTable(foodSet1);
		VarietyConcentration foodSet2 = new VarietyConcentration(group);
		foodSet2.setName("foodset1");
		foodSet2.setVarType(new VarietyType("float", floatType));
		group.addToVarietyConcTable(foodSet2);
		VarietyVariable state1 = new VarietyVariable(group);
		state1.setName("state1");
		state1.setVarType(new VarietyType("float", floatType));
		state1.setLinkConcentration(foodSet1);
		group.addToVarietyStateTable(state1);
		VarietyVariable state2 = new VarietyVariable(group);
		state2.setName("state2");
		state2.setVarType(new VarietyType("float", floatType));
		state2.setLinkConcentration(foodSet2);
		group.addToVarietyStateTable(state2);
		IdNode id1 = new IdNode("state1");
		IdNode id2 = new IdNode("state2");
		BinOpNode binOp = new BinOpNode(MathematicalOperator.MULTIPLY, id1, id2);
		ConstructedASTree constr = new ConstructedASTree(binOp);
		binOp.check(group, constr);
		if (!constr.hasExceptions())
			fail("Should not pass if the link concentrations are different");
	}

}
