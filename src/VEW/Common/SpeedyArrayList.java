package VEW.Common;

import java.util.AbstractList;

public class SpeedyArrayList extends AbstractList {

	private Object[] array;
	private int size = 0;
	
	public SpeedyArrayList() {
		this(10);
	}
	
	public SpeedyArrayList(int capacity) {
		array = new Object[capacity];
	}
	
	public void add(int index, Object obj) {
		if (array.length == size)
			ensureCapacity(size * 2 + 1);
		
		for (int i = size; i > index; i--)
			array[i] = array[i-1];
		
		array[index] = obj;
		size++;
	}

	private void ensureCapacity(int newCapacity) {
		Object[] newArray = new Object[newCapacity];
		for (int i = 0; i < size; i++)
			newArray[i] = array[i];
		
		array = newArray;
	}

	public boolean add(Object obj) {
		if (array.length == size)
			ensureCapacity(size * 2 + 1);
		
		array[size++] = obj;
		
		return true;
	}

	public Object remove(int index) {
		Object removed = array[index];
		
		for (int i = index; i < size-1; i++)
			array[i] = array[i+1];
		
		size--;
		
		return removed;
	}

	public Object set(int index, Object obj) {
		Object replaced = array[index];
		array[index] = obj;
		return replaced;
	}

	public Object get(int index) {
		return array[index];
	}

	public int size() {
		return size;
	}

	

}
