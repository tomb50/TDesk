package tomb.supportsim.models;

import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 10/10/2014 Time: 19:09
 */
public class ZDOrganisation
{
  private java.lang.Long id;
  private java.lang.String externalId;
  private java.lang.String name;
  private java.util.Date createdAt;
  private java.util.Date updatedAt;
  private java.lang.String details;
  private java.lang.String notes;
  private java.lang.Long groupId;
  private java.lang.Boolean sharedTickets;
  private java.lang.Boolean sharedComments;

  //private java.util.List<java.lang.String> domainNames;
  //private java.util.List<java.lang.String> tags;

  public ZDOrganisation()
  {

  }

  public String getExternalId()
  {
    return externalId;
  }

  public void setExternalId( final String externalId )
  {
    this.externalId = externalId;
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

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt( final Date createdAt )
  {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt()
  {
    return updatedAt;
  }

  public void setUpdatedAt( final Date updatedAt )
  {
    this.updatedAt = updatedAt;
  }

  public String getDetails()
  {
    return details;
  }

  public void setDetails( final String details )
  {
    this.details = details;
  }

  public String getNotes()
  {
    return notes;
  }

  public void setNotes( final String notes )
  {
    this.notes = notes;
  }

  public Long getGroupId()
  {
    return groupId;
  }

  public void setGroupId( final Long groupId )
  {
    this.groupId = groupId;
  }

  public Boolean getSharedTickets()
  {
    return sharedTickets;
  }

  public void setSharedTickets( final Boolean sharedTickets )
  {
    this.sharedTickets = sharedTickets;
  }

  public Boolean getSharedComments()
  {
    return sharedComments;
  }

  public void setSharedComments( final Boolean sharedComments )
  {
    this.sharedComments = sharedComments;
  }
}
