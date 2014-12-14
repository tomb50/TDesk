package tomb.supportsim.models.jira;

import com.atlassian.jira.rest.client.api.domain.*;
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
  private Status status;
  private IssueType issueType;
  private BasicProject project;
  private URI transitionsUri;
  private String summary;
  private String description;
  private User reporter;
  private User assignee;
  private Resolution resolution;
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

  public Status getStatus()
  {
    return status;
  }

  public void setStatus( final Status status )
  {
    this.status = status;
  }

  public IssueType getIssueType()
  {
    return issueType;
  }

  public void setIssueType( final IssueType issueType )
  {
    this.issueType = issueType;
  }

  public BasicProject getProject()
  {
    return project;
  }

  public void setProject( final BasicProject project )
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

  public User getReporter()
  {
    return reporter;
  }

  public void setReporter( final User reporter )
  {
    this.reporter = reporter;
  }

  public User getAssignee()
  {
    return assignee;
  }

  public void setAssignee( final User assignee )
  {
    this.assignee = assignee;
  }

  public Resolution getResolution()
  {
    return resolution;
  }

  public void setResolution( final Resolution resolution )
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
