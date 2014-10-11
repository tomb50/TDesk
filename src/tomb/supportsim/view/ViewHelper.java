package tomb.supportsim.view;

import org.zendesk.client.v2.model.Status;
import tomb.supportsim.controllers.TicketReporter;
import tomb.supportsim.models.*;
import tomb.supportsim.util.UserOrganisationComparator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 25/08/2014 Time: 17:53
 */
public class ViewHelper
{
  /*
   *
   * Cache for speed //todo improve cache mechanism
   *
   */
 private static Cache cache = new Cache();



  public static ZDOrganisation getOrganisation(final Long organisationId)
  {
    return cache.getOrganisationMap().get( organisationId );
  }
  public static ZDUser getUser(final Long userId)
  {
    return cache.getUserMap().get( userId );
  }

  public static ZDTicket getTicket(final Long ticketId)
  {
    return cache.getTicketMap().get( ticketId );
  }

  public static Map<Long,ZDOrganisation> getOrganisationMap()
  {
    return cache.getOrganisationMap();
  }

  public static Map<Long,ZDUser> getUserMap()
  {
    return cache.getUserMap();
  }

  public static Map<Long,ZDTicket> getTicketMap()
  {
    return cache.getTicketMap();
  }

  public static Integer getTotalTicketCount()
  {
    return TicketReporter.getTotalTicketCount();
  }

  public static Integer getTotalOpenTicketCount()
  {
    return TicketReporter.getTotalOpenTicketCount();
  }




  public static Integer getTicketCountByState( final Status ticketStateEnum )
  {
    return TicketReporter.getTicketCountByState( ticketStateEnum );
  }




  public static List<ZDTicket> getTicketByState(final Status status)
  {
    return TicketReporter.getTicketsByState( status );
  }

  public static List<ZDTicket> getOpenTickets()
  {
    return cache.getTickets( Status.OPEN );
  }

  //todo get rid of the literals here
  public static String getTicketLink( final Long id )
  {
    return "http://resultgroup.zendesk.com/tickets/".concat( String.valueOf( id ) );
  }

  public static String getUserLink(final Long id)
  {
    return "http://resultgroup.zendesk.com/users/".concat( String.valueOf( id ) );
  }

  public static String getTopicLink( final Long id )
  {
    return "http://resultgroup.zendesk.com/entries/".concat( String.valueOf( id ));
  }

  public static List<ZDUser> getCustomers()
  {
    return cache.getUsers();
  }

  public static List<ZDUser> getOrderedCustomers()
  {
    List<ZDUser> users = getCustomers();
    for ( Iterator it = users.iterator(); it.hasNext(); )
    {
      ZDUser user = (ZDUser) it.next();
      if(user.getOrganizationId() == null) it.remove();
    }
    Collections.sort( users, new UserOrganisationComparator() );
    return users;
  }

  public static List<ZDTopic> getOrderedTopics()
  {
    List<ZDTopic> topics =  cache.getTopics();
    Collections.sort( topics, new TopicForumComparator() );
    return topics;
  }

  public static ZDForum getForum(final Long forumId)
  {
    return cache.getForumMap().get( forumId );
  }

  public static String getForumName( final Long forumId )
  {
    return getForum( forumId ) != null ? getForum(forumId  ).getName() : "";
  }

  public static String getUserName(final long id)
  {
    return getUser( id ) != null ? getUser( id ).getName() : "";
  }
}
