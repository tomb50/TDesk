package tomb.supportsim.control.imports;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.tombeadman.screensteps.ScreenSteps;
import org.zendesk.client.v2.Zendesk;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 10/10/2014 Time: 13:30
 */
public class DataImporter
{
  private Zendesk zendesk;
  private ScreenSteps screenSteps;
  private JiraRestClient jiraClient;

  public DataImporter( final Zendesk zendesk, final ScreenSteps screenSteps, final JiraRestClient jiraClient )
  {
    this.zendesk = zendesk;
    this.screenSteps = screenSteps;
    this.jiraClient = jiraClient;
  }

  public void fullImport()
  {
    ZendeskImporter zendeskImporter = new ZendeskImporter( zendesk );
    zendeskImporter.start();

    ScreenStepsImporter screenStepsImporter = new ScreenStepsImporter( screenSteps );
    screenStepsImporter.start();

    JiraImporter jiraImporter = new JiraImporter( jiraClient );
    jiraImporter.start();

    //Back up data to disk - for dev
    DataSerializer dataSerializer = new DataSerializer();
    dataSerializer.serializeData();
  }
}
