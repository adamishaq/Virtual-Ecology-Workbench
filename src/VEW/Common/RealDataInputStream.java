package VEW.Common;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class RealDataInputStream extends DataInputStream {

  private RealDataInputStream(InputStream _in) {
    super(_in);
  }
  
  public static RealDataInputStream getInstance(InputStream in, boolean useFloats) {
    if (useFloats)
      return new FloatDataInputStream(in);
    else
      return new DoubleDataInputStream(in);
  }
  
  public abstract double readReal() throws IOException;
  
  private static class FloatDataInputStream extends RealDataInputStream {

    public FloatDataInputStream(InputStream _in) {
      super(_in);
    }
    
    public double readReal() throws IOException {
      return readFloat();
    }
    
  }
  
  private static class DoubleDataInputStream extends RealDataInputStream {

    public DoubleDataInputStream(InputStream _in) {
      super(_in);
    }
    
    public double readReal() throws IOException {
      return readDouble();
    }
    
  }

}
