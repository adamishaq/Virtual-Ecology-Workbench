package VEW.Planktonica2.StageEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenameStageButtonListener implements ActionListener {
	
	private StageEditorPanel parent;
	
	public RenameStageButtonListener(StageEditorPanel parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.rename_stage();
	}

}
