package VEW.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;

public class StringTools {
  private static StringTools _stringtools;
  private static final String openBrackets = "{[(";
  private static final String closeBrackets = "}])";

  private StringTools() { }
  
  public static String replace(String source, String srch, String repl) {
    int i=0;
    while (source.indexOf(srch,i)>=0) {
      int remember = source.indexOf(srch,i);
      source = source.substring(0,source.indexOf(srch,i))+repl+source.substring(source.indexOf(srch,i)+srch.length());
      i = remember+repl.length();
    }
    return source;
  }
  
  public static String doubleBackSlash(String s) {
    StringBuffer newString = new StringBuffer();
    for (int i=0; i<s.length(); i++) {
      if (s.charAt(i)=='\\') newString.append('\\');
      newString.append(s.charAt(i));
    }
    return newString.toString();
  }
  
  public static String nonSpace(String s) {
    // Remove spaces!
    String s2 = new String();
    for (int i=0; i<s.length(); i++) {
      if ((s.charAt(i)=='-') || (s.charAt(i) == ':'))  s2 += "_";
      else if ((s.charAt(i)=='(') || (s.charAt(i)==')')) s2 += "$";
      else if (s.charAt(i)=='.') s2 += "_";
      else if (s.charAt(i)!=' ') s2 += s.charAt(i);
      
    }
    return s2;
  }
    
   public static String join(String [] s, String separator, int slice) {
        // by evan   
        // takes positive (index + 1) or negative (from the end) slices
        
        String output = "";
        
        if (slice < 1) {
            slice = s.length + slice;
        }
        
        if (s.length > 0) {
            output = s[0];
            for (int i=1; i < slice; i++) {
                output += separator + s[i];
            }
        }
        return output;
        
    }
   
   public static String firstRegexMatch(String s, String regex) {
        String [] matches = regexMatches(s, regex);
        if (matches.length < 1) {
            return null;
        } else {
            return matches[0];
        }
   }
   
   public static String [] regexMatches(String s, String regex) {
    // by evan
    // optimization is for the weak
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(s);           
    String [] matches = new String[0];
    
    while (m.find()) {
        String [] temp = matches;
        /*if (matches == null) {
            matches = new String[1];
            temp = new String[0];
        } else {*/
        matches = new String[matches.length + 1];
        //}
        for (int i=0; i < temp.length; i++) {
            matches[i] = temp[i];
        }
        matches[temp.length] = m.group(1);
    } 
    
    return matches;
   }
   
   public static String [] slice(String [] array, int startIndex, int endIndex) {
       
       if (endIndex < 0) 
           endIndex = array.length + endIndex;
       
       String [] result = new String[endIndex - startIndex];
       for (int i=startIndex; i < endIndex; i++) {
           result[i - startIndex] = array[i];
       }
       return result;
   }
   
   public static String [] slice(String [] array, int startIndex) {
       return slice(array, startIndex, array.length);
   }
   
    
    public static String join(String [] s, String separator) {
      return join(s, separator, 0);
        
    }
  
  public static StringTools getInstance() {
    if (_stringtools==null) _stringtools = new StringTools();
    return _stringtools;
  }

  static public int easyGet(String s,char c) {
    int i=0;
    while ((i<s.length())&&(s.charAt(i)!=c)) {i++;}
    if (i>=s.length()) return -1; else return i;
  }

  
  static public int getUnNested(String s,char c) {
    int i=0,j=0;
    while ((i<s.length()) && ((s.charAt(i)!=c)||(j>0))) {
      if ((s.charAt(i)=='{')||(s.charAt(i)=='(')||(s.charAt(i)=='[')) j++;
      if ((s.charAt(i)=='}')||(s.charAt(i)==')')||(s.charAt(i)==']')) j--;
      i++;
    }
    if (i>=s.length()) return -1; else return i;
  }

  static public String spitAny(String s, String t) {

    char bracket = ' ';
      
    if (s.endsWith("{")) {
        bracket = '}';
    } else if (s.endsWith("(")) {
        bracket = ')';
    } else if (s.endsWith("[")) {
        bracket = ']';
    }
      
    s = s.substring(t.length());
    return s.substring(0,getUnNested(s, bracket));
  }    
  
  static public String spit(String s, String t) {
      
    s = s.substring(t.length());
    int pos = getUnNested(s, '}');
    if (pos < 1) {
    	return "";
    } else {
    	return s.substring(0,pos);
    }
  }    

  public static String LHS(String s,int i) { 
    if (i>=0) return s.substring(0,i); else return s; }
 
  public static String RHS(String s,int i) { 
    if (i>=0) return s.substring(i+1); else return ""; }  
    
  public static boolean chomp(String s, String t) {
    return s.startsWith(t);
    //if (t.length()>s.length()) { return false; } else { return (s.substring(0,t.length()).equals(t)); }
  }

  public static boolean memberOf(String s, String[] sa) {
    int i=0;
    while (i<sa.length) {
      if (sa[i].equals(s)) return true;
      else i++;
    }
    return false;
  }

  public static int firstOccurrence(String SearchItem, String[] SearchArray)
  {
    for(int i = 0; i < SearchArray.length; i++)
    {
      if(SearchArray[i].equals(SearchItem))
      {
        return i;
      }
    }
    return -1;
  }
  
