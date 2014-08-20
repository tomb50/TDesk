package tomb.supportsim.controllers;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.TicketStateEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 05/08/2014 Time: 18:52
 */
public class TicketReporter
{
  public static int UNASSIGNED = 0;

  public List<SupportTicket> getAllUnassignedTickets()
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria( SupportTicket.class );
    criteria.add( Restrictions.eq( "assigneeId", UNASSIGNED ) );
    List<SupportTicket> tickets = criteria.list();
    session.getTransaction().commit();
    session.close();

    return tickets;
  }

  public Integer getOpenTicketCount( final int id )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria( SupportTicket.class );
    criteria.add(
      Restrictions.and( Restrictions.eq( "assigneeId", id ), Restrictions.ne( "state", TicketStateEnum.CLOSED ) ) );
    Number count = (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return new Integer( count.intValue() );
  }

  public List<SupportTicket> getTickets( final TicketStateEnum wip )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria( SupportTicket.class );
    criteria.add( Restrictions.eq( "state", wip ) );
    List<SupportTicket> tickets = criteria.list();
    session.getTransaction().commit();
    session.close();
    return tickets;
  }
}
