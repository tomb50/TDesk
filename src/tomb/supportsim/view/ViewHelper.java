package tomb.supportsim.view;

import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.GroupMembership;
import org.zendesk.client.v2.model.Status;
import tomb.supportsim.control.*;
import tomb.supportsim.models.*;
import tomb.supportsim.util.LinkUtil;
import tomb.supportsim.util.UserOrganisationComparator;

import java.util.*;

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
  private static UserManager userManager = new UserManager();
  private static TicketManager ticketManager = new TicketManager();
  private static OrganisationManager organisationManager = new OrganisationManager();
  private static TopicManager topicManager = new TopicManager();
  private static ForumManager forumManager = new ForumManager();
  private static GroupManager groupManager = new GroupManager();
  private static GroupMembershipManager groupMembershipManager = new GroupMembershipManager();


  public static ZDOrganisation getOrganisation( final Long organisationId )
  {
    return organisationManager.getOrganisation( organisationId );
  }

  public static ZDUser getUser( final Long userId )
  {
    return userManager.getUser( userId );
  }

  public static Integer getTotalTicketCount()
  {
    return ticketManager.getTotalTicketCount();
  }


  public static Integer getTicketCountByState( final Status ticketStateEnum )
  {
    List<ZDTicket> allTickets = getTicketByState( ticketStateEnum );
    return allTickets != null ? allTickets.size() : 0;
  }

  public static List<ZDTicket> getTicketByState( final Status status )
  {
    return ticketManager.getTicketByState( status );
  }

  public static List<ZDTicket> getOpenUnassignedTickets()
  {
    return ticketManager.getOpenUnassignedTickets();
  }

  public static List<ZDTicket> getOpenTicketsForAnalystFromCache( long analystid )
  {
    return ticketManager.getOpenTicketsForAnalystFromCache( analystid );
  }

  public static String getTicketLink( final Long id )
  {
    return LinkUtil.getTicketLink( id );
  }

  public static String getUserLink( final Long id )
  {
    return LinkUtil.getUserLink( id );
  }

  public static String getTopicLink( final Long id )
  {
    return LinkUtil.getTopicLink( id );
  }


  public static List<ZDUser> getOrderedCustomers()
  {
    return userManager.getOrderedCustomers();
  }

  public static List<ZDTopic> getOrderedTopics()
  {
    return topicManager.getOrderedTopics();
  }

  public static ZDForum getForum( final Long forumId )
  {
    return forumManager.getForum( forumId );
  }

  public static String getForumName( final Long forumId )
  {
    return getForum( forumId ) != null ? getForum( forumId ).getName() : "";
  }

  public static String getUserName( final long id )
  {
    return getUser( id ) != null ? getUser( id ).getName() : "";
  }


  public static List<ZDUser> getSupportAnalysts()
  {
    Group charGroup = getGroup( "Character" );
    Group firstLine = getGroup( "Support" );
    Group javaGroup = getGroup( "Java" );

    List<GroupMembership> groupMemberships = getGroupMemberships( charGroup.getId() );
    groupMemberships.addAll( getGroupMemberships( firstLine.getId() ) );
    groupMemberships.addAll( getGroupMemberships( javaGroup.getId() ) );

    List<ZDUser> agents = new ArrayList<>();
    for ( GroupMembership groupMembership : groupMemberships )
    {
      ZDUser agent = userManager.getUser( groupMembership.getUserId() );

      if ( agent != null && !agent.getName().equals( "Helen sowerby" ) )
      {
        agents.add( agent );
      }
    }

    List<ZDUser> cleanList = new ArrayList<ZDUser>();
    for ( ZDUser user : agents )
    {
      if ( !cleanList.contains( user ) )
      {
        cleanList.add( user );
      }
    }
    return cleanList;
  }

  private static List<GroupMembership> getGroupMemberships( final Long id )
  {
    return groupMembershipManager.getGroupMemberships( id );
  }


  public static Integer getOpenTicketCount( final String groupName )
  {
    final Map<Long, ZDTicket> suitableTickets = getOpenTicketMap( groupName );
    return suitableTickets != null ? suitableTickets.size() : 0;
  }

  public static Map<Long, ZDTicket> getOpenTicketMap( final String groupName )
  {
    Group group = getGroup( groupName );
    Map<Long, ZDTicket> suitableTickets = new HashMap<>();
    List<ZDTicket> allTickets = ticketManager.getTicketByState( Status.OPEN );

    for ( ZDTicket ticket : allTickets )
    {
      if ( ticket.getGroupId().equals( group.getId() ) )
      {
        suitableTickets.put( ticket.getId(), ticket );
      }
    }
    return suitableTickets;
  }

  private static Group getGroup( final String name )
  {
    return groupManager.getGroup( name );
  }


  public static Integer getLargestWorkload()
  {
    Integer largesQueueSize = 0;
    for ( ZDUser user : getSupportAnalysts() )
    {
      if ( user != null )
      {
        Integer queueSize = getTicketCount( user.getId(), Status.OPEN );
        if ( queueSize > largesQueueSize ) largesQueueSize = queueSize;
      }
    }
    return largesQueueSize;
  }


  public static Integer getTicketCount( final Long userId, final Status status )
  {
    List<ZDTicket> tickets = getTicketByState( status );
    List<ZDTicket> ticketsForUser = new ArrayList<>();
    for ( ZDTicket ticket : tickets )
    {
      if ( ticket.getAssigneeId() != null && ticket.getAssigneeId().equals( userId ) )
      {
        ticketsForUser.add( ticket );
      }
    }
    return ticketsForUser.size();
  }


  public static String getGroupName( final Long groupId )
  {
    final Group group = groupManager.getGroup( groupId );

    return group != null ? group.getName() : "";
  }
}
