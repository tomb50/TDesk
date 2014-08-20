package tomb.supportsim.app;

import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tomb.supportsim.connection.HibernateUtil;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 06/08/2014 Time: 18:22
 */
public class SupportSimApp
{

  public static void main( String[] args )
  {
    SupportSimApp supportSimApp = new SupportSimApp();
    supportSimApp.deleteAllTickets();
    supportSimApp.start();
  }

  private void start()
  {
    //Load scheduled tasks
    new ClassPathXmlApplicationContext( "Spring-TaskScheduler.xml" );
  }


  private void deleteAllTickets()
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.getTransaction().begin();
    session.createQuery( "DELETE FROM SupportTicket" ).executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
