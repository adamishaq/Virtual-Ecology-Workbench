package VEW.Planktonica2.Model;

public class VarietyVariable extends Variety {

	public VarietyVariable(FunctionalGroup funcGroup) {
		super(funcGroup);
	}
	

	@Override
	protected String getVariableClassName() {
		return "varietyvariable";
	}

	
}
