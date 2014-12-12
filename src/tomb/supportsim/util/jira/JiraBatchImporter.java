package tomb.supportsim.util.jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import tomb.supportsim.control.Cache;

import java.net.URISyntaxException;

/**
 * Created by Tom.Beadman on 12/9/14.
 */

public class JiraBatchImporter
{
  final SearchRestClient searchRestClient;

  public JiraBatchImporter( final JiraRestClient restClient )
  {
    this.searchRestClient = restClient.getSearchClient();
  }

  public void importBugs()
    throws URISyntaxException
  {
    int maxResults = 50;
    int startingIndex = 0;
    int totalResult = 0;

    SearchResult result;

    while ( totalResult == 0 || ( startingIndex < totalResult ) )
    {
      try
      {
        result = searchRestClient.searchJql( "type = bug", maxResults, startingIndex, null ).claim();
      }
      catch ( RuntimeException e )
      {
        e.printStackTrace();
        continue;
      }
      totalResult = result.getTotal();

      Iterable<Issue> issues = result.getIssues();
      for ( Issue issue : issues )
      {
        Cache.getInstance().insertBug( issue );
      }
      startingIndex += maxResults;
    }
  }

  public void importFeatures()
    throws URISyntaxException
  {
    int maxResults = 50;
    int startingIndex = 0;
    int totalResult = 0;

    SearchResult result;

    while ( totalResult == 0 || ( startingIndex < totalResult ) )
    {
      try
      {
        result = searchRestClient.searchJql( "type = feature", maxResults, startingIndex, null ).claim();
      }
      catch ( RuntimeException e )
      {
        e.printStackTrace();
        continue;
      }
      totalResult = result.getTotal();

      Iterable<Issue> issues = result.getIssues();
      for ( Issue issue : issues )
      {
        Cache.getInstance().insertFeature( issue );
      }
      startingIndex += maxResults;
    }
  }
}