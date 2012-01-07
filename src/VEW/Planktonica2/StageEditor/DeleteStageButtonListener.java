package VEW.Planktonica2.StageEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteStageButtonListener implements ActionListener {
	
	private StageEditorPanel parent;
	
	public DeleteStageButtonListener(StageEditorPanel parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.delete_stage();
	}

}
