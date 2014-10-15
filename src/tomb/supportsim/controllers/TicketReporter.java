package tomb.supportsim.controllers;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.zendesk.client.v2.model.Status;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.ZDTicket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 05/08/2014 Time: 18:52
 */
public class TicketReporter
{

  public static List<ZDTicket> getTicketsByState( final Status ticketStateEnum )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.eq( "status", ticketStateEnum ) );
    return (List<ZDTicket>) HibernateUtil.getEntityList( ZDTicket.class, restrictions );
  }

  public static Integer getTotalTicketCount()
  {
    return HibernateUtil.getEntityCount( ZDTicket.class );
  }

  public static Integer getTotalOpenTicketCount()
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.ne( "status", Status.CLOSED ) );

    return HibernateUtil.getEntityCount( ZDTicket.class, restrictions );
  }

  public static List getTickets( final int assigneeId, final Status ticketStateEnum )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.and( Restrictions.eq( "assigneeId", assigneeId ),
                                        Restrictions.eq( "status", ticketStateEnum ) ) );

    return HibernateUtil.getEntityList( ZDTicket.class, restrictions );
  }

  public static Integer getTicketCount( final long assigneeId, final Status ticketStateEnum )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.and( Restrictions.eq( "assigneeId", assigneeId ),
                                        Restrictions.eq( "status", ticketStateEnum ) ) );
    return HibernateUtil.getEntityCount( ZDTicket.class, restrictions );
  }

  public static Integer getTicketCountByState( final Status ticketStateEnum )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.eq( "status", ticketStateEnum ) );
    return HibernateUtil.getEntityCount( ZDTicket.class, restrictions );
  }
}
