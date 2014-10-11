package tomb.supportsim.models;

import org.zendesk.client.v2.model.Role;
import org.zendesk.client.v2.model.TicketRestriction;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 10/10/2014 Time: 15:57
 */
public class ZDUser implements Serializable
{
  private java.lang.Long id;
  private java.lang.String url;
  private java.lang.String name;
  private java.lang.String externalId;
  private java.lang.String alias;
  private java.util.Date createdAt;
  private java.util.Date updatedAt;
  private java.lang.Boolean active;
  private java.lang.Boolean verified;
  private java.lang.Boolean shared;
  private java.lang.Long localeId;
  private java.lang.String timeZone;
  private java.util.Date lastLoginAt;
  private java.lang.String email;
  private java.lang.String phone;
  private java.lang.String signature;
  private java.lang.String details;
  private java.lang.String notes;
  private java.lang.Long organizationId;
  private org.zendesk.client.v2.model.Role role;
  private java.lang.Long customRoleId;
  private java.lang.Boolean moderator;
  private org.zendesk.client.v2.model.TicketRestriction ticketRestriction;
  private java.lang.Boolean onlyPrivateComments;
  private java.lang.Boolean suspended;
  private java.lang.String remotePhotoUrl;

  public ZDUser()
  {

  }

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

  public String getExternalId()
  {
    return externalId;
  }

  public void setExternalId( final String externalId )
  {
    this.externalId = externalId;
  }

  public String getAlias()
  {
    return alias;
  }

  public void setAlias( final String alias )
  {
    this.alias = alias;
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

  public Boolean getActive()
  {
    return active;
  }

  public void setActive( final Boolean active )
  {
    this.active = active;
  }

  public Boolean getVerified()
  {
    return verified;
  }

  public void setVerified( final Boolean verified )
  {
    this.verified = verified;
  }

  public Boolean getShared()
  {
    return shared;
  }

  public void setShared( final Boolean shared )
  {
    this.shared = shared;
  }

  public Long getLocaleId()
  {
    return localeId;
  }

  public void setLocaleId( final Long localeId )
  {
    this.localeId = localeId;
  }

  public String getTimeZone()
  {
    return timeZone;
  }

  public void setTimeZone( final String timeZone )
  {
    this.timeZone = timeZone;
  }

  public Date getLastLoginAt()
  {
    return lastLoginAt;
  }

  public void setLastLoginAt( final Date lastLoginAt )
  {
    this.lastLoginAt = lastLoginAt;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail( final String email )
  {
    this.email = email;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone( final String phone )
  {
    this.phone = phone;
  }

  public String getSignature()
  {
    return signature;
  }

  public void setSignature( final String signature )
  {
    this.signature = signature;
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

  public Long getOrganizationId()
  {
    return organizationId;
  }

  public void setOrganizationId( final Long organizationId )
  {
    this.organizationId = organizationId;
  }

  public Role getRole()
  {
    return role;
  }

  public void setRole( final Role role )
  {
    this.role = role;
  }

  public Long getCustomRoleId()
  {
    return customRoleId;
  }

  public void setCustomRoleId( final Long customRoleId )
  {
    this.customRoleId = customRoleId;
  }

  public Boolean getModerator()
  {
    return moderator;
  }

  public void setModerator( final Boolean moderator )
  {
    this.moderator = moderator;
  }

  public TicketRestriction getTicketRestriction()
  {
    return ticketRestriction;
  }

  public void setTicketRestriction( final TicketRestriction ticketRestriction )
  {
    this.ticketRestriction = ticketRestriction;
  }

  public Boolean getOnlyPrivateComments()
  {
    return onlyPrivateComments;
  }

  public void setOnlyPrivateComments( final Boolean onlyPrivateComments )
  {
    this.onlyPrivateComments = onlyPrivateComments;
  }

  public Boolean getSuspended()
  {
    return suspended;
  }

  public void setSuspended( final Boolean suspended )
  {
    this.suspended = suspended;
  }

  public String getRemotePhotoUrl()
  {
    return remotePhotoUrl;
  }

  public void setRemotePhotoUrl( final String remotePhotoUrl )
  {
    this.remotePhotoUrl = remotePhotoUrl;
  }
}
