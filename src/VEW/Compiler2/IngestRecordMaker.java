package VEW.Compiler2;

import java.io.PrintWriter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;


public class IngestRecordMaker {
  public static void writeIngestRecordJava(String fileName, XMLTag model) {
    try {
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      PW.println("package VEW.Sim;");
      PW.println("import java.io.DataInputStream;");
      PW.println("import java.io.DataOutputStream;");
      PW.println("import java.util.ArrayList;");
      PW.println("");
      PW.println("public class IngestRecord {");
      PW.println("  ");
      PW.println("  private static ArrayList RecordPool = new ArrayList();");
      PW.println("  private static int PoolSize = 0;");
      PW.println("  public FunctionalGroup consumer;");
      PW.println("  public double rate;");
      PW.println("  public double minimum;");
      PW.println("  ");
      PW.println("  public void writeSnapshot(DataOutputStream snapshot) throws Exception {");
      PW.println("    snapshot.writeDouble(rate);");
      PW.println("    snapshot.writeDouble(minimum);");
      PW.println("    snapshot.writeLong(consumer.id);");
      PW.println("    snapshot.writeInt(consumer.getParams()._type);");
      PW.println("  }");
      PW.println("    ");
      PW.println("  ");
      PW.println("  ");
      PW.println("  public static IngestRecord readSnapshot(BLayer blayer, DataInputStream snapshot) throws Exception {");
      PW.println("    ");
      PW.println("    double _rate = snapshot.readDouble();");
      PW.println("    double _min = snapshot.readDouble();");
      PW.println("    long _id = snapshot.readLong();");
      PW.println("    int _type = snapshot.readInt();");
      PW.println("    FunctionalGroup _consumer = blayer.findParticleInLayer(_id,_type);");
      PW.println("    if (_consumer==null) {");
      PW.println("      int i=0;");
      PW.println("      while ((_consumer==null) && (i<Kernel.W.B_Layer.length)) {");
      PW.println("        if (Kernel.W.B_Layer[i]!=blayer) _consumer=Kernel.W.B_Layer[i].findParticleInLayer(_id,_type); ");
      PW.println("        i++;");
      PW.println("      }");
      PW.println("    }");
      PW.println("    return getRecord(_consumer,_rate,_min);");
      PW.println("  }");
      PW.println("");
      PW.println("  public static IngestRecord getRecord(FunctionalGroup _Consumer, double _rate, double _minimum) {");
      PW.println("    IngestRecord Returnable;");
      PW.println("    if(PoolSize > 0) {");
      PW.println("      PoolSize--;");
      PW.println("      Returnable = (IngestRecord) RecordPool.remove(PoolSize);");
      PW.println("      Returnable.consumer = _Consumer;");
      PW.println("      Returnable.rate   = _rate;");
      PW.println("      Returnable.minimum = _minimum;      ");
      PW.println("    }");
      PW.println("    else {");
      PW.println("      Returnable = new IngestRecord(_Consumer, _rate, _minimum);");
      PW.println("    }");
      PW.println("    return Returnable;");
      PW.println("  }");
      PW.println("");
      PW.println("  public void freeRecord() {");
      PW.println("    PoolSize++;");
      PW.println("    consumer = null;");
      PW.println("    rate=0;");
      PW.println("    minimum=0;");
      PW.println("    RecordPool.add(this);");
      PW.println("  }");
      PW.println("");
      PW.println("  private IngestRecord(FunctionalGroup _Consumer, double _rate, double _minimum) {");
      PW.println("    consumer = _Consumer;");
      PW.println("    rate = _rate;");
      PW.println("    minimum = _minimum;");
      PW.println("  }");
      PW.println(" ");
      PW.println("");
      PW.println("  static {");
      PW.println("    // Start the pool off with 5000 records.");
      PW.println("    for(int i = 0; i < 5000; i++) {");
      PW.println("      RecordPool.add(new IngestRecord(null, 0, 0));");
      PW.println("    }");
      PW.println("    PoolSize = 5000;");
      PW.println("  }");
      PW.println("}");
      PW.flush();
      PW.close();
    } catch (Exception e) { e.printStackTrace(); }

  }

}

