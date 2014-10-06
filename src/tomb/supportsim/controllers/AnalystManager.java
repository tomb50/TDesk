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

  public static void recruitAnalyst( final String name, final RoleEnum role, final WorkingStateEnum workingState )
  {
    final Session session = HibernateUtil.beginTransaction();
    Analyst newAnalyst = new Analyst();
    newAnalyst.setName( name );
    newAnalyst.setRole( role );
    newAnalyst.setState( workingState );
    session.save( newAnalyst );
    HibernateUtil.commitAndClose( session );
  }

  public static void updateAnalyst( final int id, final String name, final RoleEnum role, final WorkingStateEnum workingState )
  {
    final Session session = HibernateUtil.beginTransaction();
    Analyst analyst = (Analyst) session.get( Analyst.class, id );
    analyst.setName( name );
    analyst.setRole( role );
    analyst.setState( workingState );
    session.save( analyst );
    HibernateUtil.commitAndClose( session );
  }

  public static void clockInAnalyst( int id )
  {
    if ( getAnalyst( id ) != null )
    {
      final Session session = HibernateUtil.beginTransaction();
      Analyst analyst = (Analyst) session.get( Analyst.class, id );
      analyst.setState( WorkingStateEnum.WORKING );
      session.save( analyst );
      HibernateUtil.commitAndClose( session );
    }
  }

  public static void clockOutAnalyst( int id )
  {
    if ( getAnalyst( id ) != null )
    {
      final Session session = HibernateUtil.beginTransaction();
      Analyst analyst = (Analyst) session.get( Analyst.class, id );
      analyst.setState( WorkingStateEnum.NOTWORKING );
      session.save( analyst );
      HibernateUtil.commitAndClose( session );
    }
  }

  public static void deleteAnalyst( final int id )
  {
    if ( getAnalyst( id ) != null )
    {
      HibernateUtil.deleteEntity( Analyst.class, id );
    }
  }

  private static Analyst getAnalyst( int id )
  {
    return Analyst.class.cast( HibernateUtil.getEntity( Analyst.class, id ) );
  }
}
