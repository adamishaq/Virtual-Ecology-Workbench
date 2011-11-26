package VEW.Planktonica2.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import VEW.Common.XML.XMLTag;
import VEW.XMLCompiler.ANTLR.BACONCompiler;
import VEW.XMLCompiler.ANTLR.CompilerException;

public class Function implements BuildFromXML, BuildToXML {

	private String name;
	
	private Collection<Stage> calledIn;
	private Collection<Stage> availableStages;
	
	private String archiveName;
	private String comment;
	private String author;
	
	private Catagory parent;
	
	private String source_file_path;
	
	private XMLTag baseTag;

	
	public Function(Collection<Stage> stages, String file_path, Catagory parent) {
		this.source_file_path = get_path(file_path);
		this.availableStages = stages;
		this.parent = parent;
	}

	public Function(String file_path, Catagory parent) {
		this.source_file_path = get_path(file_path);
		this.availableStages = null;
		this.parent = parent;
	}

	
	@Override
	public BuildFromXML build(XMLTag tag) {
		baseTag = tag;
		XMLTag nameTag = tag.getTag(XMLTagEnum.NAME.xmlTag());
		if (nameTag != null) {
			this.name = nameTag.getValue();
			nameTag.removeFromParent();
		}
		
		XMLTag [] xmlTags = null;
		
		if (availableStages != null) {
			xmlTags = tag.getTags(XMLTagEnum.CALLED_IN.xmlTag());
			this.calledIn = new ArrayList<Stage> (xmlTags.length);

			for (XMLTag t : xmlTags) {
				Stage s = getStageFromList(t.getValue());

				if (s != null) {
					calledIn.add(s);
					t.removeFromParent();
				}
				//TODO: if stage does not exist thrown an exception
			}
		}	
		
		xmlTags = tag.getTags(XMLTagEnum.EQUATION.xmlTag());
		createSourceFile(xmlTags);
		
		XMLTag authorTag = tag.getTag(XMLTagEnum.AUTHOR.xmlTag());
		if (authorTag != null) {
			this.author = authorTag.getValue();
			authorTag.removeFromParent();
		}
		
		XMLTag commentTag = tag.getTag(XMLTagEnum.COMMENT.xmlTag());
		if (commentTag != null) {
			this.comment = commentTag.getValue();
			commentTag.removeFromParent();
		}
		
		XMLTag archiveTag = tag.getTag(XMLTagEnum.ARCHIVE_NAME.xmlTag());
		if (archiveTag != null) {
			this.archiveName = archiveTag.getValue();
			archiveTag.removeFromParent();
		}
		
		
		return this;
	}
	
	private void createSourceFile(XMLTag[] xmlTags) {
		String parentPath = source_file_path + parent.getName() + "\\";
		File parentDirectory = new File(parentPath);
		if (!parentDirectory.exists()) {
			parentDirectory.mkdir();
		}
		File sourceFile = new File(parentPath + name + ".bacon");
		if (sourceFile.exists()) {
			for (XMLTag equationTag: xmlTags) {
				equationTag.removeFromParent();
			}
			return;
		}
		String sourceCode = "";
		for (XMLTag equationTag: xmlTags) {
			XMLTag nameTag = equationTag.getTag("name");
			XMLTag eqTag = equationTag.getTag("eq");
			String name = nameTag.getValue();
			String eq = eqTag.getValue();
			EquationStringParser parser = new EquationStringParser(eq);
			sourceCode += "\"" + name + "\": " + parser.parseEquationString() + "\n\n";
			equationTag.removeFromParent();
		}
		try {
			FileWriter writer = new FileWriter(sourceFile);
			writer.write(sourceCode);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Stage getStageFromList (String name) {
		
		if (availableStages == null || name == null)
			return null;
		
		for (Stage s : availableStages) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		
		return null;
		
	}

	/**
	 * Determines whether the given stage has been calledIn the current function
	 * 
	 * @param called
	 * @return true if stage found
	 */
	public boolean isCalledIn (Stage called) {
		for (Stage s : calledIn) {
			if (s.equals(called)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determines whether the given stage has been calledIn the current function
	 * 
	 * @param name name of stage to be found
	 * @return Stage where Stage.getName == name
	 */
	public Stage isCalledIn (String name) {
		for (Stage s : calledIn) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		
		return null;
	}
	
	public void addToCalledIn(Stage selected) {
		if (!isCalledIn(selected)) {
			calledIn.add(selected);
		}
		
	}
	
	public void removeFromCalledIn(Stage selected) {
		
		calledIn.remove(selected);
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Stage> getCalledIn() {
		return calledIn;
	}

	public void setCalledIn(Collection<Stage> calledIn) {
		this.calledIn = calledIn;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setSource_code(String source_code) {
		this.source_file_path = source_code;
	}

	public String getSource_code() {
		return source_file_path;
	}

	private String get_path(String filePath) {
		filePath = filePath.substring(0, filePath.lastIndexOf('\\'));
		filePath += "\\";
		return filePath;
	}

	@Override
	public XMLTag buildToXML() throws XMLWriteBackException {
		if (baseTag == null) {
			baseTag = new XMLTag("function");
		}
		baseTag.addTag("name", name);
		Iterator<Stage> stageIter = calledIn.iterator();
		while (stageIter.hasNext()) {
			Stage stage = stageIter.next();
			baseTag.addTag(new XMLTag("calledin", stage.getName()));
		}
		List<XMLTag> equationTags;
		try {
			equationTags = compileFunction();
			for (XMLTag eqTag : equationTags) {
				baseTag.addTag(eqTag);
			}
			if (archiveName != null)
				baseTag.addTag(new XMLTag("archivename", archiveName));
			if (comment != null)
				baseTag.addTag(new XMLTag("comment", comment));
			if (author != null)
				baseTag.addTag(new XMLTag("author", author));
		} catch (CompilerException e) {
			throw new XMLWriteBackException(e);
		}
		return baseTag;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	public List<XMLTag> compileFunction() throws CompilerException {
		String parentPath = source_file_path + parent.getName() + "\\" + name + ".bacon";
		String sourceCode = "";
		try {
			sourceCode = readSourceFile(parentPath);
		} catch (FileNotFoundException e) {
			// TODO File does not exist, bah
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Some IO issue
			e.printStackTrace();
			return null;
		}
		return compileSource(sourceCode);
	}
	
	private List<XMLTag> compileSource(String sourceCode) throws CompilerException {
		BACONCompiler compiler = new BACONCompiler(this, sourceCode);
		return compiler.compile();
	}

	private String readSourceFile(String parentPath) throws FileNotFoundException, IOException {
		FileInputStream fStream = new FileInputStream(parentPath);
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fStream));
		String sourceCode = "";
		String line = new String();
		while ((line = buffReader.readLine()) != null) 
			sourceCode += line + '\n';
		return sourceCode;
	}
	
	public void compileCodeForFunction(String code) throws CompilerException {
		compileSource(code);
	}

	public Catagory getParent() {
		return parent;
	}

	
	
	
}
