package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import VEW.Planktonica2.Display;
import VEW.Planktonica2.EditorPanel;

public class SwitchLayoutButtonListener implements ActionListener {

private EditorPanel parent;
private JButton button;
private boolean orientation = true;
	
	public SwitchLayoutButtonListener(EditorPanel edit, JButton button) {
		this.parent = edit;
		this.button = button;
	}
	
	public void actionPerformed(ActionEvent event) {
		parent.switchEditorPanel();
		if (orientation) {
			button.setIcon(new ImageIcon(Display.IconRoot + "switch_layout_vertical.png"));
			button.setToolTipText("Show the editor and preview panes side by side");
		} else {
			button.setIcon(new ImageIcon(Display.IconRoot + "switch_layout_horizontal.png"));
			button.setToolTipText("Show the editor and preview panes stacked");
		}
		this.orientation = !this.orientation;
	}
	
}
