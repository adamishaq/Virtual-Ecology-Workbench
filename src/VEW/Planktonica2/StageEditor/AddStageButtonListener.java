package VEW.Planktonica2.StageEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStageButtonListener implements ActionListener {
	
	private StageEditorPanel parent;
	
	public AddStageButtonListener(StageEditorPanel parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.add_stage();
	}

}
