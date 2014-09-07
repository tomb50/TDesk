package tomb.supportsim.app;

import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tomb.supportsim.connection.HibernateUtil;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 06/08/2014 Time: 18:22
 */
public class SupportSimApp
{

  public static SupportSimApp instance;
  public static boolean running = false;

  private SupportSimApp()
  {
  }

  public static SupportSimApp getInstance()
  {
    if ( instance == null )
    {
      instance = new SupportSimApp();
    }
    return instance;
  }

  public void start( final boolean flushTickets )
  {
    if ( !running )
    {
      //Load scheduled tasks
      new ClassPathXmlApplicationContext( "Spring-TaskScheduler.xml" );
      if ( flushTickets ) deleteAllTickets();
      running = true;
    }
  }


  public void deleteAllTickets()
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.getTransaction().begin();
    session.createQuery( "DELETE FROM SupportTicket" ).executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
