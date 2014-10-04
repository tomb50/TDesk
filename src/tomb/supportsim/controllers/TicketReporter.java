package tomb.supportsim.controllers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.Customer;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.TicketStateEnum;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.util.*;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 05/08/2014 Time: 18:52
 */
public class TicketReporter
{
  public static final int UNASSIGNED = 0;
  public static String TICKET_KEY = "TICKET_KEY";
  public static String ANALYST_KEY = "ANALYST_KEY";
  public static String CUSTOMER_KEY = "CUSTOMER_KEY";

  public static List<SupportTicket> getAllUnassignedTickets()
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.eq( "assigneeId", UNASSIGNED ) );
    return (List<SupportTicket>) HibernateUtil.getEntityList( SupportTicket.class, restrictions );
  }

  public static Integer getOpenTicketCount( final int analystId )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add(
      Restrictions.and( Restrictions.eq( "assigneeId", analystId ), Restrictions.ne( "state", TicketStateEnum.CLOSED ) ) );
    return HibernateUtil.getEntityCount( SupportTicket.class, restrictions );
  }

  public static List<SupportTicket> getTicketsByState( final TicketStateEnum ticketStateEnum )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.eq( "state", ticketStateEnum ) );
    return HibernateUtil.getEntityList( SupportTicket.class, restrictions );
  }

  public static Integer getTicketCountByType(final TicketTypeEnum ticketTypeEnum)
  {
    final List<Criterion> restrictions = new ArrayList<>(  );
    restrictions.add( Restrictions.eq( "type", ticketTypeEnum ));
    return  HibernateUtil.getEntityCount( SupportTicket.class, restrictions );
  }

  public static Integer getTotalTicketCount()
  {
    return HibernateUtil.getEntityCount( SupportTicket.class );
  }

  public static List<SupportTicket> getAllTickets()
  {
    return (List<SupportTicket>) HibernateUtil.getEntityList( SupportTicket.class);
  }

  public static List<SupportTicket> getClosedTicketsByAnalyst( final Analyst analyst )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.and( Restrictions.eq( "assigneeId", analyst.getId() ),
                                        Restrictions.eq( "state", TicketStateEnum.CLOSED ) ) );

    return HibernateUtil.getEntityList( SupportTicket.class, restrictions );
  }

  public static Integer getClosedTicketCount()
  {
    final List<Criterion> restrictions = new ArrayList<>(  );
    restrictions.add( Restrictions.eq( "state", TicketStateEnum.CLOSED ));
    return  HibernateUtil.getEntityCount( SupportTicket.class, restrictions );
  }


  public static List getJoinedDetailsForAllTickets()
  {
    final String hQLQuery =
      "FROM SupportTicket t, Customer c, Analyst a WHERE t.customerId = c.id AND t.assigneeId = a.id ";
    Session session = HibernateUtil.beginTransaction();
    Query query = session.createQuery( hQLQuery );
    final List rawResults = query.list();
    HibernateUtil.commitAndClose( session );

    final List results = new ArrayList();
    for ( final Iterator it = rawResults.iterator(); it.hasNext(); )
    {
      Object[] objects = (Object[]) it.next();
      final SupportTicket ticket = (SupportTicket) objects[0];
      final Customer customer = (Customer) objects[1];
      final Analyst analyst = (Analyst) objects[2];
      HashMap map = new HashMap();
      map.put( TICKET_KEY, ticket );
      map.put( CUSTOMER_KEY, customer );
      map.put( ANALYST_KEY, analyst );
      results.add( map );
    }
    return results;
  }

  public static Map getJoinedDetails( final int ticketId )
  {
    final String hQLQuery =
      "FROM SupportTicket t, Customer c, Analyst a WHERE t.customerId = c.id AND t.assigneeId = a.id AND t.id = :ticket_id ";
    Session session = HibernateUtil.beginTransaction();
    Query query = session.createQuery( hQLQuery );
    query.setParameter( "ticket_id", ticketId );
    final Object[] objects = (Object[]) query.uniqueResult();
    HibernateUtil.commitAndClose( session );

    HashMap map = new HashMap();
    if ( objects != null )
    {
      final SupportTicket ticket = (SupportTicket) objects[0];
      final Customer customer = (Customer) objects[1];
      final Analyst analyst = (Analyst) objects[2];
      map.put( TICKET_KEY, ticket );
      map.put( CUSTOMER_KEY, customer );
      map.put( ANALYST_KEY, analyst );
    }
    return map;
  }

  public static List getTickets( final int assigneeId, final TicketStateEnum ticketStateEnum )
  {
    final List<Criterion> restrictions = new ArrayList<>();
    restrictions.add( Restrictions.and( Restrictions.eq( "assigneeId", assigneeId ),
                                        Restrictions.eq( "state", ticketStateEnum ) ) );

    return HibernateUtil.getEntityList( SupportTicket.class, restrictions );
  }

  public static List getJoinedDetailsForNewTickets()
  {
    final String hQLQuery =
      "FROM SupportTicket t, Customer c WHERE t.customerId = c.id and t.state = 'NEW'";
    Session session = HibernateUtil.beginTransaction();
    Query query = session.createQuery( hQLQuery );
    final List rawResults = query.list();
    HibernateUtil.commitAndClose( session );

    final List results = new ArrayList();
    for ( final Iterator it = rawResults.iterator(); it.hasNext(); )
    {
      Object[] objects = (Object[]) it.next();
      final SupportTicket ticket = (SupportTicket) objects[0];
      final Customer customer = (Customer) objects[1];
      HashMap map = new HashMap();
      map.put( TICKET_KEY, ticket );
      map.put( CUSTOMER_KEY, customer );
      results.add( map );
    }
    return results;
  }
}
