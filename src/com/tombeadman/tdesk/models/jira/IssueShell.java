package com.tombeadman.tdesk.models.jira;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.net.URI;


/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 13/12/14 Time: 10:00
 */
public class IssueShell implements Serializable
{
  private URI self;
  private String key;
  private Long id;
  private StatusShell status;
  private IssueTypeShell issueType;
  private BasicProjectShell project;
  private URI transitionsUri;
  private String summary;
  private String description;
  private UserShell reporter;
  private UserShell assignee;
  private ResolutionShell resolution;
  private DateTime creationDate;
  private DateTime updateDate;

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

  public StatusShell getStatus()
  {
    return status;
  }

  public void setStatus( final StatusShell status )
  {
    this.status = status;
  }

  public IssueTypeShell getIssueType()
  {
    return issueType;
  }

  public void setIssueTypeShell( final IssueTypeShell issueType )
  {
    this.issueType = issueType;
  }

  public BasicProjectShell getProject()
  {
    return project;
  }

  public void setProject( final BasicProjectShell project )
  {
    this.project = project;
  }

  public URI getTransitionsUri()
  {
    return transitionsUri;
  }

  public void setTransitionsUri( final URI transitionsUri )
  {
    this.transitionsUri = transitionsUri;
  }

  public String getSummary()
  {
    return summary;
  }

  public void setSummary( final String summary )
  {
    this.summary = summary;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( final String description )
  {
    this.description = description;
  }

  public UserShell getReporter()
  {
    return reporter;
  }

  public void setReporter( final UserShell reporter )
  {
    this.reporter = reporter;
  }

  public UserShell getAssignee()
  {
    return assignee;
  }

  public void setAssignee( final UserShell assignee )
  {
    this.assignee = assignee;
  }

  public ResolutionShell getResolution()
  {
    return resolution;
  }

  public void setResolutionShell( final ResolutionShell resolution )
  {
    this.resolution = resolution;
  }

  public DateTime getCreationDate()
  {
    return creationDate;
  }

  public void setCreationDate( final DateTime creationDate )
  {
    this.creationDate = creationDate;
  }

  public DateTime getUpdateDate()
  {
    return updateDate;
  }

  public void setUpdateDate( final DateTime updateDate )
  {
    this.updateDate = updateDate;
  }
}
