package com.tombeadman.tdesk.datastore;

import com.tombeadman.screensteps.model.*;
import com.tombeadman.tdesk.models.screensteps.ScreenstepsTableEntry;
import com.tombeadman.tdesk.models.zendesk.*;
import com.tombeadman.tdesk.util.ScreenStepsKeyUtil;
import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.GroupMembership;
import org.zendesk.client.v2.model.Status;
import com.tombeadman.tdesk.models.jira.IssueShell;

import java.util.*;

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

  //Jira Store
  private Map<String, IssueShell> bugMap;
  private Map<String, IssueShell> featureMap;


  //On screen lists
  private List<ScreenstepsTableEntry> screenstepsEntryList;
  private List<IssueShell> jiraIssueList; //Convenience super list;

  public void setOrganisationMap( final Map<Long, ZDOrganisation> organisationMap )
  {
    this.organisationMap = organisationMap;
  }

  public void setUserMap( final Map<Long, ZDUser> userMap )
  {
    this.userMap = userMap;
  }

  public void setTicketMap( final Map<Long, ZDTicket> ticketMap )
  {
    this.ticketMap = ticketMap;
  }

  public void setTopicMap( final Map<Long, ZDTopic> topicMap )
  {
    this.topicMap = topicMap;
  }

  public void setForumMap( final Map<Long, ZDForum> forumMap )
  {
    this.forumMap = forumMap;
  }

  public void setGroupMap( final Map<Long, Group> groupMap )
  {
    this.groupMap = groupMap;
  }

  public void setGroupMembershipMap( final Map<Long, GroupMembership> groupMembershipMap )
  {
    this.groupMembershipMap = groupMembershipMap;
  }

  public void setSpaceMap( final Map<String, Space> spaceMap )
  {
    this.spaceMap = spaceMap;
  }

  public void setManualMap( final Map<String, Manual> manualMap )
  {
    this.manualMap = manualMap;
  }

  public void setLessonMap( final Map<String, Lesson> lessonMap )
  {
    this.lessonMap = lessonMap;
  }

  public void setBugMap( final Map<String, IssueShell> bugMap )
  {
    this.bugMap = bugMap;
  }

  public void setFeatureMap( final Map<String, IssueShell> featureMap )
  {
    this.featureMap = featureMap;
  }

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

  public void insertBug( final IssueShell issue )
  {
    getBugMap().put(issue.getKey(),issue);
  }

  public Map<String, IssueShell> getBugMap()
  {
    if( bugMap == null)
    {
      bugMap = new HashMap<>(  );
    }
    return bugMap;
  }

  public void insertFeature( final IssueShell issue )
  {
    getFeatureMap().put(issue.getKey(),issue);
  }

  public Map<String, IssueShell> getFeatureMap()
  {
    if( featureMap == null)
    {
      featureMap = new HashMap<>(  );
    }
    return featureMap;
  }

  public List<IssueShell> getJiraBugs()
  {
    return new ArrayList<>( getBugMap().values() );
  }

  public List<IssueShell> getJiraIssues()
  {
    if(jiraIssueList == null)
    {
      jiraIssueList = new ArrayList<IssueShell>( getBugMap().values() );
      jiraIssueList.addAll( getFeatureMap().values() );
    }
    return jiraIssueList;
  }

  public List<ScreenstepsTableEntry> getScreenstepsEntryList()
  {
    if (screenstepsEntryList == null)
    {
      screenstepsEntryList = new ArrayList<>(  );
      populateScreenStepsEntryTable(screenstepsEntryList);
    }
    return screenstepsEntryList;
  }

  private void populateScreenStepsEntryTable( final List<ScreenstepsTableEntry> screenstepsEntryList )
  {
    for ( Manual manual : getManuals() )
    {
      for ( Chapter chapter : manual.getChapters() )
      {
        for ( Lesson lesson : chapter.getLessons() )
        {
          final String spaceName = manual.getSpace().getTitle();
          final String manualName = manual.getTitle();
          final String chapterName = chapter.getTitle();
          final String lessonTitle = lesson.getTitle();
          final String url = lesson.getUrl();
          screenstepsEntryList.add(
            new ScreenstepsTableEntry( spaceName, manualName, chapterName, lessonTitle, url ) );
        }
      }
    }
  }
}
