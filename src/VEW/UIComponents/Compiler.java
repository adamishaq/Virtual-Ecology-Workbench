
package VEW.UIComponents;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import org.antlr.runtime.*;

import VEW.XMLCompiler.ANTLR.ANTLRParser;
import VEW.XMLCompiler.ASTNodes.ConstructedASTree;
import VEW.XMLCompiler.ASTNodes.SemanticCheckException;
import VEW.XMLCompiler.ASTNodes.TreeWalkerException;

import java.io.*;

public class Compiler {
	
	// Test folder @ C:\Users\Andy\Imperial\Planktonika\Test Cases\Source.txt
	
	private final static JButton pre = new JButton("Preview");
	private final static JButton compile = new JButton("Compile");
	private static JTextPane syntax = new JTextPane();
	private static LatexPreview preview = new LatexPreview();
	private final static SyntaxHighlighter syntax_highlighter = new SyntaxHighlighter();
	private final static JEditorPane error_log = new JEditorPane();
	
	// Open/save components
	final static JFileChooser file_chooser = new JFileChooser();
	private final static JButton open = new JButton("Open");
	private final static JButton save = new JButton("Save");
	
	private final static JPanel lpanel = new JPanel();
	
	public static void main(String[] args) {
	
		final JFrame frame = new JFrame("BACON Compiler");

		final JPanel no_wrap_syntax = new JPanel(new BorderLayout());
		no_wrap_syntax.add(syntax);
		JScrollPane scroll_pane_syntax = new JScrollPane(no_wrap_syntax);
		scroll_pane_syntax.setPreferredSize(new Dimension(400,400));
		
		final JPanel no_wrap_preview = new JPanel(new BorderLayout());
		no_wrap_preview.add(preview,BorderLayout.NORTH);
		JScrollPane scroll_pane_preview = new JScrollPane(no_wrap_preview);
		scroll_pane_preview.setPreferredSize(new Dimension(400,400));
		
		JScrollPane scroll_pane_errors = new JScrollPane(error_log);
		scroll_pane_errors.setPreferredSize(new Dimension(808,100));
		
		syntax.addKeyListener(new TypingListener());
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
		syntax.setFont(font);
		syntax.setContentType("text/html");
		syntax.setText("<html><PRE></PRE></html>");
		
		error_log.setEditable(false);
		error_log.setFont(font);
		error_log.setContentType("text/html");
		error_log.setText("<html><PRE></PRE></html>");
		
		lpanel.add(scroll_pane_syntax);
		lpanel.add(scroll_pane_preview);
		lpanel.add(scroll_pane_errors);
		
		javax.swing.filechooser.FileFilter f = new BACONFilter();
		file_chooser.setFileFilter(f);
		
		open.setPreferredSize(new Dimension(80,25));
		open.addActionListener(new OpenListener());
		lpanel.add(open);
		
		save.setPreferredSize(new Dimension(80,25));
		save.addActionListener(new SaveListener());
		lpanel.add(save);
		
		pre.setPreferredSize(new Dimension(80,25));
		pre.addActionListener(new PreviewListener());
		lpanel.add(pre);
		
		compile.setPreferredSize(new Dimension(80,25));
		compile.addActionListener(new CompileListener());
		lpanel.add(compile);
		
		
		lpanel.setSize((850), (600));
		lpanel.setVisible(true);
		frame.add(lpanel);
		frame.setSize((850), (600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		preview.update_preview("pre");
		preview.setVisible(false);
		
	}
	
	private static void highlight_syntax() {
		int pos = syntax.getCaret().getDot();
		syntax.setText(syntax_highlighter.highlight(syntax.getText()));
		syntax.getCaret().setDot(pos);
	}
	
	public static void preview() {
		// Clear all error lines
		syntax_highlighter.clear_flags();
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
	
static class CompileListener implements ActionListener {
		
	public void actionPerformed(ActionEvent event) {
		syntax_highlighter.clear_flags();
		ANTLRParser p = new ANTLRParser (syntax_highlighter.getPlainText(syntax.getText()));
		try {
			ConstructedASTree ct = p.getAST();
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
				for (TreeWalkerException t : ct.getExceptions()) {
					syntax_highlighter.flag_line(t);
					errors += t.getError() + "\n";
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
		
}

static class OpenListener implements ActionListener {
	
	public void actionPerformed(ActionEvent event) {
		int choice = file_chooser.showOpenDialog(lpanel);
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
	
}

static class SaveListener implements ActionListener {
	
	public void actionPerformed(ActionEvent event) {
		int choice = file_chooser.showSaveDialog(lpanel);
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
	
}

static class PreviewListener implements ActionListener {
	
	public void actionPerformed(ActionEvent event) {
		/*
		// Clear all error lines
		syntax_highlighter.clear_flags();
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
			}
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
			}
			highlight_syntax();
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			System.out.println("RECOGNITION EXCEPTION");
			e.printStackTrace();
		}*/
		preview();
	}
	
}

static class TypingListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO - only characters?
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// Parse text and check for errors?
			preview();
		} else if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_UP
				&& e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_RIGHT
				&& e.getKeyCode() != KeyEvent.VK_LEFT) {
			// Update the syntax pane with highlighting, ensuring caret
			// position remains the same
			highlight_syntax();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}

}
