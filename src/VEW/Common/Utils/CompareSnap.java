package VEW.Common.Utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;

import VEW.Common.DateDialog;
import VEW.Common.Random.RanMT;

public class CompareSnap {
  private static final TimeZone GMTTimeZone = TimeZone.getTimeZone("GMT");
  private static final GregorianCalendar _gc = new GregorianCalendar(GMTTimeZone);
    
  public static void checkRandomObj(BufferedInputStream b1, BufferedInputStream b2) throws Exception {
    ObjectInputStream s1 = new ObjectInputStream(b1);
    final RanMT r1 = (RanMT)s1.readObject();
    ObjectInputStream s2 = new ObjectInputStream(b2);
    final RanMT r2 = (RanMT)s2.readObject();
    final double raw1 = r1.raw();
    final double raw2 = r2.raw();
    if (raw1!=raw2) System.out.println("Random check: FAILED - "+raw1+" != "+raw2); 
  }
  
  private static void checkVar(DataInputStream d1, DataInputStream d2, String s) throws Exception {
    final int h1 = d1.readInt();
    final int h2 = d2.readInt();
    if (h1!=h2) System.out.println(s+" ERROR - history size "+h1+", "+h2);
    checkDouble(d1,d2,s+" DELTA");
    checkDouble(d1,d2,s+" BUFFER");
    checkDouble(d1,d2,s+" CURRENT");
    if (h1>1) {
      for (int i=0; i<h1-1; i++) {
        final double ch1 = d1.readDouble();
        final double ch2 = d2.readDouble();
        if (ch1!=ch2) System.out.println(s+" ERROR - h["+i+"] - "+ch1+", "+ch2);
      }
    }
  }
    
  
  public static double checkDouble(DataInputStream d1, DataInputStream d2, String s) throws Exception {
    final double db1 = d1.readDouble();
    final double db2 = d2.readDouble();
    if ((db1!=db2) && (!(Double.isNaN(db1)) && (!(Double.isNaN(db2))))) System.out.println(s+" ERROR - "+db1+" != "+db2);
    return db1;
  }
  
  public static int checkInt(DataInputStream d1, DataInputStream d2, String s) throws Exception {
    final int db1 = d1.readInt();
    final int db2 = d2.readInt();
    if (db1!=db2) System.out.println(s+" ERROR - "+db1+" != "+db2);
    return db1;
  }
  
  public static void checkLong(DataInputStream d1, DataInputStream d2, String s) throws Exception {
    final long db1 = d1.readLong();
    final long db2 = d2.readLong();
    if (db1!=db2) System.out.println(s+" ERROR - "+db1+" != "+db2);
  }
  
  public static void checkBoolean(DataInputStream d1, DataInputStream d2, String s) throws Exception {
    final boolean db1 = d1.readBoolean();
    final boolean db2 = d2.readBoolean();
    if (db1!=db2) System.out.println(s+" ERROR - "+db1+" != "+db2);
  }
  
    public static void  checkPLayer(DataInputStream d1, DataInputStream d2, String s) throws Exception {
    checkInt(d1,d2,s+" ID");
    checkDouble(d1,d2,s+" AvgDens");
    checkDouble(d1,d2,s+" AvgIrrad");
    checkDouble(d1,d2,s+" AvgSaln");
    checkDouble(d1,d2,s+" AvgTemp");
    checkDouble(d1,d2,s+" Density");
    checkDouble(d1,d2,s+" Depth");
    checkDouble(d1,d2,s+" Full_Irrad");
    checkDouble(d1,d2,s+" Salinity");
    checkDouble(d1,d2,s+" Temp");
    checkDouble(d1,d2,s+" Vis_Irrad");
  }
  
