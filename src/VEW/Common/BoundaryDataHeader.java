package VEW.Common;

import java.io.Serializable;

public class BoundaryDataHeader implements Serializable
{
  public final double ForcingStep;
  public final int Steps, StartDay, StartMonth;
  public final float StartTime;
  public final String[] FieldNames;

  public BoundaryDataHeader(double _ForcingStep, int _Steps, int _StartDay, int _StartMonth, float _StartTime, String[] _FieldNames)
  {
    ForcingStep = _ForcingStep;
    Steps = _Steps;
    StartDay = _StartDay;
    StartMonth = _StartMonth;
    StartTime = _StartTime;
    FieldNames = _FieldNames;
  }
}