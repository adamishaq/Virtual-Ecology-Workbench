package VEW.Planktonica2;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import VEW.Common.StringTools;

public class EquationPanel extends JPanel {
  String EQ;
  boolean BRACKETS = true;
  Font normalFont = new Font("Default",Font.PLAIN,16);
  Font smallFont = normalFont.deriveFont(12.0f);
  Font boldFont = new Font("Default",Font.PLAIN,16);

  public void defaultFonts() {
    normalFont = new Font("Default",Font.PLAIN,16);
    boldFont = new Font("Default",Font.PLAIN,16);
    smallFont = normalFont.deriveFont(12.0f);
  }

  public void smallFonts() {
    normalFont = new Font("Default",Font.PLAIN,12);
    boldFont = new Font("Default",Font.PLAIN,12);
    smallFont = normalFont.deriveFont(9.0f);
  }
  
  public EquationPanel(String eq) {
    super();
    EQ = eq;
  }

  public void setEquation(String eq) {
    EQ = eq;
  }
 

  private double getEqWidth(Graphics2D g, String s) {
    if (StringTools.chomp(s,"\\minus{")) return textWidth(g, "-", normalFont)+getEqWidth(g,StringTools.spit(s,"\\minus{"));
    else if (StringTools.chomp(s,"\\abs{")) return textWidth(g, "||", normalFont)+getEqWidth(g,StringTools.spit(s,"\\abs{"));
    else if (StringTools.chomp(s,"\\varietysum{")) return textWidth(g, "varietysum()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\varietysum{"));
    else if (StringTools.chomp(s,"\\varietymul{")) return textWidth(g, "varietymul()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\varietymul{"));    
    else if (StringTools.chomp(s,"\\rnd{")) return textWidth(g, "rnd()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\rnd{"));
    else if (StringTools.chomp(s,"\\ln{")) return textWidth(g, "ln()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\ln{"));  
    else if (StringTools.chomp(s,"\\log10{")) return textWidth(g, "log()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\log10{"));      
    else if (StringTools.chomp(s,"\\asin{")) return textWidth(g, "sin()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\asin{"));  
    else if (StringTools.chomp(s,"\\acos{")) return textWidth(g, "cos()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\acos{"));  
    else if (StringTools.chomp(s,"\\atan{")) return textWidth(g, "tan()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\atan{"));  
    else if (StringTools.chomp(s,"\\sin{")) return textWidth(g, "sin()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\sin{"));      
    else if (StringTools.chomp(s,"\\cos{")) return textWidth(g, "cos()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\cos{"));
    else if (StringTools.chomp(s,"\\tan{")) return textWidth(g, "tan()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\tan{"));  
    else if (StringTools.chomp(s,"\\sqrt{")) return textWidth(g, "V", normalFont)+getEqWidth(g,StringTools.spit(s,"\\sqrt{"));  
    else if (StringTools.chomp(s,"\\integrate{")) return textWidth(g,"integrate",normalFont)+getEqWidth(g,StringTools.spit(s,"\\integrate{"));
    else if (StringTools.chomp(s,"\\exp{")) return textWidth(g, "e", normalFont)+getEqWidth(g,StringTools.spit(s,"\\exp{"));  
    else if (StringTools.chomp(s,"\\pow{")) return getBinEqWidth(g,StringTools.spit(s,"\\pow{"),"^");  
    else if (StringTools.chomp(s,"\\div{")) return getBinEqWidth(g,StringTools.spit(s,"\\div{"),"/");  
    else if (StringTools.chomp(s,"\\sub{")) return textWidth(g, "(-)", normalFont)+getBinEqWidth(g,StringTools.spit(s,"\\sub{"),"-");      
    else if (StringTools.chomp(s,"\\conditional{")) return textWidth(g, "(if then else )", normalFont)+getTriEqWidth(g,StringTools.spit(s,"\\conditional{"));
    else if (StringTools.chomp(s,"\\min{")) return textWidth(g, "min[]", normalFont)+getMultiEqWidth(g,StringTools.spit(s,"\\min{"),"min");  
    else if (StringTools.chomp(s,"\\max{")) return textWidth(g, "max[]", normalFont)+getMultiEqWidth(g,StringTools.spit(s,"\\max{"),"max");  
    else if (StringTools.chomp(s,"\\mul{")) return getMultiEqWidth(g,StringTools.spit(s,"\\mul{"),"*");  
    else if (StringTools.chomp(s,"\\add{")) return getMultiEqWidth(g,StringTools.spit(s,"\\add{"),"+");  
    else if (StringTools.chomp(s,"\\fullIrradAt{")) return textWidth(g, "fullIrradAt()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\fullIrradAt{"));      
    else if (StringTools.chomp(s,"\\salinityAt{")) return textWidth(g, "salinityAt()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\salinityAt{"));      
    else if (StringTools.chomp(s,"\\temperatureAt{")) return textWidth(g, "temperatureAt()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\temperatureAt{"));      
    else if (StringTools.chomp(s,"\\densityAt{")) return textWidth(g, "densityAt()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\densityAt{"));      
    else if (StringTools.chomp(s,"\\visIrradAt{")) return textWidth(g, "visIrradAt()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\visIrradAt{"));      
    else if (StringTools.chomp(s,"\\var{")) return SSWidth(g,StringTools.spit(s,"\\var{"),normalFont);  
    else if (StringTools.chomp(s,"\\stage{")) return SSWidth(g,StringTools.spit(s,"\\stage{"),normalFont);
    else if (StringTools.chomp(s,"\\change{")) return textWidth(g, "change()", normalFont)+getEqWidth(g,StringTools.spit(s,"\\change{"));  
    else if (StringTools.chomp(s,"\\divide{")) return textWidth(g, "divide()", boldFont)+textWidth(g, "[]", normalFont)+getEqWidth(g,StringTools.spit(s,"\\divide{"));
    else if (StringTools.chomp(s,"\\release{")) return textWidth(g, "release()", boldFont)+textWidth(g, "[]", normalFont)+getEqWidth(g,StringTools.spit(s,"\\release{"));
    else if (StringTools.chomp(s,"\\remineralise{")) return textWidth(g, "remineralise()", boldFont)+textWidth(g, "[]", normalFont)+getEqWidth(g,StringTools.spit(s,"\\remineralise{")); // LEGACY
    else if (StringTools.chomp(s,"\\ingest{")) return textWidth(g, "ingest()", boldFont)+textWidth(g, "[]", normalFont)+getEqWidth(g,StringTools.spit(s,"\\ingest{"));
    else if (StringTools.chomp(s,"\\uptake{")) return textWidth(g, "uptake()", boldFont)+textWidth(g, "[]", normalFont)+getEqWidth(g,StringTools.spit(s,"\\uptake{"));
    else if (StringTools.chomp(s,"\\pchange{")) return textWidth(g, "pchange()", boldFont)+textWidth(g, "[]", normalFont)+getEqWidth(g,StringTools.spit(s,"\\pchange{"));
    else if (StringTools.chomp(s,"\\varhist{")) {
      s = StringTools.spit(s,"\\varhist{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      lhs = StringTools.spit(lhs,"\\var{");
      return textWidth(g,lhs+"_",normalFont)+getEqWidth(g,rhs);
    }
    else if (StringTools.chomp(s,"\\val{")) {
      s = StringTools.spit(s,"\\val{");
      s = StringTools.LHS(s,StringTools.getUnNested(s,','));
      s = StringTools.spit(s,"\\sival{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      if (!(rhs.equals("0"))) return SSWidth(g,lhs+" x 10^{"+rhs+"}",normalFont);
      else return SSWidth(g,lhs,normalFont);
    }
    else if (StringTools.chomp(s,"\\assign")) {
      s = StringTools.spit(s,"\\assign{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return getEqWidth(g,lhs)+SSWidth(g," = ",normalFont)+getEqWidth(g,rhs);
    }
    else if (StringTools.chomp(s,"\\assigndiff")) {
      s = StringTools.spit(s,"\\assigndiff{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      lhs = StringTools.spit(lhs,"\\var{");
      return getEqWidth(g,lhs)+SSWidth(g,"d = ",normalFont)+getEqWidth(g,rhs);

    }
    else if (StringTools.chomp(s,"\\ifthen")) {
      s = StringTools.spit(s,"\\ifthen{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      lhs = StringTools.spit(lhs,"\\var{");
      return getEqWidth(g,lhs)+SSWidth(g,"if then ",normalFont)+getEqWidth(g,rhs);
    }

    else return 0;
  }


  private double SSWidth(Graphics2D g, String s,Font f) {
    String subscript = "";
    String superscript = "";
    s = s.replace('$','_');
    if (s.indexOf("_")>=0) {
      subscript = s.substring(s.indexOf("_")+1);
      s = s.substring(0,s.indexOf("_"));
      if (subscript.indexOf("^")>=0) {
        superscript = subscript.substring(subscript.indexOf("^")+1);
        subscript = subscript.substring(0,subscript.indexOf("^"));
      }
    }
    if (s.indexOf("^")>=0) {
      superscript = s.substring(s.indexOf("^")+1);
      s = s.substring(0,s.indexOf("^"));
    }
    if (subscript.indexOf("{")>=0) subscript = StringTools.spit(subscript,"{");
    if (superscript.indexOf("{")>=0) superscript = StringTools.spit(superscript,"{");
    double width=textWidth(g, s, f);
    if (subscript.length()>0) {
      width+=textWidth(g,subscript,smallFont);
    }
    if (superscript.length()>0) {
      width+=textWidth(g,superscript,smallFont);
    }
    return width;
  }

  private int textHeight(Graphics2D g, String s, Font f) {
    //FontRenderContext frc = g.getFontRenderContext();
    //Rectangle2D r = f.getStringBounds(s,frc);
    return 18; // (int) r.getHeight();
  }

  private int textWidth(Graphics2D g, String s, Font f) {
    FontRenderContext frc = g.getFontRenderContext();
    Rectangle2D r = f.getStringBounds(s,frc);
    return (int) r.getWidth();
  }

  private double SSHeight(Graphics2D g, String s,Font f) {
    String subscript = "";
    String superscript = "";
    s = s.replace('$','_');
    if (s.indexOf("_")>=0) {
      subscript = s.substring(s.indexOf("_")+1);
      s = s.substring(0,s.indexOf("_"));
      if (subscript.indexOf("^")>=0) {
        superscript = subscript.substring(subscript.indexOf("^")+1);
        subscript = subscript.substring(0,subscript.indexOf("^"));
      }
    }
    if (s.indexOf("^")>=0) {
      superscript = s.substring(s.indexOf("^")+1);
      s = s.substring(0,s.indexOf("^"));
    }
    if (subscript.indexOf("{")>=0) subscript = StringTools.spit(subscript,"{");
    if (superscript.indexOf("{")>=0) superscript = StringTools.spit(superscript,"{");
    double height=textHeight(g,s,f);
    if (subscript.length()>0) {
      int newSize = (int) Math.max(6,f.getSize()/1.2);
      height+=(newSize/3);
    }
    if (superscript.length()>0) {
      int newSize = (int) Math.max(6,f.getSize()/1.2);
      height+=(newSize/3);
    }
    return 18; //height;
  }
  
  private int plotSS(Graphics2D g, int x, int y, String s) {
    String subscript = "";
    String superscript = "";
    s = s.replace('$','_');
    if (s.indexOf("_")>=0) {
      subscript = s.substring(s.indexOf("_")+1);
      s = s.substring(0,s.indexOf("_"));
      if (subscript.indexOf("^")>=0) {
        superscript = subscript.substring(subscript.indexOf("^")+1);
        subscript = subscript.substring(0,subscript.indexOf("^"));
      }
    }
    if (s.indexOf("^")>=0) {
      superscript = s.substring(s.indexOf("^")+1);
      s = s.substring(0,s.indexOf("^"));
    }
    if (subscript.indexOf("{")>=0) subscript = StringTools.spit(subscript,"{");
    if (superscript.indexOf("{")>=0) superscript = StringTools.spit(superscript,"{");
    int newSize = 0;
    int dropBy = 0;
    int hoistBy = 0;
    if (subscript.length()>0) {
      newSize = (int) Math.max(6,normalFont.getSize()/1.2);
      dropBy = (newSize/3);
    }
    if (superscript.length()>0) {
      newSize = (int) Math.max(6,normalFont.getSize()/1.2);
      hoistBy = (newSize/3);
    }
    int mainPos = y+(textHeight(g,s,normalFont)/2)+((hoistBy/2)-(dropBy/2));
    g.drawString(s,x,mainPos);
    x+=textWidth(g, s, normalFont);
    if (subscript.length()>0) {
      //Font nf = new Font(normalFont.getName(),normalFont.getStyle(),newSize);
      //g.setFont(nf);
      g.setFont(smallFont);
      g.drawString(subscript,x,mainPos+dropBy);
      //x+=textWidth(g,subscript,nf);
      x+=textWidth(g,subscript,smallFont);
    }
    if (superscript.length()>0) {
      //Font nf = new Font(normalFont.getName(),normalFont.getStyle(),newSize);
      //g.setFont(nf);
      //g.drawString(superscript,x,mainPos-(textHeight(g,superscript,nf)/2));
      //x+=textWidth(g,superscript,nf);
      g.setFont(smallFont);
      g.drawString(superscript,x,mainPos-(textHeight(g,superscript,smallFont)/2));
      x+=textWidth(g,superscript,smallFont);

    }
    g.setFont(normalFont);
    return x+1;
  }

  /*private String GetSival(String s) {
    s = StringTools.spit(StringTools.LHS(s,StringTools.getUnNested(s,',')),"\\sival{");
    double val = 0.0;
    if (!(s.equals(""))) {
      String vTemp = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String eTemp = StringTools.RHS(s,StringTools.getUnNested(s,','));
      try { 
        double expon = Double.parseDouble(eTemp);
        val = Double.parseDouble(vTemp)*Math.pow(10,expon);
      } catch (NumberFormatException n) { val = 0.0; }
    }
    return ""+val;
  }
*/
  private double getBinEqWidth(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    if (op.equals("/")) return Math.max(getEqWidth(g,lhs),getEqWidth(g,rhs));
    else if (op.equals("-")) return getEqWidth(g,lhs)+getEqWidth(g,rhs)+textWidth(g, "+", normalFont);
    else if (op.equals("^")) return getEqWidth(g,lhs)+getEqWidth(g,rhs);
    else return 0.0;
  }

  private double getTriEqWidth(Graphics2D g, String s) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    String mid = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
    rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
    return getBoolEqWidth(g,lhs)+getEqWidth(g,rhs)+getEqWidth(g,mid)+textWidth(g, "(if then else )", normalFont);
  }

  private double getMultiEqWidth(Graphics2D g,String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    String preop = "";
    String midop = "";
    if (op.equals("max")) { preop="max[]"; midop=","; }
    else if (op.equals("min")) { preop="min[]"; midop=","; }
    else if (op.equals("*")) { preop=""; midop="."; }
    else if (op.equals("+")) { preop=""; midop="+"; }
    if (rhs.equals("")) midop="";
    double width = getEqWidth(g,lhs)+textWidth(g, midop, normalFont)+textWidth(g, preop, normalFont);
    while (StringTools.getUnNested(rhs,',')>=0) {
      lhs = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      width += getEqWidth(g,lhs)+textWidth(g, midop, normalFont);
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
    }
    width += getEqWidth(g,rhs)+textWidth(g, midop, normalFont);
    return width;
  }
  
  private double getBoolEqWidth(Graphics2D g, String s) {
    if (StringTools.chomp(s,"\\not{")) return textWidth(g, "!", normalFont) + getBoolEqWidth(g,StringTools.spit(s,"\\not{"));
    else if (StringTools.chomp(s,"\\greater{")) return getBinBoolEqWidth(g,StringTools.spit(s,"\\greater{"),">");
    else if (StringTools.chomp(s,"\\greaterequal{")) return getBinBoolEqWidth(g,StringTools.spit(s,"\\greaterequal{"),"\u2265");
    else if (StringTools.chomp(s,"\\equal{")) return getBinBoolEqWidth(g,StringTools.spit(s,"\\equal{"),"=");
    else if (StringTools.chomp(s,"\\less{")) return getBinBoolEqWidth(g,StringTools.spit(s,"\\less{"),">");
    else if (StringTools.chomp(s,"\\lessequal{")) return getBinBoolEqWidth(g,StringTools.spit(s,"\\lessequal{"),"\u2264");
    else if (StringTools.chomp(s,"\\neq{")) return getBinBoolEqWidth(g,StringTools.spit(s,"\\neq{"),"=");
    else if (StringTools.chomp(s,"\\and{")) return getMultiBoolEqWidth(g,StringTools.spit(s,"\\and{")," and ");
    else if (StringTools.chomp(s,"\\or{")) return getMultiBoolEqWidth(g,StringTools.spit(s,"\\or{")," or ");
    else if (StringTools.chomp(s,"\\someVariety{")) return textWidth(g, "someVariety", normalFont) + getBoolEqWidth(g,StringTools.spit(s,"\\someVariety{"));
    else if (StringTools.chomp(s,"\\allVariety{")) return textWidth(g, "allVariety", normalFont) + getBoolEqWidth(g,StringTools.spit(s,"\\allVariety{"));
    else if (StringTools.chomp(s,"\\noVariety{")) return textWidth(g, "noVariety", normalFont) + getBoolEqWidth(g,StringTools.spit(s,"\\noVariety{"));

    else return 0;
  }

  private double getBinBoolEqWidth(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    return textWidth(g, op, normalFont)+getBoolEqWidth(g,lhs)+getBoolEqWidth(g,rhs);
  }

  private double getMultiBoolEqWidth(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    double width = getEqWidth(g,lhs);
    if (!rhs.equals("")) width+= textWidth(g, op, normalFont);
    while (StringTools.getUnNested(rhs,',')>=0) {
      lhs = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      width += getEqWidth(g,lhs)+textWidth(g, op, normalFont);
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
    }
    width += getEqWidth(g,rhs)+textWidth(g, op, normalFont);
    return width;
  }

  private double getEqTop(Graphics2D g, String s) {
    if (StringTools.chomp(s,"\\minus{")) return getEqTop(g,StringTools.spit(s,"\\minus{"));
    else if (StringTools.chomp(s,"\\integrate{")) return getEqTop(g,StringTools.spit(s,"\\integrate{"));
    else if (StringTools.chomp(s,"\\abs{")) return getEqTop(g,StringTools.spit(s,"\\abs{"));
    else if (StringTools.chomp(s,"\\rnd{")) return getEqTop(g,StringTools.spit(s,"\\rnd{"));
    else if (StringTools.chomp(s,"\\ln{")) return getEqTop(g,StringTools.spit(s,"\\ln{"));  
    else if (StringTools.chomp(s,"\\log10{")) return getEqTop(g,StringTools.spit(s,"\\log10{"));  
    else if (StringTools.chomp(s,"\\asin{")) return getEqTop(g,StringTools.spit(s,"\\asin{"));  
    else if (StringTools.chomp(s,"\\acos{")) return getEqTop(g,StringTools.spit(s,"\\acos{"));  
    else if (StringTools.chomp(s,"\\atan{")) return getEqTop(g,StringTools.spit(s,"\\atan{"));  
    else if (StringTools.chomp(s,"\\varietysum{")) return getEqTop(g,StringTools.spit(s,"\\varietysum{"));  
    else if (StringTools.chomp(s,"\\varietymul{")) return getEqTop(g,StringTools.spit(s,"\\varietymul{"));  
    else if (StringTools.chomp(s,"\\visIrradAt{")) return getEqTop(g,StringTools.spit(s,"\\visIrradAt{"));
    else if (StringTools.chomp(s,"\\fullIrradAt{")) return getEqTop(g,StringTools.spit(s,"\\fullIrradAt{"));
    else if (StringTools.chomp(s,"\\salinityAt{")) return getEqTop(g,StringTools.spit(s,"\\salinityAt{"));
    else if (StringTools.chomp(s,"\\temperatureAt{")) return getEqTop(g,StringTools.spit(s,"\\temperatureAt{"));
    else if (StringTools.chomp(s,"\\densityAt{")) return getEqTop(g,StringTools.spit(s,"\\densityAt{"));
    else if (StringTools.chomp(s,"\\sin{")) return getEqTop(g,StringTools.spit(s,"\\sin{"));      
    else if (StringTools.chomp(s,"\\cos{")) return getEqTop(g,StringTools.spit(s,"\\cos{"));
    else if (StringTools.chomp(s,"\\tan{")) return getEqTop(g,StringTools.spit(s,"\\tan{"));  
    else if (StringTools.chomp(s,"\\sqrt{")) return getEqTop(g,StringTools.spit(s,"\\sqrt{"));  
    else if (StringTools.chomp(s,"\\exp{")) return getEqTop(g,StringTools.spit(s,"\\exp{"));
    else if (StringTools.chomp(s,"\\pow{")) return getBinEqTop(g,StringTools.spit(s,"\\pow{"),"^");  
    else if (StringTools.chomp(s,"\\div{")) return getBinEqTop(g,StringTools.spit(s,"\\div{"),"/");  
    else if (StringTools.chomp(s,"\\sub{")) return getBinEqTop(g,StringTools.spit(s,"\\sub{"),"-");      
    else if (StringTools.chomp(s,"\\conditional{")) return getTriEqTop(g,StringTools.spit(s,"\\conditional{"));
    else if (StringTools.chomp(s,"\\min{")) return getMultiEqTop(g,StringTools.spit(s,"\\min{"),"min");  
    else if (StringTools.chomp(s,"\\max{")) return getMultiEqTop(g,StringTools.spit(s,"\\max{"),"max");  
    else if (StringTools.chomp(s,"\\mul{")) return getMultiEqTop(g,StringTools.spit(s,"\\mul{"),"*");  
    else if (StringTools.chomp(s,"\\add{")) return getMultiEqTop(g,StringTools.spit(s,"\\add{"),"+");  
    else if (StringTools.chomp(s,"\\stage{")) return 0;  
    else if (StringTools.chomp(s,"\\var{")) return 0;  
    else if (StringTools.chomp(s,"\\val{")) return 0;
    else if (StringTools.chomp(s,"\\varhist{")) {
      s = StringTools.spit(s,"\\varhist{");
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return getEqTop(g,rhs);
    }
    else if (StringTools.chomp(s,"\\not{")) return getEqTop(g,StringTools.spit(s,"\\not{"));
    else if (StringTools.chomp(s,"\\greater{")) return getBinBoolEqTop(g,StringTools.spit(s,"\\greater{"),">");
    else if (StringTools.chomp(s,"\\greaterequal{")) return getBinBoolEqTop(g,StringTools.spit(s,"\\greaterequal{"),">");
    else if (StringTools.chomp(s,"\\equal{")) return getBinBoolEqTop(g,StringTools.spit(s,"\\equal{"),"=");
    else if (StringTools.chomp(s,"\\less{")) return getBinBoolEqTop(g,StringTools.spit(s,"\\less{"),">");
    else if (StringTools.chomp(s,"\\lessequal{")) return getBinBoolEqTop(g,StringTools.spit(s,"\\lessequal{"),"<");
    else if (StringTools.chomp(s,"\\neq{")) return getBinBoolEqTop(g,StringTools.spit(s,"\\neq{"),"=");
    else if (StringTools.chomp(s,"\\and{")) return getMultiBoolEqTop(g,StringTools.spit(s,"\\and{")," and ");
    else if (StringTools.chomp(s,"\\or{")) return getMultiBoolEqTop(g,StringTools.spit(s,"\\or{")," or ");
    else if (StringTools.chomp(s,"\\someVariety{")) return getEqTop(g,StringTools.spit(s,"\\someVariety{"));
    else if (StringTools.chomp(s,"\\noVariety{")) return getEqTop(g,StringTools.spit(s,"\\noVariety{"));
    else if (StringTools.chomp(s,"\\allVariety{")) return getEqTop(g,StringTools.spit(s,"\\allVariety{"));
    else if (StringTools.chomp(s,"\\assign{")) {
      s = StringTools.spit(s,"\\assign{");
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return getEqTop(g,rhs);
    } else if (StringTools.chomp(s,"\\set{")) {
      s = StringTools.spit(s,"\\set{");
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return getEqTop(g,rhs);
    } else if (StringTools.chomp(s,"\\assigndiff{")) {
      s = StringTools.spit(s,"\\assigndiff{");
      String lhs = StringTools.spit(StringTools.LHS(s,StringTools.getUnNested(s,',')),"\\var{");
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return Math.min(getEqTop(g,"\\div{\\var{d"+lhs+"},\\var{dt}}"),getEqTop(g,rhs));
    } else if (StringTools.chomp(s,"\\ifthen{")) {
      s = StringTools.spit(s,"\\ifthen{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return Math.min(getEqTop(g,lhs),getEqTop(g,rhs));
    } else if (StringTools.chomp(s,"\\create{")) { return 0;
    } else if (StringTools.chomp(s,"\\change{")) { return 0;
    } else if (StringTools.chomp(s,"\\pchange{")) {
      s = StringTools.spit(s,"\\pchange{");
      return getEqTop(g,StringTools.RHS(s,StringTools.getUnNested(s,',')));
    } else if (StringTools.chomp(s,"\\divide")) {
      return getEqTop(g,StringTools.spit(s,"\\divide{"));
    } else if (StringTools.chomp(s,"\\release{")) {
      s = StringTools.spit(s,"\\release{");
      return getEqTop(g,StringTools.RHS(s,StringTools.getUnNested(s,',')));
    } else if (StringTools.chomp(s,"\\remineralise{")) {
      s = StringTools.spit(s,"\\remineralise{");
      return getEqTop(g,StringTools.RHS(s,StringTools.getUnNested(s,',')));
   
    } else if (StringTools.chomp(s,"\\uptake{")) {
      s = StringTools.spit(s,"\\uptake{");
      return getEqTop(g,StringTools.RHS(s,StringTools.getUnNested(s,',')));
    } else if (StringTools.chomp(s,"\\ingest{")) {
      s = StringTools.spit(s,"\\ingest{");
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      String mid = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
      return Math.min(getEqTop(g,mid),getEqTop(g,rhs));
    }
    else {
      //System.out.println("EqPanel error: "+s+" not found in getEqTop");
      return 0;
    }
  }

  
  private double getBinEqTop(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    //String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    if (op.equals("/")) {
      return -(2+getEqHeight(g,lhs));
    } else return 0;
  }

  private double getTriEqTop(Graphics2D g, String s) {
   // String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    String mid = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
    rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
    return Math.min(getEqTop(g,rhs),getEqTop(g,mid));
  }

  private double getMultiEqTop(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    double top = getEqTop(g,lhs);
    while (StringTools.getUnNested(rhs,',')>=0) {
      lhs = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      top = Math.min(top,getEqTop(g,lhs));
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
    }
    top = Math.min(top,getEqTop(g,rhs));
    return top;
  }

  private double getBinBoolEqTop(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    return Math.min(getEqTop(g,lhs),getEqTop(g,rhs));
  }

  private double getMultiBoolEqTop(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    double top = getEqTop(g,lhs);
    while (StringTools.getUnNested(rhs,',')>=0) {
      lhs = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      top = Math.max(top,getEqTop(g,lhs));
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
    }
    top = Math.min(top,getEqHeight(g,rhs));
    return top;
  }

  private double getEqHeight(Graphics2D g, String s) {
    if (StringTools.chomp(s,"\\minus{")) return getEqHeight(g,StringTools.spit(s,"\\minus{"));
    else if (StringTools.chomp(s,"\\integrate{")) return getEqHeight(g,StringTools.spit(s,"\\integrate{"));
    else if (StringTools.chomp(s,"\\abs{")) return getEqHeight(g,StringTools.spit(s,"\\abs{"));
    else if (StringTools.chomp(s,"\\rnd{")) return getEqHeight(g,StringTools.spit(s,"\\rnd{"));
    else if (StringTools.chomp(s,"\\ln{")) return getEqHeight(g,StringTools.spit(s,"\\ln{"));  
    else if (StringTools.chomp(s,"\\log10{")) return getEqHeight(g,StringTools.spit(s,"\\log10{"));  
    else if (StringTools.chomp(s,"\\asin{")) return getEqHeight(g,StringTools.spit(s,"\\asin{"));  
    else if (StringTools.chomp(s,"\\acos{")) return getEqHeight(g,StringTools.spit(s,"\\acos{"));  
    else if (StringTools.chomp(s,"\\atan{")) return getEqHeight(g,StringTools.spit(s,"\\atan{"));  
    else if (StringTools.chomp(s,"\\varietysum{")) return getEqHeight(g,StringTools.spit(s,"\\varietysum{"));  
    else if (StringTools.chomp(s,"\\varietymul{")) return getEqHeight(g,StringTools.spit(s,"\\varietymul{"));  
    else if (StringTools.chomp(s,"\\visIrradAt{")) return getEqHeight(g,StringTools.spit(s,"\\visIrradAt{"));
    else if (StringTools.chomp(s,"\\fullIrradAt{")) return getEqHeight(g,StringTools.spit(s,"\\fullIrradAt{"));
    else if (StringTools.chomp(s,"\\salinityAt{")) return getEqHeight(g,StringTools.spit(s,"\\salinityAt{"));
    else if (StringTools.chomp(s,"\\temperatureAt{")) return getEqHeight(g,StringTools.spit(s,"\\temperatureAt{"));
    else if (StringTools.chomp(s,"\\densityAt{")) return getEqHeight(g,StringTools.spit(s,"\\densityAt{"));
    else if (StringTools.chomp(s,"\\sin{")) return getEqHeight(g,StringTools.spit(s,"\\sin{"));      
    else if (StringTools.chomp(s,"\\cos{")) return getEqHeight(g,StringTools.spit(s,"\\cos{"));
    else if (StringTools.chomp(s,"\\tan{")) return getEqHeight(g,StringTools.spit(s,"\\tan{"));  
    else if (StringTools.chomp(s,"\\sqrt{")) return getEqHeight(g,StringTools.spit(s,"\\sqrt{"));  
    else if (StringTools.chomp(s,"\\exp{")) return getEqHeight(g,StringTools.spit(s,"\\exp{"));
    else if (StringTools.chomp(s,"\\pow{")) return getBinEqHeight(g,StringTools.spit(s,"\\pow{"),"^");  
    else if (StringTools.chomp(s,"\\div{")) return getBinEqHeight(g,StringTools.spit(s,"\\div{"),"/");  
    else if (StringTools.chomp(s,"\\sub{")) return getBinEqHeight(g,StringTools.spit(s,"\\sub{"),"-");      
    else if (StringTools.chomp(s,"\\conditional{")) return getTriEqHeight(g,StringTools.spit(s,"\\conditional{"));
    else if (StringTools.chomp(s,"\\min{")) return getMultiEqHeight(g,StringTools.spit(s,"\\min{"),"min");  
    else if (StringTools.chomp(s,"\\max{")) return getMultiEqHeight(g,StringTools.spit(s,"\\max{"),"max");  
    else if (StringTools.chomp(s,"\\mul{")) return getMultiEqHeight(g,StringTools.spit(s,"\\mul{"),"*");  
    else if (StringTools.chomp(s,"\\add{")) return getMultiEqHeight(g,StringTools.spit(s,"\\add{"),"+");  
    else if (StringTools.chomp(s,"\\stage{")) return SSHeight(g,StringTools.spit(s,"\\stage{"),normalFont);  
    else if (StringTools.chomp(s,"\\var{")) return SSHeight(g,StringTools.spit(s,"\\var{"),normalFont);  
    else if (StringTools.chomp(s,"\\varhist{")) {
      s = StringTools.spit(s,"\\varhist{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      if (lhs.equals("\\? Var{}")) lhs = "\\var{?}";
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      lhs = StringTools.spit(lhs,"\\var{");
      return SSHeight(g,lhs+"_",normalFont)+getEqHeight(g,rhs);

    } else if (StringTools.chomp(s,"\\val{")) {
      s = StringTools.spit(s,"\\val{");
      s = StringTools.LHS(s,StringTools.getUnNested(s,','));
      s = StringTools.spit(s,"\\sival{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      if (!(rhs.equals("0"))) return SSHeight(g,lhs+"x10^{"+rhs+"}",normalFont);
      else return SSHeight(g,lhs,normalFont);
    }
    else if (StringTools.chomp(s,"\\not{")) return getEqHeight(g,StringTools.spit(s,"\\not{"));
    else if (StringTools.chomp(s,"\\greater{")) return getBinBoolEqHeight(g,StringTools.spit(s,"\\greater{"),">");
    else if (StringTools.chomp(s,"\\greaterequal{")) return getBinBoolEqHeight(g,StringTools.spit(s,"\\greaterequal{"),">");
    else if (StringTools.chomp(s,"\\equal{")) return getBinBoolEqHeight(g,StringTools.spit(s,"\\equal{"),"=");
    else if (StringTools.chomp(s,"\\less{")) return getBinBoolEqHeight(g,StringTools.spit(s,"\\less{"),">");
    else if (StringTools.chomp(s,"\\lessequal{")) return getBinBoolEqHeight(g,StringTools.spit(s,"\\lessequal{"),"<");
    else if (StringTools.chomp(s,"\\neq{")) return getBinBoolEqHeight(g,StringTools.spit(s,"\\neq{"),"=");
    else if (StringTools.chomp(s,"\\and{")) return getMultiBoolEqHeight(g,StringTools.spit(s,"\\and{")," and ");
    else if (StringTools.chomp(s,"\\or{")) return getMultiBoolEqHeight(g,StringTools.spit(s,"\\or{")," or ");
    else if (StringTools.chomp(s,"\\someVariety{")) return getEqHeight(g,StringTools.spit(s,"\\someVariety{"));
    else if (StringTools.chomp(s,"\\noVariety{")) return getEqHeight(g,StringTools.spit(s,"\\noVariety{"));
    else if (StringTools.chomp(s,"\\allVariety{")) return getEqHeight(g,StringTools.spit(s,"\\allVariety{"));
    else if (StringTools.chomp(s,"\\assign{")) {
      s = StringTools.spit(s,"\\assign{");
      String lhs = StringTools.spit(StringTools.LHS(s,StringTools.getUnNested(s,',')),"\\var{");
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return Math.max(getEqHeight(g,rhs),SSHeight(g,lhs,normalFont));
    } else if (StringTools.chomp(s,"\\set{")) {
      s = StringTools.spit(s,"\\set{");
      String lhs = StringTools.spit(StringTools.LHS(s,StringTools.getUnNested(s,',')),"\\var{");
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return Math.max(getEqHeight(g,rhs),SSHeight(g,lhs,normalFont));
    } else if (StringTools.chomp(s,"\\assigndiff{")) {
      s = StringTools.spit(s,"\\assigndiff{");
      String lhs = StringTools.spit(StringTools.LHS(s,StringTools.getUnNested(s,',')),"\\var{");
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return Math.max(getEqHeight(g,"\\div{\\var{d"+lhs+"},\\var{dt}}"),getEqHeight(g,rhs));
    } else if (StringTools.chomp(s,"\\ifthen{")) {
      s = StringTools.spit(s,"\\ifthen{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return Math.max(getEqHeight(g,lhs),getEqHeight(g,rhs));
    } else if (StringTools.chomp(s,"\\create{")) {
      String param = StringTools.spit(s,"\\create{");
      double max = getEqHeight(g,StringTools.LHS(param,StringTools.getUnNested(param,',')));
      param = StringTools.RHS(param,StringTools.getUnNested(param,','));

      while (param.length()>2) {
        double hei = getEqHeight(g,StringTools.LHS(param,StringTools.getUnNested(param,',')));
        max = max + hei;
        if (param.indexOf(',')<0) param = "";
        param = StringTools.RHS(param,StringTools.getUnNested(param,','));
      }
      return max;
    } else if (StringTools.chomp(s,"\\change{")) {
      return SSHeight(g,"change()",normalFont);
    } else if (StringTools.chomp(s,"\\pchange{")) {
      s = StringTools.spit(s,"\\pchange{");
    //  String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return getEqHeight(g,rhs);
    } else if (StringTools.chomp(s,"\\divide")) {
      return getEqHeight(g,StringTools.spit(s,"\\divide{"));
    } else if (StringTools.chomp(s,"\\release{")) {
      s = StringTools.spit(s,"\\release{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return Math.max(getEqHeight(g,lhs),getEqHeight(g,rhs));
    } else if (StringTools.chomp(s,"\\remineralise{")) {
      s = StringTools.spit(s,"\\remineralise{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return Math.max(getEqHeight(g,lhs),getEqHeight(g,rhs));
    } else if (StringTools.chomp(s,"\\uptake{")) {
      s = StringTools.spit(s,"\\uptake{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return Math.max(getEqHeight(g,lhs),getEqHeight(g,rhs));
    } else if (StringTools.chomp(s,"\\ingest{")) {
      s = StringTools.spit(s,"\\ingest{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      String mid = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
      return Math.max(Math.max(getEqHeight(g,lhs),getEqHeight(g,mid)),getEqHeight(g,rhs));
    }
    else {
      //System.out.println("EqPanel error: "+s+" not found in getEqHeight");
      return 18;
    }
  }

  private double getBinEqHeight(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    if (op.equals("/")) {
      return getEqHeight(g,lhs)+4+getEqHeight(g,rhs);
    } else if (op.equals("-")) return Math.max(getEqHeight(g,lhs),getEqHeight(g,rhs));
    else if (op.equals("^")) return getEqHeight(g,lhs);
    else return 0.0;
  }

  private double getTriEqHeight(Graphics2D g, String s) {
    //String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    String mid = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
    rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
    return Math.max(getEqHeight(g,rhs),getEqHeight(g,mid));
  }

  private double getMultiEqHeight(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    double height = getEqHeight(g,lhs);
    while (StringTools.getUnNested(rhs,',')>=0) {
      lhs = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      height = Math.max(height,getEqHeight(g,lhs));
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
    }
    height = Math.max(height,getEqHeight(g,rhs));
    return height;
  }

  private double getBinBoolEqHeight(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    return Math.max(getEqHeight(g,lhs),getEqHeight(g,rhs));
  }

  private double getMultiBoolEqHeight(Graphics2D g, String s, String op) {
    String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
    String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
    double height = getEqHeight(g,lhs);
    while (StringTools.getUnNested(rhs,',')>=0) {
      lhs = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      height = Math.max(height,getEqHeight(g,lhs));
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
    }
    height = Math.max(height,getEqHeight(g,rhs));
    return height;
  }

  private double getStatWidth(Graphics2D g, String s) {
    if (StringTools.chomp(s,"\\assign{")) {
      s = StringTools.spit(s,"\\assign{");
      double lhs = SSWidth(g,StringTools.spit(StringTools.LHS(s,StringTools.getUnNested(s,',')),"\\var{"),normalFont);
      double rhs = getEqWidth(g,StringTools.RHS(s,StringTools.getUnNested(s,',')));
      return lhs+rhs;
    } if (StringTools.chomp(s,"\\set{")) {
      s = StringTools.spit(s,"\\set{");
      double lhs = SSWidth(g,StringTools.spit(StringTools.LHS(s,StringTools.getUnNested(s,',')),"\\var{"),normalFont);
      double rhs = getEqWidth(g,StringTools.RHS(s,StringTools.getUnNested(s,',')));
      return lhs+rhs;

    } else if (StringTools.chomp(s,"\\assigndiff{")) {
      s = StringTools.spit(s,"\\assigndiff{");
      String lhs = StringTools.spit(StringTools.LHS(s,StringTools.getUnNested(s,',')),"\\var{");
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return getEqWidth(g,"\\div{\\var{d"+lhs+"},\\var{dt}}")+textWidth(g, " = ", normalFont)+getEqWidth(g,rhs);
    } else if (StringTools.chomp(s,"ifthen{")) {
      s = StringTools.spit(s,"\\ifthen{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      return getBoolEqWidth(g,lhs)+getFuncWidth(g,rhs)+textWidth(g, "if, ", normalFont);
    } else return 0;
  }

  private double getFuncWidth(Graphics2D g, String s) {
    if (StringTools.chomp(s,"\\change{")) return textWidth(g, "change", normalFont);
    else if (StringTools.chomp(s,"\\create{")) {
      String param = StringTools.spit(s,"\\create{");
      double max = textWidth(g, "create", normalFont)+getStatWidth(g,StringTools.LHS(param,StringTools.getUnNested(param,',')));
      param = StringTools.RHS(param,StringTools.getUnNested(param,','));
      while (param.length()>2) {
        double wid = getFuncWidth(g,StringTools.LHS(param,StringTools.getUnNested(param,',')));
        if (max>wid) max = wid;
        if (param.indexOf(',')<=0) param = "";
        param = StringTools.RHS(param,StringTools.getUnNested(param,','));
      }
      return max;
    }
      
    else if (StringTools.chomp(s,"\\pchange{")) return textWidth(g, "pchange", normalFont);
    else if (StringTools.chomp(s,"\\divide{")) return textWidth(g, "divide", normalFont);
    else if (StringTools.chomp(s,"\\ingest{")) return textWidth(g, "ingest", normalFont);
    else if (StringTools.chomp(s,"\\release{")) return textWidth(g, "release", normalFont);
    else if (StringTools.chomp(s,"\\remineralise{")) return textWidth(g, "remineralise", normalFont);    
    else if (StringTools.chomp(s,"\\uptake{")) return textWidth(g, "uptake", normalFont);
    else if (StringTools.chomp(s,"\\assign{")) return getStatWidth(g,s);
    else if (StringTools.chomp(s,"\\assigndiff{")) return getStatWidth(g,s);
    else return 0;
  }

  public int writeEq(Graphics2D g, int x, int y, String s) {
    if (StringTools.chomp(s,"\\assigndiff{")) {
      s = StringTools.spit(s,"\\assigndiff{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      if (StringTools.chomp(s,"\\var{")) lhs = StringTools.spit(s,"\\var{"); else lhs="";
      lhs = "\\div{\\var{d"+lhs+"},\\var{dt}}";
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      int rHeight = (int) getEqHeight(g,rhs);
      int lHeight = (int) getEqHeight(g,lhs);
      int top = (int) Math.min(getEqTop(g,lhs),getEqTop(g,rhs));
      x = writeEq(g,x,y-top,lhs);
      g.setFont(new Font(normalFont.getName(),Font.BOLD,normalFont.getSize()));
      x = plotSS(g,x,y-top," = ");
      g.setFont(normalFont);
      x = writeEq(g,x,y-top,rhs);
      y+=Math.max(rHeight,lHeight)+10;
      x=0;
    } else if (StringTools.chomp(s,"\\ifthen{")) {
      s = StringTools.spit(s,"\\ifthen{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      int rHeight = (int) getEqHeight(g,rhs);
      int lHeight = (int) getEqHeight(g,lhs);
      int top = (int) Math.min(getEqTop(g,lhs),getEqTop(g,rhs));
      x = plotSS(g,x,y-top,"if ");
      x = writeEq(g,x,y-top,lhs);
      g.setFont(boldFont);
      x = plotSS(g,x,y-top,", ");
      g.setFont(normalFont);
      x = writeEq(g,x,y-top,rhs);
      y+=Math.max(rHeight,lHeight)+10;
      x=0;
    } else if (StringTools.chomp(s,"\\assign{")) {
      s = StringTools.spit(s,"\\assign{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      if (StringTools.chomp(s,"\\var{")) lhs = StringTools.spit(s,"\\var{"); else lhs="";
      int top = (int) getEqTop(g,rhs);
      x = plotSS(g,x,y-top,lhs);
      x = plotSS(g,x,y-top,"=");
      x = writeEq(g,x,y-top,rhs);

    } else if (StringTools.chomp(s,"\\set{")) {
      s = StringTools.spit(s,"\\set{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      if (StringTools.chomp(s,"\\var{")) lhs = StringTools.spit(s,"\\var{"); else lhs="";
      x = plotSS(g,x,y,lhs);
      x = plotSS(g,x,y,"=");
      x = writeEq(g,x,y,rhs);
    
    } else if (StringTools.chomp(s,"\\stage{")) {
      s = StringTools.spit(s,"\\stage{");
      x = plotSS(g,x,y,s);    
    } else if (StringTools.chomp(s,"\\minus{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"-");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\minus{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\abs{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"|");
      g.setFont(normalFont);
      x = writeEq(g,x,y,StringTools.spit(s,"\\abs{"));
      g.setFont(boldFont);
      x = plotSS(g,x,y,"|");
      g.setFont(normalFont);
    } else if (StringTools.chomp(s,"\\varietymul{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"varietymul)");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\varietymul{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\varietysum{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"varietysum");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\varietysum{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\rnd{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"rnd");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\rnd{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\integrate{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"integrate");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\integrate{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\ln{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"ln");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\ln{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\log10{")) {
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,"log${10}");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"("); 
      x = writeEq(g,x,y,StringTools.spit(s,"\\log10{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\asin{")) {
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,"sin^{-1}");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\asin{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\acos{")) {
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,"cos^{-1}");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\acos{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\atan{")) {
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,"tan^{-1}");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\atan{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\sin{")) {
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,"sin");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\sin{"));
      x = plotSS(g,x,y,")");

    } else if (StringTools.chomp(s,"\\visIrradAt{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"visIrradAt");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\visIrradAt{"));
      x = plotSS(g,x,y,")");

    } else if (StringTools.chomp(s,"\\fullIrradAt{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"fullIrradAt");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\fullIrradAt{"));
      x = plotSS(g,x,y,")");

    } else if (StringTools.chomp(s,"\\salinityAt{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"salinityAt");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\salinityAt{"));
      x = plotSS(g,x,y,")");

    } else if (StringTools.chomp(s,"\\densityAt{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"densityAt");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\densityAt{"));
      x = plotSS(g,x,y,")");

    } else if (StringTools.chomp(s,"\\temperatureAt{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"temperatureAt");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\temperatureAt{"));
      x = plotSS(g,x,y,")");

    } else if (StringTools.chomp(s,"\\pchange{")) {
      s = StringTools.spit(s,"\\pchange{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      g.setFont(boldFont);
      x = plotSS(g,x,y,"pchange");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,lhs);
      x = plotSS(g,x,y,",");
      x = writeEq(g,x,y,rhs);
      x = plotSS(g,x,y,")");

    } else if (StringTools.chomp(s,"\\ingest{")) {
      s = StringTools.spit(s,"\\ingest{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      String mid = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
      g.setFont(boldFont);
      x = plotSS(g,x,y,"ingest");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,lhs);
      x = plotSS(g,x,y,",");
      x = writeEq(g,x,y,mid);
      x = plotSS(g,x,y,",");
      x = writeEq(g,x,y,rhs);
      x = plotSS(g,x,y,")");

    } else if (StringTools.chomp(s,"\\divide{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"divide");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\divide{"));
      x = plotSS(g,x,y,")");

    } else if (StringTools.chomp(s,"\\cos{")) {
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,"cos");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\cos{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\tan{")) {
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,"tan");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\tan{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\sqrt{")) {
      
      g.setFont(boldFont);
      g.drawString("sqrt(",x,y);
      x+=textWidth(g, "sqrt(", normalFont);
      x = writeEq(g,x,y,StringTools.spit(s,"\\sqrt{"));
      g.drawString(")",x,y);
      x+=textWidth(g, ")", normalFont);
    } else if (StringTools.chomp(s,"\\exp{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"e");
      g.setFont(normalFont);
      int newSize = (int) Math.max(6,normalFont.getSize()/1.5);
    //  int raiseBy = newSize/2;
      Font storeFont = normalFont;
      normalFont = new Font(normalFont.getName(),normalFont.getStyle(),newSize);
      g.setFont(normalFont);
      x = writeEq(g,x,y-3,StringTools.spit(s,"\\exp{"));
      normalFont = storeFont;
      g.setFont(normalFont);
    } else if (StringTools.chomp(s,"\\pow{")) {
      s = StringTools.spit(s,"\\pow{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      x = writeEq(g,x,y,lhs);
      int newSize = (int) Math.max(6,normalFont.getSize()/1.2);
      int raiseBy = newSize/3;
      Font storeFont = normalFont;
      normalFont = new Font(normalFont.getName(),normalFont.getStyle(),newSize);
      g.setFont(normalFont);
      x = writeEq(g,x,y-raiseBy,rhs);
      normalFont = storeFont;
      g.setFont(normalFont);
    } else if (StringTools.chomp(s,"\\div{")) {
      s = StringTools.spit(s,"\\div{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      int widTop = (int) getEqWidth(g,lhs);
      int widBot = (int) getEqWidth(g,rhs);
      int heiTop = (int) getEqHeight(g,lhs);
      int heiBot = (int) getEqHeight(g,rhs);
      int wMax= Math.max(widTop,widBot);
      int x2 = writeEq(g,x+((wMax/2)-(widTop/2)),y-((heiTop/2)+2),lhs);
      int x3 = writeEq(g,x+((wMax/2)-(widBot/2)),y+((heiBot/2)+2),rhs);
      g.drawLine(x,y,Math.max(x3,x2),y);
      x = Math.max(x3,x2)+2;
    } else if (StringTools.chomp(s,"\\sub{")) {
      s = StringTools.spit(s,"\\sub{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,lhs);
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,"-");
      g.setFont(normalFont);
      x = writeEq(g,x,y,rhs);
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\conditional{")) {
      s = StringTools.spit(s,"\\conditional{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      String mid = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
      rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
      x = plotSS(g,x,y,"(if ");
      x = writeEq(g,x,y,lhs);
      x = plotSS(g,x,y,"then ");
      x = writeEq(g,x,y,mid);
      x = plotSS(g,x,y,"else ");
      x = writeEq(g,x,y,rhs);
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\varhist{")) {
      s = StringTools.spit(s,"\\varhist{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      if (lhs.equals("\\? Var{}")) lhs = "\\var{?}";
      lhs = StringTools.spit(lhs,"\\var{");
      x = plotSS(g,x,y,lhs+"[");
      x = writeEq(g,x,y,rhs);
      x = plotSS(g,x,y,"]");

    } else if (StringTools.chomp(s,"\\var{")) {
      s = StringTools.spit(s,"\\var{");
      x = plotSS(g,x,y,s);
    } else if (StringTools.chomp(s,"\\delta{")) {
      s = "\\div{\\var{d"+StringTools.spit(StringTools.spit(s,"\\delta{"),"\\var{")+"},\\var{dt}}";
      x = writeEq(g,x,y,s);
    } else if (StringTools.chomp(s,"\\val{")) {
      s = StringTools.spit(s,"\\val{");
      s = StringTools.LHS(s,StringTools.getUnNested(s,','));
      s = StringTools.spit(s,"\\sival{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      if ((rhs.equals("0"))) x = plotSS(g,x,y,lhs);
      else x = plotSS(g,x,y,lhs+" x 10^{"+rhs+"}");
    } else if ((StringTools.chomp(s,"\\max{"))||(StringTools.chomp(s,"\\min{"))||(StringTools.chomp(s,"\\add{"))||(StringTools.chomp(s,"\\mul{"))
              ||(StringTools.chomp(s,"\\and{"))||(StringTools.chomp(s,"\\or{"))||(StringTools.chomp(s,"\\someVariety{"))
              ||(StringTools.chomp(s,"\\allVariety{"))||(StringTools.chomp(s,"\\noVariety{"))) {
      String preop = "";
      String midop = "";
      if (StringTools.chomp(s,"\\max{")) { preop = "max "; midop = ","; s = StringTools.spit(s,"\\max{"); }
      else if (StringTools.chomp(s,"\\min{")) { preop = "min "; midop = ","; s = StringTools.spit(s,"\\min{"); }
      else if (StringTools.chomp(s,"\\add{")) { midop = "+"; s = StringTools.spit(s,"\\add{"); }
      else if (StringTools.chomp(s,"\\someVariety{")) { midop = ""; s = StringTools.spit(s,"\\someVariety{"); }
      else if (StringTools.chomp(s,"\\allVariety{")) { midop = ""; s = StringTools.spit(s,"\\allVariety{"); }
      else if (StringTools.chomp(s,"\\noVariety{")) { midop = ""; s = StringTools.spit(s,"\\noVariety{"); }
      else if (StringTools.chomp(s,"\\mul{")) { midop = ""; s = StringTools.spit(s,"\\mul{"); }
      else if (StringTools.chomp(s,"\\and{")) { midop = " and "; s = StringTools.spit(s,"\\and{"); }
      else if (StringTools.chomp(s,"\\or{")) { midop = " or "; s = StringTools.spit(s,"\\or{"); }

      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      if (rhs.equals("")) midop="";
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,preop);
      g.setFont(normalFont);
      if (BRACKETS) x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,lhs);
      g.setFont(boldFont);
      if (midop.length()>0) x = plotSS(g,x,y,midop);
      g.setFont(normalFont);
      while (StringTools.getUnNested(rhs,',')>=0) {
        lhs = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
        rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
        x = writeEq(g,x,y,lhs);
        g.setFont(boldFont);
        if (midop.length()>0) x = plotSS(g,x,y,midop);
        g.setFont(normalFont);
      }
      x = writeEq(g,x,y,rhs);
      if (BRACKETS) x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\not{")) {
      g.setFont(boldFont);
      s = StringTools.spit(s,"\\not{");
      x = plotSS(g,x,y,"!");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,s);
      x = plotSS(g,x,y,")");
    } else if ((StringTools.chomp(s,"\\greater{"))||(StringTools.chomp(s,"\\greaterequal{"))||(StringTools.chomp(s,"\\equal{"))
            ||(StringTools.chomp(s,"\\less{"))||(StringTools.chomp(s,"\\lessequal{"))||(StringTools.chomp(s,"\\neq{"))) {
      String symb = "";
      if (StringTools.chomp(s,"\\greater{")) { symb = ">"; s = StringTools.spit(s,"\\greater{"); }
      else if (StringTools.chomp(s,"\\greaterequal{")) { symb = "\u2265"; s = StringTools.spit(s,"\\greaterequal{"); }
      else if (StringTools.chomp(s,"\\equal{")) { symb = "="; s = StringTools.spit(s,"\\equal{"); }
      else if (StringTools.chomp(s,"\\less{")) { symb = "<"; s = StringTools.spit(s,"\\less{"); }
      else if (StringTools.chomp(s,"\\lessequal{")) { symb = "\u2264"; s = StringTools.spit(s,"\\lessequal{"); }
      else if (StringTools.chomp(s,"\\neq{")) { symb = "~"; s = StringTools.spit(s,"\\neq{"); }
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,lhs);
      
      g.setFont(boldFont);
      x = plotSS(g,x,y,symb);
      g.setFont(normalFont);
      x = writeEq(g,x,y,rhs);
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\change{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"change(");
      g.setFont(normalFont);
      x = writeEq(g,x,y,StringTools.spit(s,"\\change{"));
      g.setFont(boldFont);
      x = plotSS(g,x,y,")");
      g.setFont(normalFont);
    } else if (StringTools.chomp(s,"\\create{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"create");      
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      String param= StringTools.spit(s,"\\create{");
      int storeX = x;
    //  int storeY = y;
      x = writeEq(g,x,y,StringTools.LHS(param,StringTools.getUnNested(param,',')));
      x = plotSS(g,x,y,",");
      param = StringTools.RHS(param,StringTools.getUnNested(param,','));
      x = writeEq(g,x,y,StringTools.LHS(param,StringTools.getUnNested(param,',')));
      x = plotSS(g,x,y,")");
      param = StringTools.RHS(param,StringTools.getUnNested(param,','));
      while (param.length()>2) {
        x = storeX;
        String theparam = StringTools.LHS(param,StringTools.getUnNested(param,','));
        y += getEqHeight(g,theparam);
        x = writeEq(g,x,y,theparam);
        if (param.indexOf(',')<0) param = "";
        param = StringTools.RHS(param,StringTools.getUnNested(param,','));
      }

    } else if (StringTools.chomp(s,"\\divide{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"divide");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      x = writeEq(g,x,y,StringTools.spit(s,"\\divide{"));
      x = plotSS(g,x,y,")");
    } else if (StringTools.chomp(s,"\\uptake{")) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"uptake");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      s = StringTools.spit(s,"\\uptake{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      x = writeEq(g,x,y,lhs);
      x = plotSS(g,x,y,",");
      x = writeEq(g,x,y,rhs);
      x = plotSS(g,x,y,")");
    } else if ((StringTools.chomp(s,"\\release{")) || (StringTools.chomp(s,"\\remineralise{"))) {
      g.setFont(boldFont);
      x = plotSS(g,x,y,"release");
      g.setFont(normalFont);
      x = plotSS(g,x,y,"(");
      if (StringTools.chomp(s,"\\release{")) s = StringTools.spit(s,"\\release{");
      else s = StringTools.spit(s,"\\remineralise{");
      String lhs = StringTools.LHS(s,StringTools.getUnNested(s,','));
      String rhs = StringTools.RHS(s,StringTools.getUnNested(s,','));
      x = writeEq(g,x,y,lhs);
      x = plotSS(g,x,y,",");
      x = writeEq(g,x,y,rhs);
      x = plotSS(g,x,y,")");
    }

    return x;
  }

  public void paintComponent(Graphics g2) {
    super.paintComponent(g2);
    
    Graphics2D g = (Graphics2D) g2;

    g.setFont(normalFont);
    int maxwidth = 0;
    int x = 0;
    int y = 18;
    String s = EQ;
    int linecount = 0;
    while (s.indexOf("\\newline")>=0) {
      s = s.substring(s.indexOf("\\newline")+8);
      linecount++;
    }
    String eqlist = EQ;
    if (linecount==0) { linecount = 1; eqlist += "\\newline"; }
    for (int i=0; i<linecount; i++) {
      s = new String(eqlist.substring(0,eqlist.indexOf("\\newline")));
      if (eqlist.length()-eqlist.indexOf("\\newline")>8) 
        eqlist = eqlist.substring(eqlist.indexOf("\\newline")+8);
      else eqlist = "";
      if (StringTools.chomp(s,"\\txt{")) {
        smallFonts();
        String eq = "\\var{"+StringTools.spit(s,"\\txt{")+"}";
      //  int height = (int) getEqHeight(g,eq);
        x = writeEq(g,x,y,eq);
      } else if (StringTools.chomp(s,"\\unit{")) {
        if (s.equals("\\unit{0,0,0}")) s = "\\unit{}";
        smallFonts();
        BRACKETS = false;
        String eq = new String();
        int count = 0;
        s = StringTools.spit(s,"\\unit{");
        while (s.length()>0) {
          String pre = StringTools.LHS(s,StringTools.getUnNested(s,','));
          s = StringTools.RHS(s,StringTools.getUnNested(s,','));
          String mid = StringTools.LHS(s,StringTools.getUnNested(s,','));
          s = StringTools.RHS(s,StringTools.getUnNested(s,','));
          if (s.indexOf(",")==-1) s+=",";
          String sup = StringTools.LHS(s,StringTools.getUnNested(s,','));
          s = StringTools.RHS(s,StringTools.getUnNested(s,','));
          if (pre.equals("-3")) pre = "m";
          else if (pre.equals("-6")) pre= "u";
          else if (pre.equals("-9")) pre = "n";
          else if (pre.equals("-12")) pre = "p";
          else if (pre.equals("3")) pre = "k";
          else if (pre.equals("6")) pre = "M";
          else if (pre.equals("9")) pre = "G";
          else if (pre.equals("12")) pre = "T";
          else pre = "";
          if (sup.equals("1")) eq += "\\var{"+pre+mid+"}"; else
            eq += "\\pow{\\var{"+pre+mid+"},\\var{"+sup+"}}";
          if (s.length()>0) eq+=",";
          count++;
        }
        if (count>1) eq = "\\mul{"+eq+"}";
        int height = (int) getEqHeight(g,eq);
        x = writeEq(g,x,y,eq);
        y = y + height + 2;
        BRACKETS = true;
        defaultFonts();
      } else if (StringTools.chomp(s,"\\var{")) {
        s = StringTools.spit(s,"\\var{");
        smallFonts();
        x = plotSS(g,x,y,s);
        defaultFonts();
      }
      
      else {
        int height = (int) getEqHeight(g,s);
        maxwidth = (int)Math.max(getEqWidth(g,s),maxwidth);
        writeEq(g, x, y, s);
        y = y + height;
      }
    }
    setPreferredSize(new Dimension(2*Math.max(800,maxwidth), y + 10));
    revalidate();
  }
}
