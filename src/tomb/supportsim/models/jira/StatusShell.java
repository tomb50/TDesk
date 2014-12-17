package tomb.supportsim.models.jira;

import java.io.Serializable;
import java.net.URI;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 17/12/14 Time: 11:34
 */
public class StatusShell implements Serializable
{
  private  Long id;
  private  String description;
  private   URI self;
  private   String name;

  public Long getId()
  {
    return id;
  }

  public void setId( final Long id )
  {
    this.id = id;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( final String description )
  {
    this.description = description;
  }

  public URI getSelf()
  {
    return self;
  }

  public void setSelf( final URI self )
  {
    this.self = self;
  }

  public String getName()
  {
    return name;
  }

  public void setName( final String name )
  {
    this.name = name;
  }
}
