package VEW.Planktonica2.StageEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectDialogButtonListener implements ActionListener {
	
	private SelectStageDialog parent;
	private boolean delete;
	
	public SelectDialogButtonListener(SelectStageDialog parent,boolean delete) {
		this.parent = parent;
		this.delete = delete;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (delete) {
			parent.delete_selected();
		} else {
			parent.rename_selected();
		}
	}

}
