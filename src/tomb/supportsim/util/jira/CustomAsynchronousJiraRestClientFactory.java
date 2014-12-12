package tomb.supportsim.util.jira;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import java.net.URI;


/**
 * Created by Tom.Beadman on 12/12/14.
 */

public class CustomAsynchronousJiraRestClientFactory extends AsynchronousJiraRestClientFactory
{

  public JiraRestClient create( final URI serverUri, final AuthenticationHandler authenticationHandler, final int timeout )
  {
    final DisposableHttpClient httpClient = new CustomAsynchronousHttpClientFactory()
      .createClient( serverUri, authenticationHandler, timeout );

    return new AsynchronousJiraRestClient( serverUri, httpClient );
  }

  public JiraRestClient createWithBasicHttpAuthentication( final URI serverUri, final String username,
                                                           final String password, final int timeout )
  {
    return create( serverUri, new BasicHttpAuthenticationHandler( username, password ), timeout );
  }

  @Override
  public JiraRestClient create( final URI serverUri, final HttpClient httpClient )
  {
    final DisposableHttpClient disposableHttpClient =
      new CustomAsynchronousHttpClientFactory().createClient( httpClient );
    return new AsynchronousJiraRestClient( serverUri, disposableHttpClient );
  }
}