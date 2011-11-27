package VEW.Planktonica2.StageEditor;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

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
import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.FunctionalGroup;
import VEW.Planktonica2.Model.Stage;

public class StageEditorPanel extends JPanel {

	private static final long serialVersionUID = -4354313346035286536L;
	
	private FunctionalGroupController controller;

	
	public StageEditorPanel (FunctionalGroupController controller) {
		super();
		this.controller = controller;
		
		initializeGUI();
		
		
	}

	private void initializeGUI() {
		
		ColumnModel m = new ColumnModel(this.controller);
		
		TableModel headerData = new RowModel(this.controller);
        TableModel data = new StageTableModel(this.controller, m);
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
        corner.setReorderingAllowed(false);
        corner.setResizingAllowed(false);

        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);

        new JScrollPaneAdjuster(scrollPane);

        new JTableRowHeaderResizer(scrollPane).setEnabled(true);
        
        new RowHeaderResizer(scrollPane).setEnabled(true);

        this.add(scrollPane);
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
			Function f = controller.getFunctionAtIndex(x);
			if (f == null) {
				return "";
			} else {
				return f.getName();
			}
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
				int modelIndex = 1;

				for (String s : stages) {
					TableColumn c = new TableColumn(modelIndex);
					c.setHeaderValue(s);
					this.addColumn(c);
					modelIndex++;
				}
			}
			
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
				
				String stageName = this.getColumnName(y-1);
				
				Stage selected = this.controller.getStage(stageName);
				Function f = this.controller.getFunctionAtIndex(x);
				
				// called in holds a referrence to the origional stage
				return f.isCalledIn(selected);
			}
		}
		
		@Override
		public void setValueAt(Object aValue, int x, int y) {
			if (aValue instanceof Boolean) {
				boolean isCalled = (Boolean) aValue;
				
				String stageName = this.getColumnName(y-1);
				
				Stage selected = this.controller.getStage(stageName);
				Function f = controller.getFunctionAtIndex(x);
				
				if (isCalled) {
					// add to list of CalledIn
					f.addToCalledIn(selected);
				} 
				else {
					// remove from list
					f.removeFromCalledIn(selected);
				}
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
