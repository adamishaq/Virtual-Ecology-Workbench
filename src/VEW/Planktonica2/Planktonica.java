package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.util.prefs.BackingStoreException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import VEW.Common.XML.XMLFile;
import VEW.Controller2.VEWController2;

import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;
import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.Model;
/**
 * VEW Planktonica display for editing functional groups and chemicals, based on MVC OO design principle
 * 
 * @author Michael Hinstridge & Chris Bates
 *
 */

public class Planktonica extends JPanel {

	private static final long serialVersionUID = 5356091519865535067L;

	/* Categories tab */
	protected final JTabbedPane catTab = new JTabbedPane();
	
	/* Display panes for the tabpane... its Func-y */
	protected Display funcView;
	protected Display chemView;
	
	public VEWController2 vc2;
	public XMLFile xmlFile;
	private JFrame parent;
	private Model currentModel;

	  
	/**
	 * Constructor for starting the Planktonica class
	 * @param jd - reference to the VEWController2 parent
	 * @param xmlFile - reference to the XMLFile of the model
	 */
	public Planktonica (VEWController2 jd, XMLFile xmlFile) {
		vc2 = jd;
	    parent = jd;
	    Model m = new Model(xmlFile);
	    currentModel = m;
	    this.xmlFile = xmlFile;
		try {
			m.buildFromFile();
		} catch (BackingStoreException e) {
			System.err.println(e);
			new JDialog(jd, "XMLFile: " + xmlFile.getName() + " failed to load.");
			jd.dispose();
		}
		
		FunctionalGroupController funcController = new FunctionalGroupController(m);
	    funcView = new FunctionalDisplay(funcController, catTab.getSize());
	    chemView = new ChemicalDisplay(new ChemicalController(m,funcController), catTab.getSize());
	    initialiseGUI();
	    parent.pack();
	}
	
	public Planktonica (XMLFile xmlFile) {
		this.xmlFile = xmlFile;
	    Model m = new Model(xmlFile);
		try {
			m.buildFromFile();
		} catch (BackingStoreException e) {
			System.err.println(e);
		}
		
		FunctionalGroupController funcController = new FunctionalGroupController(m);
	    funcView = new FunctionalDisplay(funcController, catTab.getSize());
	    chemView = new ChemicalDisplay(new ChemicalController(m,funcController), catTab.getSize());
	    initialiseGUI();
	}
	
	private void initialiseGUI() {
		setLayout(new BorderLayout(2, 2));
		
		catTab.addTab("Functional Groups", funcView);
		catTab.addTab("Chemicals", chemView);
		
		add(catTab);
	}

  /* The frames */


  public boolean greenLight(boolean fix) {
	Function.COMPILEFULLY = true;
	return true;
  }
  
  public Model getModel() {
	  return currentModel;
  }


}
