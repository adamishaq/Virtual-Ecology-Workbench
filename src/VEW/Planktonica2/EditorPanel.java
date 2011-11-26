package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.antlr.runtime.RecognitionException;

import VEW.Planktonica2.ControllerStructure.SourcePath;
import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.UIComponents.AutocompleteBox;
import VEW.Planktonica2.UIComponents.BACONFilter;
import VEW.Planktonica2.UIComponents.LatexPreview;
import VEW.Planktonica2.UIComponents.SyntaxHighlighter;
import VEW.XMLCompiler.ANTLR.ANTLRParser;
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
	
	// Open/save components
	final static JFileChooser file_chooser = new JFileChooser();
	
	public EditorPanel (VEWController controller) {
		super();
		this.controller = controller;
		this.controller.addObserver(this);
		initialise();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof SourcePath) {
			
			SourcePath p = (SourcePath) arg;
			
			open_source_file(p.getPath());
			
		}
		
	}
	
	public void initialise() {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.75;
		c.gridx = 0;
		c.gridy = 0;
		syntax = new JTextPane();
		this.syntax.setEnabled(false);
		final JPanel no_wrap_syntax = new JPanel(new BorderLayout());
		no_wrap_syntax.add(syntax);
		JScrollPane scroll_pane_syntax = new JScrollPane(no_wrap_syntax);
		scroll_pane_syntax.setPreferredSize(new Dimension(300,350));
		syntax_highlighter = new SyntaxHighlighter();
		this.add(scroll_pane_syntax, c);
		
		c.gridx = 1;
		c.gridy = 0;
		preview = new LatexPreview();
		final JPanel no_wrap_preview = new JPanel(new BorderLayout());
		no_wrap_preview.add(preview,BorderLayout.NORTH);
		JScrollPane scroll_pane_preview = new JScrollPane(no_wrap_preview);
		scroll_pane_preview.setPreferredSize(new Dimension(300,350));
		this.add(scroll_pane_preview, c);
		
		c.weighty = 0.25;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		error_log = new JEditorPane();
		JScrollPane scroll_pane_errors = new JScrollPane(error_log);
		scroll_pane_errors.setPreferredSize(new Dimension(606,125));
		this.add(scroll_pane_errors, c);
		
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
		
		//this.add(scroll_pane_syntax);
		//this.add(scroll_pane_preview);
		//this.add(scroll_pane_errors);
		
		javax.swing.filechooser.FileFilter f = new BACONFilter();
		file_chooser.setFileFilter(f);

		this.setSize((850), (600));
		this.setVisible(true);
		
		preview.update_preview("pre");
		preview.setVisible(false);

		
	}
	
	private void highlight_syntax() {
		int pos = syntax.getCaret().getDot();
		syntax.setText(syntax_highlighter.highlight(syntax.getText()));
		syntax.getCaret().setDot(pos);
	}
	
	public void compile() {
		syntax_highlighter.clear_flags();
		ANTLRParser p = new ANTLRParser (syntax_highlighter.getPlainText(syntax.getText()));
		try {
			ConstructedASTree ct = p.getAST();
			//ct.getTree().check();
			if (ct.getExceptions().isEmpty()) {
				String latex = "\\begin{array}{lr}";
				latex += ct.getTree().generateLatex();
				latex += "\\end{array}";
				preview.setVisible(true);
				preview.update_preview(latex);
				System.out.println(ct.getTree().generateXML());
				error_log.setText("<html><PRE>Compilation succeeded!</PRE></html>");
			} else {
				String errors = "<html><PRE>Compilation errors occurred:\n";
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
				errors += "</font>";
				errors += "\nCompilation aborted!</PRE></html>";
				error_log.setText(errors);
			}
			highlight_syntax();
		} catch (RecognitionException e) {
			System.out.println("RECOGNITION EXCEPTION");
			e.printStackTrace();
		}
	}
	
	public void check() {
		syntax_highlighter.clear_flags();
		ANTLRParser p = new ANTLRParser (syntax_highlighter.getPlainText(syntax.getText()));
		try {
			ConstructedASTree ct = p.getAST();
			ct.getTree().check(controller.getCurrentlySelectedFunction().getParent(), ct);
			if (ct.getExceptions().isEmpty()) {
				String latex = "\\begin{array}{lr}";
				latex += ct.getTree().generateLatex();
				latex += "\\end{array}";
				preview.setVisible(true);
				preview.update_preview(latex);
				error_log.setText("<html><PRE>Check succeeded!</PRE></html>");
			} else {
				String errors = "<html><PRE>Errors in source file:\n";
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
				errors += "</font>";
				errors += "</PRE></html>";
				error_log.setText(errors);
			}
			highlight_syntax();
		} catch (RecognitionException e) {
			System.out.println("RECOGNITION EXCEPTION");
			e.printStackTrace();
		}
	}
	
	public void preview() {
		ANTLRParser p = new ANTLRParser (syntax_highlighter.getPlainText(syntax.getText()));
		//System.out.println(syntax_highlighter.getPlainText(syntax.getText()));
		try {
			ConstructedASTree ct = p.getAST();
			if (ct.getTree() != null) {
				String latex = "\\begin{array}{lr}";
				latex += ct.getTree().generateLatex();
				latex += "\\end{array}";
				preview.setVisible(true);
				preview.update_preview(latex);
			}/*
			if (ct.getExceptions().isEmpty()) {
				error_log.setText("");
			} else {
				String errors = "<html><PRE>Compilation errors occurred:\n";
				errors += "<font color=#FF0000>";
				for (TreeWalkerException t : ct.getExceptions()) {
					syntax_highlighter.flag_line(t);
					errors += t.getError() + "\n";
				}
				errors += "</font>";
				errors += "</PRE></html>";
				error_log.setText(errors);
			}*/
			//highlight_syntax();
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
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
		if (pos <= 0)
			return true;
		char c = syntax_highlighter.getPlainText(syntax.getText()).charAt(pos);
		return !Character.isLetterOrDigit(c);
	}
	
	public void open_source_file(String filePath) {
        try {
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
			syntax.setText("<html><head></head><PRE>Could not find source file " + filePath
					+ "</PRE></html>");
		}
	}
	
	public void clear() {
		this.syntax.setEnabled(false);
		this.syntax.setText("<html><PRE></PRE></html>");
		this.preview.setVisible(false);
		this.error_log.setText("<html><PRE></PRE></html>");
	}
	
	
/*	
static class CompileListener implements ActionListener {
		
	private EditorPanel parent;
	
	public CompileListener(EditorPanel edit) {
		parent = edit;
	}
	
	public void actionPerformed(ActionEvent event) {
		syntax_highlighter.clear_flags();
		ANTLRParser p = new ANTLRParser (syntax_highlighter.getPlainText(syntax.getText()));
		try {
			ConstructedASTree ct = p.getAST();
			//ct.getTree().check();
			if (ct.getExceptions().isEmpty()) {
				String latex = "\\begin{array}{lr}";
				latex += ct.getTree().generateLatex();
				latex += "\\end{array}";
				preview.setVisible(true);
				preview.update_preview(latex);
				System.out.println(ct.getTree().generateXML());
				error_log.setText("<html><PRE>Compilation succeeded!</PRE></html>");
			} else {
				String errors = "<html><PRE>Compilation errors occurred:\n";
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
				errors += "</font>";
				errors += "\nCompilation aborted!</PRE></html>";
				error_log.setText(errors);
			}
			parent.highlight_syntax();
		} catch (RecognitionException e) {
			System.out.println("RECOGNITION EXCEPTION");
			e.printStackTrace();
		}
	}
		
}*/
/*
static class OpenListener implements ActionListener {
	
	private JPanel parent;
	
	public OpenListener(JPanel editorPanel) {
		parent = editorPanel;
	}

	public void actionPerformed(ActionEvent event) {
		int choice = file_chooser.showOpenDialog(parent);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = file_chooser.getSelectedFile();
            String fpath = file.getAbsolutePath();
            try {
    			FileInputStream fstream = new FileInputStream(fpath);
    			DataInputStream in = new DataInputStream(fstream);
    			BufferedReader br = new BufferedReader(new InputStreamReader(in));
    			String file_text = "<html><head></head><PRE>", line = "";
    			while ((line = br.readLine()) != null)   {
    				file_text += (line + "\n"); 
    			}
    			in.close();
    			file_text += "</PRE></html>";
    			syntax.setText(file_text);
    			highlight_syntax();
    		} catch (Exception e) {
    			syntax.setText("Could not find the file!");
    		}
        }
		
		
	}
	
}*/
/*
static class SaveListener implements ActionListener {
	
	private JPanel parent;
	
	public SaveListener(JPanel editorPanel) {
		parent = editorPanel;
	}

	public void actionPerformed(ActionEvent event) {
		int choice = file_chooser.showSaveDialog(parent);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = file_chooser.getSelectedFile();
            String fpath = file.getAbsolutePath() + ".bacon";
            // TODO - check for overwrites, extensions etc.
            try {
    			FileOutputStream fstream = new FileOutputStream(fpath);
    			PrintStream out = new PrintStream(fstream);
    			out.print(syntax_highlighter.getPlainText(syntax.getText()));
    			out.close();
    		} catch (Exception e) {
    			System.out.println("Save failed!");
    		}
        }
		
		
	}
	
}*/

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
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_CONTROL ||
			e.getKeyCode() == KeyEvent.VK_ALT)
			// Ignore them
			return;
		if (!parent.caret_in_comment() && (!parent.current_autocomplete().equals("")
				|| parent.space_before_caret())) {
			parent.show_autocomplete(e);
		} else {
			parent.hide_autocomplete();
		}
		//System.out.println(space_before_caret());
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// Parse text and check for errors?
			parent.preview();
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
