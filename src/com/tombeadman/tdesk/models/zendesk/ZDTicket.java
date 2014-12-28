package com.tombeadman.tdesk.models.zendesk;

import com.tombeadman.tdesk.util.EqualsUtil;
import org.zendesk.client.v2.model.Priority;
import org.zendesk.client.v2.model.Status;
import org.zendesk.client.v2.model.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 10/10/2014 Time: 16:24
 */
public class ZDTicket implements Serializable

{
  public ZDTicket()
  {
  }

  private Long id;
  private String url;
  private String subject;
  private String description;
  private Status status;
  private Long requesterId;
  private Long organizationId;
  private Date createdAt;
  private Date updatedAt;
  private java.lang.String externalId;
  private org.zendesk.client.v2.model.Type type;
  private org.zendesk.client.v2.model.Priority priority;
  private java.lang.String recipient;
  private java.lang.Long submitterId;
  private java.lang.Long assigneeId;
  private java.lang.Long groupId;
  private java.lang.Long forumTopicId;
  private java.lang.Long problemId;
  private boolean hasIncidents;
  private java.util.Date dueAt;
  //private java.util.List<java.lang.String> tags;
  //private java.util.List<org.zendesk.client.v2.model.CustomFieldValue> customFields;
  //private org.zendesk.client.v2.model.SatisfactionRating satisfactionRating;
  //private java.util.List<java.lang.Long> sharingAgreementIds;
  //private java.util.List<java.lang.Long> followupIds;
  //private java.lang.Long ticketFormId;
  //protected Comment comment;
  //protected org.zendesk.client.v2.model.Ticket.Requester requester;
  // private java.util.List<java.lang.Long> collaboratorIds;
  //private Via via;


  public String getUrl()
  {
    return url;
  }

  public void setUrl( final String url )
  {
    this.url = url;
  }

  public Long getId()
  {
    return id;
  }

  public void setId( final Long id )
  {
    this.id = id;
  }

  public String getSubject()
  {
    return subject;
  }

  public void setSubject( final String subject )
  {
    this.subject = subject;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( final String description )
  {
    this.description = description;
  }

  public Status getStatus()
  {
    return status;
  }

  public void setStatus( final Status status )
  {
    this.status = status;
  }

  public Long getRequesterId()
  {
    return requesterId;
  }

  public void setRequesterId( final Long requesterId )
  {
    this.requesterId = requesterId;
  }

  public Long getOrganizationId()
  {
    return organizationId;
  }

  public void setOrganizationId( final Long organizationId )
  {
    this.organizationId = organizationId;
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

  public String getExternalId()
  {
    return externalId;
  }

  public void setExternalId( final String externalId )
  {
    this.externalId = externalId;
  }

  public Type getType()
  {
    return type;
  }

  public void setType( final Type type )
  {
    this.type = type;
  }

  public Priority getPriority()
  {
    return priority;
  }

  public void setPriority( final Priority priority )
  {
    this.priority = priority;
  }

  public String getRecipient()
  {
    return recipient;
  }

  public void setRecipient( final String recipient )
  {
    this.recipient = recipient;
  }

  public Long getSubmitterId()
  {
    return submitterId;
  }

  public void setSubmitterId( final Long submitterId )
  {
    this.submitterId = submitterId;
  }

  public Long getAssigneeId()
  {
    return assigneeId;
  }

  public void setAssigneeId( final Long assigneeId )
  {
    this.assigneeId = assigneeId;
  }

  public Long getGroupId()
  {
    return groupId;
  }

  public void setGroupId( final Long groupId )
  {
    this.groupId = groupId;
  }

  public Long getForumTopicId()
  {
    return forumTopicId;
  }

  public void setForumTopicId( final Long forumTopicId )
  {
    this.forumTopicId = forumTopicId;
  }

  public Long getProblemId()
  {
    return problemId;
  }

  public void setProblemId( final Long problemId )
  {
    this.problemId = problemId;
  }

  public boolean isHasIncidents()
  {
    return hasIncidents;
  }

  public void setHasIncidents( final boolean hasIncidents )
  {
    this.hasIncidents = hasIncidents;
  }

  public Date getDueAt()
  {
    return dueAt;
  }

  public void setDueAt( final Date dueAt )
  {
    this.dueAt = dueAt;
  }

  @Override public boolean equals(Object aThat) {
    //check for self-comparison
    if ( this == aThat ) return true;

    //use instanceof instead of getClass here for two reasons
    //1. if need be, it can match any supertype, and not just one class;
    //2. it renders an explict check for "that == null" redundant, since
    //it does the check for null already - "null instanceof [type]" always
    //returns false. (See Effective Java by Joshua Bloch.)
    if ( !(aThat instanceof ZDTicket) ) return false;
    //Alternative to the above line :
    //if ( aThat == null || aThat.getClass() != this.getClass() ) return false;

    //cast to native object is now safe
    ZDTicket that = (ZDTicket)aThat;

    //now a proper field-by-field evaluation can be made
    return
      EqualsUtil.areEqual( this.id, that.id ) &&
        EqualsUtil.areEqual(this.url, that.url) &&
        EqualsUtil.areEqual(this.subject, that.subject) &&
        EqualsUtil.areEqual(this.description, that.description) &&
        EqualsUtil.areEqual(this.status, that.status) &&
        EqualsUtil.areEqual(this.requesterId, that.requesterId) &&
        EqualsUtil.areEqual(this.organizationId, that.organizationId) &&
        EqualsUtil.areEqual(this.createdAt, that.createdAt) &&
        EqualsUtil.areEqual(this.updatedAt, that.updatedAt) &&
        EqualsUtil.areEqual(this.externalId, that.externalId) &&
        EqualsUtil.areEqual(this.type, that.type) &&
        EqualsUtil.areEqual(this.priority, that.priority) &&
        EqualsUtil.areEqual(this.recipient, that.recipient) &&
        EqualsUtil.areEqual(this.submitterId, that.submitterId) &&
        EqualsUtil.areEqual(this.assigneeId, that.assigneeId) &&
        EqualsUtil.areEqual(this.groupId, that.groupId) &&
        EqualsUtil.areEqual(this.forumTopicId, that.forumTopicId) &&
        EqualsUtil.areEqual(this.problemId, that.problemId) &&
        EqualsUtil.areEqual(this.hasIncidents, that.hasIncidents) &&
        EqualsUtil.areEqual( this.dueAt, that.dueAt );
        // For reference
        // Arrays.equals( this.fMaintenanceChecks, that.fMaintenanceChecks );
  }
}
