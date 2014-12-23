package tomb.supportsim.control.imports;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.*;
import tomb.supportsim.control.Cache;
import tomb.supportsim.util.ConvertUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 14/12/14 Time: 14:43
 */
public class ZendeskImporter extends Importer
{
  private final Zendesk zendesk;

  public ZendeskImporter( final Zendesk zendesk )
  {
    super();
    this.zendesk = zendesk;
  }

  @Override
  public void run()
  {
    setActive( true );
    long time = System.currentTimeMillis();
    importZendeskData();
    System.out.println( "All data imported from Zendesk" );
    System.out.println( ( "Zendesk - Time taken: " + (System.currentTimeMillis() - time ) / 1000) );
    setActive( false );

  }

  private void importZendeskData()
  {
    //persist Users
    Logger.getLogger( "Data" ).log( Level.INFO, "ZENDESK IMPORT" );
    System.out.println( "Importing Users from Zendesk" );
    saveUsers();

    //persist tickets
    System.out.println( "Importing Tickets from Zendesk" );
    saveTickets();

    //persist Organisations
    System.out.println( "Importing Organisations from ZenDesk" );
    saveOrganisations();

    //persist GroupMemberships
    System.out.println( "Importing Group Memberships from ZenDesk" );
    saveGroupMemberships();

    //persist GroupMemberships
    System.out.println( "Importing Groups from ZenDesk" );
    saveGroups();

    //persist Forums
    System.out.println( "Importing Forums from ZenDesk" );
    saveForums();

    //persist Topics
    System.out.println( "Importing Topics from ZenDesk" );
    saveTopics();
  }


  private void saveGroups()
  {
    for ( Group group : zendesk.getGroups() )
    {
      Cache.getInstance().insertGroup( group );
    }
  }

  private void saveTickets()
  {

    for ( Ticket ticket : zendesk.getTickets() )
    {
      Cache.getInstance().insertTicket( ConvertUtil.toTicket( ticket ) );
    }
  }

  private void saveUsers()
  {
    for ( final User user : zendesk.getUsers() )
    {
      if ( user.getActive() )
      {
        Cache.getInstance().insertUser( ConvertUtil.toUser( user ) );
      }
    }
  }

  private void saveOrganisations()
  {
    for ( final Organization organization : zendesk.getOrganizations() )
    {
      Cache.getInstance().insertOrganisation( ConvertUtil.toOrganisation( organization ) );
    }
  }

  private void saveGroupMemberships()
  {
    for ( GroupMembership groupMembership : zendesk.getGroupMemberships() )
    {
      Cache.getInstance().insertGroupMembership( groupMembership );
    }
  }

  private void saveForums()
  {
    for ( Forum forum : zendesk.getForums() )
    {
      Cache.getInstance().insertForum( ConvertUtil.toForum( forum ) );
    }
  }

  private void saveTopics()
  {
    for ( Topic topic : zendesk.getTopics() )
    {

      Cache.getInstance().insertTopic( ConvertUtil.toTopic( topic ) );
    }
  }
}