  public static int indexOf(String s, String [] array) {
  	return firstOccurrence(s, array);
  }

  public static boolean isIn(char c, String s) {
    return (s.indexOf(c)>=0);
  }

  public static String[] nestedSplit(String s, char t) {
    int nestCount = 0;
    int i=0;
    Vector V = new Vector();
    while (i<s.length()) {
      if (openBrackets.indexOf(""+s.charAt(i))>=0) nestCount++;
      if (closeBrackets.indexOf(""+s.charAt(i))>=0) nestCount--;
      if ((s.charAt(i)==t) && (nestCount==0)) {

        V.add(s.substring(0,i));
        s = s.substring(i+1);
        i = -1;
      }
      i++;
    }
    if (s.length()>0) V.add(s);
    String[] sa = new String[V.size()];
    i=0;
    while (i<V.size()) sa[i] = (String) V.elementAt(i++);
    return sa;
  }

  public static void addInOrder(Vector V, String s) {
    int i=0;
    while ((i<V.size()) && ((((String) V.elementAt(i)).toUpperCase().compareTo(s.toUpperCase())<0))) i++;
    if (i<V.size()) V.add(i,s); else V.add(s);
  }
  
  public static void setStringItem(JComboBox j, String s) {
    int i=0;
    boolean found = false;
    while (i<j.getItemCount() && (!found)) {
      String t = (String) j.getItemAt(i);
      if (s.equals(t)) {
        found = true;
        j.setSelectedIndex(i);
      }
      i++;
    }
  }
  
  public static void setListItem(JList j, String s) {
    int i=0;
    boolean found=false;
    DefaultListModel jm = (DefaultListModel) j.getModel();
    while (i<jm.getSize() && (!found)) {
      String t = (String) jm.elementAt(i);
      if (s.equals(t)) {
        found = true;
        j.setSelectedIndex(i);
      }
      i++;
    }
    
   
  }
  
  public static void setDoubleItem(JComboBox j, double di) {
    int i=0;
    boolean found = false;
    while (i<j.getItemCount() && (!found)) {
      double d = Double.parseDouble((String)j.getItemAt(i));
      if (d==di) {
        found = true;
        j.setSelectedIndex(i);
      }
      i++;
    }
  }
  
  public static boolean parseBoolean(String s) {
    return (s.toUpperCase().equals("TRUE"));
  }
  
  public static String htmlFromXML(String s) {
    s = s.replaceAll("~lt~","<");
    s = s.replaceAll("~gt~",">");
    return s;
  }
  
  public static String htmlIntoXML(String s) {
    s = s.replaceAll("<","~lt~");
    s = s.replaceAll(">","~gt~");
    s = s.replaceAll("\n","");
    return s;
  }
  
  public static String htmlFromXMLStripped(String s) {
    s = htmlFromXML(s);
    if (s.indexOf("<body>")>=0) s = s.substring(s.indexOf("<body>")+6);
    if (s.indexOf("</body>")>=0) s = s.substring(0,s.indexOf("</body>"));
    if (s.indexOf("<html>")>=0) s = s.substring(s.indexOf("<html>")+6);
    if (s.indexOf("</html>")>=0) s = s.substring(0,s.indexOf("</html>"));
    

    return s;
  }
  
  public static void copyFile(String inDir, String outDir, String file)  {
    copyFile(new File(inDir + "/" + file), new File(outDir + "/" + file));
  }

  public static void copyFile(String inDir, String outDir, String file1, String file2)  {
    copyFile(new File(inDir + "/" + file1), new File(outDir + "/" + file2));
  }

  public static String assureUniqueFile(String path, String fileName) {
    if (!new File(path+File.separator+fileName).exists()) return fileName;
    else {
      int no = 0;
      while (new File(path+File.separator+fileName+"_"+no).exists()) no++; 
      return fileName+"_"+no;
    }
  }
  
  public static void copyFile(String in, String out) {
    copyFile(new File(in), new File(out));
  }
  
  private static void copyFile(File in, File out)  {
    try {
      FileInputStream fis = new FileInputStream(in);
      FileOutputStream fos = new FileOutputStream(out);
      copyStream(fis,fos);
    } catch (Exception e) { e.printStackTrace(); }
  }
  
  public static void copyStream(InputStream fis, OutputStream fos) {
    try {
      byte[] buf = new byte[1024];
      int i = 0;
      while ((i = fis.read(buf)) != -1) {
        fos.write(buf, 0, i);
      }
      fis.close();
      fos.close();
    } catch (Exception e) { e.printStackTrace(); }
    
  }
  
  public static double log10(double d) { return Math.log(d)/Math.log(10); }
  
  public static void recursiveDelete(File f) {
    File[] fList = f.listFiles();
    for (int i=0; i<fList.length; i++) {
      if (fList[i].isFile()) fList[i].delete();
      else {
        recursiveDelete(fList[i]);
        fList[i].delete();
      }
    }
    f.delete();
  }
  
  public static PrintWriter OpenOutputFile(String s) {
    PrintWriter outDF = null;
    try {
      outDF = new PrintWriter(new FileOutputStream(new File(s)));
    } catch (IOException e) { e.printStackTrace(); }

    return outDF;
  }

}