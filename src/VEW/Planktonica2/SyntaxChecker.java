package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import VEW.Common.XML.XMLTag;
import VEW.Controller2.VEWController2;

public class SyntaxChecker extends JDialog {
  private JButton okButton = new JButton("Ok");
  private JTextArea details = new JTextArea("");
  private EventHandler eh = new EventHandler();
  private VEWController2 vc2 = null;
  
  private XMLTag model;
  
  public SyntaxChecker(VEWController2 vc, XMLTag _model) {
    super(vc,"Checking Model",true);
    vc2 = vc;
    this.model = _model;
    JScrollPane detailsScroller = new JScrollPane(details);
    detailsScroller.setPreferredSize(new Dimension(450,300));
    getContentPane().add(detailsScroller,BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(okButton);
    okButton.addActionListener(eh);
    getContentPane().add(buttonPanel,BorderLayout.SOUTH);
    addWindowListener(eh);
    setSize(500,400);
    pack();
  }

  public static StringBuffer getVarFrom(String match, StringBuffer source) {
    StringBuffer theVar = new StringBuffer();
    int pos = source.indexOf(match);
    for (int i=pos; i<pos+match.length(); i++) source.setCharAt(i,' ');
    int i=pos+match.length();
    while (source.charAt(i)!='}') {
      theVar.append(source.charAt(i));
      source.setCharAt(i++,' ');
    }
    source.setCharAt(i,' ');
    return theVar;
  }
    
  
  public static int getFunction(StringBuffer buf, int index) {
    int startFunc=index;
    while (buf.charAt(startFunc)!='&') {
    	startFunc--;
    }
    int endFunc=startFunc;
    while (buf.charAt(endFunc)!='?') {
    	endFunc++;
    }
    startFunc++;
    return Integer.parseInt(buf.substring(startFunc,endFunc));
  }
    
    public static StringBuffer funcToRW(XMLTag function) {
    StringBuffer rw = new StringBuffer();
    XMLTag[] eqs = function.getTags("equation");
    for (int eqNo=0; eqNo<eqs.length; eqNo++) {
      StringBuffer eq = new StringBuffer(eqs[eqNo].getValue("eq"));
      while (eq.indexOf("\\set{\\var{")>=0) {
    	  eq.setCharAt(eq.indexOf("\\set{\\var{")+8,'q');
      }
      while (eq.indexOf("\\assign{\\var{")>=0) {
    	  rw.append(">"+getVarFrom("\\assign{\\var{",eq)+"?");
      }
      while (eq.indexOf("\\assigndiff{\\var{")>=0) {
    	  rw.append(">"+getVarFrom("\\assigndiff{\\var{",eq)+"?");
      }
      while (eq.indexOf("\\var{")>=0) { 
    	  rw.append("<"+getVarFrom("\\var{",eq)+"?");
      }
    }
    return rw;
  }
   
    
  
  public void scanForDuplicateWrites(StringBuffer uses, XMLTag fg, String stage, XMLTag[] funcList) {
    int pos = 0;
    while (uses.indexOf(">",pos)>=0) {
      pos = uses.indexOf(">",pos);
      String theVar = uses.substring(pos+1,uses.indexOf("?",pos));
      if (uses.indexOf(">"+theVar+"?",pos+1)>=0) {
        String func1 = funcList[getFunction(uses,pos)].getValue("name");
        String func2 = funcList[getFunction(uses,uses.indexOf(">"+theVar+"?",pos+1))].getValue("name");
        details.append("ERROR - "+stage+" "+fg.getValue("name")+", "+theVar+" duplicate writes in "+func1+" and "+func2+". \n");
        update(getGraphics());
      } 
      pos++;
    }
  }
  
  public void scanForNotUsed(StringBuffer uses, XMLTag fg, String stage, XMLTag[] funcList) {
    StringBuffer spare = new StringBuffer();
    spare.append(uses);
    int pos=0;
    while (spare.indexOf(">")>=0) {
      pos = spare.indexOf(">");
      String theVar = spare.substring(pos+1,spare.indexOf("?",pos));
      XMLTag theVarTag = fg.getTagWhere("*","name",theVar);
      if (theVarTag!=null) {
        String type = theVarTag.getName();
        if (!type.equals("local")) {
          while (spare.indexOf(">"+theVar+"?")>=0) {
            pos = spare.indexOf(">"+theVar+"?");
            spare.delete(pos,pos+theVar.length()+1);
          }
          while (spare.indexOf("<"+theVar+"?")>=0) {
            pos = spare.indexOf("<"+theVar+"?");
            spare.delete(pos,pos+theVar.length()+1);
          }
        } else {
          if (spare.indexOf("<"+theVar+"?")<0) {
            String func = funcList[getFunction(spare,pos)].getValue("name");
            details.append("WARNING: "+stage+" "+fg.getValue("name")+", "+theVar+" written in "+func+" and not used.\n");
            update(getGraphics());
          }
        }
      }
      spare.setCharAt(pos,'+');
    }
  }
  
  public boolean scanForReadBeforeWrite(StringBuffer uses, XMLTag fg, String stage, XMLTag[] funcList) {
    boolean abort=false;
    StringBuffer spare = new StringBuffer();
    spare.append(uses);
    int pos=0;
    while ((spare.indexOf(">")>=0) /*&& (!abort)*/) {
      pos = spare.indexOf(">");
      String theVar = spare.substring(pos+1,spare.indexOf("?",pos));
      XMLTag theVarTag = fg.getTagWhere("*","name",theVar);
      if (theVarTag!=null) {
        String type = theVarTag.getName();
        if (!type.equals("local")) {
          while (spare.indexOf(">"+theVar+"?")>=0) {
            pos = spare.indexOf(">"+theVar+"?");
            spare.delete(pos,pos+theVar.length()+1);
          }
          while (spare.indexOf("<"+theVar+"?")>=0) {
            pos = spare.indexOf("<"+theVar+"?");
            spare.delete(pos,pos+theVar.length()+1);
          }
        } else {
          if (spare.indexOf("<"+theVar+"?")>=0) {
            int posRead = spare.indexOf("<"+theVar+"?");
            while ((posRead>=0) /*&& (!abort)*/) {
              if (posRead<pos) {
                int f1 = getFunction(spare,posRead);
                int f2 = getFunction(spare,pos);
                String func1 = funcList[f1].getValue("name");
                String func2 = funcList[f2].getValue("name");
                details.append("ERROR: "+stage+" "+fg.getValue("name")+", "+theVar+" read in "+func1+", written in "+func2+"\n");
                update(getGraphics());
                abort=true;
                int fgFunc1=0;
                int fgFunc2=0;
                while (fg.getTags("function")[fgFunc1]!=funcList[f1]) {
                	fgFunc1++;
                }
                while (fg.getTags("function")[fgFunc2]!=funcList[f2]) { 
                	fgFunc2++;
                }
                //swapTag(fg,"function",fgFunc1,fgFunc2);
              }
              spare.setCharAt(posRead,'+');
              posRead = spare.indexOf("<"+theVar+"?");
            }
          }
        }
      }
      spare.setCharAt(pos,'+');    
    } 
    return abort;
  }
  
  public void swapTag(XMLTag parent,String tagName, int index1, int index2) {
    XMLTag[] origKids = parent.getTags(tagName);
    int countTags = origKids.length;
    XMLTag[] newKids = new XMLTag[countTags];
    
    for (int i=0; i<countTags; i++) {
      int targetIndex=i;
      if (targetIndex==index1) targetIndex=index2;
      else if (targetIndex==index2) targetIndex=index1;
      newKids[targetIndex] = (XMLTag) origKids[i].clone();
      origKids[i].removeFromParent();
    }
    for (int i=0; i<countTags; i++) parent.addTag(newKids[i]);
  }
    
  public StringBuffer generateDefinitionString(XMLTag fg, int stageNo, String stage, XMLTag[] funcsToRun) {
    StringBuffer rwBuffer = new StringBuffer();
    rwBuffer.append("!"+stageNo+"?");                                   // Start with !stageNo?
    for (int funcNo=0; funcNo<funcsToRun.length; funcNo++) {            // For each function
      rwBuffer.append("&"+funcNo+"?");                                  // Append &funcNo?
      rwBuffer.append(funcToRW(funcsToRun[funcNo]));                    // And append the definitions for that func.
    }
    return rwBuffer;
  }
  
  
  public boolean checkModel() {
    details.setText("");
    boolean didSomething = false;
    XMLTag[] fgs = model.getTags("functionalgroup");
    for (int fgNo=0; fgNo<fgs.length; fgNo++) {                             // For each functional group
      XMLTag[] stages = fgs[fgNo].getTags("stage");                         // Get list of stages
      for (int stageNo=0; stageNo<stages.length; stageNo++) {               // For each stage
        String stage = fgs[fgNo].getTags("stage")[stageNo].getValue("name");
        XMLTag[] funcsToRun = fgs[fgNo].getTagsWhere("function","calledin",stage);     // Get function list for that stage.
        StringBuffer rwBuffer = generateDefinitionString(fgs[fgNo],stageNo, stage, funcsToRun);
        scanForDuplicateWrites(rwBuffer, fgs[fgNo],stage,funcsToRun);
        scanForNotUsed(rwBuffer, fgs[fgNo],stage,funcsToRun);
        

        /*boolean hadToFix = */scanForReadBeforeWrite(rwBuffer, fgs[fgNo], stage, funcsToRun);
        //while (hadToFix) {
//          funcsToRun = fgs[fgNo].getTagsWhere("function","calledin",stage);     // Get function list for that stage.
          //rwBuffer = generateDefinitionString(fgs[fgNo],stageNo, stage, funcsToRun);
          //hadToFix = scanForReadBeforeWrite(rwBuffer, fgs[fgNo], stage, funcsToRun);
          //didSomething=true;
        //}
      }
      XMLTag[] funcs = fgs[fgNo].getTags("function");
      for (int i=0; i<funcs.length; i++) {
        XMLTag[] calls = funcs[i].getTags("calledin");
        if (calls.length==0) {
          details.append("WARNING: Function "+funcs[i].getValue("name")+" in "+fgs[fgNo].getValue("name")+" not called in any stage\n");
        }
      }
    } 
    if (didSomething) {
      vc2.unsaved(true);
    }
    
    String text = details.getText();
    if (details.getText().length()<1) {
      details.append("Model checked ok!");
      update(getGraphics());
      return true;
    }
    else {
    	details.append("Some issues have occured!");
    	update(getGraphics());
    	return false; 
    }
  }
  
  class EventHandler implements ActionListener, WindowListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==okButton) setVisible(false);
    }

    
    public void windowActivated(WindowEvent e) {
      SyntaxChecker.this.setLocation(vc2.getX()+25,vc2.getY()+25);
      okButton.setEnabled(false);
      update(getGraphics());
      checkModel();
      okButton.setEnabled(true);
    }
    public void windowClosed(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
  }
}

