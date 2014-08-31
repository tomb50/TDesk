package tomb.supportsim.controllers;

import org.hibernate.Session;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.TicketStateEnum;
import tomb.supportsim.util.TimeModel;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 02/08/2014 Time: 19:30
 */
public class TicketManager
{

  /*todo remove time coupling between this and poller*/
  public void potentiallyCreateNewTicket()
  {
    Random random = new Random();
    int i = random.nextInt( 3 );
    if ( i == 0 )
    {
      createNewTicket(); //%33% chance of ticket creation
      System.out.println( "Ticket is being created" );
    }
  }

  private void createNewTicket()
  {
    final Session session = HibernateUtil.beginTransaction();
    final SupportTicket newTicket = new SupportTicket();
    final DetailsManager detailsManager = new DetailsManager();
    detailsManager.createDetailsForNewTicket( newTicket );
    session.save( newTicket );
    HibernateUtil.commitAndClose( session );
  }

  public SupportTicket getTicket( int id )
  {
    return SupportTicket.class.cast( HibernateUtil.getEntity( SupportTicket.class, id ) );
  }

  public void closeTicket( int id )
  {
    if ( getTicket( id ) != null )
    {
      final Session session = HibernateUtil.beginTransaction();
      final SupportTicket ticket = (SupportTicket) session.get( SupportTicket.class, id );
      ticket.setState( TicketStateEnum.CLOSED );
      session.save( ticket );
      HibernateUtil.commitAndClose( session );
    }
  }

  public void assignTicket( final int ticketId, final int analystId )
  {
    if ( getTicket( ticketId ) != null && getAnalyst( analystId ) != null )
    {
      final Session session = HibernateUtil.beginTransaction();
      final SupportTicket ticket = (SupportTicket) session.get( SupportTicket.class, ticketId );
      ticket.setAssigneeId( analystId );
      ticket.setState( TicketStateEnum.WIP );
      ticket.setTimeAssigned( new Timestamp( TimeModel.getTime() ) );
      session.save( ticket );
      HibernateUtil.commitAndClose( session );
    }
  }

  //todo refactor this out of Class
  public Analyst getAnalyst( int id )
  {
    final Session session = HibernateUtil.beginTransaction();
    final Analyst analyst = (Analyst) session.get( Analyst.class, id );
    HibernateUtil.commitAndClose( session );
    return analyst;
  }
}
