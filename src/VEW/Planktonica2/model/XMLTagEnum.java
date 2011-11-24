package VEW.Planktonica2.model;

public enum XMLTagEnum {
	FUNCTIONAL_GROUP("functionalgroup"), CHEMICAL("chemical"), NAME("name"), INVISIBLE("invisible"),
	STAGE("stage"), FUNCTION("function"), CALLED_IN("calledin"), EQUATION("equation"), EQ("eq"), 
	AUTHOR("author"), COMMENT("comment"), ARCHIVE_NAME("archivename"), PARAMETER("parameter"), 
	DESCRIPTION("desc"), VALUE("value"), HIST("hist"), VARIETY_LINK("link"), LOCAL("local"), 
	VARIABLE("variable"), VARIETY_CONCENTRATION("varietyconcentration"), VARIETY_VARIABLE("varietyvariable"),
	VARIETY_LOCAL("varietylocal"), VARIETY_PARAM("varietyparameter"), PIGMENT("pigment"), SPECTRUM("spectrum"), 
	GRAPH_VAL("eq"), UNIT("unit");
	
	
	private final String tag;
	
	private XMLTagEnum (String xmlTag) {
		this.tag = xmlTag;
	}
	
	public String xmlTag() {
		return this.tag;
	}
}
