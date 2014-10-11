package tomb.supportsim.view;

import org.zendesk.client.v2.model.Status;
import tomb.supportsim.controllers.AnalystReporter;
import tomb.supportsim.controllers.CustomerReporter;
import tomb.supportsim.controllers.DescriptionTemplateReporter;
import tomb.supportsim.controllers.TicketReporter;
import tomb.supportsim.models.*;
import tomb.supportsim.models.enums.RoleEnum;
import tomb.supportsim.models.enums.TicketStateEnum;
import tomb.supportsim.models.enums.TicketTypeEnum;
import tomb.supportsim.util.UserOrganisationComparator;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 25/08/2014 Time: 17:53
 */
public class ViewHelper
{

  /*
   *
   * Cache for speed //todo improve cache mechanism
   *
   */
 private static Cache cache = new Cache();



  public static ZDOrganisation getOrganisation(final Long organisationId)
  {
    return cache.getOrganisationMap().get( organisationId );
  }

  public static ZDUser getUser(final Long userId)
  {
    return cache.getUserMap().get( userId );
  }

  public static ZDTicket getTicket(final Long ticketId)
  {
    return cache.getTicketMap().get( ticketId );
  }

  public static Map<Long,ZDOrganisation> getOrganisationMap()
  {
    return cache.getOrganisationMap();
  }

  public static Map<Long,ZDUser> getUserMap()
  {
    return cache.getUserMap();
  }

  public static Map<Long,ZDTicket> getTicketMap()
  {
    return cache.getTicketMap();
  }


  public static List getAnalystAttributes()
  {
    return Arrays.asList( Analyst.class.getDeclaredFields() );
  }

  public static List<Analyst> getAnalysts()
  {
    return AnalystReporter.getAllAnalysts();
  }


  public static List<SupportTicket> getAllTickets()
  {
    return TicketReporter.getAllTickets();
  }


  public static List<Field> getTicketAttributes()
  {
    return Arrays.asList( SupportTicket.class.getDeclaredFields() );
  }

  public static List getAllCustomers()
  {
    final CustomerReporter customerReporter = new CustomerReporter();
    return customerReporter.getAllCustomers();
  }

  public static List<Field> getCustomerAttributes()
  {
    return Arrays.asList( Customer.class.getDeclaredFields() );
  }

  public static List<Field> getDescriptionTemplateAttributes()
  {
    return Arrays.asList( DescriptionTemplate.class.getDeclaredFields() );
  }

  public static List getAllDescriptionTemplates()
  {
    return DescriptionTemplateReporter.getAllDescriptionTemplates();
  }

  public static Map getTicketCountByTypeMap()
  {
    final Map map = new HashMap();
    for ( TicketTypeEnum ticketTypeEnum : TicketTypeEnum.values() )
    {
      map.put( ticketTypeEnum, TicketReporter.getTicketCountByType( ticketTypeEnum ) );
    }
    return map;
  }

  public static Integer getTicketCountByType( final TicketTypeEnum ticketTypeEnum )
  {
    return TicketReporter.getTicketCountByType( ticketTypeEnum );
  }

  public static List getJoinedDetailsForAllTickets()
  {
    return TicketReporter.getJoinedDetailsForAllTickets();
  }


  public static Integer getTotalTicketCount()
  {
    return TicketReporter.getTotalTicketCount();
  }

  public static Integer getTotalOpenTicketCount()
  {
    return TicketReporter.getTotalOpenTicketCount();
  }


  public static Map getClosedTicketCountByAnalystMap()
  {
    Map map = new HashMap<Analyst, Integer>();
    List<Analyst> analystList = AnalystReporter.getAllAnalysts();
    for ( Analyst analyst : analystList )
    {
      List<SupportTicket> ticketList = TicketReporter.getClosedTicketsByAnalyst( analyst );
      map.put( analyst, ticketList.size() );
    }

    return map;
  }

  public static List getJoinedDetailsForNewTickets()
  {
    return TicketReporter.getJoinedDetailsForNewTickets();
  }

  public static Integer getTicketCountByState( final Status ticketStateEnum )
  {
    return TicketReporter.getTicketCountByState( ticketStateEnum );
  }

  private static List<Analyst> getAnalystByRole( final RoleEnum roleEnum )
  {
    return AnalystReporter.getSuitableAnalysts( roleEnum );
  }

