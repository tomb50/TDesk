package tomb.supportsim.control.imports;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import tomb.supportsim.util.jira.JiraBatchImporter;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    logger.info( "All data imported from JIRA - Time taken: " + ( System.currentTimeMillis() - time ) / 1000 );
    setActive( false );
  }

  public void importJiraData()
  {
    saveJiraBugs();
    saveJiraFeatures();
  }

  private void saveJiraBugs()
  {
    logger.info( "Importing bugs from JIRA" );
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
    logger.info( "Importing features from JIRA" );
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
