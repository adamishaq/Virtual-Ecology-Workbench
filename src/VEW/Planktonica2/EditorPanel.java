package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

import org.antlr.runtime.RecognitionException;

import VEW.Common.Pair;
import VEW.Planktonica2.ControllerStructure.SourcePath;
import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.UIComponents.AutocompleteBox;
import VEW.Planktonica2.UIComponents.BACONFilter;
import VEW.Planktonica2.UIComponents.LatexPreview;
import VEW.Planktonica2.UIComponents.SyntaxHighlighter;
import VEW.Planktonica2.UIComponents.TEXFilter;
import VEW.XMLCompiler.ANTLR.ANTLRParser;
import VEW.XMLCompiler.ASTNodes.BACONCompilerException;
import VEW.XMLCompiler.ASTNodes.ConstructedASTree;
import VEW.XMLCompiler.ASTNodes.SemanticCheckException;
import VEW.XMLCompiler.ASTNodes.TreeWalkerException;

public class EditorPanel extends JPanel implements Observer {

	private VEWController controller;
	
	private static final long serialVersionUID = 960655324263522980L;
	private JTextPane syntax;
	private LatexPreview preview;
	private SyntaxHighlighter syntax_highlighter;
	private AutocompleteBox auto_complete;
	private JEditorPane error_log;
	private JSplitPane editorSplitPane;
	private JSplitPane overSplitPane;
	private String current_source;
	private int oldLocation = 175;
	
	// Open/save components
	private JFileChooser file_chooser;
	private JFileChooser tex_chooser;
	
