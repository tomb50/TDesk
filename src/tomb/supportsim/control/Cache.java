package tomb.supportsim.control;

import com.tombeadman.screensteps.model.*;
import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.GroupMembership;
import org.zendesk.client.v2.model.Status;
import tomb.supportsim.models.*;
import tomb.supportsim.util.ScreenStepsKeyUtil;

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
  //Zendesk store
  private Map<Long, ZDOrganisation> organisationMap;
  private Map<Long, ZDUser> userMap;
  private Map<Long, ZDTicket> ticketMap;
  private Map<Long, ZDTopic> topicMap;
  private Map<Long, ZDForum> forumMap;
  private Map<Long, Group> groupMap;
  private Map<Long, GroupMembership> groupMembershipMap;

  //ScreenSteps store
  private Map<String, Space> spaceMap;
  private Map<String, Manual> manualMap;
  private Map<String, Lesson> lessonMap;



  private static Cache instance;

  public static Cache getInstance()
  {
    if ( instance == null )
    {
        instance = new Cache();
    }
    return instance;
  }

  private Cache()
  {

  }


  public Map<Long, Group> getGroupMap()
  {
    if ( groupMap == null )
      groupMap = new HashMap<>();
    return groupMap;
  }


  public Map<Long, GroupMembership> getGroupMembershipMap()
  {
    if ( groupMembershipMap == null )
      groupMembershipMap = new HashMap<>();
    return groupMembershipMap;
  }

  public List<GroupMembership> getGroupMemberships()
  {
    return new ArrayList<>( getGroupMembershipMap().values() );
  }

  public List<GroupMembership> getGroupMemberships(final Long groupId)
  {
    final List<GroupMembership> allGroupMemberships = getGroupMemberships();
    final List<GroupMembership> groupMemberships = new ArrayList<>(  );
    for(GroupMembership groupMembership : allGroupMemberships)
    {
      if(groupMembership.getGroupId().equals( groupId ))
      {
        groupMemberships.add( groupMembership );
      }
    }
    return groupMemberships;
  }

  public Map<Long, ZDOrganisation> getOrganisationMap()
  {
    if ( organisationMap == null )
      organisationMap = new HashMap<>();
    return organisationMap;
  }


  public Map<Long, ZDUser> getUserMap()
  {
    if ( userMap == null )
      userMap = new HashMap<>();
    return userMap;
  }

  public List<ZDUser> getUsers()
  {
    return new ArrayList<>( getUserMap().values() );
  }

  public Map<Long, ZDTicket> getTicketMap()
  {
    if ( ticketMap == null )
      ticketMap = new HashMap<>();
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
      if ( ticket.getStatus() != null && ticket.getStatus().equals( status ) && ticket.getAssigneeId() == null )
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
    if ( topicMap == null )
      topicMap = new HashMap<>();
    return topicMap;
  }

  public List<ZDTopic> getTopics()
  {
    return new ArrayList<>( getTopicMap().values() );
  }

  public List<Manual> getManuals()
  {
    return new ArrayList<>( getManualMap().values() );
  }

  public List<Lesson> getLessons()
  {
    return new ArrayList<>( getLessonMap().values() );
  }

  public List<Group> getGroups()
  {
    return new ArrayList<>( getGroupMap().values() );
  }


  public Map<Long, ZDForum> getForumMap()
  {
    if ( forumMap == null )
    {
      forumMap = new HashMap<>();
    }
    return forumMap;
  }

  public List<ZDForum> getForums()
  {
    return new ArrayList<>( getForumMap().values() );
  }

  public void insertGroup( Group group )
  {
    getGroupMap().put( group.getId(), group );
  }

  public void insertTicket( ZDTicket ticket )
  {
    getTicketMap().put( ticket.getId(), ticket );
  }

  public void insertUser( ZDUser user )
  {
    getUserMap().put( user.getId(), user );
  }

  public void insertGroupMembership( GroupMembership groupMembership )
  {
    getGroupMembershipMap().put( groupMembership.getId(), groupMembership );
  }

  public void insertForum( ZDForum forum )
  {
    getForumMap().put( forum.getId(), forum );
  }

  public void insertTopic( ZDTopic topic )
  {
    getTopicMap().put( topic.getId(), topic );
  }

  public void insertOrganisation( final ZDOrganisation zdOrganisation )
  {
    getOrganisationMap().put( zdOrganisation.getId(), zdOrganisation );
  }

  public void insertSpace(final Space space)
  {
    getSpaceMap().put(space.getId(),space);
  }

  public void insertLesson(final Lesson lesson)
  {
    getLessonMap().put(lesson.getId(),lesson);
  }

  public void insertManual(final Manual manual)
  {
    getManualMap().put( ScreenStepsKeyUtil.getManualPK( manual ),manual);
  }

  public Map<String, Space> getSpaceMap()
  {
    if (spaceMap == null)
    {
      spaceMap = new HashMap<>(  );
    }
    return spaceMap;
  }

  public Map<String, Manual> getManualMap()
  {
    if (manualMap == null)
    {
      manualMap = new HashMap<>(  );
    }
    return manualMap;
  }

  public Map<String, Lesson> getLessonMap()
  {
    if (lessonMap == null)
    {
      lessonMap = new HashMap<>(  );
    }
    return lessonMap;
  }
}
