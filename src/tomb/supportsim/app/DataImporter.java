package tomb.supportsim.app;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.GroupMembership;
import org.zendesk.client.v2.model.Organization;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.User;
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



    final long startTime = System.currentTimeMillis();
    //persist Users
    System.out.println("Persisting Users from ZenDesk to Database");
    saveUsers();
    System.out.println("Users saved");
    System.out.println( ( System.currentTimeMillis() - startTime ) / 1000 );

    //persist tickets
    System.out.println("Persisting Tickets from ZenDesk to Database");
    saveTickets();
    System.out.println("Tickets saved");
    System.out.println( (System.currentTimeMillis() - startTime)/1000 );

    //persist Organisations
    System.out.println("Persisting Organisations from ZenDesk to Database");
    saveOrganisations();
    System.out.println("Organisations saved");
    System.out.println( (System.currentTimeMillis() - startTime)/1000 );

    //persist GroupMemberships
    System.out.println("Persisting Group Memberships from ZenDesk to Database");
    saveGroupMemberships();
    System.out.println("Memberships saved");
    System.out.println( (System.currentTimeMillis() - startTime)/1000 );
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

}