  public static void checkPhysics(DataInputStream d1, DataInputStream d2, int chemCount, int[] stages) throws Exception {
    int size1 = d1.readInt();
    int size2 = d2.readInt();
    if (size1!=size2) System.out.println("Physics: P_Layer_List size FAILURE, "+size1+" != "+size2); 
    for (int i=0; i<size1; i++) checkPLayer(d1,d2,"P_Layer_List "+i);
    size1 = d1.readInt();
    size2 = d2.readInt();
    if (size1!=size2) System.out.println("Physics: P_Layer_Ref_List size FAILURE, "+size1+" != "+size2); 
    for (int i=0; i<size1; i++) checkPLayer(d1,d2,"P_Layer_Ref_List "+i);
    checkInt(d1,d2,"PLayer.count");
    checkVar(d1,d2,"MLDepth");
    checkVar(d1,d2,"Max_MLD");
    checkVar(d1,d2,"Min_MLD");
    checkVar(d1,d2,"ML_Temp");
    checkVar(d1,d2,"ML_Dens");
    checkVar(d1,d2,"ML_Saln");
    checkVar(d1,d2,"SC_Flux");
    checkVar(d1,d2,"O_MLDTime");
    checkVar(d1,d2,"C_MLDTime");
    checkVar(d1,d2,"SH_Flux");
    checkVar(d1,d2,"UStarCube");
    checkVar(d1,d2,"WMax");
    checkBoolean(d1,d2,"OffSeason");
    checkVar(d1,d2,"Deepest Turbocline This Year");
    checkVar(d1,d2,"Deepest Turbocline Ever");
    checkBoolean(d1,d2,"Getting Deeper");
    checkDouble(d1,d2,"Bounce depth");
    checkDouble(d1,d2,"Old Bounce Depth");
    checkInt(d1,d2,"Dead Period");
    checkBoolean(d1,d2,"OddTimeStep");
    checkDouble(d1,d2,"MLD");
    checkDouble(d1,d2,"MLS");
    checkDouble(d1,d2,"MLT");
    checkDouble(d1,d2,"Kernel.DTime");
   
  }
    

  public static void checkBLayerChems(DataInputStream d1, DataInputStream d2,String s, int chemCount) throws Exception {
    for (int i=0; i<chemCount; i++) {
      checkVar(d1,d2,s+" chem no. "+i+" concentration");
      checkInt(d1,d2,s+" chem no. "+i+" type");
      int vsize1 = d1.readInt();
      int vsize2 = d2.readInt();
      if (vsize1!=vsize2) System.out.println(s+" chems no. "+i+", vars FAILURE, "+vsize1+" != "+vsize2);
      for (int j=0; j<vsize1; j++) {
        checkVar(d1,d2,s+" chem no. "+i+", var no. "+j);
      }
    }
  }
  
  public static void checkFG(DataInputStream d1, DataInputStream d2, String s, int chemCount) throws Exception {
    long id1 = d1.readLong();
    long id2 = d2.readLong();
    if (id1!=id2) System.out.println(s+" Agent ids don't match - "+id1+", "+id2);
    checkInt(d1,d2,s+" "+id1+" type");
    checkInt(d1,d2,s+" "+id1+" stage");
    checkVar(d1,d2,s+" "+id1+" z");
    checkVar(d1,d2,s+" "+id1+" c");
    int spec1 = d1.readInt();
    int spec2 = d2.readInt();
    if (spec1!=spec2) System.out.println(s+" "+id1+" Ingested.length: "+spec1+","+spec2);
    for (int i=0; i<spec1; i++) {
      int stage1 = d1.readInt();
      int stage2 = d2.readInt();
      if (stage1!=stage2) System.out.println(s+" "+id1+" Ingested["+i+"].length: "+stage1+","+stage2);
      for (int j=0; j<stage1; j++) {
        checkVar(d1,d2,s+" "+id1+" Ingested["+i+"]["+j+"]");
      }
    }
    for (int i=0; i<spec1; i++) checkVar(d1,d2,s+" "+id1+" pool["+i+"]");
    for (int i=0; i<chemCount; i++) checkDouble(d1,d2,s+" "+id1+" released["+i+"]");
    int v1 = d1.readInt();
    int v2 = d2.readInt();
    if (v1!=v2) System.out.println(s+" "+id1+" Var count: "+v1+","+v2);
    for (int i=0; i<v1; i++) checkVar(d1,d2,s+" "+id1+" Var no. "+i);
    int vv1 = d1.readInt();
    int vv2 = d2.readInt();
    if (vv1!=vv2) System.out.println(s+" "+id1+" V-Var count: "+vv1+","+vv2);
    for (int i=0; i<vv1; i++) {
      int vvl1 = d1.readInt();
      int vvl2 = d2.readInt();
      if (vvl1!=vvl2) System.out.println(s+" "+id1+" V-Var "+i+" arity:  "+vvl1+","+vvl2);
      for (int j=0; j<vvl1; j++) checkVar(d1,d2,s+" "+id1+" V-var "+i+" entry "+j);
    }
  }
    
  
  public static void checkBLayerParticles(DataInputStream d1, DataInputStream d2,String s, int chemCount, int[] stages) throws Exception {
    for (int i=0; i<stages.length; i++) {
      int pCount1 = d1.readInt();
      int pCount2 = d2.readInt();
      if (pCount1!=pCount2) System.out.println(s+" Particles Species "+i+", all stages, count: "+pCount1+","+pCount2);
      for (int j=0; j<pCount1; j++) {
        checkFG(d1,d2,s+" Agent list no. "+j,chemCount);
      }
      for (int j=0; j<stages[i]; j++) {
        int pc1 = d1.readInt();
        int pc2 = d2.readInt();
        if (pc1!=pc2) System.out.println(s+" Particles Species "+i+", stage "+j+", count: "+pc1+","+pc2);
        for (int k=0; k<pc1; k++) checkLong(d1,d2,s+" Species "+i+" stage "+j+", id list");
      }
    }
  }
  
