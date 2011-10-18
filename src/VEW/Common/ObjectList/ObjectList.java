package VEW.Common.ObjectList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ObjectList extends JPanel {
	
	private ObjectManager manager;
	private ObjectListModel model;
	private JList list;
	private JButton addButton;
	private JButton editButton;
	private JButton removeButton;
	
	public ObjectList(ObjectManager _manager) {
		super(new BorderLayout());
		
		manager = _manager;
		model = new ObjectListModel();
		
		createComponents();
	}
	
	private void createComponents() {
		list = new JList(model);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				boolean isSelection = list.getSelectedIndex() != -1;
				editButton.setEnabled(isSelection);
				removeButton.setEnabled(isSelection);
			}
		});
		add(new JScrollPane(list), BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				manager.editObject(manager.createNew(), new AddObject());
			}
		});
		buttonPanel.add(addButton);
		
		editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.editObject(list.getSelectedValue(),
						new ReplaceObject(list.getSelectedIndex()));
			}
		});
		editButton.setEnabled(false);
		buttonPanel.add(editButton);
		
		removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.remove(list.getSelectedIndex());
			}
		});
		removeButton.setEnabled(false);
		buttonPanel.add(removeButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public List getObjects() {
		return model.getObjects();
	}
	
	public ObjectManager getManager() {
		return manager;
	}

	public void setManager(ObjectManager _manager) {
		manager = _manager;
		reset();
	}

	public void reset() {
		model.reset();
	}
	
	private class ObjectListModel extends AbstractListModel {

		private List objects = new ArrayList();
		
		public int getSize() {
			return objects.size();
		}

		public void reset() {
			int size = objects.size();
			objects.clear();
			if (size > 0)
				fireIntervalRemoved(this, 0, size - 1);
		}

		public List getObjects() {
			return objects;
		}

		public Object getElementAt(int index) {
			return objects.get(index);
		}
		
		public void add(Object obj) {
			objects.add(obj);
			fireIntervalAdded(this, objects.size() - 1, objects.size() - 1);
		}
		
		public void add(int index, Object obj) {
			objects.add(index, obj);
			fireIntervalAdded(this, index, index);
		}
		
		public void remove(int index) {
			objects.remove(index);
			fireIntervalRemoved(this, index, index);
		}
		
		public void replace(int index, Object obj) {
			objects.set(index, obj);
			fireContentsChanged(this, index, index);
		}
		
	}

	private class AddObject implements ObjectEditListener {

		public void onEditFinished(Object obj) {
			model.add(obj);
		}
		
	}
	
	private class ReplaceObject implements ObjectEditListener {
		
		private int index;
		
		public ReplaceObject(int _index) {
			index = _index;
		}
		
		public void onEditFinished(Object obj) {
			model.replace(index, obj);
		}
		
	}
}
