package com.tombeadman.tdesk.util.jira;

import com.atlassian.event.api.EventPublisher;
import com.atlassian.httpclient.apache.httpcomponents.DefaultHttpClient;
import com.atlassian.httpclient.api.Request;
import com.atlassian.httpclient.api.factory.HttpClientOptions;
import com.atlassian.httpclient.spi.ThreadLocalContextManagers;
import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousHttpClientFactory;
import com.atlassian.jira.rest.client.internal.async.AtlassianHttpClientDecorator;
import com.atlassian.jira.rest.client.internal.async.DisposableHttpClient;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.util.concurrent.Effect;

import java.io.File;
import java.net.URI;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Created by Tom.Beadman on 12/12/14.
 */

public class CustomAsynchronousHttpClientFactory extends AsynchronousHttpClientFactory
{
  public DisposableHttpClient createClient( final URI serverUri, final AuthenticationHandler authenticationHandler,
                                            final int timeout )
  {
    final HttpClientOptions options = new HttpClientOptions();
    options.setSocketTimeout( timeout, TimeUnit.SECONDS );
    options.setRequestPreparer( new Effect<Request>()
    {
      @Override
      public void apply( final Request request )
      {
        authenticationHandler.configure( request );
      }
    } );

    final DefaultHttpClient defaultHttpClient = new DefaultHttpClient( new NoOpEventPublisher(),
                                                                       new RestClientApplicationProperties( serverUri ),
                                                                       ThreadLocalContextManagers.noop(), options );

    return new AtlassianHttpClientDecorator( defaultHttpClient )
    {
      @Override
      public void destroy()
        throws Exception
      {
        defaultHttpClient.destroy();
      }
    };
  }

  private static class NoOpEventPublisher implements EventPublisher
  {
    @Override
    public void publish( Object o )
    {
    }


    @Override
    public void register( Object o )
    {
    }

    @Override
    public void unregister( Object o )
    {
    }

    @Override
    public void unregisterAll()
    {
    }
  }

  private static class RestClientApplicationProperties implements ApplicationProperties

  {
    private final String baseUrl;

    private RestClientApplicationProperties( URI jiraURI )
    {
      this.baseUrl = jiraURI.getPath();
    }

    @Override
    public String getBaseUrl()
    {
      return baseUrl;
    }

    @Override
    public String getDisplayName()
    {
      return "Atlassian JIRA Rest Java Client";
    }

    @Override
    public String getVersion()
    {
      return null;
    }

    @Override
    public Date getBuildDate()
    {
      // TODO implement using MavenUtils, JRJC-123
      throw new UnsupportedOperationException();
    }

    @Override
    public String getBuildNumber()
    {
      // TODO implement using MavenUtils, JRJC-123
      return String.valueOf( 0 );
    }

    @Override
    public File getHomeDirectory()
    {
      return new File( "." );
    }

    @Override
    public String getPropertyValue( final String s )
    {
      throw new UnsupportedOperationException( "Not implemented" );
    }
  }
}