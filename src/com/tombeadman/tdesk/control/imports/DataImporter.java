package com.tombeadman.tdesk.control.imports;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.tombeadman.screensteps.ScreenSteps;
import org.zendesk.client.v2.Zendesk;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 10/10/2014 Time: 13:30
 */
public class DataImporter
{
  final ZendeskImporter zendeskImporter;
  final ScreenStepsImporter screenStepsImporter;
  final JiraImporter jiraImporter;

  public DataImporter( final Zendesk zendesk, final ScreenSteps screenSteps, final JiraRestClient jiraClient )
  {
    zendeskImporter = new ZendeskImporter( zendesk );
    screenStepsImporter = new ScreenStepsImporter( screenSteps );
    jiraImporter = new JiraImporter( jiraClient );
  }

  public void fullImport()
  {
    zendeskImporter.start();
    screenStepsImporter.start();
    jiraImporter.start();
  }

  public boolean isImporting()
  {
    return zendeskImporter.isActive() || screenStepsImporter.isActive() || jiraImporter.isActive();
  }
}
