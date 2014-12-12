package tomb.supportsim.app;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.tombeadman.screensteps.ScreenSteps;
import com.tombeadman.screensteps.model.*;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.*;
import tomb.supportsim.control.Cache;
import tomb.supportsim.models.ConvertUtil;
import tomb.supportsim.util.jira.JiraBatchImporter;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 10/10/2014 Time: 13:30
 */
public class DataImporter
{
  private Zendesk zendesk;
  private ScreenSteps screenSteps;
  private JiraRestClient jiraClient;
  private JiraBatchImporter jiraBatchImporter;

  public DataImporter( final Zendesk zendesk, final ScreenSteps screenSteps, final JiraRestClient jiraClient )
  {
    this.zendesk = zendesk;
    this.screenSteps = screenSteps;
    this.jiraClient = jiraClient;
    jiraBatchImporter = new JiraBatchImporter( jiraClient );
  }


  protected void fullImport()
  {
    importZendeskData();
    importScreenstepsData();
    importJiraData();
  }

  public void importJiraData()
  {

    //persist Spaces
    long time = System.currentTimeMillis();
    System.out.println( "Importing bugs from JIRA" );
    saveJiraBugs();
    System.out.println( "Jira bugs saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist Manuals
    System.out.println( "Importing features from JIRA" );
    saveJiraFeatures();
    System.out.println( "Jira features saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
  }

  private void saveJiraBugs()
  {
    try
    {
      jiraBatchImporter.importBugs();
    }
    catch ( URISyntaxException e )
    {
      e.printStackTrace();
    }
  }

  private void saveJiraFeatures()
  {
    try
    {
      jiraBatchImporter.importFeatures();
    }
    catch ( URISyntaxException e )
    {
      e.printStackTrace();
    }
  }

  public void importScreenstepsData()
  {
    //persist Spaces
    long time = System.currentTimeMillis();
    System.out.println( "Importing Spaces from Screensteps" );
    saveSpaces();
    System.out.println( "Spaces saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist Manuals
    System.out.println( "Importing Manuals from Screensteps" );
    saveManuals();
    System.out.println( "Spaces saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );


    //testMethod();

    //persist Lessons
    //System.out.println( "Importing Lessons from Screensteps" );
    //saveLessons();
    //System.out.println( "Spaces saved" );
    //System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    //time = System.currentTimeMillis();
  }

  /*private void testMethod()
  {
    Map<String ,Manual> manualMap = Cache.getInstance().getManualMap();

    System.out.println(manualMap.size());
    for(Map.Entry <String,Manual> entry : manualMap.entrySet())
    {
      for( Chapter chapter : entry.getValue().getChapters())
      {
        for (Lesson lesson : chapter.getLessons())
        {
          System.out.println(lesson.getTitle() + "-" + lesson.getUrl());
        }
    }


  }

}   */

  /*
   * This methods pulles back all Manual data for each space. Space data is pulled from the Cache
    * i.e. Space data must be pulled before this is executed
   */
  private void saveManuals()
  {
    for ( Map.Entry<String, Space> entry : Cache.getInstance().getSpaceMap().entrySet() )
    {
      Space space = entry.getValue();
      {
        for ( Asset asset : space.getAssets() )
        {
          if ( asset.getAssetType().equals( "Manual" ) )
          {
            System.out.println( "saving" + space.getId() + "-" + asset.toString() );
            final Manual manual = screenSteps.getManual( space.getId(), asset.getId() );
            Cache.getInstance().insertManual( manual );
          }
        }
      }
    }
  }

  /*
  * This call is in two parts due to Screensteps API, the first returns a list of Space header info.
  * We then use the retrieved Space Id to retrieve the full data for each Space.
   */
  private void saveSpaces()
  {
    final Spaces spaces = screenSteps.getSpaces();
    for ( Space space : spaces.getSpaces() )
    {
      Space fullSpace = screenSteps.getSpace( space.getId() );
      Cache.getInstance().insertSpace( fullSpace );
    }
  }

  private void importZendeskData()
  {

    long time = System.currentTimeMillis();
    //persist Users
    System.out.println( "Importing Users from Zendesk" );
    saveUsers();
    System.out.println( "Users saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist tickets
    System.out.println( "Importing Tickets from Zendesk" );
    saveTickets();
    System.out.println( "Tickets saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist Organisations
    System.out.println( "Importing Organisations from ZenDesk" );
    saveOrganisations();
    System.out.println( "Organisations saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist GroupMemberships
    System.out.println( "Importing Group Memberships from ZenDesk" );
    saveGroupMemberships();
    System.out.println( "Memberships saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();


    //persist GroupMemberships
    System.out.println( "Importing Groups from ZenDesk" );
    saveGroups();
    System.out.println( "Memberships saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist Forums
    System.out.println( "Importing Forums from ZenDesk" );
    saveForums();
    System.out.println( "Forums saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
    time = System.currentTimeMillis();

    //persist Topics
    System.out.println( "Importing Topics from ZenDesk" );
    saveTopics();
    System.out.println( "Topics saved" );
    System.out.println( ( System.currentTimeMillis() - time ) / 1000 );
  }

  private void saveGroups()
  {
    for ( Group group : zendesk.getGroups() )
    {
      Cache.getInstance().insertGroup( group );
    }
  }

  private void saveTickets()
  {

    for ( Ticket ticket : zendesk.getTickets() )
    {
      Cache.getInstance().insertTicket( ConvertUtil.toTicket( ticket ) );
    }
  }

  private void saveUsers()
  {
    for ( final User user : zendesk.getUsers() )
    {
      if ( user.getActive() )
      {
        Cache.getInstance().insertUser( ConvertUtil.toUser( user ) );
      }
    }
  }

  private void saveOrganisations()
  {
    for ( final Organization organization : zendesk.getOrganizations() )
    {
      Cache.getInstance().insertOrganisation( ConvertUtil.toOrganisation( organization ) );
    }
  }

  private void saveGroupMemberships()
  {
    for ( GroupMembership groupMembership : zendesk.getGroupMemberships() )
    {
      Cache.getInstance().insertGroupMembership( groupMembership );
    }
  }

  private void saveForums()
  {
    for ( Forum forum : zendesk.getForums() )
    {
      Cache.getInstance().insertForum( ConvertUtil.toForum( forum ) );
    }
  }

  private void saveTopics()
  {
    for ( Topic topic : zendesk.getTopics() )
    {

      Cache.getInstance().insertTopic( ConvertUtil.toTopic( topic ) );
    }
  }
}
