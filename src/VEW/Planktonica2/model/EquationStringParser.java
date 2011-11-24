package VEW.Planktonica2.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import VEW.Common.StringTools;

public class EquationStringParser {
	private String equationString;
	/*
	public static void main(String[] args) {
		String eqString = "\\assign{\\var{C_pmax},\\conditional{\\greater{\\var{C_N$Pool},\\var{C_pmax}},\\var{C_N$Pool},\\var{C_pmax}}}";
		EquationStringParser parser = new EquationStringParser(eqString);
		System.out.print(parser.parseRuleString(eqString));
	}*/
	
	public EquationStringParser(String equationString) {
		this.equationString = equationString;
	}
	
	public String parseEquationString() {
		return parseRuleString(equationString) + "\n";
		
	}

	private String parseRuleString(String ruleString) {
		String rule = new String();
		String[] ruleParts = extractRuleType(ruleString);
		String ruleName = ruleParts[0];
		String argsString = extractArgs(ruleParts[1]);
		if (ruleName.equals("\\assign")) {
			String[] args = splitArgs(argsString, 2);
			rule = parseVariableString(args[0]) + " = " + parseExpressionString(args[1]);
		}
		else if (ruleName.equals("\\change")) {
			rule = "change(" + parseStageString(argsString) + ")";
		}
		else if (ruleName.equals("\\ifthen")) {
			String[] args = splitArgs(argsString, 2);
			rule = "if (" + parseBExprString(args[0]) + ")" + " then" + parseRuleString(args[1]);
		}
		else if (ruleName.equals("\\pchange")) {
			String[] args = splitArgs(argsString, 2);
			rule = "pchange(" + parseStageString(args[0]) + "," + parseExpressionString(args[1]) + ")";
		}
		else if (ruleName.equals("\\ingest")) {
			String[] args = splitArgs(argsString, 3);
			rule = "ingest(" + parseVariableString(args[0]) + "," + parseExpressionString(args[1]) + "," +
								parseExpressionString(args[2]) + ")";
		}
		else if (ruleName.equals("\\divide")) {
			rule = "divide(" + parseExpressionString(argsString) + ")";
		}
		else if (ruleName.equals("\\uptake")) {
			String[] args = splitArgs(argsString, 2);
			rule = "uptake(" + parseExpressionString(args[0]) + "," + parseVariableString(args[1]) + ")";
		}
		else if (ruleName.equals("\\release")) {
			String[] args = splitArgs(argsString, 2);
			rule = "release(" + parseExpressionString(args[0]) + "," + parseVariableString(args[1]) + ")";
		}
		else if (ruleName.equals("\\create")) {
			String[] argList = splitAllArgs(argsString);
			rule = "create(" + parseStageString(argList[0]) + "," 
					+ parseExpressionString(argList[1]) + ")";
			if (argList.length > 2) {
				rule += " with [" + parseAssignList(Arrays.copyOfRange(argList, 2, argList.length)) + "]";
			}
		}
		return rule;
	}
	
	private String parseAssignList(String[] argList) {
		String assignListString = new String();
		for (String arg: argList) {
			String[] ruleParts = extractRuleType(arg);
			if (ruleParts[0].equals("\\set")) {
				String assignArgs = extractArgs(ruleParts[1]);
				String[] argArray = splitArgs(assignArgs, 2);
				assignListString += parseVariableString(argArray[0]) + " = " 
									+ parseExpressionString(argArray[1]) + ",";
			}
		}
		return assignListString.substring(0, assignListString.length()-1);
	}

	private String parseBExprString(String exprString) {
		String bexprCode = new String();
		String[] bexprParts = extractRuleType(exprString);
		String bexprName = bexprParts[0];
		String argString = extractArgs(bexprParts[1]);
		if (bexprName.equals("\\and")) {
			bexprCode = constructBooleanOperatorString(argString, "and");
		}
		else if (bexprName.equals("\\or")) {
			bexprCode = constructBooleanOperatorString(argString, "or");
		}
		else if (bexprName.equals("\\not")) {
			bexprCode = "not(" + parseBExprString(argString) + ")";
		}
		else if (bexprName.equals("\\equal")) {
			bexprCode = parseBComparitorString(argString, "==");
		}
		else if (bexprName.equals("\\greater")) {
			bexprCode = parseBComparitorString(argString, ">");
		}
		else if (bexprName.equals("\\greaterequal")) {
			bexprCode = parseBComparitorString(argString, ">=");
		}
		else if (bexprName.equals("\\less")) {
			bexprCode = parseBComparitorString(argString, "<");
		}
		else if (bexprName.equals("\\lessequal")) {
			bexprCode = parseBComparitorString(argString, "<=");
		}
		else if (bexprName.equals("\\neq")) {
			bexprCode = parseBComparitorString(argString, "<>");
		}
		else if (bexprName.equals("\\someVariety")) {
			bexprCode = "some(" + parseBExprString(argString) + ")";
		}
		else if (bexprName.equals("\\allVariety")) {
			bexprCode = "all(" + parseBExprString(argString) + ")";
		}
		else if (bexprName.equals("\\noneVariety")) {
			bexprCode = "none(" + parseBExprString(argString) + ")";
		}
		
		return bexprCode;
	}

	private String parseBComparitorString(String argString, String operator) {
		String[] equalArgs = splitArgs(argString, 2);
		return "(" + parseExpressionString(equalArgs[0]) + " " + operator + " " +
						  parseExpressionString(equalArgs[1]) + ")";
	}

