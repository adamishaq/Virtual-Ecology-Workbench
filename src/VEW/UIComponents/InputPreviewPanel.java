package VEW.UIComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class InputPreviewPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4541133409300076707L;
	private SyntaxHighlighter syntax_highlighter = new SyntaxHighlighter();
	private JTextPane input = new JTextPane();
	private LatexPreview preview = new LatexPreview();
	private int height;
	private int width;
	
	public InputPreviewPanel() {
		this.setPreferredSize(new Dimension(650,300));
		this.height = 300;
		this.width = 650;
		initialise();
	}
	
	public InputPreviewPanel(Dimension d) {
		this.setPreferredSize(d);
		this.height = d.height;
		this.width = d.width;
		initialise();
	}
	
	private void initialise() {
		
		final JPanel no_wrap_syntax = new JPanel(new BorderLayout());
		no_wrap_syntax.add(input);
		JScrollPane scroll_pane_syntax = new JScrollPane(no_wrap_syntax);
		scroll_pane_syntax.setPreferredSize(
				new Dimension((this.width / 100) * 95, this.height * 95 / 200));
		this.add(scroll_pane_syntax);
		
		final JPanel no_wrap_preview = new JPanel(new BorderLayout());
		no_wrap_preview.add(preview,BorderLayout.NORTH);
		JScrollPane scroll_pane_preview = new JScrollPane(no_wrap_preview);
		scroll_pane_preview.setPreferredSize(
				new Dimension((this.width / 100) * 95, this.height * 95 / 200));
		this.add(scroll_pane_preview);
		
	}
	
	public JTextPane getInput() {
		return this.input;
	}
	
	public LatexPreview getPreview() {
		return this.preview;
	}

}
