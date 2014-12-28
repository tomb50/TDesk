package com.tombeadman.tdesk.control.imports;

import com.tombeadman.tdesk.control.Cache;
import com.tombeadman.tdesk.models.*;
import com.tombeadman.screensteps.model.Manual;
import com.tombeadman.screensteps.model.Space;
import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.GroupMembership;
import com.tombeadman.tdesk.models.jira.IssueShell;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 13/12/14 Time: 10:37
 */
public class DataRestorer
{
  private final Logger logger = Logger.getLogger( getClass().getSimpleName() );

  public void unserializeData()
  {
    unserializeZendeskData();
    unserializeScreenstepsData();
    unserialzeJiraData();
  }

  private void unserializeZendeskData()
  {
    unserliazeZendeskUsers();
    unserializeZendeskTickets();
    unserialzeZendeskOrganisations();
    unserlializseZendeskGroupMemberships();
    unserializeZendeskGroups();
    unserializeZendeskForums();
    unserlializeZendeskTopics();
  }

  private void unserializeScreenstepsData()
  {
    unserialzSSSpaces();
    unserializeSSManuals();
  }

  private void unserialzeJiraData()
  {
    unserializeBugs();
    unserlizaleFeatures();
  }

  private void unserlializeZendeskTopics()
  {
    Map<Long, ZDTopic> topicMap;
    logger.info( "Unserliaizing Zendesk Data - Topics" );
    try (
      InputStream file = new FileInputStream( "zd-topics.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      topicMap = (Map<Long, ZDTopic>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setTopicMap( topicMap );
  }

  private void unserializeZendeskForums()
  {
    Map<Long, ZDForum> forumMap;
    logger.info( "Unserliaizing Zendesk Data - Forums" );
    try (
      InputStream file = new FileInputStream( "zd-forums.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      forumMap = (Map<Long, ZDForum>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setForumMap( forumMap );
  }

  private void unserializeZendeskTickets()
  {
    Map<Long, ZDTicket> ticketMap;
    logger.info( "Unserliaizing Zendesk Data - Tickets" );
    try (
      InputStream file = new FileInputStream( "zd-tickets.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      ticketMap = (Map<Long, ZDTicket>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setTicketMap( ticketMap );
  }

  private void unserialzeZendeskOrganisations()
  {
    Map<Long, ZDOrganisation> orgMap;
    logger.info( "Unserliaizing Zendesk Data - Organisations" );
    try (
      InputStream file = new FileInputStream( "zd-orgs.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      orgMap = (Map<Long, ZDOrganisation>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setOrganisationMap( orgMap );
  }

  private void unserlializseZendeskGroupMemberships()
  {
    Map<Long, GroupMembership> groupMemMap;
    logger.info( "Unserliaizing Zendesk Data - Group Memberships" );
    try (
      InputStream file = new FileInputStream( "zd-group-mem.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      groupMemMap = (Map<Long, GroupMembership>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setGroupMembershipMap( groupMemMap );
  }

  private void unserializeZendeskGroups()
  {
    Map<Long, Group> groupMap;
    logger.info( "Unserliaizing Zendesk Data - Groups" );
    try (
      InputStream file = new FileInputStream( "zd-groups.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      groupMap = (Map<Long, Group>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setGroupMap( groupMap );
  }

  private void unserliazeZendeskUsers()
  {
    Map<Long, ZDUser> userMap;
    logger.info( "Unserliaizing Zendesk Data - Users" );
    try (
      InputStream file = new FileInputStream( "zd-users.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      userMap = (Map<Long, ZDUser>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setUserMap( userMap );
  }

  private void unserialzSSSpaces()
  {
    Map<String, Space> spaceMap;
    logger.info( "Unserliaizing Screensteps Data - Spaces" );
    try (
      InputStream file = new FileInputStream( "ss-spaces.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      spaceMap = (Map<String, Space>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setSpaceMap( spaceMap );
  }

  private void unserializeSSManuals()
  {
    Map<String, Manual> manMap;
    logger.info( "Unserliaizing Screensteps Data - Maps" );
    try (
      InputStream file = new FileInputStream( "ss-mans.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      manMap = (Map<String, Manual>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setManualMap( manMap );
  }

  private void unserializeBugs()
  {
    Map<String, IssueShell> bugMap;
    logger.info( "Unserliaizing JIRA Data - bugs" );
    try (
      InputStream file = new FileInputStream( "jira-bugs.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      bugMap = (Map<String, IssueShell>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setBugMap( bugMap );
  }

  private void unserlizaleFeatures()
  {
    Map<String, IssueShell> featMap;
    logger.info( "Unserliaizing JIRA Data - Features" );
    try (
      InputStream file = new FileInputStream( "jira-feat.ser" );
      InputStream buffer = new BufferedInputStream( file );
      ObjectInput input = new ObjectInputStream( buffer );
    )
    {
      //deserialize the List
      featMap = (Map<String, IssueShell>) input.readObject();
    }
    catch ( ClassNotFoundException | IOException ex )
    {
      throw new RuntimeException( ex );
    }
    Cache.getInstance().setFeatureMap( featMap );
  }
}
