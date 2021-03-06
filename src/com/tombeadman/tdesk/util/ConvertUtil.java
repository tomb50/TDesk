package com.tombeadman.tdesk.util;

import com.atlassian.jira.rest.client.api.domain.*;
import com.tombeadman.tdesk.models.jira.*;
import com.tombeadman.tdesk.models.zendesk.*;
import org.zendesk.client.v2.model.Forum;
import org.zendesk.client.v2.model.Organization;
import org.zendesk.client.v2.model.Topic;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 10/10/2014 Time: 16:04
 */
public class ConvertUtil
{
  /*
   * Utility class for converting from full ZD objects to App objects where appropriate.
   * In most cases we don't need to bother with the all the ZD object details, especially those with
   * Custom data structures, which are non-trivial to persist.
   *
   * Those details should be present but commented out on the Model, and so can be identified and added later
   * if required.
   */

  public static ZDUser toUser( org.zendesk.client.v2.model.User user )
  {
    final ZDUser zdUser = new ZDUser();
    zdUser.setId( user.getId() );
    zdUser.setUrl( user.getUrl() );
    zdUser.setName( user.getName() );
    zdUser.setExternalId( user.getExternalId() );
    zdUser.setAlias( user.getAlias() );
    zdUser.setCreatedAt( user.getCreatedAt() );
    zdUser.setUpdatedAt( user.getUpdatedAt() );
    zdUser.setActive( user.getActive() );
    zdUser.setVerified( user.getVerified() );
    zdUser.setShared( user.getShared() );
    zdUser.setLocaleId( user.getLocaleId() );
    zdUser.setTimeZone( user.getTimeZone() );
    zdUser.setLastLoginAt( user.getLastLoginAt() );
    zdUser.setEmail( user.getEmail() );
    zdUser.setPhone( user.getPhone() );
    zdUser.setSignature( user.getSignature() );
    zdUser.setDetails( user.getDetails() );
    zdUser.setNotes( user.getNotes() );
    zdUser.setOrganizationId( user.getOrganizationId() );
    zdUser.setRole( user.getRole() );
    zdUser.setCustomRoleId( user.getCustomRoleId() );
    zdUser.setModerator( user.getModerator() );
    zdUser.setTicketRestriction( user.getTicketRestriction() );
    zdUser.setOnlyPrivateComments( user.getOnlyPrivateComments() );
    zdUser.setSuspended( user.getSuspended() );
    zdUser.setRemotePhotoUrl( user.getRemotePhotoUrl() );
    return zdUser;
  }

  public static ZDTicket toTicket( final org.zendesk.client.v2.model.Ticket ticket )
  {
    final ZDTicket zdTicket = new ZDTicket();
    zdTicket.setId( ticket.getId() );
    zdTicket.setUrl( ticket.getUrl() );
    zdTicket.setSubject( ticket.getSubject() );
    zdTicket.setDescription( ticket.getDescription() );
    zdTicket.setStatus( ticket.getStatus() );
    zdTicket.setRequesterId( ticket.getRequesterId() );
    zdTicket.setOrganizationId( ticket.getOrganizationId() );
    zdTicket.setCreatedAt( ticket.getCreatedAt() );
    zdTicket.setUpdatedAt( ticket.getUpdatedAt() );
    zdTicket.setExternalId( ticket.getExternalId() );
    zdTicket.setType( ticket.getType() );
    zdTicket.setPriority( ticket.getPriority() );
    zdTicket.setRecipient( ticket.getRecipient() );
    zdTicket.setSubmitterId( ticket.getSubmitterId() );
    zdTicket.setAssigneeId( ticket.getAssigneeId() );
    zdTicket.setGroupId( ticket.getGroupId() );
    zdTicket.setForumTopicId( ticket.getForumTopicId() );
    zdTicket.setProblemId( ticket.getProblemId() );
    zdTicket.setHasIncidents( ticket.isHasIncidents() );
    zdTicket.setDueAt( ticket.getDueAt() );
    return zdTicket;
  }

  public static ZDOrganisation toOrganisation( final Organization organization )
  {
    final ZDOrganisation zdOrganisation = new ZDOrganisation();
    zdOrganisation.setId( organization.getId() );
    zdOrganisation.setName( organization.getName() );
    zdOrganisation.setCreatedAt( organization.getCreatedAt() );
    zdOrganisation.setUpdatedAt( organization.getUpdatedAt() );
    zdOrganisation.setDetails( organization.getDetails() );
    zdOrganisation.setNotes( organization.getNotes() );
    zdOrganisation.setGroupId( organization.getGroupId() );
    zdOrganisation.setSharedTickets( organization.getSharedTickets() );
    zdOrganisation.setSharedComments( organization.getSharedComments() );
    return zdOrganisation;
  }

