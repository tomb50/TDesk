package tomb.supportsim.util;

import tomb.supportsim.app.TDeskApp;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 21:20
 */
public class LinkUtil
{
  private static final String domain =
    (String) TDeskApp.getInstance().getProperties().get( PropertyKeys.ZENDESK_SUBDOMAIN );
  private static final String baseHttpUrl = "http://".concat( domain ).concat( ".zendesk.com" );

  public static String getTicketLink( final Long id )
  {
    return baseHttpUrl.concat( "/tickets/".concat( String.valueOf( id ) ) );
  }

  public static String getUserLink( final Long id )
  {
    return baseHttpUrl.concat( "/users/".concat( String.valueOf( id ) ) );
  }

  public static String getTopicLink( final Long id )
  {
    return baseHttpUrl.concat( "/entries/".concat( String.valueOf( id ) ) );
  }
}
