package tomb.supportsim.view;

import tomb.supportsim.controllers.AnalystReporter;
import tomb.supportsim.controllers.CustomerReporter;
import tomb.supportsim.controllers.DescriptionTemplateReporter;
import tomb.supportsim.controllers.TicketReporter;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.Customer;
import tomb.supportsim.models.DescriptionTemplate;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.RoleEnum;
import tomb.supportsim.models.enums.TicketStateEnum;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 25/08/2014 Time: 17:53
 */
public class ViewHelper
{
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

  public static List<Customer> getAllCustomers()
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

  public static List<DescriptionTemplate> getAllDescriptionTemplates()
  {
    return DescriptionTemplateReporter.getAllDescriptionTemplates();
  }

  public static List<List<Analyst>> getActiveAnalystLol()
  {
    return AnalystReporter.getActiveLoL();
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

  public static Integer getTicketCountByState( final TicketStateEnum ticketStateEnum )
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

  public static Integer getTicketCount(final int analystId, final TicketStateEnum ticketStateEnum)
  {
    return TicketReporter.getTicketCount( analystId, ticketStateEnum );
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

}
