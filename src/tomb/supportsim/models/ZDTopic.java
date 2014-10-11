package tomb.supportsim.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 11/10/2014 Time: 21:08
 */
public class ZDTopic implements Serializable
{
  public ZDTopic(){}

  private java.lang.Long id;
  private java.lang.String url;
  private java.lang.String title;
  private java.lang.String body;
  private String topicType;
  private java.lang.Long submitterId;
  private java.lang.Long updaterId;
  private java.lang.Long forumId;
  private java.lang.Boolean locked;
  private java.lang.Boolean pinned;
  private java.lang.Boolean highlighted;
  private java.lang.Boolean answered;
  private java.lang.Long commentCount;
  private java.lang.Long position;
  private java.util.Date createdAt;
  private java.util.Date updatedAt;

  //private java.util.List<java.lang.String> searchPhrases;
  //private java.util.List<java.lang.String> tags;
  // private java.util.List<org.zendesk.client.v2.model.Attachment> attachments;
  //private java.util.List<org.zendesk.client.v2.model.Attachment.Upload> uploads;


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

  public String getTitle()
  {
    return title;
  }

  public void setTitle( final String title )
  {
    this.title = title;
  }

  public String getBody()
  {
    return body;
  }

  public void setBody( final String body )
  {
    this.body = body;
  }

  public String getTopicType()
  {
    return topicType;
  }

  public void setTopicType( final String topicType )
  {
    this.topicType = topicType;
  }

  public Long getSubmitterId()
  {
    return submitterId;
  }

  public void setSubmitterId( final Long submitterId )
  {
    this.submitterId = submitterId;
  }

  public Long getUpdaterId()
  {
    return updaterId;
  }

  public void setUpdaterId( final Long updaterId )
  {
    this.updaterId = updaterId;
  }

  public Long getForumId()
  {
    return forumId;
  }

  public void setForumId( final Long forumId )
  {
    this.forumId = forumId;
  }

  public Boolean getLocked()
  {
    return locked;
  }

  public void setLocked( final Boolean locked )
  {
    this.locked = locked;
  }

  public Boolean getPinned()
  {
    return pinned;
  }

  public void setPinned( final Boolean pinned )
  {
    this.pinned = pinned;
  }

  public Boolean getHighlighted()
  {
    return highlighted;
  }

  public void setHighlighted( final Boolean highlighted )
  {
    this.highlighted = highlighted;
  }

  public Boolean getAnswered()
  {
    return answered;
  }

  public void setAnswered( final Boolean answered )
  {
    this.answered = answered;
  }

  public Long getCommentCount()
  {
    return commentCount;
  }

  public void setCommentCount( final Long commentCount )
  {
    this.commentCount = commentCount;
  }

  public Long getPosition()
  {
    return position;
  }

  public void setPosition( final Long position )
  {
    this.position = position;
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
