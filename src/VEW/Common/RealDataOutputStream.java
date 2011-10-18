package VEW.Common;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class RealDataOutputStream extends DataOutputStream {

  private RealDataOutputStream(OutputStream _out) {
    super(_out);
  }
  
  public static RealDataOutputStream getInstance(OutputStream out, boolean usingFloats) {
    if (usingFloats)
      return new FloatDataOutputStream(out);
    else
      return new DoubleDataOutputStream(out);
  }
  
  public abstract void writeReal(double value) throws IOException;
  
  private static class FloatDataOutputStream extends RealDataOutputStream {
    
    public FloatDataOutputStream(OutputStream _out) {
      super(_out);
    }
    
    public void writeReal(double value) throws IOException {
      writeFloat((float) value);
    }
    
  }
  
  private static class DoubleDataOutputStream extends RealDataOutputStream {
    
    public DoubleDataOutputStream(OutputStream _out) {
      super(_out);
    }
    
    public void writeReal(double value) throws IOException {
      writeDouble(value);
    }
  }
  
}
