package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Vector;
import java.util.prefs.BackingStoreException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.VEWController2;

import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;
import VEW.Planktonica2.model.Model;
import VEW.Scenario2.AddVarPage;
import VEW.Scenario2.EquationEditor;
import VEW.Scenario2.EquationPanel;
import VEW.Scenario2.VariableChooser;
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

	  
	/**
	 * Constructor for starting the Planktonica class
	 * @param jd - reference to the VEWController2 parent
	 * @param xmlFile - reference to the XMLFile of the model
	 */
	public Planktonica (VEWController2 jd, XMLFile xmlFile) {
		vc2 = jd;
	    parent = jd;
	    Model m = new Model(xmlFile);
		try {
			m.buildFromFile();
		} catch (BackingStoreException e) {
			System.err.println(e);
			new JDialog(jd, "XMLFile: " + xmlFile.getName() + " failed to load.");
			jd.dispose();
		}
		
	    funcView = new FunctionalDisplay(new FunctionalGroupController(m), catTab.getSize());
	    chemView = new ChemicalDisplay(new ChemicalController(m), catTab.getSize());
	    initialiseGUI();
	    parent.pack();
	}
	
	public Planktonica (XMLFile xmlFile) {
	    Model m = new Model(xmlFile);
		try {
			m.buildFromFile();
		} catch (BackingStoreException e) {
			System.err.println(e);
		}
		
	    funcView = new FunctionalDisplay(new FunctionalGroupController(m), catTab.getSize());
	    chemView = new ChemicalDisplay(new ChemicalController(m), catTab.getSize());
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
	  
	return true;
  }


}
