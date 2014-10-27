package tomb.supportsim.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zendesk.client.v2.Zendesk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static tomb.supportsim.util.PropertyKeys.*;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 06/08/2014 Time: 18:22
 */
public class TDeskApp
{

  /*
   * For non-web server testing.
   */
  public static void main( String[] args )
  {
    TDeskApp tDeskApp = TDeskApp.getInstance();
    tDeskApp.start();
    System.exit( 0 );
  }

  private static TDeskApp instance;
  private static boolean running = false;
  private Properties properties;


  public Zendesk getZd()
  {
    return zd;
  }

  private Zendesk zd;
  DataImporter dataImporter;

  private TDeskApp() throws IOException
  {
    properties = loadProperties();
    configureZendesk();
    dataImporter = new DataImporter( zd );
  }

  private void configureZendesk()
  {
    final String domain =
      "https://".concat( properties.getProperty( ZENDESK_SUBDOMAIN ) ).concat( ".zendesk.com" );
    String user = properties.getProperty( ZENDESK_USER );
    String password = properties.getProperty( ZENDESK_PASSWORD );
    zd = new Zendesk.Builder( domain )
      .setUsername( user )
      .setPassword( password ) // or we can do .setToken("...")  instead
      .build();
  }

  private Properties loadProperties() throws IOException
  {
    Properties properties = new Properties(  );
    final String PROPERTY_FILE = "server.properties";
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream( PROPERTY_FILE );
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

  public static TDeskApp getInstance()
  {
    if ( instance == null )
    {
      try
      {
        instance = new TDeskApp();
      }
      catch(IOException io)
      {
        System.exit( 1 ); //Can not continue
      }
    }
    return instance;
  }

  public void start()
  {
    if ( !running )
    {
      running = true;

      dataImporter.fullImport();

      System.out.println( "Setting up scheudling" );
      new ClassPathXmlApplicationContext( "Spring-TaskScheduler.xml" );
    }
  }


}
