package VEW.Planktonica2.DisplayEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import VEW.Planktonica2.Display;
import VEW.Planktonica2.Model.Unit;
import VEW.Planktonica2.Model.UnitChecker;


public class AddCategoryButtonListener implements ActionListener {

	private Display parent;
	
	public AddCategoryButtonListener(Display d) {
		parent = d;
	}
	
	public void actionPerformed(ActionEvent event) {
        parent.addCategory();/*
		Unit sec = new Unit(1,"seconds",1);
		Unit sec2 = new Unit(1,"seconds",-1);
		Unit mol = new Unit(1,"mol",3);
		ArrayList<Unit> first = new ArrayList<Unit>();
		first.add(sec); first.add(mol);
		ArrayList<Unit> second = new ArrayList<Unit>();
		second.add(sec2); second.add(mol);
		System.out.println(new UnitChecker().divide_units(first, second));*/
	}
	
}
