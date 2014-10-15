package tomb.supportsim.app;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.*;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.ConvertUtil;

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
    System.out.println("Persisting Users from ZenDesk to Database");
    saveUsers();
    System.out.println("Users saved");
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist tickets
    System.out.println("Persisting Tickets from ZenDesk to Database");
    saveTickets();
    System.out.println("Tickets saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();

    //persist Organisations
    System.out.println("Persisting Organisations from ZenDesk to Database");
    saveOrganisations();
    System.out.println("Organisations saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();

    //persist GroupMemberships
    System.out.println("Persisting Group Memberships from ZenDesk to Database");
    saveGroupMemberships();
    System.out.println("Memberships saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();


    //persist GroupMemberships
    System.out.println("Persisting Groups from ZenDesk to Database");
    saveGroups();
    System.out.println("Memberships saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();

    //persist Forums
    System.out.println("Persisting Forums from ZenDesk to Database");
    saveForums();
    System.out.println("Forums saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
    time = System.currentTimeMillis();

    //persist Topics
    System.out.println("Persisting Topics from ZenDesk to Database");
    saveTopics();
    System.out.println("Topics saved");
    System.out.println( (System.currentTimeMillis() - time)/1000 );
  }

  private void saveGroups()
  {
    for (Group group : zendesk.getGroups() )
    {
      HibernateUtil.insertEntity( group );
    }

  }

  private void saveTickets()
  {

    for ( Ticket ticket :  zendesk.getTickets() )
    {
      HibernateUtil.insertEntity( ConvertUtil.toTicket( ticket ) );
    }
  }

  private void saveUsers()
  {
   for(final User user : zendesk.getUsers())
   {
     HibernateUtil.insertEntity( ConvertUtil.toUser( user ) );
   }
  }

  private void saveOrganisations()
  {
    for(final Organization organization : zendesk.getOrganizations())
    {
      HibernateUtil.insertEntity( ConvertUtil.toOrganisation( organization ) );
    }
  }

  private void saveGroupMemberships()
  {
    for ( GroupMembership groupMembership : zendesk.getGroupMemberships())
    {
      HibernateUtil.insertEntity( groupMembership );
    }
  }

  private void saveForums()
  {
    for( Forum forum : zendesk.getForums())
    {
      HibernateUtil.insertEntity( ConvertUtil.toForum( forum ) );
    }
  }

  private void saveTopics()
  {
    for( Topic topic : zendesk.getTopics())
    {
      HibernateUtil.insertEntity( ConvertUtil.toTopic( topic ) );
    }
  }

}
