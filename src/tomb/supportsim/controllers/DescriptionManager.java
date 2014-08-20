package tomb.supportsim.controllers;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.DescriptionTemplate;
import tomb.supportsim.models.DescriptionTemplatePK;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 19/08/2014 Time: 11:03
 */
public class DescriptionManager
{
  Random randomGenerater = new Random();

  public int getNextSequence( final TicketTypeEnum ticketTypeEnum )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria( DescriptionTemplate.class );
    criteria.add( Restrictions.eq( "id.type", ticketTypeEnum ) );
    Number max = (Number) criteria.setProjection( Projections.max( "id.id" ) ).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return max != null ? max.intValue() + 1 : 1;
  }

  public void addDescription( final DescriptionTemplatePK pk, final String text1, final String text2,
                              final String text3 )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    DescriptionTemplate descriptionTemplate = new DescriptionTemplate();
    descriptionTemplate.setId( pk );
    descriptionTemplate.setText1( text1 );
    descriptionTemplate.setText2( text2 );
    descriptionTemplate.setText3( text3 );
    session.save( descriptionTemplate );
    session.getTransaction().commit();
    session.close();
  }

  public void addDescription( final TicketTypeEnum ticketTypeEnum, final String text1, final String text2,
                              final String text3 )
  {
    DescriptionTemplatePK pk = new DescriptionTemplatePK( ticketTypeEnum, getNextSequence( ticketTypeEnum ) );
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    DescriptionTemplate descriptionTemplate = new DescriptionTemplate();
    descriptionTemplate.setId( pk );
    descriptionTemplate.setText1( text1 );
    descriptionTemplate.setText2( text2 );
    descriptionTemplate.setText3( text3 );
    session.save( descriptionTemplate );
    session.getTransaction().commit();
    session.close();
  }

  public String getRandomDescriptionElement1( TicketTypeEnum ticketTypeEnum )
  {
    int i = getByTypeCount( ticketTypeEnum );
    return getText1( new DescriptionTemplatePK( ticketTypeEnum, randomGenerater.nextInt( i ) + 1 ) );
  }

  public String getRandomDescriptionElement2( TicketTypeEnum ticketTypeEnum )
  {
    int i = getByTypeCount( ticketTypeEnum );
    return getText2( new DescriptionTemplatePK( ticketTypeEnum, randomGenerater.nextInt( i ) + 1 ) );
  }

  private int getByTypeCount( final TicketTypeEnum ticketTypeEnum )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Criteria criteria = session.createCriteria( DescriptionTemplate.class );
    criteria.add( Restrictions.eq( "id.type", ticketTypeEnum ) );
    Number count = (Number) criteria.setProjection( Projections.count( "id.id" ) ).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return count != null ? count.intValue() + 1 : 0;
  }


  public String getText1( final DescriptionTemplatePK pk )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    DescriptionTemplate descriptionTemplate = (DescriptionTemplate) session.get( DescriptionTemplate.class, pk );
    session.getTransaction().commit();
    return descriptionTemplate != null ? descriptionTemplate.getText1() : "";
  }

  public String getText2( final DescriptionTemplatePK pk )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    DescriptionTemplate descriptionTemplate = (DescriptionTemplate) session.get( DescriptionTemplate.class, pk );
    session.getTransaction().commit();
    return descriptionTemplate != null ? descriptionTemplate.getText2() : "";
  }
}
