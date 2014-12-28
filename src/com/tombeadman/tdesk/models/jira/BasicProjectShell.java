package com.tombeadman.tdesk.models.jira;

import java.io.Serializable;
import java.net.URI;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 17/12/14 Time: 11:30
 */
public class BasicProjectShell implements Serializable
{
  private  URI self;
  private  String key;
  private  Long id;
  private  String name;

  public URI getSelf()
  {
    return self;
  }

  public void setSelf( final URI self )
  {
    this.self = self;
  }

  public String getKey()
  {
    return key;
  }

  public void setKey( final String key )
  {
    this.key = key;
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
}
