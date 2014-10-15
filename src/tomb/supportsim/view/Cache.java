package tomb.supportsim.view;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.GroupMembership;
import org.zendesk.client.v2.model.Status;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 10/10/2014 Time: 22:20
 */

/*
 * For common cases when it is easier and quicker than reading from DB
 */
public class Cache
{

  private Map<Long, ZDOrganisation> organisationMap;
  private Map<Long, ZDUser> userMap;
  private Map<Long, ZDTicket> ticketMap;
  private Map<Long, ZDTopic> topicMap;
  private Map<Long, ZDForum> forumMap;
  private Map<Long, Group> groupMap;
  private Map<Long, GroupMembership> groupMembershipMap;


  public Map<Long, Group> getGroupMap()
  {
    if ( groupMap == null || groupMap.isEmpty())
    {
      groupMap = new HashMap<>();
      final List<Group> groupList = HibernateUtil.getEntityList( Group.class );
      for ( Group group : groupList )
      {
        groupMap.put( group.getId(), group );
      }
    }
    return groupMap;
  }


  public Map<Long, GroupMembership> getGroupMembershipMap()
  {
    if ( groupMembershipMap == null || groupMembershipMap.isEmpty())
    {
      groupMembershipMap = new HashMap<>();
      final List<GroupMembership> groupMembershipList = HibernateUtil.getEntityList( GroupMembership.class );
      for ( GroupMembership groupMembership : groupMembershipList )
      {
        groupMembershipMap.put( groupMembership.getId(), groupMembership );
      }
    }
    return groupMembershipMap;
  }

  public List<GroupMembership> getGroupMemberships()
  {
    return new ArrayList<>( getGroupMembershipMap().values() );
  }

  public Map<Long, ZDOrganisation> getOrganisationMap()
  {
    if ( organisationMap == null )
    {
      organisationMap = new HashMap<>();
      populateCustomerMap();
    }
    return organisationMap;
  }

  private void populateCustomerMap()
  {
    List<ZDOrganisation> organisationList = HibernateUtil.getEntityList( ZDOrganisation.class );
    for ( ZDOrganisation organisation : organisationList )
    {
      organisationMap.put( organisation.getId(), organisation );
    }
  }

  public Map<Long, ZDUser> getUserMap()
  {
    if ( userMap == null || userMap.isEmpty() )
    {
      userMap = new HashMap<>();
      final List<Criterion> restrictions = new ArrayList<>();
      restrictions.add( Restrictions.eq( "active", true ) );
      List<ZDUser> userList = HibernateUtil.getEntityList( ZDUser.class, restrictions );
      for ( ZDUser user : userList )
      {
        userMap.put( user.getId(), user );
      }
    }
    return userMap;
  }

  public List<ZDUser> getUsers()
  {
    return new ArrayList<>( getUserMap().values() );
  }

  public Map<Long, ZDTicket> getTicketMap()
  {
    if ( ticketMap == null || ticketMap.isEmpty())
    {
      ticketMap = new HashMap<>();
      final List<ZDTicket> ticketList = HibernateUtil.getEntityList( ZDTicket.class );
      for ( ZDTicket ticket : ticketList )
      {
        ticketMap.put( ticket.getId(), ticket );
      }
    }
    return ticketMap;
  }


  public List<ZDTicket> getTickets( final Status status )
  {
    final List<ZDTicket> ticketList = new ArrayList<>();
    for ( Map.Entry<Long, ZDTicket> entry : getTicketMap().entrySet() )
    {
      ZDTicket ticket = entry.getValue();
      if ( ticket.getStatus().equals( status ) ) ticketList.add( ticket );
    }
    return ticketList;
  }

  public List<ZDTicket> getUnassignedTickets( final Status status )
  {
    final List<ZDTicket> ticketList = new ArrayList<>();
    for ( Map.Entry<Long, ZDTicket> entry : getTicketMap().entrySet() )
    {
      ZDTicket ticket = entry.getValue();
      if (  ticket.getStatus() != null && ticket.getStatus().equals( status ) && ticket.getAssigneeId() == null)
      {
        ticketList.add( ticket );
      }
    }
    return ticketList;
  }


  public List<ZDTicket> getTickets( final Status status, long userId )
  {
    final List<ZDTicket> ticketList = new ArrayList<>();
    for ( Map.Entry<Long, ZDTicket> entry : getTicketMap().entrySet() )
    {
      ZDTicket ticket = entry.getValue();
      if ( ticket.getStatus() != null &&
        ticket.getStatus().equals( status ) &&
        ticket.getAssigneeId() != null && ticket.getAssigneeId() == userId )
      {
        ticketList.add( ticket );
      }
    }
    return ticketList;
  }

  public Map<Long, ZDTopic> getTopicMap()
  {
    if ( topicMap == null || userMap.isEmpty() )
    {
      topicMap = new HashMap<>();
      final List<ZDTopic> topicList = HibernateUtil.getEntityList( ZDTopic.class );
      for ( ZDTopic topic : topicList )
      {
        topicMap.put( topic.getId(), topic );
      }
    }
    return topicMap;
  }

  public List<ZDTopic> getTopics()
  {
    return new ArrayList<>( getTopicMap().values() );
  }

  public List<Group> getGroups()
  {
    return new ArrayList<>( getGroupMap().values() );
  }


  public Map<Long, ZDForum> getForumMap()
  {
    if ( forumMap == null || forumMap.isEmpty() )
    {
      forumMap = new HashMap<>();
      final List<ZDForum> forumList = HibernateUtil.getEntityList( ZDForum.class );
      for ( ZDForum forum : forumList )
      {
        forumMap.put( forum.getId(), forum );
      }
    }
    return forumMap;
  }

  public List<ZDForum> getForums()
  {
    return new ArrayList<>( getForumMap().values() );
  }
}
