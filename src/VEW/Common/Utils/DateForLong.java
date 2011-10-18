package VEW.Common.Utils;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import VEW.Common.DateDialog;

public class DateForLong {
  public static void main(String[] args) {
    if (args.length!=1) System.out.println("java DateForLong 12345678");
    else {
      final TimeZone GMTTimeZone = TimeZone.getTimeZone("GMT");
      final GregorianCalendar gc = new GregorianCalendar(GMTTimeZone);
      gc.setTimeInMillis(Long.parseLong(args[0]));
      System.out.println(DateDialog.getString(gc)+" (GMT)");
    }
  }
}
