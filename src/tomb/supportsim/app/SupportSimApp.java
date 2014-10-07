package tomb.supportsim.app;

import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tomb.supportsim.connection.HibernateUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 06/08/2014 Time: 18:22
 */
public class SupportSimApp
{

  private static SupportSimApp instance;
  private static boolean running = false;
  private static String PROPERTY_FILE = "server.properties";
  private Properties properties;

  private SupportSimApp() throws IOException
  {
    properties = loadProperties();
  }

  private Properties loadProperties() throws IOException
  {
    Properties properties = new Properties(  );
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE);
    properties.load(inputStream);
    if (inputStream == null) {
      throw new FileNotFoundException("property file '" + PROPERTY_FILE + "' not found in the classpath");
    }
    return properties;
  }

  public Properties getProperties()
  {
    return properties;
  }

  public static SupportSimApp getInstance()
  {
    if ( instance == null )
    {
      try
      {
        instance = new SupportSimApp();
      }
      catch(IOException io)
      {
        System.exit( 1 ); //Can not continue
      }
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


  void deleteAllTickets()
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.getTransaction().begin();
    session.createQuery( "DELETE FROM SupportTicket" ).executeUpdate();
    session.getTransaction().commit();
    session.close();
  }


  public static void main( String[] args )
  {
    SupportSimApp supportSimApp = SupportSimApp.getInstance();
    supportSimApp.start( true );
  }
}
