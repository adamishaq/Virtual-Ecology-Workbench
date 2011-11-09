
package VEW.UIComponents;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import org.antlr.runtime.*;

import VEW.XMLCompiler.ANTLR.ANTLRParser;
import VEW.XMLCompiler.ASTNodes.ASTree;
import VEW.XMLCompiler.ASTNodes.ConstructedASTree;
import VEW.XMLCompiler.ASTNodes.SemanticCheckException;
import VEW.XMLCompiler.ASTNodes.TreeWalkerException;

import java.io.*;

public class Compiler {
	
	// Test folder @ C:\Users\Andy\Imperial\Planktonika\Test Cases\Source.txt
	
	private final static JButton open = new JButton("Open");
	private final static JButton pre = new JButton("Preview");
	private final static JButton compile = new JButton("Compile");
	private final static JTextField file_path = new JTextField("C:\\Users\\Andy\\Imperial\\Planktonika\\Test Cases\\Source.txt");
	private static JTextPane syntax = new JTextPane();
	private static LatexPreview preview = new LatexPreview();
	private final static SyntaxHighlighter syntax_highlighter = new SyntaxHighlighter();
	private final static JTextPane error_log = new JTextPane();
	
	public static void main(String[] args) {
	
		final JFrame frame = new JFrame("BACON Compiler");
		final JPanel lpanel = new JPanel();

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
		
		file_path.setPreferredSize(new Dimension(300,25));
		lpanel.add(file_path);
		
		open.setPreferredSize(new Dimension(80,25));
		open.addActionListener(new OpenListener());
		lpanel.add(open);
		
		pre.setPreferredSize(new Dimension(80,25));
		pre.addActionListener(new PreviewListener());
		lpanel.add(pre);
		
		compile.setPreferredSize(new Dimension(80,25));
		compile.addActionListener(new CompileListener());
		lpanel.add(compile);
		
		syntax.addKeyListener(new TypingListener());
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
		syntax.setFont(font);
		syntax.setContentType("text/html");
		syntax.setText("<html><PRE></PRE></html>");
		
		//error_log.setEnabled(false);
		lpanel.add(scroll_pane_syntax);
		lpanel.add(scroll_pane_preview);
		lpanel.add(scroll_pane_errors);
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
			} else {
				String errors = "Compilation errors occurred:\n";
				for (TreeWalkerException t : ct.getExceptions()) {
					syntax_highlighter.flag_line(t);
					errors += t.getError() + "\n";
				}
				errors += "Compilation aborted";
				highlight_syntax();
				error_log.setText(errors);
			}
		} catch (RecognitionException e) {
			System.out.println("RECOGNITION EXCEPTION");
			e.printStackTrace();
		}
	}
		
}

static class OpenListener implements ActionListener {
	
	public void actionPerformed(ActionEvent event) {
		try {
			String fpath = file_path.getText();
			FileInputStream fstream = new FileInputStream(fpath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String file = "<html><head></head><PRE>", line = "";
			while ((line = br.readLine()) != null)   {
				file += (line + "\n"); 
			}
			in.close();
			file += "</PRE></html>";
			syntax.setText(file);
			highlight_syntax();
		} catch (Exception e) {
			syntax.setText("Could not find the file!");
		}
		
	}
	
}

static class PreviewListener implements ActionListener {
	
	public void actionPerformed(ActionEvent event) {
		// Clear all error lines
		syntax_highlighter.clear_flags();
		ANTLRParser p = new ANTLRParser (syntax_highlighter.getPlainText(syntax.getText()));
		//System.out.println(syntax_highlighter.getPlainText(syntax.getText()));
		try {
			ConstructedASTree ct = p.getAST();
			if (ct.getExceptions().isEmpty()) {
				String latex = "\\begin{array}{lr}";
				latex += ct.getTree().generateLatex();
				latex += "\\end{array}";
				preview.setVisible(true);
				preview.update_preview(latex);
			} else {
				String errors = "Compilation errors occurred:\n";
				for (TreeWalkerException t : ct.getExceptions()) {
					syntax_highlighter.flag_line(t);
					errors += t.getError() + "\n";
				}
				errors += "Preview aborted";
				highlight_syntax();
				error_log.setText(errors);
			}
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			System.out.println("RECOGNITION EXCEPTION");
			e.printStackTrace();
		}
	}
	
}

static class TypingListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// Parse text and check for errors?
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
