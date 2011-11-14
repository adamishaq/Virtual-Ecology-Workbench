package VEW.UIComponents;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class BACONFilter extends FileFilter {

	public BACONFilter() {
		super();
	}
	
	@Override
	public boolean accept(File pathname) {
		if (pathname.isDirectory())
	        return true;
	    String extension = getExtension(pathname);
	    if (extension != null && extension.equals("bacon")) {
	        return true;
	    }
	    return false;
	}
	
	public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

	@Override
	public String getDescription() {
		return "Bacon Source File (.bacon)";
	}

}
