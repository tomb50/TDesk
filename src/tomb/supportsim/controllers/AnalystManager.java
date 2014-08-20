package tomb.supportsim.controllers;

import org.hibernate.Session;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.enums.RoleEnum;
import tomb.supportsim.models.enums.WorkingStateEnum;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 02/08/2014 Time: 19:30
 */
public class AnalystManager
{

  public static void main( String[] args )
  {
    AnalystManager analystManager = new AnalystManager();
    Analyst robb = analystManager.getAnalyst( 1 );

    boolean caught = true;

    Analyst none = analystManager.getAnalyst( 2 );

    boolean caught2 = true;

    analystManager.deleteAnalyst( 2 );
  }

  public void recruitAnalyst( final String name, final RoleEnum role, final WorkingStateEnum workingState )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Analyst newAnalyst = new Analyst();
    newAnalyst.setName( name );
    newAnalyst.setRole( role );
    newAnalyst.setState( workingState );
    session.save( newAnalyst );
    session.getTransaction().commit();
    session.close();
  }

  public void updateAnalyst( final int id, final String name, final RoleEnum role, final WorkingStateEnum workingState )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Analyst analyst = (Analyst) session.get( Analyst.class, id );
    analyst.setName( name );
    analyst.setRole( role );
    analyst.setState( workingState );
    session.save( analyst );
    session.getTransaction().commit();
    session.close();
  }

  public void clockInAnalyst( int id )
  {
    if ( getAnalyst( id ) != null )
    {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      Analyst analyst = (Analyst) session.get( Analyst.class, id );
      analyst.setState( WorkingStateEnum.WORKING );
      session.getTransaction().commit();
      session.close();
    }
  }

  public void clockOutAnalyst( int id )
  {
    if ( getAnalyst( id ) != null )
    {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      Analyst analyst = (Analyst) session.get( Analyst.class, id );
      analyst.setState( WorkingStateEnum.NOTWORKING );
      session.getTransaction().commit();
      session.close();
    }
  }

  public void deleteAnalyst( final int id )
  {
    if ( getAnalyst( id ) != null )
    {
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      session.delete( session.get( Analyst.class, id ) );
      session.getTransaction().commit();
      session.close();
    }
  }

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
