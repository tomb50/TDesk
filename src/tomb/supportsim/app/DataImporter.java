package tomb.supportsim.app;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.*;
import tomb.supportsim.control.Cache;
import tomb.supportsim.models.ConvertUtil;
import tomb.supportsim.view.ViewHelper;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 10/10/2014 Time: 13:30
 */
public class DataImporter
{
  Zendesk zendesk;

  public DataImporter(Zendesk zendesk)
  {
    this.zendesk = zendesk;
  }


  protected void fullImport()
  {

    long time = System.currentTimeMillis();
    //persist Users
    System.out.println("Importing Users from Zendesk");
    saveUsers();
    System.out.println("Users saved");
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist tickets
    System.out.println("Importing Tickets from Zendesk");
    saveTickets();
    System.out.println("Tickets saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();

    //persist Organisations
    System.out.println("Importing Organisations from ZenDesk");
    saveOrganisations();
    System.out.println("Organisations saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();

    //persist GroupMemberships
    System.out.println("Importing Group Memberships from ZenDesk");
    saveGroupMemberships();
    System.out.println("Memberships saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();


    //persist GroupMemberships
    System.out.println("Importing Groups from ZenDesk");
    saveGroups();
    System.out.println("Memberships saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();

    //persist Forums
    System.out.println("Importing Forums from ZenDesk");
    saveForums();
    System.out.println("Forums saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();

    //persist Topics
    System.out.println("Importing Topics from ZenDesk");
    saveTopics();
    System.out.println("Topics saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
  }

  private void saveGroups()
  {
    for (Group group : zendesk.getGroups() )
    {
      Cache.getInstance().insertGroup( group );
    }

  }

  private void saveTickets()
  {

    for ( Ticket ticket :  zendesk.getTickets() )
    {
      Cache.getInstance().insertTicket( ConvertUtil.toTicket( ticket ) );
    }
  }

  private void saveUsers()
  {
   for(final User user : zendesk.getUsers())
   {
     if(user.getActive())
       Cache.getInstance().insertUser(  ConvertUtil.toUser( user ) );
   }
  }

  private void saveOrganisations()
  {
    for(final Organization organization : zendesk.getOrganizations())
    {
      Cache.getInstance().insertOrganisation( ConvertUtil.toOrganisation( organization ) );
    }
  }

  private void saveGroupMemberships()
  {
    for ( GroupMembership groupMembership : zendesk.getGroupMemberships())
    {
      Cache.getInstance().insertGroupMembership( groupMembership );
    }
  }

  private void saveForums()
  {
    for( Forum forum : zendesk.getForums())
    {
      Cache.getInstance().insertForum( ConvertUtil.toForum( forum ) );
    }
  }

  private void saveTopics()
  {
    for( Topic topic : zendesk.getTopics())
    {

      Cache.getInstance().insertTopic( ConvertUtil.toTopic( topic ) );
    }
  }

}
