package tomb.supportsim.models;

import tomb.supportsim.models.enums.TimeZoneEnum;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 19/08/2014 Time: 08:36
 */
public class Customer implements Serializable
{

  private int id;
  private String name;
  private TimeZoneEnum timeZone;

  public Customer()
  {
  }

  public int getId()
  {
    return id;
  }

  public void setId( final int id )
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName( final String name )
  {
    this.name = name;
  }

  public TimeZoneEnum getTimeZone()
  {
    return timeZone;
  }

  public void setTimeZone( final TimeZoneEnum timeZone )
  {
    this.timeZone = timeZone;
  }
}
