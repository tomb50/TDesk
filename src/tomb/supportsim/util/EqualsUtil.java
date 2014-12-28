package tomb.supportsim.util;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 21/10/14 Time: 21:42
 */
public class EqualsUtil
{
  public static boolean areEqual( boolean aThis, boolean aThat )
  {
    return aThis == aThat;
  }

  public static boolean areEqual( char aThis, char aThat )
  {
    return aThis == aThat;
  }

  public static boolean areEqual( long aThis, long aThat )
  {
    /*
    * Implementation Note
    * Note that byte, short, and int are handled by this method, through
    * implicit conversion.
    */
    return aThis == aThat;
  }

  public static boolean areEqual( float aThis, float aThat )
  {
    return Float.floatToIntBits( aThis ) == Float.floatToIntBits( aThat );
  }

  public static boolean areEqual( double aThis, double aThat )
  {
    return Double.doubleToLongBits( aThis ) == Double.doubleToLongBits( aThat );
  }

  /**
   * Possibly-null object field.
   * <p/>
   * Includes type-safe enumerations and collections, but does not include arrays. See class comment.
   */
  public static boolean areEqual( Object aThis, Object aThat )
  {
    //Logger.getLogger(getClass().getName()).log( Level.INFO,"Object");
    return aThis == null ? aThat == null : aThis.equals( aThat );
  }
}