	public EditorPanel (VEWController controller) {
		super();
		this.controller = controller;
		this.controller.addObserver(this);
		initialise();
		apply_options();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof SourcePath) {
			SourcePath p = (SourcePath) arg;
			open_source_file(p.getPath(),false);
		}
		
	}
	
	public void initialise() {
		
		this.setLayout(new GridLayout());
		
		syntax = new JTextPane();
		this.syntax.setEnabled(false);
		final JPanel no_wrap_syntax = new JPanel(new BorderLayout());
		no_wrap_syntax.add(syntax);
		JScrollPane scroll_pane_syntax = new JScrollPane(no_wrap_syntax);
		scroll_pane_syntax.setPreferredSize(new Dimension(300,350));
		syntax_highlighter = new SyntaxHighlighter();
		
		preview = new LatexPreview();
		final JPanel no_wrap_preview = new JPanel(new BorderLayout());
		no_wrap_preview.add(preview,BorderLayout.NORTH);
		JScrollPane scroll_pane_preview = new JScrollPane(no_wrap_preview);
		scroll_pane_preview.setPreferredSize(new Dimension(300,350));
		
		error_log = new JEditorPane();
		JScrollPane scroll_pane_errors = new JScrollPane(error_log);
		scroll_pane_errors.setPreferredSize(new Dimension(606,125));
		
		overSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		editorSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		editorSplitPane.setMinimumSize(new Dimension(this.getWidth(), 200));
		
		//overSplitPane.setDividerLocation(0.75);
		overSplitPane.setDividerLocation(DisplayOptions.getOptions().ERROR_PANEL_SIZE);
		overSplitPane.setDividerSize(4);
		overSplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY,
				new EditorPaneChangeListener(this));
		
		scroll_pane_syntax.setMinimumSize(new Dimension(200, overSplitPane.getHeight()));
		scroll_pane_preview.setMinimumSize(new Dimension(200, overSplitPane.getHeight()));
		
		//editorSplitPane.setDividerLocation(0.5);
		editorSplitPane.setDividerLocation(DisplayOptions.getOptions().EDITOR_PANEL_SIZE);
		editorSplitPane.setDividerSize(4);
		editorSplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY,
				new EditorPaneChangeListener(this));
		
		syntax.addKeyListener(new TypingListener(this));
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
		syntax.setFont(font);
		syntax.setContentType("text/html");
		syntax.setText("<html><PRE></PRE></html>");
		
		auto_complete = new AutocompleteBox(syntax,controller);
		
		error_log.setEditable(false);
		error_log.setFont(font);
		error_log.setContentType("text/html");
		error_log.setText("<html><PRE></PRE></html>");
		
		// Set up the file choosers, pointing at the current directory
    	String dir = controller.get_xml_source();
    	this.file_chooser = new JFileChooser(dir);
    	javax.swing.filechooser.FileFilter f = new BACONFilter();
    	file_chooser.setFileFilter(f);
    	
    	this.tex_chooser = new JFileChooser(dir);
    	javax.swing.filechooser.FileFilter t = new TEXFilter();
    	tex_chooser.setFileFilter(t);

		this.setSize((850), (600));
		this.setVisible(true);
		
		preview.update_preview("pre");
		preview.setVisible(false);

		editorSplitPane.setLeftComponent(scroll_pane_syntax);
		editorSplitPane.setRightComponent(scroll_pane_preview);
		overSplitPane.setTopComponent(editorSplitPane);
		overSplitPane.setBottomComponent(scroll_pane_errors);
		
		
		this.add(overSplitPane);

	}
	
	private void highlight_syntax() {
		int pos = syntax.getCaret().getDot();
		//System.out.println((syntax.getText()).replaceAll("\n", "\\\\\n"));
		syntax.setText(syntax_highlighter.highlight(syntax.getText()));
		//System.out.println((syntax.getText()).replaceAll("\n", "\\\\\n"));
		syntax.getCaret().setDot(pos);
	}
	
	public void compile() {
		if (this.current_source == null)
			return;
		int choice = JOptionPane.showOptionDialog(this, "You must save the current source file before compiling",
				"Save source file", JOptionPane.YES_NO_OPTION, 1, null, null, 1);
		if (choice == 1)
			return;
		// Save the source file
		if (!this.save())
			return;
		Pair<ArrayList<String>, ArrayList<String>> results = controller.writeBackToXMLFile();
		List<String> exceptions = results.getFirst();
		List<String> warnings = results.getSecond();
		if (!exceptions.isEmpty()) {
			String errors = "<html><PRE>Compilation errors occurred:\n";
			errors += "<font color=#FF0000>";
			for (String s : exceptions) {
				errors += s + "\n";
			}
			errors += "</font>";
			errors += "\nCompilation aborted!</PRE></html>";
			error_log.setText(errors);
		}
		else {
			if (!warnings.isEmpty()){
				String warningStr = "<html><PRE>Compilation warnings occurred:\n";
				warningStr += "<font color=#FF9900>";
				for (String s : warnings) {
					warningStr += s + "\n";
				}
				warningStr += "</font>\nCompilation succeeded!</PRE></html>";
				error_log.setText(warningStr);
				return;
			}
			error_log.setText("<html><PRE>Compilation succeeded!</PRE></html>");
		}
	}

	private String format_errors(ConstructedASTree ct, String errors) {
		errors += "<font color=#FF0000>";
		for (Exception t : ct.getExceptions()) {
			syntax_highlighter.flag_line(t);
			if (t instanceof TreeWalkerException) {
				TreeWalkerException twe = (TreeWalkerException) t;
				errors += twe.getError() + "\n";
			} else if (t instanceof SemanticCheckException) {
				SemanticCheckException sce = (SemanticCheckException) t;
				errors += sce.getError() + "\n";
			} else {
				errors += "Unknown error\n";
			}
		}
		errors += "</font>\n";
		errors = format_warnings(ct, errors);
		return errors;
	}

	private String format_warnings(ConstructedASTree ct, String errors) {
		errors += "<font color=#FF9900>";
		for (String s : ct.getWarnings()) {
			errors += "Warning: " + s + "\n";
		}
		errors += "</font>";
		return errors;
	}
	
	public void check() {
		//System.out.println(syntax.getText());
		if (this.current_source == null)
			return;
		syntax_highlighter.clear_flags();
		ANTLRParser p = new ANTLRParser (syntax_highlighter.getPlainText(syntax.getText()));
		try {
			ConstructedASTree ct = p.getAST();
			if (ct.getExceptions().isEmpty())
				ct.getTree().check(controller.getCurrentlySelectedFunction().getParent(), ct);
			if (ct.getExceptions().isEmpty()) {
				if (ct.hasWarnings()) {
					String errors = "<html><PRE>Warnings in source file:\n";
					errors = format_warnings(ct, errors);
					errors += "\nCheck succeeded!</PRE></html>";
					error_log.setText(errors);
				} else {
					error_log.setText("<html><PRE>Check succeeded!</PRE></html>");
				}
			} else {
				String errors = "<html><PRE>Errors in source file:\n";
				errors = format_errors(ct, errors);
				errors += "</PRE></html>";
				error_log.setText(errors);
			}
			String latex = "\\begin{array}{lr}";
			if (ct.getTree() != null)
				latex += ct.getTree().generateLatex();
			latex += "\\end{array}";
			preview.setVisible(true);
			preview.update_preview(latex);
			highlight_syntax();
		} catch (RecognitionException e) {
			System.out.println("RECOGNITION EXCEPTION");
			e.printStackTrace();
		}
	}
	
	public void preview() {
		if (this.current_source == null)
			return;
		ANTLRParser p = new ANTLRParser (syntax_highlighter.getPlainText(syntax.getText()));
		try {
			ConstructedASTree ct = p.getAST();
			if (ct == null || ct.getTree() == null)
				return;
			ct.getTree().check(controller.getCurrentlySelectedFunction().getParent(), ct);
			if (ct.getTree() != null) {
				String latex = "\\begin{array}{lr}";
				latex += ct.getTree().generateLatex();
				latex += "\\end{array}";
				preview.setVisible(true);
				preview.update_preview(latex);
			}
		} catch (RecognitionException e) {
			System.out.println("RECOGNITION EXCEPTION");
			e.printStackTrace();
		}
	}
	
	public void show_autocomplete(KeyEvent e) {
		auto_complete.show_suggestions(e);
	}
	
	public void hide_autocomplete() {
		auto_complete.hide_suggestions();
	}
	
	public String current_autocomplete() {
		return auto_complete.getCurrent_word();
	}
	
	public boolean caret_in_comment() {
		int pos = syntax.getCaretPosition() - 2;
		char[] chars = syntax_highlighter.getPlainText(syntax.getText()).toCharArray();
		if (pos >= chars.length)
			return false;
		for (int i = pos; i > 0; i--) {
			if (chars[i] == '\n')
				return false;
			if (chars[i] == '/' && chars[i-1] == '/')
				return true;
		}
		return false;
	}
	
	public int get_caret_line() {
		int newlines = 1;
		int pos = syntax.getCaretPosition() - 2;
		char[] chars = syntax_highlighter.getPlainText(syntax.getText()).toCharArray();
		for (int i = pos; i > 0; i--) {
			if (chars[i] == '\n')
				newlines++;
		}
		return newlines;
	}
	
	private boolean space_before_caret() {
		int pos = syntax.getCaretPosition() - 3;
		if (pos <= 0 || pos >= syntax_highlighter.getPlainText(syntax.getText()).length())
			return true;
		char c = syntax_highlighter.getPlainText(syntax.getText()).charAt(pos);
		return !Character.isLetterOrDigit(c);
	}
	
	public String word_before_caret() {
		String word = "";
		int pos = syntax.getCaretPosition() - 2;
		char[] chars = syntax_highlighter.getPlainText(syntax.getText()).toCharArray();
		if (pos >= chars.length)
			return word;
		for (int i = pos; i > 0; i--) {
			if (!(Character.isLetterOrDigit(chars[i]) || chars[i] == '_'))
				return word;
			else
				word += chars[i];
		}
		return word;
	}
	
	public void show_box(String word) {
		this.auto_complete.force_show(word,syntax.getCaretPosition());
	}
	
	public void open_source_file(String filePath,boolean importing) {
        try {
        	if (!importing)
        		this.current_source = new String(filePath);
			FileInputStream fstream = new FileInputStream(filePath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String source = "<html><head></head><PRE>", line = "";
			String file_text = "";
			while ((line = br.readLine()) != null)   {
				file_text += (line + "\n"); 
			}
			in.close();
			file_text = file_text.replaceAll("<","&lt;");
			file_text = file_text.replaceAll(">","&gt;");
			file_text = file_text.replaceAll("\"","&quot;");
			source += file_text;
			source += "</PRE></html>";
			syntax.setEnabled(true);
			preview.setVisible(false);
			syntax.setText(source);
			highlight_syntax();
			syntax.setCaretPosition(0);
		} catch (Exception e) {
			syntax.setText("<html><head></head><PRE></PRE></html>");
			syntax.setEnabled(true);
		}
	}
	
	public boolean save() {
		try {
			FileOutputStream fstream = new FileOutputStream(this.current_source);
			PrintStream out = new PrintStream(fstream);
			out.print(syntax_highlighter.getPlainText(syntax.getText()));
			out.close();
			error_log.setText("<html><PRE>Save successful</PRE></html>");
			return true;
		} catch (Exception e) {
			error_log.setText("<html><PRE>Error when saving to file " 
					+ this.current_source + "</PRE></html>");
			return false;
		}
	}
	
	public void import_source() {
		if (controller.getSelectedCatagory() == null)
			return;
		// Show a file open dialog
		int choice = file_chooser.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = file_chooser.getSelectedFile();
            String fpath = file.getAbsolutePath();
			String name = JOptionPane.showInputDialog(this,
		        	"Choose a name for the imported function",
		            "Import Function", 1);
		    if (name != null) {
		    	if (controller.addFunction(this,name)) {
		    		open_source_file(fpath,true);
		    		this.save();
		    	}
		    }
        }
	}
	
	public void export_latex() {
		int choice = tex_chooser.showSaveDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = tex_chooser.getSelectedFile();
            String fpath = file.getAbsolutePath();
            if (!fpath.endsWith(".tex"))
            	fpath += ".tex";
            // Try opening the file
            try {
            	FileOutputStream fstream = new FileOutputStream(fpath);
            	PrintStream out = new PrintStream(fstream);
            	// Get the LaTeX string
            	String latex = "\\documentclass[a4wide, 11pt]{article}\n";
            	latex += "\\setlength{\\parskip}{0.3cm}\n";
            	latex += "\\setlength{\\parindent}{0cm}\n";
            	latex += "\\begin{document}\n";
            	latex += "\\title{" + controller.get_model_name() + "}\n";
            	latex += "\\maketitle\n";
            	latex += controller.generate_model_latex();
            	latex += "\\end{document}";
            	out.print(latex);
            	out.close();
            } catch (Exception e) {
            	error_log.setText("<html><PRE>Export failed: could not open file " +
            			fpath + "</PRE></html>");
            }
        }
	}
	
	public void show_options() {
		DisplayOptionsDialog d = new DisplayOptionsDialog(this);
		d.setAlwaysOnTop(true);
		d.setLocationRelativeTo(null);
		d.setResizable(false);
		d.setVisible(true);
	}
	
	public void apply_options() {
		if (DisplayOptions.getOptions().LAYOUT_VERTICAL
				&& editorSplitPane.getOrientation() == JSplitPane.VERTICAL_SPLIT) {
			switchEditorPanel();
		} else if (!DisplayOptions.getOptions().LAYOUT_VERTICAL
				&& editorSplitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
			switchEditorPanel();
		}
	}
	
	public void clear() {
		this.save();
		this.current_source = null;
		this.syntax.setEnabled(false);
		this.syntax.setText("<html><PRE></PRE></html>");
		this.preview.setVisible(false);
		this.error_log.setText("<html><PRE></PRE></html>");
		this.syntax_highlighter.clear_flags();
		this.auto_complete.new_word();
	}
	
	public String get_error_at_caret() {
		String expected = "";
		ANTLRParser p = 
			new ANTLRParser (syntax_highlighter.getPlainText(syntax.getText()).substring(0,
					syntax.getCaretPosition()));
		try {
			ConstructedASTree ct = p.getAST();
			if (ct.getExceptions() != null && !ct.getExceptions().isEmpty()) {
				BACONCompilerException e = ct.getExceptions().get(ct.getExceptions().size() - 1);
				expected = e.getError();
			}
		} catch (RecognitionException e) {
			System.out.println("RECOGNITION EXCEPTION");
			e.printStackTrace();
		}
		return expected;
	}
	
	public String get_selected_text() {
		return syntax.getSelectedText();
	}
	
	public void paste(String s) {
		syntax.replaceSelection(s);
		highlight_syntax();
	}
	
	public void switchEditorPanel() {
		int currLocation = editorSplitPane.getDividerLocation();
		if (editorSplitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
			
			editorSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		else {
			editorSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		}
		editorSplitPane.setDividerLocation(oldLocation);
		oldLocation = currLocation;
	
	}
	
	public void update_panel_size() {
		DisplayOptions.getOptions().EDITOR_PANEL_SIZE = editorSplitPane.getDividerLocation();
		DisplayOptions.getOptions().ERROR_PANEL_SIZE = overSplitPane.getDividerLocation();
		DisplayOptions.getOptions().write_config();
	}

//<<<<<<< HEAD
/*	public void actionPerformed(ActionEvent event) {
		int choice = file_chooser.showSaveDialog(parent);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = file_chooser.getSelectedFile();
            String fpath = file.getAbsolutePath() + ".bacon";
            // check for overwrites, extensions etc.
            try {
    			FileOutputStream fstream = new FileOutputStream(fpath);
    			PrintStream out = new PrintStream(fstream);
    			out.print(syntax_highlighter.getPlainText(syntax.getText()));
    			out.close();
    		} catch (Exception e) {
    			System.out.println("Save failed!");
    		}
        }
		
*/		
//=======
class EditorPaneChangeListener implements PropertyChangeListener {
	
	EditorPanel parent;
	
	public EditorPaneChangeListener(EditorPanel parent) {
		this.parent = parent;
//>>>>>>> master
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		parent.update_panel_size();
	}
	
}
	
class PreviewListener implements ActionListener {
	
	EditorPanel parent;
	
	public PreviewListener(EditorPanel edit) {
		parent = edit;
	}
	
	public void actionPerformed(ActionEvent event) {
		parent.preview();
	}
	
}

class TypingListener implements KeyListener {

	private EditorPanel parent;
	
	public TypingListener(EditorPanel edit) {
		parent = edit;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_V && e.isControlDown()) {
			e.consume();
			Clipboard clipboard = 
		        Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable data = clipboard.getContents(clipboard);
			if (data != null) {
				try {
					if (data.isDataFlavorSupported(DataFlavor.stringFlavor)) {
						String s = (String)(data.getTransferData(
								DataFlavor.stringFlavor));
						parent.paste(s);//.replaceSelection(s);
					}
				} catch (Exception ex) {}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT || e.isControlDown())
			// Ignore them
			return;
		if (e.getKeyCode() == KeyEvent.VK_F1 && !parent.caret_in_comment()) {
			String new_word = new StringBuffer(parent.word_before_caret()).reverse().toString();
			parent.show_box(new_word);
		} else if (!parent.caret_in_comment() && (!parent.current_autocomplete().equals("")
				|| parent.space_before_caret())) {
			parent.show_autocomplete(e);
		} else {
			parent.hide_autocomplete();
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// Parse text and check for errors
			parent.check();
		} else if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_UP
				&& e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_RIGHT
				&& e.getKeyCode() != KeyEvent.VK_LEFT) {
			// Update the syntax pane with highlighting, ensuring caret
			// position remains the same
			parent.highlight_syntax();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
	
}
