package VEW.Planktonica2.StageEditor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;
import VEW.Planktonica2.Model.FunctionalGroup;

public class StageEditorPanel extends JPanel {

	private static final long serialVersionUID = -4354313346035286536L;
	
	private FunctionalGroupController controller;

	
	public StageEditorPanel (FunctionalGroupController controller) {
		super();
		this.controller = controller;
		
		initializeGUI();
		
		
	}

	private void initializeGUI() {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		ColumnModel m = new ColumnModel(this.controller);
		
		TableModel headerData = new RowModel(this.controller);
        AbstractTableModel data = new StageTableModel(this.controller, m);
        JTable table = new JTable(data);
        table.setColumnModel(m);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JTable rowHeader = new JTable(headerData);
        rowHeader.setColumnModel(new EmptyColumns());

        LookAndFeel.installColorsAndFont
            (rowHeader, "TableHeader.background", 
            "TableHeader.foreground", "TableHeader.font");
        
        rowHeader.setIntercellSpacing(new Dimension(0, 0));
        Dimension d = rowHeader.getPreferredScrollableViewportSize();
        d.width = rowHeader.getPreferredSize().width;
        rowHeader.setPreferredScrollableViewportSize(d);
        rowHeader.setRowHeight(table.getRowHeight());
        rowHeader.setDefaultRenderer(Object.class, new RowHeaderRenderer());
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setRowHeaderView(rowHeader);
        
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JTableHeader corner = rowHeader.getTableHeader();
        corner.setResizingAllowed(false);
        corner.setReorderingAllowed(false);

        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);

        new JScrollPaneAdjuster(scrollPane);

        new JTableRowHeaderResizer(scrollPane).setEnabled(true);
        
        new RowHeaderResizer(scrollPane).setEnabled(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridwidth = 9;
        c.gridx = 0;
        c.gridy = 0;
        this.add(scrollPane,c);
        JPanel button_panel = new JPanel(new GridLayout());
        button_panel.setPreferredSize(new Dimension(250,50));
        AddStageButtonListener list = new AddStageButtonListener(this);
        JButton add_stage = new JButton("Add");
        add_stage.setPreferredSize(new Dimension(75,30));
        add_stage.addActionListener(list);
        JButton rename_stage = new JButton("Rename");
        rename_stage.setPreferredSize(new Dimension(100,30));
        rename_stage.addActionListener(new RenameStageButtonListener(this));
        JButton delete_stage = new JButton("Delete");
        delete_stage.setPreferredSize(new Dimension(75,30));
        delete_stage.addActionListener(new DeleteStageButtonListener(this));
        button_panel.add(add_stage);
        button_panel.add(rename_stage);
        button_panel.add(delete_stage);
        c.weightx = 0;
        c.gridwidth = 3;
        c.gridy = 1; 
        c.gridx = 3;
        this.add(button_panel,c);
	}
	
	/**
	 * Returns a list of all stage names in the current functional group
	 * @return - <code>Collection</code> of Strings corresponding to stage names
	 */
	public Collection<String> get_stages() {
		// Get all available stages
		FunctionalGroup g = controller.getSelectedFunctionalGroup();
		if (g != null) {
			Collection<String> stages = g.getStageNames();
			ArrayList<String> stage_names = new ArrayList<String>();
			for (String s : stages) {
				stage_names.add(s);
			}
			// Alphabetise them
			Collections.sort(stage_names);
			return stage_names;
		}
		return null;
	}
	
	/**
	 * Presents the user with a dialog box to allow them to add a stage to the current functional
	 * group
	 */
	public void add_stage() {
		if (this.controller.getSelectedCatagory() == null)
			return;
		String name = JOptionPane.showInputDialog(this,
	        	"Choose a name for the stage",
	            "Name Stage", 1);
	    if (name == null) {
	    	return;
	    }
	    // If the stage cannot be added, inform the user
		if (!controller.addStage(name)) {
			JOptionPane.showMessageDialog(this, "A Stage with that name already exists");
		}
	}
	
	/**
	 * Present the user with a dialog box to choose a stage to delete
	 */
	public void delete_stage() {
		SelectStageDialog s = new SelectStageDialog(this,true);
		s.setAlwaysOnTop(true);
		s.setLocationRelativeTo(null);
		s.setVisible(true);
	}
	
	/**
	 * Remove a chosen stage fro mthe model
	 * @param select - The name of the selected stage
	 */
	public void remove_stage(String select) {
		controller.delete_stage(select);
	}

