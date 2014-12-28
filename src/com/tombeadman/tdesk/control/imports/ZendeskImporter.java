package com.tombeadman.tdesk.control.imports;

import com.tombeadman.tdesk.control.Cache;
import com.tombeadman.tdesk.util.ConvertUtil;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.*;

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
    logger.info( "All data imported from Zendesk Time taken: " + ( System.currentTimeMillis() - time ) / 1000 );
    setActive( false );
  }

  private void importZendeskData()
  {
    logger.info( "ZENDESK IMPORT" );
    saveUsers();
    saveTickets();
    saveOrganisations();
    saveGroupMemberships();
    saveGroups();
    saveForums();
    saveTopics();
  }


  private void saveGroups()
  {
    logger.info( "Importing Groups from ZenDesk" );
    for ( Group group : zendesk.getGroups() )
    {
      Cache.getInstance().insertGroup( group );
    }
  }

  private void saveTickets()
  {
    logger.info( "Importing Tickets from Zendesk" );
    for ( Ticket ticket : zendesk.getTickets() )
    {
      Cache.getInstance().insertTicket( ConvertUtil.toTicket( ticket ) );
    }
  }

  private void saveUsers()
  {
    logger.info( "Importing Users from Zendesk" );
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
    logger.info( "Importing Organisations from ZenDesk" );
    for ( final Organization organization : zendesk.getOrganizations() )
    {
      Cache.getInstance().insertOrganisation( ConvertUtil.toOrganisation( organization ) );
    }
  }

  private void saveGroupMemberships()
  {
    logger.info( "Importing Group Memberships from ZenDesk" );
    for ( GroupMembership groupMembership : zendesk.getGroupMemberships() )
    {
      Cache.getInstance().insertGroupMembership( groupMembership );
    }
  }

  private void saveForums()
  {
    logger.info( "Importing Forums from ZenDesk" );
    for ( Forum forum : zendesk.getForums() )
    {
      Cache.getInstance().insertForum( ConvertUtil.toForum( forum ) );
    }
  }

  private void saveTopics()
  {
    logger.info( "Importing Topics from ZenDesk" );
    for ( Topic topic : zendesk.getTopics() )
    {
      Cache.getInstance().insertTopic( ConvertUtil.toTopic( topic ) );
    }
  }
}
