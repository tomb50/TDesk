package tomb.supportsim.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 11/10/2014 Time: 21:08
 */
public class ZDForum implements Serializable
{
  public ZDForum(){}

  private java.lang.Long id;
  private java.lang.String url;
  private java.lang.String name;
  private java.lang.String description;
  private java.lang.Long categoryId;
  private java.lang.Long organizationId;
  private java.lang.Long localeId;
  private java.lang.Boolean locked;
  private java.lang.Long unansweredTopics;
  private java.lang.Long position;
  private java.lang.String forumType;
  private java.lang.String access;
  private java.util.Date createdAt;
  private java.util.Date updatedAt;

  //private java.util.List<java.lang.String> tags;


  public Long getId()
  {
    return id;
  }

  public void setId( final Long id )
  {
    this.id = id;
  }

  public String getUrl()
  {
    return url;
  }

  public void setUrl( final String url )
  {
    this.url = url;
  }

  public String getName()
  {
    return name;
  }

  public void setName( final String name )
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( final String description )
  {
    this.description = description;
  }

  public Long getCategoryId()
  {
    return categoryId;
  }

  public void setCategoryId( final Long categoryId )
  {
    this.categoryId = categoryId;
  }

  public Long getOrganizationId()
  {
    return organizationId;
  }

  public void setOrganizationId( final Long organizationId )
  {
    this.organizationId = organizationId;
  }

  public Long getLocaleId()
  {
    return localeId;
  }

  public void setLocaleId( final Long localeId )
  {
    this.localeId = localeId;
  }

  public Boolean getLocked()
  {
    return locked;
  }

  public void setLocked( final Boolean locked )
  {
    this.locked = locked;
  }

  public Long getUnansweredTopics()
  {
    return unansweredTopics;
  }

  public void setUnansweredTopics( final Long unansweredTopics )
  {
    this.unansweredTopics = unansweredTopics;
  }

  public Long getPosition()
  {
    return position;
  }

  public void setPosition( final Long position )
  {
    this.position = position;
  }

  public String getForumType()
  {
    return forumType;
  }

  public void setForumType( final String forumType )
  {
    this.forumType = forumType;
  }

  public String getAccess()
  {
    return access;
  }

  public void setAccess( final String access )
  {
    this.access = access;
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
}