  public static ZDTopic toTopic( final Topic topic )
  {
    final ZDTopic zdTopic = new ZDTopic();
    zdTopic.setId( topic.getId() );
    zdTopic.setUrl( topic.getUrl() );
    zdTopic.setTitle( topic.getTitle() );
    zdTopic.setBody( topic.getBody() );
    zdTopic.setTopicType( topic.getTopicType() );
    zdTopic.setSubmitterId( topic.getSubmitterId() );
    zdTopic.setUpdaterId( topic.getUpdaterId() );
    zdTopic.setForumId( topic.getForumId() );
    zdTopic.setLocked( topic.getLocked() );
    zdTopic.setPinned( topic.getPinned() );
    zdTopic.setHighlighted( topic.getHighlighted() );
    zdTopic.setAnswered( topic.getAnswered() );
    zdTopic.setCommentCount( topic.getCommentCount() );
    zdTopic.setPosition( topic.getPosition() );
    zdTopic.setCreatedAt( topic.getCreatedAt() );
    zdTopic.setUpdatedAt( topic.getUpdatedAt() );
    return zdTopic;
  }

  public static ZDForum toForum( final Forum forum )
  {
    final ZDForum zdForum = new ZDForum();
    zdForum.setId( forum.getId() );
    zdForum.setUrl( forum.getUrl() );
    zdForum.setName( forum.getName() );
    zdForum.setDescription( forum.getDescription() );
    zdForum.setCategoryId( forum.getCategoryId() );
    zdForum.setOrganizationId( forum.getOrganizationId() );
    zdForum.setLocaleId( forum.getLocaleId() );
    zdForum.setLocked( forum.getLocked() );
    zdForum.setUnansweredTopics( forum.getUnansweredTopics() );
    zdForum.setPosition( forum.getPosition() );
    zdForum.setForumType( forum.getForumType() );
    zdForum.setAccess( forum.getAccess() );
    zdForum.setCreatedAt( forum.getCreatedAt() );
    zdForum.setUpdatedAt( forum.getUpdatedAt() );
    return zdForum;
  }

  public static IssueShell toShellIssue( final Issue issue )
  {
    final IssueShell issueShell = new IssueShell();
    issueShell.setSelf( issue.getSelf() );
    issueShell.setKey( issue.getKey() );
    issueShell.setId( issue.getId() );
    issueShell.setStatus( toStatusShell( issue.getStatus() ) );
    issueShell.setIssueTypeShell( toIssueTypeShell( issue.getIssueType() ) );
    issueShell.setProject( toBasicProjectShell( issue.getProject() ) );
    issueShell.setTransitionsUri( issue.getTransitionsUri() );
    issueShell.setSummary( issue.getSummary() );
    issueShell.setDescription( issue.getDescription() );
    issueShell.setReporter( toUserShell( issue.getReporter() ) );
    issueShell.setAssignee( toUserShell( issue.getAssignee() ) );
    issueShell.setResolutionShell( toResolutionShell( issue.getResolution() ) );
    issueShell.setCreationDate( issue.getCreationDate() );
    issueShell.setUpdateDate( issue.getUpdateDate() );
    return issueShell;
  }

  public static UserShell toUserShell( final User user )
  {
    final UserShell userShell = new UserShell();
    if ( user != null )
    {
      userShell.setSelf( user.getSelf() );
      userShell.setDisplayName( user.getDisplayName() );
      userShell.setEmailAddress( user.getEmailAddress() );
    }
    return userShell;
  }

  public static ResolutionShell toResolutionShell( final Resolution resolution )
  {
    final ResolutionShell resolutionShell = new ResolutionShell();
    if ( resolution != null )
    {
      resolutionShell.setSelf( resolution.getSelf() );
      resolutionShell.setDescription( resolution.getDescription() );
      resolutionShell.setId( resolution.getId() );
      resolutionShell.setName( resolution.getName() );
    }
    return resolutionShell;
  }

  public static IssueTypeShell toIssueTypeShell( final IssueType issueType )
  {
    final IssueTypeShell issueTypeShell = new IssueTypeShell();
    if ( issueType != null )
    {
      issueTypeShell.setId( issueType.getId() );
      issueTypeShell.setSelf( issueType.getSelf() );
      issueTypeShell.setDescription( issueType.getDescription() );
      issueTypeShell.setName( issueType.getName() );
      issueTypeShell.setSubtask( issueType.isSubtask() );
    }
    return issueTypeShell;
  }

  public static BasicProjectShell toBasicProjectShell( final BasicProject basicProject )
  {
    final BasicProjectShell basicProjectShell = new BasicProjectShell();
    if ( basicProject != null )
    {
      basicProjectShell.setName( basicProject.getName() );
      basicProjectShell.setId( basicProject.getId() );
      basicProjectShell.setSelf( basicProject.getSelf() );
      basicProjectShell.setKey( basicProject.getKey() );
    }
    return basicProjectShell;
  }

  public static StatusShell toStatusShell( final Status status )
  {
    final StatusShell statusShell = new StatusShell();
    if ( status != null )
    {
      statusShell.setName( status.getName() );
      statusShell.setSelf( status.getSelf() );
      statusShell.setId( status.getId() );
      statusShell.setDescription( status.getDescription() );
    }
    return statusShell;
  }
}
