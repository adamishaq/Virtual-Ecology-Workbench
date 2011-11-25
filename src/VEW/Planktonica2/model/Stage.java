package VEW.Planktonica2.model;

import VEW.Common.XML.XMLTag;

public class Stage implements BuildFromXML, BuildToXML {
	
	private Boolean log;
	private Boolean closure;
	private String name;
	private String comment;
	
	private XMLTag baseTag;
	
	public Stage() {
		name = null;
		comment = null;
		log = null;
		closure = null;
	}
	
	@Override
	public BuildFromXML build(XMLTag tag) {
		baseTag = tag;
		String logValue = tag.getAttribute("log");
		if (logValue != null) {
			this.log = Boolean.valueOf(logValue);
		}
		
		String closureValue = tag.getAttribute("closure");
		if (closureValue != null) {
			this.closure = Boolean.valueOf(closureValue);
		}
		
		XMLTag nameTag = tag.getTag("name"); 
		if (nameTag != null) {
			this.name = nameTag.getValue();
			nameTag.removeFromParent();
		}
		
		XMLTag commentTag = tag.getTag("comment");
		if (commentTag != null) {
			this.comment = commentTag.getValue();
			commentTag.removeFromParent();
		}
		
		return this;
	}

	@Override
	public boolean equals (Object o) {
		return (o instanceof Stage && ((Stage) o).getName().equals(this.getName()));
		
	}
	
	
	public boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}

	public boolean isClosure() {
		return closure;
	}

	public void setClosure(boolean closure) {
		this.closure = closure;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public XMLTag buildToXML() {
		if (baseTag == null) {
			baseTag = new XMLTag("stage");
		}
		if (log != null)
			baseTag.setAttribute("log", Boolean.toString(log));
		if (closure != null)
			baseTag.setAttribute("closure", Boolean.toString(closure));
		if (name != null)
			baseTag.addTag("name", name);
		if (comment != null)
			baseTag.addTag("comment", comment);
		return baseTag;
	}

	
	
	
}
