package tomb.supportsim.util;

import tomb.supportsim.app.TDeskApp;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 21:20
 */
public class LinkUtil
{

  //Zendesk
  private static final String zendeskDomain =
    (String) TDeskApp.getInstance().getProperties().get( PropertyKeys.ZENDESK_SUBDOMAIN );
  private static final String zendeskBaseHttpUrl = "http://".concat( zendeskDomain ).concat( ".zendesk.com" );

  //Jira
  private static final String jiraDomain =
    (String) TDeskApp.getInstance().getProperties().get( PropertyKeys.JIRA_URL );
  private static final String jiraBrowseUrl = jiraDomain.concat( "/browse/" );



  public static String getTicketLink( final Long id )
  {
    return zendeskBaseHttpUrl.concat( "/tickets/".concat( String.valueOf( id ) ) );
  }

  public static String getUserLink( final Long id )
  {
    return zendeskBaseHttpUrl.concat( "/users/".concat( String.valueOf( id ) ) );
  }

  public static String getTopicLink( final Long id )
  {
    return zendeskBaseHttpUrl.concat( "/entries/".concat( String.valueOf( id ) ) );
  }

  public static String getJiraBrowseLink(final String key)
  {
    return jiraBrowseUrl.concat( key );
  }
}