  public static void checkBLayerRequests(DataInputStream d1, DataInputStream d2,String s, int chemCount, int[] stages) throws Exception {
    for (int i=0; i<stages.length; i++) {
      for (int j=0; j<stages[i]; j++) {
        int rc1 = d1.readInt();
        int rc2 = d2.readInt();
        if (rc1!=rc2) System.out.println(s+" REQUESTS Species "+i+" stage "+j+" Request count "+rc1+","+rc2);
        for (int k=0; k<rc1; k++) {
          checkDouble(d1,d2,s+" REQ spec "+i+" stage "+j+" rc "+k+" RATE");
          checkDouble(d1,d2,s+" REQ spec "+i+" stage "+j+" rc "+k+" MINIMUM");
          checkLong(d1,d2,s+" REQ spec "+i+" stage "+j+" rc "+k+" CONSUMER ID");
          checkInt(d1,d2,s+" REQ spec "+i+" stage "+j+" rc "+k+" CONSUMER TYPE");
        }
      }
    }
    for (int i=0; i<chemCount; i++) {
      int cc1 = d1.readInt();
      int cc2 = d2.readInt();
      if (cc1!=cc2) System.out.println(s+" Chem "+i+", CONSUMERS count "+cc1+","+cc2);
      for (int j=0; j<cc1; j++) {
        checkLong(d1,d2,s+" CON spec "+i+" chem "+i+" cc "+j+" CONSUMER ID");
        checkInt(d1,d2,s+" CON spec "+i+" chem "+i+" cc "+j+" CONSUMER TYPE");        
        checkDouble(d1,d2,s+" CON spec "+i+" chem "+i+" cc "+j+" CONSUMER AMOUNT");        
      }
    }
  }
  
  public static void checkBLayerStats(DataInputStream d1, DataInputStream d2,String s, int chemCount, int[] stages) throws Exception {
    for (int i=0; i<stages.length; i++)
      for (int j=0; j<stages[i]+1; j++)
        checkVar(d1,d2,s+" SpecConc["+i+"]["+j+"]");
    for (int i=0; i<chemCount; i++) 
      checkDouble(d1,d2,s+" released["+i+"]");
  }

