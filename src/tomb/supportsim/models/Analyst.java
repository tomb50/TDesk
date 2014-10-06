package tomb.supportsim.models;

import tomb.supportsim.models.enums.RoleEnum;
import tomb.supportsim.models.enums.WorkingStateEnum;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 01/08/2014 Time: 12:00
 */
public class Analyst implements Serializable
{
  private int id;
  private String name;
  private RoleEnum role;
  private WorkingStateEnum state;

  public Analyst()
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

  public RoleEnum getRole()
  {
    return role;
  }

  public void setRole( final RoleEnum role )
  {
    this.role = role;
  }

  public WorkingStateEnum getState()
  {
    return state;
  }

  public void setState( final WorkingStateEnum state )
  {
    this.state = state;
  }

  @Override
  public String toString()
  {
    return "Analyst{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", role=" + role +
      ", state=" + state +
      '}';
  }
}
