package VEW.Planktonica2;

import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class ModelDisplay extends JPanel {

	static final long serialVersionUID = -4049107146333413101L;
	
	public abstract void paintComponent(Graphics g);

}