  public static void checkBLayerSwimmers(DataInputStream d1, DataInputStream d2, String s, int[] spec) throws Exception {
    for (int speciesNo = 0; speciesNo< spec.length; speciesNo++) {
      for (int stages=0; stages < spec[speciesNo]; stages++) {
        int noSwimmers = checkInt(d1,d2,s+" Number of swimmers, ["+speciesNo+"]["+stages+"]);");
        for (int i=0; i<noSwimmers; i++) {
          checkLong(d1,d2,s+" Swim["+speciesNo+"]["+stages+"], no. "+i+", id error");
          checkDouble(d1,d2,s+" Swim["+speciesNo+"]["+stages+"], no. "+i+", prop error");
        }
      }
    }
  }
  
  
  public static void checkBiology(DataInputStream d1, DataInputStream d2, int chemCount, int[] stages) throws Exception {
    int size1 = d1.readInt();
    int size2 = d2.readInt();
    if (size1!=size2) System.out.println("Biology: B_Layer size FAILURE, "+size1+" != "+size2);
    for (int i=0; i<size1; i++) checkBLayerChems(d1,d2,"BLAYER "+i,chemCount);
    for (int i=0; i<size1; i++) checkBLayerParticles(d1,d2,"BLAYER "+i,chemCount,stages);
    for (int i=0; i<size1; i++) checkBLayerSwimmers(d1,d2,"BLAYER "+i,stages);
    for (int i=0; i<size1; i++) checkBLayerRequests(d1,d2,"BLAYER "+i,chemCount,stages);
    for (int i=0; i<size1; i++) checkBLayerStats(d1,d2,"BLAYER "+i,chemCount,stages);    
    checkLong(d1,d2,"Functional Group IDCOUNT");
    for (int i=0; i<stages.length; i++)
      for (int j=0; j<stages[i]; j++) 
        checkInt(d1,d2,"Lost Particles["+i+"]["+j+"]");
    for (int i=0; i<stages.length; i++) {
      for (int j=0; j<stages[i]+1; j++) {
        checkVar(d1,d2,"Column SpeciesTotal["+i+"]["+j+"]");
      }
    }
  }
      
  
  public static void main(String[] args) throws Exception {
    if (args.length!=2) {
      System.out.println("Format: java CompareSnap snap.1 snap.2");
    } else {
      final File snap1 = new File(args[0]);
      final File snap2 = new File(args[1]);
      BufferedInputStream b1 = new BufferedInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(snap1))));
      BufferedInputStream b2 = new BufferedInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(snap2)))); 
      StringBuffer sb1 = new StringBuffer();
      StringBuffer sb2 = new StringBuffer();
      final DataInputStream d1 = new DataInputStream(b1);
      final DataInputStream d2 = new DataInputStream(b2);       
      for (int i=0; i<6; i++) sb1.append(d1.readChar());
      for (int i=0; i<6; i++) sb2.append(d2.readChar());
      if (!sb1.toString().equals(sb2.toString())) System.out.println("Snapshot header error - "+sb1.toString()+","+sb2.toString());
      int ver1 = d1.readInt();
      int ver2 = d2.readInt();
      if (ver1!=ver2) System.out.println("Version mis-match - "+ver1+","+ver2);
      System.out.println(sb1.toString()+" v. "+ver1);
      long t1 = d1.readLong();
      long t2 = d2.readLong();
      if (t1!=t2) System.out.println("Time mis-match - "+t1+","+t2);
      _gc.setTimeInMillis(t1);
      System.out.println("Time: "+t1+" = "+DateDialog.getString(_gc));
      checkRandomObj(b1,b2);
      int chemCount = d1.readInt();
      int cc2 = d2.readInt();
      if (chemCount!=cc2) System.out.println(" Chemical Count: "+chemCount+","+cc2);
      int speciesCount = d1.readInt();
      int sp2 = d2.readInt();
      if (speciesCount!=sp2) System.out.println(" Species Count: "+speciesCount+","+sp2);
      int[] stages = new int[speciesCount];
      for (int i=0; i<speciesCount; i++) {
        stages[i]=d1.readInt();
        final int compare = d2.readInt();
        if (stages[i]!=compare) System.out.println("Species "+i+", stage count: "+stages[i]+","+compare);
      }
      checkPhysics(d1,d2,chemCount,stages);
      checkBiology(d1,d2,chemCount,stages);
      System.out.println("Finished");
    }
  }
}
