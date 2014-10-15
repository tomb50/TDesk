package tomb.supportsim.app;

import org.zendesk.client.v2.Zendesk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static tomb.supportsim.util.PropertyKeys.*;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 06/08/2014 Time: 18:22
 */
public class SupportSimApp
{

  /*
   * For non-web server testing.
   */
  public static void main( String[] args )
  {
    SupportSimApp supportSimApp = SupportSimApp.getInstance();
    supportSimApp.start();
    System.exit( 0 );
  }

  private static SupportSimApp instance;
  private static boolean running = false;
  private Properties properties;
  Zendesk zd;
  DataImporter dataImporter;

  private SupportSimApp() throws IOException
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

  public void start( )
  {
    if ( !running )
    {
      running = true;
      //new ClassPathXmlApplicationContext( "Spring-TaskScheduler.xml" );
     dataImporter.fullImport();


    }
  }


}
