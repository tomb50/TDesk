package com.tombeadman.tdesk.util;

import com.tombeadman.tdesk.app.TDeskApp;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 27/10/14 Time: 21:20
 */
public class LinkUtil
{

  //Zendesk
  private static final String zendeskDomain = getProperty( PropertyKeys.ZENDESK_SUBDOMAIN, null );
  private static final String zendeskBaseHttpUrl = "http://".concat( zendeskDomain ).concat( ".zendesk.com" );
  private static final String zendeskBaseViewUrl = zendeskBaseHttpUrl.concat( "/agent/filters/" );


  /* Hardcode these ids to save having to (at time of writing) implement the (unimplemnted) View model in the
  Zendesk API for the sake of getting the URLs URL. These can be overwritten in the server.properties*/
  private static final String unAssignedFirstlineViewId = getProperty( PropertyKeys.ZENDESK_VIEWID_FL, "50074472" );
  private static final String unAssignedJavaViewId = getProperty( PropertyKeys.ZENDESK_VIEWID_JAVA, "49978101" );
  private static final String unAssignedCharacterViewId =
    getProperty( PropertyKeys.ZENDESK_VIEWID_CHARACTER, "50074832" );
  private static final String unAssignedDevelopmentViewId =
    getProperty( PropertyKeys.ZENDESK_VIEWID_DEVELOPMENT, "49978111" );
  private static final String unAssignedProjectViewId = getProperty( PropertyKeys.ZENDESK_VIEWID_PROJECTS, "50074842" );
  private static final String unAssignedBIViewId = getProperty( PropertyKeys.ZENDESK_VIEWID_BI, "50074852" );

  //Jira
  private static final String jiraDomain = getProperty( PropertyKeys.JIRA_URL, null );
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

  public static String getJiraBrowseLink( final String key )
  {
    return jiraBrowseUrl.concat( key );
  }

  public static String getUnassignedFLViewLink()
  {
    return zendeskBaseViewUrl.concat( unAssignedFirstlineViewId );
  }

  public static String getUnassignedJavaViewLink()
  {
    return zendeskBaseViewUrl.concat( unAssignedJavaViewId );
  }

  public static String getUnAssignedCharacterLink()
  {
    return zendeskBaseViewUrl.concat( unAssignedCharacterViewId );
  }

  public static String getUnAssignedProjectLink()
  {
    return zendeskBaseViewUrl.concat( unAssignedProjectViewId );
  }

  public static String getUnAssignedDevelopmentLink()
  {
    return zendeskBaseViewUrl.concat( unAssignedDevelopmentViewId );
  }

  public static String getUnassignedBIViewLink()
  {
    return zendeskBaseViewUrl.concat( unAssignedBIViewId );
  }


  private static String getProperty( final String propertyKey, final String defaultVal )
  {
    final String value = (String) TDeskApp.getInstance().getProperties().get( propertyKey );
    return isStringEmpty( value ) ? defaultVal : value;
  }

  public static boolean isStringEmpty( String s )
  {
    return s == null || s.length() < 1;
  }
}
