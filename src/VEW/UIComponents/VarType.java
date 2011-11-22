package VEW.UIComponents;

public enum VarType {

	GROUPVAR{
	    public String toString() {
	        return "Group Variable";
	    }
	},
	GROUPPARAM{
	    public String toString() {
	        return "Group Parameter";
	    }
	},
	LOCALVAR{
	    public String toString() {
	        return "Local Variable";
	    }
	},
	FOODPARAM{
	    public String toString() {
	        return "Food-Based Parameter";
	    }
	},
	FOODSET{
	    public String toString() {
	        return "Food Set/Concentration";
	    }
	},
	FOODLOCAL{
	    public String toString() {
	        return "Food-Based Local";
	    }
	},
	FOODVAR{
	    public String toString() {
	        return "Food-Based Variable";
	    }
	};
	
}
