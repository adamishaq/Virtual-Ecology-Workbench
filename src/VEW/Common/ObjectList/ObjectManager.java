package VEW.Common.ObjectList;

public interface ObjectManager {

	public Object createNew();
	public void editObject(Object obj, ObjectEditListener listener);
	
}
