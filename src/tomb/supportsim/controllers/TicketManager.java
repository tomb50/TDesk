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
  public static void main( String[] args )
  {
    TicketManager ticketManager = new TicketManager();
    ticketManager.createNewTicket();
  }


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
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    SupportTicket newTicket = new SupportTicket();
    DetailsManager detailsManager = new DetailsManager();
    detailsManager.createDetailsForNewTicket( newTicket );
    session.save( newTicket );
    session.getTransaction().commit();
    session.close();
  }

  public SupportTicket getTicket( int id )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    SupportTicket ticket = (SupportTicket) session.get( SupportTicket.class, id );
    session.getTransaction().commit();
    session.close();
    return ticket;
  }

  public void closeTicket( int id )
  {
    if ( getTicket( id ) != null )
    {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      SupportTicket ticket = (SupportTicket) session.get( SupportTicket.class, id );
      ticket.setState( TicketStateEnum.CLOSED );
      session.save( ticket );
      session.getTransaction().commit();
      session.close();
    }
  }

  public void assignTicket( final int ticketId, final int analystId )
  {
    if ( getTicket( ticketId ) != null && getAnalyst( analystId ) != null )
    {

      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      SupportTicket ticket = (SupportTicket) session.get( SupportTicket.class, ticketId );
      ticket.setAssigneeId( analystId );
      ticket.setState( TicketStateEnum.WIP );
      ticket.setTimeAssigned( new Timestamp( TimeModel.getTime() ) );
      session.save( ticket );
      session.getTransaction().commit();
      session.close();
    }
  }


  //todo refactor this out of Class
  public Analyst getAnalyst( int id )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Analyst analyst = (Analyst) session.get( Analyst.class, id );
    session.getTransaction().commit();
    session.close();
    return analyst;
  }
}
