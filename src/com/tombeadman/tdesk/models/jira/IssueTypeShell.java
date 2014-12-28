package com.tombeadman.tdesk.models.jira;

import java.io.Serializable;
import java.net.URI;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 17/12/14 Time: 11:24
 */
public class IssueTypeShell implements Serializable
{
  private URI self;
  private Long id;
  private String name;
  private boolean isSubtask;
  private String description;

  public URI getSelf()
  {
    return self;
  }

  public void setSelf( final URI self )
  {
    this.self = self;
  }

  public Long getId()
  {
    return id;
  }

  public void setId( final Long id )
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

  public boolean isSubtask()
  {
    return isSubtask;
  }

  public void setSubtask( final boolean isSubtask )
  {
    this.isSubtask = isSubtask;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( final String description )
  {
    this.description = description;
  }
}
