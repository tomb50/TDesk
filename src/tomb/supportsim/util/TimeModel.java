package tomb.supportsim.util;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 23:31
 */
public class TimeModel
{
  private static final long baseTime = System.currentTimeMillis();


  //Use this instead of raw system calls
  public static long getTime()
  {
    //Returns time as passing an hour per minute
    return baseTime + ( 60 * ( System.currentTimeMillis() - baseTime ) );
  }
}