  public static List<Analyst> getAnalystInWIP( final RoleEnum roleEnum )
  {
    final List<Analyst> analysts = AnalystReporter.getSuitableAnalysts( roleEnum );
    for ( final Iterator it = analysts.iterator(); it.hasNext(); )
    {
      final Analyst analyst = (Analyst) it.next();
      if ( TicketReporter.getTicketCount( analyst.getId(), TicketStateEnum.WIP ) == 0 )
      {
        it.remove( );
      }
    }

    return analysts;
  }

  public static List getTickets( final int analystId, final TicketStateEnum ticketStateEnum )
  {
    return TicketReporter.getTickets( analystId,ticketStateEnum );
  }

  public static Integer getLargestQueue(final RoleEnum roleEnum)
  {
    return AnalystReporter.getLargestQueue(roleEnum);
  }

  public static String getFreeAnalysts( final RoleEnum roleEnum )
  {
    final StringBuilder sb = new StringBuilder();
    final List<Analyst> analysts = getAnalystByRole( roleEnum );
    for ( final Analyst analyst : analysts )
    {
      final List<SupportTicket> wipTickets = getTickets( analyst.getId(), TicketStateEnum.WIP );
      final List<SupportTicket> queuedTickets = getTickets( analyst.getId(), TicketStateEnum.QUEUED );
      if ( wipTickets.isEmpty() && queuedTickets.isEmpty() ) sb.append( analyst.getName() ).append( ", " );
    }

    //Trim the last comma
    return sb.toString().isEmpty() ? sb.toString() :
           sb.toString().substring( 0, sb.toString().length() - ", ".length() );
  }

  public static boolean roleHasActiveTickets(final RoleEnum roleEnum)
  {
    boolean active = false;
    HashSet<TicketTypeEnum> ticketTypeEnums = getTicketTypes(roleEnum);
    for (final TicketTypeEnum ticketTypeEnum : ticketTypeEnums)
    {
      if(TicketReporter.getTicketCountByTypeAndState( ticketTypeEnum,TicketStateEnum.WIP ) != 0)
      {
        active = true;
      }

    }
   return active;
  }

  private static HashSet<TicketTypeEnum> getTicketTypes( final RoleEnum roleEnum )
  {
    final HashSet<TicketTypeEnum> ticketTypeEnums = new HashSet<>(  );

    switch ( roleEnum )
    {
     case FIRST_LINE:
       ticketTypeEnums.add( TicketTypeEnum.LOCKED_DOCUMENT );
       ticketTypeEnums.add( TicketTypeEnum.SYSTEM_DOWN );
       break;
      case DBA:
        ticketTypeEnums.add( TicketTypeEnum.DBA );
        break;
      case JAVA:
        ticketTypeEnums.add( TicketTypeEnum.JAVA );
        break;
      case ABL:
        ticketTypeEnums.add( TicketTypeEnum.ABL );
        break;

    }
    return ticketTypeEnums;
  }

  public static List<ZDTicket> getTicketByState(final Status status)
  {
    return TicketReporter.getTicketsByState( status );

  }

  public static List<ZDTicket> getOpenTickets()
  {
    return cache.getTickets( Status.OPEN );
  }

  //todo get rid of the literals here
  public static String getTicketLink( final Long id )
  {
    return "http://resultgroup.zendesk.com/tickets/".concat( String.valueOf( id ) );
  }

  public static String getUserLink(final Long id)
  {
    return "http://resultgroup.zendesk.com/users/".concat( String.valueOf( id ) );
  }


  public static List<ZDUser> getCustomers()
  {
    return cache.getUsers();
  }

  public static List<ZDUser> getOrderedCustomers()
  {
    List<ZDUser> users = getCustomers();
    for ( Iterator it = users.iterator(); it.hasNext(); )
    {
      ZDUser user = (ZDUser) it.next();
      if(user.getOrganizationId() == null) it.remove();
    }
    Collections.sort( users, new UserOrganisationComparator() );
    return users;
  }

  public static List<ZDUser> getOrderedCustomersWithPhone()
  {
    List<ZDUser> users = getCustomers();
    for ( Iterator it = users.iterator(); it.hasNext(); )
    {
      ZDUser user = (ZDUser) it.next();
      if(user.getPhone() == null) it.remove();
    }

    Collections.sort( users, new UserOrganisationComparator() );
    return users;
  }

  public static List<ZDUser> getOrderedCustomersWithoutPhone()
  {
    List<ZDUser> users = getCustomers();
    for ( Iterator it = users.iterator(); it.hasNext(); )
    {
      ZDUser user = (ZDUser) it.next();
      if(user.getPhone() != null) it.remove();
    }
    Collections.sort( users, new UserOrganisationComparator() );
    return users;
  }


}