	/**
	 * Present the user with a dialog box to choose a stage to rename
	 */
	public void rename_stage() {
		SelectStageDialog s = new SelectStageDialog(this,false);
		s.setAlwaysOnTop(true);
		s.setLocationRelativeTo(null);
		s.setVisible(true);
	}
	
	/**
	 * Rename a chosen stage in the model
	 * @param name - The previous name of the stage
	 * @param new_name - The new name for the stage
	 */
	public void stage_rename(String name, String new_name) {
		controller.rename_stage(name, new_name);
	}
	
	private class RowModel extends AbstractTableModel implements Observer {

		private static final long serialVersionUID = 731778953897127991L;

		private FunctionalGroupController controller;
		
		public RowModel(FunctionalGroupController controller) {
			super();
			this.controller = controller;
			controller.addObserver(this);
		}

		@Override
		public int getColumnCount() {
			return 1;
		}

		@Override
		public int getRowCount() {
			return controller.getNoFunctions();
		}

		@Override
		public Object getValueAt(int x, int y) {
			return controller.getFunctionalNameAtIndex(x);
		}

		
		@Override
		public void update(Observable obs, Object arg) {
			if (obs == controller && arg instanceof FunctionalGroup) {
				
				this.fireTableDataChanged();
				
			}
			
		}
		
	}
	
	private class ColumnModel extends DefaultTableColumnModel implements Observer {

		private static final long serialVersionUID = -1025098191670924655L;
		
		private FunctionalGroupController controller;
		
		public ColumnModel(FunctionalGroupController controller) {
			super();
			this.controller = controller;
			controller.addObserver(this);
			fillColumns();
		}

		private void fillColumns() {
			
			// remove all columns.
			this.tableColumns.removeAllElements();

			
			FunctionalGroup g = controller.getSelectedFunctionalGroup();
			
			if (g != null) {
			
				Collection<String> stages = g.getStageNames();
				// Sort the stage names alphabetically
				ArrayList<String> stage_names = new ArrayList<String>();
				for (String s : stages) {
					stage_names.add(s);
				}
				Collections.sort(stage_names);
				int modelIndex = 1;

				for (String s : stage_names) {
					TableColumn c = new TableColumn(modelIndex);
					c.setHeaderValue(s);
					this.addColumn(c);
					modelIndex++;
				}
			}
			
			
			
		}
		
		@Override
		public void moveColumn(int from, int to) {
			super.moveColumn(from, from);
		}
		


		@Override
		public void update(Observable obs, Object arg) {
			
			if (obs == controller && arg instanceof FunctionalGroup) {
				
				fillColumns();
				
			}
			
		}
		
	}
	
	private class EmptyColumns extends DefaultTableColumnModel {
		
		private static final long serialVersionUID = -1294065712418743957L;

		public EmptyColumns () {
			super();
			this.tableColumns.removeAllElements();
			TableColumn c = new TableColumn();
			c.setHeaderValue("");
			this.addColumn(c);
		}
		
	}
	
	private class StageTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 9005405339776933763L;
		private FunctionalGroupController controller;
		private TableColumnModel columnModel;
		
		public StageTableModel (FunctionalGroupController controller, TableColumnModel columnModel) {
			super();
			this.controller = controller;
			this.columnModel = columnModel;
		}
		
		@Override
		public int getColumnCount() {
			int i = this.controller.getNoStages(); 
			return i;
			
		}

		@Override
		public int getRowCount() {
			int i = this.controller.getNoFunctions(); 
			return i;
		}

		
		@Override
		/**
		 * 
		 * if x < 2 it returns the function name at y
		 *
		 * 
		 * else it returns whether the function(y) is called in the stage(x)
		 * 
		 * @param x
		 * @param y
		 * return correct value at cell (x, y)
		 */
		public Object getValueAt(int x, int y) {
			
			if (controller.getSelectedFunctionalGroup() == null) {
				
				return "No Selected Functional Group.";
				
			} else {
				String stageName = this.getColumnName(y - 1);
				
				return this.controller.stageIsCalledIn(stageName, x);
				
			}
		}
		
		@Override
		public void setValueAt(Object aValue, int x, int y) {
			if (aValue instanceof Boolean) {
				boolean isCalled = (Boolean) aValue;
				
				String stageName = this.getColumnName(y-1);

				this.controller.setStageIsCalledIn(stageName, x, isCalled);	

			}
			
			this.fireTableCellUpdated(x, y);
		}
		
		@Override
		public Class<?> getColumnClass(int column) {
			return Boolean.class;
		}
		
		@Override
		public String getColumnName(int index) {
			if (index < columnModel.getColumnCount()) {
				return this.columnModel.getColumn(index).getHeaderValue().toString();
			} else {
				return "";
			}
			
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}
	}
	
}
