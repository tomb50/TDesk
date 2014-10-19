package tomb.supportsim.view;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.GroupMembership;
import org.zendesk.client.v2.model.Status;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.controllers.TicketReporter;
import tomb.supportsim.models.*;
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
    return cache.getTicketMap().size();
  }

  public static Integer getTotalOpenTicketCount()
  {
    return TicketReporter.getTotalOpenTicketCount();
  }

  public static Integer getTicketCountByState( final Status ticketStateEnum )
  {
    List<ZDTicket> allTickets = getTicketByState( ticketStateEnum );
    return allTickets != null ? allTickets.size() : 0;
  }

  public static List<ZDTicket> getTicketByState(final Status status)
  {
    return cache.getTickets( status );
  }

  public static List<ZDTicket> getOpenUnassignedTickets()
  {
    return cache.getUnassignedTickets( Status.OPEN );

  }

  public static List<ZDTicket> getOpenTicketsForAnalystFromCache( long analystid )
  {
   return cache.getTickets( Status.OPEN, analystid );
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

  public static List<ZDUser> getJavaAnalysts()
  {
    Group javaGroup = getGroup( "Java" );

    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.eq( "groupId", javaGroup.getId() ) );
    List<GroupMembership> groupMemberships = HibernateUtil.getEntityList( GroupMembership.class, restrictions );

    List<ZDUser> javaAgents = new ArrayList<>();
    for ( GroupMembership groupMembership : groupMemberships )
    {
      ZDUser javaAnalyst = cache.getUserMap().get( groupMembership.getUserId() );
      if (javaAnalyst != null && !javaAnalyst.getName().equals( "Helen sowerby"))
      javaAgents.add( cache.getUserMap().get( groupMembership.getUserId() ) );
    }

    return javaAgents;
  }


  public static List<ZDUser> getNonJavaAnalysts()
  {

    Group charGroup = getGroup( "Character" );
    Group firstLine = getGroup( "Support" );

    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.or( Restrictions.eq( "groupId", charGroup.getId() ),
                                       Restrictions.eq( "groupId", firstLine.getId() ) ) );
    List<GroupMembership> groupMemberships = HibernateUtil.getEntityList( GroupMembership.class, restrictions );

    List<ZDUser> agents = new ArrayList<>();
    for ( GroupMembership groupMembership : groupMemberships )
    {
      ZDUser agent = cache.getUserMap().get( groupMembership.getUserId() );
      if ( agent != null && !agent.getName().equals( "Helen sowerby" ) )
      {
        agents.add( cache.getUserMap().get( groupMembership.getUserId() ) );
      }
    }

    return agents;
  }

  public static List<ZDUser> getSupportAnalysts()
  {
    Group charGroup = getGroup( "Character" );
    Group firstLine = getGroup( "Support" );
    Group javaGroup = getGroup( "Java" );

    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.or(
      Restrictions.or( Restrictions.eq( "groupId", charGroup.getId() ),
                       Restrictions.eq( "groupId", firstLine.getId() ) ),
      Restrictions.eq( "groupId", javaGroup.getId() ) ) );
    List<GroupMembership> groupMemberships = HibernateUtil.getEntityList( GroupMembership.class, restrictions );

    List<ZDUser> agents = new ArrayList<>();
    for ( GroupMembership groupMembership : groupMemberships )
    {
      ZDUser agent = cache.getUserMap().get( groupMembership.getUserId() );
      if ( agent != null && !agent.getName().equals( "Helen sowerby" ) )
      {
        agents.add( cache.getUserMap().get( groupMembership.getUserId() ) );
      }
    }

    List<ZDUser> cleanlist = new ArrayList<ZDUser>();
    for ( ZDUser user : agents )
    {
      if ( !cleanlist.contains( user ) )
      {
        cleanlist.add( user );
      }
    }

    return cleanlist;
  }


  public static Integer getOpenTicketCount(final String groupName)
  {
   final Map<Long, ZDTicket> suitableTickets = getOpenTicketMap( groupName );
   return suitableTickets != null ? suitableTickets.size() : 0;
  }

  public static Map<Long,ZDTicket> getOpenTicketMap(final String groupName)
  {
    Group group = getGroup( groupName );
    Map<Long, ZDTicket> suitableTickets = new HashMap<>();
    List<ZDTicket> allTickets = cache.getTickets( Status.OPEN );

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
    Group thisGroup = null;
    List<Group> groups = cache.getGroups();
    for ( Group group : groups )
    {
      if ( group.getName().equals( name ) )
      {
        thisGroup = group;
      }
    }
    return thisGroup;
  }


  public static Integer getLargestJavaWorkload()
  {
    Integer largesQueueSize = 0;
    for ( ZDUser user : getJavaAnalysts() )
    {
      if ( user != null )
      {
        Integer queueSize = TicketReporter.getTicketCount( user.getId(), Status.OPEN );
        if ( queueSize > largesQueueSize ) largesQueueSize = queueSize;
      }
    }
    return largesQueueSize;
  }

  public static Integer getLargestNonJavaWorkload()
  {
    Integer largesQueueSize = 0;
    for ( ZDUser user : getNonJavaAnalysts() )
    {
      if ( user != null )
      {
        Integer queueSize = TicketReporter.getTicketCount( user.getId(), Status.OPEN );
        if ( queueSize > largesQueueSize ) largesQueueSize = queueSize;
      }
    }
    return largesQueueSize;

  }

  public static Cache getCache()
  {
    return cache;
  }


  //toto fix NPE here
  public static String getGroupName( final Long groupId )
  {
    return cache.getGroupMap().get( groupId ).getName();
  }
}
