package tomb.supportsim.control.imports;

import com.tombeadman.screensteps.model.Manual;
import com.tombeadman.screensteps.model.Space;
import org.zendesk.client.v2.model.Group;
import org.zendesk.client.v2.model.GroupMembership;
import tomb.supportsim.control.Cache;
import tomb.supportsim.models.*;
import tomb.supportsim.models.jira.IssueShell;

import java.io.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 13/12/14 Time: 11:17
 */
public class DataSerializer
{
  protected void serializeData()
  {
    serializeZendeskData();
    serializeScreenstepsData();
    serialzeJiraData();
  }

  private void serializeZendeskData()
  {

    serliazeZendeskUsers();
    serializeZendeskTickets();
    serialzeZendeskOrganisations();
    serlializseZendeskGroupMemberships();
    serializeZendeskGroups();
    serializeZendeskForums();
    serlializeZendeskTopics();
  }

  private void serializeScreenstepsData()
  {
    serialzSSSpaces();
    serializeSSManuals();
  }

  private void serialzeJiraData()
  {
    serializeBugs();
    serlizaleFeatures();
  }

  private void serlializeZendeskTopics()
  {
    Map<Long, ZDTopic> topicMap = Cache.getInstance().getTopicMap();
    System.out.println( "Serializing Zendesk Data - Topics" );
    try (
      OutputStream file = new FileOutputStream( "zd-topics.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( topicMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serializeZendeskForums()
  {
    Map<Long, ZDForum> forumMap = Cache.getInstance().getForumMap();
    System.out.println( "Serliaizing Zendesk Data - Forums" );
    try (
      OutputStream file = new FileOutputStream( "zd-forums.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( forumMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serializeZendeskTickets()
  {
    Map<Long, ZDTicket> ticketMap = Cache.getInstance().getTicketMap();
    System.out.println( "Serliaizing Zendesk Data - Tickets" );
    try (
      OutputStream file = new FileOutputStream( "zd-tickets.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( ticketMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serialzeZendeskOrganisations()
  {
    Map<Long, ZDOrganisation> orgMap = Cache.getInstance().getOrganisationMap();
    System.out.println( "Serliaizing Zendesk Data - Organisations" );
    try (
      OutputStream file = new FileOutputStream( "zd-orgs.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( orgMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serlializseZendeskGroupMemberships()
  {
    Map<Long, GroupMembership> groupMemMap = Cache.getInstance().getGroupMembershipMap();
    System.out.println( "Serliaizing Zendesk Data - Group Memberships" );
    try (
      OutputStream file = new FileOutputStream( "zd-group-mem.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( groupMemMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serializeZendeskGroups()
  {
    Map<Long, Group> groupMap = Cache.getInstance().getGroupMap();
    System.out.println( "Serliaizing Zendesk Data - Groups" );
    try (
      OutputStream file = new FileOutputStream( "zd-groups.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( groupMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serliazeZendeskUsers()
  {
    Map<Long, ZDUser> userMap = Cache.getInstance().getUserMap();
    System.out.println( "Serliaizing Zendesk Data - Users" );
    try (
      OutputStream file = new FileOutputStream( "zd-users.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( userMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serialzSSSpaces()
  {
    Map<String, Space> spaceMap = Cache.getInstance().getSpaceMap();
    System.out.println( "Serliaizing Screensteps Data - Spaces" );
    try (
      OutputStream file = new FileOutputStream( "ss-spaces.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( spaceMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serializeSSManuals()
  {
    Map<String, Manual> manMap = Cache.getInstance().getManualMap();
    System.out.println( "Serliaizing Screensteps Data - Maps" );
    try (
      OutputStream file = new FileOutputStream( "ss-mans.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( manMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serializeBugs()
  {
    Map<String, IssueShell> bugMap = Cache.getInstance().getBugMap();
    System.out.println( "Serliaizing JIRA Data - bugs" );
    try (
      OutputStream file = new FileOutputStream( "jira-bugs.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( bugMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }

  private void serlizaleFeatures()
  {
    Map<String, IssueShell> featMap = Cache.getInstance().getFeatureMap();
    System.out.println( "Serliaizing JIRA Data - Features" );
    try (
      OutputStream file = new FileOutputStream( "jira-feat.ser" );
      OutputStream buffer = new BufferedOutputStream( file );
      ObjectOutput output = new ObjectOutputStream( buffer );
    )
    {
      output.writeObject( featMap );
    }
    catch ( IOException ex )
    {
      throw new RuntimeException( ex );
    }
  }
}
