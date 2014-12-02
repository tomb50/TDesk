package tomb.supportsim.app;

import com.tombeadman.screensteps.ScreenSteps;
import com.tombeadman.screensteps.model.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zendesk.client.v2.Zendesk;
import tomb.supportsim.control.Cache;
import tomb.supportsim.view.ViewHelper;

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
    throws IOException
  {
    /*TDeskApp tDeskApp = new TDeskApp();
    tDeskApp.dataImporter.importScreenstepsData();
    for ( Manual manual : ViewHelper.getManuals() )
    {
      for ( Chapter chapter  : manual.getChapters() )
      {
        for ( Lesson thinLesson : chapter.getLessons() )
        {
          String spaceName = manual.getSpace().getTitle();
          String manualName = manual.getTitle();
          String chapterName = chapter.getTitle();
          String title = thinLesson.getTitle();
          String url = thinLesson.getUrl();

          System.out.println(spaceName + manualName + chapterName + title + url);

        }
      }

    }*/
  }

  private static TDeskApp instance;
  private static boolean running = false;
  private Properties properties;


  public Zendesk getZd()
  {
    return zd;
  }

  private Zendesk zd;
  private ScreenSteps ss;
  private DataImporter dataImporter;

  private TDeskApp() throws IOException
  {
    properties = loadProperties();
    zd = configureZendesk();
    ss = configureScreenSteps();
    dataImporter = new DataImporter( zd,ss);
  }

  private ScreenSteps configureScreenSteps()
  {
    final String user = properties.getProperty( SCREENSTEPS_USER );
    final String password = properties.getProperty( SCREENSTEPS_PASSWORD );
    final String domain = properties.getProperty( SCREENSTEPS_DOMAIN );
    return new ScreenSteps( user,password,domain );
  }

  private Zendesk configureZendesk()
  {
    final String domain =
      "https://".concat( properties.getProperty( ZENDESK_SUBDOMAIN ) ).concat( ".zendesk.com" );
    String user = properties.getProperty( ZENDESK_USER );
    String password = properties.getProperty( ZENDESK_PASSWORD );
    return new Zendesk.Builder( domain )
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
