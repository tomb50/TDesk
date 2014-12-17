package tomb.supportsim.control.imports;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import tomb.supportsim.util.jira.JiraBatchImporter;

import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 14/12/14 Time: 14:58
 */
public class JiraImporter extends Importer
{
  final JiraRestClient jiraRestClient;
  private JiraBatchImporter jiraBatchImporter;

  public JiraImporter( final JiraRestClient jiraRestClient )
  {
    this.jiraRestClient = jiraRestClient;
    jiraBatchImporter = new JiraBatchImporter( jiraRestClient );
  }

  @Override
  public void run()
  {
    setActive( true );
    long time = System.currentTimeMillis();
    importJiraData();
    System.out.println("All data imported from JIRA");
    System.out.println( ( "JIRA - Time taken: " + (System.currentTimeMillis() - time ) / 1000) );
    setActive( false );
  }

  public void importJiraData()
  {

    //persist Spaces

    System.out.println( "Importing bugs from JIRA" );
    saveJiraBugs();

    //persist Manuals
    System.out.println( "Importing features from JIRA" );
    saveJiraFeatures();
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
}