	private String parseStageString(String argsString) {
		String stage = new String();
		String[] ruleParts = extractRuleType(argsString);
		if (ruleParts[0].equals("\\stage")) {
			stage = extractArgs(ruleParts[1]);
		}
		return stage;
	}

	private String parseExpressionString(String exprString) {
		String exprCode = new String();
		String[] ruleParts  = extractRuleType(exprString);
		String exprName = ruleParts[0];
		String argString = extractArgs(ruleParts[1]);
		if (exprName.equals("\\var")) {
			exprCode = argString;
		}
		else if (exprName.equals("\\val")) {
			String[] valArgs = splitArgs(argString, 2);
			String[] siValParts = extractRuleType(valArgs[0]);
			String siValContents = extractArgs(siValParts[1]);
			if (siValParts[0].equals("\\sival")) {
				String[] siValArgs = splitArgs(siValContents, 2);
				exprCode = siValArgs[0];
			}
		}
		else if (exprName.equals("\\conditional")) {
			String[] ifArgs = splitArgs(argString, 3);
			exprCode = "(if (" + parseBExprString(ifArgs[0]) + ") then " + parseExpressionString(ifArgs[1]) +
							" else " + parseExpressionString(ifArgs[2]) + ")";
		}
		else if (exprName.equals("\\add")) {
			exprCode = constructBinaryOperatorString(argString, "+");
		}
		else if (exprName.equals("\\mul")) {
			exprCode = constructBinaryOperatorString(argString, "*");
		}
		else if (exprName.equals("\\sub")) {
			exprCode = constructBinaryOperatorString(argString, "-");
		}
		else if (exprName.equals("\\div")) {
			exprCode = constructBinaryOperatorString(argString, "/");
		}
		else if (exprName.equals("\\pow")) {
			exprCode = constructBinaryOperatorString(argString, "^");
		}
		else if (exprName.equals("\\min")) {
			exprCode = constructBinaryFunctionString(argString, "min");
		}
		else if (exprName.equals("\\max")) {
			exprCode = constructBinaryFunctionString(argString, "max");
		}
		else if (exprName.equals("\\varhist")) {
			String[] varhistArgs = splitArgs(argString, 2);
			exprCode = "varhist(" + parseVariableString(varhistArgs[0]) + "," +
									parseExpressionString(varhistArgs[1]) + ")";
		}
		else if (exprName.equals("\\varietysum")) {
			exprCode = "sum(" + parseExpressionString(argString) + ")";
		}
		else if (exprName.equals("\\varietymul")) {
			exprCode = "product(" + parseExpressionString(argString) + ")";
		}
		else if (exprName.equals("\\varietyavg")) {
			exprCode = "average(" + parseExpressionString(argString) + ")";
		}
		else if (!exprName.contains("?")) {
			exprName = exprName.substring(1, exprName.length());
			exprCode = exprName + "(" + parseExpressionString(argString) + ")";
		}
		
		return exprCode;
	}

	private String constructBinaryFunctionString(String argString, String function) {
		String exprCode = "";
		String[] minArgs = splitAllArgs(argString);
		for (int n = minArgs.length-2; n >= 0; n++) {
			if (n == minArgs.length-2) {
				exprCode = function + "(" + parseExpressionString(minArgs[n]) + "," +
											parseExpressionString(minArgs[n+1]) + ")";
			}
			else {
				exprCode = function + "(" + parseExpressionString(minArgs[n]) + "," + exprCode + ")";
			}
		}
		return exprCode;
	}

	private String constructBinaryOperatorString(String argString, String operator) {
		String[] opArgs = splitAllArgs(argString);
		String exprCode = "(" + parseExpressionString(opArgs[0]);
		for(int n = 1; n < opArgs.length; n++) {
			exprCode += " " + operator + " " + parseExpressionString(opArgs[n]);
		}
		exprCode += ")";
		return exprCode;
	}
	
	private String constructBooleanOperatorString(String argString, String operator) {
		String[] opArgs = splitAllArgs(argString);
		String bexprCode = "(" + parseBExprString(opArgs[0]);
		for(int n = 1; n < opArgs.length; n++) {
			bexprCode += " " + operator + " " + parseBExprString(opArgs[n]);
		}
		bexprCode += ")";
		return bexprCode;
	}

	private String parseVariableString(String argsString) {
		String var = new String();
		String[] ruleParts = extractRuleType(argsString);
		if (ruleParts[0].equals("\\var"))
			var = extractArgs(ruleParts[1]);
		return var;
	}

	private String[] extractRuleType(String str) {
		return str.split("\\{", 2);
	}
	
	private String extractArgs(String str) {
		int bracketIndex = StringTools.getUnNested(str, '}');
		return str.substring(0, bracketIndex);
	}
	
	private String[] splitArgs(String args, int numberOfArgs) {
		String[] splitArgs = new String[numberOfArgs];
		String rest = args;
		for (int n = 0; n < numberOfArgs; n++) {
			int splitIndex = StringTools.getUnNested(rest, ',');
			splitArgs[n] = StringTools.LHS(rest, splitIndex);
			rest = StringTools.RHS(rest, splitIndex);
		}
		return splitArgs;
	}
	
	private String[] splitAllArgs(String args) {
		List<String> argList = new ArrayList<String>();
		String rest = args;
		while (!rest.isEmpty()) {
			int splitIndex = StringTools.getUnNested(rest, ',');
			argList.add(StringTools.LHS(rest, splitIndex));
			rest = StringTools.RHS(rest, splitIndex);
		}
		return argList.toArray(new String[0]);
	}
}
