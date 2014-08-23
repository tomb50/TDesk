package tomb.supportsim.connection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 02/08/2014 Time: 19:24
 */
public class HibernateUtil
{

  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory()
  {
    try
    {
      // Create the SessionFactory from hibernate.cfg.xml
      return new Configuration().configure().buildSessionFactory();
    }
    catch ( Throwable ex )
    {
      // Make sure you log the exception, as it might be swallowed
      System.err.println( "Initial SessionFactory creation failed." + ex );
      throw new ExceptionInInitializerError( ex );
    }
  }

  public static SessionFactory getSessionFactory()
  {
    return sessionFactory;
  }


  public static Object getEntity( final Class entityClass, final Serializable pk )
  {
    final Session session = beginTransaction();
    final Object entity = session.get( entityClass, pk );
    commitAndClose( session );
    return entity;
  }

  public static void deleteEntity( final Class entityClass, final Serializable pk )
  {
    final Session session = beginTransaction();
    session.delete( session.get( entityClass, pk ) );
    commitAndClose( session );
  }

  public static List getEntityList( final Class entityClass )
  {
    final Session session = beginTransaction();
    final Criteria criteria = session.createCriteria( entityClass );
    final List entities = criteria.list();
    commitAndClose( session );
    return entities;
  }


  public static List getEntityList( final Class entityClass, final List<Criterion> restrictions )
  {
    final Session session = beginTransaction();
    final Criteria criteria = session.createCriteria( entityClass );
    for ( Criterion criterion : restrictions )
    {
      criteria.add( criterion );
    }
    final List entities = criteria.list();
    commitAndClose( session );
    return entities;
  }

  public static Integer getEntityCount( final Class entityClass )
  {
    final Session session = beginTransaction();
    final Criteria criteria = session.createCriteria( entityClass );
    final Number count = (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
    commitAndClose( session );
    return count.intValue();
  }

  public static Integer getEntityCount( final Class entityClass, final List<Criterion> restrictions )
  {
    final Session session = beginTransaction();
    final Criteria criteria = session.createCriteria( entityClass );
    for ( Criterion criterion : restrictions )
    {
      criteria.add( criterion );
    }
    final Number count = (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
    commitAndClose( session );
    return count.intValue();
  }

  public static Integer getEntityMax( final Class entityClass, final String id )
  {
    final Session session = beginTransaction();
    final Criteria criteria = session.createCriteria( entityClass );
    final Number max = (Number) criteria.setProjection( Projections.max( id ) ).uniqueResult();
    commitAndClose( session );
    return max.intValue();
  }


  public static Integer getEntityMax( final Class entityClass, final String id, final List<Criterion> restrictions )
  {
    final Session session = beginTransaction();
    final Criteria criteria = session.createCriteria( entityClass );
    for ( Criterion criterion : restrictions )
    {
      criteria.add( criterion );
    }
    final Number max = (Number) criteria.setProjection( Projections.max( id ) ).uniqueResult();
    commitAndClose( session );
    return max.intValue();
  }

  public static void insertEntity( final Object entity )
  {
    final Session session = beginTransaction();
    session.save( entity );
    commitAndClose( session );
  }

  public static void updateEntity( final Object entity )
  {
    final Session session = beginTransaction();
    session.update( entity );
    commitAndClose( session );
  }

  // Convenience methods

  public static Session beginTransaction()
  {
    final Session session = HibernateUtil.getSessionFactory().openSession();
    session.getTransaction().begin();
    return session;
  }

  public static void commitAndClose( final Session session )
  {
    session.getTransaction().commit();
    session.close();
  }
}
