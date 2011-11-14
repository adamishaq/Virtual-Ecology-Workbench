package VEW.Planktonica2.model;



public class VarietyType extends Type {
	
	private Type elementType;
	
	public VarietyType(String _name, Type _elementType) {
		super(_name);
		elementType = _elementType;
		
	}
	
	public Type getElementType() {
		return elementType;
	}

}
