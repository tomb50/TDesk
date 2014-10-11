package tomb.supportsim.models;

import org.zendesk.client.v2.model.Organization;

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

  public static ZDUser toUser( org.zendesk.client.v2.model.User zdUser)
  {
    final ZDUser user = new ZDUser();
    user.setId( zdUser.getId() );
    user.setUrl( zdUser.getUrl() );
    user.setName( zdUser.getName() );
    user.setExternalId( zdUser.getExternalId() );
    user.setAlias( zdUser.getAlias() );
    user.setCreatedAt( zdUser.getCreatedAt() );
    user.setUpdatedAt( zdUser.getUpdatedAt() );
    user.setActive( zdUser.getActive() );
    user.setVerified( zdUser.getVerified() );
    user.setShared( zdUser.getShared() );
    user.setLocaleId( zdUser.getLocaleId() );
    user.setTimeZone( zdUser.getTimeZone() );
    user.setLastLoginAt( zdUser.getLastLoginAt() );
    user.setEmail( zdUser.getEmail() );
    user.setPhone( zdUser.getPhone() );
    user.setSignature( zdUser.getSignature() );
    user.setDetails( zdUser.getDetails() );
    user.setNotes( zdUser.getNotes() );
    user.setOrganizationId( zdUser.getOrganizationId() );
    user.setRole( zdUser.getRole() );
    user.setCustomRoleId( zdUser.getCustomRoleId() );
    user.setModerator( zdUser.getModerator() );
    user.setTicketRestriction( zdUser.getTicketRestriction() );
    user.setOnlyPrivateComments( zdUser.getOnlyPrivateComments() );
    user.setSuspended( zdUser.getSuspended() );
    user.setRemotePhotoUrl( zdUser.getRemotePhotoUrl() );

    return user;
  }

  public static ZDTicket toTicket(final org.zendesk.client.v2.model.Ticket zdTicket)
  {
    final ZDTicket ticket = new ZDTicket();


    ticket.setId( zdTicket.getId() );
    ticket.setUrl( zdTicket.getUrl() );
    ticket.setSubject( zdTicket.getSubject() );
    ticket.setDescription( zdTicket.getDescription() );
    ticket.setStatus( zdTicket.getStatus() );
    ticket.setRequesterId( zdTicket.getRequesterId() );
    ticket.setOrganizationId( zdTicket.getOrganizationId() );
    ticket.setCreatedAt( zdTicket.getCreatedAt() );
    ticket.setUpdatedAt( zdTicket.getUpdatedAt() );
    ticket.setExternalId( zdTicket.getExternalId() );
    ticket.setType( zdTicket.getType() );
    ticket.setPriority( zdTicket.getPriority() );
    ticket.setRecipient( zdTicket.getRecipient() );
    ticket.setSubmitterId( zdTicket.getSubmitterId() );
    ticket.setAssigneeId( zdTicket.getAssigneeId() );
    ticket.setGroupId( zdTicket.getGroupId() );
    ticket.setForumTopicId( zdTicket.getForumTopicId() );
    ticket.setProblemId( zdTicket.getProblemId() );
    ticket.setHasIncidents( zdTicket.isHasIncidents() );
    ticket.setDueAt( zdTicket.getDueAt() );

    return ticket;
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


}
