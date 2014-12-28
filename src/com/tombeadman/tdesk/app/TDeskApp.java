package com.tombeadman.tdesk.app;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.tombeadman.tdesk.control.imports.DataSerializer;
import com.tombeadman.screensteps.ScreenSteps;
import com.tombeadman.tdesk.util.PropertyKeys;
import com.tombeadman.tdesk.view.JiraIssueSorter;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zendesk.client.v2.Zendesk;
import com.tombeadman.tdesk.control.imports.DataImporter;
import com.tombeadman.tdesk.control.imports.DataRestorer;
import com.tombeadman.tdesk.util.jirahttp.CustomAsynchronousJiraRestClientFactory;
import com.tombeadman.tdesk.view.ScreenStepsEntrySorter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    TDeskApp tDeskApp = new TDeskApp();
    if ( !running )
    {
      running = true;
      if(local)
      {
        DataRestorer dataRestorer = new DataRestorer();
        dataRestorer.unserializeData();
      }
      else
      {
        tDeskApp.dataImporter.fullImport();
      }
      Logger.getLogger( "app" ).log( Level.INFO, "Initialising Scheduler" );
      new ClassPathXmlApplicationContext( "Spring-TaskScheduler.xml" );
    }
  }

  private static TDeskApp instance;
  private static boolean running = false;
  private Properties properties;
  private static boolean local;


  public Zendesk getZd()
  {
    return zd;
  }

  private Zendesk zd;
  private ScreenSteps ss;
  private JiraRestClient jira;
  private DataImporter dataImporter;

  private TDeskApp() throws IOException
  {
    properties = loadProperties();
    zd = configureZendesk();
    ss = configureScreenSteps();
    jira = configureJira();
    dataImporter = new DataImporter( zd,ss,jira);
    local = isLocal();
  }

  private boolean isLocal()
  {
    String property = properties.getProperty( PropertyKeys.LOCAL_DATA );
    boolean local = Boolean.valueOf( property );
    return local;
  }

  private ScreenSteps configureScreenSteps()
  {
    final String user = properties.getProperty( PropertyKeys.SCREENSTEPS_USER );
    final String password = properties.getProperty( PropertyKeys.SCREENSTEPS_PASSWORD );
    final String domain = properties.getProperty( PropertyKeys.SCREENSTEPS_DOMAIN );
    return new ScreenSteps( user,password,domain );
  }

  private Zendesk configureZendesk()
  {
    final String domain =
      "https://".concat( properties.getProperty( PropertyKeys.ZENDESK_SUBDOMAIN ) ).concat( ".zendesk.com" );
    String user = properties.getProperty( PropertyKeys.ZENDESK_USER );
    String password = properties.getProperty( PropertyKeys.ZENDESK_PASSWORD );
    return new Zendesk.Builder( domain )
      .setUsername( user )
      .setPassword( password ) // or we can do .setToken("...")  instead
      .build();
  }

  private JiraRestClient configureJira()
  {
    final String userName = properties.getProperty( PropertyKeys.JIRA_USERNAME );
    final String passWord = properties.getProperty( PropertyKeys.JIRA_PASSWORD );
    final String URLString = properties.getProperty( PropertyKeys.JIRA_URL );
    final int timeout = Integer.valueOf( properties.getProperty( PropertyKeys.JIRA_TIMEOUT ));
    URI jiraServerUri = null;

    try
    {
      jiraServerUri = new URI(URLString);
    }
    catch ( URISyntaxException e )
    {
      throw new RuntimeException( e );
    }
    CustomAsynchronousJiraRestClientFactory factory = new CustomAsynchronousJiraRestClientFactory();
    JiraRestClient restClient = factory.createWithBasicHttpAuthentication( jiraServerUri, userName, passWord, timeout );

    return restClient;
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
      if(local)
      {
        DataRestorer dataRestorer = new DataRestorer();
        dataRestorer.unserializeData();
      }
      else
      {
        dataImporter.fullImport();

        while ( dataImporter.isImporting() )
        {
          try
          {
            Thread.sleep( 10000 ); //10 secs
          }
          catch ( InterruptedException ex )
          {
            Thread.currentThread().interrupt();
          }
        }

        //Back up data to disk - for dev
        DataSerializer dataSerializer = new DataSerializer();
        dataSerializer.serializeData();
      }
      JiraIssueSorter.run(); // proactively sort a convenience list
      ScreenStepsEntrySorter.run();
      Logger.getLogger(getClass().getName()).log( Level.INFO, "Setting up scheudling" );
      new ClassPathXmlApplicationContext( "Spring-TaskScheduler.xml" );
    }
  }


}
