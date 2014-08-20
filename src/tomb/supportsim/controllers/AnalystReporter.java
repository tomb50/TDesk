package tomb.supportsim.controllers;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.enums.RoleEnum;
import tomb.supportsim.models.enums.WorkingStateEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 05/08/2014 Time: 18:52
 */
public class AnalystReporter
{

  public static void main( String[] args )
  {
    AnalystReporter analystReporter = new AnalystReporter();
    List working = analystReporter.getWorkingAnalysts();
    List notWorking = analystReporter.getNotWorkingAnalysts();

    System.out.println( "WORKING" + working.toString() );
    System.out.println( "****************" );
    System.out.println( "NOT WORKING" + notWorking.toString() );
  }

  private List getNotWorkingAnalysts()
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.getTransaction().begin();
    Criteria criteria = session.createCriteria( Analyst.class );
    criteria.add( Restrictions.eq( "state", WorkingStateEnum.NOTWORKING ) );
    List analysts = criteria.list();
    session.getTransaction().commit();
    session.close();
    return analysts;
  }

  private void getAllAnalysts()
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.getTransaction().begin();
    Criteria criteria = session.createCriteria( Analyst.class );
    criteria.add( Restrictions.eq( "name", "Tom Beadman" ) );
    List analyst = criteria.list();

    System.out.println( analyst );
    boolean found = true;
  }

  private List<Analyst> getWorkingAnalysts()
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.getTransaction().begin();
    Criteria criteria = session.createCriteria( Analyst.class );
    criteria.add( Restrictions.eq( "state", WorkingStateEnum.WORKING ) );
    List analysts = criteria.list();
    session.getTransaction().commit();
    session.close();
    return analysts;
  }

  public List<Analyst> getSuitableFreeAnalysts( RoleEnum role )
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.getTransaction().begin();
    Criteria criteria = session.createCriteria( Analyst.class );
    criteria.add( Restrictions.eq( "state", WorkingStateEnum.WORKING ) );
    criteria.add( Restrictions.or( Restrictions.eq( "role", RoleEnum.ALL ), Restrictions.eq( "role", role ) ) );
    List analysts = criteria.list();
    session.getTransaction().commit();
    session.close();
    return analysts;
  }
}